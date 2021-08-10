<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">

function selectRow(row, meshFaultName) {
	var obj = $("#selectMeshFaultReasonTbody").find("tr[id=" + meshFaultName + "]");
	if(obj.length == 0) {
		var cloneRow = $(row).clone(true);
		cloneRow.find("td:eq(0)").remove();
		var userRowArr = new Array();
		userRowArr.push("<tr target='id' id='" + meshFaultName +"'>");
		userRowArr.push("<td style='text-align: center;'>");
		var value = "{meshname:'" + meshFaultName + "'}";
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
		$("input[id ='" + meshFaultName + "']").attr("checked", true);
		$("#selectMeshFaultReasonTbody tr[id=" + meshFaultName + "]",$.pdialog.getCurrent()).remove();
		$("#selectMeshFaultReasonAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectMeshFaultReasonTbody tr",$.pdialog.getCurrent()).length);	
   	}
}

function removeSelectedItem() {
	var groupName = "${vo.groupName}";
	$("#"+groupName+"_meshname").val("");
	$("#selectMeshFaultReasonTbody tr").remove();
	$("#selectMeshFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
	$("input[type ='checkbox']").attr("checked", false);
}

function delSelectedUser(obj) {
	$(obj).parent().parent().remove();
	var id = $(obj).parent().parent().attr("id");
	$("input[id ='" + id + "']").attr("checked", false);
	$("#selectMeshFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
	var selectSupplier = $("#selectMeshFaultReasonTbody tr", $.pdialog.getCurrent());
	if (selectSupplier == null || selectSupplier == undefined || selectSupplier.length <= 0) {
		var groupName = "${vo.groupName}";
		$("#"+groupName+"_meshname").val("");
	}
}
</script>
<div class="pageContent">
	<div style="float: left;width: 55%;border: 1px solid #ededed;" id="meshFaultReasonDiv">
		<div class="pageHeader">待选列表</div>
		<form onsubmit="return divSearch(this, 'meshFaultReasonDiv')" id="pagerForm" action="qms/commonSelect/meshFaultReasonSelectResult.do" method="post">
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
	                    <th width="5%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
	                    <th width="50%">合并故障名称</th>
	                </tr>
	            </thead>
	            <tbody id="meshFaultReasonList">
	                <c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.meshFaultName}" onclick="selectRow(this, '${o.meshFaultName}')">
	                        <td style="text-align: center;">
	                            <input type="checkbox" name="chkbox" id="${o.meshFaultName}">
	                        </td>
	                        <td>${o.meshFaultName}</td>
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
		<div id="selectMeshFaultReasonDiv">
    		<table class="list" width="100%" layoutH="145">
            	<thead>
                	<tr>
	                    <th><input type="checkbox" class="checkboxCtrl" group="id" ></th>
	                    <th>合并故障名称</th>
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