
<%@ page import="com.asinbow.assetman.Roll" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'roll.label', default: 'Roll')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-roll" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-roll" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'roll.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="adminRead" title="${message(code: 'roll.adminRead.label', default: 'Admin Read')}" />
					
						<g:sortableColumn property="adminWrite" title="${message(code: 'roll.adminWrite.label', default: 'Admin Write')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'roll.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="normalRead" title="${message(code: 'roll.normalRead.label', default: 'Normal Read')}" />
					
						<g:sortableColumn property="normalWrite" title="${message(code: 'roll.normalWrite.label', default: 'Normal Write')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${rollInstanceList}" status="i" var="rollInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${rollInstance.id}">${fieldValue(bean: rollInstance, field: "name")}</g:link></td>
					
						<td><g:formatBoolean boolean="${rollInstance.adminRead}" /></td>
					
						<td><g:formatBoolean boolean="${rollInstance.adminWrite}" /></td>
					
						<td>${fieldValue(bean: rollInstance, field: "description")}</td>
					
						<td><g:formatBoolean boolean="${rollInstance.normalRead}" /></td>
					
						<td><g:formatBoolean boolean="${rollInstance.normalWrite}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${rollInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
