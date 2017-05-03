
<div class="navbar-fixed" style="z-index:99999;">
	<nav>
		<div class="nav-wrapper">
			<a href="/" class="left brand-logo">&nbsp;&nbsp;My Journey</a>
			<ul class="right hide-on-med-and-down">
				<li><a href="/list/"><i class="fa fa-list"></i>&nbsp;游记列表</a></li>
				<#if isLogin>
					<li><a href="/auth/user/index"><i class="fa fa-user"></i>&nbsp;Username</a></li>
				<#else>
					<li><a href="/login/" class="tooltipped" data-position="bottom" data-delay="50" data-tooltip="登陆"><i class="fa fa-sign-in"></i>&nbsp;</a></li>
				</#if>
				<li><a href="/create/" class="tooltipped" data-position="bottom" data-delay="50" data-tooltip="发表游记"><i class="fa fa-plus"></i>&nbsp;</a></li>
			</ul>
		</div>
	</nav>
</div>

<div class="fixed-action-btn" style="bottom: 45px; right: 24px;">
  <a class="btn-floating btn-large red">
    <i class="material-icons">mode_edit</i>
  </a>
  <ul>
    <li><a class="btn-floating red" style="transform: scaleY(0.4) scaleX(0.4) translateY(40px) translateX(0px); opacity: 0;"><i class="material-icons">insert_chart</i></a></li>
    <li><a class="btn-floating yellow darken-1" style="transform: scaleY(0.4) scaleX(0.4) translateY(40px) translateX(0px); opacity: 0;"><i class="material-icons">format_quote</i></a></li>
    <li><a class="btn-floating green" style="transform: scaleY(0.4) scaleX(0.4) translateY(40px) translateX(0px); opacity: 0;"><i class="material-icons">publish</i></a></li>
    <li><a class="btn-floating blue" style="transform: scaleY(0.4) scaleX(0.4) translateY(40px) translateX(0px); opacity: 0;"><i class="material-icons">attach_file</i></a></li>
  </ul>
</div>


