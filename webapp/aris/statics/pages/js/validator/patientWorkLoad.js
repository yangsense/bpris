

var _userTJStation = parent.top.userTJStation;
var _sysOrg = parent.top.sysOrg;

$(function () {
    initDict();
    //查询
    firstLevel();
    $("#time_type").val("5");
    $("#time_type").select2();
    loadTimeType();
    
//    selectReadOnly("cityCode");
    $("#cityCode").attr("readonly","true");
    $("#jd").select2();
    selectCityList();
    selectList();
    selectLocList();
    selectPatientList();
    reloadGrid();
});

//设置下拉框只读--不生效
function selectReadOnly(selectedId){
    var obj = document.getElementById(selectedId);
    obj.onmouseover = function(){
        obj.setCapture();
    }
    obj.onmouseout = function(){
        obj.releaseCapture();
    }
    obj.onfocus = function(){
        obj.blur();
    }
    obj.onbeforeactivate = function(){
        return false;
    }
}

//起始日期
var dateSt;
var dateEt;

function initDict() {

    //城市加载
    $.ajax({
        type: "POST",
        async: false,
        url:GLOBAL.WEBROOT +  '/basecode/getCitys.ajax',
        dataType:'json',
        success:function(data){
            var jsonCitys = eval(data.cityCode);
            var source_cityCode = $("#cityCode");
            source_cityCode.empty();
            source_cityCode.append("<option value='-1'>请选择城市</option>");
            for (var i = 0; i < jsonCitys.length; i++) {
                source_cityCode.append("<option value='" + jsonCitys[i].citycode + "'>" + jsonCitys[i].citydesc + "</option>");
            }
            source_cityCode.val(_sysOrg.cityCode);
            source_cityCode.select2();
        }
    });
    getCountySelect();
    // getOrgSelects();
}

function getCountySelect(){
    //区县加载
    var cityCode = $("#cityCode").val();
    $.ajax({
        type: "POST",
        async: false,
        url:GLOBAL.WEBROOT +  '/basecode/getCountys.ajax?cityCode='+cityCode,
        dataType:'json',
        success:function(data){
            var jsonCountys = eval(data.countyMsg);
            var source_countyCode = $("#countyCode");
            source_countyCode.empty();
            source_countyCode.append("<option value='-1'>请选择区县</option>");
            for (var i = 0; i < jsonCountys.length; i++) {
                source_countyCode.append("<option value='" + jsonCountys[i].countycode + "'>" + jsonCountys[i].countydesc + "</option>");
            }

            if('ADMIN_PACS_TJ_COUNTY'==_userTJStation){
                source_countyCode.val(_sysOrg.districtCode);
                source_countyCode.select2();
                $("#countyCode").attr("readonly","true");
                $("#citylast").hide();
            }else{
                source_countyCode.select2();
            }
            
            getOrgSelects();
        }
    });
}



function getOrgSelects(){
    var cityCode = $("#cityCode").val();
    var countyCode = $("#countyCode").val();
    //机构列表加载
    $.ajax({
        type: "POST",
        async: false,
        url:GLOBAL.WEBROOT +  '/basecode/getOrgSelects.ajax?cityCode='+cityCode+"&countyCode="+countyCode,
        dataType:'json',
        success:function(data){
            var jsonOrgMsg = eval(data.orgMsg);
            var source_orgMsg = $("#orgId");
            source_orgMsg.empty();
            source_orgMsg.append("<option value='-1'>请选择机构</option>");
            for (var i = 0; i < jsonOrgMsg.length; i++) {
                source_orgMsg.append("<option value='" + jsonOrgMsg[i].orgid + "'>" + jsonOrgMsg[i].orgname + "</option>");
            }
            source_orgMsg.select2();
        }
    });
}

function Appendzero (obj) {
	if (obj < 10) return "0" + obj; else return obj;
}

//时间选择
function loadTimeType(){
    var timeType =$("#time_type").val();
    var n=new Date();
    if(timeType=="1"){
        $("#timetype_title").html("日期：");
        $("#dateOfDay").val(GetDateStr(-0));
        document.getElementById("time_type1").style.display = "";
        document.getElementById("time_type2").style.display = "none";
        document.getElementById("time_type3").style.display = "none";
        document.getElementById("time_type4").style.display = "none";
        document.getElementById("time_type5").style.display = "none";
    }else if(timeType=="2"){
        $("#timetype_title").html("日期区间：");
        $("#startDate").val(GetDateStr(-30));
        $("#endDate").val(GetDateStr(-0));
        document.getElementById("time_type1").style.display = "none";
        document.getElementById("time_type2").style.display = "";
        document.getElementById("time_type3").style.display = "none";
        document.getElementById("time_type4").style.display = "none";
        document.getElementById("time_type5").style.display = "none";
    }else if(timeType=="3"){
        $("#timetype_title").html("月份：");
        $("#monthDate").val(n.getFullYear()+"-"+Appendzero(n.getMonth()+1));
        document.getElementById("time_type1").style.display = "none";
        document.getElementById("time_type2").style.display = "none";
        document.getElementById("time_type3").style.display = "";
        document.getElementById("time_type4").style.display = "none";
        document.getElementById("time_type5").style.display = "none";
    }else if(timeType=="4"){
        $("#timetype_title").html("年份：");
        $("#yearDateOfJD").val(n.getFullYear());
        document.getElementById("time_type1").style.display = "none";
        document.getElementById("time_type2").style.display = "none";
        document.getElementById("time_type3").style.display = "none";
        document.getElementById("time_type4").style.display = "";
        document.getElementById("time_type5").style.display = "none";
    }else if(timeType=="5"){
        $("#timetype_title").html("年份：");
        $("#yearDate").val(n.getFullYear());
        document.getElementById("time_type1").style.display = "none";
        document.getElementById("time_type2").style.display = "none";
        document.getElementById("time_type3").style.display = "none";
        document.getElementById("time_type4").style.display = "none";
        document.getElementById("time_type5").style.display = "";
    }
}


//第一级别
function firstLevel(){
    document.getElementById("city_div").style.display="";
    document.getElementById("county_div").style.display="none";
    document.getElementById("loc_div").style.display="none";
    document.getElementById("patient_div").style.display="none";
}

//第二级别
function secondLevel(){
    document.getElementById("city_div").style.display="none";
    document.getElementById("county_div").style.display="";
    document.getElementById("loc_div").style.display="none";
    document.getElementById("patient_div").style.display="none";
}

//第三级别
function thirdLevel(){
    document.getElementById("city_div").style.display="none";
    document.getElementById("county_div").style.display="none";
    document.getElementById("loc_div").style.display="";
    document.getElementById("patient_div").style.display="none";
}

//第四级别
function fourthLevel(){
    document.getElementById("city_div").style.display="none";
    document.getElementById("county_div").style.display="none";
    document.getElementById("loc_div").style.display="none";
    document.getElementById("patient_div").style.display="";
}



//查询列表
function selectCityList() {
    firstLevel();
    var grid_selector = "#citylist";
    var pager_selector = "#city_pager";
    var data = $("#searchForm").serializeObject();
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/patientWorkLoad/queryCityPageList.ajax",
        mtype : "post",
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['城市编码','城市名称','登记数量','采集人次','图像数量','报告数量','审核数量'],
        colModel: [
            {name: 'cityCode', index: 'cityCode',width:30,sortable: false,hidden:true},
            {name: 'cityDesc', index: 'cityDesc',width:30, sortable: false,formatter: function (param1, param2, recode) {
                return '<div class="action-buttons">' +
                    '<a class="blue" href="javascript:void(0)" onclick="viewCountyDetail(\'' + recode.cityCode + '\')" title="'+param1+'">'+param1+'</a>' +
                    "</div>";
            }},
            {name: 'registNum', index: 'registNum', width:30,sortable: false},
            {name: 'collectNum', index: 'collectNum', width:30,sortable: false},
            {name: 'imageNum', index: 'imageNum',width:50,sortable: false},
            {name: 'reportNum', index: 'reportNum',width:50,sortable: false},
            {name: 'checkedNum', index: 'checkedNum', width:30,sortable: false}
        ],
        viewrecords: false,
        rowNum:10,
        page: 1,
        // footerrow:true,
        // gridComplete: completeMethod,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
            completeCityMethod();
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}


//查询列表
function selectList() {
    secondLevel();
    var grid_selector = "#aiscserverlist";
    var pager_selector = "#aiscserver_pager";
    var data = $("#searchForm").serializeObject();
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/patientWorkLoad/queryCountyPageList.ajax",
        mtype : "post",
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['区县编码','县区','登记数量','采集人次','图像数量','报告数量','审核数量'],
        colModel: [
            {name: 'countyCode', index: 'countyCode',width:30,sortable: false,hidden:true},
            {name: 'countyDesc', index: 'countyDesc',width:30, sortable: false,formatter: function (param1, param2, recode) {
             return '<div class="action-buttons">' +
                     '<a class="blue" href="javascript:void(0)" onclick="viewLocDetail(\'\',\'' + recode.countyCode + '\')" title="'+param1+'">'+param1+'</a>' +
                    "</div>";
             }},
            {name: 'registNum', index: 'registNum', width:30,sortable: false},
            {name: 'collectNum', index: 'collectNum', width:30,sortable: false},
            {name: 'imageNum', index: 'imageNum',width:50,sortable: false},
            {name: 'reportNum', index: 'reportNum',width:50,sortable: false},
            {name: 'checkedNum', index: 'checkedNum', width:30,sortable: false}
        ],
        viewrecords: false,
        rowNum:10,
        page: 1,
        // footerrow:true,
        // gridComplete: completeMethod,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
            completeCounTyMethod();
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}


/*统计市级会诊信息*/
function completeCityMethod() {
    var sum_checkedNum=$("#citylist").getCol('checkedNum',false,'sum');
    var sum_reportNum=$("#citylist").getCol('reportNum',false,'sum');
    var sum_collectNum=$("#citylist").getCol('collectNum',false,'sum');
    var sum_imageNum=$("#citylist").getCol('imageNum',false,'sum');
    var sum_registNum=$("#citylist").getCol('registNum',false,'sum');
    var data = [sum_registNum,sum_collectNum,sum_imageNum,sum_reportNum,sum_checkedNum];
    initCharts(data);
}

/*统计区县级别 */
function completeCounTyMethod(){
    var sum_checkedNum=$("#aiscserverlist").getCol('checkedNum',false,'sum');
    var sum_reportNum=$("#aiscserverlist").getCol('reportNum',false,'sum');
    var sum_collectNum=$("#aiscserverlist").getCol('collectNum',false,'sum');
    var sum_imageNum=$("#aiscserverlist").getCol('imageNum',false,'sum');
    // $("#aiscserverlist").footerData('set', { "countyDesc": "<label style='color: red; width: 100px;'>合计</label>","checkedNum": "<label style='color: red;'>"+sum_checkedNum+"</label>","reportNum":"<label style='color: red;'>"+sum_reportNum+"</label>", "collectNum": "<label style='color: red;'>"+sum_collectNum+"</label>","imageNum": "<label style='color: red;'>"+sum_imageNum+"</label>"});

    $("#county_total_label").html("合计：报告合计:"+sum_reportNum+"&nbsp; 采集合计:"+sum_collectNum+" &nbsp; 图像合计:"+sum_imageNum+" &nbsp; 审核合计:"+sum_checkedNum);
}


function viewCityDetil() {
    firstLevel();
    reloadCityGrid();
}

function viewCountyDetail(cityCode){
    secondLevel();
    reloadCountyGrid(cityCode);
}

/**
 * 查看科室级统计信息
 */
function viewLocDetail(orgId,countyCode) {
    thirdLevel();
    reloadLocGrid(orgId,countyCode);
}

/**
 * 查询患者信息
 */
function viewPatientDetail(orgId,locCode){
    fourthLevel();
    reloadPatientGrid(orgId,locCode);
}

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}

//科室级别统计信息
function selectLocList(counutyCode) {
    var grid_selector = "#loclist";
    var pager_selector = "#loc_pager";
    var data = $("#searchForm").serializeObject();
    data.countyCode = counutyCode;
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/patientWorkLoad/queryLocPageList.ajax",
        mtype : "post",
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['区县编码','县区','机构Id','机构名称','科室代码','科室名称','登记数量','采集人次','图像数量','报告数量','审核数量'],
        colModel: [
            {name: 'countyCode', index: 'countyCode',width:30,sortable: false,hidden:true},
            {name: 'countyDesc', index: 'countyDesc',width:30, sortable: false},
            {name: 'orgId', index: 'orgId',width:30,sortable: false},
            {name: 'orgDesc', index: 'orgDesc',width:100, sortable: false},
            {name: 'locCode', index: 'locCode',width:50,sortable: false,hidden:true},
            {name: 'locDesc', index: 'locDesc',width:30, sortable: false,formatter: function (param1, param2, recode) {
                return '<div class="action-buttons">' +
                    '<a class="blue" href="javascript:void(0)" onclick="viewPatientDetail(\'' + recode.orgId + '\',\''+recode.locCode+'\')" title="'+param1+'">'+param1+'</a>' +
                    "</div>";
            }},
            {name: 'registNum', index: 'registNum', width:30,sortable: false},
            {name: 'collectNum', index: 'collectNum', width:30,sortable: false},
            {name: 'imageNum', index: 'imageNum',width:50,sortable: false},
            {name: 'reportNum', index: 'reportNum',width:50,sortable: false},
            {name: 'checkedNum', index: 'checkedNum', width:30,sortable: false}
        ],
        viewrecords: false,
        rowNum:10,
        // footerrow:true,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
            completeLocMethod();
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}

/*统计区县级别 */
function completeLocMethod(){
    var sum_checkedNum=$("#loclist").getCol('checkedNum',false,'sum');
    var sum_reportNum=$("#loclist").getCol('reportNum',false,'sum');
    var sum_collectNum=$("#loclist").getCol('collectNum',false,'sum');
    var sum_imageNum=$("#loclist").getCol('imageNum',false,'sum');
    // $("#loclist").footerData('set', { "countyDesc": "<label style='color: red;'>合计</label>","checkedNum": "<label style='color: red;'>"+sum_checkedNum+"</label>","reportNum":"<label style='color: red;'>"+sum_reportNum+"</label>", "collectNum": "<label style='color: red;'>"+sum_collectNum+"</label>","imageNum": "<label style='color: red;'>"+sum_imageNum+"</label>"});

    $("#loc_total_label").html("合计：报告合计:"+sum_reportNum+"&nbsp; 采集合计:"+sum_collectNum+" &nbsp; 图像合计:"+sum_imageNum+" &nbsp; 审核合计:"+sum_checkedNum);
}

//患者级别统计信息
function selectPatientList(checkDate,counutyCode) {
    var grid_selector = "#patientlist";
    var pager_selector = "#patient_pager";
    var data = $("#searchForm").serializeObject();
    data.countyCode = counutyCode;
    data.checkDate = checkDate;
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/patientWorkLoad/queryPatientPageList.ajax",
        mtype : "post",
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['区县代码','区县','机构代码','机构名称','科室代码','科室名称','登记号','姓名','年龄','性别','检查项目','图像数量','报告医生','审核医生'],
        colModel: [
            {name: 'countyCode', index: 'countyCode',width:30,sortable: false,hidden:true},
            {name: 'countyDesc', index: 'countyDesc',width:30, sortable: false},
            {name: 'orgId', index: 'orgId',width:30,sortable: false,hidden:true},
            {name: 'orgDesc', index: 'orgDesc',width:100, sortable: false},
            {name: 'locCode', index: 'locCode',width:30,sortable: false,hidden:true},
            {name: 'locDesc', index: 'locDesc',width:50, sortable: false},
            {name: 'registNo', index: 'registNo',width:30, sortable: false},
            {name: 'patientName', index: 'patientName', width:30,sortable: false},
            {name: 'patientAge', index: 'patientAge',width:30, sortable: false},
            {name: 'patientSex', index: 'patientSex', width:30,sortable: false},
            {name: 'checkItem', index: 'checkItem', width:100,sortable: false},
            {name: 'imageNum', index: 'imageNum',width:50,sortable: false},
            {name: 'reportDoc', index: 'reportDoc',width:50,sortable: false},
            {name: 'checkDoc', index: 'checkDoc', width:30,sortable: false}
        ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}



//查询按钮响应事件
function reloadGrid(){
   var cityCode = $("#cityCode").val();
   var countyCode = $("#countyCode").val();
   var orgId = $("#orgId").val();

   //TODO 按照时间维度，类型就行参数封装
    var time_type = $("#time_type").val();
    if(time_type=='1'){ //日报表
        var day = $("#dateOfDay").val();
        if(!day){
            layer.alert("日报表日期不能为空！");
            return;
        }
        dateSt = day;
        dateEt = day;
    }else if(time_type=='2'){//日期区间报表
        var startDay = $("#startDate").val();
        var endDay = $("#endDate").val();
        if(!startDay||!endDay){
            layer.alert("区间报表起始日期不能为空！");
            return;
        }
        dateSt = startDay;
        dateEt = endDay;
    }else if(time_type=='3'){//月报表
        var monthDate = $("#monthDate").val();
        if(!monthDate){
            layer.alert("月报表时间不能为空！");
            return;
        }
        //TODO 根据月份计算天数
        var days = getDaysByMonth(monthDate)
        dateSt = monthDate+"-01";
        dateEt = monthDate+"-"+days;
    }else if(time_type=='4'){//季度报表
        var yearDateOfJD = $("#yearDateOfJD").val();
        if(!yearDateOfJD){
            layer.alert("年份不能为空！");
            return;
        }
        var jd = $("#jd").val();
        var lastMonth = jd*3;
        var firstMonth = jd*3-2;
        if(lastMonth<10){
            lastMonth = "0"+lastMonth;
        }

        if(firstMonth<10){
            firstMonth = "0"+firstMonth;
        }

        //TODO 根据月份计算天数
        var lastDay = getDaysByMonth(yearDateOfJD+"-"+lastMonth);
        dateSt = yearDateOfJD+"-"+firstMonth+"-01";
        dateEt = yearDateOfJD+"-"+lastMonth+"-"+lastDay;
    }else if(time_type=='5'){//年报表
        var yearDate = $("#yearDate").val();
        if(!yearDate){
            layer.alert("年份不能为空！");
            return;
        }
        dateSt =yearDate+"-01-01" ;
        dateEt =yearDate+"-12-31" ;
    }

   if('-1'!=orgId){ //机构级别
       viewLocDetail(orgId,countyCode);
       return;
   }

   if('-1'!=countyCode){//区县级别
       viewCountyDetail(cityCode);
       return;
   }

    if('-1'!=cityCode){//市级级别
        viewCityDetil();
        return;
    }


}


//根据月份计算日期
function getDaysByMonth(monthOfYear) {
    var ts = monthOfYear.split("-")
    var d = new Date(ts[0],ts[1], 0);
    return d.getDate();
}


//加载市级级别列表
function reloadCityGrid(checkDate,counutyCode){
    var data = $("#searchForm").serializeObject();
    data.startDate = dateSt;
    data.endDate = dateEt;
    $("#citylist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}


//加载区县级别列表
function reloadCountyGrid(cityCode){
    var data = $("#searchForm").serializeObject();
    data.startDate = dateSt;
    data.endDate = dateEt;
    data.cityCode = cityCode;
    $("#aiscserverlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//加载科室级别列表aiscserverlist
function reloadLocGrid(orgId,countyCode){
    var data = $("#searchForm").serializeObject();
    data.startDate = dateSt;
    data.endDate = dateEt;
    data.orgId = orgId;
    data.countyCode = countyCode;
    $("#loclist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//加载患者级别列表
function reloadPatientGrid(orgId,locCode){
    var data = $("#searchForm").serializeObject();
    data.startDate = dateSt;
    data.endDate = dateEt;
    data.locCode = locCode;
    data.orgId = orgId;
    $("#patientlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

/** 初始化图表1 **/
function initCharts(data){
    var chart = {
        type: 'column'
    };
    var title = {
        text: '咸阳市患者检查信息统计'
    };

    var xAxis = {
        categories: ['登记数量','采集数量','图像数量','报告数量','已审核数量'],
        crosshair: true
    };
    var yAxis = {
        min: 0,
        title: {
            text: '人次'
        }
    };
    var tooltip = {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    };
    var plotOptions = {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    };
    var credits = {
        enabled: false
    };

    var series= [{
        name: '咸阳市1',
        data: data
    }];

    var json = {};
    json.chart = chart;
    json.title = title;
    json.tooltip = tooltip;
    json.xAxis = xAxis;
    json.yAxis = yAxis;
    json.series = series;
    json.plotOptions = plotOptions;
    json.credits = credits;
    $('#chart01').highcharts(json);
}
