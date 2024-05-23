package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ServicioDiagnostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class ControladorDiagnostico {

    private ServicioDiagnostico servicioDiagnostico;

    @Autowired
    public ControladorDiagnostico(ServicioDiagnostico servicioDiagnostico)
    {
        this.servicioDiagnostico = servicioDiagnostico;
    }

    @GetMapping("/diagnostico/{id}")
    public String obtenerDiagnostico(@PathVariable("id") Integer id, Model model)
    {
        Diagnostico diagnostico = servicioDiagnostico.findById(id);
        if(diagnostico != null) {
           model.addAttribute("diagnostico", diagnostico);
           return "mostrarDiagnostico";
        }else {
            model.addAttribute("mensaje", "No se encuuentra ningun diagnostico asociado a este sintoma");
            return "error";
        }


    }

}
