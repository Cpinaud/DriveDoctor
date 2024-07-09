package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.RepositorioSintoma;
import com.drivedoctor.dominio.Sintoma;
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

    //OBTIENE TODOS LOS SINTOMAS DE LA BD
    @Override
    public List<Sintoma> getAll() {

        if (sintomas == null) {
            cargarSintomasDesdeBD();
        }
        return this.sintomas;
    }

    @Override
    public Sintoma findByName(String nombre) {
        String sql = "From Sintoma s where s.nombre = :nombre";
        Query<Sintoma> query = this.sessionFactory.getCurrentSession().createQuery(sql, Sintoma.class);
        query.setParameter("nombre", nombre);
        return query.getSingleResult();
    }

    //OBTIENE UN SINTOMA POR SU ID
    @Override
    public Sintoma findById(Integer idSintoma) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Sintoma.class, idSintoma);
    }


    //OBTIENE LOS SINTOMAS DE VARIOS ITEMS
    @Override
    public List<Sintoma> obtenerPorItemsTablero(List<ItemTablero> items) {
        String sql = "FROM Sintoma WHERE itemTablero IN (:items)";
        Query<Sintoma> query = this.sessionFactory.getCurrentSession().createQuery(sql, Sintoma.class);
        query.setParameter("items", items);
        return query.getResultList();
    }

    @Override
    public List<Sintoma> obtenerPorIds(List<Integer> idSintomas) {
        return null;
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
