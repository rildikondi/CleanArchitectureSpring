package com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver;


public interface OutputBoundary {
     EquationSystemOutput formatData(EquationSystemOutput equationSystemOutput);

     void sendData(EquationSystemOutput equationSystemOutput);
}
