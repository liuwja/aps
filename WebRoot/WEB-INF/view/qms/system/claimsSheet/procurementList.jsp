<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function() {
	
});
function exportExcel(url){    	
	var myForm = document.createElement("form");
	myForm.action= url;
	myForm.method="post"; 
	myForm.target="noexistForm"; 
	var objs = $("#procurementFrom input",navTab.getCurrentPanel());
	var objs_select = $("#procurementFrom select",navTab.getCurrentPanel());	
	var myInput;
	for(var i = 0 ; i< objs.length+objs_select.length ; i++){
		var $obj = null;
		if(i>=objs.length){
			$obj = $(objs_select[i-objs.length]);	
		}else{
			$obj = $(objs[i]);			
		}
		var v = $obj.val();
		if(v==null || v==""){
			v="";
		}
		if($obj.attr("type")=="checkbox"){
			if(!$obj.attr("checked")){
				v="";
			}
		}
		myInput = document.createElement("input");
		myInput.setAttribute("name", $obj.attr("name"));
		myInput.setAttribute("value", v);
		myForm.appendChild(myInput);
	}
	$(document.body,navTab.getCurrentPanel()).append(myForm);
	myForm.submit();
	
}
</script>
<div class="pageHeader">
<form id="procurementFrom" onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/claimsSheet/procurementList.do" method="post">
<div class="searchBar">
	<table class="searchContent">
		<tr>
			<td>类型：</td>
			<td>
				<select name="claims_type" id="claims_type" onchange="">
					<option value="">请选择</option>
					<option value="索赔" <c:if test="${claimsSheet.claims_type eq '索赔' }">selected="selected"</c:if>>索赔</option>
					<option value="处罚" <c:if test="${claimsSheet.claims_type eq '处罚' }">selected="selected"</c:if>>处罚</option>
				</select>
			</td>
			<td>索赔/处罚单号</td>
		    <td >
		         <input type="text" id="rework_number"  name="rework_number" size="20"  value="${claimsSheet.rework_number}"/>
		    </td>
		    <td>产品线：</td>
			<td>
				<select name="product_category" id="product_category" onchange="">
					<option value="">请选择</option>
					<c:forEach items="${product_categorys}" var="o">
						<option value="${o }" <c:if test="${claimsSheet.product_category eq o }">selected="selected"</c:if>>${o}</option>
					</c:forEach>
				</select>
			</td>
			<td>开具日期：</td>
			<td>
				 <input type="text" id="creation_time"  name="creation_time" placeholder="请输入日期" onclick="laydate()" value="${claimsSheet.creation_time}" readonly="true"/>
			</td>
			<td>关闭状态：</td>
			<td>
				<select name="is_close" id="is_close" onchange="">
					<option value=''>请选择</option>
		         	<option value="开启" <c:if test="${claimsSheet.is_close eq '开启' }">selected="selected"</c:if>>开启</option>
					<option value="关闭" <c:if test="${claimsSheet.is_close eq '关闭' }">selected="selected"</c:if>>关闭</option>
				</select>
			</td>
			<td>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
			</td>
			<td>
				<a class="button"  onclick="exportExcel('system/claimsSheet/exportProcurementExcel.do');"  title="确定导出信息？"><span>导出EXCEL</span></a>
			</td> 			
		</tr>
	</table>
</div>
</form>
</div>
<div class="pageContent">
<div class="panelBar">
	<ul class="toolBar">
<shiro:hasPermission name="base:reworkSheetData:EDIT">
		<li><a class="edit" href="system/claimsSheet/editProcurement.do?id={key}"  mask="true" target="dialog" rel="editDown" height="540" width="800"   title="计划采购登记"><span>登记</span></a></li>
</shiro:hasPermission>	
	</ul>
</div>
<table class="table" width="100%" layoutH="115">
	<thead>
		<tr>
			<th></th>
		    <th width="2%">序号</th>
		    <th width="3%">类别</th>
		    <th width="6%">返工/停线单号</th>
			<th width="4%">开单日期</th>
			<th width="4%">责任部门</th>
			<th width="4%">责任比例</th>
			<th width="8%">原因</th>
			<th width="3%">实际返工数</th>
			<th width="3%">产品线</th>
			<th width="6%">索赔/处罚单号</th>
			<th width="5%">供应商</th>
			<th width="8%">索赔/处罚原因</th>
			<th width="3%">金额</th>
			<th width="3%">申请人</th>
			<th width="4%">开具日期</th>
			<th width="3%">登记人</th>
			<th width="3%">是否回签</th>
			<th width="8%">未回签原因</th>
			<th width="3%">采购签收</th>
			<th width="5%">实际索赔金额</th>
			<th width="4%">入账月份</th>
			<th width="3%">关闭状态</th>
			<th width="6%">备注</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="o" varStatus="i">
			<tr target="key" rel="${o.id}">
				<td>
					<input type="radio" group="code" name="key" value="${o.id}">
				</td>
				<td width="2%">
        			${i.count}
        		</td>
				<td>${o.claims_type}</td> 
				<td>${o.rework_number}</td> 
				<td>${o.rework_creation_time}</td>  
				<td>${o.duty_depart } </td> 
				<td>${o.duty_proportion}</td>
				<td>${o.rework_reason}</td>
				<td>${o.rework_count}</td>
				<td>${o.product_category}</td>
				<td>${o.claims_number}</td>
				<td>${o.claims_supplier}</td>
				<td>${o.claims_reason}</td>
				<td>${o.claims_amount}</td>
				<td>${o.claims_applicant}</td>
				<td>${o.creation_time}</td>
				<td>${o.registrar}</td>
				<td>${o.is_response}</td>
				<td>${o.response_reason}</td>
				<td>${o.is_sign_in}</td>
				<td>${o.actual_claim_amount}</td>
				<td>${o.booked_month}</td>
				<td>${o.is_close}</td>
				<td>${o.remark}</td>
			</tr>		
		</c:forEach>
	</tbody>
</table>
<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>