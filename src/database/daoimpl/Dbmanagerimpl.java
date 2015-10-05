package database.daoimpl;

import configuration.Tdsconfiguration;
import database.dao.Dbmanager;
import db.exceptions.ConfigurationException;
import db.exceptions.ConfigurationNotFoundException;
import db.exceptions.Dbconnectionexception;
import db.exceptions.Dbexception;
import db.exceptions.RecordAlreadyExistsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



// TODO: Auto-generated Javadoc
/**
 * The Class DBManagerImpl.
 */
public class Dbmanagerimpl implements Dbmanager {
  
  /** The tdscon. */
  static Tdsconfiguration tdscon = null;
  
  static {
   try {
      initDriver();
    } catch (ClassNotFoundException e) {
      //e.printStackTrace();
    }
  }

  /**
   * Inits the driver.
 * @throws ClassNotFoundException 
   */
  private static void initDriver() throws ClassNotFoundException {
    tdscon = Tdsconfiguration.getInstance();
    Class.forName(tdscon.getDriver());
  } 

  /* (non-Javadoc)
   * @see database.dao.Dbmanager#getConnection()
   */
  @Override
  public Connection getConnection() throws Dbconnectionexception, ConfigurationNotFoundException {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(tdscon.getDbconnectionstring());
    } catch (SQLException e) {
      if (e.getErrorCode() == 1045) {
        System.out.println("arju" + e);
        throw new Dbconnectionexception();
      }      
    } 
    return connection;
  }

  /* (non-Javadoc)
   * @see database.dao.Dbmanager#closeConnection(java.sql.Connection)
   */
  @Override
  public void closeConnection(Connection conn) throws SQLException {
    conn.close();    
  }
/** executeSQLQuery is used to modify do the operation "Select"
 * @see database.dao.Dbmanager#executeSqlquery(java.lang.String, java.sql.Connection)
 */
  @Override
  public ResultSet executeSqlquery(String query, Statement stmt) throws SQLException {
    return stmt.executeQuery(query);
  }

/** executeDMLQuery method is used for operations like
  * Insert,Delete,Modify,Alter
  * // Method used for dml query operations.
 * throws Dbconnectionexception 
 * throws ConfigurationNotFoundException  * @throws ConfigurationException 
 * @throws ConfigurationException 
 * @throws SQLException 
 * @throws RecordAlreadyExistsException 
  */

  @Override
  public int executeDmlquery(String query) throws Dbconnectionexception, 
    ConfigurationNotFoundException, SQLException, RecordAlreadyExistsException {
    Statement stmt = null;
    Connection connection = null;
    int num = 0;
    try {
      connection = getConnection();
      stmt = connection.createStatement();
     // num = stmt.executeUpdate(query);
      System.out.println(stmt.executeUpdate(query));
      return num;
    } catch (SQLException e) {
      if (e.getSQLState().equals("23000")) {
        throw new RecordAlreadyExistsException("Record already exists");
      }
    } finally {
      if (connection != null) {
        stmt.close();
        closeConnection(connection);
      }
    }
    return num;
  }
}

