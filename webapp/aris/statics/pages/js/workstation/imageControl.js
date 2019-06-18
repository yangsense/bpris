$(function () {
	 //检过条件初始化
    initDict();
	//工作列表记录查询
//	selectRisList();
	selectPacsList();
   
    //所有select嵌套css样式
    $("#modalitiesinstudy").select2();
});

function setPacsDate(str){
//	alert($(str).val());
	$("#receptiondate").val($(str).val());
}
var orgId = $("#currentOrgId" , parent.document).val();

//initial query dict
function initDict() {
	//科室名称
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscLocName.ajax?orgId="+orgId,
        success: function (data) {
            var jsonaiscLocName = eval(data.AiscLocName);
            var loc_nameSelect = $("#locId");
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
                selectRisList();
            }else{
            	if(currFlag){
            		$("#locId").val($("#currentLocId").val());
                	$("#locId").select2();
                	selectRisList();
            	}else{
            		$("#locId option:first").prop("selected", 'selected');
                	$("#locId").select2();
                	selectRisList();
            	}            	
            } 
        }
    });
    
	//检查设备下拉列表
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscEquipType.ajax",
        success: function (data) {
            var jsonAiscEquipType = eval(data.AiscEquipType);
            var Equip_TypeSelect = $("#modalitiesinstudy");
            Equip_TypeSelect.empty();
            Equip_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscEquipType.length; i++) {
                Equip_TypeSelect.append("<option value='" + jsonAiscEquipType[i].itemname + "'>" + jsonAiscEquipType[i].itemname + "</option>");
            }
        }
    });
}

//重置
function reset(){
	$("#studyStarttime").val("");
	$("#locId").select2("val","-1"); 
	$("#equipmentId").select2("val","-1"); 
}

function reloadRisGrid(){
    var data = $("#searchForm").serializeObject()
    data.page = 1;
    data.pageSize = 10;
	$("#rislist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectRisList() {
	var grid_selector = "#rislist";
    var pager_selector = "#rislist_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/workList/queryRisList.ajax?orgId="+orgId,
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['登记号','姓名','检查号','检查项目'],
        colModel: [
            {name: 'patientId', index: 'patientId',width:50, sortable: false},             
            {name: 'patientName', index: 'patientName', width:50,sortable: false},
            {name: 'studyAccnumber', index: 'studyAccnumber',width:50, sortable: false},
            {name: 'studyItemdesc', index: 'studyItemdesc',width:50,sortable: false}
        ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: true,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function (result) {
            com.ai.bdx.util.updatePagerIcons(this);
    		//空列填充
    		var re_records = $("#rislist").getGridParam('records');
        	if(re_records < 10 )
        	{
        		for(var i=0,j=10-re_records;i<=j;i++){
        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
        			$("#rislist").jqGrid("addRowData",(re_records+1),{ '登记号':'','姓名':'','检查号':'','检查项目':''},"");
        		}
        	}
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}


//查询列表
function selectPacsList() {
	var grid_selector = "#pacslist";
    var pager_selector = "#pacslist_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/workList/queryPacsList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['key','登记号','姓名','检查号','设备类型'],
        colModel: [
        	{name: 'patientkey', index: 'patientkey',width:50, sortable: false,hidden:true},             
            {name: 'patientid', index: 'patientid',width:50, sortable: false},             
            {name: 'patientname', index: 'patientname', width:50,sortable: false},
            {name: 'accessionnumber', index: 'accessionnumber',width:50, sortable: false},
            {name: 'modalitiesinstudy', index: 'modalitiesinstudy',width:50,sortable: false}        
        ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: true,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function (result) {
            com.ai.bdx.util.updatePagerIcons(this);
    		//空列填充
    		var re_records = $("#pacslist").getGridParam('records');
        	if(re_records < 10 )
        	{
        		for(var i=0,j=10-re_records;i<=j;i++){
        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
        			$("#pacslist").jqGrid("addRowData",(re_records+1),{ '登记号':'','姓名':'','检查号':'','设备类型':''},"");
        		}
        	}
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}

function reloadPacsGrid(){
    var data = $("#searchForm").serializeObject()
    data.page = 1;
    data.pageSize = 10;
	$("#pacslist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

function UpdateControlInfo(){
	var risgr = $("#rislist").jqGrid("getGridParam","selarrrow");
	var pacsgr = $("#pacslist").jqGrid("getGridParam","selarrrow");
	if (risgr.length > 1|| risgr.length == 0) {
		layer.alert('请选择一条RIS记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	if (pacsgr.length > 1|| pacsgr.length == 0) {
		layer.alert('请选择一条PACS记录进行操作！', {icon: 5,shadeClose: true});
		return;
	}
	
	var rowRis = $("#rislist").jqGrid('getRowData',risgr[0]); 
	var rowPacs = $("#pacslist").jqGrid('getRowData',pacsgr[0]); 
	
	layer.confirm('是否把'+rowPacs.patientname+'病人的登记号'+rowPacs.patientid+'，检查号'+rowPacs.accessionnumber+'修改成'+rowRis.patientId+'，'+rowRis.studyAccnumber+'？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
        type: "GET",
        async: true,
        url: GLOBAL.WEBROOT +"/workList/updateControlInfo.ajax?patientkey="+rowPacs.patientkey+"&accessionnumber="+rowRis.studyAccnumber+"&patientid="+rowRis.patientId,
        dataType: 'json',
        success: function (data) {
			if (data.ERRCODE == "0") {
                layer.msg('更改成功', {icon: 1});
                reloadPacsGrid();
            }
            else {
                layer.msg('更改失败', {icon: 1});
            }
        }
   		});
	}, function(){
	    //layer.msg('奇葩么么哒', {icon:2});
	});
}

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}
 
 