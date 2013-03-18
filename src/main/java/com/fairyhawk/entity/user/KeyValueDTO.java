package com.fairyhawk.entity.user;

import java.io.Serializable;

/**
 * 键值类
 * @author cxs
 *
 */
public class KeyValueDTO implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 7773297939894138862L;
    private int key;
	private String value;
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
