package com.fairyhawk.entity.user;

import java.io.Serializable;
import java.util.List;
/**
 * 
 * @ClassName  Group
 * @package com.fairyhawk.entity.user
 * @description 系统用户所属组
 * @author  liuqinggang
 * @Create Date: 2013-3-2 上午09:37:48
 *
 */

public class Group implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = -8610061559380582878L;
    public static int  GROUP_DEFAULT_STATUS = 1;
	public static int GROUP_DEFAULT_PARENT_ID = -1;
	public static int GROUP_DELETE_STATUS = 2;
	/**组id*/
    private int groupId;
    /**父id*/
    private int parentGroupId;
    /**组名称*/
    private String groupName;
    /**状态*/
    private int status;
    /**创建时间*/
    private java.util.Date createTime;
    /**修改时间*/
    private java.util.Date updateTime;
    /**组级别 1.一级组 2.二级组 3.三级组*/
    private int level;
    public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	private List<Group> groupList;
        
    public List<Group> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<Group> groupList) {
		this.groupList = groupList;
	}

	public int getGroupId(){
        return groupId;
    }

    public void setGroupId(int groupId){
        this.groupId = groupId; 
    }
        
    public int getParentGroupId(){
        return parentGroupId;
    }

    public void setParentGroupId(int parentGroupId){
        this.parentGroupId = parentGroupId; 
    }
        
    public String getGroupName(){
        return groupName;
    }

    public void setGroupName(String groupName){
        this.groupName = groupName; 
    }
        
    public int getStatus(){
        return status;
    }

    public void setStatus(int status){
        this.status = status; 
    }
        
    public java.util.Date getCreateTime(){
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime; 
    }
        
    public java.util.Date getUpdateTime(){
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime; 
    }
}
