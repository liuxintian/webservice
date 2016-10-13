<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<title>Agm Question List</title> 
	<%@ include file="/pages/common/common_meta.jspf"%>
	<%@ include file="/pages/common/common_jsscripts.jspf"%>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$(window).resize(function() {
			$('#dgApproved').datagrid({
	    		height:$(window).innerHeight()-100
			});
		});
	    $(function(){
	    	$('#dgApproved').datagrid({
	    		height:$(window).innerHeight()-100,
	            view: detailview,
	            detailFormatter:function(index,row){
	                return '<div class="ddv"></div>';
	            },
	            onClickRow:function(){
	                var rows = $('#dgApproved').datagrid('getSelections');
	                var row = rows[rows.length - 1];
	                if (row){
	                    $('#dlgApproved').dialog('open').dialog('center').dialog('setTitle','Question Details From '+row.name);
	                    $('#dlgApprovedNotes').html(row.notes);
	                    $('#dlgApprovedEmail').html(row.email);
	                    $('#dlgApprovedDateTime').html(row.datetime);
						if(row.vac == null || row.vac == "undefined" || row.vac == ""){
							$('#vacApproved').hide();
						}else{
							$('#vacApproved').show();
						}
	                }
	            },
	            selectOnCheck: true,
	            checkOnSelect: true,
	            onExpandRow: function(index,row){ 
	                var ddv = $(this).datagrid('getRowDetail',index).find('div.ddv');
	                ddv.panel({
	                    border:false,
	                    cache:true,
	                    href:'<%=request.getContextPath()%>/agm/finddetails?msgid='+row.id,
	                    onLoad:function(){
	                        $('#dgApproved').datagrid('fixDetailRowHeight',index);
	                        $('#dgApproved').datagrid('selectRow',index);
	                        $('#dgApproved').datagrid('getRowDetail',index).find('form').form('load',row);
	                    }
	                });
	                $('#dgApproved').datagrid('fixDetailRowHeight',index);
	            }
	        });
	    });
	    window.setInterval(reload, 30*1000);
		$('#acompany').combobox('setValue', '<c:out value="${sessionScope.code}"/>');
	});

	function reload() {
		$('#dgApproved').datagrid('reload'); 
	}
	
	// 
	function deleteMessages(type){
		var ids = [];
		var rows = $('#dgApproved').datagrid('getSelections');
		if(type == 0){
			for(var i=0; i<rows.length; i++){
			    ids.push(rows[i].id);
			}
		}else{
			ids.push(rows[rows.length - 1].id);
		}
		if (rows){
			$.messager.confirm('Confirm','Are you sure to remove the selected messages?',function(r){
				if (r){
					$.post('<%=request.getContextPath()%>/agm/deleteusermsg?msgid='+ids.join(','), function(data){
							$('#dgApproved').datagrid('reload');	// reload the user data
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
					$('#dlgApproved').dialog('close');
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
				$('#dgApproved').datagrid('reload'); 
				$("#acurrentlink").html("Switched to ["+code+"]"); 
			}
		});
	}
	function logout(){
		$.messager.confirm('Confirm','Are you sure to logout?',function(r){
			if(r){
				location.href = "<%=request.getContextPath()%>/logout";
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
					$('#dgApproved').datagrid('reload'); 
					$("#acurrentlink").html("Switched to ["+code+"]"); 
				}
			});
		}
	}

	</script>
</head>
<body>
	<center><h2>Agm Approved Question List</h2></center>
	<c:if test="${sessionScope.role == 'ROLE_ADMIN'}">
	<div style="padding-left:5px;padding-bottom:5px;cursor:hand;">
    <label>Select Company</label>
    <input id="acompany" class="easyui-combobox" name="acompany" required="false" editable="true"  style="cursor:hand;width:260px;"
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
	<label id="acurrentlink"> </label>
	</div>
	</c:if>
	
	<center>
	<table id="dgApproved" title="Approved Questions Management" class="easyui-datagrid" style="width:100%;height:600px;"
			url="<%=request.getContextPath()%>/agm/queryapproved" idFiled="id"
			toolbar="#toolbarApproved" pagination="true" rownumbers="true" fitColumns="true" singleSelect="false">
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
	<div id="toolbarApproved"> 
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMessages(0)">Remove Selected Message</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" plain="true" onclick="logout()">Logout</a>
	</div>
	
    <div id="dlgApproved" class="easyui-dialog" style="width:500px;height:280px;"
            closed="true" buttons="#dlgApproved-buttons" data-options="resizable:true,modal:true">
        
        <form id="fmApproved" method="post" novalidate>
         	<div class="ftitle" id="dlgApprovedNotes" style="padding-left:20px;">
        	</div>
 
            <div class="fitem">
                <label class="labelone">Email:</label><label class="labeltwo" id="dlgApprovedEmail"></label>
            </div>
            <div class="fitem">
                <label class="labelone">DateTime:</label><label class="labeltwo" id="dlgApprovedDateTime"></label>
            </div>
        </form>
    </div>
    <div id="dlgApproved-buttons" style="text-align: center;">
        <label id="vacApproved" style="display:none;"><a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-vac" style="width:90px;" disabled>VAC</a></label>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-delete" onclick="javascript:deleteMessages(1);" style="width:90px">Delete</a>
    </div>        
</center>
</body>
</html>