<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%><%@ include file="/common/include.inc.jsp"%>
<%@page import="com.peg.web.menu.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>市场质量管理系统</title>
<%@ include file="head.inc"%>
<script type="text/javascript">
$(function(){
    DWZ.init("jui/dwz.frag.xml", {
        loginUrl:"loginDialog.do", loginTitle:"登录",    // 弹出登录对话框
        //loginUrl:"login.sp?method=toLogin", // 跳到登录页面
        statusCode:{ok:200, error:300, timeout:301}, //【可选】
        pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
        debug:false,    // 调试模式 【true|false】
        callback:function(){
            initEnv();
            //$("#themeList").theme({themeBase:"jui/themes"}); // themeBase 相对于index页面的主题base路径
        }
    });
	$(window).unbind('beforeunload');
	window.onbeforeunload = null;
 
    window.onbeforeunload = onbeforeunload_handler;   
    window.onunload = onunload_handler;   
    function onbeforeunload_handler(){  
        return "";
    }   
       
    function onunload_handler(){   
        logout();
    } 
    loadAlarmInfo();
});

function getDataSourceByMenuName(accordion, folder, menu, chartType) {
	if (chartType != null && chartType != "" && chartType != undefined) {
		if (chartType.indexOf("排列图") > -1) {
			chartType = "排列图";
		} else if (chartType.indexOf("对比图") > -1) {
			chartType = "对比图";
		} else if (chartType.indexOf("趋势图") > -1) {
			chartType = "趋势图";
		} else if (chartType.indexOf("时间序列图") > -1) {
			chartType = "时间序列图";
		} else if (chartType.indexOf("P控制图") > -1) {
			chartType = "P控制图";
		} else if (chartType.indexOf("数据明细") > -1) {
			chartType = "数据明细";
		}
	}
	var getDataSourceByMenuNameUrl = "<c:url value='system/dataSource/getDataSourceDescription.do'/>";
	var getDataSourceByMenuNameJsonData = {accordion:accordion, folder:folder, menu:menu, chartType:chartType};
	$.post(getDataSourceByMenuNameUrl,getDataSourceByMenuNameJsonData, function(getDataSourceByMenuNameData) {
		alertMsg.correct(getDataSourceByMenuNameData.description);
	});
}

function filterMenu(obj) {
	$("#menuContainer .accordionHeader h2 .count").remove();
	var searchContent = $.trim($(obj).val());
	if(searchContent == '') {
		$("#menuContainer li").show();
	} else {
		$("#menuContainer li").hide();
		$("#menuContainer li").filter(":contains('"+searchContent+"')").show();
		$("#menuContainer .accordionHeader").each(function(){
		  	var len = $(this).next(".accordionContent").find("a[href]").filter(":contains('"+searchContent+"')").length;
			if(len > 0) {
				$(this).find("h2").append("<span class='count' style='color:blue;float: right;display: block;overflow: hidden;text-indent: 0px;width: 20px;height: 18px;margin-top:6px;background: none'>"+len+"</span>");
		  	} else {
				$(this).find("h2").append("<span class='count' style='color:blue;float: right;display: block;overflow: hidden;text-indent: 0px;width: 20px;height: 18px;margin-top:6px;background: none'>0</span>");
			}
		});
	}
}

function logout() {
	var url = "logout.do";
	$.ajax({ url: url});
}

var loadingHtml = "<div class='loadingdiv'><img src='resources/img/dashboard/loading.gif' class='loadingimg'/><div class='loadingtext'><nobr>正在努力加载中，请稍等...</nobr></div></div>";

function getObjByIdInCurrentTab(objId) {
	return $("#"+objId, navTab.getCurrentPanel());  
}

function loadDataByUrl(url, $target) {
	$target.html(loadingHtml);
	$.ajax({
		url: url,
		cache: false,
		global:false,
		success: function(data) {
			if (data.statusCode == DWZ.statusCode.timeout) {
				$target.html(data.message + "<br><br><a href='###' onclick='DWZ.loadLogin()'>点击我登录 </a>");
			} else {
				$target.html(data);
			}
		}    
	});
}

function loadAlarmInfo(){
	var sellUrl = "system/homePage/marketHomePage.do";
	loadDataByUrl(sellUrl, $("#alarmInfoDiv"));
}
</script>
<%
MesMenu mesMenu = (MesMenu)request.getAttribute("mesMenu");
%>
</head>

<body> 
    <div id="layout" >
        <div id="header">
            <div class="headerNav">
                <img alt="" src="jui/themes/default/images/logo.png" style="margin-top: 5px;margin-left: 10px;">
                <img alt="" src="jui/themes/default/images/sysname.png" style="margin-left: 3px;">
                
                <ul class="nav">
                    <li style="background: none;color: blue;"><span>您好！${currentName}，欢迎使用市场质量管理系统。</span>今天是${currentDate}</li>
                    <li><a href="logout.do">退出</a></li>
                </ul>
                <%--
                <ul class="themeList" id="themeList">
                    <li theme="default"><div class="selected">蓝色</div></li>
                    <li theme="green"><div>绿色</div></li>
                    <!--<li theme="red"><div>红色</div></li>-->
                    <li theme="purple"><div>紫色</div></li>
                    <li theme="silver"><div>银色</div></li>
                    <li theme="azure"><div>天蓝</div></li>
                </ul>
                 --%>
            </div>

            <!-- navMenu -->
            <% if(!mesMenu.getNavMenu().isEmptyChild()){ %>
            <div id="navMenu">
                <ul style="padding-left: 200px" id="nav_ul">
                 <%=mesMenu.getNavMenuHtml() %>
                </ul>
            </div>      
            <%} %>  
        </div>
        <!-- 导航栏的开始 -->
        <div id="leftside">
            <div id="sidebar_s">
                <div class="collapse">
                    <div class="toggleCollapse"><div></div></div>
                </div>
            </div>
            <div id="sidebar">
                <div class="toggleCollapse">
                	<input type="text" size="16" placeholder="请输入要查找的菜单" onkeyup="filterMenu(this)" style="background-color: #e7eff0;border: none;margin-top: 4px;margin-left: 2px;font-weight: bold;"/>
                	<div>收缩</div>
                </div>

                <div class="accordion" fillSpace="sidebar" id="menuContainer">
                     <%=mesMenu.getAccordionHtml() %>
                </div>
            </div>
        </div>
        <!-- 导航栏的结束 -->
        
        <div id="container">
            <div id="navTab" class="tabsPage">
                <div class="tabsPageHeader">
                    <div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                        <ul class="navTab-tab">
                            <li tabid="main" class="main" style="border-left:1px solid #598F67"><a href="javascript:;"><span><span class="home_icon">主页</span></span></a></li>
                        </ul>
                    </div>
                    <div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                    <div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                    <div class="tabsMore">more</div>
                </div>
                <ul class="tabsMoreList">
                    <li><a href="javascript:;">主页</a></li>
                </ul>
                <div class="navTab-panel tabsPageContent layoutBox">
                 
                  <div class="page unitBox" style="overflow: auto;height: 100%">
	                    <p style="width: 200px;padding:1px;"></p> 
	                    <div id="alarmInfoDiv">
	                    
	                    </div>
                    </div>
                     
                </div>
            </div>
        </div>

    </div>
</body>
</html>