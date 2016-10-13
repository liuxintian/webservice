<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Meeting Management</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css"/>
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
		$('#dgMeeting').datagrid({
    		height:$(window).innerHeight()-100
		});
	}); 
	 
	$(function(){
    	$('#dgMeeting').datagrid({
    		height:$(window).innerHeight()-100,
            view: detailview,
            detailFormatter:function(index,row){
                return '<div class="ddvIndex"></div>';
            },
            onExpandRow: function(index,row){
                var ddv = $(this).datagrid('getRowDetail',index).find('div.ddvIndex');
                ddv.panel({
                    border:true,
                    cache:true,
                    method:'GET',
                    href:'<%=request.getContextPath()%>/meetings/finddetail?meetingID='+row.meetingID,
                    onLoad:function(){
                        $('#dgMeeting').datagrid('selectRow',index);
                        $('#dgMeeting').datagrid('fixDetailRowHeight',index);
                    }
                });
                $('#dgMeeting').datagrid('fixDetailRowHeight',index);
            }
        });
    });
});

var url;
var type=1;
var data;
function newMeeting(){
	reloadList();
    $('#dlgMeeting').dialog('open').dialog('center').dialog('setTitle','New Meeting');
    $('#pwddiv').hide();
    $('#fmMeeting').form('clear');

    url = '<%=request.getContextPath()%>/meetings/new'; 
    type=1;
}
function editMeeting(){
	reloadList();
    var row = $('#dgMeeting').datagrid('getSelected');
    if (row){
        $('#dlgMeeting').dialog('open').dialog('center').dialog('setTitle','Edit Meeting');
        $('#fmMeeting').form('load',row); 
        if(row.hasPassword) {
        	$('#hasPassword').prop("checked",true);
        	$('#pwddiv').show();
        }else{
        	$('#hasPassword').prop("checked",false);
        	$('#pwddiv').hide();
        }

        url = '<%=request.getContextPath()%>/meetings/edit';
    }
    type=2;
}
function saveMeeting(){
	if(!$('#fmMeeting').form('validate')){
		return;
	}
	// alert($('#hasPassword').is(":checked")); 
	var detaillist = $('#meetingDetails').combobox('getValues')+"";
	//var meetingdate = $('#meetingDate').datebox('getValue')+"";
	//alert(meetingdate); return;
	if(type==1){
	 	$.ajax({
			async:true,
			type:"POST",
			url: url, 			
			data: {companyID: $('#companyID').combobox('getValue'), hasPassword: $('#hasPassword').is(":checked"),password: $('#password').val(), meetingName: $('#meetingName').val(), meetingDate: $('#meetingDate').datebox('getValue'), meetingTime: $('#meetingTime').val(), meetingLocation: $('#meetingLocation').val()
				, meetingType: $('#meetingType').val(), meetingBeforeMsg: $('#meetingBeforeMsg').val(), meetingAfterMsg: $('#meetingAfterMsg').val()
				, meetingContacts: $('#meetingContacts').combobox('getValue'), meetingDetails: detaillist},
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
	            $('#dlgMeeting').dialog('close');        // close the dialog
	            $('#dgMeeting').datagrid('reload');    // reload the user data
			}
		});
	}else{
	 	$.ajax({
			async:true,
			type:"POST",
			url: url, 
			data: {companyID: $('#companyID').combobox('getValue'), hasPassword: $('#hasPassword').is(":checked"),password: $('#password').val(), meetingName: $('#meetingName').val(), meetingID: $('#meetingID').val(), meetingDate: $('#meetingDate').datebox('getValue'), meetingTime: $('#meetingTime').val(), meetingLocation: $('#meetingLocation').val()
				, meetingType: $('#meetingType').val(), meetingBeforeMsg: $('#meetingBeforeMsg').val(), meetingAfterMsg: $('#meetingAfterMsg').val()
				, meetingContacts: $('#meetingContacts').combobox('getValue'), meetingDetails: detaillist},
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
	            $('#dlgMeeting').dialog('close');        // close the dialog
	            $('#dgMeeting').datagrid('reload');    // reload the user data
			}
		});
	}
 	
}
function destroyMeeting(){
    var row = $('#dgMeeting').datagrid('getSelected');
    if (row){
        $.messager.confirm('Confirm','Are you sure you want to delete this record?',function(r){
            if (r){
                $.post('<%=request.getContextPath()%>/meetings/del?id='+row.meetingID,function(data){
                    if (data){
                        $('#dgMeeting').datagrid('reload');    // reload the user data
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

$(function(){
    $('#meetingDate').datebox().datebox('calendar').calendar({
        validator: function(date){
            var now = new Date();
            var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
            var d2 = new Date(now.getFullYear(), now.getMonth(), now.getDate()+100);
            return d1<=date && date<=d2;
        }
    });
    
    $('#hasPassword').change(function() {
        if($(this).is(":checked")) {
        	$('#pwddiv').show();
        }else{
        	$('#pwddiv').hide();
        }
    });
});
function reloadList(){
	$('#companyID').combobox('reload');
	$('#meetingContacts').combobox('reload');
	$('#meetingDetails').combobox('reload');
}
</script>
</head>
<body><center>
	<h2>Meeting List</h2>
	<table id="dgMeeting" title="Meeting Management" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/meetings/list" idFiled="meetingID" 
			toolbar="#toolbarMeeting" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead> 
			<tr>
			    <th field="companyID" width="100">Company ID</th>
			    <th field="meetingName" width="100">Name</th>
			    <th field="meetingDate" width="80">Date</th>
				<th field="meetingTime" width="80">Time</th>
				<th field="meetingType" width="80">Type</th>
			    <th field="hasPassword" width="80">Has Password</th>
				<th field="meetingBeforeMsg" width="150">Before Message</th>
				<th field="meetingAfterMsg" width="150">After Message</th>
				<th field="meetingLocation" width="150">Location</th>
			</tr>
		</thead>
	</table>
    <div id="toolbarMeeting">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newMeeting()">New</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editMeeting()">Edit</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyMeeting()">Remove</a>
    </div>
	
 	<div id="dlgMeeting" class="easyui-dialog" style="width:550px;height:560px;padding:10px 20px"  closed="true" buttons="#dlg-buttons"> 
        <div class="ftitle">Meeting Information</div>
        <form id="fmMeeting" method="post" novalidate><input type="hidden" name="meetingID" id="meetingID"/>  
            <div class="fitem">
                <label>Company</label>
			    <input class="easyui-combobox" name="companyID" id="companyID" style="width:280px;height:28px;" required="true" editable="false"
			            	data-options="
			                    url:'<%=request.getContextPath()%>/meetings/select', 
			                    method:'get',
			                    valueField:'companyID',
			                    textField:'tickerCode',
			                    prompt:'Please select...',
			                    panelHeight:'auto'">
            </div>
            <div class="fitem">
                <label>Meeting Name</label>
                <input class="easyui-textbox" name="meetingName" id="meetingName" required="true" data-options="multiline:true,validType:'length[3,100]'">
            </div>
            <div class="fitem">
                <label>Date</label>
                <input name="meetingDate" class="easyui-datebox" id="meetingDate" required label="Customized Format:" labelPosition="top" data-options="formatter:myformatter,parser:myparser">
            </div>
            
            <script type="text/javascript">
	        function myformatter(date){
	            var y = date.getFullYear();
	            var m = date.getMonth()+1;
	            var d = date.getDate();
	            return (d<10?('0'+d):d)+'/'+(m<10?('0'+m):m)+'/'+y;
	        }
	        function myparser(s){
	            if (!s) return new Date();
	            var ss = (s.split('/'));
	            var y = parseInt(ss[0],10);
	            var m = parseInt(ss[1],10);
	            var d = parseInt(ss[2],10);
	            if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
	                return new Date(d,m-1,y);
	            } else {
	                return new Date();
	            }
	        }
	    	</script>
            
            <div class="fitem">
                <label>Time</label>
                <input class="easyui-timespinner" name="meetingTime" id="meetingTime" required value="09:30">
            </div>
            <div class="fitem">
                <label>Location</label>
                <input name="meetingLocation" id="meetingLocation" class="easyui-textbox" style="width:280px;height:60px;" required="true" data-options="multiline:true,validType:'length[3,200]'">
            </div>
            <div class="fitem">
                <label>Type</label>
                <input name="meetingType" id="meetingType" class="easyui-textbox" required="true" data-options="multiline:true,validType:'length[3,100]'">
            </div>
            <div class="fitem">
                <label>Before Message</label>
                <input name="meetingBeforeMsg" id="meetingBeforeMsg" class="easyui-textbox" required="true" data-options="multiline:true,validType:'length[3,200]'">
            </div>
            <div class="fitem"> 
                <label>After Message</label> 
                <input name="meetingAfterMsg" id="meetingAfterMsg" class="easyui-textbox" required="true" data-options="multiline:true,validType:'length[3,200]'">
            </div>
            <div class="fitem">
                <label>Contact</label>
			    <input class="easyui-combobox" name="meetingContacts" id="meetingContacts" style="width:280px;height:28px;" editable="false"
			            	data-options="
			                    url:'<%=request.getContextPath()%>/contact/select',
			                    method:'get',
			                    valueField:'contactID',
			                    textField:'name',
			                    prompt:'Please select...',
			                    panelHeight:'auto'">
            </div>
            <div class="fitem">
                <label>Documents</label>
			    <input class="easyui-combobox" name="meetingDetails" id="meetingDetails" style="width:280px;height:60px;" editable="false"
			            	data-options="
			                    url:'<%=request.getContextPath()%>/document/select',
			                    method:'get',
			                    valueField:'documentID',
			                    textField:'documentName',
			                    prompt:'Please select...',
			                    multiple:true,multiline:true,
			                    panelHeight:'auto',
			                    panelHeight:'auto'">
            </div>
            <div class="fitem"> 
                <label>Has Password</label> 
                <input name="hasPassword" id="hasPassword" type="checkbox">
            </div>
            <div class="fitem" id="pwddiv" style="display:none;"> 
                <label>Password</label> 
                <input name="password" id="password" class="easyui-textbox" type="password" data-options="validType:['length[3,20]'],prompt:'******'">
            </div>
        </form>
    </div>
    <div id="dlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveMeeting()" style="width:90px">Save</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlgMeeting').dialog('close')" style="width:90px">Cancel</a>
    </div>
      
</center>
</body>
</html>