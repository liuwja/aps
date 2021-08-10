<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<style>
<!--
#dlg_errinfo
{
    height: 20px;
    color: red;
    font-size: 20px;
    text-align: left;
}
-->
</style>
<script type="text/javascript">
function checkInput()
{
    var loginame = document.getElementById("dlg_loginName").value;
    var pwd = document.getElementById("dlg_password").value;
    if(loginame == "")
    {
        document.getElementById("dlg_errinfo").innerHTML = "请输入用户名或密码"
        document.getElementById("dlg_loginName").focus();
        return false;
    }
    if(pwd == "")
    {
        document.getElementById("dlg_errinfo").innerHTML = "请输入用户名或密码"
        document.getElementById("dlg_password").focus();
        return false;
    }
    
    $(window).unbind('beforeunload');
	window.onbeforeunload = null;
    $(window).unbind('unload');
    window.onunload = null;
    return true;
}
function login()
{
    if(checkInput())
    {
        document.getElementById("loginForm").submit();
    }
}
</script>
<div class="pageContent">
    <form method="post" action="verifyLogin.do" class="pageForm" id= "loginForm"
    onkeydown="if(event.keyCode==13){login();}"  onsubmit="return checkInput()">
        <div class="pageFormContent" layoutH="68">
            <div class="unit">
                <div id="dlg_errinfo"></div>
            </div>        
            <div class="unit">
                <label>用户：</label>
                <input type="text" id="dlg_loginName" name="userName" size="30" class="required"/>
            </div>
            <div class="unit">
                <label>密码：</label>
                <input type="password" id="dlg_password" name="password" size="30" class="required"/>
            </div>
        </div>
        <div class="formBar">
            <ul>
                <li><div class="buttonActive"><div class="buttonContent"><button type="submit">登录</button></div></div></li>
                <li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
            </ul>
        </div>
    </form>
</div>