package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.infraestructura.config.HibernateTestInfraestructuraConfig;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.hamcrest.Matcher;
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
import static org.hamcrest.core.IsEqual.equalTo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class RepositorioModeloTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioModelo repositorioModelo;

    @BeforeEach
    public void init(){
        this.repositorioModelo = new RepositorioModeloImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queCuandoSeBuscanMarcasRenaultDevuelvaModelosDeEsaMarca(){
        modelosYMarcasExistentes();
        Marca marca = new Marca("Renault");
        marca.setId(1);

        List<Modelo> modelosObtenidos = this.repositorioModelo.getByMarca(marca);

        Integer cantidadEsperada = 2;
        assertThat(modelosObtenidos.size(), equalTo(cantidadEsperada));


    }

    private void modelosYMarcasExistentes() {
        Marca marca = new Marca("Renault");
        marca.setId(1);
        Marca marca1 = new Marca("Ford");
        marca.setId(2);
        this.sessionFactory.getCurrentSession().save(marca);
        this.sessionFactory.getCurrentSession().save(marca1);
        Modelo modelo = new Modelo("Clio",marca);
        Modelo modelo1 = new Modelo("Sandero",marca);
        Modelo modelo2 = new Modelo("Fiesta",marca1);


        this.sessionFactory.getCurrentSession().save(modelo);
        this.sessionFactory.getCurrentSession().save(modelo1);
        this.sessionFactory.getCurrentSession().save(modelo2);

    }
}
