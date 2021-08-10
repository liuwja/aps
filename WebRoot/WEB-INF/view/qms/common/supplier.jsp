<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader" style="position:static">
	<form id="partlist" onsubmit="return dwzSearch(this, 'dialog');" rel="pagerForm" action="qms/common/supplier.do?idend=${id_end}" method="post">
		<div class="searchBar pageFormContent" style="border-style: none">
			<table class="searchContent">		  
				<td>工厂：</td>
				<td>
					<select id="factorySsup" name="factoryS">
						<option value="">请选择</option>
						<c:forEach items="${factorylist}" var="list">
							<option value="${list}">${list}</option>
						</c:forEach>
					</select>
				</td>
				<td>级别：</td>
				<td>
					<input type="text" id="" name="level" value="${vo.level }"/>
				</td>
				<td>供应商编号：</td>
				<td>
					<input type="text" id="" name="numbers" value="${vo.numbers }"/>
				</td>
				<td>供应商名称：</td>
				<td>
					<input type="text" id="" name="name" value="${vo.name }"/>
				</td>
				<td>
					<input type="submit" value="查找">
				</td>
			</table>
		</div>
	</form>
</div>
<div>
	<table style="width: 100%" class="table" layoutH="140">
		<thead>
			<tr>
				<th style="width: 15%">选择</th>
				<th style="width: 20%">工厂</th>
				<th style="width: 15%">产线</th>
				<th style="width: 15%">供应商编号</th>
				<th style="width: 25%">供应商名称</th>
				<th style="width: 10%">供应商简称</th>
			</tr>
		</thead>
		<tbody id="accountTable">
			<c:forEach items="${list}" var="val">
				<tr>
					<td style="text-align: center;"><input type="checkbox" name="account" value="${val.numbers},${val.name}"></td>
					<td>${val.factoryS}</td>
					<td>${val.productionLine}</td>
					<td>${val.numbers}</td>
					<td>${val.name}</td>
					<td>${val.abbreviation}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  
	<c:import url="../../_frag/pager/panelBar.jsp">
		<c:param name="targetName" value="dialog"/>
	</c:import>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="comfirmSelects()">确定</button></div></div></li>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close" onclick="">取消</button></div></div>
			</li>
		</ul>
	</div>
</div>
<script type="text/javascript">
	$(function(){
		var v='${vo.factoryS}';
		$("#factorySsup").val(v);
	});
	function comfirmSelects(){
		var s='';
		var v='';
		var selVal = $('#accountTable input:checkbox[name="account"]:checked');
		selVal.each(function(){
			var array=$(this).val().split(",");
			s+=array[0]+',';
			v+=array[1]+',';
		});
		if(s==''){
			alertMsg.warn('请选供应商！');
			return false;
		} 
		var val = { "s": s,"v":v,"idend":"${id_end}"};
		if ($.isFunction(eqSelctCallBack)) {
			eqSelctCallBack(val);
			$.pdialog.closeCurrent();
		};
	}
</script>