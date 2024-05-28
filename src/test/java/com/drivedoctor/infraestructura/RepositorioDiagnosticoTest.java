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
    private Session session;
    private Sintoma sintomaMock;

    @BeforeEach
    public void init() {
        sessionFactory = mock(SessionFactory.class);
        session = mock(Session.class); // Crear un mock de Session
        when(sessionFactory.getCurrentSession()).thenReturn(session); // Configurar el mock sessionFactory
        repositorioDiagnostico = new RepositorioDiagnosticoImpl(sessionFactory);
        sintomaMock = mock(Sintoma.class);
    }
    @Test
    @Transactional
    @Rollback
    public void queSeObtengaElDiagnosticoCreado()
    {
        Diagnostico diagnostico = crearYguardarDiagnostico("Prueba");

        // Simular que el diagnostico se guarda en la sesión
        when(session.get(Diagnostico.class, diagnostico.getIdDiagnostico())).thenReturn(diagnostico);
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
        when(sintomaMock.getDiagnostico()).thenReturn(diagnosticoEsperado);

        // Obtener el diagnóstico desde el mock
        Diagnostico diagnosticoObtenido = sintomaMock.getDiagnostico();

        assertThat(diagnosticoObtenido, notNullValue());
        assertThat(diagnosticoObtenido.getIdDiagnostico(), equalTo(diagnosticoEsperado.getIdDiagnostico()));
    }
    @Test
    @Transactional
    @Rollback
    public void queSeObtengaDosDiagnosticosAsociadosAdosSintomas(){ //sintomas con items diferentes
        Diagnostico diagnostico1 = crearYguardarDiagnostico("Diagnostico 1");
        Diagnostico diagnostico2 = crearYguardarDiagnostico("Diagnostico 2");

        Sintoma sintomaMock1 = mock(Sintoma.class);
        Sintoma sintomaMock2 = mock(Sintoma.class);

        when(sintomaMock1.getDiagnostico()).thenReturn(diagnostico1);
        when(sintomaMock2.getDiagnostico()).thenReturn(diagnostico2);

        // Obtener los diagnósticos asociados a los síntomas desde el mock
        Diagnostico diagnosticoObtenido1 = sintomaMock1.getDiagnostico();
        Diagnostico diagnosticoObtenido2 = sintomaMock2.getDiagnostico();

        // Verificar los resultados
        assertThat(diagnosticoObtenido1, notNullValue());
        assertThat(diagnosticoObtenido1.getIdDiagnostico(), equalTo(diagnostico1.getIdDiagnostico()));

        assertThat(diagnosticoObtenido2, notNullValue());
        assertThat(diagnosticoObtenido2.getIdDiagnostico(), equalTo(diagnostico2.getIdDiagnostico()));
    }
    private Diagnostico crearYguardarDiagnostico(String descripcion) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setDescripcion(descripcion);
        repositorioDiagnostico.guardar(diagnostico);
        return diagnostico;
    }

}
