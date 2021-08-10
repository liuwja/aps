<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
	function boxDefect_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();

		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("")
			return false;
		}
		
		$("#boxDefect").submit();
		
	}
/**	
	function getBoxDefectGroup() {
	var url = "<c:url value='commonSelected/getShiftGroupByFactory.do' />";
	$("#boxgroup", navTab.getCurrentPanel()).load(url,{factory: $("#boxfactoryS",navTab.getCurrentPanel()).val()});   
	}
**/	
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
		
		var objs = $("#boxDefect input",navTab.getCurrentPanel());
		var objs2 = $("#boxDefect select",navTab.getCurrentPanel());
		
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
	<form id="boxDefect" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/boxDefectList.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<jsp:include page="../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="0" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="1" name="fgroup"/>
					<jsp:param value="0" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>	
				<td>
					不良来源： 
				</td>
				<td>
					<select name="defectSourceS" id="defectSourceS">
					
						<option value="">请选择</option>
						<option value="所有">所有</option>
						<option value="供应商">开箱不良</option>
						<option value="内部">流行性不良</option>
					</select>
					<script type="text/javascript">
                        $("#defectSourceS", navTab.getCurrentPanel()).val("${param.defectSourceS}"); 
                    </script>
				</td>
			</tr>
			<tr>
				
				<td>
					 故障机型： 
				</td>
				<td>
					<input type="text" name="defectTypeS" value="${param.defectTypeS}" size="20"/>
				</td>
				<td>
					产品编号或范围： 
				</td>
				<td>
					<input type="text" name="productNumberS" value="${param.productNumberS}" size="20"/>
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
<!--  				
				 <td>发生流程节点</td>
			   <td> 
			       <select name="processNodeS" id="processNodeS">
						<option value="">请选择</option>
						<c:forEach items="${process}" var="process">
						     <option value="${process.indexCode}">${process.indexCode}-${process.content}-${process.score}分</option>
						</c:forEach>
					</select>
					<script type="text/javascript">
                        $("#processNodeS", navTab.getCurrentPanel()).val("${param.processNodeS}"); 
                    </script>
			   </td>
			   <td>批量大小</td>
			   <td> 
			       <select name="lotQtyS" id="lotQtyI">
						<option value="">请选择</option>
						<option value="A">A-批量大小>500</option>
						<option value="B">B-50&lt;批量大小在&lt;=500之间 </option>
						<option value="C">C-10&lt;批量大小&lt;=50 </option>
						<option value="D">D-0&lt;批量大小&lt;=10 </option>
					</select>
					<script type="text/javascript">
                        $("#lotQtyI", navTab.getCurrentPanel()).val("${param.lotQtyS}"); 
                    </script>
			   </td>
			</tr>
			<tr>

			   <td>质量后果</td>
			   <td> 
			       <select name="qualityResultS" id="qualityResultS">
						<option value="">请选择</option>
					     <c:forEach items="${result}" var="result">
						     <option value="${result.indexCode}">${result.indexCode}-${result.content}-${result.score}分</option>
						</c:forEach>
					</select>
					<script type="text/javascript">
                        $("#qualityResultS", navTab.getCurrentPanel()).val("${param.qualityResultS}"); 
                    </script>
			   </td>
			     <td>质量风险分数</td>
			   <td> 
			      <input type="text" name="lowRistScore" value="${param.lowRistScore}" size="10"/> &nbsp;至 &nbsp;
			      <input type="text" name="topRistScore" value="${param.topRistScore}" size="10"/>
			   </td>
-->
				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="boxDefect_checkData()">查找</button></div></div>
				<a class="button" href="#" onclick="exportExcel('base/commonselect/boxDefectExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
				</td>
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	
	<table class="table" width="100%" layoutH="142">
		<thead>
			<tr>
			    <th width="3%">选择</th>
				<th width="6%">发生日期</th>
				<th width="6%">工厂</th>
				<th width="6%">不良来源</th>
				<th width="4%">故障机型</th>
				<th width="4%">故障机型类别</th>
				<th width="6%">故障零件名称</th>
				<th width="4%">产品编号或范围</th>
				<th width="6%">故障现象</th>
				<th width="7%">故障小类分析</th>
<!-- 								
				<th width="6%">发生流程节点</th>
				<th width="4%">批量大小</th>
				<th width="4%">质量后果</th>
				<th width="4%">质量风险系数</th>
 -->				
				<th width="6%">责任单位1</th>
				<th width="6%">责任单位2</th>
				<th width="5%">责任单位3</th>
				<th width="6%">记录人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key">
					</td>
					<td width="6%"><fmt:formatDate value="${o.recordDateT}" type="date" pattern="yyyy-MM-dd "/></td>
					<td width="6%">${o.factoryS}</td>
					<td width="7%">${o.defectSourceS}</td>				
					<td width="5%">${o.defectTypeS}</td>
					<td width="5%">${o.defectCategoryS}</td>
					<td width="5%">${o.defectSpareNameS}</td>
					<td width="5%">${o.productNumberS}</td>
					<td width="7%">${o.defectS}</td>
					<td width="7%">${o.defectReasonS}</td>	
<!--  									
					<td width="4%">${o.processNodeS}</td>
					<td width="4%">${o.lotQtyS}</td>
					<td width="4%">${o.qualityResultS}</td>
					<td width="4%">${o.qualityScoreI}</td>
-->					
					<td width="6%">${o.group1S}</td>
					<td width="5%">${o.group2S}</td>
					<td width="5%">${o.group3S }</td>
					<td width="5%">${o.recordManS}</td>

				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

