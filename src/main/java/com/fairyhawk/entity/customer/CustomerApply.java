package com.fairyhawk.entity.customer;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName CustomerApply
 * @package com.fairyhawk.entity.customer
 * @description
 * @author liuqinggang
 * @Create Date: 2013-3-5 下午04:37:36
 * 
 */
public class CustomerApply implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2825310109995192968L;
    private int id;
    private String hangYe;
    private String diQu;
    private String userName;
    private String mobile;
    private String email;
    private Date createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHangYe() {
        return hangYe;
    }

    public void setHangYe(String hangYe) {
        this.hangYe = hangYe;
    }

    public String getDiQu() {
        return diQu;
    }

    public void setDiQu(String diQu) {
        this.diQu = diQu;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
