  
$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    });
	
	selectList();
	$("#conlocId").select2();
	initSelect2forDiv($("div[name='orgName']"));
	
	$("#aiscConsultForm").validate({

		// 非空校验
		rules : {
//			conlocId:{
//				isSelect:true
//			}
		},
		errorElement:'em',
		messages: {
//			locCode:{
//        		required:'必填字段',
//        		remote:'已存在'
//        	}
		},
		submitHandler : save
	});

	$("#saveBtn").click(function(){
		$("#aiscConsultForm").submit();
	});
});

function save() {
	
		//验证
        if($("#conorgId").val()=="" || $("#conorgId").val() == null){
        	 layer.msg('请选择会诊机构', {icon: 5});
        	 return false;
        }
        if($("#conlocId").val()=="" || $("#conlocId").val() == null||$("#conlocId").val()=="-1"){
        	 layer.msg('请选择会诊科室', {icon: 5});
        	 return false;
        }
		//提交
	    var data = $("#aiscConsultForm").serializeObject();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscConOrg",
	        dataType: 'json',
	        contentType: 'application/json',
	        data: data,
	        success: function (data) {
	            if (data.ERRCODE == "0") {
	            	layer.msg('操作成功', {icon: 1});
	                reloadGrid();
	            }
	            else {
	                layer.msg('操作失败', {icon: 1});
	            }
	        }
	    });
}


function reloadGrid(){
    var data = $("#aiscConsultForm").serializeObject()
	$("#aiscConsultlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#aiscConsultlist";
    var pager_selector = "#aiscConsult_pager";
    var data = $("#aiscConsultForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/basecode/queryAiscConsult.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['conorganizatId','会诊机构ID','会诊机构','会诊科室ID','会诊科室','操作'],
        colModel: [
        	{name: 'conorganizatId', index: "conorganizatId",width:60, sortable: false,hidden:true},
        	{name: 'conorgId', index: "conorgId",width:60, sortable: false},
            {name: 'hzjgmc', index: "hzjgmc",width:60, sortable: false},
            {name: 'conlocId', index: 'conlocId', width:60,sortable: false},
            {name: 'hzks', index: 'hzks',width:70, sortable: false},
            
            {name: 'conorganizatId', conorganizatId: "conorganizatId",width:50,  sortable: false
            	,formatter: function (param1, param2, recode) {
                    return '<div class="action-buttons">' +
                        '<a class="green" href="javascript:void(0)" onclick="deleteAisConorg(\'' + param1 + '\',\'delete\')" title="删除"><i class="icon-remove bigger-120"></i></a>' +
                        "</div>";
                }
            }
        ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: false,
//        multiboxonly: true,
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

//美化检查项目描述，增加ajax请求功能
function initSelect2forDiv(div){
	div.select2({
		placeholder: "请选择机构",
        minimumInputLength: 0, 
        maximumInputLength:100,//限制最大字符，以防坑货
        id : function(repo) { //这个很重要， 没有这个就选择不了值
            return repo.orgId+"_"+repo.orgName;  
        },
        ajax: {
            url: GLOBAL.WEBROOT+"/basecode/getHuizhenOrg.ajax",
            dataType: 'json',
            method:'post',
            data: function (term, page) {
                return {
                	orgId:$("#orgId").val(),
                	//locId:$("#studyApplocid option:selected").val(),
                	locId:$("#locId").val(),
                    keyword: term,
                    rows:10,
                    page: 1
                };
            },

            results: function (data, page) {
                return {results: eval(data.huizhenOrgList)};
            }
        },
        initSelection: function(element, callback) {
//            var data = [];
//            $(element.val().split(";")).each(function () {
//                if (this.length > 0) {
//                    data.push(this);
//                }
//            });
//            callback(data);
        },
        formatResult: formatResult,
        formatSelection: formatSelection,
        dropdownCssClass: "bigdrop",
        formatSearching: function () {
            return "加载中..."
        },
        formatNoMatches: function () {
            return "没有匹配结果."
        },
        escapeMarkup: function (m) { return m; }    
	});	
}

//搜索结果显示样式
function formatResult(repo) {
	    if (repo.loading) return repo.text;
	    //var markup = "<div style='display:inline;'>"+repo.itemmastId+"</div><div style='color:#4F4F4F;display:inline'>"+repo.itemmastDesc+"</div>";
	    var markup = "<div style='color:#4F4F4F;display:inline'>"+repo.orgName+"</div>";
	    return markup;
}

//选中后输入框展现
function formatSelection(repo) {	 
    return repo.orgId + "_" + repo.orgName;
    //return repo.itemmastDesc; 
}

//会诊机构变动--重置
function changeOrgLoc(obj){
	setBodyPart(obj,$(obj).val()); 
}
//会诊科室设置
function setBodyPart(obj,str){
	try {
		var orgId = str.split("_")[0];
		$("#conorgId").val(orgId);
	} catch (e) { 
	}
	 
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT+"/basecode/getLocForOrg.ajax?orgId="+orgId,
        success: function (data) {
            var conlocBeans = eval(data.conlocBeans);
            var conlocSelect = $("#conlocId");
            conlocSelect.empty(); 
            conlocSelect.append("<option value='-1'>请选择会诊科室</option>");
            for (var i = 0; i < conlocBeans.length; i++) {
                conlocSelect.append("<option value='" + conlocBeans[i].locid + "' >" + conlocBeans[i].locdesc + "</option>");
            }
            conlocSelect.select2();
        }
    });
}

function deleteAisConorg(param){
	layer.confirm('是否确定删除该信息？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
        type: "GET",
        async: true,
        url: GLOBAL.WEBROOT +"/basecode/deleteAisConorg.ajax?id="+param,
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


//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}


function deleData(param){
	layer.confirm('是否确定删除该信息？', {
	    btn: ['确定','取消'] //按钮
	}, function(){
		$.ajax({
        type: "GET",
        async: true,
        url: GLOBAL.WEBROOT +"/basecode/deleteAisLoc.ajax?id="+param,
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
function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}