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
    $("#traceBtn", navTab.getCurrentPanel()).click(function() {
        var boardEntryTimeStart = $("#boardEntryTimeStart", navTab.getCurrentPanel()).val();
        var boardEntryTimeEnd = $("#boardEntryTimeEnd", navTab.getCurrentPanel()).val();

        if (boardEntryTimeStart == "" || boardEntryTimeEnd == "") {
            alertMsg.warn("请选择起始日期");
            return false;
        }
        if (!oneMonthTimeDiff(boardEntryTimeStart, boardEntryTimeEnd)) {
            return false;
        }
        $("#traceBtn", navTab.getCurrentPanel()).submit();
    });

    // 导出excel
    $("#exportExcel", navTab.getCurrentPanel()).on("click", function() {
        var url = "smt/exportExcelPFSAPANATraceData.do";
        var form = document.createElement("form");
        form.action = url;
        form.method = "post";
        form.target="noexistForm";
        document.body.appendChild(form);
        form.submit();
    });
</script>

<div class="pageHeader" style="position:static">
    <form onsubmit="return navTabSearch(this);" id="PFSAPANATraceDataForm" rel="pagerForm" action="smt/PFSAPANATraceData/list.do" method="post">
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
                            $("#lineName", navTab.getCurrentPanel()).val("${trace.lineName}");
                        </script>
                    </td>
                    <td>panelBarCode：</td>
                    <td>
                        <input type="search" name="panelBarCode" value="${trace.panelBarCode}" size="25">
                    </td>
                    <td>partNum：</td>
                    <td>
                        <input type="search" name="partNum" value="${trace.partNum}" size="20">
                    </td>
                </tr>
                <tr>
                    <td>vendor：</td>
                    <td>
                        <input type="search" name="vendor" value="${trace.vendor}" size="10">
                    </td>
                    <td>lotNum：</td>
                    <td>
                        <input type="search" name="lotNum" value="${trace.lotNum}" size="25">
                    </td>
                    <td>userData：</td>
                    <td>
                        <input type="search" name="userData" value="${trace.userData}" size="20">
                    </td>
                    <td>boardEntryTime：</td>
                    <td>
                        <input name="boardEntryTimeStart" id="boardEntryTimeStart" type="text" placeholder="请选择开始日期" size="15" readonly="readonly"
                               onclick="laydate({istime:true,format:'YYYY-MM-DD hh:mm:ss'})" value="${trace.boardEntryTimeStart }"/>
                        <span>至</span>
                        <input name="boardEntryTimeEnd" id="boardEntryTimeEnd" type="text" placeholder="请选择结束日期" size="15" readonly="readonly"
                               onclick="laydate({istime:true,format:'YYYY-MM-DD hh:mm:ss'})" value="${trace.boardEntryTimeEnd }"/>
                    </td>
                    <td>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="button" id="traceBtn">查找</button>
                            </div>
                        </div>
                        <div class="buttonActive">
	                        <div class="buttonContent">
	                        	<button type="button" onclick="exportExcelByCommon('smt/exportExcelPFSAPANATraceData.do', '#PFSAPANATraceDataForm');">导出</button>
	                        </div>
                        </div>
                        
                    </td>
                </tr>
            </table>
        </div>
    </form>
</div>
<div class="pageContent">
    <table class="table" width="110%" layoutH="115" >
        <thead>
            <tr>
                <th>lineName</th>
                <th>cellName</th>
                <th>pcbName</th>
                <th>panelBarCode</th>
                <th>side</th>
                <th>zNum</th>
                <th>slotNum</th>
                <th>subSlotNum</th>
                <th>partNum</th>
                <th>lotNum</th>
                <th>vendor</th>
                <th>feeder</th>
                <th>quantity</th>
                <th>boardEntryTime</th>
                <th>releaseTime</th>
                <th>mountOperator</th>
                <th>userData</th>
            </tr>
        </thead>
        <tbody align="center" id="tbodyTab">
            <c:forEach items="${list}" var="o">
                <tr>
                    <td>${o.lineName}</td>
                    <td>${o.cellName}</td>
                    <td>${o.pcbName}</td>
                    <td>${o.panelBarCode}</td>
                    <td>${o.side}</td>
                    <td>${o.zNum}</td>
                    <td>${o.slotNum}</td>
                    <td>${o.subSlotNum}</td>
                    <td>${o.partNum}</td>
                    <td>${o.lotNum}</td>
                    <td>${o.vendor}</td>
                    <td>${o.feeder}</td>
                    <td>${o.quantity}</td>
                    <td><fmt:formatDate value="${o.boardEntryTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${o.releaseTime}" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${o.mountOperator}</td>
                    <td>${o.userData}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
