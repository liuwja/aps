<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/include.inc.jsp" %>
<c:import url="../../../_frag/pager/pagerForm.jsp"></c:import>

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

    // 导出excel
    $("#exportExcel", navTab.getCurrentPanel()).on("click", function() {
        var url = "smt/exportExcelPFSAPANAMvData.do";
        var form = document.createElement("form");
        form.action = url;
        form.method = "post";
        form.target="noexistForm";
        document.body.appendChild(form);
        form.submit();
    });
</script>

<div class="pageHeader" style="position:static">
    <form onsubmit="return navTabSearch(this);" id="PFSAPANAMvDataForm" rel="pagerForm" action="smt/PFSAPANAMvData/list.do" method="post">
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
                            $("#lineName", navTab.getCurrentPanel()).val("${mv.lineName}");
                        </script>
                    </td>
                    <td>partNum：</td>
                    <td>
                        <input type="search" name="partNum" value="${mv.partNum}" size="25">
                    </td>
                    <td>slotNum：</td>
                    <td>
                        <input type="search" name="slotNum" value="${mv.slotNum}" size="5">
                    </td>
                    <td>stage：</td>
                    <td>
                        <input type="search" name="stage" value="${mv.stage}" size="3">
                    </td>
                    <td>
                        <div class="buttonActive">
                            <div class="buttonContent">
                                <button type="submit">查找</button>
                            </div>
                        </div>
                        <div class="buttonActive">
	                        <div class="buttonContent">
	                        	<button type="button" onclick="exportExcelByCommon('smt/exportExcelPFSAPANAMvData.do', '#PFSAPANAMvDataForm');">导出</button>
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
            <th>zoneName</th>
            <th>cellName</th>
            <th>lane</th>
            <th>stage</th>
            <th>zNum</th>
            <th>slotNum</th>
            <th>subSlotNum</th>
            <th>partNum</th>
            <th>lotNum</th>
            <th>vendor</th>
            <th>feeder</th>
            <th>partTimeOn</th>
        </tr>
        </thead>
        <tbody align="center" id="tbodyTab">
        <c:forEach items="${list}" var="o">
            <tr>
                <td>${o.lineName}</td>
                <td>${o.zoneName}</td>
                <td>${o.cellName}</td>
                <td>${o.lane}</td>
                <td>${o.stage}</td>
                <td>${o.zNum}</td>
                <td>${o.slotNum}</td>
                <td>${o.subSlotNum}</td>
                <td>${o.partNum}</td>
                <td>${o.lotNum}</td>
                <td>${o.vendor}</td>
                <td>${o.feeder}</td>
                <td>${o.partTimeOnStr}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:import url="../../../_frag/pager/panelBar.jsp"></c:import>
</div>
