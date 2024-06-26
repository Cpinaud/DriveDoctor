package com.drivedoctor.infraestructura;


import com.drivedoctor.dominio.Marca;
import com.drivedoctor.dominio.ParteDelAuto;
import com.drivedoctor.integracion.config.HibernateTestConfig;
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
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})

public class RepositorioParteDelAutoTest {
    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioParteDelAutoImpl repositorioParteDelAuto;

    @BeforeEach
    public void init() {
        this.repositorioParteDelAuto = new RepositorioParteDelAutoImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnaParteDeAuto() {
        // Preparación
        ParteDelAuto nuevaParte = new ParteDelAuto();
        nuevaParte.setIdParteDelAuto(1);

        this.repositorioParteDelAuto.guardar(nuevaParte);

        ParteDelAuto obtenida = (ParteDelAuto) sessionFactory.getCurrentSession()
                .get(ParteDelAuto.class, nuevaParte.getIdParteDelAuto());


        assertThat(obtenida, notNullValue());
        assertThat(obtenida.getIdParteDelAuto(), equalTo(nuevaParte.getIdParteDelAuto()));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerUnaParteDeAutoPorId() {
        ParteDelAuto parteDelAuto = new ParteDelAuto();
        parteDelAuto.setIdParteDelAuto(1);

        Session session = sessionFactory.getCurrentSession();
        session.save(parteDelAuto);

        ParteDelAuto obtenida = repositorioParteDelAuto.obtenerParte(parteDelAuto.getIdParteDelAuto());

        assertThat(obtenida, notNullValue());
        assertThat(obtenida.getIdParteDelAuto(), equalTo(parteDelAuto.getIdParteDelAuto()));
    }
}