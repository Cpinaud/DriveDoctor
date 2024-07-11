package com.drivedoctor.infraestructura;
import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<Vehiculo> getVhPorMarca(Usuario usuario, Marca marca) throws UserSinVhByMarca {
        List<Vehiculo> vehicDelUser = repositorioUsuario.buscarVhPorMarca(usuario,marca);
        if(vehicDelUser.size()== 0){
            throw new UserSinVhByMarca();
        }
        return vehicDelUser;
    }

    @Override
    public Usuario findById(Integer id) throws ElementoNoEncontrado {
        Usuario usuario= repositorioUsuario.findById(id);
        if(usuario == null){
            throw new ElementoNoEncontrado();
        }
        return usuario;
    }


}
