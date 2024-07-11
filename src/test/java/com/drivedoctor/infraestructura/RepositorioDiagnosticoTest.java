package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import com.drivedoctor.infraestructura.config.HibernateTestInfraestructuraConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class RepositorioDiagnosticoTest {

    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioDiagnostico repositorioDiagnostico;
    private Sintoma sintomaMock;

    @BeforeEach
    public void init() {
        this.repositorioDiagnostico = new RepositorioDiagnosticoImpl(this.sessionFactory);
        sintomaMock = mock(Sintoma.class);
    }
    @Test
    @Transactional
    @Rollback
    public void queSeObtengaElDiagnosticoCreado() throws ElementoNoEncontrado {
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

        Diagnostico diagnosticoObtenido1 = sintomaMock1.getDiagnostico();
        Diagnostico diagnosticoObtenido2 = sintomaMock2.getDiagnostico();

        // Verificar que cada síntoma tenga un diagnóstico asociado y que sean distintos
        assertThat(diagnosticoObtenido1, notNullValue());
        assertThat(diagnosticoObtenido2, notNullValue());
        assertThat(diagnosticoObtenido1, not(equalTo(diagnosticoObtenido2)));

        // Verificar que exactamente dos diagnósticos fueron obtenidos
        Set<Diagnostico> diagnosticosObtenidos = new HashSet<>(Arrays.asList(diagnosticoObtenido1, diagnosticoObtenido2));
        assertThat(diagnosticosObtenidos.size(), equalTo(2));
    }
    private Diagnostico crearYguardarDiagnostico(String descripcion) {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setDescripcion(descripcion);
        repositorioDiagnostico.guardar(diagnostico);
        return diagnostico;
    }

}
