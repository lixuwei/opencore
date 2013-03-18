<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>权限添加</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
<script type="text/javascript">
	$().ready(function() {
			$("#addForm").validate();
		});
	
	function checkSubmit(){
		var fid = document.getElementById('parentFunctionId').value;
		if(fid==null || fid<=0){
			document.getElementById('parentFunctionId').value=1;
			document.getElementById('level').value=0;
		}
	}
	
	//更新父类节点
	function changeParentValue(id,name,level){
		document.getElementById("level").value = Number(level) + 1;
		document.getElementById("parentFunctionId").value = id;
		document.getElementById("parentName").value = name;
		document.getElementById("funcTree").style.display="none";
	}
	
	function showFunctionTree(){
		document.getElementById("funcTree").style.display="block";
	}
	
	function closeFunctionTree(){
		document.getElementById("funcTree").style.display="none";
	}
</script>
</head>
<body>
<div>
	<s:form action="func!addFunction" name="addForm" id="addForm" method="post" onsubmit="checkSubmit();">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">权限添加</font>
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
						选择父级权限：
					</td>
					<td class="lists_tleft">
		    			<input id="parentName" type="text" name="pName" onclick="showFunctionTree()" readonly="readonly" />
		    			<div id="funcTree" style="position:absolute;width:340px; background: #ffffff;border:1px #faf0d7 solid; display: none;">
		    				<a href="javascript:closeFunctionTree();">关闭</a>
						    <script type="text/javascript">
								<!--
								d = new dTree('d','<%=contextPath%>/back/images/dtree');
								var flag = true;
								<s:iterator value="functionList">
									d.add(${functionId}, ${parentFunctionId}, '${functionName}', 'javascript:changeParentValue(${functionId}, \'${functionName}\', ${level});');
								</s:iterator>
								document.write(d);
								//-->
							</script>
					    </div>
					</td>
				</tr>
				<tr>
					<td>
						权限名称：
					</td>
					<td class="lists_tleft">
						<input type="text" name="function.functionName" id="functionName" class="{required:true}"/>
					</td>
				</tr>
				<tr>
					<td>
						权限类型：
					</td>
					<td class="lists_tleft">
						<select name="function.functionTypeId" id="functionTypeId">
							<option value="2">菜单权限</option>
							<option value="1">功能权限</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>
						权限URL：
					</td>
					<td class="lists_tleft">
						<input type="text" name="function.functionUrl" id="functionUrl" />
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<s:hidden name="function.parentFunctionId" id="parentFunctionId" value="1"></s:hidden>
						<s:hidden name="function.level" id="level" value="0"></s:hidden>
						<input type="submit" value="提交" /><input type="button" value="返回" onclick="history.go(-1)"/>
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
