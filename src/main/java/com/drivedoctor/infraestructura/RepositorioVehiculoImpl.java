package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Marca;
import com.drivedoctor.dominio.RepositorioVehiculo;
import com.drivedoctor.dominio.Vehiculo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositorioVehiculoImpl implements RepositorioVehiculo {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioVehiculoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Vehiculo vehiculo) {
        sessionFactory.getCurrentSession().save(vehiculo);

    }

    @Override
    public List<Vehiculo> getVehiculos() {
        String hql = "FROM Vehiculo ";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public List<Vehiculo> getPorMarca(Marca marca) {
        List<Vehiculo> vehiculosPorMarca = new ArrayList<>();
        List<Vehiculo> vehiculos = this.getVehiculos();
        for (Vehiculo item: vehiculos) {
            if(item.getMarca().equals(marca)){
                vehiculosPorMarca.add(item);
            }
        }
        return vehiculosPorMarca;
    }
}
