
<div class="navbar" style="z-index:99999;">
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
        			</ul>
      			</div>
    		</nav>
  		</div>