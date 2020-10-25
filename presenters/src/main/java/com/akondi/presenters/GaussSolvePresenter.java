package com.akondi.presenters;

import com.akondi.ports.presenters.GaussSolveOutputBoundary;
import com.akondi.ports.presenters.GaussSolveViewModel;
import com.akondi.ports.usescases.gausssolve.GaussSolveResponse;

public class GaussSolvePresenter implements GaussSolveOutputBoundary {
    private GaussSolveViewModel viewModel;

    @Override
    public GaussSolveViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public void present(GaussSolveResponse responseModel) {
        viewModel = new GaussSolveViewModel(responseModel.getSolveResponse());
    }
}
