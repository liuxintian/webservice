<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OMT Webservice Page List</title> 
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
		$('#tabDiv').tabs({
    		height:$(window).innerHeight()-100
		});
	});
	$('#tabDiv').tabs({
		tabPosition:"left",
		height:$(window).innerHeight()-100,
		justified: true 
	});
	
	$('#code').combobox({
		onChange:function(newValue,oldValue){ 
			setSessionCode(newValue);
		},
		url:'<%=request.getContextPath()%>/user/rolesuser',
		method:'get',
        valueField:'code',
        textField:'name',
        prompt:'Loading...',
        panelHeight:'300' 		
	});
});

function refresh(){
		var tab = $('#tabDiv').tabs('getSelected'); 
 		var index = $('#tabDiv').tabs('getTabIndex',tab);
 		if(index < 2) tab.panel('refresh', urlAry[index]);
}

function logout(){
	$.messager.confirm('Confirm','Are you sure to logout?',function(r){
		if(r){
			location.href = "<%=request.getContextPath()%>/logout"; 
		}
	});
}
var urlAry = new Array('agm/index','agm/approved','agm/online','agm/online','stat/index','agm/news','cfg/index','cfg/help','user/index');
function setSessionCode(code){
	$.ajax({
		async:true,
		type:"POST",
		url: "<%=request.getContextPath()%>/agm/setsessioncode", 
		data: {code :code},
		success: function(result) {
		}
	});
	refresh();
	$("#currentlink").html("Switched to ["+code+"]");
}
</script>
</head>
<body>  
<div>
    <label>Select Company</label> 
    <input id="code" class="easyui-combobox" name="code" required="false"  editable="true" style="width:220px;">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="logout()" style="float:right; cursor:hand;">Logout</a>
	<label id="currentlink"> </label>
</div>
<div id="tabDiv" class="easyui-tabs" data-options="tools:'#tab-tools',cache:false" style="width:100%;height:600px;">
        <div class="tabs" href="agm/index" title="Agm Question List" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="agm/index" style="width:100%;height:100%;"></iframe>
        </div>
        <div class="tabs" href="agm/approved" title="Agm Approved List" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="agm/approved" style="width:100%;height:100%;"></iframe>
        </div>
        <div class="tabs" href="agm/online" title="Agm Online List" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="agm/online" style="width:100%;height:100%;"></iframe>
        </div>
        <div class="tabs" href="stat/index" title="Data Access History" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="stat/index" style="width:100%;height:100%;"></iframe>
        </div>
        <div class="tabs" href="agm/news" title="Westpac News List" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="agm/news" style="width:100%;height:100%;"></iframe>
        </div>
        <div class="tabs" href="cfg/index" title="System Configs" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="cfg/index" style="width:100%;height:100%;"></iframe>
        </div>
        <div class="tabs" href="cfg/help" title="System Configs" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="cfg/help" style="width:100%;height:100%;"></iframe>
        </div>
        <div class="tabs" href="user/index" title="User Management" data-options="closable:false" style="overflow:hidden">
            <iframe scrolling="yes" frameborder="0"  src="user/index" style="width:100%;height:100%;"></iframe>
        </div>
 </div>

</body>
</html>