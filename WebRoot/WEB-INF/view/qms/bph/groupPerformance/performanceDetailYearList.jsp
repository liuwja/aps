<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function(){
//	addScore();
	$("#taby").rowspan(0);//传入的参数是对应的列数从0开始，哪一列有相同的内容就输入对应的列数值
});
function checkYearSel1(){
	var factory = $('#baseFactory_${id_end}', navTab.getCurrentPanel()).val();	
	var area = $('#baseArea_${id_end}',navTab.getCurrentPanel()).val();
	var shiftGroup = $('#baseGroup_${id_end}',navTab.getCurrentPanel()).val();
	var startTime = $('#startTime',navTab.getCurrentPanel()).val();
	var endTime = $('#endTime',navTab.getCurrentPanel()).val();
    if(factory ==""){
        alert("请选择工厂"); 
        return false;      
    }   
    if(area == ""){
    	alert("请选择车间");
    	return false;
    }
    if(shiftGroup ==""){
    	alert("请选择班组");
    	return false;
    }
    if(startTime == "" || endTime == ""){
    	alert("请选择期间");
    	return false;
    }  
    $("#btyear").submit();
}

</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="shiftGroupPerformanceChart/performanceDetailListYearYear.do" method="post">
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
					<jsp:param value="1" name="fagroup"/>
					<jsp:param value="false" name="isRequired"/>
					<jsp:param value="0" name="thClass"/>
					<jsp:param value="0" name="isColspan"/>
				</jsp:include>
				<td>
					月份：
				</td>
				<td>
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.startTime }" readonly="readonly" />&nbsp;&nbsp;
					至
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.endTime }" readonly="readonly" />
				</td>				
				
				<td><div class="buttonActive"><div class="buttonContent"><button id="btyear" type="button" onclick="checkYearSel1();">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>

<div class="pageContent">
<input type="hidden" id="msLength" value="${fn:length(scoreList) }"/>
<c:forEach items="${scoreList }" var="scl" varStatus="sta">
   <input type="hidden" id="in${sta.index }" value="${scl.itemScore }"/>
</c:forEach>	
	<table id="taby" class="list" width="100%" layoutH="95">
		<thead>		
			<tr id="pdthead">
				<th>考核项目</th>
				<th>KPI指标</th>
			<!--  	<th>基准</th>
				<th>目标</th>   -->
				<th>考核比例</th>	
				<c:forEach items="${scoreMap}" var="ent" begin="0" end="0">
					<c:forEach items="${ent.value}" var="e"> 
						<th >${e.key}实绩</th>
						<th >${e.key}得分</th>	
						<th >${e.key}总分</th>	
					</c:forEach> 
				</c:forEach>	    						
			</tr>
		</thead>
		<tbody>
		    	<c:set var="len" value="${fn:length(indexList) }"></c:set>
				<c:forEach items="${indexList}" var="idx" begin="0" end="0">
					<tr>
							<td>${idx.checkItem}</td>
							<td>${idx.indexCode}:${idx.checkIndex}</td>
			<!-- 			<td>${idx.baseValue}</td>
							<td>${idx.targetValue}</td>    -->	
							<td>${idx.scale == -1 ? '扣分' : idx.scale}</td>
						<c:forEach items="${scoreMap[idx.id]}" var="en" varStatus="vst">
							<td>${en.value.indexActValue}</td>
							<td>${en.value.indexScore}</td>
							<td rowspan="${len}" id="sco${vst.index }">${en.value.homeScore}</td>
	               		</c:forEach>	
					</tr>
            	</c:forEach>	
               		
				<c:forEach items="${indexList}" var="idx" begin="1">
					<tr>
							<td>${idx.checkItem}</td>
							<td>${idx.indexCode}:${idx.checkIndex}</td>
				<!--  			<td>${idx.baseValue}</td>
							<td>${idx.targetValue}</td>  -->
							<td>${idx.scale == -1 ? '扣分' : idx.scale}</td>
						<c:forEach items="${scoreMap[idx.id]}" var="en" >
							<td>${en.value.indexActValue}</td>
							<td>${en.value.indexScore}</td>
	               		</c:forEach>	
					</tr>
               	</c:forEach>	               	
		</tbody>
	</table>	
<!--  	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import> -->
</div>
