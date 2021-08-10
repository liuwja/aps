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
		var $dateTT = $("#dateT_T_" + obj);
		$dateT.val("");
		$dateTT.val("");
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
		$dateTT.attr("onclick", dateType);
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
	function radioclick(id,id2){
		var rad=$("#"+id+":checked").val();
		//alert($("#"+id2).val())
		if(rad==0){
			$("#"+id2).val("更换");
			$("#"+id2).attr("disabled","disabled");  
		}else{
			$("#"+id2).removeAttr("disabled");  
		}
	}
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
					<jsp:param value="1" name="lotTime"/>              //供应商批次
					<jsp:param value="1" name="columnNum"/>            //显示柱子数量
					<jsp:param value="1" name="result"/>               //判断结果
					<jsp:param value="0" name="analysisType"/>         //分析类型（不良批/不良数）
					<jsp:param value="0" name="submits"/>             //查询按钮
					<jsp:param value="0" name="isLastTr"/> 
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
			</jsp:include>	 --%>

<tr>
	<c:if test="${param.factory eq '1'}">
		<td>工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;厂：</td>
		<td><select id="factory_${id_end}" name="factory" style="width: 100px" onchange="getComProductType('${id_end}')">
				<option value="">请选择</option>
				<c:forEach items="${productType }" var="o">
					<option value="${o.key }">${o.key }</option>
				</c:forEach>
			</select> 
			<script type="text/javascript">
				$("#factory_${id_end}").val("${vo.factory}");
			</script>
		</td>
	</c:if>
	<c:if test="${param.productType eq '1'}">
		<td>机&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
		<td><select id="productType_${id_end}" name="productType" style="width: 100px">
				<option value="">请选择</option>
				<c:forEach items="${productType }" var="o">
					<c:if test="${o.key==vo.factory }">
						<c:forEach items="${o.value}" var="pro">
							<option value='${pro.productType}'>${pro.productType}</option>
						</c:forEach>
					</c:if>
				</c:forEach>
			</select> 
			<script type="text/javascript">
				$("#productType_${id_end}").val("${vo.productType}")
			</script>
		</td>
	</c:if>
	<c:if test="${param.supplier eq '1'}">
		<td>供&nbsp;&nbsp;&nbsp;&nbsp;应&nbsp;&nbsp;&nbsp;&nbsp;商：</td>
		<td style="width: 150px"><input style="float: left;" type="text" id="supplierLookup${id_end}_supplierName" name="supplier" value="${vo.supplier}" size="10" /> 
			<input type="hidden" id="supplierLookup${id_end}_data" name="supplierData" value="${vo.supplierData}"> 
			<input type="hidden" id="supplierLookup${id_end}_supplierCode" name="supplierCode" value="${vo.supplierCode}"> 
			<a id="btn" class="btnLook btn" href="quality/testInstance/supplierSelect.do?data=supplierLookup${id_end}"
				width="1150" height="550" lookupGroup="supplierLookup${id_end}">供应商选择</a>
			<a class="btnClear" href="javascript:void(0);"
				onclick="clearComSupplierAll('supplierLookup${id_end}')" title="清空"></a>
		</td>
	</c:if>
</tr>
<tr>
	<c:if test="${param.isNew eq '1'}">
		<td>产品成熟度：</td>
		<td><select id="isNew_${id_end}" name="isNew" style="width: 100px">
				<option value="">全部</option>
				<option value="老品">老品</option>
				<option value="新品">新品</option>
				<option value="小批量">小批量</option>
			</select> <script type="text/javascript">
				$("#isNew_${id_end}").val("${vo.isNew}");
			</script>
		</td>
	</c:if>
	<c:if test="${param.partClass eq '1'}">
		<td>物料级别：</td>
		<td><select id="partClass_${id_end}" name="partClass" style="width: 100px">
			<option value="">请选择</option>
			<option value="A">A</option>
			<option value="B">B</option>
			<option value="C">C</option>
			</select> <script type="text/javascript">
				$("#partClass_${id_end}").val("${vo.partClass}")
			</script>
		</td>
	</c:if>
	<c:if test="${param.partName eq '1'}">
		<td>物&nbsp;&nbsp;&nbsp;&nbsp;料&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
		<td style="width: 150px"><input style="float: left;" type="text" id="supPartLookup${id_end}_partName" name="partName" value="${vo.partName}" size="10" /> 
			<input type="hidden" id="supPartLookup${id_end}_data" name="partData" value="${vo.partData }"> 
			<input type="hidden" id="supPartLookup${id_end}_partCode" name="partNumber" value="${vo.partNumber }">
			<a id="btn" class="btnLook btn" href="quality/testInstance/partSelect.do?data=supPartLookup${id_end}"
				width=950 height=500 lookupGroup="supPartLookup${id_end}">物料选择</a> 
			<a class="btnClear" href="javascript:void(0);"
				onclick="clearPartAll('supPartLookup${id_end}')" title="清空"></a>
		</td>
	</c:if>
</tr>
<tr>
	<c:if test="${param.partVersion eq '1'}">
		<td>是否带版本：</td>
		<td style="width: 130px"><label style="width: 50px"><input id="partVersion_${id_end}"
				name="partVersion" type="radio" value="是" checked="checked"
				<c:if test="${vo.partVersion eq '是'}">checked='checked'</c:if>>是</label>
			<label style="width: 50px"><input id="partVersion_${id_end}" name="partVersion"
				type="radio" value="否"
				<c:if test="${vo.partVersion eq '否' or vo.partVersion eq NULL }">checked='checked'</c:if>>否</label>
		</td>
	</c:if>
	<c:if test="${param.iscrux eq '1'}">
		<td>管控类型：</td>
		<td>
			<select id="iscrux_${id_end}" name="iscrux" style="width: 100px">
			    <option value="">所有</option>
				<option value="0">关键件</option>
				<option value="1">非关键件</option>
				<option value="2">附件</option>
			</select>
			</select> <script type="text/javascript">
				$("#iscrux_${id_end}").val("${vo.iscrux}");
			</script>
		</td>
	</c:if>
	<c:if test="${param.badphenomenon eq '1'}">
		<td>不良现象：</td>
		<td>
			<input type="text" name="badphenomenon" id="badphenomenon" value="${vo.badphenomenon }">
		</td>
	</c:if>
	<c:if test="${param.columnNum eq '1'}">
		<td>排列图数量：</td>
		<td><select id="columnNum_${id_end}" name="columnNum" style="width: 50px;margin-right: 10px;">
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
				<option value="6">6</option>
				<option value="7">7</option>
				<option value="8">8</option>
				<option value="9">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
				<option value="13">13</option>
				<option value="14">14</option>
				<option value="15">15</option>
				<option value="16">16</option>
				<option value="17">17</option>
				<option value="18">18</option>
				<option value="19">19</option>
				<option value="20">20</option>
		</select>
		<script type="text/javascript">
			$("#columnNum_${id_end}").val("${vo.columnNum}");
		</script>
		<c:if test="${param.sordType eq '1' }">
		     <select id="sordType_${id_end}" name="sordType">
	             <option value="不良批/数">不良批/数-排序</option>
	             <option value="不良率">不良率-排序</option>
             </select>
             <script type="text/javascript">
               $("#sordType_${id_end}").val("${vo.sordType}")
             </script>
		</c:if>
		</td>
	</c:if>
</tr>
<tr>
	<c:if test="${param.selectDate eq '1'}">
		<td>时间维度：</td>
		<td colspan="2"><select id="selectDate_${id_end}" name="dateType" style="margin-right: 5px"
			onchange="getComDate('${id_end}')">
				<option value="天">天</option>
				<option value="周">周</option>
				<option value="月">月</option>
				<option value="年">年</option>
			</select> 
			<span id="dateTd_${id_end}"> 
				<input type='text' size='8' style="margin-right: 5px" id='dateT_${id_end}' name='dateT' placeholder='请输入日期'
					<c:if test="${vo.dateType eq '天'  }">onclick="laydate()"</c:if>
					<c:if test="${vo.dateType eq '周' }">onclick="laydate()"</c:if>
					<c:if test="${vo.dateType eq '月' or vo.dateType eq null }">onclick="laydate({isym:true, format: 'YYYY-MM'})"</c:if>
					<c:if test="${vo.dateType eq '年' }">onclick="laydate({isym:true, format: 'YYYY'})"</c:if>
				value="${vo.dateT }" readonly='true' />
				<input type='text' size='8' style="margin-right: 5px" id='dateT_T_${id_end}' name='dateT_T' placeholder='请输入日期'
					<c:if test="${vo.dateType eq '天'  }">onclick="laydate()"</c:if>
					<c:if test="${vo.dateType eq '周' }">onclick="laydate()"</c:if>
					<c:if test="${vo.dateType eq '月' or vo.dateType eq null}">onclick="laydate({isym:true, format: 'YYYY-MM'})"</c:if>
					<c:if test="${vo.dateType eq '年' }">onclick="laydate({isym:true, format: 'YYYY'})"</c:if>
				value="${vo.dateT_T }" readonly='true' />
			</span> 
		<script type="text/javascript">
			$("#selectDate_${id_end}").val("${vo.dateType eq null ? '月' : vo.dateType}")
		</script></td>
	</c:if>
		<c:if test="${param.analysisType eq '1'}">
		<td style="text-align: right;">统&nbsp;计&nbsp;方&nbsp;式：</td>
		<td colspan="2"><label style="width: 80px"><input type="radio" onclick="radioclick('analysisType_${id_end}','replaceType_${id_end}')" id="analysisType_${id_end}"
				name="analysisType" value="0"
				<c:if test="${vo.analysisType eq '0' }"> checked="checked"</c:if> >不良批次数</label>
			<label style="width: 80px"><input type="radio" onclick="radioclick('analysisType_${id_end}','replaceType_${id_end}')" id="analysisType_${id_end}"
				name="analysisType" value="1"
				<c:if test="${vo.analysisType eq '1' || vo.analysisType == null}"> checked="checked"</c:if>>不良数/率</label>
				
			<c:if test="${param.replaceType eq '1'}">
				<select id="replaceType_${id_end}" name="replaceType" style="width:85px;">
					<option value="0">更换</option>
					<option value="1">退次</option>
				</select>
				<script type="text/javascript">
					$("#replaceType_${id_end}").val("${vo.replaceType}");
				</script>
			</c:if>	
		</td>
	</c:if>
	<c:if test="${param.analysisType eq '1'}">
       </tr>
	</c:if>