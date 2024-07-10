package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.AllItemsEqual;
import com.drivedoctor.dominio.excepcion.DemasiadosSintomas;
import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;
import com.drivedoctor.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service("servicioDiagnostico")
@Transactional
public class ServicioDiagnosticoImpl implements ServicioDiagnostico {

    private RepositorioDiagnostico repositorioDiagnostico;
    private RepositorioSintoma repositorioSintoma;


    @Autowired
    public ServicioDiagnosticoImpl(RepositorioDiagnostico repositorioDiagnostico, RepositorioSintoma repositorioSintoma){
        this.repositorioDiagnostico = repositorioDiagnostico;
        this.repositorioSintoma = repositorioSintoma;
    }


    @Override
    public void guardarDiagnostico(Diagnostico diagnostico) {
        if (diagnostico == null) {
            throw new IllegalArgumentException("El diagnóstico no puede ser nulo");
        }
        repositorioDiagnostico.guardar(diagnostico);
    }

    //TRAE EL DIAGNOSTICO POR SU ID
    @Override
    public Diagnostico findById(Integer idDiagnostico) {
        if (idDiagnostico == null) {
            throw new IllegalArgumentException("El ID del diagnóstico no puede ser nulo");
        }
        Diagnostico diagnostico = repositorioDiagnostico.findById(idDiagnostico);
        if (diagnostico == null) {
            throw new DiagnosticoNotFoundException();

        }
        return diagnostico;
    }

    //TRAE EL DIAGNOSTICO ASOCIADO A UN SINTOMA
    @Override
    public Diagnostico findBySintoma(Sintoma sintoma) {
        if (sintoma != null) {
            Optional<Diagnostico> diagnosticoOpt = Optional.ofNullable(sintoma.getDiagnostico());
            if (diagnosticoOpt.isPresent()) {
               Diagnostico diagnostico = diagnosticoOpt.get();
               return repositorioDiagnostico.findById(diagnostico.getIdDiagnostico());

            } else {
                throw new DiagnosticoNotFoundException();
            }
        }else {
            throw new IllegalArgumentException("El síntoma no puede ser nulo");
        }
    }

    //TRAE LOS DIAGNOSTICOS ASOCIADOS A LOS SINTOMAS
    @Override
    public List<Diagnostico> findBySintomas(List<Sintoma> sintomas) {
        if (sintomas.isEmpty()) {
            throw new IllegalArgumentException("La lista de síntomas no puede estar vacía");
        }
        List<Diagnostico> diagnosticos = new ArrayList<>();
        for (Sintoma sintoma : sintomas) {
            Optional<Diagnostico> diagnosticoOpt = Optional.ofNullable(sintoma.getDiagnostico());
            diagnosticoOpt.ifPresent(diagnosticos::add);
        }
        return diagnosticos;
    }
    //OBTIENE UN DIAGNOSTICO POR EL ID DEL SINTOMA
    @Override
    public Diagnostico findBySintomaId(Integer idSintoma) {
        return repositorioDiagnostico.obtenerPorSintomaId(idSintoma);
    }
    //OBTIENE LOS DIAGNOSTICOS POR EL IDs DE MAS SINTOMAS
    @Override
    public List<Diagnostico> findAll() {

        return repositorioDiagnostico.findAll();
    }

    @Override
    public Sintoma findByName(String name) {
        return repositorioSintoma.findByName(name);
    }


    @Override
    public double calcularRiesgoPorSintoma(List<Sintoma> sintomas) {
        if (sintomas == null || sintomas.isEmpty()) {
                throw new IllegalArgumentException("La lista de síntomas no puede ser nula o vacía");
            }
            double riesgoTotal = 0.0;

            Set<ItemTablero> itemsProcesados = new HashSet<>();

            for(Sintoma sintoma : sintomas){
            ItemTablero itemTablero = sintoma.getItemTablero();
            System.out.println(sintoma.getItemTablero());

            if(itemTablero != null && !itemsProcesados.contains(itemTablero)) {
                String nombreItemTablero = itemTablero.getNombre();
                System.out.println(nombreItemTablero);

                switch (nombreItemTablero) {
                    case "Freno":
                        System.out.println("freno");
                        riesgoTotal += 20.0;
                        break;
                    case "Motor":
                        riesgoTotal += 60.0;
                        break;
                    case "FiltroGasolina":
                        riesgoTotal += 40.0;
                        break;
                    case "Suspension":
                        riesgoTotal += 10.0;
                        break;
                    case "Airbag":
                        riesgoTotal += 40.0;
                        break;
                    case "Embrague":
                        riesgoTotal += 50.0;
                        break;
                    case "Direccion":
                        riesgoTotal += 20.0;
                        break;
                    case "Estabilidad":
                        riesgoTotal += 5.0;
                        break;
                    case "Service":
                        riesgoTotal += 40.0;
                        break;
                    case "EPC":
                        riesgoTotal += 20.0;
                        break;

                }

                itemsProcesados.add(itemTablero);
            }
        }
            // Asegura que el riesgo total no sea mayor que 100.0
        return Math.min(riesgoTotal, 100.0);
    }

    @Override
    public List<Diagnostico> findDependingId(List<Integer> idsSintoma) throws AllItemsEqual, DemasiadosSintomas {
        List<Diagnostico> diagnosticosList = new ArrayList<>();


        if(idsSintoma == null || idsSintoma.isEmpty() ){
            return null;
        }

        if(idsSintoma.size() == 1) {
            diagnosticosList.add(repositorioDiagnostico.obtenerPorSintomaId(idsSintoma.get(0)));
            return diagnosticosList;
        }

        List<Sintoma> sintomas = repositorioSintoma.obtenerLosSintomasPorSusIds(idsSintoma);


           ItemTablero item1 = sintomas.get(0).getItemTablero();
           boolean allItemsEqual = true;

            for(Sintoma sintoma : sintomas) {
                if (!item1.equals(sintoma.getItemTablero())) {
                    allItemsEqual = false;
                    break;
                }
            }

                if(allItemsEqual){
                    throw new AllItemsEqual();
                    //Devuelvo la descripcion del item en otro método

            } else {
                if(sintomas.size() <= 3 ) {
                        for (Sintoma sintoma : sintomas) {

                            diagnosticosList.add(sintoma.getDiagnostico());
                        }

                        return diagnosticosList;


                }
                    throw new DemasiadosSintomas("Demasiados sintomas acerquese a un taller");
            }

        }


}
