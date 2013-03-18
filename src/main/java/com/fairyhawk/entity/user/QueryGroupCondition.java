package com.fairyhawk.entity.user;

import java.io.Serializable;

import com.fairyhawk.common.entity.PageEntity;
/**
 * 
 * @ClassName  QueryGroupCondition
 * @package com.fairyhawk.entity.user
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-3 上午12:26:35
 *
 */
public class QueryGroupCondition extends PageEntity implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int groupId;
    private int thirdGroupId;
        
    public int getGroupId(){
        return groupId;
    }

    public void setGroupId(int groupId){
        this.groupId = groupId;
    }

    public int getThirdGroupId() {
        return thirdGroupId;
    }

    public void setThirdGroupId(int thirdGroupId) {
        this.thirdGroupId = thirdGroupId;
    }
}