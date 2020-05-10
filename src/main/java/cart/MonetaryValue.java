package cart;

/**
 * Represents a value, usually a price of an item in the cart. The monetary value must be
 * Qualified by a supported currency code.
 */
public class MonetaryValue{

	private double value;
	private String currencyCode;

	public MonetaryValue(double value, String currencyCode) {
		this.value = value;
		this.currencyCode = currencyCode;
	}

	public double getValue() {
		return value;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}
}
