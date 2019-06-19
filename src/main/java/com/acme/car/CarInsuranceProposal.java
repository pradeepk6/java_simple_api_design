package com.acme.car;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CarInsuranceProposal {

    @NotNull
    private String postcode;
    @Positive
    private double carValue;

    public CarInsuranceProposal(@NotNull String postcode, @Positive double carValue) {
        this.postcode = postcode;
        this.carValue = carValue;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public double getCarValue() {
        return carValue;
    }

    public void setCarValue(double carValue) {
        this.carValue = carValue;
    }
}
