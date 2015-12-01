/**
 * jquery.calendario.js v1.0.0
 * http://www.codrops.com
 *
 * Licensed under the MIT license.
 * http://www.opensource.org/licenses/mit-license.php
 * 
 * Copyright 2012, Codrops
 * http://www.codrops.com
 */
;( function( $, window, undefined ) {
	
	'use strict';

	$.Calendario = function( options, element ) {
		
		this.$el = $( element );
		this._init( options );
		
	};

	// the options
	$.Calendario.defaults = {
		/*
		you can also pass:
		month : initialize calendar with this month (1-12). Default is today.
		year : initialize calendar with this year. Default is today.
		caldata : initial data/content for the calendar.
		caldata format:
		{
			'MM-DD-YYYY' : 'HTML Content',
			'MM-DD-YYYY' : 'HTML Content',
			'MM-DD-YYYY' : 'HTML Content'
			...
		}
		*/
		weeks : [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ],
		weekabbrs : [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat' ],
		months : [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December' ],
		monthabbrs : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ],
		// choose between values in options.weeks or options.weekabbrs
		displayWeekAbbr : false,
		// choose between values in options.months or options.monthabbrs
		displayMonthAbbr : false,
		// left most day in the calendar
		// 0 - Sunday, 1 - Monday, ... , 6 - Saturday
		startIn : 1,
		onDayClick : function( $el, $content, dateProperties ) { return false; }
	};

	$.Calendario.prototype = {

		_init : function( options ) {
			
			// options
			this.options = $.extend( true, {}, $.Calendario.defaults, options );
			this.today = new Date();
			this.month = ( isNaN( this.options.month ) || this.options.month == null) ? this.today.getMonth() : this.options.month - 1;
			this.year = ( isNaN( this.options.year ) || this.options.year == null) ? this.today.getFullYear() : this.options.year;
			this.caldata = this.options.caldata || {};
			this._generateTemplate();
			this._initEvents();

		},
		_initEvents : function() {

			var self = this;

			this.$el.on( 'click.calendario', 'div.fc-row > div', function() {

				var $cell = $( this ),
					idx = $cell.index(),
					$content = $cell.children( 'div' ),
					dateProp = {
						day : $cell.children( 'span.fc-date' ).text(),
						month : self.month + 1,
						monthname : self.options.displayMonthAbbr ? self.options.monthabbrs[ self.month ] : self.options.months[ self.month ],
						year : self.year,
						weekday : idx + self.options.startIn,
						weekdayname : self.options.weeks[ idx + self.options.startIn ]
					};

				if( dateProp.day ) {
					self.options.onDayClick( $cell, $content, dateProp );
				}

			} );

		},
		// Calendar logic based on http://jszen.blogspot.pt/2007/03/how-to-build-simple-calendar-with.html
		_generateTemplate : function( callback ) {

			var head = this._getHead(),
				body = this._getBody(),
				rowClass;

			switch( this.rowTotal ) {
				case 4 : rowClass = 'fc-four-rows'; break;
				case 5 : rowClass = 'fc-five-rows'; break;
				case 6 : rowClass = 'fc-six-rows'; break;
			}

			this.$cal = $( '<div class="fc-calendar ' + rowClass + '">' ).append( head, body );

			this.$el.find( 'div.fc-calendar' ).remove().end().append( this.$cal );

			if( callback ) { callback.call(); }

		},
		_getHead : function() {

			var html = '<div class="fc-head">';
		
			for ( var i = 0; i <= 6; i++ ) {

				var pos = i + this.options.startIn,
					j = pos > 6 ? pos - 6 - 1 : pos;

				html += '<div>';
				html += this.options.displayWeekAbbr ? this.options.weekabbrs[ j ] : this.options.weeks[ j ];
				html += '</div>';

			}

			html += '</div>';

			return html;

		},
		_getBody : function() {
			/*start*/
			Date.prototype.format=function(fmt) {        
				var o = {        
						"M+" : this.getMonth()+1, //月份        
						"d+" : this.getDate(), //日        
						"h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时        
								"H+" : this.getHours(), //小时        
								"m+" : this.getMinutes(), //分        
								"s+" : this.getSeconds(), //秒        
								"q+" : Math.floor((this.getMonth()+3)/3), //季度        
								"S" : this.getMilliseconds() //毫秒        
				};        
				var week = {        
						"0" : "\u65e5",        
						"1" : "\u4e00",        
						"2" : "\u4e8c",        
						"3" : "\u4e09",        
						"4" : "\u56db",        
						"5" : "\u4e94",        
						"6" : "\u516d"       
				};        
				if(/(y+)/.test(fmt)){        
					fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));        
				}        
				if(/(E+)/.test(fmt)){        
					fmt=fmt.replace(RegExp.$1, ((RegExp.$1.length>1) ? (RegExp.$1.length>2 ? "\u661f\u671f" : "\u5468") : "")+week[this.getDay()+""]);        
				}        
				for(var k in o){        
					if(new RegExp("("+ k +")").test(fmt)){        
						fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));        
					}        
				}        
				return fmt;        
			} 
			var datas;
			$.ajax({
				type:"POST",
				async: false,
				url:"/site/springapp/corporateDate/transferDatas",				
				success: function(data){
					datas = JSON.parse(data);
					console.info(datas);
					for(var index in datas)
					{
						var t = datas[index].date;
						//alert(t);
						var d =	new Date(parseInt(t));
						var year = d.getMonth();
						//alert(year);
						//var s=d.format('yyyy-MM-dd HH:mm:ss');
						//alert(s);
					}
				}
			});
			var arrData = new Array();
			var i = 0;
			for(var index in datas)
			{
				//alert(new Date(parseInt(datas[index].date)).getMonth() + 1);
				if(new Date(parseInt(datas[index].date)).getMonth() == this.month)
				{
					arrData[i] = datas[index];
					i++
				}
			}
			//alert(arrData.length);
			/*end*/
			var d = new Date( this.year, this.month + 1, 0 ),
				// number of days in the month
				monthLength = d.getDate(),
				firstDay = new Date( this.year, this.month, 1 );

			// day of the week
			this.startingDay = firstDay.getDay();

			var html = '<div class="fc-body"><div class="fc-row">',
				// fill in the days
				day = 1;

			// this loop is for weeks (rows)
			for ( var i = 0; i < 7; i++ ) {

				// this loop is for weekdays (cells)
				for ( var j = 0; j <= 6; j++ ) {

					var pos = this.startingDay - this.options.startIn,
						p = pos < 0 ? 6 + pos + 1 : pos,
						inner = '',
						today = this.month === this.today.getMonth() && this.year === this.today.getFullYear() && day === this.today.getDate(),
						content = '';
					
					if ( day <= monthLength && ( i > 0 || j >= p ) ) {
						var isDate = false;
						for(var index in arrData)
						{
							var t = arrData[index].date;
							var d =	new Date(parseInt(t));
							//alert(d.getMonth() == this.getMonth() && d.getDay() == day);
							if(d.getDay() == day)
							{
								inner += '<span class="fc-date">' + day + '</span><span class="fc-weekday">' + this.options.weekabbrs[ j + this.options.startIn > 6 ? j + this.options.startIn - 6 - 1 : j + this.options.startIn ] + '</span><span class="fc-des">'+arrData[index].name+'</span>';
								isDate = true;
							}
						}
						if(!isDate)
							inner += '<span class="fc-date">' + day + '</span><span class="fc-weekday">' + this.options.weekabbrs[ j + this.options.startIn > 6 ? j + this.options.startIn - 6 - 1 : j + this.options.startIn ] + '</span><span class="fc-des">'+'</span>';
						// this day is:
						var strdate = ( this.month + 1 < 10 ? '0' + ( this.month + 1 ) : this.month + 1 ) + '-' + ( day < 10 ? '0' + day : day ) + '-' + this.year,
							dayData = this.caldata[ strdate ];

						if( dayData ) {
							content = dayData;
						}

						if( content !== '' ) {
							inner += '<div>' + content + '</div>';
						}

						++day;

					}
					else {
						today = false;
					}

					var cellClasses = today ? 'fc-today ' : '';
					if( content !== '' ) {
						cellClasses += 'fc-content';
					}

					html += cellClasses !== '' ? '<div class="' + cellClasses + '">' : '<div>';
					html += inner;
					html += '</div>';

				}

				// stop making rows if we've run out of days
				if (day > monthLength) {
					this.rowTotal = i + 1;
					break;
				} 
				else {
					html += '</div><div class="fc-row">';
				}

			}
			html += '</div></div>';

			return html;

		},
		// based on http://stackoverflow.com/a/8390325/989439
		_isValidDate : function( date ) {

			date = date.replace(/-/gi,'');
			var month = parseInt( date.substring( 0, 2 ), 10 ),
				day = parseInt( date.substring( 2, 4 ), 10 ),
				year = parseInt( date.substring( 4, 8 ), 10 );

			if( ( month < 1 ) || ( month > 12 ) ) {
				return false;
			}
			else if( ( day < 1 ) || ( day > 31 ) )  {
				return false;
			}
			else if( ( ( month == 4 ) || ( month == 6 ) || ( month == 9 ) || ( month == 11 ) ) && ( day > 30 ) )  {
				return false;
			}
			else if( ( month == 2 ) && ( ( ( year % 400 ) == 0) || ( ( year % 4 ) == 0 ) ) && ( ( year % 100 ) != 0 ) && ( day > 29 ) )  {
				return false;
			}
			else if( ( month == 2 ) && ( ( year % 100 ) == 0 ) && ( day > 29 ) )  {
				return false;
			}

			return {
				day : day,
				month : month,
				year : year
			};

		},
		_move : function( period, dir, callback ) {

			if( dir === 'previous' ) {
				
				if( period === 'month' ) {
					this.year = this.month > 0 ? this.year : --this.year;
					this.month = this.month > 0 ? --this.month : 11;
				}
				else if( period === 'year' ) {
					this.year = --this.year;
				}

			}
			else if( dir === 'next' ) {

				if( period === 'month' ) {
					this.year = this.month < 11 ? this.year : ++this.year;
					this.month = this.month < 11 ? ++this.month : 0;
				}
				else if( period === 'year' ) {
					this.year = ++this.year;
				}

			}

			this._generateTemplate( callback );

		},
		/************************* 
		******PUBLIC METHODS *****
		**************************/
		getYear : function() {
			return this.year;
		},
		getMonth : function() {
			return this.month + 1;
		},
		getMonthName : function() {
			return this.options.displayMonthAbbr ? this.options.monthabbrs[ this.month ] : this.options.months[ this.month ];
		},
		// gets the cell's content div associated to a day of the current displayed month
		// day : 1 - [28||29||30||31]
		getCell : function( day ) {

			var row = Math.floor( ( day + this.startingDay - this.options.startIn ) / 7 ),
				pos = day + this.startingDay - this.options.startIn - ( row * 7 ) - 1;

			return this.$cal.find( 'div.fc-body' ).children( 'div.fc-row' ).eq( row ).children( 'div' ).eq( pos ).children( 'div' );

		},
		setData : function( caldata ) {

			caldata = caldata || {};
			$.extend( this.caldata, caldata );
			this._generateTemplate();

		},
		// goes to today's month/year
		gotoNow : function( callback ) {

			this.month = this.today.getMonth();
			this.year = this.today.getFullYear();
			this._generateTemplate( callback );

		},
		// goes to month/year
		goto : function( month, year, callback ) {

			this.month = month;
			this.year = year;
			this._generateTemplate( callback );

		},
		gotoPreviousMonth : function( callback ) {
			this._move( 'month', 'previous', callback );
		},
		gotoPreviousYear : function( callback ) {
			this._move( 'year', 'previous', callback );
		},
		gotoNextMonth : function( callback ) {
			this._move( 'month', 'next', callback );
		},
		gotoNextYear : function( callback ) {
			this._move( 'year', 'next', callback );
		}

	};
	
	var logError = function( message ) {

		if ( window.console ) {

			window.console.error( message );
		
		}

	};

	
	$.fn.calendario = function( options ) {

		var instance = $.data( this, 'calendario' );
		
		if ( typeof options === 'string' ) {
			
			var args = Array.prototype.slice.call( arguments, 1 );
			
			this.each(function() {
			
				if ( !instance ) {

					logError( "cannot call methods on calendario prior to initialization; " +
					"attempted to call method '" + options + "'" );
					return;
				
				}
				
				if ( !$.isFunction( instance[options] ) || options.charAt(0) === "_" ) {

					logError( "no such method '" + options + "' for calendario instance" );
					return;
				
				}
				
				instance[ options ].apply( instance, args );
			
			});
		
		} 
		else {
		
			this.each(function() {
				
				if ( instance ) {

					instance._init();
				
				}
				else {

					instance = $.data( this, 'calendario', new $.Calendario( options, this ) );
				
				}

			});
		
		}
		
		return instance;
		
	};
	
} )( jQuery, window );




var codropsEvents = {
		// 涓�湀
	'01-01-2013' : '<a>鍐滃巻鍗佷竴鏈堜簩鍗�/a> <a>鍏冩棪</a>',
	'01-05-2013' : '<a>鍐滃巻鍗佷竴鏈堜簩鍗佸洓</a><br><a href="http://www.17sucai.com/">灏忓瘨</a><br><a href="http://baike.baidu.com/view/25902.htm">鍦ㄥ叕鍘�鏈�-7鏃ヤ箣闂达紝澶槼浣嶄簬榛勭粡285掳</a>',
	'01-19-2013' : '<a>鍐滃巻鍗佷簩鏈堝垵鍏�/a> <a>鑵婂叓鑺�/a>',
	'01-20-2013' : '<a>鍐滃巻鍗佷簩鏈堝垵涔�/a><br><a href="http://www.17sucai.com/">澶у瘨</a><br><a href="http://baike.baidu.com/view/25921.htm">姣忓勾1鏈�0鏃ュ墠鍚庡お闃冲埌杈鹃粍缁�00掳鏃朵负澶у瘨</a>',
		
		// 浜屾湀
	'02-03-2013' : '<a>鍐滃巻鍗佷簩鏈堜簩鍗佷笁</a> <a>灏忓勾</a>',
	'02-04-2013' : '<a>鍐滃巻鍗佷簩鏈堜簩鍗佸洓</a><br><a href="http://www.17sucai.com/">绔嬫槬</a>',
	'02-09-2013' : '<a>鍐滃巻鍗佷簩鏈堜簩鍗佷節</a> <a>闄ゅ</a>',
	'02-10-2013' : '<a>鍐滃巻姝ｆ湀鍒濅竴</a> <a>鏄ヨ妭</a>',
	'02-14-2013' : '<a>鍐滃巻姝ｆ湀鍒濅簲</a> <a>鎯呬汉鑺�/a>',
	'02-18-2013' : '<a>鍐滃巻姝ｆ湀鍒濅節</a><br><a href="http://www.17sucai.com/">闆ㄦ按</a>',
	'02-24-2013' : '<a>鍐滃巻姝ｆ湀鍗佷簲</a> <a>鍏冨鑺�/a>',
		
		// 涓夋湀
	'03-05-2013' : '<a>鍐滃巻姝ｆ湀浜屽崄鍥�/a><br><a href="http://www.17sucai.com/">鎯婅洶</a>',
	'03-08-2013' : '<a>鍐滃巻姝ｆ湀浜屽崄涓�/a> <a>濡囧コ鑺�/a>',
	'03-12-2013' : '<a>鍐滃巻浜屾湀鍒濅竴</a> <a>妞嶆爲鑺�/a>',
	'03-20-2013' : '<a>鍐滃巻浜屾湀鍒濅節</a><br><a href="http://www.17sucai.com/">鏄ュ垎</a>',
		
		// 鍥涙湀
	'04-01-2013' : '<a>鍐滃巻浜屾湀浜屽崄涓�/a> <a>鎰氫汉鑺�/a>',
	'04-04-2013' : '<a>鍐滃巻浜屾湀浜屽崄鍥�/a> <a>娓呮槑鑺�/a><br><a href="http://www.17sucai.com/">娓呮槑</a>',
	'04-20-2013' : '<a>鍐滃巻涓夋湀鍗佷竴</a><br><a href="http://www.17sucai.com/">璋烽洦</a>',
		
		// 浜旀湀
	'05-01-2013' : '<a>鍐滃巻涓夋湀浜屽崄浜�/a> <a>鍔冲姩鑺�/a>',
	'05-04-2013' : '<a>鍐滃巻涓夋湀浜屽崄浜�/a> <a>闈掑勾鑺�/a>',
	'05-05-2013' : '<a>鍐滃巻涓夋湀浜屽崄鍏�/a><br><a href="http://www.17sucai.com/">绔嬪</a>',
	'05-12-2013' : '<a>鍐滃巻鍥涙湀鍒濅笁</a> <a>姣嶄翰鑺�/a>',
	'05-21-2013' : '<a>鍐滃巻鍥涙湀鍗佷簩</a><br><a href="http://www.17sucai.com/">灏忔弧</a>',
		
		// 鍏湀
	'06-01-2013' : '<a>鍐滃巻鍥涙湀浜屽崄涓�/a> <a>鍎跨鑺�/a>',
	'06-05-2013' : '<a>鍐滃巻鍥涙湀浜屽崄涓�/a><br><a href="http://www.17sucai.com/">鑺掔</a>',
	'06-12-2013' : '<a>鍐滃巻浜旀湀鍒濅簲</a> <a>绔崍鑺�/a>',
	'06-16-2013' : '<a>鍐滃巻浜旀湀鍒濅節</a> <a>鐖朵翰鑺�/a>',
	'06-21-2013' : '<a>鍐滃巻浜旀湀鍗佸洓</a><br><a href="http://www.17sucai.com/">澶忚嚦</a>',
		
		// 涓冩湀
	'07-01-2013' : '<a>鍐滃巻浜旀湀浜屽崄鍥�/a> <a>寤哄厷鑺�/a>',
	'07-07-2013' : '<a>鍐滃巻浜旀湀涓夊崄</a><br><a href="http://www.17sucai.com/">灏忔殤</a>',
	'07-22-2013' : '<a>鍐滃巻鍏湀鍗佷簲</a><br><a href="http://www.17sucai.com/">澶ф殤</a>',
		
		// 鍏湀
	'08-01-2013' : '<a>鍐滃巻鍏湀浜屽崄浜�/a> <a>寤哄啗鑺�/a>',
	'08-07-2013' : '<a>鍐滃巻涓冩湀鍒濅竴</a><br><a href="http://www.17sucai.com/">绔嬬</a>',
	'08-13-2013' : '<a>鍐滃巻涓冩湀鍒濅竷</a> <a>涓冨</a>',
	'08-23-2013' : '<a>鍐滃巻涓冩湀鍗佷竷</a><br><a href="http://www.17sucai.com/">澶勬殤</a>',
		
		// 涔濇湀
	'09-07-2013' : '<a>鍐滃巻鍏湀鍒濅笁</a><br><a href="http://www.17sucai.com/">鐧介湶</a>',
	'09-10-2013' : '<a>鍐滃巻鍏湀鍒濆叚</a> <a>鏁欏笀鑺�/a>',
	'09-19-2013' : '<a>鍐滃巻鍏湀鍗佷簲</a> <a>涓鑺�/a>',
	'09-23-2013' : '<a>鍐滃巻鍏湀鍗佷節</a><br><a href="http://www.17sucai.com/">绉嬪垎</a>',
		
		// 鍗佹湀
	'10-01-2013' : '<a>鍐滃巻鍏湀浜屽崄涓�/a> <a>鍥藉簡鑺�/a>',
	'10-08-2013' : '<a>鍐滃巻涔濇湀鍒濆洓</a><br><a href="http://www.17sucai.com/">瀵掗湶</a>',
	'10-13-2013' : '<a>鍐滃巻涔濇湀鍒濅節</a> <a>閲嶉槼鑺�/a>',
	'10-23-2013' : '<a>鍐滃巻涔濇湀鍗佷節</a><br><a href="http://www.17sucai.com/">闇滈檷</a>',
		
		// 鍗佷竴鏈�
	'11-07-2013' : '<a>鍐滃巻鍗佹湀鍒濅簲</a><br><a href="http://www.17sucai.com/">绔嬪啲</a>',
	'11-22-2013' : '<a>鍐滃巻鍗佹湀浜屽崄</a><br><a href="http://www.17sucai.com/">灏忛洩</a>',
	'11-28-2013' : '<a>鍐滃巻鍗佹湀浜屽崄鍏�/a> <a>鎰熸仼鑺�/a>',
		
		// 鍗佷簩鏈�
	'12-07-2013' : '<a>鍐滃巻鍗佷竴鏈堝垵浜�/a><br><a href="http://www.17sucai.com/">澶ч洩</a>',
	'12-22-2013' : '<a>鍐滃巻鍗佷竴鏈堜簩鍗�/a><br><a href="http://www.17sucai.com/">鍐嚦</a>',
	'12-24-2013' : '<a>鍐滃巻鍗佷竴鏈堜簩鍗佷簩</a> <a>骞冲畨澶�/a>',
	'12-25-2013' : '<a>鍐滃巻鍗佷竴鏈堜簩鍗佷笁</a> <a>鍦ｈ癁鑺�/a>'
};



			$(function() {
				var cal = $( '#calendar' ).calendario( {
						onDayClick : function( $el, $contentEl, dateProperties ) {

							for( var key in dateProperties ) {
								console.log( key + ' = ' + dateProperties[ key ] );
							}

						},
						caldata : codropsEvents
					} ),
					$month = $( '#custom-month' ).html( cal.getMonthName() ),
					$year = $( '#custom-year' ).html( cal.getYear() );

				$( '#custom-next' ).on( 'click', function() {
					cal.gotoNextMonth( updateMonthYear );
				} );
				$( '#custom-prev' ).on( 'click', function() {
					cal.gotoPreviousMonth( updateMonthYear );
				} );
				$( '#custom-current' ).on( 'click', function() {
					cal.gotoNow( updateMonthYear );
				} );

				function updateMonthYear() {				
					$month.html( cal.getMonthName() );
					$year.html( cal.getYear() );
				}

				// you can also add more data later on. As an example:
				/*
				someElement.on( 'click', function() {
					
					cal.setData( {
						'03-01-2013' : '<a href="#">testing</a>',
						'03-10-2013' : '<a href="#">testing</a>',
						'03-12-2013' : '<a href="#">testing</a>'
					} );
					// goes to a specific month/year
					cal.goto( 2, 2013, updateMonthYear );

				} );
				*/
				//创建事件方法
				
			});