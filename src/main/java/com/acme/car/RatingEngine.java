package com.acme.car;

import com.theftwatch.TheftRisk;

public interface RatingEngine {

    CarInsuranceQuote calculatePremium(CarInsuranceProposal carInsuranceProposal, TheftRisk theftRisk);
}
