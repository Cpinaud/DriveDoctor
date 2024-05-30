package com.drivedoctor.dominio;
import com.drivedoctor.dominio.excepcion.UserSinPermiso;
import com.drivedoctor.dominio.excepcion.UsuarioInexistente;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface ServicioVehiculo {

    void agregarVehiculo(Integer usuarioId,Vehiculo vehiculo) throws UsuarioInexistente;

    List<Vehiculo> verVehiculos(HttpServletRequest request) throws UserSinPermiso;

    List<Vehiculo> getPorMarca(Marca marca);


}
