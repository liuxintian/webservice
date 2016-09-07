<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OMT Webservice Page List</title> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jquery-ui.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-ui.js"></script>
<style>
body {
	font-family: "Trebuchet MS", "Helvetica", "Arial",  "Verdana", "sans-serif";
	font-size: 62.5%;
}
</style>
<script type="text/javascript">
$(document).ready(function(){ 
	$( "#tabs" ).tabs({
		beforeLoad: function( event, ui ) {
		    ui.jqXHR.fail(function() {
		    ui.panel.html("Couldn't load this tab. We'll try to fix this as soon as possible. ");
		    });
		}
	});

});
function logout(){
	$.messager.confirm('Confirm','Are you sure to logout?',function(r){
		if(r){
			location.href = "<%=request.getContextPath()%>/logout"; 
		}
	});
}
function setSessionCode(code){
	$.ajax({
		async:true,
		type:"POST",
		url: "<%=request.getContextPath()%>/agm/setsessioncode", 
		data: {code :code},
		success: function(result) {
			batchidstr = result;
		}
	});
}
</script>
</head>
<body>  

<div id="tabs"> 
  <ul>
    <li><a class="tab" href="agm/index">AGM Question List</a></li>
    <li><a class="tab" href="agm/approved">AGM Approved List</a></li>
    <li><a class="tab" href="agm/online">AGM Online List</a></li>
    <li><a class="tab" href="meetings/all">Meeting management</a></li>
    <li><a class="tab" href="votinglist/all">Voting List</a></li>
    <li><a class="tab" href="stat/index">Data Access History</a></li>
    <li><a class="tab" href="stat/smahistory">SMA History Data</a></li>
    <li><a class="tab" href="stat/smadiff">SMA Percent Changing</a></li>
    <li><a class="tab" href="agm/news">WBC News List</a></li>
    <li><a class="tab" href="cfg/index">System Configuration</a></li>
    <li><a class="tab" href="cfg/help">System Help</a></li>
    <li><a class="tab" href="user/index">User Management</a></li>
    <li><a class="tab" href="<%=request.getContextPath()%>/logout">Logout</a></li>
  </ul>
</div>


</body>
</html>