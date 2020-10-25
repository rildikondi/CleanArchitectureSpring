package com.akondi.ports.database;


import com.akondi.entities.GaussSolution;

import java.util.List;

public interface GaussSolutionGateway {
    public interface BadRequest {
    }

    public interface NotFound {
    }

    public void saveGaussSolution(GaussSolution gaussSolution);

    public List<GaussSolution> getAllGaussSolutionsData();

    public class EquationSystemBadDataException extends RuntimeException implements GaussSolutionGateway.BadRequest {
    }

    public class GaussSolutionsDataNotFoundException extends RuntimeException implements GaussSolutionGateway.NotFound {
    }
}
