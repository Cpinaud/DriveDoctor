package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ServicioUsuario;
import com.drivedoctor.dominio.Usuario;
import com.drivedoctor.dominio.Vehiculo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorUsuario {

    private ServicioUsuario servicioUsuario;

    public ControladorUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

   /* @RequestMapping(path = "/verMisVehiculos",method = RequestMethod.GET)
    public ModelAndView verMisVehiculos(Usuario usuario) {
        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos",this.servicioUsuario.verMisVehiculos(usuario));
        return new ModelAndView(viewName, model);
    }*/


}


