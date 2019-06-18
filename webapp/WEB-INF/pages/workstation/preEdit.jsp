<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>上次编辑内容</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet"
          href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/selectTree.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/config.js"></script>
</head>
<body>
<div class="wrap100 quickrecharge">
    <div class="r_list" style="margin-left:24px;margin-right:12px;">
        <div class="col-xs-12 col-sm-12 col-md-12">
            <div class="clearfix form-actions">
            <form class="form-horizontal" role="form">
                <input id="studyinfoId" value="studyinfoId" name="studyinfoId" type="hidden"/>
                <div class="form-group">
                    <label class="col-xs-2 control-label no-padding-right" style="width: 15%;margin-left: 20px;">
                        检查所见： </label>
                    <div class="col-xs-3" style="width: 75%;height:270px">
                       		<textarea id="templateExam" cols="5" rows="18" isRich="true" name="templateExam"
                                      class="ckeditor">
                                ${oldreportExam}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label no-padding-right" style="width: 15%;margin-left: 20px;">
                        诊断意见： </label>
                    <div class="col-xs-3" style="width: 75%;height:270px">
                        	<textarea id="templateResult" cols="5" rows="18" isRich="true" name="templateResult"
                                      class="ckeditor">
                                ${oldreportResult}</textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-2 control-label no-padding-right" style="width: 15%;margin-left: 20px;">
                        备注： </label>
                    <div class="col-xs-3" style="width: 75%;height:270px">
                         <textarea id="reportRemark" cols="160" rows="3" name="reportRemark"
                                   style="font-family: Arial, Verdana, sans-serif;font-size: 24px;color: #222;">${oldreportRemark}</textarea>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</div>
</div>
<!--右侧内容 end-->
</div>
</div>
</body>
<script type="text/javascript">
    function saveFunc() {
        var reportExam = $("#templateExam").val();
        var reportResult = $("#templateResult").val();
        var reportRemark = $("#reportRemark").val();
        var returnJson = {
            "reportExam": reportExam,
            "reportResult": reportResult,
            "reportRemark": reportRemark
        }
        return returnJson;
    }
</script>
</html>
