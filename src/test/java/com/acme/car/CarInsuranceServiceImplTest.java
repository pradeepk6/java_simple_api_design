package com.acme.car;

import com.theftwatch.PostcodeNotFoundException;
import com.theftwatch.TheftRisk;
import com.theftwatch.TheftRiskChecker;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CarInsuranceServiceImplTest {

    @Mock
    private TheftRiskChecker theftRiskCheckerMock;
    private RatingEngine ratingEngine;
    private CarInsuranceServiceImpl carInsuranceServiceMock;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ratingEngine = new SimpleRatingEngine();
        carInsuranceServiceMock = new CarInsuranceServiceImpl(ratingEngine, theftRiskCheckerMock);
    }

    @Test()
    public void testInvalidPostCodeDeniesQuote() {
        when(theftRiskCheckerMock.getRisk(any(String.class))).thenThrow(PostcodeNotFoundException.class);
        CarInsuranceProposal proposal = new CarInsuranceProposal("", 1000);
        CarInsuranceQuote quote = carInsuranceServiceMock.getQuote(proposal);
        assertFalse(quote.isInsurable());
        assertTrue(quote.getMessage().toLowerCase().contains("invalid postcode"));
    }

    @Test()
    public void testSuccessfulQuoteWithLowRiskAndBelow900() {
        when(theftRiskCheckerMock.getRisk(any(String.class))).thenReturn(TheftRisk.LOW_RISK);
        CarInsuranceProposal proposal = new CarInsuranceProposal("", 899);
        CarInsuranceQuote quote = carInsuranceServiceMock.getQuote(proposal);
        assertTrue(quote.isInsurable());
        assertTrue(quote.getQuote() == 85);
    }

    @Test()
    public void testSuccessfulQuoteWithLowRiskAndAbove899() {
        when(theftRiskCheckerMock.getRisk(any(String.class))).thenReturn(TheftRisk.LOW_RISK);
        CarInsuranceProposal proposal = new CarInsuranceProposal("", 900);
        CarInsuranceQuote quote = carInsuranceServiceMock.getQuote(proposal);
        assertTrue(quote.isInsurable());
        assertTrue(quote.getQuote() == 180);
    }

    @Test()
    public void testUnSuccessfulQuoteWithHighRisk() {
        when(theftRiskCheckerMock.getRisk(any(String.class))).thenReturn(TheftRisk.HIGH_RISK);
        CarInsuranceProposal proposal = new CarInsuranceProposal("", 900);
        CarInsuranceQuote quote = carInsuranceServiceMock.getQuote(proposal);
        assertFalse(quote.isInsurable());
        assertTrue(quote.getMessage().toLowerCase().contains("high risk"));
    }

    @Test()
    public void testUnSuccessfulQuoteWithHighCarValue() {
        when(theftRiskCheckerMock.getRisk(any(String.class))).thenReturn(TheftRisk.LOW_RISK);
        CarInsuranceProposal proposal = new CarInsuranceProposal("", 2001);
        CarInsuranceQuote quote = carInsuranceServiceMock.getQuote(proposal);
        assertFalse(quote.isInsurable());
    }
}