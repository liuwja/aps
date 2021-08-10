<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {
	loadSelectedItems();
});

function loadSelectedItems() {
	var selectItems = "${vo.meshFaultCode}";
	if($.trim(selectItems) != "") {
		var keys = selectItems.split(",");
		for(var i in keys) {
			$("#meshFaultReasonList", $.pdialog.getCurrent()).find("input[type=checkBox]").each(function(){
				var val = $(this).attr("id");
				if(keys[i] == val) {
					$(this).attr("checked","checked");
				}
			});
		}
		$("#meshFaultReasonList", $.pdialog.getCurrent()).find("input[type=checkBox]:checked").each(function(){ 
			$(this).parent().parent().click();
		});
	}
}

function selectAllRow() {
	if($("#checkBoxAll").prop("checked")) {
		$("input[name='chkbox']").each(function(){
			if (!$(this).prop("checked")) {
				$(this).attr("checked", true);
				var v = this.value.split(",");
				var userRowArr = new Array();
				userRowArr.push("<tr target='id' id='" + v[2] +"'>");
				userRowArr.push("<td style='text-align: center;'>");
				var vv = "{meshname:'" + v[1] + "', meshFaultCode:'"+v[2]+"'}";
				userRowArr.push('<input type="checkbox" checked="checked" name="id" value="'+vv+'" onclick="delSelectedUser(this)" />');
				userRowArr.push("</td>");
				userRowArr.push("<td>" + v[0] + "</td>");
				userRowArr.push("<td>" + v[1] + "</td>");
				userRowArr.push("<td>" + v[2] + "</td>");
				userRowArr.push("<td width='10%' style='text-align: center;'>");
				userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
				userRowArr.push("</td>");
				userRowArr.push("</tr>");
				$("#selectMeshFaultReasonTbody").append(userRowArr.join(""));
				$("#selectMeshFaultReasonAmount").html("已选记录数："+$("#selectMeshFaultReasonTbody tr").length);
			}
		});
	} else {
		$("input[name='chkbox']").each(function(){
			$(this).attr("checked", false);
			var v = this.value.split(",");
			$("#selectMeshFaultReasonTbody tr[id=" + v[2] + "]", $.pdialog.getCurrent()).remove();
			$("#selectMeshFaultReasonAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectMeshFaultReasonTbody tr",$.pdialog.getCurrent()).length);
		});
		var groupName = "${vo.groupName}";
		$("#"+groupName+"_id").val("");
		$("#"+groupName+"_code").val("");
		$("#"+groupName+"_name").val("");
	}
}

function selectRow(row, meshFaultName, meshFaultCode) {
	var obj = $("#selectMeshFaultReasonTbody").find("tr[id=" + meshFaultCode + "]");
	if(obj.length == 0) {
		var cloneRow = $(row).clone(true);
		cloneRow.find("td:eq(0)").remove();
		var userRowArr = new Array();
		userRowArr.push("<tr target='id' id='" + meshFaultCode +"'>");
		userRowArr.push("<td style='text-align: center;'>");
		var value = "{meshname:'" + meshFaultName + "', meshFaultCode:'"+meshFaultCode+"'}";
		userRowArr.push('<input type="checkbox" checked="checked" name="id" value="' + value + '"/>');
		userRowArr.push("</td>");
		userRowArr.push(cloneRow.html());
		userRowArr.push("<td width='10%' style='text-align: center;'>");
		userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)' title='删除'><img src='<c:url value='resources/img/delete.png'/>' /></a>");
		userRowArr.push("</td>");
		userRowArr.push("</tr>");
		$("#selectMeshFaultReasonTbody").append(userRowArr.join(""));
		$("#selectMeshFaultReasonAmount").html("已选记录数："+$("#selectMeshFaultReasonTbody tr").length);
	} else {
		
	}
}

function removeSelectedItem() {
	$("#selectMeshFaultReasonTbody tr").remove();
    $("#selectMeshFaultReasonAmount").html("已选记录数："+$("#selectMeshFaultReasonTbody tr").length);
}

function delSelectedUser(obj) {
	$(obj).parent().parent().remove();
	$("#selectMeshFaultReasonAmount").html("已选记录数："+$("#selectMeshFaultReasonTbody tr").length);
}
</script>
<div class="pageContent">
	<div style="float: left;width: 55%;border: 1px solid #ededed;" id="toSelectMeshFaultReasonList">
		<div class="pageHeader">待选列表</div>
		<form onsubmit="return divSearch(this, 'toSelectMeshFaultReasonList')" id="pagerForm" action="qms/commonSelect/meshFaultReasonSelectResult.do" method="post">
			<div class="pageHeader">
				<div class="searchBar">
					<table class="searchContent">
						<tr>
							<td class="dateRange">合并故障名称：</td>
							<td>
								<input type="text" name="meshFaultName" id="meshFaultName" size="10" value="${vo.meshFaultName}"/>
								<input type="hidden" name="pageNum" value="1" />
    							<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
    							<input type="hidden" name="direction" value="" />
							</td>
							<td class="dateRange">机型类别： </td>
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
			<table class="table" width="100%" layoutH="120">
				<thead>
                	<tr>
	                    <th width="5%"><input type="checkbox" id = "checkBoxAll" class="checkboxCtrl" group="id" onclick = "selectAllRow()"></th>
	                    <th width="25%">机型类别</th>
	                    <th width="50%">合并故障名称</th>
	                    <th width="20%">合并故障代码</th>
	                </tr>
	            </thead>
	            <tbody id="meshFaultReasonList">
	                <c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.meshFaultCode}" onclick="selectRow(this,'${o.meshFaultName}','${o.meshFaultCode}')">
	                        <td style="text-align: center;">
	                            <input type="checkbox" name="chkbox" id="${o.meshFaultCode}" value="${o.productType},${o.meshFaultName},${o.meshFaultCode}">
	                        </td>
	                        <td>${o.productType}</td>
	                        <td>${o.meshFaultName}</td>
	                        <td>${o.meshFaultCode}</td>
	                    </tr>
	                </c:forEach>
	            </tbody>
			</table>
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
		<div id="selectedMeshFaultTypeDiv">
    		<table class="list" width="100%" layoutH="145">
            	<thead>
                	<tr>
	                    <th width="5%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
	                    <th width="25%">机型类别</th>
	                    <th width="40%">合并故障名称</th>
	                    <th width="20%">合并故障代码</th>
	                    <th width="10%">操作</th>
                	</tr>
				</thead>
				<tbody id="selectMeshFaultReasonTbody"></tbody>
     		</table>
    		<div class="panelBar">
        		<div class="pages">
            		<span id="selectMeshFaultReasonAmount">已选记录数：0</span>
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