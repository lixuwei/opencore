package com.fairyhawk.service;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface RMIService extends Remote{

		public String  saysmttosmone(String smone) throws RemoteException ;
		public String  getWords(String words)  throws RemoteException;
}
