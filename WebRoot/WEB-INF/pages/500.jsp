<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  isErrorPage="true"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
    <HEAD>
        <TITLE>服务器内部错误！</TITLE>
        <META http-equiv=Content-Type content="text/html; charset=gb2312">
        <STYLE type=text/css>
.font14 {
    FONT-SIZE: 14px
}

.font12 {
    FONT-SIZE: 12px
}
</STYLE>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
                <FONT color=#0099ff>抱歉，服务器内部错误</FONT>
            </span>
            </td>
        </tr>
    </table>
    </BODY>
</HTML>