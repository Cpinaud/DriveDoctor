package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.RepositorioSintoma;
import com.drivedoctor.dominio.Sintoma;
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
    public void queSePuedaGuardarUnSintomaAsociadoAunItemDelTablero(){

        Sintoma sintoma = this.crearSintoma(ItemTablero.AIRBAG);

        this.repositorioSintoma.guardar(sintoma);
        this.sessionFactory.getCurrentSession().flush(); // Asegura que el ID se asigne

        Sintoma sintomaObtenido = (Sintoma) this.sessionFactory.getCurrentSession()
                .createQuery("FROM Sintoma WHERE idSintoma = :id")
                .setParameter("id", sintoma.getIdSintoma())
                .getSingleResult();

        assertThat(sintomaObtenido, equalTo(sintoma));
    }
    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerUnSintomaAsociadoAunItemDelTablero(){
        sintomasExistentes();

        List<Sintoma> sintomasObtenidos = this.repositorioSintoma.obtenerPorItemTablero(ItemTablero.EPC);

        Integer cantidadEsperada = 1;
        assertThat(cantidadEsperada, equalTo(sintomasObtenidos.size()));


    }
    @Test
    @Transactional
    @Rollback
    public void queSePuedaObtenerDosSintomasAsociadoAunItemDelTablero(){
        sintomasExistentes();

        List<Sintoma> sintomasObtenidos = this.repositorioSintoma.obtenerPorItemTablero(ItemTablero.EMBRAGUE);

        Integer cantidadEsperada = 2;
        assertThat(cantidadEsperada, equalTo(sintomasObtenidos.size()));

    }


    private Sintoma crearSintoma(ItemTablero itemTablero) {
        Sintoma sintoma = new Sintoma(itemTablero);
        this.sessionFactory.getCurrentSession().save(sintoma);
        return sintoma;
    }

    private void sintomasExistentes() {
        Sintoma sintoma = new Sintoma(ItemTablero.EPC);
        Sintoma sintoma2 = new Sintoma(ItemTablero.EMBRAGUE);
        Sintoma sintoma3 = new Sintoma(ItemTablero.EMBRAGUE);

        this.sessionFactory.getCurrentSession().save(sintoma);
        this.sessionFactory.getCurrentSession().save(sintoma2);
        this.sessionFactory.getCurrentSession().save(sintoma3);

    }


}
