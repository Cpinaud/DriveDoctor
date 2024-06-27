package com.drivedoctor.infraestructura;
import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.UserSinVhByMarca;
import com.drivedoctor.dominio.excepcion.UsuarioExistente;
import com.drivedoctor.dominio.excepcion.UsuarioInexistente;
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

        return vehicDelUser;
    }

    @Override
    public Usuario buscar(Integer usuarioId) {

        return repositorioUsuario.buscarPorId(usuarioId);
    }

    @Override
    public List<Vehiculo> getVhPorMarca(Usuario usuario, Marca marca) throws UserSinVhByMarca {
        List<Vehiculo> vehicDelUser = repositorioUsuario.buscarVhPorMarca(usuario,marca);
        if(vehicDelUser.size()== 0){
            throw new UserSinVhByMarca();
        }
        return vehicDelUser;
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
