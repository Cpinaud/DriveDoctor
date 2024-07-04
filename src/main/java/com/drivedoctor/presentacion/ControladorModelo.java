package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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


}
