package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Marca;
import com.drivedoctor.dominio.RepositorioMarca;
import com.drivedoctor.dominio.RepositorioUsuario;
import com.drivedoctor.dominio.ServicioMarca;
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
    public Marca obtenerMarcaPorId(Integer idMarca) {
        return this.repositorioMarca.getById(idMarca);
    }
}
