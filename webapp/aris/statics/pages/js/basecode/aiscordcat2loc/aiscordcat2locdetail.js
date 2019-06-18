$(function () {
	
	//初始化科室，检查大类，增加ajax请求功能
//    initSelect3forDiv($("div[name='locDesc']"));
//    initSelect2forDiv($("div[name='ordcategoryDesc']"));
//    initOrg();
	$("#closeBtn").click(function(){
       closeDiv();
    });
	initDict(); 
	
	var type = $("#aiscOrdCat2LocForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscOrdCat2LocForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#showText").text("保 存");
	}else{
		com.ai.bdx.util.reset("aiscOrdCat2LocForm");
		$("#showText").text("保 存");
	}

	$("#aiscOrdCat2LocForm").validate({

		// 非空校验
		rules : {
			locId : {
				isSelect:true
			},
			orgId:{
				isSelect:true
			},
			ordcatId:{
				isSelect:true
			}
		},
		errorElement:'em',
		messages: {
		},
		submitHandler : save
	});


	$("#saveBtn").click(function(){
		 $("#aiscOrdCat2LocForm").submit();
	});


});


function checkAdd() {
	//校验新增数据是否重复
	var  flag= false ;
	var orgId = $("#orgId").val();
	var locId = $("#locId").val();
	var ordcatId = $("#ordcatId").val();
	
	var ordcat2locId = $("#ordcat2locId").val();
	if($("#aiscOrdCat2LocForm").attr("dataType")=="add"){
		ordcat2locId = 0;
	}
	
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/checkAiscOrdCat2Loc.ajax?orgId='+orgId+"&locId="+locId+"&ordcatId="+ordcatId+"&ordcat2locId="+ordcat2locId,
		dataType:'json',
		success:function(data){
			if(data.result=="true"){
				flag=true ;
			}
		}
	});
	return flag ;
}


function initDict() {
    //机构列表加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/bpris/getOrgs.ajax?operatorCode='+parent.parent.user.operatorCode+"&sysType=25",
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data);
	        var orgSelect = $("#orgId");
	        orgSelect.empty(); 
	        orgSelect.append("<option value='-1'>--请选择机构--</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        orgSelect.val($("#aiscOrdCat2LocForm").attr("old_org_id")==""?parent.parent.$("#currentOrgId").val():$("#aiscOrdCat2LocForm").attr("old_org_id"));
	        orgSelect.select2();  
		}
	}); 
    
    loadLoc();
    
    initCate();
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
            if(jsonaiscLocName.length>0){
                loc_nameSelect.val($("#aiscOrdCat2LocForm").attr("old_loc_id")==""?"-1":$("#aiscOrdCat2LocForm").attr("old_loc_id"));
            }
            loc_nameSelect.select2();
        }
    });
}

function initCate() {
	
    //检查大类下拉
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdCate/getAiscOrdCategory.ajax?orgId="+$("#orgId").val(),
        success: function (data) {
            var jsonOrdCategory = eval(data.AiscOrdCategoryName);
            var ordCategorySelect = $("#ordcatId");
            ordCategorySelect.empty();
            ordCategorySelect.append("<option value='-1'>--请选择--</option>");
            for (var i = 0; i < jsonOrdCategory.length; i++) {
            	ordCategorySelect.append("<option value='" + jsonOrdCategory[i].ordcategoryid + "'>" + jsonOrdCategory[i].ordcategorydesc + "</option>");
            }
            if(jsonOrdCategory.length>0){
                ordCategorySelect.val($("#aiscOrdCat2LocForm").attr("old_ordcatId")==""?"-1":$("#aiscOrdCat2LocForm").attr("old_ordcatId"));
            }
            ordCategorySelect.select2();
        }
    });
}


function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

/**
//回填选中框列表
function setInitSelectforDiv(){

	//获取回传值
  var ordcategoryId = $("#_ordcateId").val();
  var ordcategoryDesc = $("#_ordcateDesc").val();
	
	var trcp = $("#configTab").find("[tag=ordcat2locItem]");
	
	var oldcareprovdiv= trcp.find("div[name='ordcategoryDesc']").prev("div");
	oldcareprovdiv.remove();	
	//重新渲染检查项目描述
	initSelect2forDiv(trcp.find("div[name='ordcategoryDesc']"));
	   
	var newcareprovdiv= trcp.find("div[name='ordcategoryDesc']").prev("div");

	//值回填
	newcareprovdiv.find("span[class='select2-chosen']").html(ordcategoryDesc);
	newcareprovdiv.find("input[name='ordcategoryId']").val(ordcategoryId);
	
}



//检查大类，增加ajax请求功能
function initSelect2forDiv(div){
	div.select2({
	  placeholder: "选择检查大类",
    minimumInputLength: 0, 
    maximumInputLength:100,//限制最大字符，以防坑货
    id : function(repo) { //这个很重要， 没有这个就选择不了值
        return repo.ordcategoryId+"_"+repo.ordcategoryDesc;  
    },
    ajax: {
        url: GLOBAL.WEBROOT+"/basecode/getOrdcategoryItem.ajax",
        dataType: 'json',
        method:'post',
        data: function (term, page) {
            return {
//            	orgId:orgId,
//            	locId:$("#studyApplocid option:selected").val(),
                keyword: term,
                rows:10,
                page: 1
            };
        },

        results: function (data, page) {
            return {results: data.dataList};
        }
    },
    initSelection: function(element, callback) {
        var data = [];
        $(element.val().split(";")).each(function () {
            if (this.length > 0) {
                data.push(this);
            }
        });
        callback(data);
    },
    formatResult: formatResult2,
    formatSelection: formatSelection2,
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
function formatResult2(repo) {
	    if (repo.loading) return repo.text;
	    //var markup = "<div style='display:inline;'>"+repo.itemmastId+"</div><div style='color:#4F4F4F;display:inline'>"+repo.itemmastDesc+"</div>";
	    var markup = "<div style='color:#4F4F4F;display:inline'>"+repo.ordcategoryDesc+"</div>";
	    return markup;
}
//选中后输入框展现
function formatSelection2(repo) {	 
return repo.ordcategoryId + "_" + repo.ordcategoryDesc
+"<input type='hidden' name='ordcatId' value="+repo.ordcategoryId+">"
;
}

*/
function save() {
		//验证
		if (checkAdd()){
			layer.msg('机构、科室、检查大类存在重复数据，请重新选择！', {icon: 2});
			return ;
		}
			
		//提交
	    var data = $("#aiscOrdCat2LocForm").serializeObject();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscOrdCat2Loc.ajax",
	        dataType: 'json',
	        contentType: 'application/json',
	        data: data,
	        success: function (data) {
	            if (data.ERRCODE == "0") {
	            	layer.msg('操作成功', {icon: 1});
	                parent.reloadGrid();
	                setTimeout(closeDiv,1500);
	            }
	            else {
	                layer.msg('新增失败', {icon: 1});
	            }
	        }
	    });
}
