package com.drivedoctor.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorVehiculo {

    private  List<Vehiculo> vehiculos;

    public ControladorVehiculo(List<Vehiculo> vehiculos) {

        this.vehiculos = vehiculos;
    }

    @RequestMapping(path = "/verVehiculos",method = RequestMethod.GET)
    public ModelAndView verVehiculos() {
        String viewName = "Vehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos",this.vehiculos);
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/verVehiculosPorMarca",method = RequestMethod.GET)
    public ModelAndView verVehiculosPorMarca(String marca) {
        String viewName = "Vehiculos";
        ModelMap model = new ModelMap();
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (Vehiculo vehiculo : this.vehiculos) {
            if(vehiculo.getMarca().equalsIgnoreCase(marca)){
                vehiculos.add(vehiculo);
            }
        }
        model.put("vehiculos",vehiculos);
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/verVehiculoPorMarcaYModelo",method = RequestMethod.GET)
    public ModelAndView verVehiculoPorMarcaYModelo(String marca, String modelo) {
        String viewName = "Vehiculos";
        ModelMap model = new ModelMap();
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (Vehiculo vehiculo : this.vehiculos) {
            if(vehiculo.getMarca().equalsIgnoreCase(marca) && vehiculo.getModelo().equalsIgnoreCase(modelo)){
                vehiculos.add(vehiculo);
            }
        }
        model.put("vehiculos",vehiculos);
        return new ModelAndView(viewName, model);
    }
}
