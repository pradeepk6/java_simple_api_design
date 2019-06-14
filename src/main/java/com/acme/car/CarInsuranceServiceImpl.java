package com.acme.car;

import com.theftwatch.PostcodeNotFoundException;
import com.theftwatch.TheftRisk;
import com.theftwatch.TheftRiskChecker;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CarInsuranceServiceImpl implements CarInsuranceService {

    private static final int basePremium = 85;
    private TheftRiskChecker theftRiskChecker;

    public CarInsuranceServiceImpl(TheftRiskChecker theftRiskCheckerImpl) {
        this.theftRiskChecker = theftRiskCheckerImpl;
    }

    @Override
    public CarInsuranceQuote calculatePremium(String postcode, @NotNull @Positive double carValue) {

        TheftRisk theftRisk = null;
        CarInsuranceQuote carInsuranceQuote = null;

        try {
            theftRisk = theftRiskChecker.getRisk(postcode);
        } catch (PostcodeNotFoundException e) {
            carInsuranceQuote = new CarInsuranceQuote(false, -1, "Invalid postcode");
        }

        int quote = 0;
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
