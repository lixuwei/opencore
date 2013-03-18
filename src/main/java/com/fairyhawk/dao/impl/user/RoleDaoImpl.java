package com.fairyhawk.dao.impl.user;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fairyhawk.common.dao.GenericDaoImpl;
import com.fairyhawk.dao.user.RoleDao;
import com.fairyhawk.entity.user.QueryRoleCondition;
import com.fairyhawk.entity.user.Role;
import com.fairyhawk.entity.user.RoleFunction;
import com.fairyhawk.entity.user.UserRole;

/**
 * @ClassName RoleDaoImpl
 * @package com.fairyhawk.dao.impl.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-3 上午12:13:45
 * 
 */
public class RoleDaoImpl extends GenericDaoImpl implements RoleDao {

    /**
     * 根据用户id查询
     */
    @Override
    public List<Role> getRoleListByUserId(int userId) {
        return this.getMyBatisDao().selectList("RoleMapper.getRoleListByUserId", userId);
    }

    /**
     * 获得所有roleList
     */
    @Override
    public List<Role> getRoleList() {
        return this.getMyBatisDao().selectList("RoleMapper.getRoleList", null);
    }

    /**
     * 根据id获得有role
     */
    @Override
    public Role getRoleById(int roleId) {
        return this.getMyBatisDao().selectOne("RoleMapper.getRoleById", roleId);
    }

    /**
     * 删除角色权限对应关系表
     * 
     * @param roleId
     */
    @Override
    public void deleteRoleFunctionByRoleId(int roleId) {
        this.getMyBatisDao().delete("RoleFunctionMapper.deleteRoleFunctionByRoleId",
                roleId);
    }

    /**
     * 批量添加角色权限
     * 
     */
    @Override
    public void createRoleFunctionBatch(List<RoleFunction> list) {
        this.getMyBatisDao().insert("RoleFunctionMapper.createRoleFunctionBatch", list);
    }

    /**
     * 删除角色
     */
    @Override
    public void deleteRoleById(int roleId) {
        this.getMyBatisDao().delete("RoleMapper.deleteRoleById", roleId);
    }

    /**
     * 添加角色
     */
    @Override
    public void addRole(Role role) {
        this.getMyBatisDao().insert("RoleMapper.createRole", role);
    }

    /**
     * 删除用户对应的角色
     */
    @Override
    public void deleteUserRoleRef(int userId, int roleId) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("userId", userId);
        map.put("roleId", roleId);
        this.getMyBatisDao().delete("UserRoleMapper.deleteUserRoleByuserIdRoleId", map);
    }

    /**
     * 根据roleID删除用户角色对应表
     */
    @Override
    public void deleteUserRoleByRoleId(int roleId) {
        this.getMyBatisDao().delete("UserRoleMapper.deleteUserRoleByRole", roleId);
    }

    /**
     * 根据条件获得roleList
     */
    @Override
    public List<Role> getRoleListByCondition(QueryRoleCondition queryRoleCondition) {
        return this.getMyBatisDao().selectList("RoleMapper.getRoleListByCondition",
                queryRoleCondition);
    }
    /** 
     * 用户添加角色对应关系
     */
    @Override
    public void  addUserRoleRef(int userId,int roleId){
        
             UserRole ugsr = new UserRole();
             ugsr.setRoleId(new Integer(roleId));
             ugsr.setUserId(new Integer(userId));
             ugsr.setSubjectId(new Integer(1));//废弃默认1
             ugsr.setGradeId(new Integer(1));//废弃默认1
             ugsr.setCreateTime(new Date());
             ugsr.setStatus(0);
             ugsr.setUpdateTime(new Date());
             //部门1或者个人2
             ugsr.setLimitScopeId(1);
             
        this.getMyBatisDao().insert("UserRoleMapper.createUserRole", ugsr);
    }
}
