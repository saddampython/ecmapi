package db.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskAlreadyExistsException.
 */
@SuppressWarnings("serial")
public class TaskAlreadyExistsException extends RecordAlreadyExistsException{

  /**
   * Instantiates a new task already exists exception.
   */
  public TaskAlreadyExistsException() {
    super("Task record is already available");
  }
}

