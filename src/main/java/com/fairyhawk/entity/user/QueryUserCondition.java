package com.fairyhawk.entity.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fairyhawk.common.entity.PageEntity;
/**
 * 
 * @ClassName  QueryUserCondition
 * @package com.fairyhawk.entity.user
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-2 上午09:37:25
 *
 */
public class QueryUserCondition extends PageEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int userId;
	private int groupId;
	private int userType;
	private String searchKey;
	private String searchType;
	private int isSold;
	
	List<Group> groupList = new ArrayList<Group>();
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public List<Group> getGroupList() {
		return groupList;
	}
	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public int getUserId(){
		return userId;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}
    public int getIsSold() {
        return isSold;
    }
    public void setIsSold(int isSold) {
        this.isSold = isSold;
    }
}