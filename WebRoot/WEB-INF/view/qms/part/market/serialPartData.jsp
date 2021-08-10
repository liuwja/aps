<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script>
function exportExcelBySerialPartData() {
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
	var objs = $("#incomingPartDataFrom input",navTab.getCurrentPanel());
	var objs_select = $("#incomingPartDataFrom select",navTab.getCurrentPanel());
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
	myInput.setAttribute("value", "14");
	myForm.appendChild(myInput);
	myForm.submit();
}
</script>
<div class="pageHeader" style="position:static">
<form onsubmit="return navTabSearch(this);" rel="pagerForm" id="serialDataFrom" action="quality/marketPart/serialPartData.do" method="post">
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
				<th>物料类别：</th>
				<td>
					<div id="serialPartTypeList" class="dropdownlist"></div>
				</td>
				<th>供应商：</th>
				<td>
					<input type="hidden" id="SERIAL_PART_SUPPLIER_data" name="supplierId" size="10" readonly="true" style="float: left;" value="${vo.supplierId}"/>
					<input type="hidden" id="SERIAL_PART_SUPPLIER_supplierCode" name="supplierNumber" size="10" readonly="true" style="float: left;" value="${vo.supplierNumber}"/>
                    <input type="text" id="SERIAL_PART_SUPPLIER_supplierName" name="supplierListTxt" size="10" readonly="true" style="float: left;" value="${vo.supplierListTxt}"/>
                    <a id="btn" class="btnLook btn" onclick="supplierSel(this, 'SERIAL_PART_SUPPLIER')" width=950 height=500 lookupGroup="SERIAL_PART_SUPPLIER">供应商选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearSupplier('SERIAL_PART_SUPPLIER')" title="清空"></a>
				</td>
				<th>下线时间：</th>
				<td>
                    <input type="text" id="downStartTime" name="downStartTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.downStartTime}" readonly="readonly" size="8"/>
					至
					<input type="text" id="downEndTime" name="downEndTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.downEndTime}" readonly="readonly" size="8"/>
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
					<div id="regionSerialPartList" class="dropdownlist"></div>
				</td>
				<th>物料：</th>
				<td>
					<input type="hidden" id="SERIAL_PART_data" name="partId" size="10" value="${vo.partId}"/>
					<input type="hidden" id="SERIAL_PART_id" name="partName" size="10" value="${vo.partName}"/>
                    <input type="hidden" id="SERIAL_PART_partCode" name="partNumber" value="${vo.partNumber}" />
                    <input type="text" id="SERIAL_PART_partName" name="partDescription" size="10" readonly="true" style="float: left;" value="${vo.partDescription}"/>
                    <a id="btn" onclick="partSel(this, 'SERIAL_PART')" class="btnLook btn" width=950 height=500 lookupGroup="SERIAL_PART">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearPart('SERIAL_PART')" title="清空"></a>
				</td>
				<th>发货时间：</th>
				<td>
                    <input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime}" readonly="readonly" size="8"/>
					至
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime}" readonly="readonly" size="8"/>
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
				<th>主机条码：</th>
				<td>
					<input type="text" id="serialNumber" name="serialNumber" value="${vo.serialNumber}">
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
				<td>
					<button type="submit">查询</button>
<!-- 					<button type="button" onclick="exportExcelBySerialPartData()">导出</button> -->
				</td>
			</tr>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="145" id="table">
		<thead>
			<tr>
				<th>主机条码</th>
				<th>物料名称</th>
				<th>物料条码</th>
				<th>物料编码</th>
				<th>物料类别</th>
				<th>物料级别</th>
				<th>机型类别</th>
				<th>产品系列</th>
				<th>产品型号</th>
				<th>产品成熟度</th>
				<th>服务中心</th>
				<th>供应商名称</th>
				<th>供应商简称</th>
				<th>供应商编码</th>
				<th>下线时间</th>
				<th>发货时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<td>${o.serialNumber}</td>
					<td>${o.partName}</td>
					<td>${o.partSerial}</td>
					<td>${o.partNumber}</td>
					<td>${o.partType}</td>
					<td>${o.partLevel}</td>
					<td>${o.productType}</td>
					<td>${o.partFamily}</td>
					<td>${o.qmsType}</td>
					<td>${o.partMaturity}</td>
					<td>${o.region}</td>
					<td>${o.supplier}</td>
					<td>${o.supplierShortName}</td>
					<td>${o.supplierNumber}</td>
					<td>${o.downlineTime}</td>
					<td>${o.shipDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<span id="serialPartTd">
	<script type="text/javascript">
		$(function(){
			$("#regionSerialPartList").dropdownlist({ //区域下拉框显示
				id : "regionListTxt",
				conlunms : 2,
				selectedtext:'${vo.regionListTxt}',
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
			
			$("#serialPartTypeList", navTab.getCurrentPanel()).dropdownlist({
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
		});
	</script>
</span>