package com.fairyhawk.common.service;

import com.fairyhawk.common.dao.MyBatisDao;

public abstract class BaseServiceRead {

	private MyBatisDao myBatisDaoRead;

	public MyBatisDao getMyBatisDaoRead() {
		return myBatisDaoRead;
	}

	public void setMyBatisDaoRead(MyBatisDao myBatisDaoRead) {
		this.myBatisDaoRead = myBatisDaoRead;
	}

}
