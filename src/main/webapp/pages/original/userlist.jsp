<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Management</title> 
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
		$('#dgUser').datagrid({
    		height:$(window).innerHeight()-100
		});
	});
	$(function(){
    	$('#dgUser').datagrid({
    		height:$(window).innerHeight()-100,
    		/*            view: detailview,
            detailFormatter:function(index,row){
                return '<div class="ddvIndex"></div>';
            },
             onClickRow:function(){
                var rows = $('#dgUser').datagrid('getSelections');
                var row = rows[rows.length - 1];
                if (row){
                    $('#dlgUser').dialog('open').dialog('center').dialog('setTitle','Question Details From '+row.name);
                    $('#dlgUserNotes').html(row.notes);
                    $('#dlgUserEmail').html(row.email);
                    $('#dlgUserDateTime').html(row.datetime);
                }
            }, */
            selectOnCheck: true,
            checkOnSelect: true,
            onExpandRow: function(index,row){ 
                var ddvIndex = $(this).datagrid('getRowDetail',index).find('div.ddvIndex');
                ddvIndex.panel({
                    border:false,
                    cache:true,
                    href:'<%=request.getContextPath()%>/agm/finddetails?msgid='+row.id,
                    onLoad:function(){
                        $('#dgUser').datagrid('fixDetailRowHeight',index);
                        $('#dgUser').datagrid('selectRow',index);
                        $('#dgUser').datagrid('getRowDetail',index).find('form').form('load',row);
                    }
                });
                $('#dgUser').datagrid('fixDetailRowHeight',index);
            }
        });
    });
});

var url;
var type=1;
var data;
function newUser(){
    $('#dlgUser').dialog('open').dialog('center').dialog('setTitle','New User');
    $('#fmUser').form('clear');
    url = '<%=request.getContextPath()%>/user/newuser';
    type=1;
}
function editUser(){
    var row = $('#dgUser').datagrid('getSelected');
    if (row){
        $('#dlgUser').dialog('open').dialog('center').dialog('setTitle','Edit User');
        $('#fmUser').form('load',row);
        url = '<%=request.getContextPath()%>/user/edituser';
    }
    type=2;
}
function saveUser(){
	if(!$('#fmUser').form('validate')){
		return;
	}
	if(type==1){
	 	$.ajax({
			async:true,
			type:"POST",
			url: url, 
			data: { login :$('#login').val(), password: $('#password').val(), code: $('#code').combobox('getValue'), description: $('#description').val(), email: $('#email').val(), phone: $('#phone').val()},
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
	            $('#dlgUser').dialog('close');        // close the dialog
	            $('#dgUser').datagrid('reload');    // reload the user data
			}
		});
	}else{
	 	$.ajax({
			async:true,
			type:"POST",
			url: url, 
			data: {id:$('#id').val(), login :$('#login').val(), password: $('#password').val(), code: $('#code').combobox('getValue'), description: $('#description').val(), email: $('#email').val(), phone: $('#phone').val()},
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
	            $('#dlgUser').dialog('close');        // close the dialog
	            $('#dgUser').datagrid('reload');    // reload the user data
			}
		});
	}
 	
 	/*	
    $('#fmUser').form('submit',{
        url: url,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.errorMsg){
                $.messager.show({
                    title: 'Error',
                    msg: result.errorMsg
                });
            } else {
                $('#dlgUser').dialog('close');        // close the dialog
                $('#dgUser').datagrid('reload');    // reload the user data
            }
        }
    });
 	 */	
}
function destroyUser(){
    var row = $('#dgUser').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','Are you sure you want to destroy this user?',function(r){
            if (r){
                $.post('<%=request.getContextPath()%>/user/deleteuser?id='+row.id,function(data){
                    if (data){
                        $('#dgUser').datagrid('reload');    // reload the user data
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
	<h2>User List</h2>
	<table id="dgUser" title="User Management" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/user/query" idFiled="id" 
			toolbar="#toolbarUser" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			    <th field="login" width="30">Email Address</th>
				<th field="code" width="50">User Company</th>
				<th field="phone" width="50">Phone</th>
				<th field="description" width="150">Description</th>
			</tr>
		</thead>
	</table>
    <div id="toolbarUser">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
    </div>
	
 	<div id="dlgUser" class="easyui-dialog" style="width:500px;height:330px;padding:10px 20px"  closed="true" buttons="#dlg-buttons"> 
        <div class="ftitle">User Information</div>
        <form id="fmUser" method="post" novalidate><input type="hidden" name="id" id="id"/>
            <div class="fitem">
                <label>Email Address</label>
                <input name="login" id="login" class="easyui-textbox" data-options="required:true,validType:['email','length[3,50]'],prompt:'smith@omt.com.au'" >
            </div>
            <div class="fitem">
                <label>Password:</label>
                <input name="password" id="password" class="easyui-textbox" type="password" data-options="required:true,validType:['length[3,20]'],prompt:'******'">
            </div>

            <div class="fitem">
                <label>Phone:</label>
                <input name="phone" id="phone" class="easyui-textbox">
            </div>
            <div class="fitem">
                <label>Description</label>
                <input name="description" id="description" class="easyui-textbox" >
            </div>
            <div class="fitem">
                <label>User Company</label>
                <input id="code" class="easyui-combobox" name="code" required="true" editable="true"
            	data-options="
                    url:'<%=request.getContextPath()%>/user/rolesuser',
                    method:'get',
                    valueField:'code',
                    textField:'code',
                    prompt:'Loading...',
                    panelHeight:'300'">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgUser').dialog('close')" style="width:90px">Cancel</a>
    </div>
      
</center>
</body>
</html>