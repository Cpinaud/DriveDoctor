package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ServicioSintoma;
import com.drivedoctor.dominio.ServicioSintomaImpl;
import com.drivedoctor.dominio.ServicioVehiculoImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class ControladorSintomaTest {

    private ControladorSintoma controladorSintoma;
    private ServicioSintoma servicioSintoma;


    @BeforeEach
    public void init(){

        this.servicioSintoma = mock(ServicioSintoma.class);
        this.controladorSintoma =  new ControladorSintoma(this.servicioSintoma);


    }

    @Test
    public void queSePuedaIrALaVistaDeSintoma(){


    }



}
