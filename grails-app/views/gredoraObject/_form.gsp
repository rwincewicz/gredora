<%@ page import="gredora.GredoraObject" %>


<div class="fieldcontain ${hasErrors(bean: gredoraObject, field: 'id', 'error')} ">
  <label for="id">
    <g:message code="gredoraObject.id.label" default="Pid" />

  </label>
  <g:textField name="pid" value="${gredoraObject?.id}"/>
</div>
  <div class="fieldcontain ${hasErrors(bean: gredoraObject, field: 'title', 'error')} ">
    <label for="title">
      <g:message code="gredoraObject.title.label" default="Title" />

    </label>
    <g:textField name="title" value="${gredoraObject?.title}"/>
  </div>

  <div class="fieldcontain ${hasErrors(bean: gredoraObject, field: 'type', 'error')} ">
    <label for="type">
      <g:message code="gredoraObject.type.label" default="Type" />

    </label>
    <g:select name="type" from="${['A', 'I', 'D']}" value="${gredoraObject?.type}" />
  </div>