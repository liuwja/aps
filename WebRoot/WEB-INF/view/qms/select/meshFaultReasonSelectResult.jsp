<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div class="pageHeader">待选列表</div>
<div class="pageHeader">
	<form onsubmit="return divSearch(this, 'toSelectMeshFaultReasonList')" id="pagerForm" action="qms/commonSelect/meshFaultReasonSelectResult.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td class="dateRange">合并故障名称：</td>
				<td>
					<input type="text" name="meshFaultName" id="meshFaultName" size="10" value="${vo.meshFaultName}"/>
					<input type="hidden" name="pageNum" value="1" />
					<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
					<input type="hidden" name="direction" value="" />
				</td>
				<td class="dateRange">机型类别： </td>
				<td>
					<select name="productType" >
						<option value="">请选择</option>
						<c:forEach items="${productTypes}" var="o">
						<option value="${o.machineType}" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
						</c:forEach>
					</select>
				</td>
				<td>
           			<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>               
	            </td>
			</tr>
        </table>
    </div>
    </form>
</div>
<table class="table" width="100%" layoutH="120">
	<thead>
		<tr>
		    <th width="5%"><input type="checkbox" id = "checkBoxAll" class="checkboxCtrl" group="id" onclick = "selectAllRow()"></th>
		    <th width="25%">机型类别</th>
		    <th width="50%">合并故障名称</th>
		    <th width="20%">合并故障代码</th>
		</tr>
	</thead>
	<tbody id="meshFaultReasonList">
		<c:forEach items="${list}" var="o">
			<tr target="id" rel="${o.meshFaultCode}" onclick="selectRow(this,'${o.meshFaultName}','${o.meshFaultCode}')">
				<td style="text-align: center;">
				    <input type="checkbox" name="chkbox" id="${o.meshFaultCode}"  value="${o.productType},${o.meshFaultName},${o.meshFaultCode}">
				</td>
				<td>${o.productType}</td>
				<td>${o.meshFaultName}</td>
				<td>${o.meshFaultCode}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>    