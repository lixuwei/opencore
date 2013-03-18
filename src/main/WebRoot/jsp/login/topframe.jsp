<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" media="screen" href="<%=contextPath%>/back/style/css_top.css" />
<script src="<%=contextPath %>/back/script/jquery-1.3.2.js" type="text/javascript"></script>
<script src="<%=contextPath %>/back/script/jquery.epiclock.js" type="text/javascript"></script>
<script type="text/javascript"> 
	$(function(){
	    $('#epiClock').epiclock({ format : ' Y年F月j日　D　G:i:s' });
	    $.epiclock();
	});
	
function clickLi(li) {
		
			 var $div_li =$("#top_menu_ul li");
			$div_li.click(function(){
		$(this).addClass("selected").siblings().removeClass("selected");  //去掉其它同辈<li>元素的高亮
	 // 获取当前点击的<li>元素 在 全部li元素中的索引。
	
});

		$(li).addClass("selected");
	}
</script>
</head>
<center>
<body>
<!-- 头部 -->
		<div class=topnav>
			<div class=page-top>
				<div class="logo">
					<a href="#"> 
					<img width="156" height="40" alt=""	src="<%=contextPath %>/back/images/shared/logo.gif"/>
					</a>
				</div>
				<div class="info">
					<div class=welcome>
						你好：
						<span class="username"><s:property value="#session.sys_user.userName" />
						</span>，欢迎您登录！
					</div>
					<div class="sitelink">
						<a href="backMain.action" target="_blank">网站主页</a> |
						<a href="<%=contextPath %>/sys/login/login!logout.action" title="退出系统">退出系统</a>
					</div>
				</div>
			</div>
		</div>
		
		<div class="nav-outer-repeat">
  		<!--  start nav -->
			  <div class="nav">
			     
				    <ul class="tab" id="top_menu_ul">
				      <li></li>
				     <s:iterator value="#session['tabUserFunctionList']" status="status" id="tabList">
						<s:if test="#status.index==0">
							<li onclick="clickLi(this)" style="MARGIN-LEFT: -1px" class="thisclass" >
						</s:if>
						<s:elseif test="#status.count==#session['tabUserFunctionList'].size">
							<li onclick="clickLi(this)" style="MARGIN-LEFT: -1px" > 
						</s:elseif>
						<s:else>
							<li onclick="clickLi(this)" >
						</s:else>
						<a href="<%=contextPath %>/jsp/login/leftframe.jsp?functionId=<s:property value="#tabList[0].functionId"/>" target="leftFrame"><s:property value="#tabList[0].functionName"/></a>
						 </li>
					</s:iterator>
				    </ul>
			  </div>
  			<div class="clear"></div>
		</div>

<!-- 头部 //-->
</body>
</center>
</html>