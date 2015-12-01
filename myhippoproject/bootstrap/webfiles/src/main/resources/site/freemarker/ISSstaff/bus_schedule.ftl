<#include "../include/imports.ftl">
<!DOCTYPE html>
<html>
<head>
<title>LineString arrows example</title>
<script type="text/javascript" src="<@hst.webfile path="/js/jquery-latest.js"/>"></script>
<link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap.min.css"/>" >
<script type="text/javascript" src="<@hst.webfile path="/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="<@hst.webfile path="/css/ol.css"/>" >
<script src="https://cdnjs.cloudflare.com/ajax/libs/ol3/3.7.0/ol.js"></script>
<!--<script type="text/javascript" src="<@hst.webfile path="/js/ol.js"/>"></script>-->
<script type="text/javascript" src="<@hst.webfile path="/js/select2.min.js"/>"></script>
<link rel="stylesheet" href="<@hst.webfile path="/css/select2.min.css"/>" >
<@hst.headContributions categoryIncludes="htmlHead" xhtml=true/>
<style>
html {
    height: 100%;
    width: 100%;
}
#map {
    height: 90%;
    width: 100%;
}
  .ol-popup {
        position: absolute;
        background-color: white;
        -webkit-filter: drop-shadow(0 1px 4px rgba(0,0,0,0.2));
        filter: drop-shadow(0 1px 4px rgba(0,0,0,0.2));
        padding: 0px;
        border-radius: 10px;
        border: 1px solid #61C8D0;
        bottom: 12px;
        left: -50px;
        margin:0px;
		width: 180px;
      }
  .ol-popup:after, .ol-popup:before {
        top: 100%;
        border: solid transparent;
        content: " ";
        height: 0;
        width: 0;
        position: absolute;
        pointer-events: none;
      }
  .ol-popup:after {
        border-top-color: white;
        border-width: 10px;
        left: 48px;
        margin-left: -10px;
      }
  .ol-popup:before {
        border-top-color: #cccccc;
        border-width: 11px;
        left: 48px;
        margin-left: -11px;
      }
  .ol-popup-closer {
        text-decoration: none;
        /*position: absolute;
        top: 2px;
        right: 8px;*/
        display:inline-block;
        float:right;
        width:28px;
        height:26px;
      	background: url(<@hst.webfile path="/images/close.png"/>) no-repeat;
      	margin-top:4px;
      	margin-right:4px;
      }
  .ol-popup-closer:after {
      }
  #popup-content
      {
      	padding:15px 10px;
      }
  #popup-content p,#popup-content span
      {
      	font-family:"Times New Roman";
      	margin:0px;
      	color:#61C8D0;
      }
  #poptitle
      {
      	display:inline-block;
      	float:left;
      	line-height:36px;
      }
  #poptitle span
      {
      	  width:152px;
	      padding-left:15px;
	      font-family:"Times New Roman";
	      color:#FFFFFF;
      }
</style>
</head>
<body style="width:100%;height:100%;margin:0px;">
		<div style="min-height:50px;margin-left:20px;">Bus No.
			<select id="busNo" style='width:120px;'></select>
			<p id="busInfo"></p>
		</div>		
		<div id="map"><div id="popup" class="ol-popup">
			<div style="height:36px;width:180px;padding:0px;background-color:#61C8D0;line-height:36px;">
				<div id="poptitle"></div>
				<div id="popup-closer" class="ol-popup-closer"></div>
			</div>
	        <div id="popup-content"></div>
	    </div>
	    </div>
<script>
var container = document.getElementById('popup');
var poptitle = document.getElementById('poptitle');
var content = document.getElementById('popup-content');
var closer = document.getElementById('popup-closer');
/**
 * Add a click handler to hide the popup.
 * @return {boolean} Don't follow the href.
 */
closer.onclick = function() {
	overlay.setPosition(undefined);
	closer.blur();
	return false;
};
/**
 * Create an overlay to anchor the popup to the map.
 */
var overlay = new ol.Overlay(/** @type {olx.OverlayOptions} */ ({
	element: container,
	autoPan: true,
	autoPanAnimation: {
		duration: 250
	}
}));


var raster = new ol.layer.Tile({
  source: new ol.source.OSM()
});

var source = new ol.source.Vector();

var styleFunction = function(feature, resolution) {
  var styles = [
    // linestring
    new ol.style.Style({
      stroke: new ol.style.Stroke({
        color: '#333333',
        width: 2
      })
    })
  ];
  return styles;
};

var iconStyle = new ol.style.Style({
		image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
			anchor: [0.5, 32],
			anchorXUnits: 'fraction',
			anchorYUnits: 'pixels',
			opacity: 0.75,
			src: '../images/bus.png'			
		}))
	});
function addFeatureToLayer(param)
{
	if(!param)
		return;
	var fs=[];

	for(var i in param.features_param_list)
	{

		var tar=param.features_param_list[i];
		var textStroke = new ol.style.Stroke({
			color: 'rgba(0, 0, 0, 0.6)',
			width: 5
		});
		var style;
		if(tar.getStyle&&typeof tar.getStyle =='function')
		{
			style=tar.getStyle(i,param);
		}
		else
		{
			style = new ol.style.Style({
				image: new ol.style.Icon({
					anchor: [0.5, 32],
					anchorXUnits: 'fraction',
					anchorYUnits: 'pixels',
					opacity: 0.75,
					src: tar.icon		
				}),
				text:new ol.style.Text({
					text: tar.content,
					offsetY:0,
      				fill: new ol.style.Fill({
        				color: '#ffffff'
      				}),
      				scale:2,
      				stroke:textStroke
				})
			});
		}
		
		f = new ol.Feature({
				geometry: new ol.geom.Point(ol.proj.transform(tar.coordinate, 'EPSG:4326', 'EPSG:3857')),
				name: tar.title,
				overlay_content: tar.content
		});
		f.setStyle(style);
		fs.push(f);
	}
	var vectorSource=param.layer.getSource();
	if(!vectorSource)
	{
		vectorSource = new ol.source.Vector({
			features: fs
		});
		param.layer.setSource(vectorSource);
	}
	else
	{
		vectorSource.addFeatures(fs);
	}
	
}
//create map
var map = new ol.Map({
	overlays : [overlay],
	layers: [raster],
  	target: document.getElementById('map'),
  	view: new ol.View({
    	center: ol.proj.transform([103.8205, 1.3224], 'EPSG:4326', 'EPSG:3857'),
    	zoom: 12
  	})
});

//set popup
var element = document.getElementById('popup');

//add stations on map
var map_layer = {};
var json;
var initBusRoute;
$.ajax({
	url:"/site/springapp/busschedule/getBusSchedules",
	datatype: "json",
	//async: false,
	success: function(msg){
		json = JSON.parse(msg);			
		var option=$("<option id=\"bus_test\" selected value=\"\">Please Select</option>");				 				
		$("#busNo").append(option);	
		for(var i in json){
			map_layer[json[i].code] = new ol.layer.Vector();
			map.addLayer(map_layer[json[i].code]);
			var option=$("<option id=\"bus_"+json[i].code+"\" value=\""+i+"\">"+json[i].code+"</option>");
			$("#busNo").append(option);
											
			getBusStation(json[i].code);
		}	
		<#-- 由于请求station数据需要时间 ,故，这里需要延迟  
		//setTimeout(showBusRoute,3000);
		initBusRoute = setInterval(initRoute,1000);-->
		
	},
	error:function(XMLHttpRequest, textStatus){
		<#-- alert("The bus info is not available!"); -->
	}
});

function initRoute(){
	var isend = true ;
	<#-- 该环境下需所有线路加载完毕才能正常显示 -->
	for(var i in json){
		//alert(map_layer[json[i].code].hasData);
		if(!map_layer[json[i].code].hasData){
			isend = false;
			return;
		}
	}
	if(isend)
	{
		//showBusRoute();
		setTimeout(showBusRoute,1000);
		clearInterval(initBusRoute);
	}
}
	
function showBusRoute(){
	var sel_val=$("#busNo").children("option:selected").val();
	if(sel_val==null||sel_val.toString().length<=0)
		return ;
	var data = json[sel_val];
	var busStartTime = timeF(data.startTime);
	var busEndTime = timeF(data.endTime);
	$("#busInfo").html("First Bus at :"+busStartTime+"</br>Last Bus at :"+busEndTime);
	var j = data.code;
	if(map_layer[j].hasData){			 		
		if(map_layer[j].getVisible()){
			<#-- 如果使用下面语句，则会出现BUG，（点击一条线路，再点击 please select，再点击同一条线路，则该线路的图层会消失） -->
			<#-- map_layer[j].setVisible(false);-->
		}else{
			for( var m in map_layer){
				map_layer[m].setVisible(false);
			}
			map_layer[j].setVisible(true);
			var v=map.getView();
		   	if(v)
				v.setCenter(map_layer[j]._center);
		}
	}else{
		for( var m in map_layer){
				map_layer[m].setVisible(false);
			}
		<#--alert("No data available!");-->
	}
}

$("#busNo").change(function(){	
	setTimeout(showBusRoute,500);
});	

			
function getBusStation(code){
	$.ajax({
		url:"/site/springapp/busschedule/getBusStation?id="+code,
		datatype:"json",
		success:function(msg){
			var json = JSON.parse(msg);
			show(json,map_layer[code]);
			map_layer[code].setVisible(false);
			map_layer[code].hasData = true;
		},
		error:function(XMLHttpRequest, textStatus){
			//map_layer[code].hasData = false;
		}
	});
}			
		
function show(data,map_layer)
{		
	//console.info(map_layer);
	//map.addLayer(map_layer);
	//map_layer.setVisible(true);
	var features_param_list=[];
	for(var i in data)
	{
		var d=data[i];
		var d0=null;
		if(i>0)
			d0=data[i-1];
		if(i>0)
		{
			//parseFloat(d.lon), parseFloat(d.lat)
			//console.info(i);
			getLine({
				f:{
				  lon:parseFloat(d0.lon),
				  lat:parseFloat(d0.lat)
				  },
				 t:{
				  lon:parseFloat(d.lon),
				  lat:parseFloat(d.lat)
				 },
				 callBack:function(fs,data){
				 var center=getCenter4Extent(getFeaturesExtent(fs));
				 map_layer._center=center;
				  var vectorSource=map_layer.getSource();
				    for(var n in fs)
				    {
				    	fs[n].setStyle(new ol.style.Style({
					      stroke: new ol.style.Stroke({
					        color: '#333333',
					        width: 4
					      })
					    }));
				    }
				    if(!vectorSource)
				    {
				     vectorSource = new ol.source.Vector({
				      features: fs
				     });
				     map_layer.setSource(vectorSource);
				    }
				    else
				    {
				     vectorSource.addFeatures(fs);
				    }
				 }});
		}
		features_param_list.push({
			coordinate:[parseFloat(d.lon), parseFloat(d.lat)],
			title:d.stationName,
			//icon:"http://ol3js.org/en/master/examples/data/icon.png",
			code:d.code,
			data:d,
			getStyle:function(i,param)
			{
				//console.info(param);
				if(this.data.sortNum==1||this.data.sortNum==param.features_param_list.length)
				{
					return new ol.style.Style({
									
						image: new ol.style.Icon({
							anchor: [0.5, 32],
							anchorXUnits: 'fraction',
							anchorYUnits: 'pixels',
							opacity: 0.75,
							scale:0.9,
							src: "<@hst.webfile path="/images/bus.png"/>",
							stroke:new ol.style.Stroke({
	          					color: 'rgba(10, 0, 0, 0.6)',
	          					width: 5
	        				})
						})
									
					});
				}
				else
				return new ol.style.Style({
									
					image: new ol.style.Icon({
						anchor: [0.5, 32],
						anchorXUnits: 'fraction',
						anchorYUnits: 'pixels',
						opacity: 0.75,
						scale:0.8,
						src: "<@hst.webfile path="/images/bus.png"/>"	
					})/*,
					text:new ol.style.Text({
						text: this.title,
						offsetY:0,
          				fill: new ol.style.Fill({
            				color: "#0f0"
          				}),
          				scale:1,
          				stroke:new ol.style.Stroke({
          					color: 'rgba(10, 0, 0, 0.6)',
          					width: 5
        				})
					})*/
				});
			}
		});
		
	}
	
	addFeatureToLayer({
			layer:map_layer,
			features_param_list:features_param_list
		});
				
}

function timeF(time){
	var time = parseInt(time);
	var hour = time / 60;
	var min = time % 60;
	var m ;
	min<10 ? m = "0"+min: m = min;
	return hour + " : " + m;
}
// display popup on click
map.on('click',function(evt){
	var feature = map.forEachFeatureAtPixel(evt.pixel,
		function(feature, layer) {
			return feature;
	});
	if (feature) {
		var geometry = feature.getGeometry();
		var coord = geometry.getCoordinates();
		var coordinate = evt.coordinate;
		poptitle.innerHTML = feature.get('name');
		overlay.setPosition(coordinate);
	} else {
		$(closer).click();
	}		  
});
function getLine(param)
{
 var site = 'http://www.yournavigation.org/api/1.0/gosmore.php?format=kml'+
 '&flat='+param.f.lat+'&flon='+param.f.lon+'&tlat='+param.t.lat+'&tlon='+param.t.lon+'&v=motorcar&fast=1&layer=mapnik';
 var yql = 'http://query.yahooapis.com/v1/public/yql?q=' + encodeURIComponent('select * from xml where url="' + site + '"') + '&format=xml&callback=?';
 $.getJSON(yql, function (data) {
  //console.log(data.results[0]);
  var osmxmlFormat = new ol.format.OSMXML();
  var kml=new ol.format.KML({extractStyles:false,defaultStyle:null});
  var fs=kml.readFeatures(ol.xml.parse(data.results[0]),//ol.xml.parse(d),
   { 
     dataProjection:'EPSG:4326',
     featureProjection: 'EPSG:3857'
   });
  param.callBack(fs,data.results[0]);
 });
}
function getFeaturesExtent(features) {
  var bounds;
  if(features.constructor != Array) {
           features = [features];
      }
   for (var i = 0; i < features.length; i++) {
   var g=features[i].getGeometry();
   if(g)
   {
    if (!bounds) {
     bounds = g.getExtent();
    } else {
     bounds=g.getExtent(bounds);
    }
   }
            
        }
  return bounds;
    }
  function getCenter4Extent(extent) {     
 	return [(extent[0]+extent[2])/2,(extent[1]+extent[3])/2];
  }
</script>
<@hst.headContributions categoryIncludes="htmlBodyEnd" xhtml=true/>
</body>
</html>
