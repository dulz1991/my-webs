<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

	<!-- 左侧导航栏 -->
    <div class="page-sidebar" id="sidebar"> 
        
        <!-- 菜单搜索 -->
        <div class="sidebar-header-wrapper">
            <input type="text" class="searchinput" id="menuAutoCompleteSearch" placeholder="输入菜单名称" />
            <i class="searchicon fa fa-search"></i>
            <!-- <div class="searchhelper">输入菜单名称，搜索菜单</div> -->
        </div>
        <!-- 菜单搜索 结束 -->
        
        <!-- 左侧菜单 -->
        <ul class="nav sidebar-menu">
            <!-- 首页 -->
            <li>
                <a href="/index">
                    <i class="menu-icon glyphicon glyphicon-home"></i>
                    <span class="menu-text"> 首页 </span>
                </a>
            </li>
            
            <!-- 遍历菜单 -->
            <c:forEach var="item" items="${menus}" varStatus="status">
            	<!-- 一级菜单 -->
	    		<li class="levelOneMenu">
	    			<a href="javascript:;" 
	    				<c:if test="${empty item.menus }">onclick="gotoUrl('${item.url }')" toUrl="${item.url }"</c:if>
	    				<c:if test="${not empty item.menus }">class="menu-dropdown"</c:if> 
	    				>
	    				<i class="menu-icon ${item.icon}"></i>
	    				<span class="menu-text"> ${item.name }</span>
	    				<c:if test="${not empty item.menus }"><i class="menu-expand"></i></c:if>
	    			</a>
	    			<!-- 二级菜单 -->
						<ul class="submenu" style="display: none;">
							<c:forEach var="subItem" items="${item.menus}">
								<li  class="levelTwoMenu">
									<a href="javascript:;" 
										<c:if test="${empty subItem.menus }">onclick="gotoUrl('${subItem.url }')" toUrl="${subItem.url }"</c:if>
	    								<c:if test="${not empty subItem.menus }">class="menu-dropdown"</c:if> 
									>
			                        	<span class="menu-text"> ${subItem.name }</span>
			                        	<c:if test="${not empty subItem.menus }"><i class="menu-expand"></i></c:if>
			                        </a>
			        			<!-- 三级菜单 -->            
			                    <c:if test="${not empty subItem.menus }">
									<ul class="submenu" style="display: none;">
										<c:forEach var="lastItem" items="${subItem.menus}">
											<li  class="levelThreeMenu">
												<a href="javascript:;" onclick="gotoUrl('${lastItem.url }')" toUrl="${lastItem.url }" >
						                        	<span class="menu-text"> ${lastItem.name }</span>
						                        </a>
						                    </li>			
										</c:forEach>
									</ul>	
			                    </c:if>
			                    </li>
							</c:forEach>
						</ul>
					</li>
	           	</li>
	    	</c:forEach>
	    	<!-- 遍历菜单 结束 -->
        </ul>
    </div>
    <!-- 左侧导航栏结束 -->