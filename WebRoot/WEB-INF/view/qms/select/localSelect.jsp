<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">

//<!--

jQuery(document).ready(function() {
    loadSelectedUsers();
});
 function loadSelectedUsers()
 {
	 var groupName = "${vo.navId}";
	 if($.trim(groupName) != "")
	 {
		 var keys = $("#"+groupName+"_id").val();
		 
		 if($.trim(keys) != "")
		 {
	         var url = "<c:url value='qms/commonSelect/queryFaultLocalByid.do' />";
	         $("#selectFaultTypeTbody").load(url,{keys:keys},function(){
	        	 $("#selectFaultTypeAmount").html("已选记录数："+$("#selectFaultTypeTbody tr").length);
	         });
	         
		 }
	 }
 }
 function delSelectedUser(obj)
 {
	 $(obj).parent().parent().remove();
	 $("#selectFaultTypeAmount").html("已选记录数："+$("#selectFaultTypeTbody tr").length);
 }
function selectRow(row,keystr,name,code)
{
	var obj = $("#selectFaultTypeTbody").find("tr[id="+keystr+"]");
    if(obj.length == 0)
    {
	var cloneRow = $(row).clone(true);
	cloneRow.find("td:eq(0)").remove();
	
	 var userRowArr = new Array();
	 userRowArr.push("<tr  target='id' id='" + keystr +"'>");
	 userRowArr.push("<td style='text-align: center;'>");
	 var vv = "{id:'" +keystr+"',name: '"+name+"',code:'"+code+"'}" ;
	 
     userRowArr.push('<input type="checkbox"  checked="checked"  name="id" value="'+vv+'"/>');
	 userRowArr.push("</td>");
	 userRowArr.push(cloneRow.html());
	 userRowArr.push("<td style='text-align: center;' width='10%''>");
	 userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>");
	 userRowArr.push("</td>");
	 
	 userRowArr.push("</tr>");
	 $("#selectFaultTypeTbody").append(userRowArr.join(""));
     $("#selectFaultTypeAmount").html("已选记录数："+$("#selectFaultTypeTbody tr").length);
    }
    else
   	{
    	alertMsg.info("此区域已选择");	
   	}
}

    function removeSelectedItem()
    {
        $('#selectFaultTypeTbody tr').remove();
         
        $("#selectFaultTypeAmount").html("已选记录数："+$("#selectFaultTypeTbody tr").length);
    }
    function callFunc()
    {
    	alert($("#noticeGroup_noticeMen").val());
    }
//-->
</script>
<div class="pageContent">
<%-- 待选择列表  开始--%>
	<div style="float: left;width: 55%;border: 1px solid #ededed;" id="tofindTypeSelectResult">
		<div  class="pageHeader">
		待选列表
		</div>
    	<form onsubmit="return  divSearch(this, 'tofindTypeSelectResult')" id="pagerForm" action="qms/commonSelect/findTypeSelectResult.do" method="post">
			<div class="pageHeader">
			    <div class="searchBar">
			        <table class="searchContent">
			        	<tr>
			        		<td class="dateRange">
								 服务中心代码:
			                </td>                                      
			                <td>
								<input type="text" name="locationCode" id="code" size="10" value="${mo.locationCode}"/>
			                </td> 
			        		
			                <td class="dateRange">
			                	 服务中心名称:                       
			                </td>
			                <td >
								<input type="text" name="location" id="name" size="10" value="${mo.location}"/>
			                </td>   
			            </tr>
			                <tr>
			                <td class="dateRange">
			               		所属省份:  
			                </td>                                      
			                <td>
								<input type="text" name="province" id="name" size="10" value="${mo.province}"/>
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
	                    <th width="3%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
	                    <th width="40%">服务中心代码</th>
	                    <th width="30%">服务中心名称</th>
	                    <th width="30%">所属省份</th>
	                </tr>
	            </thead>
	            <tbody> 
	                <c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.location}','${o.locationCode}')">
	                        <td style="text-align: center;">
	                            <input type="checkbox" name="chkbox" >
	                        </td>
	                        <td>${o.locationCode}</td>
	                        <td>${o.location}</td>
	                        <td>${o.province}</td>
	                    </tr>       
	                </c:forEach>        
	            </tbody>
	        </table>
	        
		   <c:import url="../../_frag/pager/panelBar.jsp">
		       <%-- 对话框分页时需要传此参数 --%>
		       <c:param name="targetName" value="dialog"/>
		       <c:param name="relDivId" value="tofindTypeSelectResult"/>    
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
                    <th width="50"><input type="checkbox" class="checkboxCtrl" group="i" ></th>
                    <th >服务中心代码</th>
                    <th >服务中心名称</th>
                    <th >所属省份</th>
                    <th >操作</th>
                </tr>
            </thead>
            <tbody id="selectFaultTypeTbody"> 
            	<c:forEach items="${list2}" var="o">
                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.location}','${o.locationCode}')">
                        <td style="text-align: center;">
                            <input type="checkbox" name="chkbox" >
                        </td>
                        <td>${o.locationCode}</td>
                        <td>${o.location}</td>
                        <td>${o.province}</td>
                        <td style='text-align: center;'>
	 						<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this)'  title='删除'><img  src='<c:url value='resources/img/delete.png'/>' /></a>
	 					</td>
                    </tr>       
                </c:forEach>  
            </tbody>
     </table>
    <div class="panelBar">
        <div class="pages">
            <span id="selectFaultTypeAmount">
                           已选记录数：0
            </span>
        </div>
    </div>       
    </div>
</div>
<%-- 已选择列表  结束--%>      
    	<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="button" multLookup="id">确定</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>

    </div>