package com.akondi.webapplication.endpoints.getsolutions;

import com.akondi.ports.presenters.GaussSolutionsOutputBoundary;
import com.akondi.ports.presenters.GaussSolutionsViewModel;
import com.akondi.ports.usescases.get.gausssolutions.GetGaussSolutionsInputBoundary;
import com.akondi.ports.usescases.get.gausssolutions.GetSolutionsDataRequest;
import com.akondi.webapplication.endpoints.BaseEndpoint;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/gausssolver")
public class GetGaussSolutionsEndpoint implements BaseEndpoint {
    private final GetGaussSolutionsInputBoundary useCase;
    private final GaussSolutionsOutputBoundary presenter;

    public GetGaussSolutionsEndpoint(GetGaussSolutionsInputBoundary useCase, GaussSolutionsOutputBoundary presenter) {
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
