/**
 * @ClassName  RoleService
 * @package com.fairyhawk.service.user
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-1 下午10:19:50
 * 
 */
package com.fairyhawk.service.user;

import java.util.List;
import java.util.Set;

import com.fairyhawk.entity.user.QueryRoleCondition;
import com.fairyhawk.entity.user.Role;

/**
 * @ClassName RoleService
 * @package com.fairyhawk.service.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-1 下午10:19:50
 * 
 */
public interface RoleService {
    /**
     * 根据用户id获得roleList
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

    /** 更新角色对应的权限。先删除后增加 */
    public void updateRoleFunction(int roleId, Set<Integer> functions);
    
    /**
     * 根据id删除role
     * @param roleId
     */
    public void  deleteRoleById (int roleId);
    
    /**
     * 添加角色
     */
    public void addRole(Role role);
    
    /**
     * 删除用户对应的角色
     */
    public void  deleteUserRoleRef (int userId,int roleId);
    
    /**
     * 根据条件获得roleList
     */
    public List<Role> getRoleListByCondition(QueryRoleCondition queryRoleCondition);
    
    /** 
     * 用户添加角色对应关系
     */
    public void  addUserRoleRef(int userId,int roleId);
            
            
}
