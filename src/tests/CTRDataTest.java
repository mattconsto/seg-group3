package tests;

import java.sql.SQLException;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import junit.framework.TestCase;

import org.junit.Test;

import dashboard.controller.CTRGraphConstructor;
import dashboard.model.DatabaseConnection;
import dashboard.model.Filter;

public class CTRDataTest extends TestCase {
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
			
			CTRGraphConstructor ctrConstructor = new CTRGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = ctrConstructor.fetchGraph().getData();
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
			
			CTRGraphConstructor ctrConstructor = new CTRGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = ctrConstructor.fetchGraph().getData();
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
			
			CTRGraphConstructor ctrConstructor = new CTRGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = ctrConstructor.fetchGraph().getData();
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
			
			CTRGraphConstructor ctrConstructor = new CTRGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = ctrConstructor.fetchGraph().getData();
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
			
			CTRGraphConstructor ctrConstructor = new CTRGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = ctrConstructor.fetchGraph().getData();
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
			CTRGraphConstructor ctrConstructor = new CTRGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setGender(FXCollections.observableArrayList("Male"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
			
			//Test invalid gender
			filter.setGender(FXCollections.observableArrayList("Invalid"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
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
			CTRGraphConstructor ctrConstructor = new CTRGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("25 to 34"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("35 to 44"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("45 to 54"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("Greater than 55"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
			
			//Test invalid age
			filter.setAge(FXCollections.observableArrayList("Invalid"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
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
			CTRGraphConstructor ctrConstructor = new CTRGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setIncome(FXCollections.observableArrayList("Medium"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setIncome(FXCollections.observableArrayList("High"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
			
			//Test invalid income
			filter.setIncome(FXCollections.observableArrayList("Income"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
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
			CTRGraphConstructor ctrConstructor = new CTRGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Shopping"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Social Media"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Blog"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Hobbies"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Travel"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
			
			//Test invalid context
			filter.setContext(FXCollections.observableArrayList("Invalid"));
			ctrConstructor = new CTRGraphConstructor(filter);

			data = ctrConstructor.fetchGraph().getData();
			assertEquals(0.5f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}
}
