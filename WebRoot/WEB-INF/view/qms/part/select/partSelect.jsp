<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {
	var firstLoad = "${firstLoad}";
    loadSelectedUsers(firstLoad);
});
 
function loadSelectedUsers(firstLoad) {
	var seldata = "${data}";
	var data = $("#"+seldata+"_data").val();
	if($.trim(data) != "") {
		var userRowArr = new Array();
		var arr = data.split(";");
		for(var i=0 ;i<arr.length ;i++) {
			if(arr[i].indexOf(",")==0){
				arr[i]=arr[i].substring(1,arr[i].length);
		    }
			var arr2 = arr[i].split(",");
			if (firstLoad == "1") {
				if(arr2.length >1) {
					var str = arr2[0]+","+arr2[1]+","+arr2[2]+";";
					var vv = "{id:'" +arr2[0]+"',partCode: '"+arr2[1]+"',partName:'"+arr2[2]+"',data:'"+str+"'}" ;
					userRowArr.push("<tr  target='id' id='" + arr2[0] +"'>");
					userRowArr.push("<td style='text-align: center;'>");
					userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'"/>');
					userRowArr.push("</td>");
					userRowArr.push("<td >");
					userRowArr.push(""+arr2[1]+"");
					userRowArr.push("</td>");
					userRowArr.push("<td >");
					userRowArr.push(""+arr2[2]+"");
					userRowArr.push("</td>");
					userRowArr.push("<td style='text-align: center;' width='10%''>");
					userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
					userRowArr.push("</td>");
					userRowArr.push("</tr>");
				}
			}
			$("input[name='chkbox']").each(function(){
				var str = arr2[0]+","+arr2[1]+","+arr2[2];
				var checkBoxValue = $(this).val();
				if (checkBoxValue == str) {
					 $(this).attr("checked", true);
				}
			});
		}
		$("#selectPartTbody",$.pdialog.getCurrent()).append(userRowArr.join(""));
		$("#selectPartAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectPartTbody tr",$.pdialog.getCurrent()).length);
	}
}

function selectAllRow() {
	if($("#checkBoxAll").prop("checked")) {
		$("input[name='chkbox']").each(function(){
			if (!$(this).prop("checked")) {
				$(this).attr("checked", true);
				var v = this.value.split(",");
				var str = this.value + ";";
				var userRowArr = new Array();
				userRowArr.push("<tr target='id' id='" + v[0] +"'>");
				userRowArr.push("<td style='text-align: center;'>");
				var vv = "{id:'" + v[0] +"',partCode: '" + v[1] + "',partName:'" + v[2] + "',data:'" + str + "'}";
				userRowArr.push('<input type="checkbox" checked="checked" name="id" value="'+vv+'" onclick="delSelectedUser(this)" />');
				userRowArr.push("</td>");
				userRowArr.push("<td>" + v[1] + "</td>");
				userRowArr.push("<td>" + v[2] + "</td>");
				userRowArr.push("<td style='text-align: center;' width='10%''>");
				userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)' title='删除'><img src='<c:url value='resources/img/delete.png'/>' /></a>");
				userRowArr.push("</td>");
				userRowArr.push("</tr>");
				$("#selectPartTbody",$.pdialog.getCurrent()).append(userRowArr.join(""));
				$("#selectPartAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectPartTbody tr",$.pdialog.getCurrent()).length);
			}
		});
	} else {
		$("input[name='chkbox']").each(function(){
			$(this).attr("checked", false);
			var v = this.value.split(",");
			$("#selectPartTbody tr[id=" + v[0] + "]",$.pdialog.getCurrent()).remove();
			$("#selectPartAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectPartTbody tr",$.pdialog.getCurrent()).length);
		});
	}
}
 
function delSelectedUser(obj) {
	$(obj).parent().parent().remove();
	var id = $(obj).parent().parent().attr("id");
	$("input[id ='" + id + "']").attr("checked", false);
	$("#selectPartAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectPartTbody tr",$.pdialog.getCurrent()).length);
	var selectSupplier = $("#selectPartTbody tr", $.pdialog.getCurrent());
	if (selectSupplier == null || selectSupplier == undefined || selectSupplier.length <= 0) {
		var seldata = "${data}";
		$("#"+seldata+"_data").val("");
		$("#"+seldata+"_partName").val("");
	}
}

function selectRow(row,keystr,name,meshName) {
	var obj = $("#selectPartTbody",$.pdialog.getCurrent()).find("tr[id="+keystr+"]");
    if(obj.length == 0) {
		var cloneRow = $(row).clone(true);
		cloneRow.find("td:eq(0)").remove();
		var str = keystr+","+name+","+meshName+";";
		var userRowArr = new Array();
		userRowArr.push("<tr  target='id' id='" + keystr +"'>");
		userRowArr.push("<td style='text-align: center;'>");
		var vv = "{id:'" +keystr+"',partCode: '"+name+"',partName:'"+meshName+"',data:'"+str+"'}";
		
		userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'" onclick="delSelectedUser(this)" />');
		userRowArr.push("</td>");
		userRowArr.push(cloneRow.html());
		userRowArr.push("<td style='text-align: center;' width='10%''>");
		userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
		userRowArr.push("</td>");
		 
		userRowArr.push("</tr>");
		$("#selectPartTbody",$.pdialog.getCurrent()).append(userRowArr.join(""));
		$("#selectPartAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectPartTbody tr",$.pdialog.getCurrent()).length);
	} else {
		$("input[id ='" + keystr + "']").attr("checked", true);
		$("#selectPartTbody tr[id=" + keystr + "]",$.pdialog.getCurrent()).remove();
		$("#selectPartAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectPartTbody tr",$.pdialog.getCurrent()).length);
   	}
}

function removeSelectedItem() {
	var seldata = "${data}";
	$("#"+seldata+"_data").val("");
	$("#"+seldata+"_partName").val("");
	$('#selectPartTbody tr').remove();
	$("#selectPartAmount").html("已选记录数："+$("#selectPartTbody tr").length);
	$("input[type ='checkbox']").attr("checked", false);
}
    
function callFunc() {
	alert($("#noticeGroup_noticeMen").val());
}
</script>
<div class="pageContent">
<%-- 待选择列表  开始--%>
	<div style="float: left;width: 55%;border: 1px solid #ededed;" id="toSelectPartList">
		<div  class="pageHeader">
		待选列表
		</div>
    	<form onsubmit="return  divSearch(this, 'toSelectPartList')" id="pagerForm" action="quality/testInstance/partSelect.do" method="post">
			<div class="pageHeader">
			    <div class="searchBar">
			        <table class="searchContent">
			 		
			        <tr>
			                <td class="dateRange">
								物料号:  		
                             
			                </td>
			                <td >
								<input type="text" name="partNumber" id="partNumber" size="10" value="${partNumber}"/>
								<input type="hidden" name="pageNum" value="1" />
    							<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
    							<input type="hidden" name="direction" value="" />
    							<input type="hidden" name="flag" value="1" />		 
			                </td>   
			                <td class="dateRange">
								物料名称:
			                </td>                                      
			                <td>
								<input type="text" name="partName" id="partName" size="10" value="${partName}"/>
			                </td> 
			        </tr>
			        <tr>
			        	<td class="dateRange">是否关键件</td>
			        	<td>
			        		<select id="isConsumed" name="isConsumed" style="width : 78px">
			        			<option value="">全选</option>
								<option value="是" ${isConsumed eq '是' ? 'selected':''}>是</option>
								<option value="否" ${isConsumed eq '否' ? 'selected':''}>否</option>
							</select>
			        	</td>
			        	<td>
			               	<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>               
			               </td>
			        </tr>
			        </table>
			    </div>
			</div>
	
	        <table id="partSelectTable" class="table" width="100%" layoutH="170">
	            <thead>
	                <tr>
	                    <th width="10%"><input id="checkBoxAll" type="checkbox" onclick="selectAllRow()" class="checkboxCtrl" group="id" ></th>
	                    <th width="20%">物料编号</th>
	                    <th width="30%">物料名称</th>
	                </tr>
	            </thead>
	            <tbody> 
	                <c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.partNumber}','${o.partName}')">
	                        <td style="text-align: center;">
	                            <input id="${o.id}" type="checkbox" name="chkbox" value="${o.idNameNumber}">
	                        </td>
	                        <td>${o.partNumber}</td>
	                        <td>${o.partName}</td>
	                    </tr>       
	                </c:forEach>        
	            </tbody>
	        </table>
	        
		   <c:import url="../../../_frag/pager/panelBar.jsp">
		       <%-- 对话框分页时需要传此参数 --%>
		       <c:param name="targetName" value="dialog"/>
		       <c:param name="relDivId" value="toSelectPartList"/>    
	       
	       </c:import>   
    	</form>
</div>
<%-- 待选择列表  结束--%>

<%-- 已选择列表  开始--%>
<div style="float: right;width: 44%;border: 1px solid #ededed;">
    <div class="pageHeader">
    已选择列表
    </div>
    <div class="pageHeader">    
        <div class="searchBar">
            <table class="searchContent">         
                <tr>
                    <td>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="removeSelectedItem()">全部删除</button></div></div>
                    </td>  
                           
                </tr>
            </table>
        </div>  
    </div>    
    <div id="selectedFaultTypeDiv">
    <table class="list" width="100%" layoutH="125">
            <thead>
                <tr>
                    <th width="50"><input type="checkbox" class="checkboxCtrl" group="id" style="visibility:hidden"></th>
                    <th >物料号</th>
                    <th >物料名称</th>
                    <th >操作</th>
                </tr>
            </thead>
            <tbody id="selectPartTbody"> 
            </tbody>
     </table>
    <div class="panelBar">
        <div class="pages">
            <span id="selectPartAmount">
                           已选记录数：0
            </span>
        </div>
    </div>       
    </div>
</div>
<%-- 已选择列表  结束--%>      
    	<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button"  multLookup="id" >确定</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close" >取消</button></div></div>
				</li>
			</ul>
		</div>

    </div>