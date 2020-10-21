package com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver;


import com.akondi.GaussTask.enterprisebusinessrules.entity.EquationSystem;

public class GaussSolverInteractor implements InputBoundary {

    private GaussSolverInput gaussSolverInput;
    private final OutputBoundary outputBoundary;
    private final DataAccessInterface dataAccessInterface;

    public GaussSolverInteractor(OutputBoundary outputBoundary,
                                 DataAccessInterface dataAccessInterface) {
        this.outputBoundary = outputBoundary;
        this.dataAccessInterface = dataAccessInterface;
    }

    @Override
    public void receiveData(GaussSolverInput gaussSolverInput) {
        this.gaussSolverInput = gaussSolverInput;
    }

    public void getData() {
        EquationSystem equationSystem = dataAccessInterface.getEquationSystem();
        EquationSystemOutput equationSystemOutput = new EquationSystemOutput(equationSystem.getA(), equationSystem.getB());
        EquationSystemOutput formattedOutput = outputBoundary.formatData(equationSystemOutput);
        //outputBoundary.sendData(formattedData);
    }
}

