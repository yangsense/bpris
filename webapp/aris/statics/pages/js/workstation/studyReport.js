//页面处理的一些逻辑：
//1.在审核报告之前需要先做报告的保存处理，不能直接进行审核。
//2.报告核查功能兼并了报告报告修改，报告状态修改，检查单状态修改，生成HTML文件上传服务器功能。
//3.打印报告功能，报告状态为已审核时才可打印。留痕保存，不留痕打印。上传服务器时，删掉<strike></strike>中的内容。
var titleshow = "";

//组织机构ID
var orgId = "";
$(function () {
    self.setInterval("preSave()", 60000);
    //组织机构ID
    orgId = $("#orgId", parent.document).val();
    //初始化模板树
    initOrgTree('public');
    hs(1);
    //校验+提交
    $("#saveBtn").click(function () {
        save();
    });
    //上次编辑内容
    $("#pre_edit").click(function () {
        preEdit();
    });
    //校验+提交
    $("#saveBtn1").click(function () {
        save();
    });
    //审核事件
    $("#checkBtn").click(function () {
        check();
    });
    //审核事件
    $("#checkBtn1").click(function () {
        check();
    });
    //二次审核
    $("#finalCheckBtn").click(function () {
        finalCheck();
    });
    //会诊资料浏览pdf下载
    $("#infoView").click(function () {
    	infoView();
    });

    //打印预览
    $("#printBtn").click(function () {
        print();
    });

    $("#printBtn1").click(function () {
        print();
    });
    //直接打印
    $("#directPrint").click(function () {
        directPrint();
    });
    $("#directPrint1").click(function () {
        directPrint();
    });
    //挂起
    $("#reportStandUp").click(function () {
        reportStandUp();
    });
    //报告对比
    $("#reportContrast").click(function () {
        reportContrast();
    });

    //调阅病人申请
    $("#viewAppBtn").click(function () {
        viewApp();
    });
    //调阅病人申请
    $("#viewAppBtn1").click(function () {
        viewApp();
    });

    //调阅影像
    $("#pacsViewBtn").click(function () {
        pacsView();
    });
    //调阅影像
    $("#pacsViewBtn1").click(function () {
        pacsView();
    });
    //所有影像
    $("#pacsViewAll").click(function () {
    	pacsViewAll();
    });

    $("#getHealthBtn").click(function () {
        toHealth();
    });

    $("#getHealthBtn1").click(function () {
        toHealth();
    });

    //调阅影像
    $("#consultStartBtn").click(function () {
        hs(8);
        $("#consultStartBtn").hide();
        consultStart();
    });

    //调阅影像
    $("#consultStartBtn1").click(function () {
        hs(8);
        $("#consultStartBtn1").hide();
        consultStart();
    });

    //从审核报告进来后，隐藏保存按钮
//	if($("#rtype").val()==2){
//		$("#saveBtn").hide();
//	}

    //公有模板维护
    $("#templateBtn").click(function () {
        openTemplateInit();
    });

    //病例收藏维护
    $("#collectionCaseBtn").click(function () {
        openCollectionCaseInit();
    });


    //公有模板维护
    $("#templateBtn1").click(function () {
        openTemplateInit();
    });

    //私有模板维护
    $("#private_templateBtn").click(function () {
        openTemplateInit();
    });
    //病例修改
    $("#bingliUpdate").click(function () {
    	bingliUpdateInit();
    });
    //文件上传
    $("#fileUpload").click(function () {
        fileUpdate();
    });
    $("#private_templateBtn1").click(function () {
        openTemplateInit();
    });
    //公有模板导入
    $("#templateBtnImport").click(function () {
        templateBtnImport('public');
    });
    $("#templateBtnImport1").click(function () {
        templateBtnImport('public');
    });

    //私有模板导入
    $("#private_templateBtnImport").click(function () {
        templateBtnImport('private');
    });
    $("#private_templateBtnImport1").click(function () {
        templateBtnImport('private');
    });


    $("#imgprint_btn").click(function () {
        imgprint();
    });
    $("#imgprint_btn1").click(function () {
        imgprint();
    });
    
    //ai结果
    $("#aiBtn").click(function () {
        aiDataUpload();
    });
    
    //初始化
    initOrgInfo();
    //是否为只读模式
    isReadonly();

    //会诊报告处理
    isConsultRole();  //会诊单自己不能书写保存、审核报告

    $("select").select2();
    var editor1 = CKEDITOR.replace('editorExam');
    editor1.on('focus', function (event) {
        $("#fudong").css("display", "none");
        titleshow = "1";
    });
    var editor2 = CKEDITOR.replace('editorResult');
    editor2.on('focus', function (event) {
        $("#fudong").css("display", "none");
        titleshow = "2";
    });
    CKEDITOR.instances.editorExam.setData("");
    CKEDITOR.instances.editorResult.setData("");
    
    //影像加载
    //$("#imgrow").html();
    //检查所见和诊断意见回填
    setReportExamResult();
    
    if ($("#itemBodyInfo").val() != "") {
        $("#bodyitem").text($("#itemBodyInfo").val());
        $("#bodyitem1").text($("#itemBodyInfo").val());
    }
    //已审核
    if ($("#reportIscompleted").val() == "1") {
        $("#reportContrast").show();
        $("#saveBtn").hide();
        $("#saveBtn1").hide();
        $("#reportStandUp").hide();
        if ($("#AUDIT_REPORT_BUTTON").val() == "true") {
            if ($("#reportVerifydocId").val() == $("#careId").val()) {
            	
                if ($("#reportFinalDocId").val() == "") {
                	if($("#studyTypeMy").val()==1){
                    	$("#checkBtn").hide();
                    }else{
                    	$("#checkBtn").show();
                    }
                } else {
                    $("#checkBtn").hide();
                }
            } else {
                $("#checkBtn").hide();
            }
        }
        if ($("#FINAL_AUDIT_REPORT").val() == "true") {
            if ($("#reportVerifydocId").val() == $("#careId").val()) {
                $("#finalCheckBtn").hide();
                if($("#studyTypeMy").val()==1){
                	$("#checkBtn").hide();
                }else{
                	$("#checkBtn").show();
                }
            } else {
                $("#checkBtn").hide();
                $("#finalCheckBtn").show();
            }
        }
        $("#collectionCaseBtn").show();
        $("#bingliUpdate").show();
        //	$("#checkBtn").text("再次审核");
        //	$("#checkBtn1").text("再次审核");
    } else if ($("#reportIscompleted").val() == "0") {
        $("#reportContrast").hide();
        if ($("#reportDocId").val() == $("#careId").val()) {
            if ($("#AUDIT_REPORT_BUTTON").val() == "true") {
                $("#checkBtn").show();
            } else {
                $("#checkBtn").hide();
            }
            $("#reportStandUp").show();
            $("#saveBtn1").show();
            $("#saveBtn").show();
        } else {
            if ($("#AUDIT_REPORT_BUTTON").val() == "true") {
                $("#checkBtn").show();
            } else {
                $("#checkBtn").hide();
            }
            if ($("#studystatusCode").val() == "UnCompleted") {
                $("#saveBtn1").show();
                $("#saveBtn").show();
                $("#reportStandUp").show();
            } else {
                $("#saveBtn1").hide();
                $("#saveBtn").hide();
                $("#reportStandUp").hide();
            }
            $("#reportStandUp").hide();
        }
        $("#collectionCaseBtn").hide();
        $("#bingliUpdate").hide();
    } else {
        $("#reportContrast").hide();
        if ($("#reportDocId").val() == $("#careId").val()) {
            $("#saveBtn1").show();
            $("#saveBtn").show();
            if ($("#AUDIT_REPORT_BUTTON").val() == "true") {
                $("#checkBtn").show();
            } else {
                $("#checkBtn").hide();
            }
        } else {
            if ($("#AUDIT_REPORT_BUTTON").val() == "true") {
                $("#checkBtn").show();
            } else {
                $("#checkBtn").hide();
            }
            $("#saveBtn1").hide();
            $("#saveBtn").hide();
        }
        $("#collectionCaseBtn").hide();
        $("#bingliUpdate").hide();
    }

//	if($("#reportFinalDocId").val()!=null&&$("#reportFinalDocId").val()!=""){
//		$("#checkBtn").hide();
//	}else{ 
//		$("#checkBtn").show();
//	}
    
    if ($("#patienttypeCode").val() == "HP") {
        $("#tips").text("体检号:");
        $("#tips1").text("体检号:");
    } else {
        $("#tips").text("住院号:");
        $("#tips1").text("住院号:");
    }
    if ($("#ispositive").val() != "") {
        $('input[name="reportIspositive"][value="' + $("#ispositive").val() + '"]').attr("checked", 'checked');
    }
    
    if(!$("#DIAGNOSE_ROLE")&&$("#studystatusCode").val()=="VERIFY"){
    	$("#printBtn").hide();
    }
    
});

function aiDataUpload(){
	layer.confirm('请选择检查类型', {
        btn: ['肺结节','取消'], //可以无限个按钮
        icon: 3, title: '提示',
        btn1: function (index, layero) {
        	$.ajax({
                type: "POST",
//                url: GLOBAL.WEBROOT + "/aiDiagnosisService/aiDataUpload.ajax?studyaccNumber=CI013193&seriesNo=2&seriesUid=1.3.12.2.1107.5.1.4.80929.30000017091623532814000017469",//-------取serialNumber
                url: GLOBAL.WEBROOT + "/studyReport/aiDataResult.ajax?studyinfoId=" + $("#studyinfoId").val()+"&orgId="+parent.$("#currentOrgId").val(),
                success: function (data) {
                    if(data){
                    	layer.alert(data.resultMsg, {icon: 5, shadeClose: true});
                    }
                }
            });
            layer.close(index);
        }
	});
}

function setReportExamResult(){
	$.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT + "/studyReport/setReportExamResult.ajax?studyinfoId=" + $("#studyinfoId").val(),
        success: function (data) {
            if(data){
            	if(data.oldreportExam!=undefined){
            	    if (data.oldreportExam!=""){
            	        CKEDITOR.instances.editorExam.setData(data.oldreportExam);
            	    }
            	    if (data.oldreportResult != "") {
            	        CKEDITOR.instances.editorResult.setData(data.oldreportResult);
            	    }
            	}
            }
        }
    });
}

function reportContrast() {
    window.open(GLOBAL.WEBROOT + "/studyReport/reportContrast.html?studyInfoId=" + $("#studyinfoId").val(), 'newwindow', '');
}

function patientReg() {
    layer.open({
        type: 2,
        title: "病人信息修改",
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/studyReport/patientInfo.html?patientGlobalid=" + $("#patientGlobalid").val()
    });

    //var orgId = $("#orgId" , parent.parent.document).val();
    //window.location.href = GLOBAL.WEBROOT + "/patientReg/patientRegPageinit.html?studyinfoId="+$("#studyinfoId").val()+"&patientGlobalid="+$("#patientGlobalid").val()+"&orgId="+orgId+"&locId="+$("#locId").val()+"&diagnoseFlag=y" ;
}

//判断当前浏览类型  
function BrowserType() {
    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串  
    var isOpera = userAgent.indexOf("Opera") > -1; //判断是否Opera浏览器  
    var isIE = userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera; //判断是否IE浏览器  
    var isEdge = userAgent.indexOf("Windows NT 6.1; Trident/7.0;") > -1 && !isIE; //判断是否IE的Edge浏览器  
    var isFF = userAgent.indexOf("Firefox") > -1; //判断是否Firefox浏览器  
    var isSafari = userAgent.indexOf("Safari") > -1 && userAgent.indexOf("Chrome") == -1; //判断是否Safari浏览器  
    var isChrome = userAgent.indexOf("Chrome") > -1 && userAgent.indexOf("Safari") > -1; //判断Chrome浏览器  

    if (isIE) {
        var reIE = new RegExp("MSIE (\\d+\\.\\d+);");
        reIE.test(userAgent);
        var fIEVersion = parseFloat(RegExp["$1"]);
        if (fIEVersion == 7) {
            return "IE7";
        }
        else if (fIEVersion == 8) {
            return "IE8";
        }
        else if (fIEVersion == 9) {
            return "IE9";
        }
        else if (fIEVersion == 10) {
            return "IE10";
        }
        else if (fIEVersion == 11) {
            return "IE11";
        }
        else {
            return "0"
        }//IE版本过低
    }//isIE end

    if (isFF) {
        return "FF";
    }
    if (isOpera) {
        return "Opera";
    }
    if (isSafari) {
        return "Safari";
    }
    if (isChrome) {
        return "Chrome";
    }
    if (isEdge) {
        return "Edge";
    }
}

function PageSetup_Null() {
    try {
        var Wsh = new ActiveXObject("WScript.Shell");
        HKEY_Key = "header";
        Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "");
        HKEY_Key = "footer";
        Wsh.RegWrite(HKEY_Root + HKEY_Path + HKEY_Key, "");
    } catch (e) {
    }
}

function directPrint() {
    if ($("#reportIscompleted").val() == "0" || $("#auditStatus").val() || $("#reportIscompleted").val() == "1") {
        PageSetup_Null();
        //WebBrowser.ExecWB(7, 1);
        document.all.ShowForm.ExecWB(7, 1);
        //document.getElementsByName("ShowForm")[0].ExecWB(7, 1);
    } else {
        layer.alert('请保存后再打印！', {icon: 5, shadeClose: true});
    }

    //记录打印次数及打印状态
//	$.ajax({
//        type: "POST",
//        async: true,
//        url: GLOBAL.WEBROOT+"/studyReport/setReportPrintInfo.ajax?reportId="+$("#reportId").val(),
//        success: function (data) {
//             setTimeout( function() {window.close();}, 100);
//        }
//    });
}

function reportStandUp() {
    $("#reportStandUp").addClass("disabled");
    //报告编码
    var reportId = $("#reportId").val();
    //检查编码
    var studyinfoId = $("#studyinfoId").val();
    //阴阳性
    var reportIspositive = $('input[name="reportIspositive"]:checked ').val();

    if ($("#careId").val()!=$("#reportDocId").val()) {
    	layer.alert('报告已被挂起', {icon: 5, shadeClose: true});
        return false;
    }
    
    if ($("#reportIscompleted").val() == "1") {
        layer.alert('报告已审核，只能做审核操作', {icon: 5, shadeClose: true});
        return false;
    }

    var eEditor = CKEDITOR.instances.editorExam;
    if (eEditor.getData() == "") {
        layer.alert('请填写检查所见！', {icon: 5, shadeClose: true});
        $("#reportStandUp").removeClass("disabled");
        return false;
    }
    var rEditor = CKEDITOR.instances.editorResult;
    if (rEditor.getData() == "") {
        layer.alert('请填写诊断意见！', {icon: 5, shadeClose: true});
        $("#reportStandUp").removeClass("disabled");
        return false;
    }
    //提交
    var data = {
        "studyinfoId": studyinfoId,
        "reportIspositive": reportIspositive,
        "reportExam": eEditor.getData(),
        "reportResult": rEditor.getData(),
        "reportRemark": $("#reportRemark").val()
    };
    data = $.toJSON(data);
    //加载层
    var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2

    var imgArr = new Array;
    var i = 0;
    $("img[name='imgup']").each(function (i) {
        imgArr[i] = $(this).attr("src");
        i = i + 1;
    });

    var re = new RegExp("&", "g");
    var imgArrstr = "";
    if (imgArr.length > 0) {
        imgArrstr = imgArr.join(",").replace(re, "@@");
        layer.msg("正在上传影像,请稍后", {icon: 16});
    }
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/studyReport/saveUpReport.ajax?imgArr=" + imgArrstr + "&studyinfoId=" + $("#studyinfoId").val() + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#locId").val() + "&reportId=" + reportId+"&orgId="+parent.$("#currentOrgId").val(),
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
            //关掉loading
            layer.closeAll('loading');
            if (data.result == 'success') {
                $("#reportId").val(data.reportId);
                $("#iframepage").attr("src", "");
                $("#auditStatus").val(0);
                $("#studystatusCode").val(data.studystatusCode);
                $("#iframepage").attr("src", data.filePath);
                layer.msg('挂起成功!');
                if ($("#reportStandUp").hasClass("disabled")) {
                    $("#reportStandUp").removeClass("disabled");
                }
                $.ajax({
                    type: "POST",
                    async: false,
                    url: GLOBAL.WEBROOT + '/studyReport/unStudyLock.ajax?studyInfoId=' + $("#studyinfoId").val(),
                    dataType: 'json',
                    // data :setdata,
                    success: function (data) {
                        if (data.ERRCODE == '1') {
//                            return;
                        }
                    }
                });
            }
            else {
                layer.msg('保存失败!');
            }

        }
    });
}

function isConsultRole() {
    if ($("#CONSULT_ROLE").val()) {
        hs(7);
    }
}

function infoView(){
	var url = "/studyReport/medicalRecordView.html?studyinfoId="+$("#studyinfoId").val();
    var title = "资料浏览";
    var index = layer.open({
        type: 2,
        title: title,
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
}


function callback(msg) {
    if (msg == 'success') {
        layer.alert('模板上传成功！', {icon: 1, shadeClose: true}, function tempForward() {
            window.parent['view_frame'].location.reload();

        });
    } else {
        layer.alert('模板上传异常，请联系管理员！', {icon: 5, shadeClose: true});
    }


}

function templateBtnImport(type) {
    var url = "/studyReport/templateImport.html?type=" + type + "&locId=" + $("#locId").val() + "&orgId=" + orgId;
    var title = "公共模板导入";
    var index = layer.open({
        type: 2,
        title: title,
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
//    layer.full(index);
}

function isReadonly() {
    if ($("#isReadonly").val() == "y") {
        hs(5);
        $("#reportExam").attr('disabled', true);
        $("#reportResult").attr('disabled', true);
    }
}

//会诊开始
function consultStart() {
    //报告编码
    var reportId = $("#reportId").val();
    //检查编码
    var studyinfoId = $("#studyinfoId").val();
    var data = {"studyinfoId": studyinfoId, "reportId": reportId};
    //机构列表加载
    $.ajax({
        type: "POST",
        async: false,
        data: data,
        url: GLOBAL.WEBROOT + '/studyReport/consultStart.ajax',
        dataType: 'json',
        success: function (data) {
            layer.msg('开始会诊!');
        }
    });
}

function initOrgInfo() {
    //机构列表加载
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT + '/bpris/getOrgs.ajax?operatorCode=' + parent.user.operatorCode + "&sysType=25",
        dataType: 'json',
        success: function (data) {
            var sysOrg = eval(data);
            var orgSelect = $("#studyConsultorg");
            orgSelect.empty();
            orgSelect.append("<option value='-1'>请选择机构</option>");
            var vFlag = 0;
            for (var i = 0; i < sysOrg.length; i++) {
                if ($("#existingStudyConsultorg").val() == sysOrg[i].orgId) {
                    vFlag = 1;
                    setLocItem($("#existingStudyConsultorg"));
                    orgSelect.append("<option value='" + sysOrg[i].orgId + "' selected>" + sysOrg[i].orgName + "</option>");
                } else {
                    orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
                }
            }
        }
    });
}
//查会诊科室
function setLocItem(obj) {
    $("#studyConsultloc").select2("val", "");
    var orgId = $(obj).val();
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT + "/patientReg/getLocInfo.ajax?orgId=" + orgId + "&locType=C",
        success: function (data) {
            var locItemBeans = eval(data.locBeans);
            var statusSelect = $("#studyConsultloc");
            statusSelect.empty();
            statusSelect.append("<option value='-1'>请选择</option>");
            for (var i = 0; i < locItemBeans.length; i++) {
                if ($("#existingStudyConsultloc").val() == locItemBeans[i].locId) {
                    statusSelect.append("<option value='" + locItemBeans[i].locId + "' selected>" + locItemBeans[i].locDesc + "</option>");
                    $("#studyConsultloc").select2();
                } else {
                    statusSelect.append("<option value='" + locItemBeans[i].locId + "' >" + locItemBeans[i].locDesc + "</option>");

                }
            }
            if ($("#studyConsultloc").val() == "-1") {
                $("#studyConsultloc option:nth-child(2)").prop("selected", 'selected');
                $("#studyConsultloc").select2();
            }
        }
    });
}

//保存
function save() {
    //$("#saveBtn").addClass("disabled");
    //报告编码
    var reportId = $("#reportId").val();
    //检查编码
    var studyinfoId = $("#studyinfoId").val();
    //阴阳性
    var reportIspositive = $('input[name="reportIspositive"]:checked ').val();

    if ($("#reportIscompleted").val() == "1") {
        layer.alert('报告已审核，只能做审核操作', {icon: 5, shadeClose: true});
        return false;
    }

    if ($("#AUDIT_REPORT_BUTTON").val() != "true"&&$("#studystatusCode").val()=="UnCompleted"&&($("#careId").val()!=$("#reportDocId").val())) {
    	layer.alert('未完成的报告需有审核权限才可保存', {icon: 5, shadeClose: true});
        return false;
    }
    
    var eEditor = CKEDITOR.instances.editorExam;
    if (eEditor.getData() == "") {
        layer.alert('请填写检查所见！', {icon: 5, shadeClose: true});
        $("#saveBtn").removeClass("disabled");
        return false;
    }
    var rEditor = CKEDITOR.instances.editorResult;
    if (rEditor.getData() == "") {
        layer.alert('请填写诊断意见！', {icon: 5, shadeClose: true});
        $("#saveBtn").removeClass("disabled");
        return false;
    }
    //提交
    var data = {
        "studyinfoId": studyinfoId,
        "reportIspositive": reportIspositive,
        "reportExam": eEditor.getData(),
        "reportResult": rEditor.getData(),
        "reportRemark": $("#reportRemark").val()
    };
    data = $.toJSON(data);
    //加载层
    var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2

    var imgArr = new Array;
    var i = 0;
    $("img[name='imgup']").each(function (i) {
        imgArr[i] = $(this).attr("src");
        i = i + 1;
    });

    var re = new RegExp("&", "g");
    var imgArrstr = "";
    if (imgArr.length > 0) {
        imgArrstr = imgArr.join(",").replace(re, "@@");
        layer.msg("正在上传影像,请稍后", {icon: 16});
    }
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/studyReport/saveReport.ajax?imgArr=" + imgArrstr + "&studyinfoId=" + $("#studyinfoId").val() + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&reportId=" + reportId+"&orgId="+parent.$("#currentOrgId").val(),
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
            //关掉loading
            layer.closeAll('loading');
            if (data.result == 'success') {
                $("#reportId").val(data.reportId);
                $("#auditStatus").val(0);
                $("#studystatusCode").val(data.studystatusCode);
                $("#iframepage").attr("src", "");
                $("#iframepage").attr("src", data.filePath);
                layer.msg('保存成功!');
                if ($("#saveBtn").hasClass("disabled")) {
                    $("#saveBtn").removeClass("disabled");
                }
            }
            else {
                layer.msg(data.result);
            }

        }
    });
}


//保存
function preSave() {

    //检查编码
    var studyinfoId = $("#studyinfoId").val();
    var eEditor = CKEDITOR.instances.editorExam;
    var rEditor = CKEDITOR.instances.editorResult;
    //提交
    var data = {
        "studyinfoId": studyinfoId,
        "reportExam": eEditor.getData(),
        "reportResult": rEditor.getData(),
        "reportRemark": $("#reportRemark").val()
    };
    data = $.toJSON(data);
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/studyReport/preSaveReport.ajax",
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
            //if (data.result=='success') {
            //	layer.msg('自动保存成功!');
            //}
            //else {
            //	layer.msg('自动保存失败!');
            //}

        }
    });
}

//审核
function check() {
    $("#checkBtn").addClass("disabled");
    //当前科室
    var locId = $("#locId").val();
    //报告编码
    var reportId = $("#reportId").val();
    //检查编码
    var studyinfoId = $("#studyinfoId").val();
    //阴阳性
    var reportIspositive = $('input[name="reportIspositive"]:checked ').val();
    var myStudyType = $("#studyTypeMy").val();
    //检查所见
    var eEditor = CKEDITOR.instances.editorExam;
    if (eEditor.getData() == "") {
        layer.alert('请填写检查所见！', {icon: 5, shadeClose: true});
        $("#checkBtn").removeClass("disabled");
        return false;
    }
    //诊断意见
    var rEditor = CKEDITOR.instances.editorResult;
    if (rEditor.getData() == "") {
        layer.alert('请填写诊断意见！', {icon: 5, shadeClose: true});
        $("#checkBtn").removeClass("disabled");
        return false;
    }
    //先保存后审核
    if (reportId == "" || reportId == null) {
        layer.alert('请保存后再进行审核！', {icon: 5, shadeClose: true});
        $("#checkBtn").removeClass("disabled");
        return false;
    }
    if ($("#studystatusCode").val() == "UnCompleted") {
        layer.alert('请保存后再进行审核！', {icon: 5, shadeClose: true});
        $("#checkBtn").removeClass("disabled");
        return false;
    }
    //加载层
    var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
    //图像保存处理
    var imgAddrArray = "";
    $.each($("#imageView").find("a"), function (i, obj) {
        var cls = $.trim($(obj).attr("class"));
        //已选中时处理
        if (cls.indexOf("current") >= 0) {
            imgAddrArray += imgAddrArray == "" ? $(obj).find("img").attr("src") : "|" + $(obj).find("img").attr("src");
        }
    });

    var args = "阳性";
    if (reportIspositive == "1") {
        args = "阴性";
    } else if (reportIspositive == "2") {
        args = "未知";
    }

    if (reportIspositive != "0" && $("#ON_OFF").val() == "YES") {
        layer.confirm('请确认报告检查结果', {
            btn: ['阳性', '阴性', '未知', '取消'], //可以无限个按钮
            icon: 7, title: '提示',
            btn1: function (index, layero) {
                reportIspositive = "0";
                //提交
                var data = {
                    "reportId": reportId,
                    "studyinfoId": studyinfoId,
                    "reportIspositive": reportIspositive,
                    "reportExam": eEditor.getData(),
                    "reportResult": rEditor.getData(),
                    "reportRemark": $("#reportRemark").val()
                };
                data = $.toJSON(data);
                $.ajax({
                    type: "POST",
                    async: true,
                    url: GLOBAL.WEBROOT + "/studyReport/checkReport.ajax?locId=" + locId + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&imgAddrArray=" + imgAddrArray+"&myStudyType="+myStudyType+"&orgId="+parent.$("#currentOrgId").val()+"&consultRole="+$("#consultRole").val(),
                    dataType: 'json',
                    data: data,
                    contentType: 'application/json',
                    success: function (data) {
                        //关掉loading
                        layer.closeAll('loading');
                        if (data.success == "success") {
                            $("#reportIscompleted").val(1);
                            $("#iframepage").attr("src", "");
                            $("#iframepage").attr("src", data.filePath);
                            $("#reportContrast").show();
                            layer.msg('审核成功!');
                            $("#saveBtn").addClass("disabled");
                            $("#saveBtn").hide();
                            if($("#studyTypeMy").val()==1){
                            	$("#checkBtn").hide();
                            }
                            if ($("#checkBtn").hasClass("disabled")) {
                                $("#checkBtn").removeClass("disabled");
                            }
                            $("#collectionCaseBtn").show();
                            $("#bingliUpdate").show();
                        } else {
                            layer.msg(data.result);
                        }
                    }
                });
                layer.close(index);
            },
            btn2: function (index, layero) {
                reportIspositive = "1";
                //提交
                var data = {
                    "reportId": reportId,
                    "studyinfoId": studyinfoId,
                    "reportIspositive": reportIspositive,
                    "reportExam": eEditor.getData(),
                    "reportResult": rEditor.getData(),
                    "reportRemark": $("#reportRemark").val()
                };
                data = $.toJSON(data);
                $.ajax({
                    type: "POST",
                    async: true,
                    url: GLOBAL.WEBROOT + "/studyReport/checkReport.ajax?locId=" + locId + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&imgAddrArray=" + imgAddrArray+"&myStudyType="+myStudyType+"&orgId="+parent.$("#currentOrgId").val()+"&consultRole="+$("#consultRole").val(),
                    dataType: 'json',
                    data: data,
                    contentType: 'application/json',
                    success: function (data) {
                        //关掉loading
                        layer.closeAll('loading');
                        if (data.success == "success") {
                            $("#reportIscompleted").val(1);
                            $("#iframepage").attr("src", "");
                            $("#iframepage").attr("src", data.filePath);
                            $("#reportContrast").show();
                            layer.msg('审核成功!');
                            $("#saveBtn").addClass("disabled");
                            $("#saveBtn").hide();
                            if($("#studyTypeMy").val()==1){
                            	$("#checkBtn").hide();
                            }
                            if ($("#checkBtn").hasClass("disabled")) {
                                $("#checkBtn").removeClass("disabled");
                            }
                            $("#collectionCaseBtn").show();
                            $("#bingliUpdate").show();
                        } else {
                            layer.msg(data.result);
                        }
                    }
                });
                layer.close(index);
            },
            btn3: function (index, layero) {
                reportIspositive = "2";
                //提交
                var data = {
                    "reportId": reportId,
                    "studyinfoId": studyinfoId,
                    "reportIspositive": reportIspositive,
                    "reportExam": eEditor.getData(),
                    "reportResult": rEditor.getData(),
                    "reportRemark": $("#reportRemark").val()
                };
                data = $.toJSON(data);
                $.ajax({
                    type: "POST",
                    async: true,
                    url: GLOBAL.WEBROOT + "/studyReport/checkReport.ajax?locId=" + locId + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&imgAddrArray=" + imgAddrArray+"&myStudyType="+myStudyType+"&orgId="+parent.$("#currentOrgId").val()+"&consultRole="+$("#consultRole").val(),
                    dataType: 'json',
                    data: data,
                    contentType: 'application/json',
                    success: function (data) {
                        //关掉loading
                        layer.closeAll('loading');
                        if (data.success == "success") {
                            $("#reportIscompleted").val(1);
                            $("#iframepage").attr("src", "");
                            $("#iframepage").attr("src", data.filePath);
                            $("#reportContrast").show();
                            layer.msg('审核成功!');
                            $("#saveBtn").addClass("disabled");
                            $("#saveBtn").hide();
                            if($("#studyTypeMy").val()==1){
                            	$("#checkBtn").hide();
                            }
                            if ($("#checkBtn").hasClass("disabled")) {
                                $("#checkBtn").removeClass("disabled");
                            }
                            $("#collectionCaseBtn").show();
                            $("#bingliUpdate").show();
                        } else {
                            layer.msg(data.result);
                        }
                    }
                });
                layer.close(index);
            }, btn4: function (index) {
                layer.close(index);
                //提交
                var data = {
                    "reportId": reportId,
                    "studyinfoId": studyinfoId,
                    "reportIspositive": reportIspositive,
                    "reportExam": eEditor.getData(),
                    "reportResult": rEditor.getData(),
                    "reportRemark": $("#reportRemark").val()
                };
                data = $.toJSON(data);
                $.ajax({
                    type: "POST",
                    async: true,
                    url: GLOBAL.WEBROOT + "/studyReport/checkReport.ajax?locId=" + locId + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&imgAddrArray=" + imgAddrArray+"&myStudyType="+myStudyType+"&orgId="+parent.$("#currentOrgId").val()+"&consultRole="+$("#consultRole").val(),
                    dataType: 'json',
                    data: data,
                    contentType: 'application/json',
                    success: function (data) {
                        //关掉loading
                        layer.closeAll('loading');
                        if (data.success == "success") {
                            $("#reportIscompleted").val(1);
                            $("#iframepage").attr("src", "");
                            $("#iframepage").attr("src", data.filePath);
                            $("#reportContrast").show();
                            layer.msg('审核成功!');
                            $("#saveBtn").addClass("disabled");
                            $("#saveBtn").hide();
                            if($("#studyTypeMy").val()==1){
                            	$("#checkBtn").hide();
                            }
                            if ($("#checkBtn").hasClass("disabled")) {
                                $("#checkBtn").removeClass("disabled");
                            }
                            $("#collectionCaseBtn").show();
                            $("#bingliUpdate").show();
                        } else {
                            layer.msg(data.result);
                        }
                    }
                });
            }
        });
    } else {
        var data = {
            "reportId": reportId,
            "studyinfoId": studyinfoId,
            "reportIspositive": reportIspositive,
            "reportExam": eEditor.getData(),
            "reportResult": rEditor.getData(),
            "reportRemark": $("#reportRemark").val()
        };
        data = $.toJSON(data);
        $.ajax({
            type: "POST",
            async: true,
            url: GLOBAL.WEBROOT + "/studyReport/checkReport.ajax?locId=" + locId + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&imgAddrArray=" + imgAddrArray+"&myStudyType="+myStudyType+"&orgId="+parent.$("#currentOrgId").val()+"&consultRole="+$("#consultRole").val(),
            dataType: 'json',
            data: data,
            contentType: 'application/json',
            success: function (data) {
                //关掉loading
                layer.closeAll('loading');
                if (data.success == "success") {
                    $("#reportIscompleted").val(1);
                    $("#iframepage").attr("src", "");
                    $("#iframepage").attr("src", data.filePath);
                    $("#reportContrast").show();
                    layer.msg('审核成功!');
                    if($("#studyTypeMy").val()==1){
                    	$("#checkBtn").hide();
                    }
                    $("#saveBtn").addClass("disabled");
                    $("#saveBtn").hide();
                    if ($("#checkBtn").hasClass("disabled")) {
                        $("#checkBtn").removeClass("disabled");
                    }
                    $("#collectionCaseBtn").show();
                    $("#bingliUpdate").show();
                } else {
                    layer.msg(data.result);
                }
            }
        });
    }

}

//二次审核
function finalCheck() {
	if($("#studyTypeMy").val()==1){
		layer.alert('会诊报告已审核，不能修改！', {icon: 5, shadeClose: true});
        return false;
    }
	
    if ($("#reportIscompleted").val() == "1") {
        $("#finalCheckBtn").addClass("disabled");
        //当前科室
        var locId = $("#locId").val();
        //报告编码
        var reportId = $("#reportId").val();
        //检查编码
        var studyinfoId = $("#studyinfoId").val();
        //阴阳性
        var reportIspositive = $('input[name="reportIspositive"]:checked ').val();
        //检查所见
        var eEditor = CKEDITOR.instances.editorExam;
        if (eEditor.getData() == "") {
            layer.alert('请填写检查所见！', {icon: 5, shadeClose: true});
            return false;
        }
        //诊断意见
        var rEditor = CKEDITOR.instances.editorResult;
        if (rEditor.getData() == "") {
            layer.alert('请填写诊断意见！', {icon: 5, shadeClose: true});
            return false;
        }
        //先保存后审核
        if (reportId == "" || reportId == null) {
            layer.alert('请保存后再进行审核！', {icon: 5, shadeClose: true});
            return false;
        }
        //加载层
        var index = layer.load(0, {shade: false}); //0代表加载的风格，支持0-2
        //图像保存处理
        var imgAddrArray = "";
        $.each($("#imageView").find("a"), function (i, obj) {
            debugger;
            var cls = $.trim($(obj).attr("class"));
            //已选中时处理
            if (cls.indexOf("current") >= 0) {
                imgAddrArray += imgAddrArray == "" ? $(obj).find("img").attr("src") : "|" + $(obj).find("img").attr("src");
            }
        });

        var args = "阳性";
        if (reportIspositive == "1") {
            args = "阴性";
        } else if (reportIspositive == "2") {
            args = "未知";
        }

        if (reportIspositive != "0" && $("#ON_OFF").val() == "YES") {
            layer.confirm('请确认报告检查结果', {
                btn: ['阳性', '阴性', '未知', '取消'], //可以无限个按钮
                icon: 7, title: '提示',
                btn1: function (index, layero) {
                    reportIspositive = "0";
                    //提交
                    var data = {
                        "reportId": reportId,
                        "studyinfoId": studyinfoId,
                        "reportIspositive": reportIspositive,
                        "reportExam": eEditor.getData(),
                        "reportResult": rEditor.getData(),
                        "reportRemark": $("#reportRemark").val()
                    };
                    data = $.toJSON(data);
                    $.ajax({
                        type: "POST",
                        async: true,
                        url: GLOBAL.WEBROOT + "/studyReport/checkFinalReport.ajax?locId=" + locId + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&imgAddrArray=" + imgAddrArray+"&orgId="+parent.$("#currentOrgId").val(),
                        dataType: 'json',
                        data: data,
                        contentType: 'application/json',
                        success: function (data) {
                            //关掉loading
                            layer.closeAll('loading');
                            if (data.success == "success") {
                                //回填终审医生
                                $("#reportFinalDocId").val(data.finalDoc);
                                //隐藏审核按钮
                                $("#checkBtn").attr("disabled", true);
                                $("#checkBtn").hide();
                                $("#reportIscompleted").val(1);
                                $("#iframepage").attr("src", "");
                                $("#iframepage").attr("src", data.filePath);
                                layer.msg('二次审核成功!');
                                if ($("#finalCheckBtn").hasClass("disabled")) {
                                    $("#finalCheckBtn").removeClass("disabled");
                                }
                            } else {
                                layer.msg(data.result);
                            }
                        }
                    });
                    layer.close(index);
                },
                btn2: function (index, layero) {
                    reportIspositive = "1";
                    //提交
                    var data = {
                        "reportId": reportId,
                        "studyinfoId": studyinfoId,
                        "reportIspositive": reportIspositive,
                        "reportExam": eEditor.getData(),
                        "reportResult": rEditor.getData(),
                        "reportRemark": $("#reportRemark").val()
                    };
                    data = $.toJSON(data);
                    $.ajax({
                        type: "POST",
                        async: true,
                        url: GLOBAL.WEBROOT + "/studyReport/checkFinalReport.ajax?locId=" + locId + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&imgAddrArray=" + imgAddrArray+"&orgId="+parent.$("#currentOrgId").val(),
                        dataType: 'json',
                        data: data,
                        contentType: 'application/json',
                        success: function (data) {
                            //关掉loading
                            layer.closeAll('loading');
                            if (data.success == "success") {
                                //回填终审医生
                                $("#reportFinalDocId").val(data.finalDoc);
                                //隐藏审核按钮
                                $("#checkBtn").attr("disabled", true);
                                $("#checkBtn").hide();
                                $("#reportIscompleted").val(1);
                                $("#iframepage").attr("src", "");
                                $("#iframepage").attr("src", data.filePath);
                                layer.msg('二次审核成功!');
                                if ($("#finalCheckBtn").hasClass("disabled")) {
                                    $("#finalCheckBtn").removeClass("disabled");
                                }
                            } else {
                                layer.msg(data.result);
                            }
                        }
                    });
                    layer.close(index);
                },
                btn3: function (index, layero) {
                    reportIspositive = "2";
                    //提交
                    var data = {
                        "reportId": reportId,
                        "studyinfoId": studyinfoId,
                        "reportIspositive": reportIspositive,
                        "reportExam": eEditor.getData(),
                        "reportResult": rEditor.getData(),
                        "reportRemark": $("#reportRemark").val()
                    };
                    data = $.toJSON(data);
                    $.ajax({
                        type: "POST",
                        async: true,
                        url: GLOBAL.WEBROOT + "/studyReport/checkFinalReport.ajax?locId=" + locId + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&imgAddrArray=" + imgAddrArray+"&orgId="+parent.$("#currentOrgId").val(),
                        dataType: 'json',
                        data: data,
                        contentType: 'application/json',
                        success: function (data) {
                            //关掉loading
                            layer.closeAll('loading');
                            if (data.success == "success") {
                                //回填终审医生
                                $("#reportFinalDocId").val(data.finalDoc);
                                //隐藏审核按钮
                                $("#checkBtn").attr("disabled", true);
                                $("#checkBtn").hide();
                                $("#reportIscompleted").val(1);
                                $("#iframepage").attr("src", "");
                                $("#iframepage").attr("src", data.filePath);
                                layer.msg('二次审核成功!');
                                if ($("#finalCheckBtn").hasClass("disabled")) {
                                    $("#finalCheckBtn").removeClass("disabled");
                                }
                            } else {
                                layer.msg(data.result);
                            }
                        }
                    });
                    layer.close(index);
                }, btn4: function (index) {
                    layer.close(index);
                    //提交
                    var data = {
                        "reportId": reportId,
                        "studyinfoId": studyinfoId,
                        "reportIspositive": reportIspositive,
                        "reportExam": eEditor.getData(),
                        "reportResult": rEditor.getData(),
                        "reportRemark": $("#reportRemark").val()
                    };
                    data = $.toJSON(data);
                    $.ajax({
                        type: "POST",
                        async: true,
                        url: GLOBAL.WEBROOT + "/studyReport/checkFinalReport.ajax?locId=" + locId + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&imgAddrArray=" + imgAddrArray+"&orgId="+parent.$("#currentOrgId").val(),
                        dataType: 'json',
                        data: data,
                        contentType: 'application/json',
                        success: function (data) {
                            //关掉loading
                            layer.closeAll('loading');
                            if (data.success == "success") {
                                //回填终审医生
                                $("#reportFinalDocId").val(data.finalDoc);
                                //隐藏审核按钮
                                $("#checkBtn").attr("disabled", true);
                                $("#checkBtn").hide();
                                $("#reportIscompleted").val(1);
                                $("#iframepage").attr("src", "");
                                $("#iframepage").attr("src", data.filePath);
                                layer.msg('二次审核成功!');
                                if ($("#finalCheckBtn").hasClass("disabled")) {
                                    $("#finalCheckBtn").removeClass("disabled");
                                }
                            } else {
                                layer.msg(data.result);
                            }
                        }
                    });
                }
            });
        } else {
            //提交
            var data = {
                "reportId": reportId,
                "studyinfoId": studyinfoId,
                "reportIspositive": reportIspositive,
                "reportExam": eEditor.getData(),
                "reportResult": rEditor.getData(),
                "reportRemark": $("#reportRemark").val()
            };
            data = $.toJSON(data);
            $.ajax({
                type: "POST",
                async: true,
                url: GLOBAL.WEBROOT + "/studyReport/checkFinalReport.ajax?locId=" + locId + "&studyConsultorg=" + $("#studyConsultorg").val() + "&studyConsultloc=" + $("#studyConsultloc").val() + "&imgAddrArray=" + imgAddrArray+"&orgId="+parent.$("#currentOrgId").val(),
                dataType: 'json',
                data: data,
                contentType: 'application/json',
                success: function (data) {
                    //关掉loading
                    layer.closeAll('loading');
                    if (data.success == "success") {
                        //回填终审医生
                        $("#reportFinalDocId").val(data.finalDoc);
                        //隐藏审核按钮
                        $("#checkBtn").attr("disabled", true);
                        $("#checkBtn").hide();
                        $("#reportIscompleted").val(1);
                        $("#iframepage").attr("src", "");
                        $("#iframepage").attr("src", data.filePath);
                        layer.msg('二次审核成功!');
                        if ($("#finalCheckBtn").hasClass("disabled")) {
                            $("#finalCheckBtn").removeClass("disabled");
                        }
                    } else {
                        layer.msg(data.result);
                    }
                }
            });
        }
    } else {
        layer.alert('请先审核后才能二次审核！', {icon: 5, shadeClose: true});
    }
}

//打印
function print() {
    //未审核不可以打印
    //保存后临时打印----12.1修改
    if ($("#reportIscompleted").val() == "0" || $("#auditStatus").val() || $("#reportIscompleted").val() == "1") {
        var w = screen.availWidth;
        var h = screen.availHeight;
        window.open(GLOBAL.WEBROOT + "/studyReport/reportPrint.html?reportId=" + $("#reportId").val() + "&finalDocId=" + $("#reportFinalDocId").val(), 'newwindow', 'width=' + w + ',height=' + h + ',top=0,left=0,status=no,scrollbars=yes')
    } else {
        layer.alert('请保存后再打印！', {icon: 5, shadeClose: true});
    }

}

function addHoverDom(treeId, treeNode) {
    if (!treeNode.isParent) {
        $("#fudong").css("display", "block");
        $("#fudong").css("left", event.clientX);
        $("#fudong").css("top", event.clientY - 50);
        $("#spansj").text(treeNode.templateExam);
        $("#spanyj").text(treeNode.templateResult);
    }
}

function removeHoverDom(treeId, treeNode) {
    $("#fudong").css("display", "none");
}

//菜单树加载
var orgTree;
function initOrgTree(flag) {
    var idno = "-1";
    var modalityId = $("#modalityId").val();
    if (flag == "public") {
        idno = "-1";
    } else if (flag == "private") {
        idno = "-2";
        modalityId = "";
    }
    var setting = {
        view: {
            showLine: true,
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        data: {
            keep: {
                leaf: false,
                parent: false
            },
            key: {
                checked: "checked",
                children: "children",
                name: "name"
            },
            simpleData: {
                enable: false,
                idKey: "id",
                pIdKey: "pid"
            }
        },
        async: {
            enable: true,
            url: GLOBAL.WEBROOT + "/studyReport/initTree.ajax?orgId=" + orgId + "&flag=" + flag + "&locId=" + $("#locId").val() + "&modalityId=" + modalityId,
            autoParam: ["id"]
        },
        callback: {
            onDblClick: function (evt, treeId, treeNode) {
                onClickTreeNode(treeNode);
                $("#fudong").css("display", "none");
            },
            beforeMouseUp: function (evt, treeId, treeNode) {
                $("#fudong").css("display", "none");
            }
        }
    };

    orgTree = $.fn.zTree.init($("#templateTree"), setting, [{
        id: idno,
        name: '模板',
        open: true,
        isParent: true,
        nocheck: true
    }]);
    var treeObj = $.fn.zTree.getZTreeObj("templateTree");
    var nodes = treeObj.getNodes();
    if (nodes.length > 0) {
        treeObj.reAsyncChildNodes(nodes[0], "refresh");
    }
}

/** 点击树节点*/
function onClickTreeNode(treeNode) {
    orgTree.expandNode(treeNode, true);
    //$("#parentOrgId").val(treeNode.id);
    //$("#orgName").val("");
    getTempInfo(treeNode); //查模板内容信息
}

//查模板信息
var i = 0;
var arr = new Array;
function getTempInfo(node) {
    if (!node.isParent) {
        $.ajax({
            type: "POST",
            async: true,
            url: GLOBAL.WEBROOT + "/studyReport/getTempInfo.ajax?templatedirId=" + node.id,
            success: function (data) {
                var temp = eval(data);
                $("#templatecontentId").val(temp.contentId);

                //实例对象并赋值

//	            var eEditor = CKEDITOR.instances.reportExam;
//	            eEditor.setData(temp.templateExam);
//	            //取值案例
//	            //if(oEditor.getData()==""){alert("请填写您的回答内容!");return false;}
//	            
//	            var rEditor = CKEDITOR.instances.reportResult;
//	            rEditor.setData(temp.templateResult);
                //追加
                i++;
                if ($("input[name='templateRadio']:checked").val() == '0') {
                    arr[i] = temp.ispositive;
                    var str = arr.join(",");
                    var ccc = str.split(",");
                    for (var aa = 0; aa < ccc.length; aa++) {
                        if (ccc[aa] == "0") {
                            $('input[name="reportIspositive"][value="0"]').attr("checked", 'checked');
                            break;
                        } else {
                            $('input[name="reportIspositive"][value="1"]').attr("checked", 'checked');
                        }
                    }

//	            	if(arr[i]=="0"){
//	            		$('input[name="reportIspositive"][value="0"]').attr("checked",'checked');
//	            	}else{
//	            		$('input[name="reportIspositive"][value="1"]').attr("checked",'checked');
//	            	}
                    CKEDITOR.instances.editorExam.setData(CKEDITOR.instances.editorExam.getData() + temp.templateExam);
                    CKEDITOR.instances.editorResult.setData(CKEDITOR.instances.editorResult.getData() + temp.templateResult);
                } else {
                    $('input[name="reportIspositive"][value="' + temp.ispositive + '"]').attr("checked", 'checked');
                    CKEDITOR.instances.editorExam.setData(temp.templateExam);
                    CKEDITOR.instances.editorResult.setData(temp.templateResult);
                }


            }
        });
    }
}

//变动标签项
function changeLabel(obj, flag) {
    $.each($("li[name=tag]"), function (i, obj) {
        $(obj).removeClass("active");
    });
    $(obj).attr("class", "active");
    if (flag == 1) {
        //公共模板
        initOrgTree('public');
        hs(1);
    } else if (flag == 2) {
        //私有模板
        initOrgTree('private');
        hs(2);
    } else if (flag == 3) {
        //短语模板
        hs(3);
        //加载短语
        getPhrase();
    } else if (flag == 4) {
        //相关检查
        hs(4);
        //加载相关检查
        getRelCheck();
    } else if (flag == 6) {
        //影像浏览
        hs(6);
    } else if (flag == 7) {
        //病历夹
        initKnowledgeTree();
        hs(7);
    }
}
//hide-show
function hs(flag) {
    if (flag == 1) {
        $("#phrase").hide();
        $("#templateTree").show();
        $("#dinput").hide();
        $("#dbutton").hide();
        $("#relCheck").hide();
        $("#templateBtn").show();
        $("#templateBtn1").show();
        $("#templateBtnImport").show();
        $("#private_templateBtnImport").hide();
        $("#private_templateBtn").hide();
        $("#imageView").hide();
        $("#templateBtnImport1").show();
        $("#private_templateBtnImport1").hide();
        $("#private_templateBtn1").hide();
        $("#imageView1").hide();
    } else if (flag == 2) {
        $("#phrase").hide();
        $("#templateTree").show();
        $("#dinput").hide();
        $("#dbutton").hide();
        $("#relCheck").hide();
        $("#templateBtn").hide();
        $("#templateBtn1").hide();
        $("#templateBtnImport").hide();
        $("#private_templateBtn").show();
        $("#private_templateBtnImport").show();
        $("#imageView").hide();
        $("#templateBtnImport1").hide();
        $("#private_templateBtn1").show();
        $("#private_templateBtnImport1").show();
        $("#imageView1").hide();
    } else if (flag == 3) {
        $("#templateTree").hide();
        $("#phrase").show();
        $("#dinput").show();
        $("#dbutton").show();
        $("#relCheck").hide();
        $("#templateBtn").hide();
        $("#templateBtn1").hide();
        $("#templateBtnImport").hide();
        $("#private_templateBtn").hide();
        $("#private_templateBtnImport").hide();
        $("#imageView").hide();
        $("#templateBtnImport1").hide();
        $("#private_templateBtn1").hide();
        $("#private_templateBtnImport1").hide();
        $("#imageView1").hide();
    } else if (flag == 4) {
        $("#relCheck").show();
        $("#templateTree").hide();
        $("#phrase").hide();
        $("#dinput").hide();
        $("#dbutton").hide();
        $("#templateBtn").hide();
        $("#templateBtnImport").hide();
        $("#private_templateBtn").hide();
        $("#private_templateBtnImport").hide();
        $("#imageView").hide();
        $("#templateBtn1").hide();
        $("#templateBtnImport1").hide();
        $("#private_templateBtn1").hide();
        $("#private_templateBtnImport1").hide();
        $("#imageView1").hide();
    } else if (flag == 5) {
        $("#saveBtn1").hide();
        $("#saveBtn").hide();
        $("#checkBtn").hide();
        $("#checkBtn1").hide();
//		$("#pacsViewBtn").hide();
//		$("#printBtn").hide();
//		$("#viewAppBtn").hide();
        $("#templateBtn").hide();
        $("#templateBtnImport").hide();
        $("#private_templateBtn").hide();
        $("#private_templateBtnImport").hide();
        $("#templateBtn1").hide();
        $("#templateBtnImport1").hide();
        $("#private_templateBtn1").hide();
        $("#private_templateBtnImport1").hide();
    } else if (flag == 6) {
        $("#imageView").show();
        $("#relCheck").hide();
        $("#templateTree").hide();
        $("#phrase").hide();
        $("#dinput").hide();
        $("#dbutton").hide();
        $("#templateBtn").hide();
        $("#templateBtnImport").hide();
        $("#private_templateBtnImport").hide();
        $("#private_templateBtn").hide();
        $("#templateBtn1").hide();
        $("#templateBtnImport1").hide();
        $("#private_templateBtnImport1").hide();
        $("#private_templateBtn1").hide();
        
        var tbsource = "C:\\Windows\\AIPACS\\Forumpath\\"+$("#patientId").val()+"_"+$("#studyAccnumber").val()+"\\";//本地文件夹路径
        
        var hdfiles = "";
         
        var objFSO =new ActiveXObject('Scripting.FileSystemObject');
         
        if(!objFSO.FolderExists(tbsource))
        {
        alert("<"+tbsource+">该文件夹路径不存在，或者路径不能含文件名！");
        objFSO = null;
        }
         
        var objFolder = objFSO.GetFolder(tbsource);
         
        var colFiles = new Enumerator(objFolder.Files);
         
         
        var re_inf1 =/\.(gif|jpg|jpeg|bmp)$/i;
         
        for (; !colFiles.atEnd(); colFiles.moveNext()) //读取文件夹下文件
        {
            var objFile = colFiles.item();
             
            if(re_inf1.test(objFile.Name.toLowerCase()))
            {
            hdfiles = hdfiles+"<div class='col-xs-6 col-md-3 imgBorder'  style='width:25%;margin-top:10px;' ><a href='javaScript:void(0)' class='preview thumbnail' onclick='chooseImag(this);'><img alt='gallery thumbnail' name='imgup' src='C:/Windows/AIPACS/Forumpath/00000673_A0027648/"+objFile.Name+"'></a></div>";
            }
         
        }
         
        document.getElementById("imgrow").innerHTML=hdfiles;
        
        
    } else if (flag == 7) {
    	$("#imageView").hide();
        $("#relCheck").hide();
        $("#templateTree").show();
        $("#phrase").hide();
        $("#dinput").hide();
        $("#dbutton").hide();
        $("#templateBtn").hide();
        $("#templateBtnImport").hide();
        $("#private_templateBtnImport").hide();
        $("#private_templateBtn").hide();
        $("#templateBtn1").hide();
        $("#templateBtnImport1").hide();
        $("#private_templateBtnImport1").hide();
        $("#private_templateBtn1").hide();
    } else if (flag == 8) {
        $("#saveBtn").show();
        $("#checkBtn").show();
        $("#pacsViewBtn").show();
        $("#pacsViewAll").show();
//        $("#printBtn").show();
//		$("#viewAppBtn").show();   
    }

}
//取短语
function getPhrase() {
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT + "/studyReport/getPhrase.ajax",
        success: function (data) {
            var phraseBeans = eval(data.phraseBeans);
            $("#phrase").html("");
            var html = "<ul>";
            for (var i = 0; i < phraseBeans.length; i++) {
                html += "<li style='width:180%;' name='pli' id=" + phraseBeans[i].phraseId + " onclick='choosePhrase(this)'>" + phraseBeans[i].phraseContent + "</li>";
            }
            html += "</ul>";
            $("#phrase").html(html);
        }
    });
}
//添加短语
function upPhrase(opflag) {
    var pInfo = $("#dinput").val();
    var currentP = $("li[class=current]");
    var pId = currentP.attr("id");
    if (opflag == "add" && pInfo == "") {
        layer.msg('请输入短语信息!');
        return false;
    }
    if (opflag == "update") {
        if (pId == "undefined" || pId == null) {
            layer.msg('请选择要修改的短语!');
            return false;
        }
        if (pInfo == "") {
            layer.msg('请输入短语信息!');
            return false;
        }
    }
    if (opflag == "del") {
        if (pId == "undefined" || pId == null) {
            layer.msg('请选择要删除的短语!');
            return false;
        }
    }
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT + "/studyReport/upPhrase.ajax?pInfo=" + encodeURI(encodeURI(pInfo)) + "&opflag=" + opflag + "&pId=" + pId,
        success: function (data) {
            getPhrase();
        }
    });
    $("#dinput").val("");
}
//短语选择
function choosePhrase(obj) {
    //选中自己，剔除其它选中
    $.each($("li[name=pli]"), function (i, obj) {
        $(obj).removeClass("current");
    });
    $(obj).attr("class", "current");
    //添加到检查所见

    //实例对象并赋值

    if (titleshow == "1" || titleshow == "") {
        var eEditor = CKEDITOR.instances.editorExam;
        var str = "";
        var eData = eEditor.getData() + $(obj).html();
        eEditor.setData(eData.replace(/<.*?>/ig, ""));//剔除文本中所有的标签
    } else if (titleshow == "2") {
        var rEditor = CKEDITOR.instances.editorResult;
        var rData = rEditor.getData() + $(obj).html();
        rEditor.setData(rData.replace(/<.*?>/ig, ""));//剔除文本中所有的标签
    }

    $("#dinput").val($(obj).html());
}

//加载相关检查--查已审核报告
function getRelCheck() {
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT + "/studyReport/getRelCheck.ajax?patientGlobalid=" + $("#patientGlobalid").val() + "&studyinfoId=" + $("#studyinfoId").val()+"&patientInpatientId="+$("#patientInpatientId").val()+"&patientIdnumber="+$("#patientIdnumber").val(),
        success: function (data) {
            var regInfoBeans = eval(data.regInfoBeans);
            $("#checkTr").siblings().remove();
            var html = "";
            for (var i = 0; i < regInfoBeans.length; i++) {
                html += "<tr onclick='chooseRelCheck(this," + regInfoBeans[i].studyinfoId + ")'><td>" + regInfoBeans[i].studyAccnumber + "</td><td>" + regInfoBeans[i].studydesc + "</td><td>" + regInfoBeans[i].studyTime + "</td><td>" + regInfoBeans[i].orgName + "</td></tr>";
            }
            $("#checkTr").after(html);

        }
    });
}
//选择关联检查
function chooseRelCheck(obj, studyinfoId) {
    //选中自己，剔除其它选中
    $.each($("#checkTb tr"), function (i, obj) {
        $(obj).removeClass("current");
    });
    $(obj).attr("class", "current");

    layer.open({
        type: 2,
        title: "病人登记记录",
        area: ['800px', '500px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/studyReport/relReportView.html?studyinfoId=" + studyinfoId
    });

}

var nosss;
var num;
//模板维护页面
function openTemplateInit() {
    var zNodes;
    var type = $(".active").find("a").attr("flag");
    var url;
    var title;

    if (type == "1") {
        title = "公有模板维护";
        url = "/studyReport/template.html?type=" + type;
    } else if (type == "2") {
        title = "私有模板维护";
        url = "/studyReport/template.html?type=" + type;
    }
    var index = layer.open({
        type: 2,
        title: title,
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
    layer.full(index);
}

//病例修改页面
function bingliUpdateInit() {
    var index = layer.open({
        type: 2,
        title: '病例修改',
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/studyReport/bingliUpdate.html"
    });
    layer.full(index);
}
//文件上传
function fileUpdate() {
    var url = "/studyReport/reportFileUpdate.html?patientId="+$("#patientId").val()+"&patientName="+encodeURI(encodeURI($("#patientName").val()))+"&reportId="+$("#reportId").val();
    var title = "文件上传";
    var index = layer.open({
        type: 2,
        title: title,
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
}

// 病例收藏页面
function openCollectionCaseInit() {
    var zNodes;
    var url;
    var title;
    var reportId = $("#reportId").val();
    title = "收藏病例";
    url = "/studyReport/collectionCase.html?reportId=" + reportId;
    var index = layer.open({
        type: 2,
        title: title,
        area: ['600px', '300px'],
        fix: false, //不固定
        content: GLOBAL.WEBROOT + url
    });
}
//查看上次编辑内容
function preEdit() {
    var studyinfoId = $("#studyinfoId").val();
    var url;
    var title = "上次编辑内容";
    url = "/studyReport/preEdit.html?studyinfoId=" + studyinfoId;
    var index = layer.open({
        type: 2,
        title: title,
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url,
        btn: ['导入', '取消'],
        yes: function (index) {
        	console.log("点击按钮index" + index);
            var res = window["layui-layer-iframe" + index].saveFunc();
            console.log("res : " + res);
            if (res) { //如果获取到的对象非空,则执行下面语句
            	console.log("进入01 : ");
                $("#reportRemark").val(res.reportRemark);
                var exam = CKEDITOR.instances.editorExam;
                exam.setData(res.reportExam);
                var result = CKEDITOR.instances.editorResult;
                result.setData(res.reportResult);
                layer.close(index);
            }
            console.log("end01 : ");
        }


    });
    layer.full(index);

}

//返回到工作列表
function goBack() {

    //TODO 校验报告解锁
    var studyinfoId = $("#studyinfoId").val();
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT + '/studyReport/unStudyLock.ajax?studyInfoId=' + studyinfoId,
        dataType: 'json',
        // data :setdata,
        success: function (data) {
            if (data.ERRCODE == '1') {
                return;
            }
        }
    });

    window.location.href = GLOBAL.WEBROOT + '/workList/init.html';
    //左侧菜单收缩 --已隐藏不点击
    if (!$("#menuTree", parent.document).is(":hidden")) {
        parent.switchBarl();
    }
}


//调阅病人申请
function pacsView() {
    var patientid = $("#patientId").val();   //"06009580";
    var accessnumber = $("#studyAccnumber").val(); //"345959";
    var filespec = "C:\\Windows\\AIPACS\\AIPACS.Desktop.ImageViewerManager.exe";
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    if (fso.FileExists(filespec)) {
        var commandline = accessnumber + "&" + patientid + "&&&&I&&&";
        filespec = filespec + " " + commandline;
        //alert(filespec);

        var wsh = new ActiveXObject('WSCript.shell');
        wsh.run(filespec);

    }
    else {
        location.href = $("#pacsViewPath").val() + "/AIpacsView/setup.exe";
    }
}
//所有影像
function pacsViewAll(){
	var patientid = $("#patientId").val();   //"06009580";
	var patientids = $("#patientIds").val();   //"06009580";
    var filespec = "C:\\Windows\\AIPACS\\AIPACS.Desktop.ImageViewerManager.exe";
    var fso = new ActiveXObject("Scripting.FileSystemObject");
    if (fso.FileExists(filespec)) {
    	var commandline = "";
    	if(patientids!=""){
    		commandline = "&" + patientids + "&&&I&&&&";
    	}else{
    		commandline = "&" + patientid + "&&&&&&&";
    	}
        filespec = filespec + " " + commandline;
        //alert(filespec);

        var wsh = new ActiveXObject('WSCript.shell');
        wsh.run(filespec);

    }
    else {
        location.href = $("#pacsViewPath").val() + "/AIpacsView/setup.exe";
    }
}

//关闭
function closeDiv() {
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
var arr = new Array();
//影像图片选择
function chooseImag(obj) {
    var cls = $.trim($(obj).attr("class"));

    if (cls.indexOf("current") >= 0) {
        $(obj).removeClass("current");
        if ($(obj).attr("class") == "preview thumbnail") {
            for (var i = 0; i < arr.length; i++) {
                if (arr[i] == $(obj).find("img").attr("src")) {
                    arr.remove(arr[i]);
                }
            }
        }
    } else {
        $(obj).addClass("current");
        if ($(obj).attr("class") != "current") {
        	arr.push($(obj).find("img").attr("src"));
        }
    }
    if(arr.length>2){
		layer.alert('最多打印2张影像！', {icon: 5, shadeClose: true});
		$(obj).removeClass("current");
		arr.remove($(obj).find("img").attr("src"));
    }
}

function imgprint() {
    var re = new RegExp("&", "g");
    if (arr.length > 0) {
        var imgArr = arr.join(",").replace(re, "@@");
        window.open(GLOBAL.WEBROOT + "/studyReport/imgPrint.html?imgArr=" + imgArr + "&studyinfoId=" + $("#studyinfoId").val(), 'newwindow', '');
    } else {
        layer.alert('请选择需要打印的影像图片！', {icon: 5, shadeClose: true});
    }
}

//跳转到健康浏览器
function toHealth() {
    var idNo = $("#patientIdnumber").val();
    if (idNo == null || idNo == "") {
        layer.alert('身份证号为空，不能调阅健康档案！', {icon: 5, shadeClose: true});
        return;
    }
    var healthIp = $("#healthIp").val();

    var url = "https://" + healthIp + "/smartcard/browse-scheduler.jsp?idno=" + idNo + "&channel=PUBLICWEB";
    window.open(url);
}


//菜单树加载
var orgTree;
function initKnowledgeTree() {
    var idno = "-1";
    var setting = {
        view: {
            showLine: true,
            addHoverDom: addKnowledgeHoverDom,
            removeHoverDom: removeHoverDom,
            selectedMulti: false
        },
        data: {
            keep: {
                leaf: false,
                parent: false
            },
            key: {
                checked: "checked",
                children: "children",
                name: "name"
            },
            simpleData: {
                enable: false,
                idKey: "id",
                pIdKey: "pid"
            }
        },
        async: {
            enable: true,
            url: GLOBAL.WEBROOT + "/studyReport/initKnowledgeTree.ajax",
            autoParam: ["id"]
        },
        callback: {
            onDblClick: function (evt, treeId, treeNode) {
                onClickKnowledgeTreeNode(treeNode);
                $("#fudong").css("display", "none");
            },
            beforeMouseUp: function (evt, treeId, treeNode) {
                $("#fudong").css("display", "none");
            }
        }
    };

    orgTree = $.fn.zTree.init($("#templateTree"), setting, [{
        id: idno,
        name: '病历夹',
        open: true,
        isParent: true,
        nocheck: true
    }]);
    var treeObj = $.fn.zTree.getZTreeObj("templateTree");
    var nodes = treeObj.getNodes();
    if (nodes.length > 0) {
        treeObj.reAsyncChildNodes(nodes[0], "refresh");
    }
}

/** 点击树节点*/
function onClickKnowledgeTreeNode(treeNode) {
    orgTree.expandNode(treeNode, true);
    getknowledgeReportInfo(treeNode); //查询病例库中关联的报告
}

function addKnowledgeHoverDom(treeId, treeNode) {
        $.ajax({
            type: "POST",
            async: true,
            url: GLOBAL.WEBROOT + "/studyReport/getKnowLedgeReportInfo.ajax?id=" + treeNode.id,
            success: function (data) {
               var temp = eval(data);
                $("#spansj").text(temp.templateExam);
                $("#spanyj").text(temp.templateResult);
            }
        });
        $("#fudong").css("display", "block");
        $("#fudong").css("left", event.clientX);
        $("#fudong").css("top", event.clientY - 50);
}
//查询病例库中关联的报告
function getknowledgeReportInfo(node) {
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/studyReport/getKnowLedgeReportInfo.ajax?id=" +node.id,
        success: function (data) {
            var temp = eval(data);
            CKEDITOR.instances.editorExam.setData(temp.templateExam);
            CKEDITOR.instances.editorResult.setData(temp.templateResult);
        }
    });
}



