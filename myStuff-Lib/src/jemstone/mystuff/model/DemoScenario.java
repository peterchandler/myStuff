package jemstone.mystuff.model;

import jemstone.util.DateUtil;


public class DemoScenario {
  public static final String DEMO_SCENARIO = "Demo Scenario";

  @SuppressWarnings("unused")
  public static EntityManager create() {
    EntityManager manager = EntityManager.getInstanceNew();

    Category home = manager.getCategory("Home Buildings");
    Category clothing = manager.getCategory("Clothing");
    Category appliances = manager.getCategory("Appliances");
    Category jewellery = manager.getCategory("Jewellery");
    Category general = manager.getCategory("General");
    Category vehicles = manager.getCategory("Vehicles");
    
    Property property = manager.newProperty();
    Address address = property.getAddress();
    address.setStreet("1 Lake Road");
    address.setSuburb("Takapuna");
    address.setCity("Auckland");
    address.setPostCode("0123");
    address.setCountryCode("NZ");

    Vehicle car = property.add(manager.newVehicle());
    car.setCategory(vehicles);
    car.setMake("BMW");
    car.setModel("325i");
    car.setYear(2005);
    car.setPurchaseAmount(23999.0);
    car.setPurchaseDate(DateUtil.calendar(2010, 6, 15).getTime());
    car.addPhoto(manager.newPhoto()).setName("Car Photo 1");
    car.addPhoto(manager.newPhoto()).setName("Car Photo 2");
    
    Building house = property.add(manager.newBuilding());
    house.setCategory(home);
    house.setPurchaseAmount(750000.0);
    house.setPurchaseDate(DateUtil.calendar(2001, 9, 1).getTime());
    house.addPhoto(manager.newPhoto()).setName("House Photo");
    
    return manager;
  }
}
