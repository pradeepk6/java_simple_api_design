package com.acme.car;

public interface CarInsuranceQuote {
    boolean isInsurable();

    double getQuote();

    String getMessage();
}
