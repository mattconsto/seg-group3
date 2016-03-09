/*
 
 */
package dashboard.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;

public class Filter {

	public boolean genderEnabled = true;
	public boolean ageEnabled = true;
	public boolean incomeEnabled = true;
	public boolean contextEnabled = true;
	public List <String> gender ;
	public List <String> age ;
	public List <String> income; 
	public List <String> context;
	public LocalDate dateTo;
	public LocalDate dateFrom;
	public String contextSQL = "1";
	public String ageSQL = "1";
	public String incomeSQL = "1";
	public String genderSQL = "1";
	public String dateSQL;
	public String timeFormatSQL = "%Y-%m-%d %H";
	public String timeFormatJava = "yyyy-MM-dd HH";

	public void setTime(String time) {
		this.timeFormatSQL = time;
		
		switch (time) {
			case "Minutes": timeFormatSQL  = "%Y-%m-%d %H:%M"; 
							timeFormatJava = "yyyy-MM-dd HH:mm";
			break;
			default:
			case "Hours":   timeFormatSQL = "%Y-%m-%d %H";
							timeFormatJava = "yyyy-MM-dd HH";
			break;
			case "Days":    timeFormatSQL = "%Y-%m-%d";
							timeFormatJava = "yyyy-MM-dd";
			break;
			case "Weeks":   timeFormatSQL = "%Y-%W";
							timeFormatJava = "yyyy-ww";
			break;
			case "Months":  timeFormatSQL = "%Y-%m";
							timeFormatJava = "yyyy-MM";
			break;
			case "Years":   timeFormatSQL = "%Y";
							timeFormatJava = "yyyy";
			break;
		}
	}
	
	public String getDateSQL() {
		return dateSQL;
	}

	public void setDateSQL(String dateSQL) {
		this.dateSQL = dateSQL;
	}

	public void setGender(ObservableList<String> gender) {
		
		this.gender.clear();
		for (String s : gender)
			this.gender.add (s);
		setGenderSQL();
	}
	private void setAnyGender(CheckComboBox<String> c) {
		c.getItems().clear();
		c.getItems().addAll("Any","Female","Male");
		c.getCheckModel().clearChecks(); 
		c.getCheckModel().check(0); 
		c.getCheckModel().check("Any");
	}
	
	public void setGender(CheckComboBox<String> c) {
		if (!genderEnabled)
			return;
		genderEnabled = false;
		IndexedCheckModel<String> genderModel = c.getCheckModel();
		if (genderModel.getCheckedIndices().isEmpty()) {
			genderModel.check(0); 
		}
		else if (genderModel.isChecked(0)) {
			if (gender.contains("Any")) { // already checked 
				if (genderModel.getCheckedIndices().size() == genderModel.getItemCount()) // they are all ticked so 
					setAnyGender(c);
				else if (genderModel.getCheckedIndices().size() > 1)
					genderModel.clearCheck(0);
			}
			else 
				setAnyGender(c);
		}
		else if (genderModel.getCheckedIndices().size() == genderModel.getItemCount()-1) { // they are all ticked so 
			setAnyGender(c);
		}
		setGender(genderModel.getCheckedItems());
		genderEnabled = true;
	}

	public void setAge(ObservableList<String> age) {
		this.age.clear();
		for (String s : age)
			this.age.add (s);
		setAgeSQL();
	}
	
	private void setAnyAge(CheckComboBox<String> c ) {
		c.getItems().clear();
		c.getItems().addAll("Any","Less than 25","25 to 34","35 to 44","45 to 54","Greater than 55");
		c.getCheckModel().clearChecks(); 
		c.getCheckModel().check(0); 
		c.getCheckModel().check("Any");
	}
	
	 public void setAge(CheckComboBox<String> c) {
		if (!ageEnabled)
			return;
		ageEnabled = false;
		IndexedCheckModel<String> genderModel = c.getCheckModel();
		if (genderModel.getCheckedIndices().isEmpty()) {
			genderModel.check(0); 
		}
		else if (genderModel.isChecked(0)) {
			if (age.contains("Any")) { // already checked 
				if (genderModel.getCheckedIndices().size() == genderModel.getItemCount()) // they are all ticked so 
					setAnyAge(c);
				else if (genderModel.getCheckedIndices().size() > 1)
					genderModel.clearCheck(0);
			}
			else 
				setAnyAge(c);
		}
		else if (genderModel.getCheckedIndices().size() == genderModel.getItemCount()-1) { // they are all ticked so 
			setAnyAge(c);
		}
		setAge(genderModel.getCheckedItems());
		ageEnabled = true;
	}
	
	public void setIncome(ObservableList<String> income) {
		this.income.clear();
		for (String s : income)
			this.income.add (s);
		setIncomeSQL();
	}
	
	private void setAnyIncome(CheckComboBox<String> c ) {
		c.getItems().clear();
		c.getItems().addAll("Any","Low","Medium","High" );
		c.getCheckModel().clearChecks(); 
		c.getCheckModel().check(0); 
		c.getCheckModel().check("Any");
	}
	
	public void setIncome(CheckComboBox<String> c) {
		if (!incomeEnabled)
			return;
		incomeEnabled = false;
		IndexedCheckModel<String> genderModel = c.getCheckModel();
		if (genderModel.getCheckedIndices().isEmpty()) {
			genderModel.check(0); 
		}
		else if (genderModel.isChecked(0)) {
			if (income.contains("Any")) { // already checked 
				if (genderModel.getCheckedIndices().size() == genderModel.getItemCount()) // they are all ticked so 
					setAnyIncome(c);
				else if (genderModel.getCheckedIndices().size() > 1)
					genderModel.clearCheck(0);
			}
			else 
				setAnyIncome(c);
		}
		else if (genderModel.getCheckedIndices().size() == genderModel.getItemCount()-1) { // they are all ticked so 
			setAnyIncome(c);
		}
		setIncome(genderModel.getCheckedItems());
		incomeEnabled = true;
	}
	
	public void setContext(ObservableList<String> ct) {
		this.context.clear();
		for (String s : ct)
			this.context.add (s);
		setContextSQL();
	}
	
	private void setAnyContext(CheckComboBox<String> c) {
		c.getItems().clear();
		c.getItems().addAll("Any","News","Shopping","Social Media","Blog","Hobbies","Travel" );
		c.getCheckModel().clearChecks(); 
		c.getCheckModel().check(0); 
		c.getCheckModel().check("Any");
	}
	
	public void setContext(CheckComboBox<String> c) {
		if (!contextEnabled)
			return;
		contextEnabled = false;
		IndexedCheckModel<String> genderModel = c.getCheckModel();
		if (genderModel.getCheckedIndices().isEmpty()) {
			genderModel.check(0); 
		}
		else if (genderModel.isChecked(0)) {
			if (context.contains("Any")) { // already checked 
				if (genderModel.getCheckedIndices().size() == genderModel.getItemCount()) // they are all ticked so 
					setAnyContext(c);
				else if (genderModel.getCheckedIndices().size() > 1)
					genderModel.clearCheck(0);
			}
			else 
				setAnyContext(c);
		}
		else if (genderModel.getCheckedIndices().size() == genderModel.getItemCount()-1) { // they are all ticked so 
			setAnyContext(c);
		}
		setContext(genderModel.getCheckedItems());
		contextEnabled = true;
	}

	
	private void setGenderSQL() {
		genderSQL = "";
		if (gender.isEmpty() || gender.contains("Any") || (gender.contains("Female") && gender.contains("Male"))){
			genderSQL = "1";
		}
		else if (gender.contains("Male"))
			genderSQL = "gender = 0";
		else
			genderSQL = "gender = 1";   
	}
	
	private void setIncomeSQL() {
		incomeSQL = "";
		if (income.isEmpty() || income.contains("Any")) {
			incomeSQL = "1";
		}
		else {
			for (String s: income) {
				if (!incomeSQL.isEmpty())
					incomeSQL += " OR ";
				switch(s) {
				case "Low":
						incomeSQL += "INCOME=0";
						break;
				case "Medium":
						incomeSQL += "INCOME=1";
						break;
				case "High":
						incomeSQL += "INCOME=2";
						break;
				default:
					incomeSQL = "1";
						break;
				}
			}
		}
	}

	private void setAgeSQL() {
		ageSQL = "";
		if (age.isEmpty() || age.contains("Any")) {
			ageSQL = "1";
		}
		else {
			for (String s: age) {
				if (!ageSQL.isEmpty())
					ageSQL += " OR ";
				switch(s) {
					case "Less than 25":
							ageSQL += "AGE=0";
							break;
					case "25 to 34":
							ageSQL += "AGE=1";
							break;
					case "35 to 44":
							ageSQL += "AGE=2";
							break;
					case "45 to 54":
							ageSQL += "AGE=3";
							break;
					case "Greater than 55":
							ageSQL += "AGE=4";
							break;
				  
					default:
							ageSQL += "1 = 1";
							break;
				}
			}
		}
	}
		 
	 
	public String getSql() {
		return "(" + contextSQL + ") and (" + ageSQL + ") and (" + incomeSQL + ") and (" + genderSQL + ") and " + dateSQL;
	}
	public String getIncomeSQL() {
		return "(" + incomeSQL +")";
	}
	public String getGenderSQL() {
		 return "(" + genderSQL + ")";
	}	  
	public String getContextSQL() {
		return "(" + contextSQL + ")";
	}
	public String getAgeSQL() {
		return "(" + ageSQL + ")";
	}
	
	private void setContextSQL() {
		contextSQL = "";
		if (context.isEmpty() || context.contains("Any") ) {
			contextSQL = "1 = 1";
		}
		else {
			for (String s: context) {
				if (!contextSQL.isEmpty())
					contextSQL += " OR ";
				switch(s) {
					case "News":
							contextSQL += "CONTEXT=0";
							break;
					case "Shopping":
							contextSQL += "CONTEXT=1";
							break;
					case "Social Media":
							contextSQL += "CONTEXT=2";
							break;
					case "Blog":
							contextSQL += "CONTEXT=3";
							break;
					case "Hobbies":
							contextSQL += "CONTEXT=4";
							break;
					case "Travel":
							contextSQL += "CONTEXT=5";
							break;
					default:
							contextSQL += "1 = 1";
							break;
					}
				}
			}	 
	}

	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
		setDateSQL();
	}
	
	public void setDateSQL() {
		StringBuilder dateQuery = new StringBuilder("(");
		
		if(dateFrom != null)
			dateQuery.append("DATE >= '" + dateFrom.toString() + " 00:00:00'");
		else
			dateQuery.append("1");
		
		dateQuery.append(" AND ");
		
		if(dateTo != null)
			dateQuery.append("DATE < '" + dateTo.toString() + " 24:00:00'");
		else
			dateQuery.append("1");
		
		dateQuery.append(")");
		
		dateSQL = dateQuery.toString();
	}


	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
		setDateSQL();
	}
	
	//TODO: Either make this do something or delete it
	public void setDateFromSQL() {
		
	}
	
	/* Default constructor sets all fields to 'any', and leaves date range unrestricted */
	public Filter() {
		gender = new ArrayList<String>();
		age = new ArrayList<String>();
		income = new ArrayList<String>();
		context = new ArrayList<String>();
		
		setGender(FXCollections.observableArrayList("Any"));
		setAge(FXCollections.observableArrayList("Any"));
		setIncome(FXCollections.observableArrayList("Any"));
		setContext(FXCollections.observableArrayList("Any"));
		
		setDateSQL();	  
	}
}
