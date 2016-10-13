<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<title>System Configuration</title> 
	<%@ include file="/pages/common/common_meta.jspf"%>
	<%@ include file="/pages/common/common_jsscripts.jspf"%>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$(window).resize(function() {
			$('#dgSyscfg').datagrid({
				height:$(window).innerHeight()-global_height_gap
			});
		});
	    $(function(){
	    	$('#dgSyscfg').datagrid({
	    		height:$(window).innerHeight()-global_height_gap,
	            pageList:global_pageList,
	            pageSize:global_pageSize,
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
<body>
  <div class="container">
   <%@ include file="/pages/common/common_header.jspf"%>
   
   <nav>
    <a href="<%=request.getContextPath()%>/agm/index">AGM Question List</a>
    <a href="<%=request.getContextPath()%>/agm/approved">AGM Approved List</a>
    <a href="<%=request.getContextPath()%>/meetings/all">Meeting management</a>
    <a href="<%=request.getContextPath()%>/stat/index">Data Access History</a>
    <a href="<%=request.getContextPath()%>/cfg/index" class="visited">System Configuration</a>
    <a href="<%=request.getContextPath()%>/logout">Logout</a>
   </nav>
   
   <article id="content">

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
    
   </article>
   
   <%@ include file="/pages/common/common_footer.jspf"%>
  </div>  
 </body>   
</html>