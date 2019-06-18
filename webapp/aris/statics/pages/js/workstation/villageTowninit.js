$(function () {
	//初始化查询时间等信息
    initDate();
    orgId = $("#orgId" , parent.document).val(); 	
    //$("#orgId").val(orgId);
	selectList();
	refreshGridDownRight("");
	//$('#saveBtn').addClass('disabled');
	//$('#printBtn').addClass('disabled');
	$("select").select2();
//	$("#myul li div").hide();
//	$("#myul li span").addClass("hand");
//	$("#myul li span").click(function () {
//	   $(this).toggleClass("current");
////	   $(this).parent().siblings().find("span").removeClass("current");
//	   $(this).parent().find("div.hid").slideToggle("fast");
//	   $(this).parent().siblings().find("div").slideUp("fast");
//	 });
	var trcp = $("#configTab").find("tr");
	var html = "<tr tag='studyItem'>    "+
			"		            <td width='20%' align = 'center'>           "+
			"		                <div  name='studyitemDesc_1' style='width:180px;' onclick='setNewBigbodyPart(this,\"\")'>        "+
			"		                </div>"+
			"		                <input type='hidden' name='studyitemDescClone_1' id='studyitemDescClone_1' />		   "+             
			"		            </td>  "+
			"		             <td width='46%'>"+
			"<input id='qryInput_1' name='qryInput_1' type='text' value='搜索' onFocus='if(value==defaultValue){value=\"\";this.style.color=\"#000\"}' onBlur='if(!value){value=defaultValue; this.style.color=\"#999\"};qryinitBodyTree(this)' style='color:#999;width:50px' />"+
			"  		<input id='bodypartNames_1' type='text' readonly=readonly class='select' style='width: 150px;' name='bodypartNames_1'  />"+
			"     	<input id='bodypartCodes_1' type='hidden' readonly name='bodypartCodes_1'/>"+
			"                    </td>  "+
			"		            <td width='2%' align = 'center'>"+
			"		                 <input id='studyitemNumber_1' name='studyitemNumber_1' class='inputW120' readonly onkeyup='value=value.replace(/[^\\.]{2,}/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
			"		            </td>        "+
			"		            <td width='2%' align = 'center'align = 'center'>"+
			"		                 <input id='studyitemPrice_1' name='studyitemPrice_1' class='inputW120' readonly onkeyup='value=value.replace(/[^\d\.]/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
			"		            </td> "+
			"		            <td width='30%' class='c'>   "+
			"		                <button  id='add' type='button' onclick='addStudyItem()'>增加</button>"+
			"		                <button  id='deleBtn_1' type='button' onclick='delStudyItem(this)'>删除</button>"+
			"		            </td>"+
			"</tr>";
	trcp.after(html);
	initSelect2forDiv($("div[name='studyitemDesc_1']"));
	
	//校验+提交
    $('#patientRegForm').validate({
    	  rules: {  
            patientName: {
            	maxlength: 16
            },
            studyApplocid: {
                required: true
            },
            patienttypeCode: {
                required: true
            },
            patientMobile:{
            	mobile:true
            },  
            patientIdnumber:{
            	isIdCardNo:true
            },  
            patienttypeCode:{
            	checkSelectVal:true
            },
            patientSex:{
            	checkSelectVal:true
            },
            patientName:{
            	required: true
            },
            periodDate:{
            	required: true
            },
            patientAgeShow:{
            	required: true
            },
            studyWard:{
            	checkStudyWard:true
            },
            equipmentId:{
            	checkSelectVal:true
            }
        },
        messages: {  
            studyApplocid:{
            	required: " * 非空！"
            },
            patienttypeCode:{
            	required: " * 非空！"
            },
            patientAgeShow:{
            	required: " * 非空！"
            },
            studyitemNumber:{
            	required: "只能输入1位！"
            },
            patientName:{
            	required: " * 非空！"
            },
            periodDate:{
            	required: " * 非空！"
            }
        },
        errorElement: 'label',
        errorClass: 'help-block-new',
        focusInvalid: true,
        invalidHandler: function (event, validator) { //display error alert on form submit
            $('.alert-danger', $('.login-form')).show();
        },
        submitHandler: function (form) {
        	//$('#saveBtn').addClass('disabled');//置灰保存按钮，避免重复提交
        	
        	//空值处理，避免String空值转long
            if($("#patientGlobalid").val()==""){
            	$("#patientGlobalid").val(0);
            }
            if($("#roomId").val()==""){
            	$("#roomId").val(0);
            }
            $("#yuyueTime").val($("input[name='periodDate']").val()+" "+$("select[name='period']").val());	
           
             //预约时间不能早于当前时间
            var n=new Date();
            var currDate = n.getFullYear()+"-"+ Appendzero((n.getMonth()+1))+"-"+ Appendzero(n.getDate())+" "+Appendzero(n.getHours())+":"+Appendzero(n.getMinutes())+":"+Appendzero(n.getSeconds());
            if(!checkEndTime(currDate,$("#yuyueTime").val())){
            	//layer.alert('预约时间不能早于当前时间！', {icon: 5,shadeClose: true});
        		//return;
            	$("#yuyueTime").val(currDate);
            }           
            if($("#patienttypeCode").val()=="INP"){
            	if($("#studyApplocid").val()=="-1"){
            		layer.alert('请选择申请科室', {icon: 5,shadeClose: true});
            		//$('#saveBtn').removeClass('disabled');
            		return;
            	}
            	if($("#studyBedno").val()==""){
            		layer.alert('请填写床号', {icon: 5,shadeClose: true});
            		$("#studyBedno").focus();
            		//$('#saveBtn').removeClass('disabled');
            		return;
            	}
            	if($("#patientInpatientid").val()==""){
            		layer.alert('请填写住院号', {icon: 5,shadeClose: true});
            		$("#patientInpatientid").focus();
            		//$('#saveBtn').removeClass('disabled');
            		return;
            	}
            	
        	}else if($("#patienttypeCode").val()=="OP"){
        		if($("#studyApplocid").val()=="-1"){
        			layer.alert('请选择申请科室', {icon: 5,shadeClose: true});
            		//$('#saveBtn').removeClass('disabled');
            		return;
        		}
        	}else if($("#patienttypeCode").val()=="HP"){
        		if($("#patientInpatientid").val()==""){
            		layer.alert('请填写体检号', {icon: 5,shadeClose: true});
            		$("#patientInpatientid").focus();
            		//$('#saveBtn').removeClass('disabled');
            		return;
            	}
        	}else{
        		
        	}
            if(($("#studyConsultorg").val()!="-1"&&$("#studyConsultloc").val()!="-1")&&$("#studyConsultorg").val()&&$("#studyConsultloc").val()){
            	$("#isConsult").val("y");
            }
            //触发验证检查项目
            var itemmark = 0;
            
            var arrayBody = new Array();
            $("#configTab").find("[tag=studyItem]").each(function(i,item){
            	if($("#studyitemDescClone_"+(i+1)).val()==""||$("#studyitemDescClone_"+(i+1)).val()==undefined){
            		itemmark=1;
            	}else{
            		itemmark=0;
            	}
            	var bodypart=new Object();
            	bodypart.itemdesc = encodeURI(encodeURI($("#studyitemDescClone_"+(i+1)).val()));
            	bodypart.itempartname = encodeURI(encodeURI($("#bodypartNames_"+(i+1)).val()));
            	bodypart.itempart = $("#bodypartCodes_"+(i+1)).val();
            	bodypart.itemnum = $("#studyitemNumber_"+(i+1)).val();
            	bodypart.itemprice = $("#studyitemPrice_"+(i+1)).val();
            	arrayBody[i]=bodypart;
            });
            
            if(itemmark==1){
            	layer.alert('请选择检查项目', {icon: 5,shadeClose: true});
        		//$('#saveBtn').removeClass('disabled');
        		return;
        	}
            var arrayBodystrs = JSON.stringify(arrayBody);
        	$("#patientAge").val($("#patientAgeShow").val()+$('#patientAgeUnit option:selected').text());
            $('#patientRegForm').ajaxSubmit({
                url: GLOBAL.WEBROOT+'/patientReg/savePatientReg.ajax?arrayBodystrs='+arrayBodystrs,
                type: 'post',
                dataType: 'json',
                success: function (json, statusText, xhr, $form) {
                    if (json.success) {
                    	if(json.flag!="true"){
                    		$("#studyInfoId").val(json.studyInfoId);
                        	//$("#reset").click();  
                        	 //$('#saveBtn').addClass('disabled');//成功才置灰按钮
                        	 resetForm();
                        	 
                        	 refreshGrid1();
                        	 refreshGrid2(json.studyAccnumber);
                        	 layer.confirm('保存成功！', {
                                 btn: ['打印','继续登记','返回'], //可以无限个按钮
                                 btn1: function (index, layero) {
                                	//打印，并跳到检查列表页
                          			regPrint(json.studyInfoId);  
                                 },
                                 btn2: function (index, layero) {
                                	 
                                 },
                                 btn3: function (index, layero) {
                                	window.location.href = GLOBAL.WEBROOT+'/workList/init.html';
                            		//左侧菜单收缩 --已隐藏不点击   
                            	    if(!$("#menuTree",parent.document).is(":hidden")){
                            	    	parent.switchBarl();
                            	    }
                                 }
                        	 });
                    	}else{
                    		layer.alert('检查记录已存在！', {icon: 5,shadeClose: true});
                    	}
                    } else {
                    	//$('#saveBtn').removeClass('disabled');//回轩保存按钮
                    	layer.msg('保存失败！'); 
                    }
                }
            });
        }
    });
});

function refreshGrid1(){
	var currentOrgId = $("#currentOrgId" , parent.document).val();
	var data = $("#searchForm").serializeObject();
	data.patientid = $("#patientid").val();
	data.accessionnumber = $("#accessionnumber").val();
	data.startDate = $("#startDate").val();
	data.endDate = $("#endDate").val();
	$("#studylist").jqGrid('setGridParam', {  
        url: GLOBAL.WEBROOT+"/workList/queryStudyList.ajax?orgId="+currentOrgId,
        datatype : 'json',
        //发送数据  
        postData : data,
        page : 1  ,
        pageSize:30 
    }).trigger('reloadGrid');  
}

function refreshGrid2(studyAccnumber){
	var currentOrgId = $("#currentOrgId" , parent.document).val();
	var data = $("#downRightForm").serializeObject() ;
	data.orgId = currentOrgId;
	data.studyAccnumber = studyAccnumber;
	$("#rightdownlist").jqGrid('setGridParam', {  
        url: GLOBAL.WEBROOT+"/workList/queryStudyRightDownList.ajax",
        datatype : 'json',
        //发送数据  
        postData : data,
        page : 1  ,
        pageSize:30 
    }).trigger('reloadGrid');  
}

function refreshGridDownRight(studyAccnumber){
	var currentOrgId = $("#currentOrgId" , parent.document).val();
	var data = $("#downRightForm").serializeObject();  
	var grid_selector = "#rightdownlist";
    var pager_selector = "#rightdownlist_pager";
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/workList/queryStudyRightDownList.ajax?orgId="+currentOrgId+"&studyAccnumber="+studyAccnumber,
        mtype : "post",  
        async: true,
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        sortable:true,
        colNames: ['reportVerifydoctorid','检查号','状态','有图像','胶片打印','有报告','是否打印','病人类型','病人ID','patientGlobalid','modalityId','病人姓名','性别','年龄','生日','申请科室','住院号','床号','检查部位','检查项目',
                   '报告医生','审核医生','二次审核医生','诊断意见','诊断结果','检查技师','登记时间','预约时间','检查开始时间','检查结束时间','报告时间','审核时间','申请机构','申请医生','会诊机构','会诊科室','检查类型','申请单号','是否加急'],
        colModel: [	
                   {name: 'reportVerifydoctorid', index: 'reportVerifydoctorid', width:60,sortable: true,hidden:true},
                   {name: 'studyAccnumber', index: 'study_Accnumber', width:80,sortable: true
//                   	,formatter:function(param1,param2, recode){  
//                   		//recode.reportResult
//       	                var content = '<li title="' + param1 + '" class="tip">' + param1 + '</li>';  
//       	                return content;  
//                       }  
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
                   		return "";
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
                   		return '';
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
                   		return '';
                   	}   
               	}}
               	//终审人
//               	{name: 'reportFinaldoctorname', index: 'report_Finaldoctorname', width:120,sortable: true ,
//                   	formatter: function (param1, param2, recode) {
//               		if(param1=='a' || param1==null || param1=='undefined' || param1==''){
//                   		return '';
//                   	} else{
//                   		return param1;
//                   	}
//               	}}, 
                   
               ],
        viewrecords: false,
        rowNum:20,
        rowList:[10,20,30],
        multiselect: true,  //checkbox
        multiboxonly: true,  //取掉这个，点击选中，再点击取消选中了。
        pager: pager_selector,
        altRows: true, 
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);      
            
          //空列填充
        },
        gridComplete: function() {
        	//加急颜色
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
        	//patientAdd(rowid);
            return true;    
        },
        onRightClickRow: function (rowid, iRow,iCol,e) {        
        },
        //行宽自动
        autowidth: true
    });
}

//初始化查询进间
function initDate(){
	var n=new Date();
    $("#startTime").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
    $("#endTime").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
    
    $("input[name=periodDate]").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
    
    var orgId = $("#orgId" , parent.document).val();
    $("#orgId").val(orgId);
  //根据科室获取检查设备类型
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT+"/workList/getEquimentByLocId.ajax?orgId="+orgId+"&locId="+$("#locId").val(),
        success: function (data) {
            var jsonEqimentType = eval(data.MODALITY_TYPE);
            var modalitySelect = $("#modalitiesinstudy");
            modalitySelect.empty(); 
            modalitySelect.append("<option value='-1'>全部</option>");
            var currFlag = false;
            for (var i = 0; i < jsonEqimentType.length; i++) {
            	modalitySelect.append("<option value='" + jsonEqimentType[i].itemname + "'>" + jsonEqimentType[i].itemname + "</option>");
            }
//            $("#modalityId option:first").prop("selected", 'selected');
//        	$("#modalityId").select2();
        }
    });
  //机构列表加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/patientReg/getConsultOrg.ajax?orgId='+orgId+"&locId="+$("#locId").val(),
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data.conorganization);
	        var orgSelect = $("#studyConsultorg");
	        orgSelect.empty(); 
	        orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].conorgId + "'>" + sysOrg[i].orgName + "</option>");
	        	//修改页面中，默认选中之前的会诊机构
//	        	if($("#eConsultorg").val() == sysOrg[i].conorgId){
//	        		orgSelect.append("<option value='" + sysOrg[i].conorgId + "'selected>" + sysOrg[i].orgName + "</option>");
//	        		setLocItem($("#eConsultorg"));
//	        	}else{
//	        		
//	        		setLocItem($("#eConsultorg"));
//	        	}
 	        }
		}
	}); 
}

//查会诊科室
function setLocItem(obj){ 
	var conorgId = $(obj).val();
	if(conorgId !="" && conorgId != '-1'){
		$.ajax({
		    type: "POST",
		    url: GLOBAL.WEBROOT+"/patientReg/getConsultLoc.ajax?orgId="+conorgId+"&locType=C&locId="+$("#locId").val(),
		    success: function (data) {
		        var locItemBeans = eval(data.locBeans);
		        var statusSelect = $("#studyConsultloc");
		        statusSelect.empty();
		        statusSelect.append("<option value='-1'>请选择</option>");
		        for (var i = 0; i < locItemBeans.length; i++) {
		        	if($("#eConsultloc").val() == locItemBeans[i].conlocId){
			            statusSelect.append("<option value='" + locItemBeans[i].conlocId + "' selected>" + locItemBeans[i].locName + "</option>");
			            $("#studyConsultloc").select2();
		        	}else{
			            statusSelect.append("<option value='" + locItemBeans[i].conlocId + "' >" + locItemBeans[i].locName + "</option>");
	              	}
		        }
		        if($("#studyConsultloc").val()=="-1"){
		        	$("#studyConsultloc option:nth-child(2)").prop("selected", 'selected');
			        $("#studyConsultloc").select2();
		        }	        
		    }
		});    
	}	
}

var titileArray = ['登记号','姓名','性别','生日','检查号','设备类型','检查日期','机构ID','patient_key'];

var dataArray = [	
                 {name: 'patientid', index: 'patient_id', width:60,sortable: true},
                 {name: 'patientname', index: 'patient_name', width:80,sortable: true},            
                 {name: 'gender', index: 'gen_der', width:50,sortable: true,
                 	formatter: function (param1, param2, recode) {
                 	if(param1=="F"){
                 		return '男';
                 	}else if(param1=="M"){
                 		return '女';
                 	}else{
                 		return '';
                 	}
             	}},   
             	{name: 'birthdate', index: 'birth_date', width:60,sortable: true,formatter:dateFormat}, 
                {name: 'accessionnumber', index: 'accession_number', width:50,sortable: true},
                {name: 'modalitiesinstudy', index: 'modalitiesinstudy', width:60,sortable: true},
             	{name: 'studydate', index: 'study_date', width:60,sortable: true,formatter:dateFormat},
             	{name: 'institutionid', index: 'org_id', width:60,sortable: true,hidden:true},
             	{name: 'patientkey', index: 'patient_key', width:60,sortable: true,hidden:true}
             ];

function dateFormat( cellvalue, options, rowObject ){  
	var str = "";
	if(cellvalue!=undefined){
		str = cellvalue.substr(0,4)+"-"+cellvalue.substr(4,5).substr(0,2)+"-"+cellvalue.substr(6,8);
	}
    return str;  
} 

//查询左下列表
function selectList() {	 
	var currentOrgId = $("#currentOrgId" , parent.document).val();
	var data = $("#searchForm").serializeObject();  
	var grid_selector = "#studylist";
    var pager_selector = "#studylist_pager";
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/workList/queryStudyList.ajax?orgId="+currentOrgId,
        mtype : "post",  
        async: true,
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        sortable:true,
        colNames: titileArray,
        colModel: dataArray,
        viewrecords: false,
        rowNum:20,
        rowList:[10,20,30],
        multiselect: true,  //checkbox
        multiboxonly: true,  //取掉这个，点击选中，再点击取消选中了。
        pager: pager_selector,
        altRows: true, 
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);      
            
          //空列填充
		var re_records = $("#studylist").getGridParam('records');
    	if(re_records < 20 )
    	{
    		for(var i=0,j=20-re_records;i<=j;i++){
    			$("#studylist").jqGrid("addRowData",(re_records+1),{ '登记号':'','姓名':'','性别':'','生日':'','检查号':'','检查日期':''},"");
    		}
    	}
        },
        gridComplete: function() {
        	//加急颜色
        },
        ondblClickRow: function (aRowids, status) {
        	
        },
        onSelectAll: function (aRowids, status) {
        }, 
        onSelectRow: function (rowid, status) {      
        	
        }, 
        //实现单选
        beforeSelectRow: function (rowid, event) {  
        	patientAdd(rowid);
            return true;    
        },
        onRightClickRow: function (rowid, iRow,iCol,e) {        
        },
        //行宽自动
        autowidth: true
    });
}

function ages(str){
	var unit = $('#patientAgeUnit option:selected').val();
	if(unit=="1"||unit==null){
		var n=new Date();
		n.setDate(n.getDate());
		$("input[name=patientDob]").val(n.getFullYear()-$(str).val()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
	}else if(unit=="2"){
		$("input[name=patientDob]").val(getY_M(-$("#patientAgeShow").val()));	
	}else if(unit=="3"){
		$("input[name=patientDob]").val(GetDateStr(-$("#patientAgeShow").val()));
	}else if(unit=="4"){
		var day = Math.floor( $("#patientAgeShow").val()/24);
		if(day>=1){
			$("input[name=patientDob]").val(GetDateStr(-day));
		}else{
			$("input[name=patientDob]").val(GetDateStr(-day));
		}
	}
	
	$("#patientAge").val($("#patientAgeShow").val()+$('#patientAgeUnit option:selected').text());
}

function patientAdd(rowid){
	var rowData= $("#studylist").jqGrid('getRowData',rowid);
	if(rowData.patientname!=""){
		//$('#saveBtn').removeClass('disabled');
		//$('#printBtn').removeClass('disabled');
		
		$("#patientName").val(rowData.patientname);
//		$("#patientSex").prop("readonly",true);
//		$("#patientAgeShow").prop("readonly",true);
//		$("#patientAgeUnit").prop("readonly",true);
		
		$("#patientSex").select2("val", rowData.gender=="男"?1:2);
		$("#patientId").val(rowData.patientid);
		$("#patientkey").val(rowData.patientkey);
		$("input[name=patientDob]").val(rowData.birthdate);	
		getAge($("#patientDob"));
		ename();
		var n=new Date();
	    n.setDate(n.getDate());
	    $("input[name=periodDate]").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
	    $("#studyAccnumber").val(rowData.accessionnumber);
	    $("#orgId").val(rowData.institutionid);
	    if($("#equipmentId option").length>1){
	    	$('#equipmentId').get(0).selectedIndex=1;
	    }
		$("select").select2();
	}else{
		resetForm();
	}
}

function resetForm(){
	//$('#saveBtn').addClass('disabled');
	//$('#printBtn').addClass('disabled');
	$("#patientName").val("");
	$("#patientNamepy").val("");
	$("#patientAgeShow").val("");
	$("#patientSex").select2("val",0);
	$("#patientId").val("");
	$("input[name=patientDob]").val("");	
	$('#equipmentId').select2("val","-1");
	$('#studyAccnumber').val("");
//	$('input').val("");
	
//	var tr = $("#configTab").find("[tag=studyItem]");
//	tr.find("span[class='select2-chosen']").html("选择检查项目");
//	tr.find("input[name='studyitemDescClone_1']").val("");
//	tr.find("[name=studyitemNumber_1]").val("");
//	tr.find("[name=studyitemPrice_1]").val("0"); 
}

function ename(){	
	var patientName = $('#patientName').val();
	if(patientName!=""){
		$.ajax({
		       type: "POST",
		       url: GLOBAL.WEBROOT+"/patientReg/getPatientPy.ajax?patientName="+encodeURI(encodeURI($('#patientName').val())),
		       success: function (data) {
		           var patientEname = data.patientEname;
		           $('#patientNamepy').val(patientEname);
		       }
		   }); 
	}
	if($("#patientName").val()==""){
		$("#patientNamepy").val("");
	}  
}

//计算年龄
function getAge(obj) {
	var strBirthday = $(obj).val();
	var returnAge;
	var strBirthdayArr = strBirthday.split("-");
	var birthYear = strBirthdayArr[0];
	var birthMonth = strBirthdayArr[1];
	var birthDay = strBirthdayArr[2];

	d = new Date();
	var nowYear = d.getFullYear();
	var nowMonth = d.getMonth() + 1;
	var nowDay = d.getDate();
	
	var ageUnit = 1;
	if (nowYear == birthYear) {		
		 if(nowMonth == birthMonth){			 
			 if(nowDay == birthDay){
				 ageUnit = 4;
				 returnAge = 1;
			 }else{
				 ageUnit = 3;
				 returnAge = nowDay - birthDay; 
			 }
		 }else{
			 if(nowMonth - birthMonth>1){
				 ageUnit = 2;
				 returnAge = nowMonth - birthMonth; 
			 }else{
				 ageUnit = 3;
				 var birda = parseInt(Math.abs(d-new Date(strBirthdayArr[0], strBirthdayArr[1]-1, strBirthdayArr[2]))/ 1000 / 60 / 60 /24);
				 returnAge = birda;
			 }
			 
		 }
	}else {
		var ageDiff = nowYear - birthYear; // 年之差
		if (ageDiff > 0) {
			if (nowMonth == birthMonth) {
				var dayDiff = nowDay - birthDay;// 日之差
				if (dayDiff < 0) {
					returnAge = ageDiff - 1;
				} else {
					returnAge = ageDiff;
				}
			} else {
				var monthDiff = nowMonth+12*ageDiff - birthMonth;// 月之差
				if (monthDiff > 12) {
					ageUnit =1;
					returnAge = ageDiff;
				}else if(monthDiff<12){
					monthDiff = 1;
					returnAge = ageDiff; 
				}else {
					 ageUnit = 2;
					 returnAge = monthDiff; 
				}
			}
		} else {
			returnAge = -1;// 返回-1 表示出生日期输入错误 晚于今天}
		} 
	}
	$("#patientAgeUnit option[value='"+ageUnit+"']").attr("selected",true); 
	$("#patientAgeUnit").select2();
	$("#patientAgeShow").val(returnAge);
	var u = "";
	if(ageUnit == 1){
		u = "岁"
	}else if(ageUnit == 2){
		u = "月"
	}else if(ageUnit == 3){
		u = "天"
	}else if(ageUnit == 4){
		u = "小时"
	}
	$("#patientAge").val(returnAge+u);
}

//美化检查项目描述，增加ajax请求功能
function initSelect2forDiv(div,exists){
	div.select2({
		placeholder: "选择检查项目",
        minimumInputLength: 0, 
        maximumInputLength:100,//限制最大字符，以防坑货
        id : function(repo) { //这个很重要， 没有这个就选择不了值
            return repo.itemmastId+"_"+repo.itemmastDesc+"_"+repo.itemmastEndesc;  
        },
        ajax: {
            url: GLOBAL.WEBROOT+"/patientReg/getStudyItem.ajax",
            dataType: 'json',
            method:'post',
            data: function (term, page) {
            	$("div[id^='bodypartNames_']").hide();
                return {
                	orgId:orgId,
                	//locId:$("#studyApplocid option:selected").val(),
                	locId:$("#locId").val(),
                	exists:exists,
                    keyword: encodeURI(term),
                    equipmentId :$("#equipmentId").val(),
                    rows:10,
                    page: 1
                };
            },

            results: function (data, page) {
                return {results: data.dataList};
            }
        },
        initSelection: function(element, callback) {
            var data = [];
            $(element.val().split(";")).each(function () {
                if (this.length > 0) {
                    data.push(this);
                }
            });
            callback(data);
        },
        formatResult: formatResult,
        formatSelection: formatSelection,
        dropdownCssClass: "bigdrop",
        formatSearching: function () {
            return "加载中..."
        },
        formatNoMatches: function () {
            return "没有匹配结果."
        },
        escapeMarkup: function (m) { return m; }    
	});	
}
//搜索结果显示样式
function formatResult(repo){
	if(repo.loading) return repo.text;
	//var?markup?=?"<div style='display:inline;'>"+repo.itemmastId+"</div><div style='color:#4F4F4F;display:inline'>"+repo.itemmastDesc+"</div>";
	    var markup="<div style='color:#4F4F4F;display:inline'>"+repo.itemmastDesc+"</div>";
	    return markup;
}
//选中后输入框展现
function formatSelection(repo){	 
	var str = "选择检查项目";
	if(repo.itemmastId!=undefined){
		str = repo.itemmastId + "_" + repo.itemmastDesc;
	}
	
    return str +"<input type='hidden' name='price' value="+repo.itemmastPrice+" id=\"price"+repo.itemmastId+"\">";
//return?repo.itemmastDesc; 
}

function changeType(obj){
	if($(obj).val()=="INP"){
		$(".ret_span_INP").show();
		$(".ret_span_HP").hide();
		$(".ret_span_OP").hide();
		$("#hpnum").text("住院号：");
	}else if($(obj).val()=="HP"){
		$(".ret_span_HP").show();
		$(".ret_span_OP").hide();
		$(".ret_span_INP").hide();
		$("#hpnum").text("体检号：");
	}else if($(obj).val()=="OP"){
		$(".ret_span_OP").show();
		$(".ret_span_HP").hide();
		$(".ret_span_INP").hide();
		$("#hpnum").text("住院号：");
	}else if($(obj).val()=="EP"){
		if($("#isNew").val()!="n"){
			if($("#patientName").val()==""){
				$("#patientName").val("未知");
				ename();
				$("#patientSex").select2("val","0");
				var n=new Date();
				n.setDate(n.getDate());
				$("input[name=patientDob]").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
				getAge($("input[name=patientDob]"));
			}
		}
	}else{
		$(".ret_span_OP").hide();
		$(".ret_span_HP").hide();
		$(".ret_span_INP").hide();
	}
	
}

function changeCareProv(obj){
	var locId = $("#studyApplocid option:selected").val();
	setCareProv(orgId,$(obj).val());
	//setStudyItem(orgId,locId);//检查项
	//setSeq(orgId,locId);
}

//申请医生查询
function setCareProv(orgId,locId){
	//申请医生下拉列表
	$.ajax({
	    type: "POST",
	    async: true,
	    url: GLOBAL.WEBROOT+"/patientReg/getCareProv.ajax?orgId="+orgId+"&locId="+locId+"&doctype=5",
	    success: function (data) {
	        var careProvBeans = eval(data.careProvBeans);
	        var statusSelect = $("#studyAppdoc");
	        statusSelect.empty();
	        statusSelect.append("<option value='-1'>请选择</option>");
	        for (var i = 0; i < careProvBeans.length; i++) {
	        	if($("#old_studyAppdoc").val()==careProvBeans[i].careprovId){
	        		statusSelect.append("<option value='" + careProvBeans[i].careprovId + "' selected>" + careProvBeans[i].careprovName + "</option>");
	        	}else{
	        		statusSelect.append("<option value='" + careProvBeans[i].careprovId + "'>" + careProvBeans[i].careprovName + "</option>");
	        	}
		        if(locId=="-1"){
		        	$("#studyAppdoc").select2('val',"-1");
		        }
	        }
	        statusSelect.select2();
	    }
	});
}

//重置设备房间
function setRoomId(obj){
	//钟永伟添加的
//	if($("#equipmentId").val()!=null&&$("#equipmentId").val()!='-1'){		 
//		setCookies("C_Equipment",$("#equipmentId").val(),240);
//	}
	
	
	$("input[name^='bodypartNames_']").each(function(i){
		$(this).val("");
	});
	$("input[name^='bodypartCodes_']").each(function(){
		$(this).val("");
	})
	$("div[id^='bodypartNames_']").remove();
	var roomId = $("#equipmentId option:selected").attr("roomId")
	$("#roomId").val(roomId);
	
	$("#configTab").find("[tag=studyItem]").find("[name='studyitemDesc_1']").val("");
	//$("#configTab").find("[tag=studyItem]").find("[name=studyitemBodyinfoClone]").val("");
	$("#configTab").find("[tag=studyItem]").find("[name=studyitemNumber_1]").val("");
	$("#configTab").find("[tag=studyItem]").find("[name=studyitemPrice_1]").val("0");
	$("#configTab").find("[tag=studyItem]").find("[name=studyitemDescClone_1]").val("");
	$("#configTab").find("[tag=studyItem]").find("span[class='select2-chosen']").html("选择检查项目");
	
//	var tr = $("#configTab").find("[tag=studyItem]:last");
//	tr.find("[name=studyitemBodyinfoClone]").val("");
}
var isqs = 1;
function setNewBigbodyPart(obj,bodypartDesc){
	var itemmastId= $(obj).val();
	var id = itemmastId.substr(0,itemmastId.indexOf("_"));
	var treeids= $(obj).attr("name").substr($(obj).attr("name").indexOf("_"),$(obj).attr("name").length-1);
	try {
		$(obj).next("input[name=studyitemDescClone"+treeids+"]").val(itemmastId);
	} catch (e) { 
	}
	$("input[name='bodypartNames"+treeids+"']").val("");
	$("input[name='bodypartCodes"+treeids+"']").val("");
	optionForMulti = {
			dataStructure: {
				idKey: "bodypartCode",
				pIdKey: "bodypartPid",
				rootPId: "-9999",
				name: "bodypartDesc"
			},
			data: {
				url: GLOBAL.WEBROOT + "/patientReg/getBodypartTree.ajax?itemmastId="+id+"&orgId="+orgId+"&bodypartDesc="+bodypartDesc,    //获取数据的URL
				param: ["orgId"]  //获取数据的参数
			},
			rootNode: [{bodypart2Id:"-9999", bodypartDesc: "部位",bodypartCode: "-9999", open: true, isParent: true, nocheck: false}]
		};
		
	com.ai.bdx.util.ztreeComp("bodypartNames"+treeids, true,optionForMulti,callFuncForMultiQuery);
	
	$("input[name='studyitemNumber"+treeids+"']").val(1);
    $("input[name='studyitemPrice"+treeids+"']").val($("#price"+id).val());
}

function callFuncForMultiQuery(zTree) {
    var nodes = zTree.getCheckedNodes(true);
    var v = "";
    var n = "";
    for (var i = 0, l = nodes.length; i < l; i++) {
        if(nodes[i].bodypartType!=1&&nodes[i].bodypartCode!="-9999"){
        	v += nodes[i].bodypartCode + ",";
        	n += nodes[i].bodypartDesc + ",";
        }
    }
    if (v.length > 0) v = v.substring(0, v.length - 1);
    if (n.length > 0) n = n.substring(0, n.length - 1);
    var treeId  = zTree.setting.treeId;
    var treeName = treeId.substr(0,treeId.lastIndexOf("_"));
    var nos = treeName.substr(treeName.indexOf("_"),treeName.length-1);
    $('#bodypartCodes'+nos).val(v);
    $('#qryInput'+nos).val("");
    $("#"+treeName).val(n);
}

function qryinitBodyTree(obj){
	var inputId = $(obj).attr("id").substr($(obj).attr("id").indexOf("_"),$(obj).attr("id").length-1);
	var itemmastId=$("#studyitemDescClone"+inputId).val();
	var id = itemmastId.substr(0,itemmastId.indexOf("_"));
	try {
		$(obj).next("input[name=studyitemDescClone"+treeids+"]").val(itemmastId);
	} catch (e) { 
	}
	var bodypartDesc = $(obj).val();
	//$("input[name='bodypartNames"+inputId+"']").val("");
	//$("input[name='bodypartCodes"+inputId+"']").val("");
	optionForMulti = {
			dataStructure: {
				idKey: "bodypartCode",
				pIdKey: "bodypartPid",
				rootPId: "-9999",
				name: "bodypartDesc"
			},
			data: {
				url: GLOBAL.WEBROOT + "/patientReg/getBodypartTree.ajax?itemmastId="+id+"&orgId="+orgId+"&bodypartDesc="+encodeURI(encodeURI(bodypartDesc)),    //获取数据的URL
				param: ["orgId"]  //获取数据的参数
			},
			rootNode: [{bodypart2Id:"-9999", bodypartDesc: "部位",bodypartCode: "-9999", open: true, isParent: true, nocheck: false}]
		};
	com.ai.bdx.util.ztreeComp("bodypartNames"+inputId, true,optionForMulti,callFuncForMultiQuery);
	
	$("input[name='studyitemNumber"+inputId+"']").val(1);
    $("input[name='studyitemPrice"+inputId+"']").val($("#price"+id).val());
}

//添加检查明细项
function addStudyItem(){    
	isqs+=1;
	var tr = $("#configTab").find("tr:last");
	if(tr.find("[name='studyitemDesc_"+isqs+"']").val()!=""||$("#isNew").val()=="n"||$("#isHis").val()=="y"){
		tr.after("<tr tag='studyItem'>    "+
				"		            <td width='20%' align = 'center'>           "+
				"		                <div  name='studyitemDesc_"+isqs+"' style='width:180px;' onclick='setNewBigbodyPart(this,\"\")'>        "+
				"		                </div>"+
				"		                <input type='hidden' name='studyitemDescClone_"+isqs+"' id='studyitemDescClone_"+isqs+"' />		   "+             
				"		            </td>  "+
				"		             <td width='46%'>"+
				"<input id='qryInput_"+isqs+"' name='qryInput_"+isqs+"' type='text' value='搜索' onFocus='if(value==defaultValue){value=\"\";this.style.color=\"#000\"}' onBlur='if(!value){value=defaultValue; this.style.color=\"#999\"};' onchenge='qryinitBodyTree(this)' style='color:#999;width:50px' />"+
				"                            <input id='bodypartNames_"+isqs+"' type='text' readonly=readonly class='select' style='width: 150px;'"+
				"                                   name='bodypartNames_"+isqs+"' />"+
				"                            <input id='bodypartCodes_"+isqs+"' type='hidden' readonly name='bodypartCodes_"+isqs+"'/>"+
				"                    </td>  "+
				"		            <td width='2%' align = 'center'>"+
				"		                 <input id='studyitemNumber_"+isqs+"' name='studyitemNumber_"+isqs+"' class='inputW120' readonly onkeyup='value=value.replace(/[^\\.]{2,}/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
				"		            </td>        "+
				"		            <td width='2%' align = 'center'align = 'center'>"+
				"		                 <input id='studyitemPrice_"+isqs+"' name='studyitemPrice_"+isqs+"' class='inputW120' readonly onkeyup='value=value.replace(/[^\d\.]/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
				"		            </td> "+
				"		            <td width='30%' class='c'>   "+
				"		                <button  id='add' type='button' onclick='addStudyItem()'>增加</button>"+
				"		                <button  id='deleBtn_"+isqs+"' type='button' onclick='delStudyItem(this)'>删除</button>"+
				"		            </td>"+
				"</tr>");
		initSelect2forDiv($("div[name='studyitemDesc_"+isqs+"']"));
		
	}else{
		layer.alert('请选择检查项目！', {icon: 5,shadeClose: true});
	}
	
}

//删除检查明细项
function delStudyItem(obj){
	var id_nos = $(obj).attr("id").substr($(obj).attr("id").indexOf("_"),$(obj).attr("id").length-1);
	var trf = $("#configTab").find("[tag=studyItem]:last");
	var trToatl = $("#configTab").find("[tag=studyItem]");
    if(trToatl.length!=1){
//    	if(trf.find("[name='studyitemDesc"+id_nos+"']").val()!=""){
//    		var id = trf.find("[name=studyitemDesc"+id_nos+"]").val().split("_")[0];
//    		if(mastId!=""){
//    			var arr = mastId.substring(0,mastId.length-1).split(",");
//    			for(var i=0;i<arr.length;i++) {
//    				if(arr[i]==id){
//    					arr.splice($.inArray(id,arr),1);
//    				}
//    			}
//    			mastId = arr.join(",")+",";
//    		}
//    	}
        trf.remove() ;
    }else{
//    	trf.find("[name='studyitemDescClone']").val("");
    	addStudyItem();
    	trf.remove();
    }
}

//打印
function regPrint(studyInfoId){
	if (studyInfoId != null && studyInfoId != "") {
		layer.open({
	        type: 2,
	        title:"登记信息打印",
	        area: ['800px', '500px'],
	        fix: false, //不固定
	        maxmin: true,
	        content: GLOBAL.WEBROOT + "/patientReg/regPrint.html?studyinfoId="+studyInfoId
	    });		 
	}else{
		layer.msg('请登记后再打印！', {icon: 5});
	}   
}

function Appendzero (obj) {
	if (obj < 10) return "0" + obj; else return obj;
}
//比较时间大小
function checkEndTime(startTime,endTime){   
    var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
    var end=new Date(endTime.replace("-", "/").replace("-", "/"));  
    if(end<start){  
        return false;  
    }  
    return true;  
}  

function f1(callback){
	window.setTimeout(writeReprot(2),1);
}

function pacsView(type,obj){
	var gr = $("#rightdownlist").jqGrid("getGridParam","selarrrow");
	 
	if (gr.length > 1 || gr.length == 0) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	var rowData ;
	if(type==1){
		rowData = $("#rightdownlist").jqGrid('getRowData',gr[0]); 
		if (rowData.studystatusCode=="取消预约") {
			layer.alert('报告已取消，不允许调阅影像！', {icon: 5,shadeClose: true});
			return;
		}
	}else{
		var rowid = $(obj).closest('tr').attr("id");
		rowData = $("#rightdownlist").jqGrid('getRowData',rowid);
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
//         var commandline = accessnumber+"&"+patientid+"&&&&I&&&&"+orgId;
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

//书写报告  
function writeReprot(rtype){
	var gr = $("#rightdownlist").jqGrid("getGridParam","selarrrow");
	if (gr.length == 0 || gr.length > 1) {
		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	var rowData = $("#rightdownlist").jqGrid('getRowData',gr[0]);
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
	if($("#hzisFlag").val()==1&&rowData.studyType=="远程诊断"){
		if($("#consultRole").val()=="y"){
			if(rowData.studystatusCode=="已审核"||rowData.studystatusCode=="已录入报告"||rowData.studystatusCode=="报告未完成"||rowData.studystatusCode=="审核通过"){
				window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+$("#locId").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
			}else if(rowData.studystatusCode=="会诊开始"){
				layer.confirm('病人[<span style="color:red">'+rowData.patientName+'</span>]会诊已开始，您确认要书写报告吗？', {
					  btn: ['是','否'] //按钮
					}, function(){	
						var locId = $("#locInfo").val();
//						var studyHavereport = rowData.studyHavereport;
//						var havereport = 0;
//						if(""!=studyHavereport ){
//							havereport = 1;
//						}
						window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+$("#locId").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;;

					}, function(){ 
						return;
					});
			}else{
				layer.alert('会诊申请需要审核，暂不允许书写报告！', {icon: 5,shadeClose: true});
				return ;
			}
		}else{
			if(rowData.studystatusCode!="已审核"){
				layer.alert('会诊申请报告暂未审核！', {icon: 5,shadeClose: true});
				return ;
			}else{
				window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+$("#locId").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
			}
		}
	}else{
		window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype="+rtype+"&locId="+$("#locId").val()+"&modalityId="+rowData.modalityId+"&orgId="+orgId;
	}
	 
}

function getY_M(n){
	var y = new Date().getFullYear();
	var m = new Date().getMonth()+1;
	m += n;
	y += parseInt(m / 12);
	m %= 12;
	if(m < 1){
	y--;
	m = 12 + m;
	}
	return y + "-" + ((m < 10) ? ("0" + m) : m)+"-01";
}

function replace(mthis){
	/*var result=$(mthis).attr("value").replace(/(^\s*)|(\s*$)/g, "");
	$(mthis).attr("value",result); */
	var name = $(mthis).val();
	while (name.indexOf('  ') > 0) {  
		name = name.replace('  ', ' ');  
    }
	$(mthis).val($.trim(name));
}
