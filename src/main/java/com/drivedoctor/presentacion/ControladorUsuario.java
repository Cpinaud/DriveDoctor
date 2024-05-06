package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ServicioUsuario;
import com.drivedoctor.dominio.Usuario;
import com.drivedoctor.dominio.Vehiculo;
import com.drivedoctor.dominio.excepcion.UsuarioExistente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorUsuario {

    private ServicioUsuario servicioUsuario;

    public ControladorUsuario(ServicioUsuario servicioUsuario) {
        this.servicioUsuario = servicioUsuario;
    }

    @RequestMapping(path = "/verMisVehiculos",method = RequestMethod.GET)
    public ModelAndView verMisVehiculos() {
        String viewName = "Vehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos",this.servicioUsuario.verMisVehiculos());
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/nuevo-vehiculo", method = RequestMethod.GET)
    public ModelAndView nuevoUsuario() {
        ModelMap model = new ModelMap();
        model.put("vehiculo", new Vehiculo());
        return new ModelAndView("nuevo-vehiculo", model);
    }

    @RequestMapping(path = "/agregarVehiculo", method = RequestMethod.POST)
    public ModelAndView agregarVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        ModelMap model = new ModelMap();

            servicioUsuario.agregarVehiculo(vehiculo);

        return new ModelAndView("redirect:/verMisVehiculos");
    }
}


