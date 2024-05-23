package com.drivedoctor.dominio;

import com.drivedoctor.infraestructura.ServicioSintomaImpl;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class ServicioSintomaTest {

    private ServicioSintoma servicioSintoma;
    private RepositorioSintoma repositorioSintoma;

    @BeforeEach
    public void init(){
        this.repositorioSintoma = mock(RepositorioSintoma.class);
        this.servicioSintoma = new ServicioSintomaImpl(this.repositorioSintoma);
    }

    @Test
    public void queSePuedaGuardarUnSintomaEnElSistema(){
        Sintoma sintoma = new Sintoma(ItemTablero.EMBRAGUE);

        this.repositorioSintoma.guardar(sintoma);

        verify(repositorioSintoma).guardar(sintoma);
    }

    @Test
    public void quePuedaObtenerLosItemsDelTableroEnBaseAMiSintoma(){
        List<Sintoma> sintomasMock = new ArrayList<>();
        sintomasMock.add(new Sintoma(ItemTablero.EMBRAGUE));
        sintomasMock.add(new Sintoma(ItemTablero.EMBRAGUE));
        when(this.repositorioSintoma.obtenerPorItemTablero(ItemTablero.EMBRAGUE)).thenReturn(sintomasMock);

        List<Sintoma> sintomas = this.servicioSintoma.problemaEnTablero(ItemTablero.EMBRAGUE);

        assertThat(sintomas.size(), equalTo(2));

    }

}
