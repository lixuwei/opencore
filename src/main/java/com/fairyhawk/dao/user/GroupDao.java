package com.fairyhawk.dao.user;

import java.util.List;

import com.fairyhawk.entity.user.Group;

/**
 * @ClassName GroupDao
 * @package com.fairyhawk.dao.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-5 下午02:48:33
 * 
 */
public interface GroupDao {
    /** 根据id查询Group */
    public Group getGroupById(int id);

    /** 根据所有的Group */
    public List<Group> getGroupList();

    /**根据parentId查询Group*/
    public List<Group> getChildGroupById(int parentId);
    
    /**删除group根据id*/
    public void deleteGroupById(int id);
    
    /**更新group根据id*/
    public void  updateGroup(Group group);
    
    /**根据Level(查询Group*/
    public List<Group> getGroupByLevel(int level);
    
    /** 添加组 */
    public void addGroup(Group group);
}
