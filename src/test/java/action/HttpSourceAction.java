package action;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpSourceAction {
	public static void main(String [] agrs){
		//lianghao();
		puhao();
		//sanlian();
		
	}
	
	public static  void lianghao() {
		int goodnum=0;
		int shitnum=0;
		
		HttpClient client = new HttpClient();
		 //String url="http://service.bj.10086.cn/simcard/gotone_random_"+i+".html";
		String url="http://service.bj.10086.cn/jxhsimcard/newSearch.action";
		//var startNumList=['startNumNoLimit','134','135','136','137','138','139','150','151','152','157','158','159','182','183','187','188'];
		 PostMethod method = new PostMethod(url);
		 try {
			
			 method.setParameter("mobileBrandEn", "gotone");
			 method.setParameter("pagination", "1");
			 method.setParameter("repeatNumberSelect", "0");
			 method.setParameter("repeatTimeSelect", "3");
			 method.setParameter("searchMode", "1");
			 method.setParameter("searchType", "startNumberType");
			 method.setParameter("sectionNo", "randomNumberType");
			 method.setParameter("startNumber", "139");
			 
			client.executeMethod(method);
			method.getResponseBodyAsStream();
				BufferedReader 
				reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"UTF-8"));
	           String str = null;  
	           String dd = "";
	           while ((str = reader.readLine()) != null) {
	        	 //<li id="149|13910232414||false|" >
	        	   //System.out.println(dd);
	        	   if(str.indexOf("<li id=\"158|")>=0){
	        		   dd=str.substring(16,27);
	        		   if (dd.indexOf("4")<0){
	        			   goodnum++;
	        			   String dd2=str.substring(28,29);
	        			   //if (dd2.equals("D4-4-5-1")|| dd2.equals("E3-4-5-2") || dd2.equals("E6-4-5-2")){
	        			   System.out.println(str.substring(28,36)+"=="+dd);
	        			   if (dd2.equals("D")|| dd2.equals("E")){ 
	        			   }
	        			   //System.out.println(dd);
	        		   }else{
	        			   shitnum++;
	        		   }
	        	   }
	           }
			method.abort();  
		} catch (Exception e) {
			e.printStackTrace();
		}
		 System.out.println("good!"+goodnum);  
		 System.out.println("shit!"+shitnum); 
	
	}
	public static void puhao() {
		HttpClient client = new HttpClient();
		int goodnum=0;
		int shitnum=0;
		for (int i=1;i<14;i++){
			//System.out.println("第"+i+"页");  
			//String url="http://service.bj.10086.cn/simcard/gotone_random_"+i+".html";
			String url="http://service.bj.10086.cn/jxhsimcard/newSearch.action?pagination="+i+"&searchType=startNumberType&packageId=&searchMode=1&mobileBrandEn=gotone&startNumber=139&hotNumber=777&randomNumber=978884&repea";
			//String url="http://service.bj.10086.cn/jxhsimcard/newSearch.action";
			 HttpMethod method = new GetMethod(url);
			 try {
				client.executeMethod(method);
				method.getResponseBodyAsStream();
					BufferedReader 
					reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"gb2312"));
		           String str = null;  
		           String dd = "";
		           String dd2= "";
		          // StringBuffer buffer = new StringBuffer();
		           while ((str = reader.readLine()) != null) {
		        	 //<li id="149|13910232414||false|" > C3-4-5-2
		        	   if(str.indexOf("<li id=\"158|")>=0){
		        		   dd=str.substring(16,27);
		        		   dd2=str.substring(28,36);
		        		   //System.out.println(dd2);
		        		   //buffer.append(i+"页="+dd2+"\n");
		        		   if (dd2.equals("C4-4-5-1")){
		        			   System.out.println(i+"页："+dd);  
		        			   //System.out.println("good:"+i+"页："+dd);  
		        			   goodnum++;
		        		   }else{
		        			   //System.out.println("shit:"+dd);  
		        			   shitnum++;
		        		   }
		        	   }

		           }
//		           File file = new File("D:\\a"+i+".txt");
//		           if (!file.exists())file.createNewFile();
//		           FileOutputStream fileStream = new FileOutputStream(file);
//		           fileStream.write(buffer.toString().getBytes());
//		           fileStream.close();
		           
				method.abort();  
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		 System.out.println("goodnum:"+goodnum);  
		 System.out.println("shitnum:"+shitnum); 
	}
	public static void ph(){

		int goodnum=0;
		int shitnum=0;
		
		HttpClient client = new HttpClient();
		 //String url="http://service.bj.10086.cn/simcard/gotone_random_"+i+".html";
		String url="http://service.bj.10086.cn/simcard/showSearchList.action";
		//var startNumList=['startNumNoLimit','134','135','136','137','138','139','150','151','152','157','158','159','182','183','187','188'];
		 PostMethod method = new PostMethod(url);
		 try {
			
			 method.setParameter("mobileBrandEn", "gotone");
			 method.setParameter("pagination", "1");
			 method.setParameter("repeatNumberSelect", "0");
			 method.setParameter("repeatTimeSelect", "3");
			 method.setParameter("searchMode", "1");
			 method.setParameter("searchType", "startNumberType");
			 method.setParameter("sectionNo", "randomNumberType");
			 method.setParameter("startNumber", "139");
			 method.setParameter("hotNumber", "520");
			 
			 
			 
			client.executeMethod(method);
			method.getResponseBodyAsStream();
				BufferedReader 
				reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"UTF-8"));
	           String str = null;  
	           String dd = "";
	           while ((str = reader.readLine()) != null) {
	        	 //<li id="149|13910232414||false|" >
	        	   if(str.indexOf("<li id=\"158|")>=0){
	        		   dd=str.substring(16,27);
	        		   if (dd.indexOf("4")<0){
	        			   goodnum++;
	        			   System.out.println(dd);
	        		   }else{
	        			   shitnum++;
	        		   }
	        	   }
	           }
			method.abort();  
		} catch (Exception e) {
			e.printStackTrace();
		}
		 System.out.println("good!"+goodnum);  
		 System.out.println("shit!"+shitnum); 
	
	
	}
	
	
	public static  void sanlian() {
//		int goodnum=0;
//		int shitnum=0;
		
		String ss [] ={"520"};//{"000","111","222","333","444","555","666","777","888","999"};
		for (String mys:ss){
			for (int i=0;i<10;i++){

				HttpClient client = new HttpClient();
				 //String url="http://service.bj.10086.cn/simcard/gotone_random_"+i+".html";
				String url="http://service.bj.10086.cn/simcard/showSearchList.action";
				//var startNumList=['startNumNoLimit','134','135','136','137','138','139','150','151','152','157','158','159','182','183','187','188'];
				 PostMethod method = new PostMethod(url);
				 try {
					 method.setParameter("hotNumber","000");
					 method.setParameter("mobileBrandEn", "gotone");
					 method.setParameter("pagination", "1");
					 method.setParameter("randomNumber", "777");
					 method.setParameter("repeatNumber", "0");
					 method.setParameter("repeatNumberSelect", "0");
					 method.setParameter("repeatTime", "3");
					 method.setParameter("repeatTimeSelect", "3");
					 method.setParameter("searchMode", "1");
					 
					 method.setParameter("searchType", "lastNumberType");
					 method.setParameter("sectionNo", "on");
					 
					 method.setParameter("lastNumber", i+mys);
					 System.out.println(i+mys);
					 
					client.executeMethod(method);
					method.getResponseBodyAsStream();
						BufferedReader 
						reader = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),"UTF-8"));
			           String str = null;  
			           String dd = "";
			           while ((str = reader.readLine()) != null) {
			        	 //<li id="149|13910232414||false|" >
			        	   //System.out.println(dd);
			        	   if(str.indexOf("<li id=\"149|")>=0){
			        		   dd=str.substring(22,33);
			        		   System.out.println("dd2:"+dd);
			        	   }
			           }
					method.abort();  
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		 
	}
}

