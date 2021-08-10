<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div class="pageHeader">待选列表</div>
		<form onsubmit="return  divSearch(this, 'toSelectRegionList')" id="pagerForm" action="qms/commonSelect/regionSelect.do" method="post">
			<div class="pageHeader">
				<div class="searchBar">
					<table class="searchContent">
						<tr>
							<td class="dateRange">合并中心名称</td>
							<td>
								<input type="text" name="mergeRegion" value="${vo.mergeRegion}" />
								<input type="hidden" name="pageNum" value="1" />
    							<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
    							<input type="hidden" name="direction" value="" />
							</td>
							<td class="dateRange">服务中心</td>
							<td><input type="text" name="region" value="${vo.region}" /></td>
						</tr>
						<tr>
							<td class="dateRange">所在省份</td>
							<td><input type="text" name="province" value="${vo.province}" /></td>
							<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div></td>
						</tr>
					</table>
				</div>
			</div>
			<table class="table" width="100%" layoutH="170">
				<thead>
					<tr>
						<th width="5%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
						<th width="30%">合并服务中心名称</th>
	                    <th width="10%">所在省份</th>
	                    <th width="10%">仓库编码</th>
	                    <th width="30%">服务中心名称</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="o">
						<tr target="id" rel="${o.region}" onclick="selectRegionRow(this, '${o.id}', '${o.mergeRegion}', '${o.locationCode}', '${o.region}')">
							<td style="text-align: center;"><input type="checkbox" name="chkbox"></td>
	                        <td>${o.mergeRegion}</td>
	                        <td>${o.province}</td>
	                        <td>${o.locationCode}</td>
	                        <td>${o.region}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:import url="../../_frag/pager/panelBar.jsp">
		       <%-- 对话框分页时需要传此参数 --%>
		       <c:param name="targetName" value="dialog"/>
		       <c:param name="relDivId" value="toSelectRegionList"/>    
	       </c:import>
		</form>