<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<script type="text/javascript">

function saveBox(){
	var duty1 = $("#duty1").val();
	var duty2 = $("#duty2").val();
	if(duty2 == "班组"){
		var duty3 = $("#baseGroupEdit").val();
	}else if(duty2 =="供应商"){
		var duty3 = $("#OPEN_POOR_SUPPLIER_EDIT_supplierName").val();
	}else{
		var duty3 = "其他";
	}
	if((duty1==""||duty1==null) && (duty2==""||duty2==null)){
		alertMsg.error("请选择责任方");
		return false;
	}
	var url = "poorOpen/save.do?";
	var data = {backNum:${ap.backNum},duty1:duty1,duty2:duty2,duty3:duty3};
	var callback = function(data){
		if(data.result == "0"){
			$("#market").submit();
			$.pdialog.closeCurrent();
		}else{
			
		}
	};
	$.post(url,data,callback,"json");
}
function dimSave(){
	var duty2 = $("#duty2").val();
	if(duty2 == '供应商'){
		$("#group").attr("style","display:none;")
		$("#other").attr("style","display:none;")
		$("#supplier").attr("style","display:block;")
		
	}else if(duty2 == '班组'){
		$("#supplier").attr("style","display:none;")
		$("#other").attr("style","display:none;")
		$("#group").attr("style","display:block;")
	}else{
		$("#supplier").attr("style","display:none;")
		$("#other").attr("style","display:block;")
		$("#group").attr("style","display:none;")
	}
}
$(function(){
})
		


</script>
<div class="pageContent">
	<form>
		 <input
			type="hidden" id="key" name="atrKey" value="${ap.atrKey}" />
		<div class="pageFormContent" layoutH="55">
			<table class="tableFormContent nowrap">
				<tr>
					<th>反馈单号</th>
					<td><input type="text" name="backNum"
						id="backNum" size="30" value="${ap.backNum}"
						readonly="readonly" /></td>
					
					<th>反馈日期</th>
					<td colspan="3"><input type="text" name="bactTime"
						id="bactTime" size="30" value="${ap.bactTime}"
						readonly="readonly" />
					</td>
				</tr>

				<tr>

					<th>单据类别</th>
					<td><input name="billType" id="billType" type="text"
						size="30"  value="${ap.billType}"
						readonly="readonly" /></td>
					<th>反馈类型</th>
					<td><input name="backType" id="backType" type="text"
						size="30"  value="${ap.backType}"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<th>故障大类</th>
					<td><input type="text" name="faultType"
						id="faultType" size="30" value="${ap.faultType}"
						readonly="readonly" /></td>
						<th>故障小类</th>
					<td><input type="text" name="faultReason"
						id="faultReason" size="30" value="${ap.faultReason}"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<th>报告主题</th>
					<td><input type="text" name="billTheme"
						id="billTheme" size="30" value="${ap.billTheme}"
						readonly="readonly" /></td>
						<th>产品编码</th>
					<td><input type="text" name="productNum"
						id="productNum" size="30" value="${ap.productNum}"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<th>产品类型</th>
					<td><input type="text" name="productType"
						id="productType" size="30" value="${ap.productType}"
						readonly="readonly" /></td>
						<th>产品型号</th>
					<td><input type="text" name="partType"
						id="partType" size="30" value="${ap.partType}"
						readonly="readonly" /></td>
				</tr>
					<tr>
					<th>使用期限</th>
					<td><input type="text" name="useTime"
						id="useTime" size="30" value="${ap.useTime}"
						readonly="readonly" /></td>
						<th>发现场所</th>
					<td><input type="text" name="findPlace"
						id="findPlace" size="30" value="${ap.findPlace}"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<th>问题描述</th>
					<td colspan="3"><textarea type="text" name="describe"
						id="describe" style="width: 100%" value="${ap.describe}"
						readonly="readonly" /></td>
						
				</tr>
				<tr>
					<th>操作场景描述</th>
					<td colspan="3"><textarea type="text" name="placeDescribe"
						id="placeDescribe" style="width: 100%" value="${ap.placeDescribe}"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<th>处置情况</th>
					<td><input type="text" name="disposalSituation"
						id="disposalSituation" size="30" value="${ap.disposalSituation}"
						readonly="readonly" /></td>
						<th>初步原因分析</th>
					<td><input type="text" name="causeAnalysis"
						id="causeAnalysis" size="30" value="${ap.causeAnalysis}"
						readonly="readonly" /></td>
				</tr>
				<tr>
				
					<th>网点</th>
					<td><input type="text" name="point"
						id="point" size="30" value="${ap.point}"
						readonly="readonly" /></td>
						
					<th>改善建议</th>
					<td><input type="text" name="suggest"
						id="point" size="30" value="${ap.suggest}"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<th>技师姓名</th>
					<td><input type="text" name="mechanicName"
						id="mechanicName" size="30" value="${ap.mechanicName}"
						readonly="readonly" /></td>
						<th>技师电话</th>
					<td><input type="text" name="mechanicPhone"
						id="mechanicPhone" size="30" value="${ap.mechanicPhone}"
						readonly="readonly" /></td>
				</tr>
				<tr>
					<th>安装日期</th>
					<td><input type="text" name="installTime"
						id="installTime" size="30" value="${ap.installTime}"
						readonly="readonly" /></td>
						<th>生产日期</th>
					<td><input type="text" name="production"
						id="production" size="30" value="${ap.production}"
						readonly="readonly" /></td>
				</tr>
				<tr>	
					<th>总部工程师确认</th>		
					<td colspan="3"><input type="text" name="engineerConfirm"
						id="engineerConfirm" size="60" value="${ap.engineerConfirm}"
						readonly="readonly" /></td>
				</tr>
				
					<c:if test="${type==1}">
				<tr>
						<th>责任方1:</th>
						<td><select name="duty1" id="duty1" >
								<option value="" selected="selected">-请选择-</option>
								<option value="物流">物流</option>
								<option value="电器一厂">电器一厂</option>
								<option value="电器二厂">电器二厂</option>
								<option value="燃气工厂">燃气工厂</option>
								<option value="其他">其他</option>
						</select></td>
				</tr>
						  <script type="text/javascript">
						$("#duty1").val("${ap.duty1}");
					</script>
					</c:if>
					<c:if test="${type == 2}">
				<tr>
					<th>责任方1</th>
					<td colspan="3"><input type="text" name="duty1"
						size="60" value="${ap.duty1}"
						readonly="readonly" /></td>
						</tr>
						<tr>
						<th>责任方2</th>
						<td colspan="3"><select name="duty2" id="duty2" onchange="dimSave()">
								<option value="" selected="selected">-请选择-</option>
								<option value="班组">班组</option>
								<option value="供应商">供应商</option>
								<option value="其他">其他</option>
						</select></td>
				</tr>
				<tr>
				<th>责任方3</th>
				<td colspan="3">
				<div id="supplier" style="display:none">
					<input type="hidden" id="OPEN_POOR_SUPPLIER_EDIT_data" name="supplierId" readonly="true" style="float: left;" value="${vo.supplierId}"/>
					<input type="hidden" id="OPEN_POOR_SUPPLIER_EDIT_supplierCode" name="supplierNumber" readonly="true" style="float: left;" value="${vo.supplierNumber}"/>
                    <input type="text" id="OPEN_POOR_SUPPLIER_EDIT_supplierName" name="supplierListTxt" readonly="true" style="float: left;" value="${vo.supplierListTxt}"/>
                    <a id="btn" class="btnLook btn" onclick="supplierSel(this, 'OPEN_POOR_SUPPLIER_EDIT')" lookupGroup="OPEN_POOR_SUPPLIER_EDIT">供应商选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearSupplier('OPEN_POOR_SUPPLIER_EDIT')" title="清空"></a>
				</div>
				<div id="group" style="display:none">
				<select name="baseGroup" id="baseGroupEdit" <c:if test="${param.isRequired}">class="required"</c:if>>
						<option value="">请选择</option>
						<c:forEach items="${fgroupList}" var="o">
						<option value="${o.shiftGroup }">${o.shiftGroup}</option>
						</c:forEach>
				</select>
				<script type="text/javascript">
					$("#baseGroup").val("${vo.baseGroup}");
				</script>
				</div>
				<div id="other" style="display:none">其他</div>
				</td>
				</tr>
					</c:if>
				
			</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="saveBox()">确定</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
