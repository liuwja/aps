<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script>
function loadMarketDefectTrendChart(index, obj) {
	$("#title", navTab.getCurrentPanel()).val(index);
	$("#titleContext", navTab.getCurrentPanel()).val($(obj).html());
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val(); //机型类别
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val(); //截止日期
	var xCount = $("#xCount", navTab.getCurrentPanel()).val(); //X轴数量
	var reg = new RegExp("^-?\\d+$");
	//【机型类别】设置为非必选项    liuwjg			2019年8月2日13:59:10
/* 	if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    } */
	if(queryMonth == ""){
		alertMsg.info("请选择维修截至月份");
		return false;
	}
	if(xCount.match(reg) == null){
		alertMsg.info("X轴数量请输入整数!");
        return false;
    } else if(xCount < 5){
    	alertMsg.info("X轴数量不能少于5!");
        return false;
    }
	if(index == 23 || index == 24) {
		/* var partNumber = $("input[name='partNumber']:last", navTab.getCurrentPanel()).val();
		var mesPartNumber = $("input[name='mesPartNumber']:last", navTab.getCurrentPanel()).val();
		var mesPartCategoryTwoName = $("input[name='mesPartCategoryTwoName']:last", navTab.getCurrentPanel()).val();
		var crmPartCategoryTwoName = $("input[name='crmPartCategoryTwoName']:last", navTab.getCurrentPanel()).val();
		var f = false;
		
		if(partNumber != null && partNumber != "undefined" && partNumber != "") {		
			f= true;
		}
		if(mesPartNumber != null && mesPartNumber != "undefined" && mesPartNumber != "") {			
			f= true;
		}
		if(mesPartCategoryTwoName != null && mesPartCategoryTwoName != "undefined" && mesPartCategoryTwoName != "") {			
			f=true;
		}
		if(crmPartCategoryTwoName != null && crmPartCategoryTwoName != "undefined" && crmPartCategoryTwoName != "") {			
			f=true;
		}
		if(!f){
			alertMsg.info("查询产品-物料时，需要输入物料");
			return false;
		} */
	} else if(index == 25 || index == 26) {
		var supplierNumber = $("input[name='supplierNumber']:last", navTab.getCurrentPanel()).val();
		if(supplierNumber == null || supplierNumber == "undefined" || supplierNumber == "") {
			alertMsg.info("查询产品-供应商-关键物料时，需要输入供应商");
			return false;
		}
	}
	var formData = decodeURIComponent($("#marketDefectTrendNewForm", navTab.getCurrentPanel()).serialize(), true);
	var url = "<c:url value='quality/marketPart/marketDefectTrendNew.do'/>";
	$.post(url, formData, function(data) {
		jinitHeight("");
		if (data.result == 0) {
			showMarketDefectTrendChartsNew("marketDefectTrendNewChart", data.chartsInfo);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}
</script>
<!--  <style>
          table {
            cellspacing:0 ;
            *border-collapse: collapse; /* IE7 and lower */
            border-spacing: 0;
            width: 100%;
        }
        .bordered tr:hover {
            background: #fbf8e9;
            -o-transition: all 0.1s ease-in-out;
            -webkit-transition: all 0.1s ease-in-out;
            -moz-transition: all 0.1s ease-in-out;
            -ms-transition: all 0.1s ease-in-out;
            transition: all 0.1s ease-in-out;
        }
        .bordered th {
            padding: 7px;
            text-align: center;
            cellspacing:0;
        }
        .bordered td{
            padding: 7px;
            text-align: center;
            cellspacing:0;
        }
        .bordered th {
             background-image: -webkit-gradient(linear, left top, left bottom, from(#ebf3fc), to(#dce9f9));
             background-image: -webkit-linear-gradient(top, #ebf3fc, #dce9f9);
             background-image:    -moz-linear-gradient(top, #ebf3fc, #dce9f9);
             background-image:     -ms-linear-gradient(top, #ebf3fc, #dce9f9);
             background-image:      -o-linear-gradient(top, #ebf3fc, #dce9f9);
             background-image:         linear-gradient(top, #ebf3fc, #dce9f9);
        }
        .bordered td:first-child, .bordered th:first-child {
            border-left: none;
        }
        .bordered  tr:nth-of-type(2n){background:#FFFFFF;cursor: pointer;}
        .bordered  tr:nth-of-type(2n+1){background:#F7FAFC;cursor: pointer;}
        .bordered  tbody tr:hover{  background: #fbf8e9;
            -o-transition: all 0.1s ease-in-out;
            -webkit-transition: all 0.1s ease-in-out;
            -moz-transition: all 0.1s ease-in-out;
            -ms-transition: all 0.1s ease-in-out;
            transition: all 0.1s ease-in-out;
        } 
    </style> -->

<div class="pageHeader" style="position:static">
<form id="marketDefectTrendNewForm" onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
	<input type="hidden" id="title" name="title">
	<input type="hidden" id="titleContext" name="titleContext">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<th>机型类别：</th>
				<td>
					<select name="productType" required="required" onchange="loadProductData('#markFamilyTmList', '#markpartTypeList');">
						<option value="">请选择</option>
						<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType}" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
						</c:forEach>
					</select>
				</td>
				<th>产品系列：</th>
                <td>
                	<div id="markFamilyTmList" class="dropdownlist"></div>
                </td>	
				<th>供应商：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_TREND_NEW_SUPPLIER_data" name="supplierId" readonly="true" style="float: left;" value="${vo.supplierId}"/>
					<input type="hidden" id="MARKET_DEFECT_TREND_NEW_SUPPLIER_supplierCode" name="supplierNumber" readonly="true" style="float: left;" value="${vo.supplierNumber}"/>
                    <input type="text" id="MARKET_DEFECT_TREND_NEW_SUPPLIER_supplierName" name="supplierListTxt" readonly="true" style="float: left;" value="${vo.supplierListTxt}"/>
                    <a id="btn" class="btnLook btn" onclick="supplierSel(this, 'MARKET_DEFECT_TREND_NEW_SUPPLIER')" lookupGroup="MARKET_DEFECT_TREND_NEW_SUPPLIER">供应商选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearSupplier('MARKET_DEFECT_TREND_NEW_SUPPLIER')" title="清空"></a>
				</td>
				<th>故障大类：</th>
				<td>
					<input type="hidden" name="faultTypeID" id="MARKET_DEFECT_TREND_NEW_id" readonly="true" value="${vo.faultTypeID}"/>  
                    <input type="hidden" name="faultTypeCode" id="MARKET_DEFECT_TREND_NEW_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="MARKET_DEFECT_TREND_NEW_name" size="10" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a id="btn" onclick="faultTypeSel(this, 'MARKET_DEFECT_TREND_NEW')" class="btnLook btn" lookupGroup="MARKET_DEFECT_TREND_NEW">故障大类选择</a>
                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('MARKET_DEFECT_TREND_NEW');" title="清空"></a> 
				</td>
				<th>故障小类是否有效：</th>
				<td>
					<select id="faultReasonValid" name="faultReasonValid">
						<option value="">全选</option>
						<option value="是" ${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid eq '是' ? 'selected':''}>是</option>
						<option value="否" ${vo.faultReasonValid eq '否' ? 'selected':''}>否</option>
					</select>
				</td>
				<!-- <td>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="exportExcelByCommon('quality/marketPart/excelOutput_marketDefectTrendNew.do', '#marketDefectTrendNewForm');">导出</button></div></div>
				</td> -->
			</tr>
			<tr>
				<th>管控类型：</th>
				<td>
					<select id="isConsumed" name="isConsumed" style="width : 78px">
						<option value="-1">全选</option>
						<option value="0" ${vo.isConsumed eq '0' ? 'selected':''}>关键件</option>
						<option value="1" ${vo.isConsumed eq '1' ? 'selected':''}>非关键件</option>
						<option value="2" ${vo.isConsumed eq '2' ? 'selected':''}>附件</option>
					</select>
				</td>
				<th>型号：</th>
				<td>
					<div id="markpartTypeList" class="dropdownlist"></div>
				</td>
				<th>区域：</th>
				<td>
					<div id="regionMarketTrendNewList" class="dropdownlist"></div>
				</td>
				<th>故障小类：</th>
				<td>
					<input type="hidden" name="faultReasonID" id="MARKET_DEFECT_TREND_NEW_R_id"  value="${vo.faultReasonID}"/>  
    				<input type="hidden" name="faultReasonCode" id="MARKET_DEFECT_TREND_NEW_R_code" value="${vo.faultReasonCode}"/>  
    				<input type="text"   name="faultReasonTxt" id="MARKET_DEFECT_TREND_NEW_R_name" size="10" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="faultReasonSel(this, 'MARKET_DEFECT_TREND_NEW_R')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_TREND_NEW_R">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearFault('MARKET_DEFECT_TREND_NEW_R')"  title="清空"></a> 
				</td>
				<th>维修率：</th>
				<td>
					<select name='isPorB'>
						<option value="0">%</option>
						<option value="1">ppm</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>是否带版本：</th>
				<td>
					<select id="hasVersion" name="hasVersion" style="width : 78px">
						<option value="2" ${vo.hasVersion eq '2' ? 'selected':''}>否</option>
						<option value="1" ${vo.hasVersion eq '1' ? 'selected':''}>是</option>
					</select>
				</td>
				<th>时间：</th>
				<td>
                	<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth}" readonly="readonly" required="required" size="8"/>
				</td>
				<th>CRM物料：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_TREND_NEW_PART_data" name="partId" value="${vo.partId}" size="10"/>
					<input type="hidden" id="MARKET_DEFECT_TREND_NEW_PART_id" name="partName" value="${vo.partName}" size="10"/>
                    <input type="hidden" id="MARKET_DEFECT_TREND_NEW_PART_partCode" name="partNumber" value="${vo.partNumber}" />
                    <input type="text" id="MARKET_DEFECT_TREND_NEW_PART_partName" name="partDescription" size="10" readonly="true" value="${vo.partDescription}" style="float: left;"/>
                    <a id="btn" onclick="partSel(this, 'MARKET_DEFECT_TREND_NEW_PART')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_TREND_NEW_PART">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearPart('MARKET_DEFECT_TREND_NEW_PART')" title="清空"></a>
				</td>
				<th>CRM物料分类:</th>
				<td> 
					<input type="hidden" id="CRM_CATEGORY_id" name="crmPartCategoryId" value="${vo.crmPartCategoryId}" size="10"/>
					<input type="hidden" id="CRM_CATEGORY_supplierCode" name="crmPartCategoryName" value="${vo.crmPartCategoryName}" size="10"/><!-- 一级分类 -->
                    <input type="text" id="CRM_CATEGORY_supplierName" name="crmPartCategoryTwoName" size="10" readonly="true"  value ="${vo.crmPartCategoryTwoName }" style="float: left;"/><!-- 二级分类 -->
                    <a id="btn" onclick="categorySel(this, 'CRM_CATEGORY')" class="btnLook btn" width=950 height=500 lookupGroup="CRM_CATEGORY">CRM物料分类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearCategory('CRM_CATEGORY')" title="清空"></a>
				</td>
			</tr>
			<tr>
				<th>百台内：</th>
				<td>
					<select name="isOver" id="isOver">
						<option value="">全选</option>
						<option value="否" ${vo.isOver eq '否' ? 'selected':''}>是</option>
						<option value="是" ${vo.isOver eq '是' ? 'selected':''}>否</option>
					</select>
				</td>
				<th>排列图数量：</th>
				<td>
					<input type="text" size="2" name="xCount" id="xCount" value="${vo.xCount}" required="required" />
				</td>
				<th>MES物料：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_TREND_NEW_MES_PART_data" name="mesPartId" size="10" value="${vo.mesPartId}"/>
					<input type="hidden" id="MARKET_DEFECT_TREND_NEW_MES_PART_id" name="mesPartName" size="10" value="${vo.mesPartName}"/>
                    <input type="hidden" id="MARKET_DEFECT_TREND_NEW_MES_PART_partCode" name="mesPartNumber" value="${vo.mesPartNumber}" />
                    <input type="text" id="MARKET_DEFECT_TREND_NEW_MES_PART_partName" name="mesPartDescription" size="10" readonly="true" style="float: left;" value="${vo.mesPartDescription}"/>
                    <a id="btn" onclick="partSel(this, 'MARKET_DEFECT_TREND_NEW_MES_PART')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_TREND_NEW_MES_PART">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearPart('MARKET_DEFECT_TREND_NEW_MES_PART')" title="清空"></a>
				</td>
				<th>MES物料分类:</th>
				<td> 
					<input type="hidden" id="MES_CATEGORY_id" name="mesPartCategoryId" value="${vo.mesPartCategoryId}" size="10"/>
					<input type="hidden" id="MES_CATEGORY_supplierCode" name="mesPartCategoryName" value="${vo.mesPartCategoryName}" size="10"/><!-- 一级分类 -->
                    <input type="text" id="MES_CATEGORY_supplierName" name="mesPartCategoryTwoName" size="10" readonly="true"  value ="${vo.mesPartCategoryTwoName }" style="float: left;"/><!-- 二级分类 -->
                    <a id="btn" onclick="categorySel(this, 'MES_PART_CATEGORY')" class="btnLook btn" width=950 height=500 lookupGroup="MES_CATEGORY">mes物料分类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearCategory('MES_CATEGORY')" title="清空"></a>
				</td>
			</tr>
			<tr>
				<td colspan="12">
					
					<button type="button" onclick="loadMarketDefectTrendChart(21, this)" title="分子:当月成品维修数   分母:前推三个月,累计12个月平均发货数">产品-百台维修率单月趋势图</button>
					<button type="button" onclick="loadMarketDefectTrendChart(22, this)" title="分子:累计12个月平均维修数    分母:前推三个月累计12个月平均发货数">产品-百台维修率累计趋势图</button>
					
					<button type="button" onclick="loadMarketDefectTrendChart(23, this)" title="分子:单月维修产品更换物料数   分母:前推三个月,累计12个月平均发货数">产品-物料-百台维修率单月趋势图</button>
					<button type="button" onclick="loadMarketDefectTrendChart(24, this)" title="分子:累计12个月维修产品更换物料平均数   分母:前推三个月,累计12个月平均发货数">产品-物料-百台维修率累计趋势图</button>
					 
					<button type="button" onclick="loadMarketDefectTrendChart(25, this)" title="分子:单月维修产品更换的对应供应商的维修数   分母:前推三个月,累计12个月平均扫码入库数">产品-供应商-关键物料-百台维修率单月趋势图</button>
					<button type="button" onclick="loadMarketDefectTrendChart(26, this)" title="分子:累计12个月维修产品更换对应供应商的平均维修数   分母:前推三个月,累计12个月平均扫码入库数">产品-供应商-关键物料-百台维修率累计趋势图</button>
				</td>
			</tr>
		</table>
	</div>
</form>
</div>
<span id="marketDefectTrendTd">
	<script type="text/javascript">
		$(function(){
			loadRegionData("#regionMarketTrendNewList", "regionListTxt", [${o.regionListTxt}], ${jsonRegions});
			loadProductFamilyData("#markFamilyTmList", "productFamilyTxt", [${o.productFamilyTxt}], ${jsonProFamily});
    	loadProductTypeData("#markpartTypeList", "partTypeListTxt", [${o.partTypeListTxt}], ${jsonParts});	
		});
	</script>
</span>
<div class="pageContent">
	<div id="marketDefectTrendNewChart" class="singleChartDiv"></div>
</div>