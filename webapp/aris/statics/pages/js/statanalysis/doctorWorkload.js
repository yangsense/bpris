//组织机构ID
var orgId = ""; 
var chart01;
$(function () {
	//默认时间
	initDate();
	
	orgId = $("#orgId" , parent.document).val(); 
    
    //检过条件初始化
    initDict();
    //所有select嵌套css样式
    $("#studyDoctorid").select2();
    $("#reportVerifydoctorid").select2();
});

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
var currentLocId = $("#currentLocId" , parent.document).val();

function initChartData(){
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/doctorWorkload/getDocWorkCharts.ajax?orgId="+orgId,
        data : $("#searchForm").serializeObject(),
        success: function (data) {
    		chart01.series[0].setData(data.useCount);
    		chart01.series[1].setData(data.useFee);
    		chart01.xAxis[0].setCategories(data.workX);
        }
    });
}

  
/** 初始化图表1 **/
function initCharts(){
    
	chart01= new Highcharts.Chart({				   //new 一个chart对象
        chart: {
        	renderTo: 'chart01',				   //图表的页面显示器
            type: 'line'                           //指定图表的类型，默认是折线图（line）
        },
        title: {
            text: '医生工作量统计'      	   //指定图表标题
        },
        
        yAxis: [{
            title: {
                text: '报告数量'                  //指定y轴的标题
            }
        },{
            title: {
                text: '总费用'                  //指定y轴的标题
            },
            labels: {
                format: '{value}元',
                style: {
                    color: '#4572A7'
                }
            },
            opposite: true
        }],
        series: [{ 
        	type: 'column',
            name: '报告数量'
        }, {
            name: '总费用',
            yAxis: 1,
            tooltip: {
                valueSuffix: '元'
            }
        }
        ]
    });
   	
}

//initial query dict
function initDict() {
	
	var orgId = $("#currentOrgId" , parent.document).val();
	//科室名称
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscLocByType.ajax?orgId="+orgId+"&locType=E",
        success: function (data) {
            var jsonaiscLocName = eval(data.AiscLocName);
            var loc_nameSelect = $("#locId");
            var currFlag =false ;
            loc_nameSelect.empty();
            loc_nameSelect.append("<option value='-1'>--请选择--</option>");
            for (var i = 0; i < jsonaiscLocName.length; i++) {
                loc_nameSelect.append("<option value='" + jsonaiscLocName[i].locid + "'>" + jsonaiscLocName[i].locdesc + "</option>");
                if(jsonaiscLocName[i].locid == $("#currentLocId").val()){
                	currFlag = true;
                }
                $("#locId").select2();
            }
            if($("#currentLocId").val()=="" || $("#currentLocId").val()==undefined){
            	$("#locId option:first").prop("selected", 'selected');
                $("#locId").select2();
                selectList();
            }else{
            	if(currFlag){
            		$("#locId").val($("#currentLocId").val());
                	$("#locId").select2();
                	//工作列表记录查询
					selectList();
            	}else{
            		$("#locId option:first").prop("selected", 'selected');
                	$("#locId").select2();
                	selectList();
            	}            	
            } 
            
            getDoctor();
        }
    });
	
	
}

function getTotalInfo(){
	var data = $("#searchForm").serializeObject();
	data.orgId= orgId;
	$.ajax({
		type : "POST",
		async : true,
		url : GLOBAL.WEBROOT + '/doctorWorkload/getTotalInfo.ajax',
		data:data,
		success : function(data) {
			if (data.ERRCODE == "0") {
				$("#divTotalInfo").html("报告数量合计："+data.studyitemNumber+"  总费用合计："+data.studyitemPrice+"");
			} else {
				alert("操作失败！" + data.ERRINFO);
			}
		}
	});
}

function getDoctor(){
	//医生下拉列表
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT+"/patientReg/getCareProv.ajax?orgId="+orgId+"&locId="+$("#locId").val()+"&doctype=1",
        success: function (data) {
            var careProvBeans = eval(data.careProvBeans);
            var statusSelect = $("#studyDoctorid");
            statusSelect.empty();
            statusSelect.append("<option value='-1'>请选择医生</option>");
            for (var i = 0; i < careProvBeans.length; i++) {
            	statusSelect.append("<option value='" + careProvBeans[i].careprovId + "'>" + careProvBeans[i].careprovName + "</option>");
            }
            $("#reportVerifydoctorid").html(statusSelect.html());
        }
	});
}
//重置
function reset(){
	$("#startTime").val("");
	$("#endTime").val("");
	$("#studyDoctorid").select2("val","-1"); 
	$("#reportVerifydoctorid").select2("val","-1"); 
	$("#locId").select2("val","-1"); 
}

function exportDoctorWork(){
	var data = $("#searchForm").serialize();
	var url=GLOBAL.WEBROOT + '/doctorWorkload/exportDoctorWork.ajax?'+data;
    window.open(url);
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject()
    data.page = 1;
    data.pageSize = 10;
	$("#doctorlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#doctorlist";
    var pager_selector = "#doctorlist_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/doctorWorkload/queryDoctorWorklistList.ajax?orgId="+orgId,
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '50%',
        postData :data,
        colNames: ['reportDoctorid','reportVerifydoctorid','studystatusCode','报告日期','报告医生','审核医生','报告状态','报告数量','总费用','阳性率'],
        colModel: [
            {name: 'reportDoctorid', index: 'reportDoctorid',width:60,sortable: false,hidden:true},
            {name: 'reportVerifydoctorid', index: 'reportVerifydoctorid',width:60,sortable: false,hidden:true},
            {name: 'studystatusCode', index: 'studystatusCode',width:60,sortable: false,hidden:true},
            {name: 'reportDate', index: 'reportDate',width:60,sortable: false},
            {name: 'reportDoctorname', index: 'reportDoctorname',width:60, sortable: false},
            {name: 'reportVerifydoctorname', index: 'reportVerifydoctorname',width:60, sortable: false},
            {name: 'studystatusCodeDesc', index: 'studystatusCodeDesc', width:60,sortable: true},
            {name: 'reportId', index: 'reportId',width:60, sortable: false,
                formatter: function (param1, param2, recode) {
                    var date = recode.reportDate;
                    var studyitemNumber = recode.reportId == undefined ? "" : recode.reportId;
                    return '<div>' +
                        '<a class="blue" href="javascript:void(0)" onclick="view(\'' + date + '\',this)">' + studyitemNumber + '</a>' +
                        "</div>";
                }
            },
            {name: 'studyitemPrice', index: 'studyitemPrice',width:60, sortable: false},
            {name: 'zb', index: 'zb',width:50,sortable: false}   
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
            var data=result;
            var careprovName=new Array(result.rows.length);
            var reportId=new Array(result.rows.length);
            var studyitemPrice=new Array(result.rows.length);
            for(var i=0;i<result.rows.length;i++){
            	careprovName[i]=result.rows[i].careprovName;
            	reportId[i]=result.rows[i].reportId;
            	studyitemPrice[i]=result.rows[i].studyitemPrice;
            }
    		initChartData();
    		//空列填充
    		var re_records = $("#doctorlist").getGridParam('records');
        	if(re_records < 10 )
        	{
        		for(var i=0,j=10-re_records;i<=j;i++){
        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
        			$("#doctorlist").jqGrid("addRowData",(re_records+1),{ '报告日期':'','医生名称':'','报告数量':'','总费用':''},"");
        		}
        	}
        	getTotalInfo();
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });

    initCharts();

}

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}


function view(date,obj){
    var rowid = $(obj).closest('tr').attr("id");
    var rowData = $("#doctorlist").jqGrid('getRowData',rowid);
    var data = $("#searchForm").serialize();
    layer.open({
        type: 2,
        title:"检查记录",
        area: ['1000px', '450px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/doctorWorkload/studyInfoList.html?studystatusCode="+rowData.studystatusCode+"&reportDate="+date+"&reportDoctorid="+rowData.reportDoctorid+"&reportVerifydoctorid="+rowData.reportVerifydoctorid
    });
}

 