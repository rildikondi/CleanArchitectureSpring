package com.akondi.interfaceadapters.viewmodels;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GaussSolutionsViewModel {
    @Singular("showSolutionsDataViewModel") private List<GaussSolutionViewModel> solutionsData;
}

