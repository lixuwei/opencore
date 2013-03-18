<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %> 
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<title>用户信息修改</title>
		<script type="text/javascript" src="<%=contextPath%>/back/script/validate.js"></script>
		<link type="text/css" rel="stylesheet" href="<%=contextPath%>/background/css/base.css" />
		<link type="text/css" rel="stylesheet" href="<%=contextPath%>/background/css/layout.css" />
		<script type="text/javascript" src="<%=contextPath%>/back/script/jquery-1.3.2.js"></script> 
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/jquery.validate.js"></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/localization/messages_cn.js" ></script>
		<script type="text/javascript" src="<%=contextPath%>/back/script/jQueryValidate/lib/jquery.metadata.js" ></script>
		<script type="text/javascript">
			$().ready(function() {
				jQuery.validator.addMethod("tel", function(value, element) {
					var pattern = /^[0-9]{1}[0-9-]*$/;
	 					return this.optional(element) || pattern.test(value);
	 				});
				jQuery.extend(jQuery.validator.messages, { 
		  			equalTo : "两次密码输入不一致",
		  			tel : "只能输入数字和中划线"
				}); 
	 			$("#updateForm").validate();
	 		});
	 		
			function getFChildGroup(pId){
				$.ajax({  
					url : "<%=contextPath%>/sys/user!getAllGroup.action",  
					data : {groupId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success:function(data){
						document.getElementById('sGroupId').options.length = 1;  //清空原有的option 
						var str="";  
						var entity =data.entity;
						for(var i=0;i<entity.length;i++){  
							str+="<option value='"+entity[i].groupId+"'>"+entity[i].groupName+"</option>"  
						}  
						$("#sGroupId").html($("#sGroupId").html() + str);
						document.getElementById('tGroupId').options.length = 1;
					}  
				});  
			} 
			

			function getSChildGroup(pId){
				$.ajax({  
					url : "<%=contextPath%>/sys/user!getAllGroup.action",  
					data : {groupId : pId},  // 参数  
					type : "post",  
					cache : false,  
					dataType : "json",  //返回json数据 
					error: function(){ 
						alert('error');      
					}, 
					success: function(data){
						document.getElementById('tGroupId').options.length = 1;  //清空原有的option 
						var str="";  
						var entity =data.entity;
						for(var i=0;i<entity.length;i++){  
							str+="<option value='"+entity[i].groupId+"'>"+entity[i].groupName+"</option>"  
						}  
						$("#tGroupId").html($("#tGroupId").html() + str);  
					}
				});  
			} 
				
			
	 		
			function checkSubmit(){
				var fGroupId = document.getElementById("fGroupId").value;
				var sGroupId = document.getElementById("sGroupId").value;
				var tGroupId = document.getElementById("tGroupId").value;
				if(tGroupId!='-1'){
					document.getElementById("user.groupId").value = tGroupId;
				}else if(sGroupId!='-1'&&tGroupId=='-1'){
					document.getElementById("user.groupId").value = sGroupId;
				}else{
					alert("用户必须归属二级组或三级组下!");
					 return false;
				}
				return true;
			}
			
			function sub(form){
				if(checkSubmit()){
					form.submit();
				};
			};
			
		</script>
</head>
<body>

<!-- 内容 开始  -->
<div class="zhong ml5">

	<div class="dqnav">
    	<p class="ml15 pl20">当前位置：<a href="#">用户管理</a>&gt; <a href="#">用户列表</a> </p>
    </div>
    
    
	<div class="cxtab mt18 ml18 mr10">
	
	    <div class="ss clearer">
	      <p class="left"><img src="<%=contextPath%>/background/images/jiao01.jpg" /></p>
	      <p class="right"><img src="<%=contextPath%>/background/images/jiao02.jpg" /></p>
	    </div>
	    
	    <div class="zss">
	    
	      <div class="zss01a">
	      
	        <div class="zss01b pb15">
	          
	          
	          <!-- form开始 -->
	          <form  action="<%=contextPath%>/sys/user!editUser.action" id="updateForm" method="post" name="updateForm">
					<div class="zss02 pt8 pl8 pr8">
						<div class="ss2 clearer sbg">
							<p class="left"><img src="<%=contextPath%>/background/images/zi_01.jpg"/></p>
							<p class="right"><img src="<%=contextPath%>/background/images/zi_02.jpg"/></p>
						</div>
						 <div class="zss02b">
					        	<div class="zs01">
					                <div class="zs02 pb10">
					                  <div class="stab_tuijian ml18 pt15">
					                    	<dl class="left pb6 font12px">
					                        	<dt class="left bold mr6" ><font color="red">*</font>用户名:</dt>
					                        	<dd class="left">
					                        	<input type="text" readonly="readonly" name="user.loginName" id="user.loginName"	value="<s:property value="user.loginName"/>" />
												</dd>
					                        </dl>  
					                  </div>
					                  
					                  <div class="stab_tuijian ml18 pt15">
					                    	<dl class="left pb6 font12px">
					                        	<dt class="left bold mr6" ><font color="red">*</font>真实姓名</dt>
					                        	<dd class="left"><input type="text" name="user.userName" id="user.userName"
												value="<s:property value="user.userName"/>" class="{required:true,maxlength:20}"/></dd>
					                        </dl>  
					                  </div>
					                  
					                  <div class="stab_tuijian ml18 pt15">
					                    	<dl class="left pb6 font12px" style="width:575px;">
					                        	<dt class="left bold mr6" ><font color="red">*</font>所属用户组</dt>
					                        	<dd class="left" style="width:100px;"> 
						                        	<select id="fGroupId" onchange="getFChildGroup(this.value)">
																<option value="-1">
																	请选择
																</option>
																<s:if test="user.group.level>1">
																	<s:iterator value="groupList" id="group">
																		<s:if test="#group.level==1">
																			<option value="<s:property value="#group.groupId"/>" <s:property value="first_g.groupId==#group.groupId?'selected':''"/>><s:property value="#group.groupName"/></option>
																		</s:if>
																	</s:iterator>
																</s:if>
																
													</select>
												</dd>
												<dd class="left" style="width:100px;">			
														<select id="sGroupId"  onchange="getSChildGroup(this.value)">
															<option value="-1">
																请选择
															</option>
															<s:if test="user.group.level>1">
																<s:iterator value="groupList" id="group">
																	<s:if test="#group.level==2&&#group.parentGroupId==first_g.groupId">
																		<option value="<s:property value="#group.groupId"/>" <s:property value="second_g.groupId==#group.groupId?'selected':''"/>><s:property value="#group.groupName"/></option>
																	</s:if>
																</s:iterator>
															</s:if>
														</select>
												</dd>
												<dd class="left" style="width:100px;">	
													<select id="tGroupId">
															<option value="-1">
																请选择
															</option>
															<s:if test="user.group.level>1">
																<s:iterator value="groupList" id="group">
																	<s:if test="#group.level==3&&#group.parentGroupId==second_g.groupId">
																		<option value="<s:property value="#group.groupId"/>" <s:property value="third_g.groupId==#group.groupId?'selected':''"/>><s:property value="#group.groupName"/></option>
																	</s:if>
																</s:iterator>
															</s:if>
														</select>
												</dd>
					                        </dl>
					                       	
												  
					                  </div>
					                  <div class="stab_tuijian ml18 pt15">
					                    	<dl class="left pb6 font12px" style="width:575px;">
					                        	<dt class="left bold mr6" >联系电话</dt>
					                        	<dd class="left" style="width:275px;"><input type="text" name="user.tel" id="user.tel"
												value="<s:property value="user.tel"/>"  class="{required:true,tel:true}"/></dd>
					                        </dl>  
					                  </div>
					                  <div class="stab_tuijian ml18 pt15" >
					                    	<dl class="left pb6 font12px"  style="width:575px;" >
					                        	<dt class="left bold mr6" >email</dt>
					                        	<dd class="left"  style="width:275px;"  ><input type="text" name="user.email" id="user.email"
												value="<s:property value="user.email"/>" class="{required:true,email:true,maxlength:50}" />
												</dd>
					                        </dl>  
					                  </div>
					                  <div class="stab_tuijian ml18 pt15">
					                    	<dl class="left pb6 font12px">
					                        	<dt class="left bold mr6" >性别</dt>
					                        	<dd class="left"><input type="radio" name="user.userTypeId" value="1" <s:property value="user.userTypeId==1?'checked':''"/> /> 男
												<input type="radio" name="user.userTypeId" value="0" <s:property value="user.userTypeId==0?'checked':''"/> /> 女</dd>
					                        </dl>  
					                  </div>
							
					                </div>
					                
					                <div class="as03">
					                	<input type="hidden" name="user.loginPwd" id="user.loginPwd" value="<s:property value="user.loginPwd"/>"/>
										<input type="hidden" name="user.userId" id="user.userId" value="<s:property value="user.userId"/>"/>
										<input type="hidden" name="user.status" id="user.status" value="<s:property value="user.status"/>"/>
										<input type="hidden" name="user.groupId" id="user.groupId" value="<s:property value="user.groupId"/>"/>
										<a onclick="sub(document.updateForm)" href="#" ><img src="<%=contextPath%>/background/images/butt_20.jpg"/></a>
										<a onclick="history.go(-1)"><img src="<%=contextPath%>/background/images/butt_fanhui.jpg"/></a>
									    
									</div>	 
											
					              </div>
					      </div>
				            <div class="ss3 clearer sbg">
				              <p class="left"><img src="<%=contextPath%>/background/images/zi_03.jpg" /></p>
				              <p class="right"><img src="<%=contextPath%>/background/images/zi_04.jpg" /></p>
				            </div>
					  
					 
	          
					 </div> 
          	  </form>
          
	          <!-- form结束 -->
	          
	          
	          
	        </div><!-- zss01b pb15 -->
	        
	      </div><!-- zss01a -->
	      
	    </div><!-- zss -->
	
	    <div class="ss4 clearer">
	      <p class="left"><img src="<%=contextPath%>/background/images/jiao03.jpg"/></p>
	      <p class="right"><img src="<%=contextPath%>/background/images/jiao04.jpg"/></p>
	    </div>
		    
	  </div><!-- cxtab mt18 ml18 mr10 -->
	  
</div><!-- zhong ml5 -->

<!-- 内容 结束 -->	
	
	
</body>
</html>
