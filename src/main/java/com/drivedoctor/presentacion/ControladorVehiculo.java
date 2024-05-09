package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.Marca;
import com.drivedoctor.dominio.ServicioVehiculo;
import com.drivedoctor.dominio.Usuario;
import com.drivedoctor.dominio.Vehiculo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControladorVehiculo {

    private ServicioVehiculo servicioVehiculo;

    public ControladorVehiculo(ServicioVehiculo servicioVehiculo) {
        this.servicioVehiculo = servicioVehiculo;
    }


    @RequestMapping(path = "/nuevo-vehiculo", method = RequestMethod.GET)
    public ModelAndView nuevoVehiculo() {
        ModelMap model = new ModelMap();
        model.put("vehiculo", new Vehiculo());
        return new ModelAndView("nuevo-vehiculo", model);
    }

    @RequestMapping(path = "/agregarVehiculo", method = RequestMethod.POST)
    public ModelAndView agregarVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo) {
        ModelMap model = new ModelMap();

        servicioVehiculo.agregarVehiculo(vehiculo);

        return new ModelAndView("redirect:/verVehiculos");
    }

    @RequestMapping(path = "/verVehiculos",method = RequestMethod.GET)
    public ModelAndView verVehiculos() {
        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos",this.servicioVehiculo.verVehiculos());
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/buscarPorMarca",method = RequestMethod.POST)
    public ModelAndView buscarPorMarca(Marca marca) {
        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos", this.servicioVehiculo.getPorMarca(marca));
        return new ModelAndView(viewName, model);
    }
}
