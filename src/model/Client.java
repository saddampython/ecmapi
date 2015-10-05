package model;

// TODO: Auto-generated Javadoc
//Client class-contains getter and setter methods.

/**
 * The Class Client.
 */
public class Client {

  /** The hostname. */
  private String hostname;
  
  /** The username. */
  private String username;
  
  /** The client id. */
  private int clientId;

  /**
   * Gets the hostname.
   *
   * @return the hostname
   */
  public String getHostname() {
    return hostname;  
  }

  /**
   * Sets the hostname.
   *
   * @param hostname the new hostname
   */
  public void setHostname(String hostname) {
    this.hostname = hostname;  
  }

  /**
   * Gets the username.
   *
   * @return the username
   */
  public String getUsername() {  
    return username;  
  }

  /**
   * Sets the username.
   *
   * @param username the new username
   */
  public void setUsername(String username) {  
    this.username = username;  
  }

  /**
   * Gets the client id.
   *
   * @return the client id
   */
  public int getClientId() {  
    return clientId;  
  }

  /**
   * Sets the client id.
   *
   * @param clientId the new client id
   */
  public void setClientId(int clientId) {  
    this.clientId = clientId;  
  }

}
