package com.akondi.GaussTask.service;

import com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver.GaussSolver;
import com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver.GaussSolverInput;
import com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver.GaussSolverRepository;
import com.akondi.GaussTask.enterprisebusinessrules.entity.EquationSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GaussSolverServiceImpl implements GaussSolverService {

    private final GaussSolverRepository gaussSolverRepository;

    @Autowired
    public GaussSolverServiceImpl(@Qualifier("inMemoryDb") GaussSolverRepository gaussSolverRepository) {
        this.gaussSolverRepository = gaussSolverRepository;
    }

    public double[] solveEquationSystem(EquationSystem equationSystem) {
        double[] solution = GaussSolver.solve(equationSystem.getA(), equationSystem.getB());
        gaussSolverRepository.save(equationSystem, solution);
        return solution;
    }

    public List<GaussSolverInput> getAllGaussSolverRequests() {
        return gaussSolverRepository.selectAllGaussSolverRequests();
    }
}
