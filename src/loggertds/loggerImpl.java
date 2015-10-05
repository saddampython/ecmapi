package loggertds;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import configuration.Tdsconfiguration;
import db.exceptions.ConfigurationNotFoundException;

public class loggerImpl implements Loger,Runnable {
  
  int logLevel = 0;
  static Tdsconfiguration tdscon = null;
  private String content_log = "";
  private String logerFilePath;
  private String className;
  private String methodName;
  private String message;
  private Exception excep = null;
  private String level;
  
  Thread logThread = null;
  
  loggerImpl() {   
    
    tdscon = Tdsconfiguration.getInstance();
    logerFilePath = tdscon.getLogFilePath();
    logLevel = tdscon.getLogLevel();    
  }
  
  @Override
  public void logWarn(String className, String methodName, String message) 
      throws IOException, ConfigurationNotFoundException {
     
    if (logLevel != 0 && logLevel >= loglevel.WARNING) { 
      this.className = className;
      this.methodName = methodName;
      this.message = message; 
 
      if (logLevel != 0 && logLevel >= loglevel.WARNING) {
        logThread = new Thread(this,"WARNING");
        logThread.start();      
      }
    }
  }

  @Override
  public void logWarn(String className, String methodName, String message,Exception ex) 
      throws IOException, ConfigurationNotFoundException {
    this.className = className;
    this.methodName = methodName;
    this.message = message;
    this.excep = ex; 

    if (logLevel != 0 && logLevel >= loglevel.WARNING) {
      logThread = new Thread(this,"WARNING");
      logThread.start();
    }
  }

  @Override
  public void logError(String className, String methodName, String message) 
      throws IOException, ConfigurationNotFoundException {
    this.className = className;
    this.methodName = methodName;
    this.message = message;
    
    if (logLevel != 0 && logLevel >= loglevel.ERROR) {
      logThread = new Thread(this,"ERROR");
      logThread.start();      
    }
  }

  @Override
  public void logError(String className, String methodName, String message,Exception ex) 
      throws IOException, ConfigurationNotFoundException {
    this.className = className;
    this.methodName = methodName;
    this.message = message;
    this.excep = ex;
    
    if (logLevel != 0 && logLevel >= loglevel.ERROR) {
      logThread = new Thread(this,"ERROR");
      logThread.start();
    }
  }

  @Override
  public void logInfo(String className, String methodName, String message) 
      throws IOException, ConfigurationNotFoundException {
    this.className = className;
    this.methodName = methodName;
    this.message = message;

    if (logLevel != 0 && logLevel >= loglevel.INFO) {
      logThread = new Thread(this,"INFO");
      logThread.start();
    }
  }

  @Override
  public void logInfo(String className, String methodName, String message,Exception ex) 
      throws IOException, ConfigurationNotFoundException {
    this.className = className;
    this.methodName = methodName;
    this.message = message;
    this.excep = ex;

    if (logLevel != 0 && logLevel >= loglevel.INFO) {
      logThread = new Thread(this,"INFO");
      logThread.start();
    }
  }

  @Override
  public void logDebug(String className, String methodName, String message) 
      throws IOException, ConfigurationNotFoundException {

    this.className = className;
    this.methodName = methodName;
    this.message = message;
    
    if (logLevel != 0 && logLevel >= loglevel.DEBUG) {
      logThread = new Thread(this,"DEBUG");
      logThread.start();      
    }
  }

  @Override
  public void logDebug(String className, String methodName, String message,Exception ex) 
      throws IOException, ConfigurationNotFoundException {
    this.className = className;
    this.methodName = methodName;
    this.message = message;
    this.excep = ex;

    if (logLevel != 0 && logLevel >= loglevel.DEBUG) {
      logThread = new Thread(this,"DEBUG");
      logThread.start();
    }
  }
  
  public String contentBuilder(String logLevel,String className,String methodName,String message) {

    String content = new String("[" + getCurrentDate() + "]" + "[" + logLevel + "]" + "[" 
        + className + "]" + "[" + methodName + "]:[" + message + "]");
    return content;
  }
  
  public String contentBuilder(String logLevel,String className,String methodName,String message,Exception ex) {

    String content = new String("[" + getCurrentDate() + "]" + "[" + logLevel + "]" + "[" 
        + className + "]" + "[" + methodName + "]:[" + message + "] [" + ex + "]");
    return content;
  }
  
  public String getCurrentDate() {

    Date myDate = new Date();
    SimpleDateFormat mdyFormat = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss a");
    String mdy = mdyFormat.format(myDate);
    return mdy;
  }
  
  public void run() {

    try {
      synchronized (this) {
        String content;
        if ( excep == null ) {
          content = contentBuilder(logThread.getName(),className,methodName,message);
        }
        else {
          content = contentBuilder(logThread.getName(),className,methodName,message,excep);
        }
        
        System.out.println("content is" + content);
        FileWriter writer = new FileWriter(logerFilePath,true);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        bufferedWriter.write(content);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();        
       
      }
    } catch (Exception e) {
      //e.printStackTrace();
    }
  }
 
}
