package com.drivedoctor.dominio;

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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
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
}
