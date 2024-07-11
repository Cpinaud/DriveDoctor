package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(path = "/eliminar-vehiculo", method = RequestMethod.POST)
    public ModelAndView eliminarVehiculo(ModelMap model,HttpServletRequest request,
                                         @RequestParam("idVehiculo") Integer idVehiculo,
                                         @RequestParam("patente") String patente,
                                         RedirectAttributes redirectAttributes) throws ElementoNoEncontrado {


        if (request.getSession().getAttribute("ID")==null){
            return new ModelAndView("redirect:/login");
        }
        Integer idVehiculoPorPatente = 0;
        try{
            idVehiculoPorPatente = servicioVehiculo.buscarByPatente(patente).getId();
        } catch (VehiculoInexistente e){

            return new ModelAndView("redirect:/verMisVehiculos");
        }
        Integer userId = (Integer) request.getSession().getAttribute("ID");

        try{
            servicioVehiculo.validarVehiculoUser(idVehiculo,idVehiculoPorPatente,userId);
        } catch (VehiculoInvalido e){
            return new ModelAndView("redirect:/verMisVehiculos");
        }
        Vehiculo vehiculo = servicioVehiculo.findById(idVehiculo);
        servicioVehiculo.eliminarVehiculo(vehiculo);

        return new ModelAndView("redirect:/verMisVehiculos");
    }

    @GetMapping("/modificar-vehiculo/{id}")
    public ModelAndView modificarVehiculo(ModelMap model,HttpServletRequest request,@PathVariable("id") Integer idVehiculo) throws ElementoNoEncontrado {
        if (request == null || request.getSession() == null) {
            return new ModelAndView("home");
        }
        Vehiculo vehiculo = servicioVehiculo.findById(idVehiculo);
        model.put("vehiculo", vehiculo);
        if (request.getSession().getAttribute("ID")==null){
            return new ModelAndView("redirect:/login");
        }
        model.put("id",request.getSession().getAttribute("ID"));
        return new ModelAndView("modificar-vehiculo", model);
    }

    @RequestMapping(path = "/editarVehiculo", method = RequestMethod.POST)
    public ModelAndView editarVehiculo(HttpServletRequest request,
                                        @RequestParam("anoFabricacion") Integer anio,
                                       @RequestParam("patente") String patente,
                                       @RequestParam("id") Integer idVehiculo,
                                        RedirectAttributes redirectAttributes) {

        if (request == null || request.getSession() == null) {
            return new ModelAndView("home");
        }
        ModelMap model = new ModelMap();
        Integer usuarioId= (Integer) request.getSession().getAttribute("ID");

        try{
            servicioVehiculo.modificarVehiculo(usuarioId,idVehiculo,patente,anio);
        } catch (UsuarioInexistente e){
            return new ModelAndView("home");
        } catch (AnioInvalido e){
            redirectAttributes.addFlashAttribute("error", "El año del vehiculo debe ser mayor o igual a 2000");
            return new ModelAndView("redirect:/modificar-vehiculo/" + idVehiculo, model);
        } catch (PatenteInvalida e){
            redirectAttributes.addFlashAttribute("error", "El formato de la patente es inválido");
            return new ModelAndView("redirect:/modificar-vehiculo/" + idVehiculo, model);
        } catch (PatenteExistente e){
            redirectAttributes.addFlashAttribute("error", "Ya se ingresó un vehiculo con esa patente");
            return new ModelAndView("redirect:/modificar-vehiculo/" + idVehiculo, model);
        } catch (VehiculoInexistente e) {
            redirectAttributes.addFlashAttribute("error", "El vehiculo no existe");
            return new ModelAndView("redirect:/modificar-vehiculo/" + idVehiculo, model);
        } catch (vehiculoSinCambios e) {
            redirectAttributes.addFlashAttribute("error", "No ha modificado ningún dato del vehiculo");
            return new ModelAndView("redirect:/modificar-vehiculo/" + idVehiculo, model);
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("error", "Ha ocurrido un problema al modificar el vehículo");
            return new ModelAndView("redirect:/modificar-vehiculo/" + idVehiculo, model);
        }

        return new ModelAndView("redirect:/verMisVehiculos");
    }


    @RequestMapping(path = "/nuevo-vehiculo", method = RequestMethod.GET)
    public ModelAndView nuevoVehiculo(ModelMap model,HttpServletRequest request) {
        if (request == null || request.getSession() == null) {
            return new ModelAndView("home");
        }

        model.put("vehiculo", new Vehiculo());
        List<Marca> marcas = this.servicioMarca.obtenerMarcasAll();
        model.put("marcas", marcas);
        model.put("id",request.getSession().getAttribute("ID"));
        return new ModelAndView("nuevo-vehiculo", model);
    }

    @RequestMapping(path = "/agregarVehiculo", method = RequestMethod.POST)
    public ModelAndView agregarVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo,
                                        HttpServletRequest request,
                                        @RequestParam("modeloId") Integer modeloId,
                                        RedirectAttributes redirectAttributes) throws ElementoNoEncontrado {

        if (request == null || request.getSession() == null) {
            return new ModelAndView("home");
        }
        ModelMap model = new ModelMap();

        try{
        Modelo modelo = servicioModelo.findById(modeloId);
        vehiculo.setModelo(modelo);
        Marca marca = modelo.getMarca();
        vehiculo.setMarca(marca);

        }catch (ElementoNoEncontrado e){
            redirectAttributes.addFlashAttribute("error", "La marca no existe");
            return new ModelAndView("redirect:/nuevo-vehiculo", model);
        }


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
       if (request == null || request.getSession() == null) {
           return new ModelAndView("home");
       }
        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos",this.servicioVehiculo.verVehiculos(request));

        List<Marca> marcas = this.servicioMarca.obtenerMarcasAll();
        model.put("marcas", marcas);
        return new ModelAndView(viewName, model);
    }


}
