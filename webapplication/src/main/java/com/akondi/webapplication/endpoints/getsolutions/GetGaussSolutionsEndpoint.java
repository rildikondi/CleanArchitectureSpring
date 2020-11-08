package com.akondi.webapplication.endpoints.getsolutions;

import com.akondi.interfaceadapters.presenters.GaussSolutionsViewModelOutputBoundary;
import com.akondi.interfaceadapters.viewmodels.GaussSolutionsViewModel;
import com.akondi.usecases.get.gausssolutions.GetGaussSolutionsInputBoundary;
import com.akondi.usecases.get.gausssolutions.GetSolutionsDataRequest;
import com.akondi.webapplication.endpoints.BaseEndpoint;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/gausssolver")
public class GetGaussSolutionsEndpoint implements BaseEndpoint {
    private final GetGaussSolutionsInputBoundary useCase;
    private final GaussSolutionsViewModelOutputBoundary presenter;

    public GetGaussSolutionsEndpoint(
            GetGaussSolutionsInputBoundary useCase,
            GaussSolutionsViewModelOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @GetMapping
    @ApiOperation(value = "Get solutions", response = GaussSolutionsViewModel.class)
    public ResponseEntity execute() {
        useCase.execute(new GetSolutionsDataRequest());

        return ResponseEntity.ok(presenter.getViewModel());
    }
}
