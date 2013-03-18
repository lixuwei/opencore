<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户密码修改</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript">
			$().ready(function() {
	 			$("#updateForm").validate({
	 				rules : {
	 					"user.loginPwd" : {
	 						required : true,
	 						minlength : 6,
	 						maxlength : 20
	 					}
	 				},
	 				messages : {
	 					"user.loginPwd" : {
	 						required : "请填写新密码",
	 						minlength : "密码不能低于6位",
	 						maxlength : "密码不能超过20位"
	 					}
	 				}
	 			});
	 		});
		</script>
	</head>
	<body>
<div>
	<s:form action="user!updatePwd" name="updateForm" id="updateForm" method="post">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">用户密码修改</font>
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
			<table width="100%" border="0" cellspacing="1" cellpadding="0" class="lists_info">
				<tr>
					<td width="20%">
						用户名：
					</td>
					<td class="lists_tleft">
						<input type="text" readonly="readonly" value="<s:property value="user.userName"/>"/>
					</td>
				</tr>
				<tr>
					<td>
						登陆名：
					</td>
					<td class="lists_tleft">
						<input type="text" readonly="readonly" value="<s:property value="user.loginName"/>"/>
					</td>
				</tr>
				<tr>
					<td>
						新密码：
					</td>
					<td class="lists_tleft">
						<input type="password" name="user.loginPwd" id="loginPwd" maxlength="25"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" value="<s:property value="user.userId"/>" name="user.userId"/>
						
						<input type="submit" value="提交" />
						<input type="button" value="返回" onclick="history.go(-1)"/>
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
	</s:form>
</div>
	</body>
</html>
