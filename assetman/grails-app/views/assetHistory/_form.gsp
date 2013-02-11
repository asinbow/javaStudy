<%@ page import="com.asinbow.assetman.AssetHistory" %>



<div class="fieldcontain ${hasErrors(bean: assetHistoryInstance, field: 'asset', 'error')} required">
	<label for="asset">
		<g:message code="assetHistory.asset.label" default="Asset" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="asset" name="asset.id" from="${com.asinbow.assetman.Asset.list()}" optionKey="id" required="" value="${assetHistoryInstance?.asset?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: assetHistoryInstance, field: 'creator', 'error')} required">
	<label for="creator">
		<g:message code="assetHistory.creator.label" default="Creator" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="creator" name="creator.id" from="${com.asinbow.assetman.User.list()}" optionKey="id" required="" value="${assetHistoryInstance?.creator?.id}" class="many-to-one"/>
</div>

