<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/taglibs.jsp" %>
    <%@ include file="/common/meta.jsp" %>
    <title>IT运营管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />    
    <meta http-equiv="X-UA-Compatible" content="IE=edge" > 
     <link href="${ctx}/aris/statics/pages/css/index/css/manage.css" rel="stylesheet" type="text/css">
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <style type="text/css">
<%--      .ui-jqgrid-btable .ui-widget-content.ui-state-highlight {--%>
<%--       background-color: #c4efc9--%>
<%--      }--%>
    </style>
        
</head>
<script type="text/javascript">
	$(function(){
		parent.$("#content").attr("src",$("#ITOperationPath").val());
	})
</script>
<body>
<input type="hidden" id="ITOperationPath" value="${ITOperationPath }"/>
</body>
</html>
