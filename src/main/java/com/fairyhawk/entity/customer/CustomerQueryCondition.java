package com.fairyhawk.entity.customer;

import java.util.Date;

import com.fairyhawk.common.entity.BaseEntity;

public class CustomerQueryCondition extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4181611215034299276L;
	private String email;
	private int cusId;
	private Date regTime;

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

}
