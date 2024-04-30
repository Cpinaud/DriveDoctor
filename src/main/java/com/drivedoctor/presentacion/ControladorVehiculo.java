package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ServicioVehiculo;
import com.drivedoctor.dominio.Vehiculo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ControladorVehiculo {

    private ServicioVehiculo servicioVehiculo;

    public ControladorVehiculo(ServicioVehiculo servicioVehiculo) {
        this.servicioVehiculo = servicioVehiculo;
    }

    @RequestMapping(path = "/verVehiculos",method = RequestMethod.GET)
    public ModelAndView verVehiculos() {
        String viewName = "Vehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos",this.servicioVehiculo.verVehiculos());
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/verVehiculosPorMarca",method = RequestMethod.GET)
    public ModelAndView verVehiculosPorMarca(String marca) {
        String viewName = "Vehiculos";
        ModelMap model = new ModelMap();
        List<Vehiculo> vehiculos = this.servicioVehiculo.verVehiculosPorMarca(marca);
        model.put("vehiculos",vehiculos);
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/verVehiculoPorMarcaYModelo",method = RequestMethod.GET)
    public ModelAndView verVehiculoPorMarcaYModelo(String marca, String modelo) {
        String viewName = "Vehiculos";
        ModelMap model = new ModelMap();
        List<Vehiculo> vehiculos = this.servicioVehiculo.verVehiculoPorMarcaYModelo(marca,modelo);
        model.put("vehiculos",vehiculos);
        return new ModelAndView(viewName, model);
    }
}
