package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioMarcaImpl implements ServicioMarca {

    private RepositorioMarca repositorioMarca;



    public ServicioMarcaImpl(RepositorioMarca repositorioMarca) {
        this.repositorioMarca = repositorioMarca;
    }

    @Override
    public List<Marca> obtenerMarcasAll() {
        return this.repositorioMarca.getAll();
    }

    @Override
    public Marca findById(Integer idMarca) throws ElementoNoEncontrado {
        Marca marcaBuscada = this.repositorioMarca.findById(idMarca);
        if (marcaBuscada == null) {
            throw new ElementoNoEncontrado();
        }
        return marcaBuscada;
    }


}
