import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.LogManager;

import db.exceptions.ConfigurationNotFoundException;
import loggertds.Loger;
import loggertds.Logmanager;




// TODO: Auto-generated Javadoc
/**
 * The Class DemoExecution.
 */
public class DemoExecution {
 
  /**
   * The main method.
   *
   * @param args the arguments
 * @throws IOException 
 * @throws ConfigurationNotFoundException 
   */
  public static void main(String[] args) {
    try {
     Loger log = Logmanager.getLogger();
     log.logDebug("hi","hello","how are you");
     Thread.sleep(100);
     System.out.println("bye1");

     log.logDebug("h","hre","are");
     Thread.sleep(100);
     System.out.println("bye2");

     log.logInfo("hi","llo","how");
     Thread.sleep(100);
     System.out.println("bye3");

     log.logDebug("hi","he","are you");
     Thread.sleep(100);
     System.out.println("bye4");
     
    } catch (Exception e) {
    	e.printStackTrace();
    }
  }
}