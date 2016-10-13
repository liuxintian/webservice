<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Agm Question List</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/color.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datagrid-detailview.js"></script>
</head>
	
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
<body>
	<center><h2>Agm Question List</h2></center>
	<c:if test="${sessionScope.role == 'ROLE_ADMIN'}">
	<div style="padding-left:5px;padding-bottom:5px;cursor:hand;">
    <label>Select Company</label>
    <input id="company" class="easyui-combobox" name="company" required="false" editable="true"  style="cursor:hand;width:260px;"
    data-options="
		onSelect:function(rec){ 
			setSessionCode(rec.code);
		},
		onChange:function(rec){
			setEmptyCode(rec);
		},
		url:'<%=request.getContextPath()%>/user/rolesuser',
		method:'get',
		required:false,
        valueField:'code',
        textField:'code',
        prompt:'Loading...', 
        panelHeight:'300' ">
	<label id="currentlink"> </label>
	</div>
	</c:if>
	
	<center>
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
	
    <div id="dlgAgm" class="easyui-dialog" style="width:500px;height:280px;"
            closed="true" buttons="#dlgAgm-buttons" data-options="resizable:true,modal:true">
        
        <form id="fmIndex" method="post" novalidate>
         	<div class="ftitle" id="dlgAgmNotes" style="padding-left:20px;">
        	</div>
 
            <div class="fitem">
                <label class="labelone">Email:</label><label class="labeltwo" id="dlgAgmEmail"></label>
            </div>
            <div class="fitem">
                <label class="labelone">DateTime:</label><label class="labeltwo" id="dlgAgmDateTime"></label>
            </div>
        </form>
    </div>
    <div id="dlgAgm-buttons" style="text-align: center;">
        <label id="vacAgm" style="display:none;"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-vac" style="width:90px;" disabled>VAC</a></label>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="javascript:approveUser();" style="width:90px">Approve</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" onclick="javascript:deleteMessages(1);" style="width:90px">Delete</a>
    </div>        
</center>
</body>
</html>