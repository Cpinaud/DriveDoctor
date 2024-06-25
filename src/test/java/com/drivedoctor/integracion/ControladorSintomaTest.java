package com.drivedoctor.integracion;

import com.drivedoctor.config.GoogleMapsConfig;
import com.drivedoctor.dominio.Sintoma;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = {HibernateTestConfig.class, SpringWebTestConfig.class, GoogleMapsConfig.class})
public class ControladorSintomaTest {

    private Sintoma sitomaMock;

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
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
                .andExpect(model().attributeExists("sintoma"));
    }



}
