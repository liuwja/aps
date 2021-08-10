<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {
	var firstLoad = "${firstLoad}";
	loadSelectedUsers(firstLoad);
});

function loadSelectedUsers(firstLoad) {
	var groupName = "${vo.groupName}";
	if($.trim(groupName) != "") {
		var keys = $("#" + groupName + "_id").val();
		if($.trim(keys) != "") {
			if (firstLoad == "1") {
				var url = "<c:url value='qms/commonSelect/queryFaultReasonByid.do' />";
				$("#selectFaultReasonTbody").load(url,{keys:keys},function() {
					$("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
				});
			}
			
			$("input[name='chkbox']").each(function(){
				var str = keys.split(",");
				for (var key in str) {
					var checkBoxValue = $(this).attr("id");
					if (checkBoxValue == str[key]) {
						 $(this).attr("checked", true);
					}
				}
			});
		}
	}
}

function selectAllRow() {
	if($("#checkBoxAll").prop("checked")) {
		$("input[name='chkbox']").each(function(){
			if (!$(this).prop("checked")) {
				$(this).attr("checked", true);
				var v = this.value.split(",");
				var userRowArr = new Array();
				userRowArr.push("<tr target='id' id='" + v[0] +"'>");
				userRowArr.push("<td style='text-align: center;'>");
				var vv = "{id:'" + v[0] + "',name:'" + v[1] + "',meshname:'" + v[2] + "',code:'" + v[3] + "',meshFaultCode:'" + v[4] + "'}";
				userRowArr.push('<input type="checkbox" checked="checked" name="id" value="'+vv+'" onclick="delSelectedUser(this)" />');
				userRowArr.push("</td>");
				userRowArr.push("<td>" + v[2] + "</td>");
				userRowArr.push("<td>" + v[5] + "</td>");
				userRowArr.push("<td>" + v[3] + "</td>");
				userRowArr.push("<td>" + v[1] + "</td>");
				userRowArr.push("<td width='10%' style='text-align: center;'>");
				userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
				userRowArr.push("</td>");
				userRowArr.push("</tr>");
				$("#selectFaultReasonTbody").append(userRowArr.join(""));
				$("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
			}
		});
	} else {
		$("input[name='chkbox']").each(function(){
			$(this).attr("checked", false);
			var v = this.value.split(",");
			$("#selectFaultReasonTbody tr[id=" + v[0] + "]", $.pdialog.getCurrent()).remove();
			$("#selectFaultReasonAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectFaultReasonTbody tr",$.pdialog.getCurrent()).length);
		});
		var groupName = "${vo.groupName}";
		$("#"+groupName+"_id").val("");
		$("#"+groupName+"_code").val("");
		$("#"+groupName+"_name").val("");
	}
}

function delSelectedUser(obj) {
	$(obj).parent().parent().remove();
	var id = $(obj).parent().parent().attr("id");
	$("input[id ='" + id + "']").attr("checked", false);
	$("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
	var selectSupplier = $("#selectFaultReasonTbody tr", $.pdialog.getCurrent());
	if (selectSupplier == null || selectSupplier == undefined || selectSupplier.length <= 0) {
		var groupName = "${vo.groupName}";
		$("#"+groupName+"_id").val("");
		$("#"+groupName+"_code").val("");
		$("#"+groupName+"_name").val("");
	}
}

function selectRow(row,keystr,name,meshName,code,meshFaultCode) {
	var obj = $("#selectFaultReasonTbody").find("tr[id=" + keystr + "]");
    if(obj.length == 0) {
		var cloneRow = $(row).clone(true);
		cloneRow.find("td:eq(0)").remove();
		var userRowArr = new Array();
		userRowArr.push("<tr  target='id' id='" + keystr +"'>");
		userRowArr.push("<td style='text-align: center;'>");
		var vv = "{id:'" + keystr + "',name:'" + name + "',meshname:'" + meshName + "',code:'" + code + "',meshFaultCode:'" + meshFaultCode + "'}";
		userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'" onclick="delSelectedUser(this)" />');
		userRowArr.push("</td>");
		userRowArr.push(cloneRow.html());
		userRowArr.push("<td width='10%' style='text-align: center;'>");
		userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
		userRowArr.push("</td>");
		userRowArr.push("</tr>");
		$("#selectFaultReasonTbody").append(userRowArr.join(""));
		$("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
	} else {
		var status = $("input[id ='" + keystr + "']").attr("checked");
		if (status == "checked") {
			$("input[id ='" + keystr + "']").attr("checked", true);
		}
		$("#selectFaultReasonTbody tr[id=" + keystr + "]",$.pdialog.getCurrent()).remove();
		$("#selectFaultReasonAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectFaultReasonTbody tr",$.pdialog.getCurrent()).length);	
   	}
}

function removeSelectedItem() {
	var groupName = "${vo.groupName}";
	$("#"+groupName+"_id").val("");
	$("#"+groupName+"_code").val("");
	$("#"+groupName+"_name").val("");
	$("#selectFaultReasonTbody tr").remove();
	$("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
	$("input[type ='checkbox']").attr("checked", false);
}

function callFunc() {
	alertMsg.info($("#noticeGroup_noticeMen").val());
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
							<td>
								<input type="text" name="name" id="name" size="10" value="${vo.name}"/>
							</td>
			                <td class="dateRange">故障代码:</td>
			                <td>
								<input type="text" name="code" id="code" size="10" value="${vo.code}"/>
			                </td> 
						</tr>
		                <tr>
		                	<td class="dateRange">合并故障名称:</td>
			                <td>
								<input type="text" name="meshFaultName" id="meshFaultName" size="10" value="${vo.meshFaultName}"/>
								<input type="hidden" name="pageNum" value="1" />
    							<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
    							<input type="hidden" name="direction" value="" />	 
			                </td>   
			                <td>机型类别: </td>
							<td>
								<select name="productType" >
									<option value="">请选择</option>
									<c:forEach items="${productTypes}" var="o">
										<option value="${o.machineType}" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
									</c:forEach>
								</select>
							</td>	
			                <td>
			                	<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>               
			                </td>  
						</tr>
					</table>
				</div>
			</div>
	        <table class="table" width="100%" layoutH="170">
	            <thead>
	                <tr>
	                    <th width="5%"><input id="checkBoxAll" type="checkbox" onclick="selectAllRow()" class="checkboxCtrl" group="id" ></th>
	                    <th width="30%">合并故障名称</th>
	                    <th width="20%">机型类别</th>
	                    <th width="20%">故障代码</th>
	                    <th width="30%">故障名称</th>
	                </tr>
	            </thead>
	            <tbody> 
                	<c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.name}','${o.meshFaultName}','${o.code}','${o.meshFaultCode}', '${o.productType}')">
	                        <td style="text-align: center;">
	                            <input id="${o.id}" type="checkbox" name="chkbox" value="${o.id},${o.name},${o.meshFaultName},${o.code},${o.meshFaultCode},${o.productType}" />
	                        </td>
	                        <td>${o.meshFaultName}</td>
	                        <td>${o.productType}</td>
	                        <td>${o.code}</td>
	                        <td>${o.name}</td>
	                    </tr>       
	                </c:forEach>        
	            </tbody>
	        </table>
			<c:import url="../../_frag/pager/panelBar.jsp">
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
            		<tr><td></td></tr>  
                	<tr>
                    	<td>
                    		<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="removeSelectedItem()">全部删除</button></div></div>
                    	</td>           
               		</tr>
            	</table>
			</div>  
    	</div>    
    	<div id="selectedFaultTypeDiv">
    		<table class="list" width="100%" layoutH="145">
            	<thead>
                	<tr>
	                    <th width="5%"><input type="hidden" class="checkboxCtrl" group="id" ></th>
	                    <th width="30%">合并故障名称</th>
	                    <th width="15%">机型类别</th>
	                    <th width="15%">故障代码</th>
	                    <th width="30%">故障名称</th>
	                    <th width="15%">操作</th>
                	</tr>
            	</thead>
            	<tbody id="selectFaultReasonTbody"></tbody>
     		</table>
	    	<div class="panelBar">
	        	<div class="pages">
	            	<span id="selectFaultReasonAmount">已选记录数：0</span>
	        	</div>
	    	</div>
    	</div>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="buttonActive"><div class="buttonContent"><button type="button"  multLookup="id" >确定</button></div></div>
			</li>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close" >取消</button></div></div>
			</li>
		</ul>
	</div>
</div>