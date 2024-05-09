package com.drivedoctor.dominio;
import java.util.List;
public interface ServicioVehiculo {

    void agregarVehiculo(Vehiculo vehiculos);

    List<Vehiculo> verVehiculos();

    List<Vehiculo> getPorMarca(Marca marca);
}
