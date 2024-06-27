package com.drivedoctor.infraestructura;

import com.drivedoctor.dominio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ServicioHistorialImpl implements ServicioHistorial {


    private RepositorioHistorial repositorioHistorial;
    private RepositorioVehiculo repositorioVehiculo;
    private RepositorioDiagnostico repositorioDiagnostico;

    private RepositorioSintoma repositorioSintoma;

    @Autowired
    public ServicioHistorialImpl(RepositorioHistorial repositorioHistorial,
                                 RepositorioVehiculo repositorioVehiculo,
                                 RepositorioDiagnostico repositorioDiagnostico,
                                 RepositorioSintoma repositorioSintoma){
        this.repositorioHistorial = repositorioHistorial;
        this.repositorioVehiculo = repositorioVehiculo;
        this.repositorioDiagnostico =repositorioDiagnostico;
        this.repositorioSintoma =repositorioSintoma;
    }

    @Override
    public void agregarHistoria(Integer idVehiculo, List<Integer> idSintoma,
                                List<Integer>  idDiagnostico) {
        Vehiculo vehiculo = repositorioVehiculo.getById(idVehiculo);
        Historial historia = new Historial();
        historia.setVehiculo(vehiculo);
        List<Diagnostico> diagnosticos =  new ArrayList<>();
        for (Integer diagnostico : idDiagnostico) {
            diagnosticos.add(repositorioDiagnostico.findById(diagnostico));
        }
        historia.setDiagnosticos(diagnosticos);

        List<Sintoma> sintomas =  new ArrayList<>();
        for (Integer sintoma : idSintoma) {
            sintomas.add(repositorioSintoma.findById(sintoma));
        }
        historia.setSintomas(sintomas);
        historia.setFecha(new Date());

        repositorioHistorial.guardarHistoria(historia);

    }
}
