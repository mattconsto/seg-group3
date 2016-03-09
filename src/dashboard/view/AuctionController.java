package dashboard.view;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import org.controlsfx.control.CheckComboBox;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import dashboard.controller.*;
import dashboard.model.*;
/**
 * Auction Controller.
 */
public class AuctionController extends AnchorPane {
	private Main application;
	
	@FXML private MenuBar menu;
	@FXML private MenuItem importCampaign;
	@FXML private MenuItem close;
	/* The controlsFX checkComboBox is not supported in scenebuilder. To be able 
	   to open the view in scenebuilder replace org.controlsfx.control.CheckComboBox with ComboBox
	   The .fxml file also needs to be edited to remove the org.controlsfx.control.CheckComboBox
	 */
	@FXML private CheckComboBox<String> filterGender;
	@FXML private CheckComboBox<String> filterAge;
	@FXML private CheckComboBox<String> filterIncome;
	@FXML private CheckComboBox<String> filterContext;
	@FXML private ComboBox<String> filterMetrics;
	@FXML private DatePicker filterDateFrom;
	@FXML private DatePicker filterDateTo;
	@FXML private Button generateGraph;
	@FXML private LineChart<Date,Number> lineChart;
	@FXML private ComboBox<String> filterTime;
	@FXML private Label campaignName;
	@FXML private MenuItem openCampaign;
	@FXML private TableView<ObservableMetrics> tableResults;
	@FXML private TableColumn<ObservableMetrics, String> metricCol;
	@FXML private TableColumn<ObservableMetrics, String> resultCol;
	
	private ObservableList<ObservableMetrics> tableMetrics = FXCollections.observableArrayList();
	
	@FXML private MenuItem deleteCampaign;
	@FXML private MenuItem exportCampaign;
	@FXML private MenuItem printCampaign;
	@FXML private MenuItem mnuColour;
	@FXML private MenuItem mnuFont;
	@FXML private MenuItem mnuPaths;
	@FXML private MenuItem mnuUnits;
	@FXML private MenuItem mnuDefinitions;
	@FXML private MenuItem mnuSearch;
	@FXML private MenuItem mnuList;
	@FXML private MenuItem mnuAbout;
	@FXML private TextField txtBounceTime;
	@FXML private TextField txtBouncePages;
	@FXML private RadioButton rbByBounceTime;
	@FXML private ToggleGroup grBounce;
	@FXML private RadioButton rbByBouncePages;

	private Filter filter;

	public void setApp(Main application){
		this.application = application;
	}

	public void init() {
		filter = new Filter();

		filterDateFrom.setValue((LocalDate.of(2015,01,01)));
		filterDateTo.setValue((LocalDate.of(2015,01,14)));
		filterGender.getItems().addAll("Any","Female","Male");
		filterAge.getItems().addAll("Any","Less than 25","25 to 34","35 to 44","45 to 54","Greater than 55");
		filterIncome.getItems().addAll("Any","Low","Medium","High");
		filterContext.getItems().addAll("Any","News","Shopping","Social Media","Blog","Hobbies","Travel");
		filterGender.getCheckModel().check(0);
		filterAge.getCheckModel().check(0);
		filterContext.getCheckModel().check(0);
		filterIncome.getCheckModel().check(0);
		metricCol.setCellValueFactory(cellData -> cellData.getValue().descriptionProperty());
		resultCol.setCellValueFactory(cellData -> cellData.getValue().resultProperty());
		configureTable();  
		configureFilters();

		campaignName.setText(DatabaseConnection.getDbfile().replace(".db", ""));
		generateGraph.setDisable(false);
		//generateData(null);
	}
	
	private void configureFilters() {
		filterGender.getCheckModel().getCheckedItems().addListener(
			(ListChangeListener.Change<? extends String> c) -> filter.setGender(filterGender));
		filterAge.getCheckModel().getCheckedItems().addListener(
			(ListChangeListener.Change<? extends String> c) -> filter.setAge(filterAge));
		filterIncome.getCheckModel().getCheckedItems().addListener(
			(ListChangeListener.Change<? extends String> c) -> filter.setIncome(filterIncome));
		filterContext.getCheckModel().getCheckedItems().addListener(
			(ListChangeListener.Change<? extends String> c) -> filter.setContext(filterContext));
	}

	private final ListChangeListener<ObservableMetrics> tableSelectionChanged = (ListChangeListener.Change<? extends ObservableMetrics> c) -> {
		ObservableMetrics item = tableResults.getSelectionModel().getSelectedItem();
		if(item != null)
			updateGraph(item.getDescription());
	};

	// Configure the table widget: set up its column, and register the
	// selection changed listener.
	private void configureTable() {
		metricCol.setCellValueFactory(new PropertyValueFactory<>("description"));
		resultCol.setCellValueFactory(new PropertyValueFactory<>("result"));
		tableResults.setItems(tableMetrics);

		tableResults.getSelectionModel().getSelectedItems().addListener(tableSelectionChanged);
	}
	
	@FXML private void importCampaignAction(ActionEvent event) {}

	@FXML
	private void closeAction(ActionEvent event) {
		DatabaseConnection.closeConnection();
		application.getStage().close();
	}
	
	@FXML
	private void generateData(ActionEvent event) {
		filter.setDateFrom(filterDateFrom.getValue());
		filter.setDateTo(filterDateTo.getValue());
				
		lineChart.getData().clear();
		lineChart.getXAxis().setLabel(filterTime.getValue());  
		lineChart.getYAxis().setLabel(filterMetrics.getValue());

		lineChart.getData().clear();
		updateGraph(filterMetrics.getValue());
		
		// Cheap and nasty threading
		new Thread(new MetricsUpdater(tableMetrics, filter)).start();;
	}

	private void updateGraph(String metric) {
		lineChart.getYAxis().setLabel("Number");
		GraphConstructor constructor;

		switch(metric) {
			default:
			case "Bounces":            constructor = new BounceGraphConstructor(filter);            break;
			case "Impressions":        constructor = new ImpressionsGraphConstructor(filter);       break;
			case "Clicks":             constructor = new ClicksGraphConstructor(filter);            break;
			case "Unique Impressions": constructor = new UniqueImpressionsGraphConstructor(filter); break;
			case "Unique Clicks":      constructor = new UniqueClicksGraphConstructor(filter);      break;
			case "Conversions":        constructor = new ConversionGraphConstructor(filter);        break;
			case "CPC":                constructor = new CPCGraphConstructor(filter);               break;
			case "CPA":                constructor = new CPAGraphConstructor(filter);               break;
			case "CPM":                constructor = new CPMGraphConstructor(filter);               break;
			case "CTR":                constructor = new CTRGraphConstructor(filter);               break;
			case "Total Cost":         constructor = new TotalCostGraphConstructor(filter);         break;
			case "Bounce Rate":        constructor = new BounceRateGraphConstructor(filter);        break;
		}

		lineChart.setCreateSymbols(false);
		lineChart.setLegendVisible(true);
		
		try {
			Series<Date, Number> data = constructor.fetchGraph();
			data.setName(metric);
			lineChart.getData().add(data);
		} catch (SQLException e) {
			System.err.println("Unable to fetch data from database: " + e.getMessage());
		}
	}
	
	@FXML
	private void openCampaignAction(ActionEvent event) {
		FileChooser fChooser = new FileChooser();
		fChooser.setInitialDirectory(new File(System.getProperty("user.dir")));
		fChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Campaign files (*.db)", "*.db"));
		fChooser.setTitle("Select campaign" );
		File s = fChooser.showOpenDialog(application.getStage());
		if (s != null) {  
			DatabaseConnection.closeConnection();
			DatabaseConnection.setDbfile(s.getPath());

			campaignName.setText(s.getName().replace(".db", ""));
			generateGraph.setDisable(false); 
			generateData(null);
		}
	}   
}