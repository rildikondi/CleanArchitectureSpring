package com.akondi.presenters;

import com.akondi.ports.presenters.GaussSolutionsOutputBoundary;
import com.akondi.ports.presenters.GaussSolutionsViewModel;
import com.akondi.ports.usescases.get.gausssolutions.GaussSolutionsResponse;

public class GaussSolutionsPresenter extends BaseGaussSolutionPresenter implements GaussSolutionsOutputBoundary {
    private GaussSolutionsViewModel viewModel;

    public GaussSolutionsViewModel getViewModel() {
        return viewModel;
    }

    public void present(GaussSolutionsResponse responseModel) {
        GaussSolutionsViewModel.GaussSolutionsViewModelBuilder gaussSolutionsViewModelBuilder = GaussSolutionsViewModel.builder();
        responseModel.getSolutions()
                .stream()
                .map(BaseGaussSolutionPresenter::mapToGaussSolutionsViewModel)
                .forEach(gaussSolutionsViewModelBuilder::showSolutionsDataViewModel);
        viewModel = gaussSolutionsViewModelBuilder.build();

    }
}
