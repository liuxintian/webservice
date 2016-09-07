<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
    <title>OMT WebService WebSocket Client</title>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/sockjs-0.3.4.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/stomp.js"></script>
    <script type="text/javascript">
        var stompClient = null;
        
        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
            document.getElementById('response').innerHTML = '';
        }
        
        function connect() {
            var socket = new SockJS("http://localhost:9090/omtwebservice/omtwebsocket"); // should be a real url like: http://host:15660/omt
            stompClient = Stomp.over(socket);            
            stompClient.connect({}, function(frame) {
                setConnected(true);
                console.log('Connected: ' + frame);
                stompClient.subscribe('/notify/response', function(notify){
                    showGreeting(JSON.parse(notify.body).name);
                });
            });
        }
        
        function disconnect() {
            if (stompClient != null) {
                stompClient.disconnect();
            }
            setConnected(false);
            console.log("Disconnected");
        }
        
        function sendName() {
            var name = document.getElementById('name').value;
            var data = document.getElementById('data').value;
            stompClient.send("/notify/greeting", {}, JSON.stringify({ 'name': name,'data': data}));
        }
        
        function showGreeting(message) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            response.appendChild(p);
        }
    </script>
</head>
<body onload="disconnect()" style="text-align:center;">
<noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></noscript>
<div>
    <div>
        <button id="connect" onclick="connect();">Connect</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
    </div><br>
    <div id="conversationDiv">
        <label>What is your company?</label><input type="text" id="name" /><br>
        <label>What is your message?</label><input type="text" id="data" /><br>
        <button id="sendName" onclick="sendName();">Send</button>
        <br>
        
        <p id="response"><label>Response from websocket server:</label>
        </p>
    </div>
</div>
</body>
</html>