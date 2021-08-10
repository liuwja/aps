<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

<script type="text/javascript" src="<%=basePath%>/resources/js/timeDiff.js"></script>
<script type="text/javascript">
    function doOnlyLineSelectBase() {
        var factory = $("#factory", navTab.getCurrentPanel()).val();
        var lineNameSelect = $("#lineName", navTab.getCurrentPanel());
        lineNameSelect.empty();
        lineNameSelect.append("<option value=''>请选择</option>");
        if (factory.indexOf("请选择") > -1) {
            return;
        }
        <c:forEach items="${lineMap}" var="map">
        if (factory === '${map.key}') {
            <c:forEach items="${map.value}" var="o">
            lineNameSelect.append("<option value='${o.productionLineNumber}'>${o.productionLineName}</option>");
            </c:forEach>
        }
        </c:forEach>
    }

    // 校验起始日期是否在一个月内
    $("#scrapBtn", navTab.getCurrentPanel()).click(function() {
        var startTimeStamp = $("#startTimeStamp", navTab.getCurrentPanel()).val();
        var endTimeStamp = $("#endTimeStamp", navTab.getCurrentPanel()).val();

        if (startTimeStamp == "" || endTimeStamp == "") {
            alertMsg.warn("请选择起始日期");
            return false;
        }
        if (!oneMonthTimeDiff(startTimeStamp, endTimeStamp)) {
            return false;
        }
        $("#scrapBtn", navTab.getCurrentPanel()).submit();
    });

    // 导出excel
    $("#exportExcel", navTab.getCurrentPanel()).on("click", function() {
        var url = "smt/exportExcelPFSAPANAScrapData.do";
        var form = document.createElement("form");
        form.action = url;
        form.method = "post";
        form.target="noexistForm";
        document.body.appendChild(form);
        form.submit();
    });
</script>

<div class="pageHeader" style="position:static">
    <form onsubmit="return navTabSearch(this);" id="PFSAPANAScrapDataForm" rel="pagerForm" action="smt/PFSAPANAScrapData/list.do" method="post">
        <div class="searchBar">
            <table class="searchContent">
                <tr>
                    <th>工厂：</th>
                    <td>
                        <select name="factory" id="factory" onchange="doOnlyLineSelectBase();">
                            <option value="">请选择</option>
                            <c:forEach items="${factorys}" var="o">
                                <option value="${o.factory }"
                                        <c:if test="${cvo.factory eq o.factory }">selected="selected"</c:if>>${o.factory}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>lineName:</td>
                    <td>
                        <select name="lineName" id="lineName">
                            <option value="">请选择</option>
                            <c:forEach items="${lineList}" var="o">
                                <option value="${o.productionLineNumber}"
                                        <c:if test="${cvo.productLineNumber eq o.productionLineNumber}">selected="selected"</c:if>
                                >${o.productionLineName}</option>
                            </c:forEach>
                        </select>
                        <script type="text/javascript">
                            $("#lineName", navTab.getCurrentPanel()).val("${scrap.lineName}");
                        </script>
                    </td>
                    <td>cellname：</td>
                    <td>
                        <select name="cellName" id="cellName">
                            <option value="">所有</option>
                            <option value="NPM-1">NPM-1</option>
                            <option value="NPM-2">NPM-2</option>
                        </select>
                        <script type="text/javascript">
                            $("#cellName").val("${scrap.cellName}");
                        </script>
                    </td>
                    <td>location：</td>
                    <td>
                        <select name="location" id="location">
                            <option value="">所有</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                        </select>
                        <script type="text/javascript">
                            $("#location").val("${scrap.location}");
                        </script>
                    </td>
                    <td>partNum：</td>
                    <td>
                        <input type="search" name="partNum" value="${scrap.partNum}"/>
                    </td>
                    <td>starttime：</td>
                    <td>
                        <input name="startTimeStamp" id="startTimeStamp" type="text" placeholder="请选择开始日期" size="15" readonly="readonly"
                               onclick="laydate({istime:true,format:'YYYY-MM-DD hh:mm:ss'})" value="${scrap.startTimeStamp }"/>
                        <span>至</span>
                        <input name="endTimeStamp" id="endTimeStamp" type="text" placeholder="请选择结束日期" size="15" readonly="readonly"
                               onclick="laydate({istime:true,format:'YYYY-MM-DD hh:mm:ss'})" value="${scrap.endTimeStamp }"/>
                    </td>
                    <td>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="button" id="scrapBtn">查找</button>
                            </div>
                        </div>
                        <div class="buttonActive">
	                        <div class="buttonContent">
	                        	<button type="button" onclick="exportExcelByCommon('smt/exportExcelPFSAPANAScrapData.do', '#PFSAPANAScrapDataForm');">导出</button>
	                        </div>
                        </div>
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<div class="pageContent">
    <table class="table" width="100%" layoutH="95" >
        <thead>
        <tr>
            <th>lineName</th>
            <th>cellName</th>
            <th>location</th>
            <th>partNum</th>
            <th>materialId</th>
            <th>pickupCount</th>
            <th>scrap</th>
            <th>starttime</th>
            <th>endtime</th>
        </tr>
        </thead>
        <tbody align="center" id="tbodyTab">
        <c:forEach items="${list}" var="o">
            <tr>
                <td>${o.lineName}</td>
                <td>${o.cellName}</td>
                <td>${o.location}</td>
                <td>${o.partNum}</td>
                <td>${o.materialId}</td>
                <td>${o.pickupCount}</td>
                <td>${o.scrap}</td>
                <td>${o.startTimeStamp}</td>
                <td>${o.endTimeStamp}</td>
            </tr>
        </c:forEach>
        <tr>
            <td colspan="5">总计：</td>
            <td>${totalPickupCount}</td>
            <td>${totalScrap}</td>
            <td colspan="2">抛料率：${throwMaterialRate}%</td>
        </tr>
        </tbody>
    </table>
    <c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
