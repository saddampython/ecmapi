package database.dao;

import db.exceptions.ConfigurationException;
import db.exceptions.Dbexception;
import model.Client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Clientdao{

  public int addclient(Client client)throws SQLException, 
      Dbexception,ConfigurationException, IOException;

  public int modifyClient(Client client) throws SQLException, 
      Dbexception,ConfigurationException, IOException;

  public int deleteClient(Client client) throws Dbexception, Exception,ConfigurationException;

  public List<Client> getClients() throws SQLException,Dbexception, ConfigurationException, IOException;

}
