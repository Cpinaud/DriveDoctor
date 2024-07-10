package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Historial;
import com.drivedoctor.dominio.RepositorioItemTablero;
import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.Sintoma;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RepositorioItemTableroImpl implements RepositorioItemTablero {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioItemTableroImpl(SessionFactory sessionFactory){

        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ItemTablero> findAll() {
        String hql = "FROM ItemTablero ";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public ItemTablero findById(Integer idItemTablero) {
        return (ItemTablero) sessionFactory.getCurrentSession().createCriteria(ItemTablero.class)
                .add(Restrictions.eq("idItemTablero", idItemTablero))
                .uniqueResult();
    }

    @Override
    public void guardar(ItemTablero itemTablero) {

        this.sessionFactory.getCurrentSession().save(itemTablero);
    }

    @Override
    public void modificar(ItemTablero itemTablero) {
        sessionFactory.getCurrentSession().update(itemTablero);
    }

    @Override
    public void eliminar(ItemTablero itemTablero) {
        sessionFactory.getCurrentSession().delete(itemTablero);
    }


}
