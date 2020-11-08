package com.akondi.usecases.gausssolve;

import java.time.LocalDate;

public interface Clock {
    default LocalDate now() {
        return LocalDate.now();
    }
}
