<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>OMT-Web Service-Management</title> 
  <meta name="robots" content="noindex,nofollow">
  <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/color.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datagrid-detailview.js"></script>
  <style type="text/css">
   /* over all rules */
   *{ box-sizing:border-box; }
   html,body{ margin:0;padding:0;background:#ddd;color:#333;font-family:'Open Sans','Trebuchet MS','Arial',sans-serif;font-size:14px; }
   .container{ width:100%; height:100%; float:left;position:relative; background:#fff;padding:10px; }
   a{ text-decoration:none;color:#c33; }
   
   /* header and footer */
   header,footer,article,nav,section{ float:left;padding:10px; }
   header,footer{ width:100%; }
   header,footer{ background-color:#333;color:#fff;text-align:right;height:100px; }
   header{ font-size:1.8em;font-weight:bold; }
   header big{ line-height:100px;vertical-align:middle; }
   .logo{ float:left; line-height:120px; vertical-align:middle; }
   
   footer{ background-color:#999;text-align:center;height:40px; }
   footer a{ text-decoration:underline;color:#fff;font-weight:bold; }
   
   /* navigation on the left hand side */
   nav{ text-align:center;width:23%; height:100%; margin-right:1%;border:1px solid #ccc;margin:5px; }
   nav a{ display:block;width:100%;background-color:#c33;color:#fff;height:30px;margin-bottom:10px;padding:10px;border-radius:3px;line-height:10px;vertical-align:middle; }
   nav a:hover,nav a:active{ background-color:#226; }
   
   /* <article> tag is our 'content area' */
   article{ width:75%; }
   h1{ padding:0;margin:0 0 20px 0; }
   
   /* section */
   article section{ padding:0; }
   
   /* basic page division */
   .halves,.thirds{ width:49%;float:left;margin-right:2%; }
   .thirds{ width:32.66666%;margin-right:1%; }
   
   .last{ margin-right:0; }
  </style>

<script type="text/javascript">
$(document).ready(function(){
	$(window).resize(function() {
		$('#dgAgm').datagrid({
    		height:$(window).innerHeight()-100
		});
	});
	$(function(){
    	$('#dgAgm').datagrid({
    		height:$(window).innerHeight()-100,
            view: detailview,
            detailFormatter:function(index,row){
                return '<div class="ddvIndex"></div>';
            },
            onClickRow:function(){
                var rows = $('#dgAgm').datagrid('getSelections');
                var row = rows[rows.length - 1];
                if (row){
                    $('#dlgAgm').dialog('open').dialog('center').dialog('setTitle','Question Details From '+row.name);
                    $('#dlgAgmNotes').html(row.notes);
                    $('#dlgAgmEmail').html(row.email);
                    $('#dlgAgmDateTime').html(row.datetime);
					if(row.vac == null || row.vac == "undefined" || row.vac == ""){
						$('#vacAgm').hide();
					}else{
						$('#vacAgm').show();
					}
                }
            },
            selectOnCheck: true,
            checkOnSelect: true,
            onExpandRow: function(index,row){ 
                var ddvIndex = $(this).datagrid('getRowDetail',index).find('div.ddvIndex');
                ddvIndex.panel({
                    border:false,
                    cache:true,
                    href:'<%=request.getContextPath()%>/agm/finddetails?msgid='+row.id,
                    onLoad:function(){
                        $('#dgAgm').datagrid('fixDetailRowHeight',index);
                        $('#dgAgm').datagrid('selectRow',index);
                        $('#dgAgm').datagrid('getRowDetail',index).find('form').form('load',row);
                    }
                });
                $('#dgAgm').datagrid('fixDetailRowHeight',index);
            }
        });
    });
    window.setInterval(reload, 30*1000);
	$('#company').combobox('setValue', '<c:out value="${sessionScope.code}"/>');
});
	
	function reload() {
		$('#dgAgm').datagrid('reload'); 
	}
	
	// 
	function approveUser(){
		var ids = [];
		var rows = $('#dgAgm').datagrid('getSelections');
		for(var i=0; i<rows.length; i++){
		    ids.push(rows[i].id);
		}
		
		if (rows.length > 0){
			$.messager.confirm('Confirm','Are you sure to approve the selected messages?',function(r){
				if (r){
					$.post('<%=request.getContextPath()%>/agm/postapprove?msgid='+ids.join(','), function(data){
							$('#dgAgm').datagrid('reload');	// reload the user data
							$.messager.show({	// show result message
								title: 'Result',
								msg: data.result,
								showType:'slide',
								style:{
									right:'',
									top:document.body.scrollTop+document.documentElement.scrollTop,
									bottom:''
								}
						});
					});
					$('#dlgAgm').dialog('close');
				}
			});
		}
		
	}
	
	// 
	function deleteMessages(type){
		var ids = [];
		var rows = $('#dgAgm').datagrid('getSelections');
		if(type == 0){
			for(var i=0; i<rows.length; i++){
			    ids.push(rows[i].id);
			}
		}else{
			ids.push(rows[rows.length - 1].id);
		}
		
		if (rows.length > 0){
			$.messager.confirm('Confirm','Are you sure to remove the selected messages?',function(r){
				if (r){
					$.post('<%=request.getContextPath()%>/agm/deleteusermsg?msgid='+ids.join(','), function(data){
							$('#dgAgm').datagrid('reload');	// reload the user data
							$.messager.show({	// show result message
								title: 'Result',
								msg: data.result,
								showType:'slide',
								style:{
									right:'',
									top:document.body.scrollTop+document.documentElement.scrollTop,
									bottom:''
								}
						});
					});
					$('#dlgAgm').dialog('close');
				}
			});
		}
	}
	
function setSessionCode(code){
	$.ajax({
		async:true,
		type:"POST",
		url: "<%=request.getContextPath()%>/agm/setsessioncode", 
		data: {code :code},
		success: function(result) {
			batchidstr = result;
			$('#dgAgm').datagrid('reload'); 
			$("#currentlink").html("Switched to ["+code+"]"); 
		}
	});
}
function setEmptyCode(code){
	if(code == ""){
		$.ajax({
			async:true,
			type:"POST",
			url: "<%=request.getContextPath()%>/agm/setsessioncode", 
			data: {code :code},
			success: function(result) {
				batchidstr = result;
				$('#dgAgm').datagrid('reload'); 
				$("#currentlink").html("Switched to ["+code+"]"); 
			}
		});		
	}
}

function logout(){
	$.messager.confirm('Confirm','Are you sure to logout?',function(r){
		if(r){
			location.href = "<%=request.getContextPath()%>/logout";
		}
	});
}
</script>
</head> 
<body>
  <div class="container">
   <header>
    <img class="logo" src="https://omnimarkettide.com/wp-content/uploads/2016/08/omtlogo_lightnew.png"/> <big>OMT-Web Service-Management</big>
   </header>
   
   <nav>
    <a href="#section1">User Management</a>
    <a href="#section2">Watch List</a>
    <a href="#section3">Push Notification</a>
    <a href="#section4">Company List</a>
    <a href="#section5">Media Management</a>
    <a href="#section6">Key Dates</a>
    <a href="#section7">Analytics Data</a>
   </nav>
   
   <article>
    <h1>A Better Approch Than Tables or Frames</h1>
	<table id="dgAgm" title="Questions Management" class="easyui-datagrid" style="width:100%;height:600px;"
		url="<%=request.getContextPath()%>/agm/querylist" idFiled="id" 
		toolbar="#toolbarAgm" pagination="true" rownumbers="true" fitColumns="true" singleSelect="false">
	<thead>
		<tr>
		    <th data-options="field:'ck',checkbox:true"></th>
		    <th field="name" width="30">Name</th>
			<th field="email" width="60">Email</th>
			<th field="datetime" width="50">Date Time</th>
			<th field="vac" width="50">VAC</th>
			<th field="notes" width="150">Message</th>
		</tr>
	</thead>
	</table>
	<div id="toolbarAgm">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMessages(0)">Remove Selected Message</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" plain="true" onclick="approveUser()">Approve Selected Message</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="logout()">Logout</a>
	</div>
   </article>
   
   <footer>
      Copyright 2016 @ <a href="http://www.omnimarkettide.com" title="The market leader in digital engagement">Omni Market Tide</a>
   </footer>
  </div>  
 </body>   
</html>