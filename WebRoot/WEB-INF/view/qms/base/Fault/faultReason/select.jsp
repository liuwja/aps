<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {
    loadSelectedUsers();
});

function loadSelectedUsers(usernames) {
	var groupName = "${vo.groupName}";
	if($.trim(groupName) != "") {
		var keys = $("#"+groupName+"_id").val();
		if($.trim(keys) != "") {
			var url = "<c:url value='qms/common/queryFaultReasonByid.do' />";
			$("#selectFaultReasonTbody").load(url,{keys:keys},function() {
				$("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
			});
		}
	}
}

function delSelectedUser(obj) {
	$(obj).parent().parent().remove();
	$("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
}

function selectRow(row,keystr,name,meshName,code) {
	var obj = $("#selectFaultReasonTbody").find("tr[id="+keystr+"]");
    if(obj.length == 0) {
		var cloneRow = $(row).clone(true);
		cloneRow.find("td:eq(0)").remove();
	 	var userRowArr = new Array();
	 	userRowArr.push("<tr  target='id' id='" + keystr +"'>");
	 	userRowArr.push("<td style='text-align: center;'>");
	 	var vv = "{id:'" +keystr+"',name: '"+name+"',meshname:'"+meshName+"',code:'"+code+"'}" ;
     	userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'"/>');
		userRowArr.push("</td>");
		userRowArr.push(cloneRow.html());
		userRowArr.push("<td style='text-align: center;' width='10%''>");
		userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
		userRowArr.push("</td>");
	 	userRowArr.push("</tr>");
	 	$("#selectFaultReasonTbody").append(userRowArr.join(""));
     	$("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
	} else {
    	alertMsg.info("此故障小类已选择");	
   	}
}

function removeSelectedItem() {
	$('#selectFaultReasonTbody tr').remove();
	$("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
}

function callFunc() {
	alert($("#noticeGroup_noticeMen").val());
}

function mergeSelectedItem() { //合并
	var keyArry = [];
	$("#selectFaultReasonTbody").find("tr").each(function(i){
		keyArry.push($(this).attr("id"));
	});
	var keys = keyArry.join(",");
	var url = "base/fault/faultReason/mesh.do?keys="+keys;
	var opt = {width:500,height:200, mask:true};
	$.pdialog.open(url, "dlg_mesh", "合并", opt);
}

function breakSelectedItem(){ //拆分
	var keyArry = [];
	$("#selectFaultReasonTbody").find("tr").each(function(i){
		keyArry.push($(this).attr("id"));
	});
	var keys = keyArry.join(",");
	$.ajax({
		type:"post",
		data:{"keys":keys},
		url:"base/fault/faultReason/breakMesh.do",
		success:function(data){
			$.pdialog.closeCurrent();
			if(data=="success"){
				alertMsg.correct("拆分成功！");
			}else{
				alertMsg.error("拆分失败！");
			}
			navTab.reload("base/fault/faultReason/faultReason.do");
		}
	});
}
</script>
<div class="pageContent">
	<div style="float: left;width: 55%;border: 1px solid #ededed;" id="toSelectFaultReasonList">
		<div  class="pageHeader">待选列表</div>
    	<form onsubmit="return  divSearch(this, 'toSelectFaultReasonList')" id="pagerForm" action="qms/commonSelect/faultReasonSelectResult.do" method="post">
			<div class="pageHeader">
			    <div class="searchBar">
			        <table class="searchContent">
			        	<tr>
			        		<td class="dateRange">故障名称:</td>
			        		<td><input type="text" name="name" id="name" size="10" value="${vo.name}"/></td>
			                <td class="dateRange">故障代码:</td>
			                <td><input type="text" name="code" id="code" size="10" value="${vo.code}"/></td>
			        	</tr>
			        	<tr>
			        		<td class="dateRange">合并故障名称:</td>
			                <td>
								<input type="text" name="meshFaultName" id="meshFaultName" size="10" value="${vo.meshFaultName}"/>
								<input type="hidden" name="pageNum" value="1" />
    							<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
    							<input type="hidden" name="direction" value="" />	 
			                </td>
			        		<td>机型类别:</td>
							<td>
								<select name="productType" >
									<option value="">请选择</option>
									<c:forEach items="${productTypes}" var="o">
									<option value="${o.machineType}" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
									</c:forEach>
								</select>
							</td>	
							<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div></td>  
			        	</tr>
			        </table>
			    </div>
			</div>
	        <table class="table" width="100%" layoutH="170">
	            <thead>
	                <tr>
	                    <th width="5%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
	                    <th width="10%">合并故障代码</th>
	                    <th width="25%">合并故障名称</th>
	                    <th width="10%">机型类别</th>
	                    <th width="10%">故障代码</th>
	                    <th width="25%">故障名称</th>
	                </tr>
	            </thead>
	            <tbody> 
	                <c:forEach items="${list}" var="o">
	                	<c:set value="${fn:length(o.faultList)}" var="len"></c:set>
	                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.name}','${o.meshFaultName}','${o.code}')">
	                        <td style="text-align: center;">
	                            <input type="checkbox" name="chkbox" >
	                        </td>
	                        <td>${o.meshFaultCode}</td>
	                        <td>${o.meshFaultName}</td>
	                        <td>${o.productType}</td>
	                        <td>${o.code}</td>
	                        <td>${o.name}</td>
	                    </tr>       
	                </c:forEach>        
	            </tbody>
	        </table>
			<c:import url="../../../../_frag/pager/panelBar.jsp">
		       <%-- 对话框分页时需要传此参数 --%>
		       <c:param name="targetName" value="dialog"/>
		       <c:param name="relDivId" value="toSelectFaultReasonList"/>    
			</c:import>   
    	</form>
	</div>
	<div style="float: right;width: 44%;border: 1px solid #ededed;">
    	<div class="pageHeader">已选择列表</div>
    	<div class="pageHeader">    
        	<div class="searchBar">
            	<table class="searchContent">         
                	<tr>
                    	<td><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="removeSelectedItem()">全部删除</button></div></div></td>  
                    	<td><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="mergeSelectedItem()">合并选择项</button></div></div></td>    
                    	<td><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="breakSelectedItem()">拆分选择项</button></div></div></td>           
                	</tr>
            	</table>
        	</div>  
    	</div>    
    	<div id="selectedFaultTypeDiv">
    		<table class="list" width="100%" layoutH="125">
            	<thead>
                	<tr>
	                    <th width="5%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
	                    <th width="10%">合并故障代码</th>
	                    <th width="25%">合并故障名称</th>
	                    <th width="10%">机型类别</th>
	                    <th width="10%">故障代码</th>
	                    <th width="25%">故障名称</th>
	                    <th width="5%">操作</th>
	                </tr>
            	</thead>
            	<tbody id="selectFaultReasonTbody"></tbody>
     		</table>
			<div class="panelBar"><div class="pages"><span id="selectFaultReasonAmount">已选记录数：0</span></div></div>       
    	</div>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button"  multLookup="id">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close" >取消</button></div></div></li>
		</ul>
	</div>
</div>