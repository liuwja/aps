<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
    jQuery(document).ready(function(){
        //jQuery("#formID").validationEngine();
    });

</script>
<div class="pageContent">
	<form method="post" id="formID"  action="<c:url value='system/user/update.do?navTabId=userlistNav&callbackType=closeCurrent'/>" 
	class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" >
		<input type="hidden" name="id" value="${user.id}">
		<table class="tableFormContent nowrap">
        <tr >
            <th>工号</th>
            <td><input name="username" type="text" size="40" class="required" value="${user.username}" /></td>
        </tr>
        <tr>
        <th>姓名</th>
            <td><input name="name" type="text" size="40" class="required" value="${user.name}" /></td>          
        </tr>
        
        <tr>
            <th>email</th>
            <td>
            <input type="text" size="40" name="email" id="email" class="email"  value="${user.email}"/>
            </td>   
        </tr>
            <tr>
            <th>手机</th>
            <td>
            <input type="text" size="40" name="phone"  value="${user.phone}"/>
            </td>
        </tr>
        
        <tr>
            <th>工厂 </th>
            
            <td><select name="factory" class="required combox">
                    <option value="">请选择</option>
                    <option value="燃气工厂">燃气工厂</option>
                    <option value="电器工厂" >电器工厂</option>
                </select><span style="color:red; font-family:华文中宋;">*</span>	
            </td>
        </tr>
        
        
		</table>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
