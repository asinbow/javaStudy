<%@ page import="com.asinbow.assetman.Asset" %>



<div class="fieldcontain ${hasErrors(bean: assetInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="asset.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${assetInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: assetInstance, field: 'deleted', 'error')} ">
	<label for="deleted">
		<g:message code="asset.deleted.label" default="Deleted" />
		
	</label>
	<g:checkBox name="deleted" value="${assetInstance?.deleted}" />
</div>

