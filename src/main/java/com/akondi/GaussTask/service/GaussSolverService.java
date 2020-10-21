package com.akondi.GaussTask.service;

import com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver.GaussSolverInput;
import com.akondi.GaussTask.enterprisebusinessrules.entity.EquationSystem;
import org.springframework.stereotype.Service;


import java.util.List;

public interface GaussSolverService {

    double[] solveEquationSystem(EquationSystem equationSystem) ;

    List<GaussSolverInput> getAllGaussSolverRequests();
}
