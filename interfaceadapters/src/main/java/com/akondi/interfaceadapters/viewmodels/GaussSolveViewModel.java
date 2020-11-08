package com.akondi.interfaceadapters.viewmodels;


public class GaussSolveViewModel {
    private final double[] solution;

    public GaussSolveViewModel(double[] solution) {
        this.solution = solution;
    }

    public double[] getSolution() {
        return solution;
    }
}
