<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader" style="position:static">
	<form id="marketRepairIntermediateForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/repairRecord/marketRepairRecordIntermediate.do" method="post">
	<div class="searchBar" style="">
		<table class="searchContent">
			<tr>
				<td>主机条码： </td>
				<td><input type="search" name="partCode" value="${vo.partCode}" size="15"/></td>
				<td>维修工单： </td>
				<td><input type="search" name="orderNumber" value="${vo.orderNumber}" size="15"/></td>
<!-- 				<td >故障大类：</td> -->
<!-- 	   			<td> -->
<!-- 	   				<input type="hidden" name="faultTypeID" id="marketIntermediateft_id" readonly="true" value="${comVo.faultTypeID}"/>   -->
<!-- 	                   <input type="hidden" name="faultTypeCode" id="marketIntermediateft_code" readonly="true" value="${comVo.faultTypeCode}"/>   -->
<!-- 	                   <input type="search" name="faultTypeTxt" id="marketIntermediateft_name" size="10" readonly="true" value="${comVo.faultTypeTxt}" style="float: left;"/>                   -->
<!-- 	                   <a onclick="faultTypeSel(this, 'marketIntermediateft');" style="display:inline-block;" class="btnLook btn" width=950 height=500 lookupGroup="marketIntermediateft">故障大类选择</a> -->
<!-- 	                   <a style="display:inline-block;"  class="btnClear" href="javascript:void(0);" onclick="clearFault('marketIntermediateft')" title="清空"></a>  -->
<!-- 	   			</td> -->
				<td>维修日期：</td>
				<td>
					<input type="search" name="startTime" placeholder="请输入日期" onclick="laydate()" size="8"  readonly="true" value="${vo.startTime}"/>
					至
					<input type="search" name="endTime" placeholder="请输入日期" onclick="laydate()" size="8" readonly="true" value="${vo.endTime}"/>
				</td>
				
				<td>创建日期：</td>
				<td>
					<input type="search" name="recordTimeStart" placeholder="请输入日期" onclick="laydate()" size="8"  readonly="true" value="${vo.recordTimeStart}"/>
					至
					<input type="search" name="recordTimeEnd" placeholder="请输入日期" onclick="laydate()" size="8" readonly="true" value="${vo.recordTimeEnd}"/>
				</td>
				<td colspan="2">
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">查找</button>
						</div>
					</div>
					<a class="button" onclick="exportExcelIntermediate('base/repairRecord/excelOutputIntermediate.do');"  title="确定导出信息？"><span>导出</span></a>	
				</td>
			</tr>
<!-- 			<tr> -->
<!--     			<td>故障小类：</td> -->
<!--     			<td> -->
<!--     				<input type="hidden" name="faultReasonID" id="marketIntermediatefr_id"  value="${comVo.faultReasonID}"/>   -->
<!--     				<input type="hidden" name="faultReasonCode" id="marketIntermediatefr_code" value="${comVo.faultReasonCode}"/>   -->
<!--     				<input type="text" name="faultReasonTxt" id="marketIntermediatefr_name" size="10" readonly="true" value="${comVo.faultReasonTxt}" style="float: left;"/>    				 -->
<!--     				<a style="display:inline-block" onclick="faultReasonSel(this, 'marketIntermediatefr')" class="btnLook btn" width=950 height=500 lookupGroup="marketIntermediatefr">故障小类选择</a>   -->
<!-- 					<a style="display:inline-block;" class="btnClear" href="javascript:void(0);" onclick="clearFault('marketIntermediatefr')"  title="清空"></a>  -->
<!--     			</td> -->
<!-- 			</tr> -->
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" style="width: 280%" layoutH="140">
		<thead>
			<tr>
				<th align="center" style="width: 3%">服务中心</th>
				<th align="center" style="width: 4%">工单编号</th>
				<th align="center" style="width: 4%">主机条码</th>
				<th align="center" style="width: 4%">物料名称</th>
				<th style="width: 2%">故障大类代码</th>
				<th style="width: 2%">故障小类代码</th>
				<th style="width: 4%">故障大类名称</th>
				<th style="width: 4%">故障小类名称</th>
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
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<td>${o.serviceCenter}</td>
					<td>${o.orderNumber}</td>
					<td>${o.partCode}</td>
					<td>${o.partName}</td>
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
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script>
function exportExcelIntermediate(url){    
	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var objs = $("#marketRepairIntermediateForm input",navTab.getCurrentPanel());
	var objs_select = $("#marketRepairIntermediateForm select",navTab.getCurrentPanel());	
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
	document.body.appendChild(myForm);
	myForm.submit();
}
</script>