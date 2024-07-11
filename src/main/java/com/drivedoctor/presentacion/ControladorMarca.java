package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.ItemTablero;
import com.drivedoctor.dominio.Marca;
import com.drivedoctor.dominio.ServicioItemTablero;
import com.drivedoctor.dominio.ServicioMarca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;

@Controller
public class ControladorMarca {



        private ServicioMarca servicioMarca;

        @Autowired
        public ControladorMarca(ServicioMarca servicioMarca) {
            this.servicioMarca = servicioMarca;
        }

        @RequestMapping(value = "/nuevaMarca")
        public ModelAndView iraMarca(HttpServletRequest request) {
            String rolU = "ADMIN";
            if (!request.getSession().getAttribute("rol").equals(rolU)) {
                return new ModelAndView("home");
            }
            ModelMap modelo = new ModelMap();
            modelo.put("marca", new Marca());
            return new ModelAndView("nueva-marca", modelo);
        }

        @RequestMapping(value = "/crearMarca", method = RequestMethod.POST)
        public ModelAndView crearMarca(@ModelAttribute("marca") Marca marca) {

            servicioMarca.guardarMarca(marca);

            return new ModelAndView("redirect:/adminVehiculos");
        }






    }


