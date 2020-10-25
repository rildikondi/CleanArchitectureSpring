package com.akondi.ports.presenters;

import com.akondi.ports.usescases.get.gausssolutions.GaussSolutionsResponse;

public interface GaussSolutionsOutputBoundary {
    GaussSolutionsViewModel getViewModel();
    void present(GaussSolutionsResponse responseModel);
}
