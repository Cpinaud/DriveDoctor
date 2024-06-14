package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.ServicioItemTablero;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("servicioItemTablero")
@Transactional
public class ServicioItemTableroImpl implements ServicioItemTablero {

    private RepositorioItemTablero repositorioItemTablero;



    @Override
    public List<ItemTablero> obtenerTodosLosItems() {
        return repositorioItemTablero.findAll();
    }

    @Override
    public ItemTablero findById(Integer idItemTablero) {
        return repositorioItemTablero.findById(idItemTablero);
    }
}
