<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	function r_checkData(){
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();

		if(endTime<startTime){
			alertMsg.info("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("");
			return false;
		}
		$("#repair").submit();
		
	}
	//导出excel
	function repairTotalExcel(url){
		var myForm = document.createElement("form");
		myForm.action= url;
		myForm.method="post"; 
		myForm.target="noexistForm"; 
		var objs = $("#repair input",navTab.getCurrentPanel());
		var objs_select = $("#repair select",navTab.getCurrentPanel());	
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
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		if(endTime<startTime){
			alertMsg.info("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("");
			return false;
		}
		document.body.appendChild(myForm);
		myForm.submit();
	}
</script>
<div class="pageHeader">
	<form id="repair" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/repairTotal/repairTotal.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td style="padding-right:8px;">
					型号：
				</td>
				<td style="padding-right:10px;">
					<input type="text" name="partType" value="${vo.partType}" size="12"/>
				</td>
				<td style="padding-right:8px;">
					区域：
				</td>
				<td style="padding-right:10px;">
				<input type="text" name="region" value="${vo.region}" size="12"/>
				</td>
				<td style="padding-right:8px;">
					产线编号：
				</td>
				<td style="padding-right:10px;">
				<input type="text" name="productlineNumber" value="${vo.productlineNumber}" size="12"/>
					
				</td>
				<td style="padding-right:8px;">
					故障大类代码：
				</td>
				<td style="padding-right:10px;">
				<input type="text" name="faultTypeCode" value="${vo.faultTypeCode}" size="12"/>
				</td>
				<td>
					<a class="button"  onclick="repairTotalExcel('base/repairTotal/excelOutput.do');" title="确定导出信息？"><span>导出EXCEL</span></a>					
				</td>	

			</tr>
			<tr>	
				<td style="padding-right:8px;">
					故障小类代码：
				</td>
				<td style="padding-right:10px;">
					<input type="text" name="faultReasonCode" value="${vo.faultReasonCode}" size="12"/>
					
				</td>
				
				<td style="padding-right:8px;">
					合并故障小类名称：
				</td>
				<td style="padding-right:10px;">
					<input type="text" name="meshFaultName" value="${faultReason.meshFaultName}" size="12"/>
					
				</td>
				<td style="padding-right:8px;">
					下线月份：
				</td>
				<td style="padding-right:10px;">
				<input type="text" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" name="downlineMonth" value="${vo.downlineMonth}" size="12"/>
				</td>
			</tr>
			<tr>
				<td style="padding-right:8px;">
					机型类别： 
				</td>
				<td style="padding-right:10px;">
					<select name="productType" id="r_productType">
					
						<option value="">所有</option>
						<c:forEach items="${typelist}" var="t">
							<option value="${t.machineType}">${t.machineType}</option>
						</c:forEach>
					</select>
					<script type="text/javascript">
						$("#r_productType").val("${vo.productType}");
					</script>
				</td>	
				
				
				<td style="padding-right:8px;">
					维修是否过期
				</td>
				<td style="padding-right:8px;">
					<select name="isOver" id="isOver">
						<option value="">---请选择---</option>
						<option value="是" <c:if test="${'是' eq vo.isOver}">selected</c:if> >是</option> 
						<option value="否" <c:if test="${'否' eq vo.isOver}">selected</c:if> >否</option> 
					</select>
				</td>
				<td style="padding-right:8px;">
					故障小类是否有效
				</td>
				<td style="padding-right:10px;">
					<select name="valid" id="valid">
						<option value="">全选</option>
						<option value="是" <c:if test="${vo.productType eq NULL || vo.productType eq '' || '是' eq faultReason.valid}">selected</c:if> >是</option> 
						<option value="否" <c:if test="${'否' eq faultReason.valid}">selected</c:if> >否</option> 
					</select>
				</td>
				<td style="padding-right:8px;">
					维修月份：
				</td>
				<td style="padding-right:10px;">
					<input type="text" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" id="startTime" name="startTime" value="${vo.startTime}" size="8"/>
						&nbsp;至&nbsp;
					<input type="text" placeholder="请输入日期" id="endTime" onclick="laydate({isym:true, format: 'YYYY-MM'})" readonly="true" name="endTime" value="${vo.endTime}" size="8"/>
				</td>
				
				<td style="line-height:25px;padding-right:0px;">
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="r_checkData();">查找</button></div></div>
					&nbsp;&nbsp;&nbsp;维修总数：<font color="red">${sum}</font>		
				</td>	
				
				

			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th align="center" >机型类别</th>
				<th align="center" >下线月份</th>
				<th align="center" >维修月份</th>	
				<th>产品系列</th>							
				<th align="center" >型号</th>
				<th align="center" >区域</th>
				<th align="center" >产线编号</th>
				<th align="center" >故障大类代码</th>
				<th align="center" >故障小类代码</th>
				<th align="center" >维修数量</th>
				<th align="center"  width="5%">是否过期</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="${o.id}">
					<%--需维护资料--%>					
					<td>${o.productType}</td>
					<td>${o.downlineMonth}</td>
					<td>${o.repairedMonth}</td>
					<td>${o.productFamily}</td>
					<td>${o.partType}</td>
					<td>${o.region}</td>
					<td>${o.productlineNumber}</td>
					<td>${o.faultTypeCode}</td>
					<td>${o.faultReasonCode}</td>
					<td>${o.repairedCount}</td>
					<td>${o.isOver}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../_frag/pager/panelBar.jsp"></c:import>
</div>
