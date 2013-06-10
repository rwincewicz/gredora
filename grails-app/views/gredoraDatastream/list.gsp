
<%@ page import="gredora.GredoraDatastream" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gredoraDatastream.label', default: 'GredoraDatastream')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gredoraDatastream" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gredoraDatastream" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
                                  <tr>
						<g:sortableColumn property="dsId" title="${message(code: 'ds.dsId.label', default: 'Ds Id')}" />
					
						<g:sortableColumn property="dsLabel" title="${message(code: 'ds.dsLabel.label', default: 'Ds Label')}" />
					
						<g:sortableColumn property="dsLocation" title="${message(code: 'ds.dsLocation.label', default: 'Ds Location')}" />
					
						<g:sortableColumn property="dsMIMEType" title="${message(code: 'ds.dsMIMEType.label', default: 'Ds MIME Type')}" />
                                                
                                                <g:sortableColumn property="dsChecksum" title="${message(code: 'ds.dsChecksum.label', default: 'Ds Checksum')}" />
					
						<g:sortableColumn property="dsChecksumType" title="${message(code: 'ds.dsChecksumType.label', default: 'Ds Checksum Type')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dsList}" status="i" var="ds">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link url="${resource(file: pid)}/datastream/${ds.dsId}">${fieldValue(bean: ds, field: "dsId")}</g:link></td>
					
						<td>${fieldValue(bean: ds, field: "dsLabel")}</td>
					
						<td>${fieldValue(bean: ds, field: "dsLocation")}</td>
					
						<td>${fieldValue(bean: ds, field: "dsMIMEType")}</td>
                                                
                                                <td>${fieldValue(bean: gredoraDatastreamInstance, field: "dsChecksum")}</td>
					
						<td>${fieldValue(bean: ds, field: "dsChecksumType")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			
		</div>
	</body>
</html>
