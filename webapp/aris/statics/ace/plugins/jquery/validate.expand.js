// 手机号码验证
jQuery.validator.addMethod("mobile", function(value, element) {
    var length = value.length;
    var mobile =  /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "格式错误");   

// 电话号码验证   
jQuery.validator.addMethod("phone", function(value, element) {
    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
    return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");
//身份证号码验证
jQuery.validator.addMethod("isIdCardNo", function(value, element) {
    return this.optional(element) || isIdCardNo(value);
}, "号码错误");
//下拉框-1值校验
jQuery.validator.addMethod("checkSelectVal", function(value, element) {
    return this.optional(element) || checkSelectVal(value);
}, "请选择");

jQuery.validator.addMethod("publicSelectVal", function(value, element) {
    return this.optional(element) || publicSelectVal(value,element);
}, "");

//删掉下拉框为-1的标签
jQuery.validator.addMethod("checkStudyWard", function(value, element) {
    return this.optional(element) || checkStudyWard(value);
}, "");
jQuery.validator.addMethod("checkStudyApplocid", function(value, element) {
    return this.optional(element) || checkStudyApplocid(value);
}, "");
jQuery.validator.addMethod("checkStudyAppdoc", function(value, element) {
    return this.optional(element) || checkStudyAppdoc(value);
}, "");
jQuery.validator.addMethod("checkEquipmentId", function(value, element) {
    return this.optional(element) || checkEquipmentId(value);
}, "");
jQuery.validator.addMethod("checkPaymenttypeId", function(value, element) {
    return this.optional(element) || checkPaymenttypeId(value);
}, "");
jQuery.validator.addMethod("checkStudylevelId", function(value, element) {
    return this.optional(element) || checkStudylevelId(value);
}, "");
jQuery.validator.addMethod("checkStudyConsultorg", function(value, element) {
    return this.optional(element) || checkStudyConsultorg(value);
}, "");
jQuery.validator.addMethod("checkStudyConsultloc", function(value, element) {
    return this.optional(element) || checkStudyConsultloc(value);
}, "");
// 邮政编码验证   
jQuery.validator.addMethod("zipCode", function(value, element) {
    var tel = /^[0-9]{6}$/;
    return this.optional(element) || (tel.test(value));
}, "邮政编码格式错误");

// QQ号码验证   
jQuery.validator.addMethod("qq", function(value, element) {
    var tel = /^[1-9]\d{4,9}$/;
    return this.optional(element) || (tel.test(value));
}, "qq号码格式错误");

// IP地址验证
jQuery.validator.addMethod("ip", function(value, element) {
    var ip = /^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
    return this.optional(element) || (ip.test(value) && (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256));
}, "Ip地址格式错误");

// 字母和数字的验证
jQuery.validator.addMethod("chrnum", function(value, element) {
    var chrnum = /^([a-zA-Z0-9]+)$/;
    return this.optional(element) || (chrnum.test(value));
}, "只能输入数字和字母(字符A-Z, a-z, 0-9)");

// 中文的验证
jQuery.validator.addMethod("chinese", function(value, element) {
    var chinese = /^[\u4e00-\u9fa5]+$/;
    return this.optional(element) || (chinese.test(value));
}, "只能输入中文");

// 下拉框验证
$.validator.addMethod("selectNone", function(value, element) {
    return value == "请选择";
}, "必须选择一项");

// 字节长度验证
jQuery.validator.addMethod("byteRangeLength", function(value, element, param) {
    var length = value.length;
    for (var i = 0; i < value.length; i++) {
        if (value.charCodeAt(i) > 127) {
            length++;
        }
    }
    return this.optional(element) || (length >= param[0] && length <= param[1]);
}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算2个字节)"));

/** 根据身份证得出性别和出生日期*/
function getData(id,birthday,sexcode){
	   var ido=document.getElementById(id);
	   var bd=document.getElementById(birthday);
	   var sex=document.getElementById(sexcode);
	   if(!/^\d{6}((?:19|20)((?:\d{2}(?:0[13578]|1[02])(?:0[1-9]|[12]\d|3[01]))|(?:\d{2}(?:0[13456789]|1[012])(?:0[1-9]|[12]\d|30))|(?:\d{2}02(?:0[1-9]|1\d|2[0-8]))|(?:(?:0[48]|[2468][048]|[13579][26])0229)))\d{2}(\d)[xX\d]$/.test(ido.value)){
	      alert('身份证号非法!');
	      return;
	   }
	   
	   bd.value=(RegExp.$1).substr(0,4)+'-'+(RegExp.$1).substr(4,2)+'-'+(RegExp.$1).substr(6,2);
	   sex.value=(ido.value.substr(14,3)%2==0)?'2':'1';
	   //sex.value=(parseInt(RegExp.$2)%2==0?'2':'1');
	}

/**
 * 验证yyyy-MM-DD
 * @param {Object} num
 * @return {TypeName} 
 */
function isDateYMD(value){
	
	var reg=/^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$/; 
	if(!value.match(reg)){ 
	    return false;
	}else{ 
		return true;
    } 

}

//下拉框值为-1时提示选择
function publicSelectVal(value,obj){
	if(value == -1){
		$(obj).remove();
		return true;
	}
	return true;
}

//下拉框值为-1时提示选择
function checkSelectVal(val){
	if(val == -1){
		return false;
	}
	return true;
}
function checkStudyWard(val){
	if(val == -1){
		$("#studyWard").remove();
		return true;
	}
	return true;
}

function checkStudyApplocid(val){
	if(val == -1){
		$("#studyApplocid").remove();
		return true;
	}
	return true;
}
function checkStudyAppdoc(val){
	if(val == -1){
		$("#studyAppdoc").remove();
		return true;
	}
	return true;
}
function checkEquipmentId(val){
	if(val == -1){
		$("#equipmentId").remove();
		return true;
	}
	return true;
}
function checkPaymenttypeId(val){
	if(val == -1){
		$("#paymenttypeId").remove();
		return true;
	}
	return true;
}
function checkStudylevelId(val){
	if(val == -1){
		$("#studylevelId").remove();
		return true;
	}
	return true;
}
function checkStudyConsultorg(val){
	if(val == -1){
		$("#studyConsultorg").remove();
		return true;
	}
	return true;
}
function checkStudyConsultloc(val){
	if(val == -1){
		$("#studyConsultloc").remove();
		return true;
	}
	return true;
}



//增加身份证验证
function isIdCardNo(num) {
    var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
    var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
    var varArray = new Array();
    var intValue;
    var lngProduct = 0;
    var intCheckDigit;
    var intStrLen = num.length;
    var idNumber = num;
    // initialize
    if ((intStrLen != 15) && (intStrLen != 18)) {
        return false;
    }
    // check and set value
    for (i = 0; i < intStrLen; i++) {
        varArray[i] = idNumber.charAt(i);
        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
            return false;
        } else if (i < 17) {
            varArray[i] = varArray[i] * factorArr[i];
        }
    }

    if (intStrLen == 18) {
        //check date
        var date8 = idNumber.substring(6, 14);
        if (isDate8(date8) == false) {
            return false;
        }
        // calculate the sum of the products
        for (i = 0; i < 17; i++) {
            lngProduct = lngProduct + varArray[i];
        }
        // calculate the check digit
        intCheckDigit = parityBit[lngProduct % 11];
        // check last digit
        if (varArray[17] != intCheckDigit) {
            return false;
        }
    }
    else {        //length is 15
        //check date
        var date6 = idNumber.substring(6, 12);
        if (isDate6(date6) == false) {
            return false;
        }
    }
    return true;
}
function isDate6(sDate) {
    if (!/^[0-9]{6}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    if (year < 1700 || year > 2500) return false
    if (month < 1 || month > 12) return false
    return true
}
/**
* 判断是否为“YYYYMMDD”式的时期
*
*/
function isDate8(sDate) {
    if (!/^[0-9]{8}$/.test(sDate)) {
        return false;
    }
    var year, month, day;
    year = sDate.substring(0, 4);
    month = sDate.substring(4, 6);
    day = sDate.substring(6, 8);
    var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    if (year < 1700 || year > 2500) return false
    if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
    if (month < 1 || month > 12) return false
    if (day < 1 || day > iaMonthDays[month - 1]) return false
    return true
}
