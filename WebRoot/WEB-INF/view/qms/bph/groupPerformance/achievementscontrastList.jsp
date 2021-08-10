<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<style>
.dropdownSearchBar {
    background-color: #ebf0f5;
    border-color: #99bbe8;
    border-style: solid;
    border-width: 1px 0;
    margin-top: 2px;
    padding: 5px;
    width: 100%;
}
</style>
<script type="text/javascript">
jQuery(document).ready(function(){
	$('#baseGroup_${id_end}',navTab.getCurrentPanel()).change(function(){
		getprimaryKey();
	});
	var jsongroup=(new Function("","return "+'${group}'))();
	var jsongroupsel=(new Function("","return "+'${groupsel}'))();
	var jsonitem=(new Function("","return "+'${item}'))();
	var jsonitemsel=(new Function("","return "+'${itemsel}'))();
	var jsonindex=(new Function("","return "+'${index}'))();
	var jsonindexsel=(new Function("","return "+'${indexsel}'))();
	setgroup(jsongroupsel,jsongroup);
	setitem(jsonitemsel,jsonitem);
	setindex(jsonindexsel,jsonindex);
	$('#baseCategory_${id_end}',navTab.getCurrentPanel()).on("change",function(){
		getitem();
		getgroup();		
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
	$("#achievementscontrastid",navTab.getCurrentPanel()).submit();
}
function setgroup(arr,json){//arr：数组；json：json；班组名称
	$('#grouplist').dropdownlist({
		id:'grouplistselect',
		columns:2,
	    selectedtext:'',
	    listboxwidth:400,//下拉框宽
	    maxchecked:100,
	    checkbox:true,
	    listboxmaxheight:400,
	    width:120,
	    requiredvalue:[],
	    selected:arr==""?[]:arr,//默认选中
	    data:json,//数据，格式：{value:name}
	    onchange:function(text,value){
	    	getindex();
	    }
	});
}
function setitem(arr,json){//arr：数组；json：json；考核项目
	$('#itemlist').dropdownlist({
		id:'itenlistselect',
		columns:2,
	    selectedtext:'',
	    listboxwidth:400,//下拉框宽
	    maxchecked:100,
	    checkbox:true,
	    listboxmaxheight:400,
	    width:120,
	    requiredvalue:[],
	    selected:arr==""?[]:arr,//默认选中
	    data:json,//数据，格式：{value:name}
	    onchange:function(text,value){
	    	getindex();
	    }
	});
}
function setindex(arr,json){//arr：数组；json：json；考核指标
	$('#indexlist').dropdownlist({
		id:'indexlistselect',
		columns:2,
	    selectedtext:'',
	    listboxwidth:400,//下拉框宽
	    maxchecked:200,
	    checkbox:true,
	    listboxmaxheight:400,
	    width:120,
	    requiredvalue:[],
	    selected:arr==""?[]:arr,//默认选中
	    data:json,//数据，格式：{value:name}
	    onchange:function(text,value){}
	});
}
function getgroup(){
	var url_1 = "<c:url value='groupPerformanceChart/getgroup.do'/>";
	$.post(url_1, $("#achievementscontrastid").serialize(), function(data) {
		if(data.result==0){
			setgroup('',data.group)
		}if(data.result==1){
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}
function getitem(){//查询考核项目
	var url_1 = "<c:url value='groupPerformanceChart/getitem.do'/>";
	$.post(url_1, $("#achievementscontrastid").serialize(), function(data) {
		if(data.result==0){
			setitem('',data.item)
		}if(data.result==1){
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}

function getindex(){//查询考核指标
	var v=$("#itenlistselect").val();//id=itenlist+select
	var url_1 = "<c:url value='groupPerformanceChart/getindex.do'/>";
	$.post(url_1, {Str:v}, function(data) {
		if(data.result==0){
			setindex('',data.index)
		}if(data.result==1){
			alertMsg.error("查询出错，请联系管理员");
			return ;
		}
	});
}
function gettable(){
	/*var val=$("#grouplistselect").val();
	var val2=$("#itemlistselect").val();
	var val3=$("#indexlistselect").val();*/
	var baseFactory = $('select[name="baseFactory"]', navTab.getCurrentPanel()).val();
	if(baseFactory==""){
        alert("请选择工厂");
        return false;
    }
	var baseArea = $('select[name="baseArea"]', navTab.getCurrentPanel()).val();
	if(baseArea==""){
        alert("请选择车间");
        return false;
    }
	var baseGroup = $('select[name="baseCategory"]', navTab.getCurrentPanel()).val();
	if(baseGroup==""){
        alert("请选择班组类别");
        return false;
    }
	var baseGroup = $('#grouplistselect', navTab.getCurrentPanel()).val();
	if(baseGroup==""){
        alert("请选择班组");
        return false;
    }
	var baseGroup = $('#monthly', navTab.getCurrentPanel()).val();
	if(baseGroup==""){
        alert("请选择月份");
        return false;
    }
	$("#achievementscontrastid").attr("action","groupPerformanceChart/achievementscontrastshow.do");
	$("#achievementscontrastid",navTab.getCurrentPanel()).submit();
}

function setchar(obj){
	var array = obj.split("-");
	var arr=array[0];
	var indexkey=array[1];
	var mon=$("#monthly",navTab.getCurrentPanel()).val();
	navTab.openTab('ContrastTimeChar', "groupPerformanceChart/contrastTimeChar.do", { title:'对比时间序列图', fresh:true, data:{str:obj,mon:mon}});	
}
</script>
<div class="pageHeader" style="position:static">
     <form id="achievementscontrastid" onsubmit="return navTabSearch(this);" rel="pagerForm" action="groupPerformanceChart/achievementscontrast.do" method="post">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
				    <jsp:include page="../../common/factory_area_group.jsp" flush="true">
						<jsp:param value="inline" name="dispalyType"/>
						<jsp:param value="1" name="factory"/>
						<jsp:param value="1" name="area"/>
						<jsp:param value="1" name="category"/>
						<jsp:param value="0" name="fcategory"/>
						<jsp:param value="0" name="checkItem"/>
						<jsp:param value="0" name="fgroup"/>
						<jsp:param value="0" name="fagroup"/>
						<jsp:param value="false" name="isRequired"/>
						<jsp:param value="0" name="thClass"/>
						<jsp:param value="0" name="isColspan"/>
					</jsp:include>
					<td>班组名称:</td>	                     
	                <td>
	                    <div id="grouplist" class="dropdownlist"></div>
	                </td>
                </tr>
                <tr>
					<td>考核项目:</td>	                     
	                <td>
	                    <div id="itemlist" class="dropdownlist"></div>
	                </td>
					<td>考核指标:</td>	
					<td>
	                    <div id="indexlist" class="dropdownlist"></div>
	                </td>
					<td>月份:</td>	
					<td>
					    <input type="text" id="monthly"  name="monthly" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${param.monthly }" readonly="true"/>
					</td>	
					<td><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="gettable()">查找</button></div></div>
					</td>			
				</tr>
			</table>
		</div>
	</form>
</div>
<div class="pageContent" >
	<div style="height: 96%">
	<table id="fixTable" class="fixTable" style="width: 100%">
		<c:if test="${column!= null || fn:length(column) != 0}">
		<thead style="background: #cae8ea">
			<tr>
				<th rowspan="2">项目</th>
				<th rowspan="2">指标</th>
				<th colspan="${fn:length(column)}">班组名称</th>
				<th rowspan="2">时间序列图</th>
			</tr>
			 <tr>
				<c:forEach items="${column}" var="m">
					<th>${m}</th>
				</c:forEach>
			</tr>
		</thead>
		</c:if>
		<tbody id="fixTableBody">
			<c:forEach items="${map}" var="m" begin="0" varStatus="status">
				<tr class="trginsd_tr">
					<td style="min-width:70px;" rowspan="${fn:length(m.value)}">${m.key}</td>
					<c:forEach items="${m.value}"  var="list" varStatus="sub" begin="0" end="0">
							<c:forEach items="${list}"  var="num" varStatus="sub2">
							   	<c:set var="len" value="${fn:length(list) }"></c:set>
						   		     <c:forEach items="${num }" var="str">
						   		       <c:choose>
								   			<c:when test="${sub2.index==len-1}">
								   			    <c:if test="${str ne ' ' }">
								   			       <td><input type="button" value="对比" onclick="setchar('${str}')"/></td>
								   			    </c:if>
								   			    <c:if test="${str eq ' ' }">
								   			       <td></td>
								   			    </c:if>
								   			</c:when>
								   			<c:otherwise>
								   				<td>${str}</td>	
								   			</c:otherwise>
								   		</c:choose>  
						   		     </c:forEach>   
							</c:forEach>
					</c:forEach>
				</tr>							
				<c:forEach items="${m.value}"  var="list" varStatus="sub">					
					<c:if test="${sub.count>1}">
						<tr class="trginsd_tr">	
							<c:forEach items="${list}"  var="num" varStatus="sub2">
								<c:set var="len" value="${fn:length(list) }"></c:set>
						   		<c:forEach items="${num }" var="str">
					   		       <c:choose>
							   			<c:when test="${sub2.index==len-1}">
							   			    <c:if test="${str ne ' ' }">
							   			       <td><input type="button" value="对比" onclick="setchar('${str}')"/></td>
							   			    </c:if>
							   			    <c:if test="${str eq ' ' }">
							   			       <td></td>
							   			    </c:if>
							   			</c:when>
							   			<c:otherwise>
							   				<td>${str}</td>	
							   			</c:otherwise>
							   		</c:choose>  
						   		</c:forEach>
							</c:forEach>
						</tr>
					</c:if>						
				</c:forEach>				
			</c:forEach>
		</tbody>
	</table>
	</div>
</div>	