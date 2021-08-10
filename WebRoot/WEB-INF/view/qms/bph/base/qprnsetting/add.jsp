<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
        //jQuery("#formID").validationEngine();
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/qprnsetting/save.do?navTabId=qprnSettingNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">
		<tr >
			<th>QPRN</th>
			<td><input type="text" id="qprn" name="qprn" class="required">
		    </td>
		    <th>得分类型</th>
		    <td><input type="radio" id="scoreType" class="required" name="scoreType" value="1">得分&nbsp;&nbsp;
		        <input type="radio" id="scoreType" name="scoreType" value="0">扣分
		    </td>		
		</tr>
		<tr>   
		    <th>是否满权重</th>
		    <td><input type="text" name="weight" id="weight" value="" class="required number"/>
		    </td>
		    <th>分数值</th>
		    <td><input name="score" id="score" type="text"  value="" class="required number"/></td>
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
