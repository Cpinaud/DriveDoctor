package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.RepositorioTaller;
import com.drivedoctor.dominio.Taller;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("repositorioTaller")
public class RepositorioTallerImpl implements RepositorioTaller {

    private SessionFactory sessionFactory;

    public RepositorioTallerImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    @Override
    public List<Taller> findAll() {
        String sql = "From Taller";
        Query<Taller> query = this.sessionFactory.getCurrentSession().createQuery(sql, Taller.class);

        return query.getResultList();
    }

    @Override
    public List<Taller> obtenerPorLocalidad(String localidad) {
        return List.of();
    }


}
