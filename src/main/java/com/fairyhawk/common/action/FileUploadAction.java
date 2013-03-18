package com.fairyhawk.common.action;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * @ClassName  FileUploadAction
 * @package com.fairyhawk.common.action
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-1 下午04:29:28
 * 
 */
public class FileUploadAction extends CommonAction {
    /**
     * 
     */
    private static final long serialVersionUID = -8597202092492029585L;
    private File image; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型

    public String execute() throws Exception {
        String realpath = ServletActionContext.getServletContext().getRealPath("/images");
        System.out.println("realpath: "+realpath);
        if (image != null) {
            File savefile = new File(new File(realpath), imageFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(image, savefile);
            ActionContext.getContext().put("message", "文件上传成功");
        }
        return "success";
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

}
