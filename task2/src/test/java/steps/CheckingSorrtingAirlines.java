package steps;

import java.util.List;

import org.openqa.selenium.WebElement;

import a1qa.framework.test.BaseTest;
import a1qa.google.flights.pages.FlightsPage;
import a1qa.google.flights.utils.Airlines;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static a1qa.google.flights.pages.FlightsPage.*;
import static org.testng.Assert.assertTrue;

public class CheckingSorrtingAirlines extends BaseTest {
	
	private FlightsPage flightPage;
	
	@When("^I clicked sorting airlines$")
	public void i_clicked_sorting_airlines() throws Throwable {
		flightPage = new FlightsPage();
		flightPage.clickAirline();
	}

	@When("^choose Belavia$")
	public void choose_Belavia() throws Throwable {
	    flightPage.switchAirline(Airlines.BELAVIA.getTitle());
	}

	@Then("^flights only Belavia$")
	public void flights_only_Belavia() throws Throwable {
		List<WebElement> flights = flightPage.getListFlights();
		boolean bool = true;
		for(int i = 0, l = flights.size(); i < l; ++i) {
			String airlineTitle = flights.get(i).findElement(FLIGHTS_PAGE_AIRLINE_TITLE).getText();
			if(!Airlines.BELAVIA.getTitle().equals(airlineTitle)) {
				bool = false;
				break;
			}
		}
		assertTrue(bool, "Error airlines sorting");
		log.info("Sorted airlines complete");
	}
}