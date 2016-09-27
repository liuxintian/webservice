<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>OMT Webservice Question List</title> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/omtcss.css" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/color.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/datagrid-detailview.js"></script>
</head>
	
<script type="text/javascript">
function submitForm(){
    $('#uploadFrm').form('submit',{
        onSubmit: function(){
            var ret = $(this).form('validate');
            if(ret) {
                $('#uploadBtn').linkbutton({
                    disabled: true
                });
            }
            return ret;
        },
        success: function(data){
            $('#uploadFrm').form('reset');
            var json_obj = $.parseJSON(data);
            customImageurl = '<%=request.getContextPath()%>'+json_obj.url;
            $.messager.show({	// show result message
				title: 'Result',
				msg: json_obj.result,
				showType:'slide',
				style:{
					right:'',
					top:document.body.scrollTop+document.documentElement.scrollTop,
					bottom:''
				}
			});
            loadGraphics();
            $('#uploadBtn').linkbutton({
                disabled: false
            });
        }
    });
}

var customImageurl = "<%=request.getContextPath()%>/css/images/ok.png";
var defaultImageurl = "";
function loadGraphics(){
    //v-- will not work! 
    document.getElementById("loadImage").src = defaultImageurl;
    //v-- will work given your example conditions
    document.getElementById("loadImage").src = customImageurl;
}
window.onload = function(){
    loadGraphics();
};
</script>
<body><center>
    <h2>System Manual Documentation</h2>
    <div style="margin:20px 0 10px 0;"></div>
    <div class="easyui-accordion" data-options="multiple:true" style="width:70%;height1:500px;">
        <div title="Interfaces" data-options="iconCls:'icon-help',selected:true" style="padding:10px;">
        	<p><a href="<%=request.getContextPath()%>/cfg/getinterface"> User Development Guide</a></p>
        </div>
        <div title="Reference" data-options="iconCls:'icon-ok',selected:true" style="overflow:auto;padding:10px;">
            <p><a href="<%=request.getContextPath()%>/cfg/getreference"> Administrator Commands Reference</a></p>
        </div>
        <div title="Upload and Import Files" data-options="iconCls:'icon-ok',selected:true" style="overflow:auto;padding:10px;">
        
        <form id="uploadFrm" method="POST" modelAttribute="uploadForm" enctype="multipart/form-data" action="<%=request.getContextPath()%>/file/savefiles"> 
	        <div class="easyui-panel" title="Upload Holiday File" style="width:100%;max-width:50%;padding:30px 60px;">
		        <div style="margin-bottom:20px">
		            <input class="easyui-filebox" label="File1:" name="files[0]" labelPosition="top" required data-options="prompt:'Choose a [.xlsx] file...'" style="width:100%">
		        </div>
		        <div>
		            <a href="#" id="uploadBtn" class="easyui-linkbutton" style="width:100%" onclick="submitForm()">Upload</a>
		        </div>
				<div id="loadImage-wrap">
				    <img id="loadImage" />
				</div>
	   	 	</div>
        </form>
        
        </div>
    </div>
    
</center></body>
</html>