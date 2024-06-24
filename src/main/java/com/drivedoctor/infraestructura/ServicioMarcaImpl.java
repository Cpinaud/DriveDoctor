package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.MarcaNoEncontrada;
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
    public Marca obtenerMarcaPorId(Integer idMarca) throws MarcaNoEncontrada {
        Marca marcaBuscada = this.repositorioMarca.getById(idMarca);
        if (marcaBuscada == null) {
            throw new MarcaNoEncontrada();
        }
        return marcaBuscada;
    }


}
