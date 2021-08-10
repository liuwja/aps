<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
jQuery(document).ready(function() {
    loadSelectPartType();
});

function loadSelectPartType() {
	var groupName = "${partTypeId}";
	if($.trim(groupName) != "") {
		var keys = groupName.split(",");
		for(var i in keys) {
			$("#partTypeContent", $.pdialog.getCurrent()).find("input[type=checkBox]").each(function(){
				var val = $(this).attr("part");
				if(keys[i] == val) {
					$(this).attr("checked","checked");
				}
			});
		}
		$("#partTypeContent", $.pdialog.getCurrent()).find("input[type=checkBox]:checked").each(function(){ 
			$(this).parent().parent().click();
		});
	} 
}
 
 function delSelectedUser(obj)
 {
	 $(obj).parent().parent().remove();
	 $("#selectPartTypeAmount").html("已选记录数："+$("#selectPartTypeTbody tr").length);
 }
function selectRow(row,keystr,productType,productFactory,partType)
{
	var obj = $("#selectPartTypeTbody").find("#"+keystr);
    
    if(obj.length == 0)
    {
	var cloneRow = $(row).clone(true);
	//alert(cloneRow.html());
	cloneRow.find("td:eq(0)").remove();
	
	 var userRowArr = new Array();
	 userRowArr.push("<tr  target='id' id='" + keystr +"'>");
	 userRowArr.push("<td style='text-align: center;'>");
	 var vv = "{id:'"+keystr+"',p_productType:'"+productType+"',p_productFactory:'"+productFactory+"',partType:'"+partType+"'}";
	 
     userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'"/>');
	 userRowArr.push("</td>");
	 userRowArr.push(cloneRow.html());
	 userRowArr.push("<td width='10%' style='text-align: center;'>");
	 userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
	 userRowArr.push("</td>");
	 
	 userRowArr.push("</tr>");
	 //alert(userRowArr.join(""));
	 $("#selectPartTypeTbody").append(userRowArr.join(""));
     $("#selectPartTypeAmount").html("已选记录数："+$("#selectPartTypeTbody tr").length);
    }
    else
   	{
//     	alertMsg.info("此故障小类已选择");	
   	}
}

    function removeSelectedItem()
    {
        $('#selectPartTypeTbody tr').remove();
         
        $("#selectPartTypeAmount").html("已选记录数："+$("#selectPartTypeTbody tr").length);
    }
//-->
</script>
<div class="pageContent">
<%-- 待选择列表  开始--%>
	<div style="float: left;width: 55%;border: 1px solid #ededed;" id="toSelectPartTypeList">
		<div  class="pageHeader">
		待选列表
		</div>
    	<form onsubmit="return  divSearch(this, 'toSelectPartTypeList')" id="pagerForm" action="qms/commonSelect/partTypeSelectResult.do" method="post">
			<div class="pageHeader">
			    <div class="searchBar">
			        <table class="searchContent">
			 		
			        <tr>
			                <td>
								机型类别： 
							</td>
							<td>
								<select name="productType" >
									<option value="">请选择</option>
									<c:forEach items="${productTypes}" var="o">
									<option value="${o.machineType}" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
									</c:forEach>
								</select>
    							<input type="hidden" name="direction" value="" />	 
			                </td> 
			                 <td class="dateRange">
								产品系列:
			                </td>                                      
			                <td>
								<input type="text" name=productFamily id="productFamily" size="10" value="${vo.productFamily}"/>
			                </td> 
			             </tr>
			             <tr>
			                <td class="dateRange">
								型号:
			                </td>                                      
			                <td>
								<input type="text" name="partType" id="partType" size="10" value="${vo.partType}"/>
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
	                    <th width="5%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
	                    <th width="30%">机型类别</th>
	                    <th width="30%">产品系列</th>
	                    <th width="30%">产品型号</th>
	                </tr>
	            </thead>
	            <tbody id="partTypeContent"> 
	                <c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.partType}" onclick="selectRow(this,'${o.partType}','${o.productType}','${o.productFamily}','${o.partType}')">
	                        <td style="text-align: center;">
	                            <input type="checkbox" name="chkbox" part="${o.partType}">
	                        </td>
	                        <td>${o.productType}</td>
	                        <td>${o.productFamily}</td>
	                        <td>${o.partType}</td>
	                    </tr>       
	                </c:forEach>        
	            </tbody>
	        </table>
	        
		  <div class="panelBar">
		    <div class="pages">
		        <span>共${totalCount}条 </span>
		    </div>
		</div> 
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
                    <th width="5%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
                    <th width="30%">机型类别</th>
	                <th width="30%">产品系列</th>
	                <th width="30%">产品型号</th>
	                <th width="5%"></th>
                </tr>
            </thead>
            <tbody id="selectPartTypeTbody"> 
            </tbody>
     </table>
    <div class="panelBar">
        <div class="pages">
            <span id="selectPartTypeAmount">
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