<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Company Meeting Management</title> 
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
		$('#dgCompany').datagrid({
    		height:$(window).innerHeight()-100
		});
	});
	
	var conf = {
            options:{
                fitColumns:true,
                idField:'companyID',
                columns:[[
                    {field:'companyID',title:'Company ID',width:80},
                    {field:'companyName',title:'Company Name',width:100},
                    {field:'tickerCode',title:'Ticker Code',width:50},
                    {field:'companyLogo',title:'Company Logo',width:300}
                ]], 
                data:[
                    {companyID:'C001',meetingID:'M001',companyName:'Omni Market Tide Ltd.',tickerCode:'OMT',companyLogo:'http://omnimarkettide.com/wp-content/themes/coded/img/hero-logo.png'},
                    {companyID:'C002',meetingID:'M002',companyName:'Telstr Company Ltd.',tickerCode:'TLS',companyLogo:'http://omnimarkettide.com/wp-content/themes/coded/img/hero-logo.png'}
                ]
            },
            subgrid:{
                options:{
                    fitColumns:true,
                    foreignField:'companyID',
                    columns:[[
                        {field:'companyID',title:'Company ID',width:100},
                        {field:'meetingID',title:'Meeting ID',width:100},
                        {field:'meetingDate',title:'Meeting Date',width:100},
                        {field:'meetingTime',title:'Meeting Time',width:100},
                        {field:'meetingType',title:'Meeting Type',width:100},
                        {field:'meetingLocation',title:'Meeting Location',width:200},
                        {field:'meetingBeforeMsg',title:'Meeting Before Message',width:200},
                        {field:'meetingAfterMsg',title:'Meeting After Message',width:200}
                    ]],
                    data:[
                        {companyID:'C001',meetingID:'M001',documentID:'DOC001',meetingDate:'08/08/2016',meetingTime:'10:30', meetingLocation:'200 Toorak Road, South Yarra VIC 3141', meetingType:'AGM',meetingBeforeMsg:'Please bring your laptop together.',meetingAfterMsg:'Dont forget to take your laptop.'},
                        {companyID:'C002',meetingID:'M002',documentID:'DOC002',meetingDate:'09/08/2016',meetingTime:'10:30', meetingLocation:'200 Toorak Road, South Yarra VIC 3141', meetingType:'AGM',meetingBeforeMsg:'Please bring your laptop together.',meetingAfterMsg:'Dont forget to take your laptop.'}
                    ]
                }/* ,
                subgrid:{
                    options:{
                        fitColumns:true,
                        foreignField:'contactID',
                        columns:[[
                            {field:'contactID',title:'Contact ID',width:60},
                            {field:'name',title:'Name',width:80},
                            {field:'email',title:'Email',width:100},
                            {field:'phone',title:'Phone',width:100},
                            {field:'weburl',title:'WEB URL',width:200},
                        ]],
                        data:[
                            {contactID:'CT001',name:'Admin',email:'admin@omnimarkettide.com',phone:'61 3 8566 6888', weburl:'http://omnimarkettide.com/wp-content/uploads/20160706-CoSec-and-CFO.pdf'},
                            {contactID:'CT002',name:'Admin',email:'admin@omnimarkettide.com',phone:'61 3 8566 6888', weburl:'http://omnimarkettide.com/wp-content/uploads/20160706-CoSec-and-CFO.pdf'}
                        ]
                    },
	                subgrid:{
                        options:{
                            fitColumns:true,
                            foreignField:'documentID',
                            singleSelect:true,
                            columns:[[
                                {field:'documentID',title:'Document ID',width:60},
                                {field:'documentType',title:'Document Type',width:60},
                                {field:'documentName',title:'Document Name',width:100},
                                {field:'documentLink',title:'Document Link',width:200}
                            ]],
                            data:[
                                {documentID:'DOC001',documentType:'Video',documentName:'The Annual General Meeting 1.',documentLink:'http://omnimarkettide.com/wp-content/uploads/20160705.pdf'},
                                {documentID:'DOC002',documentType:'Audio',documentName:'The Annual General Meeting 2.',documentLink:'http://omnimarkettide.com/wp-content/uploads/20160706.pdf'}
                            ]
                        },
	                	subgrid:{
	                    options:{
	                        fitColumns:true,
	                        foreignField:'documentID',
	                        columns:[[
	                            {field:'attributeName',title:'Attribute Name',width:100},
	                            {field:'attributeValue',title:'Attribute Value',width:100},
	                        ]],
	                        data:[
	                            {attributeName:'Length',attributeValue:'01:30'},
	                            {attributeName:'Size',attributeValue:'10MB'}
	                        ]
	                    }
	                }
                } 
                },*/
            }
        };
	$(function(){
    	$('#dgCompany').datagrid({
    		height:$(window).innerHeight()-100
        }).datagrid('subgrid', conf);;
    });
});

var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if ($('#dgCompany').datagrid('validateRow', editIndex)){
        $('#dgCompany').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}

function append(){
    if (endEditing()){
        $('#dgCompany').datagrid('appendRow',{status:'P'});
        editIndex = $('#dgCompany').datagrid('getRows').length-1;
        $('#dgCompany').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
    }
}
// url="<%=request.getContextPath()%>/company/list" 
</script>
</head>
<body><center>
	<h2>Company Meeting:${company.companyName}</h2>
	<table id="dgCompany" title="Company Meeting Management" class="easyui-datagrid" style="width:95%;height:600px;"
			toolbar="#toolbarUser" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
	</table>
    <div id="toolbarUser">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="append()">Append</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove</a>
    </div>
      
</center>
</body>
</html>