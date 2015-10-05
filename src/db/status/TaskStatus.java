package db.status;

public class TaskStatus {
  
  /**
   * Gets the task status.
   *
   * @param state the state
   * @return the task status
   */
  public static int getTaskStatus(String state) {
    int stateValue = 0;
    if (state == null) {
      return stateValue;
    }

    switch (state.toLowerCase()) {
      case "success" : 
        stateValue = 1;
        break;
      case "failed" : 
        stateValue = 2;
        break;
      default: 
        stateValue = 0;
        break;
    }
    return stateValue;
  }
}
