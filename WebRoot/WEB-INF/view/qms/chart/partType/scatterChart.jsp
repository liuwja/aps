<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="0">
<style type="text/css" media="screen">
	${demo.css}
.toolBar {
	margin-top: 5px;
}
.toolBar li {
	margin-left: 5px;
	font-weight: bold;
}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	//loadPartTypeChart();  
});

var title = "型号";

function loadScatterChartCondition(loadType) {
	var url = "<c:url value='qms/common/partTypeLineOps.do' />";
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
	var factory = $('#factory', navTab.getCurrentPanel()).val();
	var productFamilyTxt = $('#productFamilyTxt', navTab.getCurrentPanel()).val();
	var plineListTxt = $('#plineListTxt', navTab.getCurrentPanel()).val();
	var jsonVar = {productType:productType,factory:factory,plineListTxt:plineListTxt,productFamilyTxt:productFamilyTxt,
			title: "\"四象限分析\"",productFamilyDocId:"porductFamilyScatterList",plineDocId:"plineScatterList"};
	if(loadType == 0){
		delete jsonVar["productFamilyTxt"];
	}
	if(loadType == 1){
		delete jsonVar["plineListTxt"];
	}
	$("#scatterChartId", navTab.getCurrentPanel()).load(url,jsonVar);
}

function loadScatterChart(scatterType) {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
	if(productType==""){
        alertMsg.info("请选择机型类别");
    }
 //   var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
//	if(queryMonth == ''){
//		alertMsg.info("请选择维修开始和结束月份");
	//	return false;
	//}
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	if(startTime == ''){
		alertMsg.info("请选择维修开始月份");
		return false;
	}
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		if(endTime == ''){
		alertMsg.info("请选择维修开始月份");
		return false;
	}
	var productFamilyTxt = $("#productFamilyTxt", navTab.getCurrentPanel()).val(); //产线
	var partTypeListTxt = $("#SCAT_PT_partType", navTab.getCurrentPanel()).val(); //选择型号
	var plineListTxt = $("#plineListTxt", navTab.getCurrentPanel()).val(); //选择产线
	var regionListTxt = $("#regionListTxt", navTab.getCurrentPanel()).val(); //选择区域
    var faultReasonCode = $("#SCAT_code", navTab.getCurrentPanel()).val();
    var faultTypeCode  = $("#SCAT_LB_code", navTab.getCurrentPanel()).val();
    var faultReasonValid  = $("#faultReasonValid", navTab.getCurrentPanel()).val();
    var statisData = $("#statisData", navTab.getCurrentPanel()).val();
    var isOver  = "否";
    var isConsumedPart = $("#isConsumedPart",navTab.getCurrentPanel()).val();	//是否消耗配件
	var xCount = 9999;
	var url = "";
	title = scatterType;
	var tempTitle = scatterType + "百台维修四象限分析图";
	switch(scatterType) {
		case "型号" :
			url = "<c:url value='partTypeChart/getPartTypeInfo.do'/>";
			break;
		case "产线" : 
			url = "<c:url value='plineChart/getPlineInfo.do'/>";
			break;
		case "区域" :
			url = "<c:url value='regionChart/getRegionInfo.do'/>";
			break;
		default :
			alertMsg.error("查询出错，请联系管理员");
			return;
	}
	$.post(url, {productType:productType, startTime:startTime,endTime:endTime,xCount:xCount, title:tempTitle,productFamilyTxt:productFamilyTxt,
		faultReasonCode:faultReasonCode, faultTypeCode:faultTypeCode, faultReasonValid:faultReasonValid, isOver:isOver,
		partTypeListTxt:partTypeListTxt, regionListTxt:regionListTxt, plineListTxt:plineListTxt, statisData:statisData,isConsumedPart:isConsumedPart}, function(data) {
			if (data.result == 0) {
				setScatterData(data.chartsInfo, "0");
				showScatterChart("scatterChart", data.chartsInfo, "0");
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
		}
	});
}

function execlOutput() { //导出execl
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
    if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    }
	var myForm = document.createElement("form");
	switch(title) {
		case '型号' : 
			url = "<c:url value='partTypeChart/execlOutput.do'/>";
			break;
		case '区域' :
			url = "<c:url value='regionChart/execlOutput.do'/>";
			break;
		case '产线' : 
			url = "<c:url value='plineChart/execlOutput.do'/>";
			break;
		default :
			break;
	}
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm";
	var objs = $("#scatterChartForm input",navTab.getCurrentPanel());
	var objs_select = $("#scatterChartForm select",navTab.getCurrentPanel());
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
	myInput = document.createElement("input");
	myInput.setAttribute("name", "isOver");
	myInput.setAttribute("value", "否");
	myForm.appendChild(myInput);
	document.body.appendChild(myForm);
	myForm.submit();
}
		 
function scat_fr_clearAll()
{
    $("#SCAT_id", navTab.getCurrentPanel()).val("");
    $("#SCAT_name", navTab.getCurrentPanel()).val("");
    $("#SCAT_code", navTab.getCurrentPanel()).val("");
}
function SCAT_FR_Sel(obj){
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultReasonSelect.do?groupName=SCAT";
	$(obj).attr("href",href+"&productType="+productType);
}

function scat_lb_clearAll()
{
    $("#SCAT_LB_id", navTab.getCurrentPanel()).val("");
    $("#SCAT_LB_code", navTab.getCurrentPanel()).val("");
    $("#SCAT_LB_name", navTab.getCurrentPanel()).val("");
}
function scatlbRfSel(obj){
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultTypeSelect.do?groupName=SCAT_LB";
	$(obj).attr("href",href+"&productType="+productType);
}
function SCAT_PTSel(obj) {
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var partTypeId = $("#SCAT_PT_id",navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/partTypeSelect.do?groupName=SCAT_PT";
	$(obj).attr("href",href+"&productType="+productType+"&partTypeId="+partTypeId);
}
function SCAT_PT_clearAll() {
	$("#SCAT_PT_id", navTab.getCurrentPanel()).val("");
	$("#SCAT_PT_partType", navTab.getCurrentPanel()).val("");
}
function SCAT_REGIONSel() {
	var strarr = $("#SCAT_REGION_id", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/findRegionSelect.do?strarr="+strarr;
	$(obj).attr("href",href);
}
function SCAT_REGION_clearAll() {
	$("#SCAT_REGION_id", navTab.getCurrentPanel()).val("");
	$("#SCAT_REGION_code", navTab.getCurrentPanel()).val("");
	$("#SCAT_REGION_name", navTab.getCurrentPanel()).val("");
}
</script>
<div class="pageHeader" style="position:static" >
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post" id="scatterChartForm">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<th>机型类别：</th>
					<td>
						<select name="productType" onchange="loadScatterChartCondition(0);">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
								<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
						</select>
		   			</td>
		   			<th>产品系列：</th>          
					<td>
						<div id="porductFamilyScatterList" class="dropdownlist"></div>
					</td>
					<th>统计数据：</th>
	                <td>
						<select id="statisData" name="statisData">
							<option value="repairCount" selected="selected">维修数</option> 	
	                    	<option value="repairRate" <c:if test="${vo.statisData=='repairRate'}">selected="selected"</c:if> >维修率</option>
						</select>
	                </td>
					<th>故障小类：</th>
    				<td>
	    				<input type="hidden" name="faultReasonID" id="SCAT_id" value="${vo.faultReasonID}"/>
	    				<input type="hidden" name="faultReasonCode" id="SCAT_code" value="${vo.faultReasonCode}"/>
	    				<input type="text" name="faultReasonTxt" id="SCAT_name" size="15" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
	    				<a id="btn" onclick="SCAT_FR_Sel(this)" class="btnLook btn" href="qms/commonSelect/faultReasonSelect.do?groupName=SCAT" width=950 height=500 lookupGroup="SCAT">故障小类选择</a>  
						<a class="btnClear" href="javascript:void(0);" onclick="scat_fr_clearAll()" title="清空"></a> 
    				</td>
    			</tr>
				<tr>	
					<th>工厂：</th>
					<td>
						<select name="factory" id="factory" onchange="loadScatterChartCondition(1);">
							<option value="">请选择</option>
								<c:forEach items="${factorys}" var="o">
									<option value="${o.factory }" <c:if test="${vo.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
								</c:forEach>
						</select>
					</td>
					<th>型号：</th>                       
					<td>
						<input type="hidden" id="SCAT_PT_id" value="">
    					<input type="text" name="partTypeListTxt" id="SCAT_PT_partType" size="12" readonly="true" value="${vo.partTypeListTxt}" style="float: left;"/>    				
    					<a id="btn" onclick="SCAT_PTSel(this)"  class="btnLook btn" href="qms/commonSelect/partTypeSelect.do?groupName=SCAT_PT" width=850 height=500 lookupGroup="SCAT_PT">型号选择</a>  
						<a class="btnClear" href="javascript:void(0);" onclick="SCAT_PT_clearAll()" title="清空"></a>
					</td>
					<th>维修开始日期：</th>
					<td><input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime }" readonly="readonly" size="8"/></td>
					<th>维修结束日期：</th>
					<td><input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime }" readonly="readonly" size="8"/></td>	
<!-- 			<td><input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:ture, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly" size="8"/></td> -->		
					<th>故障大类：</th>
	                <td>
	                    <input type="hidden" name="faultTypeID" id="SCAT_LB_id" size="15" readonly="true" value="${vo.faultTypeID}"/>  
	                    <input type="hidden" name="faultTypeCode" id="SCAT_LB_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
	                    <input type="text" name="faultTypeTxt" id="SCAT_LB_name" size="15" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
	                    <a onclick="scatlbRfSel(this);" id="btn" class="btnLook btn" href="qms/commonSelect/faultTypeSelect.do?groupName=SCAT_LB" width=950 height=500 lookupGroup="SCAT_LB">故障大类选择</a>
	                    <a class="btnClear" href="javascript:void(0);" onclick="scat_lb_clearAll()" title="清空"></a> 
	                </td>
				</tr>
				<tr>
					<th>产线：</th>                       
					<td><div id="plineScatterList" class="dropdownlist"></div></td>
					<th>区域：</th>                       
					<td>
						<div id="regionScatterList" class="dropdownlist"></div>
					</td>
					<th>故障小类是否有效：</th>
					<td>
						<select id="faultReasonValid" name="faultReasonValid">
							<option value="">全选</option>
							<option value="是" ${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid eq '是' ? 'selected':''}>是</option>
							<option value="否" ${vo.faultReasonValid eq '否' ? 'selected':''}>否</option>
						</select>
					</td>
					<th>是否消耗配件：</th>
					<td>
						<select id="isConsumedPart" name="isConsumedPart">
							<option value="">全选</option>
							<option value="是" ${vo.isConsumedPart eq '是' ? 'selected':''}>是</option>
							<option value="否" ${vo.isConsumedPart eq '否' ? 'selected':''}>否</option>
						</select>
					</td>
				</tr>
				<tr>
	               <td colspan="10">
						<div class="panelBar">
							<ul class="toolBar">
								<li><a href="javascript:void(0);" onclick="loadScatterChart('型号')">型号散点图</a></li>
								<li><a href="javascript:void(0);" onclick="loadScatterChart('区域')">区域散点图</a></li>
								<li><a href="javascript:void(0);" onclick="loadScatterChart('产线')">产线散点图</a></li>
								<li><a href="javascript:void(0);" onclick="execlOutput()">导出Execl</a></li>
								<li><a href="javascript:void(0);" onclick="getDataSourceByMenuName('市场质量统计分析', '具体分析', '百台四象限分析', '象限图')">数据来源</a></li>
							</ul>
						</div>
	               </td>               			
				</tr>
			</table>
		</div>
	</form>
</div>
	<span id="scatterChartId">
		<script type="text/javascript">
			$(function(){
				$("#plineScatterList").dropdownlist({
				    id:'plineListTxt',
				    columns:3,
				    selectedtext:'',
				    listboxwidth:450,//下拉框宽
				    maxchecked:100,
				    checkbox:true,
				    listboxmaxheight:400,
				    width:120,
				    requiredvalue:[],
				    selected:[${vo.plineListTxt}],
				    data:${jsonLines},//数据，格式：{value:name}
				    onchange:function(text,value){
				    }
				});
				
				$("#porductFamilyScatterList").dropdownlist({
				    id:'productFamilyTxt',
				    columns:3,
				    selectedtext:'',
				    listboxwidth:500,//下拉框宽
				    maxchecked:100,
				    checkbox:true,
				    listboxmaxheight:400,
				    width:120,
				    requiredvalue:[],
				    selected:[${vo.productFamilyTxt}],
				    data:${jsonProFamily},//数据，格式：{value:name}
				    onchange:function(text,value){
				    }
				});
				
				$("#regionScatterList").dropdownlist({
		            id:'regionListTxt',
		            columns:3,
		            selectedtext:'',
		            listboxwidth:700,//下拉框宽
		            maxchecked:100,
		            checkbox:true,
		            listboxmaxheight:400,
		            width:120,
		            requiredvalue:[],
		            selected:[${vo.regionListTxt}],
		            data:${jsonRegions},//数据，格式：{value:name}
		            onchange:function(text,value){
		            }
		        });
			});
		</script>
	</span>
<div  class="pageContent" >
		<div id="scatterChart" class="singleChartDiv" style="height: 450px"></div>
</div>