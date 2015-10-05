package database.dao;

import db.exceptions.ConfigurationException;
import db.exceptions.ConfigurationNotFoundException;
import db.exceptions.Dbexception;
import model.Client;
import model.Node;
import model.TaskInstance;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public interface Taskinstancedao {
  
  public int addTaskInstanceInfo(TaskInstance taskInstance,Client client,
      Node node) throws SQLException,Dbexception,ConfigurationException, IOException;   
  
  public int addTaskInfo(TaskInstance taskInstance);
  
  public int delete(TaskInstance taskInstance) throws SQLException,
    Dbexception,ConfigurationException, IOException;   
  
  public int modify(TaskInstance taskInstance) throws SQLException, 
    Dbexception, ConfigurationNotFoundException, ConfigurationException, IOException;  
  
  public void setTaskstatus(int taskId, String taskStatus) throws SQLException,Dbexception, 
    ConfigurationNotFoundException, ConfigurationException, IOException;   
  
  public List<TaskInstance> getTasksbyclientid(int clientId) throws SQLException,Dbexception, 
    ConfigurationException, IOException;   
  
  public TaskInstance getTaskbyid(int taskId) throws SQLException,
    Dbexception,ConfigurationException, IOException;   
  
  public List<TaskInstance> getTasksbystatus(String taskStatus) throws SQLException,
    Dbexception,ConfigurationException, IOException;   
  
  public List<TaskInstance> getTasksbynodeid(int nodeId) throws SQLException,
    Dbexception,ConfigurationException, IOException;  
  
  public void assignNode(Node node, TaskInstance taskInstance) throws SQLException,
    Dbexception, ConfigurationNotFoundException, ConfigurationException;
}
