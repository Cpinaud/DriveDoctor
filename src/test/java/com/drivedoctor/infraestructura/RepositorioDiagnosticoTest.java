package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.infraestructura.config.HibernateTestInfraestructuraConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class RepositorioDiagnosticoTest {

    private RepositorioDiagnostico repositorioDiagnostico;
    private SessionFactory sessionFactory;
    private Sintoma sintomaMock;
    private Diagnostico diagnosticoMock;
    private RepositorioSintoma repositorioSintoma;

    @BeforeEach
    public void init() {
        sessionFactory = mock(SessionFactory.class);
        Session session = mock(Session.class); // Crear un mock de Session
        when(sessionFactory.getCurrentSession()).thenReturn(session); // Configurar el mock sessionFactory
        repositorioDiagnostico = new RepositorioDiagnosticoImpl(sessionFactory);
        sintomaMock = mock(Sintoma.class);
        diagnosticoMock = mock(Diagnostico.class);
        this.repositorioSintoma = new RepositorioSintomaImpl(this.sessionFactory);
    }
    @Test
    @Transactional
    @Rollback
    public void queSeObtengaElDiagnosticoCreado()
    {
        Diagnostico diagnostico = crearYguardarDiagnostico("Prueba");

        Diagnostico diagnosticoObtenido = repositorioDiagnostico.findById(diagnostico.getIdDiagnostico());

        assertThat(diagnosticoObtenido, notNullValue());
        assertThat(diagnosticoObtenido.getIdDiagnostico(), equalTo(diagnostico.getIdDiagnostico()));
        assertThat(diagnosticoObtenido.getDescripcion(), equalTo(diagnostico.getDescripcion()));


    }
    @Test
    @Transactional
    @Rollback
    public void queSeObtengaUnDiagnosticoAsociadoAunSintoma(){

        Diagnostico diagnosticoEsperado = crearYguardarDiagnostico("Prueba");
        Sintoma sintoma = crearYguardarSintoma("Perdida de nafta", ItemTablero.MOTOR, diagnosticoEsperado);

        // Configurar el comportamiento del mock para que devuelva el diagnóstico esperado cuando se llama a findById() con el ID del diagnóstico
        when(repositorioDiagnostico.findById(diagnosticoEsperado.getIdDiagnostico())).thenReturn(diagnosticoEsperado);

        Diagnostico diagnosticoObtenido = sintoma.getDiagnostico();

        assertThat(diagnosticoObtenido, notNullValue());
        assertThat(diagnosticoObtenido.getIdDiagnostico(), equalTo(diagnosticoEsperado.getIdDiagnostico()));
    }
    @Test
    @Transactional
    @Rollback
    public void queSeObtengaDosDiagnosticosAsociadosAdosSintomas(){ //sintomas con items diferentes
        Diagnostico diagnostico1 = crearYguardarDiagnostico("Diagnostico 1");
        Diagnostico diagnostico2 = crearYguardarDiagnostico("Diagnostico 2");

        Sintoma sintoma1 = crearYguardarSintoma("Perdida de nafta", ItemTablero.MOTOR, diagnostico1);
        Sintoma sintoma2 = crearYguardarSintoma("Sonda lambda", ItemTablero.FRENOS, diagnostico2);

        // Configurar el comportamiento del repositorio para que devuelva los diagnósticos esperados
        when(repositorioDiagnostico.findById(diagnostico1.getIdDiagnostico())).thenReturn(diagnostico1);
        when(repositorioDiagnostico.findById(diagnostico2.getIdDiagnostico())).thenReturn(diagnostico2);

        // Obtener los diagnósticos asociados a los síntomas
        Diagnostico diagnosticoObtenido1 = sintoma1.getDiagnostico();
        Diagnostico diagnosticoObtenido2 = sintoma2.getDiagnostico();

        // Verificar los resultados
        assertThat(diagnosticoObtenido1, notNullValue());
        assertThat(diagnosticoObtenido1.getIdDiagnostico(), equalTo(diagnostico1.getIdDiagnostico()));

        assertThat(diagnosticoObtenido2, notNullValue());
        assertThat(diagnosticoObtenido2.getIdDiagnostico(), equalTo(diagnostico2.getIdDiagnostico()));

    }
    /*@Test
    @Transactional
    @Rollback
    public void queNoSeObtengaTresOmasDiagnosticosAsociadosATresoMasSintomas(){ //sintomas con items diferentes
        Diagnostico diagnostico1 = crearYguardarDiagnostico("Diagnostico 1");
        Diagnostico diagnostico2 = crearYguardarDiagnostico("Diagnostico 2");
        Diagnostico diagnostico3 = crearYguardarDiagnostico("Diagnostico 3");

        Sintoma sintoma1 = crearYguardarSintoma("Perdida de nafta", 1, diagnostico1.getIdDiagnostico());
        Sintoma sintoma2 = crearYguardarSintoma("Sonda lambda", 2, diagnostico2.getIdDiagnostico());
        Sintoma sintoma3 = crearYguardarSintoma("Cambios en el recorrido del pedal de freno", 3, diagnostico3.getIdDiagnostico());

        // Suponiendo que el método findBySintomasIds retorne null si hay más de dos diagnósticos
        List<Integer> idsSintomas = Arrays.asList(sintoma1.getIdSintoma(), sintoma2.getIdSintoma(), sintoma3.getIdSintoma());
        Diagnostico diagnosticoObtenido = repositorioDiagnostico.findBySintomasIds(idsSintomas);

        assertThat(diagnosticoObtenido, nullValue());

    }
    @Test
    @Transactional
    @Rollback
    public void queNoSeObtengaDiagnosticosAsociadosASintomasQueCompartenMismoItem(){
        Diagnostico diagnostico = crearYguardarDiagnostico("Prueba");

        // Crear y guardar síntomas con el mismo ítem de tablero
        Sintoma sintoma1 = crearYguardarSintoma("Perdida de nafta", 1, diagnostico.getIdDiagnostico());
        Sintoma sintoma2 = crearYguardarSintoma("Manguera rota", 1, diagnostico.getIdDiagnostico());

        // Suponiendo que el método findBySintomasIds retorne null si los síntomas comparten el mismo ítem de tablero
        List<Integer> idsSintomas = Arrays.asList(sintoma1.getIdSintoma(), sintoma2.getIdSintoma());
        Diagnostico diagnosticoObtenido = repositorioDiagnostico.findBySintomasIds(idsSintomas);

        assertThat(diagnosticoObtenido, nullValue());

    }*/

    private Diagnostico crearYguardarDiagnostico(String descripcion) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setDescripcion(descripcion);
        repositorioDiagnostico.guardar(diagnostico);
        return diagnostico;
    }

    private Sintoma crearYguardarSintoma(String nombre, ItemTablero itemTablero, Diagnostico diagnostico) {
        Sintoma sintoma = new Sintoma();
        sintoma.setNombre(nombre);
        sintoma.setItemTablero(itemTablero);
        sintoma.setDiagnostico(diagnostico);
        // este emtodo deberia ir en el repositorio de Diagnostico o de sintoma
        repositorioSintoma.guardar(sintoma);
        return sintoma;
    }



}
