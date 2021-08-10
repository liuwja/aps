<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div  class="pageHeader">
待选列表
</div>
<div class="pageHeader">
    <form onsubmit="return  divSearch(this, 'tofindTypeSelectResult')" id="pagerForm" action="qms/commonSelect/findTypeSelectResult.do" method="post">
    <div class="searchBar">
        <table class="searchContent">

	        <tr>
			        		<td class="dateRange">
								 服务中心代码:
			                </td>                                      
			                <td>
								<input type="text" name="locationCode" id="code" size="10" value="${mo.locationCode}"/>
			                </td> 
			        		
			                <td class="dateRange">
			                	 服务中心名称:                       
			                </td>
			                <td >
								<input type="text" name="location" id="name" size="10" value="${mo.location}"/>
			                </td>   
			            </tr>
			                <tr>
			                <td class="dateRange">
			               		所属省份:  
			                </td>                                      
			                <td>
								<input type="text" name="province" id="name" size="10" value="${mo.province}"/>
							</select>
			                </td> 
			                <td>
			                	<div class="buttonActive"><div class="buttonContent"><button type="submit">查找</button></div></div>               
			                </td>  
			        </tr>
        </table>
    </div>
    </form>
</div>

        <table class="table" width="100%" layoutH="170">
	            <thead>
	                <tr>
	                	<th width="3%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
	                    <th width="40%">服务中心代码</th>
	                    <th width="30%">服务中心名称</th>
	                    <th width="30%">所属省份</th>
	                </tr>
	            </thead>
	            <tbody> 
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
	            </tbody>
	        </table>
        
    <c:import url="../../_frag/pager/panelBar.jsp">
       <%-- 对话框分页时需要传此参数 --%>
       <c:param name="targetName" value="dialog"/>
       <c:param name="relDivId" value="tofindTypeSelectResult"/>    
       
    </c:import>   
   