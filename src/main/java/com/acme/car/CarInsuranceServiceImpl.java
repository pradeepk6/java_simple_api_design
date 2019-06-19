package com.acme.car;

import com.theftwatch.PostcodeNotFoundException;
import com.theftwatch.TheftRisk;
import com.theftwatch.TheftRiskChecker;

public class CarInsuranceServiceImpl implements CarInsuranceService {

    private TheftRiskChecker theftRiskChecker;

    public CarInsuranceServiceImpl(TheftRiskChecker theftRiskCheckerImpl) {
        this.theftRiskChecker = theftRiskCheckerImpl;
    }

    @Override
    public CarInsuranceQuote getQuote(CarInsuranceProposal proposal, RatingEngine rater) {

        TheftRisk theftRisk = null;
        CarInsuranceQuote carInsuranceQuote = null;

        try {
            theftRisk = theftRiskChecker.getRisk(proposal.getPostcode());
        } catch (PostcodeNotFoundException e) {
            return new CarInsuranceQuote(false, -1, "Invalid postcode");
        } catch (Exception e) {
            throw new TheftRiskServiceFailureException("TheftRisk Service has failed", e);
        }

        carInsuranceQuote = rater.calculatePremium(proposal, theftRisk);

        return carInsuranceQuote;
    }
}
