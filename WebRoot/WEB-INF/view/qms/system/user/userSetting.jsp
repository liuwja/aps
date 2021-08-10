<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
<!--
jQuery(document).ready(function() {
});
 function delSelectedUser(obj,userKey)
 {
        var groupKey = $("#settingGroupKey").val();
        var url = "system/group/delUserGroup.do";
        $.post(url, {groupKey: groupKey,userKey: userKey},function()
        {
        	 $("#selectUserTbody").find("tr:eq(0)");
             $("#selectUserTbody").find("tr[userKey="+userKey+"]").remove();;
             $("#selectPersonAmount").html("已选记录数："+$("#selectUserTbody tr").length);
        });
 }
function selectUser(row,userKey)
{       
    var obj = $("#selectUserTbody").find("tr[userKey="+userKey+"]");
    if(obj.length == 0)
    {
    var url = "system/group/addUserGroup.do";
    var groupKey = $("#settingGroupKey").val();
    
    $.post(url, {groupKey: groupKey,userKey: userKey},function(){
        var cloneRow = $(row).clone(true);
        cloneRow.find("td:eq(0)").remove();
        
         var userRowArr = new Array();
         userRowArr.push("<tr  target='userId' userKey='" + userKey +"'>");
         userRowArr.push("<td style='text-align: center;'>");
         userRowArr.push('<input type="checkbox"  checked="checked"  name="userKey" value="'+userKey+'"/>');
         userRowArr.push("</td>");
         userRowArr.push(cloneRow.html());
         userRowArr.push("<td>");
         userRowArr.push("<a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this,"+userKey+")'  title='删除'><img  src='<c:url value="/resources/img/delete.png"/>' /></a>");
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
         $newtr.find("td:eq(4)").css("width",$tr.find("th:eq(4)").css("width"));
         $newtr.find("td:eq(5)").css("width",$tr.find("th:eq(5)").css("width"));
         $("#selectUserTbody").append($newtr);
         $("#selectPersonAmount").html("已选记录数："+$("#selectUserTbody tr").length);
        });
    }
    else
    {
        //alert("此用户已选择");    
    }
    
}

    function delSelectedUsers(userKey)
    {
        var groupKey = $("#settingGroupKey").val();
        var url = "system/group/delUserGroup.do";
        $.post(url, {groupKey: groupKey, userKey: userKey});
        /**
        $('#selectUserTbody tr').remove();
         
        $("#selectPersonAmount").html("已选记录数："+$("#selectUserTbody tr").length);
        **/
    }
    function callFunc()
    {
        alert($("#noticeGroup_noticeMen").val());
    }
    function reloadGroupPage()
    {
    	navTab.reload("system/group/list.do", {navTabId: "grouplistNav"});
    }    
//-->
</script>
<div class="pageContent">
<%-- 待选择列表  开始--%>
<fieldset style="width:98%;margin-left: 5px;border: 1px solid #9A9DC7;">
    <legend>当前用户组</legend> 
	<table class="list" width="50%">
	<tr>
	    <th style="width: 100px">编号:</th>   
	     <td style="width: 200px">    
	    ${group.groupCode}
	     </td>    
	      <th style="width: 100px">名称：</th>  
	     <td style="width: 200px">     
	    ${group.groupName}
	<input type="hidden" name="groupKey" id="settingGroupKey" value="${group.groupKey}" />
	    </td>    
	</tr>
	</table>
</fieldset>
  <fieldset style="float: left;width: 52%;border: 1px solid #9A9DC7;margin-left: 5px" id="usersListDiv">
        <legend>待选用户列表</legend>
<div class="pageHeader">
    <div class="searchBar">
    <form onsubmit="return  divSearch(this, 'usersListDiv')" id="pagerForm" action="system/user/queryUsers.do" method="post">
        <table class="searchContent">
            <tr>
                <td>
                                            用户名： 
                </td>
                <td>
                    <input type="hidden" name="pageNum" value="1" />
                    <input type="hidden" name="numPerPage" value="${page.numPerPage}" />
                    <input type="hidden" name="direction" value="" />
                    <input type="text" name="userName" value="${param.userName}" size="10"/>
                </td>               
                <td>
                                            姓名： 
                </td>
                <td>
                    <input type="text" name="name" value="${param.name}" size="10"/>
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
                    <th width="120">用户号</th>
                    <th width="120">用户姓名</th>
                    <th width="120">工厂</th>
                    <th width="120">车间</th>
                </tr>
            </thead>
            <tbody> 
                <c:forEach items="${allUsers}" var="o">
                    <tr target="userId" rel="${o.userKey}" onclick="selectUser(this,'${o.userKey}')">
                        <td style="text-align: center;">
                            <input type="checkbox" name="chkbox" >
                        </td>
                        <td>${o.userName}</td>
                        <td>${o.description}</td>
		                <td>${o.uda1}</td>
		                <td>${o.uda2}</td>                        
                    </tr>       
                </c:forEach>        
            </tbody>
        </table>
        
    <c:import url="../../../_frag/pager/panelBar.jsp">
       <%-- 对话框分页时需要传此参数 --%>
       <c:param name="targetName" value="dialog"/>
       <c:param name="relDivId" value="usersListDiv"/>    
    </c:import>   
<%-- 待选择列表  结束--%>
 </fieldset>
<%-- 已选择列表  开始--%>
  <fieldset style="float: left;width: 44%;border: 1px solid #9A9DC7;margin-left: 6px;margin-right: 2px">
        <legend>已选择用户</legend>    
    <div class="pageHeader">    
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <td>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="delSelectedUsers()">全部删除</button></div></div>
                    </td>           
                </tr>
            </table>
        </div>  
    </div>    
    <div id="selectedUsersDiv">
    <table id = "selectedGroupTable" class="table" width="100%" layoutH="225">
            <thead>
                <tr>
                    <th width="100"><input type="checkbox" class="checkboxCtrl" group="userId" ></th>
                    <th width="200">用户名</th>
                    <th width="300">用户姓名</th>
                    <th width="150">工厂</th>
                    <th width="150">车间</th>
                    <th width="100">操作</th>
                </tr>
            </thead>
            <tbody id="selectUserTbody"> 
                <c:forEach items="${groupUsers}" var="o" >
                    <tr target="userId" rel="${o.userKey}" userKey = '${o.userKey}'>
                        <td style="text-align: center;">
                            <input type="checkbox" name="userKey" value="${o.userKey}">
                        </td>
                        <td>${o.userName}</td>
                        <td>${o.description}</td>
                        <td>${o.uda1}</td>
                        <td>${o.uda2}</td>                         
                        <td>
                        <a class='delete' href='javascript:void(0);' onclick='delSelectedUser(this, ${o.userKey})'  title='删除'><img  src='<c:url value="/resources/img/delete.png"/>' /></a>
                        </td>
                    </tr>       
                </c:forEach>             
            </tbody>
     </table>
    <div class="panelBar">
        <div class="pages">
            <span id="selectPersonAmount">
                           已选记录数：${fn:length(groupUsers)}
            </span>
        </div>
    </div>       
    </div>
 </fieldset>
<%-- 已选择列表  结束--%>      
        <div class="formBar">
            <ul>
                <li>
                    <div class="button"><div class="buttonContent"><button type="button" class="close" onclick="reloadGroupPage()">确定</button></div></div>
                </li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>                
            </ul>
        </div>

    </div>