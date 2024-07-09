package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Historial;
import com.drivedoctor.dominio.RepositorioItemTablero;
import com.drivedoctor.dominio.ItemTablero;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
       Session session = sessionFactory.getCurrentSession();
        return session.get(ItemTablero.class, idItemTablero);
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
