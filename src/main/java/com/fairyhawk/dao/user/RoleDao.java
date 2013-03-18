package com.fairyhawk.dao.user;

import java.util.List;

import com.fairyhawk.entity.user.QueryRoleCondition;
import com.fairyhawk.entity.user.Role;
import com.fairyhawk.entity.user.RoleFunction;

/**
 * @ClassName RoleDao
 * @package com.fairyhawk.dao.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-3 上午12:13:08
 * 
 */
public interface RoleDao {

    /**
     * 根据用户id查询所有role
     * 
     * @param userId
     * @return
     */
    public List<Role> getRoleListByUserId(int userId);

    /**
     * 获得所有roleList
     */
    public List<Role> getRoleList();

    /**
     * 根据id获得有role
     */
    public Role getRoleById(int roleId);

    /**
     * 删除角色权限对应关系表
     * 
     * @param roleId
     */
    public void deleteRoleFunctionByRoleId(int roleId);

    /**
     * 批量添加角色权限
     * 
     */
    public void createRoleFunctionBatch(List<RoleFunction> list);
    
    /**
     * 删除角色
     */
    public void deleteRoleById(int roleId);
    
    /**
     * 添加角色
     */
    public void addRole(Role role);
    
    /**
     * 删除用户对应的角色
     */
    public void  deleteUserRoleRef (int userId,int roleId);
    
    /**
     * 根据roleID删除用户角色对应表
     */
    public void  deleteUserRoleByRoleId (int roleId);
    
    /**
     * 根据条件获得roleList
     */
    public List<Role> getRoleListByCondition(QueryRoleCondition queryRoleCondition);
    
    
    /** 
     * 用户添加角色对应关系
     */
    public void  addUserRoleRef(int userId,int roleId);
}
