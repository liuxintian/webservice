<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Information Management</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/color.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datagrid-detailview.js"></script>
	
<script type="text/javascript">
function saveToDo(){
	if(!$('#fmToDo').form('validate')){
		return;
	}
 	$.ajax({
		async:true,
		type:"POST",
		url: '<%=request.getContextPath()%>/todo/new', 
		data: { content :$('#content').val()},
		success: function(data) {
			$.messager.show({
                title: 'Message',
                msg: data.result,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
			$('#fmToDo').form('clear');
            $('#dgToDo').datagrid('reload'); 
		}
	});	
}


</script>
</head>
<body><center>
	<form id="fmToDo" method="post" novalidate>
    <div>
         <label>Information:</label>
         <input name="content" id="content" class="easyui-textbox" style="width:40%;" data-options="required:true,validType:['length[3,500]'],prompt:''" >
         <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="saveToDo()" style="width:100px;">Add</a>
   </div>
    </form>

</center>
</body>
</html>