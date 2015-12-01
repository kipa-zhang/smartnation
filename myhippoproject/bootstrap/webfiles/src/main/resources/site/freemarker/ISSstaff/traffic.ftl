<#include "../include/imports.ftl">
<!DOCTYPE html>
<html>
<head>
<title>Traffic</title>
<script type="text/javascript" src="<@hst.webfile path="/js/jquery-latest.js"/>"></script>
<link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap.min.css"/>" >
<script type="text/javascript" src="<@hst.webfile path="/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" href="<@hst.webfile path="/css/ol.css"/>" >
<!--<script src="https://cdnjs.cloudflare.com/ajax/libs/ol3/3.7.0/ol.js"></script>-->
<script type="text/javascript" src="<@hst.webfile path="/js/ol.js"/>"></script>
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
		//width: 180px;
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
      	  //width:152px;
	      padding-left:15px;
	      font-family:"Times New Roman";
	      color:#FFFFFF;
      }
 /* #traffic-image
  	{
  		height:100px;
  		width:150px;
  	}*/
</style>
</head>
<body style="width:100%;height:100%;margin:0px;">
		<div id="map"><div id="popup" class="ol-popup">
			<div style="height:36px;padding:0px;background-color:#61C8D0;line-height:36px;">
				<div id="poptitle"></div>
				<div id="popup-closer" class="ol-popup-closer"></div>
			</div>
	        <div id="popup-content"><img id="traffic-image"></div>
	    </div>
	    </div>
<script>
var container = document.getElementById('popup');
var poptitle = document.getElementById('poptitle');
var content = document.getElementById('popup-content');
var closer = document.getElementById('popup-closer');
var img = document.getElementById('traffic-image');
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

var iconStyle = new ol.style.Style({
		image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
			anchor: [0.5, 32],
			anchorXUnits: 'fraction',
			anchorYUnits: 'pixels',
			opacity: 0.75,
			src: '<@hst.webfile path="/images/camera.png"/>'			
		}))
	});
		
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

/*
//addHardCodeTrafficMonitor
var coordinates = [[103.7882, 1.3423], [103.7960862, 1.4296436], [103.7770862, 1.4096436], [103.7867862, 1.3996936]];

var features = new Array();
	var feature;
	for(var i in coordinates){
		feature = new ol.Feature({
				geometry: new ol.geom.Point(ol.proj.transform(coordinates[i], 'EPSG:4326', 'EPSG:3857'))
			});
		features[i] = feature;
	}


map.addLayer(new ol.layer.Vector({
	source:new ol.source.Vector({
			features: features
		})}
));*/

		
//add stations on map

	var json;
	//var map_layer_v = [];
	$.ajax({
		url:"/site/springapp/traffic/camera",
		datatype: "json",
		success: function(msg){
			json = JSON.parse(msg);
			var fs = new Array();
			var feature, i;
			for(i in json){
			//console.log(json[i].lon+";"+ typeof(json[i].lat)+";"+json[i].lat+";"+ ol.proj.transform([json[i].lon,json[i].lat], 'EPSG:4326', 'EPSG:3857'));
				feature = new ol.Feature({
					geometry : new ol.geom.Point(ol.proj.transform([parseFloat(json[i].lon), parseFloat(json[i].lat)], 'EPSG:4326', 'EPSG:3857')),
					cameraID : json[i].cameraID
				});
				feature.setStyle(iconStyle);
				fs.push(feature);
			}
			//console.log(fs[0].getGeometry().getCoordinates() +";"+ ol.proj.transform([103.87115,1.2953134], 'EPSG:4326','EPSG:3857'));
			map.addLayer(new ol.layer.Vector({
					source: new ol.source.Vector({
						features : fs
					})
				}))			
			//$("#busNo").select2();
			//show(map,json,map_main);
		},
		error:function(XMLHttpRequest, textStatus){
				alert("Data Error");
			}
		});
	
	


	// display popup on click
	map.on('click',function(evt){
	var feature = map.forEachFeatureAtPixel(evt.pixel,
	function(feature, layer) {
		return feature;
	});
	console.log(feature);
	if (feature) {
		var geometry = feature.getGeometry();
		var coord = geometry.getCoordinates();
		var coordinate = evt.coordinate;
		var cid = feature.get('cameraID');
		poptitle.innerHTML = cid;
		overlay.setPosition(coordinate);	
		img.src = "/site/traimgs/"+cid+".jpg";		
	} else {
		$(closer).click();
	}		  
});

</script>
<@hst.headContributions categoryIncludes="htmlBodyEnd" xhtml=true/>
</body>
</html>