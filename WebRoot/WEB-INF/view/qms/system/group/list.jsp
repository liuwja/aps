<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<!-- 分页相关 -->
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>
<script type="text/javascript">
$(function(){
	$("#resetBtn", navTab.getCurrentPanel()).click(function(){
	   $("form input", navTab.getCurrentPanel()).val("");
    });
})
</script>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" rel="pagerForm" action="system/group/list.do" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					用户编号：
				</td>
				<td>
					<input type="text" name="groupCode" value="${param.groupCode}" />
				</td>	
				<td>
					用户组名称：
				</td>
                <td>
                    <input type="text" name="groupName" value="${param.groupName}" />
                </td>   				
				<td>
				<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>
				<div class="buttonActive"><div class="buttonContent"><button type="button" id="resetBtn">重置</button></div></div>
				</td>	
			</tr>
		</table>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
            <shiro:hasPermission name="sys:userGroupMgr:ADD">
				<li><a class="add" href="system/group/add.do" target="dialog" width="628" height="340" rel="addCarrier" mask="true" title="新增—用户组数据"  ><span>新增</span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:userGroupMgr:EDIT">
    			<li><a class="edit" href="system/group/edit.do?groupKey={groupKey}"  height="340" width="628" target="dialog" mask="true"  rel="editCarrier" title="修改—用户组数据"><span>修改</span></a></li>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:userGroupMgr:DEL">
    			<li><a class="delete" href="system/group/delete.do?groupKey={groupKey}"  target="ajaxTodo"  title="确定要删除吗?"><span>删除</span></a></li>
            </shiro:hasPermission>                        		
		</ul>
	</div>
	<table class="table" width="100%" layoutH="123">
		<thead>
			<tr>
			    <th width="5%" >选择</th>
			    <th width="15%">用户组编号</th>
			    <th width="15%">用户组名称</th>
			    <th >用户</th>
			    <th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
		<!-- 文字在左，数字在右 -->
		<c:forEach items="${list}" var="o" varStatus="s">
			<tr target="groupKey" rel="${o.groupKey}">
				<td align="center">
					<input type="radio" group="code" name="carrierCode">
				</td>
				<td>${o.groupCode}</td>
				<td>${o.groupName}</td>
				<td>
				<c:forEach items="${o.ugList}" var="ug" varStatus="ugst">
				    ${ug.userDescription}(${ug.userName})&nbsp;
					<c:if test="${ugst.count % 5 ==0 }">
					<br>
					</c:if>
				</c:forEach>
				
				</td>
				<td>
				<shiro:hasPermission name="sys:userGroupMgr:usersSetting">
				<a class="edit" href="system/user/userSetting.do?groupKey=${o.groupKey}"   height="500" width="1000"  mask="true" target="dialog" rel="usergroupPLG" title="设置用户"><span>设置用户</span></a>
				</shiro:hasPermission>
				<shiro:hasPermission name="sys:userGroupMgr:plgSetting">
				<a class="edit" href="system/group/editGroupPrivilege.do?groupKey=${o.groupKey}"   height="500" width="850" target="dialog" maxable ="false" rel="groupPlg" title="设置用户组权限"><span>设置用户组权限</span></a>
				</shiro:hasPermission>				
				</td>
			</tr>		
		</c:forEach>
		</tbody>
	</table>
	<!-- 分页相关 -->
	<c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
