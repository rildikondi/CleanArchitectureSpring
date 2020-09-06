package com.akondi.GaussTask.dao;

import com.akondi.GaussTask.GaussSolver;
import com.akondi.GaussTask.model.EquationSystem;
import com.akondi.GaussTask.model.GaussSolverRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository("inMemoryDb")
public class FakeGaussSolverDataAccessService implements GaussSolverDao  {
   private static List<GaussSolverRequest> DB = new ArrayList<>();

    @Autowired
    private IdGenerator idGenerator;

    @Override
    public double[] solve(EquationSystem equationSystem) {
        double[] solution = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String document = objectMapper.writeValueAsString(equationSystem);
            solution = GaussSolver.solve(equationSystem.getA(), equationSystem.getB());
            String jsonResult = objectMapper.writeValueAsString(solution);
            DB.add(new GaussSolverRequest(idGenerator.getNextId(), LocalDateTime.now().toString(), document,  jsonResult));
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return solution;
    }

    @Override
    public List<GaussSolverRequest> selectAllGaussSolverRequests() {
        return DB;
    }
}
