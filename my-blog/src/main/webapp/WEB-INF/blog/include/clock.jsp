<head>
<meta charset="utf-8">
<style type="text/css">
body,ul,li{
	padding: 0;
	margin:0;
}
.box{
	
}
.box li{
	position: relative;
	text-align: center;
	list-style-type: none;
	display: inline-block;
	width: 56px;
	height:65px;
	text-shadow:0 2px 1px #f4f4f4;
	border:1px solid #9fa2ad;
	border-radius: 5px;
	margin-right:10px;
	background: -webkit-gradient(linear,0 0, 0 100%,
	color-stop(.2,rgba(248,248,248,.3)),
	color-stop(.5,rgba(168,173,190,.5)),
	color-stop(.51,rgba(168,173,190,.3)),
	color-stop(.9,rgba(248,248,248,.2)));
	background: -webkit-linear-gradient(top,rgba(248,248,248,.3) 20%,rgba(168,173,190,.5) 50%,rgba(168,173,190,.3) 51%, rgba(248,248,248,.2) 90%);
	background: -moz-linear-gradient(top, rgba(248,248,248,.3) 20%,rgba(168,173,190,.5) 50%,rgba(168,173,190,.3) 51%, rgba(248,248,248,.2) 90%);
	background: -o-linear-gradient(top, rgba(248,248,248,.3) 20%, rgba(168,173,190,.5) 50%, rgba(168,173,190,.3) 51%, rgba(248,248,248,.2) 90%);
	background: -ms-linear-gradient(top, rgba(248,248,248,.3) 20%, rgba(168,173,190,.5) 50%, rgba(168,173,190,.3) 51%, rgba(248,248,248,.2) 90%);
	background: linear-gradient(top, rgba(248,248,248,.3) 20%, rgba(168,173,190,.5) 50%, rgba(168,173,190,.3) 51%, rgba(248,248,248,.2) 90%);
	box-shadow:inset 0 -2px 0 #f1f1f1,0 1px 0 #f1f1f1,0 2px 0 #9fa2ad,0 3px 0 #f1f1f1,0 4px 0 #9fa2ad;
}
.box li:before,
.box li:after{
	display: block;
	content: "";
	position: absolute;
	width: 56px;
}
.box li:before{
	top:50%;
	height: 1px;
	box-shadow: 0 1px 0 #868995,0 2px 1px #fff;
}
.box li:after{
	bottom: -25px;
	height: 30px;
	border-radius:0 0 5px 5px;
	background: -webkit-gradient(linear,0 0,0 100%,from(rgba(0,0,0,.1)),to(rgba(0,0,0,0)));
	background: -webkit-linear-gradient(top,rgba(0,0,0,.1),rgba(0,0,0,0));
	background: -moz-linear-gradient(top,rgba(0,0,0,.1),rgba(0,0,0,0));
	background: -o-linear-gradient(top,rgba(0,0,0,.1),rgba(0,0,0,0));
	background: -ms-linear-gradient(top,rgba(0,0,0,.1),rgba(0,0,0,0));
	background: linear-gradient(top,rgba(0,0,0,.1),rgba(0,0,0,0));
	z-index: 1
}
.box li span:first-child{
	font:35px 'BitstreamVeraSansMonoBold';
	color: #52555a;
	display: block;
	height: 36px;
	line-height: 35px;
}
</style>
</head>
<body>

<div class="box">
	<ul>
		<li><span id="hour"></span><span>时</span></li>
		<li><span id="minute"></span><span>分</span></li>
		<li><span id="second"></span><span>秒</span></li>
	</ul>
</div>

<script type="text/javascript">
var hour=document.getElementById('hour');
var minute=document.getElementById('minute');
var second=document.getElementById('second');
function showTime(){
	var oDate=new Date();
	var iHours=oDate.getHours();
	var iMinute=oDate.getMinutes();
	var iSecond=oDate.getSeconds();
	hour.innerHTML=AddZero(iHours);
	minute.innerHTML=AddZero(iMinute);
	second.innerHTML=AddZero(iSecond);
}
showTime();
setInterval(showTime,1000);
function AddZero(n){
	if(n<10){
		return '0'+n;
	}
	return ''+n;
}
</script>

<div style="text-align:center;margin:10px 0; font:normal 14px/24px 'MicroSoft YaHei';">

</div>
</body>
