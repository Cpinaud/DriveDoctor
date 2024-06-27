package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.AllItemsEqual;
import com.drivedoctor.dominio.excepcion.DemasiadosSintomas;
import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;
import com.drivedoctor.dominio.excepcion.VehiculoInvalido;
import com.drivedoctor.infraestructura.ServicioDiagnosticoImpl;
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
    private ServicioItemTablero servicioItemTablero;

    @Autowired
    public ControladorDiagnostico(ServicioDiagnostico servicioDiagnostico, ServicioSintoma servicioSintoma,ServicioItemTablero servicioItemTablero)
    {
        this.servicioDiagnostico = servicioDiagnostico;
        this.servicioSintoma = servicioSintoma;
        this.servicioItemTablero = servicioItemTablero;
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
            if(diagnostico.size() == 0){
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
    public String obtenerDiagnosticoPorSintomas(@RequestParam("idsSintomas") String idsSintomasStr,
                                                @RequestParam("idVh") Integer idVehiculo,
                                                Model model) {
        logger.info("Llamada a /diagnosticos con idsSintomas: {}", idsSintomasStr);


        if (idsSintomasStr == null || idsSintomasStr.isEmpty()) {
            model.addAttribute("mensaje", "No se han seleccionado síntomas.");
            return "diagnosticos";
        }

        List<Integer> idsSintomas = Arrays.stream(idsSintomasStr.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        try{
            List<Diagnostico> devolucion = servicioDiagnostico.findDependingId(idsSintomas);
            model.addAttribute("diagnosticos", devolucion);
            if(devolucion.size() == 0){
                //throw new DiagnosticoNotFoundException("No se encuentra ningun diagnostico asociado a este id");
                model.addAttribute("mensaje", "No se encuentra ningun diagnostico asociado a este id");
                return "diagnosticos";
            }

        } catch (AllItemsEqual e) {
            List<ItemTablero> devolucion = new ArrayList<>();
            devolucion.add(servicioItemTablero.findById(servicioSintoma.findById(idsSintomas.get(0)).getItemTablero().getIdItemTablero()));
            model.addAttribute("diagnosticos", devolucion);

            return "diagnosticos";
        } catch (DemasiadosSintomas e) {
            //throw new DemasiadosSintomas("Demasiados sintomas acerquese a un taller");
            model.addAttribute("mensaje", "Demasiados sintomas acerquese a un taller.");
            return "diagnosticos";
        }
        ////armar lista de sintomas dependiendo idsSintomas
        model.addAttribute("idsSintomas", idsSintomas);
        model.addAttribute("idVh", idVehiculo);

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
