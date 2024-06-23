package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ServiceTaller;
import com.drivedoctor.dominio.Taller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/talleres")
public class ControladorTaller {

    private final ServiceTaller serviceTaller;


    @Autowired
    public ControladorTaller(ServiceTaller serviceTaller){
        this.serviceTaller = serviceTaller;

    }

    @GetMapping
    public List<Taller> getAllTalleres(){
        return serviceTaller.findAll();
    }
}
