function dateComp(d1, d2) {

	var date1 = new Date(Date.parse(d1.replace("-", "/")));

	var date2 = new Date(Date.parse(d2.replace("-", "/")));

	//alert(date1.getTime() + " " + date2.getTime());

	return date1 >= date2;
}

function compareDate(d1, d2) {

	//"yyyy-MM-dd HH:mm:ss
	var date1 = new Date(Date.parse(d1));

	var date2 = new Date(Date.parse(d2));

	return date1 > date2;
}

function fun() {
	var d1 = "2012-12-03";
	var d2 = "2013-01-03";

	var d = dateComp(d1, d2);
	alert(d);
}

/**是否整数或者0**/
function isInt(num) {
     var reg = new RegExp("^\\d+$");
     return reg.test(num);
}

var oneDayMillisecond = 86400000;
function minusDay()
{
    addOneDay(-oneDayMillisecond);
}

function addDays(dateStr,days)
{
    var date = new Date();
    try{
          var startTime = Date.parse(dateStr + " 00:00:00");
          date.setTime(startTime);
          if(date == "Invalid Date")
          {
        	  alert("无效的日期");
              return;
          }
          var times = days*24*60*60*1000;
          date.setTime(date.getTime() + times);
    }catch(e)
    {
        alert(e);
    }
    return date;
}
//合并单元格
jQuery.fn.rowspan = function(colIdx) { //封装的一个JQuery小插件 
	return this.each(function(){ 
	var that; 
	$('tr', this).each(function(row) { 
	$('td:eq('+colIdx+')', this).filter(':visible').each(function(col) { 
	if (that!=null && $(this).html() == $(that).html()) { 
	rowspan = $(that).attr("rowSpan"); 
	if (rowspan == undefined) { 
	$(that).attr("rowSpan",1); 
	rowspan = $(that).attr("rowSpan"); } 
	rowspan = Number(rowspan)+1; 
	$(that).attr("rowSpan",rowspan); 
	$(this).hide(); 
	} else { 
	that = this; 
	} 
	}); 
	}); 
	}); 
	}  

function jinitHeight(option){
	 var contentHeight = $("div.navTab-panel ").innerHeight();
     var searchBarHeight = $("div.searchBar ",navTab.getCurrentPanel()).innerHeight();
     var heigth = (parseInt(contentHeight)- parseInt(searchBarHeight))*0.85;
     $(".singleChartDiv",navTab.getCurrentPanel()).css({"height":heigth});
}
	 
function isNotEmpty(str) { //判断是否为空
	if (str != null && str != undefined && str != "") {
		return true;
	}
	return false;
}

function isEmpty(str) { //判断是否为空
	if (str != null && str != undefined && str != "") {
		return false;
	}
	return true;
}

function faultTypeSel(obj, identification) { //跳转故障大类选择页面
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultTypeSelect.do?groupName=" + identification;
	$(obj).attr("href",href+"&productType="+productType);
}

function faultReasonSel(obj, identification) { //跳转故障小类选择页面
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultReasonSelect.do?groupName=" + identification;
	$(obj).attr("href",href+"&productType="+productType);
}

function meshFaultReasonSelect(obj, identification) { //跳转合并故障小类选择页面
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/meshFaultReasonSelect.do?groupName=" + identification;
	$(obj).attr("href",href+"&productType="+productType);
}

function vocCategory(obj,identification){//跳转到VOC分类选择页面
	var href = "qms/commonSelect/vocCategory.do?groupName="+identification
}

function productTypeSel(obj, identification) { //跳转型号选择页面
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/partTypeSelect.do?groupName=" + identification;
	$(obj).attr("href",href+"&productType="+productType);
}

function supplierSel(obj, identification) { //跳转供应商选择页面
	var href = "quality/testInstance/supplierSelect.do?data=" + identification;
	$(obj).attr("href", href);
}

function partSel(obj, identification) { //跳转物料选择页面
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var isConsumed = $("#isConsumed",navTab.getCurrentPanel()).val(); //是否关键件
	var href = "quality/testInstance/partSelect.do?data=" + identification;
	$(obj).attr("href",href+"&productType="+productType+"&isConsumed=" + isConsumed);
}

function clearFault(identification) { //清空故障大类或故障小类
	var identification_id = "#" + identification + "_id";
	var identification_code = "#" + identification + "_code";
	var identification_name = "#" + identification + "_name";
	$(identification_id, navTab.getCurrentPanel()).val("");
	$(identification_code, navTab.getCurrentPanel()).val("");
	$(identification_name, navTab.getCurrentPanel()).val("");
}

function clearMergeFault(identification) { //清空合并故障小类
	var identification_code = "#" + identification + "_meshFaultCode";
	var identification_name = "#" + identification + "_meshname";
	$(identification_code, navTab.getCurrentPanel()).val("");
	$(identification_name, navTab.getCurrentPanel()).val("");
}

function clearProductType(identification) { //清空型号
	var identification_id = "#" + identification + "_id";
	var identification_partType = "#" + identification + "_partType";
	$(identification_id, navTab.getCurrentPanel()).val("");
	$(identification_partType, navTab.getCurrentPanel()).val("");
}

function clearSupplier(identification) { //清空供应商
	var identification_data = "#" + identification + "_data";
	var identification_supplierCode = "#" + identification + "_supplierCode";
	var identification_supplierName = "#" + identification + "_supplierName";
	$(identification_data, navTab.getCurrentPanel()).val("");
	$(identification_supplierCode, navTab.getCurrentPanel()).val("");
	$(identification_supplierName, navTab.getCurrentPanel()).val("");
}

function clearPart(identification) { //清空物料
	var identification_data = "#" + identification + "_data";
	var identification_partCode = "#" + identification + "_partCode";
	var identification_partName = "#" + identification + "_partName";
	$(identification_data, navTab.getCurrentPanel()).val("");
	$(identification_partCode, navTab.getCurrentPanel()).val("");
	$(identification_partName, navTab.getCurrentPanel()).val("");
}
function categorySel(obj, identification) { //跳转物料分类选择页面		
	//var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var partCategoryName = $("#CRM_PART_CATEGORY_supplierCode",navTab.getCurrentPanel()).val(); //是否关键件
	var href = "quality/testInstance/partCategorySelect.do?data=" + identification;
	$(obj).attr("href",href+"&categoryName="+partCategoryName);
}
function clearCategory(identification) { //清空物料分类
	var identification_data = "#" + identification + "_id";
	var identification_partCode = "#" + identification + "_supplierCode";
	var identification_partName = "#" + identification + "_supplierName";
	$(identification_data, navTab.getCurrentPanel()).val("");
	$(identification_partCode, navTab.getCurrentPanel()).val("");
	$(identification_partName, navTab.getCurrentPanel()).val("");
}
function loadProductData(productFamilyDivId, productTypeDivId) { //加载机型系列及机型类别
	loadProductFamily(productFamilyDivId, productTypeDivId);
	loadProductType(productTypeDivId);
}	
	
function loadProductFamily(productFamilyDivId, productTypeDivId) { //加载机型系列
	var url = "qms/common/loadProductData.do";
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var jsonVar = {productType:productType, title:"1"};
	$.post(url, jsonVar, function(data) {
		$(productFamilyDivId).html("");
		$(productFamilyDivId,navTab.getCurrentPanel()).dropdownlist({
		    id:"productFamilyTxt",
		    columns:3,
		    listboxwidth:500,//下拉框宽
		    maxchecked:100,
		    checkbox:true,
		    listboxmaxheight:400,
		    width:100,
		    data:data,//数据，格式：{value:name}
		    onchange:function(text, value){
		    	if (isNotEmpty(productTypeDivId)) {
		    		loadProductType(productTypeDivId);
		    	}
		    },
		    isEditText:false
		});
	});
}

function loadProductType(divId) { //加载机型类别
	var url = "qms/common/loadProductData.do";
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var productFamilyTxt = $("#productFamilyTxt", navTab.getCurrentPanel()).val();
	var jsonVar = {productType:productType, productFamilyTxt:productFamilyTxt, title:"2"};
	$.post(url, jsonVar, function(data) {
		$(divId).html("");
		$(divId,navTab.getCurrentPanel()).dropdownlist({
		    id:"partTypeListTxt",
		    columns:3,
		    listboxwidth:500,//下拉框宽
		    maxchecked:100,
		    checkbox:true,
		    listboxmaxheight:400,
		    width:100,
		    data:data,//数据，格式：{value:name}
		    onchange:function(text, value){},
		    isEditText:false
		});
	});
}

function loadProductLine(productLineDidvId) { //加载产线
	var url = "qms/common/loadProductData.do";
	var factory = $("#factory", navTab.getCurrentPanel()).val();
	var jsonVar = {factory:factory, title:"3"};
	$.post(url, jsonVar, function(data) {
		$(productLineDidvId).html("");
		$(productLineDidvId,navTab.getCurrentPanel()).dropdownlist({
		    id:"plineListTxt",
		    columns:3,
		    listboxwidth:500,//下拉框宽
		    maxchecked:100,
		    checkbox:true,
		    listboxmaxheight:400,
		    width:100,
		    data:data,//数据，格式：{value:name}
		    onchange:function(text, value){},
		    isEditText:false
		});
	});
}

function exportExcelByCommon(url, formId){ //导出excel
	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var objs = $(formId + " input", navTab.getCurrentPanel());
	var objs_select = $(formId + " select",navTab.getCurrentPanel());
	var myInput;
	for(var i = 0 ; i< objs.length+objs_select.length ; i++){
		var $obj = null;
		if(i>=objs.length){
			$obj = $(objs_select[i-objs.length]);	
		}else{
			$obj = $(objs[i]);			
		}
		var v = $obj.val();
		if(v==null || v==""){
			v="";
		}
		if($obj.attr("type")=="checkbox"){
			if(!$obj.attr("checked")){
				v="";
			}
		}
		myInput = document.createElement("input");
		myInput.setAttribute("name", $obj.attr("name"));
		myInput.setAttribute("value", v);
		myForm.appendChild(myInput);
	}
	document.body.appendChild(myForm);
	myForm.submit();
}

function loadProductFamilyData(divId, dataId, productFamilyTxt, jsonProFamily) { //初始化页面时，加载机型系列
	$(divId, navTab.getCurrentPanel()).dropdownlist({
		 id:dataId,
         columns:3,
         selectedtext:'',
         listboxwidth:450,//下拉框宽
         maxchecked:100,
         checkbox:true,
         listboxmaxheight:400,
         width:120,
         requiredvalue:[],
         selected:productFamilyTxt,
         data:jsonProFamily,//数据，格式：{value:name}
         onchange:function(text,value){}
	 });
}

function loadGasTypeData(divId, dataId, productTypeListTxt, jsonParts) { //初始化页面时，加载气源
	$(divId, navTab.getCurrentPanel()).dropdownlist({
        id:dataId,
        columns:3,
        selectedtext:'',
        listboxwidth:450,//下拉框宽
        maxchecked:20,
        checkbox:true,
        listboxmaxheight:400,
        width:120,
        requiredvalue:[],
        selected:productTypeListTxt,
        data:jsonParts,//数据，格式：{value:name}
        onchange:function(text,value){}
	});
}


function loadProductTypeData(divId, dataId, productTypeListTxt, jsonParts) { //初始化页面时，加载机型类别
	$(divId, navTab.getCurrentPanel()).dropdownlist({
        id:dataId,
        columns:3,
        selectedtext:'',
        listboxwidth:450,//下拉框宽
        maxchecked:100,
        checkbox:true,
        listboxmaxheight:400,
        width:120,
        requiredvalue:[],
        selected:productTypeListTxt,
        data:jsonParts,//数据，格式：{value:name}
        onchange:function(text,value){}
	});
}
function loadvoiceCategoryData(divId, dataId, voiceCategoryListTxt, jsonParts) { //初始化页面时，加客户之声二级分类
	$(divId, navTab.getCurrentPanel()).dropdownlist({
        id:dataId,
        columns:3,
        selectedtext:'',
        listboxwidth:450,//下拉框宽
        maxchecked:100,
        checkbox:true,
        listboxmaxheight:400,
        width:120,
        requiredvalue:[],
        selected:voiceCategoryListTxt,
        data:jsonParts,//数据，格式：{value:name}
        onchange:function(text,value){}
	});
}

function loadRegionData(divId, dataId, regionListTxt, jsonRegions) { //初始化页面时，加载服务中心
	$(divId, navTab.getCurrentPanel()).dropdownlist({
         id:dataId,
         columns:3,
         selectedtext:'',
         listboxwidth:450,//下拉框宽
         maxchecked:100,
         checkbox:true,
         listboxmaxheight:400,
         width:120,
         requiredvalue:[],
         selected:regionListTxt,
         data:jsonRegions,//数据，格式：{value:name}
         onchange:function(text,value){}
     });
}

function loadMergeRegionData(divId, dataId, mergeRegionListTxt, jsonMergeRegions) { //初始化页面时，加载合并服务中心
	$(divId, navTab.getCurrentPanel()).dropdownlist({
        id:dataId,
        columns:3,
        selectedtext:'',
        listboxwidth:450,//下拉框宽
        maxchecked:100,
        checkbox:true,
        listboxmaxheight:400,
        width:120,
        requiredvalue:[],
        selected:mergeRegionListTxt,
        data:jsonMergeRegions,//数据，格式：{value:name}
        onchange:function(text,value){}
    });
}

function loadProductLineData(divId, dataId, plineListTxt, jsonLines) { //初化页面时，加载产线
	$(divId, navTab.getCurrentPanel()).dropdownlist({
        id:dataId,
        columns:3,
        selectedtext:'',
        listboxwidth:450,//下拉框宽
        maxchecked:100,
        checkbox:true,
        listboxmaxheight:400,
        width:120,
        requiredvalue:[],
        selected:plineListTxt,
        data:jsonLines,//数据，格式：{value:name}
        onchange:function(text,value){}
    });
}

function loadDepartMent(type, department) {
	var url = "qms/common/loadDepartment.do";
	var factory = "";
	debugger;
	if(type != null && type == "pdialog") {
		factory = $("#factoryNumber", $.pdialog.getCurrent()).val();
	} else {
		factory = $("#factoryNumber", navTab.getCurrentPanel()).val();
	}
	if(factory == null || factory == "undefined" || factory == "") {
		if(type != null && type == "pdialog") {
			$("#department", $.pdialog.getCurrent()).html("");
		} else {
			$("#department", navTab.getCurrentPanel()).html("<option value=\"\">未选择</option>");
		}
		return false;
	}
	var jsonVar = {factory:factory};
	$.post(url, jsonVar, function(data) {
		var departmentHtml = "<option value=\"\">未选择</option>";
		for(var i in data.departmentList) {
			//console.log(data.departmentList[i].departmentName);
			if(department != null && (department == data.departmentList[i].departmentName || department == data.departmentList[i].departmentNumber)) {
				departmentHtml = departmentHtml + "<option value=\"" + data.departmentList[i].departmentNumber + "\" selected=\"selected\">" + data.departmentList[i].departmentName + "</option>";
			} else {
				departmentHtml = departmentHtml + "<option value=\"" + data.departmentList[i].departmentNumber + "\">" + data.departmentList[i].departmentName + "</option>";
			}
		}
		if(type != null && type == "pdialog") {
			$("#department", $.pdialog.getCurrent()).html(departmentHtml);
		} else {
			$("#department", navTab.getCurrentPanel()).html(departmentHtml);
		}
	});
}