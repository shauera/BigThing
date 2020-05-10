package cart;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.BeforeClass;
import org.junit.Rule;

import fixtures.RatesFetcher;
import i18n.Countries;

import static org.junit.Assert.*;

import model.IRatesFetcher;
import model.Rates;

public class CalculatorTest {

	private static Rates rates;

	@BeforeClass
	public static void setUpBeforeClass() {
		IRatesFetcher ratesFetcher = new RatesFetcher();

		rates = new Rates(ratesFetcher);
	}

	@Test
	public void test_add_both_SEK_result_in_SEK() {

		MonetaryValue price1 = new MonetaryValue(5, Countries.SWEEDEN.getCurrencyCode());
		MonetaryValue price2 = new MonetaryValue(6, Countries.SWEEDEN.getCurrencyCode());

		Calculator calc= new Calculator(rates, price1);
		calc.add(price2);

		assertEquals(11, calc.getValueIn(Countries.SWEEDEN.getCurrencyCode()).getValue(), 0.00001);
	}

	@Test
	public void test_add_both_USD_result_in_USD() {

		MonetaryValue price1 = new MonetaryValue(-3, Countries.UNITED_STATES.getCurrencyCode());
		MonetaryValue price2 = new MonetaryValue(9, Countries.UNITED_STATES.getCurrencyCode());

		Calculator calc= new Calculator(rates, price1);
		calc.add(price2);

		assertEquals(6, calc.getValueIn(Countries.UNITED_STATES.getCurrencyCode()).getValue(), 0.00001);
	}

	@Test
	public void test_add_SEK_to_USD_result_in_USD() {

		MonetaryValue price1 = new MonetaryValue(1, Countries.SWEEDEN.getCurrencyCode());
		MonetaryValue price2 = new MonetaryValue(1, Countries.UNITED_STATES.getCurrencyCode());

		Calculator calc= new Calculator(rates, price1);
		calc.add(price2);

		assertEquals(1.1024, calc.getValueIn(Countries.UNITED_STATES.getCurrencyCode()).getValue(), 0.0001);
	}

	@Test
	public void test_add_USD_to_SEK_result_in_USD() {

		MonetaryValue price1 = new MonetaryValue(1, Countries.SWEEDEN.getCurrencyCode());
		MonetaryValue price2 = new MonetaryValue(1, Countries.UNITED_STATES.getCurrencyCode());

		Calculator calc= new Calculator(rates, price1);
		calc.add(price2);

		assertEquals(1.1024, calc.getValueIn(Countries.UNITED_STATES.getCurrencyCode()).getValue(), 0.0001);
	}

	@Test
	public void test_add_zero_USD_to_SEK_result_in_SEK() {

		MonetaryValue price1 = new MonetaryValue(1, Countries.SWEEDEN.getCurrencyCode());
		MonetaryValue price2 = new MonetaryValue(0, Countries.UNITED_STATES.getCurrencyCode());

		Calculator calc= new Calculator(rates, price1);
		calc.add(price2);

		assertEquals(1, calc.getValueIn(Countries.SWEEDEN.getCurrencyCode()).getValue(), 0.0001);
	}

	@Test
	public void test_convert_USD_to_SEK() {

		MonetaryValue price1 = new MonetaryValue(1, Countries.UNITED_STATES.getCurrencyCode());

		Calculator calc1= new Calculator(rates, price1);

		assertEquals(
				9.7643,
				calc1.getValueIn(Countries.SWEEDEN.getCurrencyCode()).getValue(),
				0.0001);
	}

	@Test
	public void test_add_USD_to_zero_SEK_result_in_SEK_compared_to_convert_to_SEK() {

		MonetaryValue price1 = new MonetaryValue(0, Countries.SWEEDEN.getCurrencyCode());
		MonetaryValue price2 = new MonetaryValue(1, Countries.UNITED_STATES.getCurrencyCode());

		Calculator calc1= new Calculator(rates, price2);
		calc1.add(price1);

		Calculator calc2= new Calculator(rates, price2);

		assertEquals(
				calc1.getValueIn(Countries.SWEEDEN.getCurrencyCode()).getValue(),
				calc2.getValueIn(Countries.SWEEDEN.getCurrencyCode()).getValue(),
				0.0000000001);
	}

	@Test
	public void test_sub_USD_from_SEK_result_in_SEK() {

		MonetaryValue price1 = new MonetaryValue(10, Countries.SWEEDEN.getCurrencyCode());
		MonetaryValue price2 = new MonetaryValue(1, Countries.UNITED_STATES.getCurrencyCode());

		Calculator calc= new Calculator(rates, price1);
		calc.sub(price2);

		assertEquals(
				0.2356,
				calc.getValueIn(Countries.SWEEDEN.getCurrencyCode()).getValue(),
				0.0001);
	}

	@Rule
	public ExpectedException exceptionRule1 = ExpectedException.none();

	@Test
	public void test_unsupported_currency() {

		exceptionRule1.expect(IllegalArgumentException.class);
		exceptionRule1.expectMessage("Could not find currency code [NOT_SUPPORTED_CORRENCY]");

		MonetaryValue price = new MonetaryValue(10, "NOT_SUPPORTED_CORRENCY");
		@SuppressWarnings("unused")
		Calculator calc= new Calculator(rates, price);
	}

	@Rule
	public ExpectedException exceptionRule2 = ExpectedException.none();

	@Test
	public void test_invalid_calculator_initialization() {

		exceptionRule2.expect(IllegalArgumentException.class);
		exceptionRule2.expectMessage("Calculator can't initialize with null rates");

		Rates NULL_RATES = null;
		@SuppressWarnings("unused")
		Calculator calc= new Calculator(NULL_RATES);
	}

	@Rule
	public ExpectedException exceptionRule3 = ExpectedException.none();

	@Test
	public void test_invalid_rates_initialization() {

		exceptionRule3.expect(IllegalArgumentException.class);
		exceptionRule3.expectMessage("Rates can't initialize with null ratesFetcher");

		IRatesFetcher NULL_RATES_Fetcher = null;
		Rates rates = new Rates(NULL_RATES_Fetcher);
	}
}
