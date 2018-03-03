<#include "/base-lib/baseMacro.ftl"> 
<@base base_title="系统配置" openIndex=6 activeIndex=0>
	
	<div class="main-content" style="margin:0;padding:0">
					
			<section class="mailbox-env">
				<div class="row">
					<div class="col-sm-9 mailbox-right">
						<#if active=='0'>
							<#include "form_blog_syscfg.ftl">
						</#if>
						<#if active=='1'>
							<#include "form_excute_syscfg.ftl">
						</#if>				
					</div>
					
					<div class="col-sm-3 mailbox-left">
						<div class="mailbox-sidebar">
							<ul class="list-unstyled mailbox-list">
								<li class="<#if active=='0'>active</#if>">
									<a href="/backend/sys/index?active=0">
										博客首页数据 
										<span class="badge badge-success pull-right">1</span>
									</a>
								</li>
								<li class="<#if active=='1'>active</#if>">
									<a href="/backend/sys/index?active=1">
										任务执行
										<span class="badge badge-success pull-right">1</span>
									</a>
								</li>
								<li class="<#if active=='2'>active</#if>">
									<a href="/backend/sys/index?active=2">
										公共
										<span class="badge badge-success pull-right">0</span>
									</a>
								</li>
							</ul>
							<div class="vspacer"></div>
						</div>
					</div>
					
				</div>
			</section>
		</div>

</@base> 