package com.akondi.usecases.get.gausssolutions;


import com.akondi.usecases.database.GaussSolutionGateway;

public class GetGaussSolutions extends GetGaussSolutionBase implements GetGaussSolutionsInputBoundary {
    private final GaussSolutionsOutputBoundary presenter;
    private final GaussSolutionGateway gaussSolutionGateway;

    public GetGaussSolutions(GaussSolutionsOutputBoundary presenter, GaussSolutionGateway gaussSolutionGateway) {
        this.presenter = presenter;
        this.gaussSolutionGateway = gaussSolutionGateway;
    }

    @Override
    public void execute(GetSolutionsDataRequest request) {
        GaussSolutionsResponse.GaussSolutionsResponseBuilder result = GaussSolutionsResponse.builder();
        gaussSolutionGateway.getAllGaussSolutionsData().forEach(solution -> result.addSolution(makeGaussSolutionResponse(solution)));

        presenter.present(result.build());
    }
}
