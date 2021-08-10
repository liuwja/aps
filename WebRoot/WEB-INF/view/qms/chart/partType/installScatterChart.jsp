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
function loadInstallScatterChart(type) {
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
	var insStartTime = $("#insStartTime", navTab.getCurrentPanel()).val();
	var insEndTime = $("#insEndTime", navTab.getCurrentPanel()).val();
	if (!isNotEmpty(productType) || !isNotEmpty(insStartTime) || !isNotEmpty(insEndTime)) {
		alertMsg.info("请输入机型类别及安装时间");
		return false;
	}
	var productFamilyTxt = $("#productFamilyTxt", navTab.getCurrentPanel()).val(); //产线
	var partTypeListTxt = $("#partTypeListTxt", navTab.getCurrentPanel()).val(); //选择型号
	var regionListTxt = $("#regionListTxt", navTab.getCurrentPanel()).val(); //选择区域
	var faultReasonCode = $("#ISCAT_FR_code", navTab.getCurrentPanel()).val();
	var faultTypeCode  = $("#ISCAT_FT_code", navTab.getCurrentPanel()).val();
	var insStartTime = $("#insStartTime", navTab.getCurrentPanel()).val();
	var insEndTime = $("#insEndTime", navTab.getCurrentPanel()).val();
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();
	var isOver = $("#isOver", navTab.getCurrentPanel()).val();
	var faultReasonValid = $("#faultReasonValid", navTab.getCurrentPanel()).val();
	var statisData = $("#statisData", navTab.getCurrentPanel()).val();
	var isConsumedPart = $("#isConsumedPart",navTab.getCurrentPanel()).val();//是否消耗配件
	var url = "";
	if (type == 0) {
		url = "<c:url value='scatterChart/getPartTypeInfo.do'/>";
	} else if (type == 1) {
		url = "<c:url value='scatterChart/getRegionInfo.do'/>";
	} else {
		alertMsg.error("查询出错，请联系管理员");
		return false;
	}
	$.post(url, {productType:productType, productFamilyTxt:productFamilyTxt, insStartTime:insStartTime, insEndTime:insEndTime,
		faultReasonCode:faultReasonCode, faultTypeCode:faultTypeCode, startTime:startTime, endTime:endTime, isOver: isOver, faultReasonValid: faultReasonValid,
		partTypeListTxt:partTypeListTxt, regionListTxt:regionListTxt, statisData:statisData,isConsumedPart:isConsumedPart}, function(data) {
			if (data.result == 0) {
				setScatterData(data.chartsInfo, statisData);
				showScatterChart("installScatterChart", data.chartsInfo, statisData);
			} else {
				alertMsg.error("查询出错，请联系管理员");
				return ;
			}
		});
}
</script>
<div class="pageHeader" style="position:static">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post" id="installScatterForm">
		<div class="searchBar" style="">
			<table class="searchContent">
				<tr>
					<th>机型类别：</th>
					<td>
						<select name="productType" onchange="loadProductData('#porductFamilyInstallScatterList', '#partTypeInstallScatterList');">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
								<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
						</select>
		   			</td>
		   			<th>产品系列：</th>          
					<td>
						<div id="porductFamilyInstallScatterList" class="dropdownlist"></div>
					</td>
					<th>故障小类：</th>
    				<td>
	    				<input type="hidden" name="faultReasonID" id="ISCAT_FR_id" value="${vo.faultReasonID}"/>
	    				<input type="hidden" name="faultReasonCode" id="ISCAT_FR_code" value="${vo.faultReasonCode}"/>
	    				<input type="text" name="faultReasonTxt" id="ISCAT_FR_name" size="15" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
	    				<a id="btn" onclick="faultReasonSel(this, 'ISCAT_FR')" class="btnLook btn" href="qms/commonSelect/faultReasonSelect.do?groupName=ISCAT_FR" width=950 height=500 lookupGroup="ISCAT_FR">故障小类选择</a>  
						<a class="btnClear" href="javascript:void(0);" onclick="clearFault('ISCAT_FR')" title="清空"></a> 
    				</td>
    				<th>维修日期：</th>
					<td>
						<input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime}" readonly="readonly" size="8"/>
						至
						<input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime}" readonly="readonly" size="8"/>
					</td>
					<th>百台内：</th>
					<td>
	                	<select name="isOver" id="isOver">
							<option value="">全选</option>
							<option <c:if test="${vo.isOver=='否'}">selected</c:if> value="否">是</option>
							<option <c:if test="${vo.isOver=='是'}">selected</c:if> value="是">否</option>
						</select>
					</td>
					<th>统计数据：</th>
	                <td>
						<select id="statisData" name="statisData">
							<option value="repairCount" selected="selected">维修数</option> 	
	                    	<option value="repairRate" <c:if test="${vo.statisData=='repairRate'}">selected="selected"</c:if> >维修率</option>
						</select>
	                </td>
    			</tr>
				<tr>
					<th>区域：</th>                       
					<td>
						<div id="regionInstallScatterList" class="dropdownlist"></div>
					</td>
					<th>型号：</th>                       
					<td>
						<div id="partTypeInstallScatterList" class="dropdownlist"></div>
					</td>  			
					<th>故障大类：</th>
	                <td>
	                    <input type="hidden" name="faultTypeID" id="ISCAT_FT_id" size="15" readonly="true" value="${vo.faultTypeID}"/>  
	                    <input type="hidden" name="faultTypeCode" id="ISCAT_FT_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
	                    <input type="text" name="faultTypeTxt" id="ISCAT_FT_name" size="15" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
	                    <a onclick="faultTypeSel(this, 'ISCAT_FT');" id="btn" class="btnLook btn" href="qms/commonSelect/faultTypeSelect.do?groupName=ISCAT_FT" width=950 height=500 lookupGroup="ISCAT_FT">故障大类选择</a>
	                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('ISCAT_FT')" title="清空"></a> 
	                </td>
	                <th>安装日期：</th>
					<td>
						<input type="text" id="insStartTime" name="insStartTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.insStartTime}" readonly="readonly" size="8"/>
						至
						<input type="text" id="insEndTime" name="insEndTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.insEndTime}" readonly="readonly" size="8"/>
					</td>
					<th>故障小类是否有效：</th>
					<td>
						<select name="faultReasonValid" id="faultReasonValid">
							<option value="">全选</option>
							<option value="是" ${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid eq '是' ? 'selected':''} value="是">是</option>
							<option value="否" ${vo.faultReasonValid eq '否' ? 'selected':''} value="否">否</option>
						</select>
					</td>
					<th>是否消耗配件：</th>
					<td>
						<select name="isConsumedPart" id="isConsumedPart">
							<option value="">全选</option>
							<option value="是" ${vo.isConsumedPart eq '否' ? 'selected':''}>是</option>
							<option value="否" ${vo.isConsumedPart eq '否' ? 'selected':''}>否</option>
						</select>
					</td>
				</tr>
				<tr>
	               <td colspan="10">
						<div class="panelBar">
							<ul class="toolBar">
								<li><a href="javascript:void(0);" onclick="loadInstallScatterChart(0)">型号散点图</a></li>
								<li><a href="javascript:void(0);" onclick="loadInstallScatterChart(1)">区域散点图</a></li>
								<li><a href="javascript:void(0);" onclick="exportExcelByCommon('scatterChart/execlOutputByPartType.do', '#installScatterForm')">Execl导出-型号</a></li>
								<li><a href="javascript:void(0);" onclick="exportExcelByCommon('scatterChart/execlOutputByRegion.do', '#installScatterForm')">Execl导出-区域</a></li>
								<li><a href="javascript:void(0);" onclick="getDataSourceByMenuName('市场质量统计分析', '具体分析', '安装四象限分析', '象限图')">数据来源</a></li>
							</ul>
						</div>
	               </td>               			
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div id="installScatterChart" class="singleChartDiv" style="height: 450px"></div>
</div>
<script type="text/javascript">
	$(function(){
		loadProductFamilyData("#porductFamilyInstallScatterList", "productFamilyTxt", [${vo.productFamilyTxt}], ${jsonProFamily});
   		loadProductTypeData("#partTypeInstallScatterList", "partTypeListTxt", [${vo.partTypeListTxt}], ${jsonParts});
   		loadRegionData("#regionInstallScatterList", "regionListTxt", [${vo.regionListTxt}], ${jsonRegions});
	});
</script>