package com.akondi.usecases.get.gausssolutions;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GaussSolutionsResponse {
    @Singular("addSolution") private List<GaussSolutionResponse> solutions;
}
