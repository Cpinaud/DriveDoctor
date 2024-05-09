package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ServicioSintoma;
import com.drivedoctor.dominio.Sintoma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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

    
    @RequestMapping("/crearSintoma")
    public ModelAndView crearSitnoma(){
        ModelMap modelo = new ModelMap();

        return null;
    }




}
