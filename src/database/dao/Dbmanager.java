package database.dao;

import db.exceptions.ConfigurationException;
import db.exceptions.ConfigurationNotFoundException;
import db.exceptions.Dbconnectionexception;
import db.exceptions.Dbexception;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public interface Dbmanager {

  public Connection getConnection() throws Dbexception,SQLException, ConfigurationException;

  public void closeConnection(Connection conn) throws SQLException;

  public ResultSet executeSqlquery(String query,Statement stmt) throws SQLException;

  public int executeDmlquery(String query) throws SQLException, 
    Dbconnectionexception, Dbexception, ConfigurationNotFoundException, ConfigurationException;

}
