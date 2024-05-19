package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.RepositorioDiagnostico;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class RepositorioDiagnosticoTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioDiagnostico repositorioDiagnostico;

    @BeforeEach
    public void init(){this.repositorioDiagnostico = new RepositorioDiagniosticoImpl(this.sessionFactory);}

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerElDiagnostico()
    {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setDescripcion("Prueba");
        repositorioDiagnostico.guardar(diagnostico);

        // Obtener el ID del diagnóstico guardado
        Integer idDiagnostico = diagnostico.getIdDiagnostico();

        // Buscar el diagnóstico por su ID
        Diagnostico found = repositorioDiagnostico.findById(idDiagnostico);

        // Asegurarse de que el diagnóstico encontrado coincide con el guardado
        assertThat(found.getIdDiagnostico(), equalTo(idDiagnostico));
        assertThat(found.getDescripcion(), equalTo(diagnostico.getDescripcion()));


    }





}
