package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.RepositorioSintoma;
import com.drivedoctor.dominio.ServicioSintoma;
import com.drivedoctor.dominio.Sintoma;
import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;
import com.drivedoctor.dominio.excepcion.ItemTableroInvalido;
import com.drivedoctor.dominio.excepcion.SintomaExistente;
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
    public void guardarSintoma(Sintoma sintoma) throws SintomaExistente {
        Sintoma sintomaExistente = repositorioSintoma.findByName(sintoma.getNombre());
        if(sintoma.getDiagnostico() == null) {
            throw new DiagnosticoNotFoundException();
        }
        if(sintoma.getItemTablero() == null) {
            throw new ItemTableroInvalido();
        }
        if(sintomaExistente != null) {
            throw new SintomaExistente();
        }


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

    @Override
    public List<Sintoma> obtenerSintomasPorSuId(List<Integer> sintomas) {
        return repositorioSintoma.obtenerLosSintomasPorSusIds(sintomas);
    }


}

