package db.exceptions;

@SuppressWarnings("serial")
public class ConfigurationException extends Exception{

  /**
   * Instantiates a new configuration exception.
   *
   * @param message the message
   */
  ConfigurationException(String message) {
    super(message);
  }

}
