package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.*;
import com.drivedoctor.infraestructura.ServicioDiagnosticoImpl;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
                throw new DiagnosticoNotFoundException();
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
                                                Model model) throws ItemNoEncontrado {
        logger.info("Llamada a /diagnosticos con idsSintomas: {}", idsSintomasStr);
        Boolean flagItem = false;
        model.addAttribute("idVh", idVehiculo);
        if (idsSintomasStr == null || idsSintomasStr.isEmpty()) {
            model.addAttribute("mensaje", "No se han seleccionado síntomas.");
            return "diagnosticos";
        }

        List<Integer> idsSintomas = Arrays.stream(idsSintomasStr.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());




       /* String devolucion = servicioDiagnostico.findDependingId(idsSintomas);
        String mapaDiagnostico = "Demasiados sintomas acerquese a un taller";

        System.out.println(devolucion);
        model.addAttribute("diagnosticos", devolucion);
        model.addAttribute("idsSintomas", idsSintomas);

        if(devolucion.equals(mapaDiagnostico)){
            return "diagnosticoConMapa";
        }*/


        List<Sintoma> sintomas = idsSintomas.stream()
                .map(id -> servicioSintoma.findById(id))
                .collect(Collectors.toList());

        List<Diagnostico> devolucion = new ArrayList<>();
        try{
            devolucion = this.servicioDiagnostico.findDependingId(idsSintomas);
            model.addAttribute("diagnosticos", devolucion);
            if(devolucion.size() == 0){
                //throw new DiagnosticoNotFoundException("No se encuentra ningun diagnostico asociado a este id");
                model.addAttribute("mensaje", "No se encuentra ningun diagnostico asociado a este id");
                return "diagnosticos";
            }

        } catch (AllItemsEqual e) {
            List<ItemTablero> devolucion1 = new ArrayList<>();
            devolucion1.add(servicioItemTablero.findById(servicioSintoma.findById(idsSintomas.get(0)).getItemTablero().getIdItemTablero()));
            model.addAttribute("sintoma", sintomas);
            flagItem = true;
            model.addAttribute("flagItem", flagItem);
            model.addAttribute("diagnosticos", devolucion1);

            return "diagnosticos";
        } catch (DemasiadosSintomas e) {
            //throw new DemasiadosSintomas("Demasiados sintomas acerquese a un taller");
            model.addAttribute("mensaje", "Demasiados sintomas acerquese a un taller.");
            return "diagnosticos";
        }
        ////armar lista de sintomas dependiendo idsSintomas
        model.addAttribute("sintoma", sintomas);

        model.addAttribute("flagItem", flagItem);
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

    @RequestMapping(value = "/nuevoDiagnostico")
    public ModelAndView irDiagnostico(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String rolU = "ADMIN";
        if(!request.getSession().getAttribute("rol").equals(rolU)){
            return new ModelAndView("home");
        }
        ModelMap modelo = new ModelMap();
        modelo.put("diagnostico", new Diagnostico());
        return new ModelAndView("nuevo-diagnostico", modelo);
    }

    @RequestMapping(value = "/crearDiagnostico", method = RequestMethod.POST)
    public ModelAndView crearDiagnostico(@ModelAttribute("diagnostico") Diagnostico diagnostico, HttpServletRequest request) {
        servicioDiagnostico.guardarDiagnostico(diagnostico);
        return new ModelAndView("redirect:/sintoma");
    }


}
