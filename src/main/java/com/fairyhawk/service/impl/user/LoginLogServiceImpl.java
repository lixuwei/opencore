package com.fairyhawk.service.impl.user;

import com.fairyhawk.dao.user.LoginLogDao;
import com.fairyhawk.entity.user.LoginLog;
import com.fairyhawk.service.user.LoginLogService;

/**
 * @ClassName LoginLogServiceImpl
 * @package com.fairyhawk.service.impl.user
 * @description 用户登陆日志
 * @author liuqinggang
 * @Create Date: 2013-3-8 下午04:31:54
 * 
 */
public class LoginLogServiceImpl implements LoginLogService {

    private LoginLogDao loginLogDao;
    /** 添加登陆日志 */
    @Override
    public void addLoginLog(LoginLog loginLog) {
        loginLogDao.addLoginLog(loginLog);
    }
    
    public LoginLogDao getLoginLogDao() {
        return loginLogDao;
    }
    public void setLoginLogDao(LoginLogDao loginLogDao) {
        this.loginLogDao = loginLogDao;
    }

}
