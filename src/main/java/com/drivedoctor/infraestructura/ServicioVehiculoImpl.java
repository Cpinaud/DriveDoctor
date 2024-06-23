package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.*;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.drivedoctor.dominio.RepositorioUsuario;
import com.drivedoctor.dominio.Usuario;
import com.drivedoctor.dominio.RepositorioVehiculo;
import com.drivedoctor.dominio.Vehiculo;

@Service("servicioVehiculo")
@Transactional
public class ServicioVehiculoImpl implements ServicioVehiculo {
    private RepositorioVehiculo repositorioVehiculo;
    private RepositorioUsuario repositorioUsuario;

    public ServicioVehiculoImpl(RepositorioVehiculo repositorioVehiculo,RepositorioUsuario repositorioUsuario) {
        this.repositorioVehiculo = repositorioVehiculo;
        this.repositorioUsuario = repositorioUsuario;
    }

    @Override
    public void agregarVehiculo(Integer usuarioId, Vehiculo vehiculo) throws UsuarioInexistente, AnioInvalido, PatenteInvalida,PatenteExistente {
        Usuario usuario = repositorioUsuario.buscarPorId(usuarioId);
        if(usuario == null){
            throw new UsuarioInexistente();
        }
        if(vehiculo.getAnoFabricacion()<2000){
            throw new AnioInvalido();
        }
        if (!vehiculo.getPatente().matches("^[a-zA-Z]{3}[0-9]{3}$|^[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{2}$")) {
            throw new PatenteInvalida();
        }
        Vehiculo vehiculoEncontrado = repositorioVehiculo.getByPatente(vehiculo.getPatente());
        if(vehiculoEncontrado != null){
            throw new PatenteExistente();
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

    @Override
    public Vehiculo buscarById(Integer idVehiculo) {
        return repositorioVehiculo.getById(idVehiculo);
    }

    @Override
    public void modificarVehiculo(Integer usuarioId, Integer idVehiculo, String patente, Integer anio) throws UsuarioInexistente, AnioInvalido, PatenteInvalida, PatenteExistente, VehiculoInexistente, vehiculoSinCambios {
        Usuario usuario = repositorioUsuario.buscarPorId(usuarioId);
        Vehiculo vehiculo = repositorioVehiculo.getById(idVehiculo);
        if(usuario == null){
            throw new UsuarioInexistente();
        }
        if(vehiculo == null){
            throw new VehiculoInexistente();
        }
        if(anio<2000){
            throw new AnioInvalido();
        }
        if (!patente.matches("^[a-zA-Z]{3}[0-9]{3}$|^[a-zA-Z]{2}[0-9]{3}[a-zA-Z]{2}$")) {
            throw new PatenteInvalida();
        }
        Vehiculo vehiculoEncontrado = repositorioVehiculo.getByPatente(patente);
        if(vehiculoEncontrado != null && !vehiculoEncontrado.getId().equals(vehiculo.getId())){
            throw new PatenteExistente();
        }

        if(vehiculo.getAnoFabricacion().equals(anio) && vehiculo.getPatente().equals(patente)){
            throw new vehiculoSinCambios();
        }
        vehiculo.setPatente(patente);
        vehiculo.setAnoFabricacion(anio);

        repositorioVehiculo.modificar(vehiculo);
    }

    @Override
    public Vehiculo buscarByPatente(String patente) throws VehiculoInexistente {
        Vehiculo vehiculo = repositorioVehiculo.getByPatente(patente);

        if(vehiculo == null){
            throw new VehiculoInexistente();
        }
        return vehiculo;
    }

    @Override
    public void validarVehiculoUser(Integer idVehiculo, Integer idVehiculoPorPatente, Integer userId) throws VehiculoInvalido {

        if(!Objects.equals(idVehiculo, idVehiculoPorPatente)){
            throw new VehiculoInvalido();
        }else{
            Vehiculo vehiculo = repositorioVehiculo.getById(idVehiculo);
            Integer usuarioVehiculo = vehiculo.getUsuario().getId();
            if(!Objects.equals(usuarioVehiculo, userId)){
                throw new VehiculoInvalido();
            }
        }
    }

    @Override
    public void eliminarVehiculo(Vehiculo vehiculo) {
        repositorioVehiculo.eliminar(vehiculo);
    }
}
