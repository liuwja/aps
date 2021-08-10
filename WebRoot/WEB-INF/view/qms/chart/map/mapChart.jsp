<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<META HTTP-EQUIV="pragma" CONTENT="no-cache"> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate"> 
<META HTTP-EQUIV="expires" CONTENT="0">
<script type="text/javascript">
function loadMapCondition(loadType) {
	var url = "<c:url value='qms/common/partTypeLineOps.do'/>";
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var factory = $("#factory", navTab.getCurrentPanel()).val();
	var partTypeListTxt = $("#partTypeListTxt", navTab.getCurrentPanel()).val();
	var productFamilyTxt = $("#productFamilyTxt", navTab.getCurrentPanel()).val();
	var plineListTxt = $("#plineListTxt", navTab.getCurrentPanel()).val();
	var regionListTxt = $("#regionListTxt", navTab.getCurrentPanel()).val();
	var jsonVar = {productType:productType,factory:factory,partTypeListTxt:partTypeListTxt,plineListTxt:plineListTxt,regionListTxt:regionListTxt,productFamilyTxt:productFamilyTxt,
			partTypeDocId:"prodTypeMapList",productFamilyDocId:"prodFamilyMapList",plineDocId:"prodLineMapList"};
	if(loadType == 0){
		delete jsonVar["productFamilyTxt"];
		delete jsonVar["partTypeListTxt"];
	}
	if(loadType == 1){
		delete jsonVar["plineListTxt"];
	}
	$("#mapListId").load(url,jsonVar);
}

function MAP_FT_Sel(obj) {
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultTypeSelect.do?groupName=MAP_FT";
	$(obj).attr("href", href+"&productType=" + productType);
}

function MAP_R_Sel(obj) {
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var href = "qms/commonSelect/faultReasonSelect.do?groupName=MAP_R";
	$(obj).attr("href", href+"&productType=" + productType);
}

function MAP_FT_clearAll() {
	$("#MAP_FT_name",navTab.getCurrentPanel()).val("");
	$("#MAP_FT_id",navTab.getCurrentPanel()).val("");
	$("#MAP_FT_code",navTab.getCurrentPanel()).val("");
}

function MAP_R_claerAll() {
	$("#MAP_R_name",navTab.getCurrentPanel()).val("");
	$("#MAP_R_id",navTab.getCurrentPanel()).val("");
	$("#MAP_R_code",navTab.getCurrentPanel()).val("");
}

function loadMapChart() {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
    if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    }
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val();
	if(queryMonth == ''){
		alertMsg.info("请选择维修截至月份");
		return false;
	}
	var productFamilyTxt = $("#productFamilyTxt", navTab.getCurrentPanel()).val();
	var partTypeListTxt = $("#partTypeListTxt", navTab.getCurrentPanel()).val();
	var plineListTxt = $("#plineListTxt", navTab.getCurrentPanel()).val();
	var regionListTxt = $("#regionListTxt", navTab.getCurrentPanel()).val();
	var faultTypeCode  = $("#MAP_FT_code", navTab.getCurrentPanel()).val();
	var faultReasonCode = $("#MAP_R_code", navTab.getCurrentPanel()).val();
	var isOver = $("#isOver", navTab.getCurrentPanel()).val();
	var statisType =$("#statisType", navTab.getCurrentPanel()).find("option:selected").val();
	var faultReasonValid =  $("#faultReasonValid", navTab.getCurrentPanel()).val();
	var isConsumedPart = $("#isConsumedPart",navTab.getCurrentPanel()).val();
	var url = "<c:url value='mapChart/getMapInfo.do'/>";
	$.post(url, {productType : productType, queryMonth : queryMonth, productFamilyTxt : productFamilyTxt, partTypeListTxt : partTypeListTxt,isConsumedPart:isConsumedPart,
		plineListTxt : plineListTxt, regionListTxt : regionListTxt, faultTypeCode : faultTypeCode, faultReasonCode : faultReasonCode, isOver : isOver, faultReasonValid : faultReasonValid}, 
		function(data) {
			if (data.result == 0) {
				if (statisType == '0') {
					setMapData(data.chartsInfo, "维修数");
					showMapCharts("mapChart", data.chartsInfo);
				} else {
					setMapData(data.chartsInfo, "维修率");
					showMapCharts("mapChart", data.chartsInfo);
				}
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
			}
	});
}
</script>
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
		<div class="searchContent dropdownSearchBar">
			<table class="searchContent">
				<tr>
					<th>机型类别：</th>
					<td>
						<select name="productType" onchange="loadMapCondition(0);">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
								<option value="${o.machineType}" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
						</select>
		   			</td>
		   			<th>产品系列：</th>
		   			<td>
		   				<div id="prodFamilyMapList" class="dropdownlist"></div>
		   			</td>
		   			<th>型号：</th>
		   			<td>
		   				<div id="prodTypeMapList" class="dropdownlist"></div>
		   			</td>
		   			<th>故障大类：</th>
		   			<td>
						<input type="hidden" name="faultTypeID" id="MAP_FT_id" size="15" readonly="true" value="${vo.faultTypeID}"/>  
                    	<input type="hidden" name="code" id="MAP_FT_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
                    	<input type="text" name="faultTypeTxt" id="MAP_FT_name" size="15" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    	<a onclick="MAP_FT_Sel(this);" id="btn" class="btnLook btn" href="qms/commonSelect/faultTypeSelect.do?groupName=MAP_FT" width=950 height=500 lookupGroup="MAP_FT">故障大类选择</a>
                    	<a class="btnClear" href="javascript:void(0);" onclick="MAP_FT_clearAll()" title="清空"></a> 
		   			</td>
					<th>维修截止日期：</th>
					<td><input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly" size="8"/></td>                
		   			<th>百台内：</th>
		   			<td>
		   				<select name="isOver" id="mapIsOver">
							<option value="">全选</option>
							<option <c:if test="${vo.isOver=='否'}">selected</c:if> value="否">是</option>
							<option <c:if test="${vo.isOver=='是'}">selected</c:if> value="是">否</option>
						</select>
		   			</td>
		   			<td>
						 <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="getDataSourceByMenuName('市场质量统计分析', '具体分析', '区域维修分析', '地图');">区域维修分析</button></div></div>
					</td>
		   		</tr>
		   		<tr>
		   			<th>工厂：</th>
		   			<td>
		   				<select name="factory" id="factory" onchange="loadMapCondition(1);">
							<option value="">请选择</option>
							<c:forEach items="${factorys}" var="o">
							<option value="${o.factory }" <c:if test="${vo.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
							</c:forEach>
						</select>
		   			</td>
		   			<th>产线：</th>
		   			<td>
		   				<div id="prodLineMapList" class="dropdownlist"></div>
		   			</td>
		   			<th>区域：</th>
		   			<td>
		   				<div id="regionMapList" class="dropdownlist"></div>
		   			</td>
		   			<th>故障小类：</th>
		   			<td>
		   				<input type="hidden" name="faultReasonID" id="MAP_R_id" size="15" readonly="true" value="${vo.faultReasonID}"/>  
	    				<input type="hidden" name="faultReasonCode" id="MAP_R_code" size="15" readonly="true" value="${vo.faultReasonCode}"/>  
	    				<input type="text" name="faultReasonTxt" id="MAP_R_name" size="15" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
	    				<a id="btn" onclick="MAP_R_Sel(this)" class="btnLook btn" href="qms/commonSelect/faultReasonSelect.do?groupName=MAP_R" width=950 height=500 lookupGroup="MAP_R">故障小类选择</a>  
						<a class="btnClear" href="javascript:void(0);" onclick="MAP_R_claerAll()" ></a> 
		   			</td>
    				<td>
	                	<span>排序方式：&nbsp;</span>
    				</td>
    				<td>
    					<select id="statisType" name="statisType">
							<option <c:if test="${vo.statisType==0}">checked="checked"</c:if> value="0">维修数</option>
							<option <c:if test="${vo.statisType==1}">checked="checked"</c:if> value="1">维修率</option>
						</select>
    				</td>
    				<td>故障小类是否有效：</td>
	    			<td>
						<select name="faultReasonValid" id="faultReasonValid" style="float: left;">
							<option value="">全选</option>
							<option <c:if test="${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid=='是'}">selected</c:if> value="是">是</option>
							<option <c:if test="${vo.faultReasonValid=='否'}">selected</c:if> value="否">否</option>
						</select>
	    			</td>
	    			<td>是否消耗配件：</td>
	    			<td>
						<select name="isConsumedPart" id="isConsumedPart">
							<option value="">全选</option>
							<option <c:if test="${vo.isConsumedPart=='是'}">selected</c:if> value="是">是</option>
							<option <c:if test="${vo.isConsumedPart=='否'}">selected</c:if> value="否">否</option>
						</select>
	    			</td>
    				<td>
						 <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadMapChart()">查找</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
	<script type="text/javascript" src="resources/echart/china.js"></script>
<span id="mapListId">
	<script type="text/javascript">
	$(function(){
		$("#prodFamilyMapList").dropdownlist({
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
		
		$("#prodTypeMapList").dropdownlist({  
            id:'partTypeListTxt',
            columns:3,
            selectedtext:'',
            listboxwidth:450,//下拉框宽
            maxchecked:100,
            checkbox:true,
            listboxmaxheight:400,
            width:120,
            requiredvalue:[],
            selected:[${vo.partTypeListTxt}],
            data:${jsonParts},//数据，格式：{value:name}
            onchange:function(text,value){
            }
        });
		
		$("#prodLineMapList").dropdownlist({
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
		
		$("#regionMapList").dropdownlist({
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
	<div id="mapChart" class="singleChartDiv" style="height: 400px"></div>
</div>