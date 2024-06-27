package com.drivedoctor.dominio;
import java.util.List;
public interface RepositorioVehiculo {

    void guardar(Vehiculo vehiculo);

    List<Vehiculo> getVehiculos();

    List<Vehiculo> getPorMarca(Marca marca);

    Vehiculo getByPatente(String patente);

    Vehiculo getById(Integer idVehiculo);

    void modificar(Vehiculo vehiculo);

    void eliminar(Vehiculo vehiculo);

    List<Historial> obtenerHistorial(Vehiculo vehiculo);
}
