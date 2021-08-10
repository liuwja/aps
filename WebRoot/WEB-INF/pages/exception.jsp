<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isErrorPage="true"%>
<%@ page import="org.apache.log4j.Logger" %>
<%@page import="java.io.StringWriter"%>
<%@page import="java.io.PrintWriter"%>
<%
    String errorMsg = null;
    Throwable ex = null;
    if (request.getAttribute("javax.servlet.error.exception") != null) {
        ex = (Throwable) request.getAttribute("javax.servlet.error.exception");
    } else {
        ex = exception;
    }
    
    if (ex != null) {
        Logger logger = Logger.getLogger("exception.jsp");
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        errorMsg = stringWriter.toString();
        
        logger.error(errorMsg);
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>程序运行错误</title>
</head>

<body>
<span style="font-weight: bold;padding-top: 5px;">错误原因:</span><div></div>
<br/><div style="width: 260px;word-wrap:break-word;"><%=ex.getMessage() %></div>
</body>
</html>