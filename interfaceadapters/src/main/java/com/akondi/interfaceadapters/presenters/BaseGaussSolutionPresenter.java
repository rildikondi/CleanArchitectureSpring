package com.akondi.interfaceadapters.presenters;

import com.akondi.interfaceadapters.viewmodels.GaussSolutionViewModel;
import com.akondi.usecases.get.gausssolutions.GaussSolutionResponse;

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
