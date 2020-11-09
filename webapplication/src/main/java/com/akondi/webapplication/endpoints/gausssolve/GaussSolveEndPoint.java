package com.akondi.webapplication.endpoints.gausssolve;

import com.akondi.interfaceadapters.controllers.gausssolve.GaussSolveControllerInputBoundary;
import com.akondi.interfaceadapters.controllers.gausssolve.GaussSolveControllerRequest;
import com.akondi.interfaceadapters.presenters.gausssolve.GaussSolvePresenterOutputBoundary;
import com.akondi.interfaceadapters.viewmodels.GaussSolveViewModel;
import com.akondi.webapplication.endpoints.BaseEndpoint;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/api/v1/gausssolver")
@RestController
public class GaussSolveEndPoint implements BaseEndpoint {
    private final GaussSolveControllerInputBoundary controller;
    private final GaussSolvePresenterOutputBoundary presenter;

    public GaussSolveEndPoint(
            GaussSolveControllerInputBoundary controller,
            GaussSolvePresenterOutputBoundary presenter) {
        this.controller = controller;
        this.presenter = presenter;
    }

    @PostMapping
    @ApiOperation(value = "solve", response = GaussSolveViewModel.class)
    public ResponseEntity execute(@Valid @RequestBody @NonNull NewGaussSolveRequest newGaussSolveRequest) {
        controller.execute(GaussSolveControllerRequest.builder()
                .a(newGaussSolveRequest.getA())
                .b(newGaussSolveRequest.getB())
                .build()
        );
        return ResponseEntity.ok(presenter.getViewModel());
    }
}
