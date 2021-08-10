<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
	    
    });
  
</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='system/performanceCheckYear/save.do?navTabId=performanceCheckYearNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">	
		<tr >
			<th>工厂</th>
			<td><select id="factory" name="factory" class="required" >
			    <option value="">请选择</option>
		        <option value="燃气工厂">燃气工厂</option>
		        <option value="电器工厂">电器工厂</option>
		        <option value="电器二厂">电器二厂</option>
		        </select>
		    </td>
		    <th>考核指标</th>
		    <td>
		       <input type="text" name="checkIndexName" size="20"  class="required">
		    </td>		
		</tr>
		<tr>   
		    <th>年度</th>
		    <td >
		         <input type="text" id="queryYear"  name="queryYear" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY'})"  readonly="true" class="required"/>
		    </td>
		    <th>年度基准</th>
		    <td >
		         <input type="text" id="baseValueYear"  name="baseValueYear" size="20" class="required number"/>
		    </td>
		</tr>
		
		<tr>   
		    <th>年度目标</th>
		    <td >
		         <input type="text" id="targetValueYear"  name="targetValueYear" size="20" class="required number"/>
		    </td>
		    <th>上半年目标</th>
		    <td >
		         <input type="text" id="targetValueHalfyear"  name="targetValueHalfyear" size="20" class="number"/>
		    </td>
		</tr>
		<tr>   
		    <th>年度下降率</th>
		    <td colspan="3">
		         <input type="text" id="depressRateYear"  name="depressRateYear" size="20" class="number"/>
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
