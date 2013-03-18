package com.fairyhawk.common.entity;

import java.io.Serializable;

public class JsonEntity<E> implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 680608034975324914L;
	
	/**
	 * 下面三个参数可以自己根据情况赋值
	 */
	//返回是否成功
	private boolean success;
	//返回的辅助信息
	private String message;
	//具体要转成json的对象
	private E entity;
	
	public JsonEntity () {
		
	}
	public JsonEntity(boolean success, String message,
			E entity) {
		this.success = success;
		this.message = message;
		this.entity = entity;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public E getEntity() {
		return entity;
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}
	
}
