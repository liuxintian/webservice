<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Document Management</title> 
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
		$('#dgDocument').datagrid({
    		height:$(window).innerHeight()-100
		});
	}); 
	
	$(function(){
    	$('#dgDocument').datagrid({
    		height:$(window).innerHeight()-100
        });
    });
});

var url;
var type=1;
var data;
function newDocument(){
    $('#dlgDocument').dialog('open').dialog('center').dialog('setTitle','New ');
    $('#fmDocument').form('clear');
    url = '<%=request.getContextPath()%>/document/new';
    type=1;
}
function editDocument(){
    var row = $('#dgDocument').datagrid('getSelected');
    if (row){
        $('#dlgDocument').dialog('open').dialog('center').dialog('setTitle','Edit ');
        $('#fmDocument').form('load',row);
        url = '<%=request.getContextPath()%>/document/edit';
    }
    type=2;
}
function saveDocument(){
	if(!$('#fmDocument').form('validate')){
		return;
	}
	if(type==1){
	 	$.ajax({
			async:true,
			type:"POST",
			url: url, 
			data: {documentName: $('#documentName').val(), documentType: $('#documentType').val(), documentLink: $('#documentLink').val(), documentLength: $('#documentLength').val(), documentSize: $('#documentSize').val()},
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
	            $('#dlgDocument').dialog('close');        // close the dialog
	            $('#dgDocument').datagrid('reload');    // reload the user data
			}
		});
	}else{
	 	$.ajax({
			async:true,
			type:"POST",
			url: url, 
			data: {documentID: $('#documentID').val(), documentName: $('#documentName').val(), documentType: $('#documentType').val(), documentLink: $('#documentLink').val(), documentLength: $('#documentLength').val(), documentSize: $('#documentSize').val()},
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
	            $('#dlgDocument').dialog('close');        // close the dialog
	            $('#dgDocument').datagrid('reload');    // reload the user data
			}
		});
	}
 	
}
function deleteDocument(){
    var row = $('#dgDocument').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','Are you sure you want to delete this record?',function(r){
            if (r){
                $.post('<%=request.getContextPath()%>/document/del?id='+row.documentID,function(data){
                    if (data){
                        $('#dgDocument').datagrid('reload');    // reload the user data
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
	<h2>Document List</h2>
	<table id="dgDocument" title="Document Management" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/document/list" idFiled="documentID" 
			toolbar="#toolbarDocument" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			    <th field="documentName" width="100">Name</th>
				<th field="documentType" width="50">Type</th>
				<th field="documentLength" width="50">Length</th>
				<th field="documentSize" width="50">Size</th>
				<th field="documentLink" width="200">Link</th> 
			</tr>   
		</thead>
	</table>
    <div id="toolbarDocument">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newDocument()">New</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editDocument()">Edit</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteDocument()">Remove</a>
    </div>
	
 	<div id="dlgDocument" class="easyui-dialog" style="width:500px;height:330px;padding:10px 20px"  closed="true" buttons="#dlg-buttons"> 
        <div class="ftitle">Document Information</div>
        <form id="fmDocument" method="post" novalidate><input type="hidden" name="documentID" id="documentID"/>
            <div class="fitem">
                <label>Name</label>
                <input name="documentName" id="documentName" class="easyui-textbox" data-options="required:true,multiline:true,validType:'length[1,100]'">
            </div>
            <div class="fitem">
                <label>Type</label>
                <input name="documentType" id="documentType" class="easyui-textbox" data-options="required:true,validType:'length[1,100]'">
            </div>
            <div class="fitem">
                <label>Link</label>
                <input name="documentLink" id="documentLink" class="easyui-textbox" style="height:60px" data-options="validType:'url',multiline:true,required:true">
            </div>
            <div class="fitem">
                <label>Length</label>
                <input name="documentLength" id="documentLength" class="easyui-textbox" data-options="validType:'length[1,100]'">
            </div>
            <div class="fitem">
                <label>Size</label>
                <input name="documentSize" id="documentSize" class="easyui-textbox" data-options="validType:'length[1,100]'">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveDocument()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgDocument').dialog('close')" style="width:90px">Cancel</a>
    </div>
      
</center>
</body>
</html>