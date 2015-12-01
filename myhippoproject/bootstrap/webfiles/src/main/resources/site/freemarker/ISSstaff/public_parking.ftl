<#include "../include/imports.ftl">
<!DOCTYPE html>
<html lang="en" style="width:100%;height:100%;">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<link rel="stylesheet" href="<@hst.webfile path="/css/ol.css"/>" >
		<style>
	      #map {
	        height: 100%;
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
		width: 240px;
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
	  	<!--<script type="text/javascript" src="<@hst.webfile path="/js/ol.js"/>"></script>-->
	    <script type="text/javascript" src="<@hst.webfile path="/js/jquery-2.1.0.min.js"/>"></script>
	    <script src="http://openlayers.org/en/v3.6.0/build/ol.js" type="text/javascript"></script>
		<@hst.headContributions categoryIncludes="htmlHead" xhtml=true/>
	</head>
	<body style="width:100%;height:100%;margin:0px;">
		<div id="map"><div id="popup" class="ol-popup">
			<div style="height:36px;width:240px;padding:0px;background-color:#61C8D0;line-height:36px;">
				<div id="poptitle"></div>
				<div id="popup-closer" class="ol-popup-closer"></div>
			</div>
	        <div id="popup-content"></div>
	    </div>
	    <script type="text/javascript">
		    var gloable_res={
		    	base_url:'http://localhost:8080',
		    	icons:{
		    		iconimage:'<@hst.webfile path="/images/69.jpg"/>'
		    	}
		    };
		    var container = document.getElementById('popup');
		    var poptitle = document.getElementById('poptitle');
			var content = document.getElementById('popup-content');
			var closer = document.getElementById('popup-closer');
			closer.onclick = function() {
				overlay.setPosition(undefined);
				closer.blur();
				return false;
			};
		    var target=[];
			var map_layer=new ol.layer.Vector();
			var overlay = new ol.Overlay(/** @type {olx.OverlayOptions} */ ({
				element: container,
				autoPan: true,
				autoPanAnimation: {
					duration: 250
				}
			}));
	    	function test(){
	    		$.ajax({
					type:"POST",
					url:"/site/springapp/parking/getParkingData",
					success: function(data){
						//alert(data);
						if(!data.length > 0)
							return;
						data=JSON.parse(data);
						target.features_param_list = [];
						for(var k in data)
						{
							var res = data[k];
							target.features_param_list.push({
								coordinate:[parseFloat(res.lon), parseFloat(res.lat)],
								title:res.name,
								icon:gloable_res.icons.iconimage,
								positions:res.positions,
								//curPositions:res.curPositions,
								lotsPositions:res.lotsPositions,
								getStyle:function(d){
									//console.info(d);
									var color;
									if(parseInt(this.content)<=50)
									{
										fill_color="#0f0";
										stroke_color="rgba(10, 0, 0, 0.6)";
									}
									else
									{
										fill_color="#fff";
										stroke_color="rgba(10, 0, 0, 0.6)";
									}
									return new ol.style.Style({
										
										image: new ol.style.Icon({
											anchor: [0.5, 32],
											anchorXUnits: 'fraction',
											anchorYUnits: 'pixels',
											opacity: 0.75,
											src: this.icon		
										}),
										text:new ol.style.Text({
											text: this.content,
											offsetY:0,
					          				fill: new ol.style.Fill({
					            				color: fill_color
					          				}),
					          				scale:2,
					          				stroke:new ol.style.Stroke({
					          					color: 'rgba(10, 0, 0, 0.6)',
					          					width: 5
					        				})
										})
									});
								},
							});
						}
						console.info(target.features_param_list);
						addFeatureToLayer({
							layer:map_layer,
							features_param_list:target.features_param_list
						});
					}
				});
	    	}
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
						style=tar.getStyle(param);
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
							//overlay_content: tar.content,
							positions:tar.positions,
							lotsPositions:tar.lotsPositions
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
	    	
			var iconFeature = new ol.Feature({
			  geometry: new ol.geom.Point([0, 0]),
			  name: 'Null Island',
			  population: 4000,
			  rainfall: 500
			});
			
			var iconStyle = new ol.style.Style({
			  image: new ol.style.Icon(/** @type {olx.style.IconOptions} */ ({
			    anchor: [0.5, 46],
			    anchorXUnits: 'fraction',
			    anchorYUnits: 'pixels',
			    opacity: 0.75,
			    src: '/images/icon.png'
			  }))
			});
			
			iconFeature.setStyle(iconStyle);
			
			var vectorSource = new ol.source.Vector({
			  features: [iconFeature]
			});
			
			var vectorLayer = new ol.layer.Vector({
			  source: vectorSource
			});
	      var map = new ol.Map({
	        target: 'map',
	        overlays: [overlay],
	        layers: [
	          new ol.layer.Tile({
	            title: "Global Imagery",
	            source: new ol.source.OSM()
	          })
	        ],
	        view: new ol.View({
	          center: ol.proj.transform([103.794107, 1.356401], 'EPSG:4326', 'EPSG:3857'),
	          zoom: 12,
	          minZoom: 10
	        })
	      });
	      test();
	      map.addLayer(map_layer);
	      //set popup
		var element = document.getElementById('popup');

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
				//alert(feature.get('overlay_content'));
				poptitle.innerHTML = '<span>'+feature.get('name')+'</span>';
				<#--content.innerHTML = '<span>positions:</span><span>' + feature.get('positions') + -->
				<#--'</span><br/><span>currentpositions:</span><span>' + feature.get('curPositions') + '</span>';-->
				content.innerHTML = '<span> Lots :</span><span>' + feature.get('lotsPositions') +
				'</span>';
				overlay.setPosition(coordinate);
			}
			//else {
				//$(closer).click();
			//}		  
		});
	    </script>
	   <@hst.headContributions categoryIncludes="htmlBodyEnd" xhtml=true/>
	</body>
</html>