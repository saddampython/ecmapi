package database.daoimpl;

import database.dao.Clientdao;
import database.dao.Dbmanager;
import database.dao.Dbmanagerfactory;
import db.exceptions.ClientAlreadyExistsException;
import db.exceptions.ClientNotFoundException;
import db.exceptions.ConfigurationException;
import db.exceptions.Dbexception;
import db.exceptions.RecordAlreadyExistsException;
import loggertds.Loger;
import loggertds.Logmanager;
import model.Client;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



// TODO: Auto-generated Javadoc
/**
 * The Class ClientDaoImpl.
 */
public class ClientDaoImpl implements Clientdao {

/* (non-Javadoc)
 * @see database.dao.Clientdao#addclient(model.Client)
 */
  static Loger logger = null;

  static {
    initilize();
  }
  
  public static void initilize() {
    logger = Logmanager.getLogger();
  }
  
  @Override
  public int addclient(Client client) throws ConfigurationException,SQLException,
        Dbexception, IOException {
    Dbmanagerfactory databaseoperation = null;
    int rows = 0;
    try {
      databaseoperation = DbmanagerfactoryImpl.getInstance();
      Dbmanager db = databaseoperation.getDbmanager();
      logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(),
          "Adding Client Record");
      String sql = String.format("INSERT INTO client(username,hostname) VALUES('%s','%s')",
          client.getUsername(), client.getHostname());
      rows = db.executeDmlquery(sql);
      return rows;
    } catch (RecordAlreadyExistsException e) {
      System.out.println("Error" + e);
      throw new ClientAlreadyExistsException();
    }   
  }

  /* (non-Javadoc)
   * @see database.dao.Clientdao#modifyClient(model.Client)
   */
  @Override
  public int modifyClient(Client client) throws Dbexception,SQLException, ConfigurationException, IOException {
    Dbmanagerfactory databaseoperation = null;
    int rows = 0;
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    Dbmanager db = databaseoperation.getDbmanager();
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
      "Editing Client Record");
    String sql = String.format("UPDATE client SET username ='"
        + client.getUsername() + "',hostname='"
        + client.getHostname() + "' WHERE clientid = '"
        + client.getClientId() + "' ");
    rows = db.executeDmlquery(sql);
    if ( rows == 0 ) {
      throw new ClientNotFoundException();
    }
    return rows;       
  }

  /* (non-Javadoc)
   * @see database.dao.Clientdao#deleteClient(model.Client)
   */
  @Override
  public int deleteClient(Client client) throws Dbexception, ConfigurationException, SQLException, IOException {
    Dbmanagerfactory databaseoperation = null;
    int rows = 0;
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    Dbmanager db = databaseoperation.getDbmanager();
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(),
    		"Deleting Client Record");
    String sql = String.format(
        "DELETE FROM client WHERE clientid = %d ",
        client.getClientId());
    rows = db.executeDmlquery(sql);
    if ( rows == 0) {
      throw new ClientNotFoundException();
    }
    return rows;    
  }
/*
 *For the select query operation we will use the ResultSet and then we will
 *close the database connection.
 *otherwise,we will get a error while returning the ResultSet from
 *DMManagerImpl class.
 */
/* (non-Javadoc)
 * @see database.dao.Clientdao#getClients()
 */

  @Override
  public List<Client> getClients() throws SQLException,Dbexception, ConfigurationException, IOException {
    List<Client> clients = null;
    Dbmanagerfactory databaseoperation = null;
    Dbmanager db = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
   
    clients = new ArrayList<Client>();
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    db = databaseoperation.getDbmanager();
    conn = db.getConnection();
    stmt = conn.createStatement();
    String sql = "SELECT * FROM client";
    rs = db.executeSqlquery(sql,stmt);
    
    while (rs.next()) {
      System.out.println(rs.getInt("clientid"));
      System.out.println(rs.getString("hostname"));
      System.out.println(rs.getString("username"));
      Client client = new Client();
      client.setClientId(rs.getInt("clientid"));
      client.setHostname(rs.getString("hostname"));
      client.setUsername(rs.getString("username"));
      clients.add(client);
    }
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(),
    		"Getting Client Records");
    if (clients.isEmpty()) {
      throw new ClientNotFoundException();
    }
   
    if (conn != null) {
      stmt.close();
      rs.close();
      db.closeConnection(conn);
    }
    return clients;
  }
}
