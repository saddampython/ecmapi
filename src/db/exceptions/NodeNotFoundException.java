package db.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class NodeNotFoundException.
 */
@SuppressWarnings("serial")
public class NodeNotFoundException extends RecordNotFoundException{
  
  /**
   * Instantiates a new node not found exception.
   */
  public NodeNotFoundException() {
    super("Node not available");
  }
}
