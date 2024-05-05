package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.RepositorioSintoma;
import com.drivedoctor.dominio.Sintoma;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repositorioSintoma")
public class RepositorioSintomaImpl implements RepositorioSintoma {

        private SessionFactory sessionFactory;


        public RepositorioSintomaImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory; }

    @Override
    public Sintoma buscar(String problema) {
        return (Sintoma) sessionFactory.getCurrentSession().createCriteria(Sintoma.class)
                .add(Restrictions.eq("problema", problema));
    }

    @Override
    public void guaradar(Sintoma sintoma) {
            sessionFactory.getCurrentSession().save(sintoma);
    }
}
