package jemstone.mystuff.dao;

import java.io.IOException;
import java.util.Date;

import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.IdFactory;
import jemstone.mystuff.model.Item;
import jemstone.mystuff.model.Photo;
import jemstone.util.file.AbstractLoadXmlDao;
import jemstone.util.file.DaoException;

import org.xmlpull.v1.XmlPullParserException;


public class LoadXmlDao extends AbstractLoadXmlDao implements XmlConstants {
  private final EntityManager manager;
  private final IdFactoryParser idFactoryParser = new IdFactoryParser();
  private final CategoryParser categoryParser = new CategoryParser();
  private final ItemParser itemParser = new ItemParser();
  private final PhotoParser photoParser = new PhotoParser();

  public LoadXmlDao() throws DaoException {
    super(MY_NAMESPACE, MY_STUFF);

    // Create a NEW EntityManager
    manager = EntityManager.getInstanceNew();

    // Initialise the entity parsers
    parsers.put(ID_FACTORY, idFactoryParser);
    parsers.put(CATEGORY, categoryParser);
    parsers.put(ITEM, itemParser);
    parsers.put(PHOTO, photoParser);
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
  
  private class ItemParser implements EntityParser {
    private Item item;

    @Override
    public void create(int id) {
      if (id == 0) {
        item = manager.getItemFactory().get(id);
      } else {
        item = manager.getItemFactory().create(id);
      }
    }

    @Override
    public void parse(String tag, String value) {
      if (tag.equals(NAME)) {
        item.setName(value);
      } else if (tag.equals(DESCRIPTION)) {
        item.setDescription(value);
      } else if (tag.equals(AMOUNT)) {
        double amount = Double.parseDouble(value);
        item.setAmount(amount);
      } else if (tag.equals(DATE)) {
        Date date = parseDate(value);
        item.setDate(date);
      } else if (tag.equals(CATEGORY_ID)) {
        Category category = manager.getCategory(Integer.parseInt(value));
        item.setCategory(category);
      }
    }
  }
  
  private class PhotoParser implements EntityParser {
    private Photo photo;

    @Override
    public void create(int id) {
      if (id == 0) {
        photo = manager.getPhotoFactory().get(id);
      } else {
        photo = manager.getPhotoFactory().create(id);
      }
      
      // Add to current item
      itemParser.item.addPhoto(photo);
    }

    @Override
    public void parse(String tag, String value) {
      if (tag.equals(NAME)) {
        photo.setName(value);
      } else if (tag.equals(CAPTION)) {
        photo.setCaption(value);
      }
    }
  }
}
