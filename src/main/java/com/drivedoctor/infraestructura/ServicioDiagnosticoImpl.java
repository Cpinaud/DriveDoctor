package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.Diagnostico;
import com.drivedoctor.dominio.RepositorioDiagnostico;
import com.drivedoctor.dominio.ServicioDiagnostico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
