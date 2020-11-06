package com.akondi.ports.presenters;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GaussSolutionViewModel {
    private  long id;
    private  String date;
    private  String document;
    private  String result;
}
