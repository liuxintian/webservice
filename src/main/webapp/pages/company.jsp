<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<title>Company Management</title> 
	<%@ include file="/pages/common/common_meta.jspf"%>
	<%@ include file="/pages/common/common_jsscripts.jspf"%>
		
	<script type="text/javascript">
	$(document).ready(function(){ 
		$(window).resize(function() {
			$('#dgCompany').datagrid({
	    		width:'95%'
			});  
		}); 
		
		$(function(){
	    	$('#dgCompany').datagrid({
	            view: detailview,
	            detailFormatter:function(index,row){
	                return '<div style="padding:2px"><table class="ddv"></table></div>';
	            },
	            onExpandRow: function(index,row){
	                var ddv = $(this).datagrid('getRowDetail',index).find('table.ddv');
	                ddv.datagrid({
	                	view: detailview,
	                    detailFormatter:function(indexd,rowd){
	                        return '<div class="ddvIndex"></div>';
	                    },
	                    onExpandRow: function(indexd,rowd){
	                        var ddvd = $(this).datagrid('getRowDetail',indexd).find('div.ddvIndex');
	                        ddvd.panel({
	                            border:true,
	                            cache:false, 
	                            method:'GET',
	                            href:'<%=request.getContextPath()%>/meetings/finddetail?meetingID='+rowd.meetingID,
	                            onLoad:function(){
	                             	$('#dgCompany').datagrid('fixDetailRowHeight',index);
	                                ddv.datagrid('fixDetailRowHeight',indexd);
	                            }
	                        }); 
	                    }, 
	                    onCollapseRow: function(indexdd, row){
	                        var ddv = $(this).datagrid('getRowDetail',indexdd).find('div.ddvIndex');
	                        ddv.datagrid('fixDetailRowHeight',indexdd);
	                        
	                    },
	                    url:'<%=request.getContextPath()%>/company/findmeeting?companyID='+row.companyID,
	                    method:'GET',
	                    fitColumns:true,
	                    singleSelect:true,
	                    rownumbers:true,
	                    loadMsg:'',
	                    height:'auto',
	                    columns:[[ 
	                        {field:'meetingName',title:'Meeting Name',width:100},
	                        {field:'meetingDate',title:'Meeting Date',width:100},
	                        {field:'meetingTime',title:'Meeting Time',width:100},
	                        {field:'meetingType',title:'Meeting Type',width:100},
	                        {field:'meetingLocation',title:'Meeting Location',width:150},
	                        {field:'meetingBeforeMsg',title:'Meeting BeforeMsg',width:150},
	                        {field:'meetingAfterMsg',title:'Meeting AfterMsg',width:150},
	                        {field:'hasPassword',title:'Has Password',width:100}
	                    ]],
	                    onResize:function(){
	                        $('#dgCompany').datagrid('fixDetailRowHeight',index);
	                    },
	                    onLoadSuccess:function(){
	                        setTimeout(function(){
	                            $('#dgCompany').datagrid('fixDetailRowHeight',index);
	                        },0);
	                    }
	                });
	                $('#dgCompany').datagrid('fixDetailRowHeight',index);
	            }
	
	        });
	    });
	});
	
	var url;
	var type=1;
	var data;
	function newCompany(){
		reloadList();
	    $('#dlgCompany').dialog('open').dialog('center').dialog('setTitle','New User');
	    $('#fmCompany').form('clear');
	    url = '<%=request.getContextPath()%>/company/new';
	    type=1;
	}
	function editCompayn(){
		reloadList();
	    var row = $('#dgCompany').datagrid('getSelected');
	    if (row){
	        $('#dlgCompany').dialog('open').dialog('center').dialog('setTitle','Edit User');
	        $('#fmCompany').form('load',row);
	        url = '<%=request.getContextPath()%>/company/edit';
	    }
	    type=2;
	}
	function saveCompany(){
		if(!$('#fmCompany').form('validate')){
			return;
		}
		if(type==1){
		 	$.ajax({
				async:true,
				type:"POST",
				url: url, 
				data: {companyName: $('#companyName').combobox('getText'), tickerCode: $('#tickerCode').textbox('getValue'), companyLogo: $('#companyLogo').val()},
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
		            $('#dlgCompany').dialog('close');        // close the dialog
		            $('#dgCompany').datagrid('reload');    // reload the user data
				}
			});
		}else{
		 	$.ajax({
				async:true,
				type:"POST",
				url: url, 
				data: {companyID: $('#companyID').val(), companyName: $('#companyName').combobox('getText'), tickerCode: $('#tickerCode').textbox('getValue'), companyLogo: $('#companyLogo').val()},
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
		            $('#dlgCompany').dialog('close');        // close the dialog
		            $('#dgCompany').datagrid('reload');    // reload the user data
				}
			});
		}
	 	
	}
	function destroyUser(){
	    var row = $('#dgCompany').datagrid('getSelected');
	    if (row){
	        $.messager.confirm('Confirm','Are you sure you want to delete this record?',function(r){
	            if (r){
	                $.post('<%=request.getContextPath()%>/company/del?id='+row.companyID,function(data){
	                    if (data){
	                        $('#dgCompany').datagrid('reload');    // reload the user data
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
	
	function setTickCode(value){
		$('#tickerCode').textbox("setValue",value);
	}
	function reloadList(){
		$('#companyName').combobox('reload');
	}
	</script>
</head>
<body onload="onloadFun();"><center>

	<table id="dgCompany" title="Company Management" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/company/list" idFiled="companyID" 
			toolbar="#toolbarUser" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			    <th field="companyName" width="50">Company Name</th>
				<th field="tickerCode" width="50">Ticker Code</th>
				<th field="companyLogo" width="150">Company Logo</th>
			</tr>
		</thead>
	</table>
    <div id="toolbarUser">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCompany()">New</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editCompayn()">Edit</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove</a>
    </div>
	
 	<div id="dlgCompany" class="easyui-dialog" style="width:500px;height:330px;padding:10px 20px"  closed="true" buttons="#dlg-buttons"> 
        <div class="ftitle">Company Information</div>
        <form id="fmCompany" method="post" novalidate><input type="hidden" name="companyID" id="companyID"/>
            <div class="fitem"> 
                <label>Company Name</label>
			    <input name="companyName" id="companyName" class="easyui-combobox" required="true" editable="true"
			            	data-options="
			                    url:'<%=request.getContextPath()%>/company/codelist',
								onChange:function(rec){
									setTickCode(rec);
								},			                    
			                    method:'get',
			                    valueField:'value',
			                    textField:'text',
			                    prompt:'Loading...',
			                    panelHeight:'auto'">
            </div>
            <div class="fitem">
                <label>Ticker Code</label>
			    <input name="tickerCode" id="tickerCode" class="easyui-combobox" required="true" editable="false" readonly>
            </div>
            <div class="fitem">
                <label>Company Logo</label>
                <input name="companyLogo" id="companyLogo" class="easyui-textbox" style="height:60px;" data-options="multiline:true,validType:'url'">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveCompany()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgCompany').dialog('close')" style="width:90px">Cancel</a>
    </div>
      

</center></body>
</html>