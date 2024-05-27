package com.drivedoctor.presentacion;

import com.drivedoctor.dominio.Marca;
import com.drivedoctor.dominio.ServicioVehiculo;
import com.drivedoctor.dominio.Usuario;
import com.drivedoctor.dominio.Vehiculo;
import com.drivedoctor.dominio.excepcion.UserSinPermiso;
import com.drivedoctor.dominio.excepcion.UsuarioExistente;
import com.drivedoctor.dominio.excepcion.UsuarioInexistente;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpLogging;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class ControladorVehiculo {

    private ServicioVehiculo servicioVehiculo;

    public ControladorVehiculo(ServicioVehiculo servicioVehiculo) {
        this.servicioVehiculo = servicioVehiculo;
    }


    @RequestMapping(path = "/nuevo-vehiculo", method = RequestMethod.GET)
    public ModelAndView nuevoVehiculo() {
        ModelMap model = new ModelMap();
        model.put("vehiculo", new Vehiculo());
        return new ModelAndView("nuevo-vehiculo", model);
    }

    @RequestMapping(path = "/agregarVehiculo", method = RequestMethod.POST)
    public ModelAndView agregarVehiculo(@ModelAttribute("vehiculo") Vehiculo vehiculo, HttpServletRequest request) {
        ModelMap model = new ModelMap();

        Long usuarioId= (Long) request.getSession().getAttribute("ID");

        try{
            servicioVehiculo.agregarVehiculo(usuarioId,vehiculo);
        } catch (UsuarioInexistente e){
            model.put("userName","Usuario Inexistente");
            return new ModelAndView("redirect:/Home");

        }

        return new ModelAndView("redirect:/verMisVehiculos");
    }


   @RequestMapping(path = "/verVehiculos",method = RequestMethod.GET)
       public ModelAndView verVehiculos(HttpServletRequest request) throws UserSinPermiso {
        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos",this.servicioVehiculo.verVehiculos(request));
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/buscarPorMarca",method = RequestMethod.POST)
    public ModelAndView buscarPorMarca(Marca marca) {
        String viewName = "misVehiculos";
        ModelMap model = new ModelMap();
        model.put("vehiculos", this.servicioVehiculo.getPorMarca(marca));
        return new ModelAndView(viewName, model);
    }

    @RequestMapping(path = "/pruebaScraping",method = RequestMethod.GET)
    public ModelAndView prueba() {
        String url = "https://www.dnrpa.gov.ar/portal_dnrpa/ada.php?marca-tipo-mod=true";  // URL del formulario
        String respuesta = null;
        String codMarca = null;
        try {
            // Conectar y obtener el documento HTML
            Document document = Jsoup.connect(url).get();

            // Obtener el elemento del formulario
            Element formMarca = document.select("form").last();
            String actionUrlMarca = formMarca.attr("action");  // Obtener la URL de acción del formulario
            String methodMarca = formMarca.attr("method");     // Obtener el método del formulario (GET o POST)

            if (actionUrlMarca.startsWith("/")) {
                actionUrlMarca = "https://www.dnrpa.gov.ar" + actionUrlMarca;
            }
            // Completar los datos del formulario
            Connection connection = Jsoup.connect(actionUrlMarca)
                    .data("forma", "descrip")
                    .data("dato", "fiat");

            // Enviar el formulario según el método
            Connection.Response response;
            if (methodMarca.equalsIgnoreCase("post")) {
                response = connection.method(Connection.Method.POST).execute();
            } else {
                response = connection.method(Connection.Method.GET).execute();
            }

            // Procesar la respuesta
            Document responseDocument = response.parse();

            String marca = responseDocument.select("option").text();
            codMarca = marca.substring(0, Math.min(marca.length(), 3));

            /*****HASTA ACA TENGO EL CÓDIGO DE LA MARCA INGRESADA****/
            

        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelMap model = new ModelMap();
        model.put("response", codMarca);
        return new ModelAndView("pruebaScraping", model);
    }


}
