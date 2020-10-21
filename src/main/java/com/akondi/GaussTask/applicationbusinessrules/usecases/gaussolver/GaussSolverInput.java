package com.akondi.GaussTask.applicationbusinessrules.usecases.gaussolver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GaussSolverInput {
    private final long id;
    private final String date;
    private final String document;
    private final String result;

    public GaussSolverInput(@JsonProperty("id") long id,
                            @JsonProperty("date") String date,
                            @JsonProperty("document") String document,
                            @JsonProperty("result") String result) {
        this.id = id;
        this.date = date;
        this.document = document;
        this.result = result;
    }

    public long getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDocument() {
        return document;
    }

    public String getResult() {
        return result;
    }
}

