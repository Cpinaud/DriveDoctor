
function initMap() {
    const argCoords = { lat: -33.61, lng: -63.61 };
    const map = new google.maps.Map(document.getElementById('map'), {
        center: argCoords,
        zoom: 12
    });

    let userCoords;

    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition((position) => {
            userCoords = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };
            map.setCenter(userCoords);

            const userMarker = new google.maps.Marker({
                position: userCoords,
                map: map,
                title: 'Mi ubicación'
            });

            const infowindowUser = new google.maps.InfoWindow({
                content: '<h5>Mi ubicación actual</h5>'
            });

            userMarker.addListener('click', function() {
                infowindowUser.open(map, userMarker);
            });

            cargarYFiltrarTalleres(userCoords);

        }, (error) => {
            alert("Tu navegador está bien, pero ocurrió un error al obtener la ubicación: " + error.message);
        });
    } else {
        alert("No se pudo obtener la ubicación del usuario.");
    }

    function cargarYFiltrarTalleres(userCoords) {
        $.ajax({
            url: '/drivedoctor/api/talleres',
            method: 'GET',
            success: function(talleres) {
                const maxDistancia = 50 * 1000; // 50 km

                const filtrarTalleres = talleres.filter(function(taller) {
                    const tallerCoords = { lat: taller.latitud, lng: taller.longitud };

                    const distancia = google.maps.geometry.spherical.computeDistanceBetween(
                        new google.maps.LatLng(userCoords.lat, userCoords.lng),
                        new google.maps.LatLng(tallerCoords.lat, tallerCoords.lng)
                    );

                    const distanciaEnKm = distancia / 1000;

                    return distanciaEnKm < maxDistancia;
                });

                // Mostrar los talleres filtrados en el mapa
                filtrarTalleres.forEach(function(taller) {
                    const marker = new google.maps.Marker({
                        position: {lat: taller.latitud, lng: taller.longitud},
                        map: map,
                        title: taller.nombre,


                    });

                    const infowindowTaller = new google.maps.InfoWindow();



                  const numeroEstrellas = taller.estrellas;
                    let mostrarEstrellas = '';

                    for (let i = 0; i < numeroEstrellas; i++) {
                        mostrarEstrellas += '<i class="bi bi-star-fill"></i>';
                    }

                    if (numeroEstrellas % 1 !== 0) {
                        mostrarEstrellas += '<i class="bi bi-star-half"></i>';
                    }


                    const contentString = '<h4>' + taller.nombre + '</h4>' + '<h5>' + mostrarEstrellas + '</h5>';

                    infowindowTaller.setContent(contentString);


                    marker.addListener('click', function() {
                        infowindowTaller.open(map, marker);
                            calcularYMostrarDistancia(userCoords.lat, userCoords.lng, taller.latitud, taller.longitud);

                    });
                });
            },
            error: function(error) {
                console.error('Error fetching talleres:', error);
            }
        });
    }

    function calcularYMostrarDistancia(userLat, userLng, tallerLat, tallerLng) {
        const directionsService = new google.maps.DirectionsService();
        const directionsRenderer = new google.maps.DirectionsRenderer({
            map: map,
            suppressMarkers: true,
            polylineOptions: {
                strokeColor: "blue"
            }
        });


        const request = {
            origin: new google.maps.LatLng(userLat, userLng),
            destination: new google.maps.LatLng(tallerLat, tallerLng),
            travelMode: google.maps.TravelMode.DRIVING
        };

        function borrarRutaRenderer(renderer) {
            renderer.setDirections({ routes: [] });
        }


        directionsService.route(request, function(response, status) {
            if (status === google.maps.DirectionsStatus.OK) {
                borrarRutaRenderer(directionsRenderer);

                directionsRenderer.setDirections(response);


                const route = response.routes[0];
                const leg = route.legs[0];
                const distance = leg.distance.text;
                const duration = leg.duration.text;


                const rutaInfoElement = document.getElementById('ruta-info');
                rutaInfoElement.innerHTML = `<h5>Distancia: ${distance}</h5> <br> <h5>Tiempo estimado: ${duration}</h5>`;

            } else {
                console.error('Error al obtener direcciones:', status);
            }
        });
    }
}

