package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import com.drivedoctor.dominio.excepcion.UserSinVhByMarca;
import com.drivedoctor.dominio.excepcion.UsuarioSinVehiculos;

import java.util.List;

public interface ServicioUsuario {
    List<Vehiculo> getMisVehiculos(Usuario usuario) throws UsuarioSinVehiculos;
    List<Vehiculo> getVhPorMarca(Usuario usuario, Marca marca) throws UserSinVhByMarca;

    Usuario findById (Integer id) throws ElementoNoEncontrado;

}
