<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<style type="text/css">
#privilege_tbody label
{
float: none;
padding: 0px;
}
</style>
<div class="pageContent">
<form method="post" action="<c:url value='system/group/saveGroupPrivilege.do?navTabId=grouplistNav&callbackType=closeCurrent'/>" 
class="pageForm required-validate" onsubmit="return checkSel()&&validateCallback(this,dialogAjaxDone)">
	<div class="pageFormContent" layoutH="56">
	<table class="tableFormContent nowrap">
		<tr>
		<th>角色编号：</th>
		<td>
				${group.name}
				<input type="hidden" name="groupId" value="${group.id}"/>	
		</td>
		<th width="20%">角色名称：</th>
		<td width="44%">${group.description}</td>
		</tr>
	</table>
	<div style="height: 5px"></div>
		<table class="treeTable" width="100%">
		    <tr>
		        <th>模块名称</th>
		        <th width="60%">操作权限</th>
		    </tr>
		    <tbody id="privilege_tbody">
	        ${privilegeSettingHtml}
		    </tbody>
		</table>
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
	initRolePage();
	
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
//-->
</script>