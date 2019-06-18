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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscordsubcategory/edit.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
</head>

<body class="body">
<input type="hidden" id="orgIdValue" value="${orgId}"/>
<div class="wrap100 quickrecharge">
    <!--导航end-->
    <div class="q_r_list">
        <div class="listTable">
            <form class="form-horizontal"  role="form" id="editForm"  opType="${opType}" >
            <input type="hidden" name="ordsubcategoryId" id="ordsubcategoryId" value="${id}"/>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr align="left">

                        <th class="r" width="13%"><span style="color:red;">*</span> 检查大类名称：</th>
                        <td width="18%">
                            <select id="ordcategoryId" name="ordcategoryId"  class="inputW160">
                                <option value="1">请选择大类</option>
                            </select>
                        </td> 
                        <th class="r" width="13%"><span style="color:red;">*</span>检查子类编码：</th>
                        <td width="18%">
                            <input class="inputW160" type="text" id="ordsubcategoryCode" name="ordsubcategoryCode"/>
                        </td>
                    </tr>
                    <tr>
                        <th width="13%" class="r"><span style="color:red;">*</span> 检查子类名称：</th>
                        <td width="18%">
                            <input class="inputW160" type="text" id="ordsubcategoryDesc" name="ordsubcategoryDesc"/>
                        </td>
                        <th width="13%" class="r">英文描述：</th>
                        <td width="18%">
                            <input name="ordsubcategoryEndesc" id="ordsubcategoryEndesc" class="inputW160"/>
                        </td>
                    </tr><tr>
                    <th width="13%" class="r">注意事项：</th>
                    <td width="18%">
                        <input class="inputW160" type="text" id="ordsubcategoryNote" name="ordsubcategoryNote"/>
                    </td>

                </tr>
                </table>
            </form>


        </div>
    </div>
</div>
<div class="main-content">
    <div class="page-content min_style">
        <div class="col-bt-center">
            <button class="btn btn-info" type="button" id="saveBtn">
                <i class="ace-icon fa fa-check bigger-110"></i>
                <span id="showText">保 存</span>
            </button>
            <button class="btn btn-info" type="button" id="closeBtn">
                <i class="ace-icon fa fa-check bigger-110"></i>
                取 消
            </button>
        </div>
    </div>
</div>
 </body>

</html>
