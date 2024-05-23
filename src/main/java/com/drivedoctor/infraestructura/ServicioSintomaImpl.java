package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.RepositorioSintoma;
import com.drivedoctor.dominio.ServicioSintoma;
import com.drivedoctor.dominio.Sintoma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("servicioSintoma")
@Transactional
public class ServicioSintomaImpl implements ServicioSintoma {

    private RepositorioSintoma repositorioSintoma;
    @Autowired
    public ServicioSintomaImpl(RepositorioSintoma repositorioSintoma){
        this.repositorioSintoma = repositorioSintoma;
    }


    @Override
    public List<Sintoma> problemaEnTablero(ItemTablero itemTablero) {
        return repositorioSintoma.obtenerPorItemTablero(itemTablero);
    }

    @Override
    public void guardarSintoma(Sintoma sintoma) {
        repositorioSintoma.guardar(sintoma);
    }

    @Override
    public List<Sintoma> findAll() {
        return repositorioSintoma.getAll();
    }

    @Override
    public List<Sintoma> problemasEnTableros(List<ItemTablero> items) {
        return List.of();
    }


}

