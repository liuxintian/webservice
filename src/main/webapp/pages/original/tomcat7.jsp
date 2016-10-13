<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voting List</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/color.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datagrid-detailview.js"></script>
	
<script>
            var ws;
            console.log(window.location.href);

          	console.log(window.location.hostname);
           
          	console.log(window.location.host);
           
          	console.log(window.location.pathname);

            function postToServer(){
                ws.send(document.getElementById("msg").value);
                document.getElementById("msg").value = "";
            }
            function closeConnect(){
                ws.close();
            }
            function openConnect(){
            	ws = new WebSocket("ws://"+document.location.host+"/omtwebservice/websocket/chat");
                ws.onopen = function(){
                };
                ws.onmessage = function(message){
                    document.getElementById("chatlog").textContent += message.data + "\n";
                };
            } 
            function openJavaConnect(){
            	var javaws = new WebSocket("ws://"+document.location.hostname+":8887");
            	javaws.onopen = function(){
                };
                javaws.onmessage = function(message){
                    document.getElementById("chatlog").textContent += message.data + "\n";
                };
            }
        </script>
</head>
    <body>
        <button type="submit" onClick="openJavaConnect()">ConnectJava</button>

        <button type="submit" onClick="openConnect()">Connect</button>
        <button type="submit" onClick="closeConnect()">End</button><br/>
        <textarea id="chatlog" readonly style="height:300px;width:300px;"></textarea><br/>
        <input id="msg" type="text"/>
        <button type="submit" id="sendButton" onClick="postToServer()">Send!</button>
    </body>

</html>