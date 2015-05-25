<%@page import="model.Document"%>
<%@page import="servlets.SearchServlet"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Azure Search - Feature Search</title>
</head>
<body style="font-size:32px">
<script type="text/javascript">
    var appInsights=window.appInsights||function(config){
        function s(config){t[config]=function(){var i=arguments;t.queue.push(function(){t[config].apply(t,i)})}}var t={config:config},r=document,f=window,e="script",o=r.createElement(e),i,u;for(o.src=config.url||"//az416426.vo.msecnd.net/scripts/a/ai.0.js",r.getElementsByTagName(e)[0].parentNode.appendChild(o),t.cookie=r.cookie,t.queue=[],i=["Event","Exception","Metric","PageView","Trace"];i.length;)s("track"+i.pop());return config.disableExceptionTracking||(i="onerror",s("_"+i),u=f[i],f[i]=function(config,r,f,e,o){var s=u&&u(config,r,f,e,o);return s!==!0&&t["_"+i](config,r,f,e,o),s}),t
    }({
        instrumentationKey:"e753cfc2-643d-42d8-8467-837445d7d9a0"
    });

    window.appInsights=appInsights;
    appInsights.trackPageView();
</script>
	<h2>Azure Search DEMO - US Open Data<br/> Features Search for Washington State</h2>
	<form action="SearchServlet" method="POST">
		<input type="text" name="SearchQuery" size="100" >
		<input type="submit" value="Search" />
	</form>
	<br/>
<%= request.getAttribute("search") %><br/>
	<%
		List<Document> DocList = (List<Document>) request.getAttribute("DocList");

		if (DocList != null) {
	%>
	<br/>
	<table border="1">
		<tr>
			<td>Feature Name</td>
			<td>Feature Class</td>
			<td>State Alpha</td>
			<td>County Name</td>
			<td>Longitude</td>
			<td>Latitude</td>
			<td>Elevation (m))</td>
			<td>Elevation (ft)</td>
			<td>Map Name</td>
			<td>Description</td>
			<td>History</td>
			<td>Date Created</td>
			<td>Date Edited</td>
		</tr>
		<%
			for (int i = 0; i < DocList.size(); i++) {
		%>
		<tr>
			<td><%=DocList.get(i).getFeatureName()%></td>
			<td><%=DocList.get(i).getFeatureClass()%></td>
			<td><%=DocList.get(i).getStateAlpha()%></td>
			<td><%=DocList.get(i).getCountyName()%></td>
			<td><%=DocList.get(i).getLatitude()%></td>
			<td><%=DocList.get(i).getLongitude()%></td>
			<td><%=DocList.get(i).getElevationMeter()%></td>
			<td><%=DocList.get(i).getElevationFt()%></td>
			<td><%=DocList.get(i).getMapName()%></td>
			<td><%=DocList.get(i).getDescription()%></td>
			<td><%=DocList.get(i).getHistory()%></td>
			<td><%=DocList.get(i).getDateCreated()%></td>
			<td><%=DocList.get(i).getDateEdited()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
	%>
</body>

</html>