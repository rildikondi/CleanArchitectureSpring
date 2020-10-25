package com.akondi.ports.usescases.gausssolve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class GaussSolveRequest {
    private double[][] a;
    private double[] b;
}
