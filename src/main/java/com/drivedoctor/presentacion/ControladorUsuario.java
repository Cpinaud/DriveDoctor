package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ServicioUsuario;
import com.drivedoctor.dominio.Usuario;
import com.drivedoctor.dominio.Vehiculo;
import com.drivedoctor.dominio.excepcion.UsuarioExistente;
import com.drivedoctor.dominio.excepcion.UsuarioSinVehiculos;
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

    public ControladorUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }





    @RequestMapping(path = "/verMisVehiculos",method = RequestMethod.GET)
    public ModelAndView verMisVehiculos( HttpServletRequest request) {
        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        Long usuarioId= (Long) request.getSession().getAttribute("ID");
        Usuario usuario = servicioUsuario.buscar(usuarioId);

        try{
            servicioUsuario.getMisVehiculos(usuario);
            model.put("vehiculos", servicioUsuario.getMisVehiculos(usuario));
        } catch (UsuarioSinVehiculos e) {
            model.put("mensaje", "El usuario no posee vehiculos aún");

        }

        return new ModelAndView(viewName, model);
    }


}


