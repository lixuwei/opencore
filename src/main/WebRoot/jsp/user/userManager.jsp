<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/script/validate.js"></script>
<title>用户中心</title>
</head>
<body>
<div>
	<form action="<%=contextPath%>/sys/user!userManagerSubmit.action" method="post">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">用户自维护</font>
				<font class="lists_fright">
				</font>
			</td>
			<td class="td_wid_l">
				<img src="<%=contextPath%>/back/images/tab_07.gif" />
			</td>
		</tr>
		<tr>
			<td width="12"  class="lists_bor">
			</td>
			<td>
				<table  border="0" cellspacing="1" cellpadding="0" >
					<tr>
	                    <td class="lists_tright">登录名：</td>
	                    <td class="lists_tleft">${user.loginName}</td>
	                </tr>
					 <tr>
	                    <td class="lists_tright">用户名：</td>
	                    <td class="lists_tleft"><input id="userName" name="user.userName" value="${user.userName }"/></td>
	                </tr>
	                <tr>
	                    <td class="lists_tright">电话：</td>
	                    <td class="lists_tleft"><input id="tel" name="user.tel" value="${user.tel }"/></td>
	                </tr>
	                <tr>
	                    <td class="lists_tright">邮箱：</td>
	                    <td class="lists_tleft"><input id="email" name="user.email" value="${user.email }"/></td>
	                </tr>
	                <tr>
	                    <td class="lists_tright">邮编：</td>
	                    <td class="lists_tleft"><input id="zip" name="user.zip" value="${ user.zip}"/></td>
	                </tr>
	                <tr>
	                    <td class="lists_tright">地址：</td>
	                    <td class="lists_tleft"><input id="address" name="user.address" value="${user.address }"/></td>
	                </tr>
	                <tr>
	                	<td></td>
	                    <td class="lists_tleft">
	                        <input name="Submit" id="sub" type="submit" value="确定"  />
	                    </td>
	                </tr>
				</table>
			</td>
			<td width="16" class="lists_tright lists_bor2">
			</td>
		</tr>
		<tr>
			<td>
				<img src="<%=contextPath%>/back/images/tab_18.gif" />
			</td>
			<td class="lists_bottom">
			</td>
			<td>
				<img src="<%=contextPath%>/back/images/tab_20.gif" />
			</td>
		</tr>
	</table>
	</form>
</div>
</body>
</html>
