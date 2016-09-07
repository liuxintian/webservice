<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WestPac ASX announcements</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/color.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datagrid-detailview.js"></script>
	
<script type="text/javascript">
$(document).ready(function(){
	$(window).resize(function() {
		$('#dgNews').datagrid({
    		height:$(window).innerHeight()-100
		});
	});
    $(function(){
    	$('#dgNews').datagrid({
    		height:$(window).innerHeight()-100
    	});
    });
    window.setTimeout(reload, 100);
});

function reload() {
	$(window).resize(function() {
		$('#dgNews').datagrid({
    		height:$(window).innerHeight()-100
		});
	});
	$('#dgNews').datagrid('reload'); 
}
function download(){
	window.location.href = "<%=request.getContextPath()%>/agm/newsdownload";
}

</script>
</head>
<body><center>
	<h2>WestPac ASX announcements</h2>
	<table id="dgNews" title="ASX announcements" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/agm/newslist" idFiled="id"
			toolbar="#toolbarNews" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			    <th field="date" width="40">Date</th>
				<th field="title" width="60">Title</th>
				<th field="url" width="150">Link</th>
			</tr>
		</thead>
	</table>
	<div id="toolbarNews">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="download()">Download as a CSV file</a>
	</div>
</center>
</body>
</html>