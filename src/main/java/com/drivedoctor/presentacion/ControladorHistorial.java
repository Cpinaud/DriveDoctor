package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.ElementoNoEncontrado;
import com.drivedoctor.dominio.excepcion.UserSinPermiso;
import com.drivedoctor.dominio.excepcion.UsuarioSinVehiculos;
import com.drivedoctor.dominio.excepcion.VehiculoInvalido;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ControladorHistorial {
    private ServicioHistorial servicioHistorial;
    private ServicioVehiculo servicioVehiculo;

    @Autowired
    public ControladorHistorial(ServicioHistorial servicioHistorial, ServicioVehiculo servicioVehiculo)
    {
        this.servicioHistorial = servicioHistorial;
        this.servicioVehiculo = servicioVehiculo;
    }


    @RequestMapping(value = "/guardarDiagnostico", method = RequestMethod.POST)
    public ModelAndView guardarDiagnostico(HttpServletRequest request, Model model,
                                           @RequestParam("idVehiculo") Integer idVehiculo,
                                           @RequestParam("idSintoma") List<Integer> idSintomas,
                                           @RequestParam("idDiagnostico") List<Integer> idDiagnosticos) throws ElementoNoEncontrado {

        try{
            this.servicioVehiculo.validarVehiculoUser((Integer) request.getSession().getAttribute("ID"),idVehiculo);
        } catch (VehiculoInvalido e) {
            throw new RuntimeException(e);
        }

        this.servicioHistorial.agregarHistoria(idVehiculo,idSintomas,idDiagnosticos);

        return new ModelAndView("redirect:/historial/" + idVehiculo);
    }

    @RequestMapping(path = "/historial/{idVh}",method = RequestMethod.GET)
    public ModelAndView verHistorial( HttpServletRequest request,@PathVariable("idVh") Integer idVehiculo) throws ElementoNoEncontrado {
        try{
            this.servicioVehiculo.validarVehiculoUser((Integer) request.getSession().getAttribute("ID"),idVehiculo);
        } catch (VehiculoInvalido e) {
            throw new RuntimeException(e);
            //Agregar mensaje "No puede visualizar el historial de un vehiculo que no le pertenece"
        }
        Vehiculo vehiculo = servicioVehiculo.findById(idVehiculo);
        ModelMap model = new ModelMap();
        model.put("vehiculo", vehiculo);

        List<Historial> historial = servicioVehiculo.getHistoriales(idVehiculo);


        model.put("historial", historial);

        return new ModelAndView("historial", model);
    }
}
