package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.RepositorioDiagnostico;
import com.drivedoctor.infraestructura.config.HibernateTestInfraestructuraConfig;
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
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class RepositorioDiagnosticoTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioDiagnostico repositorioDiagnostico;

    @BeforeEach
    public void init(){this.repositorioDiagnostico = new RepositorioDiagnosticoImpl(this.sessionFactory);}

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerElDiagnostico()
    {
        Diagnostico diagnostico = new Diagnostico();
        diagnostico.setDescripcion("Prueba");
        repositorioDiagnostico.guardar(diagnostico);


        Integer idDiagnostico = diagnostico.getIdDiagnostico();


        Diagnostico found = repositorioDiagnostico.findById(idDiagnostico);


        assertThat(found.getIdDiagnostico(), equalTo(idDiagnostico));
        assertThat(found.getDescripcion(), equalTo(diagnostico.getDescripcion()));


    }





}
