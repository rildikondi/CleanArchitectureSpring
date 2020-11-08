package com.akondi.applicationdi;

import com.akondi.inmemorydb.InMemoryDb;
import com.akondi.interfaceadapters.controllers.gausssolve.GaussSolveController;
import com.akondi.interfaceadapters.controllers.gausssolve.GaussSolveControllerInputBoundary;
import com.akondi.interfaceadapters.controllers.getgausssolutions.GetGaussSolutionsController;
import com.akondi.interfaceadapters.controllers.getgausssolutions.GetGaussSolutionsControllerInputBoundary;
import com.akondi.usecases.database.Database;
import com.akondi.usecases.get.gausssolutions.GaussSolutionsOutputBoundary;
import com.akondi.usecases.gausssolve.GaussSolveOutputBoundary;
import com.akondi.usecases.gausssolve.Clock;
import com.akondi.usecases.gausssolve.GaussSolveInputBoundary;
import com.akondi.usecases.gausssolve.IdGenerator;
import com.akondi.usecases.get.gausssolutions.GetGaussSolutionsInputBoundary;
import com.akondi.interfaceadapters.presenters.gausssolve.GaussSolutionsPresenter;
import com.akondi.interfaceadapters.presenters.getgausssolutions.GaussSolvePresenter;
import com.akondi.usecases.gausssolve.GaussSolve;
import com.akondi.usecases.get.gausssolutions.GetGaussSolutions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
//@EnableRepositories("com.akondi.inmemorydb.repositories")
public class ApplicationConfiguration {
    @Bean
    public Database database() {
                return new InMemoryDb();
    }

    @Bean
    public Clock clock() {
        return new Clock() {
            @Override
            public LocalDate now() {
                return LocalDate.now();
            }
        };
    }

    @Bean
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }



    @Bean
    public GaussSolutionsOutputBoundary gaussSolutionsOutputBoundary() {
        return new GaussSolutionsPresenter();
    }

    @Bean
    public GetGaussSolutionsInputBoundary getGaussSolutionsInputBoundary(GaussSolutionsOutputBoundary gaussSolutionsOutputBoundary, Database database) {
        return new GetGaussSolutions(gaussSolutionsOutputBoundary, database.gaussSolutionGateway());
    }

    @Bean
    public GetGaussSolutionsControllerInputBoundary getGaussSolutionsControllerInputBoundary(GetGaussSolutionsInputBoundary getGaussSolutionsInputBoundary){
        return new GetGaussSolutionsController(getGaussSolutionsInputBoundary);
    }

//    @Bean
//    public GaussSolutionsViewModelOutputBoundary gaussSolutionsViewModelOutputBoundary(GaussSolutionsOutputBoundary gaussSolutionsOutputBoundary) {
//        return (GaussSolutionsPresenter) gaussSolutionsOutputBoundary();
//    }

    @Bean
    public GaussSolveOutputBoundary gaussSolveOutputBoundary() {
        return new GaussSolvePresenter();
    }

    @Bean
    public GaussSolveInputBoundary gaussSolveInputBoundary(GaussSolveOutputBoundary gaussSolveOutputBoundary, Database database, Clock clock) {
        return new GaussSolve(gaussSolveOutputBoundary, database.gaussSolutionGateway(), clock, idGenerator());
    }

    @Bean
    public GaussSolveControllerInputBoundary gaussSolveControllerInputBoundary(GaussSolveInputBoundary gaussSolveInputBoundary) {
        return new GaussSolveController(gaussSolveInputBoundary);
    }
//
//    @Bean
//    public GaussSolveViewModelOutputBoundary gaussSolveViewModelOutputBoundary(GaussSolveOutputBoundary gaussSolveOutputBoundary) {
//        return (GaussSolvePresenter) gaussSolveOutputBoundary();
//    }
}
