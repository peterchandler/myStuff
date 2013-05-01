package jemstone.mystuff.model;

public class Building extends Item {
  public enum F { Building, FloorArea, BuildCost };
  
  /** The floor area of the house in square metres/feet */
  private double floorArea;
  
  /** The cost of rebuilding per square metre/foot */
  private double buildCost;
  
  /** Constructed using {@link EntityManager#newBuilding()} */
  Building(int id) {
    super(id);
  }

  public double getFloorArea() {
    return floorArea;
  }

  public void setFloorArea(double floorArea) {
    this.floorArea = floorArea;
  }

  public double getBuildCost() {
    return buildCost;
  }

  public void setBuildCost(double buildCost) {
    this.buildCost = buildCost;
  }
}
