<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Pragma" content="no-cache">
<title>系统异常</title>
<style>
<!--
body {
	background-color: #ffffff;
}
td { font-size: 12px;}
-->
</style>
<script type="text/javascript">
	function showOrCloseErrorInfo() {
		var div = document.getElementById("error_div");
		if(div.style.display == "none") {
			div.style.display = "block";
		} else {
			div.style.display = "none";
		}
	}
</script>
</head>
<body>
	<div style="margin-top: 150px;">
		<table align="center" border="0" width="300" cellspacing="0"cellpadding="0" height="200">
			<tr>
				<td background="<%=request.getContextPath()%>/back/images/er.gif">
					<table align="right" cellspacing="0"cellpadding="0" width="200" height="200" border="0" style="margin-right: 3px;">
						<tr>
							<td height="115">
								<div>
									系统出现异常情况，请于系统管理员联系。
									点击<a href="#" onclick="javascript:history.go(-1);">返回</a>！<br><br>
									或者<a href="/" ">返回首页</a>！<br><br>
									<a href="javascript:showOrCloseErrorInfo()">显示/隐藏错误信息</a>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div id="error_div" style="display:none">
			<s:property value="exceptionStack"/>
		</div>
	</div>
</body>
</html>
