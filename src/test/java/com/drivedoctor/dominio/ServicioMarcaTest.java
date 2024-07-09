package com.drivedoctor.dominio;

import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import com.drivedoctor.infraestructura.ServicioMarcaImpl;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class ServicioMarcaTest {

    private ServicioMarca servicioMarca;
    private RepositorioMarca repositorioMarca;

    @BeforeEach
    public void init(){
        this.repositorioMarca = mock(RepositorioMarca.class);
        this.servicioMarca = new ServicioMarcaImpl(this.repositorioMarca);
    }

    @Test
    public void queSePuedanObtenerTodasLasMarcas(){
        List<Marca> marcasMock = new ArrayList<>();
        marcasMock.add(new Marca("Renault"));
        marcasMock.add(new Marca("Ford"));
        marcasMock.add(new Marca("Fiat"));
        when(repositorioMarca.getAll()).thenReturn(marcasMock);

        List<Marca> marcas = this.servicioMarca.obtenerMarcasAll();

        assertThat(marcas.size(), equalTo(3)); // Existan 1 elementos
    }

    @Test
    public void queSePuedaObtenerUnaMarcaPorId() throws ElementoNoEncontrado {
        Integer idbucada = 1;
        Integer id = 1;
        Marca marca = new Marca("Renault");
        marca.setId(id);

        when(this.repositorioMarca.findById(idbucada)).thenReturn(marca);
       Marca marcaObtenida = this.servicioMarca.findById(idbucada);

       assertThat(marcaObtenida.getId(), equalTo(idbucada));
        verify(this.repositorioMarca,times(1)).findById(idbucada);
    }

    @Test
    public void queDevuelvaLaExcepcionElementoNoEncontradoSiSeBuscaUnSintomaPorIdYNoSeEncuentra() throws ElementoNoEncontrado {
        Integer idBuscada = 1;
        Marca marca = new Marca();
        marca.setId(1);

        when(repositorioMarca.findById(idBuscada)).thenReturn(null);


        assertThrows(ElementoNoEncontrado.class, () -> {
            this.servicioMarca.findById(idBuscada);
        });
        verify(this.repositorioMarca,times(1)).findById(idBuscada);
    }
}
