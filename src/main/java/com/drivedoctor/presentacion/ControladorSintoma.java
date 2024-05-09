package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.ServicioSintoma;
import com.drivedoctor.dominio.Sintoma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller

public class ControladorSintoma {

    private ServicioSintoma servicioSintoma;

    @Autowired
    public ControladorSintoma(ServicioSintoma servicioSintoma){
        this.servicioSintoma = servicioSintoma;
    }




    @RequestMapping("/sintoma")
    public ModelAndView irASintoma(){
        ModelMap modelo = new ModelMap();
        modelo.put("sintoma", new Sintoma());
        return new ModelAndView("sintoma", modelo);

    }

    @RequestMapping("/nuevoSintoma")
    public ModelAndView nuevoSintoma() {
        ModelAndView modelAndView = new ModelAndView("nuevo-sintoma");
        List<String> opcionesItemTablero = Arrays.stream(ItemTablero.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        modelAndView.addObject("opcionesItemTablero", opcionesItemTablero);
        modelAndView.addObject("sintoma", new Sintoma());
        return modelAndView;
    }


    
    @RequestMapping(value = "/crearSintoma", method = RequestMethod.POST)
    public ModelAndView crearSintoma(Sintoma sintomaMock){
        ModelMap modelo = new ModelMap();
        servicioSintoma.guardarSintoma(sintomaMock);


        return new ModelAndView("redirect:/sintoma");
    }

    @RequestMapping("/mostrarSintomaPorItem")
    public ModelAndView mostrarSintoma() {
        ModelAndView modelAndView = new ModelAndView("item-tablero");
        List<String> opcionesItemTablero = Arrays.stream(ItemTablero.values())
                .map(ItemTablero::name) // Obtener el nombre de cada elemento del enum
                .collect(Collectors.toList());
        modelAndView.addObject("opcionesItemTablero", opcionesItemTablero);
        modelAndView.addObject("sintoma", new Sintoma());
        return modelAndView;
    }

    @RequestMapping(value = "/mostrarSintomaDependiendoItem", method = RequestMethod.POST )
    public ModelAndView mostrarSintomaDependiendoItem(ItemTablero itemTablero){
        ModelMap modelo = new ModelMap();
       List<Sintoma> sintomas  = servicioSintoma.problemaEnTablero(itemTablero);
       modelo.addAttribute("sintomas", sintomas);
       obtenerSintomas(sintomas, modelo);


        return new ModelAndView("mostrar-sintoma", modelo);


    }

    private static void obtenerSintomas(List<Sintoma> sintomas, ModelMap modelo) {
        try {
            if(sintomas == null|| sintomas.isEmpty()){
                modelo.addAttribute("mensaje", "No se encontraron sintomas para el item seleccionado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
