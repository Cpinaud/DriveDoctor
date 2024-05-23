package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.ServicioSintoma;
import com.drivedoctor.dominio.Sintoma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public ModelAndView nuevoSintoma() {
        ModelAndView modelAndView = new ModelAndView("nuevo-sintoma");
        List<String> opcionesItemTablero = Arrays.stream(ItemTablero.values())
                .map(Enum::name)
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
        List<String> opcionesItemTablero = Arrays.stream(ItemTablero.values())
                .map(ItemTablero::name) // Obtener el nombre de cada elemento del enum
                .collect(Collectors.toList());
        modelAndView.addObject("opcionesItemTablero", opcionesItemTablero);
        modelAndView.addObject("sintoma", new Sintoma());
        return modelAndView;
    }

    @RequestMapping("/mostrarVariosSintomasPorVariosItem")
    public ModelAndView mostrarSintomas() {
        ModelAndView modelAndView = new ModelAndView("items-tablero");
        // Obtener las opciones de los elementos del tablero
        List<String> opcionesItemsTablero = Arrays.stream(ItemTablero.values())
                .map(ItemTablero::name) // Obtener el nombre de cada elemento del enum
                .collect(Collectors.toList());

        // Obtener las opciones de sintomas
        List<Sintoma> sintomas = servicioSintoma.obtenerSintomas();
        List<String> opcionesSintomas = sintomas.stream()
                .map(Sintoma::getNombre)
                .collect(Collectors.toList());

        modelAndView.addObject("opcionesItemsTablero", opcionesItemsTablero);
        modelAndView.addObject("opcionesSintomas", opcionesSintomas); // Agregar las opciones de los síntomas al modelo
        modelAndView.addObject("sintoma", new Sintoma());
        return modelAndView;
    }
    @RequestMapping(value = "/mostrarSintomaDependiendoItem", method = RequestMethod.POST )
    public ModelAndView mostrarSintomaDependiendoItem(ItemTablero itemTablero){
        ModelMap modelo = new ModelMap();
       List<Sintoma> sintomas  = servicioSintoma.problemaEnTablero(itemTablero);
       modelo.addAttribute("sintomas", sintomas);
       obtenerSintomas(sintomas, modelo);


        return new ModelAndView("mostrar-sintoma", modelo);


    }
    @RequestMapping(value = "/mostrarSintomasDependiendoItems", method = RequestMethod.POST )
    public ModelAndView mostrarSintomasDependiendoItems(@RequestParam("itemsTablero[]") String[] itemsTablero){
        ModelMap modelo = new ModelMap();

        // Convertir los items seleccionados a una lista de ItemTablero
        List<ItemTablero> items = Arrays.stream(itemsTablero)
                .map(ItemTablero::valueOf)
                .collect(Collectors.toList());

        // Obtener los síntomas basados en los items seleccionados
        List<Sintoma> sintomasResultantes = servicioSintoma.problemasEnTableros(items);

        // Agregar los síntomas al modelo
        modelo.addAttribute("sintomas", sintomasResultantes);

        // Realizar cualquier otra lógica necesaria
        obtenerSintomas(sintomasResultantes, modelo);

        // Devolver la vista con los datos actualizados
        return new ModelAndView("mostrar-sintoma", modelo);


    }

    private static void obtenerSintomas(List<Sintoma> sintomas, ModelMap modelo) {
        try {
            if(sintomas == null|| sintomas.isEmpty()){
                modelo.addAttribute("mensaje", "No se encontraron sintomas para el item seleccionado");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
