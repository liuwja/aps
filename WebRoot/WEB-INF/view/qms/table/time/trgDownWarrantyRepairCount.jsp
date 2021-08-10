<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css">
	${demo.css}
	#fixTableBody .trginsd_tr{transition:background 0.5s;-moz-transition:background 0.5s;
	      -webkit-transition:background 0.5s; -o-transition:background 0.5s; }
	#fixTableBody .trginsd_tr:hover{background:#A9E6EA;}
		#showColor{position:fixed;bottom:10px;left:0px;width:100%;height:20px;}
	#showColor .col_ul{text-align:center;margin-left:200px;background:#666;}
	#showColor .col_ul li{list-style:none;float:left;line-height:20px;}
	#showColor .col_ul .clo_li{width:30px;height:14px;margin:2px 2px 4px 10px;}
	
  #listboxBody  tr:nth-of-type(odd) { background: #eee; }  
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
});

function checkinscdSigleCount2() {
	var productType = $('select[name="productType"]', navTab.getCurrentPanel()).val();
	var insStartTime = $("#insStartTime", navTab.getCurrentPanel()).val();
    if(productType==""){
        alertMsg.info("请选择机型类别");
        return false;
    }
    if(insStartTime==""){
    	alertMsg.info("请选择开始时间");
        return false;
    }
     var matrixTableType = $('select[name="matrixTableType"]',navTab.getCurrentPanel()).val();
    if(matrixTableType == 'Inverted' ){
    	$("#trgDownMatrixReTotalCount").attr("action","warrantyRepair/trgDownWarrantyRepairCount.do");
    }else{
    	$("#trgDownMatrixReTotalCount").attr("action","warrantyRepair/trgWarrantyRepairCount.do");
    }
	$("#trgDownMatrixReTotalCount", navTab.getCurrentPanel()).submit();
	return true;
}		
</script>
<div class="pageHeader" style="position:static">
	<form onsubmit="return navTabSearch(this);" id="trgDownMatrixReTotalCount" rel="pagerForm" action="warrantyRepair/trgDownWarrantyRepairCount.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
                <td style="text-align: center; width: 60px">机型类别：</td>
                <td style="width: 80px">
					<select name="productType" onchange="loadProductData('#prodFamilyTmdList2', '#trginsCpartTypeDownList2');">
							<option value="">-请选择-</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }" ${vo.productType eq o.machineType ? 'selected':''}>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td>
    			<td style="text-align: center; width: 60px">产品系列：</td>
                <td style="width: 120px">
                	<div id="prodFamilyTmdList2" class="dropdownlist"></div>
                </td>
                <td style="text-align: center; width: 84px">故障小类：</td>
    			<td style="width: 153px">
    				<input type="hidden" name="faultTypeID" id="inscdfr_id" value="${vo.faultTypeID}"/>  
    				<input type="hidden" name="faultReasonCode" id="inscdfr_code" value="${vo.faultReasonCode}"/>  
    				<input type="text"   name="faultReasonTxt" id="inscdfr_name" size="12" readonly="true" value="${vo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" style="display:inline-block" onclick="faultReasonSel(this, 'inscdfr')" class="btnLook btn" width=950 height=500 lookupGroup="inscdfr">故障小类选择</a>  
					<a style="display:inline-block;" class="btnClear" href="javascript:void(0);" onclick="clearFault('inscdfr')"  title="清空"></a> 
    			</td>
    			<td style="text-align: center; width: 80px">故障小类是否有效：</td>
    			<td>
					<select name="faultReasonValid" id="faultReasonValid" style="float: left;">
						<option value="">全选</option>
						<option <c:if test="${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid=='是'}">selected</c:if> value="是">是</option>
						<option <c:if test="${vo.faultReasonValid=='否'}">selected</c:if> value="否">否</option>
					</select>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="checkinscdSigleCount2();">查找</button></div></div>
    			</td>
    			<td>
    				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="getDataSourceByMenuName('市场质量统计分析', '具体分析', '保内维修分析(倒三角)', '三角阵');">数据来源</button></div></div>
    			</td>
			</tr>
			<tr>
				<td style="text-align: center;">统计方式：</td>                       
                <td style="width: 75px">
                    <select name="statisType">
                    	<option value="month" <c:if test="${'month' eq vo.statisType}">selected="selected"</c:if> >月份</option> 	
                    	<!--  <option value="quarter" <c:if test="${'quarter' eq vo.statisType}">selected="selected"</c:if> >季度</option> 	-->
                    	<option value="year" <c:if test="${'year' eq vo.statisType}">selected="selected"</c:if> >年份</option> 	
					</select>
                </td>
                <td style="text-align: center;">型号： </td>
				<td>
					<div id="trginsCpartTypeDownList2" class="dropdownlist"></div>
				</td>
				<td style="text-align: center;">故障大类：</td>
    			<td>
    				<input type="hidden" name="faultTypeID" id="inscdft_id" readonly="true" value="${vo.faultTypeID}"/>  
                    <input type="hidden" name="faultTypeCode" id="inscdft_code" readonly="true" value="${vo.faultTypeCode}"/>  
                    <input type="text" name="faultTypeTxt" id="inscdft_name" size="12" readonly="true" value="${vo.faultTypeTxt}" style="float: left;"/>                  
                    <a onclick="faultTypeSel(this, 'inscdft');" style="display:inline-block;"  id="btn" class="btnLook btn" width=950 height=500 lookupGroup="inscdft">故障大类选择</a>
                    <a style="display:inline-block;"  class="btnClear" href="javascript:void(0);" onclick="clearFault('inscdft')" title="清空"></a> 
    			</td>
    			<td style="text-align: center;">百台内：</td>
				<td>
                	<select name="isOver" id="isOver" style="float: left;">
						<option value="">全选</option>
						<option <c:if test="${vo.isOver=='否'}">selected</c:if> value="否">是</option>
						<option <c:if test="${vo.isOver=='是'}">selected</c:if> value="是">否</option>
					</select>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="exportExcelByCommon('warrantyRepair/excelOutput_trgDownWarrantyRepairCount.do', '#trgDownMatrixReTotalCount');">导出</button></div></div>
				</td>
    		</tr>
    		<tr>
                <td style="text-align: center;">统计数据：</td>
                <td>
                	<select name="statisData">
                    	<option value="repairCount" selected="selected">维修数</option> 	
                    	<option value="repairRate" <c:if test="${vo.statisData=='repairRate'}">selected="selected"</c:if> >维修率</option> 	
					</select>
                </td>
                <td style="text-align: center;">区域： </td>
				<td>
					<%-- <input type="text" name="serviceCenter" size="15" value="${vo.serviceCenter} " size="15"/> --%>
					<div id="trginsCountRegionDownList2" class="dropdownlist"></div>
				</td>
				<td style="text-align: center;">安装年月：</td>
                <td>
                	<input size="6" type="text" id="insStartTime" name="insStartTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.insStartTime }" readonly="readonly"/>
                	至
					<input size="6" type="text" id="insEndTime" name="insEndTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.insEndTime }" readonly="readonly"/>
    			</td>
				<td style="text-align: center;">合并故障小类：</td>
    			<td>
    				<input type="hidden" name="meshFaultReasonCode" id="insrcdm_meshFaultCode" value="${vo.meshFaultReasonCode}">
    				<input type="text" name="meshFaultName" id="insrcdm_meshname" size="12" readonly="true" value="${vo.meshFaultName}" style="float: left;"/>    				
    				<a id="btn" onclick="meshFaultReasonSelect(this, 'insrcdm')" class="btnLook btn" href="qms/commonSelect/meshFaultReasonSelect.do?groupName=insrcdm" width=950 height=500 lookupGroup="insrcdm">合并故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearMergeFault('insrcdm')"  title="清空"></a> 
    			</td>
			</tr>
			<tr>
				<td>三角阵：</td>
                <td>
                	<select name="matrixTableType">
                    	<option value="regular" <c:if test="${vo.matrixTableType=='regular'}">selected="selected"</c:if>>正三角</option> 	
                    	<option value="Inverted" <c:if test="${vo.matrixTableType=='Inverted'}">selected="selected"</c:if>>倒三角</option> 	
					</select>
                </td>
			</tr>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table id="fixTable2" class="fixTable">
		<c:if test="${column!= null || fn:length(column) != 0}">
		<thead>
			<tr>
				<th rowspan="2" style="width:70px;">安装年月</th>
				<th rowspan="2" style="width:70px;">安装数</th>
<!-- 				<th colspan="${fn:length(column)}">维修月（单月维修数）</th> -->
				<c:if test="${vo.statisData=='repairCount'}">
					<th colspan="${fn:length(column)}">保内维修数</th>
				</c:if>
				<c:if test="${vo.statisData=='repairRate'}">
					<th colspan="${fn:length(column)}">保内维修率</th>
				</c:if>
			</tr>
			<tr >
				<c:forEach items="${column}" var="m">
					<th style="min-width:70px;">${m}</th>
				</c:forEach>
			</tr>
		</thead>
		</c:if>
		<tbody id="fixTableBody">
			<c:forEach items="${list}" var="o" begin="0" varStatus="status">
				<tr class="trginsd_tr">
					<td style="min-width:70px;">${o.baseMonth}</td>
					<td style="min-width:70px;">${o.baseCount}</td>
					<c:if test="${vo.statisData == 'repairRate'}"> <!-- 当选择安装率时 -->
						<c:forEach var="i" begin="1" end="${o.preDiff}">
				    		<td></td>
				    	</c:forEach>
						<c:forEach items="${o.repairPercent}"  var="num" varStatus="subStatus">
							<c:if test="${o.preDiff+subStatus.index < fn:length(column)}">
<!-- 								<td>${num}</td> -->
								<c:choose>
									<c:when test="${num eq '0'}">
										<td style="background: #63be7b">${num}</td>
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
					</c:if>
					<c:if test="${vo.statisData != 'repairRate'}"> <!-- 当选择安装数时 -->
						<c:forEach var="i" begin="1" end="${o.preDiff}">
				    		<td></td>
				    	</c:forEach>
						<c:forEach items="${o.reCount}"  var="num" varStatus="subStatus">
							<c:if test="${o.preDiff+subStatus.index < fn:length(column)}">
<!-- 								<td>${num}</td> -->
								<c:choose>
									<c:when test="${num eq '0'}">
										<td style="background: #63be7b">${num}</td>
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
					</c:if>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
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
<script type="text/javascript">
	$(function(){
		loadProductFamilyData("#prodFamilyTmdList2", "productFamilyTxt", [${vo.productFamilyTxt}], ${jsonProFamily});
   		loadProductTypeData("#trginsCpartTypeDownList2", "partTypeListTxt", [${vo.partTypeListTxt}], ${jsonParts});
   		loadRegionData("#trginsCountRegionDownList2", "regionListTxt", [${vo.regionListTxt}], ${jsonRegions});
	});
       
	var tmsdFixwidth = $(".pageContent", navTab.getCurrentPanel()).width()-16;
	$("#fixTable2",navTab.getCurrentPanel()).attr("width",tmsdFixwidth-26);
	var tmsdFixHeigh = $("#navTab").height() - $(".searchBar", navTab.getCurrentPanel()).height() -66;
	$("#fixTable2",navTab.getCurrentPanel()).fixTable({
		fixRow:2,//固定行数
		fixColumn: 2,//固定列数
        width:tmsdFixwidth,//显示宽度
        height:tmsdFixHeigh//显示高度
    });
</script>