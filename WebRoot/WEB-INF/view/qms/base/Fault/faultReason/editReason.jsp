<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='base/fault/faultReason/updateFaultReason.do?navTabId=faultReasonNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" id="id" value="${faultReason.id}">
		<table class="tableFormContent nowrap">
			<tr >
                <th>机型类别：</th>
                <td>
					<select name="productType" style="width: 210px; ">
							<option value="">请选择</option>
							<option value="公共">公共</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType}" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
							</c:forEach>
					</select>
    			</td>
            </tr>
			<tr >
                <th>故障代码：</th>
                <td><input name="code" type="text" size="30" class="required" value="${faultReason.code}" /></td>
            </tr>
            <tr >
                <th>故障名称：</th>
                <td><input name="name" type="text" size="30" class="required" value="${faultReason.name}" /></td>
            </tr>
             <tr >
                <th>合并故障名称：</th>
                <td><input name="meshFaultName" type="text" size="30" class="required" value="${faultReason.meshFaultName}" /></td>
            </tr>
            <tr>
				<th>是否有效：</th>
				<td>
	                
	                <select id="valid_select" name="valid" style="width: 210px; ">
		        <option value="是">是</option>
		        <option value="否">否</option>
	                </select> 
	                         <script type="text/javascript">
	                         $("#valid_select", $.pdialog.getCurrent()).val("${faultReason.valid}");
                    </script>
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

