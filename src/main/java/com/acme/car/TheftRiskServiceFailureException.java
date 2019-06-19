package com.acme.car;

public class TheftRiskServiceFailureException extends RuntimeException {
    public TheftRiskServiceFailureException(String msg, Exception e) {
        super(msg, e);
    }
}
