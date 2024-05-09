package com.drivedoctor.dominio;
import java.util.List;
public interface RepositorioVehiculo {

    void guardar(Vehiculo vehiculo);

    List<Vehiculo> getVehiculos();

    List<Vehiculo> getPorMarca(Marca marca);
}
