
<%@ page import="gredora.GredoraObject" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gredoraObject.label', default: 'GredoraObject')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-gredoraObject" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-gredoraObject" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
                                          <th>Pid</th>
                                          <th>Title</th>
                                          <th>Type</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${objectList}" status="i" var="object">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link url="${resource(file: object.id)}">${object.id}</g:link></td>
					
                                <td><g:link url="${resource(file: object.id)}">${object.title}</g:link></td>
                                
						<td>${object.type}</td>
					
					</tr>
				</g:each>
 
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${gredoraObjectInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
