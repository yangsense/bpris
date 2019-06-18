$(function() {
	selectList();
});
function selectList() {	 
	var data = $("#searchForm").serializeObject();  
	var grid_selector = "#list";
    var pager_selector = "#pager";
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/doctorWorkload/queryStudyInfoList.ajax",
        mtype : "post",  
        async: true,
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['登记号','病人姓名','性别','年龄','病人类型','住院号','申请科室','床号','检查号','检查项目','检查部位','检查时间','检查结果'],
        colModel: [	
            {name: 'studyinfoId', index: 'studyinfoId', width:40,sortable: false},
            {name: 'patientName', index: 'patientName', width:40,sortable: false}, 
            {name: 'patientSex', index: 'patientSex', width:30,sortable: false}, 
            {name: 'patientAge', index: 'patientAge', width:30,sortable: false}, 
            {name: 'patienttypeCode', index: 'patienttypeCode', width:40,sortable: false},            
            {name: 'patientInpatientid', index: 'patientInpatientid', width:60,sortable: false},
            {name: 'locDesc', index: 'locDesc', width:50,sortable: false}, 
            {name: 'studyBedno', index: 'studyBedno', width:50,sortable: false}, 
            {name: 'studyAccnumber', index: 'studyAccnumber', width:50,sortable: false}, 
            {name: 'studyItemdesc', index: 'studyItemdesc', width:80,sortable: false}, 
            {name: 'bodyitem', index: 'bodyitem', width:80,sortable: false}, 
            {name: 'studyDatetimeDesc', index: 'studyDatetimeDesc', width:80,sortable: false},
            {name: 'reportIspositive', index: 'reportIspositive', width:50,sortable: true,
            	formatter: function (param1, param2, recode) {
            	if(param1==0){
            		return '阳性';
            	}else if(param1==1){
            		return '阴性性';
            	}else{
            		return '未知';
            	} 
        	}},     
        ],        
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: false,  //checkbox
        multiboxonly: true,  //取掉这个，点击选中，再点击取消选中了。
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
        }
    });
}