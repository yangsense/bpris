<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/messages_cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/collectionCase.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>
</head>
<body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscConsultForm">
                    <input type="hidden" id="operatorId" name="operatorId" value="${operatorId}"/>
                    <input type="hidden" id="reportId" name="reportId" value="${reportId}"/>
                    <div id="div3">
                    <th class="r" width="24%">标题：</th>
                    <td width="8%">
                        <input type="text" style="width:170px" id="keydesc"/>
                    </td>
                    <td width="8%" colspan="2">
                        <button class="btn btn-info" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                <span id="showText">添 加</span>
                        </button>
                        <button class="btn btn-info" type="button" id="closeBtn">
                            <i class="ace-icon fa fa-check bigger-110"></i>
                            	取 消
                        </button>
                    </td>
                    </div>
                    
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
