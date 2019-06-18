var orgCode = parent.org.orgCode;
var operatorName = parent.user.operatorName;
var operatorCode = parent.user.operatorCode;
var userStation = parent.userStation;

$(function () {
    $("select").select2();
    selectList();
});

function reloadGrid() {
    var data = $("#searchForm").serializeObject()
    $("#sysOperator").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
    var grid_selector = "#sysOperator";
    var pager_selector = "#sysOperator_pager";
    var data = $("#searchForm").serializeObject();
    $(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT + "/sysmanage/sysoperator/getOperatorList.ajax",
        type: "post",
        cache:false,
        datatype: "json",
        height: '100%',
        width: '100%',
        postData: data,
        colNames: ['工号', '姓名', '当前状态','归属组织机构','最新密码修改时间', '创建时间', '备注','操作'],
        colModel: [
            {name: 'operatorCode', index: 'operatorCode', sortable: false,width:100},
            {
                name: 'operatorName', index: 'operatorName', sortable: false,align:'center',width:90
            },

            {name: 'operatorStateDesc', index: 'operatorStateDesc',sortable: false,width:80,
                formatter:function(cellvalue, options, rowObject){
                    var operatorState = rowObject["operatorState"];
                    if(operatorState==0){
                        return ' <span class="label label-sm label-inverse arrowed-in">'+cellvalue+'</span>';
                    }else if (operatorState==1){
                        return ' <span class="label label-sm label-success">'+cellvalue+'</span>';
                    }else{
                        return ' <span class="label label-sm label-warning">'+cellvalue+'</span>';
                    }
                }
            },
            { name: 'orgName', index: "orgName", sortable: false,width:100,align:'center' },
            {name: 'showChangePswDate', index: 'showChangePswDate',sortable: false,width:80},
            {name: 'showCreateDate', index: 'showCreateDate',sortable: false,width:80},
            {name: 'remarks', index: 'remarks',sortable: false,width:80},
            {name: 'operatorCode', index: 'operatorCode', sortable: false,width:150
                , formatter: function (cellvalue, options, rowObject) {
                    return '<a class="blue" href="javascript:void(0)" onclick="openEdit(\'' + cellvalue + '\')" title="编辑"><i class="icon-pencil bigger-130">编辑</i></a>&nbsp;&nbsp;' +
                        '<a class="green" href="javascript:void(0)" onclick="updateOperatorRole(\'' + cellvalue + '\')" title="权限授权"><i class="icon-key bigger-130">权限授权</i></a>&nbsp;&nbsp;' +
                        '<a class="red" href="javascript:void(0)" onclick="changePwd(\'' + cellvalue + '\')" title="修改密码"><i class="icon-edit-sign bigger-130">修改密码</i></a>';
                }
            }
        ],
        viewrecords: false,
        rowNum: 10,
        rowList: [10, 15, 20, 30],
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
    }).jqGrid("navGrid",pager_selector,{refresh: true});
}

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}


function openEdit(operatorCode) {
    var url;
    if(operatorCode){
        url = GLOBAL.WEBROOT + "/sysmanage/sysoperator/page/edit.html?id="+operatorCode;
    }else{
        url = GLOBAL.WEBROOT + "/sysmanage/sysoperator/page/edit.html";
    }
    layer.open({
        type: 2,
        title:"操作员新增",
        area: ['620px', '500px'],
        fix: false, //不固定
        maxmin: true,
        content: url,
        closeBtn: 0
    });
}

function deleteStation() {
    var ids = $('#sysStation').jqGrid('getGridParam', 'selarrrow');
    if (ids.length == 0) {
        return;
    }
    layerConfirm('是否删除当前选中记录',function() {
        var stationCodes = "";
        for (var i = 0; i < ids.length; i++) {
            //选中行的时间
            var stationCode = $("#sysStation").getCell(ids[i], "stationCode");
            //建一个数组，把选中行的时间添加到这个数组中去。
            stationCodes  += "," +  stationCode;
        }
        stationCodes = stationCodes.substring(1);
        $.ajax({
            type: "GET",
            async: true,
            cache:false,
            url: GLOBAL.WEBROOT + "/sysmanage/sysstation/deleteStations?stationCodes="+stationCodes,
            dataType: 'json',
            success: function (data) {
                if (data.ERRCODE == "0") {
                    infoSuccess('温馨提示', '删除成功');
                    reloadGrid();
                }
                else {
                    info('温馨提示', data.ERRINFO);
                }
            }
        });
    });
}

function changePwd(operatorCode) {
    var url  = GLOBAL.WEBROOT + "/sysmanage/sysoperator/page/operator-pwd-change.html?id="+operatorCode;
    layer.open({
        type: 2,
        title:"修改密码",
        area: ['350px', '320px'],
        fix: false, //不固定
        maxmin: true,
        content: url,
        closeBtn: 0
    });
}

function deleteOperators() {
    var ids = $('#sysOperator').jqGrid('getGridParam', 'selarrrow');
    if (ids.length == 0) {
        info('温馨提示', "请选择至少一条记录禁用");
        return;
    }
    layerConfirm('是否禁用当前选中记录',function() {
        var operatorCodes = "";
        for (var i = 0; i < ids.length; i++) {
            //选中行的时间
            var operatorCode = $("#sysOperator").getCell(ids[i], "operatorCode");
            //建一个数组，把选中行的时间添加到这个数组中去。
            operatorCodes  += "," +  operatorCode;
        }
        operatorCodes = operatorCodes.substring(1);
        $.ajax({
            type: "GET",
            async: true,
            cache:false,
            url: GLOBAL.WEBROOT + "/sysmanage/sysoperator/deleteOperators?operatorCodes="+operatorCodes,
            dataType: 'json',
            success: function (data) {
                if (data.ERRCODE == "0") {
                    infoSuccess('温馨提示', '禁用成功');
                    reloadGrid();
                }
                else {
                    info('温馨提示', data.ERRINFO);
                }
            }
        });
    });
}

//权限授权
function updateOperatorRole(operatorCode){
    var url  = GLOBAL.WEBROOT + "/sysmanage/sysoperator/page/operatorinfo-role.html?id="+operatorCode;
    layer.open({
        type: 2,
        title:"【修改】权限授权",
        area: ['700px', '600px'],
        fix: false, //不固定
        maxmin: true,
        content: url,
        closeBtn: 1
    });
}