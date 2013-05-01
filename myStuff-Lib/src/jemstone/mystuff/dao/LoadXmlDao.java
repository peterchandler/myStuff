package jemstone.mystuff.dao;

import java.io.IOException;
import java.util.Date;

import jemstone.mystuff.model.Address;
import jemstone.mystuff.model.Building;
import jemstone.mystuff.model.Category;
import jemstone.mystuff.model.EntityManager;
import jemstone.mystuff.model.IdFactory;
import jemstone.mystuff.model.Item;
import jemstone.mystuff.model.Photo;
import jemstone.mystuff.model.Property;
import jemstone.mystuff.model.Vehicle;
import jemstone.util.file.AbstractLoadXmlDao;
import jemstone.util.file.DaoException;

import org.xmlpull.v1.XmlPullParserException;


public class LoadXmlDao extends AbstractLoadXmlDao implements XmlConstants {
  private final EntityManager manager;
  private final IdFactoryParser idFactoryParser = new IdFactoryParser();
  private final CategoryParser categoryParser = new CategoryParser();
  private final PropertyParser propertyParser = new PropertyParser();
  private final AddressParser addressParser = new AddressParser();
  private final ItemParser itemParser = new ItemParser();
  private final BuildingParser buildingParser = new BuildingParser();
  private final VehicleParser vehicleParser = new VehicleParser();
  private final PhotoParser photoParser = new PhotoParser();

  public LoadXmlDao() throws DaoException {
    super(MY_NAMESPACE, MY_STUFF);

    // Create a NEW EntityManager
    manager = EntityManager.getInstanceNew();

    // Initialise the entity parsers
    parsers.put(IdFactory.F.IdFactory.name(), idFactoryParser);
    parsers.put(Category.F.Category.name(), categoryParser);
    parsers.put(Property.F.Property.name(), propertyParser);
    parsers.put(Property.F.Address.name(), addressParser);
    parsers.put(Building.F.Building.name(), buildingParser);
    parsers.put(Vehicle.F.Vehicle.name(), vehicleParser);
    parsers.put(Item.F.Item.name(), itemParser);
    parsers.put(Photo.F.Photo.name(), photoParser);
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

      switch (IdFactory.F.valueOf(tag)) {
        case IdFactory:
          break;
        case NextCategoryId: 
          idFactory.setNextCategoryId(nextId);
          break;
        case NextItemId:
          idFactory.setNextItemId(nextId);
          break;
        case NextPhotoId:
          idFactory.setNextPhotoId(nextId);
          break;
        case NextPropertyId:
          idFactory.setNextPropertyId(nextId);
          break;
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
      switch (Category.F.valueOf(tag)) {
        case Category:
          break;
        case Name: 
          category.setName(value); 
          break;
        case Description: 
          category.setDescription(value); 
          break;
      }
    }
  }
  
  private class PropertyParser implements EntityParser {
    private Property property;

    @Override
    public void create(int id) {
      if (id == 0) {
        property = manager.getPropertyFactory().get(id);
      } else {
        property = manager.getPropertyFactory().create(id);
      }
    }

    @Override
    public void parse(String tag, String value) {
      switch (Property.F.valueOf(tag)) {
        case Property:
          break;
        case Name: 
          property.setName(value); 
          break;
        case Description: 
          property.setDescription(value); 
          break;
        case LandArea:
          property.setLandArea(Double.valueOf(value));
          break;
        case LandValue:
          property.setLandValue(Double.valueOf(value));
          break;
        case Address:
          break;
        case Buildings:
          break;
        case Vehicles:
          break;
        case Items:
          break;
      }
    }
  }
  
  private class AddressParser implements EntityParser {
    private Address address;

    @Override
    public void create(int id) {
      address = propertyParser.property.getAddress();
    }

    @Override
    public void parse(String tag, String value) {
      switch (Address.F.valueOf(tag)) {
        case Address:
          break;
        case City:
          address.setCity(value);
          break;
        case CountryCode:
          address.setCountryCode(value);
          break;
        case PostCode:
          address.setPostCode(value);
          break;
        case Street:
          address.setStreet(value);
          break;
        case Suburb:
          address.setSuburb(value);
          break;
      }
    }
  }

  private class ItemParser implements EntityParser {
    protected Item item;

    @Override
    public void create(int id) {
      if (id == 0) {
        item = manager.getItemFactory().get(id);
      } else {
        item = manager.getItemFactory().create(id);
      }
      propertyParser.property.add(item);
    }

    @Override
    public void parse(String tag, String value) {
      switch (Item.F.valueOf(tag)) {
        case Category:
          break;
        case Description:
          item.setDescription(value);
          break;
        case Item:
          break;
        case Name:
          item.setName(value);
          break;
        case Photos:
          break;
        case PurchaseAmount:
          item.setPurchaseAmount(Double.parseDouble(value));
          break;
        case PurchaseDate:
          Date date = parseDate(value);
          item.setPurchaseDate(date);
          break;
        case ReplacementAmount:
          item.setReplacementAmount(Double.parseDouble(value));
          break;
        case CategoryId:
          Category category = manager.getCategory(Integer.parseInt(value));
          item.setCategory(category);
          break;
      }
    }
  }
  
  private class BuildingParser implements EntityParser {
    private Building building;
    
    @Override
    public void create(int id) {
      if (id == 0) {
        building = manager.getBuildingFactory().get(id);
      } else {
        building = manager.getBuildingFactory().create(id);
      }
      
      itemParser.item = building;
      propertyParser.property.add(building);
    }

    @Override
    public void parse(String tag, String value) {
      try {
        switch (Building.F.valueOf(tag)) {
          case Building:
            break;
          case BuildCost:
            building.setBuildCost(Double.valueOf(value));
            break;
          case FloorArea:
            building.setFloorArea(Double.valueOf(value));
            break;
        }
      } catch (IllegalArgumentException e) {
        itemParser.parse(tag, value);
      }
    }
  }
  
  private class VehicleParser implements EntityParser {
    private Vehicle vehicle;
    
    @Override
    public void create(int id) {
      if (id == 0) {
        vehicle = manager.getVehicleFactory().get(id);
      } else {
        vehicle = manager.getVehicleFactory().create(id);
      }
      itemParser.item = vehicle;
      propertyParser.property.add(vehicle);
    }

    @Override
    public void parse(String tag, String value) {
      try {
        switch (Vehicle.F.valueOf(tag)) {
          case Color:
            vehicle.setColor(value);
            break;
          case Make:
            vehicle.setMake(value);
            break;
          case Model:
            vehicle.setModel(value);
            break;
          case Registration:
            vehicle.setRegistration(value);
            break;
          case Vehicle:
            break;
          case Year:
            vehicle.setYear(Integer.valueOf(value));
            break;
        }
      } catch (IllegalArgumentException e) {
        itemParser.parse(tag, value);
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
      switch (Photo.F.valueOf(tag)) {
        case Caption:
          photo.setCaption(value);
          break;
        case Name:
          photo.setName(value);
          break;
        case Photo:
          break;
      }
    }
  }
}
