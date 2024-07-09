package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.ServicioItemTablero;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorItemTablero {

    private ServicioItemTablero servicioItemTablero;

    @Autowired
    public ControladorItemTablero(ServicioItemTablero servicioItemTablero) {
        this.servicioItemTablero = servicioItemTablero;
    }

    @RequestMapping(value = "/nuevoItemTablero")
    public ModelAndView irItemTablero(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String rolU = "ADMIN";
        if (!request.getSession().getAttribute("rol").equals(rolU)) {
            return new ModelAndView("home");
        }
        ModelMap modelo = new ModelMap();
        modelo.put("itemTablero", new ItemTablero());
        return new ModelAndView("nuevo-itemTablero", modelo);
    }

    @RequestMapping(value = "/crearItemTablero", method = RequestMethod.POST)
    public ModelAndView crearItemTablero(@ModelAttribute("itemTablero") ItemTablero itemTablero) {

        servicioItemTablero.guardarItemTablero(itemTablero);

        return new ModelAndView("redirect:/sintoma");
    }






}
