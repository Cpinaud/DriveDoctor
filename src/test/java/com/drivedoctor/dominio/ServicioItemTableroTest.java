package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import com.drivedoctor.dominio.excepcion.ItemNoEncontrado;
import com.drivedoctor.infraestructura.RepositorioItemTableroImpl;
import com.drivedoctor.infraestructura.ServicioItemTableroImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;



public class ServicioItemTableroTest {

        private ServicioItemTablero servicioItemTablero;
        private RepositorioItemTableroImpl repositorioItemTablero;

    @BeforeEach
    public void init() {
        this.repositorioItemTablero = mock(RepositorioItemTableroImpl.class);
        this.servicioItemTablero= new ServicioItemTableroImpl(repositorioItemTablero);
    }

    @Test
    public void queSePuedanObtenerTodosLosItems() throws ItemNoEncontrado {
        List<ItemTablero> items = new ArrayList<>();
        items.add(new ItemTablero());
        items.add(new ItemTablero());
        items.add(new ItemTablero());


        when(this.repositorioItemTablero.findAll()).thenReturn(items);
       List<ItemTablero> obtenidos = this.servicioItemTablero.obtenerTodosLosItems();

       assertThat(obtenidos.size(),equalTo(3));
       verify(this.repositorioItemTablero, times(1)).findAll();
    }

    @Test
    public void queSePuedaObetenerUnItemTableroPorId() throws ElementoNoEncontrado, ItemNoEncontrado {
        Integer id = 1;
        ItemTablero itemTablero = new ItemTablero();
       itemTablero.setIdItemTablero(1);

       when(this.repositorioItemTablero.findById(id)).thenReturn(itemTablero);
       ItemTablero obtenido = this.servicioItemTablero.findById(id);

       assertThat(obtenido.getIdItemTablero(),equalTo(id));
       verify(this.repositorioItemTablero, times(1)).findById(id);
    }
}
