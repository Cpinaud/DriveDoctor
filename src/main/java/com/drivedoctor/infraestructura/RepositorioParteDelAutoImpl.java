package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.ParteDelAuto;
import com.drivedoctor.dominio.RepositorioParteDelAuto;
import com.drivedoctor.dominio.Sintoma;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("repositorioParteDelAuto")
public class RepositorioParteDelAutoImpl implements RepositorioParteDelAuto {

    private SessionFactory sessionFactory;

    public RepositorioParteDelAutoImpl(SessionFactory sessionFactory){this.sessionFactory = sessionFactory;};

    @Override
    public void guardar(ParteDelAuto parteDelAuto) {
        sessionFactory.getCurrentSession().save(parteDelAuto);
    }


    @Override
    public ParteDelAuto obtenerParte(Integer idParteDelAuto)
    {
        return sessionFactory.getCurrentSession().get(ParteDelAuto.class, idParteDelAuto);
    }
}
