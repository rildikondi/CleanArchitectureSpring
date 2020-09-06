package com.akondi.GaussTask.dao;

import com.akondi.GaussTask.model.EquationSystem;
import com.akondi.GaussTask.model.GaussSolverRequest;

import java.util.List;

public interface GaussSolverDao {
    double[] solve(EquationSystem equationSystem);

    List<GaussSolverRequest> selectAllGaussSolverRequests();
}
