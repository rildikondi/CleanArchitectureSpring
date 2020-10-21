package com.akondi.GaussTask.frameworks.db;

import com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver.GaussSolverInput;
import com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver.GaussSolverRepository;
import com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver.IdGenerator;
import com.akondi.GaussTask.enterprisebusinessrules.entity.EquationSystem;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository("inMemoryDb")
public class GaussSolverDataInMemoryDb implements GaussSolverRepository {
   private static List<GaussSolverInput> DB = new ArrayList<>();

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public void save(EquationSystem equationSystem, double[] solution) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String document = objectMapper.writeValueAsString(equationSystem);
            String jsonResult = objectMapper.writeValueAsString(solution);
            DB.add(new GaussSolverInput(idGenerator.getNextId(), LocalDateTime.now().toString(), document,  jsonResult));
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<GaussSolverInput> selectAllGaussSolverRequests() {
        return DB;
    }
}
