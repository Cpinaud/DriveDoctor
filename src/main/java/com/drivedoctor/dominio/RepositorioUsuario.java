package com.drivedoctor.dominio;
import com.drivedoctor.dominio.excepcion.UsuarioExistente;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface RepositorioUsuario extends Busqueda<Usuario,Integer> {

    Usuario buscarUsuario(String email, String password);
    void guardar(Usuario usuario) throws UsuarioExistente;

    Usuario buscar(String email);
    void modificar(Usuario usuario);

    List<Vehiculo> getMisVehiculos(Usuario usuario);

    List<Vehiculo> buscarVhPorMarca(Usuario usuario, Marca marca);

}

