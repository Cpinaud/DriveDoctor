package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.ServicioDiagnostico;
import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/diagnostico/{id}")
    public String obtenerDiagnostico(@PathVariable("id") Integer id, Model model) {
        try {
            Diagnostico diagnostico = servicioDiagnostico.findById(id);
            model.addAttribute("diagnostico", diagnostico);
            return "mostrarDiagnostico";
        } catch (DiagnosticoNotFoundException e) {
            model.addAttribute("mensaje", "El diagnóstico no pudo ser encontrado");
            return "error";
        } catch (Exception e) {
            model.addAttribute("mensaje", "Se produjo un error al procesar la solicitud");
            return "error";
        }

    }
    @GetMapping("/diagnosticos")
    public String obtenerDiagnosticoPorSintomas(@RequestParam("idsSintomas") List<Integer> idsSintomas, Model model) {
        List<Diagnostico> diagnosticos = new ArrayList<>();
        for (Integer idSintoma : idsSintomas) {
            Diagnostico diagnostico = servicioDiagnostico.findById(idSintoma);
            if (diagnostico != null) {
                diagnosticos.add(diagnostico);
            }
        }
        model.addAttribute("diagnosticos", diagnosticos);
        return "mostrarDiagnostico";
    }

}
