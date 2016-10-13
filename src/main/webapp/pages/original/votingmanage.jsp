<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voting Management</title> 
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
		$('#votingDiv').tabs({
    		height:$(window).innerHeight()-100
		});
	});
	$('#votingDiv').tabs({
		tabPosition:"left",
		height:$(window).innerHeight()-100,
		justified: true  
	});
});

</script>
</head>
<body>  
 
<div id="votingDiv" class="easyui-tabs" data-options="tools:'#tab-tools',cache:false" style="width:100%;height:600px;">
        <div title="Voting User List" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/votinguser/index" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="Voting List" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/votinglist/index" style="width:100%;height:100%;"></iframe>
        </div>
        <div title="Voting Holder List" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="<%=request.getContextPath()%>/votingholding/index" style="width:100%;height:100%;"></iframe>
        </div>
</div>

</body>
</html>