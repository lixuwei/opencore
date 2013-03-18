<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户组修改</title>
<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript">
			var groupId = "<s:property value="group.groupId"/>";
			
			$().ready(function() {
				
				var parentId="<s:property value="secondGroup.groupId"/>";
				var f_parentId="<s:property value="firstGroup.groupId"/>";
				var level="<s:property value="group.level"/>";
	 			if(level==2){
	 				$("#fGroupId").val(parentId);
	 				onchangeShow(parentId);
	 			}else if(level==3){
	 				$("#fGroupId").val(f_parentId);
	 				$("#sGroupId").val(parentId);
	 			}
	 			 
	 		});
		
			function onchangeShow(pId){
				
				if(pId == -1) {
					document.getElementById('sGroupId').options.length = 1;
					document.getElementById('group.parentGroupId').value=-1;
					document.getElementById('group.level').value=1;
					return;
				}
				document.getElementById('group.parentGroupId').value=pId;
				document.getElementById('group.level').value=2;
				
				$.ajax({  
					url : "<%=contextPath%>/sys/user!getAllGroup.action",   
					data : {groupId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:function(result){
						document.getElementById('sGroupId').options.length = 1;  //清空原有的option 
			
						var str="";  
						for(var i=0;i<result.entity.length;i++){  
							str+="<option value='"+result.entity[i].groupId+"'>"+result.entity[i].groupName+"</option>"  
						}
						$("#sGroupId").html($("#sGroupId").html() + str); 
					}
				});  
			} 
				
			
			function onchangeSecond(pId){
				if(pId == -1) {
					document.getElementById("group.parentGroupId").value = document.getElementById("fGroupId").value;
					document.getElementById("group.level").value = 2;
				} else {
					document.getElementById('group.parentGroupId').value=pId;
					document.getElementById('group.level').value=3;
				}
			}
			
			function checkSubmit(){
				var fid = document.getElementById('group.parentGroupId').value;
				if(fid==null ){
					document.getElementById('group.parentGroupId').value=-1;
					document.getElementById('group.level').value=1;
				}
				if(fid==groupId){
					alert("父级不能选择本身!");
					return false;
				}
				return true;
			}

		</script>
	</head>
	<body>
<div>
	<form action="<%=contextPath%>/sys/group!updateGroup.action" name="updateForm" id="updateForm" method="post" onsubmit=" return checkSubmit();">
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
	
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">用户组修改</font>
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
						选择用户组父级：
					</td>
					<td class="lists_tleft">
					
						<s:select name="fGroupId" id="fGroupId" list="firstList"
							listKey="groupId" listValue="groupName" headerKey="-1"
							headerValue="请选择" theme="simple" onchange="onchangeShow(this.value);">
						</s:select>
						
						<s:select name="sGroupId" id="sGroupId" list="#{}"  headerKey="-1"
							headerValue="请选择" theme="simple" onchange="onchangeSecond(this.value);">
						</s:select>
					</td>
				</tr>
				<tr>
					<td>
						用户组名称：
					</td>
					<td class="lists_tleft">
						<input type="text" name="group.groupName" id="group.groupName" class="{required:true}" value="<s:property value="group.groupName"/>"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="hidden" name="group.parentGroupId" id="group.parentGroupId" value="<s:property value="group.parentGroupId"/>" />
						<input type="hidden" name="group.level" id="group.level" value="<s:property value="group.level"/>" />
						<input type="hidden"  value="<s:property value="group.groupId"/>" name="group.groupId" id="group.groupId" />
						<input type="submit" value="提交" />
						<input type="button" onclick="history.go(-1)" value="返回"/>
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
