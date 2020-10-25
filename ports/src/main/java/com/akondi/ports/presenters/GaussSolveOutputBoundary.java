package com.akondi.ports.presenters;

import com.akondi.ports.usescases.gausssolve.GaussSolveResponse;

public interface GaussSolveOutputBoundary {
    GaussSolveViewModel getViewModel();
    void present(GaussSolveResponse responseModel);
}
