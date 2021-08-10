<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<div class="pageHeader" style="position:static">
	<form id="partlist" onsubmit="return dwzSearch(this, 'dialog');" rel="pagerForm" action="qms/common/number.do?idend=${id_end}" method="post">
		<div class="searchBar pageFormContent" style="border-style: none">
			<table class="searchContent">		  
				<td>物料类型：</td>
				<td>
					<select id="uda_3" name="uda_3">
						<option value="">请选择</option>
						<option value="物料">物料</option>
						<option value="BOM">BOM</option>
					</select>
				</td>
				<td>物料编号：</td>
				<td>
					<input type="text" id="" name="partnumber" value="${vo.partnumber }"/>
				</td>
				<td>物料名称：</td>
				<td>
					<input type="text" id="" name="description" value="${vo.description }"/>
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
				<th style="width: 22%">物料编号</th>
				<th style="width: 25%">物料名称</th>
				<th style="width: 15%">物料类型</th>
				<th style="width: 23%">库存仓库</th>
			</tr>
		</thead>
		<tbody id="partTable">
			<c:forEach items="${list}" var="val">
				<tr>
					<td style="text-align: center;"><input type="checkbox" name="part" value="${val.partnumber},${val.description}"></td>
					<td>${val.partnumber}</td>
					<td>${val.description}</td>
					<td>${val.uda_3}</td>
					<td>${val.uda_2}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
  
	<c:import url="../../_frag/pager/panelBar.jsp">
		<c:param name="targetName" value="dialog"/>
	</c:import>
	<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="comfirmSelectg()">确定</button></div></div></li>
		<li>
			<div class="button"><div class="buttonContent"><button type="button" class="close" onclick="">取消</button></div></div>
		</li>
	</ul>
</div>
</div>
<script type="text/javascript">
	$(function(){
		var v='${vo.uda_3}';
		$("#uda_3").val(v);
	});
	function comfirmSelectg(){
		var s='';
		var v='';//显示值
		var selVal = $('#partTable input:checkbox[name="part"]:checked');
		selVal.each(function(){
			//s+=$(this).val()+',';
			var array=$(this).val().split(",");
			s+=array[0]+',';
			v+=array[1]+',';
		    
		});
		if(s==''){
			alertMsg.warn('请选择物料编号！');
			return false;
		} 
		var val = { "s": s,"v":v,"idend":"${id_end}"};
		if ($.isFunction(eqSelctCallBack2)) {
			eqSelctCallBack2(val);
			$.pdialog.closeCurrent();
		};
	}
</script>