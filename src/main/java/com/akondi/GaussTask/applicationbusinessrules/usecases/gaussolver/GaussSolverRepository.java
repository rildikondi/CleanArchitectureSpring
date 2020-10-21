package com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver;

import com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver.GaussSolverInput;
import com.akondi.GaussTask.enterprisebusinessrules.entity.EquationSystem;

import java.util.List;


public interface GaussSolverRepository {
    void save(EquationSystem equationSystem, double[] solution);

    List<GaussSolverInput> selectAllGaussSolverRequests();
}
