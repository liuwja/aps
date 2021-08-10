<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader" style="position:static">
	<form id="partlist" onsubmit="return dwzSearch(this, 'dialog');" rel="pagerForm" action="qms/common/classification.do" method="post">
		<div class="searchBar pageFormContent" style="border-style: none">
			<table class="searchContent">		  
				<td>工厂：</td>
				<td>
					<select id="factoryScla" name="factoryS">
						<option value="">请选择</option>
						<c:forEach items="${factorylist}" var="list">
							<option value="${list}">${list}</option>
						</c:forEach>
					</select>
				</td>
				<td>物料大类：</td>
				<td>
					<input type="text" id="uda_2" name="uda_2" value="${vo.uda_2 }"/>
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
				<th style="width: 15%">所属工厂</th>
				<th style="width: 15%">物料大类</th>
				<th style="width: 17%">物料小类</th>
				<th style="width: 10%">绑定数量</th>
				<th style="min-width: 28%">编码规则</th>
			</tr>
		</thead>
		<tbody id="partClassTable">
			<c:forEach items="${list}" var="val">
				<tr>
					<td style="text-align: center;"><input type="checkbox" name="partclass" value="${val.uda_1}"></td>
					<td>${val.factoryS}</td>
					<td>${val.uda_2}</td>
					<td>${val.uda_1}</td>
					<td>${val.uda_3}</td>
					<td>${val.uda_4}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  
	<c:import url="../../_frag/pager/panelBar.jsp">
		<c:param name="targetName" value="dialog"/>
	</c:import>
	<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="comfirmSelect()">确定</button></div></div></li>
		<li>
			<div class="button"><div class="buttonContent"><button type="button" class="close" onclick="">取消</button></div></div>
		</li>
	</ul>
</div>
</div>
<script type="text/javascript">
	$(function(){
		var v='${vo.factoryS}';
		$("#factoryScla").val(v);
	});
	function comfirmSelect(){
		var s='';
		var selVal = $('#partClassTable input:checkbox[name="partclass"]:checked');
		selVal.each(function(){
		    s+=$(this).val()+',';
		});
		if(s==''){
			alertMsg.warn('请选择物料分类！');
			return false;
		} 
		var val = { "s": s};
		$.bringBack(val);
	}
</script>