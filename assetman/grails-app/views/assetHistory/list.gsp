
<%@ page import="com.asinbow.assetman.AssetHistory" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'assetHistory.label', default: 'AssetHistory')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-assetHistory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-assetHistory" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="assetHistory.asset.label" default="Asset" /></th>
					
						<th><g:message code="assetHistory.creator.label" default="Creator" /></th>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'assetHistory.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${assetHistoryInstanceList}" status="i" var="assetHistoryInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${assetHistoryInstance.id}">${fieldValue(bean: assetHistoryInstance, field: "asset")}</g:link></td>
					
						<td>${fieldValue(bean: assetHistoryInstance, field: "creator")}</td>
					
						<td><g:formatDate date="${assetHistoryInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${assetHistoryInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
