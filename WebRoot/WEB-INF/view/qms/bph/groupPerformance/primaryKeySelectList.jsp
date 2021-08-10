<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
jQuery(document).ready(function(){

	$('#baseGroup_${id_end}',navTab.getCurrentPanel()).change(function(){
		getprimaryKey();
	});
});
function getprimaryKey() {
	var select = $("#pkskey",navTab.getCurrentPanel());
	select.empty();
	select.append("<option value=''>请选择</option>"); 
	var factory = $("#baseFactory_${id_end}",navTab.getCurrentPanel()).val();
	var area = $("#baseArea_${id_end}",navTab.getCurrentPanel()).val();
	var group = $('#baseGroup_${id_end}',navTab.getCurrentPanel()).val();
	var url = "<c:url value='groupPerformanceChart/getprimaryKey.do' />";
	$.post(url,{area: area,factory: factory,groupName:group},function(data){
		if(data.result==1){
	//		    alert(data.list.toString());
			    for(var i in data.list){
			    	var obj = data.list[i];
			    	select.append("<option value="+obj.indexCode+">"+obj.indexName+"</option>");
			    }
			    
		}
		if(data.result==-1){
			
		}
	});   
	} 	
function checkData(){
	var factory = $("#baseFactory_${id_end}",navTab.getCurrentPanel()).val();
	var area = $("#baseArea_${id_end}",navTab.getCurrentPanel()).val();
	var group = $('#baseGroup_${id_end}',navTab.getCurrentPanel()).val();
	var index = $('#pkskey',navTab.getCurrentPanel()).val();
	var startTime = $("#startTime",navTab.getCurrentPanel()).val();
	var endTime = $('#endTime',navTab.getCurrentPanel()).val();
	if(factory==''){
		alert("请选择工厂");
		return;
	}
	if(area==''){
		alert("请选择车间");
		return ;
	}
	if(group==''){
		alert("请选择班组");
		return;
	}
	if(index==''){
		alert("请选择考核指标");
		return;
	}
	if(startTime =='' || endTime ==''){
		alert("请选择期间");
		return;
	}
	$("#primaryKeySelectiveId",navTab.getCurrentPanel()).submit();
}
</script>
<div class="pageHeader">
	<form id="primaryKeySelectiveId" onsubmit="return navTabSearch(this);" rel="pagerForm" action="groupPerformanceChart/primaryKeySelectListDo.do" method="post">
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
				<td>关键指标</td>	
				<td>
				    <select id="pkskey" name="checkIndex">
				        <option value="">请选择</option>
				        <c:forEach items="${index}" var="index">
		                  <option value="${index.indexCode}">${index.indexName}</option>
		                </c:forEach>  
				    </select>
				    <script type="text/javascript">
						$("#pkskey", navTab.getCurrentPanel()).val("${vo.checkIndex}"); 
				    </script>
				</td>
				</tr>
				<tr>
				<td>
					期间：
				</td>
				<td>
					<input type="text" id="startTime" name="startTime" placeholder="请输入日期"  onclick="laydate()" value="${vo.startTime }" readonly="readonly"/>&nbsp;至&nbsp;
					<input type="text" id="endTime" name="endTime" placeholder="请输入日期"  onclick="laydate()" value="${vo.endTime }" readonly="readonly"/>
				</td>				
				
				<td><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="checkData()">查找</button></div></div>
				</td>			
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	
	<table id="pksTab" class="table" width="100%" layoutH="120">
		<thead>
			<tr>
			    <th>工厂</th>
			    <th>车间</th>
			    <th>班组名称</th>
				<th>关键考核指标</th>
				<th>日期</th>
				<th>exl1</th>
				<th>exl1</th>
				<th>实绩值</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="o">
				<tr target="key" rel="">	
				    <td>${o.factory}</td>
				 	<td>${o.area}</td>							
					<td>${o.shiftGroupTxt}</td>
					<td>${o.indexName}</td>
					<td>${o.sumDate}</td> 
					<td>${o.col1}</td> 
					<td>${o.col2}</td> 
					<td>${o.col3}</td>  				
				</tr>		
			</c:forEach>
		</tbody>
	</table>
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
