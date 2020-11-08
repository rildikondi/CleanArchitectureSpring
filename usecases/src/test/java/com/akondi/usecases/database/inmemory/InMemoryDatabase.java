package com.akondi.usecases.database.inmemory;


import com.akondi.usecases.database.Database;
import com.akondi.usecases.database.GaussSolutionGateway;

public class InMemoryDatabase implements Database {
    private GaussSolutionGateway gaussSolutionGateway = new InMemoryGaussSolutionGateway();

    @Override
    public GaussSolutionGateway gaussSolutionGateway() {
        return gaussSolutionGateway;
    }
}
