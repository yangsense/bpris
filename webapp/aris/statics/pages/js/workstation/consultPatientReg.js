//组织机构ID
var orgId = ""; 
var locId = "";
function ages(str){
	if($("#patientAgeUnit").val()=="1"){
		var n=new Date();
		n.setDate(n.getDate());
		$("input[name=patientDob]").val(n.getFullYear()-$(str).val()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
	}else if($("#patientAgeUnit").val()=="2"){
		$("input[name=patientDob]").val(getY_M(-$("#patientAgeShow").val()));	
	}else if($("#patientAgeUnit").val()=="3"){
		$("input[name=patientDob]").val(GetDateStr(-$("#patientAgeShow").val()));
	}
	
	$("#patientAge").val($("#patientAgeShow").val()+$('#patientAgeUnit option:selected').text());
}

function GetDateStr(AddDayCount) 
{ 
	var dd = new Date(); 
	dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
	var y = dd.getFullYear(); 
	var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0
	var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate(); //获取当前几号，不足10补0
	return y+"-"+m+"-"+d; 
}

function changeType(obj){
	if($(obj).val()=="OP"){
		$("#studyWard").attr("readonly", "true"); 
		$("#studyBedno").attr("disabled","disabled");
	}else{
		$("#studyWard").attr("readonly",false);
		$("#studyBedno").attr("disabled",false);
	}
	
}

//打印
function regPrint(){
	var studyInfoId = $("#studyInfoId").val();
	if (studyInfoId != null && studyInfoId != "") {
		layer.open({
	        type: 2,
	        title:"会诊申请打印",
	        area: ['800px', '500px'],
	        fix: false, //不固定
	        maxmin: true,
	        content: GLOBAL.WEBROOT + "/patientReg/regPrint.html?studyinfoId="+studyInfoId
	    });		 
	}else{
		layer.msg('请登记申请后再打印！', {icon: 5});
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
	return y + "-" + ((m < 10) ? ("0" + m) : m);
}

$(function () {
	$("#patientAgeUnit").click(function(){
		if($("#patientAgeShow").val()!=""){
			if($("#patientAgeUnit").val()=="1"){
				var n=new Date();
				n.setDate(n.getDate());
				$("input[name=patientDob]").val(n.getFullYear()-$("#patientAgeShow").val()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
			}else if($("#patientAgeUnit").val()=="2"){
				$("input[name=patientDob]").val(getY_M(-$("#patientAgeShow").val()));				
			}else if($("#patientAgeUnit").val()=="3"){
				$("input[name=patientDob]").val(GetDateStr(-$("#patientAgeShow").val()));
			}
			$("#patientAge").val($("#patientAgeShow").val()+$('#patientAgeUnit option:selected').text());
		}
	});
	if($("#studyInfoId").val()==""||$("#studyInfoId").val()==undefined){
		$("#imgviewbtn").hide();
	}else{
		$("#imgviewbtn").show();
	}
	
	if($("#isNew").val()=='y'){
		var n=new Date();
	    n.setDate(n.getDate());
	    $("input[name=periodDate]").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
	    
	    $("#queryKey").val("0");
	    if($("#equipmentId option").length>1){
	    	$("#equipmentId").get(0).selectedIndex = 1;  
	    }
	    $("#cancelbtn").addClass("disabled");
	    $("#imagebtn").addClass("disabled");
	    $("#medicalRecordbtn").addClass("disabled");
	    $("#submitbtn").addClass("disabled");
	}else{
		//隐藏his查询条件及按钮
	    $("#hisSelectDiv").hide();
	    if($("#studystatusCode").val()=="AppSave"&&($("#studyhaveimage").val()==0||$("#studyhaveimage").val()==1)){
	    	$("#cancelbtn").removeClass("disabled");
		    $("#imagebtn").removeClass("disabled");
		    $("#submitbtn").addClass("disabled");
	    }
	    if($("#studystatusCode").val()=="AppCancel"){
	    	$("#savebtn").addClass("disabled");
	    	$("#cancelbtn").addClass("disabled");
		    $("#imagebtn").addClass("disabled");
		    $("#medicalRecordbtn").addClass("disabled");
		    $("#submitbtn").addClass("disabled");
	    }
	    if($("#studystatusCode").val()=="RejectApp"){
	    	$("#savebtn").removeClass("disabled");
	    	$("#cancelbtn").removeClass("disabled");
		    $("#imagebtn").removeClass("disabled");
		    $("#medicalRecordbtn").removeClass("disabled");
		    $("#submitbtn").removeClass("disabled");
	    }
	    if($("#studystatusCode").val()=="Consulting"||$("#studystatusCode").val()=="AppVerify"||$("#studystatusCode").val()=="APPConsult"||$("#studystatusCode").val()=="Consulted"
	    	||$("#studystatusCode").val()=="HAVERPT"||$("#studystatusCode").val()=="UnCompleted"||$("#studystatusCode").val()=="HAVERPT"||$("#studystatusCode").val()=="StartStudy"||$("#studystatusCode").val()=="VERIFY"){
	    	$("#savebtn").addClass("disabled");
	    	$("#cancelbtn").addClass("disabled");
		    $("#imagebtn").addClass("disabled");
		    $("#medicalRecordbtn").addClass("disabled");
		    $("#submitbtn").addClass("disabled");
	    }
	}
	
	var nowd=new Date();
	var time1 = time_range("08:00", "12:00", Appendzero(nowd.getHours())+":"+Appendzero(nowd.getMinutes()));
	var time2 = time_range("12:00", "13:00", Appendzero(nowd.getHours())+":"+Appendzero(nowd.getMinutes()));
	var time3 = time_range("13:00", "18:00", Appendzero(nowd.getHours())+":"+Appendzero(nowd.getMinutes()));
	var time4 = time_range("18:00", "23:00", Appendzero(nowd.getHours())+":"+Appendzero(nowd.getMinutes()));
	if(time1){
		$("input[name=period]").select2("val","08:00");
	}else if(time2){
		$("input[name=period]").select2("val","12:00");
	}else if(time3){
		$("input[name=period]").select2("val","13:00");
	}else if(time4){
		$("input[name=period]").select2("val","18:00");
	}  
	
	
    //取组织机构
	orgId = $("#orgId" , parent.document).val();  
	$("#orgId").val(orgId);
	
    //字典记录初始化
    init(); 
    
    //所有select嵌套css样式
    $("select").select2();  
     
    locId = $("#locId").val();//工作列表页面的科室
    
       
    //校验+提交
    $('#patientRegForm').validate({
        rules: {  
            patientName: {
            	maxlength: 16
            },
            studyApplocid: {
                required: true
            },
            studyConsultorg: {
                required: true
            },
            studyConsultorg: {
                checkSelectVal:true
            },
            studyConsultloc: {
                required: true
            },
            studyConsultloc: {
                checkSelectVal:true
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
            patientName:{
            	required: true
            },
            periodDate:{
            	required: true
            },
            patientDob:{
            	required: true
            },  
            studyWard:{
            	checkStudyWard:true
            },
            patientPhone:{
            	phone :true
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
            	required: "非空！"
            },
            studyitemNumber:{
            	required: "只能输入1位！"
            },
            patientName:{
            	required: " * 非空！"
            },
            periodDate:{
            	required: " * 非空！"
            },
            patientDob:{
            	required: " * 非空！"
            },
            studyConsultorg:{
            	required: " * 必选！"
            },
            studyConsultloc:{
            	required: " * 必选！"
            },
            patientPhone : {
            	required: "格式错误！"
            }
        },
        errorElement: 'label',
        errorClass: 'help-block-new',
        focusInvalid: true,
        invalidHandler: function (event, validator) { //display error alert on form submit
            $('.alert-danger', $('.login-form')).show();
        },
//        highlight: function (e) {
//            $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
//        },
//        success: function (e) {
//            $(e).closest('.form-group').removeClass('has-error').addClass('has-info');
//            $(e).remove();
//        },
        submitHandler: function (form) {
           
        	//空值处理，避免String空值转long
            if($("#patientGlobalid").val()==""){
            	$("#patientGlobalid").val(0);
            }
            if($("#roomId").val()==""){
            	$("#roomId").val(0);
            }
            var vFlag = true;
            $("input[name='studyitemDescClone']").each(function(){    
	            if($(this).val()=="" || $(this).val() == null){
	            	vFlag = false;	            	
	            }
            }) 
            if($("#isNew").val()!="n"){
            	$("#yuyueTime").val($("input[name='periodDate']").val()+" "+$("select[name='period']").val());
            }else{
            	$("#yuyueTime").val($("input[name='periodDate']").val()+$("select[name='period']").val());
            }
            var checkTime;
            if($("[name='period']").find("option:selected").text()=="上午"){
            	checkTime = $("input[name='periodDate']").val()+" 12:00:00";
            }else if($("[name='period']").find("option:selected").text()=="中午"){
            	checkTime = $("input[name='periodDate']").val()+" 13:00:00";
            }else if($("[name='period']").find("option:selected").text()=="下午"){
            	checkTime = $("input[name='periodDate']").val()+" 18:00:00";
            }else if($("[name='period']").find("option:selected").text()=="晚上"){
            	checkTime = $("input[name='periodDate']").val()+" 23:00:00";
            }
            //预约时间不能早于当前时间
            var n=new Date();
            var year = n.getFullYear();
        	var month = n.getMonth() + 1;
        	var day = n.getDate();
        	var hour = n.getHours();
        	var minute = n.getMinutes();
        	var second = n.getSeconds();
            var currDate = year + '-' + (month<10?"0"+month:month)  + '-' + (day<10?"0"+day:day)  + ' ' +(hour<10?"0"+hour:hour)+":"+(minute<10?"0"+minute:minute)+":"+(second<10?"0"+second:second)
            if(!checkEndTime(currDate,checkTime)){
            	layer.alert('预约时间不能早于当前时间！', {icon: 5,shadeClose: true});
        		return;
            }        
            
            $("#patientAge").val($("#patientAgeShow").val()+$('#patientAgeUnit option:selected').text());
            $('#patientRegForm').ajaxSubmit({
                url: GLOBAL.WEBROOT+'/patientReg/savePatientReg.ajax',
                type: 'post',
                dataType: 'json',
                success: function (json, statusText, xhr, $form) {
                    if (json.success) {
                    	$("#studyInfoId").val(json.studyInfoId);
                    	$("#patientGlobalid").val(json.patientGlobalid);
                    	$("#patientId").val(json.patientId);
                    	$("#studyAccnumber").val(json.studyAccnumber);
                    	layer.msg('申请成功!'); 
                    	//$("#reset").click();  
//                    	 $('#saveBtn').addClass('disabled');//成功才置灰按钮
//                    	 $('#cancelbtn').removeClass('disabled');
//                    	 $('#imagebtn').removeClass('disabled');
                    	 setTimeout(function(){ 
                    		 window.location.href = GLOBAL.WEBROOT + "/patientReg/consultPatientRegInit.html?studyinfoId="+json.studyInfoId+"&patientGlobalid="+json.patientGlobalid+"&orgId="+$("#orgId").val()+"&locId="+$("#locId").val()+"&diagnoseFlag=y&hzsqFlag=y" ;
                 	     },500);	
                    	 
                    	
                    } else {
                    	layer.msg('申请失败！'); 
                    }

                }
            });
        }
    });
     
});

function saveConsult(){
	$('#patientRegForm').submit();  
}

function sbmitInfo(){
	$.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/patientReg/updateRecordStatus.ajax?studyinfoId="+$("#studyInfoId").val()+"&type=AppSave"+"&patientGlobalid="+$("#patientGlobalid").val(),
        dataType: 'json',
        contentType: 'application/json',
        success: function (data) {
            if (data.success) {
            	if(data.result=="0"){
            		$("#cancelbtn").addClass("disabled");
        		    $("#imagebtn").addClass("disabled");
        		    $("#medicalRecordbtn").addClass("disabled");
        		    $("#savebtn").addClass("disabled");
        		    $("#submitbtn").addClass("disabled");
        		    layer.msg('提交申请成功!'); 
        		    setTimeout(function(){ 
        		    	goBack();
        		    },500);	
            	}else if(data.result=="-1"){
            		layer.msg("上传病人信息失败");
            		$("#savebtn").removeClass("disabled");
        		    $("#imagebtn").removeClass("disabled");
        		    $("#medicalRecordbtn").removeClass("disabled");
        		    $("#submitbtn").removeClass("disabled");
        		    $("#cancelbtn").removeClass("disabled");
            	}else if(data.result=="-2"){
            		layer.msg("上传检查信息失败");
            		$("#savebtn").removeClass("disabled");
        		    $("#imagebtn").removeClass("disabled");
        		    $("#medicalRecordbtn").removeClass("disabled");
        		    $("#submitbtn").removeClass("disabled");
        		    $("#cancelbtn").removeClass("disabled");
            	}else if(data.result=="-3"){
            		layer.msg("上传检查项目信息失败");
            		$("#savebtn").removeClass("disabled");
        		    $("#imagebtn").removeClass("disabled");
        		    $("#medicalRecordbtn").removeClass("disabled");
        		    $("#submitbtn").removeClass("disabled");
        		    $("#cancelbtn").removeClass("disabled");
            	}else if(data.result=="-4"){
            		layer.msg("上传会诊科室信息失败");
            		$("#savebtn").removeClass("disabled");
        		    $("#imagebtn").removeClass("disabled");
        		    $("#medicalRecordbtn").removeClass("disabled");
        		    $("#submitbtn").removeClass("disabled");
        		    $("#cancelbtn").removeClass("disabled");
            	}
            }else {
                layer.msg("提交申请失败");
                $("#savebtn").removeClass("disabled");
    		    $("#imagebtn").removeClass("disabled");
    		    $("#medicalRecordbtn").removeClass("disabled");
    		    $("#submitbtn").removeClass("disabled");
    		    $("#cancelbtn").removeClass("disabled");
            }
        }
    });
}

function cancelHzrecord(){
	layer.confirm('确认取消该会诊申请？', {
		btn: ['确认', '取消'], //可以无限个按钮
        icon: 7, title: '提示',},function(){ 
			$.ajax({
		        type: "POST",
		        async: true,
		        url: GLOBAL.WEBROOT + "/patientReg/updateRecordStatus.ajax?studyinfoId="+$("#studyInfoId").val()+"&type=AppCancel",
		        dataType: 'json',
		        contentType: 'application/json',
		        success: function (data) {
		            if (data.success) {
		    	    	$("#cancelbtn").addClass("disabled");
		    		    $("#imagebtn").addClass("disabled");
		    		    $("#medicalRecordbtn").addClass("disabled");
		    		    $("#submitbtn").addClass("disabled");
		    		    layer.msg('取消申请成功!'); 
		    		    setTimeout(function(){ 
		    		    	goBack();
		    		    },500);	
		            }else {
		                layer.msg("取消失败");
		                $("#savebtn").removeClass("disabled");
		    		    $("#imagebtn").removeClass("disabled");
		    		    $("#medicalRecordbtn").removeClass("disabled");
		    		    $("#submitbtn").removeClass("disabled");
		            }
		        }
		    });
    });
}

//影像资料按钮
function imageData(){
	var patientId = $("#patientId").val();   //病人ID
    var name = $("#patientName").val(); //姓名
    var sex = $("#patientSex").val()==1?"男":"女"; //性别
    var age = $("#patientAgeShow").val()+$('#patientAgeUnit option:selected').text(); //年龄
    var accessnumber = $("#studyAccnumber").val(); //检查号
    var jcxm = ''; //检查项目
    
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

function imageView(){
		var patientid =  $("#patientId").val();   //"06009580";
	    var accessnumber = $("#studyAccnumber").val(); //"345959";
	    
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

function medicalRecordUpload(type) {
    var url = "/patientReg/medicalRecordUpload.html?locId=" + $("#locId").val() + "&orgId=" + orgId+"&studyInfoId="+$("#studyInfoId").val();
    var title = "电子病历上传";
    var index = layer.open({
        type: 2,
        title: title,
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
}

function deleteFile(fileId){
	layer.confirm('确认删除该文件吗？', {
		  btn: ['是','否'] //按钮
		}, function(){
			$.ajax({
				type: "POST",
				async: false,
				url:GLOBAL.WEBROOT +  '/patientReg/deleteFile.ajax?fileId='+fileId,
				dataType:'json',
				success:function(data){
					info("提示消息", "电子病例删除成功");
					setTimeout(window.location.reload(),2500);
				}
			}); 
		}, function(){
			 layer.close();
		});
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

//字典记录初始化
function init() {
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
	        	//修改页面中，默认选中之前的会诊机构
	        	if($("#eConsultorg").val() == sysOrg[i].conorgId){
	        		orgSelect.append("<option value='" + sysOrg[i].conorgId + "'selected>" + sysOrg[i].orgName + "</option>");
	        		setLocItem($("#eConsultorg"));
	        	}else{
	        		orgSelect.append("<option value='" + sysOrg[i].conorgId + "'>" + sysOrg[i].orgName + "</option>");
	        		setLocItem($("#eConsultorg"));
	        	}
 	        }
		}
	}); 
}
//查会诊科室
function setLocItem(obj){ 
	var orgId = $(obj).val();
	if(orgId !="" && orgId != '-1'){
		$.ajax({
		    type: "POST",
		    url: GLOBAL.WEBROOT+"/patientReg/getConsultLoc.ajax?orgId="+orgId+"&locType=C&locId="+locId,
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

//科室变动-设置医生-设置检查项目
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
	    url: GLOBAL.WEBROOT+"/patientReg/getCareProv.ajax?orgId="+orgId+"&locId="+locId,
	    success: function (data) {
	        var careProvBeans = eval(data.careProvBeans);
	        var statusSelect = $("#studyAppdoc");
	        statusSelect.empty();
	        statusSelect.append("<option value='-1'>请选择</option>");
	        for (var i = 0; i < careProvBeans.length; i++) {
	            statusSelect.append("<option value='" + careProvBeans[i].careprovId + "'>" + careProvBeans[i].careprovName + "</option>");
	        }
	    }
	});
}

//重置设备房间
function setRoomId(obj){
	var roomId = $("#equipmentId option:selected").attr("roomId")
	$("#roomId").val(roomId);
}
//去掉空格
function replace(mthis){
	/*var result=$(mthis).attr("value").replace(/(^\s*)|(\s*$)/g, "");
	$(mthis).attr("value",result); */
	var name = $(mthis).val();
	while (name.indexOf('  ') > 0) {  
		name = name.replace('  ', ' ');  
    }
	$(mthis).val($.trim(name));
}
 
function setSexSelected(sexV){ 
	$("#patientSex").select2("val", sexV);
}
function setPatienttypeCode(typeCode){ 
	$("#patienttypeCode").select2("val", typeCode);
}
function setStudyApplocid(sqksdm){ 
	$("#studyApplocid").select2("val", sqksdm);
}
function setAgeUnitSelected(unit){
//	debugger;
	var unitVal;
	if(unit=="岁"){
		unitVal = 1;
	}else if(unit=="月"){
		unitVal = 2;
	}else if(unit=="天"){
		unitVal = 3;
	}
	$("#patientAgeUnit").select2("val", unitVal);
}

//当字转拼音
function ename(){	
	var patientName = $('#patientName').val();
	var patientEname=getLettersUpper(patientName);
	$('#patientNamepy').val(patientEname);
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
				 ageUnit = 3;
				 returnAge = 0;
			 }else{
				 ageUnit = 3;
				 returnAge = nowDay - birthDay; 
			 }
		 }else{
			 ageUnit = 2
			 returnAge = nowMonth - birthMonth; 
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
				var monthDiff = nowMonth - birthMonth;// 月之差
				if (monthDiff < 0) {
					returnAge = ageDiff - 1;
				}else if(monthDiff<12){
					monthDiff = 1;
					returnAge = ageDiff; 
				} else {
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
	}
	$("#patientAge").val(returnAge+u);
}

//登记信息选择
function getAllReg(obj){
	var patientName = $(obj).val();
	  $.ajax({
	        type: "POST",
	        url: GLOBAL.WEBROOT+"/patientReg/getPatients.ajax?patientName="+encodeURI(encodeURI(patientName)) ,
	        success: function (data) {
		       var total = eval(data.total);
		       if(total>0){
		    	   layer.open({
		    	        type: 2,
		    	        title:"病人登记记录",
		    	        area: ['800px', '500px'],
		    	        fix: false, //不固定
		    	        maxmin: true,
		    	        content: GLOBAL.WEBROOT + "/patientReg/patientRegList.html?patientName="+encodeURI(encodeURI(patientName))
		    	    });
		       }
	        }
	    });
}
//调基层HIS查登记信息
function getHisStudyInfo(){
	var queryKey = $("#queryKey").val();
	var queryValue = $("#queryValue").val();
	if(queryKey == "-1"){
		layer.alert('请选择查询条件！', {icon: 5,shadeClose: true});
		return false;
	}
	if(queryValue==""){
		layer.alert('请输入查询值！', {icon: 5,shadeClose: true});
		return false;
	}	
	$.ajax({
        type: "POST",
        //查询是否有his数据视图
        url: GLOBAL.WEBROOT+"/patientReg/getHisStudyInfoCount.ajax?orgId="+orgId+"&queryKey="+queryKey+"&queryValue="+queryValue+"&locId="+$("#locId").val()  ,
        //有则进入his数据视图编辑页面 hisStudyInfo.html
        success: function (data) {
	       var total = eval(data.total);
	       //杨森 将 > 改为 = 
	       if(total=0){
	    	   layer.open({
	    	        type: 2,
	    	        title:"HIS病人登记记录",
	    	        area: ['800px', '500px'],
	    	        fix: false, //不固定
	    	        maxmin: true,
	    	        content: GLOBAL.WEBROOT + "/patientReg/hisStudyInfo.html?orgId="+orgId+"&queryKey="+queryKey+"&queryValue="+queryValue+"&locId="+$("#locId").val() 
	    	    });
	       }else{
	    	   layer.alert('无此查询条件的记录！', {icon: 5,shadeClose: true});  
	       }
        }
    });  
}

//返回到工作列表
function goBack(){
	window.location.href = GLOBAL.WEBROOT+'/workList/init.html';
	//左侧菜单收缩 --已隐藏不点击   
    if(!$("#menuTree",parent.document).is(":hidden")){
    	parent.switchBarl();
    }
}

function downloadFile(fileName){
	 window.location.href = GLOBAL.WEBROOT + "/studyReport/downloadPdf?fileName="+fileName+"&oldName="+fileName.substr(0,32);
}

function time_range(beginTime, endTime, nowTime){
	  var strb = beginTime.split (":");
	  if (strb.length != 2) {
	    return false;
	  }
	 
	  var stre = endTime.split (":");
	  if (stre.length != 2) {
	    return false;
	  }
	 
	  var strn = nowTime.split (":");
	  if (stre.length != 2) {
	    return false;
	  }
	  var b = new Date ();
	  var e = new Date ();
	  var n = new Date ();
	 
	  b.setHours (strb[0]);
	  b.setMinutes (strb[1]);
	  e.setHours (stre[0]);
	  e.setMinutes (stre[1]);
	  n.setHours (strn[0]);
	  n.setMinutes (strn[1]);
	 
	  if (n.getTime () - b.getTime () > 0 && n.getTime () - e.getTime () < 0) {
	    return true;
	  } else {
	    //alert ("当前时间是：" + n.getHours () + ":" + n.getMinutes () + "，不在该时间范围内！");
	    return false;
	  }
	}

function Appendzero (obj) {
	if (obj < 10) return "0" + obj; else return obj;
}