package com.acme.car;

public class CarInsuranceQuote {

    private boolean insurable;
    private double quote;
    private String message;

    public CarInsuranceQuote(boolean insurable, double quote, String message) {
        this.insurable = insurable;
        this.quote = quote;
        this.message = message;
    }

    public boolean isInsurable() {
        return insurable;
    }

    public void setInsurable(boolean insurable) {
        this.insurable = insurable;
    }

    public double getQuote() {
        return quote;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
