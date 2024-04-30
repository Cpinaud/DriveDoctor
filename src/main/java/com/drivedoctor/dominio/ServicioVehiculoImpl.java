package com.drivedoctor.dominio;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicioVehiculoImpl implements ServicioVehiculo{


    private List<Vehiculo> vehiculos;

    public ServicioVehiculoImpl(){
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Vehiculo("Renault", "Clio"));
        vehiculos.add(new Vehiculo("Ford", "Fiesta"));
        vehiculos.add(new Vehiculo("Renault", "Sandero"));
        this.vehiculos = vehiculos;
    }
    @Override
    public List<Vehiculo> verVehiculos() {
        return this.vehiculos;
    }

    @Override
    public List<Vehiculo> verVehiculosPorMarca(String marca) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (Vehiculo vehiculo : this.vehiculos) {
            if(vehiculo.getMarca().equalsIgnoreCase(marca)){
                vehiculos.add(vehiculo);
            }
        }
        return vehiculos;
    }

    @Override
    public List<Vehiculo> verVehiculoPorMarcaYModelo(String marca, String modelo) {
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (Vehiculo vehiculo : this.vehiculos) {
            if(vehiculo.getMarca().equalsIgnoreCase(marca) && vehiculo.getModelo().equalsIgnoreCase(modelo)){
                vehiculos.add(vehiculo);
            }
        }
        return vehiculos;
    }


}
