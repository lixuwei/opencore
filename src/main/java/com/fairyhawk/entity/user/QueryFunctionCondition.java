package com.fairyhawk.entity.user;

import java.io.Serializable;

import com.fairyhawk.common.entity.PageEntity;


/**
 * 
 * @ClassName  QueryFunctionCondition
 * @package com.fairyhawk.entity.user
 * @description 查询功能对象所使用的条件类
 * @author  liuqinggang
 * @Create Date: 2013-3-3 上午12:26:23
 *
 */

public class QueryFunctionCondition extends PageEntity implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -7055484731686817657L;
    /**父功能id*/
    private int parentFunctionId;
    /**功能类型id*/
    private int functionTypeId;
    /**功能适用范围id*/
    private int functionApplyScopeId;
    /**功能状态id*/
    private Integer status;

    public int getParentFunctionId() {
        return parentFunctionId;
    }

    public void setParentFunctionId(int parentFunctionId) {
        this.parentFunctionId = parentFunctionId;
    }

    public int getFunctionTypeId() {
        return functionTypeId;
    }

    public void setFunctionTypeId(int functionTypeId) {
        this.functionTypeId = functionTypeId;
    }

    public int getFunctionApplyScopeId() {
        return functionApplyScopeId;
    }

    public void setFunctionApplyScopeId(int functionApplyScopeId) {
        this.functionApplyScopeId = functionApplyScopeId;
    }

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}