package com.fairyhawk.common.intercepter;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsStatics;

import com.fairyhawk.common.util.Constant;
import com.fairyhawk.common.util.CookieUtils;
import com.fairyhawk.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 
 * @ClassName  LimitIntercepterForWeb
 * @package com.fairyhawk.common.intercepter
 * @description 前台用户拦截器
 * @author  liuqinggang
 * @Create Date: 2013-3-1 下午12:56:43
 *
 */
public class LimitIntercepterForWeb extends AbstractInterceptor {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2637433140499037249L;

	public String intercept(ActionInvocation actionInvocation) throws Exception {
		//测试，带路径中包含test的通过
		//项目中 具体实现 ：1.是否登录。2.登录信息是否合法 3.请求的路径是否在权限内等。。。
		System.out.println("action invoc...");
		ActionContext actionContext = actionInvocation.getInvocationContext();   
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response= (HttpServletResponse)actionContext.get(StrutsStatics.HTTP_RESPONSE);
		String url =request.getRequestURI();
		//String methord = actionInvocation.getProxy().getMethod();
		String ukey=CookieUtils.getCookie(request, Constant.COOKIE_REMEMBERME_KEY);
		if (StringUtils.isEmpty(ukey)){
		    System.out.println("++ login ukey value:"+ukey);
		    CookieUtils.setCookie(response, Constant.RETURN_URL, url, 1);
			return "login";
		} else {
			return actionInvocation.invoke();
		}
	}
	
}
