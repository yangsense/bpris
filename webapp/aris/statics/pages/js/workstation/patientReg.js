
//组织机构ID
var orgId = ""; 
var locId = "";
var flag = false;
var optionForMulti;
$(function () {
	
	if($("#isNew").val()!='y'){
		$("#patientName").focus();
	}
	
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
			}else if($("#patientAgeUnit").val()=="4"){
				var day = Math.floor( $("#patientAgeShow").val()/24);
				if(day>=1){
					$("input[name=patientDob]").val(GetDateStr(-day));
				}else{
					$("input[name=patientDob]").val(GetDateStr(-day));
				}
			}
			$("#patientAge").val($("#patientAgeShow").val()+$('#patientAgeUnit option:selected').text());
		}
	});
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
	
//	$("#patientAgeUnit").onclick();
	
	if($("#isNew").val()=='y'){
		var n=new Date();
	    n.setDate(n.getDate());
	    $("input[name=periodDate]").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
	    
	    $("#queryKey").val("0");
	    if($("#equipmentId option").length>1){
	    	$("#equipmentId").get(0).selectedIndex = 1;  
	    }
	}else{
		flag = true;
		//隐藏his查询条件及按钮
	    $("#hisSelectDiv").hide();
	}
	
	
    //取组织机构
	orgId = $("#orgId" , parent.document).val();  
	$("#orgId").val(orgId);
	
	var locId = $("#studyApplocid option:selected").val();
	if(locId!=""){
		setCareProv(orgId,locId);
	}
	
    //字典记录初始化
    init(); 
    
    //所有select嵌套css样式
    $("select").select2();  
    
    //检查项目设置
    //locId = $("#studyApplocid option:selected").val();
    locId = $("#locId").val();//工作列表页面的科室
    
    //setStudyItem(orgId,locId);
    //检查部位设置
//    var itemmastId = $("#studyitemDesc_1 option:selected").val();
    //setBodyPart(orgId,itemmastId);
    //序列设置
    //setSeq(orgId,locId);
    
    //美化检查项目描述，增加ajax请求功能
    //判断是新增模式还是修改模式
    if($("#isNew").val()=='n'){
    	//是否加急赋值
    	$("#isUrgent").val($("#isUrgentOld").val());
    	$("#isUrgent").select2();
    	//若年龄是否为小时----手动赋值
    	if($("#patientAgeOld").val().substr($("#patientAgeOld").val().length-2)=="小时"){
    		$("#patientAgeUnit").val("4");
    		$("#patientAgeUnit").select2();
    		$("#patientAgeShow").val($("#patientAgeOld").val().substr(0,$("#patientAgeOld").val().length-2));
    		$("#patientAge").val($("#patientAgeShow").val()+$('#patientAgeUnit option:selected').val());
    	}else if($("#patientAgeOld").val().substr($("#patientAgeOld").val().length-1)=="天"){
    		$("#patientAgeUnit").val("3");
    		$("#patientAgeUnit").select2();
    	}else if($("#patientAgeOld").val().substr($("#patientAgeOld").val().length-1)=="月"){
    		$("#patientAgeUnit").val("2");
    		$("#patientAgeUnit").select2();
    	}else if($("#patientAgeOld").val().substr($("#patientAgeOld").val().length-1)=="岁"){
    		$("#patientAgeUnit").val("1");
    		$("#patientAgeUnit").select2();
    	}
    	
    	if($("#patienttypeCode").val()=="INP"){
    		$(".ret_span_INP").show();
    		$(".ret_span_OP").hide();
    		$(".ret_span_HP").hide();
    		$("#hpnum").text("住院号：");
    	}else if($("#patienttypeCode").val()=="HP"){
    		$(".ret_span_HP").show();
    		$(".ret_span_INP").hide();
    		$(".ret_span_OP").hide();
    		$("#hpnum").text("体检号：");
    	}else if($("#patienttypeCode").val()=="OP"){
    		$(".ret_span_OP").show();
    		$(".ret_span_HP").hide();
    		$(".ret_span_INP").hide();
    		$("#hpnum").text("体检号：");
    	}else{
    		$(".ret_span_OP").hide();
    		$(".ret_span_HP").hide();
    		$(".ret_span_INP").hide();
    	}
    	
    	//检查项目回填
    	setStudyItemInit();   
    }else{
    	var trcp = $("#configTab").find("tr");
		var html = "<tr tag='studyItem'>    "+
				"		            <td width='18%' align = 'center'>           "+
				"		                <div  name='studyitemDesc_1' style='width:360px;' onclick='setNewBigbodyPart(this,\"\")'>        "+
				"		                </div>"+
				"		                <input type='hidden' name='studyitemDescClone_1' id='studyitemDescClone_1' />		   "+             
				"		            </td>  "+
				"		             <td width='20%'>"+
				"<input id='qryInput_1' name='qryInput_1' type='text' value='搜索' onFocus='if(value==defaultValue){value=\"\";this.style.color=\"#000\"}' onBlur='if(!value){value=defaultValue; this.style.color=\"#999\"};qryinitBodyTree(this)' style='color:#999;width:80px' />"+
				"  		<input id='bodypartNames_1' type='text' readonly=readonly class='select' style='width: 350px;' name='bodypartNames_1'  />"+
				"     	<input id='bodypartCodes_1' type='hidden' readonly name='bodypartCodes_1'/>"+
				"                    </td>  "+
				"		            <td width='4%' align = 'center'>"+
				"		                 <input id='studyitemNumber_1' name='studyitemNumber_1' class='inputW160' readonly onkeyup='value=value.replace(/[^\\.]{2,}/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
				"		            </td>        "+
				"		            <td width='4%' align = 'center'align = 'center'>"+
				"		                 <input id='studyitemPrice_1' name='studyitemPrice_1' class='inputW160' readonly onkeyup='value=value.replace(/[^\d\.]/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
				"		            </td> "+
				"		            <td width='8%' class='c'>   "+
				"		                <button  id='add' type='button' onclick='addStudyItem()'>增加</button>"+
				"		                <button  id='deleBtn_1' type='button' onclick='delStudyItem(this)'>删除</button>"+
				"		            </td>"+
				"</tr>";
		trcp.after(html);
		initSelect2forDiv($("div[name='studyitemDesc_1']"));
    }   
     	
    
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
//            patientDob:{
//            	required: true
//            },  
            studyWard:{
            	checkStudyWard:true
            }
//            ,  
//            studyApplocid:{
//            	checkStudyApplocid:true   //-1处理，如果值为-1，则删除form中的标签
//            },  
//            studyAppdoc:{
//            	checkStudyAppdoc:true
//            },  
//            equipmentId:{
//            	checkEquipmentId:true
//            },  
//            paymenttypeId:{
//            	checkPaymenttypeId:true
//            },  
//            studylevelId:{
//            	checkStudylevelId:true
//            },  
//            studyConsultorg:{
//            	checkStudyConsultorg:true
//            },  
//            studyConsultloc:{
//            	checkStudyConsultloc:true
//            }
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
//            patientDob:{
//            	required: " * 非空！"
//            }
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
        	$('#saveBtn').addClass('disabled');//置灰保存按钮，避免重复提交
        	
        	//空值处理，避免String空值转long
            if($("#patientGlobalid").val()==""){
            	$("#patientGlobalid").val(0);
            }
            if($("#roomId").val()==""){
            	$("#roomId").val(0);
            }
            //检查项目不能为空校验
//            var vFlag = true;
//            $("input[name='studyitemDescClone']").each(function(){    
//	            if($(this).val()=="" || $(this).val() == null){
//	            	vFlag = false;	            	
//	            }
//            }) 
            if($("#isNew").val()!="n"){
            	$("#yuyueTime").val($("input[name='periodDate']").val()+" "+$("select[name='period']").val());	
            }else{
            	$("#yuyueTime").val($("input[name='periodDate']").val()+$("select[name='period']").val());
            }
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
            		$('#saveBtn').removeClass('disabled');
            		return;
            	}
            	if($("#studyBedno").val()==""){
            		layer.alert('请填写床号', {icon: 5,shadeClose: true});
            		$("#studyBedno").focus();
            		$('#saveBtn').removeClass('disabled');
            		return;
            	}
            	if($("#patientInpatientid").val()==""){
            		layer.alert('请填写住院号', {icon: 5,shadeClose: true});
            		$("#patientInpatientid").focus();
            		$('#saveBtn').removeClass('disabled');
            		return;
            	}
            	
        	}else if($("#patienttypeCode").val()=="OP"){
        		if($("#studyApplocid").val()=="-1"){
        			layer.alert('请选择申请科室', {icon: 5,shadeClose: true});
            		$('#saveBtn').removeClass('disabled');
            		return;
        		}
        	}else if($("#patienttypeCode").val()=="HP"){
        		if($("#patientInpatientid").val()==""){
            		layer.alert('请填写体检号', {icon: 5,shadeClose: true});
            		$("#patientInpatientid").focus();
            		$('#saveBtn').removeClass('disabled');
            		return;
            	}
        	}else{
        		
        	}
            if(($("#studyConsultorg").val()!=undefined&&$("#studyConsultloc").val()!=undefined)&&$("#studyConsultorg").val()!="-1"&&$("#studyConsultloc").val()!="-1"){
            	$("#isConsult").val("y");
            }
            
//            if($("input[name='studyitemDescClone']").val()==""||$("input[name='studyitemDescClone']").val().indexOf("undefined")!=-1){
//            	layer.alert('请选择检查项目', {icon: 5,shadeClose: true});
//        		$('#saveBtn').removeClass('disabled');
//        		return;
//            }
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
        		$('#saveBtn').removeClass('disabled');
        		return;
        	}
            var arrayBodystrs = JSON.stringify(arrayBody);
            
        	$("#patientAge").val($("#patientAgeShow").val()+$('#patientAgeUnit option:selected').text());
//        	alert($("input[name='studyitemBodyinfoClone']").val());
            $('#patientRegForm').ajaxSubmit({
                url: GLOBAL.WEBROOT+'/patientReg/savePatientReg.ajax?arrayBodystrs='+arrayBodystrs,
                type: 'post',
                dataType: 'json',
                success: function (json, statusText, xhr, $form) {
                    if (json.success) {
                    	$("#studyInfoId").val(json.studyInfoId);
                    	$("#patientGlobalid").val(json.patientGlobalid);
                    	$("#patientId").val(json.patientId);
                    	$("#studyAccnumber").val(json.studyAccnumber);
                    	//layer.msg('保存成功！'); 
                    	//$("#reset").click();  
                    	 $('#saveBtn').addClass('disabled');//成功才置灰按钮
                    	 flag = true;
                    	 
                    	 layer.confirm('保存成功！', {
                             btn: ['继续登记','打印','返回'], //可以无限个按钮
                             btn1: function (index, layero) {
                            	 window.location.href = GLOBAL.WEBROOT + "/patientReg/patientRegPageinit.html?studyinfoId="+json.studyInfoId+"&patientGlobalid="+json.patientGlobalid+"&orgId="+$("#orgId").val()+"&locId="+$("#locId").val()+"&diagnoseFlag=y&again=y" ;
                             },
                             btn2: function (index, layero) {
                            	 //打印，并跳到检查列表页
                     			regPrint();  
                             },
                             btn3: function (index, layero) {
                            	//跳转到检查列表页面
                     			goBack();
                             }
                    	 });
                    	
                    } else {
                    	$('#saveBtn').removeClass('disabled');//回轩保存按钮
                    	layer.msg('保存失败！'); 
                    }

                }
            });
        }
    });
});

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

function GetDateStr(AddDayCount) 
{ 
	var dd = new Date(); 
	dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期 
	var y = dd.getFullYear(); 
	var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0
	var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate(); //获取当前几号，不足10补0
	return y+"-"+m+"-"+d; 
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

function readCard(){
	
    var msg = readOCX.readPublicInfo('{"requestInfo": {"departmentCode": "KSDM","departmentName": "KSMC","operatorCode": "CZRDM","operatorName": "CZRXM"}}');
    var obj = $.parseJSON(msg);
    var result = obj.responseInfo;
    if (result.isSuccess != "0") {
        info('温馨提示', result.errMsg);
        return;
    }
    var cardInfo = obj.businessInfo;
    var chipSerialNo = cardInfo.chipSerialNo;
    $("#patientName").val(cardInfo.name);
    ename();
    $("#patientIdnumber").val(cardInfo.identityNo);
    $("#patientDob").val(getBirthdatByIdNo(cardInfo.identityNo));
	getAge($("#patientDob"));
    
	var msg11 = readOCX.CommonReadDF02Base("435885557", "旬邑", "1001", "儿科");
	if(msg11.split("|")[0]=="0"){
		$("#patientPhone").val(msg11.split("|")[8].split(",")[2]);
		$("#patientAddress").val(msg11.split("|")[8].split(",")[3]);
	    setSexSelected(msg11.split("|")[8].split(",")[0]);
	}
    
}

function trim(s) { return s.replace(/^\s+|\s+$/g, ""); };
//验证身份证号并获取出生日期
function getBirthdatByIdNo(iIdNo) {
var tmpStr = "";
var idDate = "";
var tmpInt = 0;
var strReturn = "";

iIdNo = trim(iIdNo);

if ((iIdNo.length != 15) && (iIdNo.length != 18)) {
strReturn = "输入的身份证号位数错误";
return strReturn;
}

if (iIdNo.length == 15) {
tmpStr = iIdNo.substring(6, 12);
tmpStr = "19" + tmpStr;
tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)

return tmpStr;
}
else {
tmpStr = iIdNo.substring(6, 14);
tmpStr = tmpStr.substring(0, 4) + "-" + tmpStr.substring(4, 6) + "-" + tmpStr.substring(6)

return tmpStr;
}
}





function Appendzero (obj) {
	if (obj < 10) return "0" + obj; else return obj;
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

//比较时间大小
function checkEndTime(startTime,endTime){   
    var start=new Date(startTime.replace("-", "/").replace("-", "/"));  
    var end=new Date(endTime.replace("-", "/").replace("-", "/"));  
    if(end<start){  
        return false;  
    }  
    return true;  
}  

function setHisItemmast(itemmastId,itemmaseCode,itemmastDesc,itemPrice){
	var tr = $("#configTab").find("[tag=studyItem]:last");
//	var olddiv= trcp.find("div[name='studyitemDesc']").prev("div");
//	olddiv.remove();	
//	trcp.find("input[name='studyitemBodyinfoClone']").prev("div").remove();
//	//重新渲染检查项目描述
//	initSelect2forDiv(trcp.find("div[name='studyitemDesc']"));
//	trcp.find("div[name='studyitemDesc']").prev("div").find("a").removeClass("select2-default");
	//值回填
	//检查项目
	if(itemmastId==""||itemmastId==null||itemmastId=="undefined"||itemmastId==undefined){
		tr.find("span[class='select2-chosen']").html("选择检查项目");
		tr.find("input[name='studyitemDescClone_1']").val("");
		tr.find("[name=studyitemNumber_1]").val("");
		tr.find("[name=studyitemPrice_1]").val("0"); 
	}else{
		tr.find("span[class='select2-chosen']").html(itemmastId+"_"+itemmastDesc);
		tr.find("input[name='studyitemDescClone_1']").val(itemmastId+"_"+itemmastDesc);
		tr.find("[name=studyitemNumber_1]").val(1);
		tr.find("[name=studyitemPrice_1]").val(itemPrice); 
		setNewBigbodyPartByUpdate("studyitemDescClone_1");
	}

}

//检查项记录回填
function setStudyItemInit(){
	//加载检查项信息
	$.ajax({
	    type: "POST",
	    async: true,
	    url: GLOBAL.WEBROOT+"/patientReg/getStydyItemInfo.ajax?studyinfoId="+$("#studyInfoId").val(),
	    success : function(data) { 			 
			$.each(data.studyItemInfoBeans, function(i, obj) { 
					var trcp = $("#configTab").find("tr:last");
					var html = "<tr tag='studyItem'>    "+
							"		            <td width='18%' align = 'center'>           "+
							"		                <div  name='studyitemDesc_"+(i+1)+"' style='width:360px;' onclick='setNewBigbodyPart(this,\"\")'>        "+
							"		                </div>"+
							"		                <input type='hidden' name='studyitemDescClone_"+(i+1)+"' id='studyitemDescClone_"+(i+1)+"' />		   "+     
							"<input type='hidden' name='price' value="+obj.studyitemPrice+" id=\"price"+obj.studyitemCode+"\">"+
							"		            </td>  "+
							"		             <td width='20%'>"+
							"<input id='qryInput_"+(i+1)+"' name='qryInput_"+(i+1)+"' type='text' value='搜索' onFocus='if(value==defaultValue){value=\"\";this.style.color=\"#000\"}' onBlur='if(!value){value=defaultValue; this.style.color=\"#999\"};qryinitBodyTree(this)' style='color:#999;width:80px' />"+
							"  		<input id='bodypartNames_"+(i+1)+"' type='text' readonly=readonly class='select' style='width: 350px;' name='bodypartNames_"+(i+1)+"'  />"+
							"     	<input id='bodypartCodes_"+(i+1)+"' type='hidden' readonly name='bodypartCodes_"+(i+1)+"'/>"+
							"                    </td>  "+
							"		            <td width='4%' align = 'center'>"+
							"		                 <input id='studyitemNumber_"+(i+1)+"' name='studyitemNumber_"+(i+1)+"' class='inputW160' readonly onkeyup='value=value.replace(/[^\\.]{2,}/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
							"		            </td>        "+
							"		            <td width='4%' align = 'center'align = 'center'>"+
							"		                 <input id='studyitemPrice_"+(i+1)+"' name='studyitemPrice_"+(i+1)+"' class='inputW160' readonly onkeyup='value=value.replace(/[^\d\.]/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
							"		            </td> "+
							"		            <td width='8%' class='c'>   "+
							"		                <button  id='add' type='button' onclick='addStudyItem()'>增加</button>"+
							"		                <button  id='deleBtn_"+(i+1)+"' type='button' onclick='delStudyItem(this)'>删除</button>"+
							"		            </td>"+
							"</tr>";
					trcp.after(html);
					
					initSelect2forDiv($("div[name='studyitemDesc_"+(i+1)+"']"));
					
					setNewBigbodyPartByUpdate("studyitemDescClone_"+(i+1)+"");
					if(obj.studyEnitemdesc==undefined){
						$("input[name='studyitemDescClone_"+(i+1)+"']").prev().prev().find("a span.select2-chosen").text(obj.studyitemCode+"_"+obj.studyitemDesc);
						$("input[name='studyitemDescClone_"+(i+1)+"']").val(obj.studyitemCode+"_"+obj.studyitemDesc);			
					}else{
						$("input[name='studyitemDescClone_"+(i+1)+"']").prev().prev().find("a span.select2-chosen").text(obj.studyitemCode+"_"+obj.studyitemDesc+"_"+obj.studyEnitemdesc);
						$("input[name='studyitemDescClone_"+(i+1)+"']").val(obj.studyitemCode+"_"+obj.studyitemDesc+"_"+obj.studyEnitemdesc);	
					}
					//$("input[name='price']").val(obj.studyitemPrice);
					$("input[name='bodypartCodes_"+(i+1)+"']").val(obj.studyitemBodypart);
					$("input[name='studyitemNumber_"+(i+1)+"']").val(obj.studyitemNumber);
					$("input[name='studyitemPrice_"+(i+1)+"']").val(obj.studyitemPrice);
					$("input[name='bodypartNames_"+(i+1)+"']").val(obj.studyitemBodyinfo);
//					$("input[name='bodypartNames_"+(i+1)+"']").live("click",function(){
//						setNewBigbodyPartByUpdate("studyitemDescClone_"+(i+1)+"");
//					})
					
					//qryinitBodyTree();
			});	
			if(data.studyItemInfoBeans!=""&&data.studyItemInfoBeans!=null){
				//$("#configTab").find("[tag=studyItem]").eq(0).remove();	
			}
		},
		error : function() {
			alert("请求超时，请稍候再试!");
		}
    });
}
//提供回显使用
//function getBodyPartByItemmastId(itemmastId,arrayObj){
//	var bodyInfoOption= "";
//	$.ajax({
//        type: "POST",
//        async: false,
//        url: GLOBAL.WEBROOT+"/patientReg/getBodyPart.ajax?orgId="+orgId+"&itemmastId="+itemmastId,
//        success: function (data) { 
//		var bodyPartBeans = eval(data.bodyPartBeans);
//            for (var i = 0; i < bodyPartBeans.length; i++) {
//            	if(arrayObj[i]==bodyPartBeans[i].bodypartDesc){
//            		bodyInfoOption+="<option value='"+bodyPartBeans[i].bodypartDesc+"' disabled='disabled'>"+bodyPartBeans[i].bodypartDesc+"</option>"
//            	}else{
//            		bodyInfoOption+="<option value='" + bodyPartBeans[i].bodypartDesc + "' >" + bodyPartBeans[i].bodypartDesc + "</option>";
//            	}            	
//            }
//	        }
//    });
//	return bodyInfoOption; 
//}
////部位自定议删除
//function removeMe(obj){
//	var bodyInfo = $(obj).parent().parent().parent().siblings("select");
//	$(bodyInfo).find('option').each(function (){  
//		if($(this).val()==$(obj).attr("v")){
//			$(this).removeAttr("disabled");
//		}
//	});
//	$(obj).parent("li").remove();  
//}
 
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

//科室变动-设置医生-设置检查项目
function changeCareProv(obj){
	var locId = $("#studyApplocid option:selected").val();
	setCareProv(orgId,$(obj).val());
	//setStudyItem(orgId,locId);//检查项
	//setSeq(orgId,locId);
}

function setCareProvHis(orgId,locCode,ysdm){
	$.ajax({
	    type: "POST",
	    async: true,
	    url: GLOBAL.WEBROOT+"/basecode/getLocId.ajax?orgId="+orgId+"&locCode="+locCode,
	    success: function (data) {
	    	$("#studyApplocid").val(data.LOC_ID); 
	    	setCareProvDoc(orgId,data.LOC_ID,ysdm);
	    }
	});
}


function setCareProvDoc(orgId,locId,ysdm){
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
	        	if(ysdm==careProvBeans[i].careprovCode){
	        		statusSelect.append("<option value='" + careProvBeans[i].careprovId + "' selected>" + careProvBeans[i].careprovName + "</option>");
	        	}else{
	        		statusSelect.append("<option value='" + careProvBeans[i].careprovId + "'>" + careProvBeans[i].careprovName + "</option>");
	        	}
	        }
	        statusSelect.select2();
	    }
	});
}

//function setCareporveDoc(orgId,ysdm){
//	$.ajax({
//	    type: "POST",
//	    async: true,
//	    url: GLOBAL.WEBROOT+"/basecode/getCareprovId.ajax?orgId="+orgId+"&careprovCode="+ysdm,
//	    success: function (data) {
//	    	$("#studyAppdoc").val(data.CAREPROV_ID); 
//	    }
//	});
//}

//序列初始化
function setSeq(orgId,locId){ 
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT+"/patientReg/getSeq.ajax?orgId="+orgId+"&locId="+locId,
        success: function (data) {
            var seq = eval(data);
            $("#patientId").val(seq.patientId);
            $("#patientGlobalid").val(seq.patientGlobalid);
            $("#studyAccnumber").val(seq.studyAccnumber);
        }
    });
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

//检查项目设置
//function setStudyItem(orgId,locId){
//	//检查项目
//    $.ajax({
//        type: "POST",
//        url: GLOBAL.WEBROOT+"/patientReg/getStudyItem.ajax?orgId="+orgId+"&locId="+locId,
//        success: function (data) {
//            var studyItemBeans = eval(data.studyItemBeans);
//            var statusSelect = $("#studyitemDesc");
//            statusSelect.empty();
//            statusSelect.append("<option value='-1'>请选择</option>");
//            for (var i = 0; i < studyItemBeans.length; i++) {
//                statusSelect.append("<option value='" + studyItemBeans[i].itemmastId + "_" + studyItemBeans[i].itemmastDesc + "' price="+studyItemBeans[i].itemmastPrice+" >" + studyItemBeans[i].itemmastDesc + "</option>");
//            } 
//        }
//    });    
//}
//检查项目变动--重置检查部位
function changeBodyPart(obj){
	setBodyPart(orgId,$(obj).val(),obj); 
}
//检查部位设置
function setBodyPart(orgId,itemmastId,obj){
	$(obj).parent().parent().find("input[name='studyitemBodyinfoClone']").val("");
	try {
		var mastId = itemmastId.split("_")[0];
		$(obj).next("input[name=studyitemDescClone]").val(itemmastId);
	} catch (e) { 
	}
	
	 $(obj).parent().parent().find("input[name='studyitemNumber']").val(1);
     $(obj).parent().parent().find("input[name='studyitemPrice']").val($(obj).prev("div").find("input[name='price']").val());
     $(obj).parent().parent().find("input[name='studyitemBodyinfoClone']").select2({
        placeholder          : "请选择部位",
        multiple             : true,
        separator            : ",",   
        maximumSelectionSize : 8,     // 限制数量
        id : function(repo) { //这个很重要， 没有这个就选择不了值
        	
            return repo.bodypartCode+"_"+repo.bodypartDesc+"_"+itemmastId.split("_")[0];  
        },
        initSelection        : function (element, callback) {   // 初始化时设置默认值
            var data = [];
            $(element.val().split(",")).each(function () {
                data.push({ bodypartCode: this, bodypartDesc: this });
            });
            callback(data);
        },
        createSearchChoice   : function(term, data) {           // 创建搜索结果（使用户可以输入匹配值以外的其它值）
        	return { bodypartDesc: term};
	    },
        formatSelection : function (item) { return item.bodypartDesc; },  // 选择结果中的显示
        formatResult    : function (item) { return item.bodypartDesc; },  // 搜索列表中的显示
        ajax: {
            url: GLOBAL.WEBROOT+"/patientReg/getBodyPart.ajax?orgId="+orgId+"&itemmastId="+mastId,
            dataType: 'json',
            method:'post',
            data     : function (term, page) {  // 请求参数（GET）
                return { q: term };
            },
            results: function (data, page) {
            	 return {results: data.bodyPartBeans};
            },
            escapeMarkup : function (m) { return m; }          
        }
    });
    
}

//部位值添加(解决多个检查项目时 且检查部位多选的情况下，值显示为：手部，胸部，头部，其中头部是第二条记录的，但提交时未区分开)
//function addBodyPartValue(obj){
//   var bpValues = "";
//   var bpArray = $(obj).select2("val");  
//    for(var i=0;i<bpArray.length;i++){ 
//    	bpValues += bpValues == ""? bpArray[i]:"、" + bpArray[i];
//    }	
//	$(obj).next("input[name=studyitemBodyinfoClone]").val(bpValues);
//}

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
	}else if(unit=="小时"){
		unitVal = 4;
	}
	$("#patientAgeUnit").select2("val", unitVal);
}

//当字转拼音
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
//	var patientEname=getLettersUpper(patientName);
	
}

var mastId ="";
var new_arr=[];
//添加检查明细项
function addStudyItem(){    
	isqs+=1;
	var tr = $("#configTab").find("tr:last");
	if(tr.find("[name='studyitemDesc_"+isqs+"']").val()!=""||$("#isNew").val()=="n"||$("#isHis").val()=="y"){
		tr.after("<tr tag='studyItem'>    "+
				"		            <td width='18%' align = 'center'>           "+
				"		                <div  name='studyitemDesc_"+isqs+"' style='width:360px;' onclick='setNewBigbodyPart(this,\"\")'>        "+
				"		                </div>"+
				"		                <input type='hidden' name='studyitemDescClone_"+isqs+"' id='studyitemDescClone_"+isqs+"' />		   "+             
				"		            </td>  "+
				"		             <td width='20%'>"+
				"<input id='qryInput_"+isqs+"' name='qryInput_"+isqs+"' type='text' value='搜索' onFocus='if(value==defaultValue){value=\"\";this.style.color=\"#000\"}' onBlur='if(!value){value=defaultValue; this.style.color=\"#999\"};' onchenge='qryinitBodyTree(this)' style='color:#999;width:80px' />"+
				"                            <input id='bodypartNames_"+isqs+"' type='text' readonly=readonly class='select' style='width: 350px;'"+
				"                                   name='bodypartNames_"+isqs+"' />"+
				"                            <input id='bodypartCodes_"+isqs+"' type='hidden' readonly name='bodypartCodes_"+isqs+"'/>"+
				"                    </td>  "+
				"		            <td width='4%' align = 'center'>"+
				"		                 <input id='studyitemNumber_"+isqs+"' name='studyitemNumber_"+isqs+"' class='inputW160' readonly onkeyup='value=value.replace(/[^\\.]{2,}/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
				"		            </td>        "+
				"		            <td width='4%' align = 'center'align = 'center'>"+
				"		                 <input id='studyitemPrice_"+isqs+"' name='studyitemPrice_"+isqs+"' class='inputW160' readonly onkeyup='value=value.replace(/[^\d\.]/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
				"		            </td> "+
				"		            <td width='8%' class='c'>   "+
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
				var monthDiff = nowMonth+12 - birthMonth;// 月之差
				if (monthDiff > 12) {
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

//打印
function regPrint(){
	var studyInfoId = $("#studyInfoId").val();
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

//登记信息选择
function getAllReg(obj){
	var patientName = $(obj).val();
	
	if(!flag){
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
        url: GLOBAL.WEBROOT+"/patientReg/getHisStudyInfoCount.ajax?orgId="+orgId+"&queryKey="+queryKey+"&queryValue="+queryValue+"&locId="+$("#locId").val(),
        success: function (data) {
	       var total = eval(data.total);
	       if(total>0){
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


function setNewBigbodyPartByUpdate(str){
	var itemmastId= $("input[name='"+str+"']").val();
	var id = itemmastId.substr(0,itemmastId.indexOf("_"));
	var lastindex = str.substr(str.indexOf("_"));
	var treeids= lastindex;
//	try {
//		//$(obj).next("input[name=studyitemDescClone"+treeids+"]").val(itemmastId);
//	} catch (e) { 
//	}
	
	optionForMulti = {
			dataStructure: {
				idKey: "bodypartCode",
				pIdKey: "bodypartPid",
				rootPId: "-9999",
				name: "bodypartDesc"
			},
			data: {
				url: GLOBAL.WEBROOT + "/patientReg/getBodypartTree.ajax?itemmastId="+id+"&orgId="+orgId+"&bodypartDesc=",    //获取数据的URL
				param: ["orgId"]  //获取数据的参数
			},
			rootNode: [{bodypart2Id:"-9999", bodypartDesc: "部位",bodypartCode: "-9999", open: true, isParent: true, nocheck: false}]
		};
	
	com.ai.bdx.util.ztreeComp("bodypartNames"+treeids, true,optionForMulti,callFuncForMultiQuery);
	
//	$("input[name='studyitemNumber"+treeids+"']").val(1);
//    $("input[name='studyitemPrice"+treeids+"']").val($("#price"+id).val());
	
}

