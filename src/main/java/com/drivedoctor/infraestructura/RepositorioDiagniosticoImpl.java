package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.RepositorioDiagnostico;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


@Repository("repositorioDiagnostico")
public class RepositorioDiagniosticoImpl implements RepositorioDiagnostico {

    private SessionFactory sessionFactory;

    public RepositorioDiagniosticoImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    @Override
    public Diagnostico buscar(Integer diagnostico) {
        return null;
    }

    @Override
    public void guardar(Diagnostico diagnostico) {

    }

    @Override
    public Diagnostico findBySintoma(ItemTablero itemTablero) {
        String hql = "SELECT d FROM Diagnostico d JOIN d.sintoma s WHERE s.itemTablero = :itemTablero";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("itemTablero", itemTablero);
        return (Diagnostico) query.getSingleResult();
    }
}
