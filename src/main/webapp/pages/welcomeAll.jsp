<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OMT Meeting Management</title> 
	<link rel="stylesheet" type="text/css" href="css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="css/easyui.css"> 
	<link rel="stylesheet" type="text/css" href="css/icon.css">
	<link rel="stylesheet" type="text/css" href="css/color.css">
	<link rel="stylesheet" type="text/css" href="css/demo.css">
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/datagrid-detailview.js"></script>

<script type="text/javascript">
$(document).ready(function(){ 
	$(window).resize(function() {
		$('#tabDiv').tabs({
    		height:$(window).innerHeight()-100
		});
	});
	$('#tabDiv').tabs({
		tabPosition:"top",
		height:$(window).innerHeight()-100,
		justified: true 
	});
	
});


</script>
</head>
<body>  
<center>
	<h2>OMT Web Service Platform</h2>
</center>
<div id="tabDiv" class="easyui-tabs" data-options="tools:'#tab-tools',cache:true" style="width:100%;height:600px;">
        <div title="Agm Question List" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="agm/index" style="width:100%;height:100%;"></iframe>
        </div>  
        <div title="Agm Approved List" data-options="closable:false" style="overflow:hidden">  
            <iframe scrolling="yes" frameborder="0"  src="agm/approved" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="Agm Online List" data-options="closable:false" style="overflow:hidden"> 
            <iframe scrolling="yes" frameborder="0"  src="agm/online" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="Meetings Management" data-options="closable:false" style="overflow:hidden"> 
            <iframe scrolling="yes" frameborder="0"  src="meetings/all" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="Data Access History" data-options="closable:false" style="overflow:hidden"> 
            <iframe scrolling="yes" frameborder="0"  src="stat/index" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="SMA History Data" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="stat/smahistory" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="SMA Percent Changing" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="stat/smadiff" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="Westpac News List" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="agm/news" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="System Configs" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="cfg/index" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="System Help" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="cfg/help" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="User Management" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="user/index" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="Logout" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="logout" style="width:100%;height:100%;"></iframe>
        </div>
</div>

</body>
</html>