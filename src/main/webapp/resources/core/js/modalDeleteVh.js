    document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('staticBackdrop');
    modal.addEventListener('show.bs.modal', function (event) {
    const button = event.relatedTarget; // Botón que activó el modal
    const id = button.getAttribute('data-id');
    const marca = button.getAttribute('data-marca');
    const modelo = button.getAttribute('data-modelo');
    const patente = button.getAttribute('data-patente');

    // Actualiza el contenido del modal
    modal.querySelector('.modal-title').textContent = `Eliminando unidad`;
        modal.querySelector('.modal-content-p').textContent = `Confirma que desea eliminar el vehiculo ${marca} ${modelo} - ${patente}?`;


     modal.querySelector('#vehiculoId').setAttribute('value', id);
        modal.querySelector('#patente').setAttribute('value', patente);
});
});
