<#include "../include/imports.ftl">
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Weather</title>
	<link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap.min.css"/>" >
	<link rel="stylesheet" href="<@hst.webfile path="/css/weather/weather.css"/>" >
	<link rel="stylesheet" href="<@hst.webfile path="/css/weather-icons.css"/>" >
	<script type="text/javascript" src="<@hst.webfile path="/js/jquery-2.1.0.min.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/bootstrap.min.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/weather/highcharts.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/weather/exporting.js"/>"></script>
	<!--<style>
		@media screen and (max-width: 768px) { /*当屏幕尺寸小于768px时，应用下面的CSS样式*/
		  #map{
		  	height:30em !important;
		  }
		  #traffic_pic{
		  	height:8em !important;
		  }
		  
		@media screen and (min-width: 768px) { /*当屏幕尺寸大于768px时，应用下面的CSS样式*/
		  #map{
		  	height:38em;
		  }
		  #traffic_pic{
		  	height:38em;
		  }
		#psi p{
			margin-bottom: 5px !important;
		}
	</style>-->
</head>

<body>
	<div style="margin-left:15px;margin-right:15px">
			<div id="weather_container" class="row" style="line-height:0.8;text-shadow: 0.2em 0.2em 0.2em #000000;padding-left: 0.5em; position: relative; ">
			  <img id="weather_back_icon" class="weather_back_icon" style="position: absolute;"/>
			  <div class="col-sm-2 col-xs-6" style="height: 12em; width:10% !important;padding-left:0em;padding-right:0em;">
	 			<a class="op_weather4_twoicon_day OP_LOG_LINK" href="#"  style="text-decoration:none;">
	 				  <p id="weather_NPSI_6" style="margin-top:1.5em;margin-bottom: 1.5em !important;"></p>
	                  <p id="weather_NPSI_0" style="margin-bottom: 5px !important;"></p>
	                  <p id="weather_NPSI_2" style="margin-bottom: 5px !important;"></p>
	                  <p id="weather_NPSI_3" style="margin-bottom: 5px !important;"></p>
	                  <p id="weather_NPSI_4" style="margin-bottom: 5px !important;"></p>
	                  <p id="weather_NPSI_5" style="margin-bottom: 5px !important;"></p>
	              </a>
			  </div>
			  <div id="weather_parent_0" class="col-sm-4 col-xs-12" style="height: 12em; width:30% !important;">
					<div class="op_weather4_twoicon_hover"></div>
					<div class="op_weather4_twoicon_split"></div>
            		
				  <a class="op_weather4_twoicon_today OP_LOG_LINK" href="#"  style="left:2em;text-decoration:none;">
	                
	                <p id="weather_date_0" class="op_weather4_twoicon_date">
	                   	 Date
	            	</p>    
	            <i id="weather_icon_0" class="font_weather_icon_size_big wi wi-yahoo-32"></i>
            		
	                <p id="weather_temp_0" class="op_weather4_twoicon_temp"></sup></p>
	                <p id="weather_forecast_0" class="op_weather4_twoicon_weath" title=""></p>
	                <!--<p id="weather_wind_0" class="op_weather4_twoicon_wind">Wind</p>-->
				 	
	              </a>
			  </div>
			  <div class="col-sm-2 col-xs-6" style="height: 12em; width:15% !important;">
	 			<a id="weather_parent_1" class="op_weather4_twoicon_day OP_LOG_LINK" href="#"  style="text-decoration:none;">
	                    <p id="weather_date_1" class="op_weather4_twoicon_date">
	                   		 Date
	                    </p>
	             <i id="weather_icon_1" class="font_weather_icon_size_small wi wi-yahoo-32"></i>

					<div class="op_weather4_twoicon_hover"></div>
            		<div class="op_weather4_twoicon_split"></div>

	                <p id="weather_temp_1" class="op_weather4_twoicon_temp"></sup></p>
	                <p id="weather_forecast_1" class="op_weather4_twoicon_weath" title=""></p>
	                <p id="weather_wind_1" class="op_weather4_twoicon_wind"></p>
				 	
	              </a>
			  </div>
			  <div class="col-sm-2 col-xs-6" style=" height: 12em; width:15% !important;">
				<a id="weather_parent_2" class="op_weather4_twoicon_day OP_LOG_LINK" href="#"   style="text-decoration:none;">
	                    <p id="weather_date_2" class="op_weather4_twoicon_date">
	                    		Date
	                    </p>
	            <i id="weather_icon_2" class="font_weather_icon_size_small wi wi-yahoo-32"></i>

					<div class="op_weather4_twoicon_hover"></div>
            		<div class="op_weather4_twoicon_split"></div>

	                <p id="weather_temp_2" class="op_weather4_twoicon_temp"></sup></p>
	                <p id="weather_forecast_2" class="op_weather4_twoicon_weath" title=""></p>
	                <p id="weather_wind_2" class="op_weather4_twoicon_wind"></p>
				 	
	              </a>
			  </div>
			  <div class="col-sm-2 col-xs-6" style=" height: 12em; width:15% !important;">
			  	<a id="weather_parent_3" class="op_weather4_twoicon_day OP_LOG_LINK" href="#"  style="text-decoration:none;" >
	                    <p id="weather_date_3" class="op_weather4_twoicon_date">
	                   		Date
	                    </p>
	            <i id="weather_icon_3" class="font_weather_icon_size_small wi wi-yahoo-32"></i>
					
					<div class="op_weather4_twoicon_hover"></div>
            		<div class="op_weather4_twoicon_split"></div>

	                <p id="weather_temp_3" class="op_weather4_twoicon_temp"></sup></p>
	                <p id="weather_forecast_3" class="op_weather4_twoicon_weath" title=""></p>
	                <p id="weather_wind_3" class="op_weather4_twoicon_wind"></p>
				 	
	              </a>
			  </div>
			  <div class="col-sm-2 col-xs-6" style=" height: 12em; width:15% !important;">
				<a id="weather_parent_4" class="op_weather4_twoicon_day OP_LOG_LINK" href="#"  style="text-decoration:none;" >
	                    <p id="weather_date_4" class="op_weather4_twoicon_date">
	                   		Date
	                    </p>
	            
						<i id="weather_icon_4" class="font_weather_icon_size_small wi wi-yahoo-32"></i>
					<div class="op_weather4_twoicon_hover"></div>
            		<div class="op_weather4_twoicon_split"></div>

	                <p id="weather_temp_4" class="op_weather4_twoicon_temp"></sup></p>
	                <p id="weather_forecast_4" class="op_weather4_twoicon_weath" title=""></p>
	                <p id="weather_wind_4" class="op_weather4_twoicon_wind"></p>
				 	
	              </a>
			  </div>
		</div>
		<div id="weather_description" class="row" style="height: 46em; background: #7AAFDE;">
				<#include "map.ftl">
				<!--<div id="map" class="col-sm-8 col-xs-12" style="height: 46em; background: #7AAFDE;">
					<b>Current Conditions:</b>
					<p id="Conditions">Mostly Cloudy, 88 F</p>
				</div>

				<div class="op_weather4_twoicon_hover"></div>
            	<div class="op_weather4_twoicon_split"></div>

				<div id="traffic_pic" class="col-sm-4 col-xs-12" style="height: 46em; background: #996666;">
					<b>Forecast:</b>
					<p id="five_day_weather"></p>
				</div>-->
		</div>
	</div>
	<script type="text/javascript">
	//var result={"query":{"count":1,"created":"2015-10-22T03:28:40Z","lang":"zh-cn","results":{"channel":{"title":"Yahoo! Weather - Singapore, SG","link":"http://us.rd.yahoo.com/dailynews/rss/weather/Singapore__SG/*http://weather.yahoo.com/forecast/SNXX0006_f.html","description":"Yahoo! Weather for Singapore, SG","language":"en-us","lastBuildDate":"Thu, 22 Oct 2015 11:00 am SGT","ttl":"60","location":{"city":"Singapore","country":"Singapore","region":""},"units":{"distance":"mi","pressure":"in","speed":"mph","temperature":"F"},"wind":{"chill":"88","direction":"150","speed":"7"},"atmosphere":{"humidity":"66","pressure":"29.85","rising":"2","visibility":"3.73"},"astronomy":{"sunrise":"6:46 am","sunset":"6:51 pm"},"image":{"title":"Yahoo! Weather","width":"142","height":"18","link":"http://weather.yahoo.com","url":"http://l.yimg.com/a/i/brand/purplelogo//uh/us/news-wea.gif"},"item":{"title":"Conditions for Singapore, SG at 11:00 am SGT","lat":"1.37","long":"103.83","link":"http://us.rd.yahoo.com/dailynews/rss/weather/Singapore__SG/*http://weather.yahoo.com/forecast/SNXX0006_f.html","pubDate":"Thu, 22 Oct 2015 11:00 am SGT","condition":{"code":"28","date":"Thu, 22 Oct 2015 11:00 am SGT","temp":"88","text":"Mostly Cloudy"},"description":"\n<img src=\"http://l.yimg.com/a/i/us/we/52/28.gif\"/><br />\n<b>Current Conditions:</b><br />\nMostly Cloudy, 88 F<BR />\n<BR /><b>Forecast:</b><BR />\nThu - Partly Cloudy. High: 94 Low: 79<br />\nFri - Partly Cloudy. High: 93 Low: 79<br />\nSat - Scattered Thunderstorms. High: 92 Low: 79<br />\nSun - Scattered Thunderstorms. High: 92 Low: 79<br />\nMon - PM Thunderstorms. High: 90 Low: 79<br />\n<br />\n<a href=\"http://us.rd.yahoo.com/dailynews/rss/weather/Singapore__SG/*http://weather.yahoo.com/forecast/SNXX0006_f.html\">Full Forecast at Yahoo! Weather</a><BR/><BR/>\n(provided by <a href=\"http://www.weather.com\" >The Weather Channel</a>)<br/>\n","forecast":[{"code":"30","date":"22 Oct 2015","day":"Thu","high":"94","low":"79","text":"Partly Cloudy"},{"code":"30","date":"23 Oct 2015","day":"Fri","high":"93","low":"79","text":"Partly Cloudy"},{"code":"38","date":"24 Oct 2015","day":"Sat","high":"92","low":"79","text":"Scattered Thunderstorms"},{"code":"38","date":"25 Oct 2015","day":"Sun","high":"92","low":"79","text":"Scattered Thunderstorms"},{"code":"38","date":"26 Oct 2015","day":"Mon","high":"90","low":"79","text":"PM Thunderstorms"}],"guid":{"isPermaLink":"false","content":"SNXX0006_2015_10_26_7_00_SGT"}}}}}};
	$('body').bind("get_forecast_event",function(event,param){
		$.ajax({
        		type:"GET",
        		url:"/site/springapp/weather/forecast/"+param.woeid,
        		success:function(data){
        				$('body').trigger("get_forecast_success_event",data);
        		},
        		error:function(){

        		}
        	});
        	
		var dataset = "psi_update";
	    var keyref = "781CF461BB6606AD28A78E343E0E4176A7B13D66DE21964C";
	    var typeval,total_PSI = 0,avg_PSI;
		$.ajax({
			type:"POST",
			url:"/site/springapp/user/searchWeather",
			dataType:"xml",
			data:{dataset:dataset,keyref:keyref},
			success:function(data){
				for(var i = 0;$("reading",$("region",data).get(i)).size();i++)
				{
					if($("longitude",$("region",data).get(i)).text() == "0" && $("latitude",$("region",data).get(i)).text() == "0")
					continue;
					
					typeval = $("reading",$("region",data).get(i)).get(0).attributes[1].value;
					//var weather_NPSI_0,weather_NPSI_2,weather_NPSI_3,weather_NPSI_4,weather_NPSI_5;
					console.info(parseInt(typeval));
					total_PSI += parseInt(typeval);
					switch(i)
					{
						case 0:
							console.info($("#weather_NPSI_" + i));
							$("#weather_NPSI_" + i).html("NO_PSI: "+typeval);
							break;
						case 2:
							$("#weather_NPSI_" + i).html("CE_PSI: "+typeval);
							break;
						case 3:
							$("#weather_NPSI_" + i).html("EA_PSI: "+typeval);
							break;
						case 4:
							$("#weather_NPSI_" + i).html("WE_PSI: "+typeval);
							break;
						case 5:
							$("#weather_NPSI_" + i).html("SO_PSI: "+typeval);
							break;
						
					}
					//$("#weather_NPSI_" + i).html(weather_NPSI_+i);
				}	
				avg_PSI = total_PSI/5;
				$("#weather_NPSI_6").html("PSI: "+avg_PSI);
				//console.info("---------"+total_PSI);
			  }	
			});
	});
	///刷新绑定
	$('body').bind("get_forecast_success_event",function(event,param){
		/**
		更新数据
		**/
		var s = "";
		var low,high;
		for(var i in param.query.results.channel.item.forecast)
		{
  			var base_url="<@hst.webfile path=""/>";
			var f=param.query.results.channel.item.forecast[i];
			low = Math.round((f.low-32)/1.8);
			high = Math.round((f.high-32)/1.8);
			$("#weather_date_"+i).html(f.date);
			$("#weather_temp_"+i).html(low+" <sup>&#176;</sup>C~"+high+" <sup>&#176;</sup>C");
			$("#weather_forecast_"+i).html(f.text);
			$("#weather_icon_"+i).removeClass("wi-yahoo-32").addClass("wi-yahoo-"+f.code);
			//$("#weather_back_icon_"+i).attr("src",base_url+"/images/weather_back/"+f.code+".png");
			var d = f.day+" - "+f.text+".High:"+f.high+" Low"+f.low+"<br/>";
			s += d;
			if(i==0)
			{
				change_weather_container_background(f,i);
				$("#weather_wind_"+i).html(param.query.results.channel.wind.speed+" "+param.query.results.channel.units.speed);
			    $("#weather_container").mouseleave(function(f0,i0){
					var targert=this;
					return function(event){
						if(event.delegateTarget==event.currentTarget)
							change_weather_container_background.apply(targert,[f0,i0]);
					};
				}(f,i));
			}
			
			$("#weather_parent_"+i).mouseenter(function(_f,_i){
				var targert=this;
				return function(event){
				console.info(event);
					if(event.delegateTarget==event.currentTarget)
					  change_weather_container_background.apply(targert,[_f,_i]);
				};
			}(f,i));
			
		}
		$("#five_day_weather").html(s);
	
	});
    $(function () {
		$('body').trigger("get_forecast_event",{woeid:"1062617"});
	});
	
	
	function change_weather_container_background(f,i)
	{
		var base_url="<@hst.webfile path=""/>";
		//$("#weather_container").animate({"background-image":"-webkit-linear-gradient(top, "+gloable_weather.colors[f.code].from+", "+gloable_weather.colors[f.code].to }, 300 );
		$("#weather_container").css("background-image","-webkit-linear-gradient(top, "+gloable_weather.colors[f.code].from+", "+gloable_weather.colors[f.code].to);
		//$("#weather_back_icon").attr("src",base_url+"/images/weather_back/"+f.code+".png");
		$("#weather_back_icon").attr("src",base_url+"/images/weather_back/"+f.code+".png");
		$("#weather_back_icon").css("left","15em");
		$("#weather_back_icon").css("top","0em");
		$("#weather_back_icon").css("height","12em");
	}
	
	
	
	var gloable_weather={
		colors:{
		1:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		2:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		3:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		4:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		5:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		6:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		7:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		8:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		9:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		10:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		11:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		12:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		13:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		14:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		15:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		16:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		17:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		18:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		19:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		20:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		21:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		22:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		23:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		24:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		25:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		26:{from:"rgb(72, 86, 99)",to:"rgb(161, 184, 202)"},
		27:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		28:{from:"rgb(13, 104, 188)",to:"rgb(114, 173, 224)"},
		29:{from:"rgb(72, 86, 99)",to:"rgb(161, 184, 202)"},
		30:{from:"rgb(13, 104, 188)",to:"rgb(114, 173, 224)"},
		31:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		32:{from:"rgb(13, 104, 188)",to:"rgb(114, 173, 224)"},
		33:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		34:{from:"rgb(13, 104, 188)",to:"rgb(114, 173, 224)"},
		35:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		36:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		37:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		38:{from:"rgb(72, 86, 99)",to:"rgb(161, 184, 202)"},
		39:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		40:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		41:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		42:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		43:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		44:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		45:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		46:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		47:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		48:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		49:{from:"rgb(24, 50, 89)",to:"rgb(52, 130, 186)"},
		}
	};
    </script>
</body>
</html>