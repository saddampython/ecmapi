package db.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientAlreadyExistsException.
 */
@SuppressWarnings("serial")
public class ClientAlreadyExistsException extends RecordAlreadyExistsException{

  /**
   * Instantiates a new client already exists exception.
   */
  public ClientAlreadyExistsException()  {
    super("Client record is already available");
  }

}

