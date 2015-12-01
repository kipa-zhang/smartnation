<#include "../include/imports.ftl">
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="<@hst.webfile path="/css/bootstrap.min.css"/>" />
        <link rel="stylesheet" type="text/css" href="<@hst.webfile path="/css/bootstrap-theme.min.css"/>" />
        <link rel="stylesheet" type="text/css" href="<@hst.webfile path="/css/hr_message/cabel-v1.css"/>" />
		
		<script type="text/javascript" src="<@hst.webfile path="/js/jquery-2.1.0.min.js"/>"></script>
		<script type="text/javascript" src="<@hst.webfile path="/js/hr_message/FancyZoom.js"/>"></script>
		<script type="text/javascript" src="<@hst.webfile path="/js/hr_message/FancyZoomHTML.js"/>"></script>
        <script type="text/javascript" src="<@hst.webfile path="/js/hr_message/bootstrap.min.js"/>"></script>
        
		<style>
			.hr-box{
				background-color:white;
				width:100%;
				height:100%;
				height:inherit;
				padding:1em 1em 1em 1em;
				margin: 0.5em 0.5em 0.5em 0em;
				box-shadow: 0 0 2px rgba(0,0,0,0.2);
				border-radius: 5px;
			}
			.hr-userimage{
				border-radius: 50%;
				width: 50px;
				height: 50px;
			}
			.hr-context{
				padding: 15px 0 3px;
				overflow: hidden;
				line-height: 23px;
				word-wrap: break-word;
			}
			.hr-icon{
				display: inline-block; 
				background-image:url(http://cdn.iknow.bdimg.com/static/common/pkg/common_z_c3a7666.png);
			}
		</style>
		<script>
		<#-- 图片全局变量的使用 -->
		var zoomImagesURI   = '<@hst.webfile path="/images/hr_message"/>' + '/';
		 
		$(document).ready(function(){
			$.ajax({
                 type:"GET",
                 url:"/site/springapp/hrmessage/getNextHRMessageInfo",
                 data:{index:0,
                 	size:5
                 },
                 success:function(data){
                 	var jsonresult = JSON.parse(data);
                 	for(var js in jsonresult)
		            {
						console.info(jsonresult[js]);
		                $("#message").append(addHR(jsonresult[js].id,jsonresult[js].icon,jsonresult[js].imgs,jsonresult[js].fromperson,jsonresult[js].toperson,jsonresult[js].time,jsonresult[js].address,jsonresult[js].content,jsonresult[js].title));
		            	<#-- 这是插件中FancyZoom.js的方法，如果图片是后来生成的，需要再调用一次  -->
		            	setupZoom();
		            }
                 }        
              });
		})
		
            
            function addHR(id,icon,imgs,from,to,time,address,content,title){
				var retime = new Date(time).Format("dd/MM hh:mm:ss");
				var iconPath = '<@hst.webfile path="/images/hr_message"/>' ;
				var appendhtml = '<div class="hr-box row"><div style="width:50px;float:left;">' + 
				'<img title="'+ from +'" alt="'+ from +'" width="50" height="50" class="hr-userimage" src="'+ iconPath+'/'+ icon +'"/>'+
				'</div><div style="margin-left: 5em;text-align: left;"><div style="margin: 0.3em 0 0;padding: 5px 0 0;font-weight:bold;font-size:1.5em;">'+
				from + ' <span style="font-weight:normal;font-size:0.7em;"> '+ retime +'</span><a target="_blank" title="manager"><i class="hr-icon"></i></a></div>'+
				'<div class="hr-context">'+ content +'</div>'+getImg(imgs)+'</div></div>';
				return 	appendhtml;
			}
			
			function getImg(imgs){
				if(imgs != null){
					var iconPath = '<@hst.webfile path="/images/hr_message"/>' ;
					var imgDiv = '<div class="photoblock-many">';
					var imgPaths =imgs.split(";");
					imgPaths.forEach(function(e){
						imgDiv += '<a href="'+iconPath+'/'+e+'.jpg" title="1111">'+
						'<img type="file" src="'+iconPath+'/'+e+'-thumb.jpg" width="161" height="123" border="0" class="photo" /></a>';
					}) 
					imgDiv += '</div>';
					return imgDiv;
				}else{
			     	return "";
			    }
			}
            
			Date.prototype.Format = function(fmt)   
			{ 
			  var o = {   
				"M+" : this.getMonth()+1,                    
				"d+" : this.getDate(),                    
				"h+" : this.getHours(),                   
				"m+" : this.getMinutes(),                 
				"s+" : this.getSeconds(),                    
				"q+" : Math.floor((this.getMonth()+3)/3), 
				"S"  : this.getMilliseconds()              
			  };   
			  if(/(y+)/.test(fmt))   
				fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
			  for(var k in o)   
				if(new RegExp("("+ k +")").test(fmt))   
			  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
			  return fmt;   
			} 
		</script>
    </head>
    <body style="background-color:#ebebeb;margin-top: 0.7em;" class="container-fluid" onLoad="setupZoom();">
		<div id="message" class="st-accordion">
			
		</div>
	</body>
		
    
</html>	