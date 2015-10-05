package db.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientNotFoundException.
 */
@SuppressWarnings("serial")
public class ClientNotFoundException extends RecordNotFoundException {

  /**
   * Instantiates a new client not found exception.
   */
  public ClientNotFoundException() {
    super("Client record not available");
  }

}
