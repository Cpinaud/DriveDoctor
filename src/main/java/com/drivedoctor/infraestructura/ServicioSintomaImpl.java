package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
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
    public void guardar(Sintoma sintoma) {

        repositorioSintoma.guardar(sintoma);
    }

    //TRAE TODOS LOS SINTOMAS DE LA TABLA
    @Override
    public List<Sintoma> findAll() {
        return repositorioSintoma.getAll();
    }

    @Override
    public Sintoma findById(Integer idSintoma) throws ElementoNoEncontrado {
        Sintoma sintoma= repositorioSintoma.findById(idSintoma);
        if(sintoma == null){
            throw new ElementoNoEncontrado();
        }
        return sintoma;
    }

    //TRAE DEL REPO TODOS LOS SINTOMAS QUE SE ASOCIAN AL ITEM
    @Override
    public List<Sintoma> problemasEnTablero(List<ItemTablero> items) {
        return repositorioSintoma.obtenerPorItemsTablero(items);
    }

    @Override
    public List<Sintoma> obtenerSintomasPorSuId(List<Integer> sintomas) {
        return repositorioSintoma.obtenerLosSintomasPorSusIds(sintomas);
    }


}

