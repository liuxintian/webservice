<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voting Holding List</title> 
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
		$('#dgVoting').datagrid({
    		height:$(window).innerHeight()-100
		});
	}); 
	
	$(function(){
    	$('#dgVoting').datagrid({
    		height:$(window).innerHeight()-100
        });
    });
	
	WebSocketOpen();
});

function WebSocketOpen() {
   if ("WebSocket" in window) {
      // Let us open a web socket
      var ws = new WebSocket("ws://"+document.location.hostname+":8887");
		
      ws.onopen = function() {
         // Web Socket is connected, send data using send()
         ws.send("Message to send");
      };
		
      ws.onmessage = function (evt)  {
         var obj = $.parseJSON( evt.data )
         $('#dgVoting').datagrid({
        	 data:obj
         });
      };
		
      ws.onclose = function() { 
         //alert("Connection is closed..."); 
      };
   } else {
      // The browser doesn't support WebSocket
      //alert("WebSocket NOT supported by your Browser!");
   }
}
</script>
</head>
<body><center>
	<h2>Voting List</h2>
	<table id="dgVoting" title="Voting Details" class="easyui-datagrid" style="width:95%;height:600px;"
			url="<%=request.getContextPath()%>/votingholding/list" idFiled="id"
			pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
		<thead>
			<tr>
			    <th field="code" width="100">Code</th>
				<th field="market" width="100">Market</th>
			</tr>
		</thead>
	</table>

</center>
</body>
</html>