$(function () {
	//默认时间
	initDate();
    //检过条件初始化
    initDict();
    //所有select嵌套css样式
    $("#equipmentId").select2();
    
    $("#studyAppdoc").select2();
    
});

var currentLocId = $("#currentLocId" , parent.document).val();
var orgId = $("#currentOrgId" , parent.document).val();
//初始化查询进间
function initDate(){
	var n=new Date();
    n.setDate(n.getDate()-30); 
    $("#startTime").val(n.getFullYear()+"-"+ Appendzero(n.getMonth()+1)+"-"+ Appendzero(n.getDate()));
    var u=new Date();
    u.setDate(u.getDate()); 
    $("#endTime").val(u.getFullYear()+"-"+ Appendzero(u.getMonth()+1)+"-"+ Appendzero(u.getDate()));
}
function Appendzero (obj) {
	if (obj < 10) return "0" + obj; else return obj;
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
            for (var i = 0; i < jsonStatus.length; i++) {
                statusSelect.append("<option value='" + jsonStatus[i].locid + "'>" + jsonStatus[i].locdesc + "</option>");
                if(jsonStatus[i].locid == $("#currentLocId").val()){
                	currFlag = true;
                }
            }
            if($("#currentLocId").val()=="" || $("#currentLocId").val()==undefined){
            	$("#locInfo option:first").prop("selected", 'selected');
                $("#locInfo").select2();
                selectList();
            }else{
            	if(currFlag){
            		$("#locInfo").val($("#currentLocId").val());
                	$("#locInfo").select2();
                	selectList();
            	}else{
            		$("#locInfo option:first").prop("selected", 'selected');
                	$("#locInfo").select2();
                	selectList();
            	}            	
            } 
          //检查设备下拉列表
      	  $.ajax({
      	      type: "POST",
      	      url: GLOBAL.WEBROOT+"/patientReg/getEquipment.ajax?orgId="+orgId+"&locId="+$("#locInfo").val(),
      	      success: function (data) {
      	          var equipmentBeans = eval(data.equipmentBeans);
      	          var statusSelect = $("#equipmentId");
      	          statusSelect.empty();
      	          statusSelect.append("<option value='-1'>全部</option>");
      	          for (var i = 0; i < equipmentBeans.length; i++) {
      	              statusSelect.append("<option value='" + equipmentBeans[i].equipmentId + "' roomId = '" + equipmentBeans[i].roomId + "' >" + equipmentBeans[i].equipmentDesc + "</option>");
      	          }
      	      }
      	  });
            //根据科室获取检查设备类型
//            $.ajax({
//                type: "POST",
//                async: false,
//                url: GLOBAL.WEBROOT+"/workList/getEquimentByLocId.ajax?orgId="+orgId+"&locId="+$("#locInfo").val(),
//                success: function (data) {
//                    var jsonEqimentType = eval(data.MODALITY_TYPE);
//                    var modalitySelect = $("#modalityId");
//                    modalitySelect.empty(); 
//                    modalitySelect.append("<option value='-1'>全部</option>");
//                    for (var i = 0; i < jsonEqimentType.length; i++) {
//                    	modalitySelect.append("<option value='" + jsonEqimentType[i].itemno + "'>" + jsonEqimentType[i].itemname + "</option>");
//                    }
//                }
//            });
        }
    });
    
    //病人类型
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT+"/worklistCount/getPatientType.ajax",
        success: function (data) {
            var jsonPatientType = eval(data.PatientType);
            var patientTypeSelect = $("#patienttypeCode");
            patientTypeSelect.empty(); 
            patientTypeSelect.append("<option value='-1'>全部</option>");
            for (var i = 0; i < jsonPatientType.length; i++) {
            	patientTypeSelect.append("<option value='" + jsonPatientType[i].itemno + "'>" + jsonPatientType[i].itemname + "</option>");
            }
            patientTypeSelect.select2();
        }
    });
    //申请科室
	$.ajax({
	    type: "POST",
	    async: false,
	    url: GLOBAL.WEBROOT+"/worklistCount/getLocApply.ajax?orgId="+orgId,
	    success: function (data) {
	        var jsonLocType = eval(data.Loc_Apply);
	        var studyLocSelect = $("#studyApplocid");
	        studyLocSelect.empty(); 
	        studyLocSelect.append("<option value='-1'>全部</option>");
	        for (var i = 0; i < jsonLocType.length; i++) {
	        	studyLocSelect.append("<option value='" + jsonLocType[i].locid + "'>" + jsonLocType[i].locdesc + "</option>");
	        }
	        studyLocSelect.select2();
	    }
	});
}

function changeCareProv(obj){
	var locId = $("#studyApplocid option:selected").val();
	setCareProv(orgId,$(obj).val());
}

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
	        statusSelect.append("<option value='-1'>全部</option>");
	        statusSelect.select2("val","-1");
	        for (var i = 0; i < careProvBeans.length; i++) {
	            statusSelect.append("<option value='" + careProvBeans[i].careprovId + "'>" + careProvBeans[i].careprovName + "</option>");
	        }
	    }
	});
}

//重置
function reset(){
	$("#startTime").val("");
	$("#endTime").val("");
	$("#equipmentId").select2("val","-1"); 
	$("#locId").select2("val","-1"); 
	$("#studyApplocid").select2("val","-1"); 
	$("#studyAppdoc").select2("val","-1"); 
	$("#patienttypeCode").select2("val","-1"); 
}

function exportWorklistCount(){
	var data = $("#searchForm").serialize();
	var url=GLOBAL.WEBROOT + '/worklistCount/exportWorklistCount.ajax?'+data;
    window.open(url);
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject()
    data.page = 1;
    data.pageSize = 10;
	$("#caselist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#caselist"; 
    var pager_selector = "#caselist_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/worklistCount/queryWorklistCount.ajax?orgId="+orgId,
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['登记号','病人姓名','性别','年龄','病人类型','住院号/门诊号/体检号','设备名称','申请科室','申请医生','床号','检查号','检查项目','检查部位','登记时间'],
        colModel: [
            {name: 'studyinfoId', index: 'studyinfoId',width:30, sortable: false},             
            {name: 'patientName', index: 'patientName',width:30, sortable: false},    
            {name: 'patientSex', index: 'patientSex', width:30,sortable: false},
            {name: 'patientAge', index: 'patientAge', width:30,sortable: false},
            {name: 'patienttypeCode', index: 'patienttypeCode',width:50,sortable: false},
            {name: 'patientInpatientid', index: 'patientInpatientid',width:50,sortable: false},
            {name: 'equipmentDesc', index: 'equipmentDesc', width:30,sortable: false},
            {name: 'locDesc', index: 'locDesc',width:30,sortable: false},
            {name: 'studyAppdocname', index: 'studyAppdocname',width:30,sortable: false},
            {name: 'studyBedno', index: 'studyBedno',width:30,sortable: false},
            {name: 'studyAccnumber', index: 'studyAccnumber',width:50,sortable: false},
            {name: 'bodyitem', index: 'bodyitem',width:50,sortable: false},
            {name: 'studyItemdesc', index: 'studyItemdesc',width:50,sortable: false},
            {name: 'cStudyOperationtime', index: 'cStudyOperationtime',width:50,sortable: false}   
        ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function (result) {
            com.ai.bdx.util.updatePagerIcons(this);
    		//空列填充
    		var re_records = $("#caselist").getGridParam('records');
        	if(re_records < 10 )
        	{
        		for(var i=0,j=10-re_records;i<=j;i++){
        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
        			$("#caselist").jqGrid("addRowData",(re_records+1),{ '登记号':'','病人姓名':'','性别':'','年龄':'','病人类型':'','住院号/门诊号/体检号':'','申请科室':'','申请医生':'','床号':'','检查号':'','检查项目':'','检查部位':'','登记时间':''},"");
        		}
        	}
        	$("#divTotalInfo").html("检查数量合计："+re_records);
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}
