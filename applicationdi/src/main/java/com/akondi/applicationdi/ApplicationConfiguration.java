package com.akondi.applicationdi;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;


@Configuration
@EntityScan("com.akondi.inmemorydb")
//@EnableRepositories("com.akondi.inmemorydb.repositories")
public class ApplicationConfiguration {
    @Bean
    public Database database(GaussSolutionRepository gaussSolutionRepository) {
        return new InMemoryDb(gaussSolutionRepository);
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
