package db.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class NodeAlreadyExistsException.
 */
@SuppressWarnings("serial")
public class NodeAlreadyExistsException extends RecordAlreadyExistsException{

  /**
   * Instantiates a new node already exists exception.
   */
  public NodeAlreadyExistsException() {
    super("Node record is already available");
  }

}
