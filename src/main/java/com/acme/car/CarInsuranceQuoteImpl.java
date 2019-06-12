package com.acme.car;

public class CarInsuranceQuoteImpl implements CarInsuranceQuote {

    private boolean insurable;
    private double quote;
    private String message;

    public CarInsuranceQuoteImpl(boolean insurable, int quote, String message) {
        this.insurable = insurable;
        this.quote = quote;
        this.message = message;
    }

    @Override
    public boolean isInsurable() {
        return insurable;
    }

    public void setInsurable(boolean insurable) {
        this.insurable = insurable;
    }

    @Override
    public double getQuote() {
        return quote;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
