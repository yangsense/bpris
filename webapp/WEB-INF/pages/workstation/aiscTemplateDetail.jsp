<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/messages_cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/templatedetail.js"></script>

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
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="templateDetaiForm" dataType="${dataType}" templatedirId="${templatedirId}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 模板名称： </label>
                        <div class="col-xs-3"  style="width: 35%">
                        	<input  class="inputW120" type="text" id="templateName" name="templateName" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 检查方法： </label>
                        <div class="col-xs-3"  style="width: 35%">
                           <input  class="inputW120" type="text" id="templateMethod" name="templateMethod"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 检查所见： </label>
                        <div class="col-xs-3"  style="width: 75%;height:270px">
                       		<textarea id="templateExam" cols="5" rows="18" isRich="true" name="templateExam" class="ckeditor">
                       		</textarea>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 诊断结果： </label>
                        <div class="col-xs-3"  style="width: 75%;height:270px">
                        	<textarea id="templateResult" cols="5" rows="18" isRich="true" name="templateResult" class="ckeditor">
                       		</textarea>
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-info" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                	<span id="showText">新 增</span>
                            </button>
                            <button class="btn btn-info" type="button" id="closeBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                             	   取 消
                            </button>
                        </div>
                    </div>
                    <input type="hidden" name="templatecontentId" id="templatecontentId" />
                </form>
			</div>
		</div>	
	</div>
</div>
 </body>
</html>
