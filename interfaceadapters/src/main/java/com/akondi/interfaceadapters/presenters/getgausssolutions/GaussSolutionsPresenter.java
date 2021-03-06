package com.akondi.interfaceadapters.presenters.getgausssolutions;

import com.akondi.interfaceadapters.presenters.BaseGaussSolutionPresenter;
import com.akondi.usecases.get.gausssolutions.GaussSolutionsResponse;
import com.akondi.interfaceadapters.viewmodels.GaussSolutionsViewModel;
import com.akondi.usecases.get.gausssolutions.GaussSolutionsOutputBoundary;

public class GaussSolutionsPresenter extends BaseGaussSolutionPresenter
        implements GaussSolutionsOutputBoundary, GaussSolutionsPresenterOutputBoundary {

    private GaussSolutionsViewModel viewModel;

    @Override
    public GaussSolutionsViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void present(GaussSolutionsResponse responseModel) {
        GaussSolutionsViewModel.GaussSolutionsViewModelBuilder gaussSolutionsViewModelBuilder = GaussSolutionsViewModel.builder();
        responseModel.getSolutions()
                .stream()
                .map(BaseGaussSolutionPresenter::mapToGaussSolutionsViewModel)
                .forEach(gaussSolutionsViewModelBuilder::showSolutionsDataViewModel);
        viewModel = gaussSolutionsViewModelBuilder.build();
    }
}
