package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioVehiculoImpl implements ServicioVehiculo {
    private RepositorioVehiculo repositorioVehiculo;

    public ServicioVehiculoImpl(RepositorioVehiculo repositorioVehiculo) {
        this.repositorioVehiculo = repositorioVehiculo;
    }
    @Override
    public void agregarVehiculo(Vehiculo vehiculo) {
        repositorioVehiculo.guardar(vehiculo);
    }

    @Override
    public List<Vehiculo> verVehiculos() {
        return repositorioVehiculo.getVehiculos();

    }

    @Override
    public List<Vehiculo> getPorMarca(Marca marca) {
       return repositorioVehiculo.getPorMarca(marca);
    }
}
