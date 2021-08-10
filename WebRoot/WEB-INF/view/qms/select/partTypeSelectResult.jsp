<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<div  class="pageHeader">
待选列表
</div>
<div class="pageHeader">
    <form onsubmit="return  divSearch(this, 'toSelectPartTypeList')" id="pagerForm" action="qms/commonSelect/partTypeSelectResult.do" method="post">

    <div class="searchBar">
        <table class="searchContent">

	        <tr>
	                <td class="dateRange">
											机型类别:                     
					    <input type="hidden" name="direction" value="" />  	              
	                </td>
	                <td >
						<select name="productType" >
									<option value="">请选择</option>
									<c:forEach items="${productTypes}" var="o">
									<option value="${o.machineType}" <c:if test="${vo.productType eq o.machineType }">selected="selected"</c:if>>${o.machineType}</option>
									</c:forEach>
						</select>
	                </td>   
	                <td class="dateRange">
						产品系列:
	                </td>                                      
	                <td>
						<input type="text" name=productFamily id="productFamily" size="10" value="${vo.productFamily}"/>
	                </td> 
	          </tr>
	          <tr>
	                <td class="dateRange">
								型号:
	                </td>                                      
	                <td>
						<input type="text" name="partType" id="partType" size="10" value="${vo.partType}"/>
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
                    <th width="5%"><input type="checkbox" class="checkboxCtrl" group="id" ></th>
                    <th width="30%">机型类别</th>
                    <th width="30%">产品系列</th>
                    <th width="30%">产品型号</th>
                </tr>
            </thead>
            <tbody> 
                <c:forEach items="${list}" var="o">
                     <tr target="id" rel="${o.partType}" onclick="selectRow(this,'${o.partType}','${o.productType}','${o.productFamily}','${o.partType}')">
	                        <td style="text-align: center;">
	                            <input type="checkbox" name="chkbox" >
	                        </td>
	                        <td>${o.productType}</td>
	                        <td>${o.productFamily}</td>
	                        <td>${o.partType}</td>
	                    </tr>    
                </c:forEach>        
            </tbody>
        </table>
        
        <div class="panelBar">
		    <div class="pages">
		        <span>共${totalCount}条 </span>
		    </div>
		</div>    