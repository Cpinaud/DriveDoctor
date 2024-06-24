package com.drivedoctor.dominio;

import com.drivedoctor.infraestructura.ServicioSintomaImpl;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.criteria.CriteriaBuilder;
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
    @Test
    public void queSePuedaObtenerTodosLosSintomas(){
        List<Sintoma> sintomas = new ArrayList<>();
        sintomas.add(new Sintoma(new ItemTablero()));
        sintomas.add(new Sintoma(new ItemTablero()));
        Integer cantidadEsperada = 2;

        when(this.repositorioSintoma.getAll()).thenReturn(sintomas);
        this.servicioSintoma.findAll();

        assertThat(sintomas.size(),equalTo(cantidadEsperada));
        verify(this.repositorioSintoma,times(1)).getAll();
    }

    @Test
    public void queSePuedaObtenerUnSintomaPorId(){
        Integer idBuscada = 1;
        Sintoma sintoma = new Sintoma(new ItemTablero());
        sintoma.setIdSintoma(1);

        when(this.repositorioSintoma.findById(idBuscada)).thenReturn(sintoma);
        this.servicioSintoma.findById(idBuscada);

        assertThat(sintoma.getIdSintoma(),equalTo(idBuscada));
        verify(this.repositorioSintoma,times(1)).findById(idBuscada);
    }

    @Test
    public void queSePuedaObtenerLosSintomasAPartirDeLosItemsDelTablero(){
        List<ItemTablero> itemsDeTablero = new ArrayList<>();
        itemsDeTablero.add(new ItemTablero("ItemFiltroGasolina"));
        itemsDeTablero.add(new ItemTablero("ItemMotor"));

        List<Sintoma> sintomas = new ArrayList<>();
        sintomas.add(new Sintoma(new ItemTablero("ItemFiltroGasolina")));
        sintomas.add(new Sintoma(new ItemTablero("ItemMotor")));

        when(this.repositorioSintoma.obtenerPorItemsTablero(itemsDeTablero)).thenReturn(sintomas);
        List<Sintoma> sintomasObtenidos =  this.servicioSintoma.problemasEnTablero(itemsDeTablero);

        assertThat(sintomasObtenidos.size(),equalTo(2));
        verify(this.repositorioSintoma,times(1)).obtenerPorItemsTablero(itemsDeTablero);
    }

    @Test
    public void queSePuedanObtenerSintomasPorSusIds(){
        Integer idBuscada = 1;
        Integer idBuscada1 = 2;
        Integer idBuscada2 = 3;
        Sintoma sintoma = new Sintoma(new ItemTablero());
        Sintoma sintoma1 = new Sintoma(new ItemTablero());
        Sintoma sintoma2 = new Sintoma(new ItemTablero());
        sintoma.setIdSintoma(1);
        sintoma1.setIdSintoma(2);
        sintoma2.setIdSintoma(3);

        List<Integer> listaDeIds =new ArrayList<>();
        listaDeIds.add(idBuscada);
        listaDeIds.add(idBuscada1);
        listaDeIds.add(idBuscada2);

        List<Sintoma> sintomasPorId = new ArrayList<>();
        sintomasPorId.add(sintoma);
        sintomasPorId.add(sintoma1);
        sintomasPorId.add(sintoma2);


        when(this.repositorioSintoma.obtenerLosSintomasPorSusIds(listaDeIds)).thenReturn(sintomasPorId);
        List<Sintoma> sintomasObtenidos = this.servicioSintoma.obtenerSintomasPorSuId(listaDeIds);

        assertThat(sintomasObtenidos.size(),equalTo(3));
        verify(this.repositorioSintoma,times(1)).obtenerLosSintomasPorSusIds(listaDeIds);
    }
}
