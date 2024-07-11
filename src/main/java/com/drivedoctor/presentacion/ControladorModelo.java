package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import com.drivedoctor.dominio.excepcion.ItemNoEncontrado;
import com.drivedoctor.dominio.excepcion.SintomaExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ControladorModelo {

    private ServicioModelo servicioModelo;
    private ServicioMarca servicioMarca;

    @Autowired
    public ControladorModelo(ServicioModelo servicioModelo, ServicioMarca servicioMarca) {
        this.servicioModelo = servicioModelo;
        this.servicioMarca = servicioMarca;
    }


    @GetMapping("/obtenerModelos")
    @ResponseBody
    public ResponseEntity<List<Modelo>> obtenerModelosPorMarca(@RequestParam("idMarca") Integer idMarca) {
        try {
            Marca marca = servicioMarca.findById(idMarca);
            List<Modelo> modelos = servicioModelo.obtenerModeloPorMarca(marca);
            return ResponseEntity.ok(modelos);
        } catch (ElementoNoEncontrado e) {
            // Manejo de la excepción cuando no se encuentra la marca
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }

    @RequestMapping("/nuevoModelo")
    public ModelAndView nuevoModelo(ModelMap model, HttpServletRequest request) {


        model.put("modelo", new Modelo());
        List<Marca> marcas = this.servicioMarca.obtenerMarcasAll();
        model.put("marcas", marcas);

        return new ModelAndView("nuevo-modelo", model);

    }


    @RequestMapping(path = "/crearModelo", method = RequestMethod.POST)
    public ModelAndView crearSintoma(@ModelAttribute("modelo") Modelo modelo,
                                     HttpServletRequest request,
                                     @RequestParam("idMarca") Integer idMarca) throws ElementoNoEncontrado, SintomaExistente {

        Marca marca = servicioMarca.findById(idMarca);
        if(marca == null) {
            throw new ElementoNoEncontrado();
        }
        modelo.setMarca(marca);

        servicioModelo.guardarModelo(modelo);




        return new ModelAndView("redirect:/adminVehiculos");
    }


}
