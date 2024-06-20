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

    //TRAE DEL REPO TODOS LOS SINTOMAS QUE SE ASOCIAN AL ITEM
    @Override
    public List<Sintoma> problemaEnTablero(ItemTablero itemTablero) {
        return repositorioSintoma.obtenerPorItemTablero(itemTablero);
    }

    @Override
    public void guardarSintoma(Sintoma sintoma) {
        repositorioSintoma.guardar(sintoma);
    }

    //TRAE TODOS LOS SINTOMAS DE LA TABLA
    @Override
    public List<Sintoma> findAll() {
        return repositorioSintoma.getAll();
    }

    @Override
    public Sintoma findById(Integer idSintoma) {
        return repositorioSintoma.findById(idSintoma);
    }

    //TRAE DEL REPO TODOS LOS SINTOMAS QUE SE ASOCIAN AL ITEM
    @Override
    public List<Sintoma> problemasEnTablero(List<ItemTablero> items) {
        return repositorioSintoma.obtenerPorItemsTablero(items);
    }



}

