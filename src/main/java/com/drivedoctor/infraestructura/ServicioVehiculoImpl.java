package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.UserSinPermiso;
import com.drivedoctor.dominio.excepcion.UsuarioInexistente;
import com.drivedoctor.dominio.excepcion.UsuarioSinVehiculos;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import com.drivedoctor.dominio.RepositorioUsuario;
import com.drivedoctor.dominio.Usuario;

@Service
@Transactional
public class ServicioVehiculoImpl implements ServicioVehiculo {
    private RepositorioVehiculo repositorioVehiculo;
    private RepositorioUsuario repositorioUsuario;

    public ServicioVehiculoImpl(RepositorioVehiculo repositorioVehiculo,RepositorioUsuario repositorioUsuario) {
        this.repositorioVehiculo = repositorioVehiculo;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void agregarVehiculo(Integer usuarioId, Vehiculo vehiculo) throws UsuarioInexistente{
        Usuario usuario = repositorioUsuario.buscarPorId(usuarioId);
        if(usuario == null){
            throw new UsuarioInexistente();
        }
        vehiculo.setUsuario(usuario);
        if (usuario.getVehiculos() == null) {
            usuario.setVehiculos(new ArrayList<>());
        }
        usuario.getVehiculos().add(vehiculo);
        repositorioVehiculo.guardar(vehiculo);


    }



    @Override
    public List<Vehiculo> verVehiculos(HttpServletRequest request) throws UserSinPermiso {
        if (request.getSession().getAttribute("ROL")!="ADMIN"){
            throw new UserSinPermiso();
        }
        return repositorioVehiculo.getVehiculos();

    }

    @Override
    public List<Vehiculo> getPorMarca(Marca marca) {
       return repositorioVehiculo.getPorMarca(marca);
    }
}
