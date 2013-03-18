package com.fairyhawk.entity.user;

import java.io.Serializable;

import com.fairyhawk.common.entity.PageEntity;
/**
 * 
 * @ClassName  QueryLoginLogCondition
 * @package com.fairyhawk.entity.user
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-3 上午12:25:47
 *
 */
public class QueryLoginLogCondition extends PageEntity implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = -516196625928174274L;
    private int userId;			//外键_登录人ID
	private String userName;	//登录人

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
