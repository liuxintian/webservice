<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Contact Management</title> 
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
		$('#dgContact').datagrid({
    		height:$(window).innerHeight()-100
		});
	}); 
	
	$(function(){
    	$('#dgContact').datagrid({
    		height:$(window).innerHeight()-100
        });
    });
});

var url;
var type=1;
var data;
function newContact(){
    $('#dlgContact').dialog('open').dialog('center').dialog('setTitle','New ');
    $('#fmContact').form('clear');
    url = '<%=request.getContextPath()%>/contact/new';
    type=1;
}
function editContact(){
    var row = $('#dgContact').datagrid('getSelected');
    if (row){
        $('#dlgContact').dialog('open').dialog('center').dialog('setTitle','Edit ');
        $('#fmContact').form('load',row);
        url = '<%=request.getContextPath()%>/contact/edit';
    }
    type=2;
}
function saveContact(){
	if(!$('#fmContact').form('validate')){
		return;
	}
	if(type==1){
	 	$.ajax({
			async:true,
			type:"POST",
			url: url, 
			data: {name: $('#name').val(), email: $('#email').val(), phone: $('#phone').val(), weburl: $('#weburl').val()},
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
	            $('#dlgContact').dialog('close');        // close the dialog
	            $('#dgContact').datagrid('reload');    // reload the user data
			}
		});
	}else{
	 	$.ajax({
			async:true,
			type:"POST",
			url: url, 
			data: {contactID: $('#contactID').val(), name: $('#name').val(), email: $('#email').val(), phone: $('#phone').val(), weburl: $('#weburl').val()},
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
	            $('#dlgContact').dialog('close');        // close the dialog
	            $('#dgContact').datagrid('reload');    // reload the user data
			}
		});
	}
 	
}
function deleteContact(){
    var row = $('#dgContact').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','Are you sure you want to delete this record?',function(r){
            if (r){
                $.post('<%=request.getContextPath()%>/contact/del?id='+row.contactID,function(data){
                    if (data){
                        $('#dgContact').datagrid('reload');    // reload the user data
                        $.messager.show({    // show error message
                            title: 'Message',
                            msg: data.result,
                            showType:'slide',
            				style:{
            					right:'',
            					top:document.body.scrollTop+document.documentElement.scrollTop,
            					bottom:''
            				}
                        });
                    }
                },'json');
            }
        });
    }
}

</script>
</head>
<body><center>
	<h2>Contact List</h2>
	<table id="dgContact" title="Contact Management" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/contact/list" idFiled="contactID" 
			toolbar="#toolbarContact" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			    <th field="name" width="50">Name</th>
				<th field="email" width="100">Email</th>
				<th field="phone" width="100">Phone</th>
				<th field="weburl" width="200">Web URL</th>
			</tr>
		</thead>
	</table>
    <div id="toolbarContact">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newContact()">New</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editContact()">Edit</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteContact()">Remove</a>
    </div>
	
 	<div id="dlgContact" class="easyui-dialog" style="width:500px;height:330px;padding:10px 20px"  closed="true" buttons="#dlg-buttons"> 
        <div class="ftitle">Contact Information</div>
        <form id="fmContact" method="post" novalidate><input type="hidden" name="contactID" id="contactID"/>
            <div class="fitem">
                <label>Name</label>
                <input name="name" id="name" class="easyui-textbox" required data-options="required:true,validType:'length[1,100]'">
            </div>
            <div class="fitem">
                <label>Email</label>
                <input name="email" id="email" class="easyui-textbox" required data-options="required:true,validType:'email'">
            </div>
            <div class="fitem">
                <label>Phone</label>
                <input name="phone" id="phone" class="easyui-textbox" >
            </div>
            <div class="fitem">
                <label>WEB URL</label>
                <input name="weburl" id="weburl" class="easyui-textbox" style="height:60px" data-options="multiline:true,validType:'url'">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveContact()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgContact').dialog('close')" style="width:90px">Cancel</a>
    </div>
      
</center>
</body>
</html>