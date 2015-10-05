package db.exceptions;

@SuppressWarnings("serial")
public class ConfigurationNotFoundException extends ConfigurationException{

  /**
   * Instantiates a new configuration not found exception.
   */
  public ConfigurationNotFoundException() {
    super("Username or Password details not found");
  }
}
