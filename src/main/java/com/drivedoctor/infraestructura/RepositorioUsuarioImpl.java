package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.RepositorioUsuario;
import com.drivedoctor.dominio.Usuario;
import com.drivedoctor.dominio.Vehiculo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository("repositorioUsuario")
public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioUsuarioImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Usuario buscarUsuario(String email, String password) {

        final Session session = sessionFactory.getCurrentSession();
        return (Usuario) session.createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .add(Restrictions.eq("password", password))
                .uniqueResult();
    }

    @Override
    public void guardar(Usuario usuario) {
        sessionFactory.getCurrentSession().save(usuario);
    }



    @Override
    public Usuario buscar(String email) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("email", email))
                .uniqueResult();
    }

    @Override
    public void modificar(Usuario usuario) {
        sessionFactory.getCurrentSession().update(usuario);
    }

    @Override
    public Usuario buscarPorId(Long usuarioId) {
        return (Usuario) sessionFactory.getCurrentSession().createCriteria(Usuario.class)
                .add(Restrictions.eq("id", usuarioId))
                .uniqueResult();
    }

    @Override
    public List<Vehiculo> getMisVehiculos(Usuario usuario) {
        Long userid = usuario.getId();
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Vehiculo.class);
        criteria.add(Restrictions.eq("usuario", usuario));
        return criteria.list();
    }

    /*@Override
    public List<Vehiculo> verMisVehiculos(Usuario usuario) {
        return usuario.getVehiculos();

    }

    @Override
    public void agregarVehiculo(Usuario usuario, Vehiculo vehiculo) {
        // Asociar el vehículo al usuario
        usuario.agregarVehiculo(vehiculo);

        // Guardar los cambios en la base de datos
        sessionFactory.getCurrentSession().save(usuario);
        sessionFactory.getCurrentSession().save(vehiculo);
    }*/


}
