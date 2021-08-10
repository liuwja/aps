<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>

<div style="width:300px; 
height:200px; 
display:table-cell; 
text-align:center; 
vertical-align:middle;">
<div style="width:200px; 
height:200px; 
display:inline-block;">
<form id="editBox" action="poorOpen/edit.do" method="post">
<table class="searchContent">
<tr>
<c:if test="${type==1}">
<td>
责任方:
</td>
<td>
	<select name="primaryDuty">
		<option value="">物流</option>
		<option value="">工厂</option>
		<option value="">其他</option>
	</select>
</td>
</c:if>
</tr>
<tr>
<c:if test="${type == 2}">
<td>
责任方:
</td>
<td>
	<select name="ultimateDuty">
		<option value="">班组</option>
		<option value="">供应商</option>
		<option value="">其他</option>
	</select>
</td>
</c:if>
</tr>
<tr>
<td>
	<button onclick="editBox()">确定</button>
</td>
<td>
	<button class="close" value="关闭">取消</button>
</td>
</tr>
</table>
</form>
</div>
</div>
<script type="text/javascript">
function editBox(){
	$("#editBox").submit();
	$.pdialog.closeCurrent();
}


</script>