<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OMT Webservice Data Exchanger</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/color.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
	var disabled = false;
	var batchidstr = "";
	$(document).ready(function(){
		// 0. start get batchid
		$.ajax({
			async:true,
			type:"POST",
			url: "<%=request.getContextPath()%>/exchanger/getbatchid", 
			success: function(result) {
				batchidstr = result;
			}
		});
		
		// 1. start to process
	    $("#startbtn").click(function(){
	    	if(disabled) return;
			var cpId  = $('#datatype').combobox('getValue');
			var mode  = $('#mode').combobox('getValue');
			var messagestr  = $('#message').val();
			if(cpId == "" ||  mode == "" || messagestr == ""){
				return;
			}
			// alert(messagestr);
 			if(cpId == "" || mode == ""){
				return;
			}
 			$.ajax({
 				async:true,
 				type:"POST",
 				url: "<%=request.getContextPath()%>/exchanger/startprocess", 
 				data: { cpId :cpId, mode:mode, batchid: batchidstr, message: messagestr},
 				success: function(result) {
 					;
 				}
 			});
 			
	    	$('#p').progressbar('setValue', 0);
	    	$('#startbtn').linkbutton('disable');
	    	disabled = true;
	    	$('#p').show();
	    	start();
	    });
	});
	
	var intervals = "";
	function start(){
		intervals = setInterval("exchangeStart()", 200);
	};
	
	// 3. query current state
	function exchangeStart(){
		var value = $('#p').progressbar('getValue');
		if(value == '100'){
			clearInterval(intervals);
			$('#startbtn').linkbutton('enable');
			disabled = false;
			return;
		}
		
		$.ajax({
			async:true,
			type:"POST",
			
			url: "<%=request.getContextPath()%>/exchanger/queryprocess", 
			data: { batchid :batchidstr}, 
			
			success: function(result) {
				var sent = parseInt(result);
				$('#p').progressbar('setValue', sent);
			}
		});
	};
	</script>
</head>
<body style="text-ailgn:center;"><center>
	<h2>OMT Data Exchanger</h2>
	
	<div style="margin:10px 0"></div>
	
	<div class="fitem"><label class="labelone">Company:</label>
    <input id="datatype" class="easyui-combobox" name="datatype" style="width:280px;height:28px;" required="true" editable="false"
            	data-options="
                    url:'<%=request.getContextPath()%>/exchanger/codelist',
                    method:'get',
                    valueField:'value',
                    textField:'text',
                    prompt:'Loading...',
                    panelHeight:'auto'">
 	</div>

	<div class="fitem"><label class="labelone">Mode:</label>
		<select class="easyui-combobox" id="mode" style="width:280px;">
	        <option value="1">Email</option>
	        <option value="2">SMS</option>
	        <option value="3" selected>Push Notification</option>
	    </select>
 	</div>

 	<div class="fitem"><label class="labelone">Message:</label>
		<input class="easyui-textbox" id="message" data-options="multiline:true, prompt:'Please input your message here...'" required="true" style="width:280px;height:100px">
 	</div>
 	
	<div id="p" class="easyui-progressbar" style="width:360px;text-align:center;display:none;"></div>

    <div style="margin:10px 0"></div>
    <div>
       <a href="#" id="startbtn" class="easyui-linkbutton" iconCls="icon-ok" style="width:360px;height:28px">Start Process</a>
    </div>
    <div style="margin:10px 0"></div>

    
</center></body>
</html>