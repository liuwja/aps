<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	#showColor{position:fixed;bottom:16px;left:0px;width:100%;height:20px;}
	#showColor .col_ul{text-align:center;margin-left:200px;background:#666;}
	#showColor .col_ul li{list-style:none;float:left;line-height:20px;}
	#showColor .col_ul .clo_li{width:30px;height:14px;margin:2px 2px 4px 10px;}
    #listboxBody  tr:nth-of-type(odd) { background: #eee;}
</style>
<script>
function matrixCheck() {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
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
	$("#marketDefectMatrixForm", navTab.getCurrentPanel()).submit();
}

function exportExcelByDefectMatrix() {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val(); //机型类别
	var startTime = $("#startTime", navTab.getCurrentPanel()).val();
	var endTime = $("#endTime", navTab.getCurrentPanel()).val();
	if(productType==""){
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
	var objs = $("#marketDefectMatrixForm input",navTab.getCurrentPanel());
	var objs_select = $("#marketDefectMatrixForm select",navTab.getCurrentPanel());
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
		if($obj.attr("type") == "checkbox"){
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
	var matrix = $("#matrixType", navTab.getCurrentPanel()).val();
	if (matrix == "正三角阵") {
		myInput.setAttribute("value", "10");
	} else {
		myInput.setAttribute("value", "11");
	}
	myForm.appendChild(myInput);
	document.body.appendChild(myForm);
	myForm.submit();
}
</script>
<div class="pageHeader" style="position:static">
<form id="marketDefectMatrixForm" onsubmit="return navTabSearch(this);" rel="pagerForm" id="marketDefectMatrixForm" action="quality/marketPart/marketDefectMatrix.do" method="post">
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
					<input type="hidden" id="MARKET_DEFECT_MATRIX_SUPPLIER_data" name="supplierId" size="10" readonly="true" style="float: left;" value="${vo.supplierId}"/>
					<input type="hidden" id="MARKET_DEFECT_MATRIX_SUPPLIER_supplierCode" name="supplierNumber" size="10" readonly="true" style="float: left;" value="${vo.supplierNumber}"/>
                    <input type="text" id="MARKET_DEFECT_MATRIX_SUPPLIER_supplierName" name="supplierListTxt" size="10" readonly="true" style="float: left;" value="${vo.supplierListTxt}"/>
                    <a id="btn" class="btnLook btn" onclick="supplierSel(this, 'MARKET_DEFECT_MATRIX_SUPPLIER')" width=950 height=500 lookupGroup="MARKET_DEFECT_MATRIX_SUPPLIER">供应商选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearSupplier('MARKET_DEFECT_MATRIX_SUPPLIER')" title="清空"></a>
				</td>
				<th>故障大类：</th>
				<td>
					<input type="hidden" name="faultTypeID" id="MARKET_DEFECT_MATRIX_id" readonly="true" value="${vo.faultTypeID}"/>  
                    <input type="hidden" name="faultTypeCode" id="MARKET_DEFECT_MATRIX_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="MARKET_DEFECT_MATRIX_name" size="10" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a id="btn" onclick="faultTypeSel(this, 'MARKET_DEFECT_MATRIX')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_MATRIX">故障大类选择</a>
                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('MARKET_DEFECT_MATRIX');" title="清空"></a> 
				</td>
				<th>故障小类是否有效：</th>
				<td>
					<select id="faultReasonValid" name="faultReasonValid">
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
					<div id="regionMarketMatrixList" class="dropdownlist"></div>
				</td>
				<th>故障小类：</th>
				<td>
					<input type="hidden" name="faultReasonID" id="MARKET_DEFECT_MATRIX_R_id"  value="${vo.faultReasonID}"/>  
    				<input type="hidden" name="faultReasonCode" id="MARKET_DEFECT_MATRIX_R_code" value="${vo.faultReasonCode}"/>  
    				<input type="text"   name="faultReasonTxt" id="MARKET_DEFECT_MATRIX_R_name" size="10" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="faultReasonSel(this, 'MARKET_DEFECT_MATRIX_R')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_MATRIX_R">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearFault('MARKET_DEFECT_MATRIX_R')" title="清空"></a> 
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
				<th>时间维度：</th>
				<td>
					<select id="selectDate" name="selectDate" style="width: 78px">
                       <option value="month" ${vo.selectDate eq 'month' ? 'selected':''}>月</option>
                       <option value="quarter" ${vo.selectDate eq 'quarter' ? 'selected':''}>季度</option>
                       <option value="year" ${vo.selectDate eq 'year' ? 'selected':''}>年</option>
                     </select>
				</td>
				<th>生产月份：</th>
				<td>
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime}" readonly="readonly" style="width: 60px" />
					至
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime}" readonly="readonly" style="width: 60px" />
				</td>
				<th>CRM物料：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_MATRIX_PART_data" name="partId" value="${vo.partId}"/>
                    <input type="hidden" id="MARKET_DEFECT_MATRIX_PART_partCode" name="partNumber" value="${vo.partNumber}" />
                    <input type="text" id="MARKET_DEFECT_MATRIX_PART_partName" name="partDescription" size="10" readonly="true" value="${vo.partDescription}" style="float: left;"/>
                    <a id="btn" onclick="partSel(this, 'MARKET_DEFECT_MATRIX_PART')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_MATRIX_PART">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearPart('MARKET_DEFECT_MATRIX_PART')" title="清空"></a>
				</td>
				<th>物料类别：</th>
				<td>
					<div id="matrixPartTypesList" class="dropdownlist"></div>
				</td>
			</tr>
			<tr>
				<th>是否关键件：</th>
				<td>
					<select id="isConsumed" name="isConsumed" style="width: 78px">
						<option value="0" ${vo.isConsumed eq '0' ? 'selected':''}>关键件</option>
						<option value="1" ${vo.isConsumed eq '1' ? 'selected':''}>非关键件</option>
						<option value="2" ${vo.isConsumed eq '2' ? 'selected':''}>附件</option>
					</select>
				</td>
				<th>最大值：</th>
				<td>
					<input type="text" name="maxValue" id="maxValue" value="${vo.maxValue}" size="17" />
				</td>
				<th>MES物料：</th>
				<td>
					<input type="hidden" id="MARKET_DEFECT_MATRIX_MES_PART_data" name="mesPartId" value="${vo.mesPartId}"/>
                    <input type="hidden" id="MARKET_DEFECT_MATRIX_MES_PART_partCode" name="mesPartNumber" value="${vo.mesPartNumber}" />
                    <input type="text" id="MARKET_DEFECT_MATRIX_MES_PART_partName" name="mesPartDescription" size="10" readonly="true" value="${vo.mesPartDescription}" style="float: left;"/>
                    <a id="btn" onclick="partSel(this, 'MARKET_DEFECT_MATRIX_MES_PART')" class="btnLook btn" width=950 height=500 lookupGroup="MARKET_DEFECT_MATRIX_MES_PART">物料选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearPart('MARKET_DEFECT_MATRIX_MES_PART')" title="清空"></a>
				</td>
				<th>是否带版本：</th>
				<td>
					<select id="hasVersion" name="hasVersion">
						<option value="2" ${vo.hasVersion eq '2' ? 'selected':''}>否</option>
						<option value="1" ${vo.hasVersion eq '1' ? 'selected':''}>是</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>三角阵类型：</th>
				<td>
					<select id="matrixType" name="matrixType" value="${vo.matrixType}">
						<option value="正三角阵" ${vo.matrixType eq '正三角阵' ? 'selected':''}>正三角</option>
						<option value="倒三角阵" ${vo.matrixType eq '倒三角阵' ? 'selected':''}>倒三角</option>
					</select>
				</td>
				<th>统计类型：</th>
				<td>
					<select id="sortType" name="sortType">
						<option value="A" ${vo.sortType eq 'A' ? 'selected':''}>不良数</option>
						<option value="B" ${vo.sortType eq 'B' ? 'selected':''}>不良率</option>
					</select>
				</td>
				<th>百台内：</th>
				<td colspan="2">
					<select name="isOver" id="isOver">
						<option value="">全选</option>
						<option value="是" ${vo.isOver eq '否' ? 'selected':''}>是</option>
						<option value="否" ${vo.isOver eq '是' ? 'selected':''}>否</option>
					</select>
					<button type="button" onclick="matrixCheck()">查询</button>
					<button type="button" onclick="exportExcelByDefectMatrix()">导出</button>
					<button type="button" onclick="getDataSourceByMenuName('物料质量统计分析', '市场部分', '市场不良三角阵', '三角阵');">数据来源</button>
				</td>
			</tr>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table id="marketDefectMatrixTdChart" class="fixTable">
		<c:if test="${column!= null || fn:length(column) != 0}">
			<thead>
				<tr>
					<th rowspan="2" style="width:70px;">发货年月</th>
					<th rowspan="2" style="width:70px;">发货数</th>
					<th colspan="${fn:length(column)}">维修月（单月维修数）</th>
				</tr>
				<tr>
					<c:forEach items="${column}" var="m">
						<th style="min-width:70px;">${m}</th>
					</c:forEach>
				</tr>
			</thead>
		</c:if>
		<tbody id = "fixTableBody">
			<c:if test="${vo.matrixType eq '正三角阵'}">
				<c:forEach items="${list}" var="o" begin="0" varStatus="status">
					<tr class="trgins_tr">
					<c:if test="${column!= null || fn:length(column) != 0}">
						<td style="min-width:70px;">${o.baseMonth}</td>
						<td style="min-width:70px;">${o.baseCount}</td>
						<c:if test="${vo.sortType == 'A'}">
					    	<c:forEach items="${o.reCount}"  var="num" varStatus="subStatus">
								<c:if test="${o.preDiff+subStatus.index < fn:length(column)}">
									<c:choose>
										<c:when test="${vo.repairCount!=0 and not empty num and num>= vo.repairCount}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
									    <c:when test="${rangeList[0] <= num and rangeList[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${rangeList[1] < num and rangeList[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${rangeList[2] < num and rangeList[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${rangeList[3] < num and rangeList[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${rangeList[4] < num and rangeList[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${rangeList[5] < num and rangeList[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${rangeList[6] < num and rangeList[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${rangeList[7] < num and rangeList[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${rangeList[8] < num and rangeList[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${rangeList[9] < num and rangeList[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${vo.sortType == 'B'}">
							<c:forEach var="i" begin="1" end="${o.preDiff}">
					    		<td></td>
					    	</c:forEach>
							<c:forEach items="${o.repairPercent}"  var="num" varStatus="subStatus">
								<c:if test="${o.preDiff+subStatus.index < fn:length(column)}">
									<c:choose>
										<c:when test="${vo.repairCount!=0 and not empty num and num>= vo.repairCount}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
									    <c:when test="${rangePercentList[0] <= num and rangePercentList[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${rangePercentList[1] < num and rangePercentList[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${rangePercentList[2] < num and rangePercentList[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${rangePercentList[3] < num and rangePercentList[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${rangePercentList[4] < num and rangePercentList[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${rangePercentList[5] < num and rangePercentList[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${rangePercentList[6] < num and rangePercentList[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${rangePercentList[7] < num and rangePercentList[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${rangePercentList[8] < num and rangePercentList[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${rangePercentList[9] < num and rangePercentList[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
								</c:if>
							</c:forEach>
						</c:if>
					</c:if>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${vo.matrixType eq '倒三角阵'}">
				<c:forEach items="${list}" var="o" begin="0" varStatus="status">
					<tr class="trgins_tr">
						<td style="min-width:70px;">${o.baseMonth}</td>
						<td style="min-width:70px;">${o.baseCount}</td>
						<c:forEach var="i" begin="1" end="${o.preDiff}">
				    		<td></td>
				   		</c:forEach>
						<c:if test="${vo.sortType == 'A'}">
							<c:forEach items="${o.reCount}" var="num" begin="0" end="${fn:length(column)-1}"  varStatus="subStatus">
								<c:choose>
									<c:when test="${vo.repairCount!=0 and not empty num and num>= vo.repairCount}">
										<td style="background: #f8696b">${num}</td>
									</c:when>
								    <c:when test="${rangeList[0] <= num and rangeList[1] >= num}">
										<td style="background: #63be7b">${num}</td>
								    </c:when>
								    <c:when test="${rangeList[1] < num and rangeList[2] >= num}">
										<td style="background: #85c87d">${num}</td>
								    </c:when>
								    <c:when test="${rangeList[2] < num and rangeList[3] >= num}">
										<td style="background: #a8d27f">${num}</td>
								    </c:when>
								    <c:when test="${rangeList[3] < num and rangeList[4] >= num}">
										<td style="background: #cbdc81">${num}</td>
								    </c:when>
								    <c:when test="${rangeList[4] < num and rangeList[5] >= num}">
										<td style="background: #ede683">${num}</td>
								    </c:when>
								    <c:when test="${rangeList[5] < num and rangeList[6] >= num}">
										<td style="background: #ffdd82">${num}</td>
								    </c:when>
								    <c:when test="${rangeList[6] < num and rangeList[7] >= num}">
										<td style="background: #fdc07c">${num}</td>
								    </c:when>
								    <c:when test="${rangeList[7] < num and rangeList[8] >= num}">
										<td style="background: #fca377">${num}</td>
								    </c:when>
								    <c:when test="${rangeList[8] < num and rangeList[9] >= num}">
										<td style="background: #fa8671">${num}</td>
								    </c:when>
								    <c:when test="${rangeList[9] < num and rangeList[10] >= num}">
										<td style="background: #f8696b">${num}</td>
								    </c:when>
								    <c:otherwise>
										<td>${num}</td>
								    </c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
						<c:if test="${vo.sortType == 'B'}">
							<c:forEach items="${o.repairPercent}" var="num" begin="0" end="${fn:length(column)-1}"  varStatus="subStatus">
								<c:choose>
									<c:when test="${vo.repairCount!=0 and not empty num and num>= vo.repairCount}">
										<td style="background: #f8696b">${num}</td>
									</c:when>
								    <c:when test="${rangePercentList[0] <= num and rangePercentList[1] >= num}">
										<td style="background: #63be7b">${num}</td>
								    </c:when>
								    <c:when test="${rangePercentList[1] < num and rangePercentList[2] >= num}">
										<td style="background: #85c87d">${num}</td>
								    </c:when>
								    <c:when test="${rangePercentList[2] < num and rangePercentList[3] >= num}">
										<td style="background: #a8d27f">${num}</td>
								    </c:when>
								    <c:when test="${rangePercentList[3] < num and rangePercentList[4] >= num}">
										<td style="background: #cbdc81">${num}</td>
								    </c:when>
								    <c:when test="${rangePercentList[4] < num and rangePercentList[5] >= num}">
										<td style="background: #ede683">${num}</td>
								    </c:when>
								    <c:when test="${rangePercentList[5] < num and rangePercentList[6] >= num}">
										<td style="background: #ffdd82">${num}</td>
								    </c:when>
								    <c:when test="${rangePercentList[6] < num and rangePercentList[7] >= num}">
										<td style="background: #fdc07c">${num}</td>
								    </c:when>
								    <c:when test="${rangePercentList[7] < num and rangePercentList[8] >= num}">
										<td style="background: #fca377">${num}</td>
								    </c:when>
								    <c:when test="${rangePercentList[8] < num and rangePercentList[9] >= num}">
										<td style="background: #fa8671">${num}</td>
								    </c:when>
								    <c:when test="${rangePercentList[9] < num and rangePercentList[10] >= num}">
										<td style="background: #f8696b">${num}</td>
								    </c:when>
								    <c:otherwise>
										<td>${num}</td>
								    </c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</div>
<div id="showColor">
	<c:if test="${vo.sortType == 'A'}">
		<ul class="col_ul">
			<c:if test="${not empty rangeList}">
				<li class="clo_li" style="background:#63be7b"></li>
				<li>${rangeList[0]}~${rangeList[1]}</li>
				<li class="clo_li" style="background:#85c87d"></li>
				<li>${rangeList[1]}~${rangeList[2]}</li>
				<li class="clo_li" style="background:#a8d27f"></li>
				<li>${rangeList[2]}~${rangeList[3]}</li>
				<li class="clo_li" style="background:#cbdc81"></li>
				<li>${rangeList[3]}~${rangeList[4]}</li>
				<li class="clo_li" style="background:#ede683"></li>
				<li>${rangeList[4]}~${rangeList[5]}</li>
				<li class="clo_li" style="background:#ffdd82"></li>
				<li>${rangeList[5]}~${rangeList[6]}</li>
				<li class="clo_li" style="background:#fdc07c"></li>
				<li>${rangeList[6]}~${rangeList[7]}</li>
				<li class="clo_li" style="background:#fca377"></li>
				<li>${rangeList[7]}~${rangeList[8]}</li>
				<li class="clo_li" style="background:#fa8671"></li>
				<li>${rangeList[8]}~${rangeList[9]}</li>
				<li class="clo_li" style="background:#f8696b"></li>
				<li>${rangeList[9]}~${rangeList[10]}</li>
			</c:if>
		</ul>
	</c:if>
	<c:if test="${vo.sortType == 'B'}">
		<ul class="col_ul">
			<c:if test="${not empty rangePercentList}">
			<li class="clo_li" style="background:#63be7b"></li>
			<li>${rangePercentList[0]}~${rangePercentList[1]}</li>
			<li class="clo_li" style="background:#85c87d"></li>
			<li>${rangePercentList[1]}~${rangePercentList[2]}</li>
			<li class="clo_li" style="background:#a8d27f"></li>
			<li>${rangePercentList[2]}~${rangePercentList[3]}</li>
			<li class="clo_li" style="background:#cbdc81"></li>
			<li>${rangePercentList[3]}~${rangePercentList[4]}</li>
			<li class="clo_li" style="background:#ede683"></li>
			<li>${rangePercentList[4]}~${rangePercentList[5]}</li>
			<li class="clo_li" style="background:#ffdd82"></li>
			<li>${rangePercentList[5]}~${rangePercentList[6]}</li>
			<li class="clo_li" style="background:#fdc07c"></li>
			<li>${rangePercentList[6]}~${rangePercentList[7]}</li>
			<li class="clo_li" style="background:#fca377"></li>
			<li>${rangePercentList[7]}~${rangePercentList[8]}</li>
			<li class="clo_li" style="background:#fa8671"></li>
			<li>${rangePercentList[8]}~${rangePercentList[9]}</li>
			<li class="clo_li" style="background:#f8696b"></li>
			<li>${rangePercentList[9]}~${rangePercentList[10]}</li>
			</c:if>
		</ul>
	</c:if>
</div>
<span id="marketDefectMatrixTd">
	<script type="text/javascript">
		var tmsdFixwidth = $(".pageContent", navTab.getCurrentPanel()).width()-16;
		$("#marketDefectMatrixTdChart",navTab.getCurrentPanel()).attr("width",tmsdFixwidth-26);
		var tmsdFixHeigh = $("#navTab").height() - $(".searchBar", navTab.getCurrentPanel()).height() -66;
		$("#marketDefectMatrixTdChart",navTab.getCurrentPanel()).fixTable({
			fixRow:2,//固定行数
			fixColumn: 2,//固定列数
	        width:tmsdFixwidth,//显示宽度
	        height:tmsdFixHeigh//显示高度
	    });
		$(function(){
			$("#regionMarketMatrixList").dropdownlist({ //区域下拉框显示
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
			
			$("#matrixPartTypesList", navTab.getCurrentPanel()).dropdownlist({
			    id:"partTypesListTxt",
			    columns:2,
			    selectedtext:'',
			    listboxwidth:300,//下拉框宽
			    maxchecked:100,
			    checkbox:true,
			    listboxmaxheight:400,
			    width:120,
			    requiredvalue:[],
			    selected:[],
			    data:${partMap},//数据，格式：{value:name}
			    onchange:function(text,value){
			    }
			});
		});
	</script>
</span>