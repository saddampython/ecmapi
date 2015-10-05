package model;

// TODO: Auto-generated Javadoc
//taskinstance class contains getter and setter method.

/**
 * The Class TaskInstance.
 */
public class TaskInstance {

  /** The task name. */
  private String taskName; 

  /** The task parameters. */
  private String taskParameters;

  /** The task exe path. */
  private String taskExePath;

  /** The task state. */
  private String taskState;  

  /** The task status. */
  private String taskStatus;  

  /** The task result. */
  private String taskResult;  

  /** The error message. */
  private String errorMessage;   

  /** The id. */
  private int id;

  
  /**
   * Sets the task name.
   *
   * @param taskName the new task name
   */
  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

   
  /**
   * Gets the task name.
   *
   * @return the task name
   */
  public String getTaskName() {
    return taskName;
  }

  /**
   * Gets the task parameters.
   *
   * @return the task parameters
   */
  public String getTaskParameters() {
    return taskParameters;
  }
   
  /**
   * Sets the task parameters.
   *
   * @param taskParameters the new task parameters
   */
  public void setTaskParameters(String taskParameters) {
    this.taskParameters = taskParameters;
  }
   
  /**
   * Sets the task exe path.
   *
   * @param taskExePath the new task exe path
   */
  public void setTaskExePath(String taskExePath) {
    this.taskExePath = taskExePath;
  }

   
  /**
   * Gets the task exe path.
   *
   * @return the task exe path
   */
  public String getTaskExePath() {
    return taskExePath;
  }

    
  /**
   * Sets the task state.
   *
   * @param taskState the new task state
   */
  public void setTaskState(String taskState) {
    this.taskState = taskState;
  }
    
  /**
   * Gets the task state.
   *
   * @return the task state
   */
  public String getTaskState() {
    return taskState;
  }
    
  /**
   * Sets the task staus.
   *
   * @param taskStatus the new task staus
   */
  public void setTaskStaus(String taskStatus) {
    this.taskState = taskStatus;
  }

  /**
   * Gets the task status.
   *
   * @return the task status
   */
  public String getTaskStatus() {
    return taskStatus;
  }
  
  /**
   * Sets the task result.
   *
   * @param taskResult the new task result
   */
  public void setTaskResult(String taskResult) {
    this.taskResult = taskResult;
  }

  /**
   * Gets the task result.
   *
   * @return the task result
   */
  public String getTaskResult() {
    return taskResult;
  }

  /**
   * Sets the error message.
   *
   * @param errorMessage the new error message
   */
  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * Gets the error message.
   *
   * @return the error message
   */
  public String getErrorMessage() {        
    return errorMessage;
  }

    
  /**
   * Sets the id.
   *
   * @param id the new id
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the id.
   *
   * @return the id
   */
  public int getId() {
    return id;
  }
}
