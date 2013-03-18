package com.fairyhawk.action.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fairyhawk.common.action.CommonAction;
import com.fairyhawk.entity.user.Group;
import com.fairyhawk.entity.user.QueryGroupCondition;
import com.fairyhawk.service.user.GroupService;

/**
 * 
 * @ClassName GroupAction
 * @package com.fairyhawk.action.user
 * @description 用户组管理
 * @author liuqinggang
 * @Create Date: 2013-3-6 下午06:08:42
 * 
 */
public class GroupAction extends CommonAction {

    /**
     * 
     */
    private static final long serialVersionUID = 4972883193722743009L;

    private static Logger logger = LoggerFactory.getLogger(GroupAction.class);

    private int groupId;
    private Group group;
    private GroupService groupService;
    private List<Group> groupList = new ArrayList<Group>();
    private List<Group> firstList = new ArrayList<Group>();
    private List<Group> secondList = new ArrayList<Group>();
    private QueryGroupCondition queryGroupCondition;
    private String groupIds;
    private Group firstGroup;
    private Group secondGroup;
    /**
     * 用户组管理显示。方法名为 toDelete未改动。。。
     */
    public String toDeleteGroup() {
        try {
            groupList = this.groupService.getGroupList();
        } catch (Exception e) {
            logger.error("GroupAction.toDeleteGroup", e);
            return ERROR;
        }
        return "toDeleteGroup";
    }

    /** 删除用户组 以及组下用户，软删除 只改状态 */
    public String deleteGroup() {
        try {
            // groupIds以逗号隔开的
            this.groupService.deleteGroups(groupIds);
        } catch (Exception e) {
            logger.error("GroupAction.deleteGroup", e);
            return ERROR;
        }
        return "deleteGroup";
    }

    /**
     * 添加组显示跳转
     */
    public String toAddGroup() {
        try {
            // 一级组
            groupList = this.groupService.getGroupByLevel(1);
        } catch (Exception e) {
            logger.error("GroupAction.toAddGroup", e);
            return ERROR;
        }
        return "toAddGroup";
    }

    /**
     * 添加组
     */
    public String addGroup() {
        try {
             this.groupService.addGroup(group);
        } catch (Exception e) {
            logger.error("GroupAction.addGroup", e);
            return ERROR;
        }
        return "changeSuccess";
    }
    
    /**
     * 修改组显示
     */
    public String toUpdateGroup() {
        try {
            
            firstList= groupService.getGroupByLevel(1);
            group= this.groupService.getGroupById(group.getGroupId());
            
            if(group.getLevel()==2){
                secondGroup=groupService.getGroupById(group.getParentGroupId());
            }else if(group.getLevel()==3){
                secondGroup=groupService.getGroupById(group.getParentGroupId());
                firstGroup=groupService.getGroupById(secondGroup.getParentGroupId());
                
            }
            
        } catch (Exception e) {
            logger.error("GroupAction.toUpdateGroup", e);
            return ERROR;
        }
        return "updateGroup";
    }
    
    /**
     * 修改组
     */
    public String updateGroup() {
        try {
             this.groupService.updateGroup(group);
        } catch (Exception e) {
            logger.error("GroupAction.updateGroup", e);
            return ERROR;
        }
        return "changeSuccess";
    }

    /*

    *//**
     * 组的树形结构列表
     * 
     * @return String
     * @throws Exception
     */
    /*
     * public String GroupTreeList() { try { // groupList =
     * this.groupService.getGroupList(queryGroupCondition); // TODO } catch
     * (Exception e) { logger.error("GroupAction.GroupTreeList", e); return
     * ERROR; } return "GroupTreeList"; }
     *//**
     * 获取子组列表
     * 
     * @return
     * @throws Exception
     * @author chenshuai
     */
    /*
     * public String getChildGroupById() throws IOException { try { List<Group>
     * childGroupList = null;// this.groupService.getChildGroupById("" // +
     * groupId); // TODO List<KeyValueDTO> myList = new
     * ArrayList<KeyValueDTO>();
     * 
     * KeyValueDTO keyvalue = null;
     * 
     * for (int i = 0; i < childGroupList.size(); i++) { keyvalue = new
     * KeyValueDTO(); Group grouptemp = childGroupList.get(i);
     * 
     * keyvalue.setKey(grouptemp.getGroupId());
     * keyvalue.setValue(grouptemp.getGroupName()); myList.add(keyvalue); }
     * 
     * this.setJson(new JsonEntity<List<KeyValueDTO>>(true, "", myList)); }
     * catch (Exception e) { logger.error("GroupAction.getChildGroupById", e);
     * return ERROR; } return "getChildGroupById"; }
     * 
     * public String techIndex() { return "techIndex"; }
     * 
     * public String techLeftframe() { try { // groupList = //
     * this.groupService.getGroupListForTechTree(queryGroupCondition); // TODO }
     * catch (Exception e) { logger.error("GroupAction.techLeftframe", e);
     * return ERROR; } return "techLeftframe"; }
     * 
     * public String techRightframe() { return "techRightframe"; }
     * 
     * public String switchframe() { return "switchframe"; }
     * 
     * public String stuIndex() { return "stuIndex"; }
     * 
     * public String stuLeftframe() { // groupList = //
     * this.groupService.getGroupListForTechTree(queryGroupCondition); // TODO
     * return "stuLeftframe"; }
     *//**
     * 往组中添加用户
     * 
     * @return
     * @throws Exception
     */
    /*
     * public String toUserRefToGroup() { return "toUserRefToGroup"; }
     * 
     * public String userRefToGroup() { return "userRefToGroup"; }
     *//**
     * 批量导入用户
     * 
     * @return
     * @throws Exception
     */
    /*
     * String toManyUserRefToGroup() { return "toManyUserRefToGroup"; }
     * 
     * public String manyUserRefToGroup() { return "manyUserRefToGroup"; }
     *//**
     * 批量导入用户
     * 
     * @return
     * @throws Exception
     */
    /*
     * public String listUserFromGroup() { return "listUserFromGroup"; }
     */
    /**
     * 从组中删除用户
     * 
     * @return
     * @throws Exception
     */
    public String deleteUserFromGroup() {
        return "deleteUserFromGroup";
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public GroupService getGroupService() {
        return groupService;
    }

    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public QueryGroupCondition getQueryGroupCondition() {
        return queryGroupCondition;
    }

    public void setQueryGroupCondition(QueryGroupCondition queryGroupCondition) {
        this.queryGroupCondition = queryGroupCondition;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getFirstGroup() {
        return firstGroup;
    }

    public void setFirstGroup(Group firstGroup) {
        this.firstGroup = firstGroup;
    }

    public Group getSecondGroup() {
        return secondGroup;
    }

    public void setSecondGroup(Group secondGroup) {
        this.secondGroup = secondGroup;
    }


    public List<Group> getFirstList() {
        return firstList;
    }

    public void setFirstList(List<Group> firstList) {
        this.firstList = firstList;
    }

    public List<Group> getSecondList() {
        return secondList;
    }

    public void setSecondList(List<Group> secondList) {
        this.secondList = secondList;
    }

    
    
}
