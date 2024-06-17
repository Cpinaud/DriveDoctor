package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.ItemTablero;

import com.drivedoctor.dominio.ServicioItemTablero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioItemTableroImpl implements ServicioItemTablero {

    private RepositorioItemTablero repositorioItemTablero;

    @Autowired
    public ServicioItemTableroImpl(RepositorioItemTablero repositorioItemTablero){
        this.repositorioItemTablero = repositorioItemTablero;
    }

    @Override
    public List<ItemTablero> obtenerTodosLosItems() {
        return repositorioItemTablero.findAll();
    }

    @Override
    public ItemTablero findById(Integer idItemTablero) {
        return repositorioItemTablero.findById(idItemTablero);
    }
}
