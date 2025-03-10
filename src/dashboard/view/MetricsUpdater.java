package dashboard.view;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import javafx.collections.ObservableList;
import dashboard.model.BounceFilter;
import dashboard.model.Filter;
import dashboard.model.ObservableMetrics;
import java.sql.DriverManager;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.prefs.Preferences;

import javafx.scene.control.TableView;

public class MetricsUpdater  {
	private ObservableList<ObservableMetrics> table;
	private TableView<ObservableMetrics> tableView;
	private Filter                            filter;
	private int iFilter;
	private ExecutorService executor;

	private Preferences preferences = Preferences.userRoot();

	public MetricsUpdater() {
		try {
			executor = Executors.newCachedThreadPool(); // .newFixedThreadPool(15);
		} catch (Exception eofException) { // EOFException |InterruptedException
			System.err.println("Failed to start threading " + eofException.getMessage());
		} //
	}

	public void stop() {
		executor.shutdownNow();
	}

	public void runUpdater(ObservableList<ObservableMetrics> table, Filter filter, BounceFilter bounceFilter, int index,
			TableView<ObservableMetrics> t, Runnable call) {
		this.tableView = t;
		this.table = table;
		this.filter = filter;
		this.iFilter = index;
		
		
		Runnable callback = new Runnable() {
			int count = 12;
			@Override
			public synchronized void run() {
				System.out.println(count);
				if(--count == 0) {
					call.run();
				}
			}
		};
		
                WorkerThread s =   new WorkerThread("SELECT COUNT(*) AS Frequency FROM "
				+ "SERVER "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON SERVER.ID=IMPRESSIONS.ID "
				+ "WHERE " + bounceFilter.getSQL() + " AND " + filter.getSql().replace("DATE", "SERVER.ENTRYDATE")+ ";", 0, callback); 
              
                executor.execute(s);
                
                s =   new WorkerThread("SELECT COUNT(*) AS Frequency FROM "
				+ "CLICKS "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON CLICKS.ID=IMPRESSIONS.ID "
				+ "WHERE " + filter.getSql().replace("DATE", "CLICKS.DATE") + ";", 1, callback); 
                
                executor.execute(s);
               
                s = new WorkerThread( "SELECT COUNT(*) AS Frequency FROM "
				+ "SERVER "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON SERVER.ID=IMPRESSIONS.ID "
				+ "WHERE CONVERSION = 1 AND " + filter.getSql().replace("DATE", "ENTRYDATE") + ";", 2, callback);
           
                executor.execute(s);
                
                s = new WorkerThread("SELECT COUNT(*) AS Frequency FROM "
                		+ "IMPRESSIONS WHERE " +  filter.getSql() +";"  , 3, callback);
          
                executor.execute(s);
                  s = new WorkerThread("SELECT COUNT(DISTINCT CLICKS.ID) AS Frequency FROM "
				+ "CLICKS "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON CLICKS.ID=IMPRESSIONS.ID "
				+ "WHERE " + filter.getSql().replace("DATE", "CLICKS.DATE") + ";"  , 4, callback);
              
                executor.execute(s);
                  s = new WorkerThread(  "SELECT COUNT(DISTINCT ID) AS Frequency FROM "
                  		+ "IMPRESSIONS WHERE " +  filter.getSql() +";", 5, callback);
               
                executor.execute(s);
                  s = new WorkerThread( "SELECT CLICKCOST, IMPCOST FROM "
				+ "(SELECT SUM(CLICKS.COST) AS CLICKCOST FROM "
				+ "CLICKS "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON CLICKS.ID=IMPRESSIONS.ID "
				+ "WHERE " + filter.getSql().replace("DATE", "CLICKS.DATE") + ") "
				+ "JOIN "
				+ "(SELECT SUM(COST) AS IMPCOST FROM "
				+ "IMPRESSIONS "
				+ "WHERE " + filter.getSql() + ");" , 6, callback);
               
                executor.execute(s);
                  s = new WorkerThread( "SELECT NUMCLICKS, NUMIMPS FROM "
				+ "(SELECT COUNT(*) AS NUMCLICKS FROM "
				+ "CLICKS "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON CLICKS.ID=IMPRESSIONS.ID "
				+ "WHERE " + filter.getSql().replace("DATE", "CLICKS.DATE") +") "
				+ "JOIN"
				+ "(SELECT COUNT(*) AS NUMIMPS FROM "
				+ "IMPRESSIONS "
				+ "WHERE " + filter.getSql() +");" , 7, callback);
             
                executor.execute(s);
                  s = new WorkerThread( "SELECT CLICKCOST, IMPCOST, Frequency FROM "
				+ "(SELECT CLICKCOST, IMPCOST FROM "
				+ "(SELECT SUM(CLICKS.COST) AS CLICKCOST FROM "
				+ "CLICKS "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON CLICKS.ID=IMPRESSIONS.ID "
				+ "WHERE " + filter.getSql().replace("DATE", "CLICKS.DATE") + ") "
				+ "JOIN "
				+ "(SELECT SUM(COST) AS IMPCOST FROM "
				+ "IMPRESSIONS "
				+ "WHERE " + filter.getSql() +")) "
				+ "JOIN "
				+ "(SELECT COUNT(*) AS Frequency FROM "
				+ "SERVER "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON IMPRESSIONS.ID=SERVER.ID "
				+ "WHERE CONVERSION = 1 AND " + filter.getSql().replace("DATE", "ENTRYDATE") + ");" , 8, callback);
           
                executor.execute(s);
                  s = new WorkerThread( "SELECT CLICKCOST, IMPCOST, NUMCLICKS FROM "
				+ "(SELECT CLICKCOST, IMPCOST FROM "
				+ "(SELECT SUM(CLICKS.COST) AS CLICKCOST FROM "
				+ "CLICKS "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON CLICKS.ID=IMPRESSIONS.ID "
				+ "WHERE " + filter.getSql().replace("DATE", "CLICKS.DATE") + ") "
				+ "JOIN "
				+ "(SELECT SUM(COST) AS IMPCOST FROM "
				+ "IMPRESSIONS "
				+ "WHERE " + filter.getSql() +")) "
				+ "JOIN"
				+ "(SELECT COUNT(*) AS NUMCLICKS FROM "
				+ "CLICKS "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON CLICKS.ID=IMPRESSIONS.ID "
				+ "WHERE " + filter.getSql().replace("DATE", "CLICKS.DATE") +");", 9, callback);
             
                executor.execute(s);
                 s = new WorkerThread( "SELECT CLICKCOST, IMPCOST, NUMIMPS FROM"
				+ "(SELECT SUM(CLICKS.COST) AS CLICKCOST FROM "
				+ "CLICKS "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON CLICKS.ID=IMPRESSIONS.ID "
				+ "WHERE " + filter.getSql().replace("DATE", "CLICKS.DATE") +") "
				+ "JOIN "
				+ "(SELECT SUM(COST) AS IMPCOST FROM "
				+ "IMPRESSIONS "
				+ "WHERE " + filter.getSql() +") "
				+ "JOIN"
				+ "(SELECT COUNT(*) AS NUMIMPS FROM "
				+ "IMPRESSIONS "
				+ "WHERE " + filter.getSql() +");" , 10, callback);
            
                executor.execute(s);
                 s = new WorkerThread( "SELECT SUM(NUMCLICKS), SUM(NUMBOUNCES) FROM "
				+ "(SELECT COUNT(*) AS NUMBOUNCES FROM "
				+ "SERVER "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON SERVER.ID=IMPRESSIONS.ID "
				+ "WHERE " + bounceFilter.getSQL() + " AND " + filter.getSql().replace("DATE", "SERVER.ENTRYDATE")+ ") "
				+ "JOIN"
				+ "(SELECT COUNT(*) AS NUMCLICKS FROM "
				+ "CLICKS "
				+ "INNER JOIN "
				+ "(SELECT * FROM IMPRESSIONS GROUP BY ID) AS IMPRESSIONS "
				+ "ON CLICKS.ID=IMPRESSIONS.ID "
				+ "WHERE " + filter.getSql().replace("DATE", "CLICKS.DATE") +");" , 11, callback);

		executor.execute(s);

	}

	public class WorkerThread implements Runnable {
		private String query;
		private int index = 0;
		private Runnable callback;

		public WorkerThread(String qry, int i, Runnable callback) {
			query = qry;
			index = i;
			this.callback = callback;
		}

		@Override
		public void run() {
			DecimalFormatSymbols symbols = new DecimalFormat().getDecimalFormatSymbols();
			DecimalFormat intFormatter = new DecimalFormat("#" + symbols.getGroupingSeparator() + "###");
			DecimalFormat decFormatter = new DecimalFormat(
					"#" + symbols.getGroupingSeparator() + "###" + symbols.getDecimalSeparator() + "00");
			String currency = preferences.get("Currency_Symbol",
					new DecimalFormat().getDecimalFormatSymbols().getCurrencySymbol());
			Connection connection = null;

			try {
				if (connection == null || connection.isClosed()) {
					Class.forName("org.sqlite.JDBC");
					connection = DriverManager.getConnection("jdbc:sqlite:" + filter.getCampaign() + ".db");

					ResultSet results = connection.createStatement().executeQuery(query);
					if (results.next()) {
						switch (index) {

						case 7:
							table.get(7).setResults(iFilter,
									String.format("%.3f%%", results.getInt(1) / results.getFloat(2) * 100));
							break;
						case 6:
							table.get(6).setResults(iFilter,
									currency + decFormatter.format(results.getFloat(1) + results.getFloat(2)));

							break;
						case 8:
							table.get(8).setResults(iFilter, currency + decFormatter
									.format((results.getFloat(1) + results.getFloat(2)) / results.getInt(3)));
							;
							break;
						case 9:
							table.get(9).setResults(iFilter, currency + decFormatter
									.format((results.getFloat(1) + results.getFloat(2)) / results.getInt(3)));
							break;
						case 10:
							table.get(10).setResults(iFilter, currency + decFormatter.format(
									((results.getFloat(1) + results.getFloat(2)) / results.getFloat(3)) * 1000));
							break;
						case 11:
							table.get(11).setResults(iFilter,
									String.format("%.1f%%", results.getInt(2) / results.getFloat(1) * 100));
							break;
						default:
							table.get(index).setResults(iFilter, intFormatter.format(results.getDouble(1)));
							break;

						}
					}
					results.close();
					tableView.refresh();
					tableView.scrollToColumnIndex(iFilter);
					connection.close();
				}
			} catch (ClassNotFoundException exception) {
				System.err.println("SQLite JDBC Library no found!");
				System.exit(1);
			} catch (SQLException exception) {
				System.err.println("SQLite Open error");
				System.err.println(exception.getMessage());
				System.exit(1);
			}

			if(callback != null) callback.run();
		}

	}
}
