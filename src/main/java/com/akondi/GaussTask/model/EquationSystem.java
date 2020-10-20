package com.akondi.GaussTask.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class EquationSystem {
    @NotEmpty
    @Size(min = 2, max = 2)
    private final double[][] a;
    @NotEmpty
    @Size(min = 2, max = 2)
    private final double[] b;

    public EquationSystem(@JsonProperty("a") double[][] a,
                          @JsonProperty("b") double[] b) {
        this.a = a;
        this.b = b;
    }

    public double[][] getA() {
        return a;
    }

    public double[] getB() {
        return b;
    }
}
