package com.akondi.GaussTask.api;

import com.akondi.GaussTask.model.EquationSystem;
import com.akondi.GaussTask.model.GaussSolverRequest;
import com.akondi.GaussTask.service.GaussSolverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/")
@RestController
public class GaussSolverController {
    private final GaussSolverService gaussSolverService;

    @Autowired
    public GaussSolverController(GaussSolverService gaussSolverService) {
        this.gaussSolverService = gaussSolverService;
    }

    @PostMapping
    public double[] solveEquationSystem(@Valid @RequestBody @NonNull EquationSystem equationSystem) {
        return gaussSolverService.solveEquationSystem(equationSystem);
    }

    @GetMapping
    public List<GaussSolverRequest> getAllGaussSolverRequests() {
        return gaussSolverService.getAllGaussSolverRequests();
    }
}
