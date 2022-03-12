<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp" />
        <h3>${titulo}</h3>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <div class="row my-2">
                <label for="username" class="form-label">Usuario</label>
                <div>
                    <input type="text" name="username" id="username" class="form-control">
                </div>
            </div>
            <div class="row my-2">
                <label for="password" class="form-label">Contraseña</label>
                <div>
                    <input type="password" name="password" id="password" class="form-control">
                </div>
            </div>
            <div class="row my-2">
                <div>
                    <input type="submit" value="Acceso" class="btn btn-primary">
                </div>
            </div>
        </form>
        <a class="btn btn-secondary" class="nav-link" href="index.jsp">Regresar</a>
<jsp:include page="layout/footer.jsp" />
