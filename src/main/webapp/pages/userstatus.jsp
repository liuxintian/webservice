<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Online User List</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/color.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datagrid-filter.js"></script>
	
<script type="text/javascript">
$(document).ready(function(){
	$(window).resize(function() {
		$('#dgOnline').datagrid({
    		height:$(window).innerHeight()-100
		});
	});
    $(function(){
    	var dgOnline = $('#dgOnline').datagrid({
    		height:$(window).innerHeight()-100,
            url: '<%=request.getContextPath()%>/agm/queryonline',
            pagination: true,
            remoteFilter: true,
            rownumbers: true,
            pageList:[10,20,50,100,200],
            onLoadSuccess:function(){
            	$('#header').html("Current Online Users [Total: "+ $('#dgOnline').datagrid('getData').total +"]");
            }
        });
    });
    window.setInterval(reload, 30*1000);
});

function reload() {
	$('#dgOnline').datagrid('reload');
}
</script>
</head>
<body><center>
	<h2 id="header">Current Online Users</h2>
	
	<table id="dgOnline" title="Online User Information" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/agm/queryonline" idFiled="id"
			pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			    <th field="emailid" width="120">Email ID</th>
				<th field="time" width="160">DateTime</th>
				<th field="code" width="160">Company</th>
			</tr>
		</thead>
	</table>
	       
</center>
</body>
</html>