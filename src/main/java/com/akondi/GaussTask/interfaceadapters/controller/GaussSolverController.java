package com.akondi.GaussTask.interfaceadapters.controller;

import com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver.GaussSolverInput;
import com.akondi.GaussTask.enterprisebusinessrules.entity.EquationSystem;
import com.akondi.GaussTask.service.GaussSolverService;
import com.akondi.GaussTask.service.GaussSolverServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/v1/")
@RestController
public class GaussSolverController {
    private final GaussSolverServiceImpl gaussSolverService;

    @Autowired
    public GaussSolverController( GaussSolverServiceImpl gaussSolverService) {
        this.gaussSolverService = gaussSolverService;
    }

    @PostMapping
    public double[] solveEquationSystem(@Valid @RequestBody @NonNull EquationSystem equationSystem) {
        return gaussSolverService.solveEquationSystem(equationSystem);
    }

    @GetMapping
    public List<GaussSolverInput> getAllGaussSolverRequests() {
        return gaussSolverService.getAllGaussSolverRequests();
    }
}
