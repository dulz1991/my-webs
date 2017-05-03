<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<ul class="nav navbar-head left fixed">
		<li><a href="/"><i class="fa fa-home"></i> Home</a></li>
		<li class="active"><a href="/blog"><i class="fa fa-fire"></i> Blog</a></li>
		<li><a href="/code"><i class="fa fa-book"></i> Code</a></li>
		<li><a href="http://my.pic"><i class="fa fa-picture-o"></i> Pic</a></li>
		<li class="search">
			<form method="get">
				<input type="text" name="search" class="search" placeholder="Search" />
			</form>
		</li>
	    <c:if test="${role==null}">
	    	<li class="right"><a href="/login"><i class="fa fa-sign-in"></i> Login</a></li>
	    </c:if>
	    <c:if test="${role!=null}">
	    	<li class="right"><a href="/account/logout"><i class="fa fa-sign-out"></i>Logout</a></li>
	    	<li class="right"><a href="javascript:;" onclick="doJump(${role})"><i class="fa fa-user"></i>${username}</a></li>
	    </c:if>
	</ul>
	<%-- <ul class="navbar-head navbar-right" style="float:right">
		<li><a href="/blog/create"><i class="fa fa-plus"></i> Create</a></li>
		<c:if test="${role!=null}">
	    	<li><a href="javascript:;" onclick="doJump(${role})"><i class="fa fa-user"></i>${username}</a></li>
	        <li><a href="/account/logout"><i class="fa fa-sign-out"></i>Logout</a></li>
	    </c:if>
	    <c:if test="${role==null}">
	    	<li><a href="/login"><i class="fa fa-sign-in"></i> Login</a></li>
	    </c:if>
	 </ul> --%>
</div>
	
<script type="text/javascript">
	function doJump(role) {
		if (role == 1) {
			window.location.href = 'http://my.user/admin';
		} else if ( role == 2 ) {
			window.location.href = 'http://my.user/user';
		}
	}
</script>
