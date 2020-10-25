package com.akondi.usecases.gausssolve;

import com.akondi.entities.GaussSolution;
import com.akondi.ports.database.GaussSolutionGateway;
import com.akondi.ports.presenters.GaussSolveOutputBoundary;
import com.akondi.ports.usescases.Clock;
import com.akondi.ports.usescases.gausssolve.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GaussSolve implements GaussSolveInputBoundary {
    private final GaussSolveOutputBoundary presenter;
    private final GaussSolutionGateway gaussSolutionGateway;
    private final IdGenerator idGenerator = new IdGenerator();
    private final Clock clock;

    public GaussSolve(GaussSolveOutputBoundary presenter,
                      GaussSolutionGateway gaussSolutionGateway,
                      Clock clock) {
        this.presenter = presenter;
        this.gaussSolutionGateway = gaussSolutionGateway;
        this.clock = clock;
    }


    public void execute(GaussSolveRequest request) {

        double[] solution = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String document = objectMapper.writeValueAsString(request);
            solution = GaussSolver.solve(request.getA(), request.getB());
            String jsonResult = objectMapper.writeValueAsString(solution);
            gaussSolutionGateway.saveGaussSolution(new GaussSolution(
                            idGenerator.getNextId(),
                            clock.now().toString(),
                            document,
                            jsonResult
                    )
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        presenter.present(new GaussSolveResponse(solution));
    }
}
