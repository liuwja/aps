<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<style>
<!--
#privilege_tbody label
{
float: none
}
-->
</style>
<div class="pageContent">
<form method="post" action="<c:url value='system/group/saveGroupPrivilege.do?navTabId=grouplistNav&callbackType=closeCurrent'/>" 
class="pageForm required-validate" onsubmit="return validateCallback(this,dialogAjaxDone)">
	<div class="pageFormContent" layoutH="56">
	<table class="tableFormContent nowrap">
		<tr>
		<th>角色编号：</th>
		<td>
				${group.groupCode}
				<input type="hidden" name="groupKey" value="${group.groupKey}"/>	
		</td>
		<th width="20%">角色名称：</th>
		<td width="44%">${group.groupName}</td>
		</tr>
	</table>
	<div style="height: 5px"></div>
	<table style="width: 100%;background-color: #F7F9FC;border:1px solid #E6E6FA">
		    <tr>
		        <th style="width:46px;line-height: 31px;font-weight: bold;">选择</th>
		        <th style="width:280px;line-height: 31px;font-weight: bold;">模块名称</th>
		        <th style="width:300px;line-height: 31px;font-weight: bold;">操作权限</th>
		    </tr>
	</table>	
	<div layoutH="136">
		<table class="treeTable" cellpadding="0" cellspacing="0"  id= "menuTreeTable">
		    <tbody id="privilege_tbody">
		      <c:forEach items="${uiMenus}" var="m" varStatus="s">
		      	<tr data-tt-id="${m.rowCode}"  <c:if test="${not empty m.parentRowCode}"> data-tt-parent-id="${m.parentRowCode}" </c:if> >
		      		<td style="text-align: center;width: 50px">
		      		<c:if test="${m.type eq 3}">
			      		<input type="checkbox" class="menuRow menu_chk_${m.parentRowCode}" value="${m.rowCode}" />
		      		</c:if>
		      		<c:if test="${m.type ne 3}">
			      		<input type="checkbox" name="folder_chk_${m.rowCode}" class="folderRow" value="${m.rowCode}" />
		      		</c:if>		      		
		      		
		      		</td>
		      		<td style="width:300px">${m.name}</td>
		      		<td style="width:300px">
		      		<c:forEach items="${m.optList}" var="op" varStatus="ops">
			      		<input type="checkbox" id="chk_${m.rowCode}_${ops.count}" name="operationCode" 
			      		 ${op.selected ? 'checked="checked"' : ''}  value="${op.permissionCode}"/>
			      		<label for="chk_${m.rowCode}_${ops.count}">${op.name }</label> 
			      		<c:if test="${ops.count % 4 ==0 }"><br> </c:if>
		      		</c:forEach>
		      		</td>
		      	</tr>
		      </c:forEach>
		    </tbody>
		</table>
		</div>
</div>
<div class="formBar">
	<ul>
		<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
		<li>
			<div class="buttonActive"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
		</li>
	</ul>
</div>	
</form>
</div>
 <script type="text/javascript">
//<!--
// top
$(document).ready(function(){
	// 初始化全选状态
	$(".setAll").each(function(){
    	var $td = $(this).parent().nextAll("td");
		var $inputSpan = $(".inputValueRole", $td);
		var allCheckBoxLength = $("input[type=checkbox]", $inputSpan).length;
		var checkedLength = $("input:checked", $inputSpan).length; 
		if (allCheckBoxLength == checkedLength) {
			$(this).attr("class", "button chk checkbox_true_full setAll");
		}
	});
	
	var $trs = $("#privilege_tbody>tr"); //选择所有行 
        $trs.filter(":odd").css("background-color","#f5fafa");
	
    $("#menuTreeTable", $.pdialog.getCurrent()).treetable({column:1, expandable: true, clickableNodeNames:true });
    $("#menuTreeTable", $.pdialog.getCurrent()).treetable('expandAll'); 
    bindMenuChkBox();
    bindFolderChkBox();	
});
function checkSel()
{
	var selLen = $('#privilege_tbody input:checkbox[name=privilegeKey]:checked').length;
	if(selLen == 0)
	{
	   alertMsg.warn('请选择至少一个权限！');
	   return false;
	}
	return true;
}
function bindMenuChkBox()
{
	
	$(".menuRow", $.pdialog.getCurrent()).click(function()
			{
				if($(this).attr("checked") == 'checked')
				{
					$(this).closest("tr").find("td:(2) input").attr("checked","checked");
				}
				else
				{
					$(this).closest("tr").find("td:(2) input").removeAttr("checked");
				}
			});
}
function bindFolderChkBox()
{
	
	$(".folderRow", $.pdialog.getCurrent()).click(function()
			{
		        var menus = $(".menu_chk_" + $(this).val(), $.pdialog.getCurrent());
				if($(this).attr("checked") == 'checked')
				{
					menus.attr("checked","checked");
					menus.trigger("click");
					menus.attr("checked","checked");
				}
				else
				{
					menus.removeAttr("checked");
					menus.trigger("click");
					menus.removeAttr("checked");
				}
			});
}
//-->
</script>