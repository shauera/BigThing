package i18n;

/**
 * Enumeration of supported countries. Supporting a new country is merely adding one line to the enum.
 */
public enum Countries {
	UNITED_STATES ("United State", "USD"),
	SWEEDEN       ("Sweeden",      "SEK");
	
	// uncomment the following line to add support for England's currency
	//ENGLAND       ("England",      "GBP");

	private final String displayName;
	private final String currencyCode;

	Countries(String displayName, String currencyCode) {
		this.displayName = displayName;
		this.currencyCode = currencyCode;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public String getCurrencyCode() {
		return this.currencyCode;
	}
}

