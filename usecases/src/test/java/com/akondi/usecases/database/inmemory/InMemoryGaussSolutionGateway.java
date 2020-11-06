package com.akondi.usecases.database.inmemory;

import com.akondi.entities.GaussSolution;
import com.akondi.ports.database.GaussSolutionGateway;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InMemoryGaussSolutionGateway implements GaussSolutionGateway {
    private List<GaussSolutionData> gaussSolutionList = new ArrayList<>();
    public InMemoryGaussSolutionGateway() {
    }

    @Override
    public void saveGaussSolution(GaussSolution gaussSolution) {
        GaussSolutionData gaussSolutionData = new GaussSolutionData(
                gaussSolution.getId(),
                gaussSolution.getDate(),
                gaussSolution.getDocument(),
                gaussSolution.getResult()
        );
        gaussSolutionList.add(gaussSolutionData);
    }

    @Override
    public List<GaussSolution> getAllGaussSolutionsData() {
        return gaussSolutionList
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
