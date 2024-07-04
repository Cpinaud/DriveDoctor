package com.drivedoctor.infraestructura;


import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.*;
import com.drivedoctor.infraestructura.ServicioVehiculoImpl;
import com.drivedoctor.integracion.config.HibernateTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.hibernate.Session;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})

public class RepositorioMarcaTest {
    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioMarcaImpl repositorioMarca;

    @BeforeEach
    public void init() {
        this.repositorioMarca = new RepositorioMarcaImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedanObtenerTodasLasMarcas() {
        Marca marca1 = new Marca();
        marca1.setNombre("Toyota");

        Marca marca2 = new Marca();
        marca2.setNombre("Ford");

        Session session = sessionFactory.getCurrentSession();
        session.save(marca1);
        session.save(marca2);

        List<Marca> marcas = repositorioMarca.getAll();

        assertThat(marcas, notNullValue());
        assertThat(marcas.size(), greaterThanOrEqualTo(2));
        assertThat(marcas, hasItems(marca1, marca2));
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerUnaMarcaPorId() {
        Marca marca1 = new Marca();
        marca1.setNombre("Toyota");
        marca1.setId(1);
        Integer Idbuscada = 1;
        Session session = sessionFactory.getCurrentSession();
        session.save(marca1);


        Marca buscada = this.sessionFactory.getCurrentSession()
                .createQuery("FROM Marca WHERE id = :idBuscado", Marca.class)
                .setParameter("idBuscado", Idbuscada)
                .uniqueResult();

        assertThat(buscada, notNullValue());
        assertThat(buscada.getId(), equalTo(Idbuscada));
    }
}