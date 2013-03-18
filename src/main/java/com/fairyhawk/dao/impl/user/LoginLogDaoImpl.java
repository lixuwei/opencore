package com.fairyhawk.dao.impl.user;

import com.fairyhawk.common.dao.GenericDaoImpl;
import com.fairyhawk.dao.user.LoginLogDao;
import com.fairyhawk.entity.user.LoginLog;

/**
 * @ClassName  LoginLogDaoImpl
 * @package com.fairyhawk.dao.impl.user
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-8 下午04:34:00
 * 
 */
public class LoginLogDaoImpl extends GenericDaoImpl implements LoginLogDao {
    /** 添加登陆日志 */
    @Override
    public void addLoginLog(LoginLog loginLog) {
        this.getMyBatisDao().insert("LoginLogMapper.addLoginLog", loginLog);
    }
}
