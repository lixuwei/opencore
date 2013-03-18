<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/inc/header.inc" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	
	<title>测试json首页</title>
	<script language="javascript" src="<%=imagesPath%>/js/common/jquery-1.7.2.min.js"></script>
	<script language="javascript" src="<%=imagesPath%>/js/common/jquery.validate.min.js"></script>
	<script language="javascript" src="<%=imagesPath%>/js/common/jquery-validation-1.9.0/jquery.validate.min.js"></script>
	<script language="javascript" src="<%=imagesPath%>/js/util/ssi_util.js?v=<%=version%>"></script>
	
  </head>
  <script type="text/javascript">
	  var baselocation = '<%=contextPath%>';
	  var imagesPath = '<%=imagesPath%>';
	  function getData(){
		  $.ajax({  
				url : "http://172.16.123.150:8080/ssi/customer/cus!tesajax.action",  
				data : {"queryCondition.cusId":500},   
				type : "post",
				cache : false,
				async : false,
				dataType : "json",
				success: function (result){
					 if(result.success == true){
						var d="";
						$.each(result.entity,function(name,value){
						 	d+=(value.cusId+"=="+value.email+"</br>");
						 });
						 $("#jsondata").html(d);
					} ;
					
				},error: function(){ 
					  alert("error");
				} 
			});
	  }
  </script>
  <body>
    test ajax;
    <input type="button" onclick="getData()" value="测试" />
    <div id="jsondata"></div>
  </body>
</html>
