
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
                title: 'Mi ubicacion'
            });

            const infowindowUser = new google.maps.InfoWindow({
                content: '<h5>Mi ubicacion actual</h5>'
            });

            userMarker.addListener('click', function() {
                infowindowUser.open(map, userMarker);
            });

            cargarYFiltrarTalleres(userCoords);

        }, () => {
            alert("Tu navegador está bien, pero ocurrió un error al obtener la ubicación.");
        });
    } else {
        alert("No se pudo obtener la ubicación del usuario.");
    }

    function cargarYFiltrarTalleres(userCoords) {
        $.ajax({
            url: '/spring/api/talleres',
            method: 'GET',
            success: function(talleres) {
                const maxDistancia = 30 * 1000;

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
                        position: { lat: taller.latitud, lng: taller.longitud },
                        map: map,
                        title: taller.nombre
                    });

                    const infowindowTaller = new google.maps.InfoWindow({
                        content: '<h5>' + taller.nombre + '</h5>'

                    });

                    marker.addListener('click', function() {
                        infowindowTaller.open(map, marker);

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

        const request = {
            origin: new google.maps.LatLng(userLat, userLng),
            destination: new google.maps.LatLng(tallerLat, tallerLng),
            travelMode: google.maps.TravelMode.DRIVING
        };

        directionsService.route(request, function(response, status) {
            if (status === google.maps.DirectionsStatus.OK) {
                const route = response.routes[0];
                const distance = route.legs[0].distance.text;
                const duration = route.legs[0].duration.text;

                const infowindowDirections = new google.maps.InfoWindow({
                    content: '<h5>Distancia:</h5>' + distance + '<br><h5>Tiempo estimado:</h5>' + duration
                });

                infowindowDirections.open(map);
            } else {
                console.error('Error al obtener direcciones:', status);
            }
        });
    }
}

