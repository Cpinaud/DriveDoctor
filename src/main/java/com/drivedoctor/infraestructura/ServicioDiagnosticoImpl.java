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
                    case ItemFreno:
                        riesgoTotal+= 20.0;
                        break;
                    case ItemMotor:
                        riesgoTotal += 60.0;
                        break;
                    case ItemFiltroGasolina:
                        riesgoTotal += 40.0;
                        break;
                    case ItemSuspension:
                        riesgoTotal += 10.0;
                        break;
                    case ItemAirbag:
                        riesgoTotal += 40.0;
                        break;
                    case ItemEmbrague:
                        riesgoTotal += 50.0;
                        break;
                    case ItemDireccion:
                        riesgoTotal += 20.0;
                        break;
                    case ItemEstabilidad:
                        riesgoTotal += 5.0;
                        break;
                    case ItemService:
                        riesgoTotal += 40.0;
                        break;
                    case ItemEPC:
                        riesgoTotal += 20.0;
                        break;
                    default:
                        return 0.0;
                }

                itemsProcesados.add(itemTablero);
            }
        }
            // Asegura que el riesgo total no sea mayor que 100.0
        return Math.min(riesgoTotal, 100.0);
    }

}
