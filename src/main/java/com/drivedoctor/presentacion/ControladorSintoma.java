package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import com.drivedoctor.dominio.excepcion.ItemNoEncontrado;
import com.drivedoctor.dominio.excepcion.SintomaExistente;
import com.drivedoctor.dominio.excepcion.VehiculoInvalido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorSintoma {

    private ServicioSintoma servicioSintoma;
    private ServicioItemTablero servicioItemTablero;
    private ServicioVehiculo servicioVehiculo;
    private ServicioDiagnostico servicioDiagnostico;


    @Autowired
    public ControladorSintoma(ServicioSintoma servicioSintoma, ServicioItemTablero servicioItemTablero,ServicioVehiculo servicioVehiculo, ServicioDiagnostico servicioDiagnostico) {
        this.servicioSintoma = servicioSintoma;
        this.servicioVehiculo = servicioVehiculo;
        this.servicioItemTablero = servicioItemTablero;
        this.servicioDiagnostico = servicioDiagnostico;

    }




    @GetMapping("/sintoma/{id}")
    public ModelAndView irASintoma(@PathVariable("id") Integer idVehiculo, HttpServletRequest request, RedirectAttributes redirectAttributes) throws ElementoNoEncontrado {

        Integer userId = (Integer) request.getSession().getAttribute("ID");
        try{
            this.servicioVehiculo.validarVehiculoUser(userId,idVehiculo);
        } catch (VehiculoInvalido e) {
            redirectAttributes.addFlashAttribute("mensaje", "No puede diagnosticar un vehiculo que no posee");
            return new ModelAndView("redirect:/verMisVehiculos");

        }
        ModelMap modelo = new ModelMap();
        modelo.put("sintoma", new Sintoma());

        modelo.put("patente", servicioVehiculo.findById(idVehiculo).getPatente());
        modelo.put("idVh", idVehiculo);
        return new ModelAndView("sintoma", modelo);

    }

    @GetMapping("/sintoma")
    public ModelAndView irASintoma(HttpServletRequest request, RedirectAttributes redirectAttributes){
        String rolU = "ADMIN";
        if(!request.getSession().getAttribute("rol").equals(rolU)){
            return new ModelAndView("home");
        }
        ModelMap modelo = new ModelMap();
        modelo.put("sintoma", new Sintoma());
        return new ModelAndView("sintoma", modelo);

    }

    @RequestMapping("/nuevoSintoma")
    public ModelAndView nuevoSintoma(ModelMap model, HttpServletRequest request) throws ItemNoEncontrado {

        model.put("sintoma", new Sintoma());
        List<ItemTablero> itemsTablero = this.servicioItemTablero.obtenerTodosLosItems();
        List<Diagnostico> diagnosticos = this.servicioDiagnostico.findAll();
        model.put("itemsTablero", itemsTablero);
        model.put("diagnosticos", diagnosticos);
        return new ModelAndView("nuevo-sintoma", model);

    }


    @RequestMapping(path = "/crearSintoma", method = RequestMethod.POST)
    public ModelAndView crearSintoma(@ModelAttribute("sintoma") Sintoma sintoma,
                                     HttpServletRequest request,
                                     @RequestParam("itemTablero") Integer idItemTablero,
                                     @RequestParam("diagnosticoId") Integer idDiagnostico) throws ItemNoEncontrado, ElementoNoEncontrado, SintomaExistente {
        System.out.println("Entró en crearSintoma");
        System.out.println(idDiagnostico);


            ItemTablero item = servicioItemTablero.findById(idItemTablero);
            if(item == null) {
                throw new ItemNoEncontrado();
            }
            sintoma.setItemTablero(item);

            Diagnostico diagnostico = servicioDiagnostico.findById(idDiagnostico);
            if(diagnostico == null) {
                throw new ItemNoEncontrado();
            }
            sintoma.setDiagnostico(diagnostico);

            servicioSintoma.guardarSintoma(sintoma);




        return new ModelAndView("redirect:/sintoma");
    }


    @GetMapping("/mostrarSintomaPorItem/{idVehiculo}")
    public ModelAndView mostrarSintoma(@PathVariable("idVehiculo") Integer idVehiculo) {
        ModelMap modelo = new ModelMap();

        List<ItemTablero> itemsTablero = servicioItemTablero.obtenerTodosLosItems();
        modelo.put("opcionesItemTablero",itemsTablero);
        modelo.put("idVehiculo",idVehiculo);
        return new ModelAndView("item-tablero",modelo);
    }

    @RequestMapping("/mostrarSintomasPorItems/{idVehiculo}")  //PROBLEM
    public ModelAndView mostrarSintomas(@PathVariable("idVehiculo") Integer idVehiculo) {

        ModelAndView modelAndView = new ModelAndView("items-tablero");
        List<ItemTablero> itemsTablero = servicioItemTablero.obtenerTodosLosItems();



        List<Sintoma> sintomas = servicioSintoma.findAll();
        System.out.println("sintomas = " + sintomas);


        modelAndView.addObject("opcionesItemsTablero", itemsTablero);
        //modelAndView.addObject("opcionesSintomas", sintomas); // Agregar las opciones de los síntomas al modelo
        modelAndView.addObject("sintoma", new Sintoma());
        modelAndView.addObject("idVehiculo",idVehiculo);
        return modelAndView;
    }
    @RequestMapping(value = "/mostrarSintomaDependiendoItem", method = {RequestMethod.GET, RequestMethod.POST} )
    public ModelAndView mostrarSintomaDependiendoItem(@RequestParam("idItemTablero") Integer idItemTablero,
                                                      @RequestParam("idVehiculo") Integer idVehiculo) throws ElementoNoEncontrado, ItemNoEncontrado {

        ModelMap modelo = new ModelMap();
        ItemTablero itemTablero = servicioItemTablero.findById(idItemTablero);
       List<Sintoma> sintomas  = servicioSintoma.problemaEnTablero(itemTablero);
        System.out.println(sintomas);
       obtenerSintomas(sintomas, modelo);
        modelo.put("idVehiculo",idVehiculo);
        return new ModelAndView("mostrar-sintoma", modelo);


    }
     @RequestMapping(value = "/mostrarSintomasDependiendoItems", method = RequestMethod.POST )
    public ModelAndView mostrarSintomasDependiendoItems(@RequestParam("itemsTablero[]") Integer[] itemsTablero,
                                                        HttpServletRequest request,
                                                        @RequestParam("idVehiculo") Integer idVehiculo,
                                                        RedirectAttributes redirectAttributes) throws ElementoNoEncontrado, ItemNoEncontrado {
         ModelMap modelo = new ModelMap();
        Integer userId = (Integer) request.getSession().getAttribute("ID");
         try{
             this.servicioVehiculo.validarVehiculoUser(userId,idVehiculo);
         } catch (VehiculoInvalido e) {
             modelo.addAttribute("mensaje", "No puede diagnosticar un vehiculo que no posee");
             return new ModelAndView("redirect:/verMisVehiculos");

         }


         List<ItemTablero> items = new ArrayList<>();
         for (Integer itemId : itemsTablero) {
             ItemTablero item = servicioItemTablero.findById(itemId);
             items.add(item);
         }
        //Esto tiene que ir en un método del servicio y agregar la opcion de buscar talleres en Maps
         if(items.size()>3){
             modelo.addAttribute("mensaje", "Las áreas seleccionadas son demasiadas, debe acercarse a un taller");
             return new ModelAndView("mostrar-sintomas", modelo);
         }
        //

         List<Sintoma> sintomas= servicioSintoma.problemasEnTablero(items);

        modelo.addAttribute("sintomas", sintomas);
        modelo.addAttribute("idVh", idVehiculo);


        obtenerSintomas(sintomas, modelo);

        return new ModelAndView("mostrar-sintomas", modelo);

    }




    private static void obtenerSintomas(List<Sintoma> sintomas, ModelMap modelo) {
        try {
            if (sintomas == null || sintomas.isEmpty()) {
                modelo.addAttribute("mensaje", "No se encontraron síntomas para el item seleccionado");
            } else {
                modelo.addAttribute("sintomas", sintomas);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
