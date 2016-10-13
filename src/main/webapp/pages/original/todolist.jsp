<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Information Management</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/color.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datagrid-detailview.js"></script>
	
<script type="text/javascript">
var autocheck = false;
var timeout = 600;
$(document).ready(function(){
	$(window).resize(function() {
		$('#dgToDo').datagrid({
    		height:$(window).innerHeight()-150
		});
	});
	$(function(){
    	$('#dgToDo').datagrid({
    		height:$(window).innerHeight()-150,
            selectOnCheck: true,
            checkOnSelect: true,
            onCheck:function(rowIndex,rowData){
            	if(!autocheck) editToDo(rowData.id, true);
            },
            onUncheck:function(rowIndex,rowData){
            	editToDo(rowData.id, false);
            },
            onCheckAll:function(rows){
            	if(rows) editToDoAll(rows, true);
            },
            onUncheckAll:function(rows){
            	if(rows) editToDoAll(rows, false);
            },
            onLoadSuccess:function(data){
            	autocheck = true;
                if(data){
                    $.each(data.rows, function(index, item){
                        if(item.status){
                            $('#dgToDo').datagrid('checkRow', index);
                        }
                    });
                }
            	autocheck = false;
            }
        });
    });
});

function editToDo(idstr, status){
	$.ajax({
		async:true,
		type:"POST",
		url: '<%=request.getContextPath()%>/todo/edit', 
		data: {id:idstr, status:status},
		success: function(data) {
			$.messager.show({
                title: 'Message',
                msg: data.result,
                timeout:timeout,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
		}
	});
}

function editToDoAll(rows, status){
	var ids = [];
	for(var i=0; i<rows.length; i++){
	    ids.push(rows[i].id);
	}
	
	$.ajax({
		async:true,
		type:"POST",
		url: '<%=request.getContextPath()%>/todo/editall', 
		data: {id:ids.join(','), status:status},
		success: function(data) {
			$.messager.show({
                title: 'Message',
                msg: data.result,
				showType:'slide',
				timeout:timeout,
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
		}
	});
}

function saveToDo(){
	if(!$('#fmToDo').form('validate')){
		return;
	}
 	$.ajax({
		async:true,
		type:"POST",
		url: '<%=request.getContextPath()%>/todo/new', 
		data: { content :$('#content').val()},
		success: function(data) {
			$.messager.show({
                title: 'Message',
                msg: data.result,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
			$('#fmToDo').form('clear');
            $('#dgToDo').datagrid('reload'); 
		}
	});	
}
var distype = true;
function displayType(){
	if(distype){
		distype = false;
		$('#displaybtn').linkbutton({
		    iconCls: 'icon-add',
		    text:'Show'
		});
		$('#dgToDo').datagrid({
			url:"<%=request.getContextPath()%>/todo/subgrid"
		});
		$('#dgToDo').datagrid('reload'); 
	}else{
		distype = true;
		$('#displaybtn').linkbutton({
		    iconCls: 'icon-remove',
		    text:'Hide'
		});
		$('#dgToDo').datagrid({
			url:"<%=request.getContextPath()%>/todo/list"
		});
		$('#dgToDo').datagrid('reload'); 
	}
}

</script>
</head>
<body><center>
	<h2>Information Management</h2>
	<form id="fmToDo" method="post" novalidate>
    <div>
         <label>Information:</label>
         <input name="content" id="content" class="easyui-textbox" style="width:40%;" data-options="required:true,validType:['length[3,500]'],prompt:''" >
         <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveToDo()" style="width:100px;">Add</a>
   </div>
    </form>
    
	<table id="dgToDo" title="Information LIST" class="easyui-datagrid" style="width:95%;height:600px;"
		url="<%=request.getContextPath()%>/todo/list" idFiled="id" toolbar="#toolbarTodo"
		pagination="true" rownumbers="true" fitColumns="true" singleSelect="false">
		<thead>
			<tr>
			    <th field="status" data-options="checkbox:true"></th>
			    <th field="content" width="200">Information</th>
				<th field="timestamp" width="150">Time Stamp</th>
			</tr>
		</thead>
	</table>
	<div id="toolbarTodo">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="displayType();" id="displaybtn">Hide</a>
	</div>
</center>
</body>
</html>