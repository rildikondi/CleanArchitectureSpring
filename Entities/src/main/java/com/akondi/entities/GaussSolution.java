package com.akondi.entities;

public class GaussSolution {
    private final long id;
    private final String date;
    private final String document;
    private final String result;

    public GaussSolution(long id,
                         String date,
                         String document,
                         String result) {
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
