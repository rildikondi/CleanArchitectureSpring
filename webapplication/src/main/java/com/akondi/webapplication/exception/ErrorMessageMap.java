package com.akondi.webapplication.exception;

import com.akondi.usecases.database.GaussSolutionGateway;

import java.util.HashMap;
import java.util.Map;

public class ErrorMessageMap {
    private ErrorMessageMap() {}

    public static Map<Class, String> errors = new HashMap<>();

    static {
        errors.put(GaussSolutionGateway.EquationSystemBadDataException.class, "EquationSystem bad data");
        errors.put(GaussSolutionGateway.GaussSolutionsDataNotFoundException.class, "Cannot find any solutions data");
    }
}
