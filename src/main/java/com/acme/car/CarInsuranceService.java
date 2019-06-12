package com.acme.car;

public interface CarInsuranceService {

    CarInsuranceQuote calculatePremium(String postcode, double carValue);
}