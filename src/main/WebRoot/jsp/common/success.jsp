<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>
<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<title>提示窗口</title>
<style>
<!--
body {
	background-color: #ffffff;
}
td { font-size: 12px;}
-->
</style>
</head>
<body>
	<div style="margin-top: 150px;">
		<table align="center" border="0" width="300" cellspacing="0"cellpadding="0" height="200">
			<tr>
				<td background="<%=request.getContextPath()%>/back/images/good.jpg">
					<table align="right" cellspacing="0"cellpadding="0" width="200" height="200" border="0" style="margin-right: 3px;">
						<tr>
							<td height="115">
								<div>
												提交成功!<a href="###" onclick="javascript:history.go(-1);">返回</a>！
							</td>
						</tr>
						<tr>
							<td height="62">
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>