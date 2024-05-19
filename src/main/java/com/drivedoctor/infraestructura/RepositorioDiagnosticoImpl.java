package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.RepositorioDiagnostico;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;


@Repository("repositorioDiagnostico")
public class RepositorioDiagnosticoImpl implements RepositorioDiagnostico {

    private SessionFactory sessionFactory;

    public RepositorioDiagnosticoImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;}

    @Override
    public Diagnostico buscar(Integer diagnostico) {
        return null;
    }

    @Override
    public void guardar(Diagnostico diagnostico) {
            sessionFactory.getCurrentSession().save(diagnostico);
    }

    @Override
    public Diagnostico findById(Integer idDiagnostico) {


        return sessionFactory.getCurrentSession().get(Diagnostico.class, idDiagnostico);
    }


}
