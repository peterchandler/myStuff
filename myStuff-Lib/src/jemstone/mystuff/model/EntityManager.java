package jemstone.mystuff.model;

import java.io.PrintWriter;
import java.util.List;

import jemstone.model.EntityFactory;
import jemstone.util.Printable;
import jemstone.util.Printer;

public class EntityManager implements jemstone.model.EntityManager, Printable {
  private static EntityManager manager = null;

  private final IdFactory idFactory = new IdFactory();
  private final ItemFactory itemFactory = new ItemFactory();
  private final PhotoFactory photoFactory = new PhotoFactory();
  private final CategoryFactory categoryFactory = new CategoryFactory();
  
  private boolean isModified;

  /** Private default constructor to enforce singleton behaviour */
  private EntityManager() {
    categoryFactory.create(0).setName("Other");
  }

  public static EntityManager getInstance() {
    if (manager == null) {
      manager = new EntityManager();
    }
    return manager;
  }

  public static EntityManager getInstanceNew() {
    manager = new EntityManager();
    return manager;
  }

  public IdFactory getIdFactory() {
    return idFactory;
  }

  public ItemFactory getItemFactory() {
    return itemFactory;
  }

  public CategoryFactory getCategoryFactory() {
    return categoryFactory;
  }

  public PhotoFactory getPhotoFactory() {
    return photoFactory;
  }

  /**
   * @return the categories
   */
  public List<Category> getCategories() {
    return categoryFactory.values();
  }

  public Category getCategory(int id) {
    return categoryFactory.get(id);
  }

  public Category getCategory(String name) {
    return categoryFactory.create(name);
  }

  /**
   * @return the items
   */
  public List<Item> getItems() {
    return itemFactory.values();
  }

  public Item getItem(int id) {
    return itemFactory.get(id);
  }

  public Item newItem() {
    return itemFactory.add();
  }

  public Photo getPhoto(int id) {
    return photoFactory.get(id);
  }

  public Photo newPhoto() {
    return photoFactory.add();
  }

  /**
   * Check whether any entities have been added, modified or deleted
   * @return
   */
  @Override
  public boolean isModified() {
    return isModified;
  }
  
  /**
   * Set flag to indicate that one or more entities have been added, modified or deleted
   * @return
   */
  @Override
  public synchronized void setModified(boolean isModified) {
    this.isModified = isModified;
  }
  
  @Override
  public final String toString() {
    return Printer.toString(this);
  }

  @Override
  public void print(PrintWriter out, int depth) {
    Printer.print(out, this, depth);

    for (Category category : categoryFactory) {
      out.println();
      category.print(out, depth+1);
    }
  }

  public class CategoryFactory extends EntityFactory<Category> {
    protected Category add() {
      return add(idFactory.nextCategoryId());
    }

    @Override
    protected Category add(int id) {
      return add(new Category(id));
    }

    @Override
    protected Category add(String name) {
      Category category = add();
      category.setName(name);
      return category;
    }
  }

  public class ItemFactory extends EntityFactory<Item> {
    protected Item add() {
      return add(idFactory.nextItemId());
    }

    @Override
    protected Item add(int id) {
      return add(new Item(id));
    }

    @Override
    protected Item add(String name) {
      Item item = add();
      item.setName(name);
      return item;
    }
  }

  public class PhotoFactory extends EntityFactory<Photo> {
    protected Photo add() {
      return add(idFactory.nextPhotoId());
    }

    @Override
    protected Photo add(int id) {
      return add(new Photo(id));
    }

    @Override
    protected Photo add(String name) {
      Photo photo = add();
      photo.setName(name);
      return photo;
    }
  }
}
