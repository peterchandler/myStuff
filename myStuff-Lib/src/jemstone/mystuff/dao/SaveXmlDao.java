package jemstone.mystuff.dao;

import java.io.IOException;
import java.util.List;

import jemstone.mystuff.model.Address;
import jemstone.mystuff.model.Building;
import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.IdFactory;
import jemstone.mystuff.model.Item;
import jemstone.mystuff.model.Photo;
import jemstone.mystuff.model.Property;
import jemstone.mystuff.model.Vehicle;
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
    startTag(IdFactory.F.IdFactory);
    
    write(IdFactory.F.NextCategoryId, idFactory.getNextCategoryId());
    write(IdFactory.F.NextItemId, idFactory.getNextItemId());
    write(IdFactory.F.NextPhotoId, idFactory.getNextPhotoId());
    write(IdFactory.F.NextPropertyId, idFactory.getNextPropertyId());
    
    endTag(IdFactory.F.IdFactory);

    // Save categories, items
    saveCategories(manager.getCategories());
    saveProperties(manager.getProperties());
  }

  private void saveCategories(List<Category> categories) throws IllegalArgumentException, IllegalStateException, IOException {
    startTag(EntityManager.F.Categories);
    
    for (Category category : categories) {
      startTag(Category.F.Category);

      write(category);
      write(Category.F.Name, category.getName());
      write(Category.F.Description, category.getDescription());

      endTag(Category.F.Category);
    }
    endTag(EntityManager.F.Categories);
  }

  private void saveProperties(List<Property> properties) throws IllegalArgumentException, IllegalStateException, IOException {
    startTag(EntityManager.F.Properties);
    
    for (Property property : properties) {
      startTag(Property.F.Property);

      writeItem(property);
      write(Property.F.LandArea, property.getLandArea());
      write(Property.F.LandValue, property.getLandValue());
      
      saveAddress(property.getAddress());
      saveBuildings(property.getBuildings());
      saveVehicles(property.getVehicles());
      saveItems(property.getItems());

      endTag(Property.F.Property);
    }
    endTag(EntityManager.F.Properties);  }

  private void saveAddress(Address address) throws IllegalArgumentException, IllegalStateException, IOException {
    startTag(Address.F.Address);
    
    write(Address.F.Street, address.getStreet());
    write(Address.F.Suburb, address.getSuburb());
    write(Address.F.City, address.getCity());
    write(Address.F.PostCode, address.getPostCode());
    write(Address.F.CountryCode, address.getCountryCode());
    
    endTag(Address.F.Address);
  }

  private void saveBuildings(List<Building> buildings) throws IllegalArgumentException, IllegalStateException, IOException {
    for (Building building : buildings) {
      startTag(Building.F.Building);

      writeItem(building);
      write(Building.F.FloorArea, building.getFloorArea());
      write(Building.F.BuildCost, building.getBuildCost());
      
      endTag(Building.F.Building);
    }
  }

  private void saveVehicles(List<Vehicle> vehicles) throws IllegalArgumentException, IllegalStateException, IOException {
    for (Vehicle vehicle : vehicles) {
      startTag(Vehicle.F.Vehicle);

      writeItem(vehicle);
      write(Vehicle.F.Color, vehicle.getColor());
      write(Vehicle.F.Make, vehicle.getMake());
      write(Vehicle.F.Model, vehicle.getModel());
      write(Vehicle.F.Registration, vehicle.getRegistration());
      write(Vehicle.F.Year, vehicle.getYear());
      
      endTag(Vehicle.F.Vehicle);
    }
  }

  private void saveItems(List<Item> items) throws IllegalArgumentException, IllegalStateException, IOException {
    for (Item item : items) {
      startTag(Item.F.Item);
      writeItem(item);
      endTag(Item.F.Item);
    }
  }

  public void writeItem(Item item) throws IOException {
    write(item);
    write(Item.F.Name, item.getName());
    write(Item.F.Description, item.getDescription());
    write(Item.F.PurchaseDate, item.getPurchaseDate());
    write(Item.F.PurchaseAmount, item.getPurchaseAmount());
    write(Item.F.ReplacementAmount, item.getReplacementAmount());
    write(Item.F.CategoryId, item.getCategory());
    
    // Write photo ids
    if (item.hasPhotos()) {
      savePhotos(item.getPhotos());
    }
  }

  private void savePhotos(List<Photo> photos) throws IllegalArgumentException, IllegalStateException, IOException {
    for (Photo photo : photos) {
      startTag(Photo.F.Photo);

      write(photo);
      write(Photo.F.Name, photo.getName());
      write(Photo.F.Caption, photo.getCaption());

      endTag(Photo.F.Photo);
    }
  }
}
