package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.*;
import com.drivedoctor.infraestructura.ServicioMarcaImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ControladorUsuario {

    private ServicioUsuario servicioUsuario;
    private ServicioMarca servicioMarca;

    public ControladorUsuario(ServicioUsuario servicioUsuario,ServicioMarca servicioMarca) {
        this.servicioUsuario = servicioUsuario;
        this.servicioMarca = servicioMarca;
    }

    @RequestMapping(path = "/buscarPorMarca", method = RequestMethod.GET)
    public ModelAndView redirectMisVh(HttpServletRequest request)
    {
        if (request == null || request.getSession() == null) {
        return new ModelAndView("home");
    }
        ModelMap model = new ModelMap();
        List<Marca> marcas = this.servicioMarca.obtenerMarcasAll();
        model.put("marcas", marcas);
        model.put("id",request.getSession().getAttribute("ID"));
        return new ModelAndView("redirect:/verMisVehiculos", model);

    }




    @RequestMapping(path = "/verMisVehiculos",method = RequestMethod.GET)
    public ModelAndView verMisVehiculos( HttpServletRequest request) throws ElementoNoEncontrado {

        if (request == null || request.getSession() == null) {
            return new ModelAndView("home");
        }

        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();

        Integer usuarioId= (Integer) request.getSession().getAttribute("ID");
        Usuario usuario = new Usuario();
        try {
            usuario = servicioUsuario.findById(usuarioId);
        } catch (ElementoNoEncontrado e) {
            model.put("mensaje", "El usuario no existe");
            return new ModelAndView(viewName, model);
        }
        List<Marca> marcas = this.servicioMarca.obtenerMarcasAll();
        model.put("marcas", marcas);
        model.put("id",request.getSession().getAttribute("ID"));
        if (request.getSession().getAttribute("ID")==null){
            return new ModelAndView("redirect:/login");
        }
        try{
            servicioUsuario.getMisVehiculos(usuario);
            model.put("vehiculos", servicioUsuario.getMisVehiculos(usuario));
        } catch (UsuarioSinVehiculos e) {
            model.put("mensaje", "El usuario no posee vehiculos aún");
        }

        return new ModelAndView(viewName, model);
    }


    @RequestMapping(path = "/buscarPorMarca",method = RequestMethod.POST)
    public ModelAndView verMisVhPorMarca(HttpServletRequest request,
                                         @RequestParam("marca") Integer marcaid,
                                         RedirectAttributes redirectAttributes) throws ElementoNoEncontrado {
        if (request == null || request.getSession() == null) {
            return new ModelAndView("home");
        }

        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        Integer usuarioId= (Integer) request.getSession().getAttribute("ID");
        Usuario usuario = new Usuario();
        try {
            usuario = servicioUsuario.findById(usuarioId);
        } catch (ElementoNoEncontrado e) {
            model.put("mensaje", "El usuario no existe");
            return new ModelAndView(viewName, model);
        }
        Marca marca = this.servicioMarca.findById(marcaid);
        List<Marca> marcas = this.servicioMarca.obtenerMarcasAll();
        model.put("marcas", marcas);
        model.put("id",request.getSession().getAttribute("ID"));
        try{
            servicioUsuario.getVhPorMarca(usuario,marca);
            model.put("vehiculos", servicioUsuario.getVhPorMarca(usuario,marca));
        } catch (UserSinVhByMarca e) {
            redirectAttributes.addFlashAttribute("mensaje", "El usuario no posee vehiculos de la marca solicitada");
            model.put("mensaje", "El usuario no posee vehiculos de la marca solicitada");
            return new ModelAndView("redirect:/verMisVehiculos", model);


        }

        return new ModelAndView(viewName, model);
    }

    @GetMapping("/adminVehiculos")
    public ModelAndView adminVehiculos(HttpServletRequest request){
        String rolU = "ADMIN";
        if (request == null || request.getSession() == null) {
            return new ModelAndView("home");
        }

        Object rol = request.getSession().getAttribute("rol");
        if (rol == null || !rol.equals(rolU)) {
            return new ModelAndView("home");
        }

        return new ModelAndView("adminVehiculos");

    }
}


