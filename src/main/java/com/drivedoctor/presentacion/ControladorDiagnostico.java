package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ServicioDiagnostico;
import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ControladorDiagnostico {

    private ServicioDiagnostico servicioDiagnostico;

    @Autowired
    public ControladorDiagnostico(ServicioDiagnostico servicioDiagnostico)
    {
        this.servicioDiagnostico = servicioDiagnostico;
    }

    //MUESTRA EL DIAGNOSTICO ASOCIADO A UN SINTOMA
    @GetMapping("/diagnostico/{id}")
    public String obtenerDiagnostico(@PathVariable("id") Integer id, Model model) {
        try {
            Diagnostico diagnostico = servicioDiagnostico.findById(id);
            model.addAttribute("diagnostico", diagnostico);
        } catch (DiagnosticoNotFoundException | IllegalArgumentException e) {
            model.addAttribute("mensaje", e);
        }
        return "mostrarDiagnostico";
    }
    //MUESTRA EL DIAGNOSTICO ASOCIADO HASTA 3 SINTOMAS
    @GetMapping("/diagnosticos")
    public String obtenerDiagnosticoPorSintomas(@RequestParam("idsSintomas") List<Integer> idsSintomas, Model model) {
        if(idsSintomas.size() <= 3) {
            List<Diagnostico> diagnosticos = servicioDiagnostico.findBySintomasIds(idsSintomas);
            if(diagnosticos.size() < 3) {
                model.addAttribute("diagnosticos", diagnosticos);
            }
        }
        return "mostrarDiagnostico";
    }

}
