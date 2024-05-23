package com.drivedoctor.infraestructura;
import com.drivedoctor.dominio.RepositorioUsuario;
import com.drivedoctor.dominio.ServicioUsuario;
import com.drivedoctor.dominio.Usuario;
import com.drivedoctor.dominio.Vehiculo;
import com.drivedoctor.dominio.excepcion.UsuarioExistente;
import com.drivedoctor.dominio.excepcion.UsuarioSinVehiculos;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ServicioUsuarioImpl implements ServicioUsuario {


    private RepositorioUsuario repositorioUsuario;

    public ServicioUsuarioImpl(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public List<Vehiculo> getMisVehiculos(Usuario usuario) throws UsuarioSinVehiculos {
        List<Vehiculo> vehicDelUser = repositorioUsuario.getMisVehiculos(usuario);
        if(vehicDelUser.size()== 0){
            throw new UsuarioSinVehiculos();
        }
        return repositorioUsuario.getMisVehiculos(usuario);
    }

    @Override
    public Usuario buscar(Long usuarioId) {
        return repositorioUsuario.buscarPorId(usuarioId);
    }

    /*@Override
    public List<Vehiculo> verMisVehiculos(Usuario usuario) {
        return this.repositorioUsuario.verMisVehiculos(usuario);
        //this.vehiculos;
    }

    @Override
    public void agregarVehiculo(Usuario usuario, Vehiculo vehiculo) {
        usuario.getVehiculos().add(vehiculo);
        repositorioUsuario.guardar(usuario);
        repositorioUsuario.guardarV(vehiculo);

    }*/


}
