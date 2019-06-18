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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscItemMast/edit.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/selectTree.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>
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
                <form class="form-horizontal" role="form" id="editForm" opType="${opType}"  old_org_Id="${AiscItemMastModel.orgId}" old_itemmast_Isenhanced="${AiscItemMastModel.itemmastIsenhanced}"  old_itemmast_service="${AiscItemMastModel.itemmastSevice}"   old_ordcategoryId="${AiscItemMastModel.ordcategoryId}"  old_ordsubcategoryId="${AiscItemMastModel.ordsubcategoryId}">
                    <div class="form-group">
                    	 <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 医嘱大类： </label>
                     	 	 <div class="col-xs-3"  style="width: 35%" >
                     	 	<select id="ordcategoryId" name="ordcategoryId" onchange="initSubCate(-1)" class="inputW160">
                               <option value="-1">请选择大类</option>
                            </select>
                    		</div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 医嘱子类ID： </label>
                         <div class="col-xs-3"  style="width: 35%" >
                          	<select id="ordsubcategoryId" name="ordsubcategoryId" class="inputW160">
                               <option value="-1">请选择子类</option>
                            </select>
                            <%--
                            <input class="inputW160" type="text" id="ordsubcategoryId" name="ordsubcategoryId"/>
                        --%></div>
                    </div>

                    <div class="form-group">
                    
                     <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 医嘱代码： </label>

                         <div class="col-xs-3"  style="width: 35%" >
                            <input class="inputW160" type="text" id="itemmastCode" name="itemmastCode"/>
                        </div>
                    
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 医嘱名称： </label>

                         <div class="col-xs-3"  style="width: 35%" >
                            <input class="inputW160" type="text" id="itemmastDesc" name="itemmastDesc" onblur="getpym();" onkeyup="getpym();"/>
                        </div>
                  
                        
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 名称拼音首字母： </label>

                         <div class="col-xs-3"  style="width: 35%" >
                            <input class="inputW160" type="text" id="pyInitial" name="pyInitial" readonly="readonly"/>
                        </div>
                        
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 英文名称： </label>

                         <div class="col-xs-3"  style="width: 35%" >
                            <input class="inputW160" type="text" id="itemmastEndesc" name="itemmastEndesc"/>
                        </div>
                        
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 医嘱费用： </label>

                         <div class="col-xs-3"  style="width: 35%" >
                            <input class="inputW160" type="text" id="itemmastPrice" name="itemmastPrice"/>
                        </div>
                        
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 检查项目权重： </label>

                         <div class="col-xs-3"  style="width: 35%" >
                            <input class="inputW160" type="text" id="itemmastWeight" name="itemmastWeight"/>
                        </div>
                    
                    </div> 
                    
                <div class="form-group">
                	<label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 曝光次数： </label>

                         <div class="col-xs-3"  style="width: 35%" >
                            <input class="inputW160" type="text" id="itemmastExposurecount" name="itemmastExposurecount"/>
                        </div>
                
                    <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 服务标志： </label>

                         <div class="col-xs-3"  style="width: 35%" >
                            <select id="itemmastSevice" name="itemmastSevice" class="inputW160">
                                <option value="-1">请选择服务标志</option>
                            </select>
                        </div>
                </div>
				<div class="form-group">
					 <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 是否增强检查： </label>

                     <div class="col-xs-3"  style="width: 35%" >
                        <select id="itemmastIsenhanced" name="itemmastIsenhanced" class="inputW160">
                            <option value="-1">请选择是否增强检查</option>
                        </select>
                     </div>
				
                     <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 所属机构： </label>
                     <div class="col-xs-3"  style="width: 35%">
                	<select id="orgId" name="orgId" class="inputW160">
                              <option value="-1">请选择机构</option>
                          </select>
                    </div>
                    </div>

                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-primary" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110 icon-save"></i>
                                <span id="showText">保 存</span>
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
