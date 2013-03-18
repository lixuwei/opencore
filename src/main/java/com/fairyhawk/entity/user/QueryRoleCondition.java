package com.fairyhawk.entity.user;

import java.io.Serializable;

import com.fairyhawk.common.entity.PageEntity;
/**
 * 
 * @ClassName  QueryRoleCondition
 * @package com.fairyhawk.entity.user
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-3 上午12:26:04
 *
 */
public class QueryRoleCondition extends PageEntity implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -5221738407808555228L;
    /**角色id*/
    private int roleId;
    /**角色类别id*/
    private int roleTypeId;
    /**角色使用范围id*/
    private int roleApplyScopeId;
    
    private int userId;
    
    private int gradeId;
    
    private int subjectId;
        
    public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getRoleId(){
        return roleId;
    }

    public void setRoleId(int roleId){
        this.roleId = roleId;
    }

    public int getRoleTypeId() {
        return roleTypeId;
    }

    public void setRoleTypeId(int roleTypeId) {
        this.roleTypeId = roleTypeId;
    }

    public int getRoleApplyScopeId() {
        return roleApplyScopeId;
    }

    public void setRoleApplyScopeId(int roleApplyScopeId) {
        this.roleApplyScopeId = roleApplyScopeId;
    }
}