<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader" style="position:static">
<form id="missDataForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/missData/partListByProduct.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<th>物料型号：</th>
				<td>
					<input type="text" id="missPartData_partNumber" name="partNumber" value="${vo.partNumber}" size="11" />
				</td>
				<th>机型类别：</th>
				<td>
					<select id="showProductType" name="showProductType">
						<option value="2" ${vo.showProductType eq '2' ? 'selected' : ''}>机型类别非空</option>
						<option value="1" ${vo.showProductType eq '1' ? 'selected' : ''}>机型类别为空</option>
					</select>
				</td>
				<th>淘汰机型：</th>
				<td>
					<select id="showDescription" name="showDescription">
						<option value=""></option>
						<option value="1" ${vo.showDescription eq '1' ? 'selected' : ''}>不显示淘汰机</option>
						<option value="2" ${vo.showDescription eq '2' ? 'selected' : ''}>显示淘汰机型</option>
					</select>
				</td>
				<td>
					<div class="buttonActive"><div class="buttonContent"><button onclick="exportMissPartDataExcel()">导出</button></div></div>
				</td>
			</tr>
			<tr>
				<th>物料名称：</th>
				<td>
					<input type="text" id="missPartData_partName" name="partName" value="${vo.partName}" size="11" />
					<input type="hidden" id="missPartData_type" name="type" value="${vo.type}" />
				</td>
				<th>产品系列：</th>
				<td>
					<select id="showPartFamily" name="showPartFamily">
						<option value="2" ${vo.showPartFamily eq '2' ? 'selected' : ''}>产品系列非空</option>
						<option value="1" ${vo.showPartFamily eq '1' ? 'selected' : ''}>产品系列为空</option>
					</select>
				</td>
				<th>产品型号：</th>
				<td>
					<select id="showPartType" name="showPartType">
						<option value="2" ${vo.showPartType eq '2' ? 'selected' : ''}>产品型号非空</option>
						<option value="1" ${vo.showPartType eq '1' ? 'selected' : ''}>产品型号为空</option>
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
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a><a class="edit" href="base/missData/editByProduct.do?partNumber={key}"  mask="true" target="dialog" height="300" width="600" rel="missDataEdit"  title="修改是否淘汰机型"><span>修改</span></a></a></li>
		</ul>
	</div>
	<table class="table" style="width: 100%" layoutH="140">
		<thead>
			<tr >
				<th>选择</th>
				<th>物料编码</th>
				<th>物料名称</th>
				<th>机型类别</th>
				<th>产品系列</th>
				<th>产品型号</th>
				<th>是否淘汰机型</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.partNumber}">
					<td><input name="partNumber" type="radio" value="${o.partNumber}"></td>
					<td>${o.partNumber}</td>
					<td>${o.partName}</td>
					<td>${o.productType}</td>
					<td>${o.partFamily}</td>
					<td>${o.partType}</td>
					<td>${o.description}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
<script>
	function exportMissPartDataExcel() {
		var myForm = document.createElement("form");
		myForm.action= "base/missData/exportProductExcel.do";
		myForm.method="post";
		myForm.target="noexistForm";
		var partNumber = $("#missPartData_partNumber", navTab.getCurrentPanel()).val();
		var partName = $("#missPartData_partName", navTab.getCurrentPanel()).val();
		var showProductType = $("#showProductType", navTab.getCurrentPanel()).val();
		var showDescription = $("#showDescription", navTab.getCurrentPanel()).val();
		var showPartFamily = $("#showPartFamily", navTab.getCurrentPanel()).val();
		var showPartType = $("#showPartType", navTab.getCurrentPanel()).val();
		
		var partNumberInput = document.createElement("input");
		partNumberInput.setAttribute("name", "partNumber");
		partNumberInput.setAttribute("value", partNumber);
		
		var partNameInput = document.createElement("input");
		partNameInput.setAttribute("name", "partName");
		partNameInput.setAttribute("value", partName);
		
		var showProductTypeInput = document.createElement("input");
		showProductTypeInput.setAttribute("name", "showProductType");
		showProductTypeInput.setAttribute("value", showProductType);
		
		var showDescriptionInput = document.createElement("input");
		showDescriptionInput.setAttribute("name", "showDescription");
		showDescriptionInput.setAttribute("value", showDescription);
		
		var showPartFamilyInput = document.createElement("input");
		showPartFamilyInput.setAttribute("name", "showPartFamily");
		showPartFamilyInput.setAttribute("value", showPartFamily);
		
		var showPartTypeInput = document.createElement("input");
		showPartTypeInput.setAttribute("name", "showPartType");
		showPartTypeInput.setAttribute("value", showPartType);
		
		var typeInput = document.createElement("input");
		typeInput.setAttribute("name", "type");
		typeInput.setAttribute("value", "1");
		
		myForm.appendChild(partNumberInput);
		myForm.appendChild(partNameInput);
		myForm.appendChild(typeInput);
		myForm.appendChild(showProductTypeInput);
		myForm.appendChild(showDescriptionInput);
		myForm.appendChild(showPartFamilyInput);
		myForm.appendChild(showPartTypeInput);
		document.body.appendChild(myForm);
		myForm.submit();
	}
</script>