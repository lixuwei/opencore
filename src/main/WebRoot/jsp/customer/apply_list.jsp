<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>试用用户申请列表</title>
	<link type="text/css" rel="stylesheet" href="<%=contextPath%>/background/css/base.css" />
	<link type="text/css" rel="stylesheet" href="<%=contextPath%>/background/css/layout.css" />
	
	<script src="<%=contextPath%>/js/common/jquery-1.7.2.min.js"></script>
	 
</head>

<body>
<div class="zhong ml5">

	<div class="dqnav">
    	<p class="ml15 pl20">当前位置：<a href="#">试用用户申请列表</a></p>
    </div>
    
    
	<div class="cxtab mt18 ml18 mr10">
	
	    <div class="ss clearer">
	      <p class="left"><img src="<%=contextPath%>/background/images/jiao01.jpg" /></p>
	      <p class="right"><img src="<%=contextPath%>/background/images/jiao02.jpg" /></p>
	    </div>
	    
	    <div class="zss">
	    
	      <div class="zss01a">
	      
	        <div class="zss01b pb15">
	          
	          <div class="zs03 pt20 pr13">
		            <div class="zs03b">
		              <table width="100%" cellspacing="0" cellpadding="0" border="0" style=" border-left:1px solid #d9d9d9; border-top:1px solid #d9d9d9;">
			              <tbody>
				                <tr  bgcolor="#f2f2f2" align="center" class="yx">
				                  <td class="yx">行 业</td>
				                  <td class="yx">所在地区</td>
				                  <td class="yx">申 请 人</td>
				                  <td class="yx">手 机 号</td>
				                  <td class="yx">邮箱</td>
				                  <td class="yx">申请时间</td>
				                </tr>
			                 	<s:iterator value="list" id="user">
					                <tr  bgcolor="#FFFFFF" align="center">
						                  <td class="yx2"><s:property value="hangYe" /></td>
						                  <td class="yx2"><s:property value="diQu"/></td>
						                  <td class="yx2"><s:property value="userName" /></td>
						                  <td class="yx2"><s:property value="mobile" /> </td>
						                  <td class="yx2"><s:property value="email" /></td>
						                  <td class="yx2 lineheight180">	<s:date name="createTime" format="yyyy:MM:dd HH:mm:ss"/></td>
						            </tr>
			                	</s:iterator>	
			              </tbody>
		              </table>
		              <jsp:include page="/WEB-INF/jsp/common/page.jsp" />
		            </div><!-- zs03b -->
	          </div><!-- zs03 pt20 pr13 -->
	          
	          
	        </div><!-- zss01b pb15 -->
	        
	      </div><!-- zss01a -->
	      
	    </div><!-- zss -->
	
	    <div class="ss4 clearer">
	      <p class="left"><img src="<%=contextPath%>/background/images/jiao03.jpg"/></p>
	      <p class="right"><img src="<%=contextPath%>/background/images/jiao04.jpg"/></p>
	    </div>
		    
	  </div><!-- cxtab mt18 ml18 mr10 -->
	  
</div><!-- zhong ml5 -->

</body>

</html>