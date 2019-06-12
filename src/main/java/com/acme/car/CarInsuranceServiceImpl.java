package com.acme.car;

import com.theftwatch.PostcodeNotFoundException;
import com.theftwatch.TheftRisk;
import com.theftwatch.TheftRiskChecker;

public class CarInsuranceServiceImpl implements CarInsuranceService {

    private static final int basePremium = 85;
    private TheftRiskChecker theftRiskChecker;

    public CarInsuranceServiceImpl(TheftRiskChecker theftRiskCheckerImpl) {
        this.theftRiskChecker = theftRiskCheckerImpl;
    }

    @Override
    public CarInsuranceQuote calculatePremium(String postcode, double carValue) {

        TheftRisk theftRisk = null;
        CarInsuranceQuote carInsuranceQuote = null;

        if (carValue < 1) throw new IllegalArgumentException("Car value must be positive");
        try {
            theftRisk = theftRiskChecker.getRisk(postcode);
        } catch (PostcodeNotFoundException e) {
            carInsuranceQuote = new CarInsuranceQuoteImpl(false, -1, "Invalid postcode");
        }

        int quote = -1;
        if (theftRisk != null) {
            if (theftRisk.equals(TheftRisk.HIGH_RISK)) {
                quote = -1;
                carInsuranceQuote = new CarInsuranceQuoteImpl(false, quote, "High Risk");
            } else if (theftRisk.equals(TheftRisk.LOW_RISK)) {
                if (carValue > 2000) {
                    quote = -1;
                    carInsuranceQuote = new CarInsuranceQuoteImpl(false, quote, "Car value too high");
                } else if (carValue > 0 && carValue < 900) {
                    quote = basePremium;
                    carInsuranceQuote = new CarInsuranceQuoteImpl(true, quote, "");
                } else if (carValue > 899 && carValue < 2001) {
                    quote = basePremium + 95;
                    carInsuranceQuote = new CarInsuranceQuoteImpl(true, quote, "");
                }
            }
        }
        return carInsuranceQuote;
    }


}
