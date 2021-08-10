<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader" style="position:static">
	<form id="marketRepairForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/repairRecord/repairRecord.do" method="post">
	<div class="searchBar" style="">
		<table class="searchContent " width="130%">
			<tr style="line-height: 28px">
				<td >机型类别： </td>
				<td >
					<select name="productType" id="productType" onchange="loadProductData('#markProductFamilyComList', '#markpartTypeList');">
						<option value="">请选择</option>
						<c:forEach items="${productTypes}" var="t">
							<option value="${t.machineType}">${t.machineType}</option>
						</c:forEach>
					</select>
					<script type="text/javascript">
						$("#productType", navTab.getCurrentPanel()).val("${vo.productType}");
					</script>
				</td>
				<td>产品系列： </td>
				<td>
<!-- 					<input type="search" name="productFamily" size="12" value="${vo.productFamily}" size="15"/> -->
					<div id="markProductFamilyComList" class="dropdownlist"></div>
				</td>
				<td>服务中心： </td>
				<td>
					<div id="markRegionComList" class="dropdownlist"></div>
				</td>
				<td >安装日期：</td>
				<td >
					<input type="search" name="insStartTime" placeholder="请输入日期" onclick="laydate()" size="7"  readonly="true" value="${vo.insStartTime}"/>
					至
					<input type="search" name="insEndTime" placeholder="请输入日期" onclick="laydate()" size="7" readonly="true" value="${vo.insEndTime}"/>
				</td>
				<td >故障大类：</td>
    			<td >
    				<input type="hidden" name="faultTypeID" id="marketft_id" readonly="true" value="${comVo.faultTypeID}"/>  
                    <input type="hidden" name="faultTypeCode" id="marketft_code" readonly="true" value="${comVo.faultTypeCode}"/>  
                    <input type="search" name="faultTypeTxt" id="marketft_name" size="10" readonly="true" value="${comVo.faultTypeTxt}" style="float: left;"/>                  
                    <a onclick="faultTypeSel(this, 'marketft');" style="display:inline-block;"  id="btn" class="btnLook btn" width=950 height=500 lookupGroup="marketft">故障大类选择</a>
                    <a style="display:inline-block;"  class="btnClear" href="javascript:void(0);" onclick="clearFault('marketft')" title="清空"></a> 
    			</td>
    			<td >百台内：</td>
				<td>
                	<select name="isOver" id="isOver">
						<option value="">请选择</option>
						<option <c:if test="${vo.isOver=='否'}">selected</c:if> value="否">是</option>
						<option <c:if test="${vo.isOver=='是'}">selected</c:if> value="是">否</option>
					</select>
				</td>
				<td >气源：</td>
				<td>
                	<select name="gas" id="rgas">
						<option value="">所有</option>
					<option value="4T">4T</option>
					<option value="10T">10T</option>
					<option value="12T">12T</option>
					<option value="20Y">20Y</option>
					<option value="7RⅡ">7RⅡ</option>
					<option value="7RⅢ">7RⅢ</option>
					<option value="7RⅣ">7RⅣ</option>
					<option value="7RⅤ">7RⅤ</option>
					<option value="6RⅡ">6RⅡ</option>
					<option value="6RⅠ">6RⅠ</option>
					<option value="5RⅠ">5RⅠ</option>
					<option value="5RⅡ">5RⅡ</option>
					<option value="5RⅤ">5RⅤ</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>主机条码： </td>
				<td>
					<input type="search" name="partCode" size="12" value="${vo.partCode}" size="15"/>
				</td>
				<td>产品型号： </td>
				<td>
					<div id="markpartTypeList" class="dropdownlist"></div>
				</td>
				<td>合并服务中心： </td>
				<td>
					<div id="markMergeRegionComList" class="dropdownlist"></div>
				</td>
				<td>生产日期：</td>
				<td>
					<input type="text" name="createStartTime" placeholder="请输入日期" onclick="laydate()" size="7"  readonly="true" value="${vo.createStartTime}"/>
					至
					<input type="text" name="createEndTime" placeholder="请输入日期" onclick="laydate()" size="7" readonly="true" value="${vo.createEndTime}"/>
				</td>
    			<td>故障小类：</td>
    			<td>
    				<input type="hidden" name="faultReasonID" id="marketfr_id"  value="${comVo.faultReasonID}"/>  
    				<input type="hidden" name="faultReasonCode" id="marketfr_code" value="${comVo.faultReasonCode}"/>  
    				<input type="text"   name="faultReasonTxt" id="marketfr_name" size="10" readonly="true" value="${comVo.faultReasonTxt}" style="float: left;"/>    				
    				<a id="btn" style="display:inline-block" onclick="faultReasonSel(this, 'marketfr')" class="btnLook btn" width=950 height=500 lookupGroup="marketfr">故障小类选择</a>  
					<a style="display:inline-block;" class="btnClear" href="javascript:void(0);" onclick="clearFault('marketfr')"  title="清空"></a> 
    			</td>
    			<td>故障小类是否有效：</td>
				<td>
					<select name="faultReasonValid" id="faultReasonValid">
						<option value="">全选</option>
						<option <c:if test="${vo.productType eq NULL || vo.productType eq '' || vo.faultReasonValid=='是'}">selected</c:if> value="是">是</option>
						<option <c:if test="${vo.faultReasonValid=='否'}">selected</c:if> value="否">否</option>
					</select>
				</td>
				<td>是否配件消耗:</td>
				<td>
					<select name="isConsumed" id="isConsumed">
						<option  value="">全选</option>
						<option <c:if test="${vo.isConsumed eq '是'}">selected</c:if> value="是">是</option>
						<option <c:if test="${vo.isConsumed eq '否'}">selected</c:if> value="否">否</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>条码重复：</td>
				<td>
                	<select name="codeRepeat" id="isOver">
						<option value="">请选择</option>
						<option <c:if test="${vo.codeRepeat==1}">selected</c:if> value="1">是</option>
						<option <c:if test="${vo.codeRepeat==0}">selected</c:if> value="0">否</option>
					</select>
				</td>
				<td>VOC分类：</td>
                <td>
                    <input type="hidden" name="vocTypeID" id="VOC_TYPE_id" size="15" readonly="true" value="${comVo.vocTypeID}"/>
                    <input type="hidden" name="vocTypeCode" id="VOC_TYPE_code" size="15" readonly="true" value="${comVo.vocTypeCode}"/>  
                    <input type="text" name="vocTypeTxt" id="VOC_TYPE_name" size="15" readonly="true" value="${comVo.vocTypeTxt}" style="float: left;"/>                  
                    <a onclick="vocCategory(this, 'VOC_TYPE');" id="btn" class="btnLook btn" href="qms/commonSelect/vocCategory.do?groupName=VOC_TYPE" width=950 height=500 lookupGroup="VOC_TYPE">故障大类选择</a>  
                    <a class="btnClear" href="javascript:void(0);" onclick="clearFault('VOC_TYPE')" ></a>
                </td> 
				<td >物料编号： </td>
				<td >
					<input type="search" name="partNumber" size="12" value="${vo.partNumber}" size="15"/>
				</td>
				<td>物料名称： </td>
				<td>
					<input type="search" name="partName" size="12" value="${vo.partName}" size="15"/>
				</td>
				<td>维修日期：</td>
				<td>
					<input type="search" name="startTime" placeholder="请输入日期" onclick="laydate()" size="7"  readonly="true" value="${vo.startTime}"/>
					至
					<input type="search" name="endTime" placeholder="请输入日期" onclick="laydate()" size="7" readonly="true" value="${vo.endTime}"/>
				</td>
				<td>合并故障小类：</td>
    			<td>
    				<input type="hidden" name="meshFaultReasonCode" id="markemfr_meshFaultCode" value="${comVo.meshFaultReasonCode}">
    				<input type="text" name="meshFaultName" id="markemfr_meshname" size="10" readonly="true" value="${comVo.meshFaultName}" style="float: left;"/>    				
    				<a id="btn" onclick="meshFaultReasonSelect(this, 'markemfr')" class="btnLook btn" width=950 height=500 lookupGroup="markemfr">合并故障小类选择</a>  
					<a class="btnClear" href="javascript:void(0);" onclick="clearMergeFault('markemfr')"  title="清空"></a> 
    			</td>
				<td colspan="2" style="padding:0px;">
					<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
					<shiro:hasPermission name="base:repairRecord:exportExcel">
					<a class="button"  onclick="exportExcelByCommon('base/repairRecord/excelOutput.do', '#marketRepairForm');"  title="确定导出信息？"><span>导出</span></a>	
					</shiro:hasPermission>		
				</td>
				</tr>	
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" style="width: 280%" layoutH="175">
		<thead>
			<tr>
				<th align="center" style="width: 3%">合并服务中心</th>
				<th align="center" style="width: 3%">服务中心</th>
				<th align="center" style="width: 4%">工单编号</th>
				<th align="center" style="width: 4%">物料名称</th>
				<th align="center" style="width: 3%">物料编号</th>
				<th align="center" style="width: 3%">机型类别</th>
				<th align="center" style="width: 2%">产品系列</th>
				<th align="center" style="width: 2%">产品型号</th>
				<th style="width: 4%">主机条码</th>
				<th style="width: 3%">下线时间(生产时间)</th>
				<th style="width: 4%">安装时间</th>
				<th style="width: 2%">故障大类代码</th>
				<th style="width: 2%">故障小类代码</th>
				<th style="width: 4%">故障大类名称</th>
				<th style="width: 4%">故障小类</th>
				<th style="width: 4%">结算说明</th>
				<th style="width: 4%">实际完成时间</th>
				<th style="width: 4%">故障描述现象</th>
				<th style="width: 8%">故障现场现象</th>
				<th style="width: 10%">详细信息</th>
				
				<th style="width: 4%">服务网点</th>
				<th style="width: 3%">服务工程师</th>
				<th style="width: 4%">家庭电话</th>
				<th style="width: 3%">手机</th>				
				<th style="width: 3%">开票日期</th>
				<th style="width: 4%">创建时间</th>
				<th style="width: 3%;min-width:10px;">购买途径</th>
				<th style="width: 5%;min-width:10px;">气源</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<%--需维护资料--%>
					<td>${o.serviceCenter}</td>
					<td>${o.rawServiceCenter}</td>
					<td>${o.orderNumber}</td>
					<td>${o.partName}</td>
					<td>${o.partNumber}</td>
					<td>${o.productType}</td>				
					<td>${o.productFamily}</td>
					<td>${o.partType}</td>
					<td>${o.partCode}</td>
					<td>${o.downlineDate}</td>
					<td><fmt:formatDate value="${o.intallDate}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.faultTypeCode}</td>
					<td>${o.faultReasonCode}</td>
					<td>${o.faultTypeName }</td>
					<td>${o.faultReasonName }</td>
					<td>${o.settlementDesc}</td>
					<td><fmt:formatDate value="${o.finishDate}" type="date" pattern="yyyy-MM-dd"/></td>
					<td>${o.faultDesc}</td>
					<td>${o.faultCurrentDesc}</td>
					<td>${o.infoDesc}</td>
					<td>${o.serviceSite}</td>
					<td>${o.serviceEngineer}</td>
					<td>${o.homePhone}</td>
					<td>${o.cellphone}</td>
					<td><fmt:formatDate value="${o.invoiceDate}" type="date" pattern="yyyy-MM-dd"/></td>
					<td><fmt:formatDate value="${o.recordTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td>${o.buyType}</td>
					<td>${o.gas}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script type="text/javascript">
	$(function(){
		loadProductFamilyData("#markProductFamilyComList", "productFamilyTxt", [${comVo.productFamilyTxt}], ${jsonProFamily});
		loadProductTypeData("#markpartTypeList", "partTypeListTxt", [${comVo.partTypeListTxt}], ${jsonParts});
		loadRegionData("#markRegionComList", "regionListTxt", [${comVo.regionListTxt}], ${jsonRegions});
		loadMergeRegionData("#markMergeRegionComList", "mergeRegionListTxt", [${comVo.mergeRegionListTxt}], ${jsonMergeRegions});
		
		$("#rgas").val("${comVo.gas}");
	});
</script>