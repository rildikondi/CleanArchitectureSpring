package com.akondi.webapplication.endpoints.getsolutions;

import com.akondi.interfaceadapters.controllers.getgausssolutions.GetGaussSolutionsControllerInputBoundary;
import com.akondi.interfaceadapters.controllers.getgausssolutions.GetSolutionsControllerRequest;
import com.akondi.interfaceadapters.presenters.gausssolve.GaussSolutionsViewModelOutputBoundary;
import com.akondi.interfaceadapters.viewmodels.GaussSolutionsViewModel;
import com.akondi.webapplication.endpoints.BaseEndpoint;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/gausssolver")
public class GetGaussSolutionsEndpoint implements BaseEndpoint {

    private final GetGaussSolutionsControllerInputBoundary controller;
    private final GaussSolutionsViewModelOutputBoundary presenter;

    public GetGaussSolutionsEndpoint(
            GetGaussSolutionsControllerInputBoundary controller,
            GaussSolutionsViewModelOutputBoundary presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @GetMapping
    @ApiOperation(value = "Get solutions", response = GaussSolutionsViewModel.class)
    public ResponseEntity execute() {
        controller.execute(new GetSolutionsControllerRequest());

        return ResponseEntity.ok(presenter.getViewModel());
    }
}
