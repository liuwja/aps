<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
	#fixTableBody .trgins_tr{transition:background 0.5s;-moz-transition:background 0.5s;
	      -webkit-transition:background 0.5s; -o-transition:background 0.5s; }
	#fixTableBody .trgins_tr:hover{background:#A9E6EA;}
	
	#showColor{position:fixed;bottom:10px;left:0px;width:100%;height:20px;}
	#showColor .col_ul{text-align:center;margin-left:200px;background:#666;}
	#showColor .col_ul li{list-style:none;float:left;line-height:20px;}
	#showColor .col_ul .clo_li{width:30px;height:14px;margin:2px 2px 4px 10px;}
	
  #listboxBody  tr:nth-of-type(odd) { background: #eee; }  
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
});

function checkinscSigleCount1() {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
	var insStartTime = $("#insStartTime", navTab.getCurrentPanel()).val();
	var startI = $("#startI", navTab.getCurrentPanel()).val();
	var endI = $("#endI", navTab.getCurrentPanel()).val();
    if(productType==""){
        alertMsg.info("请选择机型类别");
        return false;
    }
    if(insStartTime==""){
    	alertMsg.info("请选择开始时间");
        return false;
    }  
    var curFormDom = $("#trgNewInstallationCount", navTab.getCurrentPanel());
	curFormDom.submit();
	return true;
}		
</script>
<div class="pageHeader" style="position:static">
	<form onsubmit="return navTabSearch(this);" id="trgNewInstallationCount" rel="pagerForm" action="newInstall/trgNewInstallationCount.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
                <th>机型类别：</th>
                <td>
					<select name="productType" onchange="loadProductData('#newInsProdFamilyTmList', '#newInsCountpartTypeList');">
							<option value="">-请选择-</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td>
    			<th>产品系列：</th>
                <td>
                	<div id="newInsProdFamilyTmList" class="dropdownlist"></div>
                </td>	
				<th>故障小类：</th>
    			<td>
    				<input type="hidden" name="faultReasonID" id="inscfr_id" value="${vo.faultReasonID}"/>
    				<input type="hidden" name="faultReasonCode" id="inscfr_code" value="${vo.faultReasonCode}"/>
    				<input type="text" name="faultReasonTxt" id="inscfr_name" size="12" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" style="display:inline-block" onclick="faultReasonSel(this, 'inscfr')" class="btnLook btn" width=950 height=500 lookupGroup="inscfr">故障小类选择</a>  
					<a style="display:inline-block;" class="btnClear" href="javascript:void(0);" onclick="clearFault('inscfr')"  title="清空"></a> 
    			</td>
    			<th>故障小类是否有效：</th>
    			<td>
					<select name="faultReasonValid" id="faultReasonValid" style="float: left;">
						<option value="">全选</option>
						<option <c:if test="${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid=='是'}">selected</c:if> value="是">是</option>
						<option <c:if test="${vo.faultReasonValid=='否'}">selected</c:if> value="否">否</option>
					</select>
    			</td>
    			<td>
    				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="checkinscSigleCount1();">查找</button></div></div>
    				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="getDataSourceByMenuName('市场质量统计分析', '具体分析', '新品安装三角阵', '三角阵');">数据来源</button></div></div>
    			</td>
			</tr>
			<tr>
				<th>筛选条件：</th>                       
                <td>
                    <input id="startI" value="${vo.startI}" name="startI" size="1px" type="text"/>-<input id="endI" value="${vo.endI}" name="endI" size="1px" type="text"/>
                </td>
                <th>型号：</th>
				<td>
					<div id="newInsCountpartTypeList" class="dropdownlist"></div>
				</td>
    			<th>故障大类：</th>
    			<td>
    				<input type="hidden" name="faultTypeID" id="insft_id" readonly="true" value=""/>  
                    <input type="hidden" name="faultTypeCode" id="insft_code" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="insft_name" size="12" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a onclick="faultTypeSel(this, 'insft');" style="display:inline-block;"  id="btn" class="btnLook btn" width=950 height=500 lookupGroup="insft">故障大类选择</a>
                    <a style="display:inline-block;"  class="btnClear" href="javascript:void(0);" onclick="clearFault('insft')" title="清空"></a> 
    			</td>
    			<th>百台内：</th>
				<td>
                	<select name="isOver" id="isOver" style="float: left;">
						<option value="">全选</option>
						<option <c:if test="${vo.isOver=='否'}">selected</c:if> value="否">是</option>
						<option <c:if test="${vo.isOver=='是'}">selected</c:if> value="是">否</option>
					</select>
				</td>
				<th>最大维修数：</th>
				<td>
				<input name="maxCount" size="8" value='<c:if test="${vo.maxCount!=0}">${vo.maxCount}</c:if>'  type="text" />
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="exportExcelByCommon('newInstall/excelOutput_trgNewInstallationCount.do', '#trgNewInstallationCount');">导出</button></div></div>
				</td>
    		</tr>
    		<tr>
    			<th>统计数据：</th>   
                <td>
                	<select name="statisData">
                    	<option value="repairCount" <c:if test="${vo.statisData=='repairCount'}">selected="selected"</c:if>>维修数</option> 	
                    	<option value="repairRate1" <c:if test="${vo.statisData=='repairRate1'}">selected="selected"</c:if>>维修率ppm</option> 	
                    	<option value="repairRate2" <c:if test="${vo.statisData=='repairRate2'}">selected="selected"</c:if>>维修率%</option> 	
					</select>
                </td>
    			<th>区域：</th>
				<td>
					<%-- <input type="text" name="serviceCenter" size="15" value="${vo.serviceCenter} " size="15"/> --%>
					<div id="newInsCountRegionList" class="dropdownlist"></div>
				</td>
				<th>生产日期：</th>
                <td>
                	<input size="6" type="text" id="dlStartTime" name="dlStartTime" placeholder="请输入日期" onclick="laydate()" value="${vo.dlStartTime}" readonly="readonly"/>
                	至
					<input size="6" type="text" id="dlEndTime" name="dlEndTime" placeholder="请输入日期" onclick="laydate()" value="${vo.dlEndTime}" readonly="readonly"/>
    			</td>
				<th>安装年月：</th>
                <td>
                	<input size="6" type="text" id="insStartTime" name="insStartTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.insStartTime }" readonly="readonly"/>
                	至
					<input size="6" type="text" id="insEndTime" name="insEndTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.insEndTime }" readonly="readonly"/>
    			</td>
				<th>合并故障小类：</th>
    			<td>
    				<input type="hidden" name="meshFaultReasonCode" id="insrcmInsNew_meshFaultCode" value="${vo.meshFaultReasonCode}">
    				<input type="text" name="meshFaultName" id="insrcmInsNew_meshname" size="12" readonly="true" value="${vo.meshFaultName}" style="float: left;"/>    				
    				<a id="btn" onclick="meshFaultReasonSelect(this, 'insrcmInsNew')" class="btnLook btn" width=950 height=500 lookupGroup="insrcmInsNew">合并故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearMergeFault('insrcmInsNew')"  title="清空"></a> 
    			</td>
    			<th>VOC分类：</th>
                <td>
                    <input type="hidden" name="vocTypeID" id="VOC_TYPE_NEW_id" size="15" readonly="true" value="${vo.vocTypeID}"/>
                    <input type="hidden" name="vocTypeCode" id="VOC_TYPE_NEW_code" size="15" readonly="true" value="${vo.vocTypeCode}"/>  
                    <input type="text" name="vocTypeTxt" id="VOC_TYPE_NEW_name" size="15" readonly="true" value="${vo.vocTypeTxt}" style="float: left;"/>                  
                    <a onclick="vocCategory(this, 'VOC_TYPE_NEW');" id="btn" class="btnLook btn" href="qms/commonSelect/vocCategory.do?groupName=VOC_TYPE_NEW" width=950 height=500 lookupGroup="VOC_TYPE_NEW">VOC分类选择</a>  
                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('VOC_TYPE_NEW')" ></a>
                </td> 
			</tr>
			<tr>
				<th>气源</th>
				<td>
                	<div id="insGasCategoryList" class="dropdownlist"></div>
                </td>
                <th>是否消耗配件：</th>
				<td>
                	<select id="isConsumedPart" name = "isConsumedPart">
                		<option value="">全选</option>
                		<option value="是" ${vo.isConsumedPart eq '是' ? 'selected' : ''}>是</option>
                		<option value="否" ${vo.isConsumedPart eq '是' ? 'selected' : ''}>否</option>
                	</select>
                </td>
			</tr>
		</table>
	</div>
</form>
</div>
<div>
<div class="pageContent">
	<c:if test="${vo.productType != null and vo.productType!=''}">
	<table id="fixTable1" class="fixTable">
		<thead>
				<th rowspan="2" style="width:70px;">安装年月</th>
				<th rowspan="2" style="width:70px;">安装数</th>
				<th rowspan="2" style="width:70px;">累计安装数</th>

				<c:if test="${vo.statisData=='repairCount'}">
				<c:choose>
				<c:when test="${vo.startI == '' or vo.startI == null}">
					<th rowspan="2" style="width:70px;">M3维修数</th>
					<th rowspan="2" style="width:70px;">M3累计维修数</th>
					<th rowspan="2" style="width:70px;">M6维修数</th>
					<th rowspan="2" style="width:70px;">M6累计维修数</th>
					<th rowspan="2" style="width:70px;">M9维修数</th>
					<th rowspan="2" style="width:70px;">M9累计维修数</th>
					<th rowspan="2" style="width:70px;">M12维修数</th>
					<th rowspan="2" style="width:70px;">M12累计维修数</th>
					</c:when>
				<c:when test="${vo.startI != '' and vo.startI != null}">
					<th rowspan="2" style="width:70px;">M(${vo.startI}-${vo.endI})维修数</th>
					<th rowspan="2" style="width:70px;">M(${vo.startI}-${vo.endI})累计维修数</th>
				</c:when>
				</c:choose>
				</c:if>
				<c:if test="${vo.statisData=='repairRate1' or vo.statisData=='repairRate2'}">
				<c:choose>
				<c:when test="${vo.startI == '' or vo.startI == null}">
					<th rowspan="2" style="width:70px;">M3维修率</th>
					<th rowspan="2" style="width:70px;">M3累计维修率</th>
					<th rowspan="2" style="width:70px;">M6维修率</th>
					<th rowspan="2" style="width:70px;">M6累计维修率</th>
					<th rowspan="2" style="width:70px;">M9维修率</th>
					<th rowspan="2" style="width:70px;">M9累计维修率</th>
					<th rowspan="2" style="width:70px;">M12维修率</th>
					<th rowspan="2" style="width:70px;">M12累计维修率</th>
					</c:when>
				<c:when test="${vo.startI != '' and vo.startI != null}">
					<th rowspan="2" style="width:70px;">M(${vo.startI}-${vo.endI})维修率</th>
					<th rowspan="2" style="width:70px;">M(${vo.startI}-${vo.endI})累计维修率</th>
				</c:when>
				</c:choose>
					
				</c:if>
		</thead>		
		<tbody id="fixTableBody">

			<c:forEach items="${list}" var="o" begin="0" varStatus="status">
				<tr class="trgins_tr">

					<td style="min-width:70px;">${o.baseMonth}</td>
					<td style="min-width:70px;">${o.baseCount}</td>
					<td style="min-width:70px;" >${o.allBaseCount}</td>
					<c:if test="${vo.statisData == 'repairRate1' and (vo.startI == '' or vo.startI == null)}"> <!-- 当选择安装率p时 -->
						<c:forEach items="${o.repairCount}" var="num"  varStatus="subStatus">
								<c:choose>
								<c:when test="${subStatus.count >= 1 and subStatus.count <= 2 and o.l >= 3}">
								<c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
										<c:when test="${maxRepairCount[0] < num and maxRepairCount[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[1] < num and maxRepairCount[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[2] < num and maxRepairCount[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[3] < num and maxRepairCount[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[4] < num and maxRepairCount[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[5] < num and maxRepairCount[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[6] < num and maxRepairCount[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[7] < num and maxRepairCount[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[8] < num and maxRepairCount[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[9] < num and maxRepairCount[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
																								
								</c:when>
								<c:when test="${subStatus.count <= 4 and  subStatus.count > 2 and o.l >= 6}">
								<c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
										<c:when test="${maxRepairCount[0] < num and maxRepairCount[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[1] < num and maxRepairCount[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[2] < num and maxRepairCount[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[3] < num and maxRepairCount[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[4] < num and maxRepairCount[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[5] < num and maxRepairCount[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[6] < num and maxRepairCount[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[7] < num and maxRepairCount[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[8] < num and maxRepairCount[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[9] < num and maxRepairCount[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
									

							    </c:when>
							    <c:when test="${subStatus.count <= 6 and  subStatus.count > 4 and o.l >=9}">
							    <c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
										<c:when test="${maxRepairCount[0] < num and maxRepairCount[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[1] < num and maxRepairCount[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[2] < num and maxRepairCount[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[3] < num and maxRepairCount[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[4] < num and maxRepairCount[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[5] < num and maxRepairCount[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[6] < num and maxRepairCount[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[7] < num and maxRepairCount[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[8] < num and maxRepairCount[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[9] < num and maxRepairCount[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
									
							    </c:when>

							    <c:when test="${subStatus.count <= 8 and  subStatus.count > 6 and o.l >=12}">
									<c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
										<c:when test="${maxRepairCount[0] < num and maxRepairCount[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[1] < num and maxRepairCount[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[2] < num and maxRepairCount[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[3] < num and maxRepairCount[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[4] < num and maxRepairCount[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[5] < num and maxRepairCount[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[6] < num and maxRepairCount[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[7] < num and maxRepairCount[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[8] < num and maxRepairCount[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[9] < num and maxRepairCount[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
							    </c:when>
							</c:choose>
						</c:forEach>
					</c:if>
					
					<c:if test="${vo.statisData == 'repairRate1' and vo.startI != '' and vo.startI != null}"> <!-- 当选择安装率p时 -->
						<c:forEach items="${o.repairCount}" var="num"  varStatus="subStatus">
							     <c:if test="${o.l >= vo.endI}"> 
									<c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
										<c:when test="${maxRepairCount[0] < num and maxRepairCount[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[1] < num and maxRepairCount[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[2] < num and maxRepairCount[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[3] < num and maxRepairCount[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[4] < num and maxRepairCount[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[5] < num and maxRepairCount[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[6] < num and maxRepairCount[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[7] < num and maxRepairCount[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[8] < num and maxRepairCount[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${maxRepairCount[9] < num and maxRepairCount[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
							    </c:if> 
					
						</c:forEach>
					</c:if>
					

					<c:if test="${ vo.statisData == 'repairRate2' and (vo.startI == '' or vo.startI == null)}"> <!-- 当选择安装率%时 -->
						<c:forEach items="${o.repair2Count}" var="num"  varStatus="subStatus">
<!-- 							<td>${num}</td> -->
								<c:choose>
								<c:when test="${subStatus.count <= 2 and o.l >= 3}">
								<c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
										<c:when test="${maxRepair2Count[0] < num and maxRepair2Count[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[1] < num and maxRepair2Count[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[2] < num and maxRepair2Count[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[3] < num and maxRepair2Count[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[4] < num and maxRepair2Count[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[5] < num and maxRepair2Count[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[6] < num and maxRepair2Count[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[7] < num and maxRepair2Count[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[8] < num and maxRepair2Count[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[9] < num and maxRepair2Count[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
								</c:when>

								<c:when test="${subStatus.count <= 4 and  subStatus.count > 2 and o.l >= 6}">
										<c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
										<c:when test="${maxRepair2Count[0] < num and maxRepair2Count[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[1] < num and maxRepair2Count[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[2] < num and maxRepair2Count[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[3] < num and maxRepair2Count[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[4] < num and maxRepair2Count[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[5] < num and maxRepair2Count[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[6] < num and maxRepair2Count[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[7] < num and maxRepair2Count[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[8] < num and maxRepair2Count[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[9] < num and maxRepair2Count[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>

							    </c:when>

							    <c:when test="${subStatus.count <= 6 and  subStatus.count > 4 and o.l >=9}">
										<c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
										<c:when test="${maxRepair2Count[0] < num and maxRepair2Count[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[1] < num and maxRepair2Count[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[2] < num and maxRepair2Count[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[3] < num and maxRepair2Count[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[4] < num and maxRepair2Count[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[5] < num and maxRepair2Count[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[6] < num and maxRepair2Count[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[7] < num and maxRepair2Count[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[8] < num and maxRepair2Count[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[9] < num and maxRepair2Count[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
							    </c:when>

							    <c:when test="${subStatus.count <= 8 and  subStatus.count > 6 and o.l >=12}">
										<c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
										<c:when test="${maxRepair2Count[0] < num and maxRepair2Count[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[1] < num and maxRepair2Count[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[2] < num and maxRepair2Count[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[3] < num and maxRepair2Count[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[4] < num and maxRepair2Count[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[5] < num and maxRepair2Count[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[6] < num and maxRepair2Count[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[7] < num and maxRepair2Count[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[8] < num and maxRepair2Count[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[9] < num and maxRepair2Count[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
							    </c:when>
							</c:choose>
						</c:forEach>
					</c:if>
					<c:if test="${ vo.statisData == 'repairRate2' and vo.startI != '' and vo.startI != null}"> <!-- 当选择安装率%时 -->
						<c:forEach items="${o.repair2Count}" var="num"  varStatus="subStatus">
							    <c:if test="${ o.l >=vo.endI}">
										<c:choose>
										<c:when test="${vo.repairPercent!=0 and not empty num and num>= vo.repairPercent}">
											<td style="background: #f8696b">${num}</td>
										</c:when>
										<c:when test="${maxRepair2Count[0] < num and maxRepair2Count[1] >= num}">
											<td style="background: #63be7b">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[1] < num and maxRepair2Count[2] >= num}">
											<td style="background: #85c87d">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[2] < num and maxRepair2Count[3] >= num}">
											<td style="background: #a8d27f">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[3] < num and maxRepair2Count[4] >= num}">
											<td style="background: #cbdc81">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[4] < num and maxRepair2Count[5] >= num}">
											<td style="background: #ede683">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[5] < num and maxRepair2Count[6] >= num}">
											<td style="background: #ffdd82">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[6] < num and maxRepair2Count[7] >= num}">
											<td style="background: #fdc07c">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[7] < num and maxRepair2Count[8] >= num}">
											<td style="background: #fca377">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[8] < num and maxRepair2Count[9] >= num}">
											<td style="background: #fa8671">${num}</td>
									    </c:when>
									    <c:when test="${maxRepair2Count[9] < num and maxRepair2Count[10] >= num}">
											<td style="background: #f8696b">${num}</td>
									    </c:when>
									    <c:otherwise>
											<td>${num}</td>
									    </c:otherwise>
									</c:choose>
							    </c:if>

						</c:forEach>
					</c:if>
					<c:if test="${vo.statisData != 'repairRate1' and vo.statisData != 'repairRate2' and (vo.startI == '' or vo.startI == null)}"> <!-- 当选择安装数时 -->
						<c:forEach items="${o.reCount}" var="num"  varStatus="subStatus">
<!-- 							<td>${num}</td> -->
							<c:choose>
								<c:when test="${subStatus.count <= 2 and o.l >= 3}">
									<td class="newI" style="background: #63be7b">${num}</td>
								</c:when>
								<c:when test="${subStatus.count <= 4 and  subStatus.count > 2 and o.l >= 6}">
									<td class="newI" style="background: #ffdd82">${num}</td>

							    </c:when>
							    <c:when test="${subStatus.count <= 6 and  subStatus.count > 4 and o.l >=9}">
									<td class="newI" style="background: #ede683">${num}</td>
							    </c:when>
							    <c:when test="${subStatus.count <= 8 and  subStatus.count > 6 and o.l >=12}">
									<td class="newI" style="background: #fa8671">${num}</td>
							    </c:when>
							</c:choose>
						</c:forEach>
					</c:if>
					<c:if test="${vo.statisData != 'repairRate1' and vo.statisData != 'repairRate2' and vo.startI != '' and vo.startI != null}"> <!-- 当选择安装数时 -->
						<c:forEach items="${o.reCount}" var="num"  varStatus="subStatus">

							    <c:if test="${ o.l >=vo.endI}">
									<td class="newI" style="background: #fa8671">${num}</td>
							    </c:if>
						</c:forEach>
					</c:if>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	</c:if>
	</div>
<div id="showColor" style="bottom: 30px">
<ul class="col_ul" >


 		<li ><table  border="1"><tr><td id="new4"></td><td id="new5"></td><td id="new6"></td><td id="new7"></td><td id="new8"></td><td id="new9"></td><td id="new10"></td><td id="new11"></td></tr></table></li>
			

</ul>
<br/>
<br/>
	<c:if test="${vo.statisData eq 'repairRate1'}">
		<ul class="col_ul">
			<c:if test="${not empty maxRepairCount}">
			<li class="clo_li" style="background:#63be7b"></li>
			<li>${maxRepairCount[0]}~${maxRepairCount[1]}</li>
			<li class="clo_li" style="background:#85c87d"></li>
			<li>${maxRepairCount[1]}~${maxRepairCount[2]}</li>
			<li class="clo_li" style="background:#a8d27f"></li>
			<li>${maxRepairCount[2]}~${maxRepairCount[3]}</li>
			<li class="clo_li" style="background:#cbdc81"></li>
			<li>${maxRepairCount[3]}~${maxRepairCount[4]}</li>
			<li class="clo_li" style="background:#ede683"></li>
			<li>${maxRepairCount[4]}~${maxRepairCount[5]}</li>
			<li class="clo_li" style="background:#ffdd82"></li>
			<li>${maxRepairCount[5]}~${maxRepairCount[6]}</li>
			<li class="clo_li" style="background:#fdc07c"></li>
			<li>${maxRepairCount[6]}~${maxRepairCount[7]}</li>
			<li class="clo_li" style="background:#fca377"></li>
			<li>${maxRepairCount[7]}~${maxRepairCount[8]}</li>
			<li class="clo_li" style="background:#fa8671"></li>
			<li>${maxRepairCount[8]}~${maxRepairCount[9]}</li>
			<li class="clo_li" style="background:#f8696b"></li>
			<li>${maxRepairCount[9]}~${maxRepairCount[10]}</li>
			</c:if>
		</ul>
	</c:if>
	<c:if test="${vo.statisData eq 'repairRate2'}">
		<ul class="col_ul">
			<c:if test="${not empty maxRepair2Count}">
			<li class="clo_li" style="background:#63be7b"></li>
			<li>${maxRepair2Count[0]}~${maxRepair2Count[1]}</li>
			<li class="clo_li" style="background:#85c87d"></li>
			<li>${maxRepair2Count[1]}~${maxRepair2Count[2]}</li>
			<li class="clo_li" style="background:#a8d27f"></li>
			<li>${maxRepair2Count[2]}~${maxRepair2Count[3]}</li>
			<li class="clo_li" style="background:#cbdc81"></li>
			<li>${maxRepair2Count[3]}~${maxRepair2Count[4]}</li>
			<li class="clo_li" style="background:#ede683"></li>
			<li>${maxRepair2Count[4]}~${maxRepair2Count[5]}</li>
			<li class="clo_li" style="background:#ffdd82"></li>
			<li>${maxRepair2Count[5]}~${maxRepair2Count[6]}</li>
			<li class="clo_li" style="background:#fdc07c"></li>
			<li>${maxRepair2Count[6]}~${maxRepair2Count[7]}</li>
			<li class="clo_li" style="background:#fca377"></li>
			<li>${maxRepair2Count[7]}~${maxRepair2Count[8]}</li>
			<li class="clo_li" style="background:#fa8671"></li>
			<li>${maxRepair2Count[8]}~${maxRepair2Count[9]}</li>
			<li class="clo_li" style="background:#f8696b"></li>
			<li>${maxRepair2Count[9]}~${maxRepair2Count[10]}</li>
			</c:if>
		</ul>
	</c:if>
</div>
</div>
		

<script type="text/javascript">
	$(function(){
		loadProductFamilyData("#newInsProdFamilyTmList", "productFamilyTxt", [${vo.productFamilyTxt}], ${jsonProFamily});
    	loadProductTypeData("#newInsCountpartTypeList", "partTypeListTxt", [${vo.partTypeListTxt}], ${jsonParts});
     	loadRegionData("#newInsCountRegionList", "regionListTxt", [${vo.regionListTxt}], ${jsonRegions});
     	loadGasTypeData("#insGasCategoryList", "gasCategoryTxt", [${vo.gasCategoryTxt}], ${jsonGas});
     	
     	/* $("#fixTable1 tr td[class=newI]").each(function(){
     		var val = Number($(this).text());
     		alert(val);
     	}) */
     	if($('select[name="productType"]', navTab.getCurrentPanel()).val()!=null){
	     	for(var i=4;i<12;i++){
	     	$("#new"+i).html($("#fixTable1 tr").find("td:nth-child("+i+")").last().html());
	     	}
     	}
	});
	
	var tmsFixwidth = $(".pageContent", navTab.getCurrentPanel()).width()-16;
	$("#fixTable1",navTab.getCurrentPanel()).attr("width",tmsFixwidth-26);
	var tmsFixHeigh = $("#navTab").height() - $(".searchBar", navTab.getCurrentPanel()).height()-95;
	$("#fixTable1",navTab.getCurrentPanel()).fixTable({
		fixRow:1,//固定行数
        width:tmsFixwidth,//显示宽度
        height:tmsFixHeigh//显示高度
    });
</script>