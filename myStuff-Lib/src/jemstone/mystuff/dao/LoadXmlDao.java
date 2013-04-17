package jemstone.mystuff.dao;

import java.io.IOException;

import jemstone.mystuff.model.EntityManager;
import jemstone.util.file.AbstractLoadXmlDao;
import jemstone.util.file.DaoException;

import org.xmlpull.v1.XmlPullParserException;


public class LoadXmlDao extends AbstractLoadXmlDao implements XmlConstants {
  private EntityManager manager;

  public LoadXmlDao() throws DaoException {
    super(MY_NAMESPACE, MY_STUFF);
  }

  @Override
  protected void parse() throws XmlPullParserException, IOException, DaoException {
    // Create a NEW EntityManager
    manager = EntityManager.getInstanceNew();

    // Parse the file
    parse(null, null);
  }
}
