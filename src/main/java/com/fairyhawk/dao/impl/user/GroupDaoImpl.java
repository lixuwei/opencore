package com.fairyhawk.dao.impl.user;

import java.util.List;

import com.fairyhawk.common.dao.GenericDaoImpl;
import com.fairyhawk.dao.user.GroupDao;
import com.fairyhawk.entity.user.Group;

/**
 * @ClassName GroupDaoImpl
 * @package com.fairyhawk.dao.impl.user
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-5 下午02:48:53
 * 
 */
public class GroupDaoImpl extends GenericDaoImpl implements GroupDao {
    /** 根据id查询Group */
    public Group getGroupById(int id) {
        return this.getMyBatisDao().selectOne("GroupMapper.getGroupById", id);

    }

    /** 根据所有的Group */
    public List<Group> getGroupList() {
        return this.getMyBatisDao().selectList("GroupMapper.getGroupList", null);
    }

    /** 根据parentId查询Group */
    public List<Group> getChildGroupById(int parentId) {
        return this.getMyBatisDao().selectList("GroupMapper.getChildGroupById", parentId);
    }

    /** 删除group根据id */
    public void deleteGroupById(int id) {
        this.getMyBatisDao().delete("GroupMapper.deleteGroupById", id);
    }

    /** 更新group根据id */
    public void updateGroup(Group group) {
        this.getMyBatisDao().update("GroupMapper.updateGroup", group);
    }

    /** 根据Level(查询Group */
    public List<Group> getGroupByLevel(int level) {
        return this.getMyBatisDao().selectList("GroupMapper.getGroupByLevel", level);
    }
    /** 添加组 */
    public void addGroup(Group group){
        this.getMyBatisDao().insert("GroupMapper.addGroup", group);
    }
}
