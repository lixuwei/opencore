/**
 * @ClassName  UserDao
 * @package com.fairyhawk.dao
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-2 上午11:44:42
 * 
 */
package com.fairyhawk.dao.user;

import java.util.List;

import com.fairyhawk.common.entity.PageEntity;
import com.fairyhawk.entity.user.QueryUserCondition;
import com.fairyhawk.entity.user.User;

/**
 * @ClassName  UserDao
 * @package com.fairyhawk.dao
 * @description 系统用户
 * @author  liuqinggang
 * @Create Date: 2013-3-2 上午11:44:42
 * 
 */
public interface UserDao {
    /**
     * 根据登录名查询
     * @param name
     * @return
     */
    public User getUserByName(String name);
    /**
     * 查询所有用户,分页
     */
    public List<User> getAllUserList(QueryUserCondition queryUserCondition,PageEntity pageEntity);
    
    /**根据id查询用户*/
    public User getUserById(int id) ;
    
    /** 修改用户信息*/
    public void updateUser(User user);
    
    /** 修改用户状态为删除*/
    public void updateUserStatusByGroupId(List<Integer> list);
    
    /** 修改密码 */
    public void updateUserPwd(User user);
    /**
     * 新增用户
     */
    public void addUser(User user);
    
}
