package com.drivedoctor.presentacion;

import com.drivedoctor.config.GoogleMapsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ControladorMaps {

    private GoogleMapsConfig googleMapsConfig;

    @Autowired
    public ControladorMaps(GoogleMapsConfig googleMapsConfig) {
        this.googleMapsConfig = googleMapsConfig;
    }

    @RequestMapping(value = "/showMap")
    public String showMap(Model model){
        String apikey = googleMapsConfig.getApiKey();
        model.addAttribute("apikey", apikey);
        return "showMap";
    }


}

