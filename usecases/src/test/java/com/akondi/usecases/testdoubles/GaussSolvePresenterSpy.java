package com.akondi.usecases.testdoubles;

import com.akondi.ports.presenters.GaussSolveOutputBoundary;
import com.akondi.ports.presenters.GaussSolveViewModel;
import com.akondi.ports.usescases.gausssolve.GaussSolveResponse;

public class GaussSolvePresenterSpy implements GaussSolveOutputBoundary {
    private boolean isGaussSolvePresented;
    private GaussSolveResponse gaussSolveResponse;

    @Override
    public GaussSolveViewModel getViewModel() {
        return null;
    }

    @Override
    public void present(GaussSolveResponse gaussSolveResponse) {
        isGaussSolvePresented = true;
        this.gaussSolveResponse = gaussSolveResponse;
    }

    public boolean isGaussSolvePresented() {
        return isGaussSolvePresented;
    }

    public GaussSolveResponse getGaussSolveResponse() {
        return gaussSolveResponse;
    }
}
