package model;

import java.util.HashMap;
import java.util.Map;

/**
 * Represent a conversion table between a list of currencies and a base currency.
 * Conversions can be made from a currency to any other by converting to the base currency and then
 * back from the base currency into the target currency. 
 */
public class Rates{

	private Map<String, Double> rates = new HashMap<String, Double>();
	private String baseCurrencyCode;
	private IRatesFetcher ratesFetcher;

	/**
	 * Constructor - Rates
	 *
	 * @param ratesFetcher - must not be null!
	 */
	public Rates (IRatesFetcher ratesFetcher) {
		if (ratesFetcher == null) {
			throw new IllegalArgumentException("Rates can't initialize with null ratesFetcher");
		}

		this.ratesFetcher = ratesFetcher;
	}

	/**
	 * @param currncyCode - the currency for which a rate is requested 
	 * @return a number representing the equivalent of 1 base currency in the requested currency 
	 * @throws IllegalArgumentException - will be thrown in case a currency code can't be found in the currencies map
	 */
	public double getRate(String currncyCode) throws IllegalArgumentException {
		if (this.baseCurrencyCode == null) {
			loadRates();
		}

		if (currncyCode.compareTo(baseCurrencyCode) == 0) {
			return 1;
		}
	
		Double rate = rates.get(currncyCode);
		if (rate == null) {
			throw (new IllegalArgumentException(
					String.format("Could not find currency code [%s].", currncyCode)));
		}
	
		return rate;
	}

	/**
	 * @return the code of the base currency
	 */
	public String getBaseCurrencyCode() {
		return baseCurrencyCode;
	}

	@SuppressWarnings("unchecked") //This could be substituted by getting getAllRates return a class with the proper fields instead of a Map. 
	private void loadRates() {
		Map<?, ?> ratesJsonString = ratesFetcher.getAllRates();

		this.rates = (Map<String, Double>) ratesJsonString.get("rates");
		this.baseCurrencyCode = (String) ratesJsonString.get("base");
	}
}
