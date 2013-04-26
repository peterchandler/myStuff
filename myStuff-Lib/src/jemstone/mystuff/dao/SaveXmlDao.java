package jemstone.mystuff.dao;

import java.io.IOException;
import java.util.List;

import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.IdFactory;
import jemstone.mystuff.model.Item;
import jemstone.mystuff.model.Photo;
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
    final IdFactory idFactory = manager.getIdFactory();
    serializer.startTag(NAMESPACE, ID_FACTORY);
    write(NEXT_CATEGORY_ID, idFactory.getNextCategoryId());
    write(NEXT_ITEM_ID, idFactory.getNextItemId());
    write(NEXT_PHOTO_ID, idFactory.getNextPhotoId());
    serializer.endTag(NAMESPACE, ID_FACTORY);

    // Save categories, items
    saveCategories(manager.getCategories());
    saveItems(manager.getItems());
  }

  private void saveCategories(List<Category> categories) throws IllegalArgumentException, IllegalStateException, IOException {
    serializer.startTag(NAMESPACE, CATEGORIES);
    for (Category category : categories) {
      serializer.startTag(NAMESPACE, CATEGORY);

      write(category);
      write(NAME, category.getName());
      write(DESCRIPTION, category.getDescription());

      serializer.endTag(NAMESPACE, CATEGORY);
    }
    serializer.endTag(NAMESPACE, CATEGORIES);
  }

  private void saveItems(List<Item> items) throws IllegalArgumentException, IllegalStateException, IOException {
    serializer.startTag(NAMESPACE, ITEMS);
    for (Item item : items) {
      serializer.startTag(NAMESPACE, ITEM);

      write(item);
      write(NAME, item.getName());
      write(DESCRIPTION, item.getDescription());
      write(DATE, item.getDate());
      write(AMOUNT, item.getAmount());
      write(CATEGORY_ID, item.getCategory());
      
      // Write photo ids
      if (item.hasPhotos()) {
        savePhotos(item.getPhotos());
      }
      serializer.endTag(NAMESPACE, ITEM);
    }
    serializer.endTag(NAMESPACE, ITEMS);
  }

  private void savePhotos(List<Photo> photos) throws IllegalArgumentException, IllegalStateException, IOException {
    serializer.startTag(NAMESPACE, PHOTOS);
    for (Photo photo : photos) {
      serializer.startTag(NAMESPACE, PHOTO);

      write(photo);
      write(NAME, photo.getName());
      write(CAPTION, photo.getCaption());

      serializer.endTag(NAMESPACE, PHOTO);
    }
    serializer.endTag(NAMESPACE, PHOTOS);
  }
}
