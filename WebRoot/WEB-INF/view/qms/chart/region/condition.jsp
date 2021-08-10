<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
     		<tr>
                <th>机型类别：</th>
                <td>
					<select name="productType" onchange="loadRegionCondition();">
							<option value="">请选择</option>
							<c:forEach items="${productTypes}" var="o">
							<option value="${o.machineType }">${o.machineType}</option>
							</c:forEach>
					</select>
    			</td>
    			<th>区域：</th>                       
                <td>
                	<div id="regionList" class="dropdownlist"></div>
                </td>
    			<th>X轴数量：</th>
                <td>
					<input type="text" size="10" name="xCount" id="xCount" value="${vo.xCount+1 }"/>
    			</td>  			
				<th>维修截至日期：</th>
                <td>
					<input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
    			</td> 			
				<td>
					<div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadRegionChart();">查找</button></div></div>
				</td>				
			</tr>
			<script type="text/javascript">
				$(function(){
	
					$('#regionList').dropdownlist({
					    id:'regionListTxt',
					    columns:3,
					    selectedtext:'',
					    listboxwidth:450,//下拉框宽
					    maxchecked:100,
					    checkbox:true,
					    listboxmaxheight:400,
					    width:120,
					    requiredvalue:[],
					    selected:[],
					    data:${jsonRegions},//数据，格式：{value:name}
					    onchange:function(text,value){
					    }
					});
					
				});
//-->
</script>            