package com.akondi.presenters;

import com.akondi.ports.presenters.GaussSolutionViewModel;
import com.akondi.ports.usescases.get.gausssolutions.GaussSolutionResponse;

public class BaseGaussSolutionPresenter {

    protected BaseGaussSolutionPresenter() {
    }

    public static GaussSolutionViewModel mapToGaussSolutionsViewModel(GaussSolutionResponse gaussSolutionResponse) {
        return GaussSolutionViewModel
                .builder()
                .id(gaussSolutionResponse.getId())
                .date(gaussSolutionResponse.getDate())
                .document(gaussSolutionResponse.getDocument())
                .result(gaussSolutionResponse.getResult())
                .build();
    }
}
