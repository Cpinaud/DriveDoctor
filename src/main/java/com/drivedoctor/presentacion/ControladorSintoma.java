package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.ServicioItemTablero;
import com.drivedoctor.dominio.ServicioSintoma;
import com.drivedoctor.dominio.Sintoma;
import com.drivedoctor.infraestructura.ServicioItemTableroImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ControladorSintoma {

    private ServicioSintoma servicioSintoma;
    private ServicioItemTablero servicioItemTablero;


    @Autowired
    public ControladorSintoma(ServicioSintoma servicioSintoma, ServicioItemTablero servicioItemTablero) {
        this.servicioSintoma = servicioSintoma;
        this.servicioItemTablero = servicioItemTablero;

    }




    @RequestMapping("/sintoma")
    public ModelAndView irASintoma(){
        ModelMap modelo = new ModelMap();
        modelo.put("sintoma", new Sintoma());
        return new ModelAndView("sintoma", modelo);

    }

    @RequestMapping("/nuevoSintoma")
    public ModelAndView nuevoSintoma() {
        ModelAndView modelAndView = new ModelAndView("nuevo-sintoma");
        List<ItemTablero> itemsTablero = servicioItemTablero.obtenerTodosLosItems();
        List<String> opcionesItemTablero = itemsTablero.stream()
                .map(ItemTablero::getNombre)
                .collect(Collectors.toList());
        modelAndView.addObject("opcionesItemTablero", opcionesItemTablero);
        modelAndView.addObject("sintoma", new Sintoma());
        return modelAndView;
    }


    
    @RequestMapping(value = "/crearSintoma", method = RequestMethod.POST)
    public ModelAndView crearSintoma(Sintoma sintomaMock){
        ModelMap modelo = new ModelMap();
        servicioSintoma.guardarSintoma(sintomaMock);


        return new ModelAndView("redirect:/sintoma");
    }

    @RequestMapping("/mostrarSintomaPorItem")
    public ModelAndView mostrarSintoma() {
        ModelAndView modelAndView = new ModelAndView("item-tablero");
        List<ItemTablero> itemsTablero = servicioItemTablero.obtenerTodosLosItems();

        modelAndView.addObject("opcionesItemTablero", itemsTablero);
        return modelAndView;
    }

    @RequestMapping("/mostrarSintomasPorItems")  //PROBLEM
    public ModelAndView mostrarSintomas() {
        ModelAndView modelAndView = new ModelAndView("items-tablero");
        List<ItemTablero> itemsTablero = servicioItemTablero.obtenerTodosLosItems();



        List<Sintoma> sintomas = servicioSintoma.findAll();
        System.out.println("sintomas = " + sintomas);


        modelAndView.addObject("opcionesItemsTablero", itemsTablero);
        modelAndView.addObject("opcionesSintomas", sintomas); // Agregar las opciones de los síntomas al modelo
        modelAndView.addObject("sintoma", new Sintoma());
        return modelAndView;
    }
    @RequestMapping(value = "/mostrarSintomaDependiendoItem", method = RequestMethod.POST )
    public ModelAndView mostrarSintomaDependiendoItem(@RequestParam("idItemTablero") Integer idItemTablero){

        ModelMap modelo = new ModelMap();
        ItemTablero itemTablero = servicioItemTablero.findById(idItemTablero);
       List<Sintoma> sintomas  = servicioSintoma.problemaEnTablero(itemTablero);
        System.out.println(sintomas);
       obtenerSintomas(sintomas, modelo);

        return new ModelAndView("mostrar-sintoma", modelo);


    }
    /* @RequestMapping(value = "/mostrarSintomasDependiendoItems", method = RequestMethod.POST )
    public ModelAndView mostrarSintomasDependiendoItems(@RequestParam("itemsTablero[]") String[] itemsTablero){
        ModelMap modelo = new ModelMap();


        List<ItemTablero> items = Arrays.stream(itemsTablero)
                .map(ItemTablero::getDescripcion)
                .collect(Collectors.toList());


        List<Sintoma> sintomas= servicioSintoma.problemasEnTablero(items);

        modelo.addAttribute("sintomas", sintomas);


        obtenerSintomas(sintomas, modelo);

        return new ModelAndView("mostrar-sintoma", modelo);


    } */

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
