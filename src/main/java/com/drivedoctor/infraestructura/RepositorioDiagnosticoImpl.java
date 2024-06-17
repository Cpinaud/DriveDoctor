package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.RepositorioDiagnostico;
import com.drivedoctor.dominio.Sintoma;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


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

    //OBTIENE DIAGOSTICOS POR IDs
    @Override
    public List<Diagnostico> findByIds(List<Integer> idsDiagnostico) {
        return null;
    }


    @Override
    public List<Diagnostico> obtenerPorSintomasIds(List<Integer> idsSintomas) {
        String sql = "SELECT DISTINCT d FROM Diagnostico d JOIN d.sintomas s WHERE s.idSintoma IN (:idsSintomas) GROUP BY d HAVING COUNT(DISTINCT s.idSintoma) = :numSintomas";
        Query<Diagnostico> query = this.sessionFactory.getCurrentSession().createQuery(sql, Diagnostico.class);
        query.setParameter("idsSintomas", idsSintomas);
        query.setParameter("numSintomas", idsSintomas.size());
        return query.getResultList();
    }

    @Override
    public Diagnostico obtenerPorSintomaId(Integer idSintoma) {
        String sql = "SELECT s.diagnostico FROM Sintoma s WHERE s.idSintoma = :idSintoma";
        Query<Diagnostico> query = this.sessionFactory.getCurrentSession().createQuery(sql, Diagnostico.class);
        query.setParameter("idSintoma", idSintoma);
        return query.uniqueResult();
    }

}
