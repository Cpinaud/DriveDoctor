package com.drivedoctor.dominio;
import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface RepositorioVehiculo extends RepositoryCRUD<Vehiculo> {

    List<Vehiculo> getVehiculos();

    List<Vehiculo> getPorMarca(Marca marca);

    Vehiculo getByPatente(String patente);

    /*Vehiculo getById(Integer idVehiculo);*/

    void modificar(Vehiculo vehiculo);

    void eliminar(Vehiculo vehiculo);

    List<Historial> obtenerHistorial(Vehiculo vehiculo);
}
