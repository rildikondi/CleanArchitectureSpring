package com.akondi.presenters;

import com.akondi.ports.presenters.GaussSolutionsViewModel;
import com.akondi.ports.usescases.get.gausssolutions.GaussSolutionResponse;
import com.akondi.ports.usescases.get.gausssolutions.GaussSolutionsResponse;
import org.junit.Test;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;


public class GaussSolutionsPresenterTest {
    private final long firstId = 0;
    private final long secondId = 1;
    private final String firstDate = LocalDate.of(2018, 01, 01).toString();
    private final String secondDate = LocalDate.of(2018, 01, 31).toString();
    private final String firstDocument = "{\"a\":[[2.0,4.0],[5.0,-6.0]],\"b\":[8.0,4.0]}";
    private final String secondDocument = "{\"a\":[[2.0,4.0],[5.0,-6.0]],\"b\":[8.0,4.0]}";
    private final String firstResult = "[2.0,1.0]";
    private final String secondResult = "[2.0,1.0]";


    private void assertGaussSolutionsViewModel(GaussSolutionsViewModel viewModel, long id, String date, String document, String result) {
        assertThat(viewModel.getSolutionsData(),
                hasItem(both(hasProperty("id", equalTo(id)))
                        .and(hasProperty("date", equalTo(date)))
                        .and(hasProperty("document", equalTo(document)))
                        .and(hasProperty("result", equalTo(result)))
                )
        );
    }

    @Test
    public void can_present_gaussSolutions() {
        // GIVEN
        GaussSolutionsPresenter presenter = new GaussSolutionsPresenter();
        GaussSolutionsResponse responseModel = GaussSolutionsResponse
                .builder()
                .addSolution(new GaussSolutionResponse(firstId, firstDate, firstDocument, firstResult))
                .addSolution(new GaussSolutionResponse(secondId, secondDate, secondDocument, secondResult))
                .build();
        // WHEN
        presenter.present(responseModel);

        // THEN
        GaussSolutionsViewModel viewModel = presenter.getViewModel();
        assertThat(viewModel, is(not(nullValue())));
        assertThat(viewModel.getSolutionsData(), hasSize(2));
        assertGaussSolutionsViewModel(viewModel, firstId, firstDate, firstDocument, firstResult);
        assertGaussSolutionsViewModel(viewModel, secondId, secondDate, secondDocument, secondResult);
    }
}