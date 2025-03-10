package tests;

import java.sql.SQLException;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import junit.framework.TestCase;

import org.junit.Test;

import dashboard.controller.BounceRateGraphConstructor;
import dashboard.model.BounceFilter;
import dashboard.model.DatabaseConnection;
import dashboard.model.Filter;

public class BounceRateDataTest extends TestCase {
	Filter filter;
	BounceFilter bounceFilter;

	@Override
	public void setUp() {
		DatabaseConnection.setDbfile("TestData.db");
		filter = new Filter();
		filter.setCampaign("TestData");
		bounceFilter = new BounceFilter();
		bounceFilter.setPageLimit(1);
	}

	@Test
	public void testFirstMinuteTotal() {
		try {
			filter.setTime("Minutes");
			
			BounceRateGraphConstructor bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			ObservableList<XYChart.Data<Date, Number>> data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourTotal() {
		try {
			filter.setTime("Hours");
			
			BounceRateGraphConstructor bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			ObservableList<XYChart.Data<Date, Number>> data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstDayTotal() {
		try {
			filter.setTime("Days");
			
			BounceRateGraphConstructor bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			ObservableList<XYChart.Data<Date, Number>> data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstWeekTotal() {
		try {
			filter.setTime("Weeks");
			
			BounceRateGraphConstructor bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			ObservableList<XYChart.Data<Date, Number>> data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}
	
	@Test
	public void testInvalidTimeTotal() {
		try {
			filter.setTime("Other");
			
			BounceRateGraphConstructor bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			ObservableList<XYChart.Data<Date, Number>> data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourGender() {
		try {
			filter.setGender(FXCollections.observableArrayList("Female"));
			BounceRateGraphConstructor bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			ObservableList<XYChart.Data<Date, Number>> data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setGender(FXCollections.observableArrayList("Male"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
			
			//Test invalid gender
			filter.setGender(FXCollections.observableArrayList("Invalid"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourAge() {
		try {
			filter.setAge(FXCollections.observableArrayList("Less than 25"));
			BounceRateGraphConstructor bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			ObservableList<XYChart.Data<Date, Number>> data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("25 to 34"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("35 to 44"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("45 to 54"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("Greater than 55"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
			
			//Test invalid age
			filter.setAge(FXCollections.observableArrayList("Invalid"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourIncome() {
		try {
			filter.setIncome(FXCollections.observableArrayList("Low"));
			BounceRateGraphConstructor bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			ObservableList<XYChart.Data<Date, Number>> data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setIncome(FXCollections.observableArrayList("Medium"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setIncome(FXCollections.observableArrayList("High"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
			
			//Test invalid income
			filter.setIncome(FXCollections.observableArrayList("Invalid"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourContext() {
		try {
			filter.setContext(FXCollections.observableArrayList("News"));
			BounceRateGraphConstructor bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			ObservableList<XYChart.Data<Date, Number>> data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Shopping"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Social Media"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Blog"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Hobbies"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Travel"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
			
			//Test invalid context
			filter.setContext(FXCollections.observableArrayList("Invalid"));
			bounceConstructor = new BounceRateGraphConstructor(filter, bounceFilter);

			data = bounceConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}
}
