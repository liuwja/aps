<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="0">
<style type="text/css" media="screen">
	${demo.css}
	
.my-uploadify-button {
    background:none;
    border: none;
    text-shadow: none;
    border-radius:0;
}

.uploadify:hover .my-uploadify-button {
    background:none;
    border: none;
}

.fileQueue {
    width: 400px;
    height: 150px;
    overflow: auto;
    border: 1px solid #E5E5E5;
    margin-bottom: 10px;
}

</style>
<script src="resources/echart/echarts.min.js"></script>
<script src="resources/echart/china.js"></script>
<script type="text/javascript">
function loadCommonChart(index) {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
    if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    }
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
	if(queryMonth == ""){
		alertMsg.info("请选择维修截至月份");
		return false;
	}
	var xCount = $("#xCount", navTab.getCurrentPanel()).val();
	var reg = new RegExp("^-?\\d+$");
	if(xCount.match(reg)==null){
		alertMsg.info("X轴数量请输入整数!");
        return false;
    }else if(xCount<5){
    	alertMsg.info("X轴数量不能少于5!");
        return false;
    }
	//工厂，产线，区域，故障小类
    var productFamilyTxt = $("#productFamilyTxt", navTab.getCurrentPanel()).val();
    var partTypeListTxt = $("#COMMONCHART_partType", navTab.getCurrentPanel()).val();
    var plineListTxt = $("#plineListTxt", navTab.getCurrentPanel()).val();
    var regionListTxt = $("#regionListTxt", navTab.getCurrentPanel()).val();
    var faultReasonCode = $("#COMC_code", navTab.getCurrentPanel()).val();
    var faultTypeCode  = $("#COMMON_FT_code", navTab.getCurrentPanel()).val();
    var faultReasonValid  = $("#faultReasonValid", navTab.getCurrentPanel()).val();
    var isOver = "否";
    var startI = $("#startI",navTab.getCurrentPanel()).val();
    var endI = $("#endI",navTab.getCurrentPanel()).val();
    
    var statisType =$("#statisType", navTab.getCurrentPanel()).find("option:selected").val();
    var isConsumedPart = $("#isConsumedPart",navTab.getCurrentPanel()).val();
    var url = "";
    if(index==1){
    	url = "<c:url value='productFamilyChart/getProductFamilyInfo.do'/>";
    }else if(index == 2){
    	url = "<c:url value='partTypeChart/getPartTypeInfo.do'/>";
    }else if(index == 3){
    	url = "<c:url value='regionChart/getRegionInfo.do'/>";
    }else if(index == 4){
    	url = "<c:url value='plineChart/getPlineInfo.do'/>";
    }else if(index == 5){
    	url = "<c:url value='faultReasonChart/getFaultReasonInfo.do'/>";
    }else if(index == 6){
    	url = "<c:url value='faultTypeChart/getFaultTypeInfo.do'/>";
    }
	var jsonData = {productType:productType, queryMonth:queryMonth, xCount:(xCount-1), productFamilyTxt:productFamilyTxt,isOver:isOver,
			faultReasonCode:faultReasonCode, faultTypeCode:faultTypeCode, partTypeListTxt:partTypeListTxt,faultReasonValid:faultReasonValid,
		    regionListTxt:regionListTxt, plineListTxt:plineListTxt, statisType:statisType,startI:startI,endI:endI,isConsumedPart:isConsumedPart};
	$.post(url,jsonData, function(data) {
		if (data.result == 0) {
			showChart("commonChart", data.chartsInfo);
// 			$("#commonChart").append("<div style='z-index: 1000; position: absolute; top:0px'><button>测试</button></div>");
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}	

function loadCommonChartCondition(loadType) {	
	var url = "<c:url value='qms/common/partTypeLineOps.do' />";
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var factory = $("#factory", navTab.getCurrentPanel()).val();
	var productFamilyTxt = $("#productFamilyTxt", navTab.getCurrentPanel()).val();
	var plineListTxt = $("#plineListTxt", navTab.getCurrentPanel()).val();
	var jsonVar = {productType:productType,factory:factory,plineListTxt:plineListTxt,productFamilyTxt:productFamilyTxt, title: "\"年度排列分析\"",productFamilyDocId:"prodFamilyComList",plineDocId:"plineComList"};
	if(loadType == 0){
		delete jsonVar["productFamilyTxt"];
	}
	if(loadType == 1){
		delete jsonVar["plineListTxt"];
	}
	$("#commonTd", navTab.getCurrentPanel()).load(url,jsonVar);
}
</script>
<div class="pageHeader" style="position:static">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar" style="">
		<table class="searchContent">
			<tr>
				<th>机型类别：</th>
                <td>
					<select name="productType" onchange="loadCommonChartCondition(0);">
						<option value="">请选择</option>
						<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
						</c:forEach>
					</select>
    			</td>
    			<th>产品系列：</th>
                <td>
                	<div id="prodFamilyComList" class="dropdownlist"></div>
                </td>
                <th>故障小类：</th>
    			<td>
    				<input type="hidden" name="faultReasonID" id="COMC_id" value="${vo.faultReasonID}"/>
    				<input type="hidden" name="faultReasonCode" id="COMC_code" value="${vo.faultReasonCode}"/>
    				<input type="text" name="faultReasonTxt" id="COMC_name" size="15" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="faultReasonSel(this, 'COMC')" class="btnLook btn" width=950 height=500 lookupGroup="COMC">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearFault('COMC')" title="清空"></a> 
    			</td>
    			<th>X轴数量：</th>
                <td>
                    <input type="text" size="2" name="xCount" id="xCount" value="${vo.xCount+1 }" />
                </td>
                <th>百台内生产后第X月-X月维修数</th>
                <td>
                	<input type="text" size = "2" onkeyup="value=value.replace(/\D/g, '')" name="startI" id="startI" /> - <input type="text" size = "2" onkeyup="value=value.replace(/\D/g, '')" name="endI" id="endI" />
                </td>
			</tr>
			<tr>
                <th>工厂：</th>
                <td>
					<select name="factory" id="factory" onchange="loadCommonChartCondition(1);">
							<option value="">请选择</option>
							<c:forEach items="${factorys}" var="o">
							<option value="${o.factory }" <c:if test="${vo.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
							</c:forEach>
					</select>
    			</td>
    			<th>型号：</th>                       
                <td>
                	<input type="hidden" id="COMMONCHART_id" value="">
    				<input type="text" name="partTypeListTxt" id="COMMONCHART_partType" size="12" readonly="true" value="${vo.partTypeListTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="productTypeSel(this, 'COMMONCHART')"  class="btnLook btn" width=850 height=500 lookupGroup="COMMONCHART">型号选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearProductType('COMMONCHART')" title="清空"></a> 
                </td>
                <th>故障大类：</th>
                <td>
                    <input type="hidden" name="faultTypeID" id="COMMON_FT_id" size="15" readonly="true" value="${vo.faultTypeID}"/>  
                    <input type="hidden" name="code" id="COMMON_FT_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="COMMON_FT_name" size="15" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a onclick="faultTypeSel(this, 'COMMON_FT');" id="btn" class="btnLook btn" width=950 height=500 lookupGroup="COMMON_FT">故障大类选择</a>
                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('COMMON_FT')" title="清空"></a> 
                </td> 
                <th>排序方式：</th>    
    			<td>
    				<select id="statisType" name="statisType">
						<option <c:if test="${vo.statisType==0}">checked="checked"</c:if> value="0">维修数</option>
						<option <c:if test="${vo.statisType==1}">checked="checked"</c:if> value="1">维修率</option>
					</select>
    			</td>                
			</tr>
			<tr>
				<th>产线：</th>                       
                <td>
                	<div id="plineComList" class="dropdownlist"></div>
                </td>
                <th>区域：</th>                       
                <td>
                	<div id="regionCommonList" class="dropdownlist"></div>
                </td>
                <th>维修日期：</th>
                <td>
                    <input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly" size="8"/>
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
						<option value="是" ${vo.isConsumedPart eq '是'  ? 'selected':''}>是</option>
						<option value="否" ${vo.isConsumedPart eq '否' ? 'selected':''}>否</option>
					</select>
				</td>
			</tr>
			<tr>
                <td colspan="6">
                	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadCommonChart(1);">产品系列排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadCommonChart(2);">型号排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadCommonChart(3);">区域排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadCommonChart(4);">产线排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadCommonChart(5);">故障小类排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadCommonChart(6);">故障大类排列图</button></div></div>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="getDataSourceByMenuName('市场质量统计分析', '具体分析', '年度百台排列分析', '排列图');">数据来源</button></div></div>
                </td>               			
			</tr>
		</table>
	</div>
	</form>
</div>
	<span id="commonTd">
		<script type="text/javascript">
			$(function(){
				$("#plineComList").dropdownlist({
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
				
				$("#prodFamilyComList").dropdownlist({
				    id:'productFamilyTxt',
				    columns:3,
				    selectedtext:'',
				    listboxwidth:500,//下拉框宽
				    maxchecked:100,
				    checkbox:true,
				    listboxmaxheight:400,
				    width:125,
				    requiredvalue:[],
				    selected:[${vo.productFamilyTxt}],
				    data:${jsonProFamily},//数据，格式：{value:name}
				    onchange:function(text,value){
				    }
				});
				
				$("#regionCommonList").dropdownlist({
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
<div class="pageContent">
		<div id="commonChart" class="singleChartDiv" style="height: 450px; position: relative;">
			
		</div>
</div>