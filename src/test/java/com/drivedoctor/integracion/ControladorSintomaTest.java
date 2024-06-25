package com.drivedoctor.integracion;

import com.drivedoctor.config.GoogleMapsConfig;
import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.ServicioItemTablero;
import com.drivedoctor.dominio.ServicioSintoma;
import com.drivedoctor.dominio.Sintoma;
import com.drivedoctor.dominio.excepcion.ItemsNoEncontrados;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import com.drivedoctor.integracion.config.SpringWebTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateTestConfig.class, SpringWebTestConfig.class, GoogleMapsConfig.class})
public class ControladorSintomaTest {


    private ServicioItemTablero servicioItemTablero;
    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        this.servicioItemTablero = mock(ServicioItemTablero.class);
    }

    @Test
    public void quePuedaNavegarALaVistaDeSintoma() throws Exception {
        this.mockMvc.perform(get("/sintoma"))
                .andExpect(status().isOk())
                .andExpect(view().name("sintoma"))
                .andExpect(model().attributeExists("sintoma"));
    }

    @Test
    public void queSePuedaNavegarALaVistaDeNuevoSintoma() throws Exception {
        this.mockMvc.perform(get("/nuevoSintoma"))
                .andExpect(status().isOk())
                .andExpect(view().name("nuevo-sintoma"))
                .andExpect(model().attributeExists("sintoma"));
    }

    @Test
    public void queSePuedaNavegarALaVistaParaSaberUnSintomaTeniendoUnItemEnElTablero() throws Exception {
        this.mockMvc.perform(get("/mostrarSintomaPorItem"))
                .andExpect(status().isOk())
                .andExpect(view().name("item-tablero"))
                .andExpect(model().attributeExists("opcionesItemTablero"));
    }

    @Test
    public void queSePuedaCrearUnNuevoSintoma() throws Exception {
        this.mockMvc.perform(post("/crearSintoma")
                        .param("nombre", "Perdida de aceite")
                        .param("descripcion", "Pierde aciete por debajo del motor"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/sintoma"));
    }

//    @Test
//    public void quePuedaMostrarSintomaPorItem() throws Exception {
//        List<ItemTablero> itemsTablero = Arrays.asList(
//                new ItemTablero(),
//                new ItemTablero()
//        );
//
//        when(this.servicioItemTablero.obtenerTodosLosItems()).thenReturn(itemsTablero);
//
//        this.mockMvc.perform(get("/mostrarSintomaPorItem"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("item-tablero"))
//                .andExpect(model().attributeExists("opcionesItemTablero"))
//                .andExpect(model().attribute("opcionesItemTablero", itemsTablero));
//
//        verify(this.servicioItemTablero, times(1)).obtenerTodosLosItems();
//    }




}
