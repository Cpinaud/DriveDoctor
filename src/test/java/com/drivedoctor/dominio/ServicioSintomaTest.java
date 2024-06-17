package com.drivedoctor.dominio;


import com.drivedoctor.infraestructura.config.HibernateTestInfraestructuraConfig;
import com.drivedoctor.infraestructura.ServicioSintomaImpl;
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
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
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
        ItemTablero itemTableroMock = mock(ItemTablero.class);

        Sintoma sintoma = new Sintoma(itemTableroMock);

        this.repositorioSintoma.guardar(sintoma);

        verify(repositorioSintoma).guardar(sintoma);
    }

    @Test
    public void quePuedaObtenerLosItemsDelTableroEnBaseAMiSintoma(){
        ItemTablero itemTableroMock = mock(ItemTablero.class);
        when(itemTableroMock.getNombre()).thenReturn("itemEmbrague");

        List<Sintoma> sintomasMock = new ArrayList<>();
        sintomasMock.add(new Sintoma(itemTableroMock));
        sintomasMock.add(new Sintoma(itemTableroMock));
        when(this.repositorioSintoma.obtenerPorItemTablero(itemTableroMock)).thenReturn(sintomasMock);

        List<Sintoma> sintomas = this.servicioSintoma.problemaEnTablero(itemTableroMock);

        assertThat(sintomas.size(), equalTo(2));

    }



}
