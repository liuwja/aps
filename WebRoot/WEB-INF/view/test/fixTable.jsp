<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<html>
<head>
    <!--[if lt IE 9]>
    <script src="js/vendor/html5shiv.js"></script>
    <![endif]-->
<style type="text/css">
  #fixTableBody  tr:nth-of-type(odd) { 
    background: #eee; 
}  
  
</style>
    <title>固定表头列,固定列</title>
</head>
<body >
 <select>
    <option>测试</option>
    <option>测试</option>
    <option>测试</option>
    <option>测试</option>
    <option>测试</option>
    <option>测试</option>
    <option>测试</option>
    <option>测试</option>
    <option>测试</option>
    <option>测试</option>
 </select>
 
 <table>
 <tr>
        <th>维修截至日期：</th>
                <td>
                    <input type="text" id="queryMonth" name="queryMonth" placeholder="请输入日期" onclick="laydate({isym:true, format: 'YYYY-MM'})" value="${vo.queryMonth }" readonly="readonly"/>
                </td>           
                <td>
                    <div class="buttonActive"><div class="buttonContent"><button type="button" onclick="loadPartTypeAnalysisChart();">查找</button></div></div>
                </td>               
            </tr>
 </table>
<table id="fixTable" class="fixTable" width="1000">
        <thead>
                    <tr>
              <th rowspan=2>Month</th>
              <th rowspan=2>Savings1</th>
              <th rowspan=2>Savings2</th>
              <th rowspan=2>Savings3</th>
              <th rowspan=2>Savings4</th>
              <th rowspan=2>Savings5</th>
              <th colspan=2>ttest</th>
            </tr>
            <tr>
              <th>Savings6</th>
              <th>Savings7</th>
            </tr>
        </thead>
        <tbody id="fixTableBody">
            <tr>
              <td>January1</td>
              <td>$100</td>
              <td>January</td>
              <td>$100</td>
              <td>January</td>
              <td>$100</td>
              <td>January</td>
              <td>$100</td>
            </tr>
            <tr>
              <td>February2</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>January</td>
              <td>$100</td>
            </tr>
            <tr>
              <td>February3</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>January</td>
              <td>$100</td>
            </tr>
            <tr>
              <td>February4</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>January</td>
              <td>$100</td>
            </tr>
            <tr>
              <td>February5</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>January</td>
              <td>$100</td>
            </tr>
            <tr>
              <td>February6</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>January</td>
              <td>$100</td>
            </tr>
            <tr>
              <td>February7</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>January</td>
              <td>$100</td>
            </tr>
            <tr>
              <td>February8</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>January</td>
              <td>$100</td>
            </tr>
            <tr>
              <td>February9</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>February</td>
              <td>$80</td>
              <td>January</td>
              <td>$100</td>
            </tr>
        </tbody>
    </table>


<script>
$(document).ready(function(){
        $("#fixTable").fixTable({
            fixColumn: 3,//固定列数
            width:"100%",//显示宽度
            height:"92%"//显示高度
        });
});
</script>
</body>
</html>


