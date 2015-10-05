package database.daoimpl;

import database.dao.Dbmanager;
import database.dao.Dbmanagerfactory;
import database.dao.Taskinstancedao;
import db.exceptions.ConfigurationException;
import db.exceptions.ConfigurationNotFoundException;
import db.exceptions.Dbconnectionexception;
import db.exceptions.Dbexception;
import db.exceptions.RecordAlreadyExistsException;
import db.exceptions.TaskAlreadyExistsException;
import db.exceptions.TaskNotFoundException;
import db.status.TaskState;
import db.status.TaskStatus;
import loggertds.Loger;
import loggertds.Logmanager;
import model.Client;
import model.Node;
import model.TaskInstance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Class TaskInstanceDaoImpl.
 */
public class TaskInstanceDaoImpl implements Taskinstancedao{

  static Loger logger = null;

  static {
    initilize();
  }

  public static void initilize() {
    logger = Logmanager.getLogger();
  }
  /* (non-Javadoc)
   * @see database.dao.Taskinstancedao#add(model.TaskInstance)
   */
  @Override
  public int addTaskInstanceInfo(TaskInstance taskInstance,Client client,
      Node node) throws SQLException, Dbexception, ConfigurationException, IOException {
    Dbmanagerfactory databaseoperation = null;
    int rows = 0;
    try {
      databaseoperation = DbmanagerfactoryImpl.getInstance();
      Dbmanager db = databaseoperation.getDbmanager();
      System.out.println("Connection closed here4");
      
      logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
				"Adding taskInstance Record");
      
      String sql = String.format("INSERT INTO taskinstance(taskStates,taskStatus,"
          + "taskErrorMessage,taskResult,"
          + "taskParameters,client_id,node_id,task_id) "
          + "VALUES (%d,%d,'%s','%s','%s',%d,%d,%d)",
          TaskState.getTaskState(taskInstance.getTaskState()),
          TaskStatus.getTaskStatus(taskInstance.getTaskStatus()),
          taskInstance.getErrorMessage(),taskInstance.getTaskResult(),
          taskInstance.getTaskParameters(),client.getClientId(),
          node.getNodeId(),taskInstance.getId());

      rows = db.executeDmlquery(sql);
      return rows;
    } catch (RecordAlreadyExistsException e) {
      System.out.println("Error" + e);
      throw new TaskAlreadyExistsException();
    } 
   
  }
  
  /* (non-Javadoc)
   * @see database.dao.Taskinstancedao#addTaskInfo(model.TaskInstance)
   */ /*
  public int addTaskInfo(TaskInstance taskInstance) {
    Dbmanagerfactory databaseoperation = null;
    int rows = 0;
    try {
      databaseoperation = DbmanagerfactoryImpl.getInstance();
      Dbmanager db = databaseoperation.getDbmanager();
      String sql = String.format("INSERT INTO task(id,taskname,taskexepath) "
          + "VALUES (%d,'%s','%s')",taskInstance.getId(),taskInstance.getTaskName(),
          taskInstance.getTaskExePath());
      rows = db.executeDmlquery(sql);
      if ( rows == 0) {
        throw new TaskAlreadyExistsException();
      }
      return rows;
    } catch (Exception e) {
      System.out.println("Error akshay:" + e);
    }
    return rows;
  }
*/
  /* (non-Javadoc)
   * @see database.dao.Taskinstancedao#delete(model.TaskInstance)
   */
  @Override
  public int delete(TaskInstance taskInstance) throws SQLException, 
    Dbexception, ConfigurationException, IOException {
    Dbmanagerfactory databaseoperation = null;
    int rows = 0;
    
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    Dbmanager db = databaseoperation.getDbmanager();
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Deleting taskInstance Record");
    String sql = String.format("DELETE FROM TaskInstance WHERE id = %d ",taskInstance.getId());
    rows = db.executeDmlquery(sql);
    if ( rows == 0) {
      throw new TaskNotFoundException();
    }
    return rows;    
  }

  /* (non-Javadoc)
   * @see database.dao.Taskinstancedao#setTaskstatus(int, int)
   */
  @Override
  public void setTaskstatus(int taskId, String taskStatus) 
    throws Dbexception, ConfigurationException, SQLException, IOException {
    Dbmanagerfactory databaseoperation = null;
    
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    Dbmanager db = databaseoperation.getDbmanager();
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Updating taskInstance Record");
    String sql = String.format("UPDATE TaskInstance SET taskState='" 
        + TaskStatus.getTaskStatus(taskStatus) + "' "
        + "WHERE id ='" + taskId + "'");
    if (db.executeDmlquery(sql) == 0) {
      throw new TaskNotFoundException();
    }     
  }

  /* (non-Javadoc)
   * @see database.dao.Taskinstancedao#getTasksbyclientid(int)
   */
  @SuppressWarnings("unused")
  @Override
  public List<TaskInstance> getTasksbyclientid(int clientId) throws SQLException, 
    Dbexception, ConfigurationException, IOException {

    List<TaskInstance> tasks = null;
    TaskInstance task = null;
    Dbmanagerfactory databaseoperation = null;
    Dbmanager db = null;
    ResultSet rs = null;
    Connection conn = null;
    Statement stmt = null;

    tasks = new ArrayList<TaskInstance>();
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    db = databaseoperation.getDbmanager();
    String sql = "SELECT * FROM TaskInstance where clientid='" + clientId + "'";
    conn = db.getConnection();
    stmt = conn.createStatement();
    rs = db.executeSqlquery(sql,stmt);
      
    while (rs.next()) {
      task = new TaskInstance();
      task.setId(rs.getInt("id"));
      task.setTaskName(rs.getString("taskName"));
      tasks.add(task);
    }
    
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Getting taskInstance Record");
    
    if (tasks == null) {
      throw new TaskNotFoundException();
    }
    
    if (conn != null) {
      stmt.close();
      rs.close();
      db.closeConnection(conn);
    }   
    return tasks;
  }

/**
 * For the select query operation we will use the ResultSet and 
 * then we will close the database connection.
 * otherwise,we will get a error while returning the ResultSet from DMManagerImpl class.
 *
 * @param taskId the task id
 * @return the taskbyid
 * @throws SQLException the SQL exception
 * @throws Dbexception 
 * @throws ConfigurationException 
 * @throws IOException 
 */
  @SuppressWarnings("unused")
  @Override
  public TaskInstance getTaskbyid(int taskId) throws SQLException, 
    Dbexception, ConfigurationException, IOException {

    List<TaskInstance> tasks = null;
    TaskInstance task = null;
    Dbmanagerfactory databaseoperation = null;
    Dbmanager db = null;
    ResultSet rs = null;
    Connection conn = null;
    Statement stmt = null;

    tasks = new ArrayList<TaskInstance>();
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    db = databaseoperation.getDbmanager();
    String sql = "SELECT * FROM TaskInstance where task_id='" + taskId + "'";
    conn = db.getConnection();
    stmt = conn.createStatement();
    rs = db.executeSqlquery(sql,stmt);
      
    while (rs.next()) {
      task = new TaskInstance();
      task.setId(rs.getInt("id"));
      task.setTaskName(rs.getString("taskName"));
      tasks.add(task);
    }
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Adding task by id");
    if (tasks == null) {
      throw new TaskNotFoundException();
    }
    if (conn != null) {
      stmt.close();
      rs.close();
      db.closeConnection(conn);
    }
    return task;
  }

  /* (non-Javadoc)
   * @see database.dao.Taskinstancedao#getTasksbystatus(int)
   */
  @SuppressWarnings("unused")
@Override
  public List<TaskInstance> getTasksbystatus(String taskStatus) throws SQLException, 
    Dbexception, ConfigurationException, IOException {

    List<TaskInstance> tasks = null;
    TaskInstance task = null;
    Dbmanagerfactory databaseoperation = null;
    Dbmanager db = null;
    ResultSet rs = null;
    Connection conn = null;
    Statement stmt = null;

    tasks = new ArrayList<TaskInstance>();
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    db = databaseoperation.getDbmanager();
    String sql = "SELECT * FROM TaskInstance where "
        + "status='" + TaskStatus.getTaskStatus(taskStatus) + "'";
    conn = db.getConnection();
    stmt = conn.createStatement();
    rs = db.executeSqlquery(sql,stmt);

    while (rs.next()) {
      task = new TaskInstance();
      task.setId(rs.getInt("id"));
      task.setTaskName(rs.getString("taskName"));
      tasks.add(task);
    }
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Task by satus");
    if (tasks == null) {
      throw new TaskNotFoundException();
    }      
    if (conn != null) {
      stmt.close();
      rs.close();
      db.closeConnection(conn);
    }
    return tasks;
  }

  /* (non-Javadoc)
   * @see database.dao.Taskinstancedao#getTasksbynodeid(int)
   */
  @SuppressWarnings("unused")
  @Override
  public List<TaskInstance> getTasksbynodeid(int nodeId) throws SQLException, 
    Dbexception, ConfigurationException, IOException {

    List<TaskInstance> tasks = null;
    TaskInstance task = null;
    Dbmanagerfactory databaseoperation = null;
    Dbmanager db = null;
    ResultSet rs = null;
    Connection conn = null;
    Statement stmt = null;

    tasks = new ArrayList<TaskInstance>();
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    db = databaseoperation.getDbmanager();
    String sql = "SELECT * FROM TaskInstance where nodeid='" + nodeId + "'";
    conn = db.getConnection();
    stmt = conn.createStatement();
    rs = db.executeSqlquery(sql,stmt);      
    while (rs.next()) {
      task = new TaskInstance();
      task.setId(rs.getInt("id"));
      task.setTaskName(rs.getString("taskName"));
      tasks.add(task);
    }
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Task record by node id");
    if (tasks == null) {
      throw new TaskNotFoundException();
    }
     
    if (conn != null) {
      stmt.close();
      rs.close();
      db.closeConnection(conn);
    }
    return tasks;
  }

  /* (non-Javadoc)
   * @see database.dao.Taskinstancedao#assignNode(model.Node, model.TaskInstance)
   */
  @Override
  public void assignNode(Node node, TaskInstance taskInstance) throws 
  Dbexception, ConfigurationException, SQLException {

    Dbmanagerfactory databaseoperation = null;
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    Dbmanager db = databaseoperation.getDbmanager();
    String sql = String.format(
        "UPDATE TaskInstance SET node_id='" + node.getNodeId() + "' "
        + "WHERE id ='" + taskInstance.getId() + "' ");
    if (db.executeDmlquery(sql) == 0) {
      throw new TaskNotFoundException();
    }
  }

  /* (non-Javadoc)
   * @see database.dao.Taskinstancedao#modify(model.TaskInstance)
   */
  @Override
  public int modify(TaskInstance taskInstance) throws SQLException,
    Dbexception, ConfigurationException, IOException {
    Dbmanagerfactory databaseoperation = null;
    int rows = 0;
    databaseoperation = DbmanagerfactoryImpl.getInstance();
    Dbmanager db = databaseoperation.getDbmanager();
    logger.logInfo(this.getClass().getName(),Thread.currentThread().getStackTrace()[1].getMethodName(), 
			"Modifying task");
    String sql = String.format(
        "UPDATE taskinstance SET taskState ='" + taskInstance.getTaskState() + "',"
        + "taskResult='" + taskInstance.getTaskResult() + "' "
        + "WHERE id = '" + taskInstance.getId() + "' ");
    rows = db.executeDmlquery(sql);
    if ( rows == 0) {
      throw new TaskNotFoundException();
    }
    return rows;   
  }

@Override
public int addTaskInfo(TaskInstance taskInstance) {
	// TODO Auto-generated method stub
	return 0;
}

}

