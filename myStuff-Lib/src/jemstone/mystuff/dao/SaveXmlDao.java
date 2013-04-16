package jemstone.mystuff.dao;

import java.io.Writer;

import jemstone.mystuff.model.EntityManager;
import jemstone.util.file.SaveFileDao;

public class SaveXmlDao implements SaveFileDao<EntityManager>, XmlDAO {
  public SaveXmlDao(boolean throwTestException) {
  }

  @Override
  public void save(EntityManager manager, Writer writer) {
  }
}
