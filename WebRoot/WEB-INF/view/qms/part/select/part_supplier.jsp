<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<script type="text/javascript">
	function getComProductType(idEnd) {
		var productType = $("#productType_" + idEnd);
		productType.empty();
		productType.append("<option value=''>请选择</option>");
		var factroy = $("#factory_" + idEnd).val();
		<c:forEach items="${productType }" var="o">
		var key = '${o.key}';
		if (factroy == key) {
			<c:forEach items="${o.value}" var="pro">
			productType
					.append("<option value='${pro.productType}'>${pro.productType}</option>");
			</c:forEach>
		}
		</c:forEach>
	}

	function clearPartAll(obj) {
		$("#" + obj + "_data").val("");
		$("#" + obj + "_partName").val("");
		$("#" + obj + "_partCode").val("");
	}
	function clearComSupplierAll(obj) {
		$("#" + obj + "_data").val("");
		$("#" + obj + "_supplierName").val("");
		$("#" + obj + "_supplierCode").val("");
	}
	function getComDate(obj) {
		var $dateT = $("#dateT_" + obj);
		$dateT.val("");
		var selectDate = $("#selectDate_" + obj).val();
		var dateType;
		if (selectDate == "周") {
			dateType = "laydate({week:true})";
		} else if (selectDate == "月") {
			dateType = "laydate(laydate({isym:true, format: 'YYYY-MM'}))";
		} else if (selectDate == "年") {
			dateType = "laydate(laydate({isym:true, format: 'YYYY'}))";
		} else {
			dateType = "laydate()";
		}
		$dateT.attr("onclick", dateType);
	}
	$(function() {

		$('#comPartTypeList_${id_end}').dropdownlist({
			id : 'partType',
			columns : 2,
			selectedtext : '',
			listboxwidth : 300,//下拉框宽
			maxchecked : 100,
			checkbox : true,
			listboxmaxheight : 400,
			width : 120,
			requiredvalue : [],
			selected : [ ${vo.partTypeListText} ],
			data : ${partMap},//数据，格式：{value:name}
			onchange : function(text, value) {
			}
		});
	});
</script>
<%--   
                <jsp:include page="../select/part_supplier.jsp" flush="true">
					<jsp:param value="doubleLine" name="dispalyType"/> //doubleLine双列显示,threeLine 三列显示
					<jsp:param value="1" name="factory"/>              //工厂
					<jsp:param value="1" name="productType"/>          //机型类别
					<jsp:param value="1" name="supplier"/>             //供应商
					<jsp:param value="1" name="partType"/>             //物料类别
					<jsp:param value="1" name="partClass"/>            //物料级别
					<jsp:param value="1" name="partName"/>             //物料名称
					<jsp:param value="1" name="partVersion"/>          //物料版本
					<jsp:param value="1" name="isNew"/>                //新品老品
					<jsp:param value="0" name="selectDate"/>           //期间（天，周，年）
					<jsp:param value="1" name="period"/>               //期间
					<jsp:param value="1" name="smallBatch"/>           //小批量				
					<jsp:param value="1" name="source"/>           	   //国内/外				
					<jsp:param value="1" name="lotTime"/>              //供应商批次
					<jsp:param value="1" name="columnNum"/>            //显示柱子数量
					<jsp:param value="1" name="result"/>               //判断结果
					<jsp:param value="0" name="analysisType"/>         //分析类型（不良批/不良数）
					<jsp:param value="0" name="submits"/>             //查询按钮
					<jsp:param value="0" name="isLastTr"/> 
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
			</jsp:include>	 --%>
<c:if test="${param.dispalyType eq 'doubleLine' or param.dispalyType  eq 'threeLine'}">
	<tr>
</c:if>
<c:if test="${param.factory eq '1'}">
	<td>工厂：</td>
	<td><select id="factory_${id_end}" name="factory"
		onchange="getComProductType('${id_end}')">
			<option value="">请选择</option>
			<c:forEach items="${productType }" var="o">
				<option value="${o.key }">${o.key }</option>
			</c:forEach>
	</select> <script type="text/javascript">
		$("#factory_${id_end}").val("${vo.factory}");
	</script></td>
</c:if>
<c:if test="${param.productType eq '1'}">
	<td>机型：</td>
	<td><select id="productType_${id_end}" name="productType">
			<option value="">请选择</option>
			<c:forEach items="${productType }" var="o">
				<c:if test="${o.key==vo.factory }">
					<c:forEach items="${o.value}" var="pro">
						<option value='${pro.productType}'>${pro.productType}</option>
					</c:forEach>
				</c:if>
			</c:forEach>
	</select> <script type="text/javascript">
		$("#productType_${id_end}").val("${vo.productType}")
	</script></td>
</c:if>
<c:if test="${param.supplier eq '1'}">
	<td>供应商：</td>
	<td><input style="float: left;" type="text" id="supplierLookup${id_end}_supplierName" name="supplier" value="${vo.supplier}" size="10" /> 
		<input type="hidden" id="supplierLookup${id_end}_data" name="supplierData" value="${vo.supplierData}"> 
		<input type="hidden" id="supplierLookup${id_end}_supplierCode" name="supplierCode" value="${vo.supplierCode}"> 
		<a id="btn" class="btnLook btn" href="quality/testInstance/supplierSelect.do?data=supplierLookup${id_end}"
			width="1150" height="550" lookupGroup="supplierLookup${id_end}">供应商选择</a>
		<a class="btnClear" href="javascript:void(0);"
			onclick="clearComSupplierAll('supplierLookup${id_end}')" title="清空"></a>
	</td>
</c:if>
<c:if test="${param.dispalyType eq 'threeLine'}">
	</tr>
	<tr>
</c:if>
<c:if test="${param.iscrux eq '1'}">
	<td>是否关键件：</td>
	<td>
		<select id="iscrux_${id_end}" name="iscrux">
			<option value="0">是</option>
			<option value="1">否</option>
		</select>
	</select> <script type="text/javascript">
		$("#iscrux_${id_end}").val("${vo.iscrux}")
	</script></td>
	</td>
</c:if>
<c:if test="${param.partType eq '1'}">
	<td>物料类别：</td>
	<td >
		<div id="comPartTypeList_${id_end}" class="dropdownlist"></div>
	</td>
</c:if>
<c:if test="${param.dispalyType eq 'doubleLine'}">
	</tr>
	<tr>
</c:if>
<c:if test="${param.partClass eq '1'}">
	<td>物料级别：</td>
	<td><select id="partClass_${id_end}" name="partClass">
			<option value="">请选择</option>
			<option value="A">A</option>
			<option value="B">B</option>
			<option value="C">C</option>
	</select> <script type="text/javascript">
		$("#partClass_${id_end}").val("${vo.partClass}")
	</script></td>
</c:if>
<c:if test="${param.partName eq '1'}">
	<td>物料号：</td>
	<td><input style="float: left;" type="text" id="supPartLookup${id_end}_partName" name="partName" value="${vo.partName}" size="10" /> 
		<input type="hidden" id="supPartLookup${id_end}_data" name="partData" value="${vo.partData }"> 
		<input type="hidden" id="supPartLookup${id_end}_partCode" name="partNumber" value="${vo.partNumber }">
		<a id="btn" class="btnLook btn" href="quality/testInstance/partSelect.do?data=supPartLookup${id_end}"
			width=950 height=500 lookupGroup="supPartLookup${id_end}">物料选择</a> 
		<a class="btnClear" href="javascript:void(0);"
			onclick="clearPartAll('supPartLookup${id_end}')" title="清空"></a>
	</td>
</c:if>
<c:if test="${param.source eq '1' }">
<td>国内外：</td>
	<td>
		<select id="source_${id_end}" name="source">
			<option value="">所有</option>
			<option value="国内">国内</option>
			<option value="国外">国外</option>
		</select>
	</select> <script type="text/javascript">
		$("#source_${id_end}").val("${vo.source}")
	</script></td>
	</td>
</c:if>
<c:if test="${param.dispalyType eq 'threeLine'}">
	</tr>
	<tr>
</c:if>
<c:if test="${param.smallBatch eq '1'}">
<td>是否小批量:</td>
			<td>
			 <select id="smallBatch_${id_end}" name = "smallBatch">
			 	<option value="">所有</option>
			 	<option value="是">是</option>
			 	<option value="否">否</option>
			 </select></td>

			 <script type="text/javascript">
		$("#smallBatch_${id_end}").val("${vo.smallBatch}");
	</script>
	
</c:if>
<c:if test="${param.partVersion eq '1'}">
	<td>是否带版本：</td>
	<td><label><input id="partVersion_${id_end}"
			name="partVersion" type="radio" value="是" checked="checked"
			<c:if test="${vo.partVersion eq '是'}">checked='checked'</c:if>>是</label>
		<label><input id="partVersion_${id_end}" name="partVersion"
			type="radio" value="否"
			<c:if test="${vo.partVersion eq '否' or vo.partVersion eq NULL}">checked='checked'</c:if>>否</label>
	</td>
</c:if>
<c:if test="${param.isNew eq '1'}">
	<td>产品成熟度：</td>
	<td ><select id="isNew_${id_end}" name="isNew">
			<option value="">全部</option>
			<option value="老品">老品</option>
			<option value="新品">新品</option>
			<option value="little">小批量</option>
			<option value="large">非小批量</option>
	</select> <script type="text/javascript">
		$("#isNew_${id_end}").val("${vo.isNew}");
	</script></td>
</c:if>
<c:if test="${param.dispalyType eq 'doubleLine'}">
	</tr>
	<tr>
</c:if>
<c:if test="${param.selectDate eq '1'}">
	<td>时间维度：</td>
	<td><select id="selectDate_${id_end}" name="dateType"
		onchange="getComDate('${id_end}')">
			<option value="天">天</option>
			<option value="周">周</option>
			<option value="月">月</option>
			<option value="年">年</option>
	</select> <span id="dateTd_${id_end}"> <input type='text' size='10'
			id='dateT_${id_end}' name='dateT' placeholder='请输入日期'
			<c:if test="${vo.dateType eq '天' or vo.dateType eq null }">onclick="laydate()"</c:if>
			<c:if test="${vo.dateType eq '周' }">onclick="laydate()"</c:if>
			<c:if test="${vo.dateType eq '月' }">onclick="laydate({isym:true, format: 'YYYY-MM'})"</c:if>
			<c:if test="${vo.dateType eq '年' }">onclick="laydate({isym:true, format: 'YYYY'})"</c:if>
			value="${vo.dateT }" readonly='true' />
	</span> <script type="text/javascript">
		$("#selectDate_${id_end}").val("${vo.dateType}")
	</script></td>
</c:if>
<c:if test="${param.period eq '1'}">
	<td>期间：</td>
	<td>
		<input type='text' size='8' id='startTime_${id_end}' name='startTime' placeholder='请输入日期' onclick='laydate()'
			readonly='true' value="${vo.startTime }" /> 
		至 
		<input type='text' size='8' id='endTime_${id_end}' name='endTime' placeholder='请输入日期'
			onclick='laydate()' readonly='true' value="${vo.endTime }" /></td>
</c:if>
<c:if test="${param.dispalyType eq 'threeLine'}">
	</tr>
	<tr>
</c:if>
<c:if test="${param.lotTime eq '1'}">
	<td>供应商批次：</td>
	<td>
	   <input type='text' size='10' id='lotStartTime_${id_end}' name='lotStartTime' placeholder='请输入日期' onclick='laydate()' value="${vo.lotStartTime }" readonly='true' />至
	   <input type='text' size='10' id='lotEndTime_${id_end}' name='lotEndTime' placeholder='请输入日期' onclick='laydate()' value="${vo.lotEndTime }" readonly='true' />
	</td>
</c:if>

<c:if test="${param.columnNum eq '1'}">
	<td>排列图数量：</td>
	<td><select id="columnNum_${id_end}" name="columnNum">
			<option value="5">5</option>
			<option value="10">10</option>
			<option value="15">15</option>
			<option value="20">20</option>
	</select> <script type="text/javascript">
		$("#columnNum_${id_end}").val("${vo.columnNum}")
	</script></td>
</c:if>
<c:if test="${param.analysisType eq '1'}">
	<td>统计方式：</td>
	<td><label><input type="radio" id="analysisType_${id_end}"
			name="analysisType" value="0"
			<c:if test="${vo.analysisType eq '0' || vo.analysisType == null}"> checked="checked"</c:if> >不良批次数/率</label>
		<label><input type="radio" id="analysisType_${id_end}"
			name="analysisType" value="1"
			<c:if test="${vo.analysisType eq '1'}"> checked="checked"</c:if>>不良数/率</label>
	</td>
</c:if>
<c:if test="${param.result eq '1'}">
	<td>检验结果：</td>
	<td><select id="resultS_${id_end}" name="resultS">
			<option value="">全部</option>
			<option value="该批合格">合格</option>
			<option value="该批不合格">不合格</option>
	</select> <script type="text/javascript">
		$("#resultS_${id_end}").val("${vo.resultS}")
	</script></td>
</c:if>
<c:if test="${param.submits eq '1'}">
	<td>
		<div class="buttonActive">
			<div class="buttonContent">
				<button type="submit">查找</button>
			</div>
		</div>
	</td>
</c:if>
<c:if test="${param.isLastTr eq '1'}">
	</tr>
</c:if>