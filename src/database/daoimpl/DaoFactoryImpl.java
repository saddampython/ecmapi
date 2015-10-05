package database.daoimpl;

import database.dao.Clientdao;
import database.dao.Daofactory;
import database.dao.Nodedao;
import database.dao.Taskinstancedao;

public class DaoFactoryImpl implements Daofactory {

  private static DaoFactoryImpl myobj = null;

  private DaoFactoryImpl(){}

/**
 *private DAOFactoryImpl()-doesn't allow to create the instance of DAOFactoryImpl() class.
 *public static synchronized DAOFactoryImpl getInstance()
 *this is used for achieving the singleton class.
 *to get DAOFactoryImpl object instance need to call DAOFactoryImpl.getInstance()
 * Gets the single instance of DaoFactoryImpl.
 *
 * @return single instance of DaoFactoryImpl
 */

  public static synchronized Daofactory getInstance() {
    if (myobj == null) { 
      myobj = new DaoFactoryImpl();
    }
    return myobj;
  }

  
  /* (non-Javadoc)
   * @see database.dao.Daofactory#getTaskinstancedao()
   */
  @Override
  public Taskinstancedao getTaskinstancedao() {
    return new TaskInstanceDaoImpl();
  }

  /* (non-Javadoc)
   * @see database.dao.Daofactory#getNodedao()
   */
  @Override
  public Nodedao getNodedao() {
    return new Nodedaoimpl();
  }

  /* (non-Javadoc)
   * @see database.dao.Daofactory#getClientdao()
   */
  @Override
  public Clientdao getClientdao() {
    return new ClientDaoImpl();
  }
}

