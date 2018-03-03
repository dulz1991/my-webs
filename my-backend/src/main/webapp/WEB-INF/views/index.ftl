<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="后台管理首页"  base_keywords="后台管理首页" openIndex=0 activeIndex=0>
	
	<#-- @base 中间的内容将嵌套至 base 宏中的#nested处 -->
    <div class="main-content" style="">
	
		<div class="row">
			<div class="col-sm-3">
				<div class="xe-widget xe-counter xe-counter-blue" data-count=".num" data-from="0" data-to="${userCount}" data-duration="3" data-easing="false">
					<div class="xe-icon">
						<i class="linecons-user"></i>
					</div>
					<div class="xe-label">
						<strong class="num">${userCount}</strong>
						<span>用户数量</span>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="0" data-to="${blogCount}" data-duration="4" data-easing="true">
					<div class="xe-icon">
						<i class="linecons-cloud"></i>
					</div>
					<div class="xe-label">
						<strong class="num">${blogCount}</strong>
						<span>Blog数量</span>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="0" data-to="${codeCount}" data-duration="4" data-easing="true">
					<div class="xe-icon">
						<i class="linecons-cloud"></i>
					</div>
					<div class="xe-label">
						<strong class="num">${codeCount}</strong>
						<span>Code数量</span>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="0" data-to="${demoCount}" data-duration="4" data-easing="true">
					<div class="xe-icon">
						<i class="linecons-cloud"></i>
					</div>
					<div class="xe-label">
						<strong class="num">${demoCount}</strong>
						<span>Demo数量</span>
					</div>
				</div>
			</div>
		</div>		
		<div class="row">
			<div class="col-sm-3">
				<div class="xe-widget xe-counter xe-counter-info" data-count=".num" data-from="0" data-to="${discoveryCount}" data-duration="4" data-easing="true">
					<div class="xe-icon">
						<i class="linecons-cloud"></i>
					</div>
					<div class="xe-label">
						<strong class="num">${discoveryCount}</strong>
						<span>Discovery数量</span>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-sm-3">
				<div class="xe-widget xe-counter-block xe-counter-block-yellow" data-count=".num" data-from="0" data-to="${userImg.imgSize/1024/1024}" data-duration="3">
					<div class="xe-upper">
						<div class="xe-icon">
							<i class="linecons-attach"></i>
						</div>
						<div class="xe-label">
							<strong class="num">${userImg.imgSize/1024/1024}</strong>
							<span>MB</span>
						</div>
					</div>
					<div class="xe-lower">
						<div class="border"></div>
						<strong>${userImg.imgCount}个文件(user)</strong>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="xe-widget xe-counter-block xe-counter-block-yellow" data-count=".num" data-from="0" data-to="${blogrImg.imgSize/1024/1024}" data-duration="3">
					<div class="xe-upper">
						<div class="xe-icon">
							<i class="linecons-attach"></i>
						</div>
						<div class="xe-label">
							<strong class="num">${blogrImg.imgSize/1024/1024}</strong>
							<span>MB</span>
						</div>
					</div>
					<div class="xe-lower">
						<div class="border"></div>
						<strong>${blogrImg.imgCount}个文件(blog)</strong>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="xe-widget xe-counter-block xe-counter-block-yellow" data-count=".num" data-from="0" data-to="${codeImg.imgSize/1024/1024}" data-duration="3">
					<div class="xe-upper">
						<div class="xe-icon">
							<i class="linecons-attach"></i>
						</div>
						<div class="xe-label">
							<strong class="num">${codeImg.imgSize/1024/1024}</strong>
							<span>MB</span>
						</div>
					</div>
					<div class="xe-lower">
						<div class="border"></div>
						<strong>${codeImg.imgCount}个文件(code)</strong>
					</div>
				</div>
			</div>
			<div class="col-sm-3">
				<div class="xe-widget xe-counter-block xe-counter-block-yellow" data-count=".num" data-from="0" data-to="${discoveryImg.imgSize/1024/1024}" data-duration="3">
					<div class="xe-upper">
						<div class="xe-icon">
							<i class="linecons-attach"></i>
						</div>
						<div class="xe-label">
							<strong class="num">${discoveryImg.imgSize/1024/1024}</strong>
							<span>MB</span>
						</div>
					</div>
					<div class="xe-lower">
						<div class="border"></div>
						<strong>${discoveryImg.imgCount}个文件(discovery)</strong>
					</div>
				</div>
			</div>
		</div>	
			
	</div>

</@base> 