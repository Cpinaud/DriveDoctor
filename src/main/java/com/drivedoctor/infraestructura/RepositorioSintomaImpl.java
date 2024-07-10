package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository("repositorioSintoma")
public class RepositorioSintomaImpl implements RepositorioSintoma {

    private SessionFactory sessionFactory;
    private List<Sintoma> sintomas;

    public RepositorioSintomaImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;
            this.sintomas = new ArrayList<>();}

    @Override
    public Sintoma buscar(ItemTablero itemTablero) {
        return null;
    }

    @Override
    public void guardar(Sintoma sintoma) {
        sessionFactory.getCurrentSession().save(sintoma);
    }

    @Override
    public void modificar(Sintoma sintoma) {
        sessionFactory.getCurrentSession().update(sintoma);
    }

    @Override
    public void eliminar(Sintoma sintoma) {
        sessionFactory.getCurrentSession().delete(sintoma);
    }


    //OBTIENE LOS SINTOMAS ASOCIADOS A UN ITEM
    @Override
    public List<Sintoma> obtenerPorItemTablero(ItemTablero itemTablero) {
          Integer idItemTablero = itemTablero.getIdItemTablero();
          System.out.println(idItemTablero);
        try {
            String sql = "From Sintoma s where s.itemTablero = :idItemTablero";
            Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
            query.setParameter("idItemTablero", itemTablero);
            List<Sintoma> sintomas = query.getResultList();
            System.out.println("Cantidad de síntomas encontrados: " + sintomas.size());
            return sintomas;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();

        }
    }

    @Override
    public List<Sintoma> getAll() {

        if (sintomas == null) {
            cargarSintomasDesdeBD();
        }
        return this.sintomas;
    }


    @Override
    public Sintoma findByName(String nombre) {
        return (Sintoma) sessionFactory.getCurrentSession().createCriteria(Sintoma.class)
                .add(Restrictions.eq("nombre", nombre))
                .uniqueResult();
    }

    //OBTIENE UN SINTOMA POR SU ID

    @Override
    public Sintoma findById(Integer idSintoma) {
        return (Sintoma) sessionFactory.getCurrentSession().createCriteria(Sintoma.class)
                .add(Restrictions.eq("id", idSintoma))
                .uniqueResult();
    }


    @Override
    public List<Sintoma> obtenerPorItemsTablero(List<ItemTablero> items) {
        String sql = "FROM Sintoma WHERE itemTablero IN (:items)";
        Query<Sintoma> query = this.sessionFactory.getCurrentSession().createQuery(sql, Sintoma.class);
        query.setParameter("items", items);
        return query.getResultList();
    }

    @Override
    public List<Sintoma> obtenerLosSintomasPorSusIds(List<Integer> idsSintomas) {
        String sql = "SELECT s From Sintoma s where s.idSintoma IN (:idsSintomas)";
        Query<Sintoma> query = this.sessionFactory.getCurrentSession().createQuery(sql, Sintoma.class);
        query.setParameter("idsSintomas", idsSintomas);
        return query.getResultList();
    }


    private void cargarSintomasDesdeBD() {
        String sql = "FROM Sintoma";
        Query<Sintoma> query = this.sessionFactory.getCurrentSession().createQuery(sql, Sintoma.class);
        sintomas = query.getResultList();
    }
}
