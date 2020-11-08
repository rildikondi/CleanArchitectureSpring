package com.akondi.interfaceadapters.presenters.gausssolve;

import com.akondi.interfaceadapters.presenters.BaseGaussSolutionPresenter;
import com.akondi.usecases.get.gausssolutions.GaussSolutionsResponse;
import com.akondi.interfaceadapters.viewmodels.GaussSolutionsViewModel;
import com.akondi.usecases.get.gausssolutions.GaussSolutionsOutputBoundary;

public class GaussSolutionsPresenter extends BaseGaussSolutionPresenter
        implements GaussSolutionsOutputBoundary, GaussSolutionsViewModelOutputBoundary {

    private GaussSolutionsViewModel viewModel;

    @Override
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
