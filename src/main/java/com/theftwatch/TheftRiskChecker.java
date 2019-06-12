package com.theftwatch;

/**
 * This class encapsulates the checking of theft risk areas.
 */
public interface TheftRiskChecker {

    /**
     * Get the risk for cars at the passed postcode.
     *
     * @param postcode the postcode at which to assess theft risk
     * @return TheftRisk enumerating the theft risk for the given postcode
     * @throws PostcodeNotFoundException if the postcode is unknown
     */
    TheftRisk getRisk(String postcode) throws PostcodeNotFoundException;
}
