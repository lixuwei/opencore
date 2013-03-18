package com.fairyhawk.common.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.fairyhawk.common.entity.JsonEntity;
import com.fairyhawk.common.entity.PageEntity;
import com.fairyhawk.common.service.ConfigService;
import com.fairyhawk.common.util.Constant;
import com.fairyhawk.common.util.CookieUtils;
import com.fairyhawk.entity.user.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -536411481126872624L;

	ActionContext context = ActionContext.getContext();

	/** 分页页面地址及参数 */
	private String pageParms;
	/** log对象 */
	private static final Logger logger = Logger.getLogger(CommonAction.class);
	/** request对象 */
	protected HttpServletRequest servletRequest;
	/** response对象 */
	private HttpServletResponse servletResponse;
	/** session对象 */
	HttpSession session;
	/** 系统配置服务类 */
	private ConfigService configurator;

    @SuppressWarnings("unchecked")
    public JsonEntity json = new JsonEntity();

	private File[] files;
	private String[] filesFileName;
	private String[] filesContentType;
	private String savePath = "/upload";
	private String downloadFileName;
	private InputStream downloadStream;

	private PageEntity page;

	public String getUuid() {
		return UUID.randomUUID().toString();
	}

	
    public User getLoginedUser() {
     return  (User)  getSession(Constant.SYS_USER_SESSION_NAME);
    }
    
	public HttpServletRequest getServletRequest() {
		return (HttpServletRequest) (servletRequest != null ? servletRequest
				: context.get(ServletActionContext.HTTP_REQUEST));
	}

	public void setServletRequest(HttpServletRequest servletRequest) {
		this.servletRequest = servletRequest;
	}

	public HttpServletResponse getServletResponse() {
		return (HttpServletResponse) (servletResponse != null ? servletResponse
				: context.get(ServletActionContext.HTTP_RESPONSE));
	}

	public void setServletResponse(HttpServletResponse servletResponse) {
		this.servletResponse = servletResponse;
	}

	/**
	 * 获取URL及参数
	 * 
	 * @return 类似于a.action?name=tx&age=20
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
    public String getServletRequestUrlParms() {
		// 获得的地址参数，如果没有为空 ，有时是以&结束的
		StringBuffer sbUrlParms = getServletRequest().getRequestURL();
		sbUrlParms.append("?");
		Enumeration parNames = getServletRequest().getParameterNames();
		while (parNames.hasMoreElements()) {
			String parName = parNames.nextElement().toString();
			try {
				sbUrlParms.append(parName).append("=")
				.append(URLEncoder.encode(getServletRequest()
				.getParameter(parName), "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				logger.error("基类获取分页参数错误", e);
				return "";
			}
		}
		return sbUrlParms.toString();
	}

	public String getPageParms() {
        return pageParms;
    }


    public void setPageParms(String pageParms) {
        this.pageParms = pageParms;
    }
    public void setPageParms() {
        this.pageParms =  getServletRequestUrlParms();
    }

	public ConfigService getConfigurator() {
		return configurator;
	}

	public void setConfigurator(ConfigService configurator) {
		this.configurator = configurator;
	}

	/**
	 * 获取当前日期，供freemarker使用
	 * 
	 * @return 当前日期
	 */
	public Date getCurrentDate() {
		return new Date(System.currentTimeMillis());
	}

	public void setSession(String name, Object o) {
		ActionContext.getContext().getSession().put(name, o);
	}

	@SuppressWarnings("unchecked")
	public <T extends Object> T getSession(String name) {
		if (ActionContext.getContext().getSession().get(name) == null) {
			return null;
		} else {
			return (T) ActionContext.getContext().getSession().get(name);
		}
	}

	public String execute() throws Exception {
		if (hasErrors()) {
			LOG.debug("action not executed, field or action errors");
			LOG.debug("Field errors: " + getFieldErrors());
			LOG.debug("Action errors: " + getActionErrors());
			return INPUT;
		}
		return SUCCESS;
	}

	protected void upload(List<String> nameList, List<File> fileList)
			throws Exception {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		for (int i = 0; i < fileList.size(); i++) {
			fos = new FileOutputStream(this.getRealSavePath() + "/"
					+ nameList.get(i));
			fis = new FileInputStream(fileList.get(i));
			byte[] b = new byte[1024];
			while ((fis.read(b)) != -1) {
				fos.write(b);
			}
			fos.close();
			fis.close();
		}
	}

	protected void uploadForWeb(List<String> nameList, List<File> fileList)
			throws Exception {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		for (int i = 0; i < fileList.size(); i++) {
			fos = new FileOutputStream(getRealSavePathForWeb() + "/"
					+ nameList.get(i));
			fis = new FileInputStream(fileList.get(i));
			byte[] b = new byte[1024];
			while ((fis.read(b)) != -1) {
				fos.write(b);
			}
			fos.close();
			fis.close();
		}
	}

	protected String getFileType(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."), fileName.length());
	}

	public String download() throws Exception {
		try {
			downloadStream = new FileInputStream(this.getRealSavePath() + "/"
					+ this.downloadFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "download";
	}

	protected String getRealSavePath() {
		if (this.savePath.indexOf("/upload") == -1) {
			this.savePath = "/upload" + this.savePath;
		}
		String realSavePath = ServletActionContext.getServletContext()
				.getRealPath(this.savePath);
		File file = new File(realSavePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return realSavePath;
	}

	protected String getRealSavePathForWeb() {
		String realSavePath = ServletActionContext.getServletContext()
				.getRealPath(savePath);
		File file = new File(realSavePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return realSavePath;
	}

	protected String validateUpload() {
		if (this.files == null || this.files.length == 0) {
			return "请上传图片！";
		}
		if (!this.validateType()) {
			return "上传的图片必须都是jpg格式，请重新上传！";
		}
		if (!this.validateLength()) {
			return "上传的图片不能大于3M，请重新上传！";
		}
		return "true";
	}

	private boolean validateType() {
		for (int i = 0; i < this.filesContentType.length; i++) {

			if (!("image/pjpeg".equals(filesContentType[i])
					|| "image/gif".equals(this.filesContentType[i])
					|| "image/bmp".equals(this.filesContentType[i])
					|| "image/png".equals(this.filesContentType[i])
					|| "image/jpeg".equals(this.filesContentType[i])
					|| "image/jpg".equals(this.filesContentType[i]) || "image/x-png"
						.equals(this.filesContentType[i])))
				return false;
		}
		return true;
	}

	private boolean validateLength() {
		for (int i = 0; i < this.files.length; i++) {
			if (this.files[i].length() > 3072000)
				return false;
		}
		return true;
	}

	protected String getRealPath(String path) {
		return ServletActionContext.getServletContext().getRealPath(path);
	}
	public  String getIp(HttpServletRequest request) {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0
                || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
                                                            // = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }
	/**
	 * 获取登陆用户的id
	 * 
	 * @return
	 */
	protected int getLoginUserId() {
		String ukey = CookieUtils.getCookie(getServletRequest(),
				Constant.COOKIE_REMEMBERME_KEY);
		if (ukey == null || ukey.trim().equals("")) {
			return 0;
		} else {
			return Integer.valueOf(ukey.split(",")[0]);
		}
	}
	
    public void sendMessage(String content) throws IOException{  
        try{
            this.getServletResponse().setCharacterEncoding("UTF-8");      
            this.getServletResponse().getWriter().write(content);   
        }catch(Exception e){
            logger.error("sendMessage", e);
        }
    } 
	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public InputStream getDownloadStream() {
		return downloadStream;
	}

	public void setDownloadStream(InputStream downloadStream) {
		this.downloadStream = downloadStream;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public PageEntity getPage() {
	    if(getPageParms()==null){
	        this.setPageParms();
	    }
		if (page == null) {
			page = new PageEntity();
		}
		return page;
	}

	public void setPage(PageEntity page) {
		this.page = page;
	}
	 @SuppressWarnings("unchecked")
    public JsonEntity getJson() {
        return json;
    }


    @SuppressWarnings("unchecked")
    public void setJson(JsonEntity json) {
        this.json = json;
    }


    public HttpSession getSession() {
        return  session != null ? session: getServletRequest().getSession();
    }

    public void setSession(HttpSession session) {
        this.session = session;
    }
	
}
