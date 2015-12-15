<%--
/**
 * Copyright (C) 2015 Rivet Logic Corporation. All rights reserved.
 */
--%>

<%@ include file="/html/portlet/asset_publisher/init.jsp" %>

<%
AssetEntry assetEntry = (AssetEntry)request.getAttribute("view.jsp-assetEntry");
AssetRenderer assetRenderer = (AssetRenderer)request.getAttribute("view.jsp-assetRenderer");

String title = (String)request.getAttribute("view.jsp-title");
String viewURL = AssetPublisherHelperImpl.getAssetViewURL(liferayPortletRequest, liferayPortletResponse, assetEntry, viewInContext);
String summary = StringUtil.shorten(assetRenderer.getSummary(locale), abstractLength);
String thumbnailPath = assetRenderer.getThumbnailPath(liferayPortletRequest);
%>

<div class="infinite-asset-abstract">
	<img style="width:225px; height:200px;" src="<%= thumbnailPath %>" />
	<h4 class="asset-title">
		<a href="<%= viewURL %>"><%= HtmlUtil.escape(title) %></a>
	</h4>
	<br>
	<div class="asset-summary">
		<%= HtmlUtil.escape(summary) %>
	</div>
</div>