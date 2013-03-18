package com.fairyhawk.service.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.fairyhawk.service.RMIService;

public class RMIServiceImpl extends UnicastRemoteObject implements RMIService {

	public RMIServiceImpl() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;

	@Override
	public String saysmttosmone(String smone) throws RemoteException {
		System.out.println(" ++++　server被调用,client端信息：" + smone);
		return smone;
	}

	@Override
	public String getWords(String words) throws RemoteException {
		// TODO Auto-generated method stub
		return words;
	}

}
