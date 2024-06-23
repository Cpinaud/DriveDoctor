package com.drivedoctor.dominio;

import com.drivedoctor.infraestructura.ServicioMarcaImpl;
import com.drivedoctor.infraestructura.ServicioModeloImpl;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class ServicioModeloTest {

    private ServicioModelo servicioModelo;
    private RepositorioModelo repositorioModelo;

    @BeforeEach
    public void init(){
        this.repositorioModelo = mock(RepositorioModelo.class);
        this.servicioModelo = new ServicioModeloImpl(this.repositorioModelo);
    }

    @Test
    public void queSePuedanObtenerTodosLosModelosDeUnaMarca(){
        Marca marca = new Marca("Renault");
        List<Modelo> modelosMock = new ArrayList<>();
        modelosMock.add(new Modelo("Clio",marca));
        modelosMock.add(new Modelo("Sandero",marca));
        when(repositorioModelo.getByMarca(marca)).thenReturn(modelosMock);

        List<Modelo> modelos = this.servicioModelo.obtenerModeloPorMarca(marca);

        assertThat(modelos.size(), equalTo(2)); // Existan 1 elementos
    }
}
