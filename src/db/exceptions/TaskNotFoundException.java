package db.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskNotFoundException.
 */
@SuppressWarnings("serial")
public class TaskNotFoundException extends RecordNotFoundException{

  /**
   * Instantiates a new task not found exception.
   */
  public TaskNotFoundException() {
    super("Task not available");
  }
}
