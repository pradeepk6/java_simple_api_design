package com.acme.car;

import com.theftwatch.TheftRisk;

public class SimpleRatingEngine implements RatingEngine {

    private static final int basePremium = 85;
    CarInsuranceQuote carInsuranceQuote = null;

    @Override
    public CarInsuranceQuote calculatePremium(CarInsuranceProposal carInsuranceProposal, TheftRisk theftRisk) {
        CarInsuranceQuote carInsuranceQuote = null;
        int quote = 0;
        double carValue = carInsuranceProposal.getCarValue();
        if (theftRisk != null) {
            if (theftRisk.equals(TheftRisk.HIGH_RISK)) {
                carInsuranceQuote = new CarInsuranceQuote(false, quote, "High Risk");
            } else if (theftRisk.equals(TheftRisk.LOW_RISK)) {
                if (carValue > 2000) {
                    carInsuranceQuote = new CarInsuranceQuote(false, quote, "Car value too high");
                } else if (carValue > 0 && carValue < 900) {
                    quote = basePremium;
                    carInsuranceQuote = new CarInsuranceQuote(true, quote, "");
                } else if (carValue > 899 && carValue < 2001) {
                    quote = basePremium + 95;
                    carInsuranceQuote = new CarInsuranceQuote(true, quote, "");
                }
            }
        }
        return carInsuranceQuote;
    }
}
