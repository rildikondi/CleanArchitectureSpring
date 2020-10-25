package com.akondi.ports.usescases.get.gausssolutions;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GaussSolutionsResponse {
    @Singular("addSolution") private List<GaussSolutionResponse> solutions;
}
