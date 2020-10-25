package com.akondi.inmemorydb;

import com.akondi.inmemorydb.repositories.GaussSolutionRepository;
import com.akondi.ports.database.Database;
import com.akondi.ports.database.GaussSolutionGateway;


public class InMemoryDb implements Database {
    private GaussSolutionGateway gaussSolutionGateway;

    public InMemoryDb(GaussSolutionRepository gaussSolutionRepository) {
        this.gaussSolutionGateway = new InMemoryGaussSolutionGateway(gaussSolutionRepository);
    }

    @Override
    public GaussSolutionGateway gaussSolutionGateway() {
        return gaussSolutionGateway;
    }
}
