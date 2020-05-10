package cart;

import model.Rates;

/**
 * Main engine for calculating cart contents. Currency-wise, items can be mixed.
 * Result can be returned in any supported currency regardless of the items' currencies.
 * 
 * All values entered into the calculator are converted upon admittance into the base currency
 * on which calculation are being performed.
 */
public class Calculator {

	Rates rates;
	double value;

	/**
	 * Constructor - Calculator
	 * Since monetary value is not provided it will be set to 0 (of the base currency)
	 * 
	 * @param rates - must not be null!
	 */
	public Calculator(Rates rates) {
		this(rates, null);
	}

	/**
	 * Constructor - Calculator
	 *
	 * @param rates - must not be null!
	 * @param value - an initial monetary to be set in the calculator
	 */
	public Calculator(Rates rates, MonetaryValue value) {
		if (rates == null) {
			throw new IllegalArgumentException("Calculator can't initialize with null rates");
		}

		this.rates = rates;

		if (value == null) {
			value = new MonetaryValue(0, rates.getBaseCurrencyCode());
		}
		this.value = convert(value);
	}

	/**
	 * add a monetary value to the value currently stored in the calculator instance 
	 * @param value - a monetary value to be added
	 */
	public void add(MonetaryValue value) {
		this.value += convert(value);
	}

	/**
	 * subtract a monetary value from the value currently stored in the calculator instance
	 * @param value - a monetary value to be subtracted
	 */
	public void sub(MonetaryValue value) {
		this.value -= convert(value);
	}

	/**
	 * convert the value stored in the calculator instance to the requested currency
	 * @param currncyCode - the currency to convert to
	 * @return {@link MonetaryValue} at the requested currency
	 */
	public MonetaryValue getValueIn(String currncyCode) {
		return new MonetaryValue(this.value * this.rates.getRate(currncyCode), currncyCode);
	}

	private double convert(MonetaryValue value) {
		return value.getValue() / this.rates.getRate(value.getCurrencyCode());
	}
}
