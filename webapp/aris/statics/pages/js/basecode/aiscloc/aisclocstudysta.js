  
$(function () {
	initDict();
	
});
 
//initial query dict
function initDict() {
	
    //科室类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getStudyStatus.ajax",
        success: function (data) {
            var jsonStudyStatus = eval(data.STUDY_STATUS);
            var StudyStatusSelect = $("#studyStatus");
            StudyStatusSelect.empty();
            StudyStatusSelect.append("<option value='-1'>请选择</option>");
            for (var i = 0; i < jsonStudyStatus.length; i++) {
            	StudyStatusSelect.append("<option value='" + jsonStudyStatus[i].itemno + "'>" + jsonStudyStatus[i].itemname + "</option>");
            }
            StudyStatusSelect.select2();
        }
    });
    //机构列表加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/bpris/getOrgs.ajax?operatorCode='+parent.user.operatorCode+"&sysType=25",
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data);
	        var orgSelect = $("#orgId");
	        orgSelect.empty(); 
	        //orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        orgSelect.val(parent.$("#currentOrgId").val()==""?"-1":parent.$("#currentOrgId").val());
	        orgSelect.select2();  
	        selectList();
		}
	}); 
    loadLoc();
}

function loadLoc(){
	//科室名称
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscLocName.ajax?orgId="+$("#orgId").val(),
        success: function (data) {
            var jsonaiscLocName = eval(data.AiscLocName);
            var loc_nameSelect = $("#locId");
            loc_nameSelect.empty();
            loc_nameSelect.append("<option value='-1'>--请选择--</option>");
            for (var i = 0; i < jsonaiscLocName.length; i++) {
                loc_nameSelect.append("<option value='" + jsonaiscLocName[i].locid + "'>" + jsonaiscLocName[i].locdesc + "</option>");
            }
            loc_nameSelect.select2();
        }
    });
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject()
	$("#studyStatuslist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#studyStatuslist";
    var pager_selector = "#studyStatus_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/queryStudyStaLocList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['locStudystaid','科室ID','科室代码','科室名称','科室英文描述名称','检查状态代码','检查状态','所属机构','操作'],
        colModel: [
            {name: 'locStudystaId', index: 'locStudystaId', width:50,sortable: false,hidden:true},
            {name: 'locId', index: 'locId', width:50,sortable: false},
            {name: 'locCode', index: 'locCode',width:50, sortable: false},
            {name: 'locDesc', index: 'locDesc',width:50, sortable: false},
            {name: 'locEndesc', index: 'locEndesc',width:80, sortable: false},
            {name: 'studyStatus', index: 'locPhone',width:80,  sortable: false},
            {name: 'statusName', index: 'statusName',width:60, sortable: false},
            {name: 'orgName', index: "orgName",width:60, sortable: false},
            {name: 'locStudystaId', index: "locStudystaId",width:50,  sortable: false
            	,formatter: function (param1, param2, recode) {
                    return '<div class="action-buttons">' +
                        '<a class="green" href="javascript:void(0)" onclick="deleData(\'' + param1 + '\',\'delete\')" title="删除"><i class="icon-remove bigger-120"></i></a>' +
                        "</div>";
                }
            }
        ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: true,
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

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}
//操作
function view(id,type) {
	var title ="新增修改科室与检查状态关联";
	var	url="/basecode/aiscLocStatusDetail.html?id="+id+"&type="+type;
    var index = layer.open({
        type: 2,
        title:title,
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
    layer.full(index);
}

function deleData(param){
	layer.confirm('是否确定删除该信息？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
        type: "GET",
        async: true,
        url: GLOBAL.WEBROOT +"/basecode/deleteAisLocStudySta.ajax?id="+param,
        dataType: 'json',
        success: function (data) {
			if (data.ERRCODE == "0") {
                layer.msg('删除成功', {icon: 1});
                reloadGrid();
            }
            else {
                layer.msg('删除失败', {icon: 1});
            }
        }
   		});
	}, function(){
	    //layer.msg('奇葩么么哒', {icon:2});
	});
}
