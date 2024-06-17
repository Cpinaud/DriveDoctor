package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.RepositorioDiagnostico;
import com.drivedoctor.dominio.Sintoma;
import com.drivedoctor.infraestructura.config.HibernateTestInfraestructuraConfig;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestInfraestructuraConfig.class})
public class RepositorioItemTableroTest {

    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioItemTablero repositorioItemTablero;
    private ItemTablero itemTableroMock;

    @BeforeEach
    public void init() {
        this.repositorioItemTablero = new RepositorioItemTablero(this.sessionFactory);
        itemTableroMock = mock(ItemTablero.class);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedanObtenerTodosLosItems()
    {
        ItemTablero itemTablero1 = new ItemTablero();
        ItemTablero itemTablero2 = new ItemTablero();
        repositorioItemTablero.guardar(itemTablero1);
        repositorioItemTablero.guardar(itemTablero2);

        List<ItemTablero> itemsTableros = new ArrayList<>();

        itemsTableros.add(itemTablero1);
        itemsTableros.add(itemTablero2);

        List<ItemTablero> cantidadObtenida = repositorioItemTablero.findAll();


        int cantidadDeObejetos = 2;
        assertThat(cantidadObtenida.size(), equalTo(cantidadDeObejetos));




    }
}
