package com.akondi.usecases.testdoubles;

import com.akondi.usecases.get.gausssolutions.GaussSolutionsOutputBoundary;
import com.akondi.usecases.get.gausssolutions.GaussSolutionsResponse;

public class GaussSolutionsPresenterSpy  implements GaussSolutionsOutputBoundary {
    private boolean isGaussSolutionsPresented;
    private GaussSolutionsResponse gaussSolutionsResponse;

    @Override
    public void present(GaussSolutionsResponse gaussSolutionsResponse) {
        isGaussSolutionsPresented = true;
        this.gaussSolutionsResponse = gaussSolutionsResponse;
    }

    public boolean isGaussSolutionsPresented() {
        return isGaussSolutionsPresented;
    }

    public GaussSolutionsResponse getGaussSolutionsResponse() {
        return gaussSolutionsResponse;
    }

    public void setGaussSolutionsResponse(GaussSolutionsResponse gaussSolutionsResponse) {
        this.gaussSolutionsResponse = gaussSolutionsResponse;
    }
}
