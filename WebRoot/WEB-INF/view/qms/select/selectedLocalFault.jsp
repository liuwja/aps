<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
                <c:forEach items="${list}" var="o">
                    <tr target="id" rel="${o.id}" onclick="selectRow(this,'${o.id}','${o.location}','${o.locationCode}')">
                        <td style="text-align: center;">
                            <input type="checkbox" name="chkbox" >
                        </td>
                        <td>${o.locationCode}</td>
                        <td>${o.location}</td>
                        <td>${o.province}</td>
                    </tr>       
                </c:forEach>                   
