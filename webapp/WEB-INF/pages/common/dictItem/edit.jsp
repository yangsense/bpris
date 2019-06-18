<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>

    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet"
          href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <link rel="stylesheet" href="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/messages_cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/common/dictItem/edit.js"></script>
</head>

<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12">
                <form class="form-horizontal" role="form" id="editForm" opType="${opType}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"><span style="color:red;">*</span> 字典名： </label>

                        <div class="col-sm-9">
                            <input class="inputW160" type="text" id="dictName" name="dictName" value="${dictName}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"><span style="color:red;">*</span> 字典编号： </label>

                        <div class="col-sm-9">
                            <input class="inputW160" type="text" id="itemNo" name="itemNo" value="${itemNo}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"><span style="color:red;">*</span> 字典内容： </label>

                        <div class="col-sm-9">
                            <input class="inputW160" type="text" id="itemName" name="itemName"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 父节点编号： </label>

                        <div class="col-sm-9">
                            <input class="inputW160" type="text" id="parentItemNo" name="parentItemNo"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 排序： </label>

                        <div class="col-sm-9">
                            <input class="inputW160" type="text" id="itemOrder" name="itemOrder"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 状态： </label>

                        <div class="col-sm-9">
                            <select id="itemState" name="itemState">
                                <option value="1">有效</option>
                                <option value="0">无效</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 水平： </label>

                        <div class="col-sm-9">
                            <input class="inputW160" type="text" id="itemLevel" name="itemLevel"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"> 备注： </label>

                        <div class="col-sm-9">
                            <textarea rows="3" cols="50" name="itemDesc" id="itemDesc"></textarea>
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
                </form>
            </div>
        </div>
    </div>
</div>

</html>