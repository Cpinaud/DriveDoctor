package com.drivedoctor.dominio;
import com.drivedoctor.dominio.excepcion.UsuarioExistente;

import java.util.List;

public interface RepositorioUsuario {

    Usuario buscarUsuario(String email, String password);
    void guardar(Usuario usuario) throws UsuarioExistente;

    Usuario buscar(String email);
    void modificar(Usuario usuario);

    Usuario buscarPorId(Integer usuarioId);

    List<Vehiculo> getMisVehiculos(Usuario usuario);

    List<Vehiculo> buscarVhPorMarca(Usuario usuario, Marca marca);

    /*List<Vehiculo> verMisVehiculos(Usuario usuario);
    void agregarVehiculo(Usuario usuario,Vehiculo vehiculo);*/
}

