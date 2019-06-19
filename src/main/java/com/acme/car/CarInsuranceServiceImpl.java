package com.acme.car;

import com.theftwatch.PostcodeNotFoundException;
import com.theftwatch.TheftRisk;
import com.theftwatch.TheftRiskChecker;

public class CarInsuranceServiceImpl implements CarInsuranceService {

    private TheftRiskChecker theftRiskChecker;
    private RatingEngine ratingEngine;

    public CarInsuranceServiceImpl(RatingEngine ratingEngine, TheftRiskChecker theftRiskChecker) {
        this.theftRiskChecker = theftRiskChecker;
        this.ratingEngine = ratingEngine;
    }

    @Override
    public CarInsuranceQuote getQuote(CarInsuranceProposal proposal) {

        TheftRisk theftRisk;
        CarInsuranceQuote carInsuranceQuote;

        try {
            theftRisk = theftRiskChecker.getRisk(proposal.getPostcode());
        } catch (PostcodeNotFoundException e) {
            return new CarInsuranceQuote(false, -1, "Invalid postcode");
        } catch (Exception e) {
            throw new TheftRiskServiceFailureException("TheftRisk Service has failed", e);
        }

        carInsuranceQuote = ratingEngine.calculatePremium(proposal, theftRisk);

        return carInsuranceQuote;
    }
}
