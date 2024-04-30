package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.RepositorioSintoma;
import com.drivedoctor.dominio.Sintoma;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("repositorioSintoma")
public class RepositorioSintomaImpl implements RepositorioSintoma {

        private SessionFactory sessionFactory

        @Autowired
        public RepositorioSintomaImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory; }

    @Override
    public Sintoma buscar(String problema) {
        return null;
    }

    @Override
    public void guaradar(Sintoma sintoma) {

    }
}
