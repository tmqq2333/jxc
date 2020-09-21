<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
 <div>
    年份选择：<select id="yearselect">
                    <option value="2020">2020</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                </select>
    <div id="main" style="width: 600px;height:400px;"></div>
    </div>

    <script src="../js/jquery-1.11.0.js"></script>
    <script src="../js/echarts.min.js"></script>
    <script>
        var myChart = echarts.init(document.getElementById('main'));
        var  option = {
        	    title: {
        	        text: '原材料价格波动表'
        	    },
        	    tooltip: {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data: []
        	    },
        	    grid: {
        	        left: '3%',
        	        right: '4%',
        	        bottom: '3%',
        	        containLabel: true
        	    },
        	    toolbox: {
        	        feature: {
        	            saveAsImage: {}
        	        }
        	    },
        	    xAxis: {
        	        type: 'category',
        	        boundaryGap: false,
        	        data: ['一', '二', '三', '四', '五', '六', '周日']
        	    },
        	    yAxis: {
        	        type: 'value'
        	    },
        	    series: [
        	        {
        	            name: '邮件营销',
        	            type: 'line',
        	            stack: '总量',
        	            data: [120, 132, 101, 134, 90, 230, 210]
        	        },
        	        {
        	            name: '联盟广告',
        	            type: 'line',
        	            stack: '总量',
        	            data: [220, 182, 191, 234, 290, 330, 310]
        	        },
        	        {
        	            name: '视频广告',
        	            type: 'line',
        	            stack: '总量',
        	            data: [150, 232, 201, 154, 190, 330, 410]
        	        },
        	        {
        	            name: '直接访问',
        	            type: 'line',
        	            stack: '总量',
        	            data: [320, 332, 301, 334, 390, 330, 320]
        	        },
        	        {
        	            name: '搜索引擎',
        	            type: 'line',
        	            stack: '总量',
        	            data: [820, 932, 901, 934, 1290, 1330, 1320]
        	        }
        	    ]
        	};
        myChart.setOption(option);

        function getajaxdata(objyear) {
            $.ajax({
                type: "post",
                url: "../ajax",
                data: { rnum:9, cyear: objyear },
                timeout: 5000,
                dataType: "json",
                async: true,//默认设置为true，所有请求均为异步请求
                //cache：true,//默认为true（当dataType为script时，默认为false）设置为false将不会从浏览器缓存中加载请求信息。
                success: function (data) {
           
             
                 var optionhasvalue = {
                		 legend: {
                 	        data: data.data1
                 	    },
                		    xAxis: {
                		        type: 'category',
                		        data:data.data1
                		    },
                		    yAxis: {
                		        type: 'value',
                		        data:data.data2,
                		    },
                		    series: [{
                		    	 data:data.data2,
                		        type: 'bar'
                		    }]	
                  };
                 myChart.setOption(optionhasvalue);
                },
                error:function(){
				       console.log("500")
			    }, 
            
            });
            }
        getajaxdata(2020);
        $("#yearselect").change(function () {
            var v = $(this).val();
            getajaxdata(v);
        });
    </script>
</body>
</html>