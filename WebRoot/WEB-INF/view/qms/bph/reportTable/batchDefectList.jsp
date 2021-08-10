<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	function batchDefect_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();

		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("")
			return false;
		}
		var area = $("#baseArea_"+${id_end},navTab.getCurrentPanel()).val();
		var group = $("#baseGroup_"+${id_end},navTab.getCurrentPanel()).val();
		if(area != null){
			if(group == null || group ==''){
				alert("请选择班组名称！");
				return false;
			}
		}
		
		$("#batchDefect").submit();
		
	}
		
		function exportExcel(url){   
		    var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		    var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		    if(startTime==''){
		    	alert("请选择开始时间！");
		    	return;
		    }
		    if(endTime==''){
		    	alert("请选择结束时间！");
		    	return;
		    }

			var myForm = document.createElement("form");
			myForm.action= url;
			myForm.method="post"; 
			myForm.target="noexistForm"; 
			var myInput;
			
			var objs = $("#batchDefect input",navTab.getCurrentPanel());
			var objs2 = $("#batchDefect select",navTab.getCurrentPanel());
			
			for(var i = 0 ; i< objs.length+objs2.length ; i++){
				var $obj = null;
				if(i>=objs.length){
					$obj = $(objs2[i-objs.length]);	
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
			document.body.appendChild(myForm);
			myForm.submit();
		}
</script>
<div class="pageHeader">
	<form id="batchDefect" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/batchDefectList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<jsp:include page="../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="1" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>			
			<tr>
			    <td>
					产品型号： 
				</td>
				<td>
					<input type="text" name="typeS" value="${vo.typeS}" size="20"/>
				</td>
				<td>
					产品名称： 
				</td>
				<td>
					<input type="text" name="productNameS" value="${vo.productNameS}" size=20""/>
				</td>
				<td>
					不良现象： 
				</td>
				<td>
					<input type="text" name="defectS" value="${vo.defectS}" size="20"/>
				</td>
			</tr>
			<tr>
				 <td>
					发生日期： 
				</td>
				<td>
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.startTime}" type="date" pattern="yyyy-MM-dd "/>" size="10"/>
					至 <input type="text" id="endTime" name="endTime" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.endTime}" type="date" pattern="yyyy-MM-dd "/>" size="10"/>
				</td>
				
				 <td>发生流程节点</td>
			    <td>
			       <select name="processNodeS" id="processNodeS">
						<option value="">请选择</option>
						<c:forEach items="${process}" var="process">
						     <option value="${process.indexCode}">${process.indexCode}-${process.content}&nbsp; ${process.score} 分</option>
						</c:forEach>
					</select>
					<script type="text/javascript">
                        $("#processNodeS", navTab.getCurrentPanel()).val("${param.processNodeS}"); 
                    </script>
			    </td>			
			</tr>
			
			<tr>
			   
			    <td>批量大小</td>
			    <td>
			       <select name="batchS" id="batchI">
						<option value="">请选择</option>
						<option value="A">A-批量大小>500</option>
						<option value="B">B-50&lt;批量大小在&lt;=500之间 </option>
						<option value="C">C-10&lt;批量大小&lt;=50 </option>
						<option value="D">D-0&lt;批量大小&lt;=10 </option>
					</select>
					<script type="text/javascript">
                        $("#batchI", navTab.getCurrentPanel()).val("${param.batchS}"); 
                    </script>
			    </td>
			    <td>质量后果</td>
			    <td>
			       <select name="resultS" id="resultS">
						<option value="">请选择</option>
						<c:forEach items="${result}" var="result">
						   <option value="${result.indexCode}">${result.indexCode}-${result.content}-${result.score}</option>
						</c:forEach>
					</select>
					<script type="text/javascript">
                        $("#resultS", navTab.getCurrentPanel()).val("${param.resultS}"); 
                    </script>
			    </td>	
			     <td>质量风险分数</td>
			    <td>
			       <input type="text" name="lowRistScore" value="${param.lowRistScore}" size="10"/> &nbsp;至 &nbsp;   <input type="text" name="topRiskScore" value="${param.topRiskScore}" size="10"/>
			    </td>

				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="batchDefect_checkData()">查找</button></div></div>
				<a class="button" href="#" onclick="exportExcel('base/commonselect/batchDefectExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
				</td>		   
			</tr>

		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	
	<table class="table" width="150%" layoutH="170">
		<thead>
			<tr>
			    <th></th>
			    <th width="5%">发生日期</th>
			    <th width="4%">工厂</th>
		        <th width="4%">发生车间</th>   
			    <th width="4%">产线</th>
				<th width="5%">发生班组</th>
				<th width="4%">班组长</th>
				<th width="3%">产品型号</th>		
				<th width="7%">产品名称</th>	
				<th width="4%">检查工序</th>	
				<th width="3%">发现方</th>
				<th width="6%">不良部件</th>					
				<th width="3%">总数</th>
				<th width="3%">不良数</th>
				<th width="3%">不良率</th>
				<th width="5%">不良现象</th>	
				<th width="3%">处理人</th>			
				<th width="6%">责任单位1</th>
				<th width="4%">责任单位2</th>
				<th width="4%">责任单位3</th>
				<th width="3%">责任人</th>
				<th width="4%">临时措施</th>
				<th width="3%">5M1E</th>
				<th width="2%">发生流程节点</th>
				<th width="2%">批量大小</th>
				<th width="2%">质量后果</th>
				<th width="3%">质量风险分数</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key">
					</td>
					<td ><fmt:formatDate value="${o.recordDateT}" type="date" pattern="yyyy-MM-dd"/></td>
					<td>${o.factoryS}</td>
					<td>${o.areaS}</td>
					<td>${o.lineS}</td>
					<td>${o.finderS}</td>
					<td>${o.groupLeader}</td>
					<td>${o.typeS}</td>
					<td >${o.productNameS}</td>	
					<td >${o.checkStep}</td>	
					<td >${o.orderNumberS}</td>	
					<td >${o.productNumberS}</td>		
					<td >${o.totalQtyI}</td>
					<td >${o.defectQtyI}</td>
					<td>${o.rateS}</td>
					<td>${o.defectS}</td>
					<td>${o.workMan}</td>
					<td >${o.group1S}</td>
					<td>${o.group2S}</td>
					<td >${o.group3S }</td>
					<td >${o.dutyMan }</td>
					<td >${o.method }</td>
					<td >${o.meNode }</td>
					<td >${o.processNodeS}</td>
					<td >${o.batchS}</td>
					<td >${o.resultS}</td>
					<td >${o.riskScoreI}</td>
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

