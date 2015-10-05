package database.daoimpl;


import database.dao.Dbmanager;
import database.dao.Dbmanagerfactory;
import database.dao.Nodedao;
import db.exceptions.ConfigurationException;
import db.exceptions.Dbexception;
import db.exceptions.NodeAlreadyExistsException;
import db.exceptions.NodeNotFoundException;
import db.exceptions.RecordAlreadyExistsException;
import db.status.NodeState;
import loggertds.Loger;
import loggertds.Logmanager;
import model.Node;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



// TODO: Auto-generated Javadoc
/**
 * The Class Nodedaoimpl.
 */
public class Nodedaoimpl implements Nodedao {

/**
 *  NodeDAOImpl implements the NodeDAO interface.
 *  Performs the Insert,Delete,Modify,GetAvailableNodes,GetAllNodes
 *   operation.
 *
 * @param node the node
 * @throws Dbexception the dbexception
 * @throws ConfigurationException 
 * @throws SQLException 
 */
  static Loger logger = null;

  static {
    initilize();
  }
  
  public static void initilize() {
    logger = Logmanager.getLogger();
  }
  
  @Override
  public int addNode(Node node) throws Dbexception, SQLException, ConfigurationException, IOException {

    Dbmanagerfactory databaseoperation = null;
    int rows = 0;
    try {
      databaseoperation = DbmanagerfactoryImpl.getInstance();
      Dbmanager db = databaseoperation.getDbmanager();
      logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
							"Adding Node Record");
      String sql = String.format("INSERT INTO node(port,status,hostname) VALUES(%d,%d,'%s')",
          node.getNodePort(),NodeState.getNodeState(node.getNodeStatus()),node.getNodeHostname());
      rows = db.executeDmlquery(sql);
      return rows;
    } catch (RecordAlreadyExistsException e) {
      System.out.println("Error" + e);
      throw new NodeAlreadyExistsException();
    }   
  }

  /* (non-Javadoc)
   * @see database.dao.Nodedao#modifyNode(model.Node)
   */
  @Override
  public int modifyNode(Node node) throws Dbexception, ConfigurationException, SQLException, IOException {

    Dbmanagerfactory databaseoperation = null;
    int rows = 0;
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    Dbmanager db = databaseoperation.getDbmanager();
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Modifing Node Record");
    String sql = String.format("UPDATE node SET port='"
        + node.getNodePort() + "',status='" + NodeState.getNodeState(node.getNodeStatus())
        + "',hostname='" + node.getNodeHostname()
        + "' WHERE nodeid ='" + node.getNodeId() + "' ");
    rows = db.executeDmlquery(sql);
    if ( rows == 0) {
      throw new NodeNotFoundException();
    }
    return rows;
  }

  /* (non-Javadoc)
   * @see database.dao.Nodedao#deleteNode(model.Node)
   */
  @Override
  public int deleteNode(Node node) throws Dbexception,SQLException, ConfigurationException, IOException {

    Dbmanagerfactory databaseoperation = null;
    int rows = 0;
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    Dbmanager db = databaseoperation.getDbmanager();
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Deleting Node Record");
    String sql = String.format("DELETE FROM node WHERE nodeid = %d ",node.getNodeId());
    rows = db.executeDmlquery(sql);
    if ( rows == 0) {
      throw new NodeNotFoundException();
    }
    return rows;    
  }

  /* (non-Javadoc)
   * @see database.dao.Nodedao#getAvailablenodes()
   */
  @SuppressWarnings("unused")
  @Override
  public List<Node> getAvailablenodes() throws SQLException, 
    Dbexception, ConfigurationException, IOException {
    
    List<Node> nodes = null;
    Dbmanagerfactory databaseoperation = null;
    Connection conn = null;
    Dbmanager db = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    nodes = new ArrayList<Node>();
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    db = databaseoperation.getDbmanager();
    conn = db.getConnection();
    stmt = conn.createStatement();
    String sql = "SELECT * FROM node where status=1";
    rs = db.executeSqlquery(sql,stmt);
      
    while (rs.next()) {
      Node node = new Node();
      node.setNodeId(rs.getInt("nodeID"));
      node.setNodeHostame(rs.getString("hostname"));
      node.setNodePort(rs.getInt("port"));
      nodes.add(node);
    }
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Getting Available Node Record");
    
    if (nodes == null) {
      throw new NodeNotFoundException();
    }
    if (conn == null) {
      stmt.close();
      rs.close();
      db.closeConnection(conn);
    }    
    return nodes;
  }

/**
 * For the select query operation we will use the ResultSet and then we will
 * close the database connection.
 * otherwise,we will get a error while returning the ResultSet from
 * DMManagerImpl class.
 *
 * @return the allnodes
 * @throws SQLException the SQL exception
 * @throws Dbexception the dbexception
 * @throws ConfigurationException 
 * @throws IOException 
 */
  
  @SuppressWarnings("unused")
  @Override
  public List<Node> getAllnodes() throws SQLException, Dbexception, ConfigurationException, IOException {

    List<Node> nodes = null;
    Dbmanagerfactory databaseoperation = null;
    Dbmanager db = null;
    ResultSet rs = null;
    Statement stmt = null;
    Connection conn = null;

    nodes = new ArrayList<Node>();
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    db = databaseoperation.getDbmanager();
    conn = db.getConnection();
    stmt = conn.createStatement();
    String sql = "SELECT * FROM node";
    rs = db.executeSqlquery(sql,stmt);

    while (rs.next()) {
      Node node = new Node();
      node.setNodeId(rs.getInt("nodeID"));
      node.setNodeHostame(rs.getString("hostname"));
      node.setNodePort(rs.getInt("port"));
      nodes.add(node);
    }
    if (nodes == null) {
      throw new NodeNotFoundException();
    }
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Getting All Node Record");
    
    if (conn != null) {
      stmt.close();
      rs.close();
      db.closeConnection(conn);
    }
    return nodes;
  }
}
