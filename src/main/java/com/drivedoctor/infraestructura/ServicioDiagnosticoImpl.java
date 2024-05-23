package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
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
        repositorioDiagnostico.guardar(diagnostico);
    }

    @Override
    public Diagnostico findById(Integer idDiagnostico) {
        
        return repositorioDiagnostico.findById(idDiagnostico);
    }

    @Override
    public double calcularRiesgoPorSintoma(List<Sintoma> sintomas) {
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
            itemsProcesados.add(itemTablero);
        }
        }
        if(riesgoTotal > 100.0){
            riesgoTotal = 100.0;
        }
        return riesgoTotal;
    }
}
