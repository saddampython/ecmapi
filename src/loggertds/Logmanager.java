package loggertds;

public class Logmanager {
  private static Loger mylog = null;
  
  private Logmanager(){}
  
  public static synchronized Loger getLogger() {
    if (mylog == null) {
      mylog = new loggerImpl();
    }
    return mylog;
  }
}
