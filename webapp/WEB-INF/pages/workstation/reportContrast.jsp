<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>报告对比</title>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
     <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link href="${ctx}/aris/statics/pages/css/index/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link href="${ctx}/aris/statics/pages/css/index/css/detail.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/inner.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/config.js"></script>
</head>
<style>
.reportBrow{}
.reportBrow textarea{ border:none;}
.reportBrow .reportBrow-left{background: #fff;border:1px solid #ddd; padding: 5px 0 10px 0;width:49.5%;float:left;}
.reportBrow .reportBrow-left a{color: #666; font-size: 14px;}
.reportBrow .reportBrow-left ul li.active{font-weight: 700;}
.nav>li>a{padding:10px 5px;}
.reportBrow .reportBrow-left h6{background:#fff; line-height:30px; font-weight:500; height:30px;font-size:14px;text-indent: 10px; font-weight: 700;}

.reportBrow .reportBrow-left dl{ height: 400px; overflow: auto;}
.reportBrow .reportBrow-left dl h5{background:#fff; line-height:30px; font-weight:500; height:30px;font-size:14px; border-bottom:1px solid #dbdbdb; text-indent: 30px;}
.reportBrow .reportBrow-left dl h5 a{ color:#333; display:block; text-decoration: none;}
.reportBrow .reportBrow-left dl h5 a:hover,.reportBrow .reportBrow-left dl h5 a.current{ color:#333; display:block ;background-color:#eee;;}
.reportBrow .reportBrow-left dl h5 i{ font-style:normal;}
.reportBrow .reportBrow-left dl dt{ margin-left:15px;}
.reportBrow .reportBrow-left dl dd{ margin-left:15px;}
.reportBrow .reportBrow-left dl dt a{ display:block; width:100%; border-bottom:1px solid #dbdbdb; color:#333;  height:34px; font-size:14px; line-height:34px; text-indent:30px; color:#666; font-weight: 500;}
.reportBrow .reportBrow-left dl dt a:hover,.reportBrow .reportBrow-left dl dt a.current{ background-color:#eee; }
.reportBrow .reportBrow-left dl dd a{ display:block; width:100%; border-bottom:1px solid #dbdbdb; color:#333; background-position: 30px 10px; height:34px; font-size:14px; line-height:34px; text-indent:40px; color:#666;}
.reportBrow .reportBrow-left dl dd a:hover,.reportBrow .reportBrow-left dl dd a.current{ background-color:#eee;}


.reportBrow .reportBrow-right{background: #fff;border:1px solid #ddd; padding: 5px 0 10px 0;width:49.5%;float:right;}
.reportBrow .reportBrow-right a{color: #666; font-size: 14px;}
.reportBrow .reportBrow-right ul li.active{font-weight: 700;}
.nav>li>a{padding:10px 5px;}
.reportBrow .reportBrow-right h6{background:#fff; line-height:30px; font-weight:500; height:30px;font-size:14px; text-indent: 10px; font-weight: 700;}

.reportBrow .reportBrow-right dl{ height: 400px; overflow: auto;}
.reportBrow .reportBrow-right dl h5{background:#fff; line-height:30px; font-weight:500; height:30px;font-size:14px; border-bottom:1px solid #dbdbdb; text-indent: 30px;}
.reportBrow .reportBrow-right dl h5 a{ color:#333; display:block; text-decoration: none;}
.reportBrow .reportBrow-right dl h5 a:hover,.reportBrow .reportBrow-right dl h5 a.current{ color:#333; display:block ;background-color:#eee;;}
.reportBrow .reportBrow-right dl h5 i{ font-style:normal;}
.reportBrow .reportBrow-right dl dt{ margin-right:15px;}
.reportBrow .reportBrow-right dl dd{ margin-right:15px;}
.reportBrow .reportBrow-right dl dt a{ display:block; width:100%; border-bottom:1px solid #dbdbdb; color:#333;  height:34px; font-size:14px; line-height:34px; text-indent:30px; color:#666; font-weight: 500;}
.reportBrow .reportBrow-right dl dt a:hover,.reportBrow .reportBrow-right dl dt a.current{ background-color:#eee; }
.reportBrow .reportBrow-right dl dd a{ display:block; width:100%; border-bottom:1px solid #dbdbdb; color:#333; background-position: 30px 10px; height:34px; font-size:14px; line-height:34px; text-indent:40px; color:#666;}
.reportBrow .reportBrow-right dl dd a:hover,.reportBrow .reportBrow-right dl dd a.current{ background-color:#eee;}
.reportBrow .reportBrow-center{}
.reportBrow .reportBrow-right .reportBrow-right-content{ background: #fff; }
.reportBrow .reportBrow-center .reportBrow-right-content h4{background:#fff; line-height:50px; font-weight:500; height:50px;font-size:16px; border-bottom:1px solid #dbdbdb; text-indent: 10px; font-weight: 700;}
.reportBrow .reportBrow-center .reportBrow-right-content h6{background:#fff; line-height:30px; font-weight:500; height:30px;font-size:14px; border-bottom:1px solid #dbdbdb; text-indent: 10px; font-weight: 700;}
 
</style>
<body>
<input type="hidden" id="studyInfoId" value="${studyInfoId }"/>
<div  id="bodyContent">
        <form class="form-horizontal" role="form" id="reportContrastForm">
        <!-- 审核成功状态 -->
        <input type="hidden" id="auditStatus"/> 
        <!-- 报告单状态 -->
        <div class="r_list" style="margin-left:24px;margin-right:12px;"> 
            
            <div class="reportBrow m-t-20">

                <div class="col-lg-6 reportBrow-left">
	                   <div class="reportBrow-left-content">
	                        <h6>检查所见<font color="red"><span id="title1"></span></font>
                        	<select id="reportChoose" name="reportChoose" style="width: 400px;" onchange="showReportDetail()">
                               
                            </select> 
	                        </h6> 
	                        <div class="report-show" id="editorExamCon">
	                             <textarea id="editorExamCon1" cols="5" rows="5" isRich="true" name="editorExamCon1" class="ckeditor" >
	                             
	                             </textarea> 
	                        </div>
	                        <h6>诊断意见</h6> 
	                        <div class="report-show" id="editorResult">
	                            <textarea id="reportResult" cols="5" rows="5" isRich="true" name="reportResult" class="ckeditor"> 
	                            
	                            </textarea>
	                        </div>
	                        <h6>备注</h6> 
	                        <div class="report-show" style=" border:1px solid #ddd; ">
	                            <textarea id="reportRemark" cols="50" rows="3" name="reportRemark" style="font-family: Arial, Verdana, sans-serif;font-size: 24px;color: #222;"></textarea>
	                        </div>
	                    </div>
	                   	<table width="100%" style="margin-top: 5px;">
	                   		<tr style="font-family: Arial, Verdana, sans-serif;font-size: 24px;color: #222;">
	                   			<td><span id="doctext">报告</span>医生：<span id="reportDoctorname1"></span></td>
	                   			<td><span id="doctext1">报告</span>日期：<span id="reportDatetime1"></span></td>
	                   		</tr>
	                    </table>
	                </div>
                
	                <div class="col-lg-6 reportBrow-right">
	                    <div class="reportBrow-right-content">
	                        <h6>检查所见<font color="red"><span id="title2"></span></font>
	                        	<select id="reportChoose1" name="reportChoose1" style="width: 400px;" onchange="showReportDetail()">
                               
                            	</select> 
	                        </h6> 
	                        <div class="report-show" id="editorExam1">
	                             <textarea id="reportExam1" cols="5" rows="5" isRich="true" name="reportExam1" class="ckeditor" >
	                             
	                             </textarea> 
	                        </div>
	                        <h6>诊断意见</h6> 
	                        <div class="report-show" id="editorResult1">
	                            <textarea id="reportResult1" cols="5" rows="5" isRich="true" name="reportResult1" class="ckeditor"> 
	                            
	                            </textarea>
	                        </div>
	                        <h6>备注</h6> 
	                        <div class="report-show" style=" border:1px solid #ddd; ">
	                            <textarea id="reportRemark1" cols="50" rows="3" name="reportRemark1" style="font-family: Arial, Verdana, sans-serif;font-size: 24px;color: #222;"></textarea>
	                        </div>
	                    </div>
	                   	<table width="100%" style="margin-top: 5px;">
	                   		<tr style="font-family: Arial, Verdana, sans-serif;font-size: 24px;color: #222;">
	                   			<td><span id="doctext2">审核</span>医生：<span id="reportDoctorname"></span></td>
	                   			<td><span id="doctext3">审核</span>日期：<span id="reportDatetime"></span></td>
	                   		</tr>
	                    </table>
	                </div>
                 </div>
            </div>
    <!--右侧内容 end-->
</div>
</form>
</div> 
</body>
<script type="text/javascript">

function removeHTMLTag(str) {
            str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
            str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
            //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
            str=str.replace(/&nbsp;/ig,'');//去掉&nbsp;
            str=str.replace(/\s/g,''); //将空格去掉
            return str;
}

$(document).ready(function() {
   getReportSelect();
   CKEDITOR.config.readOnly = true;

});

function compare_str(str1,str2){
        var res1 = "";
        var res2 = "";
        while(str1.length && str2.length){
            var arr = find_pos(str1,str2);
            if (arr && arr.length)
            {
                if (arr[0])
                {
                    res2 +=('<font color=\'pink\'>' + str2.substr(0,arr[0]) + '</font>');
                    str2 = str2.substr(arr[0]);
                }
                res1 +=(str1.substr(0,arr[1]));
                res2 +=(str2.substr(0,arr[1]));         

                str1 = str1.substr(arr[1]);
                str2 = str2.substr(arr[1]);

            }else{
                arr = find_max_pos(str1,str2); 
                tmp = find_match_pos(str1,str2);

                if (tmp.length)
                {               
                    if (arr[0]>tmp[0] && arr[1]>tmp[1])
                    {
                        arr = tmp;
                    }
                }
 
                if (arr!=undefined && arr.length)
                {               
                    res1 +=('<font color=\'red\'>'+ str1.substr(0,arr[0]) + '</font>');
                    res2 +=('<font color=\'red\'>' + str2.substr(0,arr[1]) + '</font>');
                    res1 +=(str1.substr(arr[0],arr[2]));
                    res2 +=(str2.substr(arr[1],arr[2]));

                    str1 = str1.substr(arr[0]+arr[2]);
                    str2 = str2.substr(arr[1]+arr[2]);
                }else{
                    res1 +=('<font color=\'blue\'>' + str1 + '</font>');
                    res2 +=('<font color=\'pink\'>' + str2 + '</font>');
                    str1 = '';
                    str2 = '';
                }
                
            }
        }
        if (str1.length)
        {
            res1 +=('<font color=\'blue\'>' + str1 + '</font>');
        }
        if (str2.length)
        {
            res2 +=('<font color=\'pink\'>' + str2 + '</font>');
        }
        CKEDITOR.instances.editorExamCon1.setData(res1); 
        CKEDITOR.instances.reportExam1.setData(res2);   
    }
function compare_str1(str1,str2){
        var res1 = "";
        var res2 = "";

        while(str1.length && str2.length){
            var arr = find_pos(str1,str2);
            if (arr && arr.length)
            {
                if (arr[0])
                {
                    res2 +=('<font color=\'pink\'>' + str2.substr(0,arr[0]) + '</font>');
                    str2 = str2.substr(arr[0]);
                }
                res1 +=(str1.substr(0,arr[1]));
                res2 +=(str2.substr(0,arr[1]));         

                str1 = str1.substr(arr[1]);
                str2 = str2.substr(arr[1]);

            }else{
                arr = find_max_pos(str1,str2); 
                tmp = find_match_pos(str1,str2);

                if (tmp.length)
                {               
                    if (arr[0]>tmp[0] && arr[1]>tmp[1])
                    {
                        arr = tmp;
                    }
                }
 
                if (arr!=undefined && arr.length)
                {               
                    res1 +=('<font color=\'red\'>' + str1.substr(0,arr[0]) + '</font>');
                    res2 +=('<font color=\'red\'>' + str2.substr(0,arr[1]) + '</font>');
                    res1 +=(str1.substr(arr[0],arr[2]));
                    res2 +=(str2.substr(arr[1],arr[2]));

                    str1 = str1.substr(arr[0]+arr[2]);
                    str2 = str2.substr(arr[1]+arr[2]);
                }else{
                    res1 +=('<font color=\'blue\'>' + str1 + '</font>');
                    res2 +=('<font color=\'pink\'>' + str2 + '</font>');
                    str1 = '';
                    str2 = '';
                }
                
            }
        }
        if (str1.length)
        {
            res1 +=('<font color=\'blue\'>' + str1 + '</font>');
        }
        if (str2.length)
        {
            res2 +=('<font color=\'pink\'>' + str2 + '</font>');
        }
        CKEDITOR.instances.reportResult.setData(res1); 
        CKEDITOR.instances.reportResult1.setData(res2);   
    }
    //从串2查找对串1前N个字符的最大匹配
    function find_pos(str1,str2){
        var len = 1, pos = 0;
        var ret;    
        
        while (pos>=0){  //在str2中找不到字符时返回-1退出循环
            var str = str1.substr(0,len);
            pos = str2.indexOf(str);
            if (pos!=-1)
            {
                ret = [];
                ret.push(pos,len);  //将匹配开始的位置和匹配长度压入数组
                len++;  //增加一个匹配长度
                if (len>str1.length){  //匹配长度大于str1长度时退出
                    break;
                }
                
            }else{
                break;
            }
        }

        return ret;
    }
    
    //从串2查找对串1从第N个字符开始前N个字符的首次匹配
    function find_match_pos(str1,str2,arr){
        var len =1 , pos1 = 0, pos2 = 0, _pos2;
        var ffind = false;
        var ret = [];
        
        if (arr!=undefined)
        {
            pos1 = arr[0];pos2 = arr[1];
        }
        while((pos1+len)<str1.length){
            var str = str1.substr(pos1,len);
            _pos2 = str2.indexOf(str);
            if (_pos2!=-1)
            {
                ffind = true;len++;pos2 = _pos2;
            }else{
                if (ffind)
                {
                    len--;break;
                }else{
                    pos1 += len;len = 1;
                }
            }           
        }

        if (ffind)
        {
            ret.push(pos1,pos2,len);
        }else{
            ret = [];
        }
        
        return ret;
    }
    
    //查找下一个最适匹配
    function find_next_pos(str1,str2){

    }
    
    //查找两个字串的字符数目最大匹配
    function find_max_pos(str1,str2){
        var ret,pos1,pos2,arr=null;
        var res, max = 0;
        do
        {
            ret = find_match_pos(str1,str2,arr);
            if (ret.length)
            {
                if (ret[2]>max)
                {
                    res = ret;
                    max = ret[2];
                }
                
                arr = new Array(ret[0]+1,ret[1]);
                 
            }
        }
        while (ret.length);

        return res;
    }

    function test(){
        //var eEditor = CKEDITOR.instances.editorExamCon1;   
    	//var rEditor = CKEDITOR.instances.editorResult; 
		//var eEditor1 = CKEDITOR.instances.editorExam1;   
    	//var rEditor1 = CKEDITOR.instances.editorResult1; 
        //compare_str(CKEDITOR.instances.editorExamCon1.getData(),CKEDITOR.instances.reportExam1.getData());
        //compare_str1(CKEDITOR.instances.reportResult.getData(),CKEDITOR.instances.reportResult1.getData());
    }

    //test();
    
  //  reportChoose
  function getReportSelect(){
    var flag = ""; 
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/studyReport/getReportSelect.ajax?studyInfoId="+$("#studyInfoId").val(),
        success: function (data) {
            var jsonSelectType = eval(data.ReportSelect);
            var report_Select = $("#reportChoose");
            var report_Select1 = $("#reportChoose1");
            report_Select.empty();
            report_Select1.empty();
            if(jsonSelectType.length>0){
	            for (var i = 0; i < jsonSelectType.length; i++) {
	                report_Select.append("<option value='" + jsonSelectType[i].reporthistoryid + "'>" + jsonSelectType[i].contrastsremark+ "</option>");
	                report_Select1.append("<option value='" + jsonSelectType[i].reporthistoryid + "'>" + jsonSelectType[i].contrastsremark + "</option>");
	                if(jsonSelectType[i].status=="VERIFY"){
	                	flag = jsonSelectType[i].reporthistoryid;
	                }
	            }
	            report_Select.select2();
            	report_Select1.select2();
            }else{
            	report_Select.append("<option value='-1'>无可对比报告</option>");
	            report_Select1.append("<option value='-1'>无可对比报告</option>");
	            report_Select.select2();
            	report_Select1.select2();
	            $("#reportChoose").val("-1").trigger('change');
                $("#reportChoose1").val("-1").trigger('change');
            }
            
        },
        complete:function(){       
	        $("#reportChoose").trigger('change');
            $("#reportChoose1").val(flag).trigger('change');
	   }
    });
  }
  
  function showReportDetail(){
     var str1="";
     var str2="";
     var str3="";
     var str4="";
  	 $.ajax({
	    type: "POST",
	    async: false,
	    url: GLOBAL.WEBROOT+"/studyReport/getReportDetail.ajax?reportHistoryId="+$("#reportChoose").val(),
	    success: function (data) {
	       str1 = data.reportExam;
	       str2 = data.reportResult;
		   $("#reportRemark").val(data.reportRemark);
		   if(data.status=="HAVERPT"){
		   	  $("#doctext").text("报告");
		   	  $("#doctext1").text("报告");
		   	  $("#reportDoctorname1").text(data.reportDoc);
		      $("#reportDatetime1").text(data.reportDateTime.substr(0,19));
		      $("#title1").text("（已录入报告）");
		   }else if(data.status=="VERIFY"){
		   	  $("#doctext").text("审核");
		   	  $("#doctext1").text("审核");
		   	  $("#reportDoctorname1").text(data.reportVerifyDoc);
		      $("#reportDatetime1").text(data.reportVerifyTime.substr(0,19));
		      $("#title1").text("（已审核报告）");
		   }else if(data.status=="UnCompleted"){
		   	  $("#doctext").text("报告");
		   	  $("#doctext1").text("报告");
		   	  $("#reportDoctorname1").text(data.reportVerifyDoc);
		      $("#reportDatetime1").text(data.reportVerifyTime.substr(0,19));
		      $("#title1").text("（报告挂起）");
		   }else{
		   	  $("#doctext").text("审核");
		   	  $("#doctext1").text("审核");
		   	  $("#reportDoctorname1").text(data.reportFinalDoc);
		      $("#reportDatetime1").text(data.reportFinalTime.substr(0,19));
		      $("#title1").text("（二次审核报告）");
		   }
	    }
	}); 
  
  	 $.ajax({
	    type: "POST",
	    async: false,
	    url: GLOBAL.WEBROOT+"/studyReport/getReportDetail.ajax?reportHistoryId="+$("#reportChoose1").val(),
	    success: function (data) {
		   //CKEDITOR.instances.reportExam1.setData(data.reportExam);
	       //CKEDITOR.instances.reportResult1.setData(data.reportResult);
	       str3 = data.reportExam;
	       str4 = data.reportResult;
		   $("#reportRemark1").val(data.reportRemark);
		   if(data.status=="HAVERPT"){
		   	  $("#doctext2").text("报告");
		   	  $("#doctext3").text("报告");
		   	  $("#reportDoctorname").text(data.reportDoc);
		      $("#reportDatetime").text(data.reportDateTime.substr(0,19));
		      $("#title2").text("（已录入报告）");
		   }else if(data.status=="VERIFY"){
		   	  $("#doctext2").text("审核");
		   	  $("#doctext3").text("审核");
		   	  $("#reportDoctorname").text(data.reportVerifyDoc);
		      $("#reportDatetime").text(data.reportVerifyTime.substr(0,19));
		      $("#title2").text("（已审核报告）");
		   }else if(data.status=="UnCompleted"){
		   	  $("#doctext").text("报告");
		   	  $("#doctext1").text("报告");
		   	  $("#reportDoctorname1").text(data.reportVerifyDoc);
		      $("#reportDatetime1").text(data.reportVerifyTime.substr(0,19));
		      $("#title2").text("（报告挂起）");
		   }else{
		   	  $("#doctext2").text("审核");
		   	  $("#doctext3").text("审核");
		   	  $("#reportDoctorname").text(data.reportFinalDoc);
		      $("#reportDatetime").text(data.reportFinalTime.substr(0,19));
		      $("#title2").text("（二次审核报告）");
		   }
	    }
	}); 
  	 
  	compare_str(removeHTMLTag(str1),removeHTMLTag(str3));
    compare_str1(removeHTMLTag(str2),removeHTMLTag(str4));
  }
    
</script>
</html>
