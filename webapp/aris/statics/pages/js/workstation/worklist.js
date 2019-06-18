//组织机构ID
var orgId = ""; 
var locId = ""; 
var flagmark = "";
var studyStatusArray ;
var ch_rowid; //当前选中行号
var ySelect = true;
$(function () {
	//初始化查询时间
    initDate();
    
	orgId = $("#orgId" , parent.document).val(); 	
	if(orgId == "" || orgId == null){
		setTimeout(function(){ 			 
			orgId = $("#orgId" , parent.document).val(); 
			//检过条件初始化
		    initDict();
			//工作列表记录查询
			//selectList();
		 },10000);
	}else{  
		//检过条件初始化
	    initDict();
		//工作列表记录查询
//	    setTimeout(function(){ 
//	    	selectList();
//	    },500);	
		
	} 
	
	//获取记录日期选择
	if($("#oldstarttime").val()!=""){
		$("#startTime").val($("#oldstarttime").val());
	}
	if($("#oldendtime").val()!=""){
		$("#endTime").val($("#oldendtime").val());
	}
	
	if($("#oldstudyAccnum").val()!=""){
		$("#studyAccnumber").val($("#oldstudyAccnum").val());
	}
	if($("#oldstudytype").val()!=""){
		$("#studyType").val($("#oldstudytype").val());
	}
//	if($("#consultRole").val()=="y"){
//		$("#studyType").val("1");
//	}
	if($("#oldCheckTimeMark").val()!="0"){
		$("#isDate").val("1");
		$("#isDate").attr("checked","true");
	}else{
		$("#isDate").val("0");
		$("#isDate").attr("checked",false);
	}
    //所有select嵌套css样式
    $("#locInfo").select2();  
    $("#qType").select2(); 
    $("#studyType").select2(); 
    $("#modalityId").select2(); 
    
    if($("#oldModalityId").val()!=""){
		$("#modalityId").select2('val',$("#oldModalityId").val());
	}
    if($("#oldqType").val()!=""){
    	$("#qType").select2('val',$("#oldqType").val());
	}
    
    if($("#oldqValue").val()!=""){
		$("#qValue").val($("#oldqValue").val());
	}
    
    //左侧菜单收缩 --已隐藏不点击   
    if(!$("#menuTree",parent.document).is(":hidden")){
    	console.log("触发左侧菜单收缩功能");
    	//parent.switchBarl();
    }
    
    //科室cookie设置
//    if($("#currentLocId").val()=="" || $("#currentLocId").val()=="undefined"){
//    	$("#locInfo option:first").prop("selected", 'selected');
//        $("#locInfo").select2();
//    }else{
//    	$("#locInfo").val($("#currentLocId").val());
//    	$("#locInfo").select2();
//    }   
//    setLocCookie();
 
//    reloadGrid(0,null);
    
    //定时get消息 
    setInterval(getMessage,60000);
    //定时关闭 
    setInterval(closeTip,75000);
    
   
    
});

//初始化查询进间
function initDate(){
	var n=new Date();
    $("#startTime").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
    $("#endTime").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
}
  
//initial query dict
function initDict() {
	
	var currentOrgId = $("#currentOrgId" , parent.document).val();
    //科室下拉列表
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT+"/workList/getLocInfo.ajax?orgId="+orgId,
        success: function (data) {
            var jsonStatus = eval(data.LOC_INFO);
            var statusSelect = $("#locInfo");
            statusSelect.empty(); 
            //statusSelect.append("<option value='-1'>请选择科室</option>");
            var currFlag = false;
            if(jsonStatus.length>0){
	            for (var i = 0; i < jsonStatus.length; i++) {
	                statusSelect.append("<option value='" + jsonStatus[i].locid + "'>" + jsonStatus[i].locdesc + "</option>");
	                if(jsonStatus[i].locid == $("#currentLocId").val()){
	                	currFlag = true;
	                }
	            }
	            if($("#currentLocId").val()=="" || $("#currentLocId").val()==undefined){
	            	$("#locInfo option:first").prop("selected", 'selected');
	                $("#locInfo").select2();
	            }else{
	            	if(currFlag){
	            		$("#locInfo").val($("#currentLocId").val());
	                	$("#locInfo").select2();
	            	}else{
	            		$("#locInfo option:first").prop("selected", 'selected');
	                	$("#locInfo").select2();
	            	}            	
	            } 
            }else{
            	layer.msg('操作员与登录科室未关联！', {title:'提示',time:9000000000,btn:['去配置'],shade: [0.5, '#f5f5f5'],icon: 2},function(){window.location.href = GLOBAL.WEBROOT+'/basecode/aiscLoginLocInit'; });
            }
            //根据科室获取检查设备类型
            $.ajax({
                type: "POST",
                async: false,
                url: GLOBAL.WEBROOT+"/workList/getEquimentByLocId.ajax?orgId="+orgId+"&locId="+$("#locInfo").val(),
                success: function (data) {
                    var jsonEqimentType = eval(data.MODALITY_TYPE);
                    var modalitySelect = $("#modalityId");
                    modalitySelect.empty(); 
                    modalitySelect.append("<option value='-1'>全部</option>");
                    var currFlag = false;
                    for (var i = 0; i < jsonEqimentType.length; i++) {
                    	modalitySelect.append("<option value='" + jsonEqimentType[i].itemno + "'>" + jsonEqimentType[i].itemname + "</option>");
                    }
//                    $("#modalityId option:first").prop("selected", 'selected');
//                	$("#modalityId").select2();
                }
            });
            setLocCookie();
            
          //检查状态下拉列表
            $.ajax({
                type: "POST",
                async: true,
                url: GLOBAL.WEBROOT+"/workList/getStudyStatus.ajax?orgId="+orgId+"&locId="+$("#locInfo").val(),
                success: function (data) { 
                    var jsonStatus = eval(data.STUDY_STATUS);
                    var statusSelect = $("#studyStatus");
                    statusSelect.empty();
                    statusSelect.append("<option value='-1'>请选择状态</option>");
                    var codes = $("#oldstudystutascode").val().split(",");
                    for (var i = 0; i < jsonStatus.length; i++) {
                    	var sta = false;
                    	if($("#oldstudystutascode").val()!=""){
                    		for(var cc=0;cc<codes.length;cc++){
                    			if(jsonStatus[i].studystatus==codes[cc]){
                        			sta = true;
                        		}
                    		}
                    	}
                    	if(sta){
                    		statusSelect.append("<option value='" + jsonStatus[i].studystatus + "' selected>" + jsonStatus[i].statusname + "</option>");
                    	}else{
                    		statusSelect.append("<option value='" + jsonStatus[i].studystatus + "'>" + jsonStatus[i].statusname + "</option>");
                    	}
                        
                    }
                    $("#studyStatus").select2();
                    reloadGrid(6,null,"");
                    selectList();
                   // showTotal();
                }
            });
        }
    });
    
    
}
//科室变更记录到cookie中，下次登录时，默认选中
function setLocCookie(){ 
//	reloadGrid(0,null);
	locId = "";
	locId = $("#locInfo option:selected").val();
	$("#currentLocId").val(locId);
	
	$.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT+"/workList/setLocCookie.ajax?locId="+locId,
        success: function (data) { 
        	$("#locType").val(data.locType);
        }
    });
}
//function initRecords(data){
//	var currentOrgId = $("#currentOrgId" , parent.document).val();	
//	$.ajax({
//		type: "POST",
//		async: true,
//		url:GLOBAL.WEBROOT +  '/workList/queryRecords.ajax?orgId='+currentOrgId,
//		dataType:'json',
//		data :data,
//		success:function(data){
//			 if(data){
//				$("#total").text(data.total);
//				$("#verifyNum").text(data.VERIFY);
//				$("#haverptNum").text(data.HAVERPT);
//				$("#arriveNum").text(data.ARRIVE);
//			 }
//		}
//	}); 
//}

var time = "";
function reloadGrid(type,statusCode,print,mark){
	if(type==0){
		flagmark = "";
	}
	if(mark=="1"){
		$("#studyStatus").select2("val",statusCode);
	}else{
		//$("#studyStatus").select2("val","");
	}
	var currentOrgId = $("#currentOrgId" , parent.document).val();	
//	$("#studyStatus").select2("val",statusCode);
	var studyStatuss = "";
    var data = $("#searchForm").serializeObject() ;
    if(statusCode!=null&&statusCode!=""){
		data.studystatusCode = "'"+statusCode+"'";
    }else{
    	var studyStatusArray = $("#studyStatus").select2("val");  
 	    for(var i=0;i<studyStatusArray.length;i++){
 	    	studyStatuss += studyStatuss == "" ? "'"+studyStatusArray[i]+"'":",'"+studyStatusArray[i]+"'";
 	    }
	    data.studystatusCode = studyStatuss;
    }
    //alert($("#studyStatus").select2("val"));
    var consultFlag = '0';
    if($("#consult").is(':checked')){
    	consultFlag = '1';
    }  
    data.consultFlag = consultFlag;
    if(flagmark!=""){
        data.messageFlag = flagmark;
    }else{
    	data.messageFlag = type;
        $("#messageFlag").val(type);
    }
    /*if(flagmark ==""){
    	consultFlag = '1';
    }else{
    	consultFlag = flagmark;
    }*/
    var isDate = "0";
    if($("#isDate").is(':checked')){
    	isDate = '1';
    }  
    data.isDate = isDate;
//    if(type != 0){
//    	data.startTime = "";
//    	data.endTime = ""; 
//    }
    data.studyType = $("#studyType").val();
    data.allStudyType = "";
    $("#studyType option").each(function() {    	
    	data.allStudyType += data.allStudyType == "" ? "'"+ $(this).attr("value") + "'" : ",'" + $(this).attr("value") + "'";
    });
    var addressUrl = "";
    if(print!=null&&print!=""){
    	data.reportIsprinted = print;
    	addressUrl = GLOBAL.WEBROOT+"/workList/queryPrintWorkList.ajax?orgId="+currentOrgId;
    }else{
    	addressUrl = GLOBAL.WEBROOT+"/workList/queryWorkList.ajax?orgId="+currentOrgId;
    }
	$("#worklist").jqGrid('setGridParam', {  
        url: addressUrl,
        datatype : 'json',
        //发送数据  
        postData : data,
        page : 1  ,
        pageSize:10 
    }).trigger('reloadGrid');  
//	initRecords(data);
	
	showTotal();
}

var titileArray = ['reportVerifydoctorid','检查号','状态','有图像','胶片打印','有报告','是否打印','病人类型','病人ID','patientGlobalid','modalityId','病人姓名','性别','年龄','生日','申请科室','住院号','床号','检查部位','检查项目',
 '报告医生','审核医生','二次审核医生','诊断意见','诊断结果','检查技师','登记时间','预约时间','检查开始时间','检查结束时间','报告时间','审核时间','申请机构','申请医生','会诊机构','会诊科室','检查类型','申请单号','是否加急'];
var titileArray1 = ['reportVerifydoctorid','检查号','状态','有图像','胶片打印','有报告','是否打印','病人类型','病人ID','医院病人ID','patientGlobalid','modalityId','病人姓名','性别','年龄','生日','申请科室','住院号','床号','检查部位','检查项目',
                   '报告医生','审核医生','二次审核医生','诊断意见','诊断结果','检查技师','登记时间','预约时间','检查开始时间','检查结束时间','报告时间','审核时间','申请机构','申请医生','会诊机构','会诊科室','检查类型','申请单号','是否加急'];

var dataArray = [	
                 {name: 'reportVerifydoctorid', index: 'reportVerifydoctorid', width:60,sortable: true,hidden:true},
                 {name: 'studyAccnumber', index: 'study_Accnumber', width:80,sortable: true
//                 	,formatter:function(param1,param2, recode){  
//                 		//recode.reportResult
//     	                var content = '<li title="' + param1 + '" class="tip">' + param1 + '</li>';  
//     	                return content;  
//                     }  
                 },            
                 {name: 'studystatusCode', index: 'studystatus_Code', width:60,sortable: true},            
                 {name: 'studyHaveimage', index: 'study_Haveimage', width:50,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==1){
                 		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_3.png" onclick="pacsView(2,this)"></img>';
                 	}else if(param1==2){
                 		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_4.png" onclick="pacsView(2,this)"></img>';
                 	}if(param1==3){
                 		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_5.png" onclick="pacsView(2,this)"></img>';
                 	}else{
                 		return '';
                 	} 
             	}},   
             	{name: 'studyFilmprinted', index: 'study_filmprinted', width:60,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==1){
                 		return '是';
                 	}else{
                 		return '否';
                 	} 
             	}}, 
                 {name: 'studyHavereport', index: 'study_Havereport', width:50,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==1){
                 		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_2.png" onclick="consultReport()"></img>';
                 	}else{
                 		return '';
                 	} 
             	}},
             	{name: 'reportIsprinted', index: 'report_Isprinted', width:60,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==0){
                 		return '否';
                 	}else if(param1==1){
                 		return '是';
                 	}else{
                 		return "否";
                 	}   
             	}},
             	{name: 'patienttypeCode', index: 'patienttype_Code', width:60,sortable: true},
             	{name: 'patientId', index: 'patient_Id', width:70,sortable: true},
                 {name: 'patientGlobalid', index: 'patient_Globalid', width:60,sortable: true,hidden:true},
                 {name: 'modalityId', index: 'modalityId', width:60,sortable: true,hidden:true},
                 {name: 'patientName', index: 'patient_Name', width:80,sortable: true},
                 {name: 'patientSex', index: 'patient_Sex', width:40,sortable: true},
                 {name: 'patientAge', index: 'patient_Age', width:40,sortable: true},            
                 {name: 'patientDob', index: 'patient_Dob', width:80,sortable: true},
                 {name: 'locDesc', index: 'loc_Desc', width:60,sortable: true},
                 {name: 'patientInpatientid', index: 'patient_Inpatientid', width:80,sortable: true},
                 {name: 'studyBedno', index: 'study_Bedno', width:50,sortable: true},
                 {name: 'bodyitem', index: 'bodyitem', width:80,sortable: true},
                 {name: 'studyItemdesc', index: 'study_Itemdesc', width:80,sortable: true},
                 //报告人
             	{name: 'reportDoctorname', index: 'report_Doctorname', width:80,sortable: true ,
                 	formatter: function (param1, param2, recode) {
             		if(param1=='a' || param1==null || param1=='undefined' || param1==''){
                 		return '';
                 	}else{
                 		return param1;
                 	} 
             	}},
             	//报告审核人
             	{name: 'reportVerifydoctorname', index: 'report_Verifydoctorname', width:80,sortable: true ,
                 	formatter: function (param1, param2, recode) {
             		if(param1=='a' || param1==null || param1=='undefined' || param1==''){
                 		return '';
                 	} else{
                 		return param1;
                 	}
             	}},
             	//终审人
             	{name: 'reportFinaldoctorname', index: 'report_Finaldoctorname', width:90,sortable: true ,
                 	formatter: function (param1, param2, recode) {
             		if(param1=='a' || param1==null || param1=='undefined' || param1==''){
                 		return '';
                 	} else{
                 		return param1;
                 	}
             	}},
             	{name: 'reportExam', index: 'report_Exam', width:60,sortable: true},
             	{name: 'reportResult', index: 'report_Result', width:60,sortable: true},
             	{name: 'studyDoctorname', index: 'study_Doctorname', width:80,sortable: true }, 
             	{name: 'cStudyOperationtime', index: 'c_Study_Operationtime', width:125,sortable: true},
             	{name: 'cStudyDatetime', index: 'c_Study_Datetime', width:125,sortable: true }, 
                 {name: 'cStudyStarttime', index: 'c_Study_Starttime', width:125,sortable: true},
             	{name: 'cStudyEndtime', index: 'c_Study_Endtime', width:125,sortable: true},
             	{name: 'reportDatetime', index: 'report_Datetime', width:125,sortable: true ,
                 	formatter: function (param1, param2, recode) {
                 	if(param1=='a' || param1==null || param1=='undefined' || param1==''){
                 		return '';
                 	}else{
                 		return param1;
                 	} 
             	}},
             	{name: 'reportVerifytime', index: 'report_Verifytime', width:125,sortable: true ,
                 	formatter: function (param1, param2, recode) {
                 	if(param1=='a' || param1==null || param1=='undefined' || param1==''){
                 		return '';
                 	}else{
                 		return param1;
                 	} 
             	}},
             	{name: 'orgName', index: 'org_Name', width:80,sortable: true},
                 {name: 'studyAppdocname', index: 'study_Appdocname', width:80,sortable: true},
                 {name: 'studyConsultorgName', index: 'study_Consultorg', width:60,sortable: true},
     			{name: 'studyConsultlocName', index: 'study_Consultloc', width:60,sortable: true},
             	{name: 'studyType', index: 'study_Type', width:70,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==0){
                 		return '检查医嘱';
                 	}else if(param1==1){
                 		return '远程诊断';
                 	}else if(param1==2){
                 		return '远程会诊';
                 	}else if(param1==3){
                 		return '转诊医嘱';
                 	}else{
                 		return param1;
                 	}   
             	}},
             	{name: 'studyinfoId', index: 'studyinfo_Id', width:60,sortable: true},
             	{name: 'isUrgent', index: 'is_Urgent', width:60,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==0){
                 		return '否';
                 	}else if(param1==1){
                 		return '是';
                 	}else{
                 		return param1;
                 	}   
             	}}
             	//终审人
//             	{name: 'reportFinaldoctorname', index: 'report_Finaldoctorname', width:120,sortable: true ,
//                 	formatter: function (param1, param2, recode) {
//             		if(param1=='a' || param1==null || param1=='undefined' || param1==''){
//                 		return '';
//                 	} else{
//                 		return param1;
//                 	}
//             	}}, 
                 
             ];

var dataArray1 = [	
                 {name: 'reportVerifydoctorid', index: 'reportVerifydoctorid', width:60,sortable: true,hidden:true},
                 {name: 'studyAccnumber', index: 'study_Accnumber', width:80,sortable: true
//                 	,formatter:function(param1,param2, recode){  
//                 		//recode.reportResult
//     	                var content = '<li title="' + param1 + '" class="tip">' + param1 + '</li>';  
//     	                return content;  
//                     }  
                 },            
                 {name: 'studystatusCode', index: 'studystatus_Code', width:60,sortable: true},            
                 {name: 'studyHaveimage', index: 'study_Haveimage', width:50,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==1){
                 		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_3.png" onclick="pacsView(2,this)"></img>';
                 	}else if(param1==2){
                 		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_4.png" onclick="pacsView(2,this)"></img>';
                 	}if(param1==3){
                 		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_5.png" onclick="pacsView(2,this)"></img>';
                 	}else{
                 		return '';
                 	} 
             	}},   
             	{name: 'studyFilmprinted', index: 'study_filmprinted', width:60,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==1){
                 		return '是';
                 	}else{
                 		return '否';
                 	} 
             	}}, 
                 {name: 'studyHavereport', index: 'study_Havereport', width:50,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==1){
                 		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_2.png" onclick="consultReport()"></img>';
                 	}else{
                 		return '';
                 	} 
             	}},
             	{name: 'reportIsprinted', index: 'report_Isprinted', width:60,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==0){
                 		return '否';
                 	}else if(param1==1){
                 		return '是';
                 	}else{
                 		return "否";
                 	}   
             	}},
             	 {name: 'patienttypeCode', index: 'patienttype_Code', width:60,sortable: true},
             	 {name: 'patientId', index: 'patient_Id', width:70,sortable: true},
             	 {name: 'oldPatientId', index: 'old_patient_Id', width:70,sortable: true},
                 {name: 'patientGlobalid', index: 'patient_Globalid', width:60,sortable: true,hidden:true},
                 {name: 'modalityId', index: 'modalityId', width:60,sortable: true,hidden:true},
                 {name: 'patientName', index: 'patient_Name', width:80,sortable: true},
                 {name: 'patientSex', index: 'patient_Sex', width:40,sortable: true},
                 {name: 'patientAge', index: 'patient_Age', width:40,sortable: true},            
                 {name: 'patientDob', index: 'patient_Dob', width:80,sortable: true},
                 {name: 'locDesc', index: 'loc_Desc', width:60,sortable: true},
                 {name: 'patientInpatientid', index: 'patient_Inpatientid', width:80,sortable: true},
                 {name: 'studyBedno', index: 'study_Bedno', width:50,sortable: true},
                 {name: 'bodyitem', index: 'bodyitem', width:80,sortable: true},
                 {name: 'studyItemdesc', index: 'study_Itemdesc', width:80,sortable: true},
                 //报告人
             	{name: 'reportDoctorname', index: 'report_Doctorname', width:80,sortable: true ,
                 	formatter: function (param1, param2, recode) {
             		if(param1=='a' || param1==null || param1=='undefined' || param1==''){
                 		return '';
                 	}else{
                 		return param1;
                 	} 
             	}},
             	//报告审核人
             	{name: 'reportVerifydoctorname', index: 'report_Verifydoctorname', width:80,sortable: true ,
                 	formatter: function (param1, param2, recode) {
             		if(param1=='a' || param1==null || param1=='undefined' || param1==''){
                 		return '';
                 	} else{
                 		return param1;
                 	}
             	}},
             	//终审人
             	{name: 'reportFinaldoctorname', index: 'report_Finaldoctorname', width:90,sortable: true ,
                 	formatter: function (param1, param2, recode) {
             		if(param1=='a' || param1==null || param1=='undefined' || param1==''){
                 		return '';
                 	} else{
                 		return param1;
                 	}
             	}},
             	{name: 'reportExam', index: 'report_Exam', width:60,sortable: true},
             	{name: 'reportResult', index: 'report_Result', width:60,sortable: true},
             	{name: 'studyDoctorname', index: 'study_Doctorname', width:80,sortable: true }, 
             	{name: 'cStudyOperationtime', index: 'c_Study_Operationtime', width:125,sortable: true},
             	{name: 'cStudyDatetime', index: 'c_Study_Datetime', width:125,sortable: true }, 
                 {name: 'cStudyStarttime', index: 'c_Study_Starttime', width:125,sortable: true},
             	{name: 'cStudyEndtime', index: 'c_Study_Endtime', width:125,sortable: true},
             	{name: 'reportDatetime', index: 'report_Datetime', width:125,sortable: true ,
                 	formatter: function (param1, param2, recode) {
                 	if(param1=='a' || param1==null || param1=='undefined' || param1==''){
                 		return '';
                 	}else{
                 		return param1;
                 	} 
             	}},
             	{name: 'reportVerifytime', index: 'report_Verifytime', width:125,sortable: true ,
                 	formatter: function (param1, param2, recode) {
                 	if(param1=='a' || param1==null || param1=='undefined' || param1==''){
                 		return '';
                 	}else{
                 		return param1;
                 	} 
             	}},
             	{name: 'orgName', index: 'org_Name', width:80,sortable: true},
                 {name: 'studyAppdocname', index: 'study_Appdocname', width:80,sortable: true},
                 {name: 'studyConsultorgName', index: 'study_Consultorg', width:60,sortable: true},
     			{name: 'studyConsultlocName', index: 'study_Consultloc', width:60,sortable: true},
             	{name: 'studyType', index: 'study_Type', width:70,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==0){
                 		return '检查医嘱';
                 	}else if(param1==1){
                 		return '远程诊断';
                 	}else if(param1==2){
                 		return '远程会诊';
                 	}else if(param1==3){
                 		return '转诊医嘱';
                 	}else{
                 		return param1;
                 	}   
             	}},
             	{name: 'studyinfoId', index: 'studyinfo_Id', width:60,sortable: true},
             	{name: 'isUrgent', index: 'is_Urgent', width:60,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1==0){
                 		return '否';
                 	}else if(param1==1){
                 		return '是';
                 	}else{
                 		return param1;
                 	}   
             	}}
             	//终审人
//             	{name: 'reportFinaldoctorname', index: 'report_Finaldoctorname', width:120,sortable: true ,
//                 	formatter: function (param1, param2, recode) {
//             		if(param1=='a' || param1==null || param1=='undefined' || param1==''){
//                 		return '';
//                 	} else{
//                 		return param1;
//                 	}
//             	}}, 
                 
             ];

function getTitleList(){
	if($("#YPatientIdFlag").val()=='1'){
		return titileArray1;
	}else{
		return titileArray;
	}
}

function getDataList(){
	if($("#YPatientIdFlag").val()=='1'){
		return dataArray1;
	}else{
		return dataArray;
	}
}
//查询列表
var currentOrgId = $("#currentOrgId" , parent.document).val();	
function selectList() {	 
	var data = $("#searchForm").serializeObject();  
	var studyStatuss = "";
	var studyStatusArray = $("#studyStatus").select2("val");  
	    for(var i=0;i<studyStatusArray.length;i++){
	    	studyStatuss += studyStatuss == "" ? "'"+studyStatusArray[i]+"'":",'"+studyStatusArray[i]+"'";
	    }
    data.studystatusCode = studyStatuss;
    if(flagmark.length>0){
        data.messageFlag = flagmark;
    }
	data.isDate = $("#isDate").val();
	var grid_selector = "#worklist";
    var pager_selector = "#worklist_pager";
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/workList/queryWorkList.ajax?orgId="+currentOrgId,
        mtype : "post",  
        async: true,
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        sortable:true,
        colNames: getTitleList(),
        colModel: getDataList(),
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: true,  //checkbox
        multiboxonly: true,  //取掉这个，点击选中，再点击取消选中了。
        pager: pager_selector,
        altRows: true, 
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);            
//            var re_records = $("#worklist").getGridParam('records');
//        	if(re_records < 8 )
//        	{
//        		for(var i=0,j=8-re_records;i<=j;i++){
//        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
//        			$("#worklist").jqGrid("addRowData",(re_records+1),{ '检查号':"",'状态':"",'有图像':"",'有报告':"",'病人编号':"",'病人ID':"",'病人姓名':"",'年龄':"",'性别':"",'生日':"",'检查项目':"",'申请机构':"",
//                        '申请科室':"",'申请医生':"",'检查日期':"",'登记时间':"",'检查时间':"",'报告时间':"",'检查技师':"",'报告医生':"",'审核医生':""},"");
//        			 
//        		}
//        	}
//            initRecords(data);
        },
        gridComplete: function() {
        	//加急颜色
        	afterCompleteFunction();
        },
        ondblClickRow: function (aRowids, status) {
        	f1(pacsView(1,''));
        },
        onSelectAll: function (aRowids, status) {
        }, 
        onSelectRow: function (rowid, status) {      
        	
        }, 
        //实现单选
        beforeSelectRow: function (rowid, event) {         	
//        	$(this).jqGrid("resetSelection");  
//        	 $("#worklist").jqGrid('resetSelection');  
            return true;    
        },
        onRightClickRow: function (rowid, iRow,iCol,e) {        
        	//alert(iRow);
        },
        //行宽自动
        autowidth: true
    });
}

function f1(callback){
	window.setTimeout(writeReprot(2),1);
}

function showTotal(){
	var stucodevalue = "";
	var studyStatusArray = $("#studyStatus").select2("val");  
    for(var i=0;i<studyStatusArray.length;i++){
    	stucodevalue += stucodevalue == "" ? "'"+studyStatusArray[i]+"'":",'"+studyStatusArray[i]+"'";
    }
    var isDate = "0";
    if($("#isDate").is(':checked')){
    	isDate = '1';
    }  
    var messageFlages = flagmark;
    var setdata = {"modalityId":$("#modalityId").val(),"isDate":isDate,"studystatusCode":stucodevalue,"locId":$("#locInfo").val(),"qType":$("#qType").val(),"qValue":$("#qValue").val(),"studyAccnumber":$("#studyAccnumber").val(),"studyType":$("#studyType").val(),"startTime":$("#startTime").val(),"endTime":$("#endTime").val(),"messageFlag":messageFlages};
    //alert($.toJSON(setdata));
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/workList/queryRecords.ajax?orgId='+currentOrgId,
		dataType:'json',
		data :setdata,
		success:function(data){
			 if(data){
				 var aaa= data.VERIFY==undefined?0:data.VERIFY;
				 var bbb= data.HAVERPT==undefined?0:data.HAVERPT;
				 var ccc= data.ARRIVE==undefined?0:data.ARRIVE;
				 var ddd= data.PRINT==undefined?0:data.PRINT;
				 var eee =data.APPConsult==undefined?0:data.APPConsult;
				 var type = "0";
				$("#total").text("("+data.total+")");
				if(messageFlages=="3"){
					$("#total").attr("onclick","reloadGrid(3,'','','0')");
					$("#verifyNum").attr("onclick","reloadGrid(3,'VERIFY','','1')");
					$("#haverptNum").attr("onclick","reloadGrid(3,'HAVERPT','','1')");
					$("#arriveNum").attr("onclick","reloadGrid(3,'ARRIVE','','1')");
					$("#hzNum").attr("onclick","reloadGrid(3,'APPConsult','','1')");
					$("#printNum").attr("onclick","reloadGrid(3,'','1','0')");
					$("#verifyNum").text("("+aaa+")");
					$("#haverptNum").text("("+bbb+")");
					$("#arriveNum").text("("+ccc+")");
					$("#printNum").text("("+ddd+")");
					$("#hzNum").text("("+eee+")");
				}if(messageFlages=="5"){
					$("#total").attr("onclick","reloadGrid(3,'','','0')");
					$("#verifyNum").attr("onclick","reloadGrid(3,'VERIFY','','1')");
					$("#haverptNum").attr("onclick","reloadGrid(3,'HAVERPT','','1')");
					$("#arriveNum").attr("onclick","reloadGrid(3,'ARRIVE','','1')");
					$("#hzNum").attr("onclick","reloadGrid(3,'APPConsult','','1')");
					$("#printNum").attr("onclick","reloadGrid(3,'','1','0')");
					$("#verifyNum").text("("+aaa+")");
					$("#haverptNum").text("("+bbb+")");
					$("#arriveNum").text("("+ccc+")");
					$("#printNum").text("("+ddd+")");
					$("#hzNum").text("("+eee+")");
				}
				else{
					$("#total").attr("onclick","reloadGrid(6,'','','0')");
					$("#verifyNum").attr("onclick","reloadGrid(6,'VERIFY','','1')");
					$("#haverptNum").attr("onclick","reloadGrid(6,'HAVERPT','','1')");
					$("#arriveNum").attr("onclick","reloadGrid(6,'ARRIVE','','1')");
					$("#hzNum").attr("onclick","reloadGrid(6,'APPConsult','','1')");
					$("#printNum").attr("onclick","reloadGrid(6,'','1','0')");
					$("#verifyNum").text("("+aaa+")");
					$("#haverptNum").text("("+bbb+")");
					$("#arriveNum").text("("+ccc+")");
					$("#printNum").text("("+ddd+")");
					$("#hzNum").text("("+eee+")");
				}
				
			 }
		}
	}); 
}

function afterCompleteFunction(){
	//获取列表数据
	var ids = $("#worklist").jqGrid("getDataIDs");
	var rowDatas = $("#worklist").jqGrid("getRowData");
	for(var i=0;i<rowDatas.length;i++){
		var rowData = rowDatas[i];
		var isUrgent = rowData.isUrgent; 
		if(isUrgent=="是"||rowData.patienttypeCode=="急诊"){
			if(rowData.studystatusCode=="已审核"){
				
			}else{
				$("#"+ids[i]+ " td").css("background-color","#FF4500");
			}
		}else if(rowData.studystatusCode=="已录入报告"){
			$("#"+ids[i]+ " td").css("background-color","#BCD2EE");
		}else if(rowData.studystatusCode=="已登记"||rowData.patienttypeCode=="已检查"){
			$("#"+ids[i]+ " td").css("background-color","#D1EEEE");
		}else if(rowData.studystatusCode=="报告未完成"){
			$("#"+ids[i]+ " td").css("background-color","#FFFACD");
		}
	}	
	return true;
} 

//浏览
function showGrid(){
	var gr = $("#worklist").jqGrid("getGridParam","selarrrow");
	if (gr.length == 0) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	if (gr.length > 1) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
//	if(ch_rowid == null || ch_rowid == ""){
//		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
//		return;
//	}
	var rowData = $("#worklist").jqGrid('getRowData',gr[0]);
	//alert(rowData.studyinfoId);
	layer.open({
        type: 2,
        title:"检查详情",
        area: ['800px', '500px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/workList/getWorkListDetail.html?studyinfoId="+rowData.studyinfoId
    });
}

//会诊开始
function consultReport(){
	writeReprot(1);
	
	pacsView(1,'');
	//在线会诊，打开视频会议系统
//	if(){
//		
//	}
}

//书写报告  
function writeReprot(rtype){
	var gr = $("#worklist").jqGrid("getGridParam","selarrrow");
	if (gr.length == 0 || gr.length > 1) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	var rowData = $("#worklist").jqGrid('getRowData',gr[0]);
	//TODO 校验报告是否锁定
	var isLockFlag = '0';
    $.ajax({
        type: "POST",
        async: false,
        url:GLOBAL.WEBROOT +  '/studyReport/checkReportIsLock.ajax?studyInfoId='+rowData.studyinfoId,
        dataType:'json',
        // data :setdata,
        success:function(data){
            if(data.ERRCODE=='1'){
                layer.alert(data.ERRINFO, {icon: 5,shadeClose: true});
                isLockFlag = '1'
                return;
            }
        }
    });

	if(isLockFlag=='1'){
        return;
	}

	if (rowData.studystatusCode=="取消预约") {
		layer.alert('报告已取消，不允许书写报告！', {icon: 5,shadeClose: true});
		return;
	}
	if (rowData.studystatusCode=="拒绝申请") {
		layer.alert('申请已拒绝，不允许书写报告！', {icon: 5,shadeClose: true});
		return;
	}
	//接收方会诊申请
	if(rowData.studyType=="远程诊断"&&$("#locType").val()=="C"){
		if($("#hzisFlag").val()==1){
			if(rowData.studystatusCode=="审核通过"||rowData.studystatusCode=="已录入报告"||rowData.studystatusCode=="报告未完成"||rowData.studystatusCode=="会诊开始"||rowData.studystatusCode=="会诊结束"){
				window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+locId+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
			}else if(rowData.studystatusCode=="已审核"&&rowData.reportIsprinted=="是"){
				layer.alert('报告已打印，不能再次审核！', {icon: 5,shadeClose: true});
				return ;
			}else if(rowData.studystatusCode=="已审核"&&rowData.reportIsprinted=="否"){
				window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+locId+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
			}else{
				layer.alert('会诊申请报告需要审核！', {icon: 5,shadeClose: true});
				return ;
			}
		}else{
			window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+locId+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
		}
	}else if(rowData.studyType=="远程诊断"&&$("#locType").val()!="C"){//申请方会诊申请
		if($("#hzsqRole").val()=="y"&&$("#diagnoseRole").val()!="y"){//申请方会诊申请按钮
			if($("#hzisFlag").val()==1&&(rowData.studystatusCode!="已审核"&&rowData.studystatusCode!="会诊结束")){
				layer.alert('会诊申请报告需要审核！', {icon: 5,shadeClose: true});
				return ;
			}else if($("#hzisFlag").val()==0&&rowData.studystatusCode!="已审核"){
				layer.alert('会诊申请报告需要审核配置！', {icon: 5,shadeClose: true});
				return ;
			}else if($("#hzisFlag").val()==1&&(rowData.studystatusCode=="已审核"||rowData.studystatusCode=="会诊结束")){
				window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+locId+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
			}else if($("#hzisFlag").val()==0&&(rowData.studystatusCode=="已审核"||rowData.studystatusCode=="会诊结束")){
				window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+locId+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
			}
		}else if($("#hzsqRole").val()!="y"&&$("#diagnoseRole").val()=="y"){//申请方登记展现会诊
			window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+locId+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
		}else{
			window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+locId+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
		}
	}else{
		window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+locId+"&startTime="+$("#startTime").val()+"&endTime="+$("#endTime").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
	}
	
}

//登记
function patientReg(){
	var gr = $("#worklist").jqGrid("getGridParam","selarrrow");
	 
	if (gr.length > 1) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	var rowData = $("#worklist").jqGrid('getRowData',gr[0]);
	var locId = $("#locInfo option:selected").val(); 
	if (rowData.studystatusCode=="取消预约") {
		layer.alert('报告已取消，不允许再次登记！', {icon: 5,shadeClose: true});
		return;
	}
	if (rowData.studystatusCode=="会诊开始") {
		layer.alert('会诊已开始，不允许操作！', {icon: 5,shadeClose: true});
		return;
	}
//	if (rowData.studystatusCode=="已审核") {
//		layer.alert('报告已审核，不允许操作！', {icon: 5,shadeClose: true});
//		return;
//	}
//	var consultFlag = '0';
//    if($('#consult').is(':checked')){
//    	consultFlag = '1';
//    }
	var diagnoseFlag = $("#diagnoseRole").val();
	window.location.href = GLOBAL.WEBROOT + "/patientReg/patientRegPageinit.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&orgId="+orgId+"&locId="+locId+"&diagnoseFlag="+diagnoseFlag+"&again=n";

}

//远程会诊登记
function consultPatientReg(){
	var gr = $("#worklist").jqGrid("getGridParam","selarrrow");
	 
	if (gr.length > 1) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	var rowData = $("#worklist").jqGrid('getRowData',gr[0]);
	var locId = $("#locInfo option:selected").val(); 
	if (rowData.studystatusCode=="取消预约") {
		layer.alert('报告已取消，不允许再次登记！', {icon: 5,shadeClose: true});
		return;
	}
	if (rowData.studystatusCode=="已审核") {
		layer.alert('报告已审核，不允许操作！', {icon: 5,shadeClose: true});
		return;
	} 
	var diagnoseFlag = $("#diagnoseRole").val();
	window.location.href = GLOBAL.WEBROOT + "/patientReg/consultPatientRegInit.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&orgId="+orgId+"&locId="+locId+"&diagnoseFlag="+$("#diagnoseRole").val()+"&hzsqFlag="+$("#hzsqRole").val();

} 

//重置
function reset(){
	$("#qType").select2("val","1");
	$("#studyType").select2("val","-1");
	$("#qValue").val("");
	$("#studyAccnumber").val("");
	
	$("#consult").attr("checked",false);
	$("#studyStatus").select2("val","");
	if($("#isDate").is(':checked')){
		$("#startTime").val("");
		$("#endTime").val(""); 
    }  
}
//影像质控
function imageQryControl(){
	window.location.href = GLOBAL.WEBROOT + "/workList/imageControl.html";
}

function videoConsultation(){
	var user = eval("(" + parent.$('#user').val() + ")");
	layer.open({
        type: 2,
        title:"会议室列表",
        area: ['800px', '500px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/basecode/boardroomList.html?userCode="+user.operatorCode
    });
}

//影像资料按钮
function imageData(type,obj){
	var gr = $("#worklist").jqGrid("getGridParam","selarrrow");
	 
	if (gr.length > 1 || gr.length == 0) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	var rowData ;
	if(type==1){
		rowData = $("#worklist").jqGrid('getRowData',gr[0]); 
		if (rowData.studystatusCode=="取消预约") {
			layer.alert('报告已取消，不允许获取影像资料！', {icon: 5,shadeClose: true});
			return;
		}
	}else{
		var rowid = $(obj).closest('tr').attr("id");
		rowData = $("#worklist").jqGrid('getRowData',rowid);
		if (rowData.studystatusCode=="取消预约") {
			layer.alert('报告已取消，不允许获取影像资料！', {icon: 5,shadeClose: true});
			return;
		}
	}
	
	var patientId = rowData.patientId;   //病人ID
    var name = rowData.patientName; //姓名
    var sex = rowData.patientSex; //性别
    var age = rowData.patientAge; //年龄
    var accessnumber = rowData.studyAccnumber; //检查号
    var jcxm = rowData.studyItemdesc; //检查项目
    
	 var filespec = "C:\\Windows\\AIPACS\\CaptureImage\\CaptureImage.exe";
     var fso = new ActiveXObject("Scripting.FileSystemObject");
     if (fso.FileExists(filespec))
     {
         var commandline = "2^"+patientId+"^"+name+"^"+sex+"^"+age+"^"+accessnumber+"^"+jcxm+"";
         filespec = filespec + " " + commandline;
         //alert(filespec);

         var wsh = new ActiveXObject('WSCript.shell');
         wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1001","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1004","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1200","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1201","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1405","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\2201","0","REG_DWORD");  
         wsh.run(filespec);

     }
     else
     {
         location.href = GLOBAL.WEBROOT+"/aris/statics/files/setup.exe";
     }	 	
}

function lookAllView(type){
	var gr = $("#worklist").jqGrid("getGridParam","selarrrow");
	 
	if (gr.length > 1 || gr.length == 0) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	var rowData ;
	if(type==1){
		rowData = $("#worklist").jqGrid('getRowData',gr[0]); 
		if (rowData.studystatusCode=="取消预约") {
			layer.alert('报告已取消，不允许调阅影像！', {icon: 5,shadeClose: true});
			return;
		}
	}else{
		var rowid = $(obj).closest('tr').attr("id");
		rowData = $("#worklist").jqGrid('getRowData',rowid);
		if (rowData.studystatusCode=="取消预约") {
			layer.alert('报告已取消，不允许调阅影像！', {icon: 5,shadeClose: true});
			return;
		}
	}
	
	var patientid = rowData.patientId;   //"06009580";
    var accessnumber = rowData.studyAccnumber; //"345959";
    
	 var filespec = "C:\\Windows\\AIPACS\\AIPACS.Desktop.ImageViewerManager.exe";
     var fso = new ActiveXObject("Scripting.FileSystemObject");
     if (fso.FileExists(filespec))
     {
         var commandline = "&"+patientid+"&&&&&&&";
         filespec = filespec + " " + commandline;
//         alert(filespec);

         var wsh = new ActiveXObject('WSCript.shell');
         wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1001","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1004","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1200","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1201","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1405","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\2201","0","REG_DWORD");  
         wsh.run(filespec);

     }
     else
     {
         location.href = GLOBAL.WEBROOT+"/aris/statics/files/setup.exe";
     }	
}

//影像调阅
function pacsView(type,obj){
	var gr = $("#worklist").jqGrid("getGridParam","selarrrow");
	 
	if (gr.length > 1 || gr.length == 0) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	var rowData ;
	if(type==1){
		rowData = $("#worklist").jqGrid('getRowData',gr[0]); 
		if (rowData.studystatusCode=="取消预约") {
			layer.alert('报告已取消，不允许调阅影像！', {icon: 5,shadeClose: true});
			return;
		}
	}else{
		var rowid = $(obj).closest('tr').attr("id");
		rowData = $("#worklist").jqGrid('getRowData',rowid);
		if (rowData.studystatusCode=="取消预约") {
			layer.alert('报告已取消，不允许调阅影像！', {icon: 5,shadeClose: true});
			return;
		}
	}
	
	var patientid = rowData.patientId;   //"06009580";
    var accessnumber = rowData.studyAccnumber; //"345959";
    
	 var filespec = "C:\\Windows\\AIPACS\\AIPACS.Desktop.ImageViewerManager.exe";
     var fso = new ActiveXObject("Scripting.FileSystemObject");
     if (fso.FileExists(filespec))
     {
         var commandline = accessnumber+"&"+patientid+"&&&&I&&&";
         filespec = filespec + " " + commandline;
         //alert(filespec);

         var wsh = new ActiveXObject('WSCript.shell');
         wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1001","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1004","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1200","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1201","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1405","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\2201","0","REG_DWORD");  
         wsh.run(filespec);

     }
     else
     {
         location.href = GLOBAL.WEBROOT+"/aris/statics/files/setup.exe";
     }	 	 
}


function readCard(){
	layer.alert('功能暂未开放！', {icon: 5,shadeClose: true});
}

//报告阅览
function reportView(obj){
	var rowid = $(obj).closest('tr').attr("id");
	var rowData = $("#worklist").jqGrid('getRowData',rowid);
	var locId = $("#locInfo").val();
	window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype=0"+"&locId="+locId+"&isReadonly=y&modalityId="+rowData.modalityId;
	 
}

//取消登记
function patientRegCancel(){
	var gr = $("#worklist").jqGrid("getGridParam","selarrrow");
	if (gr.length != 1) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	var rowData = $("#worklist").jqGrid('getRowData',gr[0]);
	if(rowData.studystatusCode!="已登记"){
		layer.alert('已登记病人才能取消！', {icon: 5,shadeClose: true});
		return;
	}
	layer.open({
        type: 2,
        title:"取消登记",
        area: ['800px', '300px'],
        fix: false,
        maxmin: true,
        content: GLOBAL.WEBROOT + "/patientReg/patientRegCancel.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid
    });
}
 

//弹窗关闭
function closeTip(){ 
    $("#notice-tip").css({"z-index":-10});
	$("#notice-tip").hide();
} 


//定时扫表，查询远程会诊、远程诊断记录
function getMessage(){
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/workList/getMessage.ajax?orgId='+orgId+"&locId="+$("#locInfo option:selected").val()+"&userId="+$("#userId").val(),
		dataType:'json',
		success:function(data){
			 if(data){
				var diagnoseTotal = data.diagnoseTotal;
//				var consultTotal = data.consultTotal;
				var diagnoseVerifyTotal = data.diagnoseVerifyTotal;
//				var consultVerifyTotal = data.consultVerifyTotal;
				if(diagnoseTotal > 0 || diagnoseVerifyTotal > 0 ){
					 var html="<h5>";
				     html+="<img src='"+GLOBAL.WEBROOT+"/aris/statics/pages/css/index/images/prompt-icon.png' alt='提示' width='50'  onclick=''/> <br />";
				     if(diagnoseTotal > 0){
					     html+="<a href='javascript:void(0);' onclick='getDiagnoseInfo()' id='dNum' style='color:#f60;'>您有<span class='ret_span'>"+diagnoseTotal+"</span> 条会诊申请单，请及时处理!</a> ";
					     html+="<br/>";
				     }
				    /* if(consultTotal > 0){
					     html+="<a href='javascript:void(0);' onclick='getConsultInfo()' id='cNum' style='color:#f60;'>您有<span class='ret_span'>"+consultTotal+"</span> 条会诊申请，请及时处理!</a> ";
					     html+="<br/>";
				     }*/
				     if(diagnoseVerifyTotal > 0){
					     html+="<a href='javascript:void(0);' onclick='getDiagnoseVerifyInfo()' id='cNum' style='color:#f60;'>您有<span class='ret_span'>"+diagnoseVerifyTotal+"</span> 条会诊申请已审核，请注意查看!</a> ";
					     html+="<br/>";
				     }
				    /* if(consultVerifyTotal > 0){
					     html+="<a href='javascript:void(0);' onclick='getConsultVerifyInfo()' id='cNum' style='color:#f60;'>您有<span class='ret_span'>"+consultVerifyTotal+"</span> 条会诊申请已审核，请注意查看!</a> ";
					     html+="<br/>";
				     }*/
				     html+="</h5>";
				     html+="<div class='cl'></div>";
				     $("#floatContent").html("");
					 $("#floatContent").append(html);
					 $("#notice-tip").css("z-index",100);
					 $("#notice-tip").show();
				}
			 }
		}
	}); 
}
function getDiagnoseInfo(){
	//日期时间不选中
	$("#isDate").attr("checked",false);
	//检查方式选中 远程诊断
	$("#studyType").val("1");
	$("#studyType").select2();
	flagmark = "5";
	//修改报告状态为已阅读
	//setTimeout("saveReportStutus()",12000);
	reloadGrid(1,null);
}
function getConsultInfo(){
	//检查方式选中 远程会诊
	$("#studyType").val("2");
	$("#studyType").select2();
	reloadGrid(2,null);
}
function getDiagnoseVerifyInfo(){
	//日期时间不选中
	$("#isDate").attr("checked",false);
	//检查方式选中 远程会诊
	$("#studyType").val("1");
	$("#studyType").select2();
	flagmark = "3";
	//修改报告状态为已阅读
	//setTimeout("saveReportStutusVerrify()",12000);
	reloadGrid(3,null);
}
function getConsultVerifyInfo(){
	//检查方式选中 远程诊断
	$("#studyType").val("2");
	$("#studyType").select2();
	reloadGrid(4,null);
}
//修改申请报告为已阅读
function saveReportStutus(){ 
	$.ajax({
        type: "POST",
        async: true,
		url:GLOBAL.WEBROOT +  '/workList/saveReprtStatus.ajax?orgId='+orgId+"&locId="+$("#locInfo option:selected").val()+"&userId="+$("#userId").val(),
        success: function (data) { 
            
        }
    });
}
//修改已审核报告为已阅读
function saveReportStutusVerrify(){ 
	$.ajax({
        type: "POST",
        async: true,
		url:GLOBAL.WEBROOT +  '/workList/saveReportStutusVerrify.ajax?orgId='+orgId+"&locId="+$("#locInfo option:selected").val()+"&userId="+$("#userId").val(),
        success: function (data) { 
            
        }
    });
}



 