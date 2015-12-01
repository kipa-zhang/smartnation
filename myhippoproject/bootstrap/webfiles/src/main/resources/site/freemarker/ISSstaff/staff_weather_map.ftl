<#include "../include/imports.ftl">
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Weather</title>
	<script type="text/javascript" src="<@hst.webfile path="/js/jquery-2.1.0.min.js"/>"></script>
</head>
<body>
	<button onclick="searchWeather()">getWeather
	<script>
		function searchWeather(){
			var dataset = "psi_update";
			var keyref = "781CF461BB6606AD28A78E343E0E4176A7B13D66DE21964C";
			$.ajax({
	        		type:"POST",
	        		url:"/site/springapp/user/searchWeather",
	        		dataType:"xml",
	        		data:{dataset:dataset,keyref:keyref},
	        		success:function(data){
					for(var i = 0;i < $("region",data).size();i++)
					{
						console.info($("id",$("region",data).get(i)).text());
						console.info($("latitude",$("region",data).get(i)).text());
						console.info($("longitude",$("region",data).get(i)).text());
						//console.info("-------this is region " + j + "-------");
						//for(var i = 0;i < $("reading",$("region",data).get(j)).size();i++)
						//{
						//	console.info($("reading",$("region",data).get(j)).get(i).attributes[0].value + ":" +
						//	$("reading",$("region",data).get(j)).get(i).attributes[1].value);
						//}
					}
	        	}		
	    	});
		}
	</script>
</body>
</html>