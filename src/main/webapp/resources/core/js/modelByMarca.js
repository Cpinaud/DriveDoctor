
document.addEventListener("DOMContentLoaded", function() {
    cargarModelos();
});
function cargarModelos() {
    var marcaId = document.getElementById("marca").value;
    var modelosSelect = document.getElementById("modelo");

    // Limpiar el select de modelos
    modelosSelect.innerHTML = '';

    // Crear una nueva instancia de XMLHttpRequest
    var xhr = new XMLHttpRequest();


    // Inicializar una solicitud GET asíncrona al servidor para obtener los modelos
    xhr.open('GET', 'http://localhost:8080/spring/obtenerModelos?idMarca=' + marcaId, true);


    // Configurar el manejador de eventos onreadystatechange
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {  // La solicitud está completa
            if (xhr.status === 200) {  // El servidor respondió con un estado exitoso
                var modelos = JSON.parse(xhr.responseText);
                // Agregar los modelos al select
                modelos.forEach(function(modelo) {
                    var option = document.createElement('option');
                    option.value = modelo.id;
                    option.text = modelo.nombre;
                    modelosSelect.appendChild(option);
                });
            } else {
                console.log('Error al cargar modelos: ' + xhr.statusText);
            }
        }
    };

    // Enviar la solicitud
    xhr.send();
}