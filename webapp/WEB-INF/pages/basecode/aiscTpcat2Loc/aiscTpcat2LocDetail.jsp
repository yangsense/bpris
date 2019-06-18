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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aisctpcat2loc/aisctpcat2locdetail.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/selectTree.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscTpcatLocForm" dataType="${dataType}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 对应ID： </label>
                        <div class="col-xs-3" style="width:35%">
                            <input  class="inputW120" type="text" id="tplcat2locId" name="tplcat2locId" value="${aiscTpcat2LocBean.tplcat2locId}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 设备类型ID： </label>
                        <div class="col-xs-3" style="width:35%">
                        	<input  class="inputW120" type="text" id="modalityId" name="modalityId" value="${aiscTpcat2LocBean.modalityId}" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 科室ID： </label>
                        <div class="col-xs-3" style="width:35%">
                           <input  class="inputW120" type="text" id="locId" name="locId" value="${aiscTpcat2LocBean.locId}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 用户ID： </label>
                        <div class="col-xs-3" style="width:35%">
                           <input  class="inputW120" type="text" id="operatorId" name="operatorId" value="${aiscTpcat2LocBean.operatorId}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 模板大类ID： </label>
                        <div class="col-xs-3" style="width:35%">
                           <input  class="inputW120" type="text" id="templatecatId" name="templatecatId" value="${aiscTpcat2LocBean.templatecatId}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 机构编号： </label>
                        <div class="col-xs-3" style="width:35%">
                           <input id="orgName" type="text" readonly value="" class="select"/>
                           <input id="orgId" type="hidden" readonly name="orgId" value="${aiscTpcat2LocBean.orgId}"/>
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
                </form>
			</div>
		</div>	
	</div>
</div>
 </body>
</html>
