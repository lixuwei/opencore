
package com.fairyhawk.dao.impl.user;

import java.util.List;

import com.fairyhawk.common.dao.GenericDaoImpl;
import com.fairyhawk.common.entity.PageEntity;
import com.fairyhawk.dao.user.UserDao;
import com.fairyhawk.entity.user.QueryUserCondition;
import com.fairyhawk.entity.user.User;

/**
 * @ClassName  UserDaoImpl
 * @package com.fairyhawk.dao.impl.user
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-2 上午11:47:10
 * 
 */
public class UserDaoImpl extends GenericDaoImpl implements UserDao {

    /**
     *  根据用户名查询用户
     */
    @Override
    public User getUserByName(String name) {
        return this.getMyBatisDao().selectOne("UserMapper.getUserByLoginName", name);
    }

    /**
     * 查询所有用户，分页
     */
    @Override
    public List<User> getAllUserList(QueryUserCondition queryUserCondition,
            PageEntity pageEntity) {
        return this.queryForListPage("UserMapper.getAllUserList", queryUserCondition, pageEntity);
    }
    /**根据id查询用户*/
    public User getUserById(int id) {
        return this.getMyBatisDao().selectOne("UserMapper.getUserById", id);
    }
    /** 修改用户信息*/
    public void updateUser(User user){
        this.getMyBatisDao().update("UserMapper.updateUser", user);
    }
    
    /** 修改用户状态为删除*/
    public void updateUserStatusByGroupId(List<Integer> list){
        this.getMyBatisDao().update("UserMapper.updateUserStatusByGroupId", list);
    }
    
    /** 修改密码 */
    public void updateUserPwd(User user){
        this.getMyBatisDao().update("UserMapper.updateUserPwd", user);
    }
    /**
     * 新增用户
     */
    public void addUser(User user){
        this.getMyBatisDao().insert("UserMapper.createUser", user);
    }
    
}
