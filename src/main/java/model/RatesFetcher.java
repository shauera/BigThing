package model;

import java.util.Map;

public class RatesFetcher implements IRatesFetcher {

	@Override
	public Map<?, ?> getAllRates() {
	//TODO
	/* Use an http client to execute GET "https://api.exchangeratesapi.io/latest";
	 * this will return a json like this:
	 * {
		    "rates": {
		        "CAD": 1.5118,
		        "HKD": 8.4052,
		        "ISK": 158.5,
		        "PHP": 54.681,
		        "DKK": 7.4598,
		        "HUF": 349.38,
		        "CZK": 27.251,
		        "AUD": 1.6613,
		        "RON": 4.828,
		        "SEK": 10.5875,
		        "IDR": 16229.26,
		        "INR": 81.9615,
		        "BRL": 6.3074,
		        "RUB": 79.8383,
		        "HRK": 7.5573,
		        "JPY": 115.34,
		        "THB": 34.958,
		        "CHF": 1.0529,
		        "SGD": 1.5326,
		        "PLN": 4.5482,
		        "BGN": 1.9558,
		        "TRY": 7.7252,
		        "CNY": 7.6719,
		        "NOK": 11.0695,
		        "NZD": 1.7668,
		        "ZAR": 19.997,
		        "USD": 1.0843,
		        "MXN": 25.9023,
		        "ILS": 3.8031,
		        "GBP": 0.87535,
		        "KRW": 1322.41,
		        "MYR": 4.6994
		    },
		    "base": "EUR",
		    "date": "2020-05-08"
		}
		
		transform the json to a map 
	 */

		return null;
	}

}
