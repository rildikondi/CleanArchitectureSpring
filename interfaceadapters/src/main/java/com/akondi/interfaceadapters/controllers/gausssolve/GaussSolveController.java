package com.akondi.interfaceadapters.controllers.gausssolve;

import com.akondi.usecases.gausssolve.GaussSolveInputBoundary;
import com.akondi.usecases.gausssolve.GaussSolveRequest;

public class GaussSolveController implements GaussSolveControllerInputBoundary {
    private final GaussSolveInputBoundary useCase;

    public GaussSolveController(GaussSolveInputBoundary useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(GaussSolveControllerRequest gaussSolveControllerRequest) {
        useCase.execute(
                GaussSolveRequest
                        .builder()
                        .a(gaussSolveControllerRequest.getA())
                        .b(gaussSolveControllerRequest.getB())
                        .build()
        );
    }
}
