<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script>
</script>
<div class="pageHeader" style="position:static">
	<form id="marketDefectForm" onsubmit="return navTabSearch(this);" rel="pagerForm" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td style="text-align:center;">机型类别：</td>
					<td style="width: 78px">
						<select name="productType">
								<option value="">请选择</option>
								<c:forEach items="${productTypes}" var="o">
								<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
								</c:forEach>
						</select>
					</td>
					<td style="text-align:center;">供应商：</td>
					<td style="width: 142px">
						<input type="hidden" id="MARKET_DEFECT_SUPPLIER_data" name="supplierId" size="10" readonly="true" style="float: left;" value="${vo.supplierId}"/>
						<input type="hidden" id="MARKET_DEFECT_SUPPLIER_supplierCode" name="supplierNumber" size="10" readonly="true" style="float: left;" value="${vo.supplierNumber}"/>
	                    <input type="text" id="MARKET_DEFECT_SUPPLIER_supplierName" name="supplierListTxt" size="10" readonly="true" style="float: left;" value="${vo.supplierListTxt}"/>
	                    <a id="btn" class="btnLook btn" href="quality/testInstance/supplierSelect.do?data=MARKET_DEFECT_SUPPLIER" width=950 height=500 lookupGroup="MARKET_DEFECT_SUPPLIER">供应商选择</a>  
						<a class="btnClear" href="javascript:void(0);" onclick="clearAll_MARKET_DEFECT_SUPPLIER()" title="清空"></a>
					</td>
					<td>故障大类：</td>
					<td>
						<input type="hidden" name="faultTypeID" id="MARKET_DEFECT_id" readonly="true" value="${vo.faultTypeID}"/>  
	                    <input type="hidden" name="faultTypeCode" id="MARKET_DEFECT_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
	                    <input type="text" name="faultTypeTxt" id="MARKET_DEFECT_name" size="10" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
	                    <a id="btn" onclick="MARKET_DEFECT_Sel(this)" class="btnLook btn" href="qms/commonSelect/faultTypeSelect.do?groupName=MARKET_DEFECT" width=950 height=500 lookupGroup="MARKET_DEFECT">故障大类选择</a>
	                    <a class="btnClear" href="javascript:void(0);" onclick="clearAll_MARKET_DEFECT();" title="清空"></a> 
					</td>
					<td>故障小类是否有效：</td>
					<td>
						<select id="faultReasonValid" name="faultReasonValid" style="width : 78px">
							<option value="">全选</option>
							<option value="是" ${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid eq '是' ? 'selected':''}>是</option>
							<option value="否" ${vo.faultReasonValid eq '否' ? 'selected':''}>否</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:center;">产品成熟度：</td>
					<td>
						<select id="partMaturity" name="partMaturity" style="width: 78px">
							<option value="">全部</option>
							<option value="老品" ${vo.partMaturity eq '老品' ? 'selected':''}>老品</option>
							<option value="新品" ${vo.partMaturity eq '新品' ? 'selected':''}>新品</option>
							<option value="小批量" ${vo.partMaturity eq '小批量' ? 'selected':''}>小批量 </option>
						</select>
					</td>
					<td style="text-align:center;">区域：</td>
					<td>
						<div id="regionMarketList" class="dropdownlist"></div>
					</td>
					<td style="text-align:center;">故障小类：</td>
					<td>
						<input type="hidden" name="faultReasonID" id="MARKET_DEFECT_R_id"  value="${vo.faultReasonID}"/>  
	    				<input type="hidden" name="faultReasonCode" id="MARKET_DEFECT_R_code" value="${vo.faultReasonCode}"/>  
	    				<input type="text" name="faultReasonTxt" id="MARKET_DEFECT_R_name" size="10" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
	    				<a id="btn" onclick="MARKET_DEFECT_R_Sel(this)" class="btnLook btn" href="qms/commonSelect/faultReasonSelect.do?data=MARKET_DEFECT_R" width=950 height=500 lookupGroup="MARKET_DEFECT_R">故障小类选择</a>  
						<a class="btnClear" href="javascript:void(0);" onclick="clearAll_MARKET_DEFECT_R()"  title="清空"></a> 
					</td>
					<td>物料级别：</td>
					<td>
						<select id="partLevel" name="partLevel" style="width : 78px">
							<option value="">全选</option>
							<option value="A" ${vo.partLevel eq 'A' ? 'selected':''}>A</option>
							<option value="B" ${vo.partLevel eq 'B' ? 'selected':''}>B</option>
							<option value="C" ${vo.partLevel eq 'C' ? 'selected':''}>C</option>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align:center;">是否关键件：</td>
					<td>
						<select id="isConsumed" name="isConsumed" style="width : 78px">
							<option value="">全选</option>
							<option value="1" ${vo.isConsumed eq '1' ? 'selected':''}>是</option>
							<option value="2" ${vo.isConsumed eq '2' ? 'selected':''}>否</option>
						</select>
					</td>
					<td style="text-align:center;">时间维度：</td>
					<td>
						<select id="selectDate" name="selectDate">
	                       <option value="年" ${vo.selectDate eq '年' ? 'selected':''}>年</option>
	                       <option value="月" ${vo.selectDate eq '月' ? 'selected':''}>月</option>
	                     </select>
	                     <input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth}" readonly="readonly" size="8"/>
					</td>
					<td>物料类别：</td>
					<td>
						<div id="partTypesList" class="dropdownlist"></div>
					</td>
				</tr>
				<tr>
					<td>是否带版本：</td>
					<td>
						<select id="hasVersion" name="hasVersion" style="width : 78px">
							<option value="2" ${vo.hasVersion eq '2' ? 'selected':''}>否</option>
							<option value="1" ${vo.hasVersion eq '1' ? 'selected':''}>是</option>
						</select>
					</td>
					<td>排列图数量：</td>
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
						<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="exportExcel()">导出</button></div></div>
					</td>
				</tr>
			</table>
		</div>
	</form>
</div>