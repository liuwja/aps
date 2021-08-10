<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function(){
	  $("#baseFactory_${id_end}",navTab.getCurrentPanel()).change(function(){
		  getIpqcGroup();
	  });
});
	function assembly_checkData(){
	
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();

		if(endTime<startTime){
			alert("查询结束月份不能小于开始月份");
			$("#endTime", navTab.getCurrentPanel()).val("")
			return false;
		}
		
		$("#ipqcForm").submit();
		
	}
/**	
	function getIpqcArea() {
	var url = "<c:url value='commonSelected/getArea.do' />";
	$("#workcenterS", navTab.getCurrentPanel()).load(url,{factory: $("#factoryS",navTab.getCurrentPanel()).val()});   
	}
**/	
	function getIpqcGroup() {
	var factory =  $("#baseFactory_${id_end}",navTab.getCurrentPanel()).val();
	var url = "<c:url value='commonSelected/getShiftGroupByFactory.do' />";
	$("#ipqcGroup", navTab.getCurrentPanel()).load(url,{factory: factory}); 
	$("#ipqcDutyGroup", navTab.getCurrentPanel()).load(url,{factory: factory}); 
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
		
		var objs = $("#ipqcForm input",navTab.getCurrentPanel());
		var objs2 = $("#ipqcForm select",navTab.getCurrentPanel());
		
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
	
	function loadIpqcDefectChar() {
		var factory = $("#baseFactory_${id_end}", navTab.getCurrentPanel()).val();
		var shiftGroupTxt = $('select[name="dutyGroup"]', navTab.getCurrentPanel()).val();
	    if(shiftGroupTxt==""){
	        alert("请责任班组");
	        return false;
	    }
	    
		var startTime = $("#startTime", navTab.getCurrentPanel()).val();
		var endTime = $("#endTime", navTab.getCurrentPanel()).val();
		
		var url = "<c:url value='base/ipqcChar/ipqcDefectChar.do'/>";
				
		navTab.openTab('ipqcDefectCharNav', url, { title:'IPQC不良现象统计表', fresh:true, data:{factory:factory,shiftGroupTxt:shiftGroupTxt,startTime:startTime,endTime:endTime} });
	}	
	
	function loadIpqcScoreChar() {
		var factory = $("#baseFactory_${id_end}", navTab.getCurrentPanel()).val();
		var shiftGroupTxt = $('select[name="dutyGroup"]', navTab.getCurrentPanel()).val();
	    if(shiftGroupTxt==""){
	        alert("请责任班组");
	        return false;
	    }
		
		var url = "<c:url value='base/ipqcChar/ipqcScoreChar.do'/>";
				
		navTab.openTab('ipqcScoreCharNav', url, { title:'巡检批次不良率时间序列表', fresh:true, data:{factory:factory,shiftGroupTxt:shiftGroupTxt} });
	}	
	
</script>
<div class="pageHeader">
	<form id="ipqcForm" onsubmit="return navTabSearch(this);" rel="pagerForm" action="base/commonselect/ipqcInspects.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<jsp:include page="../../common/factory_area_group.jsp" flush="true">
					<jsp:param value="inline" name="dispalyType"/>
					<jsp:param value="1" name="factory"/>
					<jsp:param value="1" name="area"/>
					<jsp:param value="0" name="category"/>
					<jsp:param value="0" name="fcategory"/>
					<jsp:param value="0" name="checkItem"/>
					<jsp:param value="0" name="fgroup"/>
					<jsp:param value="0" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>	
				
				<td>
					发生班组： 
				</td>
				<td >
					<select name="group" id="ipqcGroup">
					
						<option value="">请选择</option>
						<c:forEach items="${group}" var="group">
						    <option value="${group.name}">${group.name}</option>
						</c:forEach>
					</select>

                    <script type="text/javascript">
                        $("#ipqcGroup",navTab.getCurrentPanel()).val("${vo.group}"); 
                    </script>					
				</td>
				<td>物料名称：</td>
				<td> <input type="text" name="partName" size="15" value="${param.partName }"></td>
			</tr>
			<tr>
			    <td>
					责任班组： 
				</td>
				<td >
					<select name="dutyGroup" id="ipqcDutyGroup">
					
						<option value="">请选择</option>
						<c:forEach items="${group}" var="group">
						    <option value="${group.name}">${group.name}</option>
						</c:forEach>
					</select>

                    <script type="text/javascript">
                        $("#ipqcDutyGroup",navTab.getCurrentPanel()).val("${vo.dutyGroup}"); 
                    </script>					
				</td>
				<td>
					判定结果： 
				</td>
				<td>
					<select name="inspectResultS" id="dutyS">
					
						<option value="">请选择</option>
						<option value="合格">合格</option>
						<option value="不合格">不合格</option>
					</select>
                    <script type="text/javascript">
                        $("#dutyS", navTab.getCurrentPanel()).val("${vo.inspectResultS}"); 
                    </script>
				</td>
					
				<td>
					发生日期： 
				</td>
				<td>
					<input type="text"  name="startTime" id="startTime" size="10" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.startTime}" type="date" pattern="yyyy-MM-dd "/>"/>
					至 <input type="text" name="endTime" id="endTime" size="10" placeholder="请输入日期"  onclick="laydate()" readonly="true" value="<fmt:formatDate value="${vo.endTime}" type="date" pattern="yyyy-MM-dd "/>"/>
				</td>

				<td colspan="2">
				<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="assembly_checkData()">查找</button></div></div>
				<a class="button" href="#" onclick="exportExcel('base/commonselect/ipqcInspectsExcelOutput.do');"   title="确定导出信息？"><span>导出EXCEL</span></a>
				</td>
				<td>
				  <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadIpqcDefectChar()">柏拉图</button></div></div>
				  <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadIpqcScoreChar()">时间序列图</button></div></div>
				</td>
				</tr>	
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr>
			    <th width="3%">选择</th>
				<th width="6%">发生日期</th>
				<th width="5%">工厂</th>
				<th width="5%">发生车间</th>
				<th width="6%">发生班组</th>
				<th width="4%">工单号</th>
				<th width="5%"> 物料名称</th>
				<th width="3%">抽检频次</th>
				<th width="3%">不良频次</th>
				<th width="3%">抽检数量</th>
				<th width="3%">不良数量</th>
				<th width="5%">责任工序</th>
				<th width="5%">判定结果</th>
				<th width="5%">不良现象</th>
				<th width="6%">责任班组1</th>
				<th width="5%">责任班组2</th>
				<th width="5%">责任班组3</th>	
				<th width="4%">发生流程节点</th>
				<th width="4%">批量大小</th>
				<th width="4%">质量后果</th>
				<th width="4%">质量风险分数</th>				
				<th width="5%">判定人</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr>
					<%--需维护资料--%>
					<td>
						<input type="radio" group="code" name="key">
					</td>
					<td width="6%">${o.findTimeS}</td>
					<td>${o.factoryS}</td>
					<td>${o.workcenterS}</td>
					<td>${o.groupNameS}</td>
					<td>${o.orderNumberS}</td>
					<td>${o.finderS}</td>				
					<td >${o.checkQtyI}</td>
					<td>${o.defectNum}</td>
					<td>${o.feedingQtyI}</td>
					<td >${o.unquantityQtyI}</td>
					<td>${o.stepNameS}</td>
					<td>${o.inspectResultS}</td>
					<td>${o.defectTypeS}</td>
					<td>${o.group1S}</td>
					<td>${o.group2S}</td>
					<td>${o.group3S}</td>
					<td>${o.processNode}</td>
					<td>${o.batch}</td>
					<td>${o.result}</td>
					<td>${o.riskScore}</td>
					<td>${o.inspectManS}</td>

				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>

