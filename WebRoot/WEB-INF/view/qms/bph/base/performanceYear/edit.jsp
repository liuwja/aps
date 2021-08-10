<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='per/item/update.do?navTabId=perItemNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
		<input type="hidden" name="id" value="${group.id }">
		<table class="tableFormContent nowrap">
		   <tr >
			<th>部门</th>
			<td><input type="text" name="department"  value="${group.departmentName }" readonly="readonly">
		    </td> 
		        <th>绩效目标大类</th>
		   <td><input type="text" name="targetclass"  value="${group.targetclass }" readonly="readonly"></td> 
		   
		    <th>绩效指标内容</th>
		   <td colspan="3"><input type="text" name="indexcontent"  value="${group.indexcontent }" readonly="readonly"></td> 
		</tr>
		
		<tr>
		<th>绩效类型</th>
		 <td><input type="text" name="performancecontent"  value="${group.performancecontent }" readonly="readonly"></td>
		 <th>权重</th>
		 <td ><input type="text" name="weight"  value="${group.weight }" readonly="readonly"></td>  
		</tr>
		<tr><th colspan="6" style="text-align: center;">修改前数据</th></tr>
		<tr>   
		    <th>上年度实际值</th>
		    <td >
		       <%-- <input type="hidden" name="itemKey" id="itemKey" value="${item.itemKey}" class="required"/> --%>
		       <input type="text" value="${item.upperactualvalue}" readonly="readonly" class="required"/>
		    </td>
		    <th>上半年目标值</th>
		    <td>
		       <input type="text" value="${item.upperhalftargetvalue}" readonly="readonly" class="required"/>
		    </td>
		    
		     <th>下半年目标值</th>
		    <td>
		       <input type="text" value="${item.secondhalftargetvalue}" readonly="readonly" class="required"/>
		    </td>
		</tr>
		<tr>
		<th>本年基准值</th>
		    <td>
		       <input type="text" value="${item.referencevalue}" readonly="readonly" class="required"/>
		    </td>
		     <th>本年目标值</th>
		    <td>
		       <input type="text" value="${item.yeartargetvalue}" readonly="readonly" class="required"/>
		    </td>
		</tr>
		<tr><th colspan="6" style="text-align: center;">修改后数据</th></tr>
		<tr>   
		    <th>上年度实际值</th>
		    <td >
		       <input type="hidden" name="itemKey" id="itemKey" value="${item.itemKey}" class="required"/>
		       <input type="text" name="upperactualvalue" id="upperactualvalue" value="${item.upperactualvalue}" class="required"/>
		    </td>
		    <th>上半年目标值</th>
		    <td>
		       <input type="text" name="upperhalftargetvalue" id="upperhalftargetvalue" value="${item.upperhalftargetvalue}" class="required"/>
		    </td>
		    
		     <th>下半年目标值</th>
		    <td>
		       <input type="text" name="secondhalftargetvalue" id="secondhalftargetvalue" value="${item.secondhalftargetvalue}" class="required"/>
		    </td>
		</tr>
			<tr>
			 <th>本年基准值</th>
		    <td>
		       <input type="text" name="referencevalue" id="referencevalue" value="${item.referencevalue}" class="required"/>
		    </td>
		     <th>本年目标值</th>
		    <td>
		       <input type="text" name="yeartargetvalue" id="yeartargetvalue" value="${item.yeartargetvalue}" class="required"/>
		    </td>
			</tr>
			<tr>
				<th>修改人</th>
				<td>
				   <input type="text" name="lastupdateuser" id="lastupdateuser" value="${item.lastupdateuser}" class="required"/>
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