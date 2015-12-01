
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
var map_layer_init = {};
var json;
//var map_layer_v = [];
$.ajax({
	url:"/site/springapp/busschedule/getBusSchedules",
	datatype: "json",
	//async: false,
	success: function(msg){
		//alert(msg);
		json = JSON.parse(msg);			
		
		for(var i in json){
			map_layer[json[i].code] = new ol.layer.Vector();
			map.addLayer(map_layer[json[i].code]);
			if(i == 0){
				var option=$("<option id=\"bus_"+json[i].code+"\" selected value=\""+i+"\">"+json[i].code+"</option>");
			}else{
				var option=$("<option id=\"bus_"+json[i].code+"\" value=\""+i+"\">"+json[i].code+"</option>");
			}				
			$("#busNo").append(option);
											
			getBusStation(json[i].code);
		}	
		
		setTimeout(showBusRoute,3000);
	},
	error:function(XMLHttpRequest, textStatus){
			
	}
});
	
function showBusRoute(){
	var sel_val=$("#busNo").children("option:selected").val();
	if(sel_val==null||sel_val.toString().length<=0)
		return ;
	var data = json[sel_val];
	var busStartTime = timeF(data.startTime);
	var busEndTime = timeF(data.endTime);						
	$("#busInfo").html("First Bus at :"+busStartTime+"</br>Last Bus at :"+busEndTime);
	var j = data.code;
	//alert(map_layer[j].hasData);
	if(map_layer[j].hasData){			 		
		if(map_layer[j].getVisible()){
			map_layer[j].setVisible(false);
			//map_main.setVisible(true);
		}else{
			//map_main.setVisible(false);
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
		
	}
}

$("#busNo").change(function(){	
	showBusRoute();
});	
			
function getBusStation(code){
	$.ajax({
		url:"/site/springapp/busschedule/getBusStation?id="+code,
		datatype:"json",
		success:function(msg){
			//alert(i);
			var json = JSON.parse(msg);
			console.info(code);
			console.info(json);
			//console.info(map_layer);
			show(json,map_layer[code]);
			map_layer[code].setVisible(false);
			map_layer[code].hasData = true;
		},
		error:function(XMLHttpRequest, textStatus){
			//alert("No data available!");
			//map_layer[code].hasData = false;
		}
	});
}			