package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
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

    @Override
    public Modelo findById(Integer modeloId) throws ElementoNoEncontrado {
        Modelo modelo = this.repositorioModelo.findById(modeloId);
        if (modelo == null) {
            throw new ElementoNoEncontrado();
        }

        return modelo;
    }

    @Override
    public void guardarModelo(Modelo modelo) {
        if (modelo == null) {
            throw new IllegalArgumentException("El modelo no puede ser nulo");
        }
        repositorioModelo.guardar(modelo);
    }


}
