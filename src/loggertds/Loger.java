package loggertds;

import db.exceptions.ConfigurationNotFoundException;

import java.io.IOException;

public interface Loger {
  public void logWarn(String className,String methodName,String message) 
      throws IOException, ConfigurationNotFoundException;
  
  public void logWarn(String className,String methodName,
      String message,Exception ex) throws IOException, ConfigurationNotFoundException;
  
  public void logError(String className,String methodName,String message) 
      throws IOException, ConfigurationNotFoundException;
  
  public void logError(String className,String methodName,
      String message,Exception ex) throws IOException, ConfigurationNotFoundException;
  
  public void logInfo(String className,String methodName,String message) 
      throws IOException, ConfigurationNotFoundException;
  
  public void logInfo(String className,String methodName,
      String message,Exception ex) throws IOException, ConfigurationNotFoundException;
  
  public void logDebug(String className,String methodName,String message) 
      throws IOException, ConfigurationNotFoundException;
  
  public void logDebug(String className,String methodName,
      String message,Exception ex) throws IOException, ConfigurationNotFoundException;
}
