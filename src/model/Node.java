package model;

// TODO: Auto-generated Javadoc
//Node class-contains getter and setter methods.

/**
 * The Class Node.
 */
public class Node {

  /** The hostname. */
  private String hostname;

  /** The port. */
  private int port;

  /** The status. */
  private String status;

  /** The nodeid. */
  private int nodeid;

  /** The current task. */
  public TaskInstance currentTask;

  /**
   * Execute task.
   *
   * @param currentTask the current task
   */
  public void executeTask(TaskInstance currentTask){

  }

  /**
   * Gets the node hostname.
   *
   * @return the node hostname
   */
  public String getNodeHostname() {
    return hostname;
  }

  /**
   * Sets the node hostame.
   *
   * @param hostname the new node hostame
   */
  public void setNodeHostame(String hostname) {
    this.hostname = hostname;
  }

  /**
   * Gets the node port.
   *
   * @return the node port
   */
  public int getNodePort() {
    return port;
  }

  /**
   * Sets the node port.
   *
   * @param port the new node port
   */
  public void setNodePort(int port) {
    this.port = port;
  }

  /**
   * Gets the node status.
   *
   * @return the node status
   */
  public String getNodeStatus() {
    return status;
  }

  /**
   * Sets the node status.
   *
   * @param status the new node status
   */
  public void setNodeStatus(String status) {
    this.status = status;
  }

  /**
   * Gets the node id.
   *
   * @return the node id
   */
  public int getNodeId() {
    return nodeid;
  }

  /**
   * Sets the node id.
   *
   * @param nodeid the new node id
   */
  public void setNodeId(int nodeid) {
    this.nodeid = nodeid;
  }

}
