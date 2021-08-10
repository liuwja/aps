<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style type="text/css" media="screen">
	${demo.css}
	
.my-uploadify-button {
    background:none;
    border: none;
    text-shadow: none;
    border-radius:0;
}

.uploadify:hover .my-uploadify-button {
    background:none;
    border: none;
}

.fileQueue {
    width: 400px;
    height: 150px;
    overflow: auto;
    border: 1px solid #E5E5E5;
    margin-bottom: 10px;
}

</style>
<script type="text/javascript">
function erpPartExportData(){
	var url = "quality/testInstance/exportErpPartExcel.do";
	var form = document.createElement("form");
	form.action = url;
	form.method = "post";
	form.target="noexistForm"; 
	document.body.appendChild(form);
	form.submit();
}
</script>

	<form id="testInstanceForm" onsubmit="return navTabSearch(this);" action="quality/testInstance/erpPartReturnDetail.do" rel="pagerForm" method="post">
	<div id="searchContentDiv" class="searchBar dropdownSearchBar" >
		<table class="searchContent">
		     <jsp:include page="../select/part_supplier.jsp" flush="true">
					<jsp:param value="threeLine" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="productType"/>
					<jsp:param value="1" name="supplier"/>
					<jsp:param value="1" name="partType"/>
					<jsp:param value="1" name="partClass"/>
					<jsp:param value="1" name="partName"/>
					<jsp:param value="1" name="partVersion"/>
					<jsp:param value="1" name="isNew"/>
					<jsp:param value="0" name="selectDate"/>
					<jsp:param value="1" name="period"/>
					<jsp:param value="1" name="lotTime"/>
					<jsp:param value="0" name="columnNum"/>
					<jsp:param value="0" name="result"/>
					<jsp:param value="0" name="analysisType"/>
					<jsp:param value="1" name="submits"/>
					<jsp:param value="0" name="isLastTr"/> 
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
			</jsp:include>	
             <td >
			    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="erpPartExportData()">导出</button></div></div>
			 </td>  
		</tr> 
		</table>
	</div>
	</form>

<div >
	<table class="table" width="100%" layoutH="165">
		<thead >
		    
			<tr>
			    <th  >选择</th>
			    <th   >退次日期</th>
			    <th   >物料编号</th>
				<th   >物料名称</th>
				<th   >供应商编号</th>
				<th   >供应商名称</th>
				<th   >退次数量</th>
				<th   >仓库</th>
			</tr>
			
		</thead>
		<tbody id="testInstentTbody">
		   <c:forEach items="${list }" var="o">
		       <tr >
		          <td ><input type="radio" name="radio"></td>
		          <td >${o.returnDate }</td>
		          <td >${o.partNumber }</td>
		          <td >${o.partName }</td>
		          <td>${o.supplierNumber }</td>
		          <td>${o.supplierName}</td>
		          <td>${o.returnNumber }</td>
	              <td>${o.wareHouse }</td>
		       </tr>
		   </c:forEach>
		</tbody>
   </table>
   <c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

