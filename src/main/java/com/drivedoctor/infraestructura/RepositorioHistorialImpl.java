package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.Historial;
import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.RepositorioHistorial;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("repositorioHistorial")
public class RepositorioHistorialImpl implements RepositorioHistorial {

    private SessionFactory sessionFactory;

    public RepositorioHistorialImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;};

    @Override
    public Historial findById(Integer id)  {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Historial.class, id);
    }

    @Override
    public void guardar(Historial historia) {

        sessionFactory.getCurrentSession().save(historia);
    }

    @Override
    public void modificar(Historial historia) {
        sessionFactory.getCurrentSession().update(historia);
    }

    @Override
    public void eliminar(Historial historia) {
        sessionFactory.getCurrentSession().delete(historia);
    }

}
