package com.akondi.usecases.testdoubles;

import com.akondi.usecases.gausssolve.GaussSolveOutputBoundary;
import com.akondi.usecases.gausssolve.GaussSolveResponse;

public class GaussSolvePresenterSpy implements GaussSolveOutputBoundary {
    private boolean isGaussSolvePresented;
    private GaussSolveResponse gaussSolveResponse;

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
