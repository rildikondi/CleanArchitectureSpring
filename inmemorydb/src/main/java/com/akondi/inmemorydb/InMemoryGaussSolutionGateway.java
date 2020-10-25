package com.akondi.inmemorydb;

import com.akondi.entities.GaussSolution;
import com.akondi.inmemorydb.data.GaussSolutionData;
import com.akondi.inmemorydb.repositories.GaussSolutionRepository;
import com.akondi.ports.database.GaussSolutionGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InMemoryGaussSolutionGateway implements GaussSolutionGateway {
    private final GaussSolutionRepository gaussSolutionRepository;

    public InMemoryGaussSolutionGateway(GaussSolutionRepository gaussSolutionRepository) {
        this.gaussSolutionRepository = gaussSolutionRepository;
    }

    @Override
    public void saveGaussSolution(GaussSolution gaussSolution) {
        GaussSolutionData gaussSolutionData = new GaussSolutionData(
                gaussSolution.getId(),
                gaussSolution.getDate(),
                gaussSolution.getDocument(),
                gaussSolution.getResult()
        );
        gaussSolutionRepository.saveGaussSolution(gaussSolutionData);
    }

    @Override
    public List<GaussSolution> getAllGaussSolutionsData() {
        return gaussSolutionRepository.getAllGaussSolutions()
                .stream()
                .map(this::mapToGaussSolution)
                .collect(Collectors.toList());
    }

    private GaussSolution mapToGaussSolution(GaussSolutionData gaussSolutionData) {
        return new GaussSolution(
                gaussSolutionData.getId(),
                gaussSolutionData.getDate(),
                gaussSolutionData.getDocument(),
                gaussSolutionData.getResult()
        );
    }
}
