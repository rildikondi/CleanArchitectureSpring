package com.akondi.usecases.get.gausssolutions;

import com.akondi.entities.GaussSolution;
import com.akondi.usecases.database.Database;
import com.akondi.usecases.database.inmemory.InMemoryDatabase;
import com.akondi.usecases.gausssolve.Clock;
import com.akondi.usecases.testdoubles.ClockStub;
import com.akondi.usecases.testdoubles.GaussSolutionsPresenterSpy;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

public class GetGaussSolutionsTest {
    private final long firstId = 0;
    private final long secondId = 1;
    private final String firstDate = LocalDate.of(2018, 01, 01).toString();
    private final String secondDate = LocalDate.of(2018, 01, 31).toString();
    private final String firstDocument = "{\"a\":[[2.0,4.0],[5.0,-6.0]],\"b\":[8.0,4.0]}";
    private final String secondDocument = "{\"a\":[[2.0,4.0],[5.0,-6.0]],\"b\":[8.0,4.0]}";
    private final String firstResult = "[2.0,1.0]";
    private final String secondResult = "[2.0,1.0]";
    private static final LocalDate CURRENT_DATE = LocalDate.of(2018, 01, 30);

    private Database database;
    private GetGaussSolutions useCase;
    private GaussSolutionsPresenterSpy presenterSpy;


    private void assertGaussSolution(long id, String date, String document, String result) {
        assertThat(presenterSpy.getGaussSolutionsResponse().getSolutions(),
                hasItem(both(hasProperty("id", equalTo(id)))
                        .and(hasProperty("date", equalTo(date)))
                        .and(hasProperty("document", equalTo(document)))
                        .and(hasProperty("result", equalTo(result)))
                )
        );
    }

    private void assertGaussSolutionPresented(int gaussSolutionSize) {
        assertThat(presenterSpy.getGaussSolutionsResponse(), is(not(nullValue())));
        assertThat(presenterSpy.getGaussSolutionsResponse().getSolutions(), hasSize(gaussSolutionSize));
        assertThat(presenterSpy.isGaussSolutionsPresented(), is(true));
    }

    private void saveGaussSolution(long id, String date, String document, String result) {
        database.gaussSolutionGateway().saveGaussSolution(new GaussSolution(id, date, document, result));
    }

    @Before
    public void setUp() {
        database = new InMemoryDatabase();
        Clock clock = new ClockStub(CURRENT_DATE);
        presenterSpy = new GaussSolutionsPresenterSpy();
        useCase = new GetGaussSolutions(presenterSpy, database.gaussSolutionGateway());
    }

    @Test
    public void no_gaussSolutions_returns_empty_response_list() {
        // WHEN
        useCase.execute(new GetSolutionsDataRequest());
        // THEN
        assertGaussSolutionPresented(0);
    }

    @Test
    public void can_get_gaussSolutions() {
        // GIVEN
        saveGaussSolution(firstId, firstDate, firstDocument, firstResult);
        saveGaussSolution(secondId, secondDate, secondDocument, secondResult);
        // WHEN
        useCase.execute(new GetSolutionsDataRequest());
        // THEN
        assertGaussSolutionPresented(2);
        assertGaussSolution(0, firstDate, firstDocument, firstResult);
        assertGaussSolution(1, secondDate, secondDocument, secondResult);
    }
}