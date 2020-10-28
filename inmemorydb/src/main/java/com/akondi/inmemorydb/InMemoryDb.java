package com.akondi.inmemorydb;

import com.akondi.ports.database.Database;
import com.akondi.ports.database.GaussSolutionGateway;


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
