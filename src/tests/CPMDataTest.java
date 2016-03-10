package tests;

import java.sql.SQLException;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import junit.framework.TestCase;

import org.junit.Test;

import dashboard.controller.CPMGraphConstructor;
import dashboard.model.DatabaseConnection;
import dashboard.model.Filter;

public class CPMDataTest extends TestCase {
	Filter filter;

	@Override
	public void setUp() {
		DatabaseConnection.setDbfile("TestData.db");
		filter = new Filter();
	}

	@Test
	public void testFirstMinuteTotal() {
		try {
			CPMGraphConstructor cpmConstructor = new CPMGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourTotal() {
		try {
			CPMGraphConstructor cpmConstructor = new CPMGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstDayTotal() {
		try {
			CPMGraphConstructor cpmConstructor = new CPMGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstWeekTotal() {
		try {
			CPMGraphConstructor cpmConstructor = new CPMGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourGender() {
		try {
			filter.setGender(FXCollections.observableArrayList("Female"));
			CPMGraphConstructor cpmConstructor = new CPMGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setGender(FXCollections.observableArrayList("Male"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourAge() {
		try {
			filter.setAge(FXCollections.observableArrayList("Less than 25"));
			CPMGraphConstructor cpmConstructor = new CPMGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("25 to 34"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("35 to 44"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("45 to 54"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setAge(FXCollections.observableArrayList("Greater than 55"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourIncome() {
		try {
			filter.setIncome(FXCollections.observableArrayList("Low"));
			CPMGraphConstructor cpmConstructor = new CPMGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setIncome(FXCollections.observableArrayList("Medium"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setIncome(FXCollections.observableArrayList("High"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}

	@Test
	public void testFirstHourContext() {
		try {
			filter.setContext(FXCollections.observableArrayList("News"));
			CPMGraphConstructor cpmConstructor = new CPMGraphConstructor(filter);

			ObservableList<XYChart.Data<Date, Number>> data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Shopping"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Social Media"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Blog"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Hobbies"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());

			filter.setContext(FXCollections.observableArrayList("Travel"));
			cpmConstructor = new CPMGraphConstructor(filter);

			data = cpmConstructor.fetchGraph().getData();
			assertEquals(6000f, data.get(0).getYValue());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			fail("SQL error");
		}
	}
}
