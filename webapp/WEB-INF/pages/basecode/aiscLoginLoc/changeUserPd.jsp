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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscloginloc/changeUserPd.js"></script>
	<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>
</head>
<!-- 修改密码 -->
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal"  id="changeUserPdForm" >
                    <%-- <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 35%"> 工号： </label>
                        <div class="col-xs-3"  style="width: 45%"  >
                            <input  class="inputW160" type="text" id="operatorCode" name="operatorCode" value="${operatorCode}" readonly="readonly"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 35%"> 姓名： </label>
                        <div class="col-xs-3"  style="width: 45%">
                        	<input  class="inputW160" type="text" id="operatorName" name="operatorName" value="${operatorName}" readonly="readonly"/>
                        </div>

                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 35%"> 旧密码： </label>
                        <div class="col-xs-3"  style="width: 45%">
                       		<input  class="inputW160" type="password" id="password" name="password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 35%"> 新密码： </label>
                        <div class="col-xs-3"  style="width: 45%">
                            <input  class="inputW160" type="password" id="newPassword" name="newPassword"/>
                        </div>
                    </div>
                    
                    <div class="clearfix form-actions">
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
                    </div> --%>
                    
                    
                    <table style="margin: 5px auto;">
                    	<tr>
                    		<td>
                    			工 号  : 
                    		</td>
                    		<td>
                    			<input  class="inputW160" type="text" id="operatorCode" name="operatorCode" value="${operatorCode}" readonly="readonly"/>
                    		</td>
                    	</tr>
                    	<tr>
                    		<td>
                    			姓 名  : 
                    		</td>
                    		<td>
                    			<input  class="inputW160" type="text" id="operatorName" name="operatorName" value="${operatorName}" readonly="readonly"/>
                    		</td>
                    	</tr>
                    	<tr>
                    		<td>
                    			旧密码:  
                    		</td>
                    		<td>
                    			<input  class="inputW160" type="password" id="password" name="password"/>
                    		</td>
                    	</tr>
                    	<tr>
                    		<td>
                    			新密码: 
                    		</td>
                    		<td>
                    			<input  class="inputW160" type="password" id="newPassword" name="newPassword"/>
                    		</td>
                    	</tr>
                        		
                    </table>
                    <div class="clearfix form-actions">
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
                    
                </form>
			</div>
		</div>	
	</div>
</div>
 </body>
</html>
