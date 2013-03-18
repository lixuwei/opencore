<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
		<title>用户角色与应用范围设置修改</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script>
		<script type="text/javascript">
		 function del(roleId, userId){
		 	if(window.confirm("您确定要删除此角色吗？")) {
		 		window.location.href = "roleAdmin!deleteRoleRef.action?roleId=" + roleId + "&userId=" + userId;
		 	}
         }
       </script>
		
	</head>
	<body>
<div>
	<form action="user!listUserByKey.action" method="post">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">[用户角色修改]用户： <s:property value="{user.userName}" /></font>
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
				<s:iterator value="user.roleList" id="role">
					<tr>
					     <td>
					      	角色名称:
					     </td>
						<td class="lists_tleft" colspan="2">
					      	&nbsp;<s:property value="roleName" />&nbsp;
					      	<a href="#" onclick="del(<s:property value="roleId"/>, <s:property value="user.userId"/>)">删除</a>
					     </td>
					</tr>
					</s:iterator>
					<tr>
						<td colspan="3" class="lists_tcenter">
						
						<a href="<%=contextPath%>/sys/roleAdmin!toAddRoleRef.action?userId=<s:property value="user.userId"/>">添加角色</a>
						&nbsp;&nbsp;
						<a href="<%=contextPath%>/sys/user!listAllUser.action?page.currentPage=1">返回列表</a>
				
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
