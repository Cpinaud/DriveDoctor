package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.UsuarioExistente;

import java.util.List;

public interface ServicioUsuario {
    List<Vehiculo> verMisVehiculos();
    List<Vehiculo> verVehiculosPorMarca(String marca);
    List<Vehiculo> verVehiculoPorMarcaYModelo(String marca,String modelo);

    void agregarVehiculo(Vehiculo vehiculo);
}
