<#include "../include/imports.ftl">
<!DOCTYPE html>
<html>
<head>
	<title>Detail Admin - Home</title>
    
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<style>
		html,body{
			        height: 100%;
			        width: 100%;
			 }
			#sidebar-nav { 
	        	    overflow: hidden;
					height: 424px; 
	        }
			.modal-header {
			  padding: 2px 15px !important;
			}
			.h3{
				font-family: Times, TimesNR, 'New Century Schoolbook',
				Georgia, 'New York', serif;
			}
			.modal {
			  width: 460px;
  			  margin-left: -230px;
			}
			small{
				text-align:center;
				color: #a94442 !important;
			}
			.modal-body{
				max-height: 700px !important;
			}
			
			<!-- autocomplete style -->
			.autocomplete-suggestions
			{
				background-color:#fff !important;
			}
			.topic_img
			{
				width:25px;
				height:25px;
				background-position:-15px -5px !important;
				position:relative;
				z-index:99;
				bottom:25px;
				right:0px;
				display:none;
				float:right;
			}
			.topicDiv
			{
				display:inline-block;
				float:left;
				min-width:300px;
			}
			<!--.ui-autocomplete
			{
				width:206px;
			}
			.ui-autocomplete li
			{
				list-style-type:none;
			}-->
			@media (max-width: 768px) {
				#searchModal
				{
					width:95% !important;
					margin-left:0px;
				}
				.topicDiv
				{
					height:200px;
					width: 40%;
					margin-left:5%;
				}
			}
			@media (min-width: 768px) and (max-width: 1200px) {
				#searchModal
				{
					width:60% !important;
					margin-left:20%;
					left:0;
				}
				.topicDiv
				{
					height:200px;
					width: 20%;
					margin-left:5%;
				}
			}
			@media (min-width: 1200px)
			{
				#searchModal
				{
					width:60% !important;
					margin-left:20%;
					left:0;
				}
				.topicDiv
				{
					height:200px;
					width: 20%;
					margin-left:5%;
				}
			}
	  </style>
    <!-- bootstrap -->
    <link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap.css"/>" >
    <!--<link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap.min.css"/>" >-->
    <link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap-responsive.css"/>" >
    <link rel="stylesheet" href="<@hst.webfile path="/css/bootstrap-overrides.css"/>" >

    <!-- libraries -->
    <link rel="stylesheet" href="<@hst.webfile path="/css/jquery-ui-1.10.2.custom.css"/>" >
    <link rel="stylesheet" href="<@hst.webfile path="/css/font-awesome.css"/>" >

    <!-- global styles -->
    <link rel="stylesheet" href="<@hst.webfile path="/css/layout.css"/>" >
    <link rel="stylesheet" href="<@hst.webfile path="/css/elements.css"/>" >
    <link rel="stylesheet" href="<@hst.webfile path="/css/icons.css"/>" >

    <!-- this page specific styles -->
    <link rel="stylesheet" href="<@hst.webfile path="/css/index.css"/>" >
    
    <!-- perfect-scrollbar -->
    <link rel="stylesheet" href="<@hst.webfile path="/css/perfect-scrollbar.css"/>" >
    
    <!-- open sans font -->
    <link rel="stylesheet" href="<@hst.webfile path="/css/open_sans_font.css"/>" >

    <!-- lato font -->
    <link rel="stylesheet" href="<@hst.webfile path="/css/lato_font.css"/>" >
    
    <!--[if lt IE 9]>
    <script type="text/javascript" src="<@hst.webfile path="/js/html5.js"/>"></script>
    <![endif]-->
    
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<body>

    <!-- navbar -->
    <div class="navbar navbar-inverse" id="navbar_top">
        <div class="navbar-inner">
            <button type="button" class="btn btn-navbar visible-phone" id="menu-toggler">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            
            <a class="brand" href="index.html"><img src='<@hst.webfile path="/images/logo-13.png"/>' /></a>

            <ul class="nav pull-right">                
                <li>
                    <input class="search" type="text" id="autocomplete-dynamic" style="float:left;"/>
                    <div id="searchBn" style="width:27px;height:19px;display:inline-block;top:9px;position:absolute;right:40px;padding:4px 0px;z-index:99;"></div>
                </li>
                <li class="dropdown">
                    <img src='<@hst.webfile path="/images/25.png"/>' data-toggle="dropdown" style="margin-top:10px;"/>
                    
                    <ul class="dropdown-menu">
                    	<li><a data-toggle="modal" id="l_login" data-target="#login">Login/Register</a></li>
                        <li id="li_info" style="display:none"><a data-toggle="modal" id="l_info" data-target="#info">Personal info</a></li>
                        <li id="li_setting" style="display:none"><a data-toggle="modal" id="l_setting" data-target="#setting" onclick="showDialog('setting')">Account settings</a></li>
                        <!--<li><a href="#">Billing</a></li>
                        <li><a href="#">Export your data</a></li>
                        <li><a href="#">Send feedback</a></li>-->
                    </ul>
                </li>
            </ul>            
        </div>
    </div>
    <!-- end navbar -->

    <!-- sidebar -->
	    <div id="sidebar-nav">
	        <ul id="dashboard-menu">
	            <li class="active">
	                <div class="pointer">
	                    <div class="arrow"></div>
	                    <div class="arrow_border"></div>
	                </div>
	                <a onclick="change_src('staff_home',event)">
	                    <i class="icon-home"></i>
	                    <span>Home</span>
	                </a>
	            </li>            
	            <li>
	                <a onclick="change_src('corporate_dates',event)">
	                    <i class="icon-signal"></i>
	                    <span>Corporate Dates</span>
	                </a>
	            </li>
	            <li>
	                <a onclick="change_src('public_parking',event)">
	                    <i class="icon-group"></i>
	                    <span>Public Parking</span>
	                </a>
	            </li>
	            <li>
	                <a onclick="change_src('hr_message',event)">
	                    <i class="icon-edit"></i>
	                    <span>HR Message</span>
	                </a>
	            </li>
	            <li>
	                <a onclick="change_src('bus_schedule',event)">
	                    <i class="icon-road"></i>
	                    <span>BUS Schedule</span>
	                </a>
	            </li>
	            <li>
	                <a onclick="change_src('weather',event)">
	                    <i class="icon-cloud"></i>
	                    <span>Weather</span>
	                </a>
	            </li>
	        </ul>
	    </div>
    <!-- end sidebar -->

	
	<!-- main container -->
    <div class="content" id="content_right" style="height:100%;">
	    <div id="page_top_navigation" class="top-navigation">
			
		</div>
        <iframe frameborder="0" id="rightpage" name="Iframe1" style="width:100%;"></iframe>
    </div>

	<!-- 模态框（Modal） -->
	<div class="modal fade" id="login" tabindex="-1" role="dialog" 
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" 
						data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
				</div>
				<div class="modal-body">
					<section class="loginBox row-fluid">
						<div class="tabbable" id="tabs-634549">
							<ul class="nav nav-tabs">
								<li class="active">
									<a href="#panel-60560" data-toggle="tab">Login</a>
								</li>
								<li>
									<a href="#panel-549981" data-toggle="tab">Register</a>
								</li>
							</ul>
							<div class="tab-content">
								<div class="tab-pane active" id="panel-60560">
									<div style="width:100%">
								    	<div>
									        <div class="col-lg-8 col-lg-offset-2">
									            <form id="defaultForm" method="post" class="form-horizontal">
									                <div class="form-group">
									                    <label class="col-lg-3 control-label" style="text-align:center;">Account</label>
									                    <div class="col-lg-5">
									                        <input type="text" class="form-control" id="userName" />
									                    </div>
									                </div>
									
									                <div class="form-group" style="margin-top:10px">
									                    <label class="col-lg-3 control-label" style="text-align:center;">Password</label>
									                    <div class="col-lg-5">
									                        <input type="password" class="form-control" id="password" />
									                    </div>
									                </div>
									
									                <div class="form-group" style="margin-left:200px !important;margin-top:20px;">
									                    <div class="col-lg-9 col-lg-offset-3">
									                        <button type="button" class="btn btn-info" onclick="login()">Login</button>
									                    </div>
									                </div>
									            </form>
									        </div>
								    	</div>
									</div>
								</div>
								<div class="tab-pane" id="panel-549981">
									<div style="width:100%">
									    <div>
									        <div class="col-lg-8 col-lg-offset-2">
									            <form id="defaultForm" method="post" class="form-horizontal">
									                <div class="form-group">
									                    <label class="col-lg-3 control-label" style="text-align:center;">Nickname</label>
									                    <div class="col-lg-5">
									                        <input type="text" class="form-control" id="r_userName" name="userName" />
									                    </div>
									                </div>
									
									                <div class="form-group" style="margin-top:10px">
									                    <label class="col-lg-3 control-label" style="text-align:center;">Age</label>
									                    <div class="col-lg-5">
									                        <input type="text" class="form-control" id="r_userAge" name="userAge" />
									                    </div>
									                </div>
									                
									                <div class="form-group" style="margin-top:10px">
									                    <label class="col-lg-3 control-label" style="text-align:center;">Login Account</label>
									                    <div class="col-lg-5">
									                        <input type="text" class="form-control" id="r_userAccount" name="userAccount" />
									                    </div>
									                </div>
									                
									                <div class="form-group" style="margin-top:10px">
									                    <label class="col-lg-3 control-label" style="text-align:center;">Password</label>
									                    <div class="col-lg-5">
									                        <input type="password" class="form-control" id="r_userPassword" name="userPassword" />
									                    </div>
									                </div>
									                
									                <div class="form-group" style="margin-top:10px">
									                    <label class="col-lg-3 control-label" style="text-align:center;">Roles</label>
									                    <div class="col-lg-5">
									                        <input type="text" class="form-control" id="r_userRoles" name="userRoles" />
									                    </div>
									                </div>
									                
									                <div class="form-group" style="margin-top:10px">
									                    <label class="col-lg-3 control-label" style="text-align:center;">Interest</label>
									                    <div class="col-lg-5">
									                        <!--<input type="text" class="form-control" id="r_userInterest" name="userInterest" />-->
									                        <select class="form-control" id="r_userInterest" name="userInterest" style="height:30px;">
															  <option>bike</option>
															  <option>news</option>
															  <option>weather</option>
															  <option>others</option>
															</select>
									                    </div>
									                </div>
									
									                <div class="form-group" style="margin-left:200px !important;margin-top:20px;">
									                    <div class="col-lg-9 col-lg-offset-3">
									                        <button type="button" onclick="register()" class="btn btn-info">Sign up</button>
									                    </div>
									                </div>
									            </form>
									        </div>
									    </div>
									</div>
								</div>
							</div>
						</div>
                 	</section>
	         	</div>
	      	</div>
		</div>
	</div>
	
	<!-- 模态框（Modal） search result -->
	<div class="modal fade" id="searchModal" tabindex="-1" role="dialog" 
		aria-labelledby="mySearchModal" aria-hidden="true">
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" 
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="mySearchModal">
					Search&nbsp;Result
				</h4>
			</div>
			<div class="modal-body">content</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" 
					data-dismiss="modal">Close</button>
				<!--<button id="searchSubmit" type="button" class="btn btn-primary">Commit</button>-->
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!--模态框（Modal）-->
	<div class="modal fade" id="info" tabindex="-1" role="dialog" 
		aria-labelledby="myInfoModal" aria-hidden="true" >
	<div class="modal-dialog ">
		<div class="modal-content">
			<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal"
	               data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" id="myIndoModal">
	            	Personal Info
	            </h4>
	         </div>
		    <div class="modal-body">
		        <div class="col-lg-8 col-lg-offset-2">
		            <form id="infoForm" method="post" class="form-horizontal">
		                <div class="form-group">
		                    <label class="col-lg-3 control-label" style="text-align:center;">Nickname</label>
		                    <div class="col-lg-5">
		                        <input type="text" class="form-control" id="r_userName" name="userName" />
		                    </div>
		                </div>
		
		                <div class="form-group" style="margin-top:10px">
		                    <label class="col-lg-3 control-label" style="text-align:center;">Age</label>
		                    <div class="col-lg-5">
		                        <input type="text" class="form-control" id="r_userAge" name="userAge" />
		                    </div>
		                </div>
		                		                		                
		                <div class="form-group" style="margin-top:10px">
		                    <label class="col-lg-3 control-label" style="text-align:center;">Roles</label>
		                    <div class="col-lg-5">
		                        <input type="text" class="form-control" id="r_userRoles" name="userRoles" />
		                    </div>
		                </div>
		                
		                <div class="form-group" style="margin-top:10px">
		                    <label class="col-lg-3 control-label" style="text-align:center;">Interest</label>
		                    <div class="col-lg-5">
		                        <input type="text" class="form-control" id="r_userInterest" name="userInterest" />
		                    </div>
		                </div>
		
				        <div class="form-group" style="margin-top:10px">
		                    <label class="col-lg-3 control-label" style="text-align:center;">Address</label>
		                    <div class="col-lg-5">
		                        <input type="text" class="form-control" id="r_userAddress" name="userAddress" />
		                    </div>
		                </div>
					
		                <!--<div class="form-group" style="margin-left:200px !important;margin-top:20px;">
		                    <div class="col-lg-9 col-lg-offset-3">
		                        <button type="submit" onclick="change()" class="btn btn-info">Change</button>
		                    </div>
		                </div>-->
		                <div class="form-group" style="margin-left:200px !important;margin-top:20px;">
		                    <div class="col-lg-9 col-lg-offset-3">
		                        <button type="button" onclick="setUserInfo()" class="btn btn-info">Save</button>
		                    </div>
		                </div>
		            </form>
		        </div>
		    </div>
		</div>
	</div>
	</div>
	
	<!--模态框（Modal）-->
	<div class="modal fade" id="setting" tabindex="-1" role="dialog" 
		aria-labelledby="mySettingModal" aria-hidden="true" >
	<div class="modal-dialog ">
		<div style="width:100%" class="modal-content">
			<div class="modal-header">
	            <button type="button" class="close" data-dismiss="modal"
	               data-dismiss="modal" aria-hidden="true">
	                  &times;
	            </button>
	            <h4 class="modal-title" id="myModalLabel">
	            	Account Setting
	            </h4>
	         </div>
		    <div class="modal-body">
		        <div class="col-lg-8 col-lg-offset-2">
		            <form id="settingForm" method="post" class="form-horizontal">
		                <div class="form-group">
		                    <label class="col-lg-3 control-label" style="text-align:center;">Old Password</label>
		                    <div class="col-lg-5">
		                        <input type="password" class="form-control" id="opwd" name="OldPassword" />
		                    </div>
		                </div>
		                
		                <div class="form-group" style="margin-top:10px">
		                    <label class="col-lg-3 control-label" style="text-align:center;">New Password</label>
		                    <div class="col-lg-5">
		                        <input type="password" class="form-control" id="pwd" name="NewPassword" />
		                    </div>
		                </div>
		
		                <div class="form-group" style="margin-top:10px">
		                    <label class="col-lg-3 control-label" style="text-align:center;">Confirm Password</label>
		                    <div class="col-lg-5">
		                        <input type="password" class="form-control" id="cpwd" name="ConfirmPassword" />
		                    </div>
		                </div>
		                
		                <div class="form-group" style="margin-left:200px !important;margin-top:20px;">
		                    <div class="col-lg-9 col-lg-offset-3">
		                        <button type="button" onclick="changePwd()" class="btn btn-info">Save</button>
		                    </div>
		                </div>
		            </form>
		        </div>
		    </div>
		</div>
	</div>
	</div>
	
	<!-- scripts -->
	<script type="text/javascript" src="<@hst.webfile path="/js/jquery-2.1.0.min.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/bootstrap.min.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/bootstrapValidator.min.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/jquery-ui-1.10.2.custom.min.js"/>"></script>
	<#-- cookie -->
	<script type="text/javascript" src="<@hst.webfile path="/js/cookiehelper.js"/>"></script>
    <!-- knob -->
    <script type="text/javascript" src="<@hst.webfile path="/js/jquery.knob.js"/>"></script>
    <!-- flot charts -->
    <script type="text/javascript" src="<@hst.webfile path="/js/jquery.flot.js"/>"></script>
    <script type="text/javascript" src="<@hst.webfile path="/js/jquery.flot.stack.js"/>"></script>
    <script type="text/javascript" src="<@hst.webfile path="/js/jquery.flot.resize.js"/>"></script>
    <script type="text/javascript" src="<@hst.webfile path="/js/theme.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/jquery.mousewheel.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/perfect-scrollbar.js"/>"></script>
	<script type="text/javascript" src="<@hst.webfile path="/js/jquery-ui.min.js"/>"></script>
    <script type="text/javascript">
    	String.prototype.endWith=function(endStr){
          	  var d=this.length-endStr.length;
          	  return (d>=0&&this.lastIndexOf(endStr)==d)
		};
		function searchTransfer(url)
		{
			document.getElementById("rightpage").src=url;
		}
    </script>
    <!-- auto-complete----start -->
    <script type="text/javascript" src="<@hst.webfile path="/js/autocomplete/jquery.mockjax.js"/>"></script>
    <script type="text/javascript" src="<@hst.webfile path="/js/autocomplete/jquery.autocomplete.js"/>"></script>
    <!-- auto-complete----end  -->
    
    <script type="text/javascript">

	$(document).ready(function() {
	    
	    <#-- 用户登录状态确定 -->
	    islogin();
	    
	    $('#panel-549981').bootstrapValidator({
	//        live: 'disabled',
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            userName: {
	                message: 'The username is not valid',
	                validators: {
	                    notEmpty: {
	                        message: 'The username is required and cannot be empty'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 30,
	                        message: 'The username must be more than 6 and less than 30 characters long'
	                    },
	                    regexp: {
	                        regexp: /^[a-zA-Z0-9_\.]+$/,
	                        message: 'The username can only consist of alphabetical, number, dot and underscore'
	                    },
	                }
	            },
	            
	            userAge: {
		            validators: {
		            	notEmpty: {
	                        message: 'The age is required and cannot be empty'
	                    },
	                    between: {
	                        min: 0,
	                        max: 100,
	                        message: 'The age must be between 0 and 100'
	                    }
	                }
	            },
	            
	            userAccount: {
	            	validators: {
		            	notEmpty: {
	                        message: 'The account is required and cannot be empty'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 30,
	                        message: 'The account must be more than 6 and less than 30 characters long'
                    	}
	                }
	            },
	            
	            userPassword: {
	            	validators: {
		            	notEmpty: {
	                        message: 'The password is required and cannot be empty'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 30,
	                        message: 'The password must be more than 6 and less than 30 characters long'
                    	}
	                }
	            },
	            
	            userRoles: {
	            	validators: {
		            	notEmpty: {
	                        message: 'The role is required and cannot be empty'
	                    },
	                    regexp: {
	                        regexp: /^[a-z\s]+$/i,
	                        message: 'The role can consist of alphabetical characters and spaces only'
                    	}
	                }
	            },
	            
	            userInterest: {
	            	validators: {
		            	notEmpty: {
	                        message: 'The age is required and cannot be empty'
	                    },
	                    regexp: {
	                        regexp: /^[a-z\s]+$/i,
	                        message: 'The interest can consist of alphabetical characters and spaces only'
                    	}
	                }
	            }
	        }
	    });
	
	$('#info').bootstrapValidator({
	//        live: 'disabled',
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	            userName: {
	                message: 'The username is not valid',
	                validators: {
	                    notEmpty: {
	                        message: 'The username is required and cannot be empty'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 30,
	                        message: 'The username must be more than 6 and less than 30 characters long'
	                    },
	                    regexp: {
	                        regexp: /^[a-zA-Z0-9_\.]+$/,
	                        message: 'The username can only consist of alphabetical, number, dot and underscore'
	                    },
	                }
	            },
	            
	            userAge: {
		            validators: {
		            	notEmpty: {
	                        message: 'The age is required and cannot be empty'
	                    },
	                    between: {
	                        min: 0,
	                        max: 100,
	                        message: 'The age must be between 0 and 100'
	                    }
	                }
	            },
	            	            
	            userRoles: {
	            	validators: {
		            	notEmpty: {
	                        message: 'The role is required and cannot be empty'
	                    },
	                    regexp: {
	                        regexp: /^[a-z\s]+$/i,
	                        message: 'The role can consist of alphabetical characters and spaces only'
                    	}
	                }
	            },
	            
	            userInterest: {
	            	validators: {
	                    regexp: {
	                        regexp: /^[a-z\s]+$/i,
	                        message: 'The interest can consist of alphabetical characters and spaces only'
                    	}
	                }
	            }
	        }
	    });
	    
	    $("#setting").bootstrapValidator({
	        message: 'This value is not valid',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	OldPassword:{
	        		validators: {
	        			notEmpty: {
	                        message: 'The Old Password is required and cannot be empty'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 30,
	                        message: 'The password must be more than 6 and less than 30 characters long'
                    	}
	        		}
	        	},
	        	NewPassword: {
	        		validators: {
	        			notEmpty: {
	                        message: 'The Password is required and cannot be empty'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 30,
	                        message: 'The password must be more than 6 and less than 30 characters long'
                    	}
	        		}
	        	},
	        	ConfirmPassword: {
	        		validators:{
	        			notEmpty: {
	                        message: 'The Confirm Password is required and cannot be empty'
	                    },
	                    stringLength: {
	                        min: 6,
	                        max: 30,
	                        message: 'The password must be more than 6 and less than 30 characters long'
                    	},
                    	identical: {
                    		message: 'Please type the same password',
                    		field: 'NewPassword'
                    	}
	        		}
	        	}
	        }	    	
	    });
	    	   
	});
    	
		function iFrameHeight() {   
			var ifm= document.getElementById("rightpage");   
			//var subWeb = document.frames ? document.frames["rightpage"].document : ifm.contentDocument;   
			//if(ifm != null && subWeb != null)
			var content_right= document.getElementById("content_right");
			{
			   ifm.height =content_right.clientHeight ;
			   ifm.width = content_right.clientWidth;
			}   
		}
		
		function change_src(url,e){
		     $("#rightpage").attr("src", url);
		     $("#dashboard-menu>li[class='active']").attr("class", "");
		     $(e.currentTarget.parentNode).attr("class","active");
		     
		     var remove=$("#dashboard-menu div[class='pointer']").remove();
		     if(remove)
		     {
		     	$(e.currentTarget.parentNode).prepend(remove);
		     }
		   };
	
		function islogin(){
			<#-- 通过后台验证session是否登录状态 -->
		
			$.ajax({
				type:"POST",
				url:"/site/springapp/user/get_login_user",
				success: function(user){
					if(user)
					{
						$('#l_login').html(user.userName);
						changeloginicon(user.userName);
						$("#li_info").css("display" , "inherit");
						$("#li_setting").css("display" , "inherit");
						getUserInfo();
					}
				}
			});
		
		}
		
		function changeloginicon(username){
			$('#l_login').html(username);
				$('#login').modal('hide');
				
				$("body").trigger("com.st.login");
				$("#tabs-634549").html("");
				$("#tabs-634549").append("<ul  class='nav nav-tabs'><li  class='active'><a href='#panel-123456' data-toggle='tab'>Logout</a></li></ul>"+
										"<div  class='tab-content'><div  class='tab-pane active' id='panel-123456'><div style='width:100%'><div>"+
										"<div  class='col-lg-8 col-lg-offset-2'><form  id='defaultForm' method='post' class='form-horizontal'>"+
										"<h3 class='h3'  style='text-align:center;'>You have already logged in to account!</h3>"+
										"<div class='form-group'  style='margin-left:200px !important;margin-top:20px;'>"+
										"<div  class='col-lg-9 col-lg-offset-3'>"+
										"<button type='button' class='btn btn-info' onClick='login_out()'>Logout</button></div></div>"+
										"</form></div></div></div></div></div>");
		}
	
		function login(){
			var userAccount = document.getElementById("userName").value;
			var userPassword = document.getElementById("password").value;
			$.ajax({
				type:"POST",
				url:"/site/springapp/user/login",
				data:{userAccount:userAccount,userPassword:userPassword},
				success: function(user){
					if(user)
					{
						changeloginicon(user.userName);
						$("#li_info").css("display" , "inherit");
						$("#li_setting").css("display" , "inherit");
						alert("Login successfully!");
						getUserInfo();
					}else{
						alert("Username or password error!!!");
					}
				}
			});
		}
		
		function login_out()
		{
			$.ajax({
				type:"POST",
				url:"/site/springapp/user/login_out",
				success: function(){
					//alert("Log out Success!");
					$('#l_login').click(login_out);
					$('#login').modal('hide');
					window.location.reload();
				}
			});
		
		}
		function initRightpage()
		{
			if(document.getElementById('rightpage')&&document.getElementById('rightpage').contentWindow&&document.getElementById('rightpage').contentWindow.topicView)
			{
			     document.getElementById('rightpage').contentWindow.topicView();
			}
		}
		function register(){
			var userName = document.getElementById("r_userName").value;
			var userAge = document.getElementById("r_userAge").value;
			var userAccount = document.getElementById("r_userAccount").value;
			var userPassword = document.getElementById("r_userPassword").value;
			var userRoles = document.getElementById("r_userRoles").value;
			var userInterest = document.getElementById("r_userInterest").value;
			$.ajax({
				type:"POST",
				url:"/site/springapp/user/register",
				data:{userName:userName,userAge:userAge,userAccount:userAccount,userPassword:userPassword,userRoles:userRoles,userInterest:userInterest},
				success: function(html){
					if(html){
						$('#login').modal('hide');
						$('#login').attr("display","none");
						//document.getElementById('login').style.display = "none";
						//$("div[class='modal-backdrop fade in']").css("display","none");
						alert("success");
					}else{
						alert("Registration failed!!!");
					}
				}
			});
		}

		
			
        $('body').bind("com.st.login com.st.rightpage.ready",function(){
        	console.info(arguments);
        	initRightpage();
        	iFrameHeight();
		});
		document.getElementById('rightpage').onload=function(){
			$("body").trigger("com.st.rightpage.ready");
		};
        $(function () {
        	$("body").trigger("com.st.ready");
        	$("#content_right").outerHeight(document.body.clientHeight-$("#navbar_top").outerHeight());
        	$("#sidebar-nav").outerHeight(document.body.clientHeight-$("#navbar_top").outerHeight());
            // jQuery Knobs
            $(".knob").knob();



            // jQuery UI Sliders
            $(".slider-sample1").slider({
                value: 100,
                min: 1,
                max: 500
            });
            $(".slider-sample2").slider({
                range: "min",
                value: 130,
                min: 1,
                max: 500
            });
            $(".slider-sample3").slider({
                range: true,
                min: 0,
                max: 500,
                values: [ 40, 170 ],
            });

            // jQuery Flot Chart
            var visits = [[1, 50], [2, 40], [3, 45], [4, 23],[5, 55],[6, 65],[7, 61],[8, 70],[9, 65],[10, 75],[11, 57],[12, 59]];
            var visitors = [[1, 25], [2, 50], [3, 23], [4, 48],[5, 38],[6, 40],[7, 47],[8, 55],[9, 43],[10,50],[11,47],[12, 39]];

            var plot = $.plot($("#statsChart"),
                [ { data: visits, label: "Signups"},
                 { data: visitors, label: "Visits" }], {
                    series: {
                        lines: { show: true,
                                lineWidth: 1,
                                fill: true, 
                                fillColor: { colors: [ { opacity: 0.1 }, { opacity: 0.13 } ] }
                             },
                        points: { show: true, 
                                 lineWidth: 2,
                                 radius: 3
                             },
                        shadowSize: 0,
                        stack: true
                    },
                    grid: { hoverable: true, 
                           clickable: true, 
                           tickColor: "#f9f9f9",
                           borderWidth: 0
                        },
                    legend: {
                            // show: false
                            labelBoxBorderColor: "#fff"
                        },  
                    colors: ["#a7b5c5", "#30a0eb"],
                    xaxis: {
                        ticks: [[1, "JAN"], [2, "FEB"], [3, "MAR"], [4,"APR"], [5,"MAY"], [6,"JUN"], 
                               [7,"JUL"], [8,"AUG"], [9,"SEP"], [10,"OCT"], [11,"NOV"], [12,"DEC"]],
                        font: {
                            size: 12,
                            family: "Open Sans, Arial",
                            variant: "small-caps",
                            color: "#697695"
                        }
                    },
                    yaxis: {
                        ticks:3, 
                        tickDecimals: 0,
                        font: {size:12, color: "#9da3a9"}
                    }
                 });

            function showTooltip(x, y, contents) {
                $('<div id="tooltip">' + contents + '</div>').css( {
                    position: 'absolute',
                    display: 'none',
                    top: y - 30,
                    left: x - 50,
                    color: "#fff",
                    padding: '2px 5px',
                    'border-radius': '6px',
                    'background-color': '#000',
                    opacity: 0.80
                }).appendTo("body").fadeIn(200);
            }

            var previousPoint = null;
            $("#statsChart").bind("plothover", function (event, pos, item) {
                if (item) {
                    if (previousPoint != item.dataIndex) {
                        previousPoint = item.dataIndex;

                        $("#tooltip").remove();
                        var x = item.datapoint[0].toFixed(0),
                            y = item.datapoint[1].toFixed(0);

                        var month = item.series.xaxis.ticks[item.dataIndex].label;

                        showTooltip(item.pageX, item.pageY,
                                    item.series.label + " of " + month + ": " + y);
                    }
                }
                else {
                    $("#tooltip").remove();
                    previousPoint = null;
                }
            });
        });
        $("#autocomplete-dynamic").hover(
        	function(){
        		$(this).css("background-color","#fff");
        	},
        	function()
        	{
        		if(this != document.activeElement)
        		{
	        		if(!($(this).val().toString().trim().length > 0))
	        		{
	        			$(this).css("background-color","#000");
	        		}
        		}
        	}
        );
        $("#autocomplete-dynamic").blur(function() {
			if(!($(this).val().toString().trim().length > 0))
    		{
    			$(this).css("background-color","#000");
    		}
		});
        //一开始需要把login和searchModal两个弹框display设为none，否则缩小界面布局会有问题
        $("#login").css("display","none");
        $("#searchModal").css("display","none");
        $("#info").css("display","none");
        $("#setting").css("display","none");
        var search_input;
        var search_result;
        var stopic_ids = [];
        $("#searchBn").click(function(){
        	search_input = "" + $("#autocomplete-dynamic").val().toString().trim() + "";
            if(search_input == "")
            	return;
            //searchTopic();
            stopic_ids = [];
        	//$('#searchModal').modal('show');
        });
        $("#autocomplete-dynamic").keydown(function(event){
        	if(event.keyCode != 13)
            {
				return;
            }
            search_input = "" + $("#autocomplete-dynamic").val().toString().trim() + "";
            if(search_input == "")
            	return;
            //searchTopic();
            stopic_ids = [];
        	//$('#searchModal').modal('show');
        });
        function searchTopic()
        {
        	$.ajax({
        		type:"POST",
        		//data:{search_input:search_input},
        		data:{query:search_input},
	    		//url:"/site/springapp/searchtopic/findTopicsByStr",
	    		url:"/site/springapp/searchtopic/searchDocsByName",
	    		success:function(data){
	    			search_result = JSON.parse(data);
	    			var searchHTML = "";
	    			for(var index in search_result)
	    			{
						console.info(search_result[index]);
						var url = "<@hst.webfile path="/images/0.jpg"/>";
						var imgurl = "<@hst.webfile path="/images/hook.png"/>";
						var backurl = "<@hst.webfile path=""/>" + search_result[index].icon;
						searchHTML += "<div class='topicDiv' id='topic_" + 
							search_result[index].topicId + 
							"' onclick='checkTopic(this);'><input class='stopic_url' type='hidden' value='" + search_result[index].url + "'" + 
							"<input class='stopic_id' type='hidden' value='" + search_result[index].topicId + "' /><div style='width:100%;height:80%;background:url(" + backurl + ") no-repeat;background-size: cover;display:inline-block;float:left;'></div>" +
							"<div class='topic_img' style='background:url(" + imgurl + ") no-repeat;'/></div>";
	    			}
							//"<span style='display:inline-block;float:left;width:100%;height:20%;text-align:left;font-size:16px;'>" + 
							//search_result[index].topicName + "</span>" + 
					$("#searchModal .modal-body").html(searchHTML);
	    		}
        	});
        }
        function checkTopic(obj)
        {
        	$("#searchModal").modal('hide');
        	var urlStr = "" + $("#" + obj.id + " .stopic_url").val();
        	$("#rightpage").attr("src", urlStr.trim());
        	//var img_obj = $("#" + obj.id + " .topic_img");
        	//if(img_obj.css("display") == "none")
        	//{
	        //	$("#" + obj.id + " .topic_img").css("display","inline-block");
	        //	stopic_ids.push($("#" + obj.id + " .stopic_id").val());
        	//}
			//else
			//{
			//	$("#" + obj.id + " .topic_img").css("display","none");
			//	arrayRemove(stopic_ids,$("#" + obj.id + " .stopic_id").val());
			//}
        }
        $("#searchSubmit").click(function(){
        	if(stopic_ids.toString().length > 0)
        	{
        		var isLogin = false;
	        	$.ajax({
	        		type:"POST",
	        		async:false,
		    		url:"/site/springapp/user/isLogin",
		    		success:function(result){
		    			isLogin = result;
		    		}
	        	});
	        	if(isLogin)
	        	{
	        		$.ajax({
	        		type:"POST",
	        		data:{topicIds:stopic_ids.toString()},
		    		url:"/site/springapp/searchtopic/checkTopicsByUser",
		    		success:function(result){
		    			console.info(result);
		    		}
	        	});
	        	}
        	}
        });
        function arrayRemove(arr,val)
        {
        	for (var i = 0; i < arr.length; i++) {
                if (arr[i] == val)
                arr.splice(i, 1);
            }
        }
        function autocomplete()
        {
	        <!-- autocomplete start -->
            // Setup jQuery ajax mock:
			var _sources = [];
	    	$.ajax({
	    		type:"POST",
	    		async:false,
	    		url:"/site/springapp/searchtopic/searchForSiteBean",
	    		success:function(data){
	    			if(data.length == 0)
	    				return;
	    			//console.info(data);
	    			var imagebaseurl = "<@hst.webfile path=""/>";
	    			for(var i in data)
	    			{
		    			var _clone = {"value":"","label":"","desc":"","icon":""};
		    			_clone["value"] = data[i].sitename;
		    			_clone["label"] = data[i].identifier;
		    			_clone["desc"] = data[i].url;
		    			if(data[i].imageurl.length == 0)
		    				_clone["icon"] = imagebaseurl + "/images/topic/2.jpg";
		    			else
		    				_clone["icon"] = imagebaseurl + data[i].imageurl;
		    			_sources.push(_clone);
	    			}
	    			//var topics = JSON.parse(data);
	    			//for(var i in topics)
	    			//{
	    			//	var _clone = {"value":"","data":""};
	    			//	_clone["value"] = topics[i].topicName;
	    			//	_clone["data"] = topics[i].topicId;
	    			//	_sources.push(_clone);
	    			//}
	    		}
	    	});
	    	
	    	$('#autocomplete-dynamic').autocomplete({
	        	lookup: _sources,
	        	minChars: 1
	    	});
	    	<!-- autocomplete end -->
        }
        
        window.onload=function(){  
                 changeDivHeight();  
                 change_src('staff_home',event);
                 autocomplete();
        }
        
        window.onresize=function(){  
             changeDivHeight();  
        }
        
        function changeDivHeight(){               
            var h = document.documentElement.clientHeight;
            if(h > 500) 
            	$('#sidebar-nav').perfectScrollbar({suppressScrollY:true});
			else
				$('#sidebar-nav').perfectScrollbar({suppressScrollY:false});
		}
		
		//change userpassword
		function changePwd(){
			var opwd = $("#opwd").val();
			var pwd = $("#pwd").val();
			$.ajax({
				type:"POST",
				url:"/site/springapp/user/changePwd",
				data:{oldPassword:opwd, userPassword:pwd},
				success:function(msg){
					//alert(msg);
					if(msg == true){
						alert("Change successed!");
						$("#setting").modal('hide');
//						$("#setting").css("display" ,"none");
					}else{
						alert("Change failed!");
					}
				}
			});
		}
		
		//get & set user info
		function setUserInfo(){
			var userName = $("#r_userName").val();
			var userAge = $("#r_userAge").val();
			var userRoles = $("#r_userRoles").val();
			var userInterest = $("r_#userInterest").val();
			var userAddress = $("#r_userAddress").val();
			$.ajax({
				type:"POST",
				url:"/site/springapp/user/changeUserInfo",		
				data:{userName:userName, userAge:userAge, userRoles:userRoles, userInterest:userInterest, userAddress:userAddress},
				success:function(msg){
					if(msg == true){
						alert("Change Successfully");
						getUserInfo();
						$("#info").modal('hide');
						//$("#info").css("display" ,"none");
					}else{
						alert("Change failed");
					}
				}
			});
		}
		
		function getUserInfo(){
			$.ajax({
				type:"POST",
				url:"/site/springapp/user/getPersonalInfo",		
				success:function(msg){
					var info = JSON.parse(msg);
					
					$("#r_userName").val(info.userName);
					$("#r_userAge").val(info.userAge);
					$("#r_userRoles").val(info.userRoles);
					$("#r_userInterest").val(info.userInterest);
					$("#r_userAddress").val(info.userAddress);
					
				}		
			});
		}
    </script>
	<div style="display:none;"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>