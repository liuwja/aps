<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
    });

</script>
<div class="pageContent">
	<form method="post" id="formID" action="<c:url value='per/index/update.do?navTabId=perIndexNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<table class="tableFormContent nowrap">
		<tr>
		 <th>年度</th>
		   <td><input type="text"  value="<fmt:formatDate value="${group.chekyear}"  pattern="yyyy"/>" onclick="laydate({isym:true, format: 'YYYY'})"  readonly="true" class="required" />
		    </td>   
		   <th>部门</th>
		   <td><input type="text"  value="${group.department }" readonly="readonly">
		    </td> 
		     <th>绩效目标大类</th>
		   <td ><input type="text"  value="${group.targetclass }" readonly="readonly"></td> 
		  		  
		</tr>
		<tr>  
		 <th>衡量指标内容</th>
		   <td><input type="text"  value="${group.indexcontent }" readonly="readonly"></td> 
		   <th>绩效类型</th>
		   <td><input type="text" value="${group.performancecontent }" readonly="readonly"></td>
		   
		   <th>权重</th>
		   <td colspan="3"><input type="text"  value="${group.weight }" readonly="readonly"></td>  
		</tr>
		
		<tr>		  
		    <th>上年实际值</th>
		    <td >
		       <input type="hidden" name="itemKey" id="itemKey" value="${item.itemKey}"  />
		       <input type="text" name="upperactualvalue" id="upperactualvalue" value="${item.upperactualvalue}"  readonly="readonly"/>
		    </td>
		    
		    <th>上半年目标</th>
		    <td>
		       <input type="text" name="upperhalftargetvalue" id="upperhalftargetvalue" value="${item.upperhalftargetvalue}"  readonly="readonly"/>
		    </td>
		    <th>下半年目标</th>
		    <td>
		       <input type="text" name="secondhalftargetvalue" id="secondhalftargetvalue" value="${item.secondhalftargetvalue}"  readonly="readonly"/>
		    </td>
		    
		</tr>
		<tr>     
		    <th>本年基准值</th>
		    <td>
		       <input type="text" name="referencevalue" id="referencevalue" value="${item.referencevalue}"  readonly="readonly"/>
		    </td>
		    <th>本年目标值</th>
		    <td>
		       <input type="text" name="yeartargetvalue" id="yeartargetvalue" value="${item.yeartargetvalue}"  readonly="readonly"/>
		    </td>
		    </tr>
		    
		    <tr>
		    <input type="hidden" name="indexKey" id="indexKey" value="${index.indexKey}" class="required"/>
		     <th>基准值</th>
		    <td>
		       <input type="text" name=basevalue  value="${index.basevalue}" class="required"/>
		    </td>
 
		    <th>目标值</th>
		    <td>		    
		       <input type="text"  name="targetvalue" id="targetvalue" value="${index.targetvalue}" class="required"/>
		    </td>
		   
		   <th>月份</th>
		   <td colspan="3">
		  <input type="text" class="required" name="month" placeholder="请输入日期" value="${index.month}" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true"/></td>
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
