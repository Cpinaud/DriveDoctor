package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Historial;
import com.drivedoctor.dominio.RepositorioHistorial;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("repositorioHistorial")
public class RepositorioHistorialImpl implements RepositorioHistorial {

    private SessionFactory sessionFactory;

    public RepositorioHistorialImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;};

    @Override
    public void guardarHistoria(Historial historia) {
        sessionFactory.getCurrentSession().save(historia);
    }
}
