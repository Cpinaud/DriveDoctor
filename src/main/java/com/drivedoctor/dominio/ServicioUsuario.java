package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.UsuarioSinVehiculos;

import java.util.List;

public interface ServicioUsuario {
    List<Vehiculo> getMisVehiculos(Usuario usuario) throws UsuarioSinVehiculos;

    Usuario buscar(Integer usuarioId);


   /* void agregarVehiculo(Usuario usuario, Vehiculo vehiculo);
    List<Vehiculo> verVehiculosPorMarca(String marca);
    List<Vehiculo> verVehiculoPorMarcaYModelo(String marca,String modelo);
     List<Vehiculo> verMisVehiculos(Usuario usuario);

    void agregarVehiculo(Vehiculo vehiculo);*/
}
