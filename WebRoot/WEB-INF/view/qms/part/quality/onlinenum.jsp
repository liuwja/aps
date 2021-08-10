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
	<form id="onlinesl" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/quality/onlinenum.do" method="post">
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
					<jsp:param value="10" name="analysisType"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="1" name="iscrux"/>
				</jsp:include>
					<td >
						<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
					</td>
				</tr>
			</table>
		</div>
		<input id="bs2" type="hidden" name="bs"/> 
	</form>
</div>
<div class="pageContent" id="tab2" hid="1" style="display: ">
	<table class="table" width="100%" layoutH="160">
		<thead>
			<tr>
				<th>发生日期</th>
				<th>产品线</th>
				<th>物料编号</th>
				<th>物料名称</th>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>不良现象</th>
				<th>不良数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="list">
				<tr>
					<td><fmt:formatDate value="${list.date_TT}" type="date" pattern="yyyy-MM-dd "/></td>
					<td>${list.moldtype}</td>
					<td>${list.part_number}</td>
					<td>${list.description}</td>
					<td>${list.supcode}</td>
					<td>${list.supname}</td>
					<td>${list.defect_s}</td>
					<td>${list.badnum }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
