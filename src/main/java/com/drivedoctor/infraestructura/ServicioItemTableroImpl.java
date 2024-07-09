package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.ItemTablero;

import com.drivedoctor.dominio.ServicioItemTablero;
import com.drivedoctor.dominio.excepcion.ItemNoEncontrado;
import com.drivedoctor.dominio.excepcion.ItemsNoEncontrados;
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
    public List<ItemTablero> obtenerTodosLosItems()  {
        return repositorioItemTablero.findAll();

    }

    @Override
    public ItemTablero findById(Integer idItemTablero) throws ItemNoEncontrado {
        ItemTablero itemTablero = repositorioItemTablero.findById(idItemTablero);
        if (itemTablero == null) {
            throw new ItemNoEncontrado();
        }
        return itemTablero;
    }

    @Override
    public void guardarItemTablero(ItemTablero itemTablero) {
        repositorioItemTablero.guardar(itemTablero);
    }
}
