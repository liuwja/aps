<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">

//<!--

jQuery(document).ready(function() {
    loadSelectedUsers();
});
 function loadSelectedUsers()
 {
	 var faultReasonIds = "${faultReasonIds}";
	 if($.trim(faultReasonIds) != "")
	 {
         var url = "<c:url value='qms/commonSelect/moreMeshFaultSel.do' />";
         $("#selectFaultReasonTbody").load(url,{keys:faultReasonIds},function(){
        	 $("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
         });
	 }
 }
 function delSelectedUser(obj)
 {
	 $(obj).parent().parent().remove();
	 $("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
 }
function selectRow(row,id,meshFaultName)
{
	var obj = $("#selectFaultReasonTbody").find("tr[id="+id+"]");
       
    if(obj.length == 0)
    {
	var cloneRow = $(row).clone(true);
	//alert(cloneRow.html());
	cloneRow.find("td:eq(0)").remove();
	
	 var userRowArr = new Array();
	 userRowArr.push("<tr  target='id' id='" + id +"'>");
	 userRowArr.push("<td style='text-align: center;'>");
	 var vv = "{meshFaultName:'" +meshFaultName+"',id:'"+id+"'}" ;
	 
     userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'"/>');
	 userRowArr.push("</td>");
	 userRowArr.push(cloneRow.html());
	 userRowArr.push("<td width='10%' style='text-align: center;'>");
	 userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
	 userRowArr.push("</td>");
	 
	 userRowArr.push("</tr>");
	 //alert(userRowArr.join(""));
	 $("#selectFaultReasonTbody").append(userRowArr.join(""));
     $("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
    }
    else
   	{
    	alertMsg.info("此故障小类已选择");	
   	}
}

    function removeSelectedItem()
    {
        $('#selectFaultReasonTbody tr').remove();
         
        $("#selectFaultReasonAmount").html("已选记录数："+$("#selectFaultReasonTbody tr").length);
    }
    function callFunc()
    {
    	alert($("#noticeGroup_noticeMen").val());
    }
//-->
</script>
<div class="pageContent">
<%-- 待选择列表  开始--%>
	<div style="float: left;width: 55%;border: 1px solid #ededed;" id="toSelectFaultReasonList">
		<div  class="pageHeader">
		待选列表
		</div>
    	<form onsubmit="return  divSearch(this, 'toSelectFaultReasonList')" id="pagerForm" action="qms/commonSelect/moreMeshFaultSelectResult.do" method="post">
			<div class="pageHeader">
			    <div class="searchBar">
			        <table class="searchContent">
			 		
			        <tr>
			                <td class="dateRange">合并故障名称:</td>
							<td><input type="text" name="meshFaultName"
								id="meshFaultName" size="10" value="${vo.meshFaultName}" /> <input
								type="hidden" name="pageNum" value="1" /> <input type="hidden"
								name="numPerPage" value="${page.numPerPage}" /> <input
								type="hidden" name="direction" value="" /></td>
							<td>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">查找</button>
									</div>
								</div>
							</td>
			        </tr>
			        </table>
			    </div>
			</div>
	
	        <table class="table" width="100%" layoutH="150">
	            <thead>
	                <tr>
	                   <th></th>
					   <th>合并故障名称</th>
	                </tr>
	            </thead>
	            <tbody> 
	                <c:forEach items="${list}" var="o" varStatus="status">
						 <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.meshFaultName}')">
	                        <td style="text-align: center;">
	                            <input type="checkbox" name="chkbox">
	                        </td>
	                        <td>${o.meshFaultName}</td>
	                    </tr>       
					</c:forEach>
	            </tbody>
	        </table>
	        
		   <c:import url="../../_frag/pager/panelBar.jsp">
		       <%-- 对话框分页时需要传此参数 --%>
		       <c:param name="targetName" value="dialog"/>
		       <c:param name="relDivId" value="toSelectFaultReasonList"/>    
	       
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
                    <th width="50"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
                    <th >合并故障名称</th>
                    <th >操作</th>
                </tr>
            </thead>
            <tbody id="selectFaultReasonTbody"> 
            </tbody>
     </table>
    <div class="panelBar">
        <div class="pages">
            <span id="selectFaultReasonAmount">
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