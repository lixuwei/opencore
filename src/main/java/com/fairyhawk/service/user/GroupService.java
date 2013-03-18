package com.fairyhawk.service.user;

import java.util.List;

import com.fairyhawk.entity.user.Group;

/**
 * @ClassName GroupService
 * @package com.fairyhawk.service.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-5 上午10:43:13
 * 
 */
public interface GroupService {
    /** 根据id查询Group */
    public Group getGroupById(int id);

    /** 根据所有的Group */
    public List<Group> getGroupList();

    /** 根据parentId查询Group */
    public List<Group> getChildGroupById(int parentId);

    /** 删除group根据id */
    public void deleteGroupById(int id);

    /** 删除多个groupid，以逗号间隔参数 */
    public void deleteGroups(String groupIds);

    /** 根据组级别查询 */
    public List<Group> getGroupByLevel(int Level);

    /** 添加组 */
    public void addGroup(Group group);

    /** 修改组 */
    public void updateGroup(Group group);

}
