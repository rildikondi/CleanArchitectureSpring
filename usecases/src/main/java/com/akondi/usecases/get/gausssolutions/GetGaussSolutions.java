package com.akondi.usecases.get.gausssolutions;

import com.akondi.ports.database.GaussSolutionGateway;
import com.akondi.ports.presenters.GaussSolutionsOutputBoundary;
import com.akondi.ports.usescases.get.gausssolutions.GaussSolutionsResponse;
import com.akondi.ports.usescases.get.gausssolutions.GetGaussSolutionsInputBoundary;
import com.akondi.ports.usescases.get.gausssolutions.GetSolutionsDataRequest;

public class GetGaussSolutions extends GetGaussSolutionBase implements GetGaussSolutionsInputBoundary {
    private final GaussSolutionsOutputBoundary presenter;
    private final GaussSolutionGateway gaussSolutionGateway;

    public GetGaussSolutions(GaussSolutionsOutputBoundary presenter, GaussSolutionGateway gaussSolutionGateway) {
        this.presenter = presenter;
        this.gaussSolutionGateway = gaussSolutionGateway;
    }


    public void execute(GetSolutionsDataRequest request) {
        GaussSolutionsResponse.GaussSolutionsResponseBuilder result = GaussSolutionsResponse.builder();
        gaussSolutionGateway.getAllGaussSolutionsData().forEach(solution -> result.addSolution(makeGaussSolutionResponse(solution)));

        presenter.present(result.build());
    }
}
