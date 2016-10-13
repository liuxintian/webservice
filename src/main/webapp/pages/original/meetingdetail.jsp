<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Meeting Detail</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
</head>
<body>
	<table class="dv-table" style="width:100%;border:1px solid #ccc;padding:5px;margin-top:5px;">
		<tr>
			<td class="show-details-right">Name</td>
			<td class="show-details-left">${meetingJson.meetingName}</td>
			<td class="show-details-right">Date</td>
			<td class="show-details-left">${meetingJson.meetingDate}</td>
			<td class="show-details-right">Time</td>
			<td class="show-details-left">${meetingJson.meetingTime}</td>
			<td class="show-details-right">Type</td>
			<td class="show-details-left">${meetingJson.meetingType}</td> 
			<td class="show-details-right">Has Password</td>
			<td class="show-details-left">${meetingJson.hasPassword}</td>
		</tr>
		<tr>
			<td class="show-details-right">Location</td>
			<td class="show-details-left">${meetingJson.meetingLocation}</td>
			<td class="show-details-right">BeforeMsg</td>
			<td class="show-details-left">${meetingJson.meetingBeforeMsg}</td>
			<td class="show-details-right">AfterMsg</td>
			<td class="show-details-left">${meetingJson.meetingAfterMsg}</td>
			<td class="show-details-right">Contact</td>
			<td class="show-details-left">${meetingJson.meetingContacts.name}</td>
			<td class="show-details-right">Document</td> 
			<td class="show-details-left"> 
			<c:forEach items="${meetingJson.meetingDetails.documents}" var="document">
				[ ${document.documentName} ] &nbsp;
			</c:forEach>
			</td>
		</tr>
	</table>
</body>
</html>