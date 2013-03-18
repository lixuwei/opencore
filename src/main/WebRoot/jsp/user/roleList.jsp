<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>角色列表</title>
		<link rel="stylesheet" type="text/css" media="screen"	href="<%=contextPath%>/back/style/css_body.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js" ></script>
		<script type="text/javascript"	src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js" ></script>
		<script type="text/javascript" src="<%=contextPath %>/back/script/dtree.js"></script>
	
		<script type="text/javascript">
		
			var old_rId;//记录上一次切换的下拉值。
			
	        $(document).ready(function() {
	        	old_rId="<s:property value="role.roleId"/>";
	        	$("#roleId").val(old_rId);
	            //默认显示权限选中
		        <s:iterator value="role.functionList" id="function" status="h">
		            if ($("#sfId${functionId}") != undefined && null != $("#sfId${functionId}")) {
		                $("#sfId${functionId}").attr("checked", true);
		            }
		        </s:iterator>
		        cancelAddRole();
	        })
       

			function clickBox(id){
				$.each($("input[pid=" + id + "]"), function () {
					this.checked = $('#' + id).attr("checked");
					clickBox(this.id);
				});
			}

	        function addRole() {
	        	d.closeAll();
	            $("#overlay").width($(document).width());
	            $("#overlay").height($(document).height());
	            $("#overlay").show();
	            $("#addRole").show();
	        }
			function cancelAddRole() {
				$("#addRoleForm_addRole_roleName").val("");	
	            $("#overlay").hide();
	            $("#addRole").hide();
	            //d.openAll();
	        }	
	        function delRole() {
	        	if ($("#roleId").val()==-1){
        			alert("请选择角色");
        			return false;
        		}
	            if (confirm("确定删除角色 "+$("#roleId").find("option:selected").text()+" ？")) {
	                document.location.href = "<%=contextPath%>/sys/roleAdmin!delRole.action?role.roleId="+$("#roleId").val()+"";
	            } else {
	                return false;
	            }   
	            return true;
	            
	        }
        
        	
	        //切换下拉效果
	        function onchangeRole(rId){
	        	if(old_rId==rId){
	        		return ;
	        	}
	        	old_rId=rId;
	        	$("#reset").click();//清空所有
	        	$("#roleId").val(rId);
	        	$.ajax({
						url : "<%=contextPath%>/sys/roleAdmin!getJsonRoleById.action",   
						data : {"role.roleId": rId},  // 参数  
						type : "post",  
						cache : false,  
						dataType : "json",  //返回json数据 
						error: function(){ 
							alert('error');      
						}, 
						success:function(result){
						
							var role=result.entity;
							if(role ==null) return;
							var functionList=role.functionList;
							if(functionList ==null) return;
							for(var i=0;i<functionList.length;i++){  
								var sfiftmp="#sfId"+functionList[i].functionId;
								if ($(sfiftmp) != undefined && null != $(sfiftmp)) {
					                $(sfiftmp).attr("checked", true);//选中显示
					        	}	
							}
							
						}
					}); 
        	}
        
        	function updateRole(){
        		if ($("#roleId").val()==-1){
        			alert("请选择角色");
        			return false;
        		}
        		return true;
        	}
    	</script>
    	
 <!--  添加角色 弹窗  -->   	
 <style type="text/css" >
		#overlay {
			background: #303030;
			opacity: 0.50;
			filter: Alpha(opacity =   50);
			display: none;
			position: absolute;
			top: 0px;
			left: 0px;
			z-index: 100;
		}
		
		#addRole {
			margin-left: auto;
			margin-right: auto;
			border: 2px solid #FFFFFF;
			font-size: 12px;
			font-family: "宋体";
			color: #990000;
			padding-top: 20px;
			width: 400px;
			height: 200px;
			position: absolute;
			z-index: 110;
			display: none;
			background: #e7e7e7;
			left: 35%;
			top: 20%;
			opacity: 0.85;
			filter: Alpha(opacity =   85);
		}
	</style>
	    
</head>
<body>
<div>
	<form action="<%=contextPath%>/sys/roleAdmin!updateRoleFunction.action" method="post" id="updateRoleFunctionForm" onsubmit="return updateRole();">
	
	<table width="100%" border="0" cellspacing="0"  cellpadding="0" class="lists">
		<tr >
			<td class="td_wid_l">
			<img src="<%=contextPath%>/back/images/tab_03.gif" />
			</td>
			<td class="lists_top">
				<font class="lists_fleft">角色管理</font>
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
				    <td width="15%" valign="top">
					    
					    <s:select name="role.roleId" id="roleId"   list="roleList"
								listKey="roleId" listValue="roleName" headerKey="-1"
								headerValue="请选择" theme="simple" onclick="onchangeRole(this.value);">
						</s:select>
								
					</td>
					<td  class="lists_tleft">
						<input type="hidden" value="1" name="selectedFunctionIds"/>
						<script type="text/javascript">
							d = new dTree('d','<%=contextPath%>/back/images/dtree');
							d.add(1,-1,'系统功能权限');
							var flag = true;
							<s:iterator value="firstLevelFunctionList">
								<s:if test="functionTypeId==2">
									d.add(${functionId},${parentFunctionId},'<input type="checkbox" name="selectedFunctionIds" pid="sfId${parentFunctionId}" id="sfId${functionId}" onclick="clickBox(\'sfId${functionId}\')" value="${functionId}"/>${functionName}');
								</s:if>
								<s:else>
									d.add(${functionId},${parentFunctionId},'<input type="checkbox" name="selectedFunctionIds" pid="sfId${parentFunctionId}" id="sfId${functionId}" onclick="clickBox(\'sfId${functionId}\')" value="${functionId}"/><font color="red">${functionName}</font>');
								</s:else>
							</s:iterator>
							document.write(d);
						</script>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="lists_tleft">
						<input type="button" value="添加角色" onclick="addRole();"/>
						<input type="button" value="删除选中角色" onclick="delRole();"/>
						<input type="reset"  id="reset" value="清空"/>
						<input type="submit" value="保存"/><font color="red">*红字为功能权限，其他为菜单权限</font>
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

<!-- 添加角色 -->
<div id="addRole" class="addRole">
	<form action="<%=contextPath%>/sys/roleAdmin!addRole.action" method="post" id="addRoleForm">
		<table border="0" width=“100%" height="100%" >
			<tr>
				<td align="center">
					新角色名称：
					<s:textfield name="addRole.roleName"
						id="addRoleForm_addRole_roleName" />
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="submit" value="确定"/>
					<input type="button" onclick="cancelAddRole();" value="取消"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<div>
   <div id="overlay" class="overlay"/>
</div>

<!-- 添加角色 -->

</body>
</html>
