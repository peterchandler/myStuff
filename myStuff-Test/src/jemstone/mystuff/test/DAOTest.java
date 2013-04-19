package jemstone.mystuff.test;

import java.io.StringReader;
import java.io.StringWriter;

import jemstone.mystuff.dao.LoadXmlDao;
import jemstone.mystuff.dao.SaveXmlDao;
import jemstone.mystuff.model.DemoScenario;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.compare.EntityManagerComparator;
import jemstone.util.compare.CompareException;
import jemstone.util.file.DaoException;
import jemstone.util.log.Logger;
import android.test.AndroidTestCase;

public class DAOTest extends AndroidTestCase {
  private Logger log = Logger.getLogger(getClass());
  
  public void testSaveDao() throws DaoException, CompareException, AssertionError {
    EntityManager manager1 = DemoScenario.create(); 
    
    StringWriter writer = new StringWriter();

    SaveXmlDao saveDao = new SaveXmlDao();
    saveDao.save(manager1, writer);
    
    log.debug(writer);
    
    LoadXmlDao loadDao = new LoadXmlDao();
    loadDao.load(new StringReader(writer.toString()));
    
    EntityManager manager2 = EntityManager.getInstance();
    
    log.debug(manager2);
    
    new EntityManagerComparator(true).assertEquals(manager1, manager2);
  }
}
