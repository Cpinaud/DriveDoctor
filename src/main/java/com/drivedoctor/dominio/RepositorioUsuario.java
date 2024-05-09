package com.drivedoctor.dominio;
import java.util.List;

public interface RepositorioUsuario {

    Usuario buscarUsuario(String email, String password);
    void guardar(Usuario usuario);

    Usuario buscar(String email);
    void modificar(Usuario usuario);

    /*List<Vehiculo> verMisVehiculos(Usuario usuario);
    void agregarVehiculo(Usuario usuario,Vehiculo vehiculo);*/
}

