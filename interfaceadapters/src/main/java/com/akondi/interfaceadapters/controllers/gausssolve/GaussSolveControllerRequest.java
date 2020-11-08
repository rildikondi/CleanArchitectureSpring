package com.akondi.interfaceadapters.controllers.gausssolve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GaussSolveControllerRequest {
    private double[][] a;
    private double[] b;
}
