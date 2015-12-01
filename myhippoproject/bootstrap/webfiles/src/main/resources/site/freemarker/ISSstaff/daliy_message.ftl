<#include "../include/imports.ftl">

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Swiper demo</title>
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
<!-- Link Swiper's CSS -->
<link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap.min.css"/>" >
<link rel="stylesheet" href="<@hst.webfile path="/css/swiper.min.css"/>" >
<script type="text/javascript" src="<@hst.webfile path="/js/jquery-latest.js"/>"></script>
<script type="text/javascript" src="<@hst.webfile path="/js/jquery.fakecrop.js"/>"></script>
<script type="text/javascript" src="<@hst.webfile path="/js/bootstrap.min.js"/>"></script>
<!-- Demo styles -->
    <style type="text/css">
    body {
        background: #fff;
        font-family: Helvetica Neue, Helvetica, Arial, sans-serif;
        font-size: 14px;
        color:#000;
        margin: 0;
        padding: 0;
    }
    .swiper-container {
        width: 100%;
        padding-top: 50px;
        padding-bottom: 50px;
    }
    .swiper-slide {
        background-position: center;
        background-size: cover;
        width: 300px;
        height: 300px;
    }
    #show
    {
    	width: 100%;
    	height: 70%;
    }
    #imgdiv img
    {
    	height:100%;
    }
    .container
    {
    	width: 80%;
    	margin: 0 auto;
    	height: 300px;
    	padding:0px;
    }
    .row
    {
    	margin:0px;
    }
    .col-xs-12,.col-sm-6,.col-md-6
    {
    	padding:0px;
    }
    @media (max-width: 768px)
    {
    	#textview
    	{
    		height:100px;
    	}
    	#imgdiv
    	{
    		height:150px;
    	}
    	.swiper-container
    	{
    		padding-top:0px;
    	}
    	.swiper-slide
    	{
    		height:180px;
    		width:200px;
    	}
    }
    @media (min-width: 769px)
    {
    	#imgdiv
    	{
    		height:100%;
    	}
    	#textview
    	{
    		height:100%;
    	}
    	.swiper-container
    	{
    		padding-top:50px;
    	}
    	.swiper-slide
    	{
    		height:300px;
    		width:300px;
    	}
    }
    </style>
    <script type="text/javascript">
    	//var datas = [{"id":1,"desc":"It is the first day of the Gregorian calendar.","start":1420041600000,"name":"New Year\u0027s Day","date":1420041600000,"end":1420041600000},{"id":2,"desc":"It is the first day of the lunar calendar.","start":1424275200000,"name":"Chinese New Year","date":1424275200000,"end":1424534400000},{"id":3,"desc":"Also called Passion Sunday, it commemorates Jesus entering Jerusalem for his anointment as King.","start":1427990400000,"name":"Good Friday","date":1427990400000,"end":1428163200000},{"id":4,"desc":"International Labour Day.","start":1430409600000,"name":"Labour Day","date":1430409600000,"end":1430582400000},{"id":5,"desc":"I am pleased to send greetings on Vesak Day, which marks the birth, enlightenment and passing of the Buddha.","start":1432915200000,"name":"Vesak Day","date":1432915200000,"end":1433088000000},{"id":6,"desc":"The three-day Hari Raya (Hari Raya Haji), also called eid al-adha, is the world\u0027s muslims celebrate big festival.","start":1437062400000,"name":"Hari Raya Haji","date":1437062400000,"end":1437235200000},{"id":7,"desc":"This is to commemorate the 1965 Singapore independence day.","start":1438963200000,"name":"National Day","date":1438963200000,"end":1439136000000},{"id":8,"desc":"This is a public holiday.","start":1439136000000,"name":"Public Holiday","date":1439136000000,"end":1439136000000},{"id":9,"desc":"Today is the first day of Hari Raya Puasa.","start":1443024000000,"name":"Hari Raya Puasa","date":1443024000000,"end":1443024000000},{"id":10,"desc":"Today is Deepavali so there\u0027s a precious day off.","start":1447084800000,"name":"Deepavali","date":1447084800000,"end":1447084800000},{"id":11,"desc":"Christmas Day is the 25th of December, when Christmas is celebrated.","start":1450972800000,"name":"Christmas day","date":1450972800000,"end":1451145600000}];
    	
    </script>
</head>
<body>
<div class="container">
<div id="show" class="row">
	<!--<div id="imgdiv" style="width:50%;display: inline-block;float: left;height: 100%;"><img width="100%" height="100%" src="../img/swipertest/1.jpg"></div>
	<div id="textdiv" style="width:50%;display: inline-block;line-height: 300px;float: left;height: 100%;text-align: center;">
		<span style="width:100%;height:100%;">Do one thing at a time, and do well.</span>
	</div>-->
</div></div>
<!-- Swiper -->
    <div class="swiper-container">
        <div class="swiper-wrapper" id="message">
        </div>
        <!-- Add Pagination -->
        <div class="swiper-pagination"></div>
    </div>

    <!-- Swiper JS -->
    <script type="text/javascript" src="<@hst.webfile path="/js/swiper.min.js"/>"></script>

    <!-- Initialize Swiper -->
    <script type="text/javascript">
    $(function() {
    			Date.prototype.format =function(format)
				{
				var o = {
				"M+" : this.getMonth()+1, //month
				"d+" : this.getDate(), //day
				"h+" : this.getHours(), //hour
				"m+" : this.getMinutes(), //minute
				"s+" : this.getSeconds(), //second
				"q+" : Math.floor((this.getMonth()+3)/3), //quarter
				"S" : this.getMilliseconds() //millisecond
				}
				if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
				(this.getFullYear()+"").substr(4- RegExp.$1.length));
				for(var k in o)if(new RegExp("("+ k +")").test(format))
				format = format.replace(RegExp.$1,
				RegExp.$1.length==1? o[k] :
				("00"+ o[k]).substr((""+ o[k]).length));
				return format;
				}
    			
    			
                 $.ajax({
                 type:"GET",
                 url:"/site/springapp/dailymessage/getDailyMessageData",
                 data:{},
                 datatype: "json",//"xml", "html", "script", "json", "jsonp", "text".
                 beforeSend:function(){
                 },
                 success:function(data){
                    var jsonData=JSON.parse(data);
                    //alert(jsonData.length);
                     for(var i = 0;i < jsonData.length;i++)
                     {
                     	if(i == 0)
                     	{
                     		var base_url="<@hst.webfile path="/images/daliy_message/"/>";
                     		var str = "<div id=\"imgdiv\" class=\"col-xs-12 col-sm-6 col-md-6\" style=\"display: inline-block;float: left;\"><img width=\"100%\" height=\"100%\" src=\""+ base_url + "/" + jsonData[i].imagepath +"\"></div>"+
							"<div class=\"col-xs-12 col-sm-6 col-md-6\" id=\"textview\" style=\"display:inline-block;float:left;\"><div class=\"textdiv\" style=\"width:100%;display: table;float: left;height: 20%;text-align: left;\">"+
							"<span id=\"texttitle\" style=\"font-weight:700;vertical-align: middle;display:table-cell;text-align:center;font-size:18px;\">"+jsonData[i].title+"</span></div>"+
							"<div class=\"textdiv\" style=\"width:100%;display: table;float: left;height: 60%;text-align: center;\">"+
							"<span id=\"textcontent\" style=\"font-weight:700;vertical-align: middle;display:table-cell;\">"+jsonData[i].content+"</span></div>"+
							"<div class=\"textdiv\" style=\"width:100%;display: table;float: left;height: 20%;text-align: right;\">"+
							"<span id=\"texttime\" style=\"font-weight:700;vertical-align: middle;display:table-cell;\">"+new Date(parseInt(jsonData[i].time)).format('yyyy-MM-dd')+"</span></div></div>";
							$("#show").append(str);
                     	}
                        	$("#message").append(createMessage(jsonData[i]));
                     }
                     /*other*/
						var swiper = new Swiper('.swiper-container', {
					        pagination: '.swiper-pagination',
					        effect: 'coverflow',
					        grabCursor: true,
					        paginationClickable: true,
					        centeredSlides: true,
					        slidesPerView: 'auto',
					        coverflow: {
					            rotate: 50,
					            stretch: 0,
					            depth: 100,
					            modifier: 1,
					            slideShadows : true
					        }
					    });
					    // for a filled square thumbnail
					    $('.fakecrop img').fakecrop();
					    // for a fixed width/height
					    //$('img').fakecrop({fill: false});
					    swiper.on('transitionEnd',function(){
					    	var imgpath = $('.swiper-slide-active img').attr("src");
					    	var content = $('.swiper-slide-active .content').val();
					    	var time = $('.swiper-slide-active .time').val();
					    	var title = $('.swiper-slide-active .title').text();
					    	//alert(imgpath);
					    	//console.info(imgpath);
					    	$('#imgdiv img').attr("src",imgpath);
					    	$('#textcontent').text(content);
					    	$('#texttitle').text(title);
					    	$('#texttime').text(time);
					    });
                 },
                 complete: function(XMLHttpRequest, textStatus){
                 },
                 error: function(){
                     alert("error");
                 }         
              });
			
				
				
            });
            
            function createMessage(data)
            {
            var base_url="<@hst.webfile path="/images/daliy_message/"/>";
            var image;
            if(data.imagepath)
            {
            	image=data.imagepath;
            }
            else
            {
            	image="NA.jpg";
            }
            //var slidediv = "<div class=\"swiper-slide\"><div style=\"width:100%;height:700px;\">";
            //slidediv += "<div style=\"width:90%;height:40%;margin-left:5%;\">"+
            //			"<div style=\"float:left; display:inline; background:url("+ base_url + "/" + data.imagepath +") no-repeat;background-size: cover; width:60%; height:100%;\"></div>"+
			//		   "<div style=\"float:left;width:40%; height:100%;font-family:'Times New Roman';display:table;\"><span style=\"vertical-align:middle;display:table-cell;\">"+data.title+"</span><input class=\"content\" type=\"hidden\" value=\"" + data.content + "\"/></div></div>";
            return "<div class=\"swiper-slide\">"+
            "<div class=\"fakecrop\" style=\"width: 100%;height: 60%;\">"+
            "<img src=\""+ base_url + "/" + data.imagepath +"\"></div>"+
            "<div style=\"width: 100%;height: 40%;display:table;\">"+
            "<span class=\"title\" style=\"vertical-align: middle;display:table-cell;text-align:center;\">"+data.title+"</span>"+
            "<input class=\"content\" type=\"hidden\" value=\""+data.content+"\"/>"+
            "<input class=\"time\" type=\"hidden\" value=\""+new Date(parseInt(data.time)).format('yyyy-MM-dd')+"\"/>"+
            "</div></div>";
            }



	
    </script>
</body>
</html>