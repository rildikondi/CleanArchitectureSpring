package com.akondi.interfaceadapters.controllers.getgausssolutions;

import com.akondi.usecases.get.gausssolutions.GetGaussSolutionsInputBoundary;
import com.akondi.usecases.get.gausssolutions.GetSolutionsDataRequest;

public class GetGaussSolutionsController implements GetGaussSolutionsControllerInputBoundary {
    private final GetGaussSolutionsInputBoundary useCase;

    public GetGaussSolutionsController(GetGaussSolutionsInputBoundary useCase) {
        this.useCase = useCase;
    }

    @Override
    public void execute(GetSolutionsControllerRequest getSolutionsControllerRequest) {
        useCase.execute(new GetSolutionsDataRequest());
    }
}
