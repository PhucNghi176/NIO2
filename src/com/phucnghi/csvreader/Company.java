package com.phucnghi.csvreader;

class Company {
    private final String name;
    private final double capital;

    public Company(String name, double capital) {
        this.name = name;
        this.capital = capital;
    }

    public String getName() {
        return name;
    }

    public double getCapital() {
        return capital;
    }
}
