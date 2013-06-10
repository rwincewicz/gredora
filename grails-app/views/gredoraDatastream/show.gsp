
<%@ page import="gredora.GredoraDatastream" %>
<!DOCTYPE html>
<html>
  <head>
    <script src="${resource(dir: 'js')}/run_prettify.js"></script>
    <link href="${resource(dir: 'css')}/prettify.css" type="text/css" rel="stylesheet" />
    <meta name="layout" content="main">
  <g:set var="entityName" value="${message(code: 'gredoraDatastream.label', default: 'GredoraDatastream')}" />
  <title><g:message code="default.show.label" args="[entityName]" /></title>
</head>
<body>
  <a href="#show-gredoraDatastream" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
  <div class="nav" role="navigation">
    <ul>
      <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
      <li><g:link class="list" action="list" params="${[pid: gds?.pid]}"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
      <li><g:link class="create" action="create" params="${[pid: gds?.pid]}"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
  </div>
  <div id="show-gredoraDatastream" class="content scaffold-show" role="main">
    <h1><g:message code="default.show.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
      <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list gredoraDatastream">

      <g:if test="${gds?.pid}">
        <li class="fieldcontain">
          <span id="pid-label" class="property-label"><g:message code="gds.pid.label" default="Pid" /></span>

          <span class="property-value" aria-labelledby="pid-label"><g:link controller="GredoraObject" action="show" id="${gds?.pid}">${gds?.pid}</g:link></span>

        </li>
      </g:if>

      <g:if test="${gds?.dsId}">
        <li class="fieldcontain">
          <span id="dsId-label" class="property-label"><g:message code="gds.dsId.label" default="Ds Id" /></span>

          <span class="property-value" aria-labelledby="dsId-label"><g:fieldValue bean="${gds}" field="dsId"/></span>

        </li>
      </g:if>

      <g:if test="${gds?.dsLabel}">
        <li class="fieldcontain">
          <span id="dsLabel-label" class="property-label"><g:message code="gds.dsLabel.label" default="Ds Label" /></span>

          <span class="property-value" aria-labelledby="dsLabel-label"><g:fieldValue bean="${gds}" field="dsLabel"/></span>

        </li>
      </g:if>

      <g:if test="${gds?.dsMIMEType}">
        <li class="fieldcontain">
          <span id="dsMIMEType-label" class="property-label"><g:message code="gds.dsMIMEType.label" default="Ds MIME Type" /></span>

          <span class="property-value" aria-labelledby="dsMIMEType-label"><g:fieldValue bean="${gds}" field="dsMIMEType"/></span>

        </li>
      </g:if>

      <g:if test="${gds?.dsState}">
        <li class="fieldcontain">
          <span id="dsState-label" class="property-label"><g:message code="gds.dsState.label" default="Ds State" /></span>

          <span class="property-value" aria-labelledby="dsState-label"><g:fieldValue bean="${gds}" field="dsState"/></span>

        </li>
      </g:if>
      
      <g:if test="${gds?.dsControlGroup}">
        <li class="fieldcontain">
          <span id="dsState-label" class="property-label"><g:message code="gds.dsControlGroup.label" default="Ds Control Group" /></span>

          <span class="property-value" aria-labelledby="dsControlGroup-label"><g:fieldValue bean="${gds}" field="dsControlGroup"/></span>

        </li>
      </g:if>
      
      <g:if test="${gds?.dsAccessUrl}">
        <li class="fieldcontain">
          <span id="dsAccessUrl-label" class="property-label"><g:message code="gds.dsAccessUrl.label" default="Ds Access Url" /></span>

          <span class="property-value" aria-labelledby="dsAccessUrl-label"><g:link url="${gds?.dsAccessUrl}">${gds?.dsAccessUrl}</g:link></span>

        </li>
      </g:if>
      
      <g:if test="${gds?.dsContent}">
        <li class="fieldcontain">
          <span id="dsContent-label" class="property-label"><g:message code="gds.dsContent.label" default="Ds content" /></span>
          
          <span class="property-value" aria-labelledby="dsContent-label">
            <pre class="prettyprint lang-xml">
              <g:fieldValue bean="${gds}" field="dsContent"/>
            </pre>
          </span>
        </li>
      </g:if>

      <g:if test="${gds?.size}">
        <li class="fieldcontain">
          <span id="size-label" class="property-label"><g:message code="gds.size.label" default="Size" /></span>

          <span class="property-value" aria-labelledby="size-label"><g:fieldValue bean="${gds}" field="size"/></span>

        </li>
      </g:if>

            <g:if test="${gds?.dsChecksum}">
        <li class="fieldcontain">
          <span id="dsChecksum-label" class="property-label"><g:message code="gds.dsChecksum.label" default="Ds Checksum" /></span>

          <span class="property-value" aria-labelledby="dsChecksum-label"><g:fieldValue bean="${gds}" field="dsChecksum"/></span>

        </li>
      </g:if>

      <g:if test="${gds?.dsChecksumType}">
        <li class="fieldcontain">
          <span id="dsChecksumType-label" class="property-label"><g:message code="gds.dsChecksumType.label" default="Ds Checksum Type" /></span>

          <span class="property-value" aria-labelledby="dsChecksumType-label"><g:fieldValue bean="${gds}" field="dsChecksumType"/></span>

        </li>
      </g:if>
      
    </ol>
    <g:form>
      <fieldset class="buttons">
        <g:hiddenField name="id" value="${gds?.pid}" />
        <g:hiddenField name="dsId" value="${gds?.dsId}" />
        <g:link class="edit" action="edit" id="${gds?.pid}" params="${[dsId: gds?.dsId]}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
        <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
      </fieldset>
    </g:form>
  </div>
</body>
</html>
