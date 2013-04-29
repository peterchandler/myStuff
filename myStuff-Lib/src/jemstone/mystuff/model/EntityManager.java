package jemstone.mystuff.model;

import java.io.PrintWriter;
import java.util.List;

import jemstone.model.EntityFactory;
import jemstone.util.Printable;
import jemstone.util.Printer;

public class EntityManager implements jemstone.model.EntityManager, Printable {
  public enum F { EntityManager, Categories, Properties };
  
  private static EntityManager manager = null;

  private final IdFactory idFactory = new IdFactory();
  private final CategoryFactory categoryFactory = new CategoryFactory();
  private final PropertyFactory propertyFactory = new PropertyFactory();
  private final ItemFactory itemFactory = new ItemFactory();
  private final BuildingFactory buildingFactory = new BuildingFactory();
  private final VehicleFactory vehicleFactory = new VehicleFactory();
  private final PhotoFactory photoFactory = new PhotoFactory();
  
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

  public CategoryFactory getCategoryFactory() {
    return categoryFactory;
  }

  public PropertyFactory getPropertyFactory() {
    return propertyFactory;
  }

  public PhotoFactory getPhotoFactory() {
    return photoFactory;
  }

  public ItemFactory getItemFactory() {
    return itemFactory;
  }

  public BuildingFactory getBuildingFactory() {
    return buildingFactory;
  }

  public VehicleFactory getVehicleFactory() {
    return vehicleFactory;
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

  public Building newBuilding() {
    return buildingFactory.add();
  }

  public Vehicle newVehicle() {
    return vehicleFactory.add();
  }

  public Photo getPhoto(int id) {
    return photoFactory.get(id);
  }

  public Photo newPhoto() {
    return photoFactory.add();
  }


  /**
   * @return the properties
   */
  public List<Property> getProperties() {
    return propertyFactory.values();
  }

  public Property getProperty(int id) {
    return propertyFactory.get(id);
  }

  public Property getProperty(String name) {
    return propertyFactory.create(name);
  }
  
  public Property newProperty() {
    return propertyFactory.add();
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

  public class BuildingFactory extends EntityFactory<Building> {
    protected Building add() {
      return add(idFactory.nextItemId());
    }
  
    @Override
    protected Building add(int id) {
      return add(new Building(id));
    }
  
    @Override
    protected Building add(String name) {
      Building item = add();
      item.setName(name);
      return item;
    }
  }

  public class VehicleFactory extends EntityFactory<Vehicle> {
    protected Vehicle add() {
      return add(idFactory.nextItemId());
    }

    @Override
    protected Vehicle add(int id) {
      return add(new Vehicle(id));
    }

    @Override
    protected Vehicle add(String name) {
      Vehicle item = add();
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

  public class PropertyFactory extends EntityFactory<Property> {
    protected Property add() {
      return add(idFactory.nextPropertyId());
    }

    @Override
    protected Property add(int id) {
      return add(new Property(id));
    }

    @Override
    protected Property add(String name) {
      Property property = add();
      property.setName(name);
      return property;
    }
  }
}
