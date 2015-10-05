package db.status;

public class NodeState {
  
  /**
   * Gets the node state.
   *
   * @param state the state
   * @return the node state
   */
  public static int getNodeState(String state) {
    int stateValue = 0;
    if (state == null) {
      return stateValue;
    }
    
    switch (state.toLowerCase()) {
      case "available" : 
        stateValue = 1;
        break;
      case "busy" : 
        stateValue = 2;
        break;
      case "not_operational" : 
        stateValue = 3;
        break;
      default: 
        stateValue = 0;
        break;
    }
    return stateValue;
  }
}
