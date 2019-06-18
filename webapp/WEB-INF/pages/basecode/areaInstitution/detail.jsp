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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscAreaInstitution/detail.js"></script>
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
                <form class="form-horizontal" role="form" id="aiscServerForm" dataType="${dataType}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> ID： </label>
                        <div class="col-xs-3" style="width: 35%">
                            <input  class="inputW120" type="text" id="conorgId" name="conorgId" value="${bean.conorgId}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 机构ID： </label>
                        <div class="col-xs-3" style="width: 35%">
                        	<input  class="inputW120" type="text" id="orgId" name="orgId" value="${bean.orgId}" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 机构名称： </label>
                        <div class="col-xs-3" style="width: 35%">
                           <input  class="inputW120" type="text" id="orgName" name="orgName" value="${bean.orgName}"/>
                        </div>
                         <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 城市编码： </label>
                        <div class="col-xs-3" style="width: 35%">
                        	<input  class="inputW120" type="text" id="cityCode" name="cityCode" value="${bean.cityCode}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 城市名称： </label>
                        <div class="col-xs-3" style="width: 35%">
                            <input  class="inputW120" type="text" id="cityDesc" name="cityDesc" value="${bean.cityDesc}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 是否上线： </label>
                        <div class="col-xs-3" style="width: 35%">
                           <select id="haspacs" name="haspacs" class="inputW120">
                               <option value="-1">请选择</option>
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 区县编码： </label>
                        <div class="col-xs-3" style="width: 35%">
                           <input  class="inputW120" type="text" id="countyCode" name="countyCode" value="${bean.countyCode}"/>
                        </div>
                         <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 区县名称： </label>
                        <div class="col-xs-3" style="width: 35%">
                        	<input  class="inputW120" type="text" id="countyDesc" name="countyDesc" value="${bean.countyDesc}"/>
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
