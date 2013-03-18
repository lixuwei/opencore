package com.fairyhawk.entity.user;

import java.io.Serializable;

import com.fairyhawk.common.entity.PageEntity;

/**
 * 
 * @ClassName  QueryRoleFunctionCondition
 * @package com.fairyhawk.entity.user
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-3 上午12:26:43
 *
 */
public class QueryRoleFunctionCondition extends PageEntity implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = -1770602655989434519L;
    private int roleId;
    private int functionId;
        
    public int getRoleId(){
        return roleId;
    }

    public void setRoleId(int roleId){
        this.roleId = roleId;
    }
    public int getFunctionId(){
        return functionId;
    }

    public void setFunctionId(int functionId){
        this.functionId = functionId;
    }
}