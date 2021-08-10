<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
	#showColor{position:fixed;bottom:10px;left:0px;width:100%;height:20px;}
	#showColor .col_ul{text-align:center;margin-left:200px;background:#666;}
	#showColor .col_ul li{list-style:none;float:left;line-height:20px;}
	#showColor .col_ul .clo_li{width:30px;height:14px;margin:2px 2px 4px 10px;}
	
  #listboxBody  tr:nth-of-type(odd) { background: #eee; }  
 
</style>
<script type="text/javascript">
function checkSigleMonthData() {
	var productType = $("select[name='productType']", navTab.getCurrentPanel()).val();
    if(productType==""){
    	alertMsg.info("请选择机型类别");
        return false;
    }
    var startTime = $("#startTime", navTab.getCurrentPanel()).val();
    var endTime = $("#endTime", navTab.getCurrentPanel()).val();
    if (startTime == null || startTime == "" || endTime == null || endTime == "") {
    	alertMsg.info("请选择生产日期");
    	return false;
    }
    var repairCount = $("#repairCount", navTab.getCurrentPanel()).val();
    if(repairCount!=null && repairCount!=""){
    	var reg = new RegExp("^-?\\d+$");
    	if(repairCount.match(reg)==null){
    		alertMsg.info("最大维修数请输入整数值!");
            return false;
        }
    }
	var curFormDom = $("#timeMatrixForSingle", navTab.getCurrentPanel());
	var matrixTableType = $("#matrixTableType", navTab.getCurrentPanel()).val();
	if(matrixTableType == "倒三角") {
		curFormDom.attr("action", "timeMatrixTable/sigleDownMonthReCount.do");
	}
	curFormDom.submit();
	return true;
}

function exportTimeMatrixTableExcel() {
	var matrixTableType = $("#matrixTableType", navTab.getCurrentPanel()).val();
	if(matrixTableType == "正三角") {
		exportExcelByCommon("timeMatrixTable/excelOutput_sigleMonthReCount.do", "#timeMatrixForSingle");
	} else {
		exportExcelByCommon("timeMatrixTable/excelOutput_sigleDownMonthReCount.do", "#timeMatrixForSingle");
	}
}
</script>
<div class="pageHeader" style="position:static">
	<form onsubmit="return navTabSearch(this);" id="timeMatrixForSingle" rel="pagerForm" action="timeMatrixTable/sigleMonthReCount.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr style="line-height: 28px">
                <th>机型类别：</th>
                <td>
					<select name="productType" onchange="loadProductData('#prodFamilySmList', '#partTypeMtList');">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType}" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td>
    			<th>产品系列：</th>
                <td>
                	<div id="prodFamilySmList" class="dropdownlist"></div>
                </td>
    			<th>型号：</th>                       
                <td>
                	<div id="partTypeMtList" class="dropdownlist"></div>
                </td> 	
                <th>故障小类：</th>
    			<td>
    				<input type="hidden" id="SMRC_id" size="15" name="faultReasonID" readonly="true" value="${vo.faultReasonID}"/>  
    				<input type="hidden" name="faultReasonCode" id="SMRC_code" size="15" readonly="true" value="${vo.faultReasonCode}"/>  
    				<input type="text" name="faultReasonTxt" id="SMRC_name" size="15" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" onclick="faultReasonSel(this, 'SMRC')" class="btnLook btn" href="qms/commonSelect/faultReasonSelect.do?groupName=SMRC" width=980 height=500 lookupGroup="SMRC">故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearFault('SMRC')" ></a> 
    			</td>
                <th>统计方式：</th>                       
                <td>
                    <select name="statisType">
                    	<option value="month" <c:if test="${'month' eq vo.statisType}">selected="selected"</c:if> >月份</option> 	
                    	<option value="quarter" <c:if test="${'quarter' eq vo.statisType}">selected="selected"</c:if> >季度</option> 	
                    	<option value="year" <c:if test="${'year' eq vo.statisType}">selected="selected"</c:if> >年份</option> 	
					</select>
                </td>
                <td>
                	<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="getDataSourceByMenuName('市场质量统计分析', '具体分析', '时间序列分析(正三角)', '三角阵');">数据来源</button></div></div>	   
            	</td>
            </tr>
            <tr style="line-height: 28px"> 
                <th>工厂：</th>
                <td>
					<select name="factory" id="factory" onchange="loadProductLine('#plineMtList');">
							<option value="">请选择</option>
							<c:forEach items="${factorys}" var="o">
							<option value="${o.factory }" <c:if test="${vo.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
							</c:forEach>
					</select>
    			</td>
    			<th>产线：</th>                       
                <td>
                	<div id="plineMtList" class="dropdownlist"></div>
                </td>
                <th>故障大类：</th>
                <td>
                    <input type="hidden" name="faultTypeID" id="SMRC_TYPE_id" size="15" readonly="true" value="${vo.faultTypeID}"/>  
                    <input type="hidden" name="faultTypeCode" id="SMRC_TYPE_code" size="15" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="SMRC_TYPE_name" size="15" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a onclick="faultTypeSel(this, 'SMRC_TYPE');" id="btn" class="btnLook btn" href="qms/commonSelect/faultTypeSelect.do?groupName=SMRC_TYPE" width=950 height=500 lookupGroup="SMRC_TYPE">故障大类选择</a>  
                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('SMRC_TYPE')" ></a>
                </td>   
				<td colspan="2">
					故障小类是否有效：
					<select name="faultReasonValid" id="faultReasonValid">
						<option value="">全选</option>
						<option <c:if test="${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid=='是'}">selected</c:if> value="是">是</option>
						<option <c:if test="${vo.faultReasonValid=='否'}">selected</c:if> value="否">否</option>
					</select>
				</td>               
				<th>最大维修数：</th> 
				<td>
					<input type="text" id="maxCount" name="maxCount" size="8" value='<c:if test="${vo.maxCount!=0}">${vo.maxCount}</c:if>'/>
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="exportTimeMatrixTableExcel();">导出</button></div></div>
				</td>
			</tr>
			<tr style="line-height: 28px">
                <th>区域：</th>                       
                <td>
                    <div id="sigmonRegionMtList" class="dropdownlist"></div>
                </td>	
              
                <th>生产年月：</th>
                <td>
                    <input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime}" readonly="readonly" size="8"/> 
                    	至 
                    <input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime }" readonly="readonly" size="8"/>                  
                </td> 
                <th>合并故障小类：</th>
    			<td>
    				<input type="hidden" name="meshFaultReasonCode" id="SMRCM_meshFaultCode" value="${vo.meshFaultReasonCode}">
    				<input type="text" name="meshFaultName" id="SMRCM_meshname" size="15" readonly="true" value="${vo.meshFaultName}" style="float: left;"/>    				
					<a id="btn" onclick="meshFaultReasonSelect(this, 'SMRCM')" class="btnLook btn" href="qms/commonSelect/meshFaultReasonSelect.do?groupName=SMRCM" width=980 height=500 lookupGroup="SMRCM">合并故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearMergeFault('SMRCM')"  title="清空"></a> 
    			</td>   
    			<th>百台内：</th>
				<td>
                	<select name="isOver" id="isOver">
						<option value="">全选</option>
						<option <c:if test="${vo.isOver=='否'}">selected</c:if> value="否">是</option>
						<option <c:if test="${vo.isOver=='是'}">selected</c:if> value="是">否</option>
					</select>
					三角阵类型：
					<select id="matrixTableType" name="matrixTableType">
						<option value="正三角" ${vo.matrixTableType == '正三角' ? 'selected' : ''}>正三角</option>
						<option value="倒三角" ${vo.matrixTableType == '倒三角' ? 'selected' : ''}>倒三角</option>
					</select>
				</td>
				<th>统计数据：</th>   
                <td>
					<select id="smStatisData" name="statisData">
						<option value="repairCount" selected="selected">维修数</option> 	
                    	<option value="repairRate" <c:if test="${vo.statisData=='repairRate'}">selected="selected"</c:if> >维修率</option>
					</select>
                </td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="checkSigleMonthData()">查找</button></div></div>
    			</td> 	
			</tr>
			<tr>
				<th>气源</th>
				<td>
                	<div id="gasCategoryList" class="dropdownlist"></div>
                </td>
				 <th>VOC分类：</th>
                <td>
                    <input type="hidden" name="vocTypeID" id="VOC_TYPE_TIME_id" size="15" readonly="true" value="${vo.vocTypeID}"/>
                    <input type="hidden" name="vocTypeCode" id="VOC_TYPE_TIME_code" size="15" readonly="true" value="${vo.vocTypeCode}"/>  
                    <input type="text" name="vocTypeTxt" id="VOC_TYPE_TIME_name" size="15" readonly="true" value="${vo.vocTypeTxt}" style="float: left;"/>                  
                    <a onclick="vocCategory(this, 'VOC_TYPE_TIME');" id="btn" class="btnLook btn" href="qms/commonSelect/vocCategory.do?groupName=VOC_TYPE_TIME" width=950 height=500 lookupGroup="VOC_TYPE_TIME">VOC分类选择</a>  
                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('VOC_TYPE_TIME')" ></a>
                </td> 
                <th>是否消耗配件：</th>
                <td>
                    <select id="isConsumedPart" name = "isConsumedPart">
                    	<option value="">全选</option>
                    	<option value="是" ${vo.isConsumedPart == '是' ? 'selected' : ''}>是</option>
                    	<option value="否" ${vo.isConsumedPart == '否' ? 'selected' : ''}>否</option>
                    </select>
                </td>
			</tr>
		</table>
	</div>
	</form>
</div>
<script type="text/javascript">
	$(function(){
		loadProductFamilyData("#prodFamilySmList", "productFamilyTxt", [${vo.productFamilyTxt}], ${jsonProFamily});
  		loadProductTypeData("#partTypeMtList", "partTypeListTxt", [${vo.partTypeListTxt}], ${jsonParts});
  		loadRegionData("#sigmonRegionMtList", "regionListTxt", [${vo.regionListTxt}], ${jsonRegions});
  		loadProductLineData("#plineMtList", "plineListTxt", [${vo.plineListTxt}], ${jsonLines});
  		loadGasTypeData("#gasCategoryList", "gasCategoryTxt", [${vo.gasCategoryTxt}], ${jsonGas});
	});
</script>
<div class="pageContent" id="tabContent">
	<c:choose>
		<c:when test="${vo.matrixTableType eq '倒三角'}">
			<table id="fixTable" class="fixTable">
		 		<c:if test="${column!= null || fn:length(column) != 0}">
      	 			<thead>
						<tr>
							<th rowspan="2" style="min-width:70px;">生产年月</th>
							<th rowspan="2" style="min-width:70px;">生产台数</th>
							<th colspan="${fn:length(column)}">维修月（单月维修数）</th>
						</tr>
						<tr>
							<c:forEach items="${column}" var="m">
								<th style="min-width:80px;">${m}</th>
							</c:forEach>
						</tr>
					</thead>
				</c:if>
        		<tbody id="fixTableBody">
        			<c:if test="${vo.statisData eq 'repairCount'}">
        				<c:forEach items="${list}" var="o" begin="0" varStatus="status">
							<tr>
								<td style="min-width:70px;">${o.baseMonth}</td>
								<td style="min-width:70px;">${o.baseCount}</td>
								<c:forEach var="i" begin="1" end="${o.preDiff}">
				    				<td></td>
				   				</c:forEach>
								<c:forEach items="${o.reCount}" var="num" varStatus="subStatus">
									<c:if test="${o.preDiff+subStatus.index < fn:length(column)}">
										<c:choose>
											<c:when test="${vo.repairCount!=0 and not empty num and num>= vo.repairCount}">
												<td style="background: #f8696b">${num}</td>
											</c:when>
										    <c:when test="${rangeList[0] < num and rangeList[1] >= num}">
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
							</tr>		
						</c:forEach>
       				</c:if>
        			<c:if test="${vo.statisData eq 'repairRate'}">
        				<c:forEach items="${list}" var="o" begin="0" varStatus="status">
							<tr>
								<td>${o.baseMonth}</td>
								<td>${o.baseCount}</td>
								<c:forEach var="i" begin="1" end="${o.preDiff}">
							    	<td></td>
							    </c:forEach>
								<c:forEach items="${o.repairPercent}" var="num" varStatus="subStatus">
									<c:if test="${o.preDiff+subStatus.index < fn:length(column)}">
										<c:choose>
											<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
												<td style="background: #f8696b">${num}</td>
											</c:when>
										    <c:when test="${rangePercentList[0] < num and rangePercentList[1] >= num}">
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
							</tr>		
						</c:forEach>
       				</c:if>
       			</tbody>
   			</table>
		</c:when>
		<c:otherwise>
			<table id="fixTable" class="fixTable" width="1200">
      	 		<thead>
					<tr>
						<th rowspan="2" style="min-width:70px;">生产年月</th>
						<th rowspan="2" style="min-width:70px;">生产台数</th>
						<th colspan="${columnSize}">
							<c:choose>
								<c:when test="${vo.statisType=='year'}">统计方式：年</c:when>
								<c:when test="${vo.statisType=='quarter'}">统计方式：季度</c:when>
								<c:otherwise>统计方式：月</c:otherwise>
							</c:choose>
						</th>
					</tr>
					<tr>
						<c:forEach items="${columnNo}" var="cul">
							<th>${cul}</th>
						</c:forEach>
					</tr>
				</thead>
        		<tbody id="fixTableBody">
					<c:if test="${vo.statisData eq 'repairCount'}">
						<c:forEach items="${list}" var="o" begin="0" varStatus="status">
							<tr>
								<td>${o.baseMonth}</td>
								<td>${o.baseCount}</td>
								<c:forEach items="${o.reCount}" var="num">
									<c:choose>
										<c:when test="${vo.repairCount!=0 and not empty num and num>= vo.repairCount}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
									    <c:when test="${rangeList[0] < num and rangeList[1] >= num}">
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
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${vo.statisData eq 'repairRate'}">
						<c:forEach items="${list}" var="o" begin="0" varStatus="status">
							<tr>
								<td>${o.baseMonth}</td>
								<td>${o.baseCount}</td>
								<c:forEach items="${o.repairPercent}" var="num">
									<c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
									    <c:when test="${rangePercentList[0] < num and rangePercentList[1] >= num}">
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
							</tr>		
						</c:forEach>
					</c:if>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</div>	
<div id="showColor">
	<c:if test="${vo.statisData eq 'repairCount'}">
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
	<c:if test="${vo.statisData eq 'repairRate'}">
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
<script>
	var smrFixwidth = $(".pageContent", navTab.getCurrentPanel()).width()-16;
	var smrFixHeigh = $("#navTab").height() - $(".searchBar", navTab.getCurrentPanel()).height() -66;
	$("#fixTable",navTab.getCurrentPanel()).fixTable({
		fixRow:2,//固定行数
        fixColumn: 2,//固定列数
        width:smrFixwidth,//显示宽度
        height:smrFixHeigh//显示高度
    });
</script>