package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.ServicioSintoma;
import com.drivedoctor.dominio.Sintoma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    public ModelAndView nuevoSintoma(){
        ModelMap modelo = new ModelMap();
        modelo.put("sintoma", new Sintoma());
        return new ModelAndView("nuevo-sintoma", modelo);
    }

    
    @RequestMapping(value = "/crearSintoma", method = RequestMethod.POST)
    public ModelAndView crearSintoma(Sintoma sintomaMock){
        ModelMap modelo = new ModelMap();
        servicioSintoma.guardarSintoma(sintomaMock);


        return new ModelAndView("redirect:/sintoma");
    }

    @RequestMapping("/mostrarSintomaPorItem")
    public ModelAndView mostrarSintoma(){
        ModelMap modelo = new ModelMap();
        modelo.put("sintoma", new Sintoma());
        return new ModelAndView("item-tablero", modelo);
    }

    @RequestMapping(value = "/mostrarSintomaDependiendoItem", method = RequestMethod.POST )
    public ModelAndView mostrarSintomaDependiendoItem(ItemTablero itemTablero){
        ModelMap modelo = new ModelMap();
       List<Sintoma> sintomas  = servicioSintoma.problemaEnTablero(itemTablero);
       modelo.addAttribute("sintomas", sintomas);
       return new ModelAndView("mostrar-sintoma", modelo);


    }




}
