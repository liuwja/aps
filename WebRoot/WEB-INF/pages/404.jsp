<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
    <HEAD>
        <TITLE>你访问的页面不存在或被删除！</TITLE>
        <META http-equiv=Content-Type content="text/html; charset=gb2312">
        <STYLE type=text/css>
.font14 {
    FONT-SIZE: 14px
}

.font12 {
    FONT-SIZE: 12px
}
</STYLE>

        <META content="MSHTML 6.00.2900.3354" name=GENERATOR>
    </HEAD>
    <BODY>
    <table>
        <tr>
            <td>
                <img alt="图片不存在" src="<%=basePath%>/resources/img/error/x.png" >
            </td>
            
            <td style="padding-left: 15px">
	        <span class="font14">
	        可能原因：<br>
	        1、<FONT color=#0099ff>页面不存在</FONT>
	        <br>
	        2、<FONT color=#ff0000>页面已被删除</FONT>
            <br>
            3、<FONT color=#ff0000>URL拼写错误</FONT><BR>	        
	        </span>
            </td>
        </tr>
    </table>
    </BODY>
</HTML>