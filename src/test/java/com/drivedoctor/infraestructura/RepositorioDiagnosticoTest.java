package com.drivedoctor.infraestructura;


import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.RepositorioDiagnostico;
import com.drivedoctor.dominio.Sintoma;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class RepositorioDiagnosticoTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioDiagnostico repositorioDiagnostico;


    @BeforeEach
    public void init() {this.repositorioDiagnostico = new RepositorioDiagniosticoImpl(this.sessionFactory);}


    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerUnDiagnostico(){
        Sintoma perdidaGasolina = new Sintoma(ItemTablero.FILTRO_GASOLINA);

        Diagnostico diagnostico = this.crearDiagnostico(perdidaGasolina);

        Diagnostico diagnosticoEncontrado = repositorioDiagnostico.findBySintoma(ItemTablero.FILTRO_GASOLINA);
        assertEquals(ItemTablero.FILTRO_GASOLINA, diagnosticoEncontrado.getSintoma().getItemTablero());


    }




    private Diagnostico crearDiagnostico(Sintoma sintoma){
        Diagnostico diagnostico = new Diagnostico(sintoma);
        this.sessionFactory.getCurrentSession().save(diagnostico);
        return diagnostico;
    }

}
