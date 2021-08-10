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
	<form id="onlinerk" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/quality/onlineStorage.do" method="post">
		<div class="searchBar pageFormContent">
			<table class="searchContent">		  
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
					<jsp:param value="0" name="isNew"/>
					<jsp:param value="1" name="selectDate"/>
					<jsp:param value="0" name="period"/>
					<jsp:param value="0" name="lotTime"/>
					<jsp:param value="0" name="columnNum"/>
					<jsp:param value="0" name="result"/>
					<jsp:param value="0" name="analysisType"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="iscrux"/>
				</jsp:include>	
					<td >
						<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
					</td>
				</tr>
			</table>
		</div>
		<input id="bs" type="hidden" name="bs" value="0"/> 
	</form>
</div>
<div class="pageContent" id="" hid="1" style="display: ">
	<table class="table" width="100%" layoutH="165">
		<thead>
			<tr>
				<th>入库日期</th>
				<th>物料编号</th>
				<th>物料名称</th>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>供应商生产日期</th>
				<th>入库数量</th>
				<th>仓库</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td><fmt:formatDate value="${list.arrival}" type="date" pattern="yyyy-MM-dd HH:mm"/></td>
					<td>${list.partnumber}</td>
					<td>${list.partname}</td>
					<td>${list.supcode}</td>
					<td>${list.supname}</td>
					<td></td>
					<td>${list.tonum }</td>
					<td>${list.location }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>