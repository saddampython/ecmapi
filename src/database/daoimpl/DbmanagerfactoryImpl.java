package database.daoimpl;


import database.dao.Dbmanager;
import database.dao.Dbmanagerfactory;

// TODO: Auto-generated Javadoc
/**
 * The Class DbmanagerfactoryImpl.
 */
public class DbmanagerfactoryImpl implements Dbmanagerfactory {

 
  private static DbmanagerfactoryImpl myobj = null;

  /**
   * Instantiates a new dbmanagerfactory impl.
   */
  private DbmanagerfactoryImpl(){}

  /**
   * Gets the single instance of DbmanagerfactoryImpl.
   *
   * @return single instance of DbmanagerfactoryImpl
   */
  public static synchronized Dbmanagerfactory getInstance() {
    if (myobj == null) { 
      myobj = new DbmanagerfactoryImpl();
    }
    return myobj;
  }

  /* (non-Javadoc)
   * @see database.dao.Dbmanagerfactory#getDbmanager()
   */
  @Override
  public Dbmanager getDbmanager() {
    return new Dbmanagerimpl();
  }

}
