<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/inc/header.inc" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:if test="page != null" >
  <table border="0" width="100%" height="100%" class="lists_td_ablock">
        <tr>
        	<td>
        	 共有 <s:property value="page.totalResultSize"/> 条记录，当前第 <s:property value="page.currentPage"/>/<s:property value="page.totalPageSize"/> 页
        	</td>
            <td width="45">
                <s:if test="!page.first">
                    <a href="#" onclick="goPage(1);">
                </s:if>
                <img src="<%=contextPath%>/images/page/first.gif" />
                <s:if test="!page.first">
                    </a>
                </s:if>
            </td>
            <td width="45">
                <s:if test="!page.first">
                    <a href="#" onclick="goPage(<s:property value="page.currentPage-1"/>);">
                </s:if>
              	<img src="<%=contextPath%>/images/page/back.gif" />
                <s:if test="!page.first">
                    </a>
                </s:if>
            </td>
            <td id="goPageByNumber" width="15">
            </td>
            <td width="45">
                <s:if test="!page.last">
                    <a href="#" onclick="goPage(<s:property value="page.currentPage+1"/>);">
                </s:if>
               	<img src="<%=contextPath%>/images/page/next.gif" /> 
                <s:if test="!page.last">
                    </a>
                </s:if>
            </td>
            <td width="45">
                <s:if test="!page.last">
                    <a href="#" onclick="goPage(<s:property value="page.totalPageSize"/>);">
                </s:if>
               	<img src="<%=contextPath%>/images/page/last.gif" />
                <s:if test="!page.last">
                    </a>
                </s:if>
            </td>
            <td width="50">
            	<div align="center"><span class="STYLE1">转到第</span></div>
            </td>
            <td width="25">
            	<div align="center"><span class="STYLE1">
                    <input name="textfield" id="pageNoIpt" type="text" size="4" style="height:16px; margin-top:2px; width:24px; border:1px solid #999999;" /> 
                    </span></div>
            </td>
            <td width="16">
            	<div align="center"><span class="STYLE1">页 </span></div>
            </td>
            <td width="45">
            	<a href="javascript:goPageByInput()"><img src="<%=contextPath%>/images/page/go.gif" width="37" height="15" /></a>
            </td>
        </tr>
    </table>  
</s:if>

<script type="text/javascript">

	var totalPageSize=<s:property value="page.totalPageSize"/>;//总页码
    function goPage(pageNum){
        var pageNoReg = new RegExp("\\.currentPage=[0-9]*");
        document.location="${pageParms}".replace(pageNoReg,".currentPage=" + pageNum);
    }
    
    function showPageNumber() {
    	var currentPage = <s:property value="page.currentPage-1"/><1?1:<s:property value="page.currentPage"/>;
    	var totalPage = <s:property value="page.totalPageSize"/>;
    	var td = document.getElementById("goPageByNumber");
    	var maxNum = currentPage>4?6:11-currentPage;
    	if(currentPage>5) {
    		td.innerHTML = td.innerHTML + "|&nbsp;&nbsp;&nbsp;&nbsp;";
    	}
    	for(var i=4; i>0; i--) {
    		if(currentPage>i) {
    			td.innerHTML = td.innerHTML + "<a href='#' onclick='goPage("+(currentPage-i)+")'>"+ (currentPage-i) +"</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    		};
    	}
    	td.innerHTML = td.innerHTML + "<font color=orange>"+currentPage+"</font>&nbsp;&nbsp;&nbsp;&nbsp;";
    	for(var i=1; i<maxNum; i++) {
    		if(currentPage+i<=totalPage) {
    			td.innerHTML = td.innerHTML + "<a href='#' onclick='goPage("+(currentPage+i)+")'>"+ (currentPage+i) +"</a>&nbsp;&nbsp;&nbsp;&nbsp;";
    		} else {
    			break;
    		};
    	}
    	if(currentPage+maxNum<=totalPage) {
    		td.innerHTML = td.innerHTML + "|&nbsp;&nbsp;&nbsp;&nbsp;";
    	};
    }
    
    function goPageByInput() {
    	var pageNo = document.getElementById("pageNoIpt").value;
    	if(/^\d+$/.test(pageNo)==false) {
    		alert("只能输入整数，请重新输入！");
    		document.getElementById("pageNoIpt").value='';
    		return;
    	}
    	if(pageNo < 1) {
    		pageNo = 1;
    	}
    	if(pageNo > totalPageSize) {
    		if(totalPageSize>0)
    			pageNo = totalPageSize;
    		else
    			pageNo=1;
    	}
    	goPage(pageNo);
    };
    
</script>