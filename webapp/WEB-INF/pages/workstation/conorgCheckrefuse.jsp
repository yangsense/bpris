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
	<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>
</head>
<script>
$(function () {
	$("#refuseCloseBtn").click(function(){
       closeDiv();
    });

});

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}	

function refuseApplySave(){
	var data = $("#refuseForm").serializeObject();
    data.studyinfoId = $("#refuseForm").attr("paramstudyinfoId");
    data = $.toJSON(data);
    if($("#studyRemark").val()!=""){
    	
    	$.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/conorgCheck/refuseApply",
	        dataType: 'json',
	        contentType: 'application/json',
	        data: data,
	        success: function (data) {
	            if (data.ERRCODE == "0") {
	            	layer.msg('退回申请成功！');
	                parent.reloadGrid();
	               setTimeout(closeDiv,1500);
	            }
	            else {
	                layer.msg('退回申请失败！', {icon: 1});
	            }
	        }
	    });
    	
	}else{
		layer.alert('请填写退回原因', {icon: 5,shadeClose: true});
		return  false;
	}
	
}
</script>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="refuseForm" paramstudyinfoId="${studyinfoId}" >
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 25%"> 退回原因： </label>
                        <div class="col-xs-9"  style="width: 75%;">
                        	<textarea id="studyRemark" cols="40" rows="4"  name="studyRemark" ></textarea>
                        </div>
                    </div>
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-info" type="button" id="refuseBtn" onclick="refuseApplySave()">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                	<span id="showText">确 认</span>
                            </button>
                            <button class="btn btn-info" type="button" id="refuseCloseBtn">
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
