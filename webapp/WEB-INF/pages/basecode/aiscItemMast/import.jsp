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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscItemMast/import.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>
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
 <input type="hidden" id="currentLocId" value="${cookie_locId }"/>
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="editForm">
                    <div class="form-group">
                    	 <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 医嘱大类： </label>
                     	 	 <div class="col-xs-3"  style="width: 35%" >
                     	 	<select id="ordcategoryId" name="ordcategoryId" onchange="initSubCate(-1)" style="width: 100%">
                               <option value="-1">请选择大类</option>
                            </select>
                    		</div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 医嘱子类： </label>
                         <div class="col-xs-3"  style="width: 35%" >
                          	<select id="ordsubcategoryId" name="ordsubcategoryId" style="width: 100%">
                               <option value="-1">请选择子类</option>
                            </select></div>
                    </div>
                    <div class="form-group">
                    	 <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 科室： </label>
                     	 	 <div class="col-xs-3"  style="width: 35%" >
                     	 	<select id="locInfo" name="locCode" style="width: 100%">                               
                            </select> 
                    		</div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-primary" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110 icon-save"></i>
                                <span id="showText">导 入</span>
                            </button>
							<button class="btn btn-primary" type="button" id="exportItem">
                                <i class="ace-icon fa fa-check bigger-110 icon-save"></i>
                                <span id="showText">导出Excel</span>
                            </button>
                            <button class="btn btn-gray" type="button" id="closeBtn">
                                <i class="ace-icon fa fa-check bigger-110 icon-remove"></i>
                                关 闭
                            </button>
                        </div>
                    </div>
                    <input type="hidden" name="itemmastId" id="itemmastId" value="${id}"/>
                </form>
			</div>
		</div>	
	</div>
</div>
 </body>
</html>
