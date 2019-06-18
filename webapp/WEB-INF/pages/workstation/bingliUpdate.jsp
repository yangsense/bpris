<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>病例修改</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/bingliupdate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/config.js"></script>
</head>
<body>
<div class="wrap100 quickrecharge">
	<div class="r_list" style="margin-left:24px;margin-right:12px;"> 
            <div class="row report m-t-20">
                <div class="col-lg-3 report-left ">
                    <!-- 左侧树列表 -->
					<div id="templateTreeSet" region="west" split="true" title="&nbsp;" maximizable="false"
					     class="ztree layout-body panel-body" style="border:1px solid #ddd; padding:5px;height: 436px;"> 					                   
                    </div>
                    <div>
                    </div>
                </div>
                <div class="col-lg-9 report-right" >
                	<div class="q_r_list" style="border: 1px solid #ddd">
                    <!--查询条件 begin-->
						<div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12" >
                <form class="form-horizontal" role="form" id="templateDetaiForm" dataType="${templateType}" templatedirId="${templatedirId}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%;margin-left: 20px;"> 病例模板名称： </label>
                        <div class="col-xs-3"  style="width: 35%">
                        	<input  class="inputWT400" type="text" id="templateName" name="templateName" value="${templateBean.templateName}" disabled="disabled"/>
                        </div>
                    </div>
                     
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%;margin-left: 20px;"> 病例检查所见： </label>
                        <div class="col-xs-3"  style="width: 75%;height:270px">
                       		<textarea id="templateExam" cols="5" rows="18" isRich="true" name="templateExam" class="ckeditor">
                       		${templateBean.templateExam}</textarea>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%;margin-left: 20px;"> 病例诊断结果： </label>
                        <div class="col-xs-3"  style="width: 75%;height:270px">
                        	<textarea id="templateResult" cols="5" rows="18" isRich="true" name="templateResult" class="ckeditor">
                       		${templateBean.templateResult}</textarea>
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-bt-center" align="center">
                            <button class="btn btn-info" type="button" id="saveBtn" style="display:none;" onclick="save()">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                	<span id="showText">保 存</span>
                            </button>
                            <button class="btn btn-info" type="button" id="closeBtn" style="display:none;" onclick="resetTemp()">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                             	   重 置
                            </button>
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
</html>
