package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ItemNoEncontrado;
import com.drivedoctor.dominio.excepcion.ItemsNoEncontrados;
import com.drivedoctor.infraestructura.RepositorioItemTablero;
import com.drivedoctor.infraestructura.ServicioItemTableroImpl;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})

public class ServicioItemTableroTest {

        private ServicioItemTablero servicioItemTablero;
        private com.drivedoctor.infraestructura.RepositorioItemTablero repositorioItemTablero;

    @BeforeEach
    public void init() {
        this.repositorioItemTablero = mock(RepositorioItemTablero.class);
        this.servicioItemTablero= new ServicioItemTableroImpl(repositorioItemTablero);
    }

    @Test
    public void queSePuedanObtenerTodosLosItems() throws ItemsNoEncontrados {
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
    public void queSePuedaObetenerUnItemTableroPorId() throws ItemNoEncontrado {
        Integer id = 1;
        ItemTablero itemTablero = new ItemTablero();
       itemTablero.setIdItemTablero(1);

       when(this.repositorioItemTablero.findById(id)).thenReturn(itemTablero);
       ItemTablero obtenido = this.servicioItemTablero.findById(id);

       assertThat(obtenido.getIdItemTablero(),equalTo(id));
       verify(this.repositorioItemTablero, times(1)).findById(id);
    }
}
