package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.RepositorioSintoma;
import com.drivedoctor.dominio.Sintoma;
import com.drivedoctor.infraestructura.config.HibernateTestInfraestructuraConfig;
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
public class RepositorioSintomaTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioSintoma repositorioSintoma;

    @BeforeEach
    public void init(){
        this.repositorioSintoma = new RepositorioSintomaImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void queSePuedaGuardarUnSintoma(){
        Sintoma sintoma = this.crearSintoma("Perdida de nafta");

        this.repositorioSintoma.guaradar(sintoma);

        Sintoma sintomaObtenido = (Sintoma) this.sessionFactory.getCurrentSession()
                .createQuery("FROM Sintoma where idSintoma = 1")
                .getSingleResult();

        assertThat(sintomaObtenido, equalTo(sintoma));


    }
    @Test
    @Transactional
    @Rollback
    public void queSePuedaBuscarUnSintoma(){
    sintomasExistentes();

    Sintoma sintomaObtenidos = this.repositorioSintoma.buscar("desbalanceado");

    Integer cantidadaObtenida = 1;
    assertThat(cantidadaObtenida, equalTo(sintomasObtenidos));


    }



    private Sintoma crearSintoma(String nombre) {
        Sintoma sintoma = new Sintoma(nombre);
        this.sessionFactory.getCurrentSession().save(sintoma);
        return sintoma;
    }

    private void sintomasExistentes() {
        Sintoma sintoma = new Sintoma("perdida de nafta");
        Sintoma sintoma2 = new Sintoma("perdidad de aceite");
        Sintoma sintoma3 = new Sintoma("Desbalanceado");
    }
}
