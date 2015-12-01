<#include "../include/imports.ftl">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script type="text/javascript" src="<@hst.webfile path="/js/jquery-2.1.0.min.js"/>"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCme48xO3PVoIdT7y5VKi_vCB7utmVQWiU&callback=initMap&language=en"
        async defer></script>
	<!-- builds fullcalendar example -->
	<style>
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
		width:75%;
		float: left;
      }
	  .wrapper{
		height:100%;
		width:100%;
	  }
	  #camera{
		float: left;
		width:25%;
		height:100%;
	  }
	  #content a{
	  	text-decoration:none;
	  	text-align:center;
	  	font-family:"Times New Roman",Georgia,Serif;
	  }
	  a.title { 
				color:#D3D3D4; 
				background-color:rgba(0,0,0,0.6); 
				filter:progid:DXImageTransform.Microsoft.Gradient(GradientType=0, StartColorStr='#A0000000', EndColorStr='#A0000000');
				padding: 0 0 0 0; 
				position: absolute; 
				bottom: 0.2em; 
				right: 0.2em;
				width: auto; 
				text-decoration: none; 
				font: 16px Microsoft YaHei, Verdana; 
				color:#fff;
			}
		.firstHeading{
			text-align:center;
			font-family:"Times New Roman",Georgia,Serif;
			font-size: 0.8em;
		}
    </style>
	<script>
			var road="Braddell Flyover,Pan-Island Expressway,Siglap Park Connector";
			$(document).ready(function(){
				updateImgs();

				setInterval("updateImgs()",40000);
				setInterval("changeImg()",10000);
			})
			function updateImgs(){
				$.ajax({
					url:"/site/springapp/traffic/cameras?roads="+road,
					success: function(msg){
						json = JSON.parse(msg);
						$("#img1").attr("src","/site/traimgs/"+json[0].cameraID+".jpg");
						$("#msg1").html(json[0].road);
						$("#img2").attr("src","/site/traimgs/"+json[1].cameraID+".jpg");
						$("#msg2").html(json[1].road);
						$("#img3").attr("src","/site/traimgs/"+json[2].cameraID+".jpg");
						$("#msg3").html(json[2].road);
						$("#img4").attr("src","/site/traimgs/"+json[3].cameraID+".jpg");
						$("#msg4").html(json[3].road);
						
					},
					error:function(XMLHttpRequest, textStatus){
						/*alert("Can not connect server normally.");*/
					}
				});
			}

			function changeImg(){
				
				var cameraID = $("#img1").attr("cameraid");
				if(cameraID != 0){
					if(cameraID == json[json.length-1].cameraID){
						changeImgAction(0);
						return;
					}
					for(i in json){
						if(cameraID == json[i].cameraID){
							changeImgAction(Number(1)+Number(i));
							return;
						}
					}
				}else{
					changeImgAction(0);
				}
			}
			function changeImgAction(i){
				if(json.length - i < 4 && json.length - i > 0){
					$("#img1").attr("cameraid",json[json.length-1].cameraID);
					for(var k = 0;k<4;k++){
						$("#img"+(1+k)).attr("src",'<@hst.webfile path="/images/traffic/default.jpg"/>');
						$("#msg"+(1+k)).html("No Infomation");
					}
					for(var j = 0;j< json.length - i;j++){
						var imgurl = "/site/traimgs/"+json[i+j].cameraID+".jpg";
						$("#img"+(1+j)).attr("src",imgurl);
						$("#msg"+(1+j)).html(json[i+j].road);
					}
				}else{
					$("#img1").attr("cameraid",json[i+3].cameraID);
					for(var j = 0;j< 4;j++){
						var imgurl = "/site/traimgs/"+json[i+j].cameraID+".jpg";
						$("#img"+(1+j)).attr("src",imgurl);
						$("#msg"+(1+j)).html(json[i+j].road);
					}
				}
			}
			function ifImgExist(imgUrl){
				$.ajax({
					url: imgUrl,
					error: function(jqXHR, textStatus, errorThrown) {
						if(jqXHR.status == 404) {
							alert("自定义404错误提示信息");
						}
					}
				});
			}
			
		</script>
		
</head>
<body>
	<div class="wrapper">
		<div id="map"></div>
		<div id="camera">
			<div style=" position:relative;height:25% " >
				<img id="img1" cameraId="0" src='<@hst.webfile path="/images/traffic/default.jpg"/>' style="width:100%;" height="100%" />
				<a id="msg1" class="title" href="#">No Infomation</a>
			</div>
			<div style=" position:relative;height:25% ">
				<img id="img2" src='<@hst.webfile path="/images/traffic/default.jpg"/>' style="width:100%;" height="100%"/>
				<a id="msg2" class="title" href="#">No Infomation</a>
			</div>
			<div style=" position:relative;height:25% ">
				<img id="img3" src='<@hst.webfile path="/images/traffic/default.jpg"/>' style="width:100%;" height="100%"/>
				<a id="msg3" class="title" href="#">No Infomation</a>
			</div>
			<div style=" position:relative;height:24.9% ">
				<img id="img4" src='<@hst.webfile path="/images/traffic/default.jpg"/>' style="width:100%;" height="100%"/>
				<a id="msg4" class="title" href="#">No Infomation</a>
			</div>	
		</div>
	</div>	
    <script>

var map;
var markers = new Array();
var f_markers=[];
var infowindows=[];
//create google Map
function initMap(){
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: 1.3423, lng: 103.8082},
    zoom: 12,
	//mapTypeId: google.maps.MapTypeId.TERRAIN
  });


  
 // var ctaLayer = new google.maps.KmlLayer({
 //   url: 'https://sites.google.com/site/testmapzwh/home/map4test/MP14_REGION_WEB_PL.kml',
 //   map: map
 // });
  
  setInterval(getTraffic(),120000);
  setInterval(getLocation(),120000);
  var tag_i = 0;
  setInterval(function(){
  		infowindows[tag_i%infowindows.length].open(map,f_markers[tag_i%infowindows.length]);
  		if(tag_i>0)
  			infowindows[(tag_i-1)%infowindows.length].close();
  		tag_i++;
  },5000);
}
//get the traffic info and put on the map
function getTraffic(){
	$.ajax({
		url: "/site/springapp/traffic/incident",
		datatype: "json",
		success: function(msg){
			var json = JSON.parse(msg);
			var pic,location;
			for(var i in json){
				pic = icons(json[i].Type);
				location = {lng: json[i].Longitude, lat: json[i].Latitude};
				addMarker(location, pic);
			}
			setAllMarkers(map);
		}
	});
	
	
 }
 
	//get location weather
	function getLocation(){
	var dataset = "psi_update";
    var keyref = "781CF461BB6606AD28A78E343E0E4176A7B13D66DE21964C";
	$.ajax({
		type:"POST",
		url:"/site/springapp/user/searchWeather",
		dataType:"xml",
		data:{dataset:dataset,keyref:keyref},
		success:function(data){
			var place = ["North Region","National Reporting Stations","Central Region","East Region","West Region","South Region"];
			for(var i = 0;i < $("region",data).size();i++)
			{
				var pic,contentString;
				if($("longitude",$("region",data).get(i)).text() == "0" && $("latitude",$("region",data).get(i)).text() == "0")
					continue;
				var location = {lng: parseFloat($("longitude",$("region",data).get(i)).text()), lat: parseFloat($("latitude",$("region",data).get(i)).text())};
				//addMarker1(location, pic);
				//console.info($("reading",$("region",data)));
				var NPSI_CO,NPSI_O3,NPSI_PM10,NPSI_PM25,NPSI_SO2,total_weather;
				for(var j = 0;j < $("reading",$("region",data).get(i)).size();j++)
				{
					var type = $("reading",$("region",data).get(i)).get(j).attributes[0].value;
					var typeval = $("reading",$("region",data).get(i)).get(j).attributes[1].value;
					switch(type)
					{
						case "NPSI_CO":
							NPSI_CO = typeval;
							break;
						case "NPSI_O3":
							NPSI_O3 = typeval;
							break;
						case "NPSI_PM10":
							NPSI_PM10 = typeval;
							break;
						case "NPSI_PM25":
							NPSI_PM25 = typeval;
							break;
						case "NPSI_SO2":
							NPSI_SO2 = typeval;
							break;
					}
				}
				total_weather = '<a><b>NPSI_CO: </b>' + NPSI_CO + '</a></br>'+
			      			'<a><b>NPSI_O3: </b>' + NPSI_O3 + '</a></br>'+
			     		    '<a><b>NPSI_PM10: </b>' + NPSI_PM10 + '</a></br>'+
			      			'<a><b>NPSI_PM25: </b>' + NPSI_PM25 + '</a></br>'+
			      			'<a><b>NPSI_SO2: </b>' + NPSI_SO2 + '</a>'
				contentString = '<div id="content" style="width:10em">'+
				      '<p id="firstHeading" class="firstHeading"><b>'+place[i]+'</b></p>'+
				      '<div>'+total_weather+
				      '</div>'+
				      '</div>';
				console.info(contentString);
				
				var infowindow = new google.maps.InfoWindow({
					content: contentString
				});
				var image = '<@hst.webfile path="/images/traffic/Wind-Wheel.png"/>';
				var marker = new google.maps.Marker({
				  	position: location,
				    map: map,
				    icon: image,
				    title: place[i]
			  	});
			  //console.info(marker);
			  f_markers.push(marker);
			  infowindows.push(infowindow);
				  marker.addListener('click', function(mk,iw) {
				  		return function(){
				    		iw.open(map, mk);
				  		};
				  }(marker,infowindow));
		 	}
	 		setAllMarkers(map);
		   }  
		});
 	}
 
 // add marker
 function addMarker(location, pic){
	if(!pic)
		pic = 'bus.png';
	var marker = new google.maps.Marker({
	position : location,
	map: map,
	icon: pic
	});
	markers.push(marker);
 }
 
 // add marker1
 function addMarker1(location, pic){
	if(!pic)
		pic = '<@hst.webfile path="/images/traffic/location.png"/>';
	var marker = new google.maps.Marker({
	position : location,
	map: map,
	icon: pic
	});
	markers.push(marker);
 }
 
 
 
 function setAllMarkers(map){
	for(var i in markers){
		markers[i].setMap(map);
	}
 }
 
 function clearMarkers(){
	setAllMarkers(null);
 }
 
 function deleteMarkers(){
	clearMarkers();
	markers = [];
 }
 
 function showMarkers(){
	setAllMarkers(map);
 }
 function icons(msg){
	var pic;
	switch(msg){
		case "Road Work":	
			pic = '<@hst.webfile path="/images/traffic/roadwork.png"/>';
			break;
		case "Vehicle Breakdown":
			pic = '<@hst.webfile path="/images/traffic/VehicleBreakdown.png"/>';
			break;
		case "Accident":
			pic = '<@hst.webfile path="/images/traffic/accident.png"/>';
			break;
		case "Heavy Traffic":
			pic = '<@hst.webfile path="/images/traffic/HeavyTraffic.png"/>';
			break;
		case "Diversion":
			pic = '<@hst.webfile path="/images/traffic/Diversion.png"/>';
			break;
		case "Misc":
			pic = '<@hst.webfile path="/images/traffic/Misc.png"/>';
			break;
		case "Obstacle":
			pic = '<@hst.webfile path="/images/traffic/Obstacle.png"/>';
			break;
		case "RoadBlock":
			pic = '<@hst.webfile path="/images/traffic/RoadBlock.png"/>';
			break;
		case "UnattendedVehicle":
			pic = '<@hst.webfile path="/images/traffic/UnattendedVehicle.png"/>';
			break;
		case "weather":
			pic = "weather.png";
			break;
		case "location_weather":
			pic = '<@hst.webfile path="/images/traffic/Wind-Wheel.png"/>';
			break;
		default:
			pic = "default";
		}
	return pic;
 }
 
</script>
</body>
</html>

