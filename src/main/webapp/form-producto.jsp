<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.time.format.*"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout/header.jsp" />
        <h3>${titulo}</h3>
        <form action="${pageContext.request.contextPath}/productos/form" method="post">
            <div class="row mb-2">
                <label for="nombre" class="col-form-label col-sm-2">Nombre</label>
                <div class="col-sm-4">
                    <input type="text" name="nombre" id="nombre" class="form-control" maxlength="50" value="${producto.nombre}">
                </div>
                <c:if test="${errores != null && errores.containsKey('nombre')}">
                    <div class="alert alert-danger" role="alert">${errores.nombre}</div>
                </c:if>
            </div>

            <div class="row mb-2">
                <label for="precio" class="col-form-label col-sm-2">Precio</label>
                <div class="col-sm-4">
                    <input type="number" name="precio" id="precio" class="form-control" value="${producto.precio > 0 ? producto.precio: ""}">
                </div>
                <c:if test="${errores != null && not empty errores.precio}">
                    <div class="alert alert-danger" role="alert">${errores.precio}</div>
                </c:if>
            </div>

            <div class="row mb-2">
                <label for="sku" class="col-form-label col-sm-2">Sku</label>
                <div class="col-sm-4">
                    <input type="text" name="sku" id="sku" class="form-control" maxlength="10" value="${producto.sku}">
                </div>
                <c:if test="${errores != null && errores.containsKey('sku')}">
                    <div class="alert alert-danger" role="alert">${errores.sku}</div>
                </c:if>
            </div>

            <div class="row mb-2">
                <label for="fecha_registro" class="col-form-label col-sm-2">Fecha Registro</label>
                <div class="col-sm-4">
                    <input type="date" name="fecha_registro" id="fecha_registro" class="form-control" value="${producto.fechaRegistro != null? producto.fechaRegistro.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")): ""}">
                </div>
                <c:if test="${errores != null && errores.containsKey('fecha_registro')}">
                    <div class="alert alert-danger" role="alert">${errores.fecha_registro}</div>
                </c:if>
            </div>

            <div class="row mb-2">
                <label for="categoria" class="col-form-label col-sm-2">Categoria</label>
                <div class="col-sm-4">
                    <select name="categoria" id="categoria" class="form-select">
                        <option value="">-- Seleccionar --</option>
                        <c:forEach items="${categorias}" var="categoria">
                        <option value="${categoria.id}" ${categoria.id.equals(producto.categoria.id)?"selected":""}>${categoria.nombre}</option>
                        </c:forEach>
                    </select>
                </div>
                <c:if test="${errores != null && errores.containsKey('categoria')}">
                    <div class="alert alert-danger" role="alert">${errores.categoria}</div>
                </c:if>
            </div>

            <div class="row mb-2">
                <div>
                    <input type="submit" value="${producto.id!=null && producto.id>0?"Guardar":"Crear"}" class="btn btn-primary">
                </div>
            </div>

            <input name="id" type="hidden" value="${producto.id}">
        </form>
        <a class="btn btn-sm btn-secondary" href="<%=request.getContextPath()%>">Regresar</a>
<jsp:include page="layout/footer.jsp" />