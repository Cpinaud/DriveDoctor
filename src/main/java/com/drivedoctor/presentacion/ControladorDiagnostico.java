package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ServicioDiagnostico;
import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            if(diagnostico == null){
                throw new DiagnosticoNotFoundException("No se encuentra ningun diagnostico asociado a este id");
            }
            model.addAttribute("diagnostico", diagnostico);
        } catch (DiagnosticoNotFoundException | IllegalArgumentException e) {
            model.addAttribute("mensaje", e.getMessage());
            return "error";
        }
        return "mostrarDiagnostico";
    }
    //MUESTRA EL DIAGNOSTICO ASOCIADO HASTA 3 SINTOMAS
    @GetMapping("/diagnosticos")
    public String obtenerDiagnosticoPorSintomas(@RequestParam("idsSintomas") List<Integer> idsSintomas, Model model) {
        if(idsSintomas.isEmpty() || idsSintomas == null  ) {
            model.addAttribute("mensaje", "No se han seleccionado síntomas.");
            return "diagnostico";
        }
            String devolucion= servicioDiagnostico.findDependingId(idsSintomas);
                model.addAttribute("diagnosticos", devolucion);
                model.addAttribute("idsSintomas", idsSintomas);

        return "redirect:/diagnostico/" + idsSintomas.stream().map(String::valueOf).collect(Collectors.joining(","));
    }
    @GetMapping("/diagnostico/{ids}")
    @ResponseBody
    public String obtenerDiagnosticoPorIds(@RequestParam("ids") List<Integer> idsSintomas) {
        return servicioDiagnostico.findDependingId(idsSintomas);
    }

}
