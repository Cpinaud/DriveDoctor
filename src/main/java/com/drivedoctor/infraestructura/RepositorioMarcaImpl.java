package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RepositorioMarcaImpl implements RepositorioMarca {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioMarcaImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Marca> getAll() {
        String hql = "FROM Marca ";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Marca findById(Integer id) {
        return (Marca) sessionFactory.getCurrentSession().createCriteria(Marca.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();

    }

    @Override
    public void guardar(Marca marca) {

        this.sessionFactory.getCurrentSession().save(marca);
    }

    @Override
    public void modificar(Marca marca) {
        sessionFactory.getCurrentSession().update(marca);
    }

    @Override
    public void eliminar(Marca marca) {
        sessionFactory.getCurrentSession().delete(marca);
    }




}
