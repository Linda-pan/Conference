<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="subMenuList" value="${sessionScope.user_menu.secondMenuList }" />
<div class="row-fluid" id="report-nav-tabs">
	<ul id="yw0" class="nav nav-pills">
	<c:if test="${not empty subMenuList }">
	<c:forEach items="${subMenuList }" var="subMenu" >
	<c:choose>
	<c:when test="${subMenu.menu_url==currentMenu }">
	<li class="span2 report-nav-item active"><a href="<c:url value="${subMenu.menu_url }" />">${subMenu.name }</a></li>
	</c:when>
	<c:otherwise>
	<li class="span2 report-nav-item"><a href="<c:url value="${subMenu.menu_url }" />">${subMenu.name }</a></li>
	</c:otherwise>
	</c:choose>
	
	</c:forEach>
	</c:if>	
	</ul>
<!-- 	<div class="tab-content"> -->
<!-- 		<div id="report-nav-tabs_tab_1" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_2" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_3" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_4" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_5" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_6" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_7" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_8" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_9" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_10" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_11" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_12" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_13" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_14" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_15" class="tab-pane fade"></div> -->
<!-- 		<div id="report-nav-tabs_tab_16" class="tab-pane fade"></div> -->
<!-- 	</div> -->
</div>
