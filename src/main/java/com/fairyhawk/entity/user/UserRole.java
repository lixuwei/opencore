package com.fairyhawk.entity.user;

import java.io.Serializable;

/**
 * 
 * @ClassName UserRole
 * @package com.fairyhawk.entity.user
 * @description 用户对应权限
 * @author liuqinggang
 * @Create Date: 2013-3-7 下午06:41:00
 * 
 */
public class UserRole implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -8971435894866426993L;
    private int roleId;
    private int userId;
    private int gradeId;
    private int subjectId;
    private int limitScopeId;
    private int status;
    private java.util.Date createTime;
    private java.util.Date updateTime;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

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

    public int getLimitScopeId() {
        return limitScopeId;
    }

    public void setLimitScopeId(int limitScopeId) {
        this.limitScopeId = limitScopeId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }
}
