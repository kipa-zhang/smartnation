<#include "../include/imports.ftl">
<!DOCTYPE html>
<html lang="en" style="width:100%;height:100%;">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<head>
	    <link rel="stylesheet" href="<@hst.webfile path="/css/jquery-ui.css"/>" >
	    <link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap.min.css"/>" >
		<script type="text/javascript" src="<@hst.webfile path="/js/jquery-2.1.0.min.js"/>"></script>
		<script type="text/javascript" src="<@hst.webfile path="/js/jquery-ui.min.js"/>"></script>
		<script type="text/javascript" src="<@hst.webfile path="/js/jquery.dotdotdot.js"/>"></script>
		<script type="text/javascript" src="<@hst.webfile path="/js/bootstrap.min.js"/>"></script>
	    <style>
		  body {
			font-family: Times, TimesNR, 'New Century Schoolbook',
            Georgia, 'New York', serif;
		  }
		  .div0{
		  	height:170px;
		    background:url('<@hst.webfile path="/images/staff_home/5.png"/>') no-repeat;
		  }
		  .div1{
		    background:url('<@hst.webfile path="/images/staff_home/1.png"/>');
		  }
		  .div2{
		    background:url('<@hst.webfile path="/images/staff_home/2.png"/>');
		  }
		  .div3{
		    background:url('<@hst.webfile path="/images/staff_home/3.png"/>');
		  }
		  .div4{
		    background:url('<@hst.webfile path="/images/staff_home/4.png"/>');
		  }
		  .div5{
		    background:url('<@hst.webfile path="/images/staff_home/5.png"/>');
		  }
		  .div6{
		    background:url('<@hst.webfile path="/images/staff_home/6.png"/>');
		  }
		  .div7{
		    background:url('<@hst.webfile path="/images/staff_home/7.png"/>');
		  }
		  .div8{
		    background:url('<@hst.webfile path="/images/staff_home/8.png"/>');
		  }
		  .div9{
		    height:170px;
		    background-color:#ffffff;
		    text-align:center; 
		  }
		  
		  #sortable div
		  {
		  	height:170px;
		  	background-size: 100% 100%;
		  	background-repeat:no-repeat;
		    min-width:286px;
		  }
		  .col-xs-6,.col-sm-6,.col-md-4
		  {
		  	padding:0px;
		  }
		  .img{
		  	margin-top:53px;
		  	width:63px;
		  	height:63px;
		  	vertical-align:middle;
		  }
		  .ui-state-default{
		  	border: 1px solid #FFFFFF;
		  }
		  
		  .checkbox label{
		  	display:block;
		  	margin-top:10px;
		  }
		
		  @media screen and (min-width: 300px) and (max-width:619px){
		    #sortable{
		    	text-align:center !important;
		    }
		    #sortable div{
		    	text-align:center;
		    }
		    .div10{
		    	height:auto !important;
		    }
		    #topdiv
		    {
		    	width: 286px;
				height: 280px;
				margin: 0px auto;
		    }
		    #sortable div{
		    	margin: 0px auto;
		    	float:none;
		    }
		  }
		  @media screen and (min-width:620px){
		  		#topdiv{
		  			margin-left:15px;
		  			margin-right:15px;
		  		}
		  }
		  .div10
		  {
		    	min-height:140px;
		  }
		</style>
		<script>
			function topicView(){
					$.ajax({
			        		type:"POST",
			        		url:"/site/springapp/user/searchUserTopics",
			        		success:function(data){
			        			if(data){
			        				var obj = [];
			        				var userId = 1;
			        				if(data.length<=0)
			        				{
			        					$.ajax({
							        		type:"POST",
							        		url:"/site/springapp/hobby/recommend_topic",
							        		success:function(data){
							        			if(data&&data.status=="success"&&data.result){
							        				var res= data.result;
							        			    initTopicView(res);
							        			}
							        		}
							        	});
			        				}
			        				else
			        				{
				        				initTopicView(data);
			        				}
			        			}
			        		}
			        	});
			
			}
			function initTopicView(topic)
			{
				if(topic.length != 0){
					document.getElementById("sortable").innerHTML="";
					var base_url="<@hst.webfile path=""/>";
					for(var i = 0; i < topic.length; i++){
						$("#sortable").append("<div class='ui-state-default col-xs-6 col-sm-6 col-md-4 ' style='background-image:url(&quot;"+base_url+topic[i].icon+"&quot;)' onclick='change_src(&quot;"+topic[i].url+"&quot;,event)' id='" + topic[i].topicName + "'>"+"<button type='button' class='close' style='color:black;background:#8A8A8A;height:18px;' onclick='topicClose(this)'>"+"&times;"+"</button>"+"</div>");
					}
					$("#sortable").append("<div class='ui-state-default col-xs-6 col-sm-6 col-md-4 div9' id='addIcon'>"+"<img class='img' style='cursor:pointer' src='<@hst.webfile path="/images/staff_home/add.png"/>' onclick='searchOtherTopics()'/>"+"</div>");
				}
			}
			var gloable_other_topics={};
			function searchOtherTopics(){
				$.ajax({
					type:"POST",
					url:"/site/springapp/user/searchOtherTopics",
					success:function(data){
						document.getElementById("checkBoxAddTopics").innerHTML="";
						if(data&&data.length != 0){
							for(var i = 0; i<data.length; i++){
								gloable_other_topics[data[i].topicId]=data[i];
								$("#checkBoxAddTopics").append("<label>"+"<input type='checkbox' name='topic' value='"+data[i].topicId+"'/>"+data[i].topicName+"</label>");
							}
						}else{
							$("#checkBoxAddTopics").append("<h2>Please login first!</h2>");
						}
						$('#myModal').modal('show');
					}
				});
			}
			
		  $(function() {
			    $("#sortable").sortable();
			    $("#sortable").disableSelection();
			    $("#wrapper").dotdotdot({});
		  });
		  
		  function change_src(url,e){
		  	 	window.location=url;
		   };
		   function addTopicServices(tps,success,error){
		   		var add_tps="";
		   		for(var i in tps)
		   		{
		   			add_tps+=tps[i]+",";
		   		}
		   		add_tps=add_tps.replace(/,$/,"");
				$.ajax({
					type:"POST",
					url:"/site/springapp/user/addtopic",
					data:{topicIds:add_tps,userId:"1"},
					success:success,
					error:error
				});
			}
		   function addTopic(){
		   		var topics = document.getElementsByName("topic");
		   		check_topic = [];
		   		for(i in topics){
		   			if(topics[i].checked){
		   				check_topic.push(topics[i].value);
		   			}
		   		}
		   		
		   		if(check_topic.length == 0){
		   			alert("You haven't chosen topic yet!");
		   		}
		   		addTopicServices(check_topic,function(){
		   			
			   		if(check_topic.length != 0){
			   			$('#myModal').modal('hide')
			   			alert("Successfully added");
			   		}
			   		
			   		for(j in check_topic){
			   			var base_url="<@hst.webfile path=""/>";
			   			var t=gloable_other_topics[check_topic[j]];
			   			$("#addIcon").before("<div class='ui-state-default col-xs-6 col-sm-6 col-md-4 ' style='background-image:url(&quot;"+base_url+t.icon+"&quot;)' onclick='change_src(&quot;"+t.url+"&quot;,event)' id='" + t.topicName + "'>"+"<button type='button' class='close' style='color:black;background:#8A8A8A;height:18px;' onclick='topicClose(this)'>"+"&times;"+"</button>"+"</div>");
			   		}
		   		},function(){
		   			alert("error ,please try again");
		   		});
		   		
		   };
		   
		   function topicOnclik(){
		   		$("#sortable").css("display","none");
		   		$("#topdiv").after("<iframe width='100%' src='http://www.baidu.com'></iframe>");
		   };
		   
		   
		   function topicClose(obj){
		   		var e = window.event;
		   		e.stopPropagation();
		   		obj.parentElement.style.display='none';
		   		//alert(obj.parentElement.id);
		   		$.ajax({
		   			type:"POST",
		   			data:{topicName:obj.parentElement.id},
		   			url:"/site/springapp/user/deleteTopicByName",
		   			success:function(result)
		   			{
		   				console.info(result+" has bean deleted");
		   			}
		   		});
		   		return false;
		   }
		</script>
		<@hst.headContributions categoryIncludes="htmlHead" xhtml=true/>
	</head>
	<body>
		<div class="container">
			<div id="topicdiv" class="row">
				<div id="topdiv">
					<div class="col-xs-6 col-md-4 div0" style="height:140px;"></div>
				    <div class="col-xs-6 col-md-8 div10" style="border: 1px solid #FFFFFF;height:140px;background-color:#F4F4F4;min-width:286px;">
					    <span style="padding:20px;display:inline-block;font-size:18px;">If you would go up high , then use your own legs ! Do not let yourselves carried aloft; do not seat yourselves on other people's backs and heads .(F.W .Nietzsche , German Philosopher)</span><br/>
				    </div>
				 </div>
				  <div id="sortable" class="col-md-12" style="margin-top:20px;">

					  <div class="ui-state-default col-xs-6 col-sm-6 col-md-4 div1" onclick="change_src('',event)">
					  	<button type="button" class="close" style="color:black;background:#8A8A8A;height:18px;" onclick="topicClose(this)">
		                  &times;
		            	</button>
					  </div>
					  <div class="ui-state-default col-xs-6 col-sm-6 col-md-4 div2" onclick="change_src('',event)">
					  	<button type="button" class="close" style="color:black;background:#8A8A8A;height:18px;" onclick="topicClose(this)">
		                  &times;
		            	</button>
					  </div>
					  <div class="ui-state-default col-xs-6 col-sm-6 col-md-4 div3" onclick="change_src('',event)">
					  	<button type="button" class="close" style="color:black;background:#8A8A8A;height:18px;" onclick="topicClose(this)">
		                  &times;
		            	</button>
					  </div>
					
					  <div class="ui-state-default col-xs-6 col-sm-6 col-md-4 div4" onclick="change_src('',event)">
					  	<button type="button" class="close" style="color:black;background:#8A8A8A;height:18px;" onclick="topicClose(this)">
		                  &times;
		            	</button>
					  </div>
					  <div class="ui-state-default col-xs-6 col-sm-6 col-md-4 div5" onclick="change_src('',event)">
					  	<button type="button" class="close" style="color:black;background:#8A8A8A;height:18px;" onclick="topicClose(this)">
		                  &times;
		            	</button>
					  </div>
					  <div class="ui-state-default col-xs-6 col-sm-6 col-md-4 div6" onclick="topicOnclik()">
					  	<button type="button" class="close" style="color:black;background:#8A8A8A;height:18px;" onclick="topicClose(this)">
		                  &times;
		            	</button>
					  </div>
					
				
					  <div class="ui-state-default col-xs-6 col-sm-6 col-md-4 div7">
					  	<button type="button" class="close" style="color:black;background:#8A8A8A;height:18px;" onclick="topicClose(this)">
		                  &times;
		            	</button>
					  </div>
					  <div class="ui-state-default col-xs-6 col-sm-6 col-md-4 div8">
					  	<button type="button" class="close" style="color:black;background:#8A8A8A;height:18px;" onclick="topicClose(this)">
		                  &times;
		            	</button>
					  </div>
					  <div class="ui-state-default col-xs-6 col-sm-6 col-md-4 div9" id="addIcon">
					     <img class="img" style="cursor:pointer" src="<@hst.webfile path="/images/staff_home/add.png"/>" onclick="searchOtherTopics()"/>
					  </div>

				 </div>
			 </div>
	    </div>
		
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header">
		            <button type="button" class="close" 
		               data-dismiss="modal" aria-hidden="true">
		                  &times;
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		              Select Topic
		            </h4>
		         </div>
		         <div class="modal-body">
		         	<div class="checkbox" id="checkBoxAddTopics">
		         		
		         	</div>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" 
		               data-dismiss="modal">cancel
		            </button>
		            <button type="button" class="btn btn-primary" onclick="addTopic()">
		                commit
		            </button>
		         </div>
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
	   <@hst.headContributions categoryIncludes="htmlBodyEnd" xhtml=true/>
	</body>
</html>


