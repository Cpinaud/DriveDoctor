package com.drivedoctor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class FormSubmitExample {
    public static void main(String[] args) {
        String url = "https://www.dnrpa.gov.ar/portal_dnrpa/ada.php?marca-tipo-mod=true";  // URL del formulario
        try {
            // Conectar y obtener el documento HTML
            Document document = Jsoup.connect(url).get();

            // Obtener el elemento del formulario
            Element form = document.select("form").first();
            String actionUrl = form.attr("action");  // Obtener la URL de acción del formulario
            String method = form.attr("method");     // Obtener el método del formulario (GET o POST)

            // Completar los datos del formulario
            Connection connection = Jsoup.connect(actionUrl)
                    .data("dato", "ford");

            // Enviar el formulario según el método
            Connection.Response response;
            if (method.equalsIgnoreCase("post")) {
                response = connection.method(Connection.Method.POST).execute();
            } else {
                response = connection.method(Connection.Method.GET).execute();
            }

            // Procesar la respuesta
            Document responseDocument = response.parse();
            System.out.println(responseDocument.body().text());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
