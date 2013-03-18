<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>角色列表</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
	<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" /></script>
	<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" /></script>
<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	 <s:iterator value="role.columnList" id="column" status="h">
	     if ($("#sfId${columnId}") != undefined && null != $("#sfId${columnId}")) {
	         $("#sfId${columnId}").attr("checked", true);
	     }
	 </s:iterator>
	})

	function clickBox(id){
		$.each($("input[pid=" + id + "]"), function () {
			this.checked = $('#' + id).attr("checked");
			clickBox(this.id);
		});
	}
	
	function setRole(ids) {
	var s = '1';
		$('input[name=ids]:checked').each(function() {
			s +=  ','+$(this).val() ;
		});
	window.location.href = "<%=contextPath %>/sys/user!updataUserRole.action?user.userContentRole="+s+"&&user.userId="+${user.userId};
		
	}
  </script>
</head>
<body>
<div>
	<s:form action="column!delColumnContentRoles" method="POST" id="updateColumnContentRoles">
	<s:hidden name="role.roleId" />
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">权限设置</font>
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
					<td class="lists_tleft">
						<script type="text/javascript">
							d = new dTree('d','<%=contextPath%>/back/images/dtree');
							d.add(1,-1,'内容管理权限');
							var flag = true;
							<s:iterator value="columnList">
							
									d.add(${columnId},${parentId},'<input type="checkbox" name="ids" pid="sfId${parentId}" id="sfId${columnId}" onclick="clickBox(\'sfId${columnId}\')" value="${columnId}"/>${columnName}');
								
							</s:iterator>
							document.write(d);
						</script>
					</td>
				</tr>
				<tr>
					<td class="lists_tleft">
						<input type="button" value="设置权限" onclick="setRole(ids);"/>
						<input type="reset" value="清空"/>
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
