function cargarSintomas() {
    var itemSeleccionado = document.getElementById("item").value;
    var url = "/mostrarSintomasDependiendoItem?item=" + itemSeleccionado;

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Error al obtener los síntomas');
            }
            return response.json();
        })
        .then(data => {
            actualizarSintomas(data);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

function actualizarSintomas(sintomas) {
    var sintomasContainer = document.getElementById("sintomas-container");
    sintomasContainer.innerHTML = "";
    sintomas.forEach(function(sintoma) {
        var checkbox = document.createElement("input");
        checkbox.type = "checkbox";
        checkbox.name = "sintomas";
        checkbox.value = sintoma.idSintoma;

        var label = document.createElement("label");
        label.appendChild(document.createTextNode(sintoma.nombre));

        var br = document.createElement("br");

        sintomasContainer.appendChild(checkbox);
        sintomasContainer.appendChild(label);
        sintomasContainer.appendChild(br);
    });
}
