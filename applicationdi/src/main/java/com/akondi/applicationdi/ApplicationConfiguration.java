package com.akondi.applicationdi;


import com.akondi.inmemorydb.InMemoryDb;
import com.akondi.ports.database.Database;
import com.akondi.ports.presenters.GaussSolutionsOutputBoundary;
import com.akondi.ports.presenters.GaussSolveOutputBoundary;
import com.akondi.ports.usescases.Clock;
import com.akondi.ports.usescases.gausssolve.GaussSolveInputBoundary;
import com.akondi.ports.usescases.get.gausssolutions.GetGaussSolutionsInputBoundary;
import com.akondi.presenters.GaussSolutionsPresenter;
import com.akondi.presenters.GaussSolvePresenter;
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

//    @Bean
//    public IdGenerator idGenerator() {
//        return new IdGenerator();
//    }

    @Bean
    public GaussSolutionsOutputBoundary gaussSolutionsOutputBoundary() {
        return new GaussSolutionsPresenter();
    }

    @Bean
    public GetGaussSolutionsInputBoundary getGaussSolutionsInputBoundary(GaussSolutionsOutputBoundary gaussSolutionsOutputBoundary, Database database) {
        return new GetGaussSolutions(gaussSolutionsOutputBoundary, database.gaussSolutionGateway());
    }

    @Bean
    public GaussSolveOutputBoundary gaussSolveOutputBoundary() {
        return new GaussSolvePresenter();
    }

    @Bean
    public GaussSolveInputBoundary gaussSolveInputBoundary(GaussSolveOutputBoundary gaussSolveOutputBoundary, Database database, Clock clock) {
        return new GaussSolve(gaussSolveOutputBoundary, database.gaussSolutionGateway(), clock);
    }

}
