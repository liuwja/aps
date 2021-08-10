<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {
	var firstLoad = "${firstLoad}";
    loadSelectedUsers(firstLoad);
});

function loadSelectedUsers(firstLoad) {
	var groupName = "${vo.groupName}";
	if($.trim(groupName) != "") {
		var keys = $("#"+groupName+"_id").val();
		if($.trim(keys) != "") {
			if (firstLoad == "1") {
				var url = "<c:url value='qms/commonSelect/queryVocCategoryByid.do' />";
				$("#selectVocTypeTbody").load(url,{keys:keys},function(){
					$("#selectVocTypeAmount").html("已选记录数："+$("#selectVocTypeTbody tr").length);
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
		$("input[name='chkbox']").each(function() {
			if (!$(this).prop("checked")) {
				$(this).attr("checked", true);
				var v = this.value.split(",");
				var userRowArr = new Array();
				userRowArr.push("<tr  target='id' id='" + v[0] +"'>");
				userRowArr.push("<td style='text-align: center;'>");
			 	var vv = "{id:'" + v[0] +"',name: '" + v[1] + "',code:'" + v[2] + "'}";
			 	userRowArr.push('<input type="checkbox" checked="checked" name="id" value="' + vv + '" onclick="delSelectedUser(this)"/>');
			 	userRowArr.push("</td>");
			 	userRowArr.push("<td>" + v[2] + "</td>");
				userRowArr.push("<td>" + v[1] + "</td>");
				userRowArr.push("<td style='text-align: center;' width='10%''>");
				userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)' title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
				userRowArr.push("</td>");
				userRowArr.push("</tr>");
				$("#selectVocTypeTbody").append(userRowArr.join(""));
				$("#selectVocTypeAmount").html("已选记录数："+$("#selectVocTypeTbody tr").length);
			}
		});
	} else {
		$("input[name='chkbox']").each(function(){
			$(this).attr("checked", false);
			var v = this.value.split(",");
			$("#selectVocTypeTbody tr[id=" + v[0] + "]", $.pdialog.getCurrent()).remove();
			$("#selectVocTypeAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectSupplierTbody tr",$.pdialog.getCurrent()).length);
		});
		var seldata = "${data}";
		$("#"+seldata+"_id").val("");
		$("#"+seldata+"_code").val("");
		$("#"+seldata+"_name").val("");
	}
}

function delSelectedUser(obj) {
	$(obj).parent().parent().remove();
	var id = $(obj).parent().parent().attr("id");
	$("input[id ='" + id + "']").attr("checked", false);
	$("#selectVocTypeAmount").html("已选记录数："+$("#selectVocTypeTbody tr").length);
	var selectFaultType = $("#selectSupplierTbody tr", $.pdialog.getCurrent());
	if (selectFaultType == null || selectFaultType == undefined || selectFaultType.length <= 0) {
		var seldata = "${data}";
		$("#"+seldata+"_id").val("");
		$("#"+seldata+"_code").val("");
		$("#"+seldata+"_name").val("");
	}
}

function selectRow(row,keystr,name,code) {
	var obj = $("#selectVocTypeTbody").find("tr[id="+keystr+"]");
	if(obj.length == 0) {
		var cloneRow = $(row).clone(true);
		cloneRow.find("td:eq(0)").remove();
		var userRowArr = new Array();
	 	userRowArr.push("<tr  target='id' id='" + keystr +"'>");
	 	userRowArr.push("<td style='text-align: center;'>");
	 	var vv = "{id:'" +keystr+"',name: '"+name+"',code:'"+code+"'}" ;
		userRowArr.push('<input type="checkbox" checked="checked" name="id" value="'+vv+'" onclick="delSelectedUser(this)"/>');
		userRowArr.push("</td>");
		userRowArr.push(cloneRow.html());
		userRowArr.push("<td style='text-align: center;' width='10%''>");
		userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
		userRowArr.push("</td>");
		userRowArr.push("</tr>");
		$("#selectVocTypeTbody").append(userRowArr.join(""));
		$("#selectVocTypeAmount").html("已选记录数："+$("#selectVocTypeTbody tr").length);
	} else {
		$("input[id ='" + keystr + "']").attr("checked", true);
		$("#selectVocTypeTbody tr[id=" + keystr + "]",$.pdialog.getCurrent()).remove();
		$("#selectVocTypeAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectSupplierTbody tr",$.pdialog.getCurrent()).length);	
	}
}

function removeSelectedItem() {
	var seldata = "${data}";
	$("#"+seldata+"_id").val("");
	$("#"+seldata+"_code").val("");
	$("#"+seldata+"_name").val("");
	$("#selectVocTypeTbody tr").remove();
	$("#selectVocTypeAmount").html("已选记录数："+$("#selectVocTypeTbody tr").length);
	$("input[type ='checkbox']").attr("checked", false);
}

function callFunc() {
	alert($("#noticeGroup_noticeMen").val());
}
</script>
<div class="pageContent">
	<div style="float: left;width: 55%;border: 1px solid #ededed;" id="selectVocType">
		<div  class="pageHeader">待选列表</div>
		<form onsubmit="return divSearch(this, 'selectVocType')" id="pagerForm" action="qms/commonSelect/vocCategorySelectResult.do" method="post">
			<div class="pageHeader">
		    	<div class="searchBar">
			        <table class="searchContent">			        	
		                <tr>
			                <td class="dateRange">VOC一级分类:</td>
			                <td>
								<select name="paterName">
									<option value="">请选择</option>
									<c:forEach items="${vocPaterList}" var="o">
										<option value="${o.name}" <c:if test="${vo.paterName eq o.name }">selected="selected"</c:if>>${o.name}</option>
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
						<th width="3%"><input id="checkBoxAll" type="checkbox" onclick="selectAllRow()" class="checkboxCtrl" group="id" ></th>
						<th width="30%">VOC一级分类</th>
						<th width="40%">VOC二级分类</th>
					</tr>
				</thead>
	            <tbody> 
	                <c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.number}" onclick="selectRow(this,'${o.number}','${o.name}','${o.paterName}')">
	                        <td style="text-align: center;">
	                            <input id="${o.number}" type="checkbox" name="chkbox" value="${o.number},${o.name},${o.paterName}" />
	                        </td>
	                        <td>${o.paterName}</td>
	                        <td>${o.name}</td>
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
		<div id="selectedFaultTypeDiv">
			<table class="list" width="100%" layoutH="146">
				<thead>
					<tr>
					    <th width="50"><input type="hidden" class="checkboxCtrl" group="i" ></th>
					    <th >VOC一级分类</th>
					    <th >VOC二级分类</th>
					    <th >操作</th>
					</tr>
				</thead>
				<tbody id="selectVocTypeTbody"></tbody>
			</table>
			<div class="panelBar">
				<div class="pages">
					<span id="selectVocTypeAmount">已选记录数：0</span>
				</div>
			</div>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button" multLookup="id"  >确定</button></div></div></li>
			<li>
				<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
			</li>
		</ul>
	</div>
</div>