<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include
	file="/common/include.inc.jsp"%>
<div class="pageContent">

	<div id="toSelectFaultReasonList">
		<form onsubmit="return  divSearch(this, 'toSelectFaultReasonList')"
			id="pagerForm" action="qms/commonSelect/meshFaultNameSelectResult.do" method="post">
			<div class="pageHeader">
				<div class="searchBar">
					<table class="searchContent">
						<tr>
							<td class="dateRange">机型类别:</td>
							<td>
								<select name="productType" >
									<option value="">请选择</option>
									<c:forEach items="${productTypes}" var="o">
									<option value="${o.machineType}" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
									</c:forEach>
								</select>
			                </td>
							<td class="dateRange">合并故障名称:</td>
							<td><input type="text" name="meshFaultName"
								id="meshFaultName" size="10" value="${vo.meshFaultName}" /> <input
								type="hidden" name="pageNum" value="1" /> <input type="hidden"
								name="numPerPage" value="${page.numPerPage}" /> <input
								type="hidden" name="direction" value="" />
							</td>
							<td class="dateRange">合并代码:</td>
							<td>
								<input type="text" name="code" id="code" size="10" value="${vo.code}"/>
			                </td>
							<td>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">查找</button>
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>

			<table class="table" width="100%" layoutH="150">
				<thead>
					<tr>
						<th width="5%"></th>
						<th width="30%">合并故障名称</th>
						<th width="20%">机型类别</th>
	                    <th width="20%">故障代码</th>
	                    <th width="30%">故障名称</th>
					</tr>
				</thead>
				<tbody id="meshFaultTbody">
					<c:forEach items="${list}" var="o">
						<tr target="code_carrier" rel="${o.meshFaultName}">
							<td style="text-align: center;">
								<input type="radio" group="code" value="{meshFaultName:'${o.meshFaultName}'}">
							</td>
							<td>${o.meshFaultName}</td>
							<td>${o.productType}</td>
							<td>${o.code}</td>
	                        <td>${o.name}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<c:import url="../../_frag/pager/panelBar.jsp">
				<%-- 对话框分页时需要传此参数 --%>
				<c:param name="targetName" value="dialog" />
				<c:param name="relDivId" value="toSelectFaultReasonList" />
			</c:import>
		</form>
	</div>

	<div class="formBar">
		<ul>
			<li><div class="buttonActive">
					<div class="buttonContent">
						<button type="button" onclick="comfirmSelect()">确定</button>
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
</div>

<script type="text/javascript">
function comfirmSelect()
{
    var selVal = $('#meshFaultTbody input:radio[name="meshFaultName"]:checked',$.pdialog.getCurrent()).val();
    if(selVal == undefined)
    {
        alertMsg.warn('请选择合并故障名称！');
        return false;
    }
    var jsonObj = eval("("+ selVal +")");
    $.bringBack(jsonObj);
}
</script>