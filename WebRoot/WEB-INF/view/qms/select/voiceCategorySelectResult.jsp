<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script>
jQuery(document).ready(function() {
	var firstLoad = "${firstLoad}";
	loadSelectedUsers(firstLoad);
});
</script>
<div  class="pageHeader">
待选列表
</div>
<div class="pageHeader">
	<form onsubmit="return divSearch(this, 'toSelectVocTypeList')" id="pagerForm" action="qms/commonSelect/vocCategorySelectResult.do" method="post">
    <div class="searchBar">
        <table class="searchContent">
	         <tr>
			                <td class="dateRange">VOC一级分类:</td>
			                <td>
								<select name="paterName">
									<option value="">请选择</option>
									<c:forEach items="${vocPaterList}" var="o">
										<option value="${o.name}" <c:if test="${vo.paterName eq o.name }">selected="selected"</c:if>>${o.name}</option>
									</c:forEach>
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
						<th width="3%"><input id="checkBoxAll" type="checkbox" onclick="selectAllRow()" class="checkboxCtrl" group="id" ></th>
						<th width="30%">VOC一级分类</th>
						<th width="40%">VOC二级分类</th>
					</tr>
            </thead>
            <tbody> 
                 <c:forEach items="${list}" var="o">
	                    <tr target="id" rel="${o.number}" onclick="selectRow(this,'${o.number}','${o.name}','${o.paterName}')">
	                        <td style="text-align: center;">
	                            <input id="${o.number}" type="checkbox" name="chkbox" value="${o.number},${o.name},${o.paterName}" />
	                        </td>
	                        <td>${o.paterName}</td>
	                        <td>${o.name}</td>
	                    </tr>       
	                </c:forEach>
            </tbody>
        </table>
   