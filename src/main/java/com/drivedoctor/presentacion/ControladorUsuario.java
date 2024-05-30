package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.UsuarioExistente;
import com.drivedoctor.dominio.excepcion.UsuarioSinVehiculos;
import com.drivedoctor.infraestructura.ServicioMarcaImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorUsuario {

    private ServicioUsuario servicioUsuario;
    private ServicioMarca servicioMarca;

    public ControladorUsuario(ServicioUsuario servicioUsuario,ServicioMarca servicioMarca) {
        this.servicioUsuario = servicioUsuario;
        this.servicioMarca = servicioMarca;
    }






    @RequestMapping(path = "/verMisVehiculos",method = RequestMethod.GET)
    public ModelAndView verMisVehiculos( HttpServletRequest request) {
        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        Integer usuarioId= (Integer) request.getSession().getAttribute("ID");
        Usuario usuario = servicioUsuario.buscar(usuarioId);
        List<Marca> marcas = this.servicioMarca.obtenerMarcasAll();
        model.put("marcas", marcas);
        try{
            servicioUsuario.getMisVehiculos(usuario);
            model.put("vehiculos", servicioUsuario.getMisVehiculos(usuario));
        } catch (UsuarioSinVehiculos e) {
            model.put("mensaje", "El usuario no posee vehiculos aún");

        }

        return new ModelAndView(viewName, model);
    }


}


