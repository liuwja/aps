<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/qprnsetting/update.do?navTabId=qprnSettingNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${group.id}">
		<table class="tableFormContent nowrap">
		   <tr >
			<th>QPRN</th>
			<td><input type="text" id="qprn" name="qprn" value="${group.qprn}">
		    </td>
		    <th>得分类型</th>
		    <td>  <input type="radio" id="scoreType" name="scoreType" value="1" <c:if test="${group.scoreType == 1 }">checked="checked"</c:if> >得分&nbsp;&nbsp;
		        <input type="radio" id="scoreType" name="scoreType" value="0" <c:if test="${group.scoreType == 0 }">checked="checked"</c:if> >扣分
		    </td>		
		</tr>
		<tr>   
		    <th>是否满权重</th>
		    <td><input type="text" name="weitht" id="weight" value="${group.weight}" class="number"/>
		    </td>
		    <th>分数值</th>
		    <td><input name="score" id="score" type="text"  value="${group.score}" class="number"/></td>
		</tr>
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
