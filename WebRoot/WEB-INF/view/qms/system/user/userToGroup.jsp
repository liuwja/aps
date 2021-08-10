<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
<!--
jQuery(document).ready(function() {
});
 function delSelectedGroup(obj,groupKey)
 {
	 var userKey = $("#settingUserKey").val();
        var url = "system/group/delUserGroup.do";
        $.post(url, {groupKey: groupKey,userKey: userKey},function()
       	{
		     $("#selectGroupTbody").find("tr[groupKey="+groupKey+"]").remove();;
		     $("#selectPersonAmount").html("已选记录数："+$("#selectGroupTbody tr").length);
       	});
 }
function selectGroup(row,groupKey)
{		
    var obj = $("#selectGroupTbody").find("tr[groupKey="+groupKey+"]");
       
    if(obj.length == 0)
    {
	var url = "system/group/addUserGroup.do";
	var userKey = $("#settingUserKey").val();
	
	$.post(url, {groupKey: groupKey,userKey: userKey},function(){
	    var cloneRow = $(row).clone(true);
	    cloneRow.find("td:eq(0)").remove();
	    
	     var userRowArr = new Array();
	     userRowArr.push("<tr  target='userId' groupKey='" + groupKey +"'>");
	     userRowArr.push("<td style='text-align: center;'>");
	     userRowArr.push('<input type="checkbox"  checked="checked"  name="groupKey" value="'+groupKey+'"/>');
	     userRowArr.push("</td>");
	     userRowArr.push(cloneRow.html());
	     userRowArr.push("<td>");
	     userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedGroup(this,"+groupKey+")'  title='删除'><img  src='<c:url value="/resources/img/delete.png"/>' /></a>");
	     userRowArr.push("</td>");
	     
	     userRowArr.push("</tr>");
	     var newtr = userRowArr.join("");
	     
	     //根据表头获取列宽并设置数据列宽
	     var $tr = $("#selectedUsersDiv .gridThead table tr");
	     var $newtr = $(newtr);
	     $newtr.find("td:eq(0)").css("width",$tr.find("th:eq(0)").css("width"));
	     $newtr.find("td:eq(1)").css("width",$tr.find("th:eq(1)").css("width"));
	     $newtr.find("td:eq(2)").css("width",$tr.find("th:eq(2)").css("width"));
	     $newtr.find("td:eq(3)").css("width",$tr.find("th:eq(3)").css("width"));
	     $("#selectGroupTbody").append($newtr);
	     $("#selectPersonAmount").html("已选记录数："+$("#selectGroupTbody tr").length);
	    });
    }
    else
    {
        alert("此用户组已选择");    
    }
    
}

    function delSelectedGroups(groupKey)
    {
    	var userKey = $("#settingUserKey").val();
	    var url = "system/group/delUserGroup.do";
	    $.post(url, {groupKey: groupKey, userKey: userKey});
    	/**
        $('#selectGroupTbody tr').remove();
         
        $("#selectPersonAmount").html("已选记录数："+$("#selectGroupTbody tr").length);
        **/
    }
    function callFunc()
    {
        alert($("#noticeGroup_noticeMen").val());
    }
    
    function reloadUserPage()
    {
    	navTab.reload("system/user/list.do", {navTabId: "userlistNav"});
    }
//-->
</script>
<div class="pageContent">
<%-- 待选择列表  开始--%>
<fieldset style="width:97%;margin-left: 5px;border: 1px solid #9A9DC7;">
    <legend>当前用户</legend> 
	<table class="list" width="50%">
	<tr>
	    <th style="text-align: left;padding-left: 10px">姓名:</th>   
	     <td>    
	    ${sysuser.description}
	     </td>    
	      <th>用户名：</th>  
	     <td>     
	    ${sysuser.userName}
	<input type="hidden" name="userKey" id="settingUserKey" value="${sysuser.userKey}" />
	    </td>    
	</tr>
	</table>
</fieldset>

<%--待选用户组列表 --%>
<fieldset style="float: left;width: 50%;border: 1px solid #9A9DC7;margin-left: 5px" id="groupListDiv">
    <legend>待选用户组列表</legend>
	<div class="pageHeader">
	    <div class="searchBar">
	    <form onsubmit="return  divSearch(this, 'groupListDiv')" id="pagerForm" action="system/group/groupList.do" method="post">
	        <table class="searchContent">
	            <tr>
	                <td>
	                                            编号： 
	                </td>
	                <td>
					    <input type="hidden" name="pageNum" value="1" />
					    <input type="hidden" name="numPerPage" value="${page.numPerPage}" />
					    <input type="hidden" name="direction" value="" />
	                    <input type="text" name="groupCode" value="${param.groupCode }" size="10"/>
	                </td>               
	                <td>
	                                            名称： 
	                </td>
	                <td>
	                    <input type="text" name="groupName" value="${param.groupName}" size="10"/>
	                </td>               
	                <td><div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
	               
	                </td>   
	            </tr>
	        </table>
	    </form>
	    </div>
	</div>

	<table class="table" width="100%" layoutH="225">
	    <thead>
	        <tr>
	            <th width="50"><input type="checkbox" class="checkboxCtrl" group="userId" ></th>
	            <th width="120">编号</th>
	            <th width="120">名称</th>
	        </tr>
	    </thead>
	    <tbody> 
	        <c:forEach items="${list}" var="o">
	            <tr target="userId" rel="${o.groupKey}" onclick="selectGroup(this,'${o.groupKey}')">
	                <td style="text-align: center;">
	                    <input type="checkbox" name="chkbox" >
	                </td>
	                <td>${o.groupCode}</td>
	                <td>${o.groupName}</td>
	            </tr>       
	        </c:forEach>        
	    </tbody>
	</table>
        
    <c:import url="../../../_frag/pager/panelBar.jsp">
       <%-- 对话框分页时需要传此参数 --%>
       <c:param name="targetName" value="dialog"/>
       <c:param name="relDivId" value="groupListDiv"/>    
    </c:import>   
</fieldset>
<%-- 待选择列表  结束--%>

<%-- 已选择列表  开始--%>
<fieldset style="float: left;width: 45%;border: 1px solid #9A9DC7;margin-left: 6px;margin-right: 2px">
    <legend>已选择用户组</legend>  
    <div class="pageHeader">    
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="delSelectedGroups()">全部删除</button></div></div>
                    </td>           
                </tr>
            </table>
        </div>  
    </div>    
    <div id="selectedUsersDiv">
    <table id = "selectedGroupTable" class="table" width="100%" layoutH="225">
            <thead>
                <tr>
                    <th width="50"><input type="checkbox" class="checkboxCtrl" group="userId" ></th>
                    <th >编号</th>
                    <th >名称</th>
                    <th >操作</th>
                </tr>
            </thead>
            <tbody id="selectGroupTbody"> 
                <c:forEach items="${selectGroups}" var="o" >
                    <tr target="userId" rel="${o.groupKey}" groupKey = '${o.groupKey}'>
                        <td style="text-align: center;">
                            <input type="checkbox" name="groupKey" value="${o.groupKey}">
                        </td>
                        <td>${o.groupCode}</td>
                        <td>${o.groupName}</td>
                        <td>
                        <a class='delete' href='javascript:void(0);' onclick='delSelectedGroup(this, ${o.groupKey})'  title='删除'><img  src='<c:url value="/resources/img/delete.png"/>' /></a>
                        </td>
                    </tr>       
                </c:forEach>             
            </tbody>
     </table>
    <div class="panelBar">
        <div class="pages">
            <span id="selectPersonAmount">
                           已选记录数：${fn:length(selectGroups)}
            </span>
        </div>
    </div>       
    </div>
</fieldset>
<%-- 已选择列表  结束--%>      
        <div class="formBar">
            <ul>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close" onclick="reloadUserPage()">确定</button></div></div>
                </li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>                
            </ul>
        </div>

    </div>