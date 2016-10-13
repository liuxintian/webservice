<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Meeting Management</title> 
	<%@ include file="/pages/common/common_meta.jspf"%>
	<%@ include file="/pages/common/common_jsscripts.jspf"%>

	<script type="text/javascript">
	$(document).ready(function(){ 
		$(window).resize(function() {
			$('#tabDiv').tabs({
				height:$(window).innerHeight()-global_height_gap
			});
		});
		$('#tabDiv').tabs({
			tabPosition:"left",
    		height:$(window).innerHeight()-global_height_gap,
            pageList:global_pageList,
            pageSize:global_pageSize,
			justified: true 
		});
		
	});
	</script>
</head>
<body>  
  <div class="container">
   <%@ include file="/pages/common/common_header.jspf"%>
   
   <nav>
    <a href="<%=request.getContextPath()%>/agm/index">AGM Question List</a>
    <a href="<%=request.getContextPath()%>/agm/approved">AGM Approved List</a>
    <a href="<%=request.getContextPath()%>/meetings/all" class="visited">Meeting management</a>
    <a href="<%=request.getContextPath()%>/stat/index">Data Access History</a>
    <a href="<%=request.getContextPath()%>/cfg/index">System Configuration</a>
    <a href="<%=request.getContextPath()%>/logout">Logout</a>
   </nav>
   
   <article id="content">
 
	<div id="tabDiv" class="easyui-tabs" data-options="tools:'#tab-tools',cache:false" style="width:100%;height:600px;">
	        <div title="Company List" data-options="closable:false" style="overflow:hidden">
	            <iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/company/index" style="width:100%;height:100%;"></iframe>
	        </div>
	        <div title="Meeting List" data-options="closable:false" style="overflow:hidden">
	            <iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/meetings/index" style="width:100%;height:100%;"></iframe>
	        </div>
	        <div title="Contact List" data-options="closable:false" style="overflow:hidden">
	            <iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/contact/index" style="width:100%;height:100%;"></iframe>
	        </div>
	        <div title="Document List" data-options="closable:false" style="overflow:hidden">
	            <iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/document/index" style="width:100%;height:100%;"></iframe>
	        </div>
	</div>
	
   </article>
   <%@ include file="/pages/common/common_footer.jspf"%>
  </div>  
 </body>   
</html>