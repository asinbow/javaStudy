
<%@ page import="com.asinbow.assetman.Roll" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'roll.label', default: 'Roll')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-roll" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-roll" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list roll">
			
				<g:if test="${rollInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="roll.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${rollInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rollInstance?.adminRead}">
				<li class="fieldcontain">
					<span id="adminRead-label" class="property-label"><g:message code="roll.adminRead.label" default="Admin Read" /></span>
					
						<span class="property-value" aria-labelledby="adminRead-label"><g:formatBoolean boolean="${rollInstance?.adminRead}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${rollInstance?.adminWrite}">
				<li class="fieldcontain">
					<span id="adminWrite-label" class="property-label"><g:message code="roll.adminWrite.label" default="Admin Write" /></span>
					
						<span class="property-value" aria-labelledby="adminWrite-label"><g:formatBoolean boolean="${rollInstance?.adminWrite}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${rollInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="roll.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${rollInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${rollInstance?.normalRead}">
				<li class="fieldcontain">
					<span id="normalRead-label" class="property-label"><g:message code="roll.normalRead.label" default="Normal Read" /></span>
					
						<span class="property-value" aria-labelledby="normalRead-label"><g:formatBoolean boolean="${rollInstance?.normalRead}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${rollInstance?.normalWrite}">
				<li class="fieldcontain">
					<span id="normalWrite-label" class="property-label"><g:message code="roll.normalWrite.label" default="Normal Write" /></span>
					
						<span class="property-value" aria-labelledby="normalWrite-label"><g:formatBoolean boolean="${rollInstance?.normalWrite}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${rollInstance?.id}" />
					<g:link class="edit" action="edit" id="${rollInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
