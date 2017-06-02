<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="后台管理首页"  base_keywords="后台管理首页" openIndex=0 activeIndex=0>
	
	<#-- @base 中间的内容将嵌套至 base 宏中的#nested处 -->
    <div class="main-content" style="">
			
			<script type="text/javascript">
				jQuery(document).ready(function($)
				{	
					// Notifications
					setTimeout(function()
					{			
						var opts = {
							"closeButton": true,
							"debug": false,
							"positionClass": "toast-top-right toast-default",
							"toastClass": "black",
							"onclick": null,
							"showDuration": "100",
							"hideDuration": "1000",
							"timeOut": "5000",
							"extendedTimeOut": "1000",
							"showEasing": "swing",
							"hideEasing": "linear",
							"showMethod": "fadeIn",
							"hideMethod": "fadeOut"
						};
				
						toastr.info("You have been awarded with 1 year free subscription. Enjoy it!", "Account Subcription Updated", opts);
					}, 3000);
					
					// Charts
					var xenonPalette = ['#68b828','#7c38bc','#0e62c7','#fcd036','#4fcdfc','#00b19d','#ff6264','#f7aa47'];
					
					// Pageviews Visitors Chart
					var i = 0,
						line_chart_data_source = [
						{ id: ++i, part1: 4, part2: 2 },
						{ id: ++i, part1: 5, part2: 3 },
						{ id: ++i, part1: 5, part2: 3 },
						{ id: ++i, part1: 4, part2: 2 },
						{ id: ++i, part1: 3, part2: 1 },
						{ id: ++i, part1: 3, part2: 2 },
						{ id: ++i, part1: 5, part2: 3 },
						{ id: ++i, part1: 7, part2: 4 },
						{ id: ++i, part1: 9, part2: 5 },
						{ id: ++i, part1: 7, part2: 4 },
						{ id: ++i, part1: 7, part2: 3 },
						{ id: ++i, part1: 11, part2: 6 },
						{ id: ++i, part1: 10, part2: 8 },
						{ id: ++i, part1: 9, part2: 7 },
						{ id: ++i, part1: 8, part2: 7 },
						{ id: ++i, part1: 8, part2: 7 },
						{ id: ++i, part1: 8, part2: 7 },
						{ id: ++i, part1: 8, part2: 6 },
						{ id: ++i, part1: 15, part2: 5 },
						{ id: ++i, part1: 10, part2: 5 },
						{ id: ++i, part1: 9, part2: 6 },
						{ id: ++i, part1: 9, part2: 3 },
						{ id: ++i, part1: 8, part2: 5 },
						{ id: ++i, part1: 8, part2: 4 },
						{ id: ++i, part1: 9, part2: 5 },
						{ id: ++i, part1: 8, part2: 6 },
						{ id: ++i, part1: 8, part2: 5 },
						{ id: ++i, part1: 7, part2: 6 },
						{ id: ++i, part1: 7, part2: 5 },
						{ id: ++i, part1: 6, part2: 5 },
						{ id: ++i, part1: 7, part2: 6 },
						{ id: ++i, part1: 7, part2: 5 },
						{ id: ++i, part1: 8, part2: 5 },
						{ id: ++i, part1: 6, part2: 5 },
						{ id: ++i, part1: 5, part2: 4 },
						{ id: ++i, part1: 5, part2: 3 },
						{ id: ++i, part1: 6, part2: 3 },
					];
					
					$("#pageviews-visitors-chart").dxChart({
						dataSource: line_chart_data_source,
						commonSeriesSettings: {
							argumentField: "id",
							point: { visible: true, size: 5, hoverStyle: {size: 7, border: 0, color: 'inherit'} },
							line: {width: 1, hoverStyle: {width: 1}}
						},
						series: [
							{ valueField: "part1", name: "Pageviews", color: "#68b828" },
							{ valueField: "part2", name: "Visitors", color: "#eeeeee" },
						],
						legend: {
							position: 'inside',
							paddingLeftRight: 5
						},
						commonAxisSettings: {
							label: {
								visible: false
							},
							grid: {
								visible: true,
								color: '#f9f9f9'
							}
						},
						valueAxis: {
							max: 25
						},
						argumentAxis: {
					        valueMarginsEnabled: false
					    },
					});
					
					
					
					// Server Uptime Chart
					var bar1_data_source = [
						{ year: 1, 	europe: 10, americas: 0, africa: 5 },
						{ year: 2, 	europe: 20, americas: 5, africa: 15 },
						{ year: 3, 	europe: 30, americas: 10, africa: 15 },
						{ year: 4, 	europe: 40, americas: 15, africa: 30 },
						{ year: 5, 	europe: 30, americas: 10, africa: 20 },
						{ year: 6, 	europe: 20, americas: 5,  africa: 10 },
						{ year: 7, 	europe: 10, americas: 15, africa: 0 },
						{ year: 8, 	europe: 20, americas: 25, africa: 8 },
						{ year: 9, 	europe: 30, americas: 35, africa: 16 },
						{ year: 10,	europe: 40, americas: 45, africa: 24 },
						{ year: 11,	europe: 50, americas: 40, africa: 32 },
					];
					
					$("#server-uptime-chart").dxChart({
						dataSource: [
							{id: ++i, 	sales: 1},
							{id: ++i, 	sales: 2},
							{id: ++i, 	sales: 3},
							{id: ++i, 	sales: 4},
							{id: ++i, 	sales: 5},
							{id: ++i, 	sales: 4},
							{id: ++i, 	sales: 5},
							{id: ++i, 	sales: 6},
							{id: ++i, 	sales: 7},
							{id: ++i, 	sales: 6},
							{id: ++i, 	sales: 5},
							{id: ++i, 	sales: 4},
							{id: ++i, 	sales: 5},
							{id: ++i, 	sales: 4},
							{id: ++i, 	sales: 4},
							{id: ++i, 	sales: 3},
							{id: ++i, 	sales: 4},
						],
					 
						series: {
							argumentField: "id",
							valueField: "sales",
							name: "Sales",
							type: "bar",
							color: '#7c38bc'
						},
						commonAxisSettings: {
							label: {
								visible: false
							},
							grid: {
								visible: false
							}
						},
						legend: {
							visible: false
						},
						argumentAxis: {
					        valueMarginsEnabled: true
					    },
						valueAxis: {
							max: 12
						},
						equalBarWidth: {
							width: 11
						}
					});
					
					
					
					// Average Sales Chart
					var doughnut1_data_source = [
						{region: "Asia", val: 4119626293},
						{region: "Africa", val: 1012956064},
						{region: "Northern America", val: 344124520},
						{region: "Latin America and the Caribbean", val: 590946440},
						{region: "Europe", val: 727082222},
						{region: "Oceania", val: 35104756},
						{region: "Oceania 1", val: 727082222},
						{region: "Oceania 3", val: 727082222},
						{region: "Oceania 4", val: 727082222},
						{region: "Oceania 5", val: 727082222},
					], timer;
					
					$("#sales-avg-chart div").dxPieChart({
						dataSource: doughnut1_data_source,
						tooltip: {
							enabled: false,
						  	format:"millions",
							customizeText: function() { 
								return this.argumentText + "<br/>" + this.valueText;
							}
						},
						size: {
							height: 90
						},
						legend: {
							visible: false
						},  
						series: [{
							type: "doughnut",
							argumentField: "region"
						}],
						palette: ['#5e9b4c', '#6ca959', '#b9f5a6'],
					});
					
					
					
					// Pageview Stats
					$('#pageviews-stats').dxBarGauge({
						startValue: -50,
						endValue: 50,
						baseValue: 0,
						values: [-21.3, 14.8, -30.9, 45.2],
						label: {
							customizeText: function (arg) {
								return arg.valueText + '%';
							}
						},
						//palette: 'ocean',
						palette: ['#68b828','#7c38bc','#0e62c7','#fcd036','#4fcdfc','#00b19d','#ff6264','#f7aa47'],
						margin : {
							top: 0
						}
					});
					
					var firstMonth = {
						dataSource: getFirstMonthViews(),
						argumentField: 'month',
						valueField: '2014',
						type: 'area',
						showMinMax: true,
						lineColor: '#68b828',
						minColor: '#68b828',
						maxColor: '#7c38bc',
						showFirstLast: false,
					},
					secondMonth = {
						dataSource: getSecondMonthViews(),
						argumentField: 'month',
						valueField: '2014',
						type: 'splinearea',
						lineColor: '#68b828',
						minColor: '#68b828',
						maxColor: '#7c38bc',
						pointSize: 6,
						showMinMax: true,
						showFirstLast: false
					},
					thirdMonth = {
						dataSource: getThirdMonthViews(),
						argumentField: 'month',
						valueField: '2014',
						type: 'splinearea',
						lineColor: '#68b828',
						minColor: '#68b828',
						maxColor: '#7c38bc',
						pointSize: 6,
						showMinMax: true,
						showFirstLast: false
					};
					
					function getFirstMonthViews() {
						return [{ month: 1, 2014: 7341 },
						{ month: 2, 2014: 7016 },
						{ month: 3, 2014: 7202 },
						{ month: 4, 2014: 7851 },
						{ month: 5, 2014: 7481 },
						{ month: 6, 2014: 6532 },
						{ month: 7, 2014: 6498 },
						{ month: 8, 2014: 7191 },
						{ month: 9, 2014: 7596 },
						{ month: 10, 2014: 8057 },
						{ month: 11, 2014: 8373 },
						{ month: 12, 2014: 8636 }];
					};
					
					function getSecondMonthViews() {
						return [{ month: 1, 2014: 189742 },
						{ month: 2, 2014: 181623 },
						{ month: 3, 2014: 205351 },
						{ month: 4, 2014: 245625 },
						{ month: 5, 2014: 261319 },
						{ month: 6, 2014: 192786 },
						{ month: 7, 2014: 194752 },
						{ month: 8, 2014: 207017 },
						{ month: 9, 2014: 212665 },
						{ month: 10, 2014: 233580 },
						{ month: 11, 2014: 231503 },
						{ month: 12, 2014: 232824 }];
					};
					
					function getThirdMonthViews() {
						return [{ month: 1, 2014: 398},
						{ month: 2, 2014: 422},
						{ month: 3, 2014: 431},
						{ month: 4, 2014: 481},
						{ month: 5, 2014: 551},
						{ month: 6, 2014: 449},
						{ month: 7, 2014: 442},
						{ month: 8, 2014: 482},
						{ month: 9, 2014: 517},
						{ month: 10, 2014: 566},
						{ month: 11, 2014: 630},
						{ month: 12, 2014: 737}];
					};
					
					
					$('.first-month').dxSparkline(firstMonth);
					$('.second-month').dxSparkline(secondMonth);
					$('.third-month').dxSparkline(thirdMonth);
					
					
					// Realtime Network Stats
					var i = 0,
						rns_values = [130,150],
						rns2_values = [39,50],
						realtime_network_stats = [];
					
					for(i=0; i<=100; i++)
					{
						realtime_network_stats.push({ id: i, x1: between(rns_values[0], rns_values[1]), x2: between(rns2_values[0], rns2_values[1])});
					}
					
					$("#realtime-network-stats").dxChart({
						dataSource: realtime_network_stats,
						commonSeriesSettings: {
							type: "area",
							argumentField: "id"
						},
						series: [
							{ valueField: "x1", name: "Packets Sent", color: '#7c38bc', opacity: .4 },
							{ valueField: "x2", name: "Packets Received", color: '#000', opacity: .5},
						],
						legend: {
							verticalAlignment: "bottom",
							horizontalAlignment: "center"
						},
						commonAxisSettings: {
							label: {
								visible: false
							},
							grid: {
								visible: true,
								color: '#f5f5f5'
							}
						},
						legend: {
							visible: false
						},
						argumentAxis: {
					        valueMarginsEnabled: false
					    },
						valueAxis: {
							max: 500
						},
						animation: {
							enabled: false
						}
					}).data('iCount', i);
					
					$('#network-realtime-gauge').dxCircularGauge({
						scale: {
							startValue: 0,
							endValue: 200,
							majorTick: {
								tickInterval: 50
							}
						},
						rangeContainer: {
							palette: 'pastel',
							width: 3,
							ranges: [
								{ startValue: 0, endValue: 50, color: "#7c38bc" },
								{ startValue: 50, endValue: 100, color: "#7c38bc" },
								{ startValue: 100, endValue: 150, color: "#7c38bc" },
								{ startValue: 150, endValue: 200, color: "#7c38bc" },
							],
						},
						value: 140,
						valueIndicator: {
							offset: 10,
							color: '#7c38bc',
							type: 'triangleNeedle',
							spindleSize: 12
						}
					});
					
					setInterval(function(){  networkRealtimeChartTick(rns_values, rns2_values); }, 1000);
					setInterval(function(){ networkRealtimeGaugeTick(); }, 2000);
					setInterval(function(){ networkRealtimeMBupdate(); }, 3000);
					
					
					
					// CPU Usage Gauge
					$("#cpu-usage-gauge").dxCircularGauge({
						scale: {
							startValue: 0,
							endValue: 100,
							majorTick: {
								tickInterval: 25
							}
						},
						rangeContainer: {
							palette: 'pastel',
							width: 3,
							ranges: [
								{ startValue: 0, endValue: 25, color: "#68b828" },
								{ startValue: 25, endValue: 50, color: "#68b828" },
								{ startValue: 50, endValue: 75, color: "#68b828" },
								{ startValue: 75, endValue: 100, color: "#d5080f" },
							],
						},
						value: between(30, 90),
						valueIndicator: {
							offset: 10,
							color: '#68b828',
							type: 'rectangleNeedle',
							spindleSize: 12
						}
					});
					
					
					// Resize charts
					$(window).on('xenon.resize', function()
					{
						$("#pageviews-visitors-chart").data("dxChart").render();
						$("#server-uptime-chart").data("dxChart").render();
						$("#realtime-network-stats").data("dxChart").render();
						
						$('.first-month').data("dxSparkline").render();
						$('.second-month').data("dxSparkline").render();
						$('.third-month').data("dxSparkline").render();
					});
					
				});
				
				function networkRealtimeChartTick(min_max, min_max2)
				{
					var $ = jQuery,
						i = 0;
					
					var chart_data = $('#realtime-network-stats').dxChart('instance').option('dataSource');
					
					var count = $('#realtime-network-stats').data('iCount');
					
					$('#realtime-network-stats').data('iCount', count + 1);
					
					chart_data.shift();
					chart_data.push({id: count + 1, x1: between(min_max[0],min_max[1]), x2: between(min_max2[0],min_max2[1])});
					
					$('#realtime-network-stats').dxChart('instance').option('dataSource', chart_data);
				}
				
				function networkRealtimeGaugeTick()
				{
					var nr_gauge = jQuery('#network-realtime-gauge').dxCircularGauge('instance');
					
					nr_gauge.value( between(50,200) );
				}
				
				function networkRealtimeMBupdate()
				{
					var $el = jQuery("#network-mbs-packets"),
						options = {
							useEasing : true, 
							useGrouping : true, 
							separator : ',', 
							decimal : '.', 
							prefix : '' ,
							suffix : 'mb/s' 
						},
						cntr = new countUp($el[0], parseFloat($el.text().replace('mb/s')), parseFloat(between(10,25) + 1/between(15,30)), 2, 1.5, options);
						
					cntr.start();
				}
				
				function between(randNumMin, randNumMax)
				{
					var randInt = Math.floor((Math.random() * ((randNumMax + 1) - randNumMin)) + randNumMin);
					
					return randInt;
				}
			</script>
			
			<div class="row">
				<div class="col-sm-3">
					
					<div class="xe-widget xe-counter" data-count=".num" data-from="0" data-to="99.9" data-suffix="%" data-duration="2">
						<div class="xe-icon">
							<i class="linecons-cloud"></i>
						</div>
						<div class="xe-label">
							<strong class="num">99.9%</strong>
							<span>Server uptime</span>
						</div>
					</div>
					
					<div class="xe-widget xe-counter xe-counter-purple" data-count=".num" data-from="1" data-to="117" data-suffix="k" data-duration="3" data-easing="false">
						<div class="xe-icon">
							<i class="linecons-user"></i>
						</div>
						<div class="xe-label">
							<strong class="num">117k</strong>
							<span>Users Total</span>
						</div>
					</div>
					
					<div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="1000" data-to="2470" data-duration="4" data-easing="true">
						<div class="xe-icon">
							<i class="linecons-camera"></i>
						</div>
						<div class="xe-label">
							<strong class="num">2,470</strong>
							<span>New Daily Photos</span>
						</div>
					</div>
					
				</div>
				<div class="col-sm-6">
					
					<div class="chart-item-bg">
						<div class="chart-label">
							<div class="h3 text-secondary text-bold" data-count="this" data-from="0.00" data-to="14.85" data-suffix="%" data-duration="1">14.85%</div>
							<span class="text-medium text-muted">More visitors</span>
						</div>
						<div id="pageviews-visitors-chart" style="height: 298px; -webkit-user-select: none;" class="dx-visibility-change-handler"><svg width="460" height="298" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxc dxc-chart" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden;"><defs><clipPath id="DevExpress_1"><rect x="0" y="0" width="460" height="298" rx="0" ry="0" fill="none" stroke="none" stroke-width="0"></rect></clipPath><clipPath id="DevExpress_2"><rect x="0" y="0" width="460" height="298" rx="0" ry="0" fill="none" stroke="none" stroke-width="0"></rect></clipPath><pattern id="DevExpress_38" width="5" height="5" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="5" height="5" rx="0" ry="0" fill="#68b828" opacity="0.75"></rect><path stroke-width="2" stroke="#68b828" d="M 2.5 -2.5 L -2.5 2.5M 0 5 L 5 0 M 7.5 2.5 L 2.5 7.5"></path></pattern><pattern id="DevExpress_39" width="5" height="5" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="5" height="5" rx="0" ry="0" fill="#68b828" opacity="0.5"></rect><path stroke-width="2" stroke="#68b828" d="M 2.5 -2.5 L -2.5 2.5M 0 5 L 5 0 M 7.5 2.5 L 2.5 7.5"></path></pattern><pattern id="DevExpress_40" width="5" height="5" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="5" height="5" rx="0" ry="0" fill="#eeeeee" opacity="0.75"></rect><path stroke-width="2" stroke="#eeeeee" d="M 2.5 -2.5 L -2.5 2.5M 0 5 L 5 0 M 7.5 2.5 L 2.5 7.5"></path></pattern><pattern id="DevExpress_41" width="5" height="5" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="5" height="5" rx="0" ry="0" fill="#eeeeee" opacity="0.5"></rect><path stroke-width="2" stroke="#eeeeee" d="M 2.5 -2.5 L -2.5 2.5M 0 5 L 5 0 M 7.5 2.5 L 2.5 7.5"></path></pattern><filter id="DevExpress_42" x="-50%" y="-50%" width="200%" height="200%"><feGaussianBlur in="SourceGraphic" result="gaussianBlurResult" stdDeviation="2"></feGaussianBlur><feOffset in="gaussianBlurResult" result="offsetResult" dx="0" dy="4"></feOffset><feFlood result="floodResult" flood-color="#000000" flood-opacity="0.4"></feFlood><feComposite in="floodResult" in2="offsetResult" operator="in" result="compositeResult"></feComposite><feComposite in="SourceGraphic" in2="compositeResult" operator="over"></feComposite></filter></defs><g class="dxc-background"></g><g class="dxc-strips-group"><g class="dxc-h-strips" clip-path="url(#DevExpress_2)"></g><g class="dxc-v-strips" clip-path="url(#DevExpress_2)"></g></g><g class="dxc-axes-group"><g class="dxc-h-axis" clip-path="url(#DevExpress_1)"><g class="dxc-grid"><path stroke-width="1" stroke="#f9f9f9" d="M 51.5 0 L 51.5 298"></path><path stroke-width="1" stroke="#f9f9f9" d="M 115.5 0 L 115.5 298"></path><path stroke-width="1" stroke="#f9f9f9" d="M 179.5 0 L 179.5 298"></path><path stroke-width="1" stroke="#f9f9f9" d="M 243.5 0 L 243.5 298"></path><path stroke-width="1" stroke="#f9f9f9" d="M 307.5 0 L 307.5 298"></path><path stroke-width="1" stroke="#f9f9f9" d="M 371.5 0 L 371.5 298"></path><path stroke-width="1" stroke="#f9f9f9" d="M 434.5 0 L 434.5 298"></path></g><g class="dxc-elements"></g><g class="dxc-line"></g><g class="dxc-title"></g></g><g class="dxc-v-axis" clip-path="url(#DevExpress_1)"><g class="dxc-grid"><path stroke-width="1" stroke="#f9f9f9" d="M 0 298.5 L 460 298.5"></path><path stroke-width="1" stroke="#f9f9f9" d="M 0 265.5 L 460 265.5"></path><path stroke-width="1" stroke="#f9f9f9" d="M 0 233.5 L 460 233.5"></path><path stroke-width="1" stroke="#f9f9f9" d="M 0 200.5 L 460 200.5"></path><path stroke-width="1" stroke="#f9f9f9" d="M 0 167.5 L 460 167.5"></path><path stroke-width="1" stroke="#f9f9f9" d="M 0 135.5 L 460 135.5"></path><path stroke-width="1" stroke="#f9f9f9" d="M 0 102.5 L 460 102.5"></path><path stroke-width="1" stroke="#f9f9f9" d="M 0 70.5 L 460 70.5"></path><path stroke-width="1" stroke="#f9f9f9" d="M 0 37.5 L 460 37.5"></path><path stroke-width="1" stroke="#f9f9f9" d="M 0 4.5 L 460 4.5"></path></g><g class="dxc-elements"></g><g class="dxc-line"></g><g class="dxc-title"></g></g></g><g class="dxc-constant-lines-group"><g class="dxc-h-constant-lines"></g><g class="dxc-v-constant-lines"></g></g><g class="dxc-strips-labels-group"><g class="dxc-axis-labels"></g><g class="dxc-axis-labels"></g></g><g class="dxc-border"></g><g class="dxc-series-group"><g class="dxc-series"><g class="dxc-elements" stroke="#68b828" stroke-width="1" clip-path="url(#DevExpress_2)"><path stroke-width="1" d="M 0 254 L 13 244.5 L 26 244.5 L 38 254 L 51 265.5 L 64 265.5 L 77 244 L 89 222 L 102 200 L 115 222.5 L 128 222.5 L 141 178 L 153 189 L 166 200 L 179 211.5 L 192 211.5 L 204 211.5 L 217 211.5 L 230 135 L 243 189 L 256 200.5 L 268 200.5 L 281 211.5 L 294 211.5 L 307 200 L 319 211.5 L 332 211.5 L 345 222.5 L 358 222.5 L 371 233 L 383 222.5 L 396 222.5 L 409 211 L 422 233 L 434 244.5 L 447 244.5 L 460 233"></path></g><g fill="#68b828" stroke="#68b828" stroke-width="0" r="2.5" visibility="visible" class="dxc-markers" opacity="1"><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(0,254)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(13,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(26,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(38,254)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(51,265)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(64,265)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(77,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(89,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(102,200)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(115,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(128,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(141,178)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(153,189)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(166,200)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(179,211)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(192,211)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(204,211)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(217,211)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(230,135)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(243,189)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(256,200)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(268,200)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(281,211)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(294,211)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(307,200)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(319,211)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(332,211)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(345,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(358,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(371,233)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(383,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(396,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(409,211)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(422,233)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(434,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(447,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(460,233)"></circle></g></g><g class="dxc-series"><g class="dxc-elements" stroke="#eeeeee" stroke-width="1" clip-path="url(#DevExpress_2)"><path stroke-width="1" d="M 0 276 L 13 265.5 L 26 265.5 L 38 276 L 51 287 L 64 276 L 77 265 L 89 254 L 102 244 L 115 254 L 128 265 L 141 233 L 153 211 L 166 222.5 L 179 222.5 L 192 222.5 L 204 222.5 L 217 233 L 230 244.5 L 243 244.5 L 256 233 L 268 265 L 281 244 L 294 254 L 307 244 L 319 233 L 332 244 L 345 233 L 358 244.5 L 371 244.5 L 383 233 L 396 244.5 L 409 244.5 L 422 244.5 L 434 254 L 447 265.5 L 460 265.5"></path></g><g fill="#eeeeee" stroke="#eeeeee" stroke-width="0" r="2.5" visibility="visible" class="dxc-markers" opacity="1"><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(0,276)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(13,265)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(26,265)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(38,276)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(51,287)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(64,276)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(77,265)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(89,254)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(102,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(115,254)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(128,265)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(141,233)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(153,211)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(166,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(179,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(192,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(204,222)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(217,233)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(230,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(243,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(256,233)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(268,265)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(281,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(294,254)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(307,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(319,233)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(332,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(345,233)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(358,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(371,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(383,233)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(396,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(409,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(422,244)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(434,254)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(447,265)"></circle><circle cx="0" cy="0" r="2.5" stroke-width="0" transform="translate(460,265)"></circle></g></g></g><g class="dxc-labels-group"><g class="dxc-labels" visibility="hidden" clip-path="url(#DevExpress_2)" opacity="1"></g><g class="dxc-labels" visibility="hidden" clip-path="url(#DevExpress_2)" opacity="1"></g></g><g class="dxc-crosshair-cursor"></g><g class="dxc-trackers" opacity="0.0001" stroke="gray" fill="gray"><g class="dxc-crosshair-trackers" stroke="none" fill="grey"></g><g class="dxc-series-trackers"><g class="dxc-pane-tracker"><g clip-path="url(#DevExpress_2)"><path stroke-width="20" fill="none" d="M 0 254 L 13 244 L 26 244 L 38 254 L 51 265 L 64 265 L 77 244 L 89 222 L 102 200 L 115 222 L 128 222 L 141 178 L 153 189 L 166 200 L 179 211 L 192 211 L 204 211 L 217 211 L 230 135 L 243 189 L 256 200 L 268 200 L 281 211 L 294 211 L 307 200 L 319 211 L 332 211 L 345 222 L 358 222 L 371 233 L 383 222 L 396 222 L 409 211 L 422 233 L 434 244 L 447 244 L 460 233"></path></g><g clip-path="url(#DevExpress_2)"><path stroke-width="20" fill="none" d="M 0 276 L 13 265 L 26 265 L 38 276 L 51 287 L 64 276 L 77 265 L 89 254 L 102 244 L 115 254 L 128 265 L 141 233 L 153 211 L 166 222 L 179 222 L 192 222 L 204 222 L 217 233 L 230 244 L 243 244 L 256 233 L 268 265 L 281 244 L 294 254 L 307 244 L 319 233 L 332 244 L 345 233 L 358 244 L 371 244 L 383 233 L 396 244 L 409 244 L 422 244 L 434 254 L 447 265 L 460 265"></path></g></g></g><g class="dxc-markers-trackers" stroke="none" fill="grey"><g class="dxc-pane-tracker"><g><circle cx="0" cy="254" r="6"></circle><circle cx="13" cy="244" r="6"></circle><circle cx="26" cy="244" r="6"></circle><circle cx="38" cy="254" r="6"></circle><circle cx="51" cy="265" r="6"></circle><circle cx="64" cy="265" r="6"></circle><circle cx="77" cy="244" r="6"></circle><circle cx="89" cy="222" r="6"></circle><circle cx="102" cy="200" r="6"></circle><circle cx="115" cy="222" r="6"></circle><circle cx="128" cy="222" r="6"></circle><circle cx="141" cy="178" r="6"></circle><circle cx="153" cy="189" r="6"></circle><circle cx="166" cy="200" r="6"></circle><circle cx="179" cy="211" r="6"></circle><circle cx="192" cy="211" r="6"></circle><circle cx="204" cy="211" r="6"></circle><circle cx="217" cy="211" r="6"></circle><circle cx="230" cy="135" r="6"></circle><circle cx="243" cy="189" r="6"></circle><circle cx="256" cy="200" r="6"></circle><circle cx="268" cy="200" r="6"></circle><circle cx="281" cy="211" r="6"></circle><circle cx="294" cy="211" r="6"></circle><circle cx="307" cy="200" r="6"></circle><circle cx="319" cy="211" r="6"></circle><circle cx="332" cy="211" r="6"></circle><circle cx="345" cy="222" r="6"></circle><circle cx="358" cy="222" r="6"></circle><circle cx="371" cy="233" r="6"></circle><circle cx="383" cy="222" r="6"></circle><circle cx="396" cy="222" r="6"></circle><circle cx="409" cy="211" r="6"></circle><circle cx="422" cy="233" r="6"></circle><circle cx="434" cy="244" r="6"></circle><circle cx="447" cy="244" r="6"></circle><circle cx="460" cy="233" r="6"></circle></g><g><circle cx="0" cy="276" r="6"></circle><circle cx="13" cy="265" r="6"></circle><circle cx="26" cy="265" r="6"></circle><circle cx="38" cy="276" r="6"></circle><circle cx="51" cy="287" r="6"></circle><circle cx="64" cy="276" r="6"></circle><circle cx="77" cy="265" r="6"></circle><circle cx="89" cy="254" r="6"></circle><circle cx="102" cy="244" r="6"></circle><circle cx="115" cy="254" r="6"></circle><circle cx="128" cy="265" r="6"></circle><circle cx="141" cy="233" r="6"></circle><circle cx="153" cy="211" r="6"></circle><circle cx="166" cy="222" r="6"></circle><circle cx="179" cy="222" r="6"></circle><circle cx="192" cy="222" r="6"></circle><circle cx="204" cy="222" r="6"></circle><circle cx="217" cy="233" r="6"></circle><circle cx="230" cy="244" r="6"></circle><circle cx="243" cy="244" r="6"></circle><circle cx="256" cy="233" r="6"></circle><circle cx="268" cy="265" r="6"></circle><circle cx="281" cy="244" r="6"></circle><circle cx="294" cy="254" r="6"></circle><circle cx="307" cy="244" r="6"></circle><circle cx="319" cy="233" r="6"></circle><circle cx="332" cy="244" r="6"></circle><circle cx="345" cy="233" r="6"></circle><circle cx="358" cy="244" r="6"></circle><circle cx="371" cy="244" r="6"></circle><circle cx="383" cy="233" r="6"></circle><circle cx="396" cy="244" r="6"></circle><circle cx="409" cy="244" r="6"></circle><circle cx="422" cy="244" r="6"></circle><circle cx="434" cy="254" r="6"></circle><circle cx="447" cy="265" r="6"></circle><circle cx="460" cy="265" r="6"></circle></g></g></g></g><g class="dxc-legend" clip-path="url(#DevExpress_1)" transform="translate(0,0)"><g transform="translate(371,26)"><rect x="-5" y="-16" width="84" height="68" rx="0" ry="0" fill="#ffffff" class="dxc-border"></rect><g class="dxc-item" transform="translate(0,0)"><rect x="0" y="0" width="12" height="12" rx="0" ry="0" fill="#68b828"></rect><text x="19" y="11" text-anchor="start" style="fill: rgb(118, 118, 118); font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; font-size: 12px; cursor: default;"><tspan x="19" dy="0">Pageviews</tspan></text></g><g class="dxc-item" transform="translate(0,23)"><rect x="0" y="0" width="12" height="12" rx="0" ry="0" fill="#eeeeee"></rect><text x="19" y="11" text-anchor="start" style="fill: rgb(118, 118, 118); font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; font-size: 12px; cursor: default;"><tspan x="19" dy="0">Visitors</tspan></text></g></g><g class="dxc-legend-trackers" stroke="none" fill="grey" opacity="0.0001" transform="translate(371,26)"><rect x="-10" y="-5" width="94" height="23" rx="0" ry="0"></rect><rect x="-10" y="18" width="94" height="23" rx="0" ry="0"></rect></g></g><g class="dxc-tooltip"><path d="M 0 0 Z" filter="url(#DevExpress_42)" stroke-width="1" stroke="#d3d3d3" visibility="hidden"></path><g text-anchor="middle" visibility="hidden" style="font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; font-size: 12px; fill: rgb(35, 35, 35); cursor: default;"><text x="0" y="0" style="font-size: 12px;"></text></g></g></svg></div>
					</div>
					
				</div>
				<div class="col-sm-3">
					
					<div class="chart-item-bg">
						<div class="chart-label chart-label-small">
							<div class="h4 text-purple text-bold" data-count="this" data-from="0.00" data-to="95.8" data-suffix="%" data-duration="1.5">95.8%</div>
							<span class="text-small text-upper text-muted">Current Server Uptime</span>
						</div>
						<div id="server-uptime-chart" style="height: 134px; -webkit-user-select: none;" class="dx-visibility-change-handler"><svg width="215" height="134" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxc dxc-chart" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden;"><defs><clipPath id="DevExpress_3"><rect x="0" y="0" width="215" height="134" rx="0" ry="0" fill="none" stroke="none" stroke-width="0"></rect></clipPath><clipPath id="DevExpress_6"><rect x="0" y="0" width="215" height="134" rx="0" ry="0" fill="none" stroke="none" stroke-width="0"></rect></clipPath><pattern id="DevExpress_4" width="6" height="6" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="6" height="6" rx="0" ry="0" fill="#7c38bc" opacity="0.75"></rect><path stroke-width="2" stroke="#7c38bc" d="M 3 -3 L -3 3M 0 6 L 6 0 M 9 3 L 3 9"></path></pattern><pattern id="DevExpress_5" width="6" height="6" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="6" height="6" rx="0" ry="0" fill="#7c38bc" opacity="0.5"></rect><path stroke-width="2" stroke="#7c38bc" d="M 3 -3 L -3 3M 0 6 L 6 0 M 9 3 L 3 9"></path></pattern><filter id="DevExpress_43" x="-50%" y="-50%" width="200%" height="200%"><feGaussianBlur in="SourceGraphic" result="gaussianBlurResult" stdDeviation="2"></feGaussianBlur><feOffset in="gaussianBlurResult" result="offsetResult" dx="0" dy="4"></feOffset><feFlood result="floodResult" flood-color="#000000" flood-opacity="0.4"></feFlood><feComposite in="floodResult" in2="offsetResult" operator="in" result="compositeResult"></feComposite><feComposite in="SourceGraphic" in2="compositeResult" operator="over"></feComposite></filter></defs><g class="dxc-background"></g><g class="dxc-strips-group"><g class="dxc-h-strips" clip-path="url(#DevExpress_6)"></g><g class="dxc-v-strips" clip-path="url(#DevExpress_6)"></g></g><g class="dxc-axes-group"><g class="dxc-h-axis" clip-path="url(#DevExpress_3)"><g class="dxc-grid"></g><g class="dxc-elements"></g><g class="dxc-line"></g><g class="dxc-title"></g></g><g class="dxc-v-axis" clip-path="url(#DevExpress_3)"><g class="dxc-grid"></g><g class="dxc-elements"></g><g class="dxc-line"></g><g class="dxc-title"></g></g></g><g class="dxc-constant-lines-group"><g class="dxc-h-constant-lines"></g><g class="dxc-v-constant-lines"></g></g><g class="dxc-strips-labels-group"><g class="dxc-axis-labels"></g><g class="dxc-axis-labels"></g></g><g class="dxc-border"></g><g class="dxc-series-group"><g class="dxc-series"><g fill="#7c38bc" stroke="#7c38bc" stroke-width="0" class="dxc-markers" opacity="1" transform="translate(0,0) scale(1,1)"><rect x="12" y="124" width="11" height="10" rx="0" ry="0" stroke-width="0"></rect><rect x="23" y="114" width="11" height="20" rx="0" ry="0" stroke-width="0"></rect><rect x="34" y="104" width="11" height="30" rx="0" ry="0" stroke-width="0"></rect><rect x="46" y="93" width="11" height="41" rx="0" ry="0" stroke-width="0"></rect><rect x="57" y="83" width="11" height="51" rx="0" ry="0" stroke-width="0"></rect><rect x="68" y="93" width="11" height="41" rx="0" ry="0" stroke-width="0"></rect><rect x="79" y="83" width="11" height="51" rx="0" ry="0" stroke-width="0"></rect><rect x="90" y="73" width="11" height="61" rx="0" ry="0" stroke-width="0"></rect><rect x="102" y="63" width="11" height="71" rx="0" ry="0" stroke-width="0"></rect><rect x="113" y="73" width="11" height="61" rx="0" ry="0" stroke-width="0"></rect><rect x="124" y="83" width="11" height="51" rx="0" ry="0" stroke-width="0"></rect><rect x="135" y="93" width="11" height="41" rx="0" ry="0" stroke-width="0"></rect><rect x="146" y="83" width="11" height="51" rx="0" ry="0" stroke-width="0"></rect><rect x="157" y="93" width="11" height="41" rx="0" ry="0" stroke-width="0"></rect><rect x="169" y="93" width="11" height="41" rx="0" ry="0" stroke-width="0"></rect><rect x="180" y="104" width="11" height="30" rx="0" ry="0" stroke-width="0"></rect><rect x="191" y="93" width="11" height="41" rx="0" ry="0" stroke-width="0"></rect></g></g></g><g class="dxc-labels-group"><g class="dxc-labels" visibility="hidden" clip-path="url(#DevExpress_6)" opacity="1"></g></g><g class="dxc-crosshair-cursor"></g><g class="dxc-legend" clip-path="url(#DevExpress_3)"></g><g class="dxc-tooltip"><path d="M 0 0 Z" filter="url(#DevExpress_43)" stroke-width="1" stroke="#d3d3d3" visibility="hidden"></path><g text-anchor="middle" visibility="hidden" style="font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; font-size: 12px; fill: rgb(35, 35, 35); cursor: default;"><text x="0" y="0" style="font-size: 12px;"></text></g></g><g class="dxc-trackers" opacity="0.0001" stroke="gray" fill="gray"><g class="dxc-crosshair-trackers" stroke="none" fill="grey"></g><g class="dxc-series-trackers"><g class="dxc-pane-tracker"><g></g></g></g><g class="dxc-markers-trackers" stroke="none" fill="grey"><g class="dxc-pane-tracker"><g clip-path="url(#DevExpress_6)"><rect x="12" y="124" width="11" height="10" rx="0" ry="0"></rect><rect x="23" y="114" width="11" height="20" rx="0" ry="0"></rect><rect x="34" y="104" width="11" height="30" rx="0" ry="0"></rect><rect x="46" y="93" width="11" height="41" rx="0" ry="0"></rect><rect x="57" y="83" width="11" height="51" rx="0" ry="0"></rect><rect x="68" y="93" width="11" height="41" rx="0" ry="0"></rect><rect x="79" y="83" width="11" height="51" rx="0" ry="0"></rect><rect x="90" y="73" width="11" height="61" rx="0" ry="0"></rect><rect x="102" y="63" width="11" height="71" rx="0" ry="0"></rect><rect x="113" y="73" width="11" height="61" rx="0" ry="0"></rect><rect x="124" y="83" width="11" height="51" rx="0" ry="0"></rect><rect x="135" y="93" width="11" height="41" rx="0" ry="0"></rect><rect x="146" y="83" width="11" height="51" rx="0" ry="0"></rect><rect x="157" y="93" width="11" height="41" rx="0" ry="0"></rect><rect x="169" y="93" width="11" height="41" rx="0" ry="0"></rect><rect x="180" y="104" width="11" height="30" rx="0" ry="0"></rect><rect x="191" y="93" width="11" height="41" rx="0" ry="0"></rect></g></g></g></g></svg></div>
					</div>
					
					<div class="chart-item-bg">
						<div class="chart-label chart-label-small">
							<div class="h4 text-secondary text-bold" data-count="this" data-from="0.00" data-to="320.45" data-decimal="," data-duration="2">320,45</div>
							<span class="text-small text-upper text-muted">Avg. of Sales</span>
						</div>
						<div id="sales-avg-chart" style="height: 134px; position: relative;">
							<div style="position: absolute; top: 25px; right: 0px; left: 40%; bottom: 0px; -webkit-user-select: none;" class="dx-visibility-change-handler"><svg width="129" height="90" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxc dxc-chart" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden;"><defs><clipPath id="DevExpress_7"><rect x="0" y="0" width="129" height="90" rx="0" ry="0" fill="none" stroke="none" stroke-width="0"></rect></clipPath><pattern id="DevExpress_8" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#5e9b4c" opacity="0.75"></rect><path stroke-width="4" stroke="#5e9b4c" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_9" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#5e9b4c" opacity="0.5"></rect><path stroke-width="4" stroke="#5e9b4c" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_10" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#6ca959" opacity="0.75"></rect><path stroke-width="4" stroke="#6ca959" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_11" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#6ca959" opacity="0.5"></rect><path stroke-width="4" stroke="#6ca959" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_12" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#b9f5a6" opacity="0.75"></rect><path stroke-width="4" stroke="#b9f5a6" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_13" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#b9f5a6" opacity="0.5"></rect><path stroke-width="4" stroke="#b9f5a6" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_14" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#90cd7e" opacity="0.75"></rect><path stroke-width="4" stroke="#90cd7e" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_15" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#90cd7e" opacity="0.5"></rect><path stroke-width="4" stroke="#90cd7e" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_16" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#9edb8b" opacity="0.75"></rect><path stroke-width="4" stroke="#9edb8b" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_17" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#9edb8b" opacity="0.5"></rect><path stroke-width="4" stroke="#9edb8b" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_18" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#a0dc8d" opacity="0.75"></rect><path stroke-width="4" stroke="#a0dc8d" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_19" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#a0dc8d" opacity="0.5"></rect><path stroke-width="4" stroke="#a0dc8d" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_20" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#2c691a" opacity="0.75"></rect><path stroke-width="4" stroke="#2c691a" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_21" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#2c691a" opacity="0.5"></rect><path stroke-width="4" stroke="#2c691a" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_22" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#3a7727" opacity="0.75"></rect><path stroke-width="4" stroke="#3a7727" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_23" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#3a7727" opacity="0.5"></rect><path stroke-width="4" stroke="#3a7727" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_24" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#87c374" opacity="0.75"></rect><path stroke-width="4" stroke="#87c374" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_25" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#87c374" opacity="0.5"></rect><path stroke-width="4" stroke="#87c374" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_26" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#5e9b4c" opacity="0.75"></rect><path stroke-width="4" stroke="#5e9b4c" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><pattern id="DevExpress_27" width="10" height="10" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="10" height="10" rx="0" ry="0" fill="#5e9b4c" opacity="0.5"></rect><path stroke-width="4" stroke="#5e9b4c" d="M 5 -5 L -5 5M 0 10 L 10 0 M 15 5 L 5 15"></path></pattern><filter id="DevExpress_28" x="-50%" y="-50%" width="200%" height="200%"><feGaussianBlur in="SourceGraphic" result="gaussianBlurResult" stdDeviation="2"></feGaussianBlur><feOffset in="gaussianBlurResult" result="offsetResult" dx="0" dy="4"></feOffset><feFlood result="floodResult" flood-color="#000000" flood-opacity="0.4"></feFlood><feComposite in="floodResult" in2="offsetResult" operator="in" result="compositeResult"></feComposite><feComposite in="SourceGraphic" in2="compositeResult" operator="over"></feComposite></filter></defs><g class="dxc-legend" clip-path="url(#DevExpress_7)"></g><g class="dxc-series-group"><g class="dxc-series"><g class="dxc-markers"><path stroke-linejoin="round" fill="#5e9b4c" stroke="#ffffff" stroke-width="0" d="M 24.159450280596914 65.9220122850497 A 45 45 0 0 0 109 45.000000000000014 L 86 45.00000000000001 A 22 22 0 0 1 44.52239791495849 55.22853933935763 Z"></path><path stroke-linejoin="round" fill="#6ca959" stroke="#ffffff" stroke-width="0" d="M 19.64877659235065 37.38626358186886 A 45 45 0 0 0 24.159450280596914 65.9220122850497 L 44.52239791495849 55.22853933935763 A 22 22 0 0 1 42.317179667371434 41.277728862246995 Z"></path><path stroke-linejoin="round" fill="#b9f5a6" stroke="#ffffff" stroke-width="0" d="M 22.414171234250382 27.80643010117586 A 45 45 0 0 0 19.64877659235065 37.38626358186886 L 42.317179667371434 41.277728862246995 A 22 22 0 0 1 43.66915038118908 36.59425471613042 Z"></path><path stroke-linejoin="round" fill="#90cd7e" stroke="#ffffff" stroke-width="0" d="M 31.798550266891347 13.566472754619525 A 45 45 0 0 0 22.414171234250382 27.80643010117586 L 43.66915038118908 36.59425471613042 A 22 22 0 0 1 48.257069019369105 29.632497791147323 Z"></path><path stroke-linejoin="round" fill="#9edb8b" stroke="#ffffff" stroke-width="0" d="M 49.48862991260447 2.4039891751980846 A 45 45 0 0 0 31.798550266891347 13.566472754619525 L 48.257069019369105 29.632497791147323 A 22 22 0 0 1 56.90555240171774 24.175283596763506 Z"></path><path stroke-linejoin="round" fill="#a0dc8d" stroke="#ffffff" stroke-width="0" d="M 50.45706992002119 2.0862604187329623 A 45 45 0 0 0 49.48862991260447 2.4039891751980846 L 56.90555240171774 24.175283596763506 A 22 22 0 0 1 57.37901196089925 24.01994953804723 Z"></path><path stroke-linejoin="round" fill="#2c691a" stroke="#ffffff" stroke-width="0" d="M 71.32162977695153 0.5996200758455572 A 45 45 0 0 0 50.45706992002119 2.0862604187329623 L 57.37901196089925 24.01994953804723 A 22 22 0 0 1 67.57946344650964 23.293147592635606 Z"></path><path stroke-linejoin="round" fill="#3a7727" stroke="#ffffff" stroke-width="0" d="M 90.60421156885647 8.706530521324872 A 45 45 0 0 0 71.32162977695153 0.5996200758455572 L 67.57946344650964 23.293147592635606 A 22 22 0 0 1 77.00650343366317 27.256526032647713 Z"></path><path stroke-linejoin="round" fill="#87c374" stroke="#ffffff" stroke-width="0" d="M 104.13844491630525 24.655338791202986 A 45 45 0 0 0 90.60421156885647 8.706530521324872 L 77.00650343366317 27.256526032647713 A 22 22 0 0 1 83.62323973686034 35.05372118681035 Z"></path><path stroke-linejoin="round" fill="#5e9b4c" stroke="#ffffff" stroke-width="0" d="M 109 45 A 45 45 0 0 0 104.13844491630525 24.655338791202986 L 83.62323973686034 35.05372118681035 A 22 22 0 0 1 86 45 Z"></path></g></g></g><g class="dxc-labels-group"></g><g class="dxc-labels" visibility="hidden" opacity="1"></g><g class="dxc-tooltip"><path d="M 0 0 Z" filter="url(#DevExpress_28)" stroke-width="1" stroke="#d3d3d3" visibility="hidden"></path><g text-anchor="middle" visibility="hidden" style="font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; font-size: 12px; fill: rgb(35, 35, 35); cursor: default;"><text x="0" y="0" style="font-size: 12px;"></text></g></g><g class="dxc-trackers" opacity="0.0001" stroke="gray" fill="gray"><g class="dxc-crosshair-trackers" stroke="none" fill="grey"></g><g class="dxc-series-trackers"></g><g class="dxc-markers-trackers" stroke="none" fill="grey"><g><path stroke-linejoin="round" d="M 24.159450280596914 65.9220122850497 A 45 45 0 0 0 109 45.000000000000014 L 86 45.00000000000001 A 22 22 0 0 1 44.52239791495849 55.22853933935763 Z"></path><path stroke-linejoin="round" d="M 19.64877659235065 37.38626358186886 A 45 45 0 0 0 24.159450280596914 65.9220122850497 L 44.52239791495849 55.22853933935763 A 22 22 0 0 1 42.317179667371434 41.277728862246995 Z"></path><path stroke-linejoin="round" d="M 22.414171234250382 27.80643010117586 A 45 45 0 0 0 19.64877659235065 37.38626358186886 L 42.317179667371434 41.277728862246995 A 22 22 0 0 1 43.66915038118908 36.59425471613042 Z"></path><path stroke-linejoin="round" d="M 31.798550266891347 13.566472754619525 A 45 45 0 0 0 22.414171234250382 27.80643010117586 L 43.66915038118908 36.59425471613042 A 22 22 0 0 1 48.257069019369105 29.632497791147323 Z"></path><path stroke-linejoin="round" d="M 49.48862991260447 2.4039891751980846 A 45 45 0 0 0 31.798550266891347 13.566472754619525 L 48.257069019369105 29.632497791147323 A 22 22 0 0 1 56.90555240171774 24.175283596763506 Z"></path><path stroke-linejoin="round" d="M 50.45706992002119 2.0862604187329623 A 45 45 0 0 0 49.48862991260447 2.4039891751980846 L 56.90555240171774 24.175283596763506 A 22 22 0 0 1 57.37901196089925 24.01994953804723 Z"></path><path stroke-linejoin="round" d="M 71.32162977695153 0.5996200758455572 A 45 45 0 0 0 50.45706992002119 2.0862604187329623 L 57.37901196089925 24.01994953804723 A 22 22 0 0 1 67.57946344650964 23.293147592635606 Z"></path><path stroke-linejoin="round" d="M 90.60421156885647 8.706530521324872 A 45 45 0 0 0 71.32162977695153 0.5996200758455572 L 67.57946344650964 23.293147592635606 A 22 22 0 0 1 77.00650343366317 27.256526032647713 Z"></path><path stroke-linejoin="round" d="M 104.13844491630525 24.655338791202986 A 45 45 0 0 0 90.60421156885647 8.706530521324872 L 77.00650343366317 27.256526032647713 A 22 22 0 0 1 83.62323973686034 35.05372118681035 Z"></path><path stroke-linejoin="round" d="M 109 45 A 45 45 0 0 0 104.13844491630525 24.655338791202986 L 83.62323973686034 35.05372118681035 A 22 22 0 0 1 86 45 Z"></path></g></g></g><g></g></svg></div>
						</div>
					</div>
					
				</div>
			</div>
			
			
			<div class="row">
				<div class="col-sm-6">
					
					<div class="chart-item-bg">
						<div id="pageviews-stats" style="height: 320px; padding: 20px 0;" class="dx-visibility-change-handler"><svg width="460" height="280" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxg dxbg-bar-gauge" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden;"><defs><filter id="DevExpress_29" x="-50%" y="-50%" width="200%" height="200%"><feGaussianBlur in="SourceGraphic" result="gaussianBlurResult" stdDeviation="2"></feGaussianBlur><feOffset in="gaussianBlurResult" result="offsetResult" dx="0" dy="4"></feOffset><feFlood result="floodResult" flood-color="#000000" flood-opacity="0.4"></feFlood><feComposite in="floodResult" in2="offsetResult" operator="in" result="compositeResult"></feComposite><feComposite in="SourceGraphic" in2="compositeResult" operator="over"></feComposite></filter></defs><g class="dxbg-bars"><path stroke-linejoin="round" d="M 314.14570696119915 244.14570696119915 A 119 119 0 1 0 145.85429303880082 244.14570696119915 L 158.40543840486205 231.59456159513792 A 101.25 101.25 0 1 1 301.594561595138 231.59456159513792 Z" fill="#e0e0e0"></path><path stroke-linejoin="round" d="M 230 41 A 119 119 0 0 0 129.62526007673802 96.07886433003935 L 144.5971225442834 105.61331944047467 A 101.25 101.25 0 0 1 230 58.75 Z" fill="#68b828"></path><path stroke-width="2" d="M 230 58 L 230 21" stroke="#68b828" transform="rotate(-57.50999999999999,230,160)"></path><text x="87.45099960477918" y="85.06769607087367" text-anchor="middle" style="font-size: 16px; font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; cursor: default; fill: rgb(104, 184, 40);"><tspan x="87.45099960477918" dy="0">-21.3%</tspan></text><path stroke-linejoin="round" d="M 298.76613447039176 228.76613447039173 A 97.25 97.25 0 1 0 161.23386552960824 228.76613447039173 L 173.78501089566947 216.21498910433053 A 79.5 79.5 0 1 1 286.2149891043305 216.21498910433053 Z" fill="#e0e0e0"></path><path stroke-linejoin="round" d="M 292.45907052142286 85.45855508779073 A 97.25 97.25 0 0 0 230 62.75 L 230 80.5 A 79.5 79.5 0 0 1 281.0590859275385 99.06380595865669 Z" fill="#7c38bc"></path><path stroke-width="2" d="M 230 80 L 230 21" stroke="#7c38bc" transform="rotate(39.96000000000001,230,160)"></path><text x="338.5406983868428" y="50.29966016770257" text-anchor="middle" style="font-size: 16px; font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; cursor: default; fill: rgb(124, 56, 188);"><tspan x="338.5406983868428" dy="0">14.8%</tspan></text><path stroke-linejoin="round" d="M 283.38656197958437 213.38656197958434 A 75.5 75.5 0 1 0 176.61343802041566 213.38656197958434 L 189.16458338647686 200.8354166135231 A 57.75 57.75 0 1 1 270.83541661352314 200.8354166135231 Z" fill="#e0e0e0"></path><path stroke-linejoin="round" d="M 230 84.5 A 75.5 75.5 0 0 0 154.99582281634574 151.3615160471876 L 172.62925520058235 153.3924179036435 A 57.75 57.75 0 0 1 230 102.25 Z" fill="#0e62c7"></path><path stroke-width="2" d="M 230 102 L 230 21" stroke="#0e62c7" transform="rotate(-83.43,230,160)"></path><text x="62.10985504586" y="149.15438189077668" text-anchor="middle" style="font-size: 16px; font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; cursor: default; fill: rgb(14, 98, 199);"><tspan x="62.10985504586" dy="0">-30.9%</tspan></text><path stroke-linejoin="round" d="M 268.0069894887769 198.00698948877692 A 53.75 53.75 0 1 0 191.99301051122308 198.00698948877692 L 204.54415587728428 185.45584412271572 A 36 36 0 1 1 255.45584412271572 185.45584412271572 Z" fill="#e0e0e0"></path><path stroke-linejoin="round" d="M 275.5626890645732 188.51497615648697 A 53.75 53.75 0 0 0 230 106.25 L 230 124 A 36 36 0 0 1 260.516405699063 179.09840263504242 Z" fill="#fcd036"></path><path stroke-width="2" d="M 230 124 L 230 21" stroke="#fcd036" transform="rotate(122.04000000000002,230,160)"></path><text x="373.257571198379" y="246.9254955409009" text-anchor="middle" style="font-size: 16px; font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; cursor: default; fill: rgb(252, 208, 54);"><tspan x="373.257571198379" dy="0">45.2%</tspan></text></g><g class="dxg-tracker" stroke="none" stroke-width="0" fill="#000000" opacity="0.0001"><path stroke-linejoin="round" d="M 230 41 A 119 119 0 0 0 129.62526007673802 96.07886433003935 L 144.5971225442834 105.61331944047467 A 101.25 101.25 0 0 1 230 58.75 Z"></path><path stroke-linejoin="round" d="M 292.45907052142286 85.45855508779073 A 97.25 97.25 0 0 0 230 62.75 L 230 80.5 A 79.5 79.5 0 0 1 281.0590859275385 99.06380595865669 Z"></path><path stroke-linejoin="round" d="M 230 84.5 A 75.5 75.5 0 0 0 154.99582281634574 151.3615160471876 L 172.62925520058235 153.3924179036435 A 57.75 57.75 0 0 1 230 102.25 Z"></path><path stroke-linejoin="round" d="M 275.5626890645732 188.51497615648697 A 53.75 53.75 0 0 0 230 106.25 L 230 124 A 36 36 0 0 1 260.516405699063 179.09840263504242 Z"></path></g></svg></div>
						
						<div class="chart-entry-view">
							<div class="chart-entry-label">
								Pageviews
							</div>
							<div class="chart-entry-value">
								<div class="sparkline first-month dx-visibility-change-handler"><svg width="170" height="30" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxsl dxsl-sparkline" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden;"><g class="dxc-labels" visibility="hidden"></g><g class="dxc-labels" visibility="hidden"></g><g class="dxsl-series"><g class="dxc-series"><g class="dxc-elements" stroke="none" fill="#68b828" opacity="0.2"><path d="M 5 9 L 20 10 L 34 10 L 49 8 L 63 9 L 78 11 L 92 11 L 107 10 L 121 9 L 136 8 L 150 7 L 165 6 L 165 27 L 150 27 L 136 27 L 121 27 L 107 27 L 92 27 L 78 27 L 63 27 L 49 27 L 34 27 L 20 27 L 5 27 Z"></path></g><g class="dxc-borders" stroke="#68b828" stroke-width="2"><path stroke-width="2" d="M 5 9 L 20 10 L 34 10 L 49 8 L 63 9 L 78 11 L 92 11 L 107 10 L 121 9 L 136 8 L 150 7 L 165 6"></path></g><g fill="#ffffff" stroke="#ffffff" stroke-width="2" r="3" visibility="hidden" class="dxc-markers" opacity="1"><circle cx="0" cy="0" r="3" fill="#ffffff" stroke="#68b828" stroke-width="2" visibility="visible" transform="translate(92,11)"></circle><circle cx="0" cy="0" r="3" fill="#ffffff" stroke="#7c38bc" stroke-width="2" visibility="visible" transform="translate(165,6)"></circle></g></g></g></svg><div style="position: relative"><svg width="170" height="30" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxsl dxsl-tooltip" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden; left: 0px; top: -30px; position: absolute;"><g class="dxsl-tooltip-group" style="z-index: 1;"></g><g class="dxsl-tooltip-tracker-group"><rect x="0" y="0" width="170" height="30" rx="0" ry="0" fill="grey" opacity="0"></rect></g></svg></div></div>
							</div>
						</div>
						
						<div class="chart-entry-view">
							<div class="chart-entry-label">
								Visitors
							</div>
							<div class="chart-entry-value">
								<div class="sparkline second-month dx-visibility-change-handler"><svg width="170" height="30" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxsl dxsl-sparkline" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden;"><g class="dxc-labels" visibility="hidden"></g><g class="dxc-labels" visibility="hidden"></g><g class="dxsl-series"><g class="dxc-series"><g class="dxc-elements" stroke="none" fill="#68b828" opacity="0.2"><path d="M 5 12 C 5 12 12.5 12 20 12 C 27 12 29.333333333333332 11.804597701149426 34 11 C 39 10.13793103448276 44 7.862068965517241 49 7 C 53.666666666666664 6.195402298850574 56 6 63 6 C 70.5 6 70.5 12 78 12 C 85 12 87.33333333333333 11.32183908045977 92 11 C 97 10.655172413793103 99.5 10 107 10 C 114 10 114 10 121 10 C 128.5 10 128.5 8 136 8 C 143 8 143 9 150 9 C 157.5 9 165 8 165 8 C 165 8 165 27 165 27 C 165 27 157.5 27 150 27 C 143 27 143 27 136 27 C 128.5 27 128.5 27 121 27 C 114 27 114 27 107 27 C 99.5 27 97 27 92 27 C 87.33333333333333 27 85 27 78 27 C 70.5 27 70.5 27 63 27 C 56 27 53.666666666666664 27 49 27 C 44 27 39 27 34 27 C 29.333333333333332 27 27 27 20 27 C 12.5 27 5 27 5 27 Z"></path></g><g class="dxc-borders" stroke="#68b828" stroke-width="2"><path stroke-width="2" d="M 5 12 C 5 12 12.5 12 20 12 C 27 12 29.333333333333332 11.804597701149426 34 11 C 39 10.13793103448276 44 7.862068965517241 49 7 C 53.666666666666664 6.195402298850574 56 6 63 6 C 70.5 6 70.5 12 78 12 C 85 12 87.33333333333333 11.32183908045977 92 11 C 97 10.655172413793103 99.5 10 107 10 C 114 10 114 10 121 10 C 128.5 10 128.5 8 136 8 C 143 8 143 9 150 9 C 157.5 9 165 8 165 8"></path></g><g fill="#ffffff" stroke="#ffffff" stroke-width="2" r="4" visibility="hidden" class="dxc-markers" opacity="1"><circle cx="0" cy="0" r="4" fill="#ffffff" stroke="#68b828" stroke-width="2" visibility="visible" transform="translate(20,12)"></circle><circle cx="0" cy="0" r="4" fill="#ffffff" stroke="#7c38bc" stroke-width="2" visibility="visible" transform="translate(63,6)"></circle></g></g></g></svg><div style="position: relative"><svg width="170" height="30" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxsl dxsl-tooltip" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden; left: 0px; top: -30px; position: absolute;"><g class="dxsl-tooltip-group" style="z-index: 1;"></g><g class="dxsl-tooltip-tracker-group"><rect x="0" y="0" width="170" height="30" rx="0" ry="0" fill="grey" opacity="0"></rect></g></svg></div></div>
							</div>
						</div>
						
						<div class="chart-entry-view">
							<div class="chart-entry-label">
								Converted Sales
							</div>
							<div class="chart-entry-value">
								<div class="sparkline third-month dx-visibility-change-handler"><svg width="170" height="30" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxsl dxsl-sparkline" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden;"><g class="dxc-labels" visibility="hidden"></g><g class="dxc-labels" visibility="hidden"></g><g class="dxsl-series"><g class="dxc-series"><g class="dxc-elements" stroke="none" fill="#68b828" opacity="0.2"><path d="M 5 16 C 5 16 12.5 15 20 15 C 27 15 27 15 34 15 C 41.5 15 44 13.689655172413794 49 13 C 53.666666666666664 12.35632183908046 56 11 63 11 C 70.5 11 70.5 14 78 14 C 85 14 85 14 92 14 C 99.5 14 102 13.344827586206897 107 13 C 111.66666666666667 12.67816091954023 116.33333333333333 12.32183908045977 121 12 C 126 11.655172413793103 131 11.517241379310343 136 11 C 140.66666666666666 10.517241379310343 145.33333333333334 9.804597701149426 150 9 C 155 8.13793103448276 165 6 165 6 C 165 6 165 27 165 27 C 165 27 155 27 150 27 C 145.33333333333334 27 140.66666666666666 27 136 27 C 131 27 126 27 121 27 C 116.33333333333333 27 111.66666666666667 27 107 27 C 102 27 99.5 27 92 27 C 85 27 85 27 78 27 C 70.5 27 70.5 27 63 27 C 56 27 53.666666666666664 27 49 27 C 44 27 41.5 27 34 27 C 27 27 27 27 20 27 C 12.5 27 5 27 5 27 Z"></path></g><g class="dxc-borders" stroke="#68b828" stroke-width="2"><path stroke-width="2" d="M 5 16 C 5 16 12.5 15 20 15 C 27 15 27 15 34 15 C 41.5 15 44 13.689655172413794 49 13 C 53.666666666666664 12.35632183908046 56 11 63 11 C 70.5 11 70.5 14 78 14 C 85 14 85 14 92 14 C 99.5 14 102 13.344827586206897 107 13 C 111.66666666666667 12.67816091954023 116.33333333333333 12.32183908045977 121 12 C 126 11.655172413793103 131 11.517241379310343 136 11 C 140.66666666666666 10.517241379310343 145.33333333333334 9.804597701149426 150 9 C 155 8.13793103448276 165 6 165 6"></path></g><g fill="#ffffff" stroke="#ffffff" stroke-width="2" r="4" visibility="hidden" class="dxc-markers" opacity="1"><circle cx="0" cy="0" r="4" fill="#ffffff" stroke="#68b828" stroke-width="2" visibility="visible" transform="translate(5,16)"></circle><circle cx="0" cy="0" r="4" fill="#ffffff" stroke="#7c38bc" stroke-width="2" visibility="visible" transform="translate(165,6)"></circle></g></g></g></svg><div style="position: relative"><svg width="170" height="30" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxsl dxsl-tooltip" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden; left: 0px; top: -30px; position: absolute;"><g class="dxsl-tooltip-group" style="z-index: 1;"></g><g class="dxsl-tooltip-tracker-group"><rect x="0" y="0" width="170" height="30" rx="0" ry="0" fill="grey" opacity="0"></rect></g></svg></div></div>
							</div>
						</div>
					</div>
					
				</div>
				<div class="col-sm-6">
					
					<div class="chart-item-bg">
						<div class="chart-label">
							<div id="network-mbs-packets" class="h1 text-purple text-bold" data-count="this" data-from="0.00" data-to="21.05" data-suffix="mb/s" data-duration="1">24.04mb/s</div>
							<span class="text-small text-muted text-upper">Download Speed</span>
						</div>
						<div class="chart-right-legend">
							<div id="network-realtime-gauge" style="width: 170px; height: 140px" class="dx-visibility-change-handler"><svg width="170" height="140" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxg dxg-circular-gauge" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden;"><defs><filter id="DevExpress_36" x="-50%" y="-50%" width="200%" height="200%"><feGaussianBlur in="SourceGraphic" result="gaussianBlurResult" stdDeviation="2"></feGaussianBlur><feOffset in="gaussianBlurResult" result="offsetResult" dx="0" dy="4"></feOffset><feFlood result="floodResult" flood-color="#000000" flood-opacity="0.4"></feFlood><feComposite in="floodResult" in2="offsetResult" operator="in" result="compositeResult"></feComposite><feComposite in="SourceGraphic" in2="compositeResult" operator="over"></feComposite></filter></defs><g class="dxg-range-container"><path stroke-linejoin="round" d="M 37.882143841924375 58.48314494938042 A 51 51 0 0 0 48.93755415948607 114.06244584051393 L 51.058874503045715 111.94112549695427 A 48 48 0 0 1 40.65378243945824 59.631195246475684 Z" fill="#7c38bc" class="dxg-range dxg-range-0"></path><path stroke-linejoin="round" d="M 85 27 A 51 51 0 0 0 37.882143841924375 58.48314494938042 L 40.65378243945824 59.631195246475684 A 48 48 0 0 1 85 30 Z" fill="#7c38bc" class="dxg-range dxg-range-1"></path><path stroke-linejoin="round" d="M 132.11785615807563 58.48314494938042 A 51 51 0 0 0 85 27 L 85 30 A 48 48 0 0 1 129.34621756054176 59.63119524647569 Z" fill="#7c38bc" class="dxg-range dxg-range-2"></path><path stroke-linejoin="round" d="M 121.06244584051393 114.06244584051393 A 51 51 0 0 0 132.11785615807563 58.48314494938042 L 129.34621756054176 59.63119524647569 A 48 48 0 0 1 118.94112549695429 111.94112549695427 Z" fill="#7c38bc" class="dxg-range dxg-range-3"></path></g><g class="dxg-scale"><g class="dxg-major-ticks" fill="#ffffff"><path d="M 84 25 L 86 25 L 86 30 L 84 30 Z" transform="rotate(-135,85,78)"></path><path d="M 84 25 L 86 25 L 86 30 L 84 30 Z" transform="rotate(-67.5,85,78)"></path><path d="M 84 25 L 86 25 L 86 30 L 84 30 Z" transform="rotate(0,85,78)"></path><path d="M 84 25 L 86 25 L 86 30 L 84 30 Z" transform="rotate(67.5,85,78)"></path><path d="M 84 25 L 86 25 L 86 30 L 84 30 Z" transform="rotate(135,85,78)"></path></g><g class="dxg-labels" text-anchor="middle" style="fill: rgb(118, 118, 118); font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; font-size: 12px; cursor: default;"><text x="38.33095244168785" y="132.3510280736516"><tspan x="38.33095244168785" dy="0">0</tspan></text><text x="21.252312256721215" y="55.52081801826116"><tspan x="21.252312256721215" dy="0">50</tspan></text><text x="85" y="12"><tspan x="85" dy="0">100</tspan></text><text x="151.51932634081263" y="55.520818018261174"><tspan x="151.51932634081263" dy="0">150</tspan></text><text x="135.91168824543144" y="132.3510280736516"><tspan x="135.91168824543144" dy="0">200</tspan></text></g></g><g class="dxg-value-indicator" fill="#7c38bc" transform="rotate(35.156643318899995,85,78)"><path d="M 83 78 L 85 40 L 87 78 Z"></path><circle cx="85" cy="78" r="6" class="dxg-spindle-border"></circle><circle cx="85" cy="78" r="5" class="dxg-spindle-hole" fill="#ffffff"></circle></g><g class="dxg-tracker" stroke="none" stroke-width="0" fill="#000000" opacity="0.0001"><path d="M 75 40 L 75 78 L 95 78 L 95 40 Z" transform="rotate(35.156643318899995,85,78)"></path></g></svg></div>
						</div>
						<div id="realtime-network-stats" style="height: 320px; -webkit-user-select: none;" class="dx-visibility-change-handler"><svg width="460" height="320" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxc dxc-chart" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden;"><defs><clipPath id="DevExpress_30"><rect x="0" y="0" width="460" height="320" rx="0" ry="0" fill="none" stroke="none" stroke-width="0"></rect></clipPath><clipPath id="DevExpress_35"><rect x="0" y="0" width="460" height="320" rx="0" ry="0" fill="none" stroke="none" stroke-width="0"></rect></clipPath><pattern id="DevExpress_31" width="6" height="6" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="6" height="6" rx="0" ry="0" fill="#7c38bc" opacity="0.75"></rect><path stroke-width="2" stroke="#7c38bc" d="M 3 -3 L -3 3M 0 6 L 6 0 M 9 3 L 3 9"></path></pattern><pattern id="DevExpress_32" width="6" height="6" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="6" height="6" rx="0" ry="0" fill="#7c38bc" opacity="0.5"></rect><path stroke-width="2" stroke="#7c38bc" d="M 3 -3 L -3 3M 0 6 L 6 0 M 9 3 L 3 9"></path></pattern><pattern id="DevExpress_33" width="6" height="6" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="6" height="6" rx="0" ry="0" fill="#000" opacity="0.75"></rect><path stroke-width="2" stroke="#000" d="M 3 -3 L -3 3M 0 6 L 6 0 M 9 3 L 3 9"></path></pattern><pattern id="DevExpress_34" width="6" height="6" patternUnits="userSpaceOnUse"><rect x="0" y="0" width="6" height="6" rx="0" ry="0" fill="#000" opacity="0.5"></rect><path stroke-width="2" stroke="#000" d="M 3 -3 L -3 3M 0 6 L 6 0 M 9 3 L 3 9"></path></pattern><filter id="DevExpress_44" x="-50%" y="-50%" width="200%" height="200%"><feGaussianBlur in="SourceGraphic" result="gaussianBlurResult" stdDeviation="2"></feGaussianBlur><feOffset in="gaussianBlurResult" result="offsetResult" dx="0" dy="4"></feOffset><feFlood result="floodResult" flood-color="#000000" flood-opacity="0.4"></feFlood><feComposite in="floodResult" in2="offsetResult" operator="in" result="compositeResult"></feComposite><feComposite in="SourceGraphic" in2="compositeResult" operator="over"></feComposite></filter></defs><g class="dxc-background"></g><g class="dxc-strips-group"><g class="dxc-h-strips" clip-path="url(#DevExpress_35)"></g><g class="dxc-v-strips" clip-path="url(#DevExpress_35)"></g></g><g class="dxc-axes-group"><g class="dxc-h-axis" clip-path="url(#DevExpress_30)"><g class="dxc-grid"><path stroke-width="1" stroke="#f5f5f5" d="M 77.5 0 L 77.5 320"></path><path stroke-width="1" stroke="#f5f5f5" d="M 169.5 0 L 169.5 320"></path><path stroke-width="1" stroke="#f5f5f5" d="M 260.5 0 L 260.5 320"></path><path stroke-width="1" stroke="#f5f5f5" d="M 351.5 0 L 351.5 320"></path><path stroke-width="1" stroke="#f5f5f5" d="M 442.5 0 L 442.5 320"></path></g><g class="dxc-elements"></g><g class="dxc-line"></g><g class="dxc-title"></g></g><g class="dxc-v-axis" clip-path="url(#DevExpress_30)"><g class="dxc-grid"><path stroke-width="1" stroke="#f5f5f5" d="M 0 320.5 L 460 320.5"></path><path stroke-width="1" stroke="#f5f5f5" d="M 0 262.5 L 460 262.5"></path><path stroke-width="1" stroke="#f5f5f5" d="M 0 204.5 L 460 204.5"></path><path stroke-width="1" stroke="#f5f5f5" d="M 0 145.5 L 460 145.5"></path><path stroke-width="1" stroke="#f5f5f5" d="M 0 87.5 L 460 87.5"></path><path stroke-width="1" stroke="#f5f5f5" d="M 0 29.5 L 460 29.5"></path></g><g class="dxc-elements"></g><g class="dxc-line"></g><g class="dxc-title"></g></g></g><g class="dxc-constant-lines-group"><g class="dxc-h-constant-lines"></g><g class="dxc-v-constant-lines"></g></g><g class="dxc-strips-labels-group"><g class="dxc-axis-labels"></g><g class="dxc-axis-labels"></g></g><g class="dxc-border"></g><g class="dxc-series-group"><g class="dxc-series"><g class="dxc-elements" stroke="none" fill="#7c38bc" opacity="0.4" clip-path="url(#DevExpress_35)"><path d="M 0 244 L 5 244 L 9 239 L 14 239 L 18 234 L 23 235 L 27 244 L 32 240 L 36 243 L 41 239 L 46 239 L 50 234 L 55 239 L 59 244 L 64 237 L 68 236 L 73 238 L 77 236 L 82 241 L 87 241 L 91 237 L 96 233 L 100 236 L 105 233 L 109 235 L 114 238 L 118 243 L 123 234 L 128 241 L 132 233 L 137 244 L 141 233 L 146 238 L 150 234 L 155 240 L 159 239 L 164 240 L 169 243 L 173 236 L 178 240 L 182 244 L 187 233 L 191 241 L 196 240 L 200 238 L 205 236 L 210 237 L 214 240 L 219 240 L 223 243 L 228 243 L 232 243 L 237 239 L 241 243 L 246 234 L 250 240 L 255 233 L 260 235 L 269 239 L 273 239 L 278 241 L 282 238 L 287 236 L 291 243 L 296 240 L 301 235 L 305 243 L 310 239 L 314 237 L 319 241 L 323 235 L 328 239 L 332 233 L 337 240 L 342 242 L 346 244 L 351 238 L 355 234 L 360 237 L 364 236 L 369 238 L 373 236 L 378 233 L 383 244 L 387 235 L 392 233 L 396 242 L 401 233 L 405 241 L 410 237 L 414 244 L 419 240 L 424 244 L 428 233 L 433 234 L 437 244 L 442 235 L 446 233 L 451 243 L 455 243 L 460 239 L 460 320 L 455 320 L 451 320 L 446 320 L 442 320 L 437 320 L 433 320 L 428 320 L 424 320 L 419 320 L 414 320 L 410 320 L 405 320 L 401 320 L 396 320 L 392 320 L 387 320 L 383 320 L 378 320 L 373 320 L 369 320 L 364 320 L 360 320 L 355 320 L 351 320 L 346 320 L 342 320 L 337 320 L 332 320 L 328 320 L 323 320 L 319 320 L 314 320 L 310 320 L 305 320 L 301 320 L 296 320 L 291 320 L 287 320 L 282 320 L 278 320 L 273 320 L 269 320 L 260 320 L 255 320 L 250 320 L 246 320 L 241 320 L 237 320 L 232 320 L 228 320 L 223 320 L 219 320 L 214 320 L 210 320 L 205 320 L 200 320 L 196 320 L 191 320 L 187 320 L 182 320 L 178 320 L 173 320 L 169 320 L 164 320 L 159 320 L 155 320 L 150 320 L 146 320 L 141 320 L 137 320 L 132 320 L 128 320 L 123 320 L 118 320 L 114 320 L 109 320 L 105 320 L 100 320 L 96 320 L 91 320 L 87 320 L 82 320 L 77 320 L 73 320 L 68 320 L 64 320 L 59 320 L 55 320 L 50 320 L 46 320 L 41 320 L 36 320 L 32 320 L 27 320 L 23 320 L 18 320 L 14 320 L 9 320 L 5 320 L 0 320 Z"></path></g><g fill="#7c38bc" stroke="#7c38bc" stroke-width="0" r="6" visibility="hidden" class="dxc-markers" opacity="1"></g></g><g class="dxc-series"><g class="dxc-elements" stroke="none" fill="#000" opacity="0.5" clip-path="url(#DevExpress_35)"><path d="M 0 297 L 5 291 L 9 295 L 14 291 L 18 297 L 23 291 L 27 292 L 32 296 L 36 295 L 41 294 L 46 292 L 50 293 L 55 295 L 59 297 L 64 291 L 68 297 L 73 292 L 77 296 L 82 293 L 87 294 L 91 294 L 96 291 L 100 295 L 105 294 L 109 292 L 114 294 L 118 295 L 123 296 L 128 291 L 132 294 L 137 295 L 141 296 L 146 291 L 150 293 L 155 294 L 159 291 L 164 296 L 169 294 L 173 293 L 178 292 L 182 294 L 187 292 L 191 293 L 196 297 L 200 296 L 205 293 L 210 296 L 214 292 L 219 297 L 223 295 L 228 292 L 232 293 L 237 294 L 241 296 L 246 295 L 250 293 L 255 296 L 260 296 L 269 297 L 273 297 L 278 292 L 282 291 L 287 291 L 291 296 L 296 294 L 301 295 L 305 291 L 310 293 L 314 293 L 319 291 L 323 294 L 328 293 L 332 292 L 337 294 L 342 292 L 346 296 L 351 293 L 355 294 L 360 295 L 364 293 L 369 297 L 373 292 L 378 296 L 383 291 L 387 291 L 392 296 L 396 293 L 401 291 L 405 291 L 410 297 L 414 294 L 419 296 L 424 295 L 428 297 L 433 291 L 437 295 L 442 297 L 446 292 L 451 291 L 455 294 L 460 293 L 460 320 L 455 320 L 451 320 L 446 320 L 442 320 L 437 320 L 433 320 L 428 320 L 424 320 L 419 320 L 414 320 L 410 320 L 405 320 L 401 320 L 396 320 L 392 320 L 387 320 L 383 320 L 378 320 L 373 320 L 369 320 L 364 320 L 360 320 L 355 320 L 351 320 L 346 320 L 342 320 L 337 320 L 332 320 L 328 320 L 323 320 L 319 320 L 314 320 L 310 320 L 305 320 L 301 320 L 296 320 L 291 320 L 287 320 L 282 320 L 278 320 L 273 320 L 269 320 L 260 320 L 255 320 L 250 320 L 246 320 L 241 320 L 237 320 L 232 320 L 228 320 L 223 320 L 219 320 L 214 320 L 210 320 L 205 320 L 200 320 L 196 320 L 191 320 L 187 320 L 182 320 L 178 320 L 173 320 L 169 320 L 164 320 L 159 320 L 155 320 L 150 320 L 146 320 L 141 320 L 137 320 L 132 320 L 128 320 L 123 320 L 118 320 L 114 320 L 109 320 L 105 320 L 100 320 L 96 320 L 91 320 L 87 320 L 82 320 L 77 320 L 73 320 L 68 320 L 64 320 L 59 320 L 55 320 L 50 320 L 46 320 L 41 320 L 36 320 L 32 320 L 27 320 L 23 320 L 18 320 L 14 320 L 9 320 L 5 320 L 0 320 Z"></path></g><g fill="#000" stroke="#000" stroke-width="0" r="6" visibility="hidden" class="dxc-markers" opacity="1"></g></g></g><g class="dxc-labels-group"><g class="dxc-labels" visibility="hidden" clip-path="url(#DevExpress_35)"></g><g class="dxc-labels" visibility="hidden" clip-path="url(#DevExpress_35)"></g></g><g class="dxc-crosshair-cursor"></g><g class="dxc-legend" clip-path="url(#DevExpress_30)"></g><g class="dxc-tooltip"><path d="M 0 0 Z" filter="url(#DevExpress_44)" stroke-width="1" stroke="#d3d3d3" visibility="hidden"></path><g text-anchor="middle" visibility="hidden" style="font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; font-size: 12px; fill: rgb(35, 35, 35); cursor: default;"><text x="0" y="0" style="font-size: 12px;"></text></g></g><g class="dxc-trackers" opacity="0.0001" stroke="gray" fill="gray"><g class="dxc-crosshair-trackers" stroke="none" fill="grey"></g><g class="dxc-series-trackers"></g><g class="dxc-markers-trackers" stroke="none" fill="grey"></g></g></svg></div>
					</div>
					
					<div class="chart-item-bg">
						<div class="chart-label">
							<div id="network-mbs-packets" class="h1 text-secondary text-bold" data-count="this" data-from="0.00" data-to="67.35" data-suffix="%" data-duration="1.5">67.35%</div>
							<span class="text-small text-muted text-upper">CPU Usage</span>
							
							<p class="text-medium" style="width: 50%; margin-top: 10px">Sentiments two occasional affronting solicitude travelling and one contrasted. Fortune day out married parties.</p>
						</div>
						<div id="other-stats" style="min-height: 183px">
							<div id="cpu-usage-gauge" style="width: 170px; height: 140px; position: absolute; right: 20px; top: 20px" class="dx-visibility-change-handler"><svg width="170" height="140" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" stroke="none" stroke-width="0" fill="none" class="dxg dxg-circular-gauge" direction="ltr" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); display: block; overflow: hidden;"><defs><filter id="DevExpress_37" x="-50%" y="-50%" width="200%" height="200%"><feGaussianBlur in="SourceGraphic" result="gaussianBlurResult" stdDeviation="2"></feGaussianBlur><feOffset in="gaussianBlurResult" result="offsetResult" dx="0" dy="4"></feOffset><feFlood result="floodResult" flood-color="#000000" flood-opacity="0.4"></feFlood><feComposite in="floodResult" in2="offsetResult" operator="in" result="compositeResult"></feComposite><feComposite in="SourceGraphic" in2="compositeResult" operator="over"></feComposite></filter></defs><g class="dxg-range-container"><path stroke-linejoin="round" d="M 37.882143841924375 58.48314494938042 A 51 51 0 0 0 48.93755415948607 114.06244584051393 L 51.058874503045715 111.94112549695427 A 48 48 0 0 1 40.65378243945824 59.631195246475684 Z" fill="#68b828" class="dxg-range dxg-range-0"></path><path stroke-linejoin="round" d="M 85 27 A 51 51 0 0 0 37.882143841924375 58.48314494938042 L 40.65378243945824 59.631195246475684 A 48 48 0 0 1 85 30 Z" fill="#68b828" class="dxg-range dxg-range-1"></path><path stroke-linejoin="round" d="M 132.11785615807563 58.48314494938042 A 51 51 0 0 0 85 27 L 85 30 A 48 48 0 0 1 129.34621756054176 59.63119524647569 Z" fill="#68b828" class="dxg-range dxg-range-2"></path><path stroke-linejoin="round" d="M 121.06244584051393 114.06244584051393 A 51 51 0 0 0 132.11785615807563 58.48314494938042 L 129.34621756054176 59.63119524647569 A 48 48 0 0 1 118.94112549695429 111.94112549695427 Z" fill="#d5080f" class="dxg-range dxg-range-3"></path></g><g class="dxg-scale"><g class="dxg-major-ticks" fill="#ffffff"><path d="M 84 25 L 86 25 L 86 30 L 84 30 Z" transform="rotate(-135,85,78)"></path><path d="M 84 25 L 86 25 L 86 30 L 84 30 Z" transform="rotate(-67.5,85,78)"></path><path d="M 84 25 L 86 25 L 86 30 L 84 30 Z" transform="rotate(0,85,78)"></path><path d="M 84 25 L 86 25 L 86 30 L 84 30 Z" transform="rotate(67.5,85,78)"></path><path d="M 84 25 L 86 25 L 86 30 L 84 30 Z" transform="rotate(135,85,78)"></path></g><g class="dxg-labels" text-anchor="middle" style="fill: rgb(118, 118, 118); font-family: 'Segoe UI', 'Helvetica Neue', 'Trebuchet MS', Verdana; font-weight: 400; font-size: 12px; cursor: default;"><text x="38.33095244168785" y="132.3510280736516"><tspan x="38.33095244168785" dy="0">0</tspan></text><text x="21.252312256721215" y="55.52081801826116"><tspan x="21.252312256721215" dy="0">25</tspan></text><text x="85" y="12"><tspan x="85" dy="0">50</tspan></text><text x="148.7476877432788" y="55.520818018261174"><tspan x="148.7476877432788" dy="0">75</tspan></text><text x="135.91168824543144" y="132.3510280736516"><tspan x="135.91168824543144" dy="0">100</tspan></text></g></g><g class="dxg-value-indicator" fill="#68b828" transform="rotate(75.6,85,78)"><path d="M 84 78 L 84 40 L 86 40 L 86 78 Z"></path><circle cx="85" cy="78" r="6" class="dxg-spindle-border"></circle><circle cx="85" cy="78" r="5" class="dxg-spindle-hole" fill="#ffffff"></circle></g><g class="dxg-tracker" stroke="none" stroke-width="0" fill="#000000" opacity="0.0001"><path d="M 75 40 L 75 78 L 95 78 L 95 40 Z" transform="rotate(75.6,85,78)"></path></g></svg></div>
						</div>
					</div>
					
				</div>
			</div>
			
			
		</div>

</@base> 