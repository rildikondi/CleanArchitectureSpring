package com.akondi.webapplication.endpoints.gausssolve;

import com.akondi.ports.presenters.GaussSolveOutputBoundary;
import com.akondi.ports.presenters.GaussSolveViewModel;
import com.akondi.ports.usescases.gausssolve.GaussSolveInputBoundary;
import com.akondi.ports.usescases.gausssolve.GaussSolveRequest;
import com.akondi.webapplication.endpoints.BaseEndpoint;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/api/v1/gausssolver")
@RestController
public class GaussSolveEndPoint implements BaseEndpoint {
    private final GaussSolveInputBoundary useCase;
    private final GaussSolveOutputBoundary presenter;

    @Autowired
    public GaussSolveEndPoint(GaussSolveInputBoundary useCase, GaussSolveOutputBoundary presenter) {
        this.useCase = useCase;
        this.presenter = presenter;
    }

    @PostMapping
    @ApiOperation(value = "solve", response = GaussSolveViewModel.class)
    public ResponseEntity execute(@Valid @RequestBody @NonNull NewGaussSolveRequest newGaussSolveRequest) {
        useCase.execute(
                GaussSolveRequest
                .builder()
                .a(newGaussSolveRequest.getA())
                .b(newGaussSolveRequest.getB())
                .build()
        );
        return ResponseEntity.ok(presenter.getViewModel());
    }
}
