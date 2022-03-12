    </div>

    <script>
        $("#btn").click(function () {
            swal({
                title: "¿Estás seguro?",
                text: "Una vez eliminado, no se puede restaurar",
                icon: "warning",
                buttons: true,
                dangerMode: true,
            })
                .then((willDelete) => {
                    if (willDelete) {
                        swal("Eliminado correctamente", {
                            icon: "success",
                        });
                        return true;
                    } else {
                        swal("Se canceló acción");
                        return false;
                    }
                });
        })
    </script>
</body>
</html>
