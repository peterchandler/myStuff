package jemstone.mystuff.dao;

import java.io.IOException;

import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.IdFactory;
import jemstone.util.file.AbstractLoadXmlDao;
import jemstone.util.file.DaoException;

import org.xmlpull.v1.XmlPullParserException;


public class LoadXmlDao extends AbstractLoadXmlDao implements XmlConstants {
  private final EntityManager manager;
  private final IdFactoryParser idFactoryParser = new IdFactoryParser();
  private final CategoryParser categoryParser = new CategoryParser();

  public LoadXmlDao() throws DaoException {
    super(MY_NAMESPACE, MY_STUFF);

    // Create a NEW EntityManager
    manager = EntityManager.getInstanceNew();

    // Initialise the entity parsers
    parsers.put(ID_FACTORY, idFactoryParser);
    parsers.put(CATEGORY, categoryParser);
  }

  @Override
  protected void parse() throws XmlPullParserException, IOException, DaoException {
    parse(null, null);
  }

  private class IdFactoryParser implements EntityParser {
    private IdFactory idFactory;

    @Override
    public void create(int id) {
      idFactory = manager.getIdFactory();
    }

    @Override
    public void parse(String tag, String value) {
      final int nextId = Integer.parseInt(value);

      if (tag.equals(NEXT_CATEGORY_ID)) {
        idFactory.setNextCategoryId(nextId);
      }
    }
  }
  
  private class CategoryParser implements EntityParser {
    private Category category;

    @Override
    public void create(int id) {
      if (id == 0) {
        category = manager.getCategoryFactory().get(id);
      } else {
        category = manager.getCategoryFactory().create(id);
      }
    }

    @Override
    public void parse(String tag, String value) {
      if (tag.equals(NAME)) {
        category.setName(value);
      } else if (tag.equals(DESCRIPTION)) {
        category.setDescription(value);
      }
    }
  }
}
