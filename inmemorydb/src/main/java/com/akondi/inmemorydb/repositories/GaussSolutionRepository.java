package com.akondi.inmemorydb.repositories;

import com.akondi.inmemorydb.data.GaussSolutionData;

import java.util.List;

public interface GaussSolutionRepository {
    void saveGaussSolution(GaussSolutionData gaussSolutionData);

    List<GaussSolutionData> getAllGaussSolutions();
}
