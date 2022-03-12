<jsp:include page="layout/header.jsp" />
        <h3>${titulo}</h3>
        <ul class="list-group">
            <li class="list-group-item active">
                Men&uacute; de Opciones
            </li>
            <li class="list-group-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/productos">Listado de productos</a>
            </li>
            <li class="list-group-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/login.html">Acceso</a>
            </li>
            <li class="list-group-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/logout">Cerrar sesi&oacute;n</a>
            </li>
            <li class="list-group-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/carro/ver">Ver Carro</a>
            </li>
        </ul>

<jsp:include page="layout/footer.jsp" />
