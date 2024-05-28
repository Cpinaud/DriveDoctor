package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import com.drivedoctor.dominio.excepcion.DiagnosticoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("servicioDiagnostico")
@Transactional
public class ServicioDiagnosticoImpl implements ServicioDiagnostico {

    private RepositorioDiagnostico repositorioDiagnostico;

    @Autowired
    public ServicioDiagnosticoImpl(RepositorioDiagnostico repositorioDiagnostico){
        this.repositorioDiagnostico = repositorioDiagnostico;
    }


    @Override
    public void guardarDiagnostico(Diagnostico diagnostico) {
        if (diagnostico == null) {
            throw new IllegalArgumentException("El diagnóstico no puede ser nulo");
        }
        repositorioDiagnostico.guardar(diagnostico);
    }

    @Override
    public Diagnostico findById(Integer idDiagnostico) {
        if (idDiagnostico == null) {
            throw new IllegalArgumentException("El ID del diagnóstico no puede ser nulo");
        }
        Diagnostico diagnostico = repositorioDiagnostico.findById(idDiagnostico);
        if (diagnostico == null) {
            throw new DiagnosticoNotFoundException("Diagnóstico no encontrado para el ID: " + idDiagnostico);
        }
        return diagnostico;
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

        if(itemTablero != null && !itemsProcesados.contains(itemTablero)) {
            switch (itemTablero) {
                case FRENOS:
                    riesgoTotal+= 20.0;
                    break;
                case MOTOR:
                    riesgoTotal += 60.0;
                    break;
                case FILTRO_GASOLINA:
                    riesgoTotal += 40.0;
                    break;
                case SUSPENSION:
                    riesgoTotal += 10.0;
                    break;
                case AIRBAG:
                    riesgoTotal += 40.0;
                    break;
                case EMBRAGUE:
                    riesgoTotal += 50.0;
                    break;
                case DIRECCION:
                    riesgoTotal += 20.0;
                    break;
                case ESTABILIDAD:
                    riesgoTotal += 5.0;
                    break;
                case SERVICE:
                    riesgoTotal += 40.0;
                    break;
                case EPC:
                    riesgoTotal += 20.0;
                    break;

            }
            // Asegura que el riesgo total no sea mayor que 100.0
            itemsProcesados.add(itemTablero);
        }
        }
        return Math.min(riesgoTotal, 100.0);
    }
}
