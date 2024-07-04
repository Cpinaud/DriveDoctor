package com.drivedoctor.dominio;
import com.drivedoctor.dominio.excepcion.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface ServicioVehiculo extends Busqueda<Vehiculo,Integer> {

    void agregarVehiculo(Integer usuarioId,Vehiculo vehiculo) throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente, ElementoNoEncontrado;

    List<Vehiculo> verVehiculos(HttpServletRequest request) throws UserSinPermiso;

    List<Vehiculo> getPorMarca(Marca marca);

    void modificarVehiculo(Integer usuarioId, Integer idVehiculo, String patente, Integer anio) throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente, VehiculoInexistente, vehiculoSinCambios, ElementoNoEncontrado;

    Vehiculo buscarByPatente(String patente) throws VehiculoInexistente;

    void validarVehiculoUser(Integer idVehiculo, Integer idVehiculoPorPatente, Integer userId) throws VehiculoInvalido, ElementoNoEncontrado;

    void eliminarVehiculo(Vehiculo vehiculo);

    void validarVehiculoUser(Integer userId, Integer idVehiculo) throws VehiculoInvalido, ElementoNoEncontrado;

    List<Historial> getHistoriales(Integer idVehiculo) throws ElementoNoEncontrado;
}
