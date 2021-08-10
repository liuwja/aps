<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%><%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
//图表属性
    var options = {
        stacked: false,
        gutter:20,
        axis: "0 0 1 1", // Where to put the labels (trbl)
        axisystep: 10 // How many x interval labels to render (axisystep does the same for the y axis)
    };
    var r ;
    $(function() {
        // Creates canvas
        r = Raphael("chartHolder");
        
        //标题
        title = "October Browser Statistics";
        //标题位置left
        titleXpos = 290;
        //标题位置top
        titleYpos = 85;
        r.text(titleXpos, titleYpos, title).attr({"font-size": 20});
        
        var data = [[10,20,30,500],[15,25,35,50]]
        
        // stacked: false
        //例子1：多序列柱状图
        
        //barchart(left, top, width, height, values, opts) left:左边距,y:上边距,width:图表宽度,height:图表高度
        var chart1 = r.barchart(50, 100, 480, 220, data, options).hover(function() {
        	//鼠标移动tip提示
            this.flag = r.popup(this.bar.x, this.bar.y, this.bar.value).insertBefore(this);
        }, function() {
        	//鼠标移开事件
            this.flag.animate({opacity: 0}, 500, ">", function () {this.remove();});
        });
        //柱状标签
        //function (labels, isBottom)
        chart1.label([["A1",  "A2", "A3", "A4"],["B1",  "B2", "B3", "B4"]],true);
        
        //例子2：柱状图（注意每一根柱状的颜色都不相同）
        drawTestChart();
        
        //例子3：每根柱状的颜色都相同
        var mydata1 = [[10,20,30,500]];
        var rr = Raphael("chartHolder2");
        var mychart1 = rr.barchart(50, 10, 480, 200, mydata1, options).hover(function() {
            this.flag = rr.popup(this.bar.x, this.bar.y, this.bar.value).insertBefore(this);
        }, function() {
            this.flag.animate({opacity: 0}, 500, ">", function () {this.remove();});
        });
        mychart1.label([["A1",  "A2", "A3", "A4"]],true);
        
        // stacked: true
        //例子4：叠加柱状图
        options.stacked=true;
        var chart2 = rr.barchart(580, 10, 320, 200, data, options).hoverColumn(function() {
            var y = [], res = [];
            for (var i = this.bars.length; i--;) {
                y.push(this.bars[i].y);
                res.push(this.bars[i].value || "0");
            } 
            this.flag = r.popup(this.bars[0].x, Math.min.apply(Math, y), res.join(", ")).insertBefore(this);
        }, function() {
            this.flag.animate({opacity: 0}, 500, ">", function () {this.remove();});
        });
        chart2.label([["A"],["B"],["C"],["D"]],true);
        
    });
        function drawTestChart()
        {
        r.text(680, 20, "测试标题").attr({"font-size": 20});
        var mydata = [10,20,30,500];
        var mychart = r.barchart(580, 40, 320, 220, mydata, options).hover(function() {
            this.flag = r.popup(this.bar.x, this.bar.y, this.bar.value).insertBefore(this);
        }, function() {
            this.flag.animate({opacity: 0}, 100, ">", function () {this.remove();});
        });
        mychart.label(["组装产线1",  "组装产线2", "组装产线3", "组装产线4"],true);
        mychart.click ( function (){alert(this.bar.x)
        });
        }
        
        function drawMyChart()
        {
        r.text(680, 20, "测试标题").attr({"font-size": 20});
        var mydata = [15,129,267,500];
        var mychart = r.barchart(580, 40, 320, 220, mydata, options).hover(function() {
            this.flag = r.popup(this.bar.x, this.bar.y, this.bar.value).insertBefore(this);
        }, function() {
            this.flag.animate({opacity: 0}, 100, ">", function () {this.remove();});
        });
        mychart.label(["组装产线1",  "组装产线2", "组装产线3", "组装产线4"],true);
        mychart.click ( function (){alert(this.bar.x)
        });
        }
</script>
<input type="button" value="画图" onclick="drawMyChart()"/>
<div id="chartHolder"></div>
<div id="chartHolder2"></div>