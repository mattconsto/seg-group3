package tests;

import java.sql.SQLException;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import junit.framework.TestCase;

import org.junit.Test;

import dashboard.controller.CPCGraphConstructor;
import dashboard.model.DatabaseConnection;
import dashboard.model.Filter;

public class CPCDataTest extends TestCase {
	Filter filter;

	@Override
	public void setUp() {
		DatabaseConnection.setDbfile("TestData.db");
		filter = new Filter();
		filter.setCampaign("TestData");
	}

	@Test
	public void testFirstMinuteTotal() {
		try {
			filter.setTime("Minutes");
			
			CPCGraphConstructor cpcConstructor = new CPCGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourTotal() {
		try {
			filter.setTime("Hours");
			
			CPCGraphConstructor cpcConstructor = new CPCGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstDayTotal() {
		try {
			filter.setTime("Days");
			
			CPCGraphConstructor cpcConstructor = new CPCGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstWeekTotal() {
		try {
			filter.setTime("Weeks");
			
			CPCGraphConstructor cpcConstructor = new CPCGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}
	
	@Test
	public void testInvalidTimeTotal() {
		try {
			filter.setTime("Other");
			
			CPCGraphConstructor cpcConstructor = new CPCGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourGender() {
		try {
			filter.setGender(FXCollections.observableArrayList("Female"));
			CPCGraphConstructor cpcConstructor = new CPCGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setGender(FXCollections.observableArrayList("Male"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
			
			//Test invalid gender
			filter.setGender(FXCollections.observableArrayList("Invalid"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourAge() {
		try {
			filter.setAge(FXCollections.observableArrayList("Less than 25"));
			CPCGraphConstructor cpcConstructor = new CPCGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("25 to 34"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("35 to 44"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("45 to 54"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("Greater than 55"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
			
			//Test invalid age
			filter.setAge(FXCollections.observableArrayList("Invalid"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourIncome() {
		try {
			filter.setIncome(FXCollections.observableArrayList("Low"));
			CPCGraphConstructor cpcConstructor = new CPCGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setIncome(FXCollections.observableArrayList("Medium"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setIncome(FXCollections.observableArrayList("High"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
			
			//Test invalid income
			filter.setIncome(FXCollections.observableArrayList("Invalid"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourContext() {
		try {
			filter.setContext(FXCollections.observableArrayList("News"));
			CPCGraphConstructor cpcConstructor = new CPCGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Shopping"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Social Media"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Blog"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Hobbies"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Travel"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
			
			//Test invalid context
			filter.setContext(FXCollections.observableArrayList("Invalid"));
			cpcConstructor = new CPCGraphConstructor(filter);

			data = cpcConstructor.fetchGraph().getData();
			assertEquals(12f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}
}
