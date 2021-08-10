<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	//loadTimeTotalPChart();  
});

function loadTimeTotalPChart() {

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
	var partName = $("#timePartLookup_partName",navTab.getCurrentPanel()).val();
	var partNumber = $("#timePartLookup_partCode",navTab.getCurrentPanel()).val();
	var faultReasonCode = $("#COMC_code", navTab.getCurrentPanel()).val();
	var faultTypeCode  = $("#COMMONB_FT_code", navTab.getCurrentPanel()).val();
	var isConsumedPart = $("#isConsumedPart",navTab.getCurrentPanel()).val();
	var url = "<c:url value='timeMatrixTable/getTimeTotalInfo.do'/>";
	$.post(url, {productType:productType,queryMonth:queryMonth,partName:partName,partNumber:partNumber,faultReasonCode:faultReasonCode,faultTypeCode:faultTypeCode,isConsumedPart:isConsumedPart}, function(data) {
		if (data.result == 0) {
			showChart("timeTotalPChart", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}		
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
                <th>机型类别：</th>
                <td>
					<select name="productType">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td> 			
				<th>维修截至日期：</th>
	          <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
    			</td> 
    			<td>
					物料号：
				</td>
    			<td>
                    <input style="float: left;" type="text" id="timePartLookup_partName" name="partName" value="${param.partName}" size="15"/>
                    <input type="hidden" id="timePartLookup_data" value="">
                     <input type="hidden" name="partNumber" id="timePartLookup_partCode" value="${param.partNumber}">
                    <a id="btn"  class="btnLook btn" href="quality/testInstance/partSelect.do?data=timePartLookup" width=950 height=500 lookupGroup="timePartLookup">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearPart('timePartLookup')"  title="清空"></a> 
                </td>
                <th>故障小类：</th>
    			<td>
    				<input type="hidden" name="faultReasonID" id="COMC_id" value="${vo.faultReasonID}"/>
    				<input type="hidden" name="faultReasonCode" id="COMC_code" value="${vo.faultReasonCode}"/>
    				<input type="text" name="faultReasonTxt" id="COMC_name" size="15" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="faultReasonSel(this, 'COMC')" class="btnLook btn" width=950 height=500 lookupGroup="COMC">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearFault('COMC')" title="清空"></a> 
    			</td>
    			<td style="padding-right:10px;">故障大类：</td>
                <td>
                    <input type="hidden" name="faultTypeID" id="COMMONB_FT_id" size="15" readonly="true" value="${vo.faultTypeID}"/>  
                    <input type="hidden" name="faultTypeCode" id="COMMONB_FT_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="COMMONB_FT_name" size="20" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a onclick="betcomftSel(this);" id="btn" class="btnLook btn" href="qms/commonSelect/faultTypeSelect.do?groupName=COMMONB_FT" width=950 height=500 lookupGroup="COMMONB_FT">故障大类选择</a>
                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('COMMONB_FT')" title="清空"></a> 
                </td>
                <td style="padding-right:10px;">是否消耗配件：</td>
                <td>
                    <select id="isConsumedPart" name="isConsumedPart">
                    	<option value="">全选</option>
                    	<option value="是" <c:if test="${vo.isConsumedPart == '是'}">selected="selected"</c:if>>是</option>
                    	<option value="否" <c:if test="${vo.isConsumedPart == '是'}">selected="selected"</c:if>>否</option>
                    </select>
                </td> 			
				<td>
					<div class="buttonActive">
						<div class="buttonContent"><button type="button" onclick="loadTimeTotalPChart();">查找</button></div>
						<div class="buttonContent"><button type="button" onclick="getDataSourceByMenuName('市场质量统计分析', '具体分析', '生产月P控图分析', 'P控制图');">数据来源</button></div>	
					</div>
				</td>				
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
</div>	<!-- id每个页面要不一样 -->
		<div id="timeTotalPChart" class="singleChartDiv">
</div>