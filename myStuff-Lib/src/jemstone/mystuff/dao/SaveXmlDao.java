package jemstone.mystuff.dao;

import java.io.IOException;
import java.util.List;

import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.IdFactory;
import jemstone.util.file.AbstractSaveXmlDao;
import jemstone.util.file.DaoException;

public class SaveXmlDao extends AbstractSaveXmlDao<EntityManager> implements XmlConstants {
  public SaveXmlDao() throws DaoException {
    this(false);
  }

  public SaveXmlDao(boolean throwTestException) throws DaoException {
    super(MY_NAMESPACE, MY_STUFF, throwTestException);
  }

  @Override
  protected void saveContent(EntityManager manager) throws IllegalArgumentException, IllegalStateException, IOException {
    // Save id factory
    final IdFactory idFactory = EntityManager.getInstance().getIdFactory();
    serializer.startTag(NAMESPACE, ID_FACTORY);
    write(NEXT_CATEGORY_ID, idFactory.getNextCategoryId());
    serializer.endTag(NAMESPACE, ID_FACTORY);

    // Save categories, accounts and scenarios
    saveCategories(manager.getCategories());
  }

  private void saveCategories(List<Category> categories) throws IllegalArgumentException, IllegalStateException, IOException {
    serializer.startTag(NAMESPACE, CATEGORIES);
    for (int i=0, size = categories.size();  i < size;  i++) {
      Category category = categories.get(i);

      serializer.startTag(NAMESPACE, CATEGORY);

      write(category);
      write(NAME, category.getName());
      write(DESCRIPTION, category.getDescription());

      serializer.endTag(NAMESPACE, CATEGORY);
    }
    serializer.endTag(NAMESPACE, CATEGORIES);
  }
}
