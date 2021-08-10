<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script>
function checkMarketDefectData() {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val(); //机型类别
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();
/* 	if (productType==""){
        alertMsg.info("请选择机型类别");
        return false;
    } */
	if (startTime == "" || endTime == "") {
		alertMsg.info("请选择时间");
        return false;
	}
	$("#marketPartDataFrom", navTab.getCurrentPanel()).submit();
}

function exportExcelByDefectData() {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val(); //机型类别
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();
	if (productType==""){
        alertMsg.info("请选择机型类别");
        return false;
    }
	if (startTime == "" || endTime == "") {
		alertMsg.info("请选择时间");
        return false;
	}
	var myForm = document.createElement("form");
	myForm.action= "<c:url value='quality/marketPart/exportExcel.do'/>";
	myForm.method="post"; 
	myForm.target="noexistForm";
	var objs = $("#marketPartDataFrom input",navTab.getCurrentPanel());
	var objs_select = $("#marketPartDataFrom select",navTab.getCurrentPanel());
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
	myInput.setAttribute("value", "12");
	myForm.appendChild(myInput);
	document.body.appendChild(myForm);
	myForm.submit();
}
</script>
<div class="pageHeader" style="position:static">
<form onsubmit="return navTabSearch(this);" rel="pagerForm" id="marketPartDataFrom" action="quality/marketPart/marketPartData.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<th>机型类别：</th>
				<td><!-- onchange="loadProductData('#porductFamilyInstallScatterList', '#partTypeInstallScatterList');" -->
					<select name="productType" onchange="loadProductData('#porductFamilyInstallScatterList', '#partTypeInstallScatterList');">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
					</select>
				</td>
				<th>供应商：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_DATA_SUPPLIER_data" name="supplierId" size="10" readonly="true" style="float: left;" value="${vo.supplierId}"/>
					<input type="hidden" id="MARKET_DEFECT_DATA_SUPPLIER_supplierCode" name="supplierNumber" size="10" readonly="true" style="float: left;" value="${vo.supplierNumber}"/>
                    <input type="text" id="MARKET_DEFECT_DATA_SUPPLIER_supplierName" name="supplierListTxt" size="10" readonly="true" style="float: left;" value="${vo.supplierListTxt}"/>
                    <a id="btn" class="btnLook btn" onclick="supplierSel(this, 'MARKET_DEFECT_DATA_SUPPLIER')" width=950 height=500 lookupGroup="MARKET_DEFECT_DATA_SUPPLIER">供应商选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearSupplier('MARKET_DEFECT_DATA_SUPPLIER')" title="清空"></a>
				</td>
				<th>故障大类：</th>
				<td>
					<input type="hidden" name="faultTypeID" id="MARKET_DEFECT_DATA_id" readonly="true" value="${vo.faultTypeID}"/>  
                    <input type="hidden" name="faultTypeCode" id="MARKET_DEFECT_DATA_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="MARKET_DEFECT_DATA_name" size="10" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a id="btn" onclick="faultTypeSel(this, 'MARKET_DEFECT_DATA')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_DATA">故障大类选择</a>
                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('MARKET_DEFECT_DATA');" title="清空"></a> 
				</td>
				<th>故障小类是否有效：</th>
				<td>
					<select id="faultReasonValid" name="faultReasonValid">
						<option value="">全选</option>
						<option value="是" ${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid eq '是' ? 'selected':''}>是</option>
						<option value="否" ${vo.faultReasonValid eq '否' ? 'selected':''}>否</option>
					</select>
				</td>
				<th>主机条码：</th>
				<td><input type="text" id = "serialNumber" name="serialNumber" value="${vo.serialNumber}"></td>
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
					<div id="regionMarketDataList" class="dropdownlist"></div>
				</td>
				<th>故障小类：</th>
				<td>
					<input type="hidden" name="faultReasonID" id="MARKET_DEFECT_DATA_R_id"  value="${vo.faultReasonID}"/>  
    				<input type="hidden" name="faultReasonCode" id="MARKET_DEFECT_DATA_R_code" value="${vo.faultReasonCode}"/>  
    				<input type="text"   name="faultReasonTxt" id="MARKET_DEFECT_DATA_R_name" size="10" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="faultReasonSel(this, 'MARKET_DEFECT_DATA_R')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_DATA_R">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearFault('MARKET_DEFECT_DATA_R')"  title="清空"></a> 
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
				<th>产品系列：</th>
				<td><div id="porductFamilyInstallScatterList" class="dropdownlist"></div></td>
				<th>产品型号：</th>
				<td><div id="partTypeInstallScatterList" class="dropdownlist"></div></td>
				
				
				
			</tr>
			<tr>
				<th>时间维度：</th>
				<td>
					<select id="selectDate" name="selectDate" style="width: 78px">
                       <option value="年" ${vo.selectDate eq '年' ? 'selected':''}>年</option>
                       <option value="月" ${vo.selectDate eq '月' ? 'selected':''}>月</option>
                     </select>
				</td>
				<th>维修月份：</th>
				<td>
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime}" readonly="readonly" style="width: 60px" />
					至
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime}" readonly="readonly" style="width: 60px" />
				</td>
				<th>CRM物料：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_DATA_PART_data" name="partId" size="10" value="${vo.partId}"/>
                    <input type="hidden" id="MARKET_DEFECT_DATA_PART_partCode" name="partNumber" value="${vo.partNumber}" />
                    <input type="text" id="MARKET_DEFECT_DATA_PART_partName" name="partDescription" size="10" readonly="true" style="float: left;" value="${vo.partDescription}"/>
                    <a id="btn" onclick="partSel(this, 'MARKET_DEFECT_DATA_PART')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_DATA_PART">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearPart('MARKET_DEFECT_DATA_PART')" title="清空"></a>
				</td>
				<th>CRM物料分类:</th>
				<td> 
					<input type="hidden" id="CRM_PART_CATEGORY_id" name="crmPartCategoryId" value="${vo.crmPartCategoryId}" size="10"/>
					<input type="hidden" id="CRM_PART_CATEGORY_supplierCode" name="crmPartCategoryName" value="${vo.crmPartCategoryName}" size="10"/><!-- 一级分类 -->
                    <input type="text" id="CRM_PART_CATEGORY_supplierName" name="crmPartCategoryTwoName" size="10" readonly="true"  value ="${vo.crmPartCategoryTwoName }" style="float: left;"/><!-- 二级分类 -->
                    <a id="btn" onclick="categorySel(this, 'CRM_PART_CATEGORY')" class="btnLook btn" width=950 height=500 lookupGroup="CRM_PART_CATEGORY">CRM物料分类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearCategory('CRM_PART_CATEGORY')" title="清空"></a>
				</td>
				<th>物料类别：</th>
				<td>
					<div id="dataPartTypesList" class="dropdownlist"></div>
				</td>
			</tr>
			<tr>
				<th>管控类型：</th>
				<td>
					<select id="isConsumed" name="isConsumed" style="width: 78px">
						<option value="-1">全选</option>
						<option value="0" ${vo.isConsumed eq '0' ? 'selected':''}>关键件</option>
						<option value="1" ${vo.isConsumed eq '1' ? 'selected':''}>非关键件</option>
						<option value="2" ${vo.isConsumed eq '2' ? 'selected':''}>附件</option>
					</select>
				</td>
				<th>是否带版本：</th>
				<td>
					<select id="hasVersion" name="hasVersion">
						<option value="2" ${vo.hasVersion eq '2' ? 'selected':''}>否</option>
						<option value="1" ${vo.hasVersion eq '1' ? 'selected':''}>是</option>
					</select>
				</td>
				<th>MES物料：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_DATA_MES_PART_data" name="mesPartId" size="10" value="${vo.mesPartId}"/>
                    <input type="hidden" id="MARKET_DEFECT_DATA_MES_PART_partCode" name="mesPartNumber" value="${vo.mesPartNumber}" />
                    <input type="text" id="MARKET_DEFECT_DATA_MES_PART_partName" name="mesPartDescription" size="10" readonly="true" style="float: left;" value="${vo.mesPartDescription}"/>
                    <a id="btn" onclick="partSel(this, 'MARKET_DEFECT_DATA_MES_PART')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_DATA_MES_PART">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearPart('MARKET_DEFECT_DATA_MES_PART')" title="清空"></a>
				</td>
				<th>MES物料分类:</th>
				<td> 
					<input type="hidden" id="MES_PART_CATEGORY_id" name="mesPartCategoryId" value="${vo.mesPartCategoryId}" size="10"/>
					<input type="hidden" id="MES_PART_CATEGORY_supplierCode" name="mesPartCategoryName" value="${vo.mesPartCategoryName}" size="10"/><!-- 一级分类 -->
                    <input type="text" id="MES_PART_CATEGORY_supplierName" name="mesPartCategoryTwoName" size="10" readonly="true"  value ="${vo.mesPartCategoryTwoName }" style="float: left;"/><!-- 二级分类 -->
                    <a id="btn" onclick="categorySel(this, 'MES_PART_CATEGORY')" class="btnLook btn" width=950 height=500 lookupGroup="MES_PART_CATEGORY">mes物料分类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearCategory('MES_PART_CATEGORY')" title="清空"></a>
				</td>
				<th>百台内：</th>
				<td>
					<select name="isOver" id="isOver">
						<option value="">全选</option>
						<option value="否" ${vo.isOver eq '否' ? 'selected':''}>是</option>
						<option value="是" ${vo.isOver eq '是' ? 'selected':''}>否</option>
					</select>
					<button type="button" onclick="checkMarketDefectData()">查询</button>
					<button type="button" onclick="exportExcelByDefectData()">导出</button>
				</td>
			</tr>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="165" id="table">
		<thead>
			<tr>
				<th>维修工单</th>
				<th>主机条码</th>
				<th>产品系列</th>
				<th>产品型号</th>
				<th>服务中心</th>			
				<th>MES物料编号</th>	
				<th>CRM物料编号</th>
				<th>零部件条码</th>
				<th>物料名称</th>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>故障大类代码</th>
				<th>故障大类名称</th>
				<th>故障小类代码</th>
				<th>故障小类分析</th>
				<th>维修日期</th>
				<th>管控类型</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<td>${o.orderNumber}</td>
					<td>${o.serialNumber}</td>
					<td>${o.productFamily}</td>
					<td>${o.partType}</td>
					<td>${o.region}</td>
					<td>${o.mesPartNumber}</td>
					<td>${o.partNumber}</td>
					<td>${o.partSerial}</td>
					<td>${o.partName}</td>
					<td>${o.supplierNumber}</td>
					<td>${o.supplier}</td>
					<td>${o.faultTypeCode}</td>
					<td>${o.faultTypeName}</td>
					<td>${o.faultReasonCode}</td>
					<td>${o.faultReasonTxt}</td>
					<td>${o.repairDate}</td>
					<td>${o.consumptionType}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<span id="marketDefecDatatTd">
	<script type="text/javascript">
		$(function(){
			$("#regionMarketDataList").dropdownlist({ //区域下拉框显示
				id : "regionListTxt",
				conlunms : 3,
				selectedtext: '${vo.regionListTxt}',
				listboxwidth:600,
				maxchecked:100,
				checkbox:true,
				listboxmaxheight:400,
				width:120,
				requiredvalue:[],
				selected:['${vo.regionListTxt}'],
				data:${jsonRegions},
				onchange:function(text,value){}
			});
			
			$("#dataPartTypesList", navTab.getCurrentPanel()).dropdownlist({
			    id:"partTypesListTxt",
			    columns:2,
			    selectedtext:'${vo.partTypesListTxt}',
			    listboxwidth:300,//下拉框宽
			    maxchecked:100,
			    checkbox:true,
			    listboxmaxheight:400,
			    width:120,
			    requiredvalue:[],
			    selected:['${vo.partTypesListTxt}'],
			    data:${partMap},//数据，格式：{value:name}
			    onchange:function(text,value){
			    }
			});
			loadProductFamilyData("#porductFamilyInstallScatterList", "productFamilyTxt", [${commonVo.productFamilyTxt}], ${jsonProFamily});
	   		loadProductTypeData("#partTypeInstallScatterList", "partTypeListTxt", [${commonVo.partTypeListTxt}], ${jsonParts});
		});
	</script>
</span>