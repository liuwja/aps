<%@ page language="java" import="java.net.InetAddress" pageEncoding="utf-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>欢迎方太QMS系统</title>
<script>
<shiro:notAuthenticated>  
    top.location.href = "login.do"
</shiro:notAuthenticated>
<shiro:authenticated>   
    top.location.href = "verifyLogin.do"
</shiro:authenticated>   
</script> 
</head>
 
<body>
</body>
</html>