package com.fairyhawk.action.user;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyhawk.common.action.CommonAction;
import com.fairyhawk.common.entity.JsonEntity;
import com.fairyhawk.common.util.Constant;
import com.fairyhawk.common.util.MD5;
import com.fairyhawk.entity.user.Function;
import com.fairyhawk.entity.user.Group;
import com.fairyhawk.entity.user.QueryGroupCondition;
import com.fairyhawk.entity.user.QueryUserCondition;
import com.fairyhawk.entity.user.Role;
import com.fairyhawk.entity.user.User;
import com.fairyhawk.service.user.GroupService;
import com.fairyhawk.service.user.RoleService;
import com.fairyhawk.service.user.UserService;
import com.fairyhawk.util.StringUtils;

/**
 * @ClassName UserAction
 * @package com.fairyhawk.action.user
 * @description 用户管理
 * @author liuqinggang
 * @Create Date: 2013-3-5 上午10:34:09
 * 
 */
public class UserAction extends CommonAction {

    /**
     * 
     */
    private static final long serialVersionUID = -5164290730579997568L;

    private static final Logger logger = LoggerFactory.getLogger(UserAction.class);

    private User user;
    private List<User> userList = new ArrayList<User>();
    private UserService userService;
    private QueryUserCondition queryUserCondition;
    private GroupService groupService;
    private List<Group> groupList = new ArrayList<Group>();
    private QueryGroupCondition queryGroupCondition = new QueryGroupCondition();
    private String groupId;
    private String searchKey;// 条件搜索
    private String searchType;
    private List<Role> roleList = new ArrayList<Role>();
    private RoleService roleService;
    private String userType;
    private List<Function> functionList;
    private Group first_g;// 一级组
    private Group second_g;// 二级组
    private Group third_g;// 三级组
    private String changePwd;
    private int usersId;

    /**
     * 用户列表
     */
    public String listAllUser() {
        try {
            this.getPage().setPageSize(20);// 设置每页为20，默认10
            userList = userService
                    .getAllUserList(getQueryUserCondition(), this.getPage());
            usersId = this.getLoginedUser().getUserId();
        } catch (Exception e) {
            logger.error("UserAction.listAllUser", e);
            return ERROR;
        }
        return "listAllUser";
    }

    /**
     * 通过关键字查询用户
     */
    public String listUserByKey() {
        try {
            searchKey = URLDecoder.decode(searchKey, "UTF-8");
            getQueryUserCondition().setSearchKey(searchKey);
            this.getPage().setPageSize(20);
            userList = this.userService.getAllUserList(getQueryUserCondition(), this
                    .getPage());
        } catch (Exception e) {
            logger.error("UserAction.listUserByKey", e);
            return ERROR;
        }
        return "listAllUser";
    }

    /**
     * 修改用户信息显示
     */
    public String toEditUser() {
        // 一级组
        try {
            groupList = this.groupService.getGroupList();
            // gradeList = this.gradeService.getGradeList(new
            // QueryGradeCondition());
            // subjectList = this.subjectService.getSubjectList(new
            // QuerySubjectCondition());
            // 同步数据库信息
            user = this.userService.getUserById(user.getUserId());

            // ugsrList =
            // userGradeSubjectRoleService.getUGSRByUserId(user.getUserId());
            Group userGroup = groupService.getGroupById(user.getGroupId());
            user.setGroup(userGroup);
            if (user.getGroup().getLevel() == 2) {
                logger.info("user.getGroup().getLevel() == 2");
                first_g = groupService.getGroupById(user.getGroup().getParentGroupId());
                second_g = user.getGroup();
                third_g = new Group();
                third_g.setGroupId(-1);
                third_g.setGroupName("请选择");
            } else if (user.getGroup().getLevel() == 3) {
                third_g = user.getGroup();
                second_g = groupService.getGroupById(user.getGroup().getParentGroupId());
                first_g = groupService.getGroupById(second_g.getParentGroupId());

            } else {
                first_g = new Group();
                first_g.setGroupId(-1);
                first_g.setGroupName("请选择");
                second_g = new Group();
                second_g.setGroupId(-1);
                second_g.setGroupName("请选择");
                third_g = new Group();
                third_g.setGroupId(-1);
                third_g.setGroupName("请选择");
            }
        } catch (Exception e) {
            logger.error("UserAction.toEditUser", e);
            return ERROR;
        }
        return "toEditUser";
    }

    /**
     * 用户组级联联动
     */
    public String getAllGroup() throws IOException {
        try {
            List<Group> childGroupList = this.groupService.getChildGroupById(Integer
                    .valueOf(groupId));
            this.setJson(new JsonEntity<List<Group>>(true, "success", childGroupList));
        } catch (Exception e) {
            logger.error("UserAction.getAllGroup", e);
            return ERROR;
        }
        return "json";

    }

    /**
     * 更新用户信息
     */
    public String editUser() {
        try {
            userService.updateUser(user);
        } catch (Exception e) {
            logger.error("UserAction.editUser", e);
            return ERROR;
        }
        return "changeSuccess";
    }

    /**
     * 冻结、解冻用户
     */
    public String freezeUser() {
        try {
            user = this.userService.getUserById(user.getUserId());
            if (user.getStatus() == User.USER_DEFAULT_STATUS) {
                user.setStatus(User.USER_FREEZE_STATUS);
            } else {
                user.setStatus(User.USER_DEFAULT_STATUS);
            }
            this.userService.updateUser(user);
        } catch (Exception e) {
            logger.error("UserAction.freezeUser", e);
            return ERROR;
        }
        return "listAllUser";
    }

    /**
     * 删除用户
     */
    public String deleteUser() {
        try {// 软删除状态设置为2
            user = this.userService.getUserById(user.getUserId());
            user.setStatus(User.USER_DELETE_STATUS);
            this.userService.updateUser(user);
        } catch (Exception e) {
            logger.error("UserAction.deleteUser", e);
            return ERROR;
        }
        return "relistAllUser";
    }

    /**
     *修改密码显示页面
     */
    public String toUpdatePwd() {
        try {
            user = this.userService.getUserById(user.getUserId());
        } catch (Exception e) {
            logger.error("UserAction.toUpdatePwd", e);
            return ERROR;
        }
        return "toUpdatePwd";
    }

    /**
     * 修改密码
     */
    public String updatePwd() {
        try {
            User u = userService.getUserById(user.getUserId());
            u.setLoginPwd(MD5.getMD5(user.getLoginPwd()));
            userService.updateUser(u);
        } catch (Exception e) {
            logger.error("UserAction.updatePwd", e);
            return ERROR;
        }
        return "changeSuccess";
    }

    /**
     * 修改用户对应的角色
     * 
     * @return
     * @throws Exception
     */
    public String toEditUserGradeSubjectRoleSec() throws Exception {
        try {

            user = this.userService.getUserById(user.getUserId());

            roleList = roleService.getRoleListByUserId(user.getUserId());
            user.setRoleList(roleList);

        } catch (Exception e) {
            logger.error("UserAction.toEditUserGradeSubjectRoleSec", e);
            return ERROR;
        }
        return "toEditUserGradeSubjectRoleSec";
    }

     

    // 递归角色权限菜单
    public void getFun(List<Function> lf, int functionId) {
        for (Function function : lf) {
            if (function.getParentFunctionId() == functionId) {
                functionList.add(function);
                this.getFun(lf, function.getFunctionId());
            }
        }
    }
    /**
     * 用户信息自修改
     * @return
     */
    public String userManagerSubmit() {
        try{
           /* String userName = this.getServletRequest().getParameter("userName");
            System.out.println("userName:"+userName);
            String email = this.getServletRequest().getParameter("email");
            String tel = this.getServletRequest().getParameter("tel");
            String address = this.getServletRequest().getParameter("address");
            String zip = this.getServletRequest().getParameter("zip");*/
            User sysUser = getLoginedUser();
            sysUser.setUserName(user.getUserName());
            sysUser.setEmail(user.getEmail());
            sysUser.setTel(user.getTel());
            sysUser.setAddress(user.getAddress());
            sysUser.setZip(user.getZip());
            
            userService.updateUser(sysUser);
            user=sysUser;
            this.getServletRequest().getSession().setAttribute(Constant.SYS_USER_SESSION_NAME, user);
        }catch(Exception e){
            logger.error("UserAction.userManagerSubmit", e);
            return ERROR;
        }
        return "changeSuccess";
    }
    
    public String userManager() {
        user = this.getLoginedUser();
        return "userManager";
    }

    public String changePwd() {
        return "changePwd";
    }

    public String getChangePwd() {
        return changePwd;
    }

    /**
     * 修改密码
     * @param changePwd
     */
    public String changePwdSubmit() {
        try{
            User user = getLoginedUser();
            if (user.getLoginPwd().equals(MD5.getMD5(this.user.getLoginPwd()))) {
                user.setLoginPwd(MD5.getMD5(changePwd));
                userService.updateUserPwd(user);
                addActionError("密码修改成功!");
            } else {
                addActionError("原密码输入错误！");
            }
        }catch(Exception e){
            logger.error("UserAction.changePwdSubmit", e);
            return ERROR;
        }
        return "changePwd";
    }

    /**
     * 新增用户显示
     * @return String
     * @throws Exception
     */
    public String toAddUser() {
        // 一级组
        try{
            groupList = this.groupService.getGroupByLevel(1);
        }catch(Exception e){
            logger.error("UserAction.toAddUser", e);
            return ERROR;
        }
        return "toAddUser";
    }
    /**
     * 添加新用户
     * @return
     */
    public String addUser() {
        try{
            user.setLoginPwd(MD5.getMD5(user.getLoginPwd()));
            user.setCreateTime(new Date());
            this.userService.addUser(user);
        }catch(Exception e){
            logger.error("UserAction.addUser", e);
            return ERROR;
        }
        return "relistAllUser";
    }
    /**
     * 检查用户名是否可用
     * @return
     */
    public String checkLoginName() {
        try {
            String loginName = this.getQueryUserCondition().getSearchKey();
            if(StringUtils.isNotEmpty(loginName)) {
                this.getQueryUserCondition().setSearchKey(loginName.trim());
                User   user2=    userService.getUserByLoginName(loginName);
                if(user2==null) {
                    this.sendMessage("true");
                    return null;
                }
            }
            this.sendMessage("false");
        } catch(Exception e) {
            logger.error("UserAction.checkLoginName", e);
            return ERROR;
        }
        return null;
    }
    public void setChangePwd(String changePwd) {
        this.changePwd = changePwd;
    }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
    }

    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public Group getFirst_g() {
        return first_g;
    }

    public void setFirst_g(Group firstG) {
        first_g = firstG;
    }

    public Group getSecond_g() {
        return second_g;
    }

    public void setSecond_g(Group secondG) {
        second_g = secondG;
    }

    public Group getThird_g() {
        return third_g;
    }

    public void setThird_g(Group thirdG) {
        third_g = thirdG;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public QueryGroupCondition getQueryGroupCondition() {
        if (queryGroupCondition == null) {
            queryGroupCondition = new QueryGroupCondition();
        }
        return queryGroupCondition;
    }

    public void setQueryGroupCondition(QueryGroupCondition queryGroupCondition) {
        this.queryGroupCondition = queryGroupCondition;
    }

    public QueryUserCondition getQueryUserCondition() {
        if (queryUserCondition == null) {
            queryUserCondition = new QueryUserCondition();
            queryUserCondition.setUserType(-1);
        }
        return queryUserCondition;
    }

    public void setQueryUserCondition(QueryUserCondition queryUserCondition) {
        this.queryUserCondition = queryUserCondition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}
