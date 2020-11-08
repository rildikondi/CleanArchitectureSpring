package com.akondi.usecases.get.gausssolutions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GaussSolutionResponse {
    private long id;
    private String date;
    private String document;
    private String result;
}
