package com.akondi.ports.presenters;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GaussSolutionViewModel {
    private final long id;
    private final String date;
    private final String document;
    private final String result;
}
