package jemstone.mystuff.model;


public class DemoScenario {
  public static final String DEMO_SCENARIO = "Demo Scenario";

  @SuppressWarnings("unused")
  public static EntityManager create() {
    EntityManager manager = EntityManager.getInstanceNew();

    Category income = manager.getCategory("Home Buildings");
    Category food = manager.getCategory("Clothing");
    Category house = manager.getCategory("Appliances");
    Category car = manager.getCategory("Jewellery");
    Category general = manager.getCategory("General");
    Category gifts = manager.getCategory("Vehicles");
    
    return manager;
  }
}
