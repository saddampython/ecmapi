package db.exceptions;

@SuppressWarnings("serial")
public class ConfigurationLoadException extends ConfigurationException{

  /**
   * Instantiates a new configuration load exception.
   */
  public ConfigurationLoadException() {
    super("Failure loading Configuration files");
  }
}
