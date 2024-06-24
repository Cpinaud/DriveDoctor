package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.MarcaNoEncontrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
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
    public ResponseEntity<List<Modelo>> obtenerModelosPorMarca(@RequestParam("idMarca") Integer idMarca) throws MarcaNoEncontrada {
        Marca marca = servicioMarca.obtenerMarcaPorId(idMarca);
        List<Modelo> modelos =  servicioModelo.obtenerModeloPorMarca(marca);
        return ResponseEntity.ok(modelos);

    }

}
