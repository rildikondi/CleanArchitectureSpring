package com.akondi.entities;

public class EquationSystem {
    private final double[][] a;
    private final double[] b;

    public EquationSystem(double[][] a, double[] b) {
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
