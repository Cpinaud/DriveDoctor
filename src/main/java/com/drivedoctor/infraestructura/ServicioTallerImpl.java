package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.RepositorioTaller;
import com.drivedoctor.dominio.ServicioTaller;
import com.drivedoctor.dominio.Taller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioTallerImpl implements ServicioTaller {

    public RepositorioTaller repositorioTaller;

    @Autowired
    public ServicioTallerImpl(RepositorioTaller repositorioTaller){
        this.repositorioTaller = repositorioTaller;
    }

    @Override
    public List<Taller> findAll() {
        return repositorioTaller.findAll();
    }

    @Override
    public List<Taller> obtenerTalleresPorLocalidad(String localidad) {
        return List.of();
    }
}
