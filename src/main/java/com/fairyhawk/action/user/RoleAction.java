package com.fairyhawk.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyhawk.common.action.CommonAction;
import com.fairyhawk.common.entity.JsonEntity;
import com.fairyhawk.entity.user.Function;
import com.fairyhawk.entity.user.QueryRoleCondition;
import com.fairyhawk.entity.user.Role;
import com.fairyhawk.entity.user.User;
import com.fairyhawk.service.user.FunctionService;
import com.fairyhawk.service.user.RoleService;
import com.fairyhawk.service.user.UserService;
import com.fairyhawk.util.StringUtils;

/**
 * @ClassName RoleAction
 * @package com.fairyhawk.action.user
 * @description 角色管理
 * @author liuqinggang
 * @Create Date: 2013-3-7 下午03:11:49
 * 
 */
public class RoleAction extends CommonAction {

    /**
     * 
     */
    private static final long serialVersionUID = 6178496028618975286L;
    private static final Logger logger = LoggerFactory.getLogger(GroupAction.class);

    private List<Role> roleList = new ArrayList<Role>();

    private RoleService roleService;

    private FunctionService functionService;

    private UserService userService;
    private List<Function> firstLevelFunctionList;

    private Role role;

    private Role addRole;
    private String userId;
    private String roleId;
    private int usersId;
    private User user;
    private Set<Integer> selectedFunctionIds;

    /**
     * 角色管理显示
     * 
     * @return
     */
    public String roleList() {
        try {

            roleList = roleService.getRoleList();
            if (roleList == null || roleList.size() == 0) {
                logger.info("+++ get roleList size 0");
                return "roleList";
            }
            if (role != null) {
                role = roleService.getRoleById(role.getRoleId());
            } else {
                role = roleService.getRoleById(roleList.get(0).getRoleId());
            }
            firstLevelFunctionList = functionService.getFunctionList(null);

            // if (selectedFunctionIds == null)
            // selectedFunctionIds = new HashSet<Integer>();
            // for (Function function : role.getFunctionList()) {
            // selectedFunctionIds.add(function.getFunctionId());
            // }

        } catch (Exception e) {
            logger.error(" ++ getAllRoleList ", e);
        }
        return "roleList";
    }

    /**
     * json获得role.页面下拉用
     * 
     * @return
     */
    public String getJsonRoleById() {
        try {
            role = roleService.getRoleById(role.getRoleId());
            this.setJson(new JsonEntity<Role>(true, "success", role));
        } catch (Exception e) {
            logger.error(" ++ getJsonRoleById ", e);
        }
        return "json";
    }

    /**
     * 修改角色权限
     * 
     * @return
     */
    public String updateRoleFunction() {
        try {
            // 更新角色权限
            logger.info("role.getRoleId:" + role.getRoleId() + ",selectedFunctionIds:"
                    + selectedFunctionIds);
            roleService.updateRoleFunction(role.getRoleId(), selectedFunctionIds);
        } catch (Exception e) {
            logger.error("RoleAction.updateRoleFunction", e);
            return ERROR;
        }
        return roleList();
    }

    /**
     * 删除角色
     * 
     * @return String
     * @throws Exception
     */
    public String delRole() {
        try {
            roleService.deleteRoleById(role.getRoleId());
            role = null;
        } catch (Exception e) {
            logger.error("RoleAction.delRole", e);
            return ERROR;
        }
        return roleList();
    }

    /**
     * 添加角色
     * 
     * @return String
     * @throws Exception
     */
    public String addRole() {
        try {
            addRole.setCreateTime(new Date(System.currentTimeMillis()));
            addRole.setRoleApplyScopeId(Role.ROLE_APPLY_SCOPE_ALL);
            addRole.setRoleTypeId(Role.ROLE_TYPE_DEFAULT);
            addRole.setStatus(Role.ROLE_STATUS_DEFAULT);
            // 添加角色(添加方法中要返回主键)
            roleService.addRole(addRole);
            role = addRole;
        } catch (Exception e) {
            logger.error("RoleAction.addRole", e);
            return ERROR;
        }
        return roleList();
    }

    /**
     * 删除 用户对应的role(包括相关关联)
     * 
     * @return String
     * @throws Exception
     */
    public String deleteRoleRef() {
        try {
            if(StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(roleId)){
                this.roleService.deleteUserRoleRef(Integer.valueOf(userId), Integer.valueOf(roleId));
            }
        } catch (Exception e) {
            logger.error("RoleAction.deleteRoleRef", e);
            return ERROR;
        }
        return "toEditRoleRef";
    }
    
    /** 用户添加新角色显示
    * @return String
    * @throws Exception
    */
   public String  toAddRoleRef(){
       try{
           QueryRoleCondition queryRoleCondition = new QueryRoleCondition();
           queryRoleCondition.setRoleApplyScopeId(Role.ROLE_APPLY_SCOPE_ALL);//不显示超级管理
           roleList = this.roleService.getRoleListByCondition(queryRoleCondition);
           userId = this.getUserId();
           usersId=this.getLoginedUser().getUserId();
           user = userService.getUserById(Integer.valueOf(userId));
       }catch(Exception e){
           logger.error("RoleAction.toAddRoleRef", e);
           return "toAddRoleRef";
       }
       return "toAddRoleRef";
   }
   
   /** 用户添加角色
    * @return String
    * @throws Exception
    */
   public String  addRoleRef(){
       try{
            if (userId == null || "".equals(userId.trim()) || roleId == null
                    || "".equals(roleId.trim())) {
                return "toEditRoleRef";
            }
           
           //查询用户是否存在次角色
//           roleService.get
           //添加
           userId = this.getUserId();
          
           this.roleService.addUserRoleRef(Integer.valueOf(userId),Integer.valueOf(roleId));
           usersId=this.getLoginedUser().getUserId();
           
       }catch(Exception e){
           logger.error("RoleAction.addRoleRef", e);
           return "toEditRoleRef";
       }
       return "toEditRoleRef";
   }
   
    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public FunctionService getFunctionService() {
        return functionService;
    }

    public void setFunctionService(FunctionService functionService) {
        this.functionService = functionService;
    }

    public List<Function> getFirstLevelFunctionList() {
        return firstLevelFunctionList;
    }

    public void setFirstLevelFunctionList(List<Function> firstLevelFunctionList) {
        this.firstLevelFunctionList = firstLevelFunctionList;
    }

    public Set<Integer> getSelectedFunctionIds() {
        return selectedFunctionIds;
    }

    public void setSelectedFunctionIds(Set<Integer> selectedFunctionIds) {
        this.selectedFunctionIds = selectedFunctionIds;
    }

    public Role getAddRole() {
        return addRole;
    }

    public void setAddRole(Role addRole) {
        this.addRole = addRole;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
