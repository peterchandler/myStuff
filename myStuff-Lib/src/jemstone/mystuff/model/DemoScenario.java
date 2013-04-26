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
    
    Item car = manager.newItem();
    car.setName("BMW 325");
    car.setAmount(23999.0);
    car.setCategory(vehicles);
    car.setDate(DateUtil.calendar(2010, 6, 15).getTime());
    car.addPhoto(manager.newPhoto()).setName("Car Photo 1");
    car.addPhoto(manager.newPhoto()).setName("Car Photo 2");
    
    Item house = manager.newItem();
    house.setAmount(750000.0);
    house.setCategory(home);
    house.setDate(DateUtil.calendar(2001, 9, 1).getTime());
    house.addPhoto(manager.newPhoto()).setName("House Photo");
    
    return manager;
  }
}
