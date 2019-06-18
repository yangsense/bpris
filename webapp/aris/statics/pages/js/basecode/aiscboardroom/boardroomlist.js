  
$(function () {
	$("select").select2();
	selectList();
	
});
 

function reloadGrid(){
    var data = $("#searchForm").serializeObject()
	$("#boardroomlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#boardroomlist";
    var pager_selector = "#boardroom_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/queryBoardroomList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['会议室ID','会议室名称','登录校验模型','会议室密码','允许最大用户数','是否允许主席密码','主席密码','密钥 ','操作'],
        colModel: [
            {name: 'boardroomId', index: 'boardroom_Id', width:60,sortable: false},
            {name: 'boardroomName', index: 'boardroom_Name',width:70, sortable: false},
            {name: 'verifymode', index: 'verify_mode',width:90,  sortable: false,
            	formatter: function (param1, param2, recode) {
                 	if(param1=='1'){
                 		return '用户密码验证';
                 	}else if(param1=='2'){
                 		return '会议室密码验证';
                 	}else if(param1=='3'){
                 		return '匿名登录';
                 	}
            	}
            },
            {name: 'password', index: 'pass_word',width:70, sortable: false},
            {name: 'maxusercount', index: 'maxuser_count',width:100, sortable: false},
            {name: 'enablechairpwd', index: 'enable_chairpwd',width:80,  sortable: false,
            	formatter: function (param1, param2, recode) {
                 	if(param1=='0'){
                 		return '不启用';
                 	}else if(param1=='1'){
                 		return '启用';
                 	}           
            	},
            },
            {name: 'chairpassword', index: 'chair_password',width:60, sortable: false},
            {name: 'keycode', index: "key_code",width:60, sortable: false,hidden:true},
            {name: 'boardroomId', index: "board_roomId",width:50,  sortable: false
            	,formatter: function (param1, param2, recode) {
                    if(resources.containRes('deleteBtn')) {
                        return '<div class="action-buttons">' +
                            '<a class="blue" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'query\')" title="查看"><i class="icon-search bigger-120"></i></a>' +
                            '<a class="red" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'update\')" title="修改"><i class="icon-edit bigger-120"></i></a>' +
                            '<a class="green" href="javascript:void(0)" onclick="deleData(\'' + param1 + '\',\'delete\')" title="删除"><i class="icon-remove bigger-120"></i></a>' +
                            "</div>";
                    }else{
                        return '<div class="action-buttons">' +
                            '<a class="blue" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'query\')" title="查看"><i class="icon-search bigger-120"></i></a>' +
                            '<a class="red" href="javascript:void(0)" onclick="view(\'' + param1 + '\',\'update\')" title="修改"><i class="icon-edit bigger-120"></i></a>' +
                            "</div>";
                    }
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

function roomDouser(){
	var index = layer.open({
        type: 2,
        title:"用户会议授权",
        area: ['800px', '500px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/basecode/boardroomDouser.html",
    });
	layer.full(index);
}

//操作
function view(id,type) {
	var url ;
	var title ;
	if(type=="query"){
		title="查看会议室信息";
		url="/basecode/boardroomDetail.html?id="+id+"&type="+type;
		//所有控件不可用
	}else if(type=="update"){
		title = "修改会议室信息";
		url="/basecode/boardroomDetail.html?id="+id+"&type="+type;
	}else{
		title = "新增会议室信息";
		url="/basecode/boardroomDetail.html?id="+id+"&type="+type;
	}
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
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT +"/basecode/deleBoardroom.ajax?id="+param,
        dataType: 'json',
        success: function (data) {
			if (data.ERRCODE == "0") {
                layer.msg('删除成功', {icon: 1});
                reloadGrid();
            }
//            else {
//                layer.msg('删除失败', {icon: 1});
//            }
        }
   		});
	}, function(){
	    //layer.msg('奇葩么么哒', {icon:2});
	});
}

