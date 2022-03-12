<jsp:include page="layout/header.jsp" />
<div class="card">
  <div class="card-body">
    <h5 class="card-title">Bienvenido</h5>
    <h6 class="card-subtitle mb-2 text-muted">Hola ${sessionScope.username}</h6>
    <p class="card-text">Has iniciado sesi&oacute;n con &eacute;xito.</p>
  </div>
</div>
<jsp:include page="layout/footer.jsp" />
