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
  private final ItemParser<Item> itemParser = new ItemParser<Item>();
  private final BuildingParser buildingParser = new BuildingParser();
  private final VehicleParser vehicleParser = new VehicleParser();
  private final PhotoParser photoParser = new PhotoParser(itemParser);
  private final PhotoParser propertyPhotoParser = new PhotoParser(propertyParser);
  private final PhotoParser buildingPhotoParser = new PhotoParser(buildingParser);
  private final PhotoParser vehiclePhotoParser = new PhotoParser(vehicleParser);

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

  @Override
  protected EntityParser getParser(String tag, String parentTag, EntityParser parentParser) {
    EntityParser parser = super.getParser(tag, parentTag, parentParser);
    if (parser == photoParser) {
      if (parentParser == buildingParser) {
        parser = buildingPhotoParser;
      } else if (parentParser == vehicleParser) {
        parser = vehiclePhotoParser;
      } else if (parentParser == propertyParser) {
        parser = propertyPhotoParser;
      }
    }
    return parser;
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
      // Special handling for category with id=0 (Other)
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
  
  private class AddressParser implements EntityParser {
    private Address address;
  
    @Override
    public void create(int id) {
      address = propertyParser.item.getAddress();
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

  private class ItemParser<E extends Item> implements EntityParser {
    protected E item;
  
    @SuppressWarnings("unchecked")
    @Override
    public void create(int id) {
      item = (E) manager.getItemFactory().create(id);
      propertyParser.item.add(item);
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

  private class PropertyParser extends ItemParser<Property> {
    @Override
    public void create(int id) {
      item = manager.getPropertyFactory().create(id);
    }

    @Override
    public void parse(String tag, String value) {
      try {
        switch (Property.F.valueOf(tag)) {
          case Property:
            break;
          case LandArea:
            item.setLandArea(Double.valueOf(value));
            break;
          case LandValue:
            item.setLandValue(Double.valueOf(value));
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
      } catch (IllegalArgumentException e) {
        super.parse(tag, value);
      }
    }
  }
  
  private class BuildingParser extends ItemParser<Building> {
    @Override
    public void create(int id) {
      item = manager.getBuildingFactory().create(id);
      propertyParser.item.add(item);
    }

    @Override
    public void parse(String tag, String value) {
      try {
        switch (Building.F.valueOf(tag)) {
          case Building:
            break;
          case FloorArea:
            item.setFloorArea(Double.valueOf(value));
            break;
        }
      } catch (IllegalArgumentException e) {
        super.parse(tag, value);
      }
    }
  }
  
  private class VehicleParser extends ItemParser<Vehicle> {
    @Override
    public void create(int id) {
      item = manager.getVehicleFactory().create(id);
      propertyParser.item.add(item);
    }

    @Override
    public void parse(String tag, String value) {
      try {
        switch (Vehicle.F.valueOf(tag)) {
          case Color:
            item.setColor(value);
            break;
          case Make:
            item.setMake(value);
            break;
          case Model:
            item.setModel(value);
            break;
          case Registration:
            item.setRegistration(value);
            break;
          case Vehicle:
            break;
          case Year:
            item.setYear(Integer.valueOf(value));
            break;
        }
      } catch (IllegalArgumentException e) {
        super.parse(tag, value);
      }
    }
  }
  
  private class PhotoParser implements EntityParser {
    private ItemParser<?> parentParser;
    private Photo photo;

    public PhotoParser(ItemParser<?> parentParser) {
      super();
      this.parentParser = parentParser;
    }

    @Override
    public void create(int id) {
      photo = manager.getPhotoFactory().create(id);
      parentParser.item.addPhoto(photo);
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
