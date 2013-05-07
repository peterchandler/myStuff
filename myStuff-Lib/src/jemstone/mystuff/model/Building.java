package jemstone.mystuff.model;

public class Building extends Item {
  public enum F { Building, FloorArea };
  
  /** The floor area of the house in square metres/feet */
  private double floorArea;
  
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
}
