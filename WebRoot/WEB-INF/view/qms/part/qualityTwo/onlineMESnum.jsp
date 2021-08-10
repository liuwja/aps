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
<script type="text/javascript">
	function chenk(){
		var factory=$("#factory_${id_end}",navTab.getCurrentPanel()).val();
		/* if(factory==null || factory==undefined ||factory==""){
			alertMsg.info("请选择工厂！");
			return;
		} */
		var productType=$("#productType_${id_end}",navTab.getCurrentPanel()).val();
		/* if(productType==null || productType==undefined ||productType==""){
			alertMsg.info("请选择机型！");
			return;
		} */
		$("#onlinesl",navTab.getCurrentPanel()).submit();
	}
	function downloadExeclMesnum(){
		var url = "base/online/downloadExeclMesnum.do";
    	var form = document.createElement("form");
    	form.action = url;
    	form.method = "post";
    	form.target="noexistForm"; 
    	document.body.appendChild(form);
    	form.submit();
    }
</script>
<div class="pageHeader" style="position:static">
	<form id="onlinesl" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/online/onlinenumReturn.do" method="post">
		<div class="searchBar pageFormContent">
			<table class="searchContent">		  
				<jsp:include page="../select/part_supplier2.jsp" flush="true">
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
					<td colspan="5">
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="chenk()">查找</button>
							</div>
						</div>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="downloadExeclMesnum()">导出</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<input id="bs2" type="hidden" name="bs"/> 
	</form>
</div>
<div class="pageContent" id="tab2" hid="1" style="display: ">
	<table class="table" width="100%" layoutH="188">
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
