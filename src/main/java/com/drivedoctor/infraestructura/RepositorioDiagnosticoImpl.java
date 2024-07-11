package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.RepositorioDiagnostico;
import com.drivedoctor.dominio.Vehiculo;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

        return (Diagnostico) sessionFactory.getCurrentSession().createCriteria(Diagnostico.class)
                .add(Restrictions.eq("IdDiagnostico", idDiagnostico))
                .uniqueResult();
    }

    //OBTIENE DIAGOSTICOS POR IDs
    @Override
    public List<Diagnostico> findAll() {
        String hql = "From Diagnostico";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return  query.getResultList();
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
