package com.fairyhawk.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyhawk.common.action.CommonAction;
import com.fairyhawk.common.util.Constant;
import com.fairyhawk.common.util.CookieUtils;
import com.fairyhawk.common.util.DESCoder;
import com.fairyhawk.common.util.MD5;
import com.fairyhawk.entity.user.Function;
import com.fairyhawk.entity.user.LoginLog;
import com.fairyhawk.entity.user.Role;
import com.fairyhawk.entity.user.User;
import com.fairyhawk.service.user.LoginLogService;
import com.fairyhawk.service.user.RoleService;
import com.fairyhawk.service.user.UserService;
import com.fairyhawk.util.StringUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @ClassName LoginAction
 * @package com.fairyhawk.action.customer
 * @description 系统用户登录
 * @author liuqinggang
 * @Create Date: 2013-3-1 下午10:21:44
 * 
 */
public class LoginAction extends CommonAction {

    private static Logger logger = LoggerFactory.getLogger(LoginAction.class);
    /**
	 * 
	 */
    private static final long serialVersionUID = 6238900344107660927L;
    
    public static String ERROR_LIMIT_VERIFY = "limitVerifyError";
    /** 登录用户 */
    private User user;
    private UserService userService;
    private LoginLogService loginLogService;
    private RoleService roleService ;
    private String randCode;
    private int rdtType;
    private String loginFrom;
    private List<Function> functionList;
    private List<Function> curUserFunctionList;
    private List<List<Function>> tabUserFunctionList;
    private String randomCode;
    public String loginInput() {
        System.out.println("  loginInput 11111");
        return "success";
    }

    /**
     * 用户登录操作
     * @return
     */
    
    public String login() {
        String returnPage = "login";
        try {
            User userFromDb = null;
            if (user != null && !StringUtils.isBlank(user.getLoginName()) &&!StringUtils.isBlank(user.getLoginPwd()) ) {
                logger.info("+++ do user login name:"+user.getLoginName());
                // 用户名长度
                if (user.getLoginName().length() < 6 || user.getLoginName().length() > 20) {
                    addActionError("用户名长度大于6，小于20!");
                    returnPage = "login";
                    return returnPage;
                }
                // 密码长度
                if (user.getLoginPwd().length() < 6 || user.getLoginPwd().length() > 20) {
                    addActionError("密码长度大于6，小于20!");
                    returnPage = "login";
                    return returnPage;
                }
                // 验证码
//                if (!randomCode.equals(this.getSession(Constant.RAND_CODE))) {
//                    this.getSession().removeAttribute(Constant.RAND_CODE);
//                    addActionError(getText("验证码输入错误！"));
//                    returnPage = "login";
//                    return returnPage;
//                }
                //查询用户
                userFromDb = userService.getUserByLoginName(user.getLoginName());
                this.getSession().removeAttribute(Constant.RAND_CODE);
                //验证密码是否正确
                if (userFromDb != null && userFromDb.getLoginPwd().equals(MD5.getMD5(user.getLoginPwd()))) {
                    //查询role
                    List<Role> roleList =  roleService.getRoleListByUserId(userFromDb.getUserId());
                    
                    functionList = this.userService.getUserFunctionMap(roleList);
                    
                    // 用户所属功能权限，用于权限树生成
                    curUserFunctionList = this.userService.getUserFunction(roleList);
                    
                    tabUserFunctionList = processTabUserFunctionList(new ArrayList<Function>(
                            curUserFunctionList));

                    String ip = this.getIp(getServletRequest()); // 获取登录人的IP并记录
                    LoginLog loginLog = new LoginLog();
                    loginLog.setLoginTime(new Date());
                    loginLog.setIp(ip);
                    loginLog.setUserId(userFromDb.getUserId());
                    loginLogService.addLoginLog(loginLog);
    
                    ActionContext.getContext().getSession().clear();
                    this.setSession("userfunctionList", functionList);
                    this.setSession("curUserFunctionList", curUserFunctionList);
                    this.setSession("tabUserFunctionList", tabUserFunctionList);
                    this.setSession(Constant.SYS_USER_SESSION_NAME, userFromDb);
                    logger.info(" ++++ user login success: "+user.getLoginName() + " is logined ok !");
                    //
                    CookieUtils.setCookie(getServletResponse(), Constant.SYS_USER_SESSION_NAME,DESCoder.encrypt(""+userFromDb.getUserId()), 1);
                    
                    returnPage = SUCCESS;
                } else {
                    addActionError(getText("请输入正确的用户名和密码！"));
                    returnPage = "login";
                }
            }else {
                //addActionError(getText("请输入正确的用户名和密码！"));
                returnPage = "login";
            }
        } catch (Exception e) {
            logger.error("+++ do.login error", e);
            addActionError(getText("请输入正确的用户名和密码!!!"));
            return "login";
        }
        logger.info("+++ return returnPage:"+returnPage);
        return returnPage;
    }
    public void setUserFunctionToSession(){
        
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

    /**
     * 退出系统操作
     * 
     * @return String
     * @throws Exception
     */
    public String logout() {
        ActionContext.getContext().getSession().clear();
        CookieUtils.deleteAllCookie(getServletRequest(), getServletResponse());
        return "loginOut";
    }

    public String loginThenRedirect() {
        return "welcome";
    }

    public String welcome() {
        return "welcome";
    }
    public String topframe(){
        return "topframe";
    }
    public String leftframe(){
        return "leftframe";
    }
    public String switchframe(){
        return "switchframe";
    }
    public String rightframe(){
        return "rightframe";
    }
    public String bottomFrame(){
        return "bottomFrame";
    }
    public User getUser() {
        if(user ==null){
            user=new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public LoginLogService getLoginLogService() {
        return loginLogService;
    }

    public void setLoginLogService(LoginLogService loginLogService) {
        this.loginLogService = loginLogService;
    }

    public String getRandCode() {
        return randCode;
    }

    public void setRandCode(String randCode) {
        this.randCode = randCode;
    }

    public int getRdtType() {
        return rdtType;
    }

    public void setRdtType(int rdtType) {
        this.rdtType = rdtType;
    }

    public String getLoginFrom() {
        return loginFrom;
    }

    public void setLoginFrom(String loginFrom) {
        this.loginFrom = loginFrom;
    }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
    }

    public List<Function> getCurUserFunctionList() {
        return curUserFunctionList;
    }

    public void setCurUserFunctionList(List<Function> curUserFunctionList) {
        this.curUserFunctionList = curUserFunctionList;
    }

    public List<List<Function>> getTabUserFunctionList() {
        return tabUserFunctionList;
    }

    public void setTabUserFunctionList(List<List<Function>> tabUserFunctionList) {
        this.tabUserFunctionList = tabUserFunctionList;
    }

    public String getRandomCode() {
        return randomCode;
    }

    public void setRandomCode(String randomCode) {
        this.randomCode = randomCode;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }



}
