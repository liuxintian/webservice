<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OMT Webservice SMA History</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/color.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datagrid-filter.js"></script>
	
<script type="text/javascript">
$(document).ready(function(){
	$(window).resize(function() {
		$('#dgSmaHistory').datagrid({
    		height:$(window).innerHeight()-100
		});
	});
    $(function(){
    	var dgSmaHistory = $('#dgSmaHistory').datagrid({
    		height:$(window).innerHeight()-100,
            pageList:[50,20,100,200],
            url: '<%=request.getContextPath()%>/stat/smahistlist',
            pagination: true,
            remoteFilter: true,
            rownumbers: true
        });
    	
    	dgSmaHistory.datagrid('enableFilter', [{
            field:'date',
            type:'datebox',
            options:{
            	formatter:function(date){
            		var y = date.getFullYear();
                    var m = date.getMonth()+1;
                    var d = date.getDate();
                    return (d<10?('0'+d):d)+'/'+(m<10?('0'+m):m)+'/'+y;
        		},
        		parser:function(s){
        			if (!s) return new Date();
                    var ss = (s.split('/'));
                    var y = parseInt(ss[2],10);
                    var m = parseInt(ss[1],10);
                    var d = parseInt(ss[0],10);
                    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
                        return new Date(y,m-1,d);
                    } else {
                        return new Date();
                    }
        		},            	
	            onChange:function(value){
	                if (value == ''){
	                    dgSmaHistory.datagrid('removeFilterRule', 'date');
	                } else {
	                    dgSmaHistory.datagrid('addFilterRule', {
	                        field: 'date',
	                        op: 'equal',
	                        value: value
	                    });
	                }
	                dgSmaHistory.datagrid('doFilter');
	            },
              panelWidth: '240',
              editable: true
            }
        },{
            field:'type',
            type:'combobox',
            options:{
                panelHeight:'auto',
	            data:[{value:'',text:'All'},{value:'1W',text:'1W'},{value:'1M',text:'1M'},{value:'3M',text:'3M'},{value:'6M',text:'6M'},{value:'1Y',text:'1Y'}],
	            onChange:function(value){
	                if (value == ''){
	                    dgSmaHistory.datagrid('removeFilterRule', 'type');
	                } else {
	                    dgSmaHistory.datagrid('addFilterRule', {
	                        field: 'type',
	                        op: 'equal',
	                        value: value
	                    });
	                }
	                dgSmaHistory.datagrid('doFilter');
	            }
            }
        }]);
    });
    window.setTimeout(reload, 100);
});

function reload() {
	$(window).resize(function() {
		$('#dgSmaHistory').datagrid({
    		height:$(window).innerHeight()-100
		});
	});
	$('#dgSmaHistory').datagrid('reload'); 
}

</script>
</head>
<body><center>
	<h2>SMA History</h2>
	
	<table id="dgSmaHistory" title="SMA History List" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/stat/smahistlist" idFiled="id"
			pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			    <th field="code" width="50">Code</th>
				<th field="type" width="30">Type</th>
				<th field="value" width="250">Value</th>
			</tr>
		</thead>
	</table>
	       
</center>
</body>
</html>