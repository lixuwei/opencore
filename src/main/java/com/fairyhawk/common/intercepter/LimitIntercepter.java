package com.fairyhawk.common.intercepter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;

import com.fairyhawk.action.user.LoginAction;
import com.fairyhawk.common.util.Constant;
import com.fairyhawk.common.util.CookieUtils;
import com.fairyhawk.common.util.DESCoder;
import com.fairyhawk.entity.user.Function;
import com.fairyhawk.entity.user.QueryFunctionCondition;
import com.fairyhawk.entity.user.Role;
import com.fairyhawk.entity.user.User;
import com.fairyhawk.service.user.FunctionService;
import com.fairyhawk.service.user.RoleService;
import com.fairyhawk.service.user.UserService;
import com.fairyhawk.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 权限拦截器，判断登录用户是否有权限访问当前功能
 *
 * @author guoqiang.liu
 */
@SuppressWarnings("unchecked")
public class LimitIntercepter extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5622350756953568982L;
	private FunctionService functionService;
	private UserService userService;
	private User loginedUser;
	private RoleService roleService;
	private static final Logger logger = Logger.getLogger(LimitIntercepter.class);
	
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		boolean flag = false;
		Map session = actionInvocation.getInvocationContext().getSession();
		//判断是否有此功能
		ActionProxy sap = actionInvocation.getProxy();
		String invokeUrl = new StringBuffer().append(sap.getNamespace()).append("/").append(sap.getActionName()).
		append("!").append(sap.getMethod()).append(".action").toString();
		List<Function> functionList = functionService.getFunctionList(new QueryFunctionCondition());
		boolean hasFunction = false;
		for (Function function : functionList) {
			if (function.getFunctionUrl() != null && function.getFunctionUrl().indexOf(invokeUrl) != -1){
				hasFunction = true;
			}
		}
		//cookie
		
		ActionContext actionContext = actionInvocation.getInvocationContext();   
        HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        //HttpServletResponse response= (HttpServletResponse)actionContext.get(StrutsStatics.HTTP_RESPONSE);
        
		String userId= CookieUtils.getCookie(request, Constant.SYS_USER_SESSION_NAME);
		if(StringUtils.isNotEmpty(userId)){
		    userId=DESCoder.decrypt(userId);
		    //session中用户信息存在无需查询
		    User user = (User) session.get(Constant.SYS_USER_SESSION_NAME);
	        if(user!=null){
	            return actionInvocation.invoke();
	        }
	        
		    user= userService.getUserById(Integer.valueOf(userId));
		    List<Role> roleList =  roleService.getRoleListByUserId(user.getUserId());
		    functionList = this.userService.getUserFunctionMap(roleList);
		    
            
            // 用户所属功能权限，用于权限树生成
		    List<Function> curUserFunctionList = this.userService.getUserFunction(roleList);
            
		    List<List<Function>> tabUserFunctionList = processTabUserFunctionList(new ArrayList<Function>(
                    curUserFunctionList));
            
            ActionContext.getContext().getSession().clear();
            session.put("userfunctionList", functionList);
            session.put("curUserFunctionList", curUserFunctionList);
            session.put("tabUserFunctionList", tabUserFunctionList);
            
		    session.put(Constant.SYS_USER_SESSION_NAME,user);
		    
		    return actionInvocation.invoke();
		}
		
		//session
		User user = (User) session.get(Constant.SYS_USER_SESSION_NAME);
		if(user==null){
		    logger.info("++ user should login in invokeUrl:"+invokeUrl);
			return "reLogin";
		}
		if(user.getUserId()==1){
		    logger.info("++ user action pass  userName:"+user.getLoginName()+",invokeUrl:"+invokeUrl);
            return actionInvocation.invoke();
		}
		
		//访问路径在权限管理中时再做验证
		if (hasFunction) {
			List<String>  userfunctionList = (List<String>)session.get("userfunctionList");
			if(userfunctionList!=null){
				for (String function : userfunctionList) {
					if (function!= null && function.indexOf(invokeUrl) != -1){
						flag = true;
					}
				}
			}
		}
		setLoginedUser(user);
		if(!flag&&hasFunction){
		    logger.info("++ user want access limit url userName:"+user.getLoginName()+",invokeUrl:"+invokeUrl);
			return LoginAction.ERROR_LIMIT_VERIFY;
		}else{
		    logger.info("++ user action pass  userName:"+user.getLoginName()+",invokeUrl:"+invokeUrl);
			return actionInvocation.invoke();
		}
	}


    public FunctionService getFunctionService() {
        return functionService;
    }


    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }


    public UserService getUserService() {
        return userService;
    }


    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public User getLoginedUser() {
        return loginedUser;
    }


    public void setLoginedUser(User loginedUser) {
        this.loginedUser = loginedUser;
    }


    public RoleService getRoleService() {
        return roleService;
    }


    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
    private List<List<Function>> processTabUserFunctionList(List<Function> funcList) {
        List<List<Function>> tabList = new ArrayList<List<Function>>();
        for (int i = 0; i < funcList.size(); i++) {
            Function func = funcList.get(i);
            if (func.getLevel() == 0) {
                List<Function> list = new ArrayList<Function>();
                list.add(func);
                tabList.add(list);
            }
        }
        int count = funcList.size() + 1;
        while (count > funcList.size()) {
            count = funcList.size();
            for (int i = 0; i < funcList.size(); i++) {
                Function func = funcList.get(i);
                for (int j = 0; j < tabList.size(); j++) {
                    List<Function> list = tabList.get(j);
                    for (int k = 0; k < list.size(); k++) {
                        if (list.get(k).getFunctionId() == func.getParentFunctionId()) {
                            list.add(func);
                            funcList.remove(func);
                        }
                    }
                }
            }
        }
        return tabList;
    }
}
