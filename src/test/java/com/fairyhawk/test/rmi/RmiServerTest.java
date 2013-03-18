package com.fairyhawk.test.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.fairyhawk.service.RMIService;
import com.fairyhawk.service.impl.RMIServiceImpl;

public class RmiServerTest {

	public static void main(String[] args) throws Exception{
		try {
			// 创建一个远程对象
			RMIService rhello = new RMIServiceImpl();
			// 本地主机上的远程对象注册表Registry的实例，并指定端口为8888，这一步必不可少（Java默认端口是1099），必不可缺的一步，缺少注册表创建，则无法绑定对象到远程注册表上
			LocateRegistry.createRegistry(8888);

			// 把远程对象注册到RMI注册服务器上，并命名为RHello
			// 绑定的URL标准格式为：rmi://host:port/name(其中协议名可以省略，下面两种写法都是正确的）
			Naming.bind("rmi://localhost:8888/RHello", rhello);
			System.out.println(">>>>>INFO:远程IHello对象绑定成功！");
		}catch(Exception e1){
			e1.printStackTrace();
			
		}
	}

}
