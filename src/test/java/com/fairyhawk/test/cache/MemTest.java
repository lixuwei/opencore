package com.fairyhawk.test.cache;

import com.danga.MemCached.*;

public class MemTest {
 private static final String POOL_NAME="test_pool";
 protected static MemCachedClient mcc;
 static {
  //设置缓存服务器列表，当使用分布式缓存的时，可以指定多个缓存服务器
  String[] servers =
  {
		  //"172.16.123.158:12000"
		 "172.16.123.150:11211"
  };
  //与服务器列表中对应的各服务器的权重
  Integer[] weights = {3};

  //创建Socked连接池
  SockIOPool pool = SockIOPool.getInstance(POOL_NAME);

  //向连接池设定服务器和权重
  pool.setServers( servers );
  pool.setWeights( weights );
  
  //连接池参数
  pool.setInitConn( 5 );
  pool.setMinConn( 5 );
  pool.setMaxConn( 250 );
  pool.setMaxIdle( 1000 * 60 * 60 * 6 );

  // set the sleep for the maint thread
  // it will wake up every x seconds and
  // maintain the pool size
  pool.setMaintSleep( 30 );

  // set some TCP settings
  // disable nagle
  // set the read timeout to 3 secs
  // and don't set a connect timeout
  pool.setNagle( false );
  pool.setSocketTO( 3000 );
  pool.setSocketConnectTO( 0 );

  // initialize the connection pool
  pool.initialize();


  // lets set some compression on for the client
  // compress anything larger than 64k

  mcc=new MemCachedClient(POOL_NAME);
  
 }

 public static void main(String[] args) throws Exception{
  
	 mcc.set("VEDIO_7",1,200000);
  System.out.println(mcc.get("VEDIO_7"));
  

  
  
 }
}