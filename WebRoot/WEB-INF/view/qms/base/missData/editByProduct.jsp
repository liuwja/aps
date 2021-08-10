<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div class="pageContent">
	<!-- navTabId重新载入index对应的tab -->
	<form method="post" id="missDataEdit" action="<c:url value='base/missData/updateByProduct.do?navTabId=missPartDataByProductNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<table class="tableFormContent nowrap">
		        <tr>
		            <th>物料编号：</th>
		            <td>
		                <input name="partNumber" type="text" size="50" readonly="readonly" minlength="1" value="${list.partNumber}"/>
		            </td>
		        </tr>
		        <tr>
		            <th>物料名称：</th>
		            <td>
		            <input name="partName" type="text" size="50" readonly="readonly" value="${list.partName}"/>
		            </td>
		        </tr>
		        <tr>
		            <th>是否淘汰机型：</th>
		            <td>
			            <select name="description">
			            	<option value="" ${list.description eq '' ? 'selected' : ''}></option>
			            	<option value="海外机" ${list.description eq '海外机' ? 'selected' : ''}>海外机</option>
			            	<option value="淘汰机" ${list.description eq '淘汰机' ? 'selected' : ''}>淘汰机</option>
			            	<option value="空壳机" ${list.description eq '空壳机' ? 'selected' : ''}>空壳机</option>
			            </select>
		            </td>
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
