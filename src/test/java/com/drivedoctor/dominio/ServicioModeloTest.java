package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import com.drivedoctor.infraestructura.ServicioMarcaImpl;
import com.drivedoctor.infraestructura.ServicioModeloImpl;
import com.drivedoctor.infraestructura.ServicioSintomaImpl;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


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
    @Test
    public void queSePuedaObtenerUnModeloPorId() throws ElementoNoEncontrado {
        Integer idModelo = 1;
        Integer idModeloBuscado = 1;
        Modelo modelo = new Modelo("Cronos",new Marca("Renault"));
        modelo.setId(idModelo);

        when(this.repositorioModelo.findById(idModeloBuscado)).thenReturn(modelo);
       Modelo modeloObtenido = this.servicioModelo.findById(idModeloBuscado);

       assertThat(modeloObtenido.getId(), equalTo(idModeloBuscado));
       verify(this.repositorioModelo,times(1)).findById(idModeloBuscado);
    }

    @Test
    public void queDevuelvaLaExcepcionElementoNoEncontradoSiSeBuscaUnModeloPorIdYNoSeEncuentra() throws ElementoNoEncontrado {
        Integer idBuscada = 1;
        Modelo modelo = new Modelo();
        modelo.setId(1);

        when(this.repositorioModelo.findById(idBuscada)).thenReturn(null);


        assertThrows(ElementoNoEncontrado.class, () -> {
            this.servicioModelo.findById(idBuscada);
        });
        verify(this.repositorioModelo,times(1)).findById(idBuscada);
    }
}
