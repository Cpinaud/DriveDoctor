package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorVehiculo {

    private ServicioVehiculo servicioVehiculo;
    private ServicioModelo servicioModelo;
    private ServicioMarca servicioMarca;

    public ControladorVehiculo(ServicioMarca servicioMarca,ServicioVehiculo servicioVehiculo,ServicioModelo servicioModelo) {
        this.servicioVehiculo = servicioVehiculo;
        this.servicioModelo = servicioModelo;
        this.servicioMarca = servicioMarca;
    }




    @RequestMapping(path = "/nuevo-vehiculo", method = RequestMethod.GET)
    public ModelAndView nuevoVehiculo(ModelMap model) {
        //ModelMap model = new ModelMap();
        model.put("vehiculo", new Vehiculo());
        List<Marca> marcas = this.servicioMarca.obtenerMarcasAll();
        model.put("marcas", marcas);
        return new ModelAndView("nuevo-vehiculo", model);
    }

    @RequestMapping(path = "/agregarVehiculo", method = RequestMethod.POST)
    public ModelAndView agregarVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo,
                                        HttpServletRequest request,
                                        @RequestParam("modeloId") Integer modeloId,
                                        RedirectAttributes redirectAttributes) {

        Modelo modelo = servicioModelo.getById(modeloId);
        vehiculo.setModelo(modelo);
        Marca marca = modelo.getMarca();

        vehiculo.setMarca(marca);
        ModelMap model = new ModelMap();

        Integer usuarioId= (Integer) request.getSession().getAttribute("ID");

        try{
            servicioVehiculo.agregarVehiculo(usuarioId,vehiculo);
        } catch (UsuarioInexistente e){
            return new ModelAndView("home");
        } catch (AnioInvalido e){
            redirectAttributes.addFlashAttribute("error", "El año del vehiculo debe ser mayor o igual a 2000");
            return new ModelAndView("redirect:/nuevo-vehiculo", model);
        } catch (PatenteInvalida e){
            redirectAttributes.addFlashAttribute("error", "El formato de la patente es inválido");
            return new ModelAndView("redirect:/nuevo-vehiculo", model);
        } catch (PatenteExistente e){
            redirectAttributes.addFlashAttribute("error", "Ya se ingresó un vehiculo con esa patente");
            return new ModelAndView("redirect:/nuevo-vehiculo", model);
        } catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Ha ocurrido un problema al ingresar el nuevo vehículo");
            return new ModelAndView("redirect:/nuevo-vehiculo", model);
        }

        return new ModelAndView("redirect:/verMisVehiculos");
    }


   @RequestMapping(path = "/verVehiculos",method = RequestMethod.GET)
       public ModelAndView verVehiculos(HttpServletRequest request) throws UserSinPermiso {
        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos",this.servicioVehiculo.verVehiculos(request));

        List<Marca> marcas = this.servicioMarca.obtenerMarcasAll();
        model.put("marcas", marcas);
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/buscarPorMarca",method = RequestMethod.POST)
    public ModelAndView buscarPorMarca(Marca marca) {
        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos", this.servicioVehiculo.getPorMarca(marca));
        return new ModelAndView(viewName, model);
    }




}
