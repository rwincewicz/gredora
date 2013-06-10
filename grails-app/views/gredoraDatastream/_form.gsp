<%@ page import="gredora.GredoraDatastream" import="gredora.DatastreamUtilService" %>
<head>
<g:javascript src="createds.js"/>
<link type="text/css" href="${resource(dir: 'css', file: 'gredora.css')}"/>
</head>


<div class="fieldcontain ${hasErrors(bean: gredoraDatastreamInstance, field: 'pid', 'error')} ">
	<label for="pid">
		<g:message code="gredoraDatastream.pid.label" default="Pid" />
		
	</label>
	${gds?.pid}
        <g:hiddenField name="pid" value="${gds?.pid}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gds, field: 'dsId', 'error')} ">
	<label for="dsId">
		<g:message code="gds.dsId.label" default="Ds Id" />
		
	</label>
	<g:textField name="formDsId" value="${gds?.dsId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gds, field: 'dsLabel', 'error')} ">
	<label for="dsLabel">
		<g:message code="gds.dsLabel.label" default="Ds Label" />
		
	</label>
	<g:textField name="dsLabel" value="${gds?.dsLabel}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gds, field: 'dsLocation', 'error')} ">
	<label for="dsLocation">
		<g:message code="gds.dsLocation.label" default="Ds Location" />
		
	</label>
	<g:textField name="dsLocation" value="${gds?.dsLocation}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gds, field: 'dsState', 'error')} ">
	<label for="dsState">
		<g:message code="gds.dsState.label" default="Ds State" />
		
	</label>
	<g:select name="type" from="${['A', 'I', 'D']}" value="${gds?.dsState}" />
</div>

<div class="fieldcontain ${hasErrors(bean: gds, field: 'dsControlGroup', 'error')} ">
	<label for="dsControlGroup">
		<g:message code="gds.dsControlGroup.label" default="Ds Control Group" />
		
	</label>
	<g:select name="dsControlGroup" from="${['X', 'M', 'R', 'E']}" value="${gds?.dsControlGroup}" onchange="showStringContent()"/>
</div>

<div class="fieldcontain" id="stringContent">
  <label for="dsContent">
		<g:message code="gds.dsContent.label" default="Ds Content"/>
		
	</label>
  <g:textArea name="dsContent" escapeHtml="false"/>
</div>

<div class="fieldcontain ${hasErrors(bean: gds, field: 'size', 'error')}">
	<label for="size">
		<g:message code="gds.size.label" default="Size" />
	</label>
	${gds.size}
</div>

<div class="fieldcontain ${hasErrors(bean: gds, field: 'dsChecksum', 'error')} ">
	<label for="dsChecksum">
		<g:message code="gds.dsChecksum.label" default="Ds Checksum" />
		
	</label>
	${gds?.dsChecksum}
</div>

<div class="fieldcontain ${hasErrors(bean: gds, field: 'dsChecksumType', 'error')} ">
	<label for="dsChecksumType">
		<g:message code="gds.dsChecksumType.label" default="Ds Checksum Type" />
		
	</label>
	<g:select name="dsChecksumType" from="${['Disabled', 'Default', 'MD5', 'SHA-1', 'SHA-256', 'SHA-512']}" value="${gds?.dsChecksumType}" />
</div>



