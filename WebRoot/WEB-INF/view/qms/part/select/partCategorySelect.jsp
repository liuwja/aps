<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {
	var firstLoad = "${firstLoad}";
    loadSelectedSupplier(firstLoad);
});
 
function loadSelectedSupplier(firstLoad) {
	var seldata = "${data}";
	var data = $("#"+seldata+"_data").val();
	if($.trim(data) != "") {
		var userRowArr = new Array();
		var arr = data.split(";");
		for(var i=0 ;i<arr.length ;i++) {
			if(arr[i].indexOf(",")==0) {
				arr[i]=arr[i].substring(1,arr[i].length);
		    }
			var arr2 = arr[i].split(",");
			if(arr2.length > 1 && firstLoad == "1") {
				var str = arr2[0]+","+arr2[1]+","+arr2[2]+";";
				var vv = "{id:'" +arr2[0]+"',supplierCode: '"+arr2[1]+"',supplierName:'"+arr2[2]+"',data:'"+str+"'}" ;
				userRowArr.push("<tr  target='id' id='" + arr2[0] +"'>");
				userRowArr.push("<td style='text-align: center;'>");
				userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'" onclick="delSelectedUser(this)" />');
				userRowArr.push("</td>");
				userRowArr.push("<td >");
				userRowArr.push(""+arr2[1]+"");
				userRowArr.push("</td>");
				userRowArr.push("<td >");
				userRowArr.push(""+arr2[2]+"");
				userRowArr.push("</td>");
				userRowArr.push("<td style='text-align: center;' width='10%''>");
				userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)' title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
				userRowArr.push("</td>");
				userRowArr.push("</tr>");
			}
			$("input[name='chkbox']").each(function(){
				var str = arr2[0]+","+arr2[1]+","+arr2[2];
				var checkBoxValue = $(this).val();
				if (checkBoxValue == str) {
					 $(this).attr("checked", true);
				}
			});
		}
		$("#selectSupplierTbody",$.pdialog.getCurrent()).append(userRowArr.join(""));
		$("#selectSupplierAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectSupplierTbody tr",$.pdialog.getCurrent()).length);
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
				var vv = "{id:'" + v[0] +"',supplierCode: '" + v[1] + "',supplierName:'" + v[2] + "',data:'" + str + "'}";
				
				userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'" onclick="delSelectedUser(this)" />');
				userRowArr.push("</td>");
				userRowArr.push("<td>" + v[1] + "</td>");
				userRowArr.push("<td>" + v[2] + "</td>");
				userRowArr.push("<td style='text-align: center;' width='10%''>");
				userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)' title='删除'><img src='<c:url value='resources/img/delete.png'/>' /></a>");
				userRowArr.push("</td>");
				userRowArr.push("</tr>");
				$("#selectSupplierTbody",$.pdialog.getCurrent()).append(userRowArr.join(""));
				$("#selectSupplierAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectSupplierTbody tr",$.pdialog.getCurrent()).length);
			}
		});
	} else {
		$("input[name='chkbox']").each(function(){
			$(this).attr("checked", false);
			var v = this.value.split(",");
			$("#selectSupplierTbody tr[id=" + v[0] + "]", $.pdialog.getCurrent()).remove();
			$("#selectSupplierAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectSupplierTbody tr",$.pdialog.getCurrent()).length);
		});
		var seldata = "${data}";
		$("#"+seldata+"_data").val("");
		$("#"+seldata+"_supplierCode").val("");
		$("#"+seldata+"_supplierName").val("");
	}
}

function delSelectedUser(obj) {
	$(obj).parent().parent().remove();
	var id = $(obj).parent().parent().attr("id");
	$("input[id ='" + id + "']").attr("checked", false);
	$("#selectSupplierAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectSupplierTbody tr",$.pdialog.getCurrent()).length);
	var selectSupplier = $("#selectSupplierTbody tr", $.pdialog.getCurrent());
	if (selectSupplier == null || selectSupplier == undefined || selectSupplier.length <= 0) {
		var seldata = "${data}";
		$("#"+seldata+"_data").val("");
		$("#"+seldata+"_supplierCode").val("");
		$("#"+seldata+"_supplierName").val("");
	}
}

function selectRow(row,keystr,name,meshName) {
	var obj = $("#selectSupplierTbody",$.pdialog.getCurrent()).find("tr[id="+keystr+"]");
    if(obj.length == 0) {
		var cloneRow = $(row).clone(true);
		cloneRow.find("td:eq(0)").remove();
		var str = keystr+","+name+","+meshName+";";
		var userRowArr = new Array();
		userRowArr.push("<tr  target='id' id='" + keystr +"'>");
		userRowArr.push("<td style='text-align: center;'>");
		var vv = "{id:'" +keystr+"',supplierCode: '"+name+"',supplierName:'"+meshName+"',data:'"+str+"'}" ;
		userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'" onclick="delSelectedUser(this)" />');
		userRowArr.push("</td>");
		userRowArr.push(cloneRow.html());
		userRowArr.push("<td style='text-align: center;' width='10%''>");
		userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
		userRowArr.push("</td>");
		
		userRowArr.push("</tr>");
		$("#selectSupplierTbody",$.pdialog.getCurrent()).append(userRowArr.join(""));
		$("#selectSupplierAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectSupplierTbody tr",$.pdialog.getCurrent()).length);
	}
    else {
    	$("input[id ='" + keystr + "']").attr("checked", true);
		$("#selectSupplierTbody tr[id=" + keystr + "]",$.pdialog.getCurrent()).remove();
		$("#selectSupplierAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectSupplierTbody tr",$.pdialog.getCurrent()).length);
    }
}

function removeSelectedItem() {
	var seldata = "${data}";
	$("#"+seldata+"_data").val("");
	$("#"+seldata+"_supplierCode").val("");
	$("#"+seldata+"_supplierName").val("");
	$('#selectSupplierTbody tr').remove();
	$("#selectSupplierAmount",$.pdialog.getCurrent()).html("已选记录数："+$("#selectSupplierTbody tr",$.pdialog.getCurrent()).length);
	$("input[type ='checkbox']").attr("checked", false);
}

function callFunc() {
	alert($("#noticeGroup_noticeMen",$.pdialog.getCurrent()).val());
}
</script>
<div class="pageContent">
<%-- 待选择列表  开始--%>
	<div style="float: left;width: 65%;border: 1px solid #ededed;" id="toSelectCategory">
		<div  class="pageHeader">
		待选列表
		</div>
    	<form onsubmit="return  divSearch(this, 'toSelectCategory')" id="pagerForm" action="quality/testInstance/partCategorySelect.do" method="post">
			<div class="pageHeader">
			    <div class="searchBar">
			        <table class="searchContent">
			 		
			        <tr>
			                <td class="dateRange">
								一级分类:
			                </td>
			                <td >
								<select name="categoryName" required="required">
									<option value=" ">所有</option>
									<c:forEach items="${partCategoryList}" var="o">
										<option value="${o.categoryName}" ${categoryName eq o.categoryName ? 'selected':''}>${o.categoryName}</option>
									</c:forEach>
								</select>
								<input type="hidden" name="pageNum" value="1" />
    							<input type="hidden" name="numPerPage" value="${page.numPerPage}" />
    							<input type="hidden" name="direction" value="" />	 
    							<input type="hidden" name="flag" value="1" />	 
			                </td>   
<%-- 			            <td class="dateRange">
								二级分类:
			                </td>                                      
			                <td>
								<input type="text" name="supplierName" id="supplierName" size="10" value="${supplierName}"/>
			                </td>  --%>
			                <td>
			                	<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>               
			                </td>  
			        </tr>
			        </table>
			    </div>
			</div>
	
	        <table class="table" width="100%" layoutH="150" id="choseSupplier">
	            <thead>
	                <tr>
	                    <th width="10%"><input id="checkBoxAll" type="checkbox" onclick="selectAllRow()" class="checkboxCtrl" group="id" ></th>
	                    <th width="20%">一级分类</th>
	                    <th width="30%">二级分类</th>
	                </tr>
	            </thead>
	            <tbody> 
	                <c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.categoryName}','${o.categoryTwoName}')">
	                        <td style="text-align: center;">
	                            <input id="${o.id}" type="checkbox" name="chkbox" value="${o.idNameNumber}">
	                        </td>
	                        <td>${o.categoryName}</td>
	                        <td>${o.categoryTwoName}</td>
	                    </tr>       
	                </c:forEach>        
	            </tbody>
	        </table>
	        
		   <c:import url="../../../_frag/pager/panelBar.jsp">
		       <%-- 对话框分页时需要传此参数 --%>
		       <c:param name="targetName" value="dialog"/>
		       <c:param name="relDivId" value="toSelectCategory"/>    
	       
	       </c:import>   
    	</form>
</div>
<%-- 待选择列表  结束--%>

<%-- 已选择列表  开始--%>
<div style="float: right;width: 34%;border: 1px solid #ededed;">
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
    <div id="selectedSupplierDiv">
    <table class="list" width="100%" layoutH="125">
            <thead>
                <tr>
                    <th width="50"><input type="checkbox" class="checkboxCtrl" group="id" style="visibility:hidden"></th>
                    <th >一级分类</th>
                    <th >二级分类</th>
                    <th >操作</th>
                </tr>
            </thead>
            <tbody id="selectSupplierTbody"> 
            </tbody>
     </table>
    <div class="panelBar">
        <div class="pages">
            <span id="selectSupplierAmount">
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