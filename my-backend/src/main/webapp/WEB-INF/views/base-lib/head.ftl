<nav class="navbar user-info-navbar" role="navigation" style="height:40px;">
	
	<ul class="user-info-menu left-links list-inline list-unstyled">
		
		<li class="hidden-sm hidden-xs">
			<a href="#" data-toggle="sidebar">
				<i class="fa-bars"></i>
			</a>
		</li>
		
		<!-- <li class="dropdown hover-line">
			<a href="#" data-toggle="dropdown">
				<i class="fa-envelope-o"></i>
				<span class="badge badge-green">1</span>
			</a>
			<ul class="dropdown-menu messages">
				<li>
					<ul class="dropdown-menu-list list-unstyled ps-scrollbar" style="padding-left:0;">
						<li class="active">
							<a href="#">
								<span class="line">
									<strong>Luc Chartier</strong>
									<span class="light small">- yesterday</span>
								</span>
								
								<span class="line desc small">
									This ainât our first item, it is the best of the rest.
								</span>
							</a>
						</li>
						
					</ul>
				</li>
				<li class="external">
					<a href="blank-sidebar.html">
						<span>All Messages</span>
						<i class="fa-link-ext"></i>
					</a>
				</li>
			</ul>
		</li> -->
		
		<!-- <li class="dropdown hover-line">
			<a href="#" data-toggle="dropdown">
				<i class="fa-bell-o"></i>
				<span class="badge badge-purple">7</span>
			</a>
				
			<ul class="dropdown-menu notifications">
				<li class="top">
					<p class="small">
						<a href="#" class="pull-right">Mark all Read</a>
						You have <strong>3</strong> new notifications.
					</p>
				</li>
				<li>
					<ul class="dropdown-menu-list list-unstyled ps-scrollbar" style="padding-left:0;">
						<li class="active notification-success">
							<a href="#">
								<i class="fa-user"></i>
								
								<span class="line">
									<strong>New user registered</strong>
								</span>
								
								<span class="line small time">
									30 seconds ago
								</span>
							</a>
						</li>
						
						<li class="active notification-secondary">
							<a href="#">
								<i class="fa-lock"></i>
								
								<span class="line">
									<strong>Privacy settings have been changed</strong>
								</span>
								
								<span class="line small time">
									3 hours ago
								</span>
							</a>
						</li>
						
						<li class="notification-primary">
							<a href="#">
								<i class="fa-thumbs-up"></i>
								
								<span class="line">
									<strong>Someone special liked this</strong>
								</span>
								
								<span class="line small time">
									2 minutes ago
								</span>
							</a>
						</li>
						
						<li class="notification-danger">
							<a href="#">
								<i class="fa-calendar"></i>
								
								<span class="line">
									John cancelled the event
								</span>
								
								<span class="line small time">
									9 hours ago
								</span>
							</a>
						</li>
						
						<li class="notification-info">
							<a href="#">
								<i class="fa-database"></i>
								
								<span class="line">
									The server is status is stable
								</span>
								
								<span class="line small time">
									yesterday at 10:30am
								</span>
							</a>
						</li>
						
						<li class="notification-warning">
							<a href="#">
								<i class="fa-envelope-o"></i>
								
								<span class="line">
									New comments waiting approval
								</span>
								
								<span class="line small time">
									last week
								</span>
							</a>
						</li>
					</ul>
				</li>
				
				<li class="external">
					<a href="#">
						<span>View all notifications</span>
						<i class="fa-link-ext"></i>
					</a>
				</li>
			</ul>
		</li> -->
	</ul>
	
	<ul class="user-info-menu right-links list-inline list-unstyled">
		<!-- 搜索框 -->
		<li class="search-form">
			<form method="get" action="extra-search.html">
				<input type="text" name="s" class="form-control search-field" placeholder="输入搜索内容..." />
				<button type="submit" class="btn btn-link">
					<i class="linecons-search"></i>
				</button>
			</form>
		</li>
		<!-- 个人中心 -->
		<li class="dropdown user-profile">
			<!-- <a href="/logout"><i class="fa-lock"></i><span class="title">&nbsp;退出</span></a> -->
			<a href="#" data-toggle="dropdown">
				<img src="/img/user-4.png" alt="user-image" class="img-circle img-inline userpic-32" width="28" />
				<#if userInfo??>
					<span>${userInfo.username}<i class="fa-angle-down"></i></span>
				</#if>
			</a>
			<ul class="dropdown-menu user-profile-menu list-unstyled">
				<li><a href="/backend/profile/index"><i class="fa-user"></i>个人中心</a></li>
				<li><a href="/backend/sys/index"><i class="fa-wrench"></i>系统配置</a></li>
				<li class="last"><a href="/logout"><i class="fa-lock"></i>退出</a></li>
			</ul> 
		</li>
	</ul>
</nav>
			
<!-- <div class="page-loading-overlay">
	<div class="loader-2"></div>
</div> -->

