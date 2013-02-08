<%@ page import="com.asinbow.assetman.Roll" %>



<div class="fieldcontain ${hasErrors(bean: rollInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="roll.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" maxlength="20" pattern="${rollInstance.constraints.name.matches}" required="" value="${rollInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rollInstance, field: 'adminRead', 'error')} ">
	<label for="adminRead">
		<g:message code="roll.adminRead.label" default="Admin Read" />
		
	</label>
	<g:checkBox name="adminRead" value="${rollInstance?.adminRead}" />
</div>

<div class="fieldcontain ${hasErrors(bean: rollInstance, field: 'adminWrite', 'error')} ">
	<label for="adminWrite">
		<g:message code="roll.adminWrite.label" default="Admin Write" />
		
	</label>
	<g:checkBox name="adminWrite" value="${rollInstance?.adminWrite}" />
</div>

<div class="fieldcontain ${hasErrors(bean: rollInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="roll.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${rollInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: rollInstance, field: 'normalRead', 'error')} ">
	<label for="normalRead">
		<g:message code="roll.normalRead.label" default="Normal Read" />
		
	</label>
	<g:checkBox name="normalRead" value="${rollInstance?.normalRead}" />
</div>

<div class="fieldcontain ${hasErrors(bean: rollInstance, field: 'normalWrite', 'error')} ">
	<label for="normalWrite">
		<g:message code="roll.normalWrite.label" default="Normal Write" />
		
	</label>
	<g:checkBox name="normalWrite" value="${rollInstance?.normalWrite}" />
</div>

