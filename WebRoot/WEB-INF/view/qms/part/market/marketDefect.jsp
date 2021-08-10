<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script>
var title = 1;

function loadMarketDefectChart(index) { //加载图表
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val(); //机型类别
	var queryMonth = $("#queryMonth", navTab.getCurrentPanel()).val(); //截止日期
	var xCount = $("#xCount", navTab.getCurrentPanel()).val(); //X轴数量
	var partNumber = $("#MARKET_DEFECT_PART_partCode",navTab.getCurrentPanel()).val(); //物料编码
	var isConsumed;
	if (index == 6) {
		isConsumed = "1";
	} else {
		isConsumed = $("#isConsumed",navTab.getCurrentPanel()).val(); //是否关键件
	}
    var hasVersion = $("#hasVersion",navTab.getCurrentPanel()).val(); //是否带版本
	var reg = new RegExp("^-?\\d+$");
	if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    }
	if(queryMonth == ""){
		alertMsg.info("请选择维修截至月份");
		return false;
	}
	if(xCount.match(reg)==null){
		alertMsg.info("X轴数量请输入整数!");
        return false;
    }else if(xCount<5){
    	alertMsg.info("X轴数量不能少于5!");
        return false;
    }
	var supplierNumber = $("#MARKET_DEFECT_SUPPLIER_supplierCode", navTab.getCurrentPanel()).val(); //供应商
	var regionListTxt = $("#regionListTxt", navTab.getCurrentPanel()).val(); //区域
	var faultReasonCode = $("#MARKET_DEFECT_R_code", navTab.getCurrentPanel()).val(); //故障小类
    var faultTypeCode  = $("#MARKET_DEFECT_code", navTab.getCurrentPanel()).val(); //故障大类
    var partMaturity = $("#partMaturity",navTab.getCurrentPanel()).val(); //产品成熟度
    var selectDate = $("#selectDate",navTab.getCurrentPanel()).val(); //时间维度
    var sortType = $("#sortType",navTab.getCurrentPanel()).val(); //排序方式
    
    var faultTypeId = $("#MARKET_DEFECT_id", navTab.getCurrentPanel()).val();
    var faultReasonId = $("#MARKET_DEFECT_R_id", navTab.getCurrentPanel()).val();
    var supplierId = $("#MARKET_DEFECT_SUPPLIER_data", navTab.getCurrentPanel()).val();
    var partId = $("#MARKET_DEFECT_PART_data", navTab.getCurrentPanel()).val();
    
    var faultTypeTxt = $("#MARKET_DEFECT_name", navTab.getCurrentPanel()).val();
    var faultReasonTxt = $("#MARKET_DEFECT_R_name", navTab.getCurrentPanel()).val();
    var supplierListTxt = $("#MARKET_DEFECT_SUPPLIER_supplierName", navTab.getCurrentPanel()).val();
    var partDescription = $("#MARKET_DEFECT_PART_partName", navTab.getCurrentPanel()).val();
	var isOver = $("#isOver", navTab.getCurrentPanel()).val();
	var faultReasonValid = $("#faultReasonValid", navTab.getCurrentPanel()).val();
	var partLevel = $("#partLevel", navTab.getCurrentPanel()).val();
	var partTypesListTxt = $("#partTypesListTxt", navTab.getCurrentPanel()).val();
    
	var mesPartNumber = $("#MARKET_DEFECT_MES_PART_partCode",navTab.getCurrentPanel()).val();
	var mesPartDescription = $("#MARKET_DEFECT_MES_PART_partName", navTab.getCurrentPanel()).val();
	var mesPartId = $("#MARKET_DEFECT_MES_PART_data",navTab.getCurrentPanel()).val();
	
    var url = "<c:url value='quality/marketPart/marketDefect.do'/>";
    var jsonData = {
   		productType:productType,queryMonth:queryMonth, selectDate:selectDate,title:index,
   		xCount:(xCount-1),supplierNumber:supplierNumber,mesPartNumber:mesPartNumber,mesPartDescription:mesPartDescription,mesPartId:mesPartId,
   		faultReasonCode:faultReasonCode,faultTypeCode:faultTypeCode,partNumber:partNumber, 
	    regionListTxt:regionListTxt, isConsumed:isConsumed,partMaturity:partMaturity,isOver:isOver,
	    hasVersion:hasVersion,sortType:sortType,faultTypeID:faultTypeId,faultReasonID:faultReasonId,
	    supplierId:supplierId,partId:partId,faultTypeTxt:faultTypeTxt,faultReasonTxt:faultReasonTxt,
	    supplierListTxt:supplierListTxt,partDescription:partDescription,faultReasonValid:faultReasonValid,
	    partLevel:partLevel,partTypesListTxt:partTypesListTxt
	};
	$.post(url,jsonData, function(data) {
		jinitHeight("");
		if (data.result == 0) {
			title = index;
			setMarketDefectData(index,isConsumed,selectDate,data.chartsInfo);
			showMarketDefectCharts("marketDefectTdChart", data.chartsInfo, index, isConsumed);
		} else {
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function exportExcel() {
	if (title < 0) {
		alertMsg.info("导出数据时，请先查询要导出的数据");
		return ;
	}
	var myForm = document.createElement("form");
	myForm.action= "<c:url value='quality/marketPart/exportExcel.do'/>";
	myForm.method="post"; 
	myForm.target="noexistForm";
	var objs = $("#marketDefectForm input",navTab.getCurrentPanel());
	var objs_select = $("#marketDefectForm select",navTab.getCurrentPanel());
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
	myInput.setAttribute("name", "title");
	myInput.setAttribute("value", title);
	myForm.appendChild(myInput);
	document.body.appendChild(myForm);
	myForm.submit();
}
</script>
<div class="pageHeader" style="position:static">
<form id="marketDefectForm" onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
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
				<th>供应商：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_SUPPLIER_data" name="supplierId" size="10" readonly="true" style="float: left;" value="${vo.supplierId}"/>
					<input type="hidden" id="MARKET_DEFECT_SUPPLIER_supplierCode" name="supplierNumber" size="10" readonly="true" style="float: left;" value="${vo.supplierNumber}"/>
                    <input type="text" id="MARKET_DEFECT_SUPPLIER_supplierName" name="supplierListTxt" size="10" readonly="true" style="float: left;" value="${vo.supplierListTxt}"/>
                    <a id="btn" class="btnLook btn" onclick="supplierSel(this, 'MARKET_DEFECT_SUPPLIER')" width=950 height=500 lookupGroup="MARKET_DEFECT_SUPPLIER">供应商选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearSupplier('MARKET_DEFECT_SUPPLIER')" title="清空"></a>
				</td>
				<th>故障大类：</th>
				<td>
					<input type="hidden" name="faultTypeID" id="MARKET_DEFECT_id" readonly="true" value="${vo.faultTypeID}"/>  
                    <input type="hidden" name="faultTypeCode" id="MARKET_DEFECT_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="MARKET_DEFECT_name" size="10" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a id="btn" onclick="faultTypeSel(this, 'MARKET_DEFECT')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT">故障大类选择</a>
                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('MARKET_DEFECT');" title="清空"></a> 
				</td>
				<th>故障小类是否有效：</th>
				<td>
					<select id="faultReasonValid" name="faultReasonValid" style="width : 78px">
						<option value="">全选</option>
						<option value="是" ${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid eq '是' ? 'selected':''}>是</option>
						<option value="否" ${vo.faultReasonValid eq '否' ? 'selected':''}>否</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>产品成熟度：</th>
				<td>
					<select id="partMaturity" name="partMaturity" style="width: 78px">
						<option value="">全部</option>
						<option value="老品" ${vo.partMaturity eq '老品' ? 'selected':''}>老品</option>
						<option value="新品" ${vo.partMaturity eq '新品' ? 'selected':''}>新品</option>
						<option value="小批量" ${vo.partMaturity eq '小批量' ? 'selected':''}>小批量 </option>
					</select>
				</td>
				<th>区域：</th>
				<td>
					<div id="regionMarketList" class="dropdownlist"></div>
				</td>
				<th>故障小类：</th>
				<td>
					<input type="hidden" name="faultReasonID" id="MARKET_DEFECT_R_id"  value="${vo.faultReasonID}"/>  
    				<input type="hidden" name="faultReasonCode" id="MARKET_DEFECT_R_code" value="${vo.faultReasonCode}"/>  
    				<input type="text" name="faultReasonTxt" id="MARKET_DEFECT_R_name" size="10" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="faultReasonSel(this, 'MARKET_DEFECT_R')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_R">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearFault('MARKET_DEFECT_R')"  title="清空"></a> 
				</td>
				<th>物料级别：</th>
				<td>
					<select id="partLevel" name="partLevel" style="width : 78px">
						<option value="">全选</option>
						<option value="'A'" ${vo.partLevel eq "'A'" ? 'selected':''}>A</option>
						<option value="'B'" ${vo.partLevel eq "'B'" ? 'selected':''}>B</option>
						<option value="'C'" ${vo.partLevel eq "'C'" ? 'selected':''}>C</option>
						<option value="'A','B'" ${vo.partLevel eq "'A','B'" ? 'selected' : ''}>AB</option>
						<option value="'A','C'" ${vo.partLevel eq "'A','C'" ? 'selected' : ''}>AC</option>
						<option value="'B','C'" ${vo.partLevel eq "'B','C'" ? 'selected' : ''}>BC</option>
						<option value="'A','B','C'" ${vo.partLevel eq "'A','B','C'" ? 'selected' : ''}>ABC</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>管控类型：</th>
				<td>
					<select id="isConsumed" name="isConsumed" style="width : 78px">
						<option value="">全选</option>
						<option value="0" ${vo.isConsumed eq '0' ? 'selected':''}>关键件</option>
						<option value="1" ${vo.isConsumed eq '1' ? 'selected':''}>非关键件</option>
						<option value="2" ${vo.isConsumed eq '2' ? 'selected':''}>附件</option>
					</select>
				</td>
				<th>时间维度:</th>
				<td>
					<select id="selectDate" name="selectDate">
                       <option value="year" ${vo.selectDate eq 'year' ? 'selected':''}>年</option>
                       <option value="month" ${vo.selectDate eq 'month' ? 'selected':''}>月</option>
                     </select>
                     <input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth}" readonly="readonly" size="8"/>
				</td>
				<th>CRM物料：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_PART_data" name="partId" size="10" value="${vo.partId}"/>
					<input type="hidden" id="MARKET_DEFECT_PART_id" name="partName" size="10" value="${vo.partName}"/>
                    <input type="hidden" id="MARKET_DEFECT_PART_partCode" name="partNumber" value="${vo.partNumber}" />
                    <input type="text" id="MARKET_DEFECT_PART_partName" name="partDescription" size="10" readonly="true" style="float: left;" value="${vo.partDescription}"/>
                    <a id="btn" onclick="partSel(this, 'MARKET_DEFECT_PART')" class="btnLook btn" href="quality/testInstance/partSelect.do?data=MARKET_DEFECT_PART" width=950 height=500 lookupGroup="MARKET_DEFECT_PART">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearPart('MARKET_DEFECT_PART')" title="清空"></a>
				</td>
				<th>物料类别：</th>
				<td>
					<div id="partTypesList" class="dropdownlist"></div>
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
				<th>排序方式：</th>
				<td>
					<select id="sortType">
						<option value="不良数降序" ${vo.sortType eq '不良数降序' ? 'selected':''}>不良数降序</option>
						<option value="不良率降序" ${vo.sortType eq '不良率降序' ? 'selected':''}>不良率降序</option>
					</select>
				</td>
				<th>MES物料：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_MES_PART_data" name="mesPartId" size="10" value="${vo.mesPartId}"/>
					<input type="hidden" id="MARKET_DEFECT_MES_PART_id" name="mesPartName" size="10" value="${vo.mesPartName}"/>
                    <input type="hidden" id="MARKET_DEFECT_MES_PART_partCode" name="mesPartNumber" value="${vo.mesPartNumber}" />
                    <input type="text" id="MARKET_DEFECT_MES_PART_partName" name="mesPartDescription" size="10" readonly="true" style="float: left;" value="${vo.mesPartDescription}"/>
                    <a id="btn" onclick="partSel(this, 'MARKET_DEFECT_MES_PART')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_MES_PART">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearPart('MARKET_DEFECT_MES_PART')" title="清空"></a>
				</td>
				<th>排列图数量：</th>
				<td>
					<input type="text" size="2" name="xCount" id="xCount" value="${vo.xCount+1 }" />
					<span>百台内：</span>
					<select name="isOver" id="isOver">
						<option value="">全选</option>
						<option value="否" ${vo.isOver eq '否' ? 'selected':''}>是</option>
						<option value="是" ${vo.isOver eq '是' ? 'selected':''}>否</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="10">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadMarketDefectChart(1)">供应商市场不良</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadMarketDefectChart(2)">零部件市场不良</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadMarketDefectChart(3)">区域市场不良</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadMarketDefectChart(4)">故障大类市场不良</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadMarketDefectChart(5)">故障小类市场不良</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadMarketDefectChart(6)">机型与物料级别不良</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadMarketDefectChart(15)">物料类型市场不良</button></div></div>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="exportExcel()">导出</button></div></div>
				</td>
			</tr>
		</table>
	</div>
</form>
</div>
<script type="text/javascript">
	$(function(){
		loadRegionData("#regionMarketList", "regionListTxt", [${vo.regionListTxt}], ${jsonRegions});
		debugger;
		loadProductTypeData("#partTypesList", "partTypesListTxt", [], ${partMap});
	});
</script>
<div class="pageContent" overflow="auto">
	<div id="marketDefectTdChart" class="singleChartDiv"></div>
</div>