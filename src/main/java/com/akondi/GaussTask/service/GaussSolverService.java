package com.akondi.GaussTask.service;

import com.akondi.GaussTask.dao.GaussSolverDao;
import com.akondi.GaussTask.model.EquationSystem;
import com.akondi.GaussTask.model.GaussSolverRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GaussSolverService {
    private final GaussSolverDao gaussSolverDao;

    @Autowired
    public GaussSolverService(@Qualifier("inMemoryDb") GaussSolverDao gaussSolverDao) {
        this.gaussSolverDao = gaussSolverDao;
    }

    public double[] solveEquationSystem(EquationSystem equationSystem) {
        return gaussSolverDao.solve(equationSystem);
    }

    public List<GaussSolverRequest> getAllGaussSolverRequests() {
        return gaussSolverDao.selectAllGaussSolverRequests();
    }
}
