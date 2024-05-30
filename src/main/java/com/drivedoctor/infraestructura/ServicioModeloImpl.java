package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioModeloImpl implements ServicioModelo {

    private RepositorioModelo repositorioModelo;



    public ServicioModeloImpl(RepositorioModelo repositorioModelo) {
        this.repositorioModelo = repositorioModelo;
    }


    @Override
    public List<Modelo> obtenerModeloPorMarca(Marca marca) {
        return this.repositorioModelo.getByMarca(marca);
    }


}
