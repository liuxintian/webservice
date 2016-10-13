<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>System Configuration</title> 
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
		$('#dgSyscfg').datagrid({
    		height:$(window).innerHeight()-100
		});
	});
    $(function(){
    	$('#dgSyscfg').datagrid({
    		height:$(window).innerHeight()-100,
    		pageList:[50,20,100,200],
            onClickRow:function(){
                var row = $('#dgSyscfg').datagrid('getSelected');
                if (row){
                    $('#dlgsyscfg').dialog('open').dialog('center').dialog('setTitle','Edit: '+ row.cfgAlias);
                    $('#fmCfg').form('load',row);
                }
            }
        });
    });
    window.setTimeout(reload, 100);
});

function reload() {
	$(window).resize(function() {
		$('#dgSyscfg').datagrid({
    		height:$(window).innerHeight()-100
		});
	});
	$('#dgSyscfg').datagrid('reload'); 
}

function saveUser(){
	 
	$.messager.confirm('Confirm','Are you sure to submit?',function(r){
		if (r){
		    $('#fmCfg').form('submit',{
		        url: "<%=request.getContextPath()%>/cfg/edit", 
		        onSubmit: function(){
		            return $(this).form('validate');
		        },
		        success: function(result){
		    	  	$('#dlgsyscfg').dialog('close');        // close the dialog
		    	    $('#dgSyscfg').datagrid('reload');    // reload the user data
		        }
		    });
		}
	});
}
</script>
</head>
<body><center>
	<h2>System Configuration</h2>
	<table id="dgSyscfg" title="System Configuration [Carefully to edit the values]" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/cfg/list" idFiled="id"
			toolbar="#toolbarSyscfg" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			    <th field="cfgAlias" width="60">Item</th>
				<th field="cfgValue" width="160">Applied Value</th>
				<th field="cfgDefaultValue" width="160">Default Value</th>
			</tr>
		</thead>
	</table>

	
    <div id="dlgsyscfg" class="easyui-dialog" style="width:500px;height:180px;" closed="true" buttons="#dlgsyscfg-buttons" data-options="resizable:true,modal:true">
        <form id="fmCfg" method="post" >
        	<input type="hidden" id="cfgName" name="cfgName"/>
        	<input type="hidden" id="cfgAlias" name="cfgAlias"/>

            <div class="fitem">
            	<label>Value:</label>
            	<input class="easyui-textbox" name="cfgValue" data-options="multiline:true" required="true" style="width:70%;height:60px">
            </div>
        </form>
    </div>
    <div id="dlgsyscfg-buttons" style="text-align: center;">
     	<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveUser()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgsyscfg').dialog('close')" style="width:90px">Cancel</a>
    </div>        
</center>
</body>
</html>