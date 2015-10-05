package db.status;

public class TaskState {

  /**
   * Gets the task state.
   *
   * @param state the state
   * @return the task state
   */
  public static int getTaskState(String state) {
    int stateValue = 0;
    if (state == null) {
      return stateValue;
    }

    switch (state.toLowerCase()) {
      case "pending" : 
        stateValue = 1;
        break;
      case "in_progress" : 
        stateValue = 2;
        break;
      case "completed" : 
        stateValue = 3;
        break;
      default: 
        stateValue = 0;
        break;
    }
    return stateValue;
  }
}
