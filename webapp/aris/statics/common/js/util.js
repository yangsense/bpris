/**
 * 定义JS命名空间
 */
if (typeof com == "undefined") {
    var com = {};
}

com.ai = com.ai || {}
com.ai.bdx = com.ai.bdx || {};

com.ai.bdx.util = com.ai.bdx.util || (function () {
	/**
	 * 所有查询界面公共js
	 * 重置查询条件
	 * @memberOf {TypeName} 
	 */
	function reset(args){
		$("#"+args).find("input,textarea").each(function(){
		 	this.value ="";
		});
		//查询界面select2
		$("#"+args).find("select").each(function(){
			 $("#s2id_"+this.id).find("span").text($("#"+this.id+" option:first").text());
			 $(".select2-arrow").text("");
			 $("#"+this.id).val(-1);
		});
		
	}
	/**
	 * 查看界面公共js
	 * 置灰所有控件
	 * @memberOf {TypeName} 
	 */
	function disableAll(args){
		$("#"+args).find("input,textarea,select").each(function(){
			 	this.disabled="disabled";
		});
	}

    /**
     * href的get提交转post提交
     * url为固定参数，必须有
     * 其余为不定参数，必须为数组类型，其中第一个元素是name，第二个元素为value eg:["id",1]
     * eg: hrefPost('test.html',['id',1],['name','zhangsan']...)
     * @param url
     */
    function hrefPost(url) {

        var html = "<form action=" + url + " method='post' name='hrefForm' style='display:none'>"

        for (var i = 1; i < arguments.length; i++) {
            html += "<input type='hidden' name='" + arguments[i][0] + "' value='" + arguments[i][1] + "'>"
        }
        html += "</form>";

        document.write(html);
        document.hrefForm.submit();

    }
    
    function hrefPostMult(url,params) {

        var html = "<form action=" + url + " method='post' name='hrefForm' style='display:none'>"

        if(isNotEmpty(params)){
        	var items = params.params;
        	for(var i = 0 ;i < items.length; i ++){
        		html += "<input type='hidden' name='" + items[i][0] + "' value='" + items[i][1] + "'>"
        	}
        }
        
//        for (var i = 1; i < arguments.length; i++) {
//            html += "<input type='hidden' name='" + arguments[i][0] + "' value='" + arguments[i][1] + "'>"
//        }
        html += "</form>";

        document.write(html);
        document.hrefForm.submit();

    }

    /**
     * 校验对象是否为空
     * @param obj
     * @returns {boolean}
     */
    function isEmpty(obj) {
        if (obj == undefined || obj == null || obj == "") {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 校验对象是否为空
     * @param obj
     * @returns {boolean}
     */
    function isNotEmpty(obj) {
        if (obj == undefined || obj == null || obj == "") {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 校验对象是否为空 ,如果为undefined返回空字符串
     * @param obj
     * @returns {boolean}
     */
    function emptyVal(obj) {
        if (obj == undefined || obj == null || obj == "") {
            return "";
        } else {
            return obj;
        }
    }

    /**
     * IP地址合法性校验
     * @param ipAddr
     * @returns {boolean}
     */
    function ipValidate(ipAddr) {
        var patten = /^([1-9]|[1-9]\d{1}|1\d\d|2[0-4]\d|25[0-5])\.(([0-9]|[1-9]\d{1}|1\d\d|2[0-4]\d|25[0-5])\.){2}([1-9]|[1-9]\d{1}|1\d\d|2[0-4]\d|25[0-5])$/;
        if (patten.test(ipAddr)) {
            return true;
        }
        return false;
    }
    
    /**
     * 域名校验
     * @param domain
     * @returns {boolean}
     */
    function checkDomain(domain) {
        var patrn = /^[0-9a-zA-Z]+[0-9a-zA-Z\.-]*\.[a-zA-Z]{2,4}$/;
        if (patrn.test(domain)) return true;
        return false;
    }
    /**
     * 分页图标显示
     * @param {Object} table
     * @memberOf {TypeName} 
     */
    function updatePagerIcons(table) {
	    var replacement =
	    {
	        'ui-icon-seek-first': 'icon-double-angle-left bigger-140',
	        'ui-icon-seek-prev': 'icon-angle-left bigger-140',
	        'ui-icon-seek-next': 'icon-angle-right bigger-140',
	        'ui-icon-seek-end': 'icon-double-angle-right bigger-140'
	    };
	    $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function () {
	        var icon = $(this);
	        var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
	
	        if ($class in replacement) icon.attr('class', 'ui-icon ' + replacement[$class]);
	    })
	}


    /**
     * 树形下拉控件 支持单选及多选
     * @param inputSelector  页面组件input组件的ID
     * @param multi   是否是多选，如果true为多选，false单选
     * @param option  配置参数
     * @param callFunc 回调函数
     */
    /**
     * 树形下拉控件 支持单选及多选
     * @param inputSelector  页面组件input组件的ID
     * @param multi   是否是多选，如果true为多选，false单选
     * @param option  配置参数
     * @param callFunc 回调函数
     */
    function ztreeComp(inputSelector,multi,option,callFunc){
        var obj = $("#"+inputSelector);
        var treeId = inputSelector+"_tree";
        var divId = inputSelector+"_div";
        var t = $("<div id=\""+divId+"\"  style=\"display:none; position: absolute;z-index:9999;\"><ul id=\""+treeId+"\" class=\"ztree\" style=\"margin-top:0; width:"+(obj.innerWidth()+100)+"px; height: 400px;\"></ul></div>");
        $('body').after(t);
        var settingForMulti = {
            beforeClick: beforeClick,
            onCheck: onCheck,
            onClick: onClickForMulti
        };
        var settingForSingle = {
            onClick: onClickForSingle
        };
        var checkForMulti = {
            enable: true,
            chkboxType:{"Y":"s", "N":"s"}
        }
        var checkForSingle = {
            enable: false,
            radioType: "all"
        }
        var setting = {
            check: multi?checkForMulti:checkForSingle,
            async: {
                enable: true,
                url:option.data.url,
                autoParam:option.data.param,
                cache:false
            },
            view: {
                view: {
                    showLine: false,
                    selectedMulti: multi
                }
            },
            data: {
                simpleData: {
                    enable:true,
                    idKey: option.dataStructure.idKey,
                    pIdKey: option.dataStructure.pIdKey,
                    rootPId: option.dataStructure.rootPId
                },
                key:{
                    name:option.dataStructure.name
                }
            },
            callback: multi?settingForMulti:settingForSingle
        };

        //单选下回调函数，比如要回显我们指定的字段填充到其他字段中。
        function onClickForSingle(event, treeId, treeNode){
            if (!treeNode.isParent) {
                callFunc(treeNode);
                hideMenu();
            }
        }
        function onClickForMulti(treeNode) {
            zTree.expandNode(treeNode, true, true, true);
        }
        //解决多选下选中后再次选择取消选中
        function beforeClick(treeNode) {
            zTree.checkNode(treeNode, !treeNode.checked, null, true);
            return false;
        }
        //多选下回调函数，比如要回显我们指定的字段填充到其他字段中。
        function onCheck() {
            callFunc(zTree);
        }

        initTree(treeId,setting);
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        $("#"+inputSelector).click(function(){
            showMenu();
        })
        function showMenu() {
            var obj = $("#"+inputSelector);
            var objOffset = $("#"+inputSelector).offset();
            $("#"+divId).css({left:objOffset.left + "px", top:(objOffset.top) -400 + "px"}).slideDown("fast");
            $("body").bind("mousedown", onBodyDown);
        }
        function hideMenu() {
            $("#"+divId).fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == inputSelector || event.target.id == divId || $(event.target).parents("#"+divId).length>0)) {
                hideMenu();
            }
        }

        function initTree(){
            $.fn.zTree.init($("#"+treeId), setting,option.rootNode);
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var nodes = treeObj.getNodes();
            if (nodes.length>0) {
               treeObj.reAsyncChildNodes(nodes[0], "refresh");
            }
        };
    }
    return{
        hrefPost: hrefPost,
        hrefPostMult:hrefPostMult,
        isEmpty: isEmpty,
        isNotEmpty: isNotEmpty,
        ipValidate: ipValidate,
        checkDomain: checkDomain,
        emptyVal: emptyVal,
        ztreeComp:ztreeComp,
        updatePagerIcons:updatePagerIcons,
        reset : reset,
        disableAll:disableAll
    }
}())

//电话验证
function isPhone(str){
	return new RegExp("^0?(130|131|132|133|134|135|136|137|138|139|145|147|150|151|152|153|155|156|157|158|159|170|177|180|181|182|183|185|186|187|188|189)[0-9]{8}$").test(str);
}
//序列化对象
$.fn.serializeObject = function () {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function () {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "h+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    }

    if(/(y+)/.test(format))
    {
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }

    for(var k in o)
    {
        if(new RegExp("("+ k +")").test(format))
        {
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
}

//yyyy-MM-dd hh:mm:ss
function timestampToDate(time,format){
    return new Date(time).format(format)
}

function GetDateStr(AddDayCount)
{
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = (dd.getMonth()+1)<10?"0"+(dd.getMonth()+1):(dd.getMonth()+1);//获取当前月份的日期，不足10补0
    var d = dd.getDate()<10?"0"+dd.getDate():dd.getDate(); //获取当前几号，不足10补0
    return y+"-"+m+"-"+d;
}


function fmoney(s, n, step)
{
    n = n > 0 && n <= 20 ? n : 2;
    s = parseFloat((s + "").replace(/[^\d\.-]/g, "")).toFixed(n) + "";
    if(!step)
       return s;
    var l = s.split(".")[0].split("").reverse(),
        r = s.split(".")[1];
    var t = "";
    for(var i = 0; i < l.length; i ++ )
    {
        t += l[i] + ((i + 1) % step == 0 && (i + 1) != l.length ? "," : "");
    }
    return t.split("").reverse().join("") + "." + r;
}

//弹出框;  
function alterInfo(title,msg){
    layer.alert(msg,{icon: 0,title:title});
}   
//错误弹出框;  
function errorInfo(title,msg){   
	jQuery.messager.alert(title,msg,'error');   
}   
//信息弹出框;  
function info(title,msg){
    layer.alert(msg,{icon: 0,title:title});
}   
//警告弹出框;  
function warning(title,msg){   
	jQuery.messager.alert(title,msg,'warning');   
}   

//右下角弹出框;  
function slide(title,msg){   
	jQuery.messager.show({   
	title:title,   
	msg:msg,   
	timeout:5000,   
	showType:'slide'  
});  
	
}

//信息弹出框;
function infoCall(title,msg,fn){
    layer.alert(msg,{icon: 1,title:title},fn);
}

//信息弹出框;
function layerConfirm(msg,fn){
    layer.confirm(msg, {icon: 3, title:'提示'},fn);
}
//信息弹出框;
function infoSuccess(title,msg){
    layer.alert(msg,{icon:1,title:title});
}
