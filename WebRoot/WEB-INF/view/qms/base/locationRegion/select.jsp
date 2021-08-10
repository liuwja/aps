<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">

function selectRegionRow(row, keystr, mergeRegion, locationCode, region) {
	var obj = $("#selectRegionbody").find("tr[id="+region+"]");
	if (obj.length == 0) {
		var cloneRow = $(row).clone(true);
		cloneRow.find("td:eq(0)").remove();
		var userRowArr = new Array();
		userRowArr.push("<tr  target='id' id='" + region +"'>");
		userRowArr.push("<td style='text-align: center;'>");
		var vv = "{id:'" + keystr + "',mergeRegion: '" + mergeRegion + "',locationCode:'" + locationCode + "',region:'" + region + "'}" ;
		userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'"/>');
		userRowArr.push("</td>");
		userRowArr.push(cloneRow.html());
		userRowArr.push("<td style='text-align: center;' width='10%''>");
		userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedRegion(this)' title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
		userRowArr.push("</td>");
		userRowArr.push("</tr>");
		$("#selectRegionbody").append(userRowArr.join(""));
		$("#selectRegionAmount").html("已选记录数："+$("#selectRegionbody tr").length);
	} else {
// 		alertMsg.info("此服务中心已选择");
	}
}

function delSelectedRegion(obj) {
	$(obj).parent().parent().remove();
	$("#selectRegionAmount").html("已选记录数："+$("#selectRegionbody tr").length);
}

function removeSelectedRegionItem() {
	$('#selectRegionbody tr').remove();
    $("#selectRegionAmount").html("已选记录数："+$("#selectRegionbody tr").length);
}

function mergeSelectedRegionItem() {
	var keyArry = [];
	$("#selectRegionbody").find("tr").each(function(i){
		keyArry.push($(this).attr("id"));
	});
	if (keyArry == null || keyArry == '') {
		alertMsg.info("没有选择服务中心");
		return;
	}
	var keys = keyArry.join(",");
	var url = "base/locationRegion/mesh.do?keys=" + keys;
	var opt = {width:500, height:200, mask:true};
	$.pdialog.open(url, "dlg_mesh", "合并", opt);
}

function breakSelectedRegionItem() {
	var keyArry = [];
	$("#selectRegionbody").find("tr").each(function(i){
		keyArry.push($(this).attr("id"));
	 });
	var keys = keyArry.join(",");
	$.ajax({
		type:"post",
		data:{"keys":keys},
		url:"base/locationRegion/breakMesh.do",
		success:function(data){
			$.pdialog.closeCurrent();
			if(data=="success"){
				alertMsg.correct("拆分成功！");
			}else{
				alertMsg.error("拆分失败！");
			}
			navTab.reload("base/locationRegion/locationRegionList.do");
		}
	});
}
</script>
<div class="pageContent">
	<div style="float: left;width: 55%;border: 1px solid #ededed;" id="toSelectRegionList">
		<div class="pageHeader">待选列表</div>
		<form onsubmit="return  divSearch(this, 'toSelectRegionList')" id="pagerForm" action="qms/commonSelect/regionSelect.do" method="post">
			<div class="pageHeader">
				<div class="searchBar">
					<table class="searchContent">
						<tr>
							<td class="dateRange">合并中心名称</td>
							<td>
								<input type="text" name="mergeRegion" value="${vo.mergeRegion}" />
								<input type="hidden" name="pageNum" value="1" />
    							<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
    							<input type="hidden" name="direction" value="" />
							</td>
							<td class="dateRange">服务中心</td>
							<td><input type="text" name="region" value="${vo.region}" /></td>
						</tr>
						<tr>
							<td class="dateRange">所在省份</td>
							<td><input type="text" name="province" value="${vo.province}" /></td>
							<td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div></td>
						</tr>
					</table>
				</div>
			</div>
			<table class="table" width="100%" layoutH="170">
				<thead>
					<tr>
						<th width="5%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
						<th width="30%">合并服务中心名称</th>
	                    <th width="10%">所在省份</th>
	                    <th width="10%">仓库编码</th>
	                    <th width="30%">服务中心名称</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="o">
						<tr target="id" rel="${o.region}" onclick="selectRegionRow(this, '${o.id}', '${o.mergeRegion}', '${o.locationCode}', '${o.region}')">
							<td style="text-align: center;"><input type="checkbox" name="chkbox"></td>
	                        <td>${o.mergeRegion}</td>
	                        <td>${o.province}</td>
	                        <td>${o.locationCode}</td>
	                        <td>${o.region}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:import url="../../../_frag/pager/panelBar.jsp">
		       <%-- 对话框分页时需要传此参数 --%>
		       <c:param name="targetName" value="dialog"/>
		       <c:param name="relDivId" value="toSelectRegionList"/>    
	       </c:import>
		</form>
	</div>
	
	<div style="float: right;width: 44%;border: 1px solid #ededed;">
		<div class="pageHeader">已选择列表</div>
		<div class="pageHeader">
			<div class="searchBar">
				<table class="searchContent">         
                	<tr>
                    	<td><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="removeSelectedRegionItem()">全部删除</button></div></div></td>  
                    	<td><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="mergeSelectedRegionItem()">合并选择项</button></div></div></td>    
                    	<td><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="breakSelectedRegionItem()">拆分选择项</button></div></div></td>           
                	</tr>
            	</table>
			</div>
		</div>
		<div id="selectedRegionDiv">
			<table class="list" width="100%" layoutH="125">
				<thead>
                	<tr>
	                    <th width="5%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
	                   	<th width="30%">合并服务中心名称</th>
	                    <th width="10%">所在省份</th>
	                    <th width="10%">仓库编码</th>
	                    <th width="30%">服务中心名称</th>
	                    <th width="15%">操作</th>
                	</tr>
            	</thead>
            	<tbody id="selectRegionbody"></tbody>
			</table>
			<div class="panelBar">
        		<div class="pages">
            		<span id="selectRegionAmount">已选记录数：0</span>
        		</div>
    		</div>
		</div>
	</div>
	
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button" multLookup="id" >确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close" >取消</button></div></div></li>
		</ul>
	</div>
</div>