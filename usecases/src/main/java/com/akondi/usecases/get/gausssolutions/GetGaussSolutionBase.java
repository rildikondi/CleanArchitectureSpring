package com.akondi.usecases.get.gausssolutions;

import com.akondi.entities.GaussSolution;
import com.akondi.ports.usescases.get.gausssolutions.GaussSolutionResponse;

public abstract class GetGaussSolutionBase {

    protected GetGaussSolutionBase() {}

    public static GaussSolutionResponse makeGaussSolutionResponse(GaussSolution gaussSolution) {
               return new GaussSolutionResponse(
                       gaussSolution.getId(),
                       gaussSolution.getDate(),
                       gaussSolution.getDocument(),
                       gaussSolution.getResult()
               );
    }
}
