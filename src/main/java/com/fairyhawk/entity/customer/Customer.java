package com.fairyhawk.entity.customer;

import java.util.Date;

import com.fairyhawk.common.entity.BaseEntity;

public class Customer extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4181611215034299276L;

	private String email;
	private int cusId;
	private Date regTime;
	private String cusName;
	private Long id;
	private String name;
	
	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCusId() {
		return cusId;
	}

	public void setCusId(int cusId) {
		this.cusId = cusId;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

}
