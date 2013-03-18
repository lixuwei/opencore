package com.fairyhawk.test.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.poi.hssf.record.formula.PowerPtg;
/**
 * 
 * @title TestttpClient.java
 * @description 
 * @package exp.test
 * @author liuqinggang
 * @date 2012-9-26下午05:43:56
 */

public class TestHttpClient {
	
    /**
     * 根据ip获得所在城市 
     *  北京
     *  安徽 马鞍山
     * @param ip：192.168.0.1
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getIpAdressNameByIP(String ip) {
        try {
            HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod();
            method.setPath("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip="+ ip);
            client.executeMethod(method);
            BufferedReader in = new BufferedReader(new InputStreamReader(method
                    .getResponseBodyAsStream(), "UTF-8"));
            StringBuilder _str = new StringBuilder();
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                _str.append(inputLine);
            }
            method.releaseConnection();
            String js = _str.toString();
            js=js.substring(_str.indexOf("{"),js.length()-1);
            JSONObject jo = JSONObject.fromObject(js);
            String province = jo.get("province") == null ? "" : URLDecoder
                    .decode(jo.get("province").toString());
//          String city = jo.get("city") == null ? "" : URLDecoder.decode(jo
//                  .get("city").toString());
//          return (province.equals("") || province.equals(city)) ? city
//                  : province + " " + city;
            return province.equals("") ? null :province;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 根据手机号查询所在地,只返回Province
     * taobao.com数据
     * @param tel
     * @return CityName
     */
    @SuppressWarnings("deprecation")
    public static String getCityNameByTel_taobao(String tel) {
        try {
            HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod();
            method
                    .setPath("http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel="
                            + tel);
            client.executeMethod(method);
            BufferedReader in = new BufferedReader(new InputStreamReader(method
                    .getResponseBodyAsStream(), "GB2312"));
            StringBuilder _str = new StringBuilder();
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                _str.append(inputLine);
            }
            method.releaseConnection();
            String js = _str.toString();
            JSONObject jo = JSONObject.fromObject(js.substring(_str.indexOf("{"))
                    .trim());
            String province = jo.get("province") == null ? "" : URLDecoder
                    .decode(jo.get("province").toString());
//          String catName = jo.get("catName") == null ? "" : URLDecoder
//                  .decode(jo.get("catName").toString());
//          return (province.equals("") || province.equals(catName)) ? catName
//                  : province + " " + catName;
            return province.equals("") ? null :province;
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * 根据手机号查询所在地,只返回Province
     * showji.com数据
     * @param tel
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getCityNameByTel_shouji(String tel) {
        try {
            HttpClient client = new HttpClient();
            HttpMethod method = new GetMethod();
            method.setPath("http://api.showji.com/Locating/20080808.aspx?m="
                    + tel + "&output=json&callback=querycallback" + tel);
            client.executeMethod(method);
            BufferedReader in = new BufferedReader(new InputStreamReader(method
                    .getResponseBodyAsStream(), "UTF-8"));
            StringBuilder _str = new StringBuilder();
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                _str.append(inputLine).append("\n");
            }
            method.releaseConnection();
            String js = _str.toString();
            String js2 = js.substring(14 + tel.length(), js.length() - 3);
            JSONObject jo = JSONObject.fromObject(js2);
            String province = jo.get("Province") == null ? "" : URLDecoder
                    .decode(jo.get("Province").toString());
//          String city = jo.get("City") == null ? "" : URLDecoder.decode(jo
//                  .get("City").toString());
//          return (province.equals("") || province.equals(city)) ? city
//                  : province + " " + city;
            return province.equals("") ? null :province;
        } catch (Exception e) {
            return null;
        }
    }
	
	
	 public static void testHighSo() {
	     String [] aa = new String []{"dfsssss11@qq.com"};
	     for (int i=0;i<aa.length;i++){
	         
	         HttpClient client = new HttpClient();
	            PostMethod method = new PostMethod();
	            method.setPath("http://highso.cn/cus/cusweb!register4ajax.action");
	            try {
	                NameValuePair[] data = {
	                        new NameValuePair("customer.email", aa[i]),
	                        new NameValuePair("customer.cusPwd", "111111"),
	                        new NameValuePair("customer.mobile", "11111111111"),
	                        new NameValuePair("randomCode", "5074"),
	                        new NameValuePair("customer.subjectId", ""+i),
	                        new NameValuePair("JSESSIONID", "BDF62B1A85C8CEE343C57164D63BF70C")
	                      };
	                method.setRequestBody(data);
	                HttpMethodParams httpMethodParams = new HttpMethodParams();
	                
	                httpMethodParams.setParameter("customer.email", "ss@163.com");
	                
	                client.executeMethod(method);
	                BufferedReader in = new BufferedReader(new InputStreamReader(method
	                        .getResponseBodyAsStream()));
	                String inputLine = null;
	                while ((inputLine = in.readLine()) != null) {
	                    System.out.println(inputLine);
	                }
	                in.close();
	                method.releaseConnection();
	            } catch (HttpException e) {
	                e.printStackTrace();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	     }
	    
    }
	

    public static void testRegUer() {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod();
        method.setPath("http://XXX.com/reg.do");
        try {
            NameValuePair[] data = { new NameValuePair("email", "***@163.com"),
                    new NameValuePair("password", "123456"),
                    new NameValuePair("mobile", "130000000"), };
            method.setRequestBody(data);
            client.executeMethod(method);
            BufferedReader in = new BufferedReader(new InputStreamReader(method
                    .getResponseBodyAsStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
            method.releaseConnection();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 
     * @title TestHttpClient.java
     * @description  测试注册乐蜂网
     * @package com.test
     * @author fairyhawk
     * @date 2012-9-26下午05:43:56
     */
    public static void testLeFeng() {
        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod();
        method.setPath("http://passport.lefeng.com/reg_new_success.jsp");
        try {
            NameValuePair[] data = { 
                    new NameValuePair("usrInput", "dd0987667jh1@163.com"),//用户名
                    new NameValuePair("pwdInput", "111111"),//密码
                    new NameValuePair("repwdInput", "111111"),//确认密码
                    new NameValuePair("veriInput", "epw1"),//验证码
                    //new NameValuePair("JSESSIONID", "abcALjPYkgaWSbRSL3bOt"),
                   new NameValuePair("regtype", "3"),
                   new NameValuePair("returnback", "http://www.lefeng.com/index.html")
                   
            };
            
            method.setRequestBody(data);
            client.executeMethod(method);
            BufferedReader in = new BufferedReader(new InputStreamReader(method
                    .getResponseBodyAsStream()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();
            method.releaseConnection();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
            
            
            public static void test12306() {
                HttpClient client = new HttpClient();
                PostMethod method = new PostMethod();
                method.setPath("http://www.12306.cn/otsweb/loginAction.do?method=login");
                try {
                    NameValuePair[] data = { 
                            // 
                            new NameValuePair("loginUser.user_name", "xianxiaohong"),//
                            new NameValuePair("user.password", "LIUJASMINE"),//
                            new NameValuePair("loginRand", ""),//
                            new NameValuePair("refundLogin", ""),//
                            new NameValuePair("refundFlag", "Y"),//
                            new NameValuePair("randCode", "4mgh"),//验证码
                            new NameValuePair("JSESSIONID", "38AA3FE1A4123C24667669C2954DAA24"),
                           new NameValuePair("BIGipServerotsweb", "2262040842.62495.0000"),
                           new NameValuePair("returnback", "http://www.lefeng.com/index.html")
                           
                    };
                    
                    method.setRequestBody(data);
                    client.executeMethod(method);
                    BufferedReader in = new BufferedReader(new InputStreamReader(method
                            .getResponseBodyAsStream()));
                    String inputLine = null;
                    while ((inputLine = in.readLine()) != null) {
                        System.out.println(inputLine);
                    }
                    in.close();
                    method.releaseConnection();
                } catch (HttpException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    
            public static void main(String[] args) {
                //test12306();
//                TestHttpClient client = new TestHttpClient();
//                TestHttpClient client2 = client;//new TestHttpClient();
//                System.out.println(client.equals(client2));
                System.out.println(Math.pow(2, 10));
            }
            
            
            
            
}
