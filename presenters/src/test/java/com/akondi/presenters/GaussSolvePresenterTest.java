package com.akondi.presenters;


import com.akondi.ports.presenters.GaussSolveViewModel;
import com.akondi.ports.usescases.gausssolve.GaussSolveResponse;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GaussSolvePresenterTest  {
    private final double[] solution = {2.0,1.0};

    @Test
    public void can_present_solution_after_solving_equations() {
        //GIVEN
        GaussSolvePresenter presenter = new GaussSolvePresenter();
        // WHEN
        presenter.present(new GaussSolveResponse(solution));
        // THEN
        GaussSolveViewModel viewModel = presenter.getViewModel();
        assertThat(viewModel, is(not(nullValue())));
        assertThat(solution, equalTo(viewModel.getSolution()));
    }

}