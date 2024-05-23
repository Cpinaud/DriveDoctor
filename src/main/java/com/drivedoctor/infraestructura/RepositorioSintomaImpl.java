package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.RepositorioSintoma;
import com.drivedoctor.dominio.Sintoma;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
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
    public List<Sintoma> obtenerPorItemTablero(ItemTablero itemTablero) {
            System.out.println(itemTablero);
        try {
            String sql = "From Sintoma where itemTablero = :itemTablero";
            Query query = this.sessionFactory.getCurrentSession().createQuery(sql);
            query.setParameter("itemTablero", itemTablero);

            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
    @Override
    public List<Sintoma> getAll() {
        return this.sintomas;
    }

    @Override
    public List<Sintoma> obtenerPorItemsTablero(List<ItemTablero> items) {
        String sql = "FROM Sintoma WHERE itemTablero IN (:items)";
        Query query = this.sessionFactory.getCurrentSession().createQuery(sql);


            query.setParameter("items" ,items);


        return query.getResultList();
    }
}
