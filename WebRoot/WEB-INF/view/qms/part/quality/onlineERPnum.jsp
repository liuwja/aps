<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
	.tablestyle{
		 display:block; 
		 overflow:hidden; 
	}
	.tablestyle td{
		padding-right:20px; 
		white-space:nowrap; 
		height:25px
	}
</style>
<div class="pageHeader" style="position:static">
	<form id="onlineerpsl" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/quality/onlineERPnum.do" method="post">
		<div class="searchBar pageFormContent">
			<table class="searchContent">		  
				<jsp:include page="../select/part_supplier.jsp" flush="true">
					<jsp:param value="doubleLine" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="productType"/>
					<jsp:param value="1" name="supplier"/>
					<jsp:param value="0" name="partType"/>
					<jsp:param value="1" name="partClass"/>
					<jsp:param value="1" name="partName"/>
					<jsp:param value="1" name="partVersion"/>
					<jsp:param value="1" name="isNew"/>
					<jsp:param value="1" name="selectDate"/>
					<jsp:param value="0" name="period"/>
					<jsp:param value="0" name="lotTime"/>
					<jsp:param value="0" name="columnNum"/>
					<jsp:param value="0" name="result"/>
					<jsp:param value="0" name="analysisType"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="1" name="iscrux"/>
				</jsp:include>	
					<td >
						<input type="submit" value="查询">
					</td>
				</tr>
			</table>
		</div>
		<input id="bs3" type="hidden" name="bs"/> 
	</form>
</div>
<div class="pageContent" id="tab3" hid="1" style="display: ">
	<table class="table" width="100%" layoutH="160">
		<thead>
			<tr>
				<th>退次日期</th>
				<th>物料编号</th>
				<th>物料名称</th>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>退次数量</th>
				<th>仓库</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td><fmt:formatDate value="${list.return_date}" type="date" pattern="yyyy-MM-dd "/></td>
					<td>${list.part_number}</td>
					<td>${list.part_name}</td>
					<td>${list.supplier_number}</td>
					<td>${list.supplier_name}</td>
					<td>${list.return_number}</td>
					<td>${list.ware_house}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script type="text/javascript">
	
</script>