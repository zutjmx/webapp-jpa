<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout/header.jsp" />
        <h3>${titulo}</h3>
        <c:choose>
            <c:when test="${carro.items.isEmpty()}">
                <div class="alert alert-info" role="alert">
                    No hay productos en el carro
                </div>
            </c:when>
            <c:otherwise>
                <form name="formcarro" action="${pageContext.request.contextPath}/carro/actualizar" method="post">
                    <table class="table table-striped table-hover">
                      <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Cantidad</th>
                        <th>Total</th>
                        <th>Borrar</th>
                      </tr>
                      <c:forEach items="${carro.items}" var="item">
                          <tr>
                            <td>${item.producto.id}</td>
                            <td>${item.producto.nombre}</td>
                            <td>${item.producto.precio}</td>
                            <td><input type="text" size="4" name="cant_${item.producto.id}" value="${item.cantidad}" /></td>
                            <td>${item.importe}</td>
                            <td><input type="checkbox" value="${item.producto.id}" name="deleteProductos" /></td>
                          </tr>
                      </c:forEach>
                      <tr>
                        <td colspan="5" style="text-align: right">Total</td>
                        <td>${carro.total}</td>
                      </tr>
                    </table>
                    <a class="btn btn-primary" href="javascript:document.formcarro.submit();">Actualizar</a>
                </form>
            </c:otherwise>
        </c:choose>
        <div class="my-2">
            <a class="btn btn-sm btn-info" href="${pageContext.request.contextPath}/productos">Seguir comprando</a>
            <a class="btn btn-sm btn-secondary" href="${pageContext.request.contextPath}/index.jsp">Regresar</a>
        </div>
<jsp:include page="layout/footer.jsp" />