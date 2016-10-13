<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<title>OMT Webservice Access Statistics</title> 
	<%@ include file="/pages/common/common_meta.jspf"%>
	<%@ include file="/pages/common/common_jsscripts.jspf"%>
	
	<script type="text/javascript">
	$(document).ready(function(){
		$(window).resize(function() {
			$('#dgStatistic').datagrid({
				height:$(window).innerHeight()-global_height_gap
			});
		});
	    $(function(){
	    	var dgStatistic = $('#dgStatistic').datagrid({
	    		height:$(window).innerHeight()-global_height_gap,
	            pageList:global_pageList,
	            pageSize:global_pageSize,
	            url: '<%=request.getContextPath()%>/stat/querylist',
	            pagination: true,
	            remoteFilter: true,
	            rownumbers: true
	        });
	    	
	    	dgStatistic.datagrid('enableFilter', [{
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
		                    dgStatistic.datagrid('removeFilterRule', 'date');
		                } else {
		                    dgStatistic.datagrid('addFilterRule', {
		                        field: 'date',
		                        op: 'equal',
		                        value: value
		                    });
		                }
		                dgStatistic.datagrid('doFilter');
		            },
	              panelWidth: '240',
	              editable: true
	            }
	        },{
	            field:'type',
	            type:'combobox',
	            options:{
	                panelHeight:'auto',
		            data:[{value:'',text:'All'},{value:'SharePrice',text:'SharePrice'},{value:'ChartHistory',text:'ChartHistory'}],
		            onChange:function(value){
		                if (value == ''){
		                    dgStatistic.datagrid('removeFilterRule', 'type');
		                } else {
		                    dgStatistic.datagrid('addFilterRule', {
		                        field: 'type',
		                        op: 'equal',
		                        value: value
		                    });
		                }
		                dgStatistic.datagrid('doFilter');
		            }
	            }
	        }]);
	    });
	    window.setTimeout(reload, 100);
	});
	
	function reload() {
		$(window).resize(function() {
			$('#dgStatistic').datagrid({
	    		height:$(window).innerHeight()-100
			});
		});
		$('#dgStatistic').datagrid('reload'); 
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
    <a href="<%=request.getContextPath()%>/stat/index" class="visited">Data Access History</a>
    <a href="<%=request.getContextPath()%>/cfg/index">System Configuration</a>
    <a href="<%=request.getContextPath()%>/logout">Logout</a>
   </nav>
   
   <article id="content">
	
	<table id="dgStatistic" title="Statistics List" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/stat/querylist" idFiled="id"
			pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			    <th field="date" width="60">Date</th>
				<th field="code" width="60">Company</th>
				<th field="type" width="60">Type</th>
				<th field="count" width="50">Count</th>
			</tr>
		</thead>
	</table>
   </article>
   
   <%@ include file="/pages/common/common_footer.jspf"%>
  </div>  
 </body>   
</html>