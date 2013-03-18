package com.fairyhawk.service.impl.user;

import java.util.ArrayList;
import java.util.List;

import com.fairyhawk.dao.user.GroupDao;
import com.fairyhawk.dao.user.UserDao;
import com.fairyhawk.entity.user.Group;
import com.fairyhawk.service.user.GroupService;

/**
 * @ClassName GroupServiceImpl
 * @package com.fairyhawk.service.impl.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-5 上午10:43:31
 * 
 */
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    private UserDao userDao;

    /** 根据id查询Group */
    public Group getGroupById(int id) {
        return groupDao.getGroupById(id);

    }

    /** 根据parentId查询Group */
    public List<Group> getChildGroupById(int parentId) {
        return groupDao.getChildGroupById(parentId);
    }

    /** 根据所有的Group */
    public List<Group> getGroupList() {
        return groupDao.getGroupList();
    }

    /** 删除group根据id */
    public void deleteGroupById(int id) {
        groupDao.deleteGroupById(id);
    }

    /** 删除多个groupIds，以逗号间隔参数 */
    public void deleteGroups(String groupIds) {
        if (groupIds == null || "".equals(groupIds.trim())) {
            return;
        }
        groupIds = groupIds.replaceAll(" ", "");
        String[] groupIdsArray = groupIds.split(",");
        for (int i = 0; i < groupIdsArray.length; i++) {

            Group group = groupDao.getGroupById(Integer.valueOf(groupIdsArray[i]));
            group.setStatus(Group.GROUP_DELETE_STATUS);
            // 更新状态为删除
            groupDao.updateGroup(group);

            // 删除用户。（用户的组为2级或者3级。添加用户时限定了）
            List<Integer> userGroups = new ArrayList<Integer>();// 存储要删除的用户的组

            if (group.getLevel() == 1) {
                // 删除2级
                List<Group> childGroups_second = groupDao.getChildGroupById(group
                        .getGroupId());
                if (childGroups_second != null && childGroups_second.size() > 0) {
                    for (Group group2 : childGroups_second) {
                        group2.setStatus(Group.GROUP_DELETE_STATUS);
                        groupDao.updateGroup(group2);
                        userGroups.add(group2.getGroupId());
                        // 删除3级
                        List<Group> childGroups_third = groupDao.getChildGroupById(group2
                                .getGroupId());
                        if (childGroups_third != null && childGroups_third.size() > 0) {
                            for (Group group3 : childGroups_third) {
                                group3.setStatus(Group.GROUP_DELETE_STATUS);
                                groupDao.updateGroup(group3);
                                userGroups.add(group3.getGroupId());
                            }
                        }
                        // 删除3级
                    }

                }
                // 删除2级
            } else if (group.getLevel() == 2) {
                userGroups.add(group.getGroupId());
                // 删除3级
                List<Group> childGroups_third = groupDao.getChildGroupById(group
                        .getGroupId());
                if (childGroups_third != null && childGroups_third.size() > 0) {
                    for (Group group3 : childGroups_third) {
                        group3.setStatus(Group.GROUP_DELETE_STATUS);
                        groupDao.updateGroup(group3);
                        userGroups.add(group3.getGroupId());
                    }
                }
                // 删除3级

            } else if (group.getLevel() == 3) {
                userGroups.add(group.getGroupId());
            }
            userDao.updateUserStatusByGroupId(userGroups);
        }

    }

    /** 根据组级别查询 */
    public List<Group> getGroupByLevel(int level) {
        return groupDao.getGroupByLevel(level);
    }

    /** 添加组 */
    public void addGroup(Group group) {
        groupDao.addGroup(group);
    }

    /** 修改组 */
    public void updateGroup(Group group) {
        groupDao.updateGroup(group);
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public void setGroupDao(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
