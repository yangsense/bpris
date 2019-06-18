var chart01='';  
$(function () {
	//默认时间
	initDate();
	
    //检过条件初始化
    initDict();


    //所有select嵌套css样式
    $("#modalityId").select2();
    
    
    
});

var currentLocId = $("#currentLocId" , parent.document).val();
var orgId = $("#currentOrgId" , parent.document).val();
//初始化查询进间
function initDate(){
	var n=new Date();
    n.setDate(n.getDate()-30); 
    $("#studyStarttime").val(n.getFullYear()+"-"+ Appendzero(n.getMonth()+1)+"-"+ Appendzero(n.getDate()));
    var u=new Date();
    u.setDate(u.getDate()); 
    $("#studyEndtime").val(u.getFullYear()+"-"+ Appendzero(u.getMonth()+1)+"-"+ Appendzero(u.getDate()));
}
function Appendzero (obj) {
	if (obj < 10) return "0" + obj; else return obj;
}
function initChartData(){
	
	//科室名称
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/equiWorkload/getEquiWorkCharts.ajax?orgId="+orgId,
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
            text: '设备工作量统计'      	   //指定图表标题
        },
        
        yAxis: [{
            title: {
                text: '数量'                  //指定y轴的标题
            }
        },{
            title: {
                text: '费用'                  //指定y轴的标题
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
            name: '设备检查数量'
        }, {
            name: '设备总费用',
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
        }
    });
    
//	//检查设备下拉列表
//    $.ajax({
//        type: "POST",
//        url: GLOBAL.WEBROOT+"/patientReg/getEquipment.ajax",
//        success: function (data) {
//            var equipmentBeans = eval(data.equipmentBeans);
//            var statusSelect = $("#equipmentId");
//            statusSelect.empty();
//            statusSelect.append("<option value='-1'>--请选择--</option>");
//            for (var i = 0; i < equipmentBeans.length; i++) {
//                statusSelect.append("<option value='" + equipmentBeans[i].equipmentId + "' roomId = '" + equipmentBeans[i].roomId + "' >" + equipmentBeans[i].equipmentDesc + "</option>");
//            }
//        }
//    });
    //根据科室获取检查设备类型
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT+"/workList/getEquimentByLocId.ajax?orgId="+orgId+"&locId="+$("#locId").val(),
        success: function (data) {
            var jsonEqimentType = eval(data.MODALITY_TYPE);
            var modalitySelect = $("#modalityId");
            modalitySelect.empty(); 
            modalitySelect.append("<option value='-1'>全部</option>");
            for (var i = 0; i < jsonEqimentType.length; i++) {
            	modalitySelect.append("<option value='" + jsonEqimentType[i].itemno + "'>" + jsonEqimentType[i].itemname + "</option>");
            }
        }
    });  
}

//重置
function reset(){
	$("#studyStarttime").val("");
	$("#studyEndtime").val("");
	$("#modalityId").select2("val","-1"); 
	$("#locId").select2("val","-1"); 
}

function exportEquiWork(){
	var data = $("#searchForm").serialize();
	var url=GLOBAL.WEBROOT + '/equiWorkload/exportEquiWork.ajax?'+data;
    window.open(url);
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject()
    data.page = 1;
    data.pageSize = 10;
	$("#equilist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#equilist";
    var pager_selector = "#equilist_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/equiWorkload/queryEquiWorklistList.ajax?orgId="+orgId,
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['modalityId','检查日期','设备类型','检查数量','总费用','阳性率'],
        colModel: [
            {name: 'modalityId', index: 'modalityId',width:50, sortable: false,hidden:true},             
            {name: 'cStudyDatetime', index: 'cStudyDatetime',width:50, sortable: false},             
            {name: 'equipmentDesc', index: 'equipmentDesc', width:50,sortable: false},
            {name: 'studyitemNumber', index: 'studyitemNumber',width:50, sortable: false,
            	formatter: function (param1, param2, recode) {
                    var date =recode.cStudyDatetime;
                    var studyitemNumber = recode.studyitemNumber==undefined?"":recode.studyitemNumber;
                        return '<div>' +
                            '<a class="blue" href="javascript:void(0)" onclick="view(\'' + date + '\',this)">'+studyitemNumber+'</a>' +
                            "</div>";
                    }
            },
            {name: 'studyitemPrice', index: 'studyitemPrice',width:50,sortable: false},
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
            var deviceName=new Array(result.rows.length);
            var fee=new Array(result.rows.length);
            var checkcnt=new Array(result.rows.length);
            for(var i=0;i<result.rows.length;i++){
            	deviceName[i]=result.rows[i].equipmentDesc;
            	fee[i]=result.rows[i].studyitemNumber;
            	checkcnt[i]=result.rows[i].studyitemPrice;
            }
            //图表chart
    		initChartData();
    		
    		//空列填充
    		var re_records = $("#equilist").getGridParam('records');
        	if(re_records < 10 )
        	{
        		for(var i=0,j=10-re_records;i<=j;i++){
        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
        			$("#equilist").jqGrid("addRowData",(re_records+1),{ '检查日期':'','设备名称':'','检查数量':'','总费用':''},"");
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
function getTotalInfo(){
	var data = $("#searchForm").serializeObject();
	data.orgId= orgId;
	$.ajax({
		type : "POST",
		async : true,
		url : GLOBAL.WEBROOT + '/equiWorkload/getTotalInfo.ajax',
		data:data,
		success : function(data) {
			if (data.ERRCODE == "0") {
				$("#divTotalInfo").html("检查数量合计："+data.studyitemNumber+"  总费用合计："+data.studyitemPrice+"");
			} else {
				alert("操作失败！" + data.ERRINFO);
			}
		}
	});
}
function view(date,obj){
	var rowid = $(obj).closest('tr').attr("id");
	var rowData = $("#equilist").jqGrid('getRowData',rowid);
	var data = $("#searchForm").serialize();
	layer.open({
        type: 2,
        title:"检查记录",
        area: ['1000px', '450px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/equiWorkload/studyInfoList.html?orgId="+orgId+"&"+data+"&studyDatetime="+date+"&nowequipmentId="+rowData.modalityId
    });
}
 