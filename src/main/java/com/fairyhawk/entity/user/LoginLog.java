package com.fairyhawk.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @ClassName LoginLog
 * @package com.fairyhawk.entity.user
 * @description 登录日志
 * @author liuqinggang
 * @Create Date: 2013-3-1 下午10:12:35
 * 
 */
public class LoginLog implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5535670276285023025L;
    
    private int id; // 序号，主键，自增
    private Date loginTime; // 登录时间
    private String ip; // 登录IP
    private int userId; // 外键_登录人ID
    private String userName; // 登录真实姓名
    private String loginName; // 登录名

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
}
