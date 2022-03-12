<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout/header.jsp" />
        <h3>${titulo}</h3>
        <c:if test = "${username.present}">
            <div class="alert alert-info">Hola ${username.get()}, bienvenido</div>
            <a class="btn btn-info my-2" href="${pageContext.request.contextPath}/productos/form">[+] Nuevo Producto</a>
        </c:if>
        <button class="btn btn-danger my-2" id="btn" class="btn btn-primary">Prueba SweetAlert</button>
        <table class="table table-success table-hover table-striped">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Categoría</th>
                <c:if test = "${username.present}">
                <th>Precio</th>
                <th>Agregar</th>
                <th>Editar</th>
                <th>Eliminar</th>
                </c:if>
            </tr>
            <c:forEach items="${productos}" var = "p">
            <tr>
                <td>${p.id}</td>
                <td>${p.nombre}</td>
                <td>${p.categoria.nombre}</td>
                <c:if test = "${username.present}">
                <td>${p.precio}</td>
                <td><a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/carro/agregar?id=${p.id}">Agregar al Carro</a></td>
                <td><a class="btn btn-sm btn-success" href="${pageContext.request.contextPath}/productos/form?id=${p.id}">Editar Producto</a></td>
                <td><a class="btn btn-sm btn-danger" onClick="return confirm('Quiere eliminar?');" class="nav-link" href="${pageContext.request.contextPath}/productos/eliminar?id=${p.id}">Eliminar Producto</a></td>
                </c:if>
            </tr>
            </c:forEach>
        </table>
        <div class="my-2">
            <p>${applicationScope.mensaje}</p>
            <p>${requestScope.mensaje}</p>
            <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}">Regresar</a>
        </div>
<jsp:include page="layout/footer.jsp" />