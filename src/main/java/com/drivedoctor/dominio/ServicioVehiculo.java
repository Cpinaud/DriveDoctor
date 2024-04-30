package com.drivedoctor.dominio;

import java.util.List;

public interface ServicioVehiculo {
    List<Vehiculo> verVehiculos();
    List<Vehiculo> verVehiculosPorMarca(String marca);
    List<Vehiculo> verVehiculoPorMarcaYModelo(String marca,String modelo);
}
