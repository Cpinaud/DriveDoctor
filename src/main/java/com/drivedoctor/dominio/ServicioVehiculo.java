package com.drivedoctor.dominio;
import com.drivedoctor.dominio.excepcion.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface ServicioVehiculo {

    void agregarVehiculo(Integer usuarioId,Vehiculo vehiculo) throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente;

    List<Vehiculo> verVehiculos(HttpServletRequest request) throws UserSinPermiso;

    List<Vehiculo> getPorMarca(Marca marca);


    Vehiculo buscarById(Integer idVehiculo);

    void modificarVehiculo(Integer usuarioId, Integer idVehiculo, String patente, Integer anio) throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente, VehiculoInexistente, vehiculoSinCambios;

    Vehiculo buscarByPatente(String patente) throws VehiculoInexistente;

    void validarVehiculoUser(Integer idVehiculo, Integer idVehiculoPorPatente, Integer userId) throws VehiculoInvalido;

    void eliminarVehiculo(Vehiculo vehiculo);

    void validarVehiculoUser(Integer userId, Integer idVehiculo) throws VehiculoInvalido;


    List<Historial> getHistoriales(Integer idVehiculo);
}
