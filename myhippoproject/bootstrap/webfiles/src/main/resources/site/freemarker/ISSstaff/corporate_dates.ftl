<#include "../include/imports.ftl">
<!DOCTYPE html>
<html lang="en" style="width:100%;height:100%;">
	<head>
	    <title>Calendar</title>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
		<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
		<title>Calendar</title>
		<link rel="stylesheet" href="<@hst.webfile path="/css/calendar.css"/>" >
		<link rel="stylesheet" href="<@hst.webfile path="/css/perfect-scrollbar.css"/>" >
		<script type="text/javascript" src="<@hst.webfile path="/js/jquery-2.1.0.min.js"/>"></script>
		<!--<script type="text/javascript" src="<@hst.webfile path="/js/calendar.js"/>"></script>-->
		<script type="text/javascript" src="<@hst.webfile path="/js/jquery.mousewheel.js"/>"></script>
		<script type="text/javascript" src="<@hst.webfile path="/js/perfect-scrollbar.js"/>"></script>
		<!--<link rel="stylesheet" href="<@hst.webfile path="/css/documentation.css"/>" >
		<link rel="stylesheet" href="<@hst.webfile path="/css/jalendar.css"/>" >
		<script type="text/javascript" src="<@hst.webfile path="/js/jquery-2.1.0.min.js"/>"></script>
		<script type="text/javascript" src="<@hst.webfile path="/js/jalendar.js"/>"></script>
		<script type="text/javascript">
		$(function () {
		    
		    $('#myId1').jalendar();
		});
        </script>-->
        <style type="text/css">
        	.fc-sat,.fc-sun
        	{
        		background:#C4C4C4;
        	}
        	.fc-state-highlight
        	{
        		background:#FAEBD7 !important;
        	}
        	.fc-day-number{
        		font-size:25px !important;
        	}
        	.fc-event{
        		font-size:15px !important;
        	}
        	.fc-day-number{
        		color:#000 !important;
        	}
        </style>
		<@hst.headContributions categoryIncludes="htmlHead" xhtml=true/>
	</head>
	<body style="width:100%;height:840px;">

		
		<div id="test" style="overflow:hidden;height:100%;position:relative;width:100%;">
			<div class="container">
				<div class="custom-calendar-wrap custom-calendar-full">
					<div class="custom-header clearfix">
						<h3 class="custom-month-year">
							<span id="custom-month" class="custom-month"></span>
							<span id="custom-year" class="custom-year"></span>
							<nav>
								<span id="custom-prev" class="custom-prev"></span>
								<span id="custom-next" class="custom-next"></span>
								<span id="custom-current" class="custom-current" title="Got to current date"></span>
							</nav>
						</h3>
					</div>
					<div id="calendar" class="fc-calendar-container"></div>
				</div>
			</div>
		</div>
	   <@hst.headContributions categoryIncludes="htmlBodyEnd" xhtml=true/>
	</body>
</html>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap.css"/>" >
<link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap-responsive.css"/>" >
<link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap-overrides.css"/>" >

<link rel="stylesheet" href="<@hst.webfile path="/css/fullcalendar.css"/>" >
<link rel="stylesheet" href="<@hst.webfile path="/css/fullcalendar.print.css"/>" >
<title>Insert title here</title>
</head>
<body>
	<div class="content">
		<div class="container-fluid">
			<div id="pad-wrapper">
				<div class="row-fluid calendar-wrapper">
					<div class="span12">
						<!-- div that fullcalendar plugin uses  -->
						<div id='calendar'></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end main container -->
	
	<!-- 模态框 -->
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
		              Add Events
		            </h4>
		         </div>
		         <div class="modal-body">
		         <div class="form-group">
		         	<label class="col-lg-3 control-label" style="text-align:center;">Event</label>
			         	<div class="col-lg-10" style="text-align:center;">
			         		<input type="text" class="form-control" id="Event_text" style="height:30px !important;weight:100%;" />
			         	</div>
		         	</div>
		         </div>
		         <div class="modal-footer">
		            <button type="button" class="btn btn-default" data-dismiss="modal">cancel</button>
		            <button type="button" class="btn btn-primary" onclick="addEvent()">
		                Add
		            </button>
		         </div>
		      </div><!-- /.modal-content -->
		</div><!-- /.modal -->
		
	<!-- scripts for this page -->
	<!--<script type="text/javascript" src="<@hst.webfile path="/js/jquery-latest.js"/>"></script>-->
	<script type="text/javascript" src="<@hst.webfile path="/js/jquery-ui-1.10.2.custom.min.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/bootstrap.min.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/fullcalendar.min.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/theme.js"/>"></script>

	<!-- builds fullcalendar example -->
	<script>
		var date,describ;
		var addEvent = function(){
				var Event_text = document.getElementById("Event_text").value;
				if(Event_text){
					$('#myModal').modal('hide');
					//var td = document.getElementsByClassName('fc-day');
					
					date = (new Date(data_date)).getTime(); 
					describ = "aaa";
					//console.info(date);
					$.ajax({
						type:"POST",
						url:"/site/springapp/corporateDate/saveCorporate",
						data:{date:date,describ:describ,end:date,name:Event_text,start:date},
						success:function(html){
							if(html){
								window.location.reload();
							}
						}
					});
				}else{
					alert("You haven't add a event yet!");
				}
			};
		
		var data_date;
		
		$(document).ready(function() {
			var datas;
			$.ajax({
				type:"POST",
				async:false,
				url:"/site/springapp/corporateDate/transferDatas",
				success: function(data){
					datas = JSON.parse(data);
				}
			});
			
			
			var date = new Date();
			var d = date.getDate();
			var m = date.getMonth();
			var y = date.getFullYear();
			var eventsData = [];
			
			for(var index in datas)
			{
				//if(new Date(parseInt(datas[index].date)).getMonth() == m){
				console.info(new Date(new Date(parseFloat(datas[index].date)).getFullYear(),new Date(parseFloat(datas[index].date)).getMonth(),new Date(parseFloat(datas[index].date)).getDate()));
					eventsData.push({
						title:datas[index].name,
						start:new Date(new Date(parseFloat(datas[index].date)).getFullYear(),new Date(parseFloat(datas[index].date)).getMonth(),new Date(parseFloat(datas[index].date)).getDate())
					});
				//}
			}
			var objs = [ {
					title : 'All Day Event',
					start : new Date(y, m, 1)
				}, {
					title : 'Long Event',
					start : new Date(y, m, d - 5),
					end : new Date(y, m, d - 2)
				}, {
					id : 999,
					title : 'Repeating Event',
					start : new Date(y, m, d - 3, 16, 0),
					allDay : false
				}, {
					id : 999,
					title : 'Repeating Event',
					start : new Date(y, m, d + 4, 16, 0),
					allDay : false
				}, {
					title : 'Meeting',
					start : new Date(y, m, d, 10, 30),
					allDay : false
				}, {
					title : 'Lunch',
					start : new Date(y, m, d, 12, 0),
					end : new Date(y, m, d, 14, 0),
					allDay : false
				}, {
					title : 'Birthday Party',
					start : new Date(y, m, d + 1, 19, 0),
					end : new Date(y, m, d + 1, 22, 30),
					allDay : false
				}, {
					title : 'Click for Google',
					start : new Date(y, m, 28),
					end : new Date(y, m, 29),
					url : 'http://google.com/'
				} ];
			//console.info("1"+eventsData);
			//console.info("2"+objs);
			
			
			$('#calendar').fullCalendar({
				header : {
					left : 'month,agendaWeek,agendaDay',
					center : 'title',
					right : 'today prev,next'
				},
				selectable : true,
				selectHelper : true,
				editable : true,
				events : eventsData,
				eventBackgroundColor : '#278ccf'
			});
			
			var createEvent = function(){
				var td = document.getElementsByClassName('fc-day');
				for(var i=0;i<td.length;i++){
					td[i].ondblclick = function showModal(){
						var username;
						$.ajax({
							type:"POST",
							async:false,
							url:"/site/springapp/user/get_login_user",
							success: function(user){
								if(user)
								{
									username = user.userName;
								}
							}
						});
						console.info(typeof username == 'undefined');
					    if(typeof username == 'undefined'){
					     	alert("Please log in first.");
					     	console.info("--------------------" + username);
					     	return;
					    }
						data_date = this.attributes['data_date'].value;
						$('#myModal').modal('show');
				}
				};
			};
			createEvent();
		});
	</script>
</body>
</html>

