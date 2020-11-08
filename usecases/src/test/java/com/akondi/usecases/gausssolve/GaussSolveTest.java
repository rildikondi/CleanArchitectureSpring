package com.akondi.usecases.gausssolve;

import com.akondi.entities.GaussSolution;
import com.akondi.usecases.database.Database;
import com.akondi.usecases.database.GaussSolutionGateway;
import com.akondi.usecases.database.inmemory.InMemoryDatabase;
import com.akondi.usecases.testdoubles.ClockStub;
import com.akondi.usecases.testdoubles.GaussSolvePresenterSpy;
import com.akondi.usecases.testdoubles.IdGeneratorStub;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class GaussSolveTest {
    private final long firstId = 0;
    private final long secondId = 1;
    private final String firstDate = LocalDate.of(2018, 01, 01).toString();
    private final String secondDate = LocalDate.of(2018, 01, 31).toString();
    private final String firstDocument = "{\"a\":[[2.0,4.0],[5.0,-6.0]],\"b\":[8.0,4.0]}";
    private final String secondDocument = "{\"a\":[[2.0,4.0],[5.0,-6.0]],\"b\":[8.0,4.0]}";
    private final String firstResult = "[2.0,1.0]";
    private final String secondResult = "[2.0,1.0]";
    private static final LocalDate CURRENT_DATE = LocalDate.of(2018, 01, 30);
    private final double[][] a = {{2.0, 4.0}, {5.0, -6.0}};
    private final double[] b = {8.0, 4.0};
    private final double[] solution = {2.0, 1.0};


    private Database database;
    private GaussSolve useCase;
    private GaussSolveRequest request;
    private GaussSolveOutputBoundary presenter;

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        presenter = new GaussSolvePresenterSpy();
        useCase = new GaussSolve(presenter, database.gaussSolutionGateway(), new ClockStub(CURRENT_DATE), new IdGeneratorStub());
        request = new GaussSolveRequest(a, b);
    }

    @Test
    public void can_solve_gaussSolution() {
        //GIVEN
        GaussSolvePresenterSpy presenterSpy = (GaussSolvePresenterSpy) presenter;
        // WHEN
        useCase.execute(request);
        // THEN
        assertThat(presenterSpy.isGaussSolvePresented(), equalTo(true));
        List<GaussSolution> gaussSolutions = database.gaussSolutionGateway().getAllGaussSolutionsData();
        assertThat(gaussSolutions, hasSize(1));
    }
}