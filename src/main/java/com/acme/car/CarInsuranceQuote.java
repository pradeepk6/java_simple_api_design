package com.acme.car;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarInsuranceQuote {

    private boolean insurable;
    private double quote;
    private String message;

    public CarInsuranceQuote(boolean insurable, int quote, String message) {
        this.insurable = insurable;
        this.quote = quote;
        this.message = message;
    }
}
