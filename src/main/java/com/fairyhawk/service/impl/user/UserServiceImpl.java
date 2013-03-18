package com.fairyhawk.service.impl.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fairyhawk.common.entity.PageEntity;
import com.fairyhawk.dao.user.UserDao;
import com.fairyhawk.entity.user.Function;
import com.fairyhawk.entity.user.QueryUserCondition;
import com.fairyhawk.entity.user.Role;
import com.fairyhawk.entity.user.User;
import com.fairyhawk.service.user.UserService;

/**
 * @ClassName  UserServiceImpl
 * @package com.fairyhawk.service.impl.user
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-2 下午12:00:37
 * 
 */
public class UserServiceImpl implements UserService {
    
    
    private UserDao userDao;

    /**根据登录名查询用户*/
    @Override
    public User getUserByLoginName(String name) {
        User user=  userDao.getUserByName(name);
        return user;
    }

    /**根据id查询用户*/
    public User getUserById(int id) {
        User user=  userDao.getUserById(id);
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List getUserFunctionMap(List<Role> roleList) {
        Map<String, String> systemFunctionMap = new HashMap<String, String>();
        for (Role role : roleList) {
            for (int i = 0; i < role.getFunctionList().size(); i++) {
                Function tsf = role.getFunctionList().get(i);
                systemFunctionMap.put(tsf.getFunctionUrl(), tsf
                        .getFunctionUrl());
            }
        }
        List functionList = new ArrayList<String>(systemFunctionMap.values());
        return functionList;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Function> getUserFunction(List<Role> roleList) {
        Map<String, Function> systemFunctionMap = new HashMap<String, Function>();
        for (Role role : roleList) {
            for (int i = 0; i < role.getFunctionList().size(); i++) {
                //去重复
                Function tsf = role.getFunctionList().get(i);
                systemFunctionMap.put(String.valueOf(tsf.getFunctionId()), tsf);
            }
        }
        List functionList = new ArrayList<Function>(systemFunctionMap.values());
        return functionList;
    }

    /**
     * 查询所有用户,分页
     */
    @Override
    public List<User> getAllUserList(QueryUserCondition queryUserCondition,PageEntity pageEntity) {
        return userDao.getAllUserList(queryUserCondition, pageEntity);
    }
    
    /** 修改用户信息*/
    public void updateUser(User user){
        userDao.updateUser(user);
    }
    
    /** 修改密码 */
    public void updateUserPwd(User user){
        userDao.updateUserPwd(user);
    }
    /**
     * 新增用户
     */
    public void addUser(User user){
        userDao.addUser(user);
    }
    
    
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
