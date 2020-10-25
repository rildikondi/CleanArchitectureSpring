package com.akondi.ports.usescases.gausssolve;

import org.springframework.stereotype.Service;

@Service
public interface GaussSolveInputBoundary {
    void execute(GaussSolveRequest request);

}
