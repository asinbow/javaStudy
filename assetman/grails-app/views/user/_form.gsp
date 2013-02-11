<%@ page import="com.asinbow.assetman.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="user.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="20" pattern="${userInstance.constraints.name.matches}" required="" value="${userInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="user.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${userInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'deleted', 'error')} ">
	<label for="deleted">
		<g:message code="user.deleted.label" default="Deleted" />
		
	</label>
	<g:checkBox name="deleted" value="${userInstance?.deleted}" />
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'roll', 'error')} required">
	<label for="roll">
		<g:message code="user.roll.label" default="Roll" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="roll" name="roll.id" from="${com.asinbow.assetman.Roll.list()}" optionKey="id" required="" value="${userInstance?.roll?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'author', 'error')} ">
	<label for="author">
		<g:message code="user.author.label" default="Author" />
		
	</label>
	<g:select id="author" name="author.id" from="${com.asinbow.assetman.User.list()}" optionKey="id" value="${userInstance?.author?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

