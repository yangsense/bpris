<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/statanalysis/studyInfoList.js"></script>
</head>
 <body class="body">
 	<form id="searchForm" action="#">
 		<input type="hidden" name="orgId" value="${orgId }"/>
 		<input type="hidden" name="locId" value="${locId }"/>
 		<input type="hidden" name="modalityId" value="${modalityId }"/>
 		<input type="hidden" name="studyDatetime" value="${studyDatetime }"/>
    </form>
	<div class="main-content">
	    <div class="page-content min_style">
	        <div class="row">
	            	<table id="list" width="100%" border="0" cellspacing="0" cellpadding="0">
	               
	            	</table>
	            	<div id="pager"></div>
			</div>	
		</div>
	</div>
 </body>
</html>
