<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OMT Webservice Question List Detail</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
</head>
<body>
	<table class="dv-table" style="width:100%;border:1px solid #ccc;padding:5px;margin-top:5px;">
		<tr>
			<td class="show-details-center">Name</td>
			<td class="show-details-left">${usermessage.name}</td>
			<td class="show-details-center">Email</td>
			<td class="show-details-left">${usermessage.email}</td>
			<td class="show-details-center">Date Time</td>
			<td class="show-details-left">${usermessage.datetime}</td>
		</tr>
		<tr>
			<td class="show-details-center">Message</td>
			<td colspan="5"><textarea name="notes" class="easyui-textarea" style="width:98%;" rows="3">${usermessage.notes}</textarea></td>
		</tr>
	</table>
</body>
</html>