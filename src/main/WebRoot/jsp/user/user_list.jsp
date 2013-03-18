<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
	<title>用户列表</title>
		
	<link type="text/css" rel="stylesheet" href="<%=contextPath%>/background/css/base.css" />
	<link type="text/css" rel="stylesheet" href="<%=contextPath%>/background/css/layout.css" />
	<script src="<%=contextPath%>/js/common/jquery-1.7.2.min.js"></script>
	

<script type="text/javascript">
		 //冻结、解冻
		 function freeze(userId,obj){
		 var id= "#freeze"+userId;
		 var tdid="#tdstatus"+userId;
		 		var ss = $(id).html();
		 		var vv = null;
		 		if(ss=='冻结'){
		 			vv = '解冻';
		 			tt="冻结";
		 		}else{
		 			vv = '冻结';
		 			tt="正常";
		 		}
                $.ajax({
                    url:"<%=contextPath%>/sys/user!freezeUser.action",
                    data:"user.userId="+userId,
                    dataType:"text",
                    success:function(msg){
                        $(id).html(vv);
                        $(tdid).html(tt); 
                    }
                });
            }
			//搜索
			function search(){
				var $searchKey = $("#searchKey").val();
				var searchKey = $.trim($searchKey) 
				if(searchKey==""){
					alert("请输入用户名/姓名！");
				}else{
					searchKey = encodeURIComponent(encodeURIComponent(searchKey));
					window.location="<%=contextPath%>/sys/user!listUserByKey.action?page.currentPage=1&searchKey="+searchKey;
				}
			}
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
	          
	          
	          <!-- 搜索form开始 -->
	          <form action="<%=contextPath%>/sys/user!listUserByKey.action?page.currentPage=1" method="post" id="guestForm" name="guestForm">
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
					                        	<dt class="left bold mr6" >用户名/姓名：</dt>
					                        	<dd class="left"><input type="text" name="searchKey" id="searchKey" value="${searchKey}"/></dd>
					                        </dl>  
				                        	<div class="as03">
									        	<a  href="javascript:search()"><img src="<%=contextPath%>/background/images/cx-anniu02.jpg"/></a>
									        	<a  href="<%=contextPath%>/sys/sys/user!toAddUser.action">添加新用户</a>
											</div>	       
					                  </div>
					                    
							
					                </div>
					              </div>
					            </div>
					            <div class="ss3 clearer sbg">
					              <p class="left"><img src="<%=contextPath%>/background/images/zi_03.jpg" /></p>
					              <p class="right"><img src="<%=contextPath%>/background/images/zi_04.jpg" /></p>
					            </div>
					          </div><!--
					        <div class="as03">
					        	<a  href="javascript:search()"><img src="<%=contextPath%>/background/images/cx-anniu02.jpg"/></a>
							</div>		          
          	  --></form>
          
	          <!-- 搜索form结束 -->
	          <div class="zs03 pt20 pr13">
		            <div class="zs03b">
		              <table width="100%" cellspacing="0" cellpadding="0" border="0" style=" border-left:1px solid #d9d9d9; border-top:1px solid #d9d9d9;">
			              <tbody>
				                <tr  bgcolor="#f2f2f2" align="center" class="yx">
				                  <td class="yx">用户名</td>
				                  <td class="yx">真实姓名</td>
				                  <!-- <td class="yx">用户组</td> -->
				                  <td class="yx">状态</td>
				                  <td class="yx">联系电话</td>
				                  <td class="yx">创建时间</td>
				                  <s:if test="queryUserCondition.userType==1">
								  	<td>权限明细</td>
								  </s:if>
								  <td>操作</td>
				                  
				                </tr>
			                 	<s:iterator value="userList" id="user">
					                <tr hegith="40"  bgcolor="#FFFFFF" align="center">
						                  <td class="yx2"><s:property value="loginName" /></td>
						                  <td class="yx2"><s:property value="userName" /></td>
						                  <!-- <td class="yx2"><s:property value="group.groupName" /></td> -->
						                  <td class="yx2" id="tdstatus${user.userId}">
						                  	<s:if test="#user.status==0">正常</s:if>
											<s:if test="#user.status==1">冻结</s:if>
										  </td>
						                  <td class="yx2"><s:property value="tel" /></td>
						                  <td class="yx2"><s:date name="createTime" format="yyyy:MM:dd HH:mm:ss"/></td>
						                   
						                  <td class="yx2 lineheight180">
						                  		
												<a	href='<%=contextPath%>/sys/user!toEditUserGradeSubjectRoleSec.action?user.userId=<s:property value="userId"/>'>角色对应范围修改</a>
												<a	href='<%=contextPath%>/sys/user!toEditUser.action?user.userId=<s:property value="userId"/>'>修改</a>
												<s:if test="#user.status==0">
													<a id="freeze${user.userId}" href='#' onclick="freeze(${user.userId},this);">冻结</a>	
												</s:if>
												<s:if test="#user.status==1">
													<a href='#' id="freeze${user.userId}" onclick="freeze(${user.userId},this);">解冻</a>	
												</s:if>
												<a href='<%=contextPath%>/sys/user!deleteUser.action?user.userId=<s:property value="userId"/>'>删除</a>
												
												<a href='user!<%=contextPath%>/sys/user!toUpdatePwd.action?user.userId=<s:property value="userId"/>'>修改密码</a>
												<!--
												<a href="loginLog!getByUserId.action?queryLoginLogCondition.currentPage=1&queryLoginLogCondition.userId=<s:property value="userId"/>">查看登录日志</a>
												-->
												
										</td>
						            </tr>
			                	</s:iterator>	
			              </tbody>
		              </table>
		              <jsp:include page="/WEB-INF/jsp/common/page.jsp" />
		            </div><!-- zs03b -->
	          </div><!-- zs03 pt20 pr13 -->
	          
	          
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
