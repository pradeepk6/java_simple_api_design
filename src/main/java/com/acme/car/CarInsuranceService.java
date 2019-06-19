package com.acme.car;

public interface CarInsuranceService {

    CarInsuranceQuote getQuote(CarInsuranceProposal carInsuranceProposal);
}