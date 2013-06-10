
<%@ page import="gredora.GredoraObject" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'gredoraObject.label', default: 'GredoraObject')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-gredoraObject" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-gredoraObject" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list gredoraObject">
                          
                          <g:if test="${gredoraObject?.id}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="gredoraObject.id.label" default="Id" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${gredoraObject}" field="id"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gredoraObject?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="gredoraObject.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${gredoraObject}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${gredoraObject?.type}">
				<li class="fieldcontain">
					<span id="type-label" class="property-label"><g:message code="gredoraObject.type.label" default="Type" /></span>
					
						<span class="property-value" aria-labelledby="type-label"><g:fieldValue bean="${gredoraObject}" field="type"/></span>
					
				</li>
				</g:if>
                          <br>
                          <div>
                            <h3>Datastreams</h3>
                            <table>
                              <tbody>
                                <tr>
                              <th>DSID</th>
                              <th>Label</th>
                              <th>State</th>
                                </tr>
                                <g:each in="${ds}" status="i" var="it">
                                  <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                                    <td><g:link url="${resource(file: '')}/${gredoraObject.id}/datastream/${it.dsId}">${it.dsId}</g:link></td>
                                    <td>${it.dsLabel}</td>
                                    <td>${it.dsState}</td>
                                </tr>
                                </g:each>
                                
                              </tbody>
                            </table>
                          </div>
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${gredoraObject?.id}" />
					<g:link class="edit" action="edit" id="${gredoraObject?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
