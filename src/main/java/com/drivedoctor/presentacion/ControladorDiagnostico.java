package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;
import com.drivedoctor.dominio.excepcion.VehiculoInvalido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorDiagnostico {

    private ServicioDiagnostico servicioDiagnostico;
    private static final Logger logger = LoggerFactory.getLogger(ControladorDiagnostico.class);
    private ServicioSintoma servicioSintoma;

    @Autowired
    public ControladorDiagnostico(ServicioDiagnostico servicioDiagnostico, ServicioSintoma servicioSintoma)
    {
        this.servicioDiagnostico = servicioDiagnostico;
        this.servicioSintoma = servicioSintoma;
    }

    //MUESTRA EL DIAGNOSTICO ASOCIADO A UN SINTOMA
    @RequestMapping(value = "/diagnostico", method = RequestMethod.POST )
    public String obtenerDiagnostico(@RequestParam("id") Integer id,
                                     @RequestParam("idVehiculo") Integer idVehiculo,
                                     @RequestParam("idsSintomas") Integer idsSintomas,
                                     HttpServletRequest request,
                                     Model model) throws VehiculoInvalido {
        try {
            List<Diagnostico> diagnostico = new ArrayList<>();
            diagnostico.add(servicioDiagnostico.findById(id));
            if(diagnostico == null){
                throw new DiagnosticoNotFoundException("No se encuentra ningun diagnostico asociado a este id");
            }
            model.addAttribute("diagnostico", diagnostico);
            model.addAttribute("idVehiculo", idVehiculo);
            List<Sintoma> sintoma = new ArrayList<>();
            sintoma.add(servicioSintoma.findById(idsSintomas));
            model.addAttribute("sintoma", sintoma);
        } catch (DiagnosticoNotFoundException | IllegalArgumentException e) {
            return "error";
        }
        return "mostrarDiagnostico";
    }

    @RequestMapping(value = "/diagnosticos", method = RequestMethod.POST )
    public String obtenerDiagnosticoPorSintomas(@RequestParam("idsSintomas") String idsSintomasStr, Model model) {
        logger.info("Llamada a /diagnosticos con idsSintomas: {}", idsSintomasStr);

        if (idsSintomasStr == null || idsSintomasStr.isEmpty()) {
            model.addAttribute("mensaje", "No se han seleccionado síntomas.");
            return "diagnosticos";
        }

        List<Integer> idsSintomas = Arrays.stream(idsSintomasStr.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<String> devolucion = servicioDiagnostico.findDependingId(idsSintomas);
        model.addAttribute("diagnosticos", devolucion);
        model.addAttribute("idsSintomas", idsSintomas);

        return "diagnosticos";
    }

    @RequestMapping(value = "/mostrarPorcentajeDeDaniadoUnSintoma", method = RequestMethod.POST)
    public String mostrarPorcentajeDeDañadoDeUnSintoma(@RequestParam("idsSintomas") List<Integer> idsSintomas, Model model){

       System.out.println(idsSintomas);
        try {
            List<Sintoma> sintomas = servicioSintoma.obtenerSintomasPorSuId(idsSintomas);
            System.out.println("La lista de sintomas es esta" + sintomas);
            double porcentajeDaniado = servicioDiagnostico.calcularRiesgoPorSintoma(sintomas);
            System.out.println("Porcentaje de dañado calculado: " + porcentajeDaniado);
            model.addAttribute("porcentajeDaniado", porcentajeDaniado);
            model.addAttribute("idsSintomas", idsSintomas);
            return "mostrarPorcentajeDeDaño";
        } catch(Exception e) {
            model.addAttribute("error", "Ocurrió un error al calcular el porcentaje de daño.");
            return "error";

        }

    }



}
