package configuration;

import db.exceptions.ConfigurationException;
import db.exceptions.ConfigurationNotFoundException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

import loggertds.loglevel;
// TODO: Auto-generated Javadoc
/**
 * The Class Tdsconfiguration.
 */
public class Tdsconfiguration {

  /** The driver. */
  private String driver;

  /** The user name. */
  private String userName;

  /** The password. */
  private String password;

  /** The url. */
  private String url;

  /** The conurl. */
  private String conurl;
  
  private String logfilepath;
  
  private String logLevel;
  

  /** The configuration. */
  private static Tdsconfiguration configuration = null;

  /**
   * Instantiates a new tdsconfiguration.
   * throws ParserConfigurationException 
   * throws IOException 
   * throws SAXException 
   */
  private Tdsconfiguration() {
    try {
      File file = new File("src/configuration/tds.xml");
      DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(file);
      driver = document.getElementsByTagName("driver").item(0).getTextContent();
      userName = document.getElementsByTagName("username").item(0).getTextContent();
      password = document.getElementsByTagName("password").item(0).getTextContent();
      url = document.getElementsByTagName("url").item(0).getTextContent();
      logfilepath = document.getElementsByTagName("logfilepath").item(0).getTextContent();
      logLevel = document.getElementsByTagName("loglevel").item(0).getTextContent();
    
    } catch (ParserConfigurationException e) {
      //System.out.println(e);
    } catch (SAXException e) {
      //System.out.println(e);
    } catch (IOException e) {
      //System.out.println(e);
    }
  }
  
    
/**
 *private TDSConfiguration()-this will not allow instantiate the TDSConfiguration object.
 *public static synchronized TDSConfiguration getInstance()-will help in achieving singleton class.
 *to get the TDSConfiguration object need to call TDSConfiguration.getInstance()
 *Gets the single instance of TDSConfiguration.
 *
 * @return single instance of TDSConfiguration
 */
  public static synchronized Tdsconfiguration getInstance() {

    if (configuration == null) {
      configuration = new Tdsconfiguration();
    }
    return configuration;
  }

/**
 *Need to have different functions to get driver and to get databaseConnection.
 *We need getDriver()to maintain the Driver variable as private.
 * Gets the driver.
 *
 * @return the driver
 */
  public String getDriver() {
    return driver;
  }
  
  public String getLogFilePath() {
    return logfilepath;
  }
  
  public int getLogLevel() {
	  
    if ( logLevel.trim().equalsIgnoreCase("ERROR")) 
      return loglevel.ERROR;
    else if (logLevel.trim().equalsIgnoreCase("WARNING"))
      return loglevel.WARNING;
    else if (logLevel.trim().equalsIgnoreCase("DEBUG"))
      return loglevel.DEBUG;
    else if (logLevel.trim().equalsIgnoreCase("INFO"))
      return loglevel.INFO;
    return 0;
  }
  
  /**
   * Gets the DB connection string.
   *
   * @return the DB connection string
   * @throws ConfigurationNotFoundException the configuration not found exception
   */
  public String getDbconnectionstring() throws ConfigurationNotFoundException {
    if ( userName.isEmpty() || password.isEmpty() || url.isEmpty()) {
      throw new ConfigurationNotFoundException();
    }      
    conurl = url + "?user=" + userName + "&password=" + password + "";      
    return conurl;
  }
}
