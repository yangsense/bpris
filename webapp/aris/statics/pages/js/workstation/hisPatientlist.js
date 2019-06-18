//组织机构ID
var orgId = ""; 
$(function () {
	
	orgId = $("#orgId" , parent.document).val(); 	
	selectList();    
     
	$("#closeBtn").click(function(){
	       closeDiv();
	});
	
	$("#saveBtn").click(function(){
		//回填信息
		var gr = $("#hisPatientlist").jqGrid('getGridParam','selarrrow');
	    if(gr.length>0) {
	    	var rowtr = $("#hisPatientlist").jqGrid('getRowData',gr[0]);	
	    	//打yn标识
			$.ajax({
			    type: "POST",
			    async: false,
			    url: GLOBAL.WEBROOT+"/patientReg/validatePatient.ajax?patientMobile="+rowtr.lxdh+"&patientPhone="+rowtr.lxdh+"&patientIdnumber="+rowtr.sfzh,
			    success: function (data) {
				   if(data.success){
					 $("#isNew",parent.document).val("yn");  
					 $("#patientGlobalid",parent.document).val(data.patientGlobalid);  
					 $("#patientId",parent.document).val(data.patientId);  
				   }
			    }
			}); 
	    	
	        setPatientInfo();
	    }
		
	});
});
function setPatientInfo(){
//	parent.$("#configTab").find("[tag=studyItem]:last").after("<tr tag='studyItem'>    "+
//			"		            <td width='18%' align = 'center'>           "+
//			"		                <div  name='studyitemDesc_"+isqs+"' style='width:360px;' onclick='setNewBigbodyPart(this)'>        "+
//			"		                </div>"+
//			"		                <input type='hidden' name='studyitemDescClone_"+isqs+"' id='studyitemDescClone_"+isqs+"' />		   "+             
//			"		            </td>  "+
//			"		             <td width='18%'>"+
//			"                            <input id='bodypartNames_"+isqs+"' type='text' readonly=readonly class='select' style='width: 350px;'"+
//			"                                   name='orgNames' />"+
//			"                            <input id='bodypartCodes_"+isqs+"' type='hidden' readonly name='bodypartCodes_"+isqs+"'/>"+
//			"                    </td>  "+
//			"		            <td width='10%' align = 'center'>"+
//			"		                 <input id='studyitemNumber_"+isqs+"' name='studyitemNumber_"+isqs+"' class='inputW160' readonly onkeyup='value=value.replace(/[^\\.]{2,}/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
//			"		            </td>        "+
//			"		            <td width='10%' align = 'center'align = 'center'>"+
//			"		                 <input id='studyitemPrice_"+isqs+"' name='studyitemPrice_"+isqs+"' class='inputW160' readonly onkeyup='value=value.replace(/[^\d\.]/g,'')' onbeforepaste='clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))'/>"+
//			"		            </td> "+
//			"		            <td width='10%' class='c'>   "+
//			"		                <button  id='add' type='button' onclick='addStudyItem()'>增加</button>"+
//			"		                <button  id='deleBtn_"+isqs+"' type='button' onclick='delStudyItem(this)'>删除</button>"+
//			"		            </td>"+
//			"</tr>");
//	parent.initSelect2forDiv($("div[name='studyitemDesc_"+isqs+"']"));
	var gr = $("#hisPatientlist").jqGrid("getGridParam","selarrrow");
//	if (gr.length == 0) {
//		alert("请选择一行！");
//		return;
//	}
	if (gr.length > 0) {
		var rowData = $("#hisPatientlist").jqGrid('getRowData',gr[0]);	
		$("#patientName",parent.document).val(rowData.brxm);
		parent.ename();  
		var sexV = 1;
	    if(rowData.xb=='男'){
	    	sexV = 1;
	    }else{
	    	sexV = 2;
	    } 
	    parent.setSexSelected(sexV);
	    $("#patientMobile",parent.document).val(rowData.lxdh);
	    $("#patientIdnumber",parent.document).val(rowData.sfzh);
	    $("#patientAddress",parent.document).val(rowData.dz);    
	    var dob=rowData.birth;
	    if(rowData.birth !=null && rowData.birth.length>10){
	    	dob=rowData.patientDob.substring(0,10);
	    }
	    $("#patientDob",parent.document).val(dob);
	    //根据出生日期算出年龄
	    parent.getAge($("#patientDob",parent.document));    
	    
	    $("#patientPhone",parent.document).val(rowData.lxdh);
	    
	    //申请科室转码
	    var locId = getLocId(orgId,rowData.sqksdm);  
	    if(locId==0){
	    	locId=-1;
	    }
	    parent.setStudyApplocid(locId);  
//	    //申请医生
//	    $("#studyAppdoc_div",parent.document).empty()
//	    $("#studyAppdoc_div",parent.document).append("<input id='studyAppdoc' name='studyAppdoc' class='inputW160' disabled='disabled'>");
//	    $("#studyAppdoc",parent.document).val(rowData.sqys);
	    //病区
	    $("#studyWard_div",parent.document).empty()
	    $("#studyWard_div",parent.document).append("<input id='studyWard' name='studyWard' class='inputW160' disabled='disabled'>");
	    $("#studyWard",parent.document).val(rowData.bq);
	    $("#hisZydjxh",parent.document).val(rowData.djxh);
	    //床号  
	    $("#studyBedno",parent.document).val(rowData.ch); 
	    if(rowData.brlx=="HP"){
	    	$("#patientInpatientid",parent.document).val(rowData.mzh); 
	    }else{
	    	//住院号
		    $("#patientInpatientid",parent.document).val(rowData.zyh); 
		    //门诊号
		    $("#patientOutpatientid",parent.document).val(rowData.mzh);
	    }
	    //病人类型    
	    parent.setPatienttypeCode(rowData.brlx);
	    if(rowData.brlx=="HP"){
	    	parent.$("#hpnum").text("体检号：");
	    	parent.$(".ret_span_HP").show();
	    	parent.$(".ret_span_INP").hide();
	    	parent.$(".ret_span_OP").hide();
	    }
	    
	    $("#studyClinic",parent.document).val(rowData.jbmc); 
	    $("#studyHisappid",parent.document).val(rowData.hissqh); 
	    if(rowData.sqksdm!=""){
	    	parent.setCareProvHis(orgId,rowData.sqksdm,rowData.sqysdm)
	    }
	    //根据检查项目选中登记页面检查设备
	    var selrow =$("#hisPatientlist").getGridParam('selarrrow');
	    var columnYzmc=[];
	    var columnYzdm=[];
	    $(selrow).each(function (index, yu) {//遍历每个id 找到每个data 并把属性加到初始化数组里 
	    	var rowData = $("#hisPatientlist").jqGrid("getRowData", yu); 
	    	columnYzmc.push(rowData.yzmc); 
	    	columnYzdm.push(rowData.yzdm); 
	    }); 
	    $("#isHis",parent.document).val("y"); 
	    if(rowData.yzmc!=""&&rowData.yzdm!=""){
	    	for(var i=0;i<columnYzdm.length;i++){
		    	$.ajax({
		    		type: "POST",
				    async: false,
				    url: GLOBAL.WEBROOT+"/patientReg/getItemmastEquiment.ajax?yzmc="+encodeURI(encodeURI(columnYzmc[i]))+"&yzdm="+columnYzdm[i]+"&orgId="+parent.parent.$("#currentOrgId").val(),
				    success: function (data) {
			    		if(data.itemmastId==""||data.itemmastId==null||data.itemmastId=="undefined"||data.itemmastId==undefined){
//			    			parent.$("#equipmentId").select2("val","-1");
			    			var count =parent.$("#equipmentId").get(0).options.length;
			    			if(count>1){
			    				parent.$("#equipmentId").select2("val",parent.$("#equipmentId").get(0).options[1].value);
			    			}
			    			parent.setHisItemmast(data.itemmastId,data.itemmaseCode,data.itemmastDesc,data.itemPrice);
//			    			parent.layer.alert('该医嘱未进行维护或者设备子类未关联！', {icon: 5,shadeClose: true});
			    			
			    		}else{	
			    			parent.$("#equipmentId").select2("val", data.EquipmentId);
			    			parent.setHisItemmast(data.itemmastId,data.itemmaseCode,data.itemmastDesc,data.itemPrice);
			    		}
				    }
				});
	    	 
	    	}
	    	if(columnYzdm.length>0){
	    		//parent.$("#configTab").find("[tag=studyItem]").eq(0).remove();	
	    	}
	    }else{
	    	var count =parent.$("#equipmentId").get(0).options.length;
			if(count>1){
				parent.$("#equipmentId").select2("val",parent.$("#equipmentId").get(0).options[1].value);
			}
//	    	parent.$("#equipmentId").select2("val","-1");
	    	parent.setHisItemmast(undefined,"","","");
	    	if(columnYzdm.length>0){
	    		//parent.$("#configTab").find("[tag=studyItem]").eq(0).remove();	
	    	}
	    }
	    closeDiv();
	}
}
function reloadGrid(){
    var data = $("#searchForm").serializeObject() ;   
	$("#hisPatientlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:8}).trigger("reloadGrid");
}


//查询列表
function selectList() {	 
	var orgId = $("#orgId").val();	
	var orgCode = $("#orgCode").val();	
	var locId = $("#locId").val();	
	var queryKey = $("#queryKey").val();	
	var locCode = $("#locCode").val();
	var queryValue = $("#queryValue").val();	
	var grid_selector = "#hisPatientlist";
    var pager_selector = "#hisPatientlist_pager";
    var data = $("#searchForm").serializeObject();  
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/patientReg/getHisStudyInfoList.ajax?orgId="+orgId+"&queryKey="+queryKey+"&queryValue="+queryValue+"&orgCode="+orgCode+"&locId="+locId+"&locCode="+locCode,
        mtype : "post",  
        async: true,
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['djxh','病人姓名','性别','出生日期','联系电话','住院号','门诊号','BRLX','SQKSDM','申请科室','申请医生','病区','CH','身份证号','住址','卡号','医嘱代码','医嘱名称','总金额','收据号','ZXKSDM','机构代码','申请时间','临床诊断','his申请号','申请医生代码'],                
        colModel: [	
            {name: 'djxh', index: 'djxh', width:80,sortable: false,hidden:true},
            {name: 'brxm', index: 'brxm', width:50,sortable: true},
            {name: 'xb', index: 'xb', width:50,sortable: false},
            {name: 'birth', index: 'birth', width:50,sortable: false}, 
            {name: 'lxdh', index: 'lxdh', width:80,sortable: false},
            {name: 'zyh', index: 'zyh', width:80,sortable: false,hidden:true},
            {name: 'mzh', index: 'mzh', width:80,sortable: false,hidden:true},
            {name: 'brlx', index: 'brlx', width:50,sortable: false,hidden:true},
            {name: 'sqksdm', index: 'sqksdm', width:50,sortable: false,hidden:true},
            {name: 'sqks', index: 'sqks', width:50,sortable: false}, 
            {name: 'sqys', index: 'sqys', width:80,sortable: false},
            {name: 'bq', index: 'bq', width:80,sortable: false,hidden:true},
            {name: 'ch', index: 'ch', width:80,sortable: false,hidden:true},
            {name: 'sfzh', index: 'sfzh', width:80,sortable: false},
            {name: 'dz', index: 'dz', width:80,sortable: false},
            {name: 'kh', index: 'kh', width:80,sortable: false,hidden:true},
            {name: 'yzdm', index: 'yzdm', width:80,sortable: false,hidden:true},            
            {name: 'yzmc', index: 'yzmc', width:80,sortable: false},
            {name: 'zje', index: 'zje', width:80,sortable: false,hidden:true},
            {name: 'sjh', index: 'sjh', width:80,sortable: false,hidden:true},
            {name: 'zxksdm', index: 'zxksdm', width:80,sortable: false,hidden:true},
            {name: 'jgdm', index: 'jgdm', width:80,sortable: false,hidden:true},
            {name: 'zxsj', index: 'zxsj', width:80,sortable: false},
            {name: 'jbmc', index: 'jbmc', width:80,sortable: false,hidden:true},
            {name: 'hissqh', index: 'hissqh', width:80,sortable: false,hidden:true},
            {name: 'sqysdm', index: 'sqysdm', width:120,sortable: false,hidden:true}
        ],          
        viewrecords: false,
        rowNum:8,
        rowList:[10,15,20,30],
        multiselect: true,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
//            var ids =  $(grid_selector).jqGrid('getDataIDs');
//            var idsArray = ids.toString().split(",");
//            for(var i=0;i<idsArray.length;i++){
//            	$(grid_selector).jqGrid('setSelection',idsArray[i]);
//            }
//            $(".cbox").attr("checked","checked");
        },
        onSelectAll: function (aRowids, status) {
        }, 
        //实现单选
        onSelectRow: function (rowid,status) {
//        	if(status){
//        		 var rowIds = $(grid_selector).jqGrid('getGridParam', 'selarrrow');    
//        	}
        }, 
        autowidth: true
    });
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
 

//计算年龄
function getAge(birth) {
	var strBirthday = birth;
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
				}else {
					 ageUnit = 2;
					 returnAge = monthDiff; 
				}
			}
		} else {
			returnAge = -1;// 返回-1 表示出生日期输入错误 晚于今天}
		} 
	}
	$("#patientAgeUnit option[value='"+ageUnit+"']",parent.document).attr("selected",true); 
	//$("#patientAgeUnit",parent.document).select2();
	$("#patientAgeShow",parent.document).val(returnAge);
	var u = "";
	if(ageUnit == 1){
		u = "岁"
	}else if(ageUnit == 2){
		u = "月"
	}else if(ageUnit == 3){
		u = "天"
	}
	$("#patientAge",parent.document).val(returnAge+u);
}

//科室编码转为科室ID
function getLocId(orgId,locCode){
	var locId = "";
	//检查项目
	$.ajax({
	    type: "POST",
	    async: false,
	    url: GLOBAL.WEBROOT+"/basecode/getLocId.ajax?orgId="+orgId+"&locCode="+locCode,
	    success: function (data) {
		   locId = data.LOC_ID;	         
	    }
	}); 
	return locId;
}
 