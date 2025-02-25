package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioModeloImpl implements RepositorioModelo {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioModeloImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Modelo> getAll() {
        String hql = "FROM Modelo ";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }
    @Override
    public List<Modelo> getByMarca(Marca marca) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Modelo.class);
        criteria.add(Restrictions.eq("marca", marca));
        return criteria.list();
    }

    @Override
    public Modelo findById(Integer id) {
        String hql = "FROM Modelo WHERE id = :id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("id", id);
        return (Modelo) query.getSingleResult();
    }

    @Override
    public void guardar(Modelo modelo) {

        this.sessionFactory.getCurrentSession().save(modelo);
    }

    @Override
    public void modificar(Modelo modelo) {
        sessionFactory.getCurrentSession().update(modelo);
    }

    @Override
    public void eliminar(Modelo modelo) {
        sessionFactory.getCurrentSession().delete(modelo);
    }




}
