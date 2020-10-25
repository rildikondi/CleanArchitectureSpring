package com.akondi.ports.presenters;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GaussSolutionsViewModel {
    @Singular("showSolutionsDataViewModel") private List<GaussSolutionViewModel> solutionsData;
}

