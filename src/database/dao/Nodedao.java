package database.dao;

import db.exceptions.ConfigurationException;
import db.exceptions.ConfigurationNotFoundException;
import db.exceptions.Dbexception;
import model.Node;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Nodedao {

  public int addNode(Node node) throws SQLException, Dbexception,ConfigurationException, IOException;
  
  public int modifyNode(Node node) throws SQLException, 
    Dbexception, ConfigurationNotFoundException, ConfigurationException, IOException;
  
  public int deleteNode(Node node) throws SQLException, Dbexception,ConfigurationException, IOException;
  
  public List<Node> getAvailablenodes() throws SQLException,
    Dbexception,ConfigurationException, IOException;
  
  public List<Node> getAllnodes() throws SQLException,Dbexception,ConfigurationException, IOException;
}
