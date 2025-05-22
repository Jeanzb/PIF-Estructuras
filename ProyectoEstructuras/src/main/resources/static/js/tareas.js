
function completarDesdeData(btn) {
    const nombre = btn.getAttribute('data-nombre');
    fetch('/tareas/completar', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ nombreTarea: nombre })
    }).then(() => window.location.reload());
}

function confirmarEliminar(btn) {
    const nombre = btn.getAttribute('data-nombre');
    if (confirm('¿Estás seguro de que deseas eliminar la tarea: ' + nombre + '?')) {
        fetch('/tareas/eliminar', {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: new URLSearchParams({ nombreTarea: nombre })
        }).then(() => window.location.reload());
    }
}
