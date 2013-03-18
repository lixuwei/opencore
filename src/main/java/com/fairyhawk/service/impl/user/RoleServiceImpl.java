package com.fairyhawk.service.impl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fairyhawk.dao.user.FunctionDao;
import com.fairyhawk.dao.user.RoleDao;
import com.fairyhawk.entity.user.Function;
import com.fairyhawk.entity.user.QueryRoleCondition;
import com.fairyhawk.entity.user.Role;
import com.fairyhawk.entity.user.RoleFunction;
import com.fairyhawk.service.user.RoleService;

/**
 * @ClassName RoleServiceImpl
 * @package com.fairyhawk.service.impl.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-3 上午12:10:51
 * 
 */
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    private FunctionDao functionDao;

    @Override
    public List<Role> getRoleListByUserId(int userId) {
        List<Role> roles = roleDao.getRoleListByUserId(userId);
        if (roles != null && roles.size() > 0) {
            for (Role role : roles) {
                try {
                    List<Function> functionList = functionDao
                            .getFunctionListByRoleId(role.getRoleId());
                    role.setFunctionList(functionList);
                } catch (Exception e) {
                }

            }
            return roles;
        }
        return null;
    }

    /**
     * 获得所有roleList
     */
    public List<Role> getRoleList() {
        /*
         * List<Role> roles = roleDao.getRoleList(); if (roles != null &&
         * roles.size() > 0) { for (Role role : roles) { try { List<Function>
         * functionList = functionDao
         * .getFunctionListByRoleId(role.getRoleId());
         * role.setFunctionList(functionList); } catch (Exception e) { }
         * 
         * } return roles; }
         */
        return roleDao.getRoleList();
    }

    /**
     * 根据id获得有role
     */
    public Role getRoleById(int roleId) {
        Role role = roleDao.getRoleById(roleId);
        List<Function> functionList = functionDao.getFunctionListByRoleId(role
                .getRoleId());
        role.setFunctionList(functionList);
        return role;
    }

    /** 更新角色对应的权限。先删除后增加 */
    public void updateRoleFunction(int roleId, Set<Integer> functions) {
        roleDao.deleteRoleFunctionByRoleId(roleId);// 删除
        if (functions != null && functions.size() > 0) {
            List<RoleFunction> list = new ArrayList<RoleFunction>();
            for (int functionId : functions) {
                RoleFunction roleFunction = new RoleFunction();
                roleFunction.setCreateTime(new Date(System.currentTimeMillis()));
                roleFunction.setFunctionId(functionId);
                roleFunction.setRoleId(roleId);
                roleFunction.setStatus(RoleFunction.ROLE_FUNCTION_STATUS_DEFAULT);
                list.add(roleFunction);
            }
            roleDao.createRoleFunctionBatch(list);// 增加
        }
    }

    /**
     * 根据id删除role
     * 
     * @param roleId
     */
    public void deleteRoleById(int roleId) {
        // 删除角色和权限的对应关系表
        roleDao.deleteRoleFunctionByRoleId(roleId);
        // 删除用户和角色对应关系表
        roleDao.deleteUserRoleByRoleId(roleId);
        // 删除角色表
        roleDao.deleteRoleById(roleId);
    }

    /**
     * 添加角色
     */
    public void addRole(Role role) {
        roleDao.addRole(role);
    }

    /**
     * 删除用户对应的角色
     */
    public void deleteUserRoleRef(int userId, int roleId) {
        roleDao.deleteUserRoleRef(userId, roleId);
    }
    
    /**
     * 根据条件获得roleList
     */
    public List<Role> getRoleListByCondition(QueryRoleCondition queryRoleCondition){
        return roleDao.getRoleListByCondition(queryRoleCondition);
    }
    
    /** 
     * 用户添加角色对应关系
     */
    public void  addUserRoleRef(int userId,int roleId){
        roleDao.addUserRoleRef(userId, roleId);
    }
    
    public RoleDao getRoleDao() {
        return roleDao;
    }

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public FunctionDao getFunctionDao() {
        return functionDao;
    }

    public void setFunctionDao(FunctionDao functionDao) {
        this.functionDao = functionDao;
    }

}
