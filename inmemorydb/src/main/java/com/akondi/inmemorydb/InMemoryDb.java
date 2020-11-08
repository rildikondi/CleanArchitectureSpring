package com.akondi.inmemorydb;

import com.akondi.usecases.database.Database;
import com.akondi.usecases.database.GaussSolutionGateway;

public class InMemoryDb implements Database {
    private GaussSolutionGateway gaussSolutionGateway;

    public InMemoryDb() {
        this.gaussSolutionGateway = new InMemoryGaussSolutionGateway();
    }

    @Override
    public GaussSolutionGateway gaussSolutionGateway() {
        return gaussSolutionGateway;
    }
}
